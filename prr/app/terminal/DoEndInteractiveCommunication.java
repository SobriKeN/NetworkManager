package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

 /**
 * Command for ending communication.
 * **/
 class DoEndInteractiveCommunication extends TerminalCommand {
     DoEndInteractiveCommunication(Network context, Terminal terminal) {
      super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
     }

     /**
      For now this option has no effect but, with the help of a boolean in the Terminal Class,
      its possible to hide the option when needed.
      **/

     @Override
     protected final void execute() throws CommandException {

     }
 }


