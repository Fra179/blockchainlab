package blockchainlab.blockchain.block;

import blockchainlab.blockchain.wallet.ColdWallet;
import blockchainlab.blockchain.wallet.HotWallet;

import java.util.Arrays;

public class Transaction {
    public final double amount;
    public final HotWallet from;
    public final ColdWallet to;
    public final double fee;

    public Transaction(double amount, HotWallet from, ColdWallet to, double fee) {
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.fee = fee;
    }

    @Override
    public String toString() {
        return from + "-" + amount + "(" + fee + ")" + "->" + to;
    }

    /**
     * This function tests a few basic properties that a transaction should have
     * It does not mess with the signature as this is only a normal transaction
     * and not a SignedTransaction. To be valid a transaction should have
     * <p>
     * A positive amount of money being exchanged
     * <p>
     * a positive fee
     * <p>
     * the sender and the receiver should not be null
     *
     * @throws InvalidTransactionException this exception is thrown when a transaction is not valid
     */
    public void verify() throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Invalid amount");
        }
        if (fee < 0) {
            throw new InvalidTransactionException("Invalid fee");
        }
        if (from == null) {
            throw new InvalidTransactionException("Invalid sender");
        }
        if (to == null) {
            throw new InvalidTransactionException("Invalid receiver");
        }
    }
}
