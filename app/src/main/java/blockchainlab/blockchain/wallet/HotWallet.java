package blockchainlab.blockchain.wallet;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

import blockchainlab.blockchain.block.Transaction;

public class HotWallet extends ColdWallet {
    private KeyPair kp;
    int balance = 0;

    public HotWallet(KeyPair kp) throws NoSuchAlgorithmException {
        super(kp.getPublic());
        this.kp = kp;
    }

    public String signTransaction(Transaction t) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (t.from.pubKey != this.kp.getPublic()) {
            throw new IllegalArgumentException("Transaction is not from this wallet");
        }

        Signature s = Signature.getInstance("SHA1withRSA");
        s.initSign(this.kp.getPrivate());
        s.update(t.toString().getBytes());
        byte[] sign = s.sign();
        return Base64.getEncoder().encodeToString(sign);
    }    

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        return kpg.genKeyPair();
    }
}
