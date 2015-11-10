package com.bittground.ivanandigor;

import com.badlogic.gdx.Gdx;

/**
 * Created by Cole on 9/24/2015.
 */
public class Time {
    public static double time = 1.0d;
    private static int defaultFPS = 60;



    public static void update()
    {
        int actualFPS = Gdx.graphics.getFramesPerSecond();
        actualFPS = (actualFPS ==0) ? 3000 : actualFPS;
        time = (double) defaultFPS / actualFPS;
    }

}
