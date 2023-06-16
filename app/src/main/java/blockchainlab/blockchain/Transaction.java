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

    public Transaction(double amount, String from, String to) {
        this.amount = amount;
        this.from = new Wallet(from);
        this.to = new Wallet(to);
    }

    @Override
    public String toString() {
        return from + "-" + amount + "->" + to;
    }
}
