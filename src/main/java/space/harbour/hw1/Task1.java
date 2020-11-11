package space.harbour.hw1;

public class Task1 {
        
    private static int give_me_one() {
        char s = '1';
        int k = Integer.parseInt(String.valueOf(s));
        return k;
    }
    
    public static void main(String[] args) {
        int one = give_me_one();
        int two = one * 2;
        System.out.println(two);
    }
}