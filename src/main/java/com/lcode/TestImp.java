package com.lcode;

import java.util.*;

/**
 * Created by user on 16/8/5.
 */
public class TestImp {

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

     You may assume that each input would have exactly one solution.

     Example:
     Given nums = [2, 7, 11, 15], target = 9,

     Because nums[0] + nums[1] = 2 + 7 = 9,
     return [0, 1].
     * @param input
     * @param target
     * @return
     */
    public static int[] num_1_me(int[] input, int target) {

        for(int i=0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int [] {-1, -1};
    }

    /**
     * 合理的利用了target-ele="other ele" 这个规律,并用空间换取时间.
     * @param input
     * @param target
     * @return
     */
    public static int[] num_1_best(int[] input, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < input.length; i++) {
            int v = target - input[i];
            if (map.containsKey(v)) {
                return new int[] {map.get(v), i};
            }
            map.put(input[i], i);
        }

        return new int[] {-1, -1};
    }

    public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    /**
     * You are given two linked lists representing two non-negative numbers.
     * The digits are stored in reverse order and each of their nodes contain a single digit.
     * Add the two numbers and return it as a linked list.

     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     Output: 7 -> 0 -> 8

     注意的是:
     1 取余与取商的算法,
     2 next的赋值
     3 迭代过程的处理
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode num_2_me(ListNode l1, ListNode l2) {

        ListNode head = null;
        ListNode cc = null;
        int carry = 0;
        while(l1 != null && l2 != null) {

            ListNode item = new ListNode(carry + (l1.val + l2.val) % 10);
            if (head == null) {
                head = item;
            } else {
                cc.next = item;
            }
            cc = item;
            carry = (l1.val + l2.val) / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        return head;
    }


    public static ListNode initSingListNode(int[] l) {
        ListNode ret = null;
        ListNode a = null;
        for(int i = 0; i < l.length; i++) {
            ListNode k = new ListNode(l[i]);
            if(ret == null) {
                ret = k;
            } else {
                a.next = k;
            }
            a = k;
        }
        return ret;
    }

    public static ListNode[] initListNodes(int[] l1, int[] l2) {
        ListNode ret = initSingListNode(l1);
        ListNode ret2 = initSingListNode(l2);

        return new ListNode[] {ret, ret2};
    }

    public static void printListNode(ListNode l) {
        while(l != null) {
            System.out.print(Integer.toString(l.val) + " -> ");
            l = l.next;
        }
    }


    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * 1 需要理解滑块算法的精髓.
     * @param str
     * @return
     */
    public static int num_3_me(String str) {

        int start=0;
        int sub_len = 1;
        int longest = 1;
        for (int i = 1; i < str.length(); i++) {

            if (!str.substring(start, i).contains(String.valueOf(str.charAt(i)))) {
                sub_len++;
            } else {
                longest = Math.max(longest, sub_len);
                int first = str.substring(0, i).lastIndexOf(str.charAt(i));
                start = first + 1;
                sub_len = i - first;

            }

        }
        return longest;
    }

    public static int num_3_best1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static double num_4_other(int[] nums1, int[] nums2) {

        int total = nums1.length + nums2.length;
        if ((total & 0x1) == 1)
            return getkth(nums1, nums2, total / 2 + 1);
        else
            return (getkth(nums1, nums2, total / 2)
                    + getkth(nums1, nums2,  total / 2 + 1)) / 2;
    }

    private static double getkth(int[] l_array, int[] r_array, int k) {


        if (l_array.length > r_array.length) {
            return getkth(r_array, l_array, k);
        }
        if (l_array.length == 0) {
            return r_array[k-1];
        }
        if (k == 1) {
            return Math.min(l_array[0], r_array[0]);
        }

        int l_index = Math.min(k/2, l_array.length);
        int r_index = k - l_index;
//        int r_index = Math.min(k/2, r_array.length);
        if (l_array[l_index - 1] < r_array[r_index - 1]) {
            int[] n_l_array = new int[l_array.length - l_index];
            System.arraycopy(l_array, l_index, n_l_array, 0, l_array.length - l_index);
            return getkth(n_l_array, r_array, r_index);
        } else {
            int[] n_r_array = new int[r_array.length - r_index];
            System.arraycopy(r_array, r_index, n_r_array, 0, r_array.length - r_index);
            return getkth(l_array, n_r_array, k - r_index);
        }


    }

