package prr.core;

import java.util.Objects;

public abstract class TariffPlan {
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
