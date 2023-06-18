package blockchainlab.blockchain.block;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class SignedTransaction extends Transaction{
    public final String sign;

    public SignedTransaction(Transaction transaction, String sign) {
        super(transaction.amount, transaction.from, transaction.to, transaction.fee);
        this.sign = sign;
    }

    public void verify() throws InvalidTransactionException {
        super.verify();

        Signature sig;
        try {
            sig = Signature.getInstance("SHA1withRSA");
        } catch (NoSuchAlgorithmException e) {
            throw new InvalidTransactionException(e);
        }
        
        try {
            sig.initVerify(this.from.pubKey);
        } catch (InvalidKeyException e) {
            throw new InvalidTransactionException(e);
        }
        byte[] signed;

        try {
            sig.update(this.toString().getBytes());
            signed = sig.sign();
        } catch (SignatureException e) {
            throw new InvalidTransactionException(e);
        }

        if (Base64.getEncoder().encodeToString(signed) != this.sign) {
            throw new InvalidTransactionException("Invalid signature");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | " + sign;
    }
}
