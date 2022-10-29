package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.InvalidTerminalIDException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("terminalFriend", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      if(_network.getTerminal(_receiver.getTerminalId())
          .getTerminalAmigos()
            .contains(stringField("terminalFriend"))) {
        _network.removeFriend(_receiver.getTerminalId(), stringField("terminalFriend"));
      }
    } catch (InvalidTerminalIDException e) {
      throw new RuntimeException(e);
    }
  }
}
