package blockchainlab.blockchain;

import static blockchainlab.blockchain.InvalidTransactionsPacketException.*;

public class Transactions {
    public final Transaction first;
    public final Transaction second;
    public final Transaction third;
    public final Transaction fourth;
    public final Transaction fifth;

    public Transactions(Transaction[] transactions) throws InvalidTransactionsPacketException {
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
}
