// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.NumberUtils;

public class BitmapFontCache {
    private float color;
    private final BitmapFont font;
    private int glyphCount;
    private IntArray[] glyphIndices;
    private int[] idx;
    private boolean integer;
    private final Color tempColor;
    private final TextBounds textBounds;
    private int[] tmpGlyphCount;
    private float[][] vertexData;
    private float x;
    private float y;

    public BitmapFontCache(BitmapFont font) {
        this(font, font.usesIntegerPositions());
    }

    public BitmapFontCache(BitmapFont font, boolean integer) {
        super();
        this.color = Color.WHITE.toFloatBits();
        this.tempColor = new Color(1f, 1f, 1f, 1f);
        this.textBounds = new TextBounds();
        this.integer = true;
        this.glyphCount = 0;
        this.font = font;
        this.integer = integer;
        int v2 = font.regions.length;
        if(v2 == 0) {
            throw new IllegalArgumentException("The specified font must contain at least 1 texture page");
        }

        this.vertexData = new float[v2][];
        this.idx = new int[v2];
        int v3 = this.vertexData.length;
        if(v3 > 1) {
            this.glyphIndices = new IntArray[v3];
            int v0 = 0;
            int v1 = this.glyphIndices.length;
            while(v0 < v1) {
                this.glyphIndices[v0] = new IntArray();
                ++v0;
            }

            this.tmpGlyphCount = new int[v3];
        }
    }

    private void addGlyph(Glyph glyph, float x, float y, float width, float height) {
        float v9 = x + width;
        float v10 = y + height;
        float v4 = glyph.u;
        float v5 = glyph.u2;
        float v6 = glyph.v;
        float v7 = glyph.v2;
        int v3 = glyph.page;
        if(this.glyphIndices != null) {
            IntArray v11 = this.glyphIndices[v3];
            int v12 = this.glyphCount;
            this.glyphCount = v12 + 1;
            v11.add(v12);
        }

        float[] v8 = this.vertexData[v3];
        if(this.integer) {
            x = ((float)Math.round(x));
            y = ((float)Math.round(y));
            v9 = ((float)Math.round(v9));
            v10 = ((float)Math.round(v10));
        }

        int v1 = this.idx[v3];
        this.idx[v3] += 20;
        int v2 = v1 + 1;
        v8[v1] = x;
        v1 = v2 + 1;
        v8[v2] = y;
        v2 = v1 + 1;
        v8[v1] = this.color;
        v1 = v2 + 1;
        v8[v2] = v4;
        v2 = v1 + 1;
        v8[v1] = v6;
        v1 = v2 + 1;
        v8[v2] = x;
        v2 = v1 + 1;
        v8[v1] = v10;
        v1 = v2 + 1;
        v8[v2] = this.color;
        v2 = v1 + 1;
        v8[v1] = v4;
        v1 = v2 + 1;
        v8[v2] = v7;
        v2 = v1 + 1;
        v8[v1] = v9;
        v1 = v2 + 1;
        v8[v2] = v10;
        v2 = v1 + 1;
        v8[v1] = this.color;
        v1 = v2 + 1;
        v8[v2] = v5;
        v2 = v1 + 1;
        v8[v1] = v7;
        v1 = v2 + 1;
        v8[v2] = v9;
        v2 = v1 + 1;
        v8[v1] = y;
        v1 = v2 + 1;
        v8[v2] = this.color;
        v8[v1] = v5;
        v8[v1 + 1] = v6;
    }

