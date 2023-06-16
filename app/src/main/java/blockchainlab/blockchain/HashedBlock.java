package blockchainlab.blockchain;

public final class HashedBlock extends HashableBlock {
    public final String hash;

    public HashedBlock(HashableBlock block) {
        super(block.coinbase, block.data, block.id, block.nonce);
        this.hash = block.hash();
    }
}
