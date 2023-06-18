package blockchainlab.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
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


    public ClientNode(Communicator c, HotWallet w, ColdWallet[] contacts) throws NoSuchAlgorithmException {
        this.w = w;
        cl = new Client(c);
        this.contacts = contacts;
        rand = new Random();
        this.logger = Logger.getLogger("MinerNode-" + CLIENT_N++);
        this.bc = new Blockchain();
        logger.info("Hi, I'm client node and I'm " + w);

    }


    void handle(HashedBlock b) {
        try {
            b.verify();
            this.bc.addBlock(b);
        } catch (InvalidBlockException e) {
            logger.warning("Invalid block: " + b);
        }
    }

    void handle(Object o) {
        if (o instanceof HashedBlock) {
            handle((HashedBlock) o);
        }
    }

    public void sendMoney() {
        if (rand.nextInt(100000) == 690) {
            double amount = rand.nextDouble() * bc.getBalance(w);

            if (amount == 0) {
                return;
            }

            double fee = Math.pow(rand.nextDouble(), 3) * amount;
            Transaction t = new Transaction(amount, w, contacts[rand.nextInt(contacts.length)], fee);
            SignedTransaction st;

            try {
                st = new SignedTransaction(t, w.signTransaction(t));
            } catch (Exception e) {
                logger.warning("Can't sign transaction: " + t);
                return;
            }

            cl.broadcast(st);
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
            try {
                Object o = cl.poll();
                handle(o);
            } catch (NoSuchElementException e) {

            }
            sendMoney();
        }
    }
}