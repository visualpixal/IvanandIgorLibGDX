package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bittground.ivanandigor.components.BackgroundComponent;
import com.bittground.ivanandigor.components.TransformComponent;

/**
 * Created by Cole on 9/25/2015.
 */
public class BackgroundSystem  extends IteratingSystem {
    private OrthographicCamera camera;
    private ComponentMapper<TransformComponent> tm;

    public BackgroundSystem() {
        super(Family.all(BackgroundComponent.class).get());
        tm = ComponentMapper.getFor(TransformComponent.class);
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);
        t.pos.set(0, 0, 10.0f);
    }
}

