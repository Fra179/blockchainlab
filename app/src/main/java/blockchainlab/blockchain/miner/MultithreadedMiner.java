package blockchainlab.blockchain.miner;

// import java.util.concurrent.ThreadLocalRandom;
import static blockchainlab.constants.Constants.CPU_CORES;
import blockchainlab.blockchain.block.Block;
import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.constants.Constants;
import blockchainlab.blockchain.block.HashableBlock;
import java.util.concurrent.ThreadLocalRandom;

public class MultithreadedMiner{
    // Arbitrary strategy for deciding how many threads to run (at least 1)
    final int numThreads = Math.max(1, CPU_CORES / 2);

    public class Mining implements Runnable {

        private final int difficulty;
        private final Block block;
        private HashedBlock hashedBlock;
        private volatile boolean blockFound;

        public Mining(Block block) {
            this.block = block;
            this.difficulty = Constants.DIFFICULTY;
        }

        public void run() {
            HashableBlock pendingBlock;
            while (!blockFound) {
                int nonce = ThreadLocalRandom.current().nextInt();
                pendingBlock = new HashableBlock(block, nonce);
                if (pendingBlock.hash().toString().startsWith("0".repeat(difficulty))) {
                    blockFound = true;
                    hashedBlock = new HashedBlock(pendingBlock);
                    break;
                }
            }
        }

        public HashedBlock getHashedBlock() {
            return hashedBlock;
        }

        public boolean isBlockFound() {
            return blockFound;
        }
    }

    private Mining[] minings = new Mining[numThreads];
    private Thread[] miners = new Thread[numThreads];
    private boolean interrupted = false;

    public void startMining(Block block, int difficulty) {

        for (int i = 0; i < numThreads; i++) {
            minings[i] = new Mining(block);
            miners[i] = new Thread(minings[i]);
            miners[i].start();
        }
        
        interrupted = false;
    }

    public HashedBlock checkResult() throws BlockNotMinedException {
        if (interrupted) {
            throw new BlockNotMinedException("Mining interrupted");
        }
        
        for (int i = 0; i < numThreads; i++) {
            if (!miners[i].isAlive()) {
                stopMining();
                return minings[i].getHashedBlock();
            }
        }

        throw new BlockNotMinedException("Block not mined yet");
    }

    public void stopMining() {
        if (interrupted) {
            return;
        }

        for (int i = 0; i < numThreads; i++) {
            this.miners[i].interrupt();
        }
        interrupted = true;
    }

    public boolean isBusy() {
        return !interrupted;
    }
}

