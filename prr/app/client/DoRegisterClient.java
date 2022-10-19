package prr.app.client;

import prr.core.Network;
import prr.app.exception.DuplicateClientKeyException;
import prr.core.exception.ClientKeyAlreadyUsedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("clientKey", Message.key());
    addStringField("clientName", Message.name());
    addIntegerField("clientTaxNumber", Message.taxId());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerClient(
              stringField("clientKey"),
              stringField("clientName"),
              integerField("clientTaxNumber")
      );
    } catch (ClientKeyAlreadyUsedException e) {
      throw new DuplicateClientKeyException(e.getKey());
    }
  }
}
