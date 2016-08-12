// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.SnapshotArray;

public class HorizontalGroup extends WidgetGroup {
    private int alignment;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private boolean round;
    private boolean sizeInvalid;
    private float spacing;

    public HorizontalGroup() {
        super();
        this.sizeInvalid = true;
        this.round = true;
        this.setTouchable(Touchable.childrenOnly);
    }

    private void computeSize() {
        this.sizeInvalid = false;
        SnapshotArray v1 = this.getChildren();
        int v4 = v1.size;
        this.prefWidth = this.spacing * (((float)(v4 - 1)));
        this.prefHeight = 0f;
        int v2;
        for(v2 = 0; v2 < v4; ++v2) {
            Object v0 = v1.get(v2);
            if((v0 instanceof Layout)) {
                this.prefWidth += v0.getPrefWidth();
                this.prefHeight = Math.max(this.prefHeight, v0.getPrefHeight());
            }
            else {
                this.prefWidth += ((Actor)v0).getWidth();
                this.prefHeight = Math.max(this.prefHeight, ((Actor)v0).getHeight());
            }
        }

        if(this.round) {
            this.prefWidth = ((float)Math.round(this.prefWidth));
            this.prefHeight = ((float)Math.round(this.prefHeight));
        }
    }

    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.prefHeight;
    }

    public float getPrefWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.prefWidth;
    }

    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    public void layout() {
        float v11;
        float v4;
        float v9;
        float v2;
        float v3;
        float v10 = 0f;
        float v8 = this.spacing;
        if(this.getHeight() > 0f) {
            v3 = this.getHeight();
        }
        else {
            v3 = this.getMinHeight();
        }

        if(this.reverse) {
            if(this.getWidth() > 0f) {
                v10 = this.getWidth();
            }
            else {
                v10 = this.getMinWidth();
            }
        }

        if(this.reverse) {
            v2 = -1f;
        }
        else {
            v2 = 1f;
        }

        SnapshotArray v1 = this.getChildren();
        int v5 = 0;
        int v7 = v1.size;
        while(v5 < v7) {
            Object v0 = v1.get(v5);
            if((v0 instanceof Layout)) {
                v9 = v0.getPrefWidth();
                v4 = v0.getPrefHeight();
            }
            else {
                v9 = ((Actor)v0).getWidth();
                v4 = ((Actor)v0).getHeight();
            }

            if((this.alignment & 4) != 0) {
                v11 = 0f;
            }
            else if((this.alignment & 2) != 0) {
                v11 = v3 - v4;
            }
            else {
                v11 = (v3 - v4) / 2f;
            }

            if(this.reverse) {
                v10 += (v9 + v8) * v2;
            }

            ((Actor)v0).setBounds(v10, v11, v9, v4);
            if(!this.reverse) {
                v10 += (v9 + v8) * v2;
            }

            ++v5;
        }
    }

    public void setAlignment(int alignment) {
        this.alignment = alignment;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setRound(boolean round) {
        this.round = round;
    }

    public void setSpacing(float spacing) {
        this.spacing = spacing;
    }
}

