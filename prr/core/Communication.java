package prr.core;

import java.io.Serializable;

/**
 * Class representing a communication
 */
public abstract class Communication implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 207648227109582L;

    /** Communication's id, the first comm starts with 1 */
    private int id = 0;

    /** To check if the communication has been paid */
    private boolean isPaid;

    /** Communication's cost*/
    double cost;

    /** To check if the communication is still ocurring */
    boolean isOngoing;

    /**
     * Main Construtor
     */
    public Communication() {
        this.id++;
        this.isPaid = false;
        this.cost = 0;
        this.isOngoing = true;
    }

    /** @return Communication's id **/
    public int getId() { return id; }

    /** @return if the communication has been paid */
    public boolean isPaid() {
        return isPaid;
    }

    /** @return comm's cost */
    public double getCost() {
        return cost;
    }

    /** @return if the comm is still ongoing */
    public boolean isOngoing() {
        return isOngoing;
    }

    /** @return the detais of the communication*/
    @Override
    public String toString() {
        return "Communication{" +
                "id=" + id +
                ", isPaid=" + isPaid +
                ", cost=" + cost +
                ", isOngoing=" + isOngoing +
                '}';
    }

    /** @return how much the comm cost*/

    protected abstract double computeCost(TariffPlan plan);

    /** @return the size of the message*/
    protected abstract int getSize();
}

