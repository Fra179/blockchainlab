package blockchainlab.blockchain.miner;

//Exception thrown either when a block has not yet been mined or when another miner has found the block and
//the thread has been interrupted

public class BlockNotMinedException extends Exception {
    BlockNotMinedException(String message) {
        super(message);
    };
}
