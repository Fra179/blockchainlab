package blockchainlab.blockchain;

import java.util.concurrent.ThreadLocalRandom;

import blockchainlab.blockchain.block.Block;
import blockchainlab.blockchain.block.HashableBlock;
import blockchainlab.blockchain.block.HashedBlock;

public final class Miner {
    private int difficulty = 8; 

    public Miner(int difficulty) {
        this.difficulty = difficulty;
    }

    public Miner() {
    }

    /**
     * Mines a Block returning an HashedBlock
     * whose hash starts with as many zeros as the specified difficulty
     * 
     * @param block      block to mine
     * @param difficulty number of zeros the hash of the result should start with
     */
    public final HashedBlock mine(Block block) {
        // TODO: multithreaded
        HashableBlock hashableBlock;
        do {
            int nonce = ThreadLocalRandom.current().nextInt();
            hashableBlock = new HashableBlock(block, nonce);
        } while (!hashableBlock.hash().toString().startsWith("0".repeat(difficulty)));
        return new HashedBlock(hashableBlock);
    }
}
