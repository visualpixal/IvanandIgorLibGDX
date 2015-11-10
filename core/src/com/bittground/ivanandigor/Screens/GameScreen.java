package com.bittground.ivanandigor.Screens;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.bittground.ivanandigor.IvanAndIgor;
import com.bittground.ivanandigor.World;
import com.bittground.ivanandigor.systems.AnimationSystem;
import com.bittground.ivanandigor.systems.BackgroundSystem;
import com.bittground.ivanandigor.systems.CameraSystem;
import com.bittground.ivanandigor.systems.FrictionSystem;
import com.bittground.ivanandigor.systems.GravitySystem;
import com.bittground.ivanandigor.systems.MovementSystem;
import com.bittground.ivanandigor.systems.PlayerControlSystem;
import com.bittground.ivanandigor.systems.RenderingSystem;
import com.bittground.ivanandigor.systems.RobotSystem;
import com.bittground.ivanandigor.systems.StateSystem;

/**
 * Created by Cole on 9/25/2015.
 */
public class GameScreen extends ScreenAdapter{



    IvanAndIgor game;

    private BitmapFont bfont = new BitmapFont();
    OrthographicCamera guiCam;
    Vector3 touchPoint;
    World world;
    PooledEngine engine;
    Rectangle controlBoxBounds;
    public static final float WIDTH = 480;
    public static final float HEIGHT = 800;

    public GameScreen(IvanAndIgor game)
    {
        this.game = game;

        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, WIDTH, HEIGHT);
        touchPoint = new Vector3();
        controlBoxBounds = new Rectangle(0, 0,WIDTH, HEIGHT/5);

        engine = new PooledEngine();

        world = new World(engine);

        //engine.addSystem(new MotionControlSystem());
        engine.addSystem(new GravitySystem());
        engine.addSystem(new BackgroundSystem());
        engine.addSystem(new StateSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderingSystem(game.batch));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new FrictionSystem());
        engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new RobotSystem(world));
        engine.addSystem(new CameraSystem());


        engine.getSystem(BackgroundSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());


        world.create();



        //engine.getSystem(RenderingSystem.class).setTarget(world.getTarget());




    }

    public void drawUI () {
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.begin();
        engine.getSystem(RobotSystem.class).getPosition();
        CharSequence position = engine.getSystem(RobotSystem.class).getPosition().toString();
        bfont.setColor(1, 1, 1, 1);
        bfont.draw(game.batch, position, 10, 20);
        game.batch.draw(IvanAndIgor.atlases.get("robotsfull").findRegion("robotrightidle"), 0, 0);
        game.batch.draw(IvanAndIgor.atlases.get("robotsfull").findRegion("light"), 0, HEIGHT / 4, WIDTH, 4);
        game.batch.draw(IvanAndIgor.atlases.get("robotsfull").findRegion("robotleftidle"), 380, 0);
        game.batch.end();
    }



    public void update (float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;

        engine.update(deltaTime);
        handleInput();

    }

    private void handleInput() {
        Vector2 movement = new Vector2(0, 0);

        for (int i = 0; i < 2; i++) {
            if (Gdx.input.isTouched(i)) {
                touchPoint.x = Gdx.input.getX(i);
                touchPoint.y = Gdx.input.getY(i);
                guiCam.unproject(touchPoint);
                Gdx.app.log("TouchPoint X", "" + touchPoint.x);
                Gdx.app.log("TouchPoint Y", "" + touchPoint.y);

                if (controlBoxBounds.contains(touchPoint.x, touchPoint.y)) {
                    //movement.set(-(WIDTH/2 - touchPoint.x),0);

                    movement.set(-(WIDTH / 2 - touchPoint.x), -(HEIGHT / 10 - touchPoint.y));
                    Gdx.app.log("Accel X", "" + movement.x);
                    Gdx.app.log("Accel Y", "" + movement.y);
                    engine.getSystem(PlayerControlSystem.class).setMovement(movement);
                }
            }

        }
    }


    @Override
    public void render(float delta) {
        update(delta);
        drawUI();

    }
}