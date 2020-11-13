package space.harbour.hw5;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MyHashMapHw5<K, V> implements Map<K, V> {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyHashMapHw5<?, ?> that = (MyHashMapHw5<?, ?>) o;
        return bucketSize == that.bucketSize
                && Arrays.equals(buckets, that.buckets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bucketSize);
        result = 31 * result + Arrays.hashCode(buckets);
        return result;
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }

    private int bucketSize = 16;
    private LinkedList<Pair<K, V>>[] buckets = new LinkedList[bucketSize];

    public MyHashMapHw5() {
        clear();
    }

    @Override
    public int size() {
        int result = 0;
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            result += bucket.size();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private int keyToBucketIndex(Object key) {
        return Math.abs(key.hashCode() % bucketSize);
        //return key.hashCode() >> 27;
    }

    @Override
    public boolean containsKey(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                if (pair.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int i = keyToBucketIndex(key);
        for (Pair<K, V> pair : buckets[i]) {
            if (pair.key.equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        Pair<K, V> pair = new Pair<>(key, value);
        int index = keyToBucketIndex(key);
        if (containsKey(key)) {
            for (Pair<K, V> existingPair : buckets[index]) {
                if (existingPair.getKey().equals(key)) {
                    existingPair.value = pair.getValue();
                }
            }
        } else {
            buckets[index].add(pair);
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = keyToBucketIndex(key);
        buckets[index].removeIf(pair -> pair.key.equals(key));
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> pair : m.entrySet()) {
            put(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    // EXTRA POINT!! EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!
    // EXTRA POINT!! EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!
    // EXTRA POINT!! EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!EXTRA POINT!!

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                set.add(pair.getKey());
            }
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> collection = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                collection.add(pair.getValue());
            }
        }
        return collection;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        for (LinkedList<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                entrySet.add(new AbstractMap.SimpleEntry<K, V>(pair.getKey(), pair.getValue()));
            }
        }
        return entrySet;
    }

}
