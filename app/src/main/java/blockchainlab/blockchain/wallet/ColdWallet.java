package blockchainlab.blockchain.wallet;

import java.security.PublicKey;
import java.util.Arrays;

/**
 * A ColdWallet is a wallet that can only receive and not send money
 */
public class ColdWallet {
    // Strings objects are immutable, so this is safe enough
    public final PublicKey pubKey;

    public ColdWallet(PublicKey address) {
        this.pubKey = address;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (byte b : this.pubKey.getEncoded()) {
            res.append(String.format("%02X", b));
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ColdWallet)) {
            return false;
        } else {
            ColdWallet other = (ColdWallet) o;
            return this.pubKey.equals(other.pubKey);
        }
    }
}
