package back_tracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet_Combination_sum2 {
    class Solution {

        int[] arr;
        int target;
        ArrayList<List<Integer>> ans;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            arr = candidates;
            this.target = target;
            ans = new ArrayList<>();

            recur(new ArrayList<Integer>(), 0, 0);
            return ans;
        }

        private boolean recur(ArrayList<Integer> list, int sum, int i) {
            if (sum == target) {
                ArrayList<Integer> res = new ArrayList<>();
                res.addAll(list);
                ans.add(res);
                return true;
            } else if(sum > target) {
                return true;
            } else {
                for (int j = i; j < arr.length; j++) {
                    if (j > i && arr[j-1] == arr[j]) continue;
                    list.add(arr[j]);
                    boolean res = recur(list, sum + arr[j], j+1);
                    list.remove(list.size()-1);
                    if (res) break;
                }
                return false;
            }
        }
    }
}