    /**
     * Given a string S, find the longest palindromic(回文) substring in S.
     * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
     * @param s
     * @return
     */
    public static String num_5_me(String s) {
        int sub_start = 0;
        int sub_end = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                int k = 0;
                while(i - k >= 0 && i + 1 + k < s.length() && s.charAt(i - k) == s.charAt(i + 1 + k)) {
                    k++;
                }
                if ((k * 2 + 2) > (sub_end - sub_start + 1)) {
                    sub_start = i-k;
                    sub_end = i + k;
                }
            } else if (i > 0 && s.charAt(i - 1) == s.charAt(i + 1)) {
                int k = 0;
                while(i-1-k >= 0 && i + 1 + k < s.length() && s.charAt(i-1-k) == s.charAt(i+1+k)) {
                    k++;
                }
                if ((k * 2 + 1) > (sub_end - sub_start + 1)) {
                    sub_start = i-k;
                    sub_end = i + k;
                }
            }
        }


        return s.substring(sub_start, sub_end+1);
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:

     P   A   H   N
     A P L S I I G
     Y   I   R
     And then read line by line: "PAHNAPLSIIGYIR"
     * @param s
     * @param numRows
     * @return
     */
    public static String num_6_me(String s, int numRows) {


        StringBuilder[] sb=new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb[i] = new StringBuilder();
        }
        int index = 0;
        int incre = 0;
        for(int i = 0; i < s.length(); i++) {
            sb[index].append(s.charAt(i));
            if (index == 0) {
                incre = 1;
            }
            if (index == numRows - 1) {
                incre = -1;
            }
            index += incre;
        }
        String ret = "";
        for (int i = 0; i < numRows; i++) {
            ret += sb[i].toString();
        }


        return ret;

    }

    /**
     * Reverse digits of an integer.

     Example1: x = 123, return 321
     Example2: x = -123, return -321
     * @param x
     * @return
     */
    public static int num_7_me(int x) {


        long ret = 0l;
        int tail;
        while (x != 0) {
            tail = x % 10;
            ret = ret * 10 + tail;
            if (ret < Integer.MIN_VALUE || ret > Integer.MAX_VALUE) {
                return 0;
            }
            x = x / 10;
        }

        return (int) ret;
    }

    /**
     * z字符串转换成整型
     * @param s
     * @return
     */
    public static int num_8_atoi(String s) {

        s = s.trim();

        int sign = 1;
        if (s.startsWith("-")) {
            sign = -1;
            s = s.substring(1);
        }
        int tail;
        long ret = 0l;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return 0;
            }
            if (s.charAt(i) == ' ') {
                continue;
            }
            tail = Character.getNumericValue(s.charAt(i));
            ret = ret * 10 + tail;
        }
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return 0;
        }


        return (int) ret * sign;
    }

    /**
     * Determine whether an integer is a palindrome. Do this without extra space.
     * @param x
     * @return
     */
    public static boolean num_9_is_palindrome(int x) {

        if (x < 0) {
            return false;
        }
        int k = num_7_me(x);
        if (x == k) {
            return true;
        }
        return false;

    }

    /**
     * Implement regular expression matching with support for '.' and '*'.

     '.' Matches any single character.
     '*' Matches zero or more of the preceding element.

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "a*") → true
     isMatch("aa", ".*") → true
     isMatch("ab", ".*") → true
     isMatch("aab", "c*a*b") → true
     * @param s
     * @param p
     * @return
     */
    public static boolean num_10_isMatch(String s, String p) {

        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.length() == 1) {
            if (s.length() == 1 && (p.equals(".") || s.equals(p)))
                return true;
            return false;
        }
        if (p.charAt(1) == '*') {
            return num_10_isMatch(s, p.substring(2)) || !s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && num_10_isMatch(s.substring(1), p);
        } else {
            return s.isEmpty() && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && num_10_isMatch(s.substring(1), p.substring(1));
        }

    }

    public static List<Integer> num_30_findSubstring(String s, String[] words) {


        List<Integer> ret = new LinkedList<>();
        if (s.length() < words.length * words[0].length() || words.length == 0) {
            ret.add(-1);
            return ret;
        }
        int len = words[0].length();
        for (int i = 0; i < s.length(); i++) {

        }

        return null;
    }

    /**
     * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

     If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

     The replacement must be in-place, do not allocate extra memory.

     Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
     1,2,3 → 1,3,2
     3,2,1 → 1,2,3
     1,1,5 → 1,5,1
     * @param nums
     */
    public static void num_31_nextPermutation(int[] nums) {


        num_31_help(nums, 0, nums.length);

    }

    public static void num_31_help(int[] nums, int start, int end) {
        int i = end - 1;

        while (i > start && nums[i] < nums[i - 1]) {
            i--;
            continue;
        }
        if (i == start) {
            int j = end - 1;
            while (i != j && i < j) {
                swap(nums, i++, j--);
            }
        } else {
            swap(nums, i, i - 1);
            num_31_help(nums, i, end);

        }


    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static int num_32_longestValidParentheses(String s) {

        int len = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            }
            if (s.charAt(i) == ')' && !stack.empty()) {
                char c = stack.pop();
                if (c == '(') {
                    len += 2;
                }
            }
        }

        return len;
    }

    public static int num_33_search(int[] nums, int start, int end, int target) {

        if (nums[start] > nums[end]) {

            int m = (start + end) / 2;

            if (nums[m] == target) {
                return m;
            } else if (nums[m] < nums[start]) {
                if (target > nums[m]) {
                    return middle_search(nums, m, end, target);
                } else {
                    return num_33_search(nums, start, m, target);
                }
            } else {
                if (target < nums[m]) {
                    return middle_search(nums, start, m, target);
                } else {
                    return num_33_search(nums, m, end, target);
                }
            }


        } else {  // 折半查找
            return middle_search(nums, 0, nums.length - 1, target);
        }

    }

    public static int middle_search(int[] nums, int start, int end, int target) {
        if (end == start) {
            if (target == nums[end])
                return end;
            else
                return -1;
        }
        int m = (start + end) / 2;
        if (nums[m] < target) {
            return middle_search(nums, m + 1, end, target);
        } else if (nums[m] > target) {
            return middle_search(nums, start, m - 1, target);
        } else {
            return m;
        }
    }

    public static List<Integer> num_34_searchRange(int[] nums, int target) {

        int end = nums.length - 1;
        int start = 0;
        List<Integer> ret = new ArrayList<>();
        int index = -1;
        while (start < end) {
            int m = (start + end) / 2;
            if (nums[m] == target) {
//                ret.add(m);
                index = m;
                break;
            } else if (nums[m] < target) {
                start = m + 1;
            } else {
                end = m - 1;
            }
        }
        if (index != -1) {
            ret.add(index);
            int i = index - 1, j = index + 1;
            while (i >= 0 && nums[i] == target) {
                ret.add(0, i--);
            }
            while (j < nums.length && nums[j] == target) {
                ret.add(j++);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        // 第一题
//        int [] ret = TestImp.num_1_best(new int [] {2, 7, 11, 15}, 26);
//        System.out.println(Arrays.toString(ret));

        // 第二题
//        int [] l1 = new int [] {2 , 4 , 3};
//        int [] l2 = new int [] {5 , 6 , 4};
//        ListNode[] a = initListNodes(l1, l2);
//        ListNode r = TestImp.num_2_me(a[0], a[1]);
//        printListNode(r);
        // 第三题
//        int len = num_3_best1("adebeckm");
//        System.out.println(len);
//        int len1 = num_3_best1("bbbbbascdb");
//        System.out.println(len1);
//        int len2 = num_3_best1("pwwkew");
//        System.out.println(len2);
        // 第四题
//        double a = num_4_other(new int [] {3,5,7}, new int [] {4,6,7}); // 3,4,5,6,7,9   57 46  4567
//        System.out.println(a);
        // 第五题
//        String s = num_5_me("abacdgfdcaba");
//        System.out.println(s);
//        String s = num_6_me("PAYPALISHIRING", 3);
//        System.out.println(s);
        // 第6题
//        int x = num_7_me(-2147);
//        System.out.println(x);
//        System.out.println(Integer.MAX_VALUE);
//        int x = num_8_atoi("214333337");
//        System.out.println(x);
//        boolean a = num_9_is_palindrome(1);
//        System.out.println(a);
//        boolean a = num_10_isMatch("ab", ".*");
//        System.out.println(a);
//        int [] a = new int[] {4,3,2,1};
//        num_31_nextPermutation(a);
//        System.out.println(Arrays.toString(a));
//        int len = num_32_longestValidParentheses("))())");
//        System.out.println(len);
//        int i = num_33_search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0, 6, 1);
//        System.out.println(i);
        List<Integer> ret = num_34_searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8);
        System.out.println(ret.toString());
    }
}
