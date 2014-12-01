/**
 * Project Name:homework
 * File Name:ProtoTester.java
 * Package Name:com.dataguru.shuai.homework.Third
 * Date:2014年12月1日下午8:43:04
 * Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 *
 */

package com.dataguru.shuai.homework.Third;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dataguru.shuai.homeowrk.Third.protobuf.PersonProbuf;
import com.dataguru.shuai.homeowrk.Third.protobuf.PersonProbuf.Person;
import com.dataguru.shuai.homeowrk.Third.protobuf.PersonProbuf.Person.Builder;
import com.dataguru.shuai.homeowrk.Third.protobuf.PersonProbuf.Person.PhoneNumber;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * ClassName: ProtoTester <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * date: 2014年12月1日 下午8:43:04 <br/>
 * 
 * @author Eric Zhang
 * @since JDK 1.6 Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 */
public class ProtoTester
{
	static Log logger = LogFactory.getLog(ProtoTester.class);

	public static void main(String[] args)
	{

		Builder builder = PersonProbuf.Person.newBuilder();

		builder.setEmail("kkk@email.com");
		builder.setId(1);
		builder.setName("TestName");
		builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder()
				.setNumber("131111111")
				.setType(PersonProbuf.Person.PhoneType.MOBILE));
		builder.addPhone(PersonProbuf.Person.PhoneNumber.newBuilder()
				.setNumber("011111")
				.setType(PersonProbuf.Person.PhoneType.HOME));

		Person person = builder.build();
		byte[] buf = person.toByteArray();
		System.out.println("序列化后的对象BUF" + buf);
		try
		{
			Person person2 = PersonProbuf.Person.parseFrom(buf);
			System.out.println("序列话后的结果值为" + person2.getName() + ", " + person2.getEmail());
			List<PhoneNumber> lstPhones = person2.getPhoneList();
			for (PhoneNumber phoneNumber : lstPhones)
			{
				System.out.println(phoneNumber.getNumber());
			}
		} catch (InvalidProtocolBufferException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