    public TextBounds addMultiLineText(CharSequence str, float x, float y, float alignmentWidth, HAlignment alignment) {
        BitmapFont v8 = this.font;
        int v9 = str.length();
        this.requireSequence(str, 0, v9);
        y += v8.data.ascent;
        float v7 = v8.data.down;
        float v11 = 0f;
        int v5 = 0;
        int v12;
        for(v12 = 0; v5 < v9; ++v12) {
            int v6 = BitmapFont.indexOf(str, '\n', v5);
            float v14 = 0f;
            if(alignment != HAlignment.LEFT) {
                v14 = alignmentWidth - v8.getBounds(str, v5, v6).width;
                if(alignment == HAlignment.CENTER) {
                    v14 /= 2f;
                }
            }

            v11 = Math.max(v11, this.addToCache(str, x + v14, y, v5, v6));
            v5 = v6 + 1;
            y += v7;
        }

        this.textBounds.width = v11;
        this.textBounds.height = v8.data.capHeight + (((float)(v12 - 1))) * v8.data.lineHeight;
        return this.textBounds;
    }

    public TextBounds addMultiLineText(CharSequence str, float x, float y) {
        return this.addMultiLineText(str, x, y, 0f, HAlignment.LEFT);
    }

    public TextBounds addText(CharSequence str, float x, float y, int start, int end) {
        this.requireSequence(str, start, end);
        this.textBounds.width = this.addToCache(str, x, y + this.font.data.ascent, start, end);
        this.textBounds.height = this.font.data.capHeight;
        return this.textBounds;
    }

    public TextBounds addText(CharSequence str, float x, float y) {
        return this.addText(str, x, y, 0, str.length());
    }

    private float addToCache(CharSequence str, float x, float y, int start, int end) {
        Glyph v10;
        char v7;
        int v13;
        float v14 = x;
        Glyph v2 = null;
        BitmapFontData v8 = this.font.data;
        if(v8.scaleX == 1f) {
            if(v8.scaleY == 1f) {
                goto label_10;
            }

            goto label_62;
            do {
            label_10:
                v13 = start;
                if(v13 < end) {
                    start = v13 + 1;
                    v2 = v8.getGlyph(str.charAt(v13));
                    if(v2 == null) {
                        continue;
                    }

                    break;
                }

                goto label_34;
            }
            while(true);

            this.addGlyph(v2, x + (((float)v2.xoffset)), y + (((float)v2.yoffset)), ((float)v2.width), ((float)v2.height));
            x += ((float)v2.xadvance);
            for(v13 = start; true; v13 = start) {
            label_34:
                if(v13 >= end) {
                    break;
                }

                start = v13 + 1;
                v7 = str.charAt(v13);
                v10 = v8.getGlyph(v7);
                if(v10 != null) {
                    x += ((float)v2.getKerning(v7));
                    v2 = v10;
                    this.addGlyph(v2, x + (((float)v10.xoffset)), y + (((float)v10.yoffset)), ((float)v10.width), ((float)v10.height));
                    x += ((float)v10.xadvance);
                }
            }
        }
        else {
        label_62:
            float v11 = v8.scaleX;
            float v12 = v8.scaleY;
            v13 = start;
            while(v13 < end) {
                start = v13 + 1;
                v2 = v8.getGlyph(str.charAt(v13));
                if(v2 != null) {
                    this.addGlyph(v2, x + (((float)v2.xoffset)) * v11, y + (((float)v2.yoffset)) * v12, (((float)v2.width)) * v11, (((float)v2.height)) * v12);
                    x += (((float)v2.xadvance)) * v11;
                    v13 = start;
                }
                else {
                    v13 = start;
                    continue;
                }

                break;
            }

            while(true) {
                if(v13 >= end) {
                    break;
                }

                start = v13 + 1;
                v7 = str.charAt(v13);
                v10 = v8.getGlyph(v7);
                if(v10 != null) {
                    x += (((float)v2.getKerning(v7))) * v11;
                    v2 = v10;
                    this.addGlyph(v2, x + (((float)v10.xoffset)) * v11, y + (((float)v10.yoffset)) * v12, (((float)v10.width)) * v11, (((float)v10.height)) * v12);
                    x += (((float)v10.xadvance)) * v11;
                }

                v13 = start;
            }
        }

        return x - v14;
    }

