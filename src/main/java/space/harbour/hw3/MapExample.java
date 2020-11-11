package space.harbour.hw3;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {

        // Boys
        MyHashMap<String, String> contacts = new MyHashMap<>();
        contacts.put("Ahmed", "1111111");
        contacts.put("Vasilii", "2222222");
        contacts.put("Pierre", "3333333");

        // Girls
        MyHashMap<String, String> contacts2 = new MyHashMap<>();
        contacts2.put("Dienke", "444444");
        contacts2.put("Wilhelmina", "555555555");
        contacts2.put("Katarina", "666666666");
        contacts2.putAll(contacts2);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("-----------------------------------------TESTING-------------------");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Size of our hashmap :  " + contacts.size());
        System.out.println("Is our contact list em :  " + contacts.isEmpty());
        System.out.println("Vasilii in contact list? :  " + contacts.containsKey("Vasilii"));
        System.out.println("Vasilii in contact list? :  " + contacts.containsValue("12824"));
        System.out.println("Value corresponding to Pierre? :  " + contacts.get("Pierre"));
        System.out.println("\n" + "Put K,V in the map :  " + contacts.put("Berend", "696969"));
        System.out.println("Berend in contact list? :  " + contacts.containsKey("Berend") + "\n");
        System.out.println("\n" + "Remove Ahmed from contacts :  " + contacts.remove("Ahmed"));
        System.out.println("Ahmed in contact list? :  " + contacts.containsKey("Ahmed") + "\n");
        System.out.println("Put all the girls with the boys ");
        System.out.println("Are the girls in the contact list? :  " + contacts.keySet());
        System.out.println("What are their phone numbers? :  " + contacts.values());
        System.out.println("Which belong to each other?  :  " + contacts.entrySet());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------");
    }
}
