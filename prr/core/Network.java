package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.sun.jdi.ObjectReference;
import prr.core.exception.*;

/** Class Network that represents the network of the system**/
public class Network implements Serializable {

  /**
   * Serial number for serialization
   **/
  private static final long serialVersionUID = -488710144069185783L;

  /**
   * To see if the program has been saved since the last save() call
   */
  private boolean saveFlag = false;

  /**
   * The next communication's key
   */
  private int commId = 1;

  /**
   * The tariff plan related to this network
   */
  private TariffPlan _plano;

  /**
   * The terminals associated with the network
   **/
  private TreeMap<String, Terminal> _terminals;

  /**
   * The clients associated with the network
   **/
  private TreeMap<String, Client> _clients;

  /**
   * The communications registed in the system
   **/
  private TreeMap<Integer, Communication> _allComms;

  /**
   * Main Construtor
   */
  public Network() {
    _terminals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _clients = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    _allComms = new TreeMap<>();
    _plano = new TariffPlan("Plano");
  }

  /**
   * @return if the program has had any alterations after the last call
   */
  public boolean getSaveFlag() {
    return saveFlag;
  }

  public void deactivateSaveFlag() {
    saveFlag = false;
  }

  public void activateSaveFlag() {
    saveFlag = true;
  }

  public TreeMap<Integer, Communication> getComms() {
    return _allComms;
  }
  public void makeVoiceCall(Terminal sender, Terminal receiver) throws InvalidTerminalIDException {
    if (_terminals.containsKey(sender.getTerminalId()) &&
            _terminals.containsKey(receiver.getTerminalId())) {
      CommunicationVoice comm = new CommunicationVoice(sender, receiver, commId++);
      receiver.setComm(comm);
      receiver.setBusy(true);
      receiver.setUsed();
      sender.setComm(comm);
      sender.setBusy(true);
      sender.setUsed();
      _allComms.put(comm.getId(), comm);
      this.deactivateSaveFlag();
    }
    else
      throw new InvalidTerminalIDException(receiver.getTerminalId());
  }

  public void makeVideoCall(Terminal sender, Terminal receiver) throws InvalidTerminalIDException {
    if (_terminals.containsKey(sender.getTerminalId()) &&
               _terminals.containsKey(receiver.getTerminalId())){
      CommunicationVideo comm = new CommunicationVideo(sender, receiver, commId++);
      receiver.setComm(comm);
      receiver.setBusy(true);
      receiver.setUsed();
      sender.setComm(comm);
      sender.setBusy(true);
      sender.setUsed();
      _allComms.put(comm.getId(), comm);
      this.deactivateSaveFlag();
    }
    else
      throw new InvalidTerminalIDException(receiver.getTerminalId());
  }

