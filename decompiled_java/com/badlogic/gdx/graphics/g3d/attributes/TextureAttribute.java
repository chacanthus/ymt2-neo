// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class TextureAttribute extends Attribute {
    public static final long Bump = 0;
    public static final String BumpAlias = "bumpTexture";
    public static final String DiffuseAlias = "diffuseTexture";
    protected static long Mask = 0;
    public static final String NormalAlias = "normalTexture";
    public static final String SpecularAlias = "specularTexture";

    static  {
        TextureAttribute.Diffuse = TextureAttribute.register("diffuseTexture");
        TextureAttribute.Specular = TextureAttribute.register("specularTexture");
        TextureAttribute.Bump = TextureAttribute.register("bumpTexture");
        TextureAttribute.Normal = TextureAttribute.register("normalTexture");
        TextureAttribute.Mask = TextureAttribute.Diffuse | TextureAttribute.Specular | TextureAttribute.Bump | TextureAttribute.Normal;
    }

    public TextureAttribute(long type, TextureDescriptor arg4) {
        this(type);
        this.textureDescription.set(arg4);
    }

    public TextureAttribute(long type) {
        super(type);
        if(!TextureAttribute.is(type)) {
            throw new GdxRuntimeException("Invalid type specified");
        }

        this.textureDescription = new TextureDescriptor();
    }

    public TextureAttribute(long type, Texture texture) {
        this(type);
        this.textureDescription.texture = ((GLTexture)texture);
    }

    public TextureAttribute(TextureAttribute copyFrom) {
        this(copyFrom.type, copyFrom.textureDescription);
    }

    public Attribute copy() {
        return new TextureAttribute(this);
    }

    public static TextureAttribute createDiffuse(Texture texture) {
        return new TextureAttribute(TextureAttribute.Diffuse, texture);
    }

    public static TextureAttribute createSpecular(Texture texture) {
        return new TextureAttribute(TextureAttribute.Specular, texture);
    }

    protected boolean equals(Attribute other) {
        return ((TextureAttribute)other).textureDescription.equals(this.textureDescription);
    }

    public static final boolean is(long mask) {
        boolean v0;
        if((TextureAttribute.Mask & mask) != 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }
}

