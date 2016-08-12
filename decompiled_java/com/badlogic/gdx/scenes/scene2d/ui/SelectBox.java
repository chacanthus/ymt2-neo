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
import com.badlogic.gdx.graphics.g2d.BitmapFont$TextBounds;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener$ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;

public class SelectBox extends Widget implements Disableable {
    class ListScroll extends ScrollPane {
        final List list;
        final Vector2 screenCoords;

        public ListScroll(SelectBox arg4) {
            SelectBox.this = arg4;
            super(null, arg4.style.scrollStyle);
            this.screenCoords = new Vector2();
            this.setOverscroll(false, false);
            this.setFadeScrollBars(false);
            this.list = new List(new Object[0], arg4.style.listStyle);
            this.setWidget(this.list);
            this.list.addListener(new InputListener() {
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    this.this$1.list.setSelectedIndex(Math.min(this.this$1.this$0.items.length - 1, ((int)((this.this$1.list.getHeight() - y) / this.this$1.list.getItemHeight()))));
                    return 1;
                }
            });
            this.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    boolean v0;
                    if(event.getTarget() == this.this$1.list) {
                        v0 = true;
                    }
                    else {
                        this.this$1.this$0.hideList();
                        v0 = false;
                    }

                    return v0;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if(this.this$1.hit(x, y, true) == this.this$1.list) {
                        this.this$1.this$0.setSelection(this.this$1.list.getSelectedIndex());
                        Object v0 = Pools.obtain(ChangeEvent.class);
                        this.this$1.this$0.fire(((Event)v0));
                        Pools.free(v0);
                        this.this$1.this$0.hideList();
                    }
                }
            });
        }

        public void act(float delta) {
            super.act(delta);
            SelectBox.this.localToStageCoordinates(SelectBox.tmpCoords.set(0f, 0f));
            if(SelectBox.tmpCoords.x != this.screenCoords.x || SelectBox.tmpCoords.y != this.screenCoords.y) {
                SelectBox.this.hideList();
            }
        }

        public Actor hit(float x, float y, boolean touchable) {
            Actor v0 = super.hit(x, y, touchable);
            if(v0 == null) {
                ListScroll v0_1 = this;
            }

            return v0;
        }

        public void show(Stage stage) {
            int v7;
            stage.addActor(((Actor)this));
            SelectBox.this.localToStageCoordinates(SelectBox.tmpCoords.set(0f, 0f));
            this.screenCoords.set(SelectBox.tmpCoords);
            this.list.setItems(SelectBox.this.items);
            this.list.setSelectedIndex(SelectBox.this.selectedIndex);
            float v6 = this.list.getItemHeight();
            if(SelectBox.this.maxListCount <= 0) {
                v7 = SelectBox.this.items.length;
            }
            else {
                v7 = Math.min(SelectBox.this.maxListCount, SelectBox.this.items.length);
            }

            float v3 = v6 * (((float)v7));
            Drawable v1 = this.getStyle().background;
            if(v1 != null) {
                v3 += v1.getTopHeight() + v1.getBottomHeight();
            }

            float v5 = SelectBox.tmpCoords.y;
            float v4 = stage.getCamera().viewportHeight - SelectBox.tmpCoords.y - SelectBox.this.getHeight();
            int v2 = 1;
            if(v3 > v5) {
                if(v4 > v5) {
                    v2 = 0;
                    v3 = Math.min(v3, v4);
                }
                else {
                    v3 = v5;
                }
            }

            if(v2 != 0) {
                this.setY(SelectBox.tmpCoords.y - v3);
            }
            else {
                this.setY(SelectBox.tmpCoords.y + SelectBox.this.getHeight());
            }

            this.setX(SelectBox.tmpCoords.x);
            this.setWidth(SelectBox.this.getWidth());
            this.setHeight(v3);
            this.scrollToCenter(0f, this.list.getHeight() - (((float)SelectBox.this.selectedIndex)) * v6 - v6 / 2f, 0f, 0f);
            this.updateVisualScroll();
            this.clearActions();
            this.getColor().a = 0f;
            this.addAction(Actions.fadeIn(0.3f, Interpolation.fade));
            SelectBox.this.previousScrollFocus = null;
            Actor v0 = stage.getScrollFocus();
            if(v0 != null && !v0.isDescendantOf(((Actor)this))) {
                SelectBox.this.previousScrollFocus = v0;
            }

            stage.setScrollFocus(((Actor)this));
        }
    }

    public class SelectBoxStyle {
        public SelectBoxStyle() {
            super();
            this.fontColor = new Color(1f, 1f, 1f, 1f);
        }

        public SelectBoxStyle(BitmapFont font, Color fontColor, Drawable background, ScrollPaneStyle scrollStyle, ListStyle listStyle) {
            super();
            this.fontColor = new Color(1f, 1f, 1f, 1f);
            this.font = font;
            this.fontColor.set(fontColor);
            this.background = background;
            this.scrollStyle = scrollStyle;
            this.listStyle = listStyle;
        }

        public SelectBoxStyle(SelectBoxStyle style) {
            super();
            this.fontColor = new Color(1f, 1f, 1f, 1f);
            this.font = style.font;
            this.fontColor.set(style.fontColor);
            if(style.disabledFontColor != null) {
                this.disabledFontColor = new Color(style.disabledFontColor);
            }

            this.background = style.background;
            this.backgroundOver = style.backgroundOver;
            this.backgroundOpen = style.backgroundOpen;
            this.backgroundDisabled = style.backgroundDisabled;
            this.scrollStyle = new ScrollPaneStyle(style.scrollStyle);
            this.listStyle = new ListStyle(style.listStyle);
        }
    }

    private final TextBounds bounds;
    private ClickListener clickListener;
    boolean disabled;
    String[] items;
    int maxListCount;
    private float prefHeight;
    private float prefWidth;
    Actor previousScrollFocus;
    ListScroll scroll;
    int selectedIndex;
    SelectBoxStyle style;
    static final Vector2 tmpCoords;

    static  {
        SelectBox.tmpCoords = new Vector2();
    }

    public SelectBox(Object[] items, SelectBoxStyle style) {
        super();
        this.selectedIndex = 0;
        this.bounds = new TextBounds();
        this.setStyle(style);
        this.setItems(items);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        com.badlogic.gdx.scenes.scene2d.ui.SelectBox$1 v0 = new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v1 = false;
                if((pointer != 0 || button == 0) && !SelectBox.this.disabled) {
                    Stage v0 = SelectBox.this.getStage();
                    if(SelectBox.this.scroll == null) {
                        SelectBox.this.scroll = new ListScroll(SelectBox.this);
                    }

                    SelectBox.this.scroll.show(v0);
                    v1 = true;
                }

                return v1;
            }
        };
        this.clickListener = ((ClickListener)v0);
        this.addListener(((EventListener)v0));
    }

    public SelectBox(Object[] items, Skin skin) {
        this(items, skin.get(SelectBoxStyle.class));
    }

    public SelectBox(Object[] items, Skin skin, String styleName) {
        this(items, skin.get(styleName, SelectBoxStyle.class));
    }

    public void draw(Batch batch, float parentAlpha) {
        Color v16;
        Drawable v1;
        if(!this.disabled || this.style.backgroundDisabled == null) {
            if(this.scroll != null && this.scroll.getParent() != null && this.style.backgroundOpen != null) {
                v1 = this.style.backgroundOpen;
                goto label_10;
            }

            if((this.clickListener.isOver()) && this.style.backgroundOver != null) {
                v1 = this.style.backgroundOver;
                goto label_10;
            }

            v1 = this.style.background;
        }
        else {
            v1 = this.style.backgroundDisabled;
        }

    label_10:
        BitmapFont v7 = this.style.font;
        if(!this.disabled || this.style.disabledFontColor == null) {
            v16 = this.style.fontColor;
        }
        else {
            v16 = this.style.disabledFontColor;
        }

        Color v15 = this.getColor();
        float v3 = this.getX();
        float v4 = this.getY();
        float v5 = this.getWidth();
        float v6 = this.getHeight();
        batch.setColor(v15.r, v15.g, v15.b, v15.a * parentAlpha);
        v1.draw(batch, v3, v4, v5, v6);
        if(this.items.length > 0) {
            int v13 = v7.computeVisibleGlyphs(this.items[this.selectedIndex], 0, this.items[this.selectedIndex].length(), v5 - v1.getLeftWidth() - v1.getRightWidth());
            this.bounds.set(v7.getBounds(this.items[this.selectedIndex]));
            float v17 = ((float)(((int)((v6 - (v1.getBottomHeight() + v1.getTopHeight())) / 2f + v1.getBottomHeight() + this.bounds.height / 2f))));
            v7.setColor(v16.r, v16.g, v16.b, v16.a * parentAlpha);
            v7.draw(batch, this.items[this.selectedIndex], v3 + v1.getLeftWidth(), v4 + v17, 0, v13);
        }
    }

    public String[] getItems() {
        return this.items;
    }

    public List getList() {
        List v0;
        if(this.scroll == null || this.scroll.getParent() == null) {
            v0 = null;
        }
        else {
            v0 = this.scroll.list;
        }

        return v0;
    }

    public int getMaxListCount() {
        return this.maxListCount;
    }

    public float getPrefHeight() {
        return this.prefHeight;
    }

    public float getPrefWidth() {
        return this.prefWidth;
    }

    public String getSelection() {
        return this.items[this.selectedIndex];
    }

    public int getSelectionIndex() {
        return this.selectedIndex;
    }

    public SelectBoxStyle getStyle() {
        return this.style;
    }

    public void hideList() {
        if(this.scroll != null && this.scroll.getParent() != null) {
            Stage v1 = this.scroll.getStage();
            if(v1 != null) {
                if(this.previousScrollFocus != null && this.previousScrollFocus.getStage() == null) {
                    this.previousScrollFocus = null;
                }

                Actor v0 = v1.getScrollFocus();
                if(v0 != null && !v0.isDescendantOf(this.scroll)) {
                    goto label_23;
                }

                v1.setScrollFocus(this.previousScrollFocus);
            }

        label_23:
            this.scroll.addAction(Actions.sequence(Actions.fadeOut(0.15f, Interpolation.fade), Actions.removeActor()));
        }
    }

    public void setDisabled(boolean disabled) {
        if((disabled) && !this.disabled) {
            this.hideList();
        }

        this.disabled = disabled;
    }

    public void setItems(Object[] objects) {
        float v9;
        String[] v15_1;
        int v3;
        float v10 = 0f;
        if(objects == null) {
            throw new IllegalArgumentException("items cannot be null.");
        }

        if(!(objects instanceof String[])) {
            String[] v8 = new String[objects.length];
            v3 = 0;
            int v6 = objects.length;
            while(v3 < v6) {
                v8[v3] = String.valueOf(objects[v3]);
                ++v3;
            }

            v15_1 = v8;
        }

        this.items = v15_1;
        this.selectedIndex = 0;
        Drawable v0 = this.style.background;
        BitmapFont v2 = this.style.font;
        this.prefHeight = Math.max(v0.getTopHeight() + v0.getBottomHeight() + v2.getCapHeight() - v2.getDescent() * 2f, v0.getMinHeight());
        float v5 = 0f;
        for(v3 = 0; v3 < this.items.length; ++v3) {
            v5 = Math.max(v2.getBounds(this.items[v3]).width, v5);
        }

        this.prefWidth = v0.getLeftWidth() + v0.getRightWidth() + v5;
        ListStyle v4 = this.style.listStyle;
        ScrollPaneStyle v7 = this.style.scrollStyle;
        float v11 = this.prefWidth;
        float v12 = v4.selection.getRightWidth() + (v7.background.getLeftWidth() + v5 + v7.background.getRightWidth() + v4.selection.getLeftWidth());
        if(this.style.scrollStyle.vScroll != null) {
            v9 = this.style.scrollStyle.vScroll.getMinWidth();
        }
        else {
            v9 = 0f;
        }

        if(this.style.scrollStyle.vScrollKnob != null) {
            v10 = this.style.scrollStyle.vScrollKnob.getMinWidth();
        }

        this.prefWidth = Math.max(v11, Math.max(v9, v10) + v12);
        if(this.items.length > 0) {
            Object v1 = Pools.obtain(ChangeEvent.class);
            this.fire(((Event)v1));
            Pools.free(v1);
        }

        this.invalidateHierarchy();
    }

    public void setMaxListCount(int maxListCount) {
        this.maxListCount = maxListCount;
    }

    public void setSelection(int selection) {
        if(selection < 0) {
            throw new IllegalArgumentException("selection cannot be < 0.");
        }

        this.selectedIndex = selection;
    }

    public void setSelection(String item) {
        int v0;
        for(v0 = 0; v0 < this.items.length; ++v0) {
            if(this.items[v0].equals(item)) {
                this.selectedIndex = v0;
            }
        }
    }

    public void setStyle(SelectBoxStyle style) {
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
}

