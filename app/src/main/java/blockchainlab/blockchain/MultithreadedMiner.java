package blockchainlab.blockchain;

// import java.util.concurrent.ThreadLocalRandom;
import static blockchainlab.constants.Constants.CPU_CORES;

public class MultithreadedMiner extends Thread {
    // Arbitrary strategy for deciding how many threads to run (at least 1)
    final int numThreads = Math.max(1, CPU_CORES / 2);

    @Override
    public void run() {
    }
}
