package blockchainlab.blockchain;

public final class HashedBlock extends HashableBlock {
    public final Hash hash;

    public HashedBlock(HashableBlock block) {
        super(block.coinbase, block.data, block.id, block.nonce, block.prevBlockHash);
        this.hash = block.hash();
    }
}
