package prr.core.exception;

public class AlreadyFriendException extends Exception{

    public AlreadyFriendException() {
        super("Já existe este amigo na lista de amigos do terminal");
    }

}
