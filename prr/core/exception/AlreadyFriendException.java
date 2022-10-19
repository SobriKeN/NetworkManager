package prr.core.exception;

public class AlreadyFriendException extends Exception{

    private static final long serialVersionUID = -3556624929392966400L;

    public AlreadyFriendException() {
        super("Já existe este amigo na lista de amigos do terminal");
    }

}
