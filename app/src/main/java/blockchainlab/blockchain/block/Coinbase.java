package blockchainlab.blockchain.block;

import java.security.PublicKey;

import blockchainlab.blockchain.wallet.ColdWallet;

public class Coinbase {
    public final double amount;
    public final ColdWallet wallet;

    /**
     * This constructor returns a coinbase object
     * @param wallet the wallet where the coinbase money should be sent
     */
    public Coinbase(ColdWallet wallet) {
        // The reward amount is constant for every coinbase
        this.amount = 6;
        this.wallet = wallet;
    }

    /**
     * This constructor returns a coinbase object
     * @param address the public key of the wallet where the coinbase money should be sent
     */
    public Coinbase(PublicKey address) {
        this.amount = 6;
        this.wallet = new ColdWallet(address);
    }

    @Override
    public String toString() {
        return amount + "->" + wallet;
    }
}