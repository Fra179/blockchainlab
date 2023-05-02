package blockchainlab.blockchain;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

public class HashableBlock extends Block {
    public final int nonce;

    public HashableBlock(Block block, int nonce) {
        super(block.data, block.id);
        this.nonce = nonce;
    }

    public HashableBlock(String data, int id, int nonce) {
        super(data, id);
        this.nonce = nonce;
    }

    public final String asString() {
        return "#" + this.id + "#@" + this.nonce + "@" + this.data;
    }

    public final String hash() {
        return Hashing.sha256()
                .hashString(this.asString(), StandardCharsets.UTF_8)
                .toString();
    }

    public static void main(String[] args) {
        HashableBlock hashableBlock = new HashableBlock(new Block("Ciao mamma!"), 70);
        System.out.println(hashableBlock.asString());
        System.out.println(hashableBlock.hash());
    }
}
