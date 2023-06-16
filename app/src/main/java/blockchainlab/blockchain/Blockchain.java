package blockchainlab.blockchain;

public class Blockchain {
    public final HashedBlock block;
    public final HashedBlock next;

    public Blockchain(HashedBlock block, HashedBlock next) {
        this.block = block;
        this.next = next;
    }
}
