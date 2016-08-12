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

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class DepthTestAttribute extends Attribute {
    public static final String Alias = "depthStencil";
    protected static long Mask;

    static  {
        DepthTestAttribute.Type = DepthTestAttribute.register("depthStencil");
        DepthTestAttribute.Mask = DepthTestAttribute.Type;
    }

    public DepthTestAttribute() {
        this(515);
    }

    public DepthTestAttribute(int depthFunc) {
        this(depthFunc, true);
    }

    public DepthTestAttribute(int depthFunc, boolean depthMask) {
        this(depthFunc, 0f, 1f, depthMask);
    }

    public DepthTestAttribute(int depthFunc, float depthRangeNear, float depthRangeFar) {
        this(depthFunc, depthRangeNear, depthRangeFar, true);
    }

    public DepthTestAttribute(int depthFunc, float depthRangeNear, float depthRangeFar, boolean depthMask) {
        this(DepthTestAttribute.Type, depthFunc, depthRangeNear, depthRangeFar, depthMask);
    }

    public DepthTestAttribute(long type, int depthFunc, float depthRangeNear, float depthRangeFar, boolean depthMask) {
        super(type);
        if(!DepthTestAttribute.is(type)) {
            throw new GdxRuntimeException("Invalid type specified");
        }

        this.depthFunc = depthFunc;
        this.depthRangeNear = depthRangeNear;
        this.depthRangeFar = depthRangeFar;
        this.depthMask = depthMask;
    }

    public DepthTestAttribute(DepthTestAttribute rhs) {
        this(rhs.type, rhs.depthFunc, rhs.depthRangeNear, rhs.depthRangeFar, rhs.depthMask);
    }

    public DepthTestAttribute(boolean depthMask) {
        this(515, depthMask);
    }

    public Attribute copy() {
        return new DepthTestAttribute(this);
    }

    protected boolean equals(Attribute other) {
        boolean v1;
        Attribute v0 = other;
        if(this.depthFunc != ((DepthTestAttribute)v0).depthFunc || this.depthRangeNear != ((DepthTestAttribute)v0).depthRangeNear || this.depthRangeFar != ((DepthTestAttribute)v0).depthRangeFar || this.depthMask != ((DepthTestAttribute)v0).depthMask) {
            v1 = false;
        }
        else {
            v1 = true;
        }

        return v1;
    }

    public static final boolean is(long mask) {
        boolean v0;
        if((DepthTestAttribute.Mask & mask) != 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }
}

