package prr.core;

import java.io.Serializable;

public class Notification implements Serializable {

    /**
     * Serial number for serialization.
     */
    private static final long serialVersionUID = 5459416424048243113L;

    /**
     * The notification's type
     */
    private NotificationType tipoNotificacao;

    /**
     * The client associated with the notification
     */
    private Terminal terminalANotificar;

    /**
     * Main Constructor
     * @param tipoNotificacao
     * @param terminalANotificar
     */
    public Notification(NotificationType tipoNotificacao, Terminal terminalANotificar) {
        this.tipoNotificacao = tipoNotificacao;
        this.terminalANotificar = terminalANotificar;
    }

    /**
     @return a String that symbolizes the communication
     */

    public NotificationType getTipoNotificacao() {
        return tipoNotificacao;
    }

    public Terminal getTerminalANotificar() {
        return terminalANotificar;
    }

    public String notificationStringed() {
        return String.join(
                "|",
                getTipoNotificacao().toString(),
                getTerminalANotificar().getTerminalId()
        );
    }
}
