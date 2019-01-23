package com.neb.tvector.actions;

import com.neb.tvector.Player;

public abstract class Action {
    public void execute(Player player){
        System.out.println("Default action performed");
    }
}
