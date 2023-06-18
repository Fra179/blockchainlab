package blockchainlab.blockchain.blockchain;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Optional;

import blockchainlab.blockchain.block.Hash;
import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.wallet.ColdWallet;

public class Blockchain {
    public BlockchainNode tail = null;
    HashMap<PublicKey, Double> balances = new HashMap<>();

    /**
     * We add a block to the blockchain, if the hash of the previous block is not the same as the prevHash field
     * of the current block we consider the block as old, not valid
     *
     * @param block the hashed block that needs to be added
     * @throws InvalidBlockException This exception is thrown if the block is too old
     */
    public void addBlock(HashedBlock block) throws InvalidBlockException {
        if (tail != null && block.prevBlockHash != tail.block.hash) {
            throw new InvalidBlockException("Previous hash do not match");
        }

        BlockchainNode node = new BlockchainNode(block, tail);
        tail = node;

        // we also keep track of the balances of every wallet in an efficient way using an hashmap, so that in every
        // moment we know what the balance of a wallet is
        balances.put(block.coinbase.wallet.pubKey, balances.getOrDefault(block.coinbase.wallet.pubKey, 0.0) + block.coinbase.amount);

        for (SignedTransaction t : block.transactions.getTransactions()) {
            // detract the fee and the amount from the wallet of the sender
            balances.put(t.from.pubKey, balances.getOrDefault(t.from.pubKey, 0.0) - t.amount - t.fee);

            // add the amount of the transaction to the wallet of the receiver
            balances.put(t.to.pubKey, balances.getOrDefault(t.to.pubKey, 0.0) + t.amount);

            // the miner takes the fees
            balances.put(block.coinbase.wallet.pubKey, balances.getOrDefault(block.coinbase.wallet.pubKey, 0.0) + t.fee);
        }
    }

    /**
     * This function returns the
     *
     * @return an optional with the Hash of the latest block, null if there is no previous block
     */
    public Optional<Hash> getLatestBlock() {
        if (tail == null) {
            return Optional.empty();
        }
        return Optional.of(tail.block.hash);
    }

    /**
     * Get the id of the last block
     *
     * @return the id of the last block, -1 if there is no last block
     */
    public int getLastID() {
        if (tail == null) {
            return -1;
        }
        return tail.block.id;
    }

    /**
     * Access the hashmap and returns in a fast and efficient way the balance of a wallet
     *
     * @param wallet the wallet that we want to know the balance of
     * @return the balance of the wallet
     */
    public Double getBalance(ColdWallet wallet) {
        return balances.getOrDefault(wallet.pubKey, 0.0);
    }
}
