package blockchainlab.blockchain.miner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import blockchainlab.blockchain.block.InvalidTransactionsPacketException;
import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.block.Transaction;
import blockchainlab.blockchain.block.Transactions;

public class Mempool {
    // to increase earnings, we want a priority queue that returns always the transactions with the greatest fees
    public PriorityQueue<SignedTransaction> transactions = new PriorityQueue<>(Comparator.comparingDouble((SignedTransaction t) -> t.fee));

    /**
     * put a transaction in the mempool
     * @param t a valid signed transaction to be put in the mempool
     */
    public void push(SignedTransaction t) {
        this.transactions.add(t);
    }

    /**
     * Returns <b>ALL</b> the transactions in the mempool
     * @return a Transactions class containing all the transactions in the queue
     * @throws InvalidTransactionsPacketException the exception is raised if there are invalid transactions in the queue
     */
    public Transactions getAllTransactions() throws InvalidTransactionsPacketException {
        int size = this.transactions.size();
        SignedTransaction[] t = new SignedTransaction[size];

        for (int i = 0; i < size; i++) {
            t[i] = this.transactions.poll();
        }

        this.transactions.addAll(Arrays.asList(t));

        return new Transactions(t);
    }

    /**
     * Get at least min transactions and at most max
     * @param min the minimum amount of transactions to be returned
     * @return a packet with all the transactions
     * @throws InvalidTransactionsPacketException the exception is raised if there are not enough transactions
     */
    public Transactions getTransactions(int min) throws InvalidTransactionsPacketException {
        // TODO: implement taking at most max transactions
        if (this.transactions.size() < min) {
            throw InvalidTransactionsPacketException.PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION;
        }

        return getAllTransactions();
    }

    /**
     * Remove a list of transactions contained in the Transactions class from the mempool
     * @param t the Transactions to remove
     */
    public void removeTransactions(Transactions t) {
        for (SignedTransaction st : t.getTransactions()) {
            this.transactions.remove(st);
        }
    }

    /**
     * Remove a single transaction from the mempool
     * @param t the transaction to remove
     */
    public void removeTransaction(Transaction t) {
        this.transactions.remove(t);
    }
}
