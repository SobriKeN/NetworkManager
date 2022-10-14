package prr.core;

import java.util.ArrayList;
import java.util.List;

public class TerminalBasic extends Terminal{

    public TerminalBasic(String id) {
        super(id);
    }

    @Override
    public void makeVoiceCall(Terminal t){
    }

    @Override
    protected void acceptVoiceCall(Terminal t){
    }

    @Override
    public void makeSMS(Terminal t, String msg){
    }

    @Override
    protected void acceptSMS(Terminal t){
    }

}
