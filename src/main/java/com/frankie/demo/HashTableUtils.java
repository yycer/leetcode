package com.frankie.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: Yao Frankie
 * @date: 2020/3/10 20:04
 */
public class HashTableUtils {

    public static int getLongestStringLength(String s){
        int count = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()){
            if (map.get(c) != null)
                count = 1;
            else{
                map.put(c, 1);
                count++;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * lengthOfLongestStringUsingSlidingWindow()
     */
    public static int lolsUsingSlidingWindow(String s){
        Map<Character, Integer> map = new HashMap<>();
        int j = 0, max = 0;
        for (int i = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (map.get(cur) == null){
                map.put(cur, i);
            } else{
                // Be careful: abba
                j = Math.max((map.get(cur) + 1), j);
                map.put(cur, i);
            }
            max = Math.max(max, i - j + 1);
        }
        return max;
    }

    /**
     * Java concise HashMap solution.
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/2146/Java-concise-HashMap-solution.
     */
    public static int lolsUsingSlidingWindowConcisely(String s){
        Map<Character, Integer> map = new HashMap<>();
        int ret = 0;
        for (int i = 0, start = 0; i < s.length(); i++){
            char cur = s.charAt(i);
            if (map.containsKey(cur))
                start = Math.max(start, map.get(cur) + 1);
            map.put(cur, i);
            ret = Math.max(ret, i - start + 1);
        }
        return ret;
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            if (map.containsKey(n)){
                map.put(n, map.get(n) + 1);
                if (map.get(n) > (nums.length / 2)) return n;
            }
            else
                map.put(n, 1);
        }
        return 0;
    }

    public static int majorityElement2(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums){
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
            if (map.get(n) > nums.length / 2){
                ret = n;
                break;
            }
        }
        return ret;
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ret = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            int tmp = 0;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] < nums[i]) tmp++;
            }
            ret[i] = tmp;
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/discuss/524823/Simple-Java-solution
     */
    public static int[] smallerNumbersThanCurrentImprove(int[] nums) {
        int[] tmpArr = new int[101];
        int[] retArr = new int[nums.length];
        int cur = 0;
        for (int n: nums)
            tmpArr[n]++;

        for (int i = 0; i < 101; i++){
            cur += tmpArr[i];
            tmpArr[i] = cur;
        }

        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0)
                retArr[i] = 0;
            else
                retArr[i] = tmpArr[nums[i] - 1];
        }
        return retArr;
    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n: arr){
            if (map.containsKey(n))
                map.put(n, map.get(n) + 1);
            else
                map.put(n, 1);
        }

        return map.values().size() == map.values().stream().collect(Collectors.toSet()).size();
    }

    /**
     * https://leetcode.com/problems/unique-number-of-occurrences/discuss/392858/JavaPython-3-4-liner-and-2-liner-Using-Map-and-Set-w-brief-explanation-and-analysis.
     * 1. HashSet(Collection<? extends E> c)
     * 2. getOrDefault(Object key, V defaultValue)
     */
    public static boolean uniqueOccurrencesImprove1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n: arr)
            map.put(n, map.getOrDefault(n, 0) + 1);

        return map.values().size() == new HashSet<>(map.values()).size();
    }

    /**
     * https://leetcode.com/problems/unique-number-of-occurrences/discuss/392918/Java-array-%2B-set-beat-100
     */
    public static boolean uniqueOccurrencesImprove2(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int[] tmpArr = new int[2001];
        for (int n: arr)
            tmpArr[n + 1000]++;

        for (int n: tmpArr){
            if (n != 0){
                if (set.contains(n)) return false;
                set.add(n);
            }
        }
        return true;
    }

    /**
     * String[] words = {"cat", "bt", "hat", "tree"};
     * String   chars = "atach";
     */
    public static int countCharacters(String[] words, String chars) {

        int ret = 0;

        for (String word: words){
            Map<Character, Integer> map = buildMap(chars);
            for (char c: word.toCharArray()){
                Integer cur = map.get(c);
                if (cur != null && cur > 0)
                    map.put(c, cur - 1);
                else break;
            }
            int valueSum = map.values().stream().mapToInt(Integer::intValue).sum();
            if (valueSum + word.length() == chars.length()){
                ret += word.length();
            }
        }
        return ret;
    }

    private static Map<Character, Integer> buildMap(String chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: chars.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        return map;
    }

    /**
     * 1. Using array as HashMap(char - 97), Just for lower letter.
     * 2. use tmp value instead of stream.sum
     * 3. Arrays.copyOf(short[] original, int newLength)。
     * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/discuss/361004/Easy-Explained-Simple-Java-Check-all-char-count
     */
    public static int countCharactersImprove(String[] words, String chars) {

        int ret = 0;
        int[] seen = new int[26];
        for (Character c: chars.toCharArray())
            seen[c - 97]++;
        
        for (String word: words){
            int tmp = 0;
            int[] tSeen = Arrays.copyOf(seen, seen.length);
            for (Character c: word.toCharArray()){
                if (tSeen[c - 97] < 1){
                    break;
                } else {
                    tSeen[c - 97]--;
                    tmp++;
                }
            }
            if (tmp == word.length())
                ret += tmp;
        }
        return ret;
    }

    public static List<String> findCommonCharacters(String[] A) {

        List<String> retList = new ArrayList<>();
        int[][] helpArr = new int[A.length][26];
        // Step1: Build two-dimensional array.
        for (int i = 0; i < A.length; i++)
            for (char c: A[i].toCharArray())
                helpArr[i][c - 97]++;

        // Step2: Process common characters.
        for (int i = 1; i < A.length; i++){
            for (int j = 0; j < 26; j++){
                if (helpArr[i][j] > helpArr[i - 1][j])
                    helpArr[i][j] = helpArr[i - 1][j];
            }
        }

        // Step3: Get common characters.
        for (int i = 0; i < 26; i++){
            int cur = helpArr[A.length - 1][i];
            while (cur > 0){
                retList.add(String.valueOf((char)('a' + i)));
                cur--;
            }
        }
        return retList;
    }

    public static int findSingleNumber(int[] nums) {
        int ret = 0;
        for (int n: nums)
            ret ^= n;
        return ret;
    }

    /**
     * https://leetcode.com/problems/single-number/discuss/43000/Python-different-solutions.
     */
    public static int findSingleNumberUsingMath(int[] nums){
        int sum = IntStream.of(nums).sum();
        Set<Integer> numsSet = new HashSet<>();
        for (int n: nums)
            numsSet.add(n);

        int setSum = numsSet.stream().mapToInt(Integer::intValue).sum();
        return 2 * setSum - sum;
    }

    public static int findSingleNumber2(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums)
            map.put(n, map.getOrDefault(n, 0) + 1);
        for (Map.Entry<Integer, Integer> entry: map.entrySet()){
            if (entry.getValue() == 1){
                ret = entry.getKey();
                return ret;
            }
        }
        return ret;
    }

    public static char findTheDiff(String s, String t) {
        char ret = 0;
        int[] sArr = new int[26];
        int[] tArr = new int[26];

        for (char c: s.toCharArray())
            sArr[c - 97]++;

        for (char c: t.toCharArray())
            tArr[c - 97]++;

        for (int i = 0; i < 26; i++){
            if (tArr[i] - sArr[i] == 1) ret = (char) (i + 'a');
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/find-the-difference/discuss/86825/Java-solution-using-bit-manipulation
     */
    public static char findTheDiffUsingXor(String s, String t){
        char c = 0;
        for (char sc: s.toCharArray())
            c ^= sc;

        for (char tc: t.toCharArray())
            c ^= tc;

        return c;
    }

    /**
     * https://leetcode.com/problems/find-the-difference/discuss/86913/JavaC%2B%2B-1-liner
     */
    public static char findTheDiffUsingStream(String s, String t){
        return (char) (s + t).chars().reduce(0, (a, b) -> a ^ b);
    }

    /**
     * https://leetcode.com/problems/island-perimeter/discuss/95001/clear-and-easy-java-solution
     */
    public static int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++){
                if (grid[row][col] == 1){
                    islands++;
                    if (row < grid.length - 1 && grid[row + 1][col] == 1) neighbours++;
                    if (col < grid[row].length - 1 && grid[row][col + 1] == 1) neighbours++;
                }
            }
        return islands * 4 - neighbours * 2;
    }

    /**
     * https://leetcode.com/problems/uncommon-words-from-two-sentences/discuss/158981/Java-3-liner-and-5-liner-using-HashMap-and-HashSets-respectively.
     */
    public static String[] findUncommonWords(String A, String B) {
        Set<String> distinctSet = new HashSet<>(), comSet = new HashSet<>();
        for (String word: (A + " " + B).split("\\s")){
            if (comSet.contains(word) || !distinctSet.add(word)){
                distinctSet.remove(word);
                comSet.add(word);
            }
        }
        return distinctSet.toArray(new String[0]);
    }

    /**
     * https://leetcode.com/problems/uncommon-words-from-two-sentences/discuss/158967/C%2B%2BJavaPython-Easy-Solution-with-Explanation
     */
    public static String[] findUncommonWordsUsingMap(String A, String B){
        Map<String, Integer> map  = new HashMap<>();
        ArrayList<String> retList = new ArrayList<>();
        String[] words = (A + " " + B).split("\\s");
        for (String word: words)
            map.put(word, map.getOrDefault(word, 0) + 1);
//        for (Map.Entry<String, Integer> entry: map.entrySet())
//            if (entry.getValue() == 1) retList.add(entry.getKey());
        for (String word: map.keySet())
            if (map.get(word) == 1) retList.add(word);

        return retList.toArray(new String[0]);
    }
}
















