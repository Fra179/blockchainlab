package blockchainlab.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Logger;

import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.block.Transaction;
import blockchainlab.blockchain.blockchain.Blockchain;
import blockchainlab.blockchain.blockchain.InvalidBlockException;
import blockchainlab.blockchain.communicator.Client;
import blockchainlab.blockchain.communicator.Communicator;
import blockchainlab.blockchain.wallet.ColdWallet;
import blockchainlab.blockchain.wallet.HotWallet;

public class ClientNode implements Runnable {
    Client cl;
    HotWallet w;
    ColdWallet[] contacts;
    Blockchain bc;
    Random rand;
    Logger logger;
    private static int CLIENT_N = 0;



    void handle(HashedBlock b) {
        try {
            b.verify();
            this.bc.addBlock(b);
        } catch (InvalidBlockException e) {
            
        }
    }

    void handle(Object o) {
        if (o instanceof SignedTransaction) {
            Transaction t = (Transaction) o;
            handle(t);
        } else if (o instanceof HashedBlock) {
            HashedBlock b = (HashedBlock) o;
            handle(b);
        }
    }

    public ClientNode(Communicator c, HotWallet w, ColdWallet[] contacts) throws NoSuchAlgorithmException {
        this.w = w;
        cl = new Client(c);
        this.contacts = contacts;
        rand = new Random();
        this.logger = Logger.getLogger("MinerNode-" + CLIENT_N++);


    }

    public void sendMoney() {
        if (rand.nextInt(1000) == 690) {
            double amount = rand.nextDouble() * bc.getBalance(w.pubKey);
            double fee = Math.pow(rand.nextDouble(), 3) * amount;
            Transaction t = new Transaction(amount, w, contacts[rand.nextInt(contacts.length)], fee);
            SignedTransaction st;

            try {
                st = new SignedTransaction(t, w.signTransaction(t));
            }
            catch (Exception e) {
                logger.warning("Can't sign transaction: " + t);
                return;
            }

            cl.broadcast((Object) st);
            logger.info("New transaction sent:\n" + st);

        }
    }

    public void run() {

        int randomMin = 500;
        int randomMax = 1500;
        int randomTime = rand.nextInt(randomMin, randomMax + 1);

        
        try {
            Thread.sleep(randomTime);
        } catch (InterruptedException e) {
            
        }

        while (true) {
            Object o = cl.poll();
            handle(o);
            
            sendMoney();           
        }
    }
}