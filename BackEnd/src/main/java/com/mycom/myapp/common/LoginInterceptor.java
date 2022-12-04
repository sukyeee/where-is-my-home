 package com.mycom.myapp.common;


 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.myapp.user.dto.UserDto;


 @Component
 public class LoginInterceptor implements HandlerInterceptor {
	 @Override
	 public boolean preHandle(
		 HttpServletRequest request,
		 HttpServletResponse response,
		 Object handler) throws Exception {
		
		 System.out.println("LoginInterceptor : preHandle !!!");
		
		 // CORS put, delete 요청에 대한 허락
		 if (request.getMethod().equals("OPTIONS")) return true;
		
		 HttpSession session = request.getSession();
		 UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		 if (userDto == null) {
			 Gson gson = new Gson();
			 JsonObject jsonObject = new JsonObject();
			 jsonObject.addProperty("result", "login");
			 String jsonStr = gson.toJson(jsonObject);
			 response.getWriter().write(jsonStr);
			
			 return false;
		 }
		
		 return true; // 통과
	
	 }
 }