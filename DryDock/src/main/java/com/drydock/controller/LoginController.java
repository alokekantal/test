package com.drydock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.dto.UserInfoDTO;
import com.drydock.entity.UserCurrentShip;
import com.drydock.entity.UserDetail;
import com.drydock.service.CoreService;
import com.drydock.util.DrydockConstant;
import com.drydock.util.DrydockWebUtil;
import com.drydock.util.HandleTokenUtil;

@RestController
@RequestMapping("login")
@MultipartConfig
public class LoginController {

	@Autowired
	CoreService coreService;
	
	@Autowired
	HandleTokenUtil handleTokenUtil;
	
	
	@Autowired
	DrydockWebUtil drydockWebUtil;
	
	@Autowired
	private DatabaseConfiguration databaseConfiguration;

	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/home")
	public String welcome(HttpServletRequest request,
	        HttpServletResponse response, Map<String, Object> model) {
		String locale=request.getParameter("locale");
		if(null==locale)
			locale="en";
		LocaleResolver localeResolver=RequestContextUtils.getLocaleResolver(request);
		localeResolver.setLocale(request, response, StringUtils.parseLocaleString(locale));
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,
	        HttpServletResponse response,Map<String, Object> model) throws Exception {
		String userName=request.getParameter("username");
		UserDetail user=null;
		user = coreService.getUser(userName, request.getParameter("userpass")); //remove this from here later as per requirement
		Long userId=Long.valueOf(user.getUser_id());
		Long orgId=Long.valueOf(user.getOrgId());
		String token=request.getParameter("Token");
		UserInfoDTO info= new UserInfoDTO();
		info.setUserCode(userName);
		info.setUserId(userId);
		info.setOrgId(orgId);
		info.setToken(token);
		drydockWebUtil.createTokenFunctiondto(info);
		model.put("message", "Login Successful by user "+userName);
		model.put(DrydockConstant.BASICINFODTO, info);
		return "dashboard";
	}
	
	@RequestMapping(value="/validateLogin" , method = RequestMethod.POST)
	public @ResponseBody Object validateLogin(HttpServletRequest request,
	        HttpServletResponse response, @RequestParam("userCode")String userCode, @RequestParam("passcode")String passcode) {
		UserDetail user=null;
		List<Object> retunlist= new ArrayList<>();
		try {
			user = coreService.getUser(userCode, passcode);
			if(null!=user){
				UserInfoDTO info= new UserInfoDTO();
				info.setUserCode(user.getUserCode());
				info.setUserId(user.getUser_id());
				info.setUserFirstName(user.getFirstname());
				info.setDeptId(user.getDeptId());
				info.setOrgId(user.getOrgId());
				info.setUserType(user.getUserType());
				info.setToken(handleTokenUtil.createJWT(0, info ));
				retunlist.add(info);
				retunlist.add(databaseConfiguration.getString(DrydockConstant.APPLICATION_TYPE_KEY));
				
				if(DrydockConstant.USER_TYPE_SHIP.equalsIgnoreCase(user.getUserType())){
					retunlist.add(user.getShipid());
				}else{
					BasicInfoDTO basicInfo = new BasicInfoDTO();
					UserCurrentShip userCurrentShip = coreService.loadUserCurrentShip(user.getUser_id(), basicInfo);
					if(null!=userCurrentShip && userCurrentShip.getShipid()!=0){
						retunlist.add(userCurrentShip.getShipid());
					}else{
						retunlist.add(databaseConfiguration.getString(DrydockConstant.APPLICATION_SHIP_ID));
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CoreController.sendError(response, "Invalid Username and Password");
		}
		return retunlist;
	}

}