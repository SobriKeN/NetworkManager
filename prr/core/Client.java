package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class representing a client in the app
 */
public class Client implements Serializable {

    /** Serial number for serialization **/
    private static final long serialVersionUID = -5755265040654157806L;

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
    private long _pagamentos;  // the payments
    private long _debts;  // the debts

    /** Linked list that contains the client's notifications **/
    private ArrayList<Notification> _notificacoes;

    /**
     * Main Construtor
     * @param key
     * @param name
     * @param tax
     */
    public Client(String key, String name, int tax) {
        _key = key;
        _name = name;
        _tax = tax;
        _level = ClientLevel.NORMAL;
        _recieveNotifications = true;
        _debts = 0;
        _pagamentos = 0;
        _numeroTerminaisAssociados = 0;
        _notificacoes = new ArrayList<Notification>();
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
    public long getDebts() {return _debts; }

    public void adicionaDebts(long cost){this._debts += cost;}

    public void paga(long cost){
        this._debts -= cost;
        this._pagamentos += cost;
    }

    /** @return client's Payments */
    public long getPagamentos() {return _pagamentos; }

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

    /** @return terminal's Notifications LinkedList **/
    public ArrayList<Notification> getNotificacoesClient(){ return _notificacoes;}

    /**
     Method that changes the client's level if the requirements to the upgrade from Normal to Gold are met,
     and upgrades the Client's level
     **/
    public void upgradeNormalToGold(){
        if ((this._pagamentos - this._debts) > 500) { //após realizar um pagamento
            this._level = ClientLevel.GOLD;
        }
    }

    /**
     Method that returns true if the requirements to the upgrade from Gold to Platinum are met,
     and upgrades the Client's level
     **/
    public void upgradeGoldToPlatinum(ArrayList<Communication> commsClient){
        int numeroCommVideoConsecutivas = 0;

        for (Communication comm : commsClient){
            if (comm instanceof CommunicationVideo)
                numeroCommVideoConsecutivas++;
            else
                numeroCommVideoConsecutivas = 0;

            if (((this._pagamentos - this._debts) > 0)  && (numeroCommVideoConsecutivas >= 5)) {
                this._level = ClientLevel.PLATINUM;
                break;
            }
        }
    }

    /**
     Method that returns true if the requirements to the downgrade from Platinum to Gold are met,
     and downgrades the Client's level
     **/
    public void downgradePlatinumToGold(ArrayList<Communication> commsClient){
        int numeroCommTextoConsecutivas = 0;

        for (Communication comm : commsClient) {
            if (comm instanceof CommunicationText)
                numeroCommTextoConsecutivas++;
            else
                numeroCommTextoConsecutivas = 0;

            if ((_pagamentos - _debts) > 0 && (numeroCommTextoConsecutivas >= 2)) {
                this._level = ClientLevel.GOLD;
                break;
            }
        }
    }

    /**
     Method that changes the client's level if the requirements to the downgrade from Platinum to Normal are met,
     and downgrades the Client's level
     **/

     public void downgradePlatinumToNormal(){
         if (((_pagamentos - _debts) < 0)) { //dps de realizar uma comm
             this._level = ClientLevel.NORMAL;
         }
     }

    /**
     Method that changes the client's level if the requirements to the downgrade from Gold to Normal are met,
     and downgrades the Client's level
     **/
    public void downgradeGoldToNormal(){
        if (((_pagamentos - _debts) < 0)) { //dps de realizar uma comm
            this._level = ClientLevel.NORMAL;
        }
    }

    /** Clear the client's unread notifications array */
    public void clearNotifications() {
        _notificacoes.clear();
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
