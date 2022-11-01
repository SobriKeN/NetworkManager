package prr.core;

import java.io.Serializable;

public class CommunicationVoice extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -5965358224750296684L;

    /** The type of comm (in this case is voice)  */
    private String _type;
    /**
     * Main Construtor
     * @param duration
     */
    public CommunicationVoice(int duration, Terminal sender, Terminal receiver) {
        super(duration, sender, receiver);
        this._type = "VOICE";
    }

    private String getType(){
        return _type;
    }

    /** @return the cost of the voice message **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return plan.computeCost(this.getSender().getClientTerminal(), this);
    }

    @Override
    protected String toCommString(){
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