package com.bittground.ivanandigor.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Cole on 9/30/2015.
 */
public class FrictionComponent implements Component {
    private boolean kineticFriction = true;

    public boolean setFriction(boolean b){
        return kineticFriction = b;
    }


}
