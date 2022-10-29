package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import prr.core.exception.*;

/** Class Network that represents the network of the system**/
public class Network implements Serializable {

  /** Serial number for serialization **/
  private static final long serialVersionUID = -488710144069185783L;

  /** The network's balance **/
  private int _saldo = 0;

  /** To see if the program has been saved since the last save() call */
  private boolean saveFlag = false;

  /** The terminals associated with the network **/
  private TreeMap<String, Terminal> _terminals;

  /** The clients associated with the network **/
  private TreeMap<String, Client> _clients;

  /**
   * Main Construtor
   */
  public Network() {
    _terminals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _clients = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  }

  /** @return if the program has had any alterations after the last call */
  public boolean getSaveFlag(){
    return saveFlag;
  }

  public void deactivateSaveFlag(){
    saveFlag = false;
  }

  public void activateSaveFlag(){
    saveFlag = true;
  }

  /**
  @return a Client that the user is searching for,
  with the given argument it will know if there is such client,
  and if it doesn't exist, an exception is thrown
  @throws InvalidClientIDException
  **/

  public Client getClient(String key) throws InvalidClientIDException {
      if (_clients.containsKey(key)) {
        return _clients.get(key);
      }
    throw new InvalidClientIDException(key);
  }

  /**
   @return the Client's parameters as a String by searching it with its key and then
   using the method clientStringed from the Class Client
   * @param key
   * @throws InvalidClientIDException
  **/
  public String getClientString(String key) throws InvalidClientIDException {
    return getClient(key).clientStringed();
  }

  /**
    @return an ArrayList with all the Clients in the system, if
    there is an error on the Client's key, the program will try to
    catch the exception about that error, which is
    InvalidClientIDException
   */
  public ArrayList<String> getAllClients() {
    ArrayList<String> stringClients = new ArrayList<>();

    for (String client : _clients.keySet()) {
      try {
        stringClients.add(getClientString(client));
      } catch (InvalidClientIDException e) {
        // probably will never happen
        e.printStackTrace();
      }
    }
    return stringClients;
  }

  /**
   Void method that regists a Client with the arguments given;
   if there already exists a Client with the key given by the user,
   an exception will be thrown; if not it will simply create a Client
   and add it to the system
   * @param key
   * @param name
   * @param taxNumber
   * @throws ClientKeyAlreadyUsedException
   */
  public void registerClient(String key, String name, int taxNumber) throws ClientKeyAlreadyUsedException {
    String keyLowerCase = key.toLowerCase();
    for (String mapKey : _clients.keySet()) {
      if (mapKey.toLowerCase().equals(keyLowerCase)) {
        throw new ClientKeyAlreadyUsedException(key);
      }
    }
    Client client = new Client(key, name, taxNumber);
    _clients.put(client.getKey(), client);
    this.deactivateSaveFlag();
  }

  /**
   @return the terminal which is being looked for,
   if the terminal doesn't exist, an exception is thrown.
   If not, the program will simply return the desired terminal
   * @param key
   * @throws InvalidTerminalIDException
  **/
  public Terminal getTerminal(String key) throws InvalidTerminalIDException {
      if (_terminals.containsKey(key)) {
        return _terminals.get(key);
      }
    throw new InvalidTerminalIDException(key);
  }

  /**
   @return the Terminal's parameters by searching it with its key and then
   using the method terminalStringed from the Class Terminal
   * @param key
   * @throws InvalidTerminalIDException
   */
  public String getTerminalString(String key) throws InvalidTerminalIDException {
    return getTerminal(key).terminalStringed();
  }

  /**
   @return the ArrayList that contains all the terminals of the system,
   if there is an error on the Terminal's key, the program will try to
   catch the exception about that error, which is
   InvalidTerminalIDException
   */
  public ArrayList<String> getAllTerminals() {
    ArrayList<String> stringTerminals = new ArrayList<>();

    for (String terminal : _terminals.keySet()) {
      try {
        stringTerminals.add(getTerminalString(terminal));
      } catch (InvalidTerminalIDException e) {
        //probably never going to get used
        e.printStackTrace();
      }
    }
    return stringTerminals;
  }

