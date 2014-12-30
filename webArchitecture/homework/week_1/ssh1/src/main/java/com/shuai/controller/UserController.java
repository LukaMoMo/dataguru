/**
 * Project Name:ssh1
 * File Name:UserController.java
 * Package Name:com.shuai.controller
 * Date:2014年11月24日下午10:48:29
 * Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 *
 */

package com.shuai.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shuai.model.User;

/**
 * ClassName: UserController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2014年11月24日 下午10:48:29 <br/>
 * 
 * @author Eric Zhang
 * @since JDK 1.6 Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 */
@Controller
@RequestMapping("/user")
public class UserController
{
	private Map<String, User> users = new HashMap<String, User>();

	/**
	 *
	 */

	public UserController()
	{
		users.put("san", new User("san", "123", "张帅", "3163504123@163.com"));
		users.put("san1", new User("san1", "123", "张帅1", "3163504123@163.com"));
		users.put("san2", new User("san2", "123", "张帅2", "3163504123@163.com"));
		users.put("san3", new User("san3", "123", "张帅3", "3163504123@163.com"));
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String list(Model model)
	{
		model.addAttribute("users", users);
		return "user/list";
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		model.addAttribute(new User());
		return "user/add";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated User user,BindingResult br,@RequestParam("attachs") MultipartFile[] attachs) {
		
		for(MultipartFile attach : attachs) {
			if(!attach.isEmpty())
				System.err.println(attach.getOriginalFilename());
		}
		
		if(br.hasErrors()) {
			return "user/add";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}
	
	@RequestMapping(value ="/{username}",method = RequestMethod.GET)
	public String show(@PathVariable String username,Model md) {
		md.addAttribute(users.get(username)); 
		return "user/show";
	}
	
	@RequestMapping(value = "/{username}/update",method = RequestMethod.GET)
	public String update(@PathVariable String username,Model md) {
		md.addAttribute("user", users.get(username));
		return "user/update";
	}
	
	
	@RequestMapping(value = "/{username}/update",method = RequestMethod.POST)
	public String update(@Validated User user,BindingResult br) {
		
		if(br.hasErrors()) {
			return "user/update";
		}
		users.put(user.getUsername(), user);
		return "redirect:/user/users";
	}	
	
	@RequestMapping(value = "/{username}/delete",method = RequestMethod.GET)
	public String update(@PathVariable String username) {
		users.remove(username);
		return "redirect:/user/users";
	}
	
	
	
	
}
