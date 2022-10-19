package prr.core;

import java.io.Serializable;
import java.util.ArrayList;

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
    private int _numeroTerminaisAssociados;

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

    public void atualizaNumeroTerminaisAssoc(){
        this._numeroTerminaisAssociados++;

    }
    /** @return if the client can receive notifications */
    public boolean isRecieveNotifications() {
        return _recieveNotifications;
    }

    public void deactivateNotifications(){
        _recieveNotifications = false;
    }

    public void activateNotifications(){
        _recieveNotifications = true;
    }

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
     * Special toString
     * @return a partner in string format
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
