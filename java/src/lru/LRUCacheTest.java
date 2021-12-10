package lru;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LRUCacheTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("캐시 사이즈 입력 해주세요");
        int cacheSize = Integer.parseInt(br.readLine());
        LRUCache<String, Integer> lruCache = new LRUCache<>(cacheSize);
        System.out.println("캐시 사이즈 : " + lruCache.getCacheSize());
        while (true) {
            System.out.println("1: 캐시에 데이터 넣기, 2: 캐시에 데이터 조회, 3: 현재 캐시 상태 출력, 4: 종료");
            int query = Integer.parseInt(br.readLine());
            if (query == 1) {
                System.out.println("키 값 입력 : ");
                String key = br.readLine();
                System.out.println("데이터 입력 : ");
                int data = Integer.parseInt(br.readLine());
                lruCache.insert(key, data);
            } else if (query == 2) {
                System.out.println("키 값 입력 : ");
                String key = br.readLine();
                Integer res = lruCache.get(key);
                if (res == null) {
                    System.out.println("데이터 없음!");
                } else {
                    System.out.println("조회 결과 -> 데이터 : " + res);
                }
            } else if (query == 3) {
                lruCache.printState();
            } else {
                break;
            }
        }
    }
}
