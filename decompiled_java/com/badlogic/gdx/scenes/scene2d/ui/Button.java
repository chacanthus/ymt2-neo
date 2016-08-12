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
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener$ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

public class Button extends Table implements Disableable {
    public class ButtonStyle {
        public Drawable checked;
        public Drawable checkedOver;
        public Drawable disabled;
        public Drawable down;
        public Drawable over;
        public float pressedOffsetX;
        public float pressedOffsetY;
        public float unpressedOffsetX;
        public float unpressedOffsetY;
        public Drawable up;

        public ButtonStyle(Drawable up, Drawable down, Drawable checked) {
            super();
            this.up = up;
            this.down = down;
            this.checked = checked;
        }

        public ButtonStyle() {
            super();
        }

        public ButtonStyle(ButtonStyle style) {
            super();
            this.up = style.up;
            this.down = style.down;
            this.over = style.over;
            this.checked = style.checked;
            this.checkedOver = style.checkedOver;
            this.disabled = style.disabled;
            this.pressedOffsetX = style.pressedOffsetX;
            this.pressedOffsetY = style.pressedOffsetY;
            this.unpressedOffsetX = style.unpressedOffsetX;
            this.unpressedOffsetY = style.unpressedOffsetY;
        }
    }

    ButtonGroup buttonGroup;
    private ClickListener clickListener;
    boolean isChecked;
    boolean isDisabled;
    private ButtonStyle style;

    public Button() {
        super();
        this.initialize();
    }

    public Button(Actor child, ButtonStyle style) {
        super();
        this.initialize();
        this.add(child);
        this.setStyle(style);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Actor child, Skin skin) {
        this(child, skin.get(ButtonStyle.class));
    }

    public Button(Actor child, Skin skin, String styleName) {
        this(child, skin.get(styleName, ButtonStyle.class));
    }

    public Button(ButtonStyle style) {
        super();
        this.initialize();
        this.setStyle(style);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Skin skin) {
        super(skin);
        this.initialize();
        this.setStyle(skin.get(ButtonStyle.class));
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Skin skin, String styleName) {
        super(skin);
        this.initialize();
        this.setStyle(skin.get(styleName, ButtonStyle.class));
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Button(Drawable up) {
        this(new ButtonStyle(up, null, null));
    }

    public Button(Drawable up, Drawable down) {
        this(new ButtonStyle(up, down, null));
    }

    public Button(Drawable up, Drawable down, Drawable checked) {
        this(new ButtonStyle(up, down, checked));
    }

    public void draw(Batch batch, float parentAlpha) {
        float v10;
        float v9;
        Drawable v0;
        this.validate();
        if(!this.isPressed() || (this.isDisabled)) {
            if(!this.isDisabled || this.style.disabled == null) {
                if((this.isChecked) && this.style.checked != null) {
                    if((this.isOver()) && this.style.checkedOver != null) {
                        v0 = this.style.checkedOver;
                        goto label_49;
                    }

                    v0 = this.style.checked;
                    goto label_49;
                }

                if((this.isOver()) && this.style.over != null) {
                    v0 = this.style.over;
                    goto label_49;
                }

                v0 = this.style.up;
            }
            else {
                v0 = this.style.disabled;
            }

        label_49:
            v9 = this.style.unpressedOffsetX;
            v10 = this.style.unpressedOffsetY;
        }
        else {
            if(this.style.down == null) {
                v0 = this.style.up;
            }
            else {
                v0 = this.style.down;
            }

            v9 = this.style.pressedOffsetX;
            v10 = this.style.pressedOffsetY;
        }

        if(v0 != null) {
            Color v7 = this.getColor();
            batch.setColor(v7.r, v7.g, v7.b, v7.a * parentAlpha);
            v0.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }

        SnapshotArray v6 = this.getChildren();
        int v8;
        for(v8 = 0; v8 < ((Array)v6).size; ++v8) {
            ((Array)v6).get(v8).translate(v9, v10);
        }

        super.draw(batch, parentAlpha);
        for(v8 = 0; v8 < ((Array)v6).size; ++v8) {
            ((Array)v6).get(v8).translate(-v9, -v10);
        }
    }

    protected void drawBackground(Batch batch, float parentAlpha) {
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    public float getMinHeight() {
        return this.getPrefHeight();
    }

    public float getMinWidth() {
        return this.getPrefWidth();
    }

    public float getPrefHeight() {
        float v0 = super.getPrefHeight();
        if(this.style.up != null) {
            v0 = Math.max(v0, this.style.up.getMinHeight());
        }

        if(this.style.down != null) {
            v0 = Math.max(v0, this.style.down.getMinHeight());
        }

        if(this.style.checked != null) {
            v0 = Math.max(v0, this.style.checked.getMinHeight());
        }

        return v0;
    }

    public float getPrefWidth() {
        float v0 = super.getPrefWidth();
        if(this.style.up != null) {
            v0 = Math.max(v0, this.style.up.getMinWidth());
        }

        if(this.style.down != null) {
            v0 = Math.max(v0, this.style.down.getMinWidth());
        }

        if(this.style.checked != null) {
            v0 = Math.max(v0, this.style.checked.getMinWidth());
        }

        return v0;
    }

    public ButtonStyle getStyle() {
        return this.style;
    }

    private void initialize() {
        this.setTouchable(Touchable.enabled);
        com.badlogic.gdx.scenes.scene2d.ui.Button$1 v0 = new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                boolean v0;
                if(!Button.this.isDisabled) {
                    Button v1 = Button.this;
                    if(!Button.this.isChecked) {
                        v0 = true;
                    }
                    else {
                        v0 = false;
                    }

                    v1.setChecked(v0);
                }
            }
        };
        this.clickListener = ((ClickListener)v0);
        this.addListener(((EventListener)v0));
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isDisabled() {
        return this.isDisabled;
    }

    public boolean isOver() {
        return this.clickListener.isOver();
    }

    public boolean isPressed() {
        return this.clickListener.isPressed();
    }

    public void setChecked(boolean isChecked) {
        boolean v1;
        if(this.isChecked != isChecked && (this.buttonGroup == null || (this.buttonGroup.canCheck(this, isChecked)))) {
            this.isChecked = isChecked;
            if(this.isDisabled) {
                return;
            }

            Object v0 = Pools.obtain(ChangeEvent.class);
            if(this.fire(((Event)v0))) {
                if(!isChecked) {
                    v1 = true;
                }
                else {
                    v1 = false;
                }

                this.isChecked = v1;
            }

            Pools.free(v0);
        }
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public void setStyle(ButtonStyle style) {
        if(style == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }

        this.style = style;
        Drawable v0 = style.up;
        if(v0 == null) {
            v0 = style.down;
            if(v0 == null) {
                v0 = style.checked;
            }
        }

        if(v0 != null) {
            this.padBottom(v0.getBottomHeight());
            this.padTop(v0.getTopHeight());
            this.padLeft(v0.getLeftWidth());
            this.padRight(v0.getRightWidth());
        }

        this.invalidateHierarchy();
    }

    public void toggle() {
        boolean v0;
        if(!this.isChecked) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        this.setChecked(v0);
    }
}

