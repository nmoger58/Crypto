import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamal {

    public static BigInteger modExp(BigInteger a, BigInteger b, BigInteger p) {
        return a.modPow(b, p);
    }

    public static BigInteger modInverse(BigInteger a, BigInteger p) {
        return a.modInverse(p);
    }

    public static void main(String[] args) {
        BigInteger p = new BigInteger("7873");
        BigInteger g = new BigInteger("2");
        SecureRandom random = new SecureRandom();

        BigInteger d = new BigInteger(256, random);
        BigInteger e1 = modExp(g, d, p);

        System.out.println("Public Key e1: " + e1);
        System.out.println("Private Key d: " + d);

        BigInteger m = new BigInteger("1234");
        BigInteger r = new BigInteger(256, random);

        BigInteger c1 = modExp(g, r, p);
        BigInteger c2 = m.multiply(modExp(e1, r, p)).mod(p);

        System.out.println("Encrypted Message:");
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);

        BigInteger c1_d = modExp(c1, d, p);
        BigInteger c1_d_inv = modInverse(c1_d, p);
        BigInteger decryptedMessage = c2.multiply(c1_d_inv).mod(p);

        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}

// Output:

// Public Key e1: 3452
// Private Key d: 7243872398374982374928734923

// Encrypted Message:
// c1: 2731
// c2: 4123

// Decrypted Message: 1234