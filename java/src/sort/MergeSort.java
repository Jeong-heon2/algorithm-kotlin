package sort;

public class MergeSort {
    static int N;
    static int[] sorted;
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2};
        N = arr.length;
        sorted = new int[N];
        mergeSort(arr, 0, N - 1);
        for (int i = 0; i < N ; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        //정렬된 두 집합을  정렬된 하나의 집합으로 merge
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                sorted[k++] = arr[i++];
            } else {
                sorted[k++] = arr[j++];
            }
        }
        //나머지 것들도 merge
        if (i > mid) {
            //i가 먼저 끝남
            for (int t = j; t <= right; t++) {
                sorted[k++] = arr[t];
            }
        } else {
            //j가 먼저 끝남
            for (int t = i; t <= mid; t++) {
                sorted[k++] = arr[t];
            }
        }
        //기존 배열에 옮기기
        for (int t = left; t <= right; t++) {
            arr[t] = sorted[t];
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        // 반으로 나눠야 함
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
}
