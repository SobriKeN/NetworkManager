package prr.core;

import java.io.Serial;
import java.io.Serializable;

public class CommunicationText extends Communication implements Serializable {

    /**
     * Serial number for serialization.
     */
    private static final long serialVersionUID = 4157849120928096413L;

    /**
     * The text message
     */
    private String _message;

    /**
     * The type of comm (in this case is text)
     */
    private String _type;

    /**
     * Main Construtor
     *
     * @param message
     */
    public CommunicationText(String message, String sender, String receiver) {
        super(sender, receiver);
        this._message = message;
        this._type = "TEXT";
    }

    public String getType() {
        return _type;
    }

    /**
     * @return the length of the message
     */
    @Override
    protected int getSize() {
        return _message.length();
    }

    /**
     * @return the cost of the text message
     **/
    @Override
    protected double computeCost(TariffPlan plan) {
        return 0;
    }

    @Override
    protected String toCommString() {
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
                String.valueOf(getSize()),
                String.valueOf(Math.round(getCost())),
                "FINISHED");
    }
}
