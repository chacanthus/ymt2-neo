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
import com.badlogic.gdx.graphics.g2d.BitmapFont$HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont$TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.StringBuilder;

public class Label extends Widget {
    public class LabelStyle {
        public Drawable background;
        public BitmapFont font;
        public Color fontColor;

        public LabelStyle(BitmapFont font, Color fontColor) {
            super();
            this.font = font;
            this.fontColor = fontColor;
        }

        public LabelStyle() {
            super();
        }

        public LabelStyle(LabelStyle style) {
            super();
            this.font = style.font;
            if(style.fontColor != null) {
                this.fontColor = new Color(style.fontColor);
            }

            this.background = style.background;
        }
    }

    private final TextBounds bounds;
    private BitmapFontCache cache;
    private boolean ellipse;
    private float fontScaleX;
    private float fontScaleY;
    private int labelAlign;
    private float lastPrefHeight;
    private HAlignment lineAlign;
    private boolean sizeInvalid;
    private LabelStyle style;
    private static final Color tempColor;
    private StringBuilder tempText;
    private final StringBuilder text;
    private boolean wrap;

    static  {
        Label.tempColor = new Color();
    }

    public Label(CharSequence text, Skin skin, String fontName, Color color) {
        this(text, new LabelStyle(skin.getFont(fontName), color));
    }

    public Label(CharSequence text, LabelStyle style) {
        super();
        this.bounds = new TextBounds();
        this.text = new StringBuilder();
        this.labelAlign = 8;
        this.lineAlign = HAlignment.LEFT;
        this.sizeInvalid = true;
        this.fontScaleX = 1f;
        this.fontScaleY = 1f;
        if(text != null) {
            this.text.append(text);
        }

        this.setStyle(style);
        this.setSize(this.getPrefWidth(), this.getPrefHeight());
    }

    public Label(CharSequence text, Skin skin) {
        this(text, skin.get(LabelStyle.class));
    }

    public Label(CharSequence text, Skin skin, String styleName) {
        this(text, skin.get(styleName, LabelStyle.class));
    }

    public Label(CharSequence text, Skin skin, String fontName, String colorName) {
        this(text, new LabelStyle(skin.getFont(fontName), skin.getColor(colorName)));
    }

    private void computeSize() {
        this.sizeInvalid = false;
        if(this.wrap) {
            float v0 = this.getWidth();
            if(this.style.background != null) {
                v0 -= this.style.background.getLeftWidth() + this.style.background.getRightWidth();
            }

            this.bounds.set(this.cache.getFont().getWrappedBounds(this.text, v0));
        }
        else {
            this.bounds.set(this.cache.getFont().getMultiLineBounds(this.text));
        }
    }

    public void draw(Batch batch, float parentAlpha) {
        this.validate();
        Color v6 = Label.tempColor.set(this.getColor());
        v6.a *= parentAlpha;
        if(this.style.background != null) {
            batch.setColor(v6.r, v6.g, v6.b, v6.a);
            this.style.background.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }

        if(this.style.fontColor != null) {
            v6.mul(this.style.fontColor);
        }

        this.cache.setColors(v6);
        this.cache.setPosition(this.getX(), this.getY());
        this.cache.draw(batch);
    }

    public float getFontScaleX() {
        return this.fontScaleX;
    }

