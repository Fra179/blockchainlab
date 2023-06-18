package blockchainlab.blockchain.block;

import blockchainlab.blockchain.blockchain.InvalidBlockException;

public class Block {
    // TODO: possible problem with this approach in multithreading

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

    void verify() throws InvalidBlockException {
        for (SignedTransaction t : transactions.getTransactions()) {
            try {
                t.verify();
            } catch (InvalidTransactionException e) {
                throw new InvalidBlockException(e);
            }
        }
    }
}