    public TextBounds addWrappedText(CharSequence str, float x, float y, float wrapWidth, HAlignment alignment) {
        BitmapFont v9 = this.font;
        int v10 = str.length();
        this.requireSequence(str, 0, v10);
        y += v9.data.ascent;
        float v8 = v9.data.down;
        if(wrapWidth <= 0f) {
            wrapWidth = 2147483648f;
        }

        float v12 = 0f;
        int v6 = 0;
        int v15;
        for(v15 = 0; v6 < v10; ++v15) {
            int v13 = BitmapFont.indexOf(str, '\n', v6);
            while(v6 < v13) {
                if(!BitmapFont.isWhitespace(str.charAt(v6))) {
                    break;
                }

                ++v6;
            }

            int v7 = v6 + v9.computeVisibleGlyphs(str, v6, v13, wrapWidth);
            int v14 = v7 + 1;
            if(v7 < v13) {
                while(v7 > v6) {
                    if(BitmapFont.isWhitespace(str.charAt(v7))) {
                        break;
                    }

                    --v7;
                }

                if(v7 != v6) {
                    goto label_71;
                }

                if(v14 > v6 + 1) {
                    --v14;
                }

                v7 = v14;
                goto label_43;
            label_71:
                v14 = v7;
                while(v7 > v6) {
                    if(!BitmapFont.isWhitespace(str.charAt(v7 - 1))) {
                        break;
                    }

                    --v7;
                }
            }

        label_43:
            if(v7 > v6) {
                float v16 = 0f;
                if(alignment != HAlignment.LEFT) {
                    v16 = wrapWidth - v9.getBounds(str, v6, v7).width;
                    if(alignment == HAlignment.CENTER) {
                        v16 /= 2f;
                    }
                }

                v12 = Math.max(v12, this.addToCache(str, x + v16, y, v6, v7));
            }

            v6 = v14;
            y += v8;
        }

        this.textBounds.width = v12;
        this.textBounds.height = v9.data.capHeight + (((float)(v15 - 1))) * v9.data.lineHeight;
        return this.textBounds;
    }

    public TextBounds addWrappedText(CharSequence str, float x, float y, float wrapWidth) {
        return this.addWrappedText(str, x, y, wrapWidth, HAlignment.LEFT);
    }

    public void clear() {
        this.x = 0f;
        this.y = 0f;
        this.glyphCount = 0;
        int v0 = 0;
        int v1 = this.idx.length;
        while(v0 < v1) {
            if(this.glyphIndices != null) {
                this.glyphIndices[v0].clear();
            }

            this.idx[v0] = 0;
            ++v0;
        }
    }

    public void draw(Batch spriteBatch, float alphaModulation) {
        if(alphaModulation == 1f) {
            this.draw(spriteBatch);
        }
        else {
            Color v0 = this.getColor();
            float v1 = v0.a;
            v0.a *= alphaModulation;
            this.setColors(v0);
            this.draw(spriteBatch);
            v0.a = v1;
            this.setColors(v0);
        }
    }

    public void draw(Batch spriteBatch) {
        TextureRegion[] v2 = this.font.getRegions();
        int v0 = 0;
        int v1 = this.vertexData.length;
        while(v0 < v1) {
            if(this.idx[v0] > 0) {
                spriteBatch.draw(v2[v0].getTexture(), this.vertexData[v0], 0, this.idx[v0]);
            }

            ++v0;
        }
    }

