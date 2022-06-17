package impl;

import java.util.ArrayList;
import java.util.List;

public class LEET_SPIRAL_MATRIX {
    public List<Integer> spiralOrder(int[][] matrix) {
        int elementsCount = matrix[0].length * matrix.length;
        int xMax = matrix[0].length - 1;
        int yMax = matrix.length - 1;
        int xMin = 0;
        int yMin = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int x = 0;
        int y = 0;
        while(list.size() < elementsCount) {
            while(x <= xMax) {
                list.add(matrix[y][x++]);
            }
            x--;
            while(++y <= yMax) {
                list.add(matrix[y][x]);
            }
            y--;
            if (yMin != yMax) {
                while(--x >= xMin) {
                    list.add(matrix[y][x]);
                }
                x++;
            }
            if (xMin != xMax) {
                while(--y > yMin) {
                    list.add(matrix[y][x]);
                }
            }
            y++;
            x++;
            xMax--;
            yMax--;
            xMin++;
            yMin++;
        }
        return list;
    }
}
