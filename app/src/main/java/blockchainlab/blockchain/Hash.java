package blockchainlab.blockchain;

public class Hash {
    public final String hash;

    public Hash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return this.hash;
    }
}
