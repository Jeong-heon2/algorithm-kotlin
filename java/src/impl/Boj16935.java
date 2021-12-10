package impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj16935 {
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] queries = br.readLine().split(" ");

        br.close();

        for (String query : queries) {
            if (query.equals("1")) {
                upsideDown();
            } else if (query.equals("2")) {
                reverseLeftAndRight();
            } else if (query.equals("3")) {
                rotateRight();
            } else if (query.equals("4")) {
                rotateLeft();
            } else if (query.equals("5")) {
                partialRotateRight();
            } else if (query.equals("6")) {
                partialRotateLeft();
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0 ; i < arr.length; i++) {
            for (int j = 0 ; j < arr[0].length; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    //상하반전
    private static void upsideDown() {
        int lastIdx = arr.length - 1;
        for (int i = 0 ; i <= lastIdx/2; i++) {
            int[] tmp = arr[i];
            arr[i] = arr[lastIdx - i];
            arr[lastIdx - i] = tmp;
        }
    }

    //좌우반전
    private static void reverseLeftAndRight() {
        int lastIdx = arr[0].length - 1;
        for (int i = 0 ; i <= lastIdx/2; i++) {
            int[] tmp = columnToRow(i, arr);
            writeRowToColumn(columnToRow(lastIdx - i, arr), i, arr);
            writeRowToColumn(tmp, lastIdx - i, arr);
        }
    }

    private static void writeRowToColumn(int[] row, int i, int[][] targetArr) {
        for (int j = 0 ; j < targetArr.length; j++) {
            targetArr[j][i] = row[j];
        }
    }

    private static int[] columnToRow(int i, int[][] targetArr) {
        int[] res = new int[targetArr.length];
        for (int j = 0; j < targetArr.length; j++) {
            res[j] = targetArr[j][i];
        }
        return res;
    }

    //오른쪽 90도 회전
    private static void rotateRight() {
        int[][] newArr = new int[arr[0].length][arr.length];
        int lastIdx = newArr[0].length - 1;
        for (int i = 0 ; i < arr.length; i++) {
            writeRowToColumn(arr[i], lastIdx - i, newArr);
        }
        arr = newArr;
    }

    //왼쪽 90도 회전
    //첫행의 reverse를 첫 열에 쓴다
    //두번째행의 reverse를 두번째 열에 쓴다.
    private static void rotateLeft() {
        int[][] mewArr = new int[arr[0].length][arr.length];
        for (int i = 0 ; i < arr.length; i++) {
            writeRowToColumn(reverse(arr[i]), i, mewArr);
        }
        arr = mewArr;
    }

    private static int[] reverse(int[] targetArr) {
        int[] reversedArr = new int[targetArr.length];
        for (int i = 0 ; i < targetArr.length; i++) {
            reversedArr[i] = targetArr[targetArr.length - 1 - i];
        }
        return reversedArr;
    }

    //부분 배열 오른쪽 회전
    private static void partialRotateRight() {
        int partialN = arr.length/2;
        int partialM = arr[0].length/2;
        int[][] partialOne = getPartial(0, 0, partialM - 1, partialN -1, arr);
        int[][] partialTwo = getPartial(partialM, 0, arr[0].length - 1, partialN - 1, arr);
        int[][] partialThree = getPartial(partialM, partialN, arr[0].length - 1, arr.length - 1, arr);
        int[][] partialFour = getPartial(0, partialN, partialM - 1, arr.length - 1, arr);
        writePartial(partialOne, partialM, 0, arr[0].length - 1, partialN - 1, arr);
        writePartial(partialTwo, partialM, partialN, arr[0].length - 1, arr.length - 1, arr);
        writePartial(partialThree, 0, partialN, partialM - 1, arr.length - 1, arr);
        writePartial(partialFour, 0, 0, partialM - 1, partialN -1, arr);
    }

    //부분 배열 왼쪽 회전
    private static void partialRotateLeft() {
        int partialN = arr.length/2;
        int partialM = arr[0].length/2;
        int[][] partialOne = getPartial(0, 0, partialM - 1, partialN -1, arr);
        int[][] partialTwo = getPartial(partialM, 0, arr[0].length - 1, partialN - 1, arr);
        int[][] partialThree = getPartial(partialM, partialN, arr[0].length - 1, arr.length - 1, arr);
        int[][] partialFour = getPartial(0, partialN, partialM - 1, arr.length - 1, arr);
        writePartial(partialOne, 0, partialN, partialM - 1, arr.length - 1, arr);
        writePartial(partialTwo, 0, 0, partialM - 1, partialN -1, arr);
        writePartial(partialThree, partialM, 0, arr[0].length - 1, partialN - 1, arr);
        writePartial(partialFour, partialM, partialN, arr[0].length - 1, arr.length - 1, arr);
    }

    private static void writePartial(int[][] partialArr, int x1, int y1, int x2, int y2, int[][] targetArr) {
        for (int i = y1, y = 0; i <= y2; i++, y++) {
            for (int j = x1, x = 0; j <= x2; j++, x++) {
                targetArr[i][j] = partialArr[y][x];
            }
        }
    }

    private static int[][] getPartial(int x1, int y1, int x2, int y2, int[][] targetArr) {
        int n = y2 - y1 + 1;
        int m = x2 - x1 + 1;
        int[][] partialArr = new int[n][m];
        for (int i = y1, y = 0; i <= y2; i++, y++) {
            for (int j = x1, x = 0; j <= x2; j++, x++) {
                partialArr[y][x] = targetArr[i][j];
            }
        }
        return partialArr;
    }
}
