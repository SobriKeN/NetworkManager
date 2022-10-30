package prr.core;

import java.util.Comparator;

public class DebtsAscendingOrder implements Comparator<Client> {
    public int compare(Client c1, Client c2){
        return Long.compare(c1.getDebts(), c2.getDebts());
    }
}
