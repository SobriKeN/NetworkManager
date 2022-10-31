package prr.core;

import java.io.Serial;
import java.util.Comparator;
import java.io.Serializable;
public class DebtsAscendingOrder implements Comparator<Client>, Serializable {

    /** Serial number for serialization **/
    private static final long serialVersionUID = -5666240177742759000L;

    public int compare(Client c1, Client c2){
        return Long.compare(c1.getDebts(), c2.getDebts());
    }
}
