package blockchainlab.blockchain.block;

import static blockchainlab.blockchain.block.InvalidTransactionsPacketException.*;

/**
 * A simple class to encapsulate all the transactions
 */
public class Transactions {

    // we don't want people to mess with our transactions, so we make them private
    private final SignedTransaction[] transactions;

    /**
     * The constructor takes an array of transactions and encapsulate them
     *
     * @param transactions the list of transactions to be encapsulated
     * @throws InvalidTransactionsPacketException if the encapsulated transactions are null, we raise an exception
     */
    public Transactions(SignedTransaction[] transactions) throws InvalidTransactionsPacketException {
        this.transactions = transactions;

        for (SignedTransaction t : transactions) {
            if (t == null) {
                throw PACKET_CONTAINS_NULL_TRANSACTION_EXCEPTION;
            }
        }
    }

    /**
     * A getter for our transactions, we want to look at them
     * @return the array of transactions that are encapsulated
     */
    public SignedTransaction[] getTransactions() {
        return this.transactions;
    }

    /**
     * A list of transaction is valid only if all the transactions inside it are valid
     *
     * @throws InvalidTransactionException if even a single transaction is not valid we raise an exception
     */
    public void verify() throws InvalidTransactionException {
        for (SignedTransaction t : transactions) {
            t.verify();
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (SignedTransaction t : transactions) {
            result += "[[T]]" + t + "\n";
        }
        return result;
    }
}





/*
TODO: remove the packet if we have an error
// Why? So we're sure about transactions packet immutability
public class Transactions {
    public final SignedTransaction first;
    public final SignedTransaction second;
    public final SignedTransaction third;
    public final SignedTransaction fourth;
    public final SignedTransaction fifth;

    public Transactions(SignedTransaction[] transactions) throws InvalidTransactionsPacketException {
        if (transactions.length != 5) {
            throw PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION;
        } else if (transactions[0] == null || transactions[1] == null || transactions[2] == null
                || transactions[3] == null || transactions[4] == null) {
            throw PACKET_CONTAINS_NULL_TRANSACTION_EXCEPTION;
        } else {
            this.first = transactions[0];
            this.second = transactions[1];
            this.third = transactions[2];
            this.fourth = transactions[3];
            this.fifth = transactions[4];
        }
    }

    public SignedTransaction[] getTransactions() {
        return new SignedTransaction[] { this.first, this.second, this.third, this.fourth, this.fifth };
    }

    public void verify() throws InvalidTransactionException {
        first.verify();
        second.verify();
        third.verify();
        fourth.verify();
        fifth.verify();
    }

    @Override
    public String toString() {
        return "[[T0]]" + first + "[[T1]]" + second + "[[T2]]" + third + "[[T4]]" + fourth + "[[T5]]" + fifth;
    }
}
*/