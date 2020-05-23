package com.juc.collections.hashset;

import java.util.HashSet;
import java.util.Objects;

public class HashMapExistKey {

    /**
     * 使用HashSet判断逐渐是否存在
     * @param string
     * @return
     */
    public static Character findFirstRepeatedChar(String string) {
        if (Objects.isNull(string) || string.isEmpty()) {
            return null;
        }

        // 根据判断是否存在key,
        char[] charArray = string.toCharArray();
        HashSet<Object> charSet = new HashSet<>(charArray.length);
        for (char ch : charArray) {
            if (charSet.contains(ch)) {
                return ch;
            }

            charSet.add(ch);
        }

        return null;
    }
}
