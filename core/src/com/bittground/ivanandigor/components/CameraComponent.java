package com.bittground.ivanandigor.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by Cole on 9/25/2015.
 */
public class CameraComponent implements Component {
    public Entity target;
    public OrthographicCamera camera;
}
