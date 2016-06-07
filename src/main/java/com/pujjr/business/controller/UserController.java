package com.pujjr.business.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pujjr.business.domain.User;
@Controller
public class UserController {
/*
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable String id,String sex){
		User user=new User();
		user.setId("1");
		user.setName("dengpan");
		user.setPasswd("1111");
		return user;
	};
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getUserList(User usertmp){

		ArrayList users=new ArrayList();
		for(int i=0;i<10;i++){
			User user=new User();
			user.setId(String.valueOf(i));
			user.setName("dengpan"+i);
			user.setPasswd("passwd"+i);
			users.add(user);
		}
		PageVo page=new PageVo();
		page.setItems(users);
		page.setTotalItems(100);
		return page;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)

	@ResponseBody
	public User update(	@RequestBody User usertmp,HttpServletResponse response) throws Exception{
		//System.out.println(name+passwd);
		if(usertmp.getName().equals("dengpan")){
			User user=new User();
			user.setName("dengpan");
			user.setPasswd("passwd");
			return user;
		}else
		{
			response.setStatus(403);
			return null;
		}
		
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public User add(User usertmp,HttpServletResponse response) throws Exception{
		//System.out.println(name+passwd);
		if(usertmp.getName()=="dfd"){
			User user=new User();
			user.setName("dengpan");
			user.setPasswd("passwd");
			return user;
		}else
		{
			response.setStatus(403);
			return null;
		}
		
		
	}
	
	*/
	
}
