package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.bittground.ivanandigor.World;
import com.bittground.ivanandigor.components.MovementComponent;
import com.bittground.ivanandigor.components.RobotComponent;
import com.bittground.ivanandigor.components.StateComponent;
import com.bittground.ivanandigor.components.TransformComponent;

/**
 * Created by Cole on 9/25/2015.
 */
public class RobotSystem extends IteratingSystem {

    private static BitmapFont bFont = new BitmapFont();
    private static final Family family = Family.all(RobotComponent.class,
            StateComponent.class,
            TransformComponent.class,
            MovementComponent.class).get();

    private float accelX = 0.0f;
    private World world;
    private Vector3 position;


    private ComponentMapper<RobotComponent> rm;
    private ComponentMapper<StateComponent> sm;
    private ComponentMapper<TransformComponent> tm;
    private ComponentMapper<MovementComponent> mm;

    public RobotSystem(World world)
    {
        super(family);

        this.world = world;

        rm = ComponentMapper.getFor(RobotComponent.class);
        sm = ComponentMapper.getFor(StateComponent.class);
        tm = ComponentMapper.getFor(TransformComponent.class);
        mm = ComponentMapper.getFor(MovementComponent.class);
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

    }

    private void setPosition(Vector3 position){
        this.position = position;
    }

    public Vector3 getPosition(){
        return position;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);
        StateComponent state = sm.get(entity);
        MovementComponent mov = mm.get(entity);
        RobotComponent ivan = rm.get(entity);

        setPosition(t.pos);
        //Stops robot from going outside the world bounds
        if(t.pos.y <= 0){
            t.pos.y = 0;
            mov.velocity.y = 0;
        }

        if(t.pos.y >= World.WORLD_HEIGHT){
            t.pos.y =World.WORLD_HEIGHT;
            mov.accel.y = 0;
            mov.velocity.y = 0;
            mov.accel.x = 0;
            mov.velocity.x = 0;
        }
        if (t.pos.x < 0) {
            t.pos.x = 0;
        }

        if (t.pos.x >= World.WORLD_WIDTH) {
            t.pos.x = World.WORLD_WIDTH;
        }


    }

}
