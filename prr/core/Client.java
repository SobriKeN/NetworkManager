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

    /** Number of Terminals that are associated with the client **/
    private int _numeroTerminaisAssociados;

    /** The customer's balance -> Balance = Payments - Debts **/
    private double _pagamentos;  // the payments
    private double _debts;  // the debts

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
        this._numeroTerminaisAssociados = 0;
    }

    /** @return client's key */
    public String getKey() {
        return _key;
    }

    /** @return client's name */
    public String getName() {
        return _name;
    }

    /** @return client's Debts */
    public double getDebts() {return _debts; }

    /** @return client's Payments */
    public double getPagamentos() {return _pagamentos; }

    /** @return client's balance */
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

    /** increments the number of the terminals associated with the client **/
    public void atualizaNumeroTerminaisAssoc(){
        this._numeroTerminaisAssociados++;
    }

    /** void method that turns Off Notifications **/
    public void deactivateNotifications(){
        _recieveNotifications = false;
    }

    /** void method that turns On Notifications **/
    public void activateNotifications(){
        _recieveNotifications = true;
    }

    /**
     Method that returns true if the requirements to the upgrade from Normal to Gold are met,
     and upgrades the Client's level
     **/
    public boolean upgradeNormalToGold(){
        if (this.getLevel() == ClientLevel.NORMAL) {
            if ((_pagamentos - _debts) > 500) { //após realizar um pagamento
                this._level = ClientLevel.GOLD;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    /**
     Method that returns true if the requirements to the upgrade from Gold to Platinum are met,
     and upgrades the Client's level
     **/
    public boolean upgradeGoldToPlatinum(){
        if (this.getLevel() == ClientLevel.GOLD){
            if (((_pagamentos - _debts) > 0)) { //&& numeroCommVideoConsecutivas > 6
                this._level = ClientLevel.PLATINUM;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    /**
     Method that returns true if the requirements to the downgrade from Platinum to Gold are met,
     and downgrades the Client's level
     **/
    public boolean downgradePlatinumToGold(){
        if (this.getLevel() == ClientLevel.PLATINUM){
            if (((_pagamentos - _debts) > 0)) { //&& numeroCommTextoConsecutivas > 3
                this._level = ClientLevel.GOLD;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    /**
     Method that returns true if the requirements to the downgrade from Gold to Normal are met,
     and downgrades the Client's level
     **/
    public boolean downgradeGoldToNormal(){
        if (this.getLevel() == ClientLevel.GOLD){
            if (((_pagamentos - _debts) > 0)) { //dps de realizar uma comm
                this._level = ClientLevel.NORMAL;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }


    /**
     * Client toString
     * @return a client in string format
     */
    public String clientStringed() {
        String receiveNotisString;

        if(_recieveNotifications){receiveNotisString = "YES";}
        else{receiveNotisString = "NO";}

        return String.join(
                "|",
                "CLIENT",
                getKey(),
                getName(),
                Integer.toString(getTax()),
                getLevel().toString(),
                receiveNotisString,
                Integer.toString(_numeroTerminaisAssociados),
                String.valueOf(Math.round(getPagamentos())),
                String.valueOf(Math.round(getDebts()))
                );
    }

}
