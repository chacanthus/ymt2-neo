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

import java.util.Random;

public class MathUtils {
    class Atan2 {
        static  {
            Atan2.table = new float[16384];
            int v0;
            for(v0 = 0; v0 < MathUtils.ATAN2_DIM; ++v0) {
                int v1;
                for(v1 = 0; v1 < MathUtils.ATAN2_DIM; ++v1) {
                    Atan2.table[MathUtils.ATAN2_DIM * v1 + v0] = ((float)Math.atan2(((double)((((float)v1)) / (((float)MathUtils.ATAN2_DIM)))), ((double)((((float)v0)) / (((float)MathUtils.ATAN2_DIM))))));
                }
            }
        }

        private Atan2() {
            super();
        }
    }

    class Sin {
        static  {
            int v4 = 16384;
            Sin.table = new float[v4];
            int v0;
            for(v0 = 0; v0 < v4; ++v0) {
                Sin.table[v0] = ((float)Math.sin(((double)(((((float)v0)) + 0.5f) / 16384f * 6.283185f))));
            }

            for(v0 = 0; v0 < 360; v0 += 90) {
                Sin.table[(((int)((((float)v0)) * 45.511112f))) & 16383] = ((float)Math.sin(((double)((((float)v0)) * 0.017453f))));
            }
        }

        private Sin() {
            super();
        }
    }

    private static final int ATAN2_BITS = 7;
    private static final int ATAN2_BITS2 = 14;
    private static final int ATAN2_COUNT = 16384;
    static final int ATAN2_DIM = 0;
    private static final int ATAN2_MASK = 16383;
    private static final double BIG_ENOUGH_CEIL = 16385;
    private static final double BIG_ENOUGH_FLOOR = 0;
    private static final int BIG_ENOUGH_INT = 16384;
    private static final double BIG_ENOUGH_ROUND = 0;
    private static final double CEIL = 1;
    public static final float E = 2.718282f;
    private static final float INV_ATAN2_DIM_MINUS_1 = 0f;
    public static final float PI = 3.141593f;
    public static final float PI2 = 6.283185f;
    private static final int SIN_BITS = 14;
    private static final int SIN_COUNT = 16384;
    private static final int SIN_MASK = 16383;
    private static final float degFull = 0f;
    public static final float degRad = 0.017453f;
    private static final float degToIndex = 45.511112f;
    public static final float degreesToRadians = 0.017453f;
    public static final float nanoToSec = 0f;
    public static final float radDeg = 57.295776f;
    private static final float radFull = 6.283185f;
    private static final float radToIndex = 2607.594482f;
    public static final float radiansToDegrees = 57.295776f;
    public static Random random;

    static  {
        MathUtils.ATAN2_DIM = ((int)Math.sqrt(16384));
        MathUtils.INV_ATAN2_DIM_MINUS_1 = 1f / (((float)(MathUtils.ATAN2_DIM - 1)));
        MathUtils.random = new Random();
    }

    public MathUtils() {
        super();
    }

    public static final float atan2(float y, float x) {
        float v5;
        float v0;
        float v2;
        if(x < 0f) {
            if(y < 0f) {
                y = -y;
                v2 = 1f;
            }
            else {
                v2 = -1f;
            }

            x = -x;
            v0 = -3.141593f;
        }
        else {
            if(y < 0f) {
                y = -y;
                v2 = -1f;
            }
            else {
                v2 = 1f;
            }

            v0 = 0f;
        }

        float v6 = 1f;
        if(x < y) {
            v5 = y;
        }
        else {
            v5 = x;
        }

        float v1 = v6 / (v5 * MathUtils.INV_ATAN2_DIM_MINUS_1);
        if(v1 == Infinityf) {
            v5 = ((((float)Math.atan2(((double)y), ((double)x)))) + v0) * v2;
        }
        else {
            v5 = (Atan2.table[MathUtils.ATAN2_DIM * (((int)(y * v1))) + (((int)(x * v1)))] + v0) * v2;
        }

        return v5;
    }

    public static int ceil(float x) {
        return (((int)((((double)x)) + 16385))) - 16384;
    }

    public static int ceilPositive(float x) {
        return ((int)((((double)x)) + 1));
    }

    public static float clamp(float value, float min, float max) {
        if(value >= min) {
            if(value > max) {
                min = max;
            }
            else {
                min = value;
            }
        }

        return min;
    }

    public static int clamp(int value, int min, int max) {
        if(value >= min) {
            if(value > max) {
                min = max;
            }
            else {
                min = value;
            }
        }

        return min;
    }

    public static short clamp(short value, short min, short max) {
        if(value >= min) {
            if(value > max) {
                min = max;
            }
            else {
                min = value;
            }
        }

        return min;
    }

    public static final float cos(float radians) {
        return Sin.table[(((int)((1.570796f + radians) * 2607.594482f))) & 16383];
    }

    public static final float cosDeg(float degrees) {
        return Sin.table[(((int)((90f + degrees) * 45.511112f))) & 16383];
    }

    public static int floor(float x) {
        return (((int)((((double)x)) + 16384))) - 16384;
    }

    public static int floorPositive(float x) {
        return ((int)x);
    }

    public static boolean isPowerOfTwo(int value) {
        boolean v0;
        if(value == 0 || (value - 1 & value) != 0) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    public static int nextPowerOfTwo(int value) {
        int v0;
        if(value == 0) {
            v0 = 1;
        }
        else {
            --value;
            value |= value >> 1;
            value |= value >> 2;
            value |= value >> 4;
            value |= value >> 8;
            v0 = (value | value >> 16) + 1;
        }

        return v0;
    }

    public static final int random(int range) {
        return MathUtils.random.nextInt(range + 1);
    }

    public static final float random(float range) {
        return MathUtils.random.nextFloat() * range;
    }

    public static final float random() {
        return MathUtils.random.nextFloat();
    }

    public static final float random(float start, float end) {
        return MathUtils.random.nextFloat() * (end - start) + start;
    }

    public static final int random(int start, int end) {
        return MathUtils.random.nextInt(end - start + 1) + start;
    }

    public static final boolean randomBoolean() {
        return MathUtils.random.nextBoolean();
    }

    public static final boolean randomBoolean(float chance) {
        boolean v0;
        if(MathUtils.random() < chance) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public static int round(float x) {
        return (((int)((((double)x)) + 16384.5))) - 16384;
    }

    public static int roundPositive(float x) {
        return ((int)(0.5f + x));
    }

    public static final float sin(float radians) {
        return Sin.table[(((int)(2607.594482f * radians))) & 16383];
    }

    public static final float sinDeg(float degrees) {
        return Sin.table[(((int)(45.511112f * degrees))) & 16383];
    }
}

