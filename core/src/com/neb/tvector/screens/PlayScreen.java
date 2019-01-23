package com.neb.tvector.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.neb.tvector.Player;
import com.neb.tvector.TVGame;
import com.neb.tvector.actions.ActionDictionary;

public class PlayScreen implements Screen, InputProcessor {
    private TVGame game;
    private TiledMap map;
    private IsometricTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Texture playerTexture;
    public Player player;
    private ActionDictionary actionDictionary;

    public PlayScreen(TVGame game){
        this.game = game;
        camera = new OrthographicCamera(800,480);
        map = new TmxMapLoader().load("core/assets/testmap.tmx");
        renderer = new IsometricTiledMapRenderer(map, game.batch);
        Gdx.input.setInputProcessor(this);
        playerTexture = new Texture(Gdx.files.internal("core/assets/iso_tiles/iso_wireframe.png"));
        player = new Player();
        actionDictionary = new ActionDictionary(player);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        renderer.setView(camera);
        renderer.render();

        actionDictionary.doActions();

        game.batch.begin();
        player.addFriction();
        player.incrementPosition();
        player.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        actionDictionary.addInput(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        actionDictionary.removeInput(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    private Vector2 currentMousePosition = new Vector2();
    private Vector2 lastMousePosition = new Vector2(-1,-1);
    private Vector2 deltaVector = new Vector2();


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        lastMousePosition.set(-1,-1);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        currentMousePosition.set(screenX, screenY);
        if (!(lastMousePosition.x == -1 && lastMousePosition.y == -1)){
            deltaVector = lastMousePosition.sub(currentMousePosition);
            camera.position.add(deltaVector.x, -deltaVector.y, 0);
        }
        lastMousePosition.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
