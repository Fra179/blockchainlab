package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.wallet.ColdWallet;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void distinctWalletObjectsWithSameAddressAreTheSameWallet() {
        ColdWallet a1 = new ColdWallet("a");
        ColdWallet a2 = new ColdWallet("a");
        assertTrue(a1.equals(a2));
    }
}
