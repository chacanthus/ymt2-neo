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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.tablelayout.BaseTableLayout$Debug;
import com.esotericsoftware.tablelayout.Cell;
import com.esotericsoftware.tablelayout.Toolkit;
import com.esotericsoftware.tablelayout.Value;
import java.util.List;

public class Table extends WidgetGroup {
    private Drawable background;
    private boolean clip;
    private final TableLayout layout;
    private Skin skin;

    static  {
        if(Toolkit.instance == null) {
            Toolkit.instance = new TableToolkit();
        }
    }

    public Table() {
        this(null);
    }

    public Table(Skin skin) {
        super();
        this.skin = skin;
        this.layout = new TableLayout();
        this.layout.setTable(this);
        this.setTransform(false);
        this.setTouchable(Touchable.childrenOnly);
    }

    public Cell add(Actor actor) {
        return this.layout.add(actor);
    }

    public Cell add() {
        return this.layout.add(null);
    }

    public Cell add(String text) {
        if(this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }

        return this.add(new Label(((CharSequence)text), this.skin));
    }

    public Cell add(String text, String labelStyleName) {
        if(this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }

        return this.add(new Label(((CharSequence)text), this.skin.get(labelStyleName, LabelStyle.class)));
    }

    public Cell add(String text, String fontName, Color color) {
        if(this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }

        return this.add(new Label(((CharSequence)text), new LabelStyle(this.skin.getFont(fontName), color)));
    }

    public Cell add(String text, String fontName, String colorName) {
        if(this.skin == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }

        return this.add(new Label(((CharSequence)text), new LabelStyle(this.skin.getFont(fontName), this.skin.getColor(colorName))));
    }

    public void add(Actor[] actors) {
        int v0 = 0;
        int v1 = actors.length;
        while(v0 < v1) {
            this.layout.add(actors[v0]);
            ++v0;
        }
    }

    public Table align(int align) {
        this.layout.align(align);
        return this;
    }

    public Table bottom() {
        this.layout.bottom();
        return this;
    }

    public Table center() {
        this.layout.center();
        return this;
    }

    public void clearChildren() {
        super.clearChildren();
        this.layout.clear();
        this.invalidate();
    }

    public Cell columnDefaults(int column) {
        return this.layout.columnDefaults(column);
    }

    public Table debug() {
        this.layout.debug();
        return this;
    }

    public Table debug(Debug debug) {
        this.layout.debug(debug);
        return this;
    }

    public Table debugCell() {
        this.layout.debugCell();
        return this;
    }

    public Table debugTable() {
        this.layout.debugTable();
        return this;
    }

    public Table debugWidget() {
        this.layout.debugWidget();
        return this;
    }

    public Cell defaults() {
        return this.layout.defaults();
    }

    public void draw(Batch batch, float parentAlpha) {
        boolean v0;
        this.validate();
        this.drawBackground(batch, parentAlpha);
        if(this.isTransform()) {
            this.applyTransform(batch, this.computeTransform());
            if(this.clip) {
                if(this.background == null) {
                    v0 = this.clipBegin(0f, 0f, this.getWidth(), this.getHeight());
                }
                else {
                    v0 = this.clipBegin(this.layout.getPadLeft(), this.layout.getPadBottom(), this.getWidth() - this.layout.getPadLeft() - this.layout.getPadRight(), this.getHeight() - this.layout.getPadBottom() - this.layout.getPadTop());
                }

                if(!v0) {
                    goto label_17;
                }

                this.drawChildren(batch, parentAlpha);
                this.clipEnd();
            }
            else {
                this.drawChildren(batch, parentAlpha);
            }

        label_17:
            this.resetTransform(batch);
        }
        else {
            super.draw(batch, parentAlpha);
        }
    }

