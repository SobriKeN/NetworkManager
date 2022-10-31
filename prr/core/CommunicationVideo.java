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
    public CommunicationVideo(int duration, String sender, String receiver) {
        super(duration, sender, receiver);
        this._type = "VIDEO";
    }

    public String getType(){
        return this._type;
    }

    /** @return the cost of the video message **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }

    @Override
    protected String toCommString(){
        String status;
        if (isOngoing()){ status = "ONGOING"; }
        else
            status = "FINISHED";

        return String.join(
                "|",
                getType(),
                String.valueOf(getId()),
                getIdSender(),
                getIdReceiver(),
                String.valueOf(getDuration()),
                String.valueOf(Math.round(getCost())),
                status);

    }
}
