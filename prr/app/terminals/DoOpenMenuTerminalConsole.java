package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.InvalidTerminalIDException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("terminalKey", Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String chave = stringField("terminalKey");
    try {
      new prr.app.terminal.Menu(this._receiver,_receiver.getTerminal(chave)).open(); //idfk kill me
    } catch (InvalidTerminalIDException e) {
      throw new UnknownTerminalKeyException(e.getID());
    }
  }
}

