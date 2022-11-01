package prr.core;

import java.io.Serializable;

public class CommunicationVideo extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2838788769932805382L;

    /** The type of comm (in this case is video)  */
    private String _type;
    /**
     * Main Construtor
     * @param duration
     */
    public CommunicationVideo(int duration, Terminal sender, Terminal receiver) {
        super(duration, sender, receiver);
        this._type = "VIDEO";
    }

    public String getType(){
        return this._type;
    }

    /** @return the cost of the video message **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return plan.computeCost(this.getSender().getClientTerminal(),this);
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
