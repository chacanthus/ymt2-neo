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

public class IsometricStaggeredTiledMapRenderer extends BatchTiledMapRenderer {
    private float[] vertices;

    public IsometricStaggeredTiledMapRenderer(TiledMap map) {
        super(map);
        this.vertices = new float[20];
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap map, float unitScale) {
        super(map, unitScale);
        this.vertices = new float[20];
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap map, float unitScale, Batch batch) {
        super(map, unitScale, batch);
        this.vertices = new float[20];
    }

    public IsometricStaggeredTiledMapRenderer(TiledMap map, Batch batch) {
        super(map, batch);
        this.vertices = new float[20];
    }

    public void renderObject(MapObject object) {
    }

    public void renderTileLayer(TiledMapTileLayer layer) {
        float v12;
        float v28;
        Color v3 = this.spriteBatch.getColor();
        float v5 = Color.toFloatBits(v3.r, v3.g, v3.b, v3.a * layer.getOpacity());
        float v17 = layer.getTileWidth() * this.unitScale;
        float v9 = v17 * 0.5f;
        float v8 = layer.getTileHeight() * this.unitScale * 0.5f;
        int v25;
        for(v25 = layer.getHeight() - 1; v25 >= 0; --v25) {
            int v22;
            for(v22 = layer.getWidth() - 1; v22 >= 0; --v22) {
                Cell v4 = layer.getCell(v22, v25);
                if(v4 != null) {
                    TiledMapTile v15 = v4.getTile();
                    if(v15 != null) {
                        boolean v6 = v4.getFlipHorizontally();
                        boolean v7 = v4.getFlipVertically();
                        int v11 = v4.getRotation();
                        TextureRegion v10 = v15.getTextureRegion();
                        float v29 = (((float)v22)) * v17;
                        if(v25 % 2 == 1) {
                            v28 = v9;
                        }
                        else {
                            v28 = 0f;
                        }

                        float v23 = v29 - v28;
                        float v26 = (((float)v25)) * v8;
                        float v24 = v23 + (((float)v10.getRegionWidth())) * this.unitScale;
                        float v27 = v26 + (((float)v10.getRegionHeight())) * this.unitScale;
                        float v18 = v10.getU();
                        float v20 = v10.getV2();
                        float v19 = v10.getU2();
                        float v21 = v10.getV();
                        this.vertices[0] = v23;
                        this.vertices[1] = v26;
                        this.vertices[2] = v5;
                        this.vertices[3] = v18;
                        this.vertices[4] = v20;
                        this.vertices[5] = v23;
                        this.vertices[6] = v27;
                        this.vertices[7] = v5;
                        this.vertices[8] = v18;
                        this.vertices[9] = v21;
                        this.vertices[10] = v24;
                        this.vertices[11] = v27;
                        this.vertices[12] = v5;
                        this.vertices[13] = v19;
                        this.vertices[14] = v21;
                        this.vertices[15] = v24;
                        this.vertices[16] = v26;
                        this.vertices[17] = v5;
                        this.vertices[18] = v19;
                        this.vertices[19] = v20;
                        if(v6) {
                            v12 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = v12;
                            v12 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = v12;
                        }

                        if(v7) {
                            v12 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = v12;
                            v12 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = v12;
                        }

                        if(v11 != 0) {
                            switch(v11) {
                                case 1: {
                                    goto label_282;
                                }
                                case 2: {
                                    goto label_363;
                                }
                                case 3: {
                                    goto label_444;
                                }
                            }

                            goto label_269;
                        label_282:
                            float v14 = this.vertices[4];
                            this.vertices[4] = this.vertices[9];
                            this.vertices[9] = this.vertices[14];
                            this.vertices[14] = this.vertices[19];
                            this.vertices[19] = v14;
                            float v13 = this.vertices[3];
                            this.vertices[3] = this.vertices[8];
                            this.vertices[8] = this.vertices[13];
                            this.vertices[13] = this.vertices[18];
                            this.vertices[18] = v13;
                            goto label_269;
                        label_363:
                            v13 = this.vertices[3];
                            this.vertices[3] = this.vertices[13];
                            this.vertices[13] = v13;
                            v13 = this.vertices[8];
                            this.vertices[8] = this.vertices[18];
                            this.vertices[18] = v13;
                            v14 = this.vertices[4];
                            this.vertices[4] = this.vertices[14];
                            this.vertices[14] = v14;
                            v14 = this.vertices[9];
                            this.vertices[9] = this.vertices[19];
                            this.vertices[19] = v14;
                            goto label_269;
                        label_444:
                            v14 = this.vertices[4];
                            this.vertices[4] = this.vertices[19];
                            this.vertices[19] = this.vertices[14];
                            this.vertices[14] = this.vertices[9];
                            this.vertices[9] = v14;
                            v13 = this.vertices[3];
                            this.vertices[3] = this.vertices[18];
                            this.vertices[18] = this.vertices[13];
                            this.vertices[13] = this.vertices[8];
                            this.vertices[8] = v13;
                        }

                    label_269:
                        this.spriteBatch.draw(v10.getTexture(), this.vertices, 0, 20);
                    }
                }
            }
        }
    }
}

