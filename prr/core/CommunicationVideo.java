package prr.core;

import java.io.Serializable;

public class CommunicationVideo extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2838788769932805382L;

    /** The type of comm (in this case is video)  */
    private String _type;
    /**
     * Main Construtor
     */
    public CommunicationVideo(Terminal sender, Terminal receiver) {
        super(sender, receiver);
        this._type = "VIDEO";
    }

    public String getType(){
        return this._type;
    }

    @Override
    public String toCommString(){
        String status;
        int caracteres;
        if (isOngoing()) {
            status = "ONGOING";
            caracteres = 0;
        }
        else {
            status = "FINISHED";
            caracteres = this.getSize();
        }

        return String.join(
                "|",
                getType(),
                String.valueOf(getId()),
                getIdSender(),
                getIdReceiver(),
                String.valueOf(caracteres),
                String.valueOf(Math.round(getCost())),
                status);

    }
}
