package blockchainlab.blockchain.blockchain;

public class InvalidBlockException extends Exception {
    public InvalidBlockException(String message) {
        super(message);
    };

    public InvalidBlockException(Exception e) {
        super(e);
    };
}
