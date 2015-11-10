package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.StateComponent;
import com.bittground.ivanandigor.components.TransformComponent;

/**
 * Created by Cole on 9/25/2015.
 */
public class MovementSystem extends IteratingSystem {
    private Vector2 tmp = new Vector2();

    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class, StateComponent.class).get());

        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent pos = tm.get(entity);
        MovementComponent mov = mm.get(entity);
        StateComponent state = sm.get(entity);
        //adds acceleration to velocity
        tmp.set(mov.accel).scl(deltaTime);
        mov.velocity.add(tmp);

        Gdx.app.log("Velocity", "" + mov.velocity.x);

        //adds the new velocity to the position
        tmp.set(mov.velocity).scl(deltaTime);
        pos.pos.add(tmp.x, tmp.y, 0.0f);
    }
}
