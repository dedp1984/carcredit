package com.pujjr.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseStatus;

public class CorsFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request; 
	    HttpServletResponse res = (HttpServletResponse) response; 
	    res.setHeader("Access-Control-Allow-Origin", "*");
	    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
	    res.setHeader("Access-Control-Max-Age", "3600");
	    res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type,accept,  X-Codingpedia,Authorization");
	    res.setHeader("Access-Control-Expose-Headers", "X-My-Custom-Header, X-Another-Custom-Header");
	    if(req.getMethod().equalsIgnoreCase("OPTIONS"))
	    {
	    	res.setStatus(200);
	    }else{
	    	filterChain.doFilter(req, res);
	    }
	    
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
