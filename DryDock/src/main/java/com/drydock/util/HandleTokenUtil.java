package com.drydock.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drydock.dto.UserInfoDTO;

@Component
public class HandleTokenUtil {
	String secretKey = DrydockConstant.JWT_SECRET_CODE;

	@Autowired
	private DatabaseConfiguration databaseConfiguration;

	public HandleTokenUtil(){
		/*
		 * Default Constructor
		 */
	}
	//Method to construct a JWT

	public String createJWT(long idleTimeInMiliSec,UserInfoDTO dto) throws Exception {
		String userId= Long.toString(dto.getUserId()==null?0:dto.getUserId());
		String identifyStr=dto.getIdentifyString();
		String name=dto.getUserFirstName();
		String userCode = dto.getUserCode();
		List userRoleList = (List) dto.getUserRoleList();
		String currentLocale=dto.getCurrentLocale();
		String organizationName=dto.getOrganizationName();
		Long orgId=dto.getOrgId();
		Long deptId=dto.getDeptId();
		String tokenValidTimeInString=databaseConfiguration.getString(DrydockConstant.TOKEN_VAILD_IN_MILISEC);
		long ttlMillis;
		try{
			ttlMillis=Long.valueOf(tokenValidTimeInString)-idleTimeInMiliSec;
		}
		catch(Exception e){
			ttlMillis=3600000;
		}
		//The JWT signature algorithm we will be using to sign the token

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = secretKey.getBytes();
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder();
		Map<String, Object> hmpCLaim = new HashMap();
		hmpCLaim.put(DrydockConstant.CURRENT_LOCALE, currentLocale);
		//hmpCLaim.put(DrydockConstant.USER_ROLE_LIST, userRoleList);
		hmpCLaim.put(DrydockConstant.USERCODE, userCode);
		hmpCLaim.put(DrydockConstant.ORG_ID, orgId);
		hmpCLaim.put(DrydockConstant.DEPT_ID, deptId);
		hmpCLaim.put(DrydockConstant.USER_TYPE, dto.getUserType());
		builder.setClaims(hmpCLaim);
		builder.setId(userId+"")
		.setIssuedAt(now)
		.setSubject(identifyStr)
		.setIssuer(name)
		.setAudience(organizationName)
		.signWith(signatureAlgorithm, signingKey);
		//if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		//Builds the JWT and serializes it to a compact, URL-safe string
		String newToken = builder.compact();
		return DrydockConstant.TOKEN_AUTH_PREFIX + newToken;
	}
	
	
	//Sample method to validate and read the JWT
	public Claims parseJWT(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwt).getBody();
	}
	
	public static void main(String[] args){
		try {
			System.out.println(new HandleTokenUtil().createJWT(0, new UserInfoDTO()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}