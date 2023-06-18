package blockchainlab.blockchain.communicator;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Client {
    private String id;
    private Communicator c;

    public Client(Communicator c) {
        this.id = UUID.randomUUID().toString();
        c.register(id);
        this.c = c;
    }

    public void broadcast(Object message) {
        c.broadcast(id, message);
    }

    public Object poll() throws NoSuchElementException {
        return c.poll(id);
    }
}
