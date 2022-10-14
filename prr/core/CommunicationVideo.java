package prr.core;

import java.io.Serializable;

public class CommunicationVideo extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 205839063863879L;

    /** The type of comm (in this case is video)  */
    private String type;
    /**
     * Main Construtor
     *
     * @param duration
     */
    public CommunicationVideo(int duration) {
        super(duration);
        this.type = "VIDEO";
    }

    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }
}
