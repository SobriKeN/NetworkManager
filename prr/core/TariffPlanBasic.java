package prr.core;

import java.util.Objects;

public class TariffPlanBasic extends TariffPlan{
    public TariffPlanBasic(String name){
        super(name);
    }

    @Override
    protected double computeCost(Client c, CommunicationText t){
        int custo = 0;

        if (Objects.equals(c.get_level().toString(), "NORMAL")) {
            if (t.getSize() < 50)
                custo = 10;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 16;
            else
                custo = 2 * t.getSize();
        }
        if (Objects.equals(c.get_level().toString(), "GOLD")) {
            if (t.getSize() < 50)
                custo = 10;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 10;
            else
                custo = 2 * t.getSize();
        }
        if (Objects.equals(c.get_level().toString(), "PLATINUM")) {
            if (t.getSize() < 50)
                custo = 0;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 4;
            else
                custo = 4;
        }
        return custo;
    }

    @Override
    protected double computeCost(Client c, CommunicationVoice t){
        int custo = 0;

        if (Objects.equals(c.get_level().toString(), "NORMAL"))
            custo = 20 * (t.getDuration() / 60);
        else if (Objects.equals(c.get_level().toString(), "GOLD"))
            custo = 10 * (t.getDuration() / 60);
        else if (Objects.equals(c.get_level().toString(), "PLATINUM"))
            custo = 10 * (t.getDuration() / 60);
        return custo;
    }

    @Override
    protected double computeCost(Client c, CommunicationVideo t){
        int custo = 0;

        if (Objects.equals(c.get_level().toString(), "NORMAL"))
            custo = 30 * (t.getDuration() / 60);
        else if (Objects.equals(c.get_level().toString(), "GOLD"))
            custo = 20 * (t.getDuration() / 60);
        else if (Objects.equals(c.get_level().toString(), "PLATINUM"))
            custo = 10 * (t.getDuration() / 60);
        return custo;
    }

}
