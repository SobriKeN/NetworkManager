package prr.core;

import java.io.Serializable;

public class CommunicationVoice extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -5965358224750296684L;

    /** The type of comm (in this case is voice)  */
    private String _type;

    /**
     * Main Construtor
     */
    public CommunicationVoice(Terminal sender, Terminal receiver) {
        super(sender, receiver);
        this._type = "VOICE";
    }

    private String getType(){
        return _type;
    }

    @Override
    public String toCommString(){
        String status;
        int caracteres;
        if (isOnGoing()) {
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