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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener$ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pools;

public class List extends Widget implements Cullable {
    public class ListStyle {
        public ListStyle() {
            super();
            this.fontColorSelected = new Color(1f, 1f, 1f, 1f);
            this.fontColorUnselected = new Color(1f, 1f, 1f, 1f);
        }

        public ListStyle(BitmapFont font, Color fontColorSelected, Color fontColorUnselected, Drawable selection) {
            super();
            this.fontColorSelected = new Color(1f, 1f, 1f, 1f);
            this.fontColorUnselected = new Color(1f, 1f, 1f, 1f);
            this.font = font;
            this.fontColorSelected.set(fontColorSelected);
            this.fontColorUnselected.set(fontColorUnselected);
            this.selection = selection;
        }

        public ListStyle(ListStyle style) {
            super();
            this.fontColorSelected = new Color(1f, 1f, 1f, 1f);
            this.fontColorUnselected = new Color(1f, 1f, 1f, 1f);
            this.font = style.font;
            this.fontColorSelected.set(style.fontColorSelected);
            this.fontColorUnselected.set(style.fontColorUnselected);
            this.selection = style.selection;
        }
    }

    private Rectangle cullingArea;
    private float itemHeight;
    private String[] items;
    private float prefHeight;
    private float prefWidth;
    private boolean selectable;
    private int selectedIndex;
    private ListStyle style;
    private float textOffsetX;
    private float textOffsetY;

    public List(Object[] items, ListStyle style) {
        super();
        this.selectable = true;
        this.setStyle(style);
        this.setItems(items);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0 = false;
                if((pointer != 0 || button == 0) && (List.this.isSelectable())) {
                    List.this.touchDown(y);
                    v0 = true;
                }

                return v0;
            }
        });
    }

    public List(Object[] items, Skin skin) {
        this(items, skin.get(ListStyle.class));
    }

    public List(Object[] items, Skin skin, String styleName) {
        this(items, skin.get(styleName, ListStyle.class));
    }

    public void draw(Batch batch, float parentAlpha) {
        BitmapFont v7 = this.style.font;
        Drawable v0 = this.style.selection;
        Color v8 = this.style.fontColorSelected;
        Color v9 = this.style.fontColorUnselected;
        Color v6 = this.getColor();
        batch.setColor(v6.r, v6.g, v6.b, v6.a * parentAlpha);
        float v2 = this.getX();
        float v12 = this.getY();
        v7.setColor(v9.r, v9.g, v9.b, v9.a * parentAlpha);
        float v11 = this.getHeight();
        int v10;
        for(v10 = 0; v10 < this.items.length; ++v10) {
            if(this.cullingArea != null) {
                if(v11 - this.itemHeight <= this.cullingArea.y + this.cullingArea.height && v11 >= this.cullingArea.y) {
                    goto label_41;
                }

                if(v11 >= this.cullingArea.y) {
                    goto label_72;
                }

                return;
            }
            else {
            label_41:
                if(this.selectedIndex == v10) {
                    v0.draw(batch, v2, v12 + v11 - this.itemHeight, this.getWidth(), this.itemHeight);
                    v7.setColor(v8.r, v8.g, v8.b, v8.a * parentAlpha);
                }

                v7.draw(batch, this.items[v10], this.textOffsetX + v2, v12 + v11 - this.textOffsetY);
                if(this.selectedIndex != v10) {
                    goto label_72;
                }

                v7.setColor(v9.r, v9.g, v9.b, v9.a * parentAlpha);
            }

        label_72:
            v11 -= this.itemHeight;
        }
    }

    public float getItemHeight() {
        return this.itemHeight;
    }

    public String[] getItems() {
        return this.items;
    }

    public float getPrefHeight() {
        return this.prefHeight;
    }

    public float getPrefWidth() {
        return this.prefWidth;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public String getSelection() {
        String v0;
        if(this.items.length == 0 || this.selectedIndex == -1) {
            v0 = null;
        }
        else {
            v0 = this.items[this.selectedIndex];
        }

        return v0;
    }

    public ListStyle getStyle() {
        return this.style;
    }

    public boolean isSelectable() {
        return this.selectable;
    }

    public void setCullingArea(Rectangle cullingArea) {
        this.cullingArea = cullingArea;
    }

    public void setItems(Object[] objects) {
        int v2;
        if(objects == null) {
            throw new IllegalArgumentException("items cannot be null.");
        }

        if(!(objects instanceof String[])) {
            String[] v5 = new String[objects.length];
            v2 = 0;
            int v3 = objects.length;
            while(v2 < v3) {
                v5[v2] = String.valueOf(objects[v2]);
                ++v2;
            }

            this.items = v5;
        }
        else {
            this.items = ((String[])objects);
        }

        this.selectedIndex = 0;
        BitmapFont v1 = this.style.font;
        Drawable v4 = this.style.selection;
        this.itemHeight = v1.getCapHeight() - v1.getDescent() * 2f;
        this.itemHeight += v4.getTopHeight() + v4.getBottomHeight();
        this.textOffsetX = v4.getLeftWidth();
        this.textOffsetY = v4.getTopHeight() - v1.getDescent();
        this.prefWidth = 0f;
        for(v2 = 0; v2 < this.items.length; ++v2) {
            this.prefWidth = Math.max(v1.getBounds(this.items[v2]).width, this.prefWidth);
        }

        this.prefWidth += v4.getLeftWidth() + v4.getRightWidth();
        this.prefHeight = (((float)this.items.length)) * this.itemHeight;
        this.invalidateHierarchy();
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public void setSelectedIndex(int index) {
        if(index >= -1 && index < this.items.length) {
            this.selectedIndex = index;
            return;
        }

        throw new GdxRuntimeException("index must be >= -1 and < " + this.items.length + ": " + index);
    }

    public int setSelection(String item) {
        this.selectedIndex = -1;
        int v0 = 0;
        int v1 = this.items.length;
        while(v0 < v1) {
            if(this.items[v0].equals(item)) {
                this.selectedIndex = v0;
            }
            else {
                ++v0;
                continue;
            }

            break;
        }

        return this.selectedIndex;
    }

    public void setStyle(ListStyle style) {
        if(style == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }

        this.style = style;
        if(this.items != null) {
            this.setItems(this.items);
        }
        else {
            this.invalidateHierarchy();
        }
    }

    void touchDown(float y) {
        int v1 = this.selectedIndex;
        this.selectedIndex = ((int)((this.getHeight() - y) / this.itemHeight));
        this.selectedIndex = Math.max(0, this.selectedIndex);
        this.selectedIndex = Math.min(this.items.length - 1, this.selectedIndex);
        if(v1 != this.selectedIndex) {
            Object v0 = Pools.obtain(ChangeEvent.class);
            if(this.fire(((Event)v0))) {
                this.selectedIndex = v1;
            }

            Pools.free(v0);
        }
    }
}

