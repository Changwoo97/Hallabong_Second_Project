package kr.co.hallabong.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Format {
	private Format() {}
	
	public static Map<String, String> getMap(String str) {
		Map<String, String> map = new HashMap<>();
		
		try {
			String[] keyEqualValues = str.split("&");
			
			for (String keyEqualValue : keyEqualValues) {
				try {
					String[] keyAndValue = keyEqualValue.split("=");
					String key = keyAndValue[0];
					String value = keyAndValue[1];
					map.put(key, value);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		
		return map;
	}
	
	public static String numComma(String str) {
		Stack<Character> stack = new Stack<Character>();
		
		char[] chs = str.toCharArray();
		for (int i = 0; i < chs.length; i++) {
			if (i % 3 == 0) {
				stack.add(',');
			}
			
			stack.add(chs[chs.length - 1 - i]);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!stack.isEmpty()) {
			char ch = stack.pop();
			sb.append(ch);
		}
		
		int lastIndex = sb.length() - 1;
		if (sb.charAt(lastIndex) == ',') {
			sb.deleteCharAt(lastIndex);
		}
		
		return sb.toString();
	}
	
	public static String numComma(int num) {
		String numToStr = String.valueOf(num);
		
		return numComma(numToStr);
	}
}
