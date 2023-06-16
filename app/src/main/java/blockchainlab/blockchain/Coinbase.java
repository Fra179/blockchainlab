package blockchainlab.blockchain;

public class Coinbase {
    public final double amount;
    public final Wallet wallet;

    public Coinbase(Double amount, Wallet wallet) {
        this.amount = amount;
        this.wallet = wallet;
    }

    public Coinbase(Double amount, String address) {
        this.amount = amount;
        this.wallet = new Wallet(address);
    }

    @Override
    public String toString() {
        return amount + "->" + wallet.toString();
    }
}