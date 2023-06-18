package blockchainlab.blockchain.wallet;
import java.security.PublicKey;
public class ColdWallet {
    // Strings objects are immutable, so this is safe enough
    public final PublicKey pubKey;

    public ColdWallet(PublicKey address) {
        this.pubKey = address;
    }

    @Override
    public String toString() {
        return this.pubKey.toString();
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
