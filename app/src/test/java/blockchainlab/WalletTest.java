package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.Wallet;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {
    @Test
    void distinctWalletObjectsWithSameAddressAreTheSameWallet() {
        Wallet a1 = new Wallet("a");
        Wallet a2 = new Wallet("a");
        assertTrue(a1.equals(a2));
    }
}
