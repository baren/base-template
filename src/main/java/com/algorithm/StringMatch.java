package com.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 16/8/12.
 */
public class StringMatch {



    public int[] kmp_prepocess(String p) {
        if (p == null || p.length() <= 0) {
            throw new RuntimeException("argument p is empty!");
        }
        int [] pi = new int [p.length() + 1];

        int j= -1;
        pi[0] = j;

        for (int i = 0; i < p.length(); i++) {
            while (j >= 0 && p.charAt(i) != p.charAt(j))
                j=pi[j];
            j++;
            pi[i + 1]=j;
        }
        return pi;

    }

    /**
     * http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/kmpen.htm
     * @param s
     * @param p
     * @return
     */
    public List<Integer> kmp_search(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int [] pi = kmp_prepocess(p);
        int m = p.length();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = 0;
            int si = i;
            while (j < m && p.charAt(j) == s.charAt(si)) {
                j++;
                si++;
            }
            if (j == m) {
                list.add(j);
                i = i + p.length();
                continue;
            }
            int shift = j - pi[j];
            i = i + shift;
        }

        return list;
    }

    public static void main(String[] args) {
        StringMatch sm = new StringMatch();
        List<Integer> list = sm.kmp_search("abcabcabd", "abcabd");
        System.out.println(list.toString());
    }


}


