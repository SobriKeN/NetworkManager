package prr.core;

import java.io.Serializable;

public abstract class CommunicationInteractive extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 8009448938807110202L;

    /** The duration of the call */
    private final int duration;

    /**
     * Main Construtor
     * @param duration
     */
    public CommunicationInteractive(int duration){
        super();
        this.duration = duration;
    }

    /** @return Communication's duration **/
    public int getDuration() {
        return duration;
    }

    /** @return Communication's Size??? **/
    @Override
    protected int getSize() {
        return duration;
    }
}
