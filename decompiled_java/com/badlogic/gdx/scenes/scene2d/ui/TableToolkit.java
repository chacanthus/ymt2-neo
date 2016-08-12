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

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.esotericsoftware.tablelayout.BaseTableLayout$Debug;
import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.Toolkit;

public class TableToolkit extends Toolkit {
    class DebugRect extends Rectangle {
        public DebugRect(Debug type, float x, float y, float width, float height) {
            super(x, y, width, height);
            this.type = type;
        }
    }

    static Pool cellPool;
    static boolean drawDebug;

    static  {
        TableToolkit.cellPool = new Pool() {
            protected Cell newObject() {
                return new Cell();
            }

            protected Object newObject() {
                return this.newObject();
            }
        };
    }

    public TableToolkit() {
        super();
    }

    public void addChild(Actor parent, Actor child) {
        child.remove();
        ((Group)parent).addActor(child);
    }

    public void addChild(Object x0, Object x1) {
        this.addChild(((Actor)x0), ((Actor)x1));
    }

    public void addDebugRectangle(TableLayout layout, Debug type, float x, float y, float w, float h) {
        TableToolkit.drawDebug = true;
        if(layout.debugRects == null) {
            layout.debugRects = new Array();
        }

        layout.debugRects.add(new DebugRect(type, x, layout.getTable().getHeight() - y, w, h));
    }

    public void addDebugRectangle(BaseTableLayout x0, Debug x1, float x2, float x3, float x4, float x5) {
        this.addDebugRectangle(x0, x1, x2, x3, x4, x5);
    }

    public void clearDebugRectangles(TableLayout layout) {
        if(layout.debugRects != null) {
            layout.debugRects.clear();
        }
    }

    public void clearDebugRectangles(BaseTableLayout x0) {
        this.clearDebugRectangles(((TableLayout)x0));
    }

    public void freeCell(Cell cell) {
        cell.free();
        TableToolkit.cellPool.free(cell);
    }

    public float getHeight(Actor widget) {
        return widget.getHeight();
    }

    public float getHeight(Object x0) {
        return this.getHeight(((Actor)x0));
    }

    public float getMaxHeight(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getMaxHeight();
        }
        else {
            v0 = 0f;
        }

        return v0;
    }

    public float getMaxHeight(Object x0) {
        return this.getMaxHeight(((Actor)x0));
    }

    public float getMaxWidth(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getMaxWidth();
        }
        else {
            v0 = 0f;
        }

        return v0;
    }

    public float getMaxWidth(Object x0) {
        return this.getMaxWidth(((Actor)x0));
    }

    public float getMinHeight(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getMinHeight();
        }
        else {
            v0 = actor.getHeight();
        }

        return v0;
    }

    public float getMinHeight(Object x0) {
        return this.getMinHeight(((Actor)x0));
    }

    public float getMinWidth(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getMinWidth();
        }
        else {
            v0 = actor.getWidth();
        }

        return v0;
    }

    public float getMinWidth(Object x0) {
        return this.getMinWidth(((Actor)x0));
    }

    public float getPrefHeight(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getPrefHeight();
        }
        else {
            v0 = actor.getHeight();
        }

        return v0;
    }

    public float getPrefHeight(Object x0) {
        return this.getPrefHeight(((Actor)x0));
    }

    public float getPrefWidth(Actor actor) {
        float v0;
        if((actor instanceof Layout)) {
            v0 = ((Layout)actor).getPrefWidth();
        }
        else {
            v0 = actor.getWidth();
        }

        return v0;
    }

    public float getPrefWidth(Object x0) {
        return this.getPrefWidth(((Actor)x0));
    }

    public float getWidth(Actor widget) {
        return widget.getWidth();
    }

    public float getWidth(Object x0) {
        return this.getWidth(((Actor)x0));
    }

    public Cell obtainCell(TableLayout layout) {
        Object v0 = TableToolkit.cellPool.obtain();
        ((Cell)v0).setLayout(((BaseTableLayout)layout));
        return ((Cell)v0);
    }

    public Cell obtainCell(BaseTableLayout x0) {
        return this.obtainCell(((TableLayout)x0));
    }

    public void removeChild(Actor parent, Actor child) {
        ((Group)parent).removeActor(child);
    }

    public void removeChild(Object x0, Object x1) {
        this.removeChild(((Actor)x0), ((Actor)x1));
    }
}

