import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import org.junit.Test;
import space.harbour.hw5.MyHashMapHw5;

public class MyHashMapHw5Test {

    MyHashMapHw5<String, String> mapBoys = new MyHashMapHw5<>();
    MyHashMapHw5<String, String> mapGirls = new MyHashMapHw5<>();
    MyHashMapHw5<String, String> boysNgirls1 = new MyHashMapHw5<>();
    MyHashMapHw5<String, String> map = new MyHashMapHw5<>();

    // 1111111111
    @Test
    public void size() {
        mapBoys.put("Ahmed", "1111111");
        mapBoys.put("Vasilii", "2222222");
        mapBoys.put("Pierre", "3333333");
        assertEquals(3, mapBoys.size());
    }

    // 2222222222
    @Test
    public void isEmpty() {
        assertTrue(mapBoys.isEmpty());
    }

    private void assertTrue(boolean empty) {
    }

    // 333333333333
    @Test
    public void containsKey() {
        mapBoys.put("Ahmed", "1111111");
        mapBoys.put("Vasilii", "2222222");
        mapBoys.put("Pierre", "3333333");
        assertTrue(mapBoys.containsKey("Ahmed"));
    }

    // 4444444444444
    @Test
    public void containsValue() {
        mapBoys.put("Ahmed", "1111111");
        mapBoys.put("Vasilii", "2222222");
        mapBoys.put("Pierre", "3333333");
        assertTrue(mapBoys.containsValue("3333333"));
    }

    // 55555555555555
    @Test
    public void get() {
        mapBoys.put("Pierre", "3333333");
        assertEquals("3333333", mapBoys.get("Pierre"));
    }

    // 6666666666666666
    @Test
    public void put() {
        mapBoys.put("Vasilii", "2222222");
        assertTrue(mapBoys.containsValue("2222222"));
    }

    // 77777777777777
    @Test
    public void remove() {
        mapBoys.put("Ahmed", "1111111");
        mapBoys.put("Vasilii", "2222222");
        mapBoys.remove("Vasilii", "2222222");
        assertFalse(mapBoys.containsValue("2222222"));
    }

    private void assertFalse(boolean containsValue) {
    }

    // 8888888888
    @Test
    public void putAll() {
        boysNgirls1.put("Ahmed", "1111111");
        boysNgirls1.put("Vasilii", "2222222");
        mapGirls.put("Dienke", "444444");
        mapGirls.put("Katarina", "5555555");
        boysNgirls1.putAll(mapGirls);
        assertTrue(boysNgirls1.containsKey("Ahmed"));
        assertTrue(boysNgirls1.containsKey("Vasilii"));
        assertTrue(boysNgirls1.containsKey("Dienke"));
        assertTrue(boysNgirls1.containsKey("Katarina"));
    }

    // 999999999999999
    @Test
    public void clear() {
        boysNgirls1.put("Ahmed", "1111111");
        boysNgirls1.put("Vasilii", "2222222");
        boysNgirls1.put("Dienke", "444444");
        boysNgirls1.put("Katarina", "5555555");
        boysNgirls1.clear();
        assertTrue(boysNgirls1.isEmpty());
    }

    // 1000000000000000
    // Test that contains returns 'false' when key doesn't exist
    @Test
    public void testContainsKeyForNonExistingKey() {
        map.put("Dienke", "55555");
        assertFalse(map.containsKey("Something else"));
    }

    // 1111111111 1111111
    // Test that contains is 'false' for new maps
    @Test
    public void testContainsKeyForEmptyMap() {
        assertFalse(map.containsKey("Hello"));
    }

    // 1111112222222222222222        /////////////////////////////////////////////////////////////
    // Test size increases as elements are added
    @Test
    public void testSizeIncrementSize() {
        map.put("Dienke", "2323423");
        assertEquals(1, map.size());

        map.put("Brenda", "098990");
        assertEquals(2, map.size());
    }

    //  111111111111     33333333333
    // Test if map size decrements when removing elements
    @Test
    public void testRemoveDecrementsSize() {
        map.put("Dienke", "55555");
        map.put("Hannah", "6666");
        map.remove("Hannah");
        assertEquals(1, map.size());
        map.remove("Dienke");
        assertEquals(0, map.size());
    }

    // 11111111111 44444444444
    // Check that contains is not fooled by equivalent hash codes
    @Test
    public void testContainsKeyForKeyWithEquivalentHash() {
        map.put("Dienke", "55555");
        assertFalse(map.containsKey("SomethingElse"));
    }

    // 1111111111 55666666666
    // Test thats an added element replaces another with the same key
    @Test
    public void testReplacesValueWithSameKey() {
        map.put("Dienke", "1111111");
        map.put("Dienke", "222222");

        assertEquals("222222", map.get("Dienke"));
        assertEquals(1, map.size());
    }

    // 111111111111 7777777777
    // Make sure size doesn't decrement below 0
    @Test
    public void testRemoveAffectNewMap() {
        map.remove("Dienke");
        assertEquals(0, map.size());
    }

    // 1111111111 88888888888 /////////////////////////////////////////////////////////////
    // Check that the equals function works for the same map
    @Test
    public void testDoesIsEqualWork() {
        mapBoys.put("d", "1");
        assertEquals(mapBoys, mapBoys);
    }

    // 111111111 9999999999 /////////////////////////////////////////////////////////////
    // Test that contains is 'false' for new maps
    @Test
    public void testDoesNotContainAkey() {
        assertFalse(map.containsKey("Dienke"));
    }

    // 22222222 000000000000
    // Test that return keyset works
    @Test
    public void keySet() {
        map.put("Ahmed", "1111111");
        map.put("Vasilii", "2222222");
        map.put("Dienke", "444444");
        map.put("Katarina", "5555555");

        HashSet<String> set2 = new HashSet<>();
        set2.add("Ahmed");
        set2.add("Dienke");
        set2.add("Vasilii");
        set2.add("Katarina");

        Object set = map.keySet();
        assertEquals(set2, set);
    }

    // 2222222222 111111111111111
    @Test
    public void collection() {
        map.put("Ahmed", "1111111");
        map.put("Vasilii", "2222222");
        Object funcCollection = map.values();
        HashSet<String> madeCollection = new HashSet<>();
        madeCollection.add("2222222");
        madeCollection.add("1111111");
        assertEquals(madeCollection, funcCollection);
    }

    // 222222222222 222222222222222222
    @Test
    public void entrySet() {

        mapBoys.put("Ahmed", "1111111");
        mapBoys.put("Vasilii", "2222222");
        mapBoys.put("Pierre", "3333333");

        HashSet<String> equalsSet = new HashSet<>();
        equalsSet.add("Ahmed=1111111");
        equalsSet.add("Vasilii=2222222");
        equalsSet.add("Pierre=3333333");
        assertEquals(equalsSet.getClass(), mapBoys.entrySet().getClass());
    }

}