package com.neb.tvector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

    public float friction = 0.1f;
    public float forwardAcceleration = .3f;
    public float brake = 0.4f;
    public float sidewaysAcceleration = 0.03f;
    public float maxSpeed = 2f;

    private float speed;
    private float angleRad;

    public static Texture texture = new Texture(Gdx.files.internal("core/assets/iso_tiles/iso_wireframe_tilted.png"));
    public static Texture texture2 = new Texture(Gdx.files.internal("core/assets/iso_tiles/iso_wireframe.png"));

    public Player(){
        super(texture);
        speed = 0;
        angleRad = 0;
    }
    public void incrementPosition(){
        this.setTexture();
        this.setPosition((float)(this.getX() + speed * Math.cos(angleRad)), (float)(this.getY() + speed * Math.sin(angleRad)));
    }
    public void addFriction(){
        if (speed > friction){
            speed -= friction;
        }
    }
    public void brake(){
        if (speed > brake){
            speed -= brake;
        }
    }
    public void accelerateForward(){
        if (speed < maxSpeed){
            speed += forwardAcceleration;
        }
    }
    public void accelerateLeft(){
        angleRad -= sidewaysAcceleration;
        if (angleRad < 0){
            angleRad = (float)(2 * (3.14) + angleRad);
        }
    }
    public void accelerateRight(){
        angleRad += sidewaysAcceleration;
    }
    private void setTexture(){
        if (angleRad % (3.14 / 2) < (3.14 / 8)){
            this.setTexture(texture);
        }else if (angleRad % (3.14 / 2) < (3.14 / 3)){
            this.setTexture(texture2);
        }else{
            this.setTexture(texture);
        }
    }
}
