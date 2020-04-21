package com.frankie.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Yao Frankie
 * @date: 2020/4/21 19:51
 */
@SpringBootTest
public class StringTest {

    /**
     * 205. Isomorphic Strings
     */
    @Test
    public void p205(){
        String s = "title";
        String t = "paper";
//        boolean ret = isIsomorphic(s, t);
//        System.out.println(ret);

        Integer i1 = -128;
        Integer i2 = -128;
        Integer i3 = 127;
        Integer i4 = 127;
        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(i5 == i6);

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        Integer i128 = new Integer(128);
        map1.put('c', 127);
        map1.put('d', 128);
        map1.put('e', i128);


        map2.put('c', 127);
        map2.put('d', 128);
        map2.put('e', i128);
        System.out.println(map1.get('c') == map2.get('c'));
        System.out.println(map1.get('d') == map2.get('d'));
        System.out.println(map1.get('e') == map2.get('e'));

    }

    private boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i)) && map.get(i) != t.charAt(i)){
                return false;
            }
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }

    @Test
    public void lengthAndSizeTest(){
        int[] arr = {1, 2, 3};
        System.out.println("The length of arr is " + arr.length);

        String str = "abc";
        System.out.println("The length of str is " + str.length());

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println("The size of list is " + list.size());
    }
}
