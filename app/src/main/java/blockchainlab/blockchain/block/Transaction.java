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
