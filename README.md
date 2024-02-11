# Multi-threaded Dictionary Server #

This Dictionary Server uses a client-server architecture to implement a multi-threaded server
that allows concurrent clients to search the meaning(s) of a word, add a new word, and remove an existing word.

_How to Run_
1. Run the server through command line following the below sample command:
   
    # java –jar DictionaryServer.jar <port> <dictionary-file>
3. A sample command to start the client is:
4. 
    # java –jar DictionaryClient.jar <server-address> <server-port>
5. When the server is launched, it loads the dictionary data from *dictionary.json* which contains the initial list of words and their meanings.

