package two_pointer;

public class LEET_Median_of_Two_Sorted_Arrays {

    public static void main(String[] args) {

    }
    /*
    투 포인터 문제.
    median 값을 구해야 하므로 짝수일때 홀수일때 경우가 달라진다. 상황에 맞게 target 을 설정.
    두 배열 맨 앞에서 부터 비교하면서 카운트를 증가.
    nums1.length + nums2.length 길이의 배열을 선언해서  두 배열을 합쳐서 정렬한 형태의 배열을 만드려고 했으나 공간적 낭비가 있다고 판단.
    새로운 배열 선언 없이 해결. 다만 로직이 조금 복잡하다.
     */
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int i = 0, j = 0, cnt = 0;
            boolean isEven = (nums1.length + nums2.length) % 2 == 0;
            int target = (nums1.length + nums2.length) / 2;
            if (isEven) target -= 1;

            while (i < nums1.length && j < nums2.length) {
                int cur = 0;
                if (nums1[i] > nums2[j]) {
                    cur = nums2[j++];
                } else {
                    cur = nums1[i++];
                }
                cnt++;
                //median 을 찾음
                if (cnt - 1 == target) {
                    // 짝/홀수 경우의 수가 다르다
                    if (isEven) {
                        if (i >= nums1.length) {
                            return (double) (cur + nums2[j]) / 2;
                        } else if (j >= nums2.length) {
                            return (double) (cur + nums1[i]) / 2;
                        } else {
                            if (nums1[i] > nums2[j]) {
                                return (double) (cur + nums2[j]) / 2;
                            } else {
                                return (double) (cur + nums1[i]) / 2;
                            }
                        }
                    } else {
                        return (double) cur;
                    }
                }

            }
            // median 못 찾고 while 문 종료.
            if (i < nums1.length) {
                i += target - cnt;
                if (isEven) return (double) (nums1[i] + nums1[i+1]) / 2;
                else return (double) nums1[i];
            } else {
                j += target - cnt;
                if (isEven) return (double) (nums2[j] + nums2[j+1]) / 2;
                else return (double) nums2[j];
            }
        }
    }


    /*
    leetCode 에서 가장 빠른 풀이 이다.
     */
    static class Solution2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if (m == n) {
                if (nums1[m-1] <= nums2[0]) return (nums1[m-1] + nums2[0]) / 2.0;
                else if (nums2[m-1] <= nums1[0]) return (nums2[m-1] + nums1[0]) / 2.0;
            } else if (m < n) {
                int[] tmp = nums1;
                nums1 = nums2;
                nums2 = tmp;
            }
            m = nums1.length;
            n = nums2.length;
            int lo = 0, hi = 2*m;
            int c1, c2, lmax1 = 0, rmin1 = 0, lmax2 = 0, rmin2 = 0;
            while (lo <= hi) {
                c1 = Math.max(Math.min((hi + lo) >> 1, m + n), m - n);
                c2 = (m + n) - c1;
                lmax1 = c1 == 0 ? Integer.MIN_VALUE : nums1[(c1 - 1) >> 1];
                rmin1 = c1 == 2*m ? Integer.MAX_VALUE : nums1[c1 >> 1];
                lmax2 = c2 == 0 ? Integer.MIN_VALUE : nums2[(c2 - 1) >> 1];
                rmin2 = c2 == 2*n ? Integer.MAX_VALUE : nums2[c2 >> 1];
                if (lmax1 > rmin2) hi = c1 - 1;
                else if (lmax2 > rmin1) lo = c1 + 1;
                else break;
            }
            return (Math.max(lmax1, lmax2) + Math.min(rmin1, rmin2)) / 2.0;
        }
    }
}
