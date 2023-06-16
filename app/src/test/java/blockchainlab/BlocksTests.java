package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.Block;
import blockchainlab.blockchain.Coinbase;
import blockchainlab.blockchain.Hash;
import blockchainlab.blockchain.HashableBlock;

import static org.junit.jupiter.api.Assertions.*;

class BlocksTests {
    @Test
    void stringRepresentationOfHashableBlockContainsAllData() {
        Coinbase coinbase = new Coinbase(10.0, "@satoshi");
        Hash hash = new Hash("8d488c4e641827c3f7ce1ff93d5f1cc64f28df96ed6a7a7fe34b48bae5255c6d");
        Block block = new Block(coinbase, "Ciao Mamma!", hash);
        HashableBlock hashableBlock = new HashableBlock(block, 70);
        assertEquals(
                "[#]1[?]70[@]10.0->@satoshi[@@]Ciao Mamma![#<-#]8d488c4e641827c3f7ce1ff93d5f1cc64f28df96ed6a7a7fe34b48bae5255c6d",
                hashableBlock.toString());

        System.out.println(hashableBlock.hash());
        assertEquals("969e19c9b7d1177cd10f21f0bc2cd7cdacf7691d9e5201aec0946aaaa77c40e1",
                hashableBlock.hash().toString());
    }
}
