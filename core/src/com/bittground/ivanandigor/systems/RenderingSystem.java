package com.bittground.ivanandigor.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.bittground.ivanandigor.components.TextureComponent;
import com.bittground.ivanandigor.components.TransformComponent;

import java.util.Comparator;

/**
 * Created by Cole on 9/25/2015.
 */
public class RenderingSystem extends IteratingSystem {

    static final float CAMERA_WIDTH = 30;
    static final float CAMERA_HEIGHT = 45;
    static final float PIXELS_TO_METRES = 1.0f / 32.0f;

    private SpriteBatch batch;
    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;
    private OrthographicCamera cam;

    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<TransformComponent> transformM;

    public RenderingSystem(SpriteBatch batch) {
        super(Family.all(TransformComponent.class, TextureComponent.class).get());
        textureM = ComponentMapper.getFor(TextureComponent.class);
        transformM = ComponentMapper.getFor(TransformComponent.class);

        renderQueue = new Array<Entity>();

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(transformM.get(entityB).pos.z -
                        transformM.get(entityA).pos.z);
            }
        };

        this.batch = batch;

        cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.position.set(0, 0, 0);
    }



    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        renderQueue.sort(comparator);

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        for (Entity entity : renderQueue) {
            TextureComponent tex = textureM.get(entity);

            if (tex.textureRegion == null) {
                continue;
            }

            TransformComponent t = transformM.get(entity);

            float width = tex.textureRegion.getRegionWidth();
            float height = tex.textureRegion.getRegionHeight();
            float originX = width * 0.5f;
            float originY = height * 0.5f;

            if(tex.width != 0 && tex.height != 0) {
                batch.draw(tex.textureRegion,
                        t.pos.x - originX, t.pos.y - originY,
                        originX, originY,
                        tex.width, tex.height,
                        t.scale.x * PIXELS_TO_METRES, t.scale.y * PIXELS_TO_METRES,
                        MathUtils.radiansToDegrees * t.rotation);
            }
            //If width or height of the region isn't set manually when it is added to the entity, it will use the actual size of the texture image.
            else{
                batch.draw(tex.textureRegion,
                        t.pos.x - originX, t.pos.y - originY,
                        originX, originY,
                        width, height,
                        t.scale.x * PIXELS_TO_METRES, t.scale.y * PIXELS_TO_METRES,
                        MathUtils.radiansToDegrees * t.rotation);

            }
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera() {
        return cam;
    }
}

