package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }

  /**
   * execute that turns the terminal OFF, if the terminal's already turned off, message will be sent
   **/
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getTerminalModeEnum() == TerminalMode.OFF){
      _display.popup(Message.alreadyOff());
    }
    _receiver.turnOff();
  }
}

