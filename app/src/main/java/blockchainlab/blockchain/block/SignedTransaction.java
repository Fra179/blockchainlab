package blockchainlab.blockchain.block;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class SignedTransaction extends Transaction{
    public final String sign;

    /**
     * This class represent a normal transaction that has been signed
     * @param transaction the transaction that has been signed
     * @param sign the sign
     */
    public SignedTransaction(Transaction transaction, String sign) {
        super(transaction.amount, transaction.from, transaction.to, transaction.fee);
        this.sign = sign;
    }

    /**
     * Verify the sign, if the sign is not valid we throw an exception
     * @throws InvalidTransactionException this exception is thrown only if we fail to validate the sign
     */
    public void verify() throws InvalidTransactionException {
        super.verify();

        Signature sig;
        try {
            // We first hash the data with SHA1 and then sign with RSA
            sig = Signature.getInstance("SHA1withRSA");
        } catch (NoSuchAlgorithmException e) {
            // this should never happen, but if it does we panic and mark the transaction as invalid
            throw new InvalidTransactionException(e);
        }
        
        try {
            sig.initVerify(this.from.pubKey);
        } catch (InvalidKeyException e) {
            throw new InvalidTransactionException(e);
        }

        // the signature is base64 encoded, to be user friendly, so we first need to decode it
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
