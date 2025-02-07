package blockchainlab.blockchain.block;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

// TODO: ask Daniel to comment
public class HashableBlock extends Block {
    public final int nonce;

    public HashableBlock(Block block, int nonce) {
        super(block.coinbase, block.transactions, block.id, block.prevBlockHash);
        this.nonce = nonce;
    }

    public HashableBlock(Coinbase coinbase, Transactions transactions, int id, int nonce, Hash prevBlockHash) {
        super(coinbase, transactions, id, prevBlockHash);
        this.nonce = nonce;
    }

    @Override
    public final String toString() {
        String res = "";
        res += "Block id: " + this.id;
        res += "\nNonce: " + this.nonce;
        res += "\nCoinbase: " + this.coinbase;
        res += "\nTransactions:\n" + this.transactions + "\n\n";
        res += "Prev Hash: " + this.prevBlockHash;
        //return "[#]" + this.id + "[?]" + this.nonce + "[@]" + this.coinbase.toString() + "[@@]" + this.transactions
        //        + "[#<-#]"
        //        + this.prevBlockHash;
        return res;
    }

    public final Hash hash() {
        return new Hash(Hashing.sha256()
                .hashString(this.toString(), StandardCharsets.UTF_8)
                .toString());
    }
}
