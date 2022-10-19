package prr.app.terminals;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    String[] options = {"BASIC","FANCY"};
    addStringField("terminalKey", Message.terminalKey());
    addOptionField("terminalType", Message.terminalType(),options);
    addStringField("clientTerminal", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerTerminal(
              stringField("terminalKey"),
              stringField("terminalType"),
              stringField("clientTerminal"));
    } catch (AlreadyExistsTerminalException e) {
      throw new DuplicateTerminalKeyException(e.getKey());
    } catch (InvalidClientIDException y) {
      throw new UnknownClientKeyException(y.getID());
    } catch(InvalidTerminalIDException t){
      throw new InvalidTerminalKeyException(t.getID());
    }
  }
}

