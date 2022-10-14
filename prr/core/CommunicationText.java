package prr.core;

import java.io.Serializable;

public class CommunicationText extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2062487349214690L;

    /** Communication's id, the first comm starts with 1 */
    private int id = 0;

    /** To check if the communication has been paid */
    private boolean isPaid;

    /** Communication's cost*/
    double cost;

    /** To check if the communication is still ocurring */
    boolean isOngoing;

    /** The text message */
    private String message;

    /**
     * Main Construtor
     * @param id
     * @param isPaid
     * @param cost
     * @param isOngoing
     * @param message
     */
    public CommunicationText(int id, boolean isPaid, double cost, boolean isOngoing, String message) {
        super(isPaid,cost,isOngoing);
        this.id++;
        this.message = message;
    }

    /** @return the cost of the text message */
    @Override
    protected double computeCost(TariffPlan plan) {

    }

    /** @return the length of the message */
    @Override
    protected int getSize() {
        return message.length();
    }
}
