﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.math;

public class CatmullRomSpline implements Path {
    public boolean continuous;
    public Vector[] controlPoints;
    public int spanCount;
    private Vector tmp;
    private Vector tmp2;

    public CatmullRomSpline() {
        super();
    }

    public CatmullRomSpline(Vector[] arg1, boolean continuous) {
        super();
        this.set(arg1, continuous);
    }

    public float approximate(Vector arg2) {
        return this.approximate(arg2, this.nearest(arg2));
    }

    public float approximate(Vector arg19, int near) {
        Vector v6;
        Vector v5;
        Vector v4;
        int v15;
        int v9 = near;
        Vector v10 = this.controlPoints[v9];
        Vector[] v16 = this.controlPoints;
        if(v9 > 0) {
            v15 = v9 - 1;
        }
        else {
            v15 = this.spanCount - 1;
        }

        Vector v12 = v16[v15];
        Vector v11 = this.controlPoints[(v9 + 1) % this.spanCount];
        if(arg19.dst2(v11) < arg19.dst2(v12)) {
            v4 = v10;
            v5 = v11;
            v6 = arg19;
        }
        else {
            v4 = v12;
            v5 = v10;
            v6 = arg19;
            if(v9 > 0) {
                --v9;
            }
            else {
                v9 = this.spanCount - 1;
            }
        }

        float v1 = v4.dst(v5);
        float v2 = v6.dst(v5);
        float v3 = v6.dst(v4);
        return ((((float)v9)) + MathUtils.clamp((v1 - (v2 * v2 + v1 * v1 - v3 * v3) / (2f * v1)) / v1, 0f, 1f)) / (((float)this.spanCount));
    }

    public float approximate(Vector arg2, int start, int count) {
        return this.approximate(arg2, this.nearest(arg2, start, count));
    }

    public float approximate(Object x0) {
        return this.approximate(((Vector)x0));
    }

    public static Vector calculate(Vector arg7, float t, Vector[] arg9, boolean continuous, Vector arg11) {
        int v1;
        int v6;
        if(continuous) {
            v6 = arg9.length;
        }
        else {
            v6 = arg9.length - 3;
        }

        float v2 = t * (((float)v6));
        if(t >= 1f) {
            v1 = v6 - 1;
        }
        else {
            v1 = ((int)v2);
        }

        return CatmullRomSpline.calculate(arg7, v1, v2 - (((float)v1)), arg9, continuous, arg11);
    }

    public static Vector calculate(Vector arg7, int i, float u, Vector[] arg10, boolean continuous, Vector arg12) {
        float v6 = 0.5f;
        int v0 = arg10.length;
        float v1 = u * u;
        float v2 = v1 * u;
        arg7.set(arg10[i]).scl(1.5f * v2 - 2.5f * v1 + 1f);
        if((continuous) || i > 0) {
            arg7.add(arg12.set(arg10[(v0 + i - 1) % v0]).scl(-0.5f * v2 + v1 - v6 * u));
        }

        if((continuous) || i < v0 - 1) {
            arg7.add(arg12.set(arg10[(i + 1) % v0]).scl(-1.5f * v2 + 2f * v1 + v6 * u));
        }

        if((continuous) || i < v0 - 2) {
            arg7.add(arg12.set(arg10[(i + 2) % v0]).scl(v6 * v2 - v6 * v1));
        }

        return arg7;
    }

    public static Vector derivative(Vector arg7, float t, Vector[] arg9, boolean continuous, Vector arg11) {
        int v1;
        int v6;
        if(continuous) {
            v6 = arg9.length;
        }
        else {
            v6 = arg9.length - 3;
        }

        float v2 = t * (((float)v6));
        if(t >= 1f) {
            v1 = v6 - 1;
        }
        else {
            v1 = ((int)v2);
        }

        return CatmullRomSpline.derivative(arg7, v1, v2 - (((float)v1)), arg9, continuous, arg11);
    }

    public static Vector derivative(Vector arg7, int i, float u, Vector[] arg10, boolean continuous, Vector arg12) {
        float v6 = 4.5f;
        float v5 = 1.5f;
        int v0 = arg10.length;
        float v1 = u * u;
        arg7.set(arg10[i]).scl(-u * 5f + v1 * v6);
        if((continuous) || i > 0) {
            arg7.add(arg12.set(arg10[(v0 + i - 1) % v0]).scl(-0.5f + 2f * u - v1 * v5));
        }

        if((continuous) || i < v0 - 1) {
            arg7.add(arg12.set(arg10[(i + 1) % v0]).scl(0.5f + 4f * u - v1 * v6));
        }

        if((continuous) || i < v0 - 2) {
            arg7.add(arg12.set(arg10[(i + 2) % v0]).scl(-u + v1 * v5));
        }

        return arg7;
    }

    public Vector derivativeAt(Vector arg5, float t) {
        int v0;
        int v1 = this.spanCount;
        float v2 = t * (((float)v1));
        if(t >= 1f) {
            v0 = v1 - 1;
        }
        else {
            v0 = ((int)v2);
        }

        return this.derivativeAt(arg5, v0, v2 - (((float)v0)));
    }

    public Vector derivativeAt(Vector arg7, int span, float u) {
        int v1;
        if(this.continuous) {
            v1 = span;
        }
        else {
            v1 = span + 1;
        }

        return CatmullRomSpline.derivative(arg7, v1, u, this.controlPoints, this.continuous, this.tmp);
    }

    public Object derivativeAt(Object x0, float x1) {
        return this.derivativeAt(((Vector)x0), x1);
    }

    public float locate(Vector arg2) {
        return this.approximate(arg2);
    }

    public float locate(Object x0) {
        return this.locate(((Vector)x0));
    }

    public int nearest(Vector arg3) {
        return this.nearest(arg3, 0, this.spanCount);
    }

    public int nearest(Vector arg8, int start, int count) {
        while(start < 0) {
            start += this.spanCount;
        }

        int v4 = start % this.spanCount;
        float v1 = arg8.dst2(this.controlPoints[v4]);
        int v2;
        for(v2 = 1; v2 < count; ++v2) {
            int v3 = (start + v2) % this.spanCount;
            float v0 = arg8.dst2(this.controlPoints[v3]);
            if(v0 < v1) {
                v1 = v0;
                v4 = v3;
            }
        }

        return v4;
    }

    public CatmullRomSpline set(Vector[] arg3, boolean continuous) {
        int v0;
        if(this.tmp == null) {
            this.tmp = arg3[0].cpy();
        }

        if(this.tmp2 == null) {
            this.tmp2 = arg3[0].cpy();
        }

        this.controlPoints = arg3;
        this.continuous = continuous;
        if(continuous) {
            v0 = arg3.length;
        }
        else {
            v0 = arg3.length - 3;
        }

        this.spanCount = v0;
        return this;
    }

    public Vector valueAt(Vector arg5, float t) {
        int v0;
        int v1 = this.spanCount;
        float v2 = t * (((float)v1));
        if(t >= 1f) {
            v0 = v1 - 1;
        }
        else {
            v0 = ((int)v2);
        }

        return this.valueAt(arg5, v0, v2 - (((float)v0)));
    }

    public Vector valueAt(Vector arg7, int span, float u) {
        int v1;
        if(this.continuous) {
            v1 = span;
        }
        else {
            v1 = span + 1;
        }

        return CatmullRomSpline.calculate(arg7, v1, u, this.controlPoints, this.continuous, this.tmp);
    }

    public Object valueAt(Object x0, float x1) {
        return this.valueAt(((Vector)x0), x1);
    }
}

