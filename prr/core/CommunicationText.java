package prr.core;

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
    public CommunicationText(String message, Terminal sender, Terminal receiver) {
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

    /** Never going to be used **/
    @Override
    protected void setSizeDuration(int duration){}

    @Override
    public String toCommString() {
        return String.join(
                "|",
                getType(),
                String.valueOf(getId()),
                getSender().getTerminalId(),
                getReceiver().getTerminalId(),
                String.valueOf(getSize()),
                String.valueOf(Math.round(getCost())),
                "FINISHED");
    }
}
