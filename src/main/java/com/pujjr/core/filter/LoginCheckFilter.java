package com.pujjr.core.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
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
import javax.servlet.http.HttpSession;


public class LoginCheckFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request; 
	    HttpServletResponse res = (HttpServletResponse) response; 
	    HttpSession session = req.getSession(true);
	    
	 
	    if(req.getMethod().equalsIgnoreCase("OPTIONS"))
	    {
	    	res.setStatus(200);
	    }else
	    {
	    	System.out.println(session.getAttribute("name"));
	    	final String authHeader = req.getHeader("Authorization");

	    	
	        try 
	        {
	            final Claims claims = Jwts.parser().setSigningKey("secretkey")
	                .parseClaimsJws(authHeader).getBody();
	            request.setAttribute("claims", claims);
	        	 filterChain.doFilter(request,response); 
	        	
	        }
	        catch (Exception e) 
	        {
	            res.setStatus(401);
	            return;
	        }
	        
	       
	    }
	    
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
