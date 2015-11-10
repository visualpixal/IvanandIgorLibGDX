package com.bittground.ivanandigor;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.bittground.ivanandigor.components.AnimationComponent;
import com.bittground.ivanandigor.components.BackgroundComponent;
import com.bittground.ivanandigor.components.BoundsComponent;
import com.bittground.ivanandigor.components.CameraComponent;
import com.bittground.ivanandigor.components.FrictionComponent;
import com.bittground.ivanandigor.components.GravityComponent;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.PlayerControlComponent;
import com.bittground.ivanandigor.components.RobotComponent;
import com.bittground.ivanandigor.components.StateComponent;
import com.bittground.ivanandigor.components.TextureComponent;
import com.bittground.ivanandigor.components.TransformComponent;
import com.bittground.ivanandigor.systems.RenderingSystem;

public class World {
    private PooledEngine engine;
    public static final float WORLD_WIDTH = 60;
    public static final float WORLD_HEIGHT = 30;
    public Entity ivan;
    public Entity igor;
    public static final Vector2 gravity = new Vector2(0, -60);
    public static final Vector2 friction = new Vector2(24, 0);


    public World (PooledEngine engine) {
        this.engine = engine;
    }

    public void create() {
        ivan = createIvan();
        createCamera(ivan);
        createBackground();
    }

    private void createBackground() {
        Entity backgroundEntity = engine.createEntity();

        BackgroundComponent backgroundBackground = engine.createComponent(BackgroundComponent.class);
        TransformComponent backgroundTransform = engine.createComponent(TransformComponent.class);
        TextureComponent backgroundTexture = engine.createComponent(TextureComponent.class);

        backgroundTexture.textureRegion = IvanAndIgor.atlases.get("robotsfull").findRegion("dark");
        backgroundTexture.height = WORLD_HEIGHT * 32;
        backgroundTexture.width = WORLD_WIDTH *32;

        backgroundEntity.add(backgroundBackground);
        backgroundEntity.add(backgroundTransform);
        backgroundEntity.add(backgroundTexture);

        engine.addEntity(backgroundEntity);
    }


    public Entity getTarget()
    {
        return ivan;
    }

    private Entity createIvan() {
        int width = 32;
        int height = 32;
        Entity ivanEntity = engine.createEntity();
        RobotComponent ivanRobot = engine.createComponent(RobotComponent.class);
        TransformComponent ivanTransform = engine.createComponent(TransformComponent.class);
        AnimationComponent ivanAnimation = engine.createComponent(AnimationComponent.class);
        //MotionControlComponent ivanMotion = engine.createComponent(MotionControlComponent.class);
        FrictionComponent ivanFriction = engine.createComponent(FrictionComponent.class);
        PlayerControlComponent control = engine.createComponent(PlayerControlComponent.class);
        MovementComponent ivanMovement = engine.createComponent(MovementComponent.class);
        GravityComponent ivanGravity = engine.createComponent(GravityComponent.class);
        StateComponent ivanState = engine.createComponent(StateComponent.class);
        TextureComponent ivanTexture = engine.createComponent(TextureComponent.class);
        BoundsComponent ivanBounds = engine.createComponent(BoundsComponent.class);
        TextureRegion ivanTextureRegion = new TextureRegion(IvanAndIgor.atlases.get("robotsfull").findRegion("robotleftidle"));
        ivanAnimation.animations.put(RobotComponent.STATE_STILL, ivanTextureRegion );
        ivanAnimation.animations.put(RobotComponent.STATE_FALL, IvanAndIgor.atlases.get("robotsfull").findRegion("robotrightidle"));
        ivanAnimation.animations.put(RobotComponent.STATE_HIT, IvanAndIgor.atlases.get("robotsfull").findRegion("robotrightidle"));
        ivanAnimation.animations.put(RobotComponent.STATE_JUMP, ivanTextureRegion);

        ivanBounds.bounds.width = RobotComponent.WIDTH;
        ivanBounds.bounds.height = RobotComponent.HEIGHT;

        ivanTransform.pos.set(5.0f, 1.0f, 0.0f);

        ivanState.set(RobotComponent.STATE_JUMP);

        ivanEntity.add(ivanAnimation);
        ivanEntity.add(ivanRobot);
        ivanEntity.add(ivanBounds);
        ivanEntity.add(ivanGravity);
        ivanEntity.add(ivanFriction);
        //ivanEntity.add(ivanMotion);
        ivanEntity.add(ivanMovement);
        ivanEntity.add(control);
        ivanEntity.add(ivanTransform);
        ivanEntity.add(ivanState);
        ivanEntity.add(ivanTexture);

        engine.addEntity(ivanEntity);

        return ivanEntity;
    }


    private void createCamera(Entity target) {
        Entity entity = engine.createEntity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        entity.add(camera);

        engine.addEntity(entity);
    }



}
