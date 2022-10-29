package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.InvalidTerminalIDException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  /**
   * addStringField that represents the Terminal's key
   **/
  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("terminalFriend", Message.terminalKey());
  }

  /**
   * execute that adds a friend (of type Terminal) to the terminal's
   * friend list
   **/
  @Override
  protected final void execute() throws CommandException {
    try {
      if(!_network.getTerminal(_receiver.getTerminalId())
          .getTerminalAmigos()
            .contains(stringField("terminalFriend"))) {
        _network.addFriend(_receiver.getTerminalId(), stringField("terminalFriend"));
      }
    } catch (InvalidTerminalIDException e) {
      throw new RuntimeException(e);
    }
  }
}