  public long stopVoiceCall(CommunicationVoice comm, int duracao){
    long custo;
    Terminal sender = comm.getSender();
    Terminal receiver = comm.getReceiver();
    Client c = sender.getClientTerminal();
    comm.setOnGoing(false);
    comm.setSizeDuration(duracao);

    if (sender.getTerminalAmigos().contains(receiver.getTerminalId()))
      custo = (_plano.computeCost(c,comm)/2);
    else
      custo = _plano.computeCost(c,comm);

    comm.setCost(custo);
    comm.acabaCall();
    sender.setCommToNull();
    sender.setBusy(false);
    receiver.setCommToNull();
    receiver.setBusy(false);

    if(sender.getTerminalModeEnum().equals(TerminalMode.ON)){
      if (!sender.getTentaramNotificar().isEmpty()) {
        for (Terminal t : sender.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2I, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        sender.getTentaramNotificar().clear();
      }
    }

    if(receiver.getTerminalModeEnum().equals(TerminalMode.ON)){
      if (!receiver.getTentaramNotificar().isEmpty()) {
        for (Terminal t : receiver.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2I, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        receiver.getTentaramNotificar().clear();
      }
    }

    if(sender.getTerminalModeEnum().equals(TerminalMode.SILENCE)){
      if (!sender.getTentaramNotificar().isEmpty()) {
        for (Terminal t : sender.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2S, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        sender.getTentaramNotificar().clear();
      }
    }

    if ((c.getLevel() == ClientLevel.GOLD)){
      c.downgradeGoldToNormal();
    }
    if ((c.getLevel() == ClientLevel.PLATINUM)){
      c.downgradePlatinumToNormal();
    }
    this.deactivateSaveFlag();
    return comm.getCost();
  }

  public long stopVideoCall(CommunicationVideo comm, int duracao){
    long custo;
    Terminal sender = comm.getSender();
    Terminal receiver = comm.getReceiver();
    Client c = sender.getClientTerminal();
    comm.setOnGoing(false);
    comm.setSizeDuration(duracao);

    if (sender.getTerminalAmigos().contains(receiver.getTerminalId()))
      custo = (_plano.computeCost(c,comm)/2);
    else
      custo = _plano.computeCost(c,comm);

    comm.setCost(custo);
    comm.acabaCall();
    sender.setCommToNull();
    sender.setBusy(false);
    receiver.setCommToNull();
    receiver.setBusy(false);

    if(sender.getTerminalModeEnum().equals(TerminalMode.ON)){
      if (!sender.getTentaramNotificar().isEmpty()) {
        for (Terminal t : sender.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2I, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        sender.getTentaramNotificar().clear();
      }
    }

    if(receiver.getTerminalModeEnum().equals(TerminalMode.ON)){
      if (!receiver.getTentaramNotificar().isEmpty()) {
        for (Terminal t : receiver.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2I, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        receiver.getTentaramNotificar().clear();
      }
    }

    if(sender.getTerminalModeEnum().equals(TerminalMode.SILENCE)){
      if (!sender.getTentaramNotificar().isEmpty()) {
        for (Terminal t : sender.getTentaramNotificar()) {
          Notification n = new Notification(NotificationType.B2S, t);
          t.getClientTerminal().getNotificacoesClient().add(n);
        }
        sender.getTentaramNotificar().clear();
      }
    }

    if ((c.getLevel() == ClientLevel.GOLD)){
      c.downgradeGoldToNormal();
    }
    if ((c.getLevel() == ClientLevel.PLATINUM)){
      c.downgradePlatinumToNormal();
    }
    if ((c.getLevel()) == ClientLevel.GOLD){
      c.upgradeGoldToPlatinum(getCommsByClientId(c.getKey()));
    }
    this.deactivateSaveFlag();
    return comm.getCost();
  }

  public ArrayList<Communication> getCommsByClientId(String clientID){
    ArrayList<Communication> commClient = new ArrayList<>();

    for (int key : _allComms.keySet()) {
      if (_allComms.get(key).getSender().getClientTerminal().getKey().equals(clientID))
          commClient.add(_allComms.get(key));
    }
    return commClient;
  }

  /** @return the global network payments **/
  public long getGlobalClientPayments(){
    long clientPayments = 0;

    for (String id : _clients.keySet()){
      clientPayments += _clients.get(id).getPagamentos();
    }
    return Math.round(clientPayments);
  }

  /** @return the global network debts **/
  public long getGlobalClientDebts(){
    long clientDebts = 0;

    for (String id : _clients.keySet()){
      clientDebts += _clients.get(id).getDebts();
    }
    return Math.round(clientDebts);
  }

  /**
   @return a Comm that the user is searching for,
   with the given argument it will know if there is such communication,
   and if it doesn't exist, an exception is thrown
   @throws InvalidCommIDException
   **/
  public Communication getComm(int key) throws InvalidCommIDException {
    if (_allComms.containsKey(key)) {
      return _allComms.get(key);
    }
    throw new InvalidCommIDException(key);
  }

  /**
   @return the Comms's parameters as a String by searching it with its key and then
   using the method toCommString from the Class Communication
    * @param key
   * @throws InvalidCommIDException
   **/
  public String getCommStringed(int key) throws InvalidCommIDException {
    return getComm(key).toCommString();
  }

  /**
   @return an ArrayList with all the Communications in the system, if
   there is an error on the Communication's key, the program will try to
   catch the exception about that error, which is
   InvalidCommIDException
   */
  public ArrayList<String> getAllComms() {
    ArrayList<String> stringComms = new ArrayList<>();

    for (int key : _allComms.keySet()) {
      try {
        stringComms.add(getCommStringed(key));
      } catch (InvalidCommIDException e) {
        // probably will never happen
        e.printStackTrace();
      }
    }
    return stringComms;
  }

  /**
   @return an ArrayList with all the Communications made by a certain client,
   if there is an error on the Communication's key, the program will try to
   catch the exception about that error, which is
   InvalidCommIDException
   */
  public ArrayList<String> getCommsMadeByClient(String clientID) throws InvalidClientIDException {
    ArrayList<String> stringComms = new ArrayList<>();

    if(!_clients.containsKey(clientID)){
      throw new InvalidClientIDException(clientID);
    }

    for (int key : _allComms.keySet()) {
      if (_allComms.get(key).getSender().getClientTerminal().getKey().equals(clientID)) {
        try {
          stringComms.add(getCommStringed(key));
        } catch (InvalidCommIDException e) {
          // probably will never happen
          e.printStackTrace();
        }
      }
    }
    return stringComms;
  }

  /**
   @return an ArrayList with all the Communications received by a certain client,
   if there is an error on the Communication's key, the program will try to
   catch the exception about that error, which is
   InvalidCommIDException
   */
  public ArrayList<String> getCommsReceivedByClient(String clientID) throws InvalidClientIDException{
    ArrayList<String> stringComms = new ArrayList<>();

    if(!_clients.containsKey(clientID)){
      throw new InvalidClientIDException(clientID);
    }

    for (int key : _allComms.keySet()) {
      if (_allComms.get(key).getReceiver().getClientTerminal().getKey().equals(clientID)) {
        try {
          stringComms.add(getCommStringed(key));
        } catch (InvalidCommIDException e) {
          // probably will never happen
          e.printStackTrace();
        }
      }
    }
    return stringComms;
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
    @return an Array List with all the clients without any debts in the system,
    if there is an error on the Client's key, the program will try to
    catch the exception about that error, which is InvalidClientIDException
   */
  public ArrayList<String> getAllClientsWithNoDebts() {
    ArrayList<String> stringClients = new ArrayList<>();

    for (String client : _clients.keySet()) {
      try {
        if (_clients.get(client).getDebts() == 0)
          stringClients.add(getClientString(client));
      } catch (InvalidClientIDException e) {
        // probably will never happen
        e.printStackTrace();
      }
    }
    return stringClients;
  }

  /**
    @return an Array List with all the clients with debts in the system
    in descending order from the highest debt to the lowest,
    its created a new TreeMap that will store and sort by debt the clients
    in it, if there is an error on the Client's key, the program will try to
    catch the exception about that error, which is InvalidClientIDException
   */
  public ArrayList<String> getAllClientsWithDebts() {
    ArrayList<String> stringClients = new ArrayList<>();
    TreeMap<Client, Long>  _clientsWithDebts = new TreeMap<Client, Long>(new DebtsDescendingOrder());

    for (String c: _clients.keySet()) {
      if (_clients.get(c).getDebts() > 0)
        _clientsWithDebts.put(_clients.get(c), _clients.get(c).getDebts());
    }

    for (Client client : _clientsWithDebts.keySet()) {
      try {
          stringClients.add(getClientString(client.getKey()));
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
   @return the ArrayList that contains all the terminals of the
   system that have a positive balance (payments > debts). If there
   is an error on the Terminal's key, the program will try to
   catch the exception about that error, which is
   InvalidTerminalIDException
   */
  public ArrayList<String> getTerminalsPositiveBalance() {
    ArrayList<String> stringTerminals = new ArrayList<>();

    for (String terminal : _terminals.keySet()){
      try{
        if (getTerminal(terminal).getTerminalPayments() > getTerminal(terminal).getTerminalDebts())
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
        this.deactivateSaveFlag();
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
        this.deactivateSaveFlag();
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

  public void DoSendTextCommunication(String idSender, String idReceiver, String msg){
    long l;
    Terminal t1 = _terminals.get(idSender);
    Terminal t2 = _terminals.get(idReceiver);
    Client cliente = t1.getClientTerminal();
    CommunicationText c = new CommunicationText(msg, t1, t2, commId++);
    if (t1.getTerminalAmigos().contains(t2.getTerminalId()))
      l = (_plano.computeCost(cliente,c)/2);
    else
      l = _plano.computeCost(cliente,c);
    c.setCost(l);
    _allComms.put(c.getId(),c);

    if ((cliente.getLevel() == ClientLevel.GOLD)){ // downgrade de Gold para Normal após
      cliente.downgradeGoldToNormal();             // realizar uma comm e se tem saldo negativo
    }
    if ((cliente.getLevel() == ClientLevel.PLATINUM)){ // downgrade de Platinum para Normal após
      cliente.downgradePlatinumToNormal();             // 2 comms de texto seguidas e nao ter saldo negativo
    }
    if ((cliente.getLevel() == ClientLevel.PLATINUM)){      // downgrade de Platinum para Normal após
      cliente.downgradePlatinumToGold(getCommsByClientId(cliente.getKey()));
    }
    this.deactivateSaveFlag();
    }

    public void performPayment(int id){
      Communication c = _allComms.get(id);
      Terminal t = c.getSender();
      if(c.isPaid()){
        return;
      }
      t.paga(c.getCost());
      t.getClientTerminal().paga(c.getCost());
      c.pagarComm();
      if (t.getClientTerminal().getLevel() == ClientLevel.NORMAL)   //upgrade após fzr um pagamento para Gold
        t.getClientTerminal().upgradeNormalToGold();
      this.deactivateSaveFlag();
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

