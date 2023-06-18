package blockchainlab.blockchain.blockchain;

import blockchainlab.blockchain.block.HashedBlock;

public class BlockchainNode {
    public final HashedBlock block;
    public final BlockchainNode previous;


    public BlockchainNode(HashedBlock block, BlockchainNode previous) {
        this.block = block;
        this.previous = previous;
    }
}
