package com.pujjr.core.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CorsInterceptor extends HandlerInterceptorAdapter {
	 
		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			response.setHeader("Access-Control-Allow-Origin", "**");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type,accept,  X-Codingpedia,Authorization");
		    response.setHeader("Access-Control-Expose-Headers", "X-My-Custom-Header, X-Another-Custom-Header");
		    
		    
			return true;
		}

		@Override
		public void afterCompletion(HttpServletRequest request,
				HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			// TODO Auto-generated method stub
			super.afterCompletion(request, response, handler, ex);
			
		}
	 
	}