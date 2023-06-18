package blockchainlab;
import blockchainlab.blockchain.ClientNode;
import blockchainlab.blockchain.MinerNode;
import blockchainlab.blockchain.communicator.Communicator;
import blockchainlab.blockchain.wallet.ColdWallet;
import blockchainlab.blockchain.wallet.HotWallet;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class App {
    public int numClients;
    public int numMiners;
    public int numNodes;


    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {

        Scanner askMiners = new Scanner(System.in);
        System.out.println("Enter number of miners desired:");
        String inputMiners = askMiners.nextLine();
        System.out.println("Number of nodes is:" + inputMiners);
        int numMiners = Integer.parseInt(inputMiners);

        Scanner askClients = new Scanner(System.in);
        System.out.println("Enter number of clients desired:");
        String inputClients = askClients.nextLine();
        System.out.println("Number of clients is:" + inputClients);
        int numClients = Integer.parseInt(inputClients);

        int numNodes = numClients + numMiners;

        askMiners.close();
        askClients.close();

        //Genera tanti wallet quanti i nodi sulla rete
        HotWallet[] wallets = new HotWallet[numNodes];

        for (int i = 0; i < numNodes; i++) {
            wallets[i] = new HotWallet(HotWallet.generateKeyPair());
        }

        Thread[] clients = new Thread[numClients];
        Thread[] miners = new Thread[numMiners];

        Communicator communicator = new Communicator();


        for (int i = 0; i < numClients; i++) {
            ColdWallet[] threadContacts = new ColdWallet[numClients - 1];

            int k = 0;
            for (int j = 0; j < numClients; j++) {
                if (j != i) {
                    threadContacts[k] = wallets[j];
                    k++;
                }
            }

            clients[i] = new Thread(new ClientNode(communicator, wallets[i], threadContacts));
            clients[i].start();
        }

        for (int i = 0; i < numMiners; i++) {
            ColdWallet[] threadContacts = new ColdWallet[numMiners - 1];

            int k = 0;
            for (int j = numClients; j < numNodes; j++) {
                if (j != i+numClients) {
                    threadContacts[k] = wallets[j];
                    k++;
                }
            }

            miners[i] = new Thread(new MinerNode(communicator, wallets[i], threadContacts));
            miners[i].start();
        }

        Thread.sleep(20000);
        for (Thread t: clients) {
            t.interrupt();
        }
        for (Thread t: miners) {
            t.interrupt();
        }
    }
}