    public void draw(Batch spriteBatch, int start, int end) {
        if(this.vertexData.length == 1) {
            spriteBatch.draw(this.font.getRegion().getTexture(), this.vertexData[0], start * 20, (end - start) * 20);
        }
        else {
            TextureRegion[] v9 = this.font.getRegions();
            int v4 = 0;
            int v8 = this.vertexData.length;
            while(v4 < v8) {
                int v7 = -1;
                int v1 = 0;
                IntArray v2 = this.glyphIndices[v4];
                int v5 = 0;
                int v6 = v2.size;
                while(v5 < v6) {
                    int v3 = v2.items[v5];
                    if(v3 >= end) {
                        break;
                    }

                    if(v7 == -1 && v3 >= start) {
                        v7 = v5;
                    }

                    if(v3 >= start) {
                        ++v1;
                    }

                    ++v5;
                }

                if(v7 != -1 && v1 != 0) {
                    spriteBatch.draw(v9[v4].getTexture(), this.vertexData[v4], v7 * 20, v1 * 20);
                }

                ++v4;
            }
        }
    }

    public TextBounds getBounds() {
        return this.textBounds;
    }

    public Color getColor() {
        int v1 = NumberUtils.floatToIntColor(this.color);
        Color v0 = this.tempColor;
        v0.r = (((float)(v1 & 255))) / 255f;
        v0.g = (((float)(v1 >>> 8 & 255))) / 255f;
        v0.b = (((float)(v1 >>> 16 & 255))) / 255f;
        v0.a = (((float)(v1 >>> 24 & 255))) / 255f;
        return v0;
    }

    public BitmapFont getFont() {
        return this.font;
    }

    public float[] getVertices() {
        return this.getVertices(0);
    }

