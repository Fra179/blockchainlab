package blockchainlab.blockchain.miner;

import java.util.PriorityQueue;

import blockchainlab.blockchain.block.InvalidTransactionsPacketException;
import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.block.Transaction;
import blockchainlab.blockchain.block.Transactions;

public class Mempool {
    public PriorityQueue<SignedTransaction> transactions = new PriorityQueue<>((SignedTransaction t1, SignedTransaction t2) -> Double.compare(t1.fee, t2.fee));
    
    public void push(SignedTransaction t) {
        this.transactions.add(t);
    }

    public Transactions getAllTransactions() throws InvalidTransactionsPacketException {
        int size = this.transactions.size();
        SignedTransaction[] t = new SignedTransaction[size];

        for (int i = 0; i < size; i++) {
            t[i] = this.transactions.poll();
        }

        for (SignedTransaction st : t) {
            this.transactions.add(st);
        }

        return new Transactions(t);
    }

    public Transactions getTransactions(int min) throws InvalidTransactionsPacketException {
        if (this.transactions.size() < min) {
            throw InvalidTransactionsPacketException.PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION;
        }

        return getAllTransactions();
    }

    public void removeTransactions(Transactions t) {
        for (SignedTransaction st : t.getTransactions()) {
            this.transactions.remove(st);
        }
    }

    public void removeTransaction(Transaction t) {
        this.transactions.remove(t);
    }
}
