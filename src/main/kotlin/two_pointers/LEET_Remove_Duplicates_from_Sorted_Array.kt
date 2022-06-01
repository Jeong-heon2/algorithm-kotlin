package two_pointers

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size == 1) return 1
        var j = 1
        for(i in 1..nums.lastIndex) {
            if (nums[i] > nums[i-1]) nums[j++] = nums[i]
        }
        return j
    }
}