    public float[] getVertices(int page) {
        return this.vertexData[page];
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    private void require(int page, int glyphCount) {
        if(this.glyphIndices != null && glyphCount > this.glyphIndices[page].items.length) {
            this.glyphIndices[page].ensureCapacity(glyphCount - this.glyphIndices[page].items.length);
        }

        int v1 = this.idx[page] + glyphCount * 20;
        float[] v2 = this.vertexData[page];
        if(v2 == null) {
            this.vertexData[page] = new float[v1];
        }
        else if(v2.length < v1) {
            float[] v0 = new float[v1];
            System.arraycopy(v2, 0, v0, 0, this.idx[page]);
            this.vertexData[page] = v0;
        }
    }

    private void requireSequence(CharSequence seq, int start, int end) {
        int v3 = end - start;
        if(this.vertexData.length == 1) {
            this.require(0, v3);
        }
        else {
            int v1 = 0;
            int v2 = this.tmpGlyphCount.length;
            while(v1 < v2) {
                this.tmpGlyphCount[v1] = 0;
                ++v1;
            }

            int v4 = start;
            while(true) {
                if(v4 < end) {
                    start = v4 + 1;
                    Glyph v0 = this.font.data.getGlyph(seq.charAt(v4));
                    if(v0 == null) {
                        v4 = start;
                        continue;
                    }
                    else {
                        ++this.tmpGlyphCount[v0.page];
                        v4 = start;
                        continue;
                    }
                }
                else {
                    break;
                }

                return;
            }

            v1 = 0;
            v2 = this.tmpGlyphCount.length;
            while(v1 < v2) {
                this.require(v1, this.tmpGlyphCount[v1]);
                ++v1;
            }
        }
    }

    public void setColor(Color tint) {
        this.color = tint.toFloatBits();
    }

    public void setColor(float color) {
        this.color = color;
    }

    public void setColor(float r, float g, float b, float a) {
        this.color = NumberUtils.intToFloatColor((((int)(255f * a))) << 24 | (((int)(255f * b))) << 16 | (((int)(255f * g))) << 8 | (((int)(255f * r))));
    }

    public void setColors(Color tint) {
        float v0 = tint.toFloatBits();
        int v2 = 0;
        int v3 = this.vertexData.length;
        while(v2 < v3) {
            float[] v5 = this.vertexData[v2];
            int v1 = 2;
            int v4 = this.idx[v2];
            while(v1 < v4) {
                v5[v1] = v0;
                v1 += 5;
            }

            ++v2;
        }
    }

    public void setColors(float color) {
        int v1 = 0;
        int v2 = this.vertexData.length;
        while(v1 < v2) {
            float[] v4 = this.vertexData[v1];
            int v0 = 2;
            int v3 = this.idx[v1];
            while(v0 < v3) {
                v4[v0] = color;
                v0 += 5;
            }

            ++v1;
        }
    }

    public void setColors(float r, float g, float b, float a) {
        float v0 = NumberUtils.intToFloatColor((((int)(255f * a))) << 24 | (((int)(255f * b))) << 16 | (((int)(255f * g))) << 8 | (((int)(255f * r))));
        int v3 = 0;
        int v4 = this.vertexData.length;
        while(v3 < v4) {
            float[] v6 = this.vertexData[v3];
            int v1 = 2;
            int v5 = this.idx[v3];
            while(v1 < v5) {
                v6[v1] = v0;
                v1 += 5;
            }

            ++v3;
        }
    }

    public void setColors(Color tint, int start, int end) {
        int v4;
        int v2;
        float[] v7;
        float v0 = tint.toFloatBits();
        if(this.vertexData.length == 1) {
            v7 = this.vertexData[0];
            v2 = start * 20 + 2;
            v4 = end * 20;
            while(v2 < v4) {
                v7[v2] = v0;
                v2 += 5;
            }
        }
        else {
            int v6 = this.vertexData.length;
            for(v2 = 0; v2 < v6; ++v2) {
                v7 = this.vertexData[v2];
                int v3 = 0;
                v4 = this.glyphIndices[v2].size;
                while(v3 < v4) {
                    int v1 = this.glyphIndices[v2].items[v3];
                    if(v1 >= end) {
                        break;
                    }

                    if(v1 >= start) {
                        int v5 = 0;
                        while(v5 < 20) {
                            v7[v3 * 20 + 2 + v5] = v0;
                            v5 += 5;
                        }
                    }

                    ++v3;
                }
            }
        }
    }

    public TextBounds setMultiLineText(CharSequence str, float x, float y) {
        this.clear();
        return this.addMultiLineText(str, x, y, 0f, HAlignment.LEFT);
    }

    public TextBounds setMultiLineText(CharSequence str, float x, float y, float alignmentWidth, HAlignment alignment) {
        this.clear();
        return this.addMultiLineText(str, x, y, alignmentWidth, alignment);
    }

    public void setPosition(float x, float y) {
        this.translate(x - this.x, y - this.y);
    }

    public TextBounds setText(CharSequence str, float x, float y) {
        this.clear();
        return this.addText(str, x, y, 0, str.length());
    }

    public TextBounds setText(CharSequence str, float x, float y, int start, int end) {
        this.clear();
        return this.addText(str, x, y, start, end);
    }

    public void setUseIntegerPositions(boolean use) {
        this.integer = use;
    }

    public TextBounds setWrappedText(CharSequence str, float x, float y, float wrapWidth) {
        this.clear();
        return this.addWrappedText(str, x, y, wrapWidth, HAlignment.LEFT);
    }

    public TextBounds setWrappedText(CharSequence str, float x, float y, float wrapWidth, HAlignment alignment) {
        this.clear();
        return this.addWrappedText(str, x, y, wrapWidth, alignment);
    }

    public void translate(float xAmount, float yAmount) {
        if(xAmount != 0f || yAmount != 0f) {
            if(this.integer) {
                xAmount = ((float)Math.round(xAmount));
                yAmount = ((float)Math.round(yAmount));
            }

            this.x += xAmount;
            this.y += yAmount;
            int v1 = 0;
            int v2 = this.vertexData.length;
            while(v1 < v2) {
                float[] v4 = this.vertexData[v1];
                int v0 = 0;
                int v3 = this.idx[v1];
                while(v0 < v3) {
                    v4[v0] += xAmount;
                    int v5 = v0 + 1;
                    v4[v5] += yAmount;
                    v0 += 5;
                }

                ++v1;
            }
        }
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }
}

