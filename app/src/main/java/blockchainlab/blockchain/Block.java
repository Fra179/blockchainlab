package blockchainlab.blockchain;

public class Block {
    public static int nextId = 1;

    public final int id;
    public final String data;

    public Block(String data) {
        this.id = Block.nextId++;
        this.data = data == null ? "" : data;
    }

    protected Block(String data, int id) {
        this.id = id;
        this.data = data;
    }
}
