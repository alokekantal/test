package com.drydock.web.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.drydock.dto.BasicInfoDTO;
import com.drydock.util.DrydockConstant;
import com.drydock.util.HandleTokenUtil;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
 
	@Autowired
	HandleTokenUtil handleTokenUtil;
	
    @Override
    public void doFilter(ServletRequest req,
			ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
  
    	final HttpServletRequest request = (HttpServletRequest) req;
    	final HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url=url.replace(contextPath, "");
        String authHeader ="";
        if(!url.contains("/validateLogin") && !url.contains("/forgotPassword") && !url.endsWith(".json") && !url.contains("/error")){
        	if(url.endsWith(".action")){
        		authHeader = request.getParameter("authorization");
        	}else{
        		authHeader = request.getHeader("authorization");
        	}
        }	
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {
        	if(authHeader==""){
        		chain.doFilter(req, res);
        	}
//        	System.out.println("Starting a task for req : "+req.ge;
        	else{
            if (authHeader == null || !authHeader.startsWith(DrydockConstant.TOKEN_AUTH_PREFIX)) {
            	
            	
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(7);

            try {
                final Claims claims = handleTokenUtil.parseJWT(token);
                BasicInfoDTO info= new BasicInfoDTO();
//                info.setDeptId(claims.get));
        		//info.setFuncList(funcList); menu function
        		info.setIdentifyString(claims.getSubject());
        		info.setOrganizationName(claims.getAudience());
        		info.setUserFirstName(claims.getIssuer());
        		info.setUserType((String) claims.get(DrydockConstant.USER_TYPE));
        		Integer orgId = (Integer) claims.get(DrydockConstant.ORG_ID);
        		info.setOrgId(orgId.longValue());
        		/*info.setUserCode(claims.getUserCode());*/
        		info.setUserId(Long.valueOf(claims.getId()));
                request.setAttribute("info", info);
            } catch (final SignatureException e) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        	}
        }
        
        /*System.out.println("Starting a task for req : "+req.getRequestURI());
        String tokenValidTimeInString=databaseConfiguration.getString(DrydockConstant.TOKEN_VAILD_IN_MILISEC);
        System.out.println("tokenValidTimeInString - " + tokenValidTimeInString);
        chain.doFilter(request, response); 
        System.out.println("Committing a task for req :"+req.getRequestURI());*/
    }
 
      @Override
      public void destroy() {}

      @Override
      public void init(FilterConfig arg0) throws ServletException {}

}