package com.bittground.ivanandigor.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Cole on 9/25/2015.
 */
public class AnimationComponent implements Component {
    public IntMap<TextureRegion> animations = new IntMap<TextureRegion>();
}
