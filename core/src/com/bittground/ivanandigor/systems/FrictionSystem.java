package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.bittground.ivanandigor.components.FrictionComponent;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.TransformComponent;

/**
 * Created by Cole on 9/28/2015.
 */
public class FrictionSystem extends IteratingSystem {
    private ComponentMapper<MovementComponent> mm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<FrictionComponent> fm;
    private float previousXPos;


    public FrictionSystem() {
        super(Family.all(FrictionComponent.class, MovementComponent.class, TransformComponent.class).get());

        mm = ComponentMapper.getFor(MovementComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        fm = ComponentMapper.getFor(FrictionComponent.class);

    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MovementComponent mov = mm.get(entity);
        TransformComponent pos = tm.get(entity);
        FrictionComponent friction = fm.get(entity);

        if (pos.pos.y == 0) {
            if (mov.velocity.x == 0) {
                mov.accel.x = 0;
                previousXPos = pos.pos.x;
            }

            if (previousXPos < pos.pos.x && mov.velocity.x == 0.0) {
                mov.velocity.x = 0;
                mov.accel.x = 0;
                previousXPos = pos.pos.x;
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

            //slow down once acceleration slows to zero while moving right
            if (previousXPos < pos.pos.x) {
                mov.accel.x -= 1 / (15 * deltaTime);
                if (mov.velocity.x + mov.accel.x <= 0) {
                    mov.velocity.x = 0;
                    mov.accel.x = 0;
                    //previousXPos = pos.pos.x;
                }
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

            if (previousXPos > pos.pos.x) {
                mov.accel.x += 1 / (15 * deltaTime);
                if (mov.velocity.x + mov.accel.x >= 0) {
                    mov.velocity.x = 0;
                    mov.accel.x = 0;
                    //previousXPos = pos.pos.x;
                }
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

        /*slow down once acceleration slows to zero while moving left
        if (previousXPos > pos.pos.x) {
            mov.accel.x += 1 / (15 * deltaTime);
            Gdx.app.log("velocity", "" + mov.velocity.x);
            Gdx.app.log("accel", "" + mov.accel.x);
        }*/


            previousXPos = pos.pos.x;
        } else {
            if (mov.velocity.x == 0) {
                mov.accel.x = 0;
                previousXPos = pos.pos.x;
            }

            if (previousXPos < pos.pos.x && mov.velocity.x == 0.0) {
                mov.velocity.x = 0;
                mov.accel.x = 0;
                previousXPos = pos.pos.x;
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

            //slow down once acceleration slows to zero while moving right
            if (previousXPos < pos.pos.x) {
                mov.accel.x -= 1 / (60 * deltaTime);
                if (mov.velocity.x + mov.accel.x <= 0) {
                    mov.velocity.x = 0;
                    mov.accel.x = 0;
                    //previousXPos = pos.pos.x;
                }
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

            if (previousXPos > pos.pos.x) {
                mov.accel.x += 1 / (60 * deltaTime);
                if (mov.velocity.x + mov.accel.x >= 0) {
                    mov.velocity.x = 0;
                    mov.accel.x = 0;
                    //previousXPos = pos.pos.x;
                }
                Gdx.app.log("velocity", "" + mov.velocity.x);
                Gdx.app.log("accel", "" + mov.accel.x);
            }

        /*slow down once acceleration slows to zero while moving left
        if (previousXPos > pos.pos.x) {
            mov.accel.x += 1 / (15 * deltaTime);
            Gdx.app.log("velocity", "" + mov.velocity.x);
            Gdx.app.log("accel", "" + mov.accel.x);
        }*/


            previousXPos = pos.pos.x;
        }
    }




}
