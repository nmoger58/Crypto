import java.util.Scanner;

public class AffineCipher{

    // Function to calculate the modular inverse
    private static int findModularInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return -1;
    }

    // Function to encrypt the given text
    private static String encryptText(String input, int a, int b) {
        StringBuilder encrypted = new StringBuilder();
        input = input.toLowerCase();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int x = c - 'a';
                char encryptedChar = (char) (((a * x + b) % 26) + 'a');
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(c); // Keep other characters unchanged
            }
        }
        return encrypted.toString();
    }

    // Function to decrypt the encrypted text
    private static String decryptText(String input, int a, int b) {
        StringBuilder decrypted = new StringBuilder();
        int aInverse = findModularInverse(a, 26);

        if (aInverse == -1) {
            return "Error: 'a' has no modular inverse under mod 26.";
        }

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                int y = c - 'a';
                char decryptedChar = (char) (((aInverse * (y - b + 26)) % 26) + 'a');
                decrypted.append(decryptedChar);
            } else {
                decrypted.append(c); // Keep other characters unchanged
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Affine Cipher Program");

        // Input for encryption
        System.out.print("Input text to encrypt: ");
        String text = sc.nextLine();
        System.out.print("Enter key multiplier (a): ");
        int aKey = sc.nextInt();
        System.out.print("Enter key shift (b): ");
        int bKey = sc.nextInt();

        // Perform encryption
        String cipherText = encryptText(text, aKey, bKey);
        System.out.println("Cipher Text: " + cipherText);

        // Perform decryption
        String originalText = decryptText(cipherText, aKey, bKey);
        System.out.println("Decrypted Text: " + originalText);

        sc.close();
    }
}

// Output:

// Welcome to Affine Cipher Program
// Input text to encrypt: hello world
// Enter key multiplier (a): 7
// Enter key shift (b): 3
// Cipher Text: axeeh phkew
// Decrypted Text: hello world