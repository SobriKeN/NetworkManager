package prr.core;

import java.io.Serializable;

public class Notification implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2037563876583L;

    /** The notification's type*/
    private NotificationType tipoNotificacao;

    /** The client associated with the notification */
    private Terminal terminalANotificar;

    public Notification(NotificationType tipoNotificacao, Terminal terminalANotificar){
        this.tipoNotificacao = tipoNotificacao;
        this.terminalANotificar = terminalANotificar;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "tipoNotificacao=" + tipoNotificacao +
                '}';
    }
}
