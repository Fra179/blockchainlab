package blockchainlab.blockchain.block;

public class InvalidTransactionsPacketException extends Exception {
    public InvalidTransactionsPacketException(String message) {
        super(message);
    }

    public InvalidTransactionsPacketException(Exception message) {
        super(message);
    }

    public static final InvalidTransactionsPacketException PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION = new InvalidTransactionsPacketException(
            "Packet size doesn't match standard size.");
    public static final InvalidTransactionsPacketException PACKET_CONTAINS_NULL_TRANSACTION_EXCEPTION = new InvalidTransactionsPacketException(
            "Packet contains null transaction");
}
