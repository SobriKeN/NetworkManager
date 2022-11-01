package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }

  /**
   * execute that turns the terminal ON,i f the terminal's already turned on, message will be sent
   **/
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getTerminalModeEnum() == TerminalMode.ON){
      _display.popup(Message.alreadyOn());
    }
    _receiver.setONIdle();
  }
}
