package blockchainlab.blockchain.miner;

// import java.util.concurrent.ThreadLocalRandom;
import static blockchainlab.constants.Constants.CPU_CORES;
import blockchainlab.blockchain.block.Block;
import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.constants.Constants;
import blockchainlab.blockchain.block.HashableBlock;

import java.io.Console;
import java.util.concurrent.ThreadLocalRandom;

public class MultithreadedMiner{
    // Arbitrary strategy for deciding how many threads to run (at least 1)
    final int numThreads = Math.max(1, CPU_CORES / 2);

    /**
     * This class represents the multithreaded miner.
     */
    public class Mining implements Runnable {

        private final int difficulty;
        private final Block block;
        private HashedBlock hashedBlock;
        private volatile boolean blockFound;

        public Mining(Block block) {
            this.block = block;
            this.difficulty = Constants.DIFFICULTY;
        }

        /**
         * The function generates a random number which it uses to find a hash with the desired number of zeroes. It keeps trying until it gets a correct hash
         */
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

        /**
         * Getter method
         * @return the mined block is returned
         */
        public HashedBlock getHashedBlock() {
            return hashedBlock;
        }

        /**
         * Getter method for blockFound
         * @return boolean blockFound is returned
         */
        public boolean isBlockFound() {
            return blockFound;
        }
    }
    //minings: array of runnable objects
    private Mining[] minings = new Mining[numThreads];
    //miners: array of threads
    private Thread[] miners = new Thread[numThreads];
    //interrupted: variable that shows whether a thread is actively mining or not
    private boolean interrupted = true;

    /**
     * startMining: the method that start every thread. A loop assign the task from the mining arrays to every miner in miners and start the miner.
     * It sets interrupted to true because now every thread is mining.
     * @param block the block to be mined
     * @param difficulty the desired number of zeroes the hash of the mined block must start with
     */
    public void startMining(Block block, int difficulty) {

        for (int i = 0; i < numThreads; i++) {
            minings[i] = new Mining(block);
            miners[i] = new Thread(minings[i]);
            miners[i].start();
        }
        
        interrupted = false;
    }
    /**
     * Checks if a block has been found or not.
     * @throws BlockNotMinedException if the block as not been mined yet or it has been mined by another minerNode
     */
    public HashedBlock checkResult() throws BlockNotMinedException {
        if (interrupted) {
            throw new BlockNotMinedException("Mining interrupted");
        }
        //if threads are not still alive they are stopped and the hashedBlock is returned
        for (int i = 0; i < numThreads; i++) {
            if (!miners[i].isAlive()) {
                stopMining();
                return minings[i].getHashedBlock();
            }
        }
        throw new BlockNotMinedException("Block not mined yet");
    }

    /**
     * Stops miners
     */
    public void stopMining() {
        //if interrupted is true, do nothing
        if (interrupted) {
            return;
        }
        //interrupt all miners and set interrupted to true
        for (int i = 0; i < numThreads; i++) {
            this.miners[i].interrupt();
        }
        interrupted = true;
    }

    /**
     * checks if a thread is mining
     * @return True if the threas is mining, false if it is not.
     */
    public boolean isBusy() {
        return !interrupted;
    }
}

