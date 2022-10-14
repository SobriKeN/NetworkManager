package prr.core;

import java.io.Serializable;

public abstract class CommunicationInteractive extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 20782564287297L;

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

    @Override
    protected int getSize() {
        return duration;
    }
}
