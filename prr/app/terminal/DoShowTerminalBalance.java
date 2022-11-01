package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.InvalidTerminalIDException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show balance.
 */
class DoShowTerminalBalance extends TerminalCommand {

  DoShowTerminalBalance(Network context, Terminal terminal) {
    super(Label.SHOW_BALANCE, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    _display.popup(
            Message.terminalPaymentsAndDebts(
                    _receiver.getTerminalId(),
                    _receiver.getTerminalPayments(),
                    _receiver.getTerminalDebts()
            )
    );
  }
}
