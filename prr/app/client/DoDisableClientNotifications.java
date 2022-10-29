package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.InvalidClientIDException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("clientKey", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      if (_receiver.getClient(stringField("clientKey")).isRecieveNotifications())
        _receiver.getClient(stringField("clientKey")).deactivateNotifications();
      else
        _display.popup(Message.clientNotificationsAlreadyDisabled());
    } catch (InvalidClientIDException e) {
      throw new UnknownClientKeyException(e.getID());
    }
  }
}
