package com.xxq.appconfig;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public static void main(String[] args) {
        String s = "anviaj";
//        if (s.length() == 0) return 0;
        Integer length= 0;
        char temp = s.charAt(0);
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char test = s.charAt(i);
            Integer integer = map.get(test);
            if (integer != null) {
                length =  Math.max((i-1 - map.get(temp) +1), length);
                i = integer+1;
                map.clear();
                map.put(s.charAt(i), i);
                temp = s.charAt(i);
            } else {
                map.put(test, i);
            }
            if (i == (s.length() -1)) {
                length = Math.max((i - map.get(temp) + 1),length );
            }

            if (s.length() -map.get(temp) <length) {
                break;
            }

        }
        System.out.println(length);
    }
}
