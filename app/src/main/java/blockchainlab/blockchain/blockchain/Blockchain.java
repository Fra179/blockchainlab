package blockchainlab.blockchain.blockchain;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Optional;

import blockchainlab.blockchain.block.Hash;
import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.blockchain.block.SignedTransaction;

public class Blockchain {
    public BlockchainNode tail = null;
    HashMap<PublicKey, Double> balances = new HashMap<>();

    public void addBlock(HashedBlock block) throws InvalidBlockException {
        if (tail != null && block.prevBlockHash != tail.block.hash) {
            throw new InvalidBlockException("Previous hash do not match");
        }

        BlockchainNode node = new BlockchainNode(block, tail);
        tail = node;

        balances.put(block.coinbase.wallet.pubKey, balances.getOrDefault(block.coinbase.wallet.pubKey, 0.0) + block.coinbase.amount);

        for (SignedTransaction t : block.transactions.getTransactions()) {
            balances.put(t.from.pubKey, balances.getOrDefault(t.from.pubKey, 0.0) - t.amount - t.fee);
            balances.put(t.to.pubKey, balances.getOrDefault(t.to.pubKey, 0.0) + t.amount);
            balances.put(block.coinbase.wallet.pubKey, balances.getOrDefault(block.coinbase.wallet.pubKey, 0.0) + t.fee);
        }
    }

    public Optional<Hash> getLatestBlock() {
        if (tail == null) {
            return Optional.empty();
        }
        return Optional.of(tail.block.hash);
    }

    public int getLastID() {
        if (tail == null) {
            return -1;
        }
        return tail.block.id;
    }

    public Double getBalance(PublicKey address) {
        return balances.getOrDefault(address, 0.0);
    }
}
