// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class Animation {
    public static final int LOOP = 2;
    public static final int LOOP_PINGPONG = 4;
    public static final int LOOP_RANDOM = 5;
    public static final int LOOP_REVERSED = 3;
    public static final int NORMAL = 0;
    public static final int REVERSED = 1;
    public final float animationDuration;
    public final float frameDuration;
    final TextureRegion[] keyFrames;
    private int playMode;

    public Animation(float frameDuration, Array arg7) {
        super();
        this.playMode = 0;
        this.frameDuration = frameDuration;
        this.animationDuration = (((float)arg7.size)) * frameDuration;
        this.keyFrames = new TextureRegion[arg7.size];
        int v0 = 0;
        int v1 = arg7.size;
        while(v0 < v1) {
            this.keyFrames[v0] = arg7.get(v0);
            ++v0;
        }

        this.playMode = 0;
    }

    public Animation(float frameDuration, Array arg6, int playType) {
        super();
        this.playMode = 0;
        this.frameDuration = frameDuration;
        this.animationDuration = (((float)arg6.size)) * frameDuration;
        this.keyFrames = new TextureRegion[arg6.size];
        int v0 = 0;
        int v1 = arg6.size;
        while(v0 < v1) {
            this.keyFrames[v0] = arg6.get(v0);
            ++v0;
        }

        this.playMode = playType;
    }

    public Animation(float frameDuration, TextureRegion[] keyFrames) {
        super();
        this.playMode = 0;
        this.frameDuration = frameDuration;
        this.animationDuration = (((float)keyFrames.length)) * frameDuration;
        this.keyFrames = keyFrames;
        this.playMode = 0;
    }

    public TextureRegion getKeyFrame(float stateTime) {
        return this.keyFrames[this.getKeyFrameIndex(stateTime)];
    }

    public TextureRegion getKeyFrame(float stateTime, boolean looping) {
        int v5 = 3;
        int v4 = 2;
        int v1 = this.playMode;
        if(looping) {
            if(this.playMode != 0 && this.playMode != 1) {
                goto label_17;
            }

            if(this.playMode != 0) {
                goto label_15;
            }

            this.playMode = v4;
            goto label_12;
        label_15:
            this.playMode = v5;
        }
        else {
        label_17:
            if(looping) {
                goto label_12;
            }

            if(this.playMode == 0) {
                goto label_12;
            }

            if(this.playMode == 1) {
                goto label_12;
            }

            if(this.playMode != v5) {
                goto label_26;
            }

            this.playMode = 1;
            goto label_12;
        label_26:
            this.playMode = v4;
        }

    label_12:
        TextureRegion v0 = this.getKeyFrame(stateTime);
        this.playMode = v1;
        return v0;
    }

    public int getKeyFrameIndex(float stateTime) {
        int v0;
        if(this.keyFrames.length == 1) {
            v0 = 0;
        }
        else {
            v0 = ((int)(stateTime / this.frameDuration));
            switch(this.playMode) {
                case 0: {
                    goto label_17;
                }
                case 1: {
                    goto label_47;
                }
                case 2: {
                    goto label_22;
                }
                case 3: {
                    goto label_53;
                }
                case 4: {
                    goto label_26;
                }
                case 5: {
                    goto label_42;
                }
            }

            v0 = Math.min(this.keyFrames.length - 1, v0);
            goto label_6;
        label_17:
            v0 = Math.min(this.keyFrames.length - 1, v0);
            goto label_6;
        label_53:
            v0 = this.keyFrames.length - v0 % this.keyFrames.length - 1;
            goto label_6;
        label_22:
            v0 %= this.keyFrames.length;
            goto label_6;
        label_26:
            v0 %= this.keyFrames.length * 2 - 2;
            if(v0 >= this.keyFrames.length) {
                v0 = this.keyFrames.length - 2 - (v0 - this.keyFrames.length);
                goto label_6;
            label_42:
                v0 = MathUtils.random(this.keyFrames.length - 1);
                goto label_6;
            label_47:
                v0 = Math.max(this.keyFrames.length - v0 - 1, 0);
            }
        }

    label_6:
        return v0;
    }

    public TextureRegion[] getKeyFrames() {
        return this.keyFrames;
    }

    public int getPlayMode() {
        return this.playMode;
    }

    public boolean isAnimationFinished(float stateTime) {
        boolean v1;
        if(this.keyFrames.length - 1 < (((int)(stateTime / this.frameDuration)))) {
            v1 = true;
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }
}

