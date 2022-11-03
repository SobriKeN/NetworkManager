package prr.core;

import java.io.Serializable;

public abstract class CommunicationInteractive extends Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 8009448938807110202L;

    /** The duration of the call */
    private int _duration;

    /** To check if the communication is still ocurring */
    private boolean _isOnGoing;

    /**
     * Main Construtor
     * Same parameters as communication
     */
    public CommunicationInteractive(Terminal sender, Terminal receiver, int id){
        super(sender, receiver, id);
        this._duration = 0;
        this._isOnGoing = true;
    }

    /** @return the detais of the communication*/
    public abstract String toCommString();

    /** @return Communication's duration **/
    @Override
    protected int getSize() {
        return _duration;
    }

    @Override
    protected void setSizeDuration(int size){ this._duration = size; }

    public boolean isOnGoing(){ return _isOnGoing; }

    public void acabaCall(){this._isOnGoing = false;}

    public void setOnGoing(boolean b){ this._isOnGoing = b; }
}
