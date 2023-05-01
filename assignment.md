# Bitcoin

> Project specifications

_Bitcoin_ is a digital currency using peer-to-peer technology to operate in a node network without central authority or bank. _Transactions_ recording coin transfers are verified by a decentralized system based on strong cryptography. A distributed ledger based on _blockchain_ technology records a public history of transactions which malicious attackers cannot change if honest nodes control a majority of CPU power.

## Assignment

Develop and implement in Java a model of bitcoin circulation in a network of nodes representing independent agents and miners, and featuring no centralized authority. The model should address at least some of the interesting features of the blockchain idea for preventing double spending of coins. No need to capture the full complexity of real life, so simplifying assumptions are welcome. The system should allow running simulations where bitcoins are transferred between agents and transactions are validated by miners competing in constructing blockchains. Different blockchains may emerge during simulation and the longest should survive as the system evolves. Malicious agents and miners may also be comprised in the picture in order to verify the roboustness of the model.

The project should come with a short report including:

- an abstract description of the model, including a discussion of the adopted simplifying assumptions;
- a high level description of the system;
- a few examples of simulation showing the interesting features of the system;
- an appendix with the Java source code (which should be extensively commented).
