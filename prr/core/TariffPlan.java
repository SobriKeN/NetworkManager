package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class TariffPlan implements Serializable {

    /** Serial number */
    private static final long serialVersionUID = 2140256270744373855L;

    /** specific name of the TariffPlan **/
    private final String _name;

    /**
     * Main Constructor
     * @param name
     */
    public TariffPlan(String name){
        _name = name;
    }

    /** @return the TariffPlan's name **/
    public String getTariffName(){
        return this._name;
    }

    /**
     * The three functions below are responsible for computing the cost
     * of the transactions of the TariffPlan within the client,
     * they will have similar ways of working but different arguments,
     * depending on the Communication type
     **/
    protected abstract double computeCost(Client c, CommunicationText t);

    protected abstract double computeCost(Client c, CommunicationVoice t);

    protected abstract double computeCost(Client c, CommunicationVideo t);

}
