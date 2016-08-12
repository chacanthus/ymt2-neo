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
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener$ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;

public class Slider extends Widget implements Disableable {
    public class SliderStyle {
        public SliderStyle() {
            super();
        }

        public SliderStyle(SliderStyle style) {
            super();
            this.background = style.background;
            this.knob = style.knob;
        }

        public SliderStyle(Drawable background, Drawable knob) {
            super();
            this.background = background;
            this.knob = knob;
        }
    }

    private float animateDuration;
    private float animateFromValue;
    private Interpolation animateInterpolation;
    private float animateTime;
    boolean disabled;
    int draggingPointer;
    private float max;
    private float min;
    private float sliderPos;
    private float[] snapValues;
    private float stepSize;
    private SliderStyle style;
    private float threshold;
    private float value;
    private final boolean vertical;

    public Slider(float min, float max, float stepSize, boolean vertical, Skin skin) {
        String v0;
        StringBuilder v1 = new StringBuilder().append("default-");
        if(vertical) {
            v0 = "vertical";
        }
        else {
            v0 = "horizontal";
        }

        this(min, max, stepSize, vertical, skin.get(v1.append(v0).toString(), SliderStyle.class));
    }

    public Slider(float min, float max, float stepSize, boolean vertical, SliderStyle style) {
        super();
        this.draggingPointer = -1;
        this.animateInterpolation = Interpolation.linear;
        if(min > max) {
            throw new IllegalArgumentException("min must be > max: " + min + " > " + max);
        }

        if(stepSize <= 0f) {
            throw new IllegalArgumentException("stepSize must be > 0: " + stepSize);
        }

        this.setStyle(style);
        this.min = min;
        this.max = max;
        this.stepSize = stepSize;
        this.vertical = vertical;
        this.value = min;
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
        this.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0 = false;
                if(!Slider.this.disabled && Slider.this.draggingPointer == -1) {
                    Slider.this.draggingPointer = pointer;
                    Slider.this.calculatePositionAndValue(x, y);
                    v0 = true;
                }

                return v0;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Slider.this.calculatePositionAndValue(x, y);
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(pointer == Slider.this.draggingPointer) {
                    Slider.this.draggingPointer = -1;
                    if(!Slider.this.calculatePositionAndValue(x, y)) {
                        Object v0 = Pools.obtain(ChangeEvent.class);
                        Slider.this.fire(((Event)v0));
                        Pools.free(v0);
                    }
                }
            }
        });
    }

    public Slider(float min, float max, float stepSize, boolean vertical, Skin skin, String styleName) {
        this(min, max, stepSize, vertical, skin.get(styleName, SliderStyle.class));
    }

    public void act(float delta) {
        super.act(delta);
        this.animateTime -= delta;
    }

    boolean calculatePositionAndValue(float x, float y) {
        float v4;
        float v7;
        float v3;
        Drawable v0;
        Drawable v2;
        if(!this.disabled || this.style.disabledKnob == null) {
            v2 = this.style.knob;
        }
        else {
            v2 = this.style.disabledKnob;
        }

        if(!this.disabled || this.style.disabledBackground == null) {
            v0 = this.style.background;
        }
        else {
            v0 = this.style.disabledBackground;
        }

        float v5 = this.sliderPos;
        if(this.vertical) {
            float v1 = this.getHeight() - v0.getTopHeight() - v0.getBottomHeight();
            if(v2 == null) {
                v3 = 0f;
            }
            else {
                v3 = v2.getMinHeight();
            }

            this.sliderPos = y - v0.getBottomHeight() - 0.5f * v3;
            v7 = this.min + (this.max - this.min) * (this.sliderPos / (v1 - v3));
            this.sliderPos = Math.max(0f, this.sliderPos);
            this.sliderPos = Math.min(v1 - v3, this.sliderPos);
        }
        else {
            float v9 = this.getWidth() - v0.getLeftWidth() - v0.getRightWidth();
            if(v2 == null) {
                v4 = 0f;
            }
            else {
                v4 = v2.getMinWidth();
            }

            this.sliderPos = x - v0.getLeftWidth() - 0.5f * v4;
            v7 = this.min + (this.max - this.min) * (this.sliderPos / (v9 - v4));
            this.sliderPos = Math.max(0f, this.sliderPos);
            this.sliderPos = Math.min(v9 - v4, this.sliderPos);
        }

        boolean v8 = this.setValue(v7);
        if(v7 == v7) {
            this.sliderPos = v5;
        }

        return v8;
    }

    protected float clamp(float value) {
        return MathUtils.clamp(value, this.min, this.max);
    }

    public void draw(Batch batch, float parentAlpha) {
        float v19;
        float v20;
        float v18;
        Drawable v17;
        Drawable v7;
        Drawable v1;
        Drawable v16;
        SliderStyle v23 = this.style;
        boolean v15 = this.disabled;
        if(!v15 || v23.disabledKnob == null) {
            v16 = v23.knob;
        }
        else {
            v16 = v23.disabledKnob;
        }

        if(!v15 || v23.disabledBackground == null) {
            v1 = v23.background;
        }
        else {
            v1 = v23.disabledBackground;
        }

        if(!v15 || v23.disabledKnobBefore == null) {
            v7 = v23.knobBefore;
        }
        else {
            v7 = v23.disabledKnobBefore;
        }

        if(!v15 || v23.disabledKnobAfter == null) {
            v17 = v23.knobAfter;
        }
        else {
            v17 = v23.disabledKnobAfter;
        }

        Color v14 = this.getColor();
        float v26 = this.getX();
        float v4 = this.getY();
        float v25 = this.getWidth();
        float v6 = this.getHeight();
        if(v16 == null) {
            v18 = 0f;
        }
        else {
            v18 = v16.getMinHeight();
        }

        if(v16 == null) {
            v20 = 0f;
        }
        else {
            v20 = v16.getMinWidth();
        }

        float v24 = this.getVisualValue();
        batch.setColor(v14.r, v14.g, v14.b, v14.a * parentAlpha);
        if(this.vertical) {
            v1.draw(batch, v26 + (((float)(((int)((v25 - v1.getMinWidth()) * 0.5f))))), v4, v1.getMinWidth(), v6);
            float v21 = v6 - (v1.getTopHeight() + v1.getBottomHeight());
            if(this.min != this.max) {
                this.sliderPos = (v24 - this.min) / (this.max - this.min) * (v21 - v18);
                this.sliderPos = Math.max(0f, this.sliderPos);
                this.sliderPos = Math.min(v21 - v18, this.sliderPos) + v1.getBottomHeight();
            }

            v19 = v18 * 0.5f;
            if(v7 != null) {
                v7.draw(batch, v26 + (((float)(((int)((v25 - v7.getMinWidth()) * 0.5f))))), v4, v7.getMinWidth(), ((float)(((int)(this.sliderPos + v19)))));
            }

            if(v17 != null) {
                v17.draw(batch, v26 + (((float)(((int)((v25 - v17.getMinWidth()) * 0.5f))))), v4 + (((float)(((int)(this.sliderPos + v19))))), v17.getMinWidth(), v6 - (((float)(((int)(this.sliderPos + v19))))));
            }

            if(v16 == null) {
                return;
            }

            v16.draw(batch, v26 + (((float)(((int)((v25 - v20) * 0.5f))))), ((float)(((int)(this.sliderPos + v4)))), v20, v18);
        }
        else {
            v1.draw(batch, v26, v4 + (((float)(((int)((v6 - v1.getMinHeight()) * 0.5f))))), v25, v1.getMinHeight());
            float v22 = v25 - (v1.getLeftWidth() + v1.getRightWidth());
            if(this.min != this.max) {
                this.sliderPos = (v24 - this.min) / (this.max - this.min) * (v22 - v20);
                this.sliderPos = Math.max(0f, this.sliderPos);
                this.sliderPos = Math.min(v22 - v20, this.sliderPos) + v1.getLeftWidth();
            }

            v19 = v18 * 0.5f;
            if(v7 != null) {
                v7.draw(batch, v26, v4 + (((float)(((int)((v6 - v7.getMinHeight()) * 0.5f))))), ((float)(((int)(this.sliderPos + v19)))), v7.getMinHeight());
            }

            if(v17 != null) {
                v17.draw(batch, v26 + (((float)(((int)(this.sliderPos + v19))))), v4 + (((float)(((int)((v6 - v17.getMinHeight()) * 0.5f))))), v25 - (((float)(((int)(this.sliderPos + v19))))), v17.getMinHeight());
            }

            if(v16 == null) {
                return;
            }

            v16.draw(batch, ((float)(((int)(this.sliderPos + v26)))), ((float)(((int)((v6 - v18) * 0.5f + v4)))), v20, v18);
        }
    }

    public float getMaxValue() {
        return this.max;
    }

    public float getMinValue() {
        return this.min;
    }

    public float getPrefHeight() {
        Drawable v0;
        Drawable v1;
        float v2;
        if(this.vertical) {
            v2 = 140f;
        }
        else {
            if(!this.disabled || this.style.disabledKnob == null) {
                v1 = this.style.knob;
            }
            else {
                v1 = this.style.disabledKnob;
            }

            if(!this.disabled || this.style.disabledBackground == null) {
                v0 = this.style.background;
            }
            else {
                v0 = this.style.disabledBackground;
            }

            if(v1 == null) {
                v2 = 0f;
            }
            else {
                v2 = v1.getMinHeight();
            }

            v2 = Math.max(v2, v0.getMinHeight());
        }

        return v2;
    }

    public float getPrefWidth() {
        float v2;
        Drawable v0;
        Drawable v1;
        if(this.vertical) {
            if(!this.disabled || this.style.disabledKnob == null) {
                v1 = this.style.knob;
            }
            else {
                v1 = this.style.disabledKnob;
            }

            if(!this.disabled || this.style.disabledBackground == null) {
                v0 = this.style.background;
            }
            else {
                v0 = this.style.disabledBackground;
            }

            if(v1 == null) {
                v2 = 0f;
            }
            else {
                v2 = v1.getMinWidth();
            }

            v2 = Math.max(v2, v0.getMinWidth());
        }
        else {
            v2 = 140f;
        }

        return v2;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public SliderStyle getStyle() {
        return this.style;
    }

    public float getValue() {
        return this.value;
    }

    public float getVisualValue() {
        float v0;
        if(this.animateTime > 0f) {
            v0 = this.animateInterpolation.apply(this.animateFromValue, this.value, 1f - this.animateTime / this.animateDuration);
        }
        else {
            v0 = this.value;
        }

        return v0;
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isDragging() {
        boolean v0;
        if(this.draggingPointer != -1) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void setAnimateDuration(float duration) {
        this.animateDuration = duration;
    }

    public void setAnimateInterpolation(Interpolation animateInterpolation) {
        if(animateInterpolation == null) {
            throw new IllegalArgumentException("animateInterpolation cannot be null.");
        }

        this.animateInterpolation = animateInterpolation;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setRange(float min, float max) {
        if(min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }

        this.min = min;
        this.max = max;
        if(this.value < min) {
            this.setValue(min);
        }
        else if(this.value > max) {
            this.setValue(max);
        }
    }

    public void setSnapToValues(float[] values, float threshold) {
        this.snapValues = values;
        this.threshold = threshold;
    }

    public void setStepSize(float stepSize) {
        if(stepSize <= 0f) {
            throw new IllegalArgumentException("steps must be > 0: " + stepSize);
        }

        this.stepSize = stepSize;
    }

    public void setStyle(SliderStyle style) {
        if(style == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }

        this.style = style;
        this.invalidateHierarchy();
    }

    public boolean setValue(float value) {
        boolean v4 = false;
        value = this.snap(this.clamp((((float)Math.round(value / this.stepSize))) * this.stepSize));
        float v2 = this.value;
        if(value != v2) {
            float v3 = this.getVisualValue();
            this.value = value;
            Object v1 = Pools.obtain(ChangeEvent.class);
            boolean v0 = this.fire(((Event)v1));
            if(v0) {
                this.value = v2;
            }
            else if(this.animateDuration > 0f) {
                this.animateFromValue = v3;
                this.animateTime = this.animateDuration;
            }

            Pools.free(v1);
            if(v0) {
                goto label_11;
            }

            v4 = true;
        }

    label_11:
        return v4;
    }

    private float snap(float value) {
        if(this.snapValues != null) {
            int v0 = 0;
            while(v0 < this.snapValues.length) {
                if(Math.abs(value - this.snapValues[v0]) <= this.threshold) {
                    value = this.snapValues[v0];
                }
                else {
                    ++v0;
                    continue;
                }

                break;
            }
        }

        return value;
    }
}

