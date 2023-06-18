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

        byte[] signature = Base64.getDecoder().decode(this.sign);
        boolean valid;

        try {
            sig.update(super.toString().getBytes());
            valid = sig.verify(signature);
        } catch (SignatureException e) {
            throw new InvalidTransactionException(e);
        }


        if (!valid) {
            throw new InvalidTransactionException("Invalid signature");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | " + sign;
    }
}
