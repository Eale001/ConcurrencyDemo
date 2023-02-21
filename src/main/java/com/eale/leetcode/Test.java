package com.eale.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Admin
 * @Date 2021/3/1
 * @Description // 力扣
 * @Version 1.0
 **/
public class Test {


    public static void main(String[] args) {


        System.out.println(longestPalindrome("babab"));

        Map<String,String> map = new HashMap<>(8);

        Map<String,String> concurrentHashMap = new ConcurrentHashMap<>(8);

    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

}
