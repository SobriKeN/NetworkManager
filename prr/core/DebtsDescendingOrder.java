package prr.core;

import java.io.Serializable;
import java.util.Comparator;

public class DebtsDescendingOrder implements Comparator<Client>, Serializable {

    /** Serial number for serialization **/
    private static final long serialVersionUID = -6220091747558175800L;

    @Override
    public int compare(Client c1, Client c2){
        return Long.compare(c2.getDebts(), c1.getDebts());
    }
}
