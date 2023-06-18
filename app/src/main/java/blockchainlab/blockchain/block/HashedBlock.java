package blockchainlab.blockchain.block;
import blockchainlab.constants.Constants;
import blockchainlab.blockchain.blockchain.InvalidBlockException;

// TODO: ask Daniel to comment on the choice of having HashedBlock, HashableBlock and Block
public final class HashedBlock extends HashableBlock {
    public final Hash hash;

    public HashedBlock(HashableBlock block) {
        super(block.coinbase, block.transactions, block.id, block.nonce, block.prevBlockHash);
        this.hash = block.hash();
    }

    public void verify() throws InvalidBlockException {
        super.verify();
        if (!this.hash.toString().startsWith("0".repeat(Constants.DIFFICULTY))) {
            throw new InvalidBlockException("Hashes do not match");
        }
    }
}

