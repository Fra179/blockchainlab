package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.Block;
import blockchainlab.blockchain.Coinbase;
import blockchainlab.blockchain.HashableBlock;

import static org.junit.jupiter.api.Assertions.*;

class BlocksTests {
    @Test
    void stringRepresentationOfHashableBlockRepresentsAllData() {
        Block block = new Block(new Coinbase(10.0, "@satoshi"), "Ciao Mamma!");
        HashableBlock hashableBlock = new HashableBlock(block, 70);
        assertEquals(hashableBlock.toString(), "[#]1[#@]70[@]10.0->@satoshi[@@]Ciao Mamma!");
        assertEquals(hashableBlock.hash(), "8d488c4e641827c3f7ce1ff93d5f1cc64f28df96ed6a7a7fe34b48bae5255c6d");
    }
}
