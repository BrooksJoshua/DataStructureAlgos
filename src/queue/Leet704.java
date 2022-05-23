package queue;

/**
 * @author Joshua.H.Brooks
 * @description 二分查找
 * https://leetcode.cn/problems/binary-search/solution/er-fen-cha-zhao-by-leetcode-solution-f0xw/
 * @date 2022-05-23 14:53
 */
public class Leet704 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
    }

}

class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            //用这种方式计算mid能有效避免边界问题
            int mid = (high - low) >> 1 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}