package blockchainlab;

import org.junit.jupiter.api.Test;

import blockchainlab.blockchain.InvalidTransactionsPacketException;
import blockchainlab.blockchain.Transaction;
import blockchainlab.blockchain.Transactions;
import blockchainlab.blockchain.Wallet;

import static blockchainlab.blockchain.InvalidTransactionsPacketException.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionsTests {
    private static final Transaction t = new Transaction(10.0, new Wallet("a"), new Wallet("b"));

    @Test
    void nullTransactionsRaiseException() {
        InvalidTransactionsPacketException e = assertThrows(InvalidTransactionsPacketException.class, () -> {
            new Transactions(new Transaction[] { t, t, t, null, t });
        });

        assertEquals(e, PACKET_CONTAINS_NULL_TRANSACTION_EXCEPTION);

        assertDoesNotThrow(() -> {
            new Transactions(new Transaction[] { t, t, t, t, t });
        });
    }

    @Test
    void invalidTransactionsPacketSizeRaisesException() {
        InvalidTransactionsPacketException e1 = assertThrows(InvalidTransactionsPacketException.class, () -> {
            new Transactions(new Transaction[] {});
        });

        InvalidTransactionsPacketException e2 = assertThrows(InvalidTransactionsPacketException.class, () -> {
            new Transactions(new Transaction[] { t, t, t, t, t, t });
        });

        assertEquals(e1, PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION);
        assertEquals(e2, PACKET_SIZE_DOESNT_MATCH_STANDARD_SIZE_EXCEPTION);

        assertDoesNotThrow(() -> {
            new Transactions(new Transaction[] { t, t, t, t, t });
        });
    }
}
