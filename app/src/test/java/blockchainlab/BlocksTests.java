package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.Block;
import blockchainlab.blockchain.Coinbase;
import blockchainlab.blockchain.Hash;
import blockchainlab.blockchain.HashableBlock;
import blockchainlab.blockchain.InvalidTransactionsPacketException;
import blockchainlab.blockchain.Transaction;
import blockchainlab.blockchain.Transactions;

import static org.junit.jupiter.api.Assertions.*;

class BlocksTests {
    @Test
    void stringRepresentationOfHashableBlockContainsAllData() {
        Coinbase coinbase = new Coinbase(10.0, "@satoshi");
        Hash prevBlockHash = new Hash("8d488c4e641827c3f7ce1ff93d5f1cc64f28df96ed6a7a7fe34b48bae5255c6d");

        try {
            Transaction t1 = new Transaction(10.0, "bob", "alice");
            Transaction t2 = new Transaction(8.2, "bob", "alice");
            Transaction t3 = new Transaction(10.0, "alice", "bob");
            Transaction t4 = new Transaction(99.95, "satoshi", "alice");
            Transaction t5 = new Transaction(199.0, "satoshi", "bob");

            Transactions transactions = new Transactions(new Transaction[] { t1, t2, t3, t4, t5 });
            Block block = new Block(coinbase, transactions, prevBlockHash);
            HashableBlock hashableBlock = new HashableBlock(block, 70);
            assertEquals(
                    "[#]1[?]70[@]10.0->@satoshi[@@][[T0]]bob-10.0->alice[[T1]]bob-8.2->alice[[T2]]alice-10.0->bob[[T4]]satoshi-99.95->alice[[T5]]satoshi-199.0->bob[#<-#]8d488c4e641827c3f7ce1ff93d5f1cc64f28df96ed6a7a7fe34b48bae5255c6d",
                    hashableBlock.toString());

            System.out.println(hashableBlock.hash());
            assertEquals("28097241a795cbc0e23e853d3cc585f976eb2fb2bd5a39ddfb9a5cada8a8fc55",
                    hashableBlock.hash().toString());
        } catch (InvalidTransactionsPacketException e) {
            assertTrue(false);
        }

    }
}
