package blockchainlab.blockchain.communicator;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Communicator {

    HashMap<String, Queue<Object>> backbone = new HashMap<>();

    public Communicator() {
    }

    protected void register(String uuid) {
        backbone.put(uuid, new LinkedList<>());
    }

    protected void broadcast(String uuid, Object message) {
        backbone.keySet().forEach((key) -> {
            // if (key != uuid) { 
            // TODO: check this later
            backbone.get(key).add(message);
            // }
        });
    }

    protected Object poll(String uuid) throws NoSuchElementException {
        return backbone.get(uuid).remove();
    }
}