    public float getFontScaleY() {
        return this.fontScaleY;
    }

    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.scaleAndComputeSize();
        }

        float v1 = this.bounds.height - this.style.font.getDescent() * 2f;
        Drawable v0 = this.style.background;
        if(v0 != null) {
            v1 += v0.getTopHeight() + v0.getBottomHeight();
        }

        return v1;
    }

    public float getPrefWidth() {
        float v1;
        if(this.wrap) {
            v1 = 0f;
        }
        else {
            if(this.sizeInvalid) {
                this.scaleAndComputeSize();
            }

            v1 = this.bounds.width;
            Drawable v0 = this.style.background;
            if(v0 == null) {
                goto label_3;
            }

            v1 += v0.getLeftWidth() + v0.getRightWidth();
        }

    label_3:
        return v1;
    }

    public LabelStyle getStyle() {
        return this.style;
    }

    public CharSequence getText() {
        return this.text;
    }

    public TextBounds getTextBounds() {
        if(this.sizeInvalid) {
            this.scaleAndComputeSize();
        }

        return this.bounds;
    }

    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    public void layout() {
        float v3;
        StringBuilder v4;
        BitmapFont v11 = this.cache.getFont();
        float v13 = v11.getScaleX();
        float v14 = v11.getScaleY();
        if(this.fontScaleX != 1f || this.fontScaleY != 1f) {
            v11.setScale(this.fontScaleX, this.fontScaleY);
        }

        if(this.sizeInvalid) {
            this.computeSize();
        }

        if(this.wrap) {
            float v15 = this.getPrefHeight();
            if(v15 != this.lastPrefHeight) {
                this.lastPrefHeight = v15;
                this.invalidateHierarchy();
            }
        }

        float v16 = this.getWidth();
        float v12 = this.getHeight();
        if(!this.ellipse || v16 >= this.bounds.width) {
            v4 = this.text;
        }
        else {
            float v10 = v11.getBounds("...").width;
            if(this.tempText != null) {
                v4 = this.tempText;
            }
            else {
                v4 = new StringBuilder();
                this.tempText = v4;
            }

            v4.setLength(0);
            if(v16 <= v10) {
                goto label_72;
            }

            v4.append(this.text, 0, v11.computeVisibleGlyphs(this.text, 0, this.text.length, v16 - v10));
            v4.append("...");
        }

    label_72:
        Drawable v9 = this.style.background;
        float v5 = 0f;
        float v6 = 0f;
        if(v9 != null) {
            v5 = v9.getLeftWidth();
            v6 = v9.getBottomHeight();
            v16 -= v9.getLeftWidth() + v9.getRightWidth();
            v12 -= v9.getBottomHeight() + v9.getTopHeight();
        }

        if((this.labelAlign & 2) != 0) {
            if(this.cache.getFont().isFlipped()) {
                v3 = 0f;
            }
            else {
                v3 = v12 - this.bounds.height;
            }

            v6 = v6 + v3 + this.style.font.getDescent();
        }
        else {
            if((this.labelAlign & 4) == 0) {
                goto label_181;
            }

            if(this.cache.getFont().isFlipped()) {
                v3 = v12 - this.bounds.height;
            }
            else {
                v3 = 0f;
            }

            v6 = v6 + v3 - this.style.font.getDescent();
            goto label_104;
        label_181:
            v6 += ((float)(((int)((v12 - this.bounds.height) / 2f))));
        }

    label_104:
        if(!this.cache.getFont().isFlipped()) {
            v6 += this.bounds.height;
        }

        if((this.labelAlign & 8) == 0) {
            if((this.labelAlign & 16) != 0) {
                v5 += v16 - this.bounds.width;
            }
            else {
                v5 += ((float)(((int)((v16 - this.bounds.width) / 2f))));
            }
        }

        if(this.wrap) {
            this.cache.setWrappedText(((CharSequence)v4), v5, v6, this.bounds.width, this.lineAlign);
        }
        else {
            this.cache.setMultiLineText(((CharSequence)v4), v5, v6, this.bounds.width, this.lineAlign);
        }

        if(this.fontScaleX != 1f || this.fontScaleY != 1f) {
            v11.setScale(v13, v14);
        }
    }

    private void scaleAndComputeSize() {
        float v5 = 1f;
        BitmapFont v0 = this.cache.getFont();
        float v1 = v0.getScaleX();
        float v2 = v0.getScaleY();
        if(this.fontScaleX != v5 || this.fontScaleY != v5) {
            v0.setScale(this.fontScaleX, this.fontScaleY);
        }

        this.computeSize();
        if(this.fontScaleX != v5 || this.fontScaleY != v5) {
            v0.setScale(v1, v2);
        }
    }

    public void setAlignment(int wrapAlign) {
        this.setAlignment(wrapAlign, wrapAlign);
    }

    public void setAlignment(int labelAlign, int lineAlign) {
        this.labelAlign = labelAlign;
        if((lineAlign & 8) != 0) {
            this.lineAlign = HAlignment.LEFT;
        }
        else if((lineAlign & 16) != 0) {
            this.lineAlign = HAlignment.RIGHT;
        }
        else {
            this.lineAlign = HAlignment.CENTER;
        }

        this.invalidate();
    }

    public void setEllipse(boolean ellipse) {
        this.ellipse = ellipse;
    }

    public void setFontScale(float fontScale) {
        this.fontScaleX = fontScale;
        this.fontScaleY = fontScale;
        this.invalidateHierarchy();
    }

    public void setFontScale(float fontScaleX, float fontScaleY) {
        this.fontScaleX = fontScaleX;
        this.fontScaleY = fontScaleY;
        this.invalidateHierarchy();
    }

    public void setFontScaleX(float fontScaleX) {
        this.fontScaleX = fontScaleX;
        this.invalidateHierarchy();
    }

    public void setFontScaleY(float fontScaleY) {
        this.fontScaleY = fontScaleY;
        this.invalidateHierarchy();
    }

    public void setStyle(LabelStyle style) {
        if(style == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }

        if(style.font == null) {
            throw new IllegalArgumentException("Missing LabelStyle font.");
        }

        this.style = style;
        this.cache = new BitmapFontCache(style.font, style.font.usesIntegerPositions());
        this.invalidateHierarchy();
    }

    public void setText(CharSequence newText) {
        String v3_1;
        if(!(newText instanceof StringBuilder)) {
            if(newText == null) {
                v3_1 = "";
            }

            if(this.textEquals(((CharSequence)v3_1))) {
                return;
            }

            this.text.setLength(0);
            this.text.append(((CharSequence)v3_1));
        label_12:
            this.invalidateHierarchy();
        }
        else if(!this.text.equals(newText)) {
            this.text.setLength(0);
            this.text.append(newText);
            goto label_12;
        }
    }

    public void setWrap(boolean wrap) {
        this.wrap = wrap;
        this.invalidateHierarchy();
    }

    public boolean textEquals(CharSequence other) {
        boolean v3 = false;
        int v2 = this.text.length;
        char[] v0 = this.text.chars;
        if(v2 == other.length()) {
            int v1 = 0;
            while(true) {
                if(v1 >= v2) {
                    break;
                }
                else if(v0[v1] == other.charAt(v1)) {
                    ++v1;
                    continue;
                }

                goto label_7;
            }

            v3 = true;
        }

    label_7:
        return v3;
    }
}

