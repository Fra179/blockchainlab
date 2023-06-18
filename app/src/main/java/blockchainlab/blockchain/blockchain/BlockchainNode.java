package blockchainlab.blockchain.blockchain;

import blockchainlab.blockchain.block.HashedBlock;

/**
 * A simple class that represent the node of our linked list, the data structure we choose to represent
 * the blockchain
 */
public class BlockchainNode {
    public final HashedBlock block;
    public final BlockchainNode previous;


    public BlockchainNode(HashedBlock block, BlockchainNode previous) {
        this.block = block;
        this.previous = previous;
    }
}
