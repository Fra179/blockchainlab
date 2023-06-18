package blockchainlab.blockchain.block;

import java.security.PublicKey;

import blockchainlab.blockchain.wallet.ColdWallet;

public class Coinbase {
    public final double amount;
    public final ColdWallet wallet;

    public Coinbase(ColdWallet wallet) {
        this.amount = 6;
        this.wallet = wallet;
    }

    public Coinbase(PublicKey address) {
        this.amount = 6;
        this.wallet = new ColdWallet(address);
    }

    @Override
    public String toString() {
        return amount + "->" + wallet.toString();
    }
}