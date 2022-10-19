package prr.core;

import java.io.Serial;
import java.io.Serializable;

public class CommunicationText extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 4157849120928096413L;

    /** The text message */
    private String message;

    /** The type of comm (in this case is text)  */
    private String type;

    /**
     * Main Construtor
     * @param message
     */
    public CommunicationText(String message) {
        super();
        this.message = message;
        this.type = "TEXT";
    }

    /** @return the length of the message */
    @Override
    protected int getSize() {
        return message.length();
    }

    /** @return the cost of the text message **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }



}
