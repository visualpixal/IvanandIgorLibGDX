package com.bittground.ivanandigor.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cole on 9/25/2015.
 */
public class MovementComponent implements Component {
    public final Vector2 velocity = new Vector2();
    public final Vector2 accel = new Vector2();
}
