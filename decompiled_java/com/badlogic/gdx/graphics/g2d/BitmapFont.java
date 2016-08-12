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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BitmapFont implements Disposable {
    public class BitmapFontData {
        public float ascent;
        public float capHeight;
        public float descent;
        public float down;
        public boolean flipped;
        public FileHandle fontFile;
        public final Glyph[][] glyphs;
        public String imagePath;
        public String[] imagePaths;
        public float lineHeight;
        public float scaleX;
        public float scaleY;
        public float spaceWidth;
        public float xHeight;

        public BitmapFontData(FileHandle fontFile, boolean flip) {  // has try-catch handlers
            int v16;
            Glyph[][] v4;
            char v33_9;
            StringTokenizer v30;
            Glyph v13;
            String v17;
            String[] v25;
            int v22;
            String[] v33_7;
            String v33_5;
            int v18;
            float v6;
            boolean v33_4;
            int v33_3;
            String[] v9;
            GdxRuntimeException v33_2;
            String v21;
            super();
            this.capHeight = 1f;
            this.scaleX = 1f;
            this.scaleY = 1f;
            this.glyphs = new Glyph[128][];
            this.xHeight = 1f;
            this.fontFile = fontFile;
            this.flipped = flip;
            InputStreamReader v33 = new InputStreamReader(fontFile.read());
            BufferedReader v27 = new BufferedReader(v33, 512);
            try {
                v27.readLine();
                v21 = v27.readLine();
                if(v21 == null) {
                    v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile);
                    throw v33_2;
                }

                v9 = v21.split(" ", 7);
                v33_3 = v9.length;
                if(v33_3 < 3) {
                    v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile);
                    throw v33_2;
                }

                v33_4 = v9[1].startsWith("lineHeight=");
                if(!v33_4) {
                    v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile);
                    throw v33_2;
                }

                this.lineHeight = ((float)Integer.parseInt(v9[1].substring(11)));
                v33_4 = v9[2].startsWith("base=");
                if(!v33_4) {
                    v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile);
                    throw v33_2;
                }

                v6 = ((float)Integer.parseInt(v9[2].substring(5)));
                v18 = 1;
                v33_3 = v9.length;
                if(v33_3 < 6) {
                    goto label_155;
                }

                v33_5 = v9[5];
                if(v33_5 == null) {
                    goto label_155;
                }

                v33_4 = v9[5].startsWith("pages=");
                if(!v33_4) {
                    goto label_155;
                }
            }
            catch(Exception v11) {
                goto label_51;
            }
            catch(Throwable v33_1) {
                goto label_64;
            }

            v33 = null;
            int v34 = 5;
            try {
                v18 = Math.max(1, Integer.parseInt(v9[v34].substring(6)));
                goto label_155;
            }
            catch(Throwable v33_1) {
            }
            catch(Exception v11) {
            }
            catch(NumberFormatException v33_6) {
                try {
                label_155:
                    v33_7 = new String[v18];
                    this.imagePaths = v33_7;
                    v22 = 0;
                    while(true) {
                    label_162:
                        if(v22 >= v18) {
                            goto label_273;
                        }

                        v21 = v27.readLine();
                        if(v21 == null) {
                            v33_2 = new GdxRuntimeException("Expected more \'page\' definitions in font file " + fontFile);
                            throw v33_2;
                        }

                        v25 = v21.split(" ", 4);
                        v33_4 = v25[2].startsWith("file=");
                        if(!v33_4) {
                            v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile);
                            throw v33_2;
                        }

                        v33_4 = v25[1].startsWith("id=");
                        if(v33_4) {
                            break;
                        }

                        goto label_230;
                    }
                }
                catch(Exception v11) {
                    goto label_51;
                }
                catch(Throwable v33_1) {
                    goto label_64;
                }

                try {
                    v33_5 = v25[1].substring(3);
                    if(Integer.parseInt(v33_5) != v22) {
                        v33_2 = new GdxRuntimeException("Invalid font file: " + fontFile + " -- page ids must be indices starting at 0");
                        throw v33_2;
                    }

                    goto label_230;
                }
                catch(Throwable v33_1) {
                }
                catch(Exception v11) {
                }
                catch(NumberFormatException v10) {
                    try {
                        v33_2 = new GdxRuntimeException("NumberFormatException on \'page id\' element of " + fontFile);
                        throw v33_2;
                    label_230:
                        v33_4 = v25[2].endsWith("\"");
                        if(v33_4) {
                            v33_5 = v25[2];
                            v17 = v33_5.substring(6, v25[2].length() - 1);
                        }
                        else {
                            v33_5 = v25[2];
                            v17 = v33_5.substring(5, v25[2].length());
                        }

                        String v26 = fontFile.parent().child(v17).path().replaceAll("\\\\", "/");
                        v33_5 = this.imagePath;
                        if(v33_5 == null) {
                            this.imagePath = v26;
                        }

                        v33_7 = this.imagePaths;
                        v33_7[v22] = v26;
                        ++v22;
                        goto label_162;
                    label_273:
                        v33 = null;
                        this.descent = 0f;
                        while(true) {
                        label_277:
                            v21 = v27.readLine();
                            if(v21 != null) {
                                v33_4 = v21.startsWith("kernings ");
                                if(!v33_4) {
                                    v33_4 = v21.startsWith("char ");
                                    if(!v33_4) {
                                        continue;
                                    }

                                    v13 = new Glyph();
                                    v30 = new StringTokenizer(v21, " =");
                                    v30.nextToken();
                                    v30.nextToken();
                                    int v8 = Integer.parseInt(v30.nextToken());
                                    v33 = null;
                                    if(v8 > (((int)v33))) {
                                        continue;
                                    }

                                    this.setGlyph(v8, v13);
                                    v13.id = v8;
                                    v30.nextToken();
                                    v13.srcX = Integer.parseInt(v30.nextToken());
                                    v30.nextToken();
                                    v13.srcY = Integer.parseInt(v30.nextToken());
                                    v30.nextToken();
                                    v13.width = Integer.parseInt(v30.nextToken());
                                    v30.nextToken();
                                    v13.height = Integer.parseInt(v30.nextToken());
                                    v30.nextToken();
                                    v33_3 = Integer.parseInt(v30.nextToken());
                                    v13.xoffset = v33_3;
                                    v30.nextToken();
                                    if(flip) {
                                        v33_3 = Integer.parseInt(v30.nextToken());
                                        v13.yoffset = v33_3;
                                    }
                                    else {
                                        v33_3 = -(v13.height + Integer.parseInt(v30.nextToken()));
                                        v13.yoffset = v33_3;
                                    }

                                    v30.nextToken();
                                    v13.xadvance = Integer.parseInt(v30.nextToken());
                                    v33_4 = v30.hasMoreTokens();
                                    if(v33_4) {
                                        v30.nextToken();
                                    }

                                    v33_4 = v30.hasMoreTokens();
                                    if(!v33_4) {
                                        goto label_448;
                                    }

                                    goto label_444;
                                }
                            }

                            goto label_279;
                        }
                    }
                    catch(Exception v11) {
                        goto label_51;
                    }
                    catch(Throwable v33_1) {
                        goto label_64;
                    }

                    try {
                    label_444:
                        v33_3 = Integer.parseInt(v30.nextToken());
                        v13.page = v33_3;
                        goto label_448;
                    }
                    catch(Throwable v33_1) {
                    }
                    catch(Exception v11) {
                        try {
                        label_51:
                            throw new GdxRuntimeException("Error loading font file: " + fontFile, ((Throwable)v11));
                        }
                        catch(Throwable v33_1) {
                        label_64:
                            StreamUtils.closeQuietly(((Closeable)v27));
                            throw v33_1;
                        }
                    }
                    catch(NumberFormatException v33_6) {
                        try {
                        label_448:
                            v33_3 = v13.width;
                            if(v33_3 <= 0) {
                                goto label_277;
                            }

                            v33_3 = v13.height;
                            if(v33_3 <= 0) {
                                goto label_277;
                            }

                            float v33_8 = Math.min((((float)v13.yoffset)) + v6, this.descent);
                            this.descent = v33_8;
                            goto label_277;
                            while(true) {
                            label_279:
                                v21 = v27.readLine();
                                if(v21 != null) {
                                    v33_4 = v21.startsWith("kerning ");
                                    if(v33_4) {
                                        v30 = new StringTokenizer(v21, " =");
                                        v30.nextToken();
                                        v30.nextToken();
                                        int v12 = Integer.parseInt(v30.nextToken());
                                        v30.nextToken();
                                        v33_5 = v30.nextToken();
                                        int v28 = Integer.parseInt(v33_5);
                                        if(v12 < 0) {
                                            continue;
                                        }

                                        v33 = null;
                                        if(v12 > (((int)v33))) {
                                            continue;
                                        }

                                        if(v28 < 0) {
                                            continue;
                                        }

                                        v33 = null;
                                        if(v28 > (((int)v33))) {
                                            continue;
                                        }

                                        v13 = this.getGlyph(((char)v12));
                                        v30.nextToken();
                                        v33_5 = v30.nextToken();
                                        v13.setKerning(v28, Integer.parseInt(v33_5));
                                        continue;
                                    }
                                }

                                break;
                            }

                            v33 = null;
                            Glyph v29 = this.getGlyph(((char)v33));
                            if(v29 == null) {
                                v29 = new Glyph();
                                v29.id = 32;
                                v33 = null;
                                Glyph v32 = this.getGlyph(((char)v33));
                                if(v32 == null) {
                                    v32 = this.getFirstGlyph();
                                }

                                v29.xadvance = v32.xadvance;
                                v33 = null;
                                this.setGlyph(((int)v33), v29);
                            }

                            if(v29 != null) {
                                v33_8 = ((float)(v29.xadvance + v29.width));
                            }
                            else {
                                v33 = null;
                            }

                            this.spaceWidth = ((float)v33);
                            Glyph v31 = null;
                            int v14 = 0;
                            while(true) {
                                v33_3 = BitmapFont.xChars.length;
                                if(v14 < v33_3) {
                                    v33_9 = BitmapFont.xChars[v14];
                                    v31 = this.getGlyph(v33_9);
                                    if(v31 == null) {
                                        ++v14;
                                        continue;
                                    }
                                }

                                break;
                            }

                            if(v31 == null) {
                                v31 = this.getFirstGlyph();
                            }

                            v33_8 = ((float)v31.height);
                            this.xHeight = v33_8;
                            Glyph v7 = null;
                            v14 = 0;
                            while(true) {
                                v33_3 = BitmapFont.capChars.length;
                                if(v14 < v33_3) {
                                    v33_9 = BitmapFont.capChars[v14];
                                    v7 = this.getGlyph(v33_9);
                                    if(v7 == null) {
                                        ++v14;
                                        continue;
                                    }
                                }

                                break;
                            }

                            if(v7 == null) {
                                v4 = this.glyphs;
                                int v19 = v4.length;
                                v16 = 0;
                                goto label_368;
                            }
                            else {
                                v33_8 = ((float)v7.height);
                                this.capHeight = v33_8;
                                goto label_560;
                            label_368:
                                while(v16 < v19) {
                                    Glyph[] v23 = v4[v16];
                                    if(v23 != null) {
                                        Glyph[] v5 = v23;
                                        int v20 = v5.length;
                                        int v15;
                                        for(v15 = 0; v15 < v20; ++v15) {
                                            v13 = v5[v15];
                                            if(v13 != null) {
                                                v33_3 = v13.height;
                                                if(v33_3 != 0) {
                                                    v33_3 = v13.width;
                                                    if(v33_3 != 0) {
                                                        v33_8 = Math.max(this.capHeight, ((float)v13.height));
                                                        this.capHeight = v33_8;
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    ++v16;
                                }
                            }

                        label_560:
                            this.ascent = v6 - this.capHeight;
                            v33_8 = -this.lineHeight;
                            this.down = v33_8;
                            if(flip) {
                                this.ascent = -this.ascent;
                                v33_8 = -this.down;
                                this.down = v33_8;
                            }
                        }
                        catch(Exception v11) {
                            goto label_51;
                        }
                        catch(Throwable v33_1) {
                            goto label_64;
                        }

                        StreamUtils.closeQuietly(((Closeable)v27));
                        return;
                    }
                }
            }
        }

        public BitmapFontData() {
            super();
            this.capHeight = 1f;
            this.scaleX = 1f;
            this.scaleY = 1f;
            this.glyphs = new Glyph[128][];
            this.xHeight = 1f;
        }

        public Glyph getFirstGlyph() {
            Glyph[][] v0 = this.glyphs;
            int v5 = v0.length;
            int v4;
            for(v4 = 0; v4 < v5; ++v4) {
                Glyph[] v7 = v0[v4];
                if(v7 != null) {
                    Glyph[] v1 = v7;
                    int v6 = v1.length;
                    int v3;
                    for(v3 = 0; v3 < v6; ++v3) {
                        Glyph v2 = v1[v3];
                        if(v2 != null && v2.height != 0 && v2.width != 0) {
                            return v2;
                        }
                    }
                }
            }

            throw new GdxRuntimeException("No glyphs found!");
        }

        public FileHandle getFontFile() {
            return this.fontFile;
        }

        public Glyph getGlyph(char ch) {
            Glyph v1;
            Glyph[] v0 = this.glyphs[ch / 512];
            if(v0 != null) {
                v1 = v0[ch & 511];
            }
            else {
                v1 = null;
            }

            return v1;
        }

        public String getImagePath() {
            return this.imagePath;
        }

        public String getImagePath(int index) {
            return this.imagePaths[index];
        }

        public String[] getImagePaths() {
            return this.imagePaths;
        }

        public void setGlyph(int ch, Glyph glyph) {
            Glyph[] v0 = this.glyphs[ch / 512];
            if(v0 == null) {
                Glyph[][] v1 = this.glyphs;
                v0 = new Glyph[512];
                v1[ch / 512] = v0;
            }

            v0[ch & 511] = glyph;
        }
    }

    public class Glyph {
        public int id;
        public byte[][] kerning;
        public int xadvance;

        public Glyph() {
            super();
            this.page = 0;
        }

        public int getKerning(char ch) {
            int v1;
            if(this.kerning != null) {
                byte[] v0 = this.kerning[ch >>> 9];
                if(v0 != null) {
                    v1 = v0[ch & 511];
                }
                else {
                    goto label_9;
                }
            }
            else {
            label_9:
                v1 = 0;
            }

            return v1;
        }

        public void setKerning(int ch, int value) {
            if(this.kerning == null) {
                this.kerning = new byte[128][];
            }

            byte[] v0 = this.kerning[ch >>> 9];
            if(v0 == null) {
                byte[][] v1 = this.kerning;
                v0 = new byte[512];
                v1[ch >>> 9] = v0;
            }

            v0[ch & 511] = ((byte)value);
        }
    }

    public enum HAlignment {
        public static final enum HAlignment CENTER;
        public static final enum HAlignment RIGHT;

        static  {
            HAlignment.LEFT = new HAlignment("LEFT", 0);
            HAlignment.CENTER = new HAlignment("CENTER", 1);
            HAlignment.RIGHT = new HAlignment("RIGHT", 2);
            HAlignment[] v0 = new HAlignment[3];
            v0[0] = HAlignment.LEFT;
            v0[1] = HAlignment.CENTER;
            v0[2] = HAlignment.RIGHT;
            HAlignment.$VALUES = v0;
        }

        private HAlignment(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static HAlignment valueOf(String name) {
            return Enum.valueOf(HAlignment.class, name);
        }

        public static HAlignment[] values() {
            return HAlignment.$VALUES.clone();
        }
    }

    public class TextBounds {
        public TextBounds() {
            super();
        }

        public TextBounds(TextBounds bounds) {
            super();
            this.set(bounds);
        }

        public void set(TextBounds bounds) {
            this.width = bounds.width;
            this.height = bounds.height;
        }
    }

    private static final int LOG2_PAGE_SIZE = 9;
    private static final int PAGES = 128;
    private static final int PAGE_SIZE = 512;
    private final BitmapFontCache cache;
    public static final char[] capChars;
    final BitmapFontData data;
    private boolean flipped;
    private boolean integer;
    private boolean ownsTexture;
    TextureRegion[] regions;
    public static final char[] xChars;

    static  {
        BitmapFont.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
        BitmapFont.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    }

    public BitmapFont() {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), false, true);
    }

    public BitmapFont(FileHandle fontFile, FileHandle imageFile, boolean flip, boolean integer) {
        this(new BitmapFontData(fontFile, flip), new TextureRegion(new Texture(imageFile, false)), integer);
        this.ownsTexture = true;
    }

    public BitmapFont(FileHandle fontFile) {
        this(fontFile, false);
    }

    public BitmapFont(FileHandle fontFile, boolean flip) {
        this(new BitmapFontData(fontFile, flip), null, true);
    }

    public BitmapFont(FileHandle fontFile, FileHandle imageFile, boolean flip) {
        this(fontFile, imageFile, flip, true);
    }

    public BitmapFont(BitmapFontData data, TextureRegion region, boolean integer) {
        TextureRegion[] v0;
        if(region != null) {
            v0 = new TextureRegion[1];
            v0[0] = region;
        }
        else {
            v0 = null;
        }

        this(data, v0, integer);
    }

    public BitmapFont(FileHandle fontFile, TextureRegion region) {
        this(fontFile, region, false);
    }

    public BitmapFont(FileHandle fontFile, TextureRegion region, boolean flip) {
        this(new BitmapFontData(fontFile, flip), region, true);
    }

    public BitmapFont(BitmapFontData data, TextureRegion[] regions, boolean integer) {
        super();
        if(regions == null || regions.length == 0) {
            this.regions = new TextureRegion[data.imagePaths.length];
            int v0;
            for(v0 = 0; v0 < this.regions.length; ++v0) {
                if(data.fontFile == null) {
                    this.regions[v0] = new TextureRegion(new Texture(Gdx.files.internal(data.imagePaths[v0]), false));
                }
                else {
                    this.regions[v0] = new TextureRegion(new Texture(Gdx.files.getFileHandle(data.imagePaths[v0], data.fontFile.type()), false));
                }
            }

            this.ownsTexture = true;
        }
        else {
            this.regions = regions;
            this.ownsTexture = false;
        }

        this.cache = new BitmapFontCache(this);
        this.cache.setUseIntegerPositions(integer);
        this.flipped = data.flipped;
        this.data = data;
        this.integer = integer;
        this.load(data);
    }

    public BitmapFont(boolean flip) {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), flip, true);
    }

    public void computeGlyphAdvancesAndPositions(CharSequence str, FloatArray glyphAdvances, FloatArray glyphPositions) {
        Glyph v3;
        char v0;
        glyphAdvances.clear();
        glyphPositions.clear();
        int v4 = 0;
        int v2 = str.length();
        float v7 = 0f;
        Glyph v5 = null;
        BitmapFontData v1 = this.data;
        if(v1.scaleX == 1f) {
            while(v4 < v2) {
                v0 = str.charAt(v4);
                v3 = v1.getGlyph(v0);
                if(v3 != null) {
                    if(v5 != null) {
                        v7 += ((float)v5.getKerning(v0));
                    }

                    v5 = v3;
                    glyphAdvances.add(((float)v3.xadvance));
                    glyphPositions.add(v7);
                    v7 += ((float)v3.xadvance);
                }

                ++v4;
            }

            glyphAdvances.add(0f);
            glyphPositions.add(v7);
        }
        else {
            float v6 = this.data.scaleX;
            while(v4 < v2) {
                v0 = str.charAt(v4);
                v3 = v1.getGlyph(v0);
                if(v3 != null) {
                    if(v5 != null) {
                        v7 += (((float)v5.getKerning(v0))) * v6;
                    }

                    v5 = v3;
                    float v8 = (((float)v3.xadvance)) * v6;
                    glyphAdvances.add(v8);
                    glyphPositions.add(v7);
                    v7 += v8;
                }

                ++v4;
            }

            glyphAdvances.add(0f);
            glyphPositions.add(v7);
        }
    }

    public int computeVisibleGlyphs(CharSequence str, int start, int end, float availableWidth) {
        BitmapFontData v1 = this.data;
        int v3 = start;
        float v5 = 0f;
        Glyph v4 = null;
        availableWidth /= v1.scaleX;
        while(v3 < end) {
            char v0 = str.charAt(v3);
            Glyph v2 = v1.getGlyph(v0);
            if(v2 != null) {
                if(v4 != null) {
                    v5 += ((float)v4.getKerning(v0));
                }

                if((((float)v2.xadvance)) + v5 - availableWidth <= 0.001f) {
                    goto label_22;
                }

                break;
            label_22:
                v5 += ((float)v2.xadvance);
                v4 = v2;
            }

            ++v3;
        }

        return v3 - start;
    }

    public boolean containsCharacter(char character) {
        boolean v0;
        if(this.data.getGlyph(character) != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void dispose() {
        if(this.ownsTexture) {
            int v0;
            for(v0 = 0; v0 < this.regions.length; ++v0) {
                this.regions[v0].getTexture().dispose();
            }
        }
    }

    public TextBounds draw(Batch batch, CharSequence str, float x, float y) {
        this.cache.clear();
        TextBounds v6 = this.cache.addText(str, x, y, 0, str.length());
        this.cache.draw(batch);
        return v6;
    }

    public TextBounds draw(Batch batch, CharSequence str, float x, float y, int start, int end) {
        this.cache.clear();
        TextBounds v6 = this.cache.addText(str, x, y, start, end);
        this.cache.draw(batch);
        return v6;
    }

    public TextBounds drawMultiLine(Batch batch, CharSequence str, float x, float y) {
        this.cache.clear();
        TextBounds v6 = this.cache.addMultiLineText(str, x, y, 0f, HAlignment.LEFT);
        this.cache.draw(batch);
        return v6;
    }

    public TextBounds drawMultiLine(Batch batch, CharSequence str, float x, float y, float alignmentWidth, HAlignment alignment) {
        this.cache.clear();
        TextBounds v6 = this.cache.addMultiLineText(str, x, y, alignmentWidth, alignment);
        this.cache.draw(batch);
        return v6;
    }

    public TextBounds drawWrapped(Batch batch, CharSequence str, float x, float y, float wrapWidth) {
        this.cache.clear();
        TextBounds v6 = this.cache.addWrappedText(str, x, y, wrapWidth, HAlignment.LEFT);
        this.cache.draw(batch);
        return v6;
    }

    public TextBounds drawWrapped(Batch batch, CharSequence str, float x, float y, float wrapWidth, HAlignment alignment) {
        this.cache.clear();
        TextBounds v6 = this.cache.addWrappedText(str, x, y, wrapWidth, alignment);
        this.cache.draw(batch);
        return v6;
    }

    public float getAscent() {
        return this.data.ascent;
    }

    public TextBounds getBounds(CharSequence str) {
        return this.getBounds(str, 0, str.length(), this.cache.getBounds());
    }

    public TextBounds getBounds(CharSequence str, int start, int end, TextBounds textBounds) {
        BitmapFontData v1 = this.data;
        int v5 = 0;
        Glyph v3 = null;
        int v4 = start;
        while(v4 < end) {
            start = v4 + 1;
            v3 = v1.getGlyph(str.charAt(v4));
            if(v3 != null) {
                v5 = v3.xadvance;
                v4 = start;
            }
            else {
                v4 = start;
                continue;
            }

            break;
        }

        while(true) {
            if(v4 >= end) {
                break;
            }

            start = v4 + 1;
            char v0 = str.charAt(v4);
            Glyph v2 = v1.getGlyph(v0);
            if(v2 != null) {
                v5 += v3.getKerning(v0);
                v3 = v2;
                v5 += v2.xadvance;
            }

            v4 = start;
        }

        textBounds.width = (((float)v5)) * v1.scaleX;
        textBounds.height = v1.capHeight;
        return textBounds;
    }

    public TextBounds getBounds(CharSequence str, int start, int end) {
        return this.getBounds(str, start, end, this.cache.getBounds());
    }

    public TextBounds getBounds(CharSequence str, TextBounds textBounds) {
        return this.getBounds(str, 0, str.length(), textBounds);
    }

    public BitmapFontCache getCache() {
        return this.cache;
    }

    public float getCapHeight() {
        return this.data.capHeight;
    }

    public Color getColor() {
        return this.cache.getColor();
    }

    public BitmapFontData getData() {
        return this.data;
    }

    public float getDescent() {
        return this.data.descent;
    }

    public float getLineHeight() {
        return this.data.lineHeight;
    }

    public TextBounds getMultiLineBounds(CharSequence str) {
        return this.getMultiLineBounds(str, this.cache.getBounds());
    }

    public TextBounds getMultiLineBounds(CharSequence str, TextBounds textBounds) {
        int v5 = 0;
        float v3 = 0f;
        int v4 = 0;
        int v0 = str.length();
        while(v5 < v0) {
            int v1 = BitmapFont.indexOf(str, '\n', v5);
            v3 = Math.max(v3, this.getBounds(str, v5, v1).width);
            v5 = v1 + 1;
            ++v4;
        }

        textBounds.width = v3;
        textBounds.height = this.data.capHeight + (((float)(v4 - 1))) * this.data.lineHeight;
        return textBounds;
    }

    public TextureRegion getRegion() {
        return this.regions[0];
    }

    public TextureRegion getRegion(int index) {
        return this.regions[index];
    }

    public TextureRegion[] getRegions() {
        return this.regions;
    }

    public float getScaleX() {
        return this.data.scaleX;
    }

    public float getScaleY() {
        return this.data.scaleY;
    }

    public float getSpaceWidth() {
        return this.data.spaceWidth;
    }

    public TextBounds getWrappedBounds(CharSequence str, float wrapWidth) {
        return this.getWrappedBounds(str, wrapWidth, this.cache.getBounds());
    }

    public TextBounds getWrappedBounds(CharSequence str, float wrapWidth, TextBounds textBounds) {
        if(wrapWidth <= 0f) {
            wrapWidth = 2147483648f;
        }

        int v7 = 0;
        int v6 = 0;
        int v0 = str.length();
        float v3 = 0f;
        while(v7 < v0) {
            int v4 = BitmapFont.indexOf(str, '\n', v7);
            while(v7 < v4) {
                if(!BitmapFont.isWhitespace(str.charAt(v7))) {
                    break;
                }

                ++v7;
            }

            int v1 = v7 + this.computeVisibleGlyphs(str, v7, v4, wrapWidth);
            int v5 = v1 + 1;
            if(v1 < v4) {
                while(v1 > v7) {
                    if(BitmapFont.isWhitespace(str.charAt(v1))) {
                        break;
                    }

                    --v1;
                }

                if(v1 != v7) {
                    goto label_38;
                }

                if(v5 > v7 + 1) {
                    --v5;
                }

                v1 = v5;
                goto label_27;
            label_38:
                v5 = v1;
                while(v1 > v7) {
                    if(!BitmapFont.isWhitespace(str.charAt(v1 - 1))) {
                        break;
                    }

                    --v1;
                }
            }

        label_27:
            if(v1 > v7) {
                v3 = Math.max(v3, this.getBounds(str, v7, v1).width);
            }

            v7 = v5;
            ++v6;
        }

        textBounds.width = v3;
        textBounds.height = this.data.capHeight + (((float)(v6 - 1))) * this.data.lineHeight;
        return textBounds;
    }

    public float getXHeight() {
        return this.data.xHeight;
    }

    static int indexOf(CharSequence text, char ch, int start) {
        int v0 = text.length();
        while(true) {
            if(start >= v0) {
                break;
            }
            else if(text.charAt(start) != ch) {
                ++start;
                continue;
            }

            goto label_4;
        }

        start = v0;
    label_4:
        return start;
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    static boolean isWhitespace(char c) {
        boolean v0;
        switch(c) {
            case 9: 
            case 10: 
            case 13: 
            case 32: {
                v0 = true;
                break;
            }
            default: {
                v0 = false;
                break;
            }
        }

        return v0;
    }

    private void load(BitmapFontData data) {
        Glyph[][] v2 = data.glyphs;
        int v10 = v2.length;
        int v7;
        for(v7 = 0; v7 < v10; ++v7) {
            Glyph[] v14 = v2[v7];
            if(v14 != null) {
                Glyph[] v3 = v14;
                int v11 = v3.length;
                int v6;
                for(v6 = 0; v6 < v11; ++v6) {
                    Glyph v5 = v3[v6];
                    if(v5 != null) {
                        TextureRegion v15 = this.regions[v5.page];
                        if(v15 == null) {
                            throw new IllegalArgumentException("BitmapFont texture region array cannot contain null elements");
                        }
                        else {
                            float v9 = 1f / (((float)v15.getTexture().getWidth()));
                            float v8 = 1f / (((float)v15.getTexture().getHeight()));
                            float v12 = 0f;
                            float v13 = 0f;
                            float v18 = v15.u;
                            float v19 = v15.v;
                            float v17 = ((float)v15.getRegionWidth());
                            float v16 = ((float)v15.getRegionHeight());
                            if((v15 instanceof AtlasRegion)) {
                                v12 = v15.offsetX;
                                v13 = (((float)(v15.originalHeight - v15.packedHeight))) - v15.offsetY;
                            }

                            float v20 = ((float)v5.srcX);
                            float v21 = ((float)(v5.srcX + v5.width));
                            float v22 = ((float)v5.srcY);
                            float v23 = ((float)(v5.srcY + v5.height));
                            if(v12 > 0f) {
                                v20 -= v12;
                                if(v20 < 0f) {
                                    v5.width = ((int)((((float)v5.width)) + v20));
                                    v5.xoffset = ((int)((((float)v5.xoffset)) - v20));
                                    v20 = 0f;
                                }

                                v21 -= v12;
                                if(v21 <= v17) {
                                    goto label_142;
                                }

                                v5.width = ((int)((((float)v5.width)) - (v21 - v17)));
                                v21 = v17;
                            }

                        label_142:
                            if(v13 > 0f) {
                                v22 -= v13;
                                if(v22 < 0f) {
                                    v5.height = ((int)((((float)v5.height)) + v22));
                                    v22 = 0f;
                                }

                                v23 -= v13;
                                if(v23 <= v16) {
                                    goto label_185;
                                }

                                float v1 = v23 - v16;
                                v5.height = ((int)((((float)v5.height)) - v1));
                                v5.yoffset = ((int)((((float)v5.yoffset)) + v1));
                                v23 = v16;
                            }

                        label_185:
                            v5.u = v20 * v9 + v18;
                            v5.u2 = v21 * v9 + v18;
                            if(!data.flipped) {
                                goto label_206;
                            }

                            v5.v = v22 * v8 + v19;
                            v5.v2 = v23 * v8 + v19;
                            goto label_17;
                        label_206:
                            v5.v2 = v22 * v8 + v19;
                            v5.v = v23 * v8 + v19;
                        }
                    }

                label_17:
                }
            }
        }
    }

    public boolean ownsTexture() {
        return this.ownsTexture;
    }

    public void scale(float amount) {
        this.setScale(this.data.scaleX + amount, this.data.scaleY + amount);
    }

    public void setColor(float color) {
        this.cache.setColor(color);
    }

    public void setColor(float r, float g, float b, float a) {
        this.cache.setColor(r, g, b, a);
    }

    public void setColor(Color color) {
        this.cache.setColor(color);
    }

    public void setFixedWidthGlyphs(CharSequence glyphs) {
        Glyph v2;
        BitmapFontData v0 = this.data;
        int v4 = 0;
        int v3 = 0;
        int v1 = glyphs.length();
        while(v3 < v1) {
            v2 = v0.getGlyph(glyphs.charAt(v3));
            if(v2 != null && v2.xadvance > v4) {
                v4 = v2.xadvance;
            }

            ++v3;
        }

        v3 = 0;
        v1 = glyphs.length();
        while(v3 < v1) {
            v2 = v0.getGlyph(glyphs.charAt(v3));
            if(v2 != null) {
                v2.xoffset += (v4 - v2.xadvance) / 2;
                v2.xadvance = v4;
                v2.kerning = null;
            }

            ++v3;
        }
    }

    public void setOwnsTexture(boolean ownsTexture) {
        this.ownsTexture = ownsTexture;
    }

    public void setScale(float scaleX, float scaleY) {
        if(scaleX != 0f && scaleY != 0f) {
            BitmapFontData v0 = this.data;
            float v1 = scaleX / v0.scaleX;
            float v2 = scaleY / v0.scaleY;
            v0.lineHeight *= v2;
            v0.spaceWidth *= v1;
            v0.xHeight *= v2;
            v0.capHeight *= v2;
            v0.ascent *= v2;
            v0.descent *= v2;
            v0.down *= v2;
            v0.scaleX = scaleX;
            v0.scaleY = scaleY;
            return;
        }

        throw new IllegalArgumentException("Scale must not be zero");
    }

    public void setScale(float scaleXY) {
        this.setScale(scaleXY, scaleXY);
    }

    public void setUseIntegerPositions(boolean integer) {
        this.integer = integer;
        this.cache.setUseIntegerPositions(integer);
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }
}

