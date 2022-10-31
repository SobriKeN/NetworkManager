package prr.core;

import java.util.Objects;

public class TariffPlanBasic extends TariffPlan{

    /** Serial number */
    private static final long serialVersionUID = 5230596851456277672L;

    public TariffPlanBasic(String name){
        super(name);
    }

    /**
     * @return the Cost of the TariffPlan decided by some conditions and rules set by the
     * Client's status and balance; this method is only for Text Comms
     * @param Client c
     * @param CommunicationText t
     */
    @Override
    protected double computeCost(Client c, CommunicationText t){
        int custo = 0;

        if (Objects.equals(c.getLevel().toString(), "NORMAL")) {
            if (t.getSize() < 50)
                custo = 10;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 16;
            else
                custo = 2 * t.getSize();
        }
        if (Objects.equals(c.getLevel().toString(), "GOLD")) {
            if (t.getSize() < 50)
                custo = 10;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 10;
            else
                custo = 2 * t.getSize();
        }
        if (Objects.equals(c.getLevel().toString(), "PLATINUM")) {
            if (t.getSize() < 50)
                custo = 0;
            else if (50 <= t.getSize() && t.getSize() < 100)
                custo = 4;
            else
                custo = 4;
        }
        return custo;
    }

    /**
     * @return the Cost of the TariffPlan decided by some conditions and rules set by the
     * Client's status and balance; this method is only for Voice Comms
     * @param Client c
     * @param CommunicationVoice t
     */
    @Override
    protected double computeCost(Client c, CommunicationVoice t){
        int custo = 0;

        if (Objects.equals(c.getLevel().toString(), "NORMAL"))
            custo = 20 * t.getSize();
        else if (Objects.equals(c.getLevel().toString(), "GOLD"))
            custo = 10 * t.getSize();
        else if (Objects.equals(c.getLevel().toString(), "PLATINUM"))
            custo = 10 * t.getSize();
        return custo;
    }

    /**
     * @return the Cost of the TariffPlan decided by some conditions and rules set by the
     * Client's status and balance; this method is only for Video Comms
     * @param Client c
     * @param CommunicationVideo t
     */
    @Override
    protected double computeCost(Client c, CommunicationVideo t){
        int custo = 0;

        if (Objects.equals(c.getLevel().toString(), "NORMAL"))
            custo = 30 * t.getSize();
        else if (Objects.equals(c.getLevel().toString(), "GOLD"))
            custo = 20 * t.getSize();
        else if (Objects.equals(c.getLevel().toString(), "PLATINUM"))
            custo = 10 * t.getSize();
        return custo;
    }

}
