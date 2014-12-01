/**
 * Project Name:ssh1
 * File Name:User.java
 * Package Name:com.shuai.model
 * Date:2014年11月24日下午10:46:40
 * Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 *
 */

package com.shuai.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * ClassName: User <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2014年11月24日 下午10:46:40 <br/>
 * 
 * @author Eric Zhang
 * @since JDK 1.6 Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 */
public class User
{
	private String username;

	private String password;

	private String nickname;

	private String email;

	/**
	 * @param username
	 * @param password
	 * @param nickname
	 * @param email
	 */

	public User(String username, String password, String nickname, String email)
	{
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
	}

	/**
	 *
	 */
	
	public User()
	{
	}

	@NotEmpty(message="用户名不能为空")
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

}
