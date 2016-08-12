// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TiledDrawable extends TextureRegionDrawable {
    public TiledDrawable(TextureRegion region) {
        super(region);
    }

    public TiledDrawable() {
        super();
    }

    public TiledDrawable(TextureRegionDrawable drawable) {
        super(drawable);
    }

    public void draw(Batch batch, float x, float y, float width, float height) {
        float v15;
        float v14;
        TextureRegion v1 = this.getRegion();
        float v4 = ((float)v1.getRegionWidth());
        float v5 = ((float)v1.getRegionHeight());
        float v10 = width % v4;
        float v21 = height % v5;
        float v28 = x;
        float v29 = y;
        float v26 = x + width - v10;
        float v27 = y + height - v21;
        while(x < v26) {
            for(y = v29; y < v27; y += v5) {
                batch.draw(v1, x, y, v4, v5);
            }

            x += v4;
        }

        Texture v7 = v1.getTexture();
        float v12 = v1.getU();
        float v13 = v1.getV2();
        if(v10 > 0f) {
            v14 = v12 + v10 / (((float)v7.getWidth()));
            v15 = v1.getV();
            for(y = v29; y < v27; y += v5) {
                batch.draw(v7, x, y, v10, v5, v12, v13, v14, v15);
            }

            if(v21 > 0f) {
                batch.draw(v7, x, y, v10, v21, v12, v13, v14, v13 - v21 / (((float)v7.getHeight())));
            }
        }

        if(v21 > 0f) {
            v14 = v1.getU2();
            v15 = v13 - v21 / (((float)v7.getHeight()));
            for(x = v28; x < v26; x += v4) {
                batch.draw(v7, x, y, v4, v21, v12, v13, v14, v15);
            }
        }
    }
}

