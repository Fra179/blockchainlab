package blockchainlab.blockchain.miner;

public class NotEnoughTransactionsException extends Exception {
    NotEnoughTransactionsException(String message) {
        super(message);
    }
    NotEnoughTransactionsException(Exception message) {
        super(message);
    }
}