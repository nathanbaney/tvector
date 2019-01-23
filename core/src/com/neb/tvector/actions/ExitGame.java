package com.neb.tvector.actions;

import com.badlogic.gdx.Gdx;
import com.neb.tvector.Player;

public class ExitGame extends Action {
    public void execute(Player player){
        Gdx.app.exit();
    }
}
