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
             Terminal t = _network.getTerminal(terminalDestino);
             if (Objects.equals(_receiver.getTerminalType(), "BASIC") && commType.equals("VIDEO"))
                 _display.popup(Message.unsupportedAtOrigin(_receiver.getTerminalId(), "BASIC"));
             else if (Objects.equals(t.getTerminalType(), "BASIC") && commType.equals("VIDEO"))
                 _display.popup(Message.unsupportedAtDestination(_receiver.getTerminalId(), "BASIC"));
             else if (t.getTerminalModeEnum() == TerminalMode.OFF) {
                 _display.popup(Message.destinationIsOff(terminalDestino));
                 if (_receiver.getClientTerminal().isRecieveNotifications()
                     && !t.verificarClienteIDExistente(_receiver.getClientTerminal())) {
                     t.getTentaramNotificar().add(_receiver);
                 }
             }
             else if (_network.getTerminal(terminalDestino).getTerminalModeEnum() == TerminalMode.SILENCE){
                 _display.popup(Message.destinationIsSilent(terminalDestino));
                 if (_receiver.getClientTerminal().isRecieveNotifications()
                     && !t.verificaTerminalNotificar(_receiver)) {
                     t.getTentaramNotificar().add(_receiver);
                 }
             }
             else if (!_network.getTerminal(terminalDestino).canStartCommunication()){
                 _display.popup(Message.destinationIsBusy(terminalDestino));
                 if (_receiver.getClientTerminal().isRecieveNotifications()
                         && !t.verificaTerminalNotificar(_receiver)) {
                     t.getTentaramNotificar().add(_receiver);
                 }
             }

             else {
                 if (Objects.equals(commType, "VIDEO"))
                     _network.makeVideoCall(_receiver, _network.getTerminal(terminalDestino));
                 else
                     _network.makeVoiceCall(_receiver, _network.getTerminal(terminalDestino));
                 }
         } catch (InvalidTerminalIDException e){
             throw new UnknownTerminalKeyException(e.getID());
         }

     }
 }

