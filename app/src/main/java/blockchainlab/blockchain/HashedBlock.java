package blockchainlab.blockchain;

public final class HashedBlock extends HashableBlock {
    public final String hash;

    public HashedBlock(HashableBlock block) {
        super(block.data, block.id, block.nonce);
        this.hash = block.hash();
    }
}
