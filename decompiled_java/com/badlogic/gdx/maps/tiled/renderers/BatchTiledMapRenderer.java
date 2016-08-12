// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.maps.tiled.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import java.util.Iterator;

public abstract class BatchTiledMapRenderer implements TiledMapRenderer, Disposable {
    protected TiledMap map;
    protected boolean ownsSpriteBatch;
    protected Batch spriteBatch;
    protected float unitScale;
    protected Rectangle viewBounds;

    public BatchTiledMapRenderer(TiledMap map) {
        this(map, 1f);
    }

    public BatchTiledMapRenderer(TiledMap map, float unitScale) {
        super();
        this.map = map;
        this.unitScale = unitScale;
        this.viewBounds = new Rectangle();
        this.spriteBatch = new SpriteBatch();
        this.ownsSpriteBatch = true;
    }

    public BatchTiledMapRenderer(TiledMap map, float unitScale, Batch batch) {
        super();
        this.map = map;
        this.unitScale = unitScale;
        this.viewBounds = new Rectangle();
        this.spriteBatch = batch;
        this.ownsSpriteBatch = false;
    }

    public BatchTiledMapRenderer(TiledMap map, Batch batch) {
        this(map, 1f, batch);
    }

    protected void beginRender() {
        AnimatedTiledMapTile.updateAnimationBaseTime();
        this.spriteBatch.begin();
    }

    public void dispose() {
        if(this.ownsSpriteBatch) {
            this.spriteBatch.dispose();
        }
    }

    protected void endRender() {
        this.spriteBatch.end();
    }

    public TiledMap getMap() {
        return this.map;
    }

    public Batch getSpriteBatch() {
        return this.spriteBatch;
    }

    public float getUnitScale() {
        return this.unitScale;
    }

    public Rectangle getViewBounds() {
        return this.viewBounds;
    }

    public void render() {
        Object v2;
        this.beginRender();
        Iterator v0 = this.map.getLayers().iterator();
        while(true) {
        label_4:
            if(!v0.hasNext()) {
                goto label_20;
            }

            v2 = v0.next();
            if(!((MapLayer)v2).isVisible()) {
                continue;
            }

            if(!(v2 instanceof TiledMapTileLayer)) {
                break;
            }

            this.renderTileLayer(((TiledMapTileLayer)v2));
        }

        Iterator v1 = ((MapLayer)v2).getObjects().iterator();
        goto label_15;
    label_20:
        this.endRender();
        return;
        while(true) {
        label_15:
            if(!v1.hasNext()) {
                goto label_4;
            }

            this.renderObject(v1.next());
        }
    }

    public void render(int[] layers) {
        this.beginRender();
        int[] v0 = layers;
        int v5 = v0.length;
        int v2;
        for(v2 = 0; v2 < v5; ++v2) {
            MapLayer v3 = this.map.getLayers().get(v0[v2]);
            if(v3.isVisible()) {
                if((v3 instanceof TiledMapTileLayer)) {
                    this.renderTileLayer(((TiledMapTileLayer)v3));
                }
                else {
                    Iterator v1 = v3.getObjects().iterator();
                    while(v1.hasNext()) {
                        this.renderObject(v1.next());
                    }
                }
            }
        }

        this.endRender();
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public void setView(OrthographicCamera camera) {
        this.spriteBatch.setProjectionMatrix(camera.combined);
        float v1 = camera.viewportWidth * camera.zoom;
        float v0 = camera.viewportHeight * camera.zoom;
        this.viewBounds.set(camera.position.x - v1 / 2f, camera.position.y - v0 / 2f, v1, v0);
    }

    public void setView(Matrix4 projection, float x, float y, float width, float height) {
        this.spriteBatch.setProjectionMatrix(projection);
        this.viewBounds.set(x, y, width, height);
    }
}

