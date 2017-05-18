package hashmap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author Ye_Wenda
 * @Date 5/18/2017
 */
@Getter
@Setter
@ToString
public class YwdHashMap<K, V> {
    Entry<K, V>[] nodes = new Entry[9999];

    public V get(K key) {
        int hashCode = key.hashCode();
        Entry<K, V> node = nodes[hashCode];
        if (node == null) return null;
        Entry<K, V> result = null;
        do {
            if (node.getKey().equals(key)) {
                result = node;
            }
        } while ((node = node.getNext()) != null);
        return result == null ? null : result.getValue();
    }

    public void set(K key, V value) {
        int hashCode = key.hashCode();
        Entry<K, V> node = new Entry<>();
        node.setKey(key);
        node.setValue(value);
        // node hash位置被占
        if (nodes[hashCode] != null) {
            Entry<K, V> last = nodes[hashCode];
            while (last.next != null) {
                last = node.getNext();
            }
            last.setNext(node);
        }
        // 新hashCode
        else {
            nodes[hashCode] = node;
        }
    }

    @Getter
    @Setter
    @ToString
    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
    }

    public static void main(String[] args) {
        YwdHashMap<String, String> ywdHashMap = new YwdHashMap<String, String>();
        ywdHashMap.set("AB", "AA");
        ywdHashMap.set("BA", "BB");
        ywdHashMap.set("CA", "CC");
        System.out.println(ywdHashMap.get("BA"));
    }
}
