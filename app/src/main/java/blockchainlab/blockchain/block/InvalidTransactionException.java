package blockchainlab.blockchain.block;

public class InvalidTransactionException extends Exception {
    InvalidTransactionException(String message) {
        super(message);
    }

    public InvalidTransactionException(Exception e) {
        super(e);
    };
}
