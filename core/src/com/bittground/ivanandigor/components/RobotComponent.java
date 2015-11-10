package com.bittground.ivanandigor.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cole on 9/25/2015.
 */
public class RobotComponent implements Component {
    public static final int STATE_STILL = 0;

    public static final int STATE_BOOSTING_LEFT = 6;
    public static final int STATE_BOOSTING_RIGHT = 1;
    public static final int STATE_COASTING = 2;
    public static final int STATE_JUMP = 3;
    public static final int STATE_FALL = 4;
    public static final int STATE_HIT = 5;
    public static final float JUMP_VELOCITY = 11;
    public static final float MOVE_VELOCITY = 20;
    public static final float WIDTH = 0.8f;
    public static final float HEIGHT = 0.8f;

    public float heightSoFar = 0.0f;
}
