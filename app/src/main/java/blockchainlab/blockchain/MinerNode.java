package blockchainlab.blockchain;
import java.security.NoSuchAlgorithmException;
import blockchainlab.blockchain.block.Block;
import blockchainlab.blockchain.block.Coinbase;
import blockchainlab.blockchain.block.Hash;
import blockchainlab.blockchain.block.HashedBlock;
import blockchainlab.blockchain.block.InvalidTransactionException;
import blockchainlab.blockchain.block.InvalidTransactionsPacketException;
import blockchainlab.blockchain.block.SignedTransaction;
import blockchainlab.blockchain.block.Transaction;
import blockchainlab.blockchain.block.Transactions;
import blockchainlab.blockchain.blockchain.Blockchain;
import blockchainlab.blockchain.blockchain.InvalidBlockException;
import blockchainlab.blockchain.communicator.Client;
import blockchainlab.blockchain.communicator.Communicator;
import blockchainlab.blockchain.miner.BlockNotMinedException;
import blockchainlab.blockchain.miner.Mempool;
import blockchainlab.blockchain.miner.MultithreadedMiner;
import blockchainlab.blockchain.wallet.ColdWallet;
import blockchainlab.blockchain.wallet.HotWallet;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.logging.Logger;

public class MinerNode implements Runnable {
    Client cl;
    HotWallet w;
    MultithreadedMiner m;
    Mempool mem;
    Blockchain bc;
    Random rand = new Random();
    ColdWallet[] contacts;
    Logger logger;
    private static int MINER_N = 0;
    private int loop_n = 0;

    public MinerNode(Communicator c, HotWallet wallet, ColdWallet[] contacts) throws NoSuchAlgorithmException {
        w = wallet;
        m = new MultithreadedMiner();
        cl = new Client(c);
        this.contacts = contacts;
        this.logger = Logger.getLogger("MinerNode-" + MINER_N++);
    }

    void handle(SignedTransaction t) {
        try {
            t.verify();
            mem.push(t);
            logger.info("New transaction added to mempool:\n" + t);
        } catch (InvalidTransactionException e) {
            logger.warning("Invalid transaction: " + t);
        }
    }


    void handle(HashedBlock b) {
        try {
            b.verify();
            this.mem.removeTransactions(b.transactions);
            this.bc.addBlock(b);
            this.m.stopMining();
            logger.info("New block added to blockchain:\n" + b);
        } catch (InvalidBlockException e) {
            logger.warning("Invalid block: " + b);
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
                System.out.println("Can't sign transaction: " + t);
                return;
            }

            cl.broadcast((Object) st);
            logger.info("New transaction sent:\n" + st);
        }
    }

    public void run() {
        while (true) {
            try {
                Object o = cl.poll();
                handle(o);
            } catch (NoSuchElementException e) {

            }

            if (m.isBusy()) {
                continue;
            }

            try {
                HashedBlock b = m.checkResult();
                cl.broadcast((Object) b);
                continue;
            } catch (BlockNotMinedException e) {
                
            }
            
            Transactions t;
            try {
                t = mem.getTransactions(5);
            } catch (InvalidTransactionsPacketException e) {
                loop_n++;

                if (loop_n > 300) {
                    try {
                        t = mem.getAllTransactions();                        
                        loop_n = 0;
                        logger.info("Unable to create a block with 5 transactions, creating a block with all transactions in mempool");
                    } catch (InvalidTransactionsPacketException e2) {
                        logger.warning(e2.getMessage());
                        continue;
                    }
                }

                continue;
            }

            boolean removed = false;
            for (SignedTransaction st : t.getTransactions()) {
                if (bc.getBalance(st.from.pubKey) < (st.amount + st.fee)) {
                    mem.removeTransaction((Transaction) st);
                    removed = true;
                    logger.warning("Not enough money to send transaction: " + st);
                }
            }

            if (removed) {
                continue;
            }

            Hash h = bc.getLatestBlock().orElse(new Hash("0"));
            Block b = new Block(new Coinbase((ColdWallet) w), t, bc.getLastID() + 1, h);
            m.startMining(b, 2);
        }
    }
}