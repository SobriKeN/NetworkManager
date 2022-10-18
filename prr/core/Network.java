package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import prr.core.exception.UnrecognizedEntryException;
import prr.core.Parser;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {
  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  /** The network's balance*/
  private int _saldo = 0;

  /** The terminals associated with the network */
  private TreeMap<String,Terminal> _terminals;

  /** The clients associated with the network */
  private TreeMap<String,Client> _clients;

  public Network(){
    _terminals = new TreeMap<>();
    _clients = new TreeMap<>();
  }

  public

  public void registerClient(String key, String name, int taxNumber){
    Client client = new Client(key,name, taxNumber);
    _clients.put(client.getKey(),client);
  }

  public void registerTerminal(String key, String tipo, String idClient){
    Terminal terminal = new Terminal(key, tipo) {
    }
  }
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

}

