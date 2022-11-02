package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.TerminalMode;
import prr.core.exception.InvalidTerminalIDException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import java.util.Objects;

/**
 * Command for starting communication.
 * **/
 class DoStartInteractiveCommunication extends TerminalCommand {
     DoStartInteractiveCommunication(Network context, Terminal terminal) {
      super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
      String[] options = {"VIDEO","VOICE"};
      addStringField("terminalKey", Message.terminalKey());
      addOptionField("commType", Message.commType(), options);
     }

     @Override
     protected final void execute() throws CommandException {
         String terminalDestino = stringField("terminalKey");
         String commType = optionField("commType");
         try {
             if (Objects.equals(_receiver.getTerminalType(), "BASIC"))
                 _display.popup(Message.unsupportedAtOrigin(_receiver.getTerminalId(), "BASIC"));
             if (Objects.equals(_network.getTerminal(terminalDestino).getTerminalType(), "BASIC"))
                 _display.popup(Message.unsupportedAtDestination(_receiver.getTerminalId(), "BASIC"));
             if (_network.getTerminal(terminalDestino).getTerminalModeEnum() == TerminalMode.OFF)
                 _display.popup(Message.destinationIsOff(terminalDestino));
             if (_network.getTerminal(terminalDestino).getTerminalModeEnum() == TerminalMode.SILENCE)
                 _display.popup(Message.destinationIsSilent(terminalDestino));
             if (!_network.getTerminal(terminalDestino).canStartCommunication())
                 _display.popup(Message.destinationIsBusy(terminalDestino));

             if (Objects.equals(commType, "VIDEO")) {
                 _network.makeVideoCall(_network.getTerminal(terminalDestino), _receiver);
             } else
                 _network.makeVoiceCall(_network.getTerminal(terminalDestino), _receiver);
         } catch (InvalidTerminalIDException e){
             throw new UnknownTerminalKeyException(e.getID());
         }
     }
 }

