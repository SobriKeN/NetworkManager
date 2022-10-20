package prr.core;

import java.io.Serializable;

public class CommunicationVoice extends CommunicationInteractive implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -5965358224750296684L;

    /** The type of comm (in this case is voice)  */
    private String type;
    /**
     * Main Construtor
     * @param duration
     */
    public CommunicationVoice(int duration) {
        super(duration);
        this.type = "VOICE";
    }

    /** @return the cost of the voice message **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }
}