    protected void drawBackground(Batch batch, float parentAlpha) {
        if(this.background != null) {
            Color v6 = this.getColor();
            batch.setColor(v6.r, v6.g, v6.b, v6.a * parentAlpha);
            this.background.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    public static void drawDebug(Stage stage) {
        if(TableToolkit.drawDebug) {
            Table.drawDebug(stage.getActors(), stage.getSpriteBatch());
        }
    }

    private static void drawDebug(Array arg4, Batch batch) {
        int v1 = 0;
        int v2 = arg4.size;
        while(v1 < v2) {
            Object v0 = arg4.get(v1);
            if(((Actor)v0).isVisible()) {
                if((v0 instanceof Table)) {
                    v0.layout.drawDebug(batch);
                }

                if(!(v0 instanceof Group)) {
                    goto label_6;
                }

                Table.drawDebug(((Group)v0).getChildren(), batch);
            }

        label_6:
            ++v1;
        }
    }

    public int getAlign() {
        return this.layout.getAlign();
    }

    public Drawable getBackground() {
        return this.background;
    }

    public Cell getCell(Actor actor) {
        return this.layout.getCell(actor);
    }

    public List getCells() {
        return this.layout.getCells();
    }

    public boolean getClip() {
        return this.clip;
    }

    public Debug getDebug() {
        return this.layout.getDebug();
    }

    public float getMinHeight() {
        return this.layout.getMinHeight();
    }

    public float getMinWidth() {
        return this.layout.getMinWidth();
    }

    public float getPadBottom() {
        return this.layout.getPadBottom();
    }

    public Value getPadBottomValue() {
        return this.layout.getPadBottomValue();
    }

    public float getPadLeft() {
        return this.layout.getPadLeft();
    }

    public Value getPadLeftValue() {
        return this.layout.getPadLeftValue();
    }

    public float getPadRight() {
        return this.layout.getPadRight();
    }

    public Value getPadRightValue() {
        return this.layout.getPadRightValue();
    }

    public float getPadTop() {
        return this.layout.getPadTop();
    }

    public Value getPadTopValue() {
        return this.layout.getPadTopValue();
    }

    public float getPadX() {
        return this.layout.getPadLeft() + this.layout.getPadRight();
    }

    public float getPadY() {
        return this.layout.getPadTop() + this.layout.getPadBottom();
    }

    public float getPrefHeight() {
        float v0;
        if(this.background != null) {
            v0 = Math.max(this.layout.getPrefHeight(), this.background.getMinHeight());
        }
        else {
            v0 = this.layout.getPrefHeight();
        }

        return v0;
    }

    public float getPrefWidth() {
        float v0;
        if(this.background != null) {
            v0 = Math.max(this.layout.getPrefWidth(), this.background.getMinWidth());
        }
        else {
            v0 = this.layout.getPrefWidth();
        }

        return v0;
    }

    public int getRow(float y) {
        return this.layout.getRow(y);
    }

    public Actor hit(float x, float y, boolean touchable) {
        Actor v0 = null;
        if(this.clip) {
            if((touchable) && this.getTouchable() == Touchable.disabled) {
                goto label_8;
            }

            if(x < 0f) {
                goto label_8;
            }

            if(x >= this.getWidth()) {
                goto label_8;
            }

            if(y < 0f) {
                goto label_8;
            }

            if(y >= this.getHeight()) {
                goto label_8;
            }

            goto label_15;
        }
        else {
        label_15:
            v0 = super.hit(x, y, touchable);
        }

    label_8:
        return v0;
    }

    public void invalidate() {
        this.layout.invalidate();
        super.invalidate();
    }

    public void layout() {
        this.layout.layout();
    }

    public Table left() {
        this.layout.left();
        return this;
    }

    public Table pad(float pad) {
        this.layout.pad(pad);
        return this;
    }

    public Table pad(float top, float left, float bottom, float right) {
        this.layout.pad(top, left, bottom, right);
        return this;
    }

    public Table pad(Value pad) {
        this.layout.pad(pad);
        return this;
    }

    public Table pad(Value top, Value left, Value bottom, Value right) {
        this.layout.pad(top, left, bottom, right);
        return this;
    }

    public Table padBottom(float padBottom) {
        this.layout.padBottom(padBottom);
        return this;
    }

    public Table padBottom(Value padBottom) {
        this.layout.padBottom(padBottom);
        return this;
    }

    public Table padLeft(float padLeft) {
        this.layout.padLeft(padLeft);
        return this;
    }

    public Table padLeft(Value padLeft) {
        this.layout.padLeft(padLeft);
        return this;
    }

    public Table padRight(float padRight) {
        this.layout.padRight(padRight);
        return this;
    }

    public Table padRight(Value padRight) {
        this.layout.padRight(padRight);
        return this;
    }

    public Table padTop(float padTop) {
        this.layout.padTop(padTop);
        return this;
    }

    public Table padTop(Value padTop) {
        this.layout.padTop(padTop);
        return this;
    }

    public boolean removeActor(Actor actor) {
        boolean v1;
        if(!super.removeActor(actor)) {
            v1 = false;
        }
        else {
            Cell v0 = this.getCell(actor);
            if(v0 != null) {
                v0.setWidget(null);
            }

            v1 = true;
        }

        return v1;
    }

    public void reset() {
        this.layout.reset();
    }

    public Table right() {
        this.layout.right();
        return this;
    }

    public Cell row() {
        return this.layout.row();
    }

    public void setBackground(Drawable background) {
        if(this.background != background) {
            this.background = background;
            if(background == null) {
                this.pad(null);
            }
            else {
                this.padBottom(background.getBottomHeight());
                this.padTop(background.getTopHeight());
                this.padLeft(background.getLeftWidth());
                this.padRight(background.getRightWidth());
                this.invalidate();
            }
        }
    }

    public void setBackground(String drawableName) {
        this.setBackground(this.skin.getDrawable(drawableName));
    }

    public void setClip(boolean enabled) {
        this.clip = enabled;
        this.setTransform(enabled);
        this.invalidate();
    }

    public void setRound(boolean round) {
        this.layout.round = round;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Cell stack(Actor[] actors) {
        Stack v2 = new Stack();
        if(actors != null) {
            int v0 = 0;
            int v1 = actors.length;
            while(v0 < v1) {
                v2.addActor(actors[v0]);
                ++v0;
            }
        }

        return this.add(((Actor)v2));
    }

    public Table top() {
        this.layout.top();
        return this;
    }
}

