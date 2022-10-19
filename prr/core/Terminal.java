package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  /** Terminal's id **/
  private final String _id;

  /** Terminal's type **/
  private String _tipo;

  /** Terminal's debt **/
  private double _debt;

  /** Terminal's payments **/
  private double _payments;

  /** Enum that contains the terminal's mode **/
  private TerminalMode _terminalmode;

  /** List that contains the terminal's friends **/
  private List<String> _amigos;

  /** Linked list that contains the terminal's notifications **/
  private LinkedList<Notification> _notificacoes;

  /** Client that is associated with the terminal in question **/
  private Client _clientTerminal;

  /** Boolean that says if the terminal had made any type of comm or not **/
  private boolean _virgem;

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
    _amigos = new ArrayList<String>();
    _notificacoes = new LinkedList<>();
    _clientTerminal = null;
    _virgem = true;
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
  public double getTerminalDebts(){
    return this._debt;
  }

  /** @return terminal's payments **/
  public double getTerminalPayments(){
    return this._payments;
  }

  /** @return boolean that says if the terminal has made any type of Comm or not **/
  public boolean usedOrNot(){ return this._virgem;}

  /** @return terminal's Friends ArrayList **/
  public ArrayList<String> getTerminalAmigos(){
    return (ArrayList<String>) this._amigos;
  }

  /** @return terminal's Notifications LinkedList **/
  public LinkedList<Notification> getNotificacoesTerminal(){ return (LinkedList<Notification>) this._notificacoes;}

  /** @return terminal's asscociated Client **/
  public Client getClientTerminal(){
    return _clientTerminal;
  }

  /**
   * Void method that sets the Client's association with the terminal
   * @param _clientTerminal
   **/
  public void setClientTerminal(Client _clientTerminal) {
    this._clientTerminal = _clientTerminal;
  }

   /**
   Boolean method that returns true if the requirements necessary
   to set the terminal's mode on ON/Idle are correct,
   and then it sets the mode to ON/Idle while creating the right notification
   **/
   public boolean setONIdle() {
      if (_terminalmode.toString().equals("OFF") || _terminalmode.toString().equals("SILENCE") ||
              _terminalmode.toString().equals("BUSY")) {
        if (_terminalmode.toString().equals("OFF")) {
          Notification notif = new Notification(NotificationType.O2I, this);
          _notificacoes.addFirst(notif);
        }
        if (_terminalmode.toString().equals("SILENCE")){
          Notification notif = new Notification(NotificationType.S2I, this);
          _notificacoes.addFirst(notif);
        }
        if (_terminalmode.toString().equals("BUSY")){
          Notification notif = new Notification(NotificationType.B2I, this);
          _notificacoes.addFirst(notif);
        }
        _terminalmode = TerminalMode.ON;   // return boolean se uma destas for cumprida
        return true;
      }
      return false;
    }

  /**
   Boolean method that returns true if the requirements necessary
   to set the terminal's mode on SILENCE are correct,
   and then it sets the mode to SILENCE while creating the right notification
   **/
  public boolean setOnSilent() {
      if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("BUSY")){
        if (_terminalmode.toString().equals("OFF")){
          Notification notif = new Notification(NotificationType.O2S, this);
        }
        _terminalmode = TerminalMode.SILENCE;   // return boolean se uma destas for cumprida
        return true;
      }
      return false;
    }

  /**
   Boolean method that returns true if the requirements
   necessary to set the terminal's mode on OFF are correct,
   and then it sets the mode to OFF
   **/

  public boolean turnOff() {
    if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("SILENCE")) {
      _terminalmode = TerminalMode.OFF;
      return true;
    }
    return false;
  }

  /**
   * Void method that adds the Terminal's ID to the Friends List
   * @param idTerminalNewFriend
   **/
  public void addAmigo(String idTerminalNewFriend){
    this._amigos.add(idTerminalNewFriend);
  }

  /**
   * Terminal toString
   * @return a terminal in string format
   */
  public String terminalStringed() {
    if (this._amigos.isEmpty()) {
      return String.join(
              "|",
              getTerminalType(),
              getTerminalId(),
              getClientTerminal().getKey(),
              getTerminalModeEnum().toString(),
              String.valueOf(Math.round(getTerminalPayments())),
              String.valueOf(Math.round(getTerminalDebts())));
    } else {
      return String.join(
              "|",
              getTerminalType(),
              getTerminalId(),
              getClientTerminal().getKey(),
              getTerminalModeEnum().toString(),
              String.valueOf(Math.round(getTerminalPayments())),
              String.valueOf(Math.round(getTerminalDebts())),
              String.join(", ", _amigos));
    }
  }

  /**

  Funções sobre Comms por fazer:

  public void makeVoiceCall(Terminal t){
  }

  protected void acceptVoiceCall(Terminal t){
  }

  public void makeSMS(Terminal t, String msg){
  }

  protected void acceptSMS(Terminal t){
  }

  public void makeVideoCall(Terminal t){
  }

  protected void acceptVideoCall(Terminal t){
  }


   -> * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.

  public boolean canEndCurrentCommunication() {
    // por fazer
    return true;
  }


   -> * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.

  public boolean canStartCommunication() {
    // por fazer
    return true;
  }

  **/
}
