package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private final String _id;
  private String _tipo;
  private double _debt;
  private double _payments;
  private TerminalMode _terminalmode;
  private List<String> _amigos;
  private Client _clientTerminal;

  public Terminal(String id, String tipo){
    _id = id;
    _terminalmode = TerminalMode.ON;
    _tipo = tipo;
    _debt = 0;
    _payments = 0;
    _amigos = new ArrayList<>();
    _clientTerminal = null;
  }

  public String getTerminalId(){
    return this._id;
  }

  public String getTerminalModeString(){
    return this._tipo;
  }

  public TerminalMode getTerminalModeEnum(){
    return this._terminalmode;
  }

  public double getTerminalDebts(){
    return this._debt;
  }

  public double getTerminalPayments(){
    return this._payments;
  }

  public ArrayList<String> getTerminalAmigos(){
    return (ArrayList<String>) this._amigos;
  }

  public Client getClientTerminal(){
    return _clientTerminal;
  }

  public void setClientTerminal(Client _clientTerminal) {
    this._clientTerminal = _clientTerminal;
  }

  public boolean setONIdle() {
      if (_terminalmode.toString().equals("OFF") || _terminalmode.toString().equals("SILENCE") ||
              _terminalmode.toString().equals("BUSY")) {
        _terminalmode = TerminalMode.ON;   // return boolean se uma destas for cumprida
        return true;
      }
      return false;
    }

  public boolean setOnSilent() {
      if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("BUSY")){
        _terminalmode = TerminalMode.SILENCE;   // return boolean se uma destas for cumprida
        return true;
      }
      return false;
    }


  public boolean turnOff() {
    if (_terminalmode.toString().equals("ON") || _terminalmode.toString().equals("SILENCE")) {
      _terminalmode = TerminalMode.OFF;
      return true;
    }
    return false;
  }

  public void addAmigo(String idTerminalNewFriend){
    this._amigos.add(idTerminalNewFriend);
  }

  // depende do tipo do terminal

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

  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return true;
  }
  
  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    return true;
  }
}
