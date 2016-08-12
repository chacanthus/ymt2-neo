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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer$Cell;

public class IsometricTiledMapRenderer extends BatchTiledMapRenderer {
    private float[] vertices;

    public IsometricTiledMapRenderer(TiledMap map) {
        super(map);
        this.vertices = new float[20];
    }

    public IsometricTiledMapRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
        this.vertices = new float[20];
    }

    public IsometricTiledMapRenderer(TiledMap map, float unitScale, Batch batch) {
        super(map, unitScale, batch);
        this.vertices = new float[20];
    }

    public IsometricTiledMapRenderer(TiledMap map, Batch batch) {
        super(map, batch);
        this.vertices = new float[20];
    }

    public void renderObject(MapObject object) {
    }

    public void renderTileLayer(TiledMapTileLayer layer) {
        float v16;
        Color v1 = this.spriteBatch.getColor();
        float v6 = Color.toFloatBits(v1.r, v1.g, v1.b, v1.a * layer.getOpacity());
        int v5 = layer.getWidth() - 1;
        int v15 = layer.getHeight() - 1;
        float v10 = layer.getTileWidth() * this.unitScale * 0.5f;
        float v9 = layer.getTileHeight() * this.unitScale * 0.5f;
        int v13;
        for(v13 = v15; v13 >= 0; --v13) {
            int v3;
            for(v3 = 0; v3 <= v5; ++v3) {
                float v26 = (((float)v3)) * v10 + (((float)v13)) * v10;
                float v29 = (((float)v13)) * v9 - (((float)v3)) * v9;
                Cell v2 = layer.getCell(v3, v13);
                if(v2 != null) {
                    TiledMapTile v19 = v2.getTile();
                    if(v19 != null) {
                        boolean v7 = v2.getFlipHorizontally();
                        boolean v8 = v2.getFlipVertically();
                        int v12 = v2.getRotation();
                        TextureRegion v11 = v19.getTextureRegion();
                        float v28 = v26 + (((float)v11.getRegionWidth())) * this.unitScale;
                        float v31 = v29 + (((float)v11.getRegionHeight())) * this.unitScale;
                        float v22 = v11.getU();
                        float v24 = v11.getV2();
                        float v23 = v11.getU2();
                        float v25 = v11.getV();
                        this.vertices[0] = v26;
                        this.vertices[1] = v29;
                        this.vertices[2] = v6;
                        this.vertices[3] = v22;
                        this.vertices[4] = v24;
                        this.vertices[5] = v26;
                        this.vertices[6] = v31;
                        this.vertices[7] = v6;
                        this.vertices[8] = v22;
                        this.vertices[9] = v25;
                        this.vertices[10] = v28;
                        this.vertices[11] = v31;
                        this.vertices[12] = v6;
                        this.vertices[13] = v23;
                        this.vertices[14] = v25;
                        this.vertices[15] = v28;
                        this.vertices[16] = v29;
                        this.vertices[17] = v6;
                        this.vertices[18] = v23;
                        this.vertices[19] = v24;
                        if(v7) {
                            v16 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = v16;
                            v16 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = v16;
                        }

                        if(v8) {
                            v16 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = v16;
                            v16 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = v16;
                        }

                        if(v12 != 0) {
                            switch(v12) {
                                case 1: {
                                    goto label_283;
                                }
                                case 2: {
                                    goto label_364;
                                }
                                case 3: {
                                    goto label_445;
                                }
                            }

                            goto label_272;
                        label_283:
                            float v18 = this.vertices[4];
                            this.vertices[4] = this.vertices[9];
                            this.vertices[9] = this.vertices[14];
                            this.vertices[14] = this.vertices[19];
                            this.vertices[19] = v18;
                            float v17 = this.vertices[3];
                            this.vertices[3] = this.vertices[8];
                            this.vertices[8] = this.vertices[13];
                            this.vertices[13] = this.vertices[18];
                            this.vertices[18] = v17;
                            goto label_272;
                        label_364:
                            v17 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = v17;
                            v17 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = v17;
                            v18 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = v18;
                            v18 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = v18;
                            goto label_272;
                        label_445:
                            v18 = this.vertices[4];
                            this.vertices[4] = this.vertices[19];
                            this.vertices[19] = this.vertices[14];
                            this.vertices[14] = this.vertices[9];
                            this.vertices[9] = v18;
                            v17 = this.vertices[3];
                            this.vertices[3] = this.vertices[18];
                            this.vertices[18] = this.vertices[13];
                            this.vertices[13] = this.vertices[8];
                            this.vertices[8] = v17;
                        }

                    label_272:
                        this.spriteBatch.draw(v11.getTexture(), this.vertices, 0, 20);
                    }
                }
            }
        }
    }
}

