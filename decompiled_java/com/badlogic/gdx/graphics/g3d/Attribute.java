﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;

public abstract class Attribute {
    public final long type;
    private static final Array types;

    static  {
        Attribute.types = new Array();
    }

    protected Attribute(long type) {
        super();
        this.type = type;
    }

    public abstract Attribute copy();

    protected abstract boolean equals(Attribute arg0);

    public boolean equals(Object obj) {
        boolean v1 = false;
        if(obj != null) {
            if((((Attribute)obj)) == this) {
                v1 = true;
            }
            else if((obj instanceof Attribute)) {
                Object v0 = obj;
                if(this.type == ((Attribute)v0).type) {
                    v1 = this.equals(((Attribute)v0));
                }
            }
        }

        return v1;
    }

    public static final String getAttributeAlias(long type) {
        String v1_1;
        long v5 = 0;
        int v0 = -1;
        do {
            if(type == v5) {
                break;
            }

            ++v0;
            if(v0 >= 63) {
                break;
            }
        }
        while((type >> v0 & 1) == v5);

        if(v0 < 0 || v0 >= Attribute.types.size) {
            v1_1 = null;
        }
        else {
            Object v1 = Attribute.types.get(v0);
        }

        return v1_1;
    }

    public static final long getAttributeType(String alias) {
        long v1;
        int v0 = 0;
        while(true) {
            if(v0 >= Attribute.types.size) {
                break;
            }
            else if(Attribute.types.get(v0).compareTo(alias) == 0) {
                v1 = 1 << v0;
            }
            else {
                ++v0;
                continue;
            }

            goto label_10;
        }

        v1 = 0;
    label_10:
        return v1;
    }

    protected static final long register(String alias) {
        long v0 = Attribute.getAttributeType(alias);
        if(v0 <= 0) {
            Attribute.types.add(alias);
            v0 = 1 << Attribute.types.size - 1;
        }

        return v0;
    }

    public String toString() {
        return Attribute.getAttributeAlias(this.type);
    }
}

