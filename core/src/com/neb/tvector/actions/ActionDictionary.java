package com.neb.tvector.actions;

import com.badlogic.gdx.Input;
import com.neb.tvector.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ActionDictionary {
    private Map<Integer, Action> actionMap;
    private Set<Integer> activeKeycodes;
    private Player player;

    public ActionDictionary(Player player){
        this.player = player;
        actionMap = new HashMap<Integer, Action>();
        activeKeycodes = new HashSet<Integer>();
        initialize();
    }
    private void initialize(){
        actionMap.put(Input.Keys.W, new AccelerateForward());
        actionMap.put(Input.Keys.S, new Brake());
        actionMap.put(Input.Keys.A, new AccelerateRight());
        actionMap.put(Input.Keys.D, new AccelerateLeft());
        actionMap.put(Input.Keys.ESCAPE, new ExitGame());
    }
    public void doActions(){
        for (int keyCode : activeKeycodes){
            doAction(keyCode);
        }
    }
    public void doAction(int keyCode){
        if (actionMap.containsKey(keyCode)){
            actionMap.get(keyCode).execute(player);
        }
    }
    public void addInput(int keyCode){
        activeKeycodes.add(keyCode);
    }
    public void removeInput(int keyCode){
        activeKeycodes.remove(keyCode);
    }
}
