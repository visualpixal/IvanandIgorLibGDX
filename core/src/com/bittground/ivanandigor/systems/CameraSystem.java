package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bittground.ivanandigor.World;
import com.bittground.ivanandigor.components.CameraComponent;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.TransformComponent;

/**
 * Created by Cole on 9/25/2015.
 */
public class CameraSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<CameraComponent> cm;
    private ComponentMapper<MovementComponent> mm;

    public CameraSystem() {
        super(Family.all(CameraComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        cm = ComponentMapper.getFor(CameraComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);

    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        CameraComponent cam = cm.get(entity);

        if (cam.target == null) {
            Gdx.app.log("no target","cannot register");
            return;

        }

        TransformComponent targetPos = tm.get(cam.target);
        MovementComponent targetMov = mm.get(cam.target);

        if (targetPos == null) {
            Gdx.app.log("no target position","cannot register");
            return;
        }

        //follow target at a point behind the target in relation to the velocity to certain max amount. I think i can use targetMov.velocity.x to do it.
        cam.camera.position.y = Math.min(Math.max(targetPos.pos.y, cam.camera.viewportHeight / 2), World.WORLD_HEIGHT - cam.camera.viewportHeight / 2);
        cam.camera.position.x = Math.min(Math.max(targetPos.pos.x, cam.camera.viewportWidth/2), World.WORLD_WIDTH - cam.camera.viewportWidth/2);
        /*
        if(targetMov.velocity.x < 0)
            cam.camera.position.x = Math.min(Math.max(targetPos.pos.x - (targetMov.accel.x), cam.camera.viewportWidth/2), World.WORLD_WIDTH - cam.camera.viewportWidth/2);
        else if (targetMov.velocity.x > 0)
            cam.camera.position.x = Math.min(Math.max(targetPos.pos.x + (targetMov.accel.x), cam.camera.viewportWidth/2), World.WORLD_WIDTH - cam.camera.viewportWidth / 2);
            */
    }
}
