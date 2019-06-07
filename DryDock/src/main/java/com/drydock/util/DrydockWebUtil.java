package com.drydock.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.dto.UserInfoDTO;

@Component
public class DrydockWebUtil {


	@Autowired
	private HandleTokenUtil handleTokenUtil;

	public DrydockWebUtil(){
		/*
		 * 
		 * Default Constructor
		 */
	}

	//Method to construct a JWT

	public BasicInfoDTO createTokenFunctiondto(UserInfoDTO dto) throws Exception {
		BasicInfoDTO info = new BasicInfoDTO();
		info.setDeptId(dto.getDeptId());
		//info.setFuncList(funcList); menu function
		info.setIdentifyString(dto.getIdentifyString());
		info.setOrganizationName(dto.getOrganizationName());
		info.setOrgId(dto.getOrgId());
		info.setToken(handleTokenUtil.createJWT(0, dto));
		info.setUserCode(dto.getUserCode());
		info.setUserId(dto.getUserId());

		return info;



	}

	public static boolean isValidEmail(String email) 
	{ 
		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"; 

		Pattern pat = Pattern.compile(emailRegex); 
		if (email == null) 
			return false; 
		return pat.matcher(email).matches(); 
	}


	public static String encryptData(String key, String data) throws NoSuchAlgorithmException
	{
		StringBuilder hash = new StringBuilder();
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest((key+data).getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			throw e;
			// handle error here.}
		}

		return hash.toString();
	}
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.println(encryptData("ORG1_SA", "pass@1234"));
	}

	public static String milisToDateAsStringOrgSpec(String longValueAsString, String dateFormat, String timeFormat) throws Exception {

		String date ="";
		if(null!=longValueAsString && !longValueAsString.isEmpty()){
			Long longVal=new Long(longValueAsString);
			String dateTimeFormat=dateFormat+" "+timeFormat;
			SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
			sdf.setTimeZone(TimeZone.getDefault());
			try {
				date=sdf.format(new Date(longVal));
			} catch (Exception exp) {
				throw exp;
			}
		}

		return date;
	}


}