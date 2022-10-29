package prr.core;

import java.util.Comparator;

public class DebtsDescendingOrder implements Comparator<Client> {
    @Override
    public int compare(Client c1, Client c2){
        return Long.compare(c2.getDebts(), c1.getDebts());
    }
}
