package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.InvalidClientIDException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("clientKey", Message.key());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      if (!_receiver.getClient(stringField("clientKey")).isRecieveNotifications())
        _receiver.getClient(stringField("clientKey")).activateNotifications();
      else
        _display.popup(Message.clientNotificationsAlreadyEnabled());
    } catch (InvalidClientIDException e) {
      throw new UnknownClientKeyException(e.getID());
    }
  }
}
