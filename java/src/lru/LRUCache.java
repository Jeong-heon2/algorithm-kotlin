package lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, T> {
    private final int cacheSize;
    private final Node<K, T> head = new Node<>(null, null);
    private final Node<K, T> tail = new Node<>(null, null);
    private final HashMap<K, Node<K, T>> map = new HashMap<>();

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
        head.next = tail;
        tail.prev = head;
    }

    private void insertToHead(K key, Node<K, T> node) {
        head.next.prev = node;
        node.prev = head;
        node.next = head.next;
        head.next = node;
        map.put(key, node);
    }

    private void remove(K key, Node<K, T> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        map.remove(key);
    }

    public void insert(K key, T data) {
        Node<K, T> node = new Node<>(key, data);
        if (map.containsKey(key)) {
            // cache에 이미 있으면
            // 링크드 리스트와 map에서 삭제하고
            remove(key, map.get(key));
        } else {
            // 새로 노드에 추가해야 하므로
            // cache 크기가 다 차지 않았는가?
            if (map.size() >= cacheSize) {
                // tail 이 가리키는 것을 지우고
                remove(tail.prev.key, tail.prev);
            }
        }

        // head의 next가 이 노드를 가리키도록
        insertToHead(key, node);
    }

    public T get(K key) {
        if (map.containsKey(key)) {
            // cache 에 있으면 head 로 옮기고 리턴
            Node<K, T> targetNode = map.get(key);
            remove(key, targetNode);
            insertToHead(key, targetNode);
            return targetNode.data;
        } else {
            return null;
        }
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getFreeSize() {
        return cacheSize - map.size();
    }

    public void printState() {
        System.out.println("캐시 사이즈 : " + getCacheSize());
        System.out.println("가용 캐시 사이즈 : " + getFreeSize());
        System.out.println("=====  캐시에 저장 된 데이터 목록 =====");
        for (Map.Entry<K, Node<K, T>> entry : map.entrySet()) {
            System.out.println(entry.getKey().toString() + " : " + entry.getValue().data.toString());
        }
    }

    static class Node<K, T> {
        Node<K, T> prev;
        Node<K, T> next;
        K key;
        T data;
        public Node(K key, T data) {
            this.key = key;
            this.data = data;
        }
    }
}
