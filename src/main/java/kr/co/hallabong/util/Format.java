package kr.co.hallabong.util;

import java.util.HashMap;
import java.util.Map;

public class Format {
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
}
