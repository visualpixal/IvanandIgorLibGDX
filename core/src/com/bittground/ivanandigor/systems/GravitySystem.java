package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.bittground.ivanandigor.World;
import com.bittground.ivanandigor.components.GravityComponent;
import com.bittground.ivanandigor.components.MovementComponent;

/**
 * Created by Cole on 9/25/2015.
 */
public class GravitySystem extends IteratingSystem {
    private ComponentMapper<MovementComponent> mm;
    private float airTime;

    public GravitySystem() {
        super(Family.all(GravityComponent.class, MovementComponent.class).get());

        mm = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MovementComponent mov = mm.get(entity);
        mov.accel.add(World.gravity.x * deltaTime, World.gravity.y * deltaTime);
    }
}

