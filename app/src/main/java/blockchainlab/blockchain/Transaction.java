package blockchainlab.blockchain;

public class Transaction {
    public final double amount;
    public final Wallet from;
    public final Wallet to;

    public Transaction(double amount, Wallet from, Wallet to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }
}
