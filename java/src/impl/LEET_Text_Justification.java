package impl;

import java.util.ArrayList;
import java.util.List;

public class LEET_Text_Justification {
    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            int i = 0;
            int size = words.length;
            List<String> list = new ArrayList<>();
            while (i < size) {
                int len = words[i].length();
                int wordSize = words[i].length();
                int start = i++;
                while (i < size && len + words[i].length() + 1 <= maxWidth) {
                    len += words[i].length() + 1;
                    wordSize += words[i++].length();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(words[start]);

                if (i >= size) {
                    for (int j = start + 1 ; j < size ; j++) {
                        sb.append(" ");
                        sb.append(words[j]);
                    }
                    while(sb.length() < maxWidth) {
                        sb.append(" ");
                    }
                } else {
                    if (i - start - 1 == 0) {
                        while(sb.length() < maxWidth) {
                            sb.append(" ");
                        }
                    } else {
                        int space = (maxWidth - wordSize) / (i - start - 1);
                        int remain = (maxWidth - wordSize) % (i - start - 1);
                        for (int j = start + 1; j <= i-1 ; j++) {
                            sb.append(" ".repeat(Math.max(0, space)));
                            if (remain-- > 0) {
                                sb.append(" ");
                            }
                            sb.append(words[j]);
                        }
                    }
                }
                list.add(sb.toString());
            }
            return list;
        }
    }
}
