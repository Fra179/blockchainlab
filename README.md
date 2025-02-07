# 🔬 Blockchain Laboratory

> Blockchain Laboratory: ₿itcoin experiments.

## 🚦 Getting Started

### Installation

```bash
brew tap danielfalbo/homebrew-formulae
brew install blockchainlab
```

### Usage

#### Interactive mode: design your own experiment

Run

```bash
blockchainlab
```

to launch the app in interactive mode: it will guide you through the desing of your own experiment.

#### Run an example experiment

```bash
blockchainlab <experiment>
```

## 🧫 Available Experiments

### Simple Transactions (`basics`)

Bitcoins are transferred between agents and transactions are validated by miners competing in constructing blockchains.

### Double-Spending (`doubleSpending`)

A malicious agent attempts to create a double-spending transaction by submitting multiple payment attemps summing up to a larger amount of coins than he owns. Will the system allow them to spend more than they own?

### Invalid Transaction (`invalidTransaction`)

A malicious miner attempts to create an invalid block that includes a transaction moving bitcoins to their wallet from someone else's without their consensus.

### 51% Attack (`51percentAttack`)

An attacker gains control of more than 50% of the mining power in the network. Will them be able to validate new malicious transactions and get the invalid blocks to survive in the blockchain?

### Bogus Transactions (`bogusTransactions`)

A malicious agent attempts to flood the network with bogus transactions to disrupt the validation process. Will this slow down the network?

### Selfish Mining (`selfishMining`)

A selfish miner attempts to manipulate the network by withholding valid blocks they have found in order to gain an advantage over other miners. They may then release multiple blocks at once in order to invalidate the work of other miners and gain a larger share of the rewards.

## 👨‍💻 Contributing

Want to contribute? Read the [Developer's Notes](./dev.md)

### 🛣️ Roadmap

1. [MultithreadedMiner](./app/src/main/java/blockchainlab/blockchain/MultithreadedMiner.java)
2. Have `MultithreadedMiner`s get the blocks to mine from some sort of shared queue "pool", and notify all other miners to stop working once a block is mined.
3. Have `MultithreadedMiner`s to push mined blocks onto another different sort of "pool" of mined blocks, for nodes/validators/whoever to validate them with the blockchain and eventually merge them.
4. We'll figure it out.

## 🌍 Resources

- Professor Pietro Cenciarelli's Assignment ([original pdf](./assignment.pdf), [md transcript](./assignment.md))
- [But how does bitcoin actually work? by 3Blue1Brown](https://youtu.be/bBC-nXj3Ng4)
- [Blockchain 101 - A Visual Demo by Anders Brownworth](https://youtu.be/_160oMzblY8)
- [TeachYourselfCrypto](https://teachyourselfcrypto.com)
- [Whiteboard Crypto](https://www.youtube.com/c/whiteboardcrypto)
- [Satoshi Nakamoto's Bitcoin Paper](https://bitcoin.org/bitcoin.pdf)
- [Vitalik Buterin's Ethereum Paper](https://ethereum.org/en/whitepaper)
- My [notes](https://danielfalbo.notion.site/0b2a7d36801a4762b6e5f2cffff45705) for professor Pietro Cenciarelli's course on _Programming_.
