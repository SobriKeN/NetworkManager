package prr.core;

import java.io.Serializable;

public class CommunicationVoice extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 5756840065019584737L;

    /** The type of comm (in this case is video)  */
    private String type;
    /**
     * Main Construtor
     *
     * @param duration
     */
    public CommunicationVoice(int duration) {
        super(duration);
        this.type = "VOICE";
    }

    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }
}