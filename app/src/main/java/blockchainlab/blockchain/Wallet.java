package blockchainlab.blockchain;

public class Wallet {
    // Strings objects are immutable, so this is safe enough
    public final String address;

    public Wallet(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return this.address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Wallet)) {
            return false;
        } else {
            Wallet other = (Wallet) o;
            return this.address.equals(other.address);
        }
    }
}
