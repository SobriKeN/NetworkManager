package prr.core;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class TariffPlan implements Serializable {

    /** Serial number */
    private static final long serialVersionUID = 2140256270744373855L;

    private final String _name;

    public TariffPlan(String name){
        _name = name;
    }

    public String getTariffName(){
        return this._name;
    }

    protected abstract double computeCost(Client c, CommunicationText t);

    protected abstract double computeCost(Client c, CommunicationVoice t);

    protected abstract double computeCost(Client c, CommunicationVideo t);

}
