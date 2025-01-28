import java.util.Scanner;

public class extendedEuclidean {

    // Extended Euclidean Algorithm to find gcd and coefficients x, y
    public static int[] extendedEuclid(int a, int m) {
        if (m == 0) {
            return new int[] { a, 1, 0 };
        }

        int[] result = extendedEuclid(m, a % m);
        int gcd = result[0];
        int x1 = result[1];
        int y1 = result[2];

        // Update x and y using the recursive results
        int x = y1;
        int y = x1 - (a / m) * y1;

        return new int[] { gcd, x, y };
    }

    // Function to find the modular inverse of a mod m
    public static int modInverse(int a, int m) {
        int[] result = extendedEuclid(a, m);
        int gcd = result[0];
        int x = result[1];

        // If gcd(a, m) is not 1, then the inverse does not exist
        if (gcd != 1) {
            return -1; // No modular inverse exists
        } else {
            // Ensure x is positive and return it as the modular inverse
            return (x % m + m) % m;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input from the user
        System.out.print("Enter a number (a): ");
        int a = scanner.nextInt();

        int m = 26; // Number of letters in the alphabet

        // Calculate modular inverse
        int inverse = modInverse(a, m);

        if (inverse == -1) {
            System.out.println("No modular inverse exists for " + a + " modulo " + m);
        } else {
            System.out.println("The modular inverse of " + a + " modulo " + m + " is: " + inverse);
        }

        scanner.close();
    }
}

// Output:
// Enter a number (a): 5
// The modular inverse of 5 modulo 26 is: 21

// Enter a number (a): 13
// No modular inverse exists for 13 modulo 26
