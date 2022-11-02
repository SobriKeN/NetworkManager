package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }

  /**
   * execute that silences the terminal,if the terminal's already silent, message will be sent
   **/
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getTerminalModeEnum() == TerminalMode.SILENCE){
      _display.popup(Message.alreadySilent());
    }
    else
      _receiver.setOnSilent();
  }
}

