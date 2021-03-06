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

    public static int maxNumberOfBalloons(String text) {
        int[] arr = new int[26];
        for (char c: text.toCharArray())
            arr[c - 97]++;
        int ret = arr[0];

        for (int i = 0; i < 26; i++){
            if (i == 'l' - 97 || i == 'o' - 97)
                ret = Math.min(ret, arr[i] / 2);
            if (i == 'a' - 97 || i == 'b' - 97 || i == 'n' - 97)
                ret = Math.min(ret, arr[i]);
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/maximum-number-of-balloons/discuss/382401/WithComments-StraightForward-Java-Simple-count-of-chars
     */
    public static int maxNumberOfBalloonsConcisely(String text) {
        int[] arr = new int[26];
        for (char c: text.toCharArray())
            arr[c - 97]++;
        int ret = arr[1];                 // b
        ret = Math.min(ret, arr[0]);      // a
        ret = Math.min(ret, arr[11] / 2); // l
        ret = Math.min(ret, arr[14] / 2); // o
        ret = Math.min(ret, arr[13]);     // n
        return ret;
    }

    public static boolean validAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr = new int[26];
        for (char sc: s.toCharArray())
            arr[sc - 97]++;
        for (char ts: t.toCharArray())
            // Be careful: s = "a", t = "b";
            if (arr[ts - 97] > 0) arr[ts - 97]--;
        return Arrays.stream(arr).sum() == 0;
    }

    /**
     * 1. Math.abs()
     * 2. Stream.reduce()
     * My solutions in C++, Java, Python, C, C#, JavaScript, and Ruby
     * https://leetcode.com/problems/valid-anagram/discuss/66490/My-solutions-in-C%2B%2B-Java-Python-C-C-JavaScript-and-Ruby
     */
    public static boolean validAnagramImprove(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] arr = new int[26];
        for (char sc: s.toCharArray())
            arr[sc - 97]++;
        for (char ts: t.toCharArray())
            arr[ts - 97]--;
        return Arrays.stream(arr).reduce(0, (a, b) -> Math.abs(a) + Math.abs(b)) == 0;
    }

    public static int[] intersect(int[] nums1, int[] nums2) {

        ArrayList<Integer> retList = new ArrayList<>();
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int n: nums1)
            map1.put(n, map1.getOrDefault(n, 0) + 1);

        for (int n: nums2)
            map2.put(n, map2.getOrDefault(n, 0) + 1);

        for (int key: map1.keySet()){
            int val = Math.min(map1.getOrDefault(key, 0), map2.getOrDefault(key, 0));
            while (val > 0) {
                retList.add(key);
                val--;
            }
        }
        return retList.stream().mapToInt(i -> i).toArray();
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        ArrayList<String>   retList = new ArrayList<>();
        Map<String, Integer> tmpMap = new HashMap<>();
        Map<String, Integer> retMap = new HashMap<>();

        for (int i = 0; i < list1.length; i++)
            tmpMap.put(list1[i], i);

        for (int i = 0; i < list2.length; i++){
            if (tmpMap.containsKey(list2[i]))
                retMap.put(list2[i], tmpMap.get(list2[i]) + i);
        }

        int minVal = retMap.values().stream()
                .mapToInt(Integer::intValue).min()
                .orElse(Integer.MAX_VALUE);

        for (String res: retMap.keySet()){
            if (retMap.get(res) == minVal)
                retList.add(res);
        }

        return retList.toArray(new String[0]);
    }

    public static String[] findRestaurantImprove(String[] list1, String[] list2) {
        LinkedList<String>  retList = new LinkedList<>();
        Map<String, Integer> map    = new HashMap<>();

        for (int i = 0; i < list1.length; i++)
            map.put(list1[i], i);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++){
            Integer j = map.get(list2[i]);
            if (j != null && (i + j) <= min){
                if ((i + j) < min){
                    retList.clear();
                    min = i + j;
                }
                retList.add(list2[i]);
            }
        }
        return retList.toArray(new String[0]);
    }

    public static int longestPalindrome(String s) {
        int ret = 0;
        boolean singleFlag = false;
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c: map.keySet()){
            int cur = map.get(c);
//            if (cur == 1 || cur % 2 == 1)
            if (cur % 2 == 1)
                singleFlag = true;
            if (cur > 1)
                ret += ((cur / 2) * 2);
        }
        return ret += (singleFlag ? 1 : 0);
    }

    /**
     * https://leetcode.com/problems/longest-palindrome/discuss/89604/Simple-HashSet-solution-Java
     */
    public static int longestPalindromeUsingSet(String s) {
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c: s.toCharArray()){
            if (set.contains(c)){
                set.remove(c);
                count++;
            }
            else
                set.add(c);
        }
        return count * 2 + (set.isEmpty() ? 0 : 1);
    }

    /**
     * https://leetcode.com/problems/longest-palindrome/discuss/89674/Java-4-lines-beats-90
     */
    public static int longestPalindromeAmazing(String s) {
        int[] arr = new int[1 << 7];
        int   ret = 0;
        for (char c: s.toCharArray()) arr[c]++;
        for (int n: arr) ret += n >> 1 << 1;
        return ret == s.length() ? ret : ret + 1;
    }

    public static boolean isHappy(int n) {
        if (n <= 0) return false;
        while (n > 9){
            n = reshape(n);
        }
        if (n == 1 || n == 7) return true;
        return false;
    }

    public static int reshape(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0){
            list.add(n % 10);
            n /= 10;
        }
        // return list.stream().map(x -> (int) Math.pow(x, 2)).mapToInt(Integer::intValue).sum();
        return list.stream().mapToInt(x -> (int) Math.pow(x, 2)).sum();
    }

    public static boolean isHappyUsingString(int n) {
        if (n <= 0) return false;
        while (n > 9){
//            n = String.valueOf(n).chars().map(x -> (int) Math.pow(x - 48, 2)).sum();
            n = String.valueOf(n).chars().map(x -> (x - 48) * (x - 48)).sum();
        }
        if (n == 1 || n == 7) return true;
        return false;
    }

    /**
     * https://leetcode.com/problems/happy-number/discuss/56913/Beat-90-Fast-Easy-Understand-Java-Solution-with-Brief-Explanation
     */
    public static boolean isHappyUsingSet(int n){
        Set<Integer> set = new HashSet<>();
        int sum, rem;
        // Avoid infinite loop!
        while (set.add(n)){
            sum = 0;
            while (n > 0){
                rem = n % 10;
                sum += rem * rem;
                n /= 10;
            }
            if (sum == 1) return true;
            else n = sum;
        }
        return false;
    }

    public static boolean isHappyUsingRecursion(int n){
        if (n < 10){
            if (n == 1 || n == 7) return true;
            else return false;
        }
        int sum = 0;
        while (n > 0){
            int rem = n % 10;
            sum += rem * rem;
            n /= 10;
        }
        return isHappyUsingString(sum);
    }

    public static int findLHS(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int n: nums)
            treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);

        for (int k: treeMap.keySet()){
            int cur = treeMap.get(k);
            if (treeMap.containsKey(k + 1)){
                ret = Math.max(ret, cur + treeMap.get(k + 1));
            }
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/longest-harmonious-subsequence/discuss/103497/Simple-Java-HashMap-Solution
     */
    public static int findLHSUsingHashMap(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: nums)
            map.put(n, map.getOrDefault(n, 0) + 1);

        for (int k: map.keySet()){
            if (map.containsKey(k + 1)){
                ret = Math.max(ret, map.get(k) + map.get(k + 1));
            }
        }
        return ret;
    }

    /**
     * https://leetcode.com/problems/set-mismatch/discuss/439805/Java-O(n)-solution-using-frequency-table
     */
    public static int[] findErrorNums(int[] nums) {
        int[] retArr = new int[2];
//        int[] arr = new int[10001];
        int[] arr = new int[nums.length + 1];
        for (int n: nums)
            arr[n]++;

        for (int i = 1; i <= nums.length; i++){
            if (arr[i] == 2) retArr[0] = i;
            if (arr[i] == 0) retArr[1] = i;
        }
        return retArr;
    }

    /**
     * https://leetcode.com/problems/set-mismatch/discuss/105507/Java-O(n)-Time-O(1)-Space
     */
    public static int[] findErrorNumsAmazing(int[] nums){
        int[] ret = new int[2];
        for (int n: nums){
            if (nums[Math.abs(n) - 1] < 0)
                ret[0] = Math.abs(n);
            else
                nums[Math.abs(n) - 1] *= -1;
        }

        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0) ret[1] = i + 1;
        }
        return ret;
    }

    public static int[] findErrorNumsUsingSet(int[] nums){
        Set<Integer> set = new HashSet<>();
        int duplicate = 0, len = nums.length;
        long sum = len * (len + 1) / 2;
        for (int n: nums){
            if (set.contains(n))
                duplicate = n;
            sum -= n;
            set.add(n);
        }
        return new int[]{duplicate, (int) (duplicate + sum)};
    }

    public static boolean findIS(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            char curS = s.charAt(i);
            char curT = t.charAt(i);
            if (map.containsKey(curS) && map.get(curS) != curT)
                return false;
            if (map.containsKey(curT) && map.get(curT) != curS)
                return false;
            map.put(curS, curT);
        }
        return true;
    }

    public static boolean wordPattern(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        String[] words = str.split("\\s");
        if (pattern.length() != words.length) return false;
        for (int i = 0; i < words.length; i++){
            char cur = pattern.charAt(i);
            if (map.containsKey(cur)  && !map.get(cur).equals(words[i])) return false;
            if (!map.containsKey(cur) && map.containsValue(words[i])) return false;
            map.put(cur, words[i]);
        }
        return true;
    }

    public static boolean wordPatternAmazing(String pattern, String str) {
        Map index = new HashMap();
        String[] words = str.split("\\s");
        for (int i = 0; i < words.length; i++){
           if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
               return false;
        }
        return true;
    }

    public static int countPrimes(int n) {
        int ret = 0;
        while (n > 1){
            ret += isPrime(--n) ? 1 : 0;
        }
        return ret;
    }

    /**
     */
    private static boolean isPrime(int i) {
        if (i <= 1) return false;
        if (i == 2 || i == 3) return true;
        int sqrt = (int) Math.sqrt(i);
        while (sqrt >= 2){
            if (i % sqrt == 0) return false;
            sqrt--;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/count-primes/discuss/57588/My-simple-Java-solution
     */
    public static int countPrimesImprove(int n) {
        boolean[] notPrimeArr = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++){
            if (!notPrimeArr[i])
                count++;
            for (int j = 2; i * j < n; j++)
                notPrimeArr[i * j] = true;
        }
        return count;
    }
}
















