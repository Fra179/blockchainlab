package blockchainlab.blockchain;

public final class Miner {
    private Miner() {
    }

    /**
     * Mines a Block returning an HashedBlock
     * whose hash starts with as many zeros as the specified difficulty
     * 
     * @param block      block to mine
     * @param difficulty number of zeros the hash of the result should start with
     * @return hashed block with hash starting with `difficulty` zeros
     */
    public static final HashedBlock mine(Block block, int difficulty) {
        int nonce = 0;
        HashableBlock hashableBlock = new HashableBlock(block, nonce++);

        // TODO: multithreaded

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
