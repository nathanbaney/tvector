package com.neb.tvector.actions;

import com.neb.tvector.Player;

public class Brake extends Action {
    public void execute(Player player){
        player.brake();
    }
}
