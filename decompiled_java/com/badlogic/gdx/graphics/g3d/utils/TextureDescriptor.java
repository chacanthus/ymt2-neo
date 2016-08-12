// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.Texture$TextureWrap;

public class TextureDescriptor {
    public TextureFilter magFilter;
    public TextureFilter minFilter;
    public TextureWrap uWrap;
    public TextureWrap vWrap;

    public TextureDescriptor() {
        super();
        this.texture = null;
    }

    public TextureDescriptor(GLTexture arg7) {
        this(arg7, null, null, null, null);
    }

    public TextureDescriptor(GLTexture arg2, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
        super();
        this.texture = null;
        this.set(arg2, minFilter, magFilter, uWrap, vWrap);
    }

    public boolean equals(Object obj) {
        boolean v1 = true;
        boolean v2 = false;
        if(obj != null) {
            if((((TextureDescriptor)obj)) == this) {
                v2 = true;
            }
            else if((obj instanceof TextureDescriptor)) {
                Object v0 = obj;
                if(((TextureDescriptor)v0).texture != this.texture || ((TextureDescriptor)v0).minFilter != this.minFilter || ((TextureDescriptor)v0).magFilter != this.magFilter || ((TextureDescriptor)v0).uWrap != this.uWrap || ((TextureDescriptor)v0).vWrap != this.vWrap) {
                    v1 = false;
                }

                v2 = v1;
            }
        }

        return v2;
    }

    public void set(GLTexture arg1, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
        this.texture = arg1;
        this.minFilter = minFilter;
        this.magFilter = magFilter;
        this.uWrap = uWrap;
        this.vWrap = vWrap;
    }

    public void set(TextureDescriptor arg2) {
        this.texture = arg2.texture;
        this.minFilter = arg2.minFilter;
        this.magFilter = arg2.magFilter;
        this.uWrap = arg2.uWrap;
        this.vWrap = arg2.vWrap;
    }
}