  /**
   @return the ArrayList that contains all the terminals of the
   system that were never used once. if there is an error on the
   Terminal's key, the program will try to
   catch the exception about that error, which is
   InvalidTerminalIDException
   */
  public ArrayList<String> getAllVirginTerminals() {
    ArrayList<String> stringTerminals = new ArrayList<>();

    for (String terminal : _terminals.keySet()){
      try{
        if (getTerminal(terminal).usedOrNot())
          stringTerminals.add(getTerminalString(terminal));
      } catch (InvalidTerminalIDException e){
        //probably is never going to happen
        e.printStackTrace();
      }
    }
    return stringTerminals;
  }

  /**
   @return a terminal that is created and registed with the given arguments,
   if the terminal's key length is bigger than 6, it has an error;
   if the terminal's key already exists, it has an error;
   and if there is no client with the given key, it has an error;
   * @param key
   * @param tipo
   * @param idClient
   * @throws InvalidClientIDException
   * @throws AlreadyExistsTerminalException
   * @throws InvalidTerminalIDException
   */
  public Terminal registerTerminal(String tipo, String key, String idClient) throws InvalidClientIDException,
          AlreadyExistsTerminalException, InvalidTerminalIDException {
    if (key.length() != 6) {
      throw new InvalidTerminalIDException(key);
    }
    if (_terminals.containsKey(key)) {
      throw new AlreadyExistsTerminalException(key);
    }
    if (!_clients.containsKey(idClient)) {
      throw new InvalidClientIDException(idClient);
    }
    Terminal terminal = new Terminal(key, tipo);
    Client c = _clients.get(idClient);
    terminal.setClientTerminal(c);
    _terminals.put(terminal.getTerminalId(), terminal);
    c.atualizaNumeroTerminaisAssoc(); // incrementa numero de terminais associados ao cliente
    this.deactivateSaveFlag();
    return terminal;
  }

  /**
   Void method that verifies and adds a Friend of the type
   terminal, to the given terminal's friend list.
   * @param idTerminal
   * @param friend
   */
  public void addFriend(String idTerminal, String friend) throws InvalidTerminalIDException {
    Terminal terminal;

    if (_terminals.containsKey(friend)) {
      if (_terminals.containsKey(idTerminal)) {
        terminal = _terminals.get(idTerminal);
        terminal.addAmigo(friend);
      } else
        throw new InvalidTerminalIDException(idTerminal);
    } else
      throw new InvalidTerminalIDException(friend);
  }

  /**
   Void method that verifies and removes a Friend of the type
   terminal, to the given terminal's friend list.
   * @param idTerminal
   * @param friend
   */
  public void removeFriend(String idTerminal, String friend) throws InvalidTerminalIDException {
    Terminal terminal;

    if (_terminals.containsKey(friend)) {
      if (_terminals.containsKey(idTerminal)) {
        terminal = _terminals.get(idTerminal);
        terminal.removeAmigo(friend);
      } else
        throw new InvalidTerminalIDException(idTerminal);
    } else
      throw new InvalidTerminalIDException(friend);
  }
  /**
   @return a list of the notifications in String for later purposes and methods, with
   the given key, it searches the respective client, "reads" and clears the Client's
   notifications, and then it saves the notifications that were turned to Strings in an ArrayList
   * @param key
   * @throws InvalidClientIDException
   */
  public List<String> readClientNotifications(String key) throws InvalidClientIDException {
    Client client = getClient(key);
    List<String> notificationInString = new ArrayList<>();
    for(Notification n: client.getNotificacoesClient()){
      notificationInString.add(n.notificationStringed());
    }
    client.clearNotifications();
    return notificationInString;
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO error while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException  {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

}

