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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.esotericsoftware.tablelayout.BaseTableLayout$Debug;
import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.Toolkit;
import java.util.List;

class TableLayout extends BaseTableLayout {
    Array debugRects;
    private ImmediateModeRenderer debugRenderer;
    boolean round;

    public TableLayout() {
        super(Toolkit.instance);
        this.round = true;
    }

    public void drawDebug(Batch batch) {
        float v1;
        float v2;
        float v6;
        if(this.getDebug() != Debug.none && this.debugRects != null) {
            if(this.debugRenderer == null) {
                if(Gdx.graphics.isGL20Available()) {
                    this.debugRenderer = new ImmediateModeRenderer20(64, false, true, 0);
                }
                else {
                    this.debugRenderer = new ImmediateModeRenderer10(64);
                }
            }

            float v8 = 0f;
            float v11 = 0f;
            Object v5 = this.getTable();
            while(v5 != null) {
                if((v5 instanceof Group)) {
                    v8 += ((Actor)v5).getX();
                    v11 += ((Actor)v5).getY();
                }

                Group v5_1 = ((Actor)v5).getParent();
            }

            this.debugRenderer.begin(batch.getProjectionMatrix(), 1);
            int v3 = 0;
            int v4 = this.debugRects.size;
            while(v3 < v4) {
                Object v7 = this.debugRects.get(v3);
                float v9 = v8 + ((DebugRect)v7).x;
                float v12 = ((DebugRect)v7).y + v11 - ((DebugRect)v7).height;
                float v10 = v9 + ((DebugRect)v7).width;
                float v13 = v12 + ((DebugRect)v7).height;
                if(((DebugRect)v7).type == Debug.cell) {
                    v6 = 1f;
                }
                else {
                    v6 = 0f;
                }

                if(((DebugRect)v7).type == Debug.widget) {
                    v2 = 1f;
                }
                else {
                    v2 = 0f;
                }

                if(((DebugRect)v7).type == Debug.table) {
                    v1 = 1f;
                }
                else {
                    v1 = 0f;
                }

                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v9, v12, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v9, v13, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v9, v13, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v10, v13, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v10, v13, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v10, v12, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v10, v12, 0f);
                this.debugRenderer.color(v6, v2, v1, 1f);
                this.debugRenderer.vertex(v9, v12, 0f);
                if(this.debugRenderer.getNumVertices() == 64) {
                    this.debugRenderer.end();
                    this.debugRenderer.begin(batch.getProjectionMatrix(), 1);
                }

                ++v3;
            }

            this.debugRenderer.end();
        }
    }

    public void invalidateHierarchy() {
        super.invalidate();
        this.getTable().invalidateHierarchy();
    }

    public void layout() {
        Object v4;
        float v16;
        float v13;
        Object v5;
        int v11;
        int v10;
        Object v12 = this.getTable();
        float v17 = ((Table)v12).getWidth();
        float v9 = ((Table)v12).getHeight();
        super.layout(0f, 0f, v17, v9);
        List v6 = this.getCells();
        if(this.round) {
            v10 = 0;
            v11 = v6.size();
            while(v10 < v11) {
                v5 = v6.get(v10);
                if(!((Cell)v5).getIgnore()) {
                    float v14 = ((float)Math.round(((Cell)v5).getWidgetWidth()));
                    v13 = ((float)Math.round(((Cell)v5).getWidgetHeight()));
                    float v15 = ((float)Math.round(((Cell)v5).getWidgetX()));
                    v16 = v9 - (((float)Math.round(((Cell)v5).getWidgetY()))) - v13;
                    ((Cell)v5).setWidgetBounds(v15, v16, v14, v13);
                    v4 = ((Cell)v5).getWidget();
                    if(v4 != null) {
                        ((Actor)v4).setBounds(v15, v16, v14, v13);
                    }
                }

                ++v10;
            }
        }
        else {
            v10 = 0;
            v11 = v6.size();
            while(v10 < v11) {
                v5 = v6.get(v10);
                if(!((Cell)v5).getIgnore()) {
                    v13 = ((Cell)v5).getWidgetHeight();
                    v16 = v9 - ((Cell)v5).getWidgetY() - v13;
                    ((Cell)v5).setWidgetY(v16);
                    v4 = ((Cell)v5).getWidget();
                    if(v4 != null) {
                        ((Actor)v4).setBounds(((Cell)v5).getWidgetX(), v16, ((Cell)v5).getWidgetWidth(), v13);
                    }
                }

                ++v10;
            }
        }

        SnapshotArray v8 = ((Table)v12).getChildren();
        v10 = 0;
        v11 = ((Array)v8).size;
        while(v10 < v11) {
            Object v7 = ((Array)v8).get(v10);
            if((v7 instanceof Layout)) {
                ((Layout)v7).validate();
            }

            ++v10;
        }
    }
}

