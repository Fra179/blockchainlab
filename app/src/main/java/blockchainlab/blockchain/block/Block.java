package blockchainlab.blockchain.block;

import blockchainlab.blockchain.blockchain.InvalidBlockException;

public class Block {

    public final int id;
    public final Transactions transactions;

    public final Coinbase coinbase;
    public final Hash prevBlockHash;

    public Block(Coinbase coinbase, Transactions transactions, int id, Hash prevBlockHash) {
        this.coinbase = coinbase;
        this.id = id;
        this.transactions = transactions;
        this.prevBlockHash = prevBlockHash;
    }

    /**
     * This function verifies the validity of a block.
     * If the block is not valid an exception is raised
     *
     * @throws InvalidBlockException
     */
    void verify() throws InvalidBlockException {
        try {
            transactions.verify();
        } catch (InvalidTransactionException e) {
            throw new InvalidBlockException(e);
        }
    }
}
