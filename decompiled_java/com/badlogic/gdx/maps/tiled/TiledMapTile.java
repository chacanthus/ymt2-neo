﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;

public abstract interface TiledMapTile {
    public enum BlendMode {
        public static final enum BlendMode ALPHA;
        public static final enum BlendMode NONE;

        static  {
            BlendMode.NONE = new BlendMode("NONE", 0);
            BlendMode.ALPHA = new BlendMode("ALPHA", 1);
            BlendMode[] v0 = new BlendMode[2];
            v0[0] = BlendMode.NONE;
            v0[1] = BlendMode.ALPHA;
            BlendMode.$VALUES = v0;
        }

        private BlendMode(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static BlendMode valueOf(String name) {
            return Enum.valueOf(BlendMode.class, name);
        }

        public static BlendMode[] values() {
            return BlendMode.$VALUES.clone();
        }
    }

    public abstract BlendMode getBlendMode();

    public abstract int getId();

    public abstract MapProperties getProperties();

    public abstract TextureRegion getTextureRegion();

    public abstract void setBlendMode(BlendMode arg0);

    public abstract void setId(int arg0);
}

