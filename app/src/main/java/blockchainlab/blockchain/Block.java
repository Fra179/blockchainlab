package blockchainlab.blockchain;

public class Block {
    public static int nextId = 1;

    public final int id;
    public final String data;

    public final Coinbase coinbase;

    public Block(Coinbase coinbase, String data) {
        this.coinbase = coinbase;
        this.id = Block.nextId++;
        this.data = data == null ? "" : data;
    }

    protected Block(Coinbase coinbase, String data, int id) {
        this.coinbase = coinbase;
        this.id = id;
        this.data = data;
    }
}
