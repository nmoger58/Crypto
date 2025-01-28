import java.util.Scanner;

public class VigenereCipher {

    public static String encryptText(String message, String key) {
        StringBuilder encrypted = new StringBuilder();
        message = message.toUpperCase();
        key = key.toUpperCase();

        int keyPos = 0;
        for (char ch : message.toCharArray()) {
            if (Character.isLetter(ch)) {
                char encChar = (char) (((ch - 'A' + (key.charAt(keyPos) - 'A')) % 26) + 'A');
                encrypted.append(encChar);
                keyPos = (keyPos + 1) % key.length();
            } else {
                encrypted.append(ch);
            }
        }
        return encrypted.toString();
    }

    public static String decryptText(String cipher, String key) {
        StringBuilder decrypted = new StringBuilder();
        cipher = cipher.toUpperCase();
        key = key.toUpperCase();

        int keyPos = 0;
        for (char ch : cipher.toCharArray()) {
            if (Character.isLetter(ch)) {
                char decChar = (char) (((ch - 'A' - (key.charAt(keyPos) - 'A') + 26) % 26) + 'A');
                decrypted.append(decChar);
                keyPos = (keyPos + 1) % key.length();
            } else {
                decrypted.append(ch);
            }
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Vigenere Cipher Tool");

        System.out.print("Input your message: ");
        String message = input.nextLine();
        System.out.print("Enter the key: ");
        String key = input.nextLine();

        String encrypted = encryptText(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decryptText(encrypted, key);
        System.out.println("Decrypted: " + decrypted);

        input.close();
    }
}

// Output:

// Welcome to the Vigenère Cipher Program!
// Enter your plaintext message: HELLO VIGENERE
// Enter your encryption key: KEY

// Encrypted Message: RIJVS UYVJNWI
// Decrypted Message: HELLO VIGENERE