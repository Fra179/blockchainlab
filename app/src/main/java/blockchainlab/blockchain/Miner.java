package blockchainlab.blockchain;

public final class Miner {
    private Miner() {
    }

    public static final HashedBlock mine(Block block, int difficulty) {
        int nonce = 0;
        HashableBlock hashableBlock = new HashableBlock(block, nonce++);

        while (!hashableBlock.hash().startsWith("0".repeat(difficulty))) {
            hashableBlock = new HashableBlock(block, nonce++);
        }
        return new HashedBlock(hashableBlock);
    }

    public static void main(String[] args) {
        Block block = new Block("Ciao Mamma!");
        HashedBlock hashedBlock = Miner.mine(block, 6);
        System.out.println(hashedBlock.asString());
        System.out.println(hashedBlock.hash());
    }
}
