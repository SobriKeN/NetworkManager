package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.InvalidCommIDException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("commKey",Message.commKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    if (!_network.getComms().containsKey(integerField("commKey")) ||
            !_network.getComms().get(integerField("commKey")).getIdSender().equals(_receiver.getTerminalId())){
      _display.popup(Message.invalidCommunication());
    }
    _network.performPayment(integerField("commKey"));
  }
}
