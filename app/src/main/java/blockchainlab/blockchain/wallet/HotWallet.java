package blockchainlab.blockchain.wallet;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.block.Transaction;

/**
 * A hot wallet is a wallet that, other than being able to receive money, is also able to send them
 */
public class HotWallet extends ColdWallet {
    private final KeyPair kp;

    /**
     * A constructor that takes a pair of Public and Private key and returns a wallet
     * @param kp the Public and Private keys
     */
    public HotWallet(KeyPair kp) {
        super(kp.getPublic());
        this.kp = kp;
    }

    /**
     * A function to sign a transaction that can be then broadcast on the network. For the signature we first hash the
     * data and then sign it.
     * @param t the transaction to sign
     * @return the signed transaction
     * @throws NoSuchAlgorithmException if we don't have the algorithm to sign the transaction
     * @throws InvalidKeyException if the key is not valid
     * @throws SignatureException if there is a problem during the signature process
     */
    public SignedTransaction signTransaction(Transaction t) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (t.from.pubKey != this.kp.getPublic()) {
            // of course we can't sign a transaction that's not from our wallet
            throw new IllegalArgumentException("Transaction is not from this wallet");
        }

        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(this.kp.getPrivate());
        s.update(t.toString().getBytes());
        byte[] sign = s.sign();
        // the signature is base64 encoded to be user-friendly, so we can visualize it using only ascii characters
        return new SignedTransaction(t, Base64.getEncoder().encodeToString(sign));
    }

    /**
     * This static method generates a random pair of private and public keys
     * @return a KeyPair
     * @throws NoSuchAlgorithmException this exception is thrown if we're missing the desired algorithm
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.genKeyPair();
    }
}
