package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bittground.ivanandigor.components.MotionControlComponent;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.RobotComponent;
import com.bittground.ivanandigor.components.StateComponent;

/**
 * Created by Cole on 9/26/2015.
 */
public class MotionControlSystem extends IteratingSystem {
    private ComponentMapper<MotionControlComponent> mcm;
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<StateComponent> sm;

    public MotionControlSystem() {
        super(Family.all(MotionControlComponent.class, MovementComponent.class, StateComponent.class).get());
        mcm = ComponentMapper.getFor(MotionControlComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {

        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        float accelX = 0.0f;
        float accelY = 0.0f;
        accelX = Gdx.input.getAccelerometerX();
        accelY = Gdx.input.getAccelerometerY();
        MotionControlComponent motionm = mcm.get(entity);
        MovementComponent mov = mm.get(entity);
        StateComponent state = sm.get(entity);
        motionm.accel.set(accelX, accelY);

        //allows motion of phone to control robot velocity in x and y directions
        if (state.get() != RobotComponent.STATE_HIT) {
            mov.velocity.x = -motionm.accel.x / 10.0f * RobotComponent.MOVE_VELOCITY;
            mov.velocity.y = motionm.accel.y / 10.0f * RobotComponent.MOVE_VELOCITY;
        }


    }

}
