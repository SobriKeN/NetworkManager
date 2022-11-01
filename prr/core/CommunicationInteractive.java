package prr.core;

import java.io.Serializable;

public abstract class CommunicationInteractive extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 8009448938807110202L;

    /** The duration of the call */
    private final int _duration;

    /**
     * Main Construtor
     * @param duration
     */
    public CommunicationInteractive(int duration, Terminal sender, Terminal receiver){
        super(sender, receiver);
        this._duration = duration;
    }

    /** @return the detais of the communication*/
    public abstract String toCommString();

    /** @return Communication's duration **/
    @Override
    protected int getSize() {
        return _duration;
    }
}
