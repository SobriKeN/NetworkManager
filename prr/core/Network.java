package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import prr.core.exception.*;
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

  public void registerClient(String key, String name, int taxNumber) throws ClientKeyAlreadyUsedException {
    String keyLowerCase = key.toLowerCase();
    for (String mapKey : _clients.keySet()) {
      if (mapKey.toLowerCase().equals(keyLowerCase)) {
        throw new ClientKeyAlreadyUsedException(key);
      }
      Client client = new Client(key, name, taxNumber);
      _clients.put(client.getKey(), client);
    }
  }

  public Client getClient(String key) throws InvalidClientIDException{
    for (String clientKey : _clients.keySet()){
      if(clientKey.equals(key)){
        return _clients.get(clientKey);
      }
    }
    throw new InvalidClientIDException(key);
  }

  public Terminal getTerminal(String key) throws InvalidTerminalIDException {
    for (String terminalKey : _terminals.keySet()){
      if(terminalKey.equals(key)){
        return _terminals.get(terminalKey);
      }
    }
    throw new InvalidTerminalIDException(key);
  }

  public String getClientString(String key) throws InvalidClientIDException {
    return getClient(key).clientStringed();
  }

  public String getTerminalString(String key) throws InvalidTerminalIDException{
    return getTerminal(key).terminalStringed();
  }
  public ArrayList<String> getAllClients() {
    ArrayList<String> stringClients = new ArrayList<>();

    for (String client : _clients.keySet()) {
      try {
        stringClients.add(getClient(client).clientStringed());
      } catch (InvalidClientIDException e) {
        // probably will never happen
        e.printStackTrace();
      }
    }
    return stringClients;
  }

  public ArrayList<String> getAllTerminals(){
    ArrayList<String> stringTerminals = new ArrayList<>();

    for (String terminal : _terminals.keySet()){
      try {
        stringTerminals.add(getTerminal(terminal).terminalStringed());
      } catch (InvalidTerminalIDException e) {
        //probably never gonna get used
        e.printStackTrace();
      }
    }
    return stringTerminals;
  }

  public ArrayList<String> getAllVirginTerminals() {
    ArrayList<String> stringTerminals = new ArrayList<>();

    for (String terminal : _terminals.keySet()){
      try{
        if (getTerminal(terminal).usedOrNot())
          stringTerminals.add(getTerminal(terminal).terminalStringed());
      } catch (InvalidTerminalIDException e){
        //probably is never going to happen
        e.printStackTrace();
      }
    }
    return stringTerminals;
  }

  public Terminal registerTerminal(String key, String tipo, String idClient) throws InvalidClientIDException, AlreadyExistsTerminalException {
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

