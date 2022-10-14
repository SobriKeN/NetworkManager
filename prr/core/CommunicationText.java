package prr.core;

import java.io.Serializable;

public class CommunicationText extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2062487349214690L;

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

    /** @return the cost of the text message */
    @Override
    protected double computeCost(TariffPlan plan) {
        return;
    }

    /** @return the length of the message */
    @Override
    protected int getSize() {
        return message.length();
    }
}
