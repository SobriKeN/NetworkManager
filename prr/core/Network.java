package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import prr.core.exception.InvalidClientIDException;
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

  /** The clients associated with the network uuuu*/
  private TreeMap<String,Client> _clients;

  public Network(){
    _terminals = new TreeMap<>();
    _clients = new TreeMap<>();
  }

  public boolean addFriend(String idTerminal, String friend){
    Terminal terminal;

    if (_terminals.containsKey(friend)){
      if (_terminals.containsKey(idTerminal)){
        terminal = _terminals.get(idTerminal);
        terminal.addAmigo(idTerminal);
        return true;
      }
      else
        return false;
    }
    else
      return false;
  }

  public Client registerClient(String key, String name, int taxNumber){
    Client client = new Client(key,name, taxNumber);
    _clients.put(client.getKey(),client);
    return client;
  }

  public Client getClient(String key) throws InvalidClientIDException{
    for (String clientKey : _clients.keySet()){
      if(clientKey.equals(key)){
        return _clients.get(clientKey);
      }
    }
    throw new InvalidClientIDException(key);
  }


  public String getClientString(String key) throws InvalidClientIDException {
    return getClient(key).clientStringed();
  }

  public Terminal registerTerminal(String key, String tipo, String idClient) throws InvalidClientIDException {
    int i = 0;
    Terminal terminal = new Terminal(key, tipo);
    if (_terminals.containsKey(idClient)) {
      terminal.setClientTerminal(_clients.get(idClient));
      _terminals.put(terminal.getTerminalId(), terminal);
      Client c = _clients.get(idClient);
      c.atualizaNumeroTerminaisAssoc(); // incrementa numero de terminais associados ao cliente
      return terminal;
    }
    else
      throw new InvalidClientIDException(idClient);
  }
  
  public Terminal getTerminal(String idTerminal){
    return _terminals.get(idTerminal);
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

