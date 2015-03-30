package com.shfb.test;

import com.shfb.common.entity.Users;
import com.shfb.common.util.BaseUtil;

public class StringTest {

	public static void main(String[] args) {
		Users user=new Users();
		user.setId(1);
		System.out.println(BaseUtil.getJsonFromObject(user));
	}

}
