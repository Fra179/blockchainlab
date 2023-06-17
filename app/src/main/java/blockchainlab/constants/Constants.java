package blockchainlab.constants;

public final class Constants {
    public static final String VERSION = "0.0.7 dev";

    public static final String BTC = "₿";
    public static final String BLOCKCHAIN_LAB_STRING = BTC + "lockchain Lab";

    public static final String VERBOSE_VERSION = BLOCKCHAIN_LAB_STRING + "(v" + VERSION + ")";

    public static final int CPU_CORES = Runtime.getRuntime().availableProcessors();
}
