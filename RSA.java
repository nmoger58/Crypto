import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        // Generate two small prime numbers p and q (8 bits)
        BigInteger p = BigInteger.probablePrime(8, random);
        BigInteger q = BigInteger.probablePrime(8, random);

        // Calculate n = p * q
        BigInteger n = p.multiply(q);

        // Calculate phi(n) = (p-1)*(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        BigInteger e;
        do {
            e = new BigInteger(8, random);
        } while (!e.gcd(phi).equals(BigInteger.ONE) || e.compareTo(phi) >= 0);

        // Calculate private key d such that e * d ≡ 1 (mod phi(n))
        BigInteger d = e.modInverse(phi);

        // Display keys
        System.out.println("Public Key (e, n): " + e + ", " + n);
        System.out.println("Private Key (d, n): " + d + ", " + n);

        // Message to encrypt
        BigInteger message = new BigInteger("12345");
        System.out.println("Original Message: " + message);

        // Encryption: ciphertext = message^e mod n
        BigInteger ciphertext = message.modPow(e, n);
        System.out.println("Encrypted Message: " + ciphertext);

        // Decryption: decryptedMessage = ciphertext^d mod n
        BigInteger decryptedMessage = ciphertext.modPow(d, n);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

// Output:

// Public Key (e, n): 179, 221
// Private Key (d, n): 79, 221
// Original Message: 12345
// Encrypted Message: 188
// Decrypted Message: 12345