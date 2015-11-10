package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.PlayerControlComponent;
import com.bittground.ivanandigor.components.StateComponent;

/**
 * Created by Cole on 9/30/2015.
 */
public class PlayerControlSystem extends IteratingSystem {

    private Vector2 movement = new Vector2();

    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<PlayerControlComponent> pcm;

    public PlayerControlSystem(){
        super(Family.all(MovementComponent.class,StateComponent.class,PlayerControlComponent.class).get());

        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        pcm = ComponentMapper.getFor(PlayerControlComponent.class);

    }

    public void setMovement(Vector2 movement){
        this.movement = movement;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        movement.x = 0;
        movement.y = 0;

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent mov = mm.get(entity);
        StateComponent state = sm.get(entity);
        PlayerControlComponent control = pcm.get(entity);
        if(mov.accel.x < 5 && mov.accel.x > -10)
            mov.accel.x += movement.x/5;
        if(mov.accel.y < 10 && movement.y > 0)
            mov.accel.y = Math.min (10, mov.accel.y + movement.y/2);
        //movement.x = 0;
        //movement.y = 0;
    }


}
