package pg_hash;

import java.util.Arrays;
import java.util.HashMap;

public class Q2 {
    public static void main(String[] args) {
        String[] arr = {"11","1212", "22222", "10", "9"};
    }
    class Solution {
        public boolean solution(String[] phone_book) {
            HashMap<String, Boolean> map = new HashMap<String, Boolean>();
            Arrays.sort(phone_book);
            for (String phone : phone_book) {
                String sub = "";
                for (int i = 0; i < phone.length(); i++) {
                    sub += phone.charAt(i);
                    if (map.containsKey(sub)) {
                        return false;
                    }
                }
                map.put(phone, true);
            }
            return true;
        }
    }
    class Solution2 {
        public boolean solution(String[] phoneBook) {
            Arrays.sort(phoneBook);
            boolean result = true;
            for (int i=0; i<phoneBook.length-1; i++) {
                if (phoneBook[i+1].startsWith(phoneBook[i])) {
                    result = false;
                    break;
                }
            }
            return result;
        }
    }
}
