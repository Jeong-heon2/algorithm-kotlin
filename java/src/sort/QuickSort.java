package sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 2, 7, 5};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int pivot = start;
        int i = start + 1;
        int j = end;
        int tmp;
        while (i <= j) {
            // pivot 보다 큰 값을 찾을 때 까지
            while (i <= end && arr[pivot] >= arr[i]) {
                i++;
            }
            // pivot 보다 작은 값 찾을 때 까지
            while (j > start && arr[j] >= arr[pivot]) {
                j--;
            }
            if (i > j) {
                // pivot 과 j 를 교체
                tmp = arr[j];
                arr[j] = arr[pivot];
                arr[pivot] = tmp;
            } else {
                // i 와 j 를 교환
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        // pivot과 j를 교체 했으니, j 기준 왼쪽
        quickSort(arr, start, j - 1);
        // j 기준 오른쪽
        quickSort(arr, j + 1, end);
    }
}
