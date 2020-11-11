package space.harbour.hw2;

public class Pascal {

    // BINOMIAL EXPANSIONS: https://www.youtube.com/watch?v=OMr9ZF1jgNc&t=211s

    public static void main(String[] args) {
        System.out.println();
        int n;
        n = 8;
        System.out.println("Burnt PascalTriangle");
        System.out.println("--------------------");
        // Outer loop to print n rows
        int i;
        int j;
        for (i = 0; i <= n; i++) {
            // First inner loop to print blank spaces in print statement
            for (j = 0; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Secondary inner loop, from J to I, consisting of space + nCr binomial value
            for (j = 0; j <= i; j++) {
                System.out.print(" " + ncr(i, j));
            }
            // Newline after each row!
            System.out.println();
            //System.out.println("level =" + i);
        }
    }

    // Factorial function scripted
    static int factorial(int n) {
        int f;
        for (f = 1; n > 1; n--) {
            f *= n;
        }
        return f;
    }

    // Compute standardized nCr function from probability theory
    static int ncr(int n, int r) {
        return factorial(n) / (factorial(n - r) * factorial(r));
    }

}

