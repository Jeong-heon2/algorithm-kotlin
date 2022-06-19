package hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LEET_GROUP_ANAGRAM {
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<Group> groups = new ArrayList<>();
            for(String str : strs) {
                boolean flag = false;
                for (Group group : groups) {
                    boolean flag2 = true;
                    for (int i = 0 ; i < str.length(); i++) {
                        if (!group.anagram.contains(str.charAt(i))) {
                            flag2 = false;
                            break;
                        }
                    }
                    if (flag2) {
                        group.list.add(str);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    groups.add(new Group(str));
                }
            }
            return groups.stream().map(group ->
                group.list
            ).sorted(Comparator.comparingInt(List::size)).collect(Collectors.toList());
        }

        class Group {
            HashSet<Character> anagram;
            List<String> list;

            public Group (String a) {
                this.anagram = new HashSet<>();
                for (int i = 0 ; i < a.length(); i++) {
                    anagram.add(a.charAt(i));
                }
                this.list = new ArrayList<>();
            }
        }
    }
}
