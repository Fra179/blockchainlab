package blockchainlab.blockchain.block;

import static blockchainlab.blockchain.block.InvalidTransactionsPacketException.*;

public class Transactions {
    private SignedTransaction[] transactions;

    public Transactions(SignedTransaction[] transactions) throws InvalidTransactionsPacketException {
        this.transactions = transactions;

        for (SignedTransaction t : transactions) {
            if (t == null) {
                throw PACKET_CONTAINS_NULL_TRANSACTION_EXCEPTION;
            }
        }
    }

    public SignedTransaction[] getTransactions() {
        return this.transactions;
    }

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