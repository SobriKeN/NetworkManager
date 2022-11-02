package prr.app.terminal;

import prr.core.*;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

 /**
 * Command for ending communication.
 * **/
 class DoEndInteractiveCommunication extends TerminalCommand {
     DoEndInteractiveCommunication(Network context, Terminal terminal) {
         super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
         addIntegerField("duracao", Message.duration());
     }

     /**
      * For now this option has no effect but, with the help of a boolean in the Terminal Class,
      * it's possible to hide the option when needed.
      **/

     @Override
     protected final void execute() throws CommandException {
         int duracao = integerField("duracao");
         try {
             if (_receiver.getCurrentComm() instanceof CommunicationVideo)
                 _display.popup(
                         Message.communicationCost(
                                 _network.stopVideoCall((CommunicationVideo) _receiver.getCurrentComm(), duracao)
                         )
                 );
             if (_receiver.getCurrentComm() instanceof CommunicationVoice)
                 _display.popup(
                         Message.communicationCost(
                                 _network.stopVoiceCall((CommunicationVoice) _receiver.getCurrentComm(), duracao)
                         )
                 );
         } catch (NumberFormatException e) { // suposta exceção para o formato numerico da duração
             throw new RuntimeException(e);
         }
     }
 }


