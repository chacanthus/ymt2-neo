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

public class VerticalGroup extends WidgetGroup {
    private int alignment;
    private float prefHeight;
    private float prefWidth;
    private boolean reverse;
    private boolean round;
    private boolean sizeInvalid;
    private float spacing;

    public VerticalGroup() {
        super();
        this.sizeInvalid = true;
        this.round = true;
        this.setTouchable(Touchable.childrenOnly);
    }

    private void computeSize() {
        this.sizeInvalid = false;
        SnapshotArray v1 = this.getChildren();
        int v4 = v1.size;
        this.prefWidth = 0f;
        this.prefHeight = this.spacing * (((float)(v4 - 1)));
        int v2;
        for(v2 = 0; v2 < v4; ++v2) {
            Object v0 = v1.get(v2);
            if((v0 instanceof Layout)) {
                this.prefWidth = Math.max(this.prefWidth, v0.getPrefWidth());
                this.prefHeight += v0.getPrefHeight();
            }
            else {
                this.prefWidth = Math.max(this.prefWidth, ((Actor)v0).getWidth());
                this.prefHeight += ((Actor)v0).getHeight();
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
        float v5;
        float v10;
        float v3;
        float v12;
        float v4;
        float v9 = this.spacing;
        if(this.getWidth() > 0f) {
            v4 = this.getWidth();
        }
        else {
            v4 = this.getMinWidth();
        }

        if(this.reverse) {
            v12 = 0f;
        }
        else if(this.getHeight() > 0f) {
            v12 = this.getHeight();
        }
        else {
            v12 = this.getMinHeight();
        }

        if(this.reverse) {
            v3 = 1f;
        }
        else {
            v3 = -1f;
        }

        SnapshotArray v2 = this.getChildren();
        int v6 = 0;
        int v8 = v2.size;
        while(v6 < v8) {
            Object v1 = v2.get(v6);
            if((v1 instanceof Layout)) {
                v10 = v1.getPrefWidth();
                v5 = v1.getPrefHeight();
                if(v10 != 0f && v10 <= v4) {
                    goto label_28;
                }

                v10 = v4;
            }
            else {
                v10 = ((Actor)v1).getWidth();
                v5 = ((Actor)v1).getHeight();
            }

        label_28:
            if((this.alignment & 8) != 0) {
                v11 = 0f;
            }
            else if((this.alignment & 16) != 0) {
                v11 = v4 - v10;
            }
            else {
                v11 = (v4 - v10) / 2f;
            }

            if(!this.reverse) {
                v12 += (v5 + v9) * v3;
            }

            if(this.round) {
                ((Actor)v1).setBounds(((float)Math.round(v11)), ((float)Math.round(v12)), ((float)Math.round(v10)), ((float)Math.round(v5)));
            }
            else {
                ((Actor)v1).setBounds(v11, v12, v10, v5);
            }

            if(this.reverse) {
                v12 += (v5 + v9) * v3;
            }

            ++v6;
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

