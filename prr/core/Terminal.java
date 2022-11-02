package prr.core;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * Class that represents the terminal.
 */
public class Terminal implements Serializable {

  /** Serial number **/
  private static final long serialVersionUID = -8325403726503191669L;

  /** Terminal's id **/
  private final String _id;

  /** Terminal's type **/
  private String _tipo;

  /** Terminal's debt **/
  private long _debt;

  /** Terminal's payments **/
  private long _payments;

  /** Enum that contains the terminal's mode **/
  private TerminalMode _terminalmode;

  /** List that contains the terminal's friends **/
  private TreeSet<String> _amigos;

  /** Client that is associated with the terminal in question **/
  private Client _clientTerminal;

  /** Boolean that says if the terminal had made any type of comm or not **/
  private boolean _virgem;

  /** Boolean that says if the terminal is in a Communication or not **/
  private boolean _busy;

  /** Current communication in the terminal */
  private Communication _currentComm;

  /**
   * Main Construtor
   * @param id
   * @param tipo
   */

  public Terminal(String id, String tipo){
    _id = id;
    _terminalmode = TerminalMode.ON;
    _tipo = tipo;
    _debt = 0;
    _payments = 0;
    _amigos = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
    _clientTerminal = null;
    _virgem = true;
    _busy = false;
    _currentComm = null;
  }

  /** @return terminal's id **/
  public String getTerminalId(){
    return this._id;
  }

  /** @return terminal's type **/
  public String getTerminalType(){
    return this._tipo;
  }

  /** @return terminal's mode **/
  public TerminalMode getTerminalModeEnum(){
    return this._terminalmode;
  }

  /** @return terminal's debt **/
  public long getTerminalDebts(){
    return this._debt;
  }

  /** @return terminal's payments **/
  public long getTerminalPayments(){
    return this._payments;
  }

  /** @return boolean that says if the terminal has made any type of Comm or not **/
  public boolean usedOrNot(){ return this._virgem;}

  /** @return terminal's Friends ArrayList **/
  public TreeSet<String> getTerminalAmigos(){return (TreeSet<String>) this._amigos;}

  /** @return terminal's asscociated Client **/
  public Client getClientTerminal(){
    return _clientTerminal;
  }

  /** @return terminal's asscociated Client **/
  public Communication getCurrentComm(){
    return _currentComm;
  }

  public void setUsed(){
    this._virgem = false;
  }

  public void setBusy(boolean b){
    this._busy = b;
  }

  public void adicionaDebts(long cost){
    this._debt += cost;
  }

  /**
   * Void method that sets the Client's association with the terminal
   * @param _clientTerminal
   **/
  public void setClientTerminal(Client _clientTerminal) {
    this._clientTerminal = _clientTerminal;
  }

  /**
   * Void method that sets the communication that is going to be made
   * @param _currentComm
   **/
   public void setComm(Communication _currentComm) {
    this._currentComm = _currentComm;
  }

   /**
   Void method that returns true if the requirements necessary
   to set the terminal's mode on ON/Idle are correct,
   and then it sets the mode to ON/Idle while creating the right notification
   **/
   public void setONIdle() {
      if (_terminalmode.toString().equals("OFF") || _terminalmode.toString().equals("SILENCE") ||
              _terminalmode.toString().equals("BUSY")) {
        if (_terminalmode.toString().equals("OFF")) {
          Notification notif = new Notification(NotificationType.O2I, this);
          this._clientTerminal.getNotificacoesClient().add(notif);
        }
        if (_terminalmode.toString().equals("SILENCE")){
          Notification notif = new Notification(NotificationType.S2I, this);
          this._clientTerminal.getNotificacoesClient().add(notif);
        }
        if (_terminalmode.toString().equals("BUSY")){
          Notification notif = new Notification(NotificationType.B2I, this);
          this._clientTerminal.getNotificacoesClient().add(notif);
        }
        _terminalmode = TerminalMode.ON;
      }
    }

  /**
   Void method that returns true if the requirements necessary
   to set the terminal's mode on SILENCE are correct,
   and then it sets the mode to SILENCE while creating the right notification
   **/
  public void setOnSilent() {
      if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("BUSY")){
        if (_terminalmode.toString().equals("OFF")){
          Notification notif = new Notification(NotificationType.O2S, this);
          this._clientTerminal.getNotificacoesClient().add(notif);
        }
        _terminalmode = TerminalMode.SILENCE;
      }
    }

  /**
   Void method that returns true if the requirements
   necessary to set the terminal's mode on OFF are correct,
   and then it sets the mode to OFF
   **/

  public void turnOff() {
    if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("SILENCE")) {
      _terminalmode = TerminalMode.OFF;
    }
  }

  /**
   * Void method that adds the Terminal's ID to the Friends List
   * @param idTerminalNewFriend
   **/
  public void addAmigo(String idTerminalNewFriend){
    this._amigos.add(idTerminalNewFriend);
  }

  /**
   * Void method that adds the Terminal's ID to the Friends List
   * @param idTerminalOldFriend
   **/
  public void removeAmigo(String idTerminalOldFriend){
    this._amigos.remove(idTerminalOldFriend);
  }

  /**
   * Terminal toString
   * @return a terminal in string format
   */
  public String terminalStringed() {
    String terminalModeStringed = getTerminalModeEnum().toString();

    if(getTerminalModeEnum() == TerminalMode.ON){
      terminalModeStringed = "IDLE";
    }

    if (this._amigos.isEmpty()) {
      return String.join(
              "|",
              getTerminalType(),
              getTerminalId(),
              getClientTerminal().getKey(),
              terminalModeStringed,
              String.valueOf(Math.round(getTerminalPayments())),
              String.valueOf(Math.round(getTerminalDebts())));
    } else {
      return String.join(
              "|",
              getTerminalType(),
              getTerminalId(),
              getClientTerminal().getKey(),
              terminalModeStringed,
              String.valueOf(Math.round(getTerminalPayments())),
              String.valueOf(Math.round(getTerminalDebts())),
              String.join(", ", _amigos));
    }
  }


  /** Funções sobre Comms por fazer: **/

  public void makeSMS(Terminal t, String msg){
    CommunicationText c = new CommunicationText(msg, this, t);
  }

  protected void acceptSMS(Terminal t){

  }

  public void makeVoiceCall(Terminal t){
    CommunicationVoice c = new CommunicationVoice(this, t);
  }

  protected void acceptVoiceCall(Terminal t){
  }

  public void makeVideoCall(Terminal t){
    CommunicationVideo c = new CommunicationVideo(this, t);
  }

  protected void acceptVideoCall(Terminal t){
  }

  /**
   -> * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
  **/
  public boolean canEndCurrentCommunication() {
    return _busy;
  }

  /**
   -> * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
  **/
  public boolean canStartCommunication() {
    return !_busy;
  }

}
