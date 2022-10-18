package prr.core;

import java.io.Serializable;

/**
 * Class representing a client in the app
 */
public class Client implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 2084398098986L;

    /** Client's key */
    private String _key;

    /** Client's name */
    private String _name;

    /** Client's tax number */
    private int _tax;

    /** Client's level */
    private ClientLevel _level;

    /** To check if the client can receive notifications */
    private boolean _recieveNotifications;

    /** The customer's balance*/
    private double _pagamentos;
    private double _debts;

    /**
     * Main Construtor
     * @param key
     * @param name
     * @param tax
     */
    public Client(String key, String name, int tax) {
        this._key = key;
        this._name = name;
        this._tax = tax;
        this._level = ClientLevel.NORMAL;
        this._recieveNotifications = true;
        this._debts = 0;
        this._pagamentos = 0;
    }

    /** @return client's key */
    public String getKey() {
        return _key;
    }

    /** @return client's name */
    public String getName() {
        return _name;
    }

    /** @return client's balance */
    public double getDebts() {return _debts; }
    public double getPagamentos() {return _pagamentos; }
    public double getSaldo(){return (_pagamentos - _debts); }

    /** @return client's tax number */
    public int getTax() {
        return _tax;
    }

    /** @return client's level */
    public ClientLevel getLevel() {
        return _level;
    }

    /** @return if the client can receive notifications */
    public boolean isRecieveNotifications() {
        return _recieveNotifications;
    }

    public void upgradeNormalToGold(){this._level = ClientLevel.GOLD;}

    public void upgradeGoldToPlatinum(){
        if(this.getLevel() == ClientLevel.GOLD){
        }
    }


    /**
     * Special toString
     * @param id
     * @return a partner in string format
     */
    public String clientStringed(String id) {
        String receiveNotisString;
        int terminalsClient;

        if(_recieveNotifications){receiveNotisString = "YES";}
        else{receiveNotisString = "NO";}

        if ()

        return String.join(
                "|",
                "CLIENT",
                getKey(),
                getName(),
                getTax(),
                getLevel().toString(),
                receiveNotisString,
                String.valueOf(Math.round(getNumeroTerminais()),
                String.valueOf(Math.round(getPagamentos()),
                String.valueOf(Math.round(getDebts()));
    }

}
