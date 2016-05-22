package com.wuliao.dandan;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("chen");
		list.add("chen2");
		list.add("chen3");
		list.add("chen5");
		list.add("chen4");
		list.add("chen6");
		for (String it : list) {
			System.out.println(it);
		}
	}
}
