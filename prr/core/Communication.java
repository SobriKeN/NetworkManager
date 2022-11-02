package prr.core;

import java.io.Serializable;

/**
 * Class representing a communication
 */
public abstract class Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = -809010677570144248L;

    /** Communication's id, the first comm starts with 1 */
    private int _id = 0;

    /** To check if the communication has been paid */
    private boolean _isPaid;

    /** The terminal that will receive the comm */
    private Terminal _receiver;

    /** The terminal that will send the comm */
    private Terminal _sender;

    /** Communication's cost*/
    private double _cost;

    /** To check if the communication is still ocurring */
    private boolean _isOngoing;

    /**
     * Main Construtor
     */
    public Communication(Terminal sender, Terminal receiver) {
        this._id++;
        this._isPaid = false;
        this._cost = 0;
        this._isOngoing = true;
        this._receiver = receiver;
        this._sender = sender;
    }

    /** @return Communication's id **/
    public int getId() { return _id; }

    public Terminal getReceiver() {
        return _receiver;
    }

    public Terminal getSender() {
        return _sender;
    }

    public String getIdReceiver(){
        return _receiver.getTerminalId();
    }

    public String getIdSender(){
        return _receiver.getTerminalId();
    }

    /** @return if the communication has been paid */
    public boolean isPaid() {
        return _isPaid;
    }

    /** @return comm's cost */
    public double getCost() {
        return _cost;
    }

    /** @return if the comm is still ongoing */
    public boolean isOngoing() {
        return _isOngoing;
    }

    /** @return the detais of the communication*/
    public abstract String toCommString();

    /** @return the size of the message*/
    protected abstract int getSize();
}

