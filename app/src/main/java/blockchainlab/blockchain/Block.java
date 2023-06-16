package blockchainlab.blockchain;

public class Block {
    public static int nextId = 1;

    public final int id;
    public final String data;

    public final Coinbase coinbase;
    public final Hash prevBlockHash;

    public Block(Coinbase coinbase, String data, Hash prevBlockHash) {
        this.coinbase = coinbase;
        this.id = Block.nextId++;
        this.data = data == null ? "" : data;
        this.prevBlockHash = prevBlockHash;
    }

    protected Block(Coinbase coinbase, String data, int id, Hash prevBlocHash) {
        this.coinbase = coinbase;
        this.id = id;
        this.data = data;
        this.prevBlockHash = prevBlocHash;
    }
}
