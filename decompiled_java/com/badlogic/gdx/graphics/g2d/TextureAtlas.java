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
import com.badlogic.gdx.graphics.Pixmap$Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.Texture$TextureWrap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Sort;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;

public class TextureAtlas implements Disposable {
    public class AtlasRegion extends TextureRegion {
        public int index;
        public String name;
        public int originalWidth;
        public int packedWidth;
        public int[] pads;
        public boolean rotate;
        public int[] splits;

        public AtlasRegion(Texture texture, int x, int y, int width, int height) {
            super(texture, x, y, width, height);
            this.originalWidth = width;
            this.originalHeight = height;
            this.packedWidth = width;
            this.packedHeight = height;
        }

        public AtlasRegion(AtlasRegion region) {
            super();
            this.setRegion(((TextureRegion)region));
            this.index = region.index;
            this.name = region.name;
            this.offsetX = region.offsetX;
            this.offsetY = region.offsetY;
            this.packedWidth = region.packedWidth;
            this.packedHeight = region.packedHeight;
            this.originalWidth = region.originalWidth;
            this.originalHeight = region.originalHeight;
            this.rotate = region.rotate;
            this.splits = region.splits;
        }

        public void flip(boolean x, boolean y) {
            super.flip(x, y);
            if(x) {
                this.offsetX = (((float)this.originalWidth)) - this.offsetX - this.getRotatedPackedWidth();
            }

            if(y) {
                this.offsetY = (((float)this.originalHeight)) - this.offsetY - this.getRotatedPackedHeight();
            }
        }

        public float getRotatedPackedHeight() {
            float v0;
            if(this.rotate) {
                v0 = ((float)this.packedWidth);
            }
            else {
                v0 = ((float)this.packedHeight);
            }

            return v0;
        }

        public float getRotatedPackedWidth() {
            float v0;
            if(this.rotate) {
                v0 = ((float)this.packedHeight);
            }
            else {
                v0 = ((float)this.packedWidth);
            }

            return v0;
        }
    }

    public class AtlasSprite extends Sprite {
        float originalOffsetX;
        float originalOffsetY;
        final AtlasRegion region;

        public AtlasSprite(AtlasRegion region) {
            float v6 = 1f;
            super();
            this.region = new AtlasRegion(region);
            this.originalOffsetX = region.offsetX;
            this.originalOffsetY = region.offsetY;
            this.setRegion(((TextureRegion)region));
            this.setOrigin((((float)region.originalWidth)) / 2f, (((float)region.originalHeight)) / 2f);
            int v1 = region.getRegionWidth();
            int v0 = region.getRegionHeight();
            if(region.rotate) {
                super.rotate90(true);
                super.setBounds(region.offsetX, region.offsetY, ((float)v0), ((float)v1));
            }
            else {
                super.setBounds(region.offsetX, region.offsetY, ((float)v1), ((float)v0));
            }

            this.setColor(v6, v6, v6, v6);
        }

        public AtlasSprite(AtlasSprite sprite) {
            super();
            this.region = sprite.region;
            this.originalOffsetX = sprite.originalOffsetX;
            this.originalOffsetY = sprite.originalOffsetY;
            this.set(((Sprite)sprite));
        }

        public void flip(boolean x, boolean y) {
            super.flip(x, y);
            float v3 = this.getOriginX();
            float v4 = this.getOriginY();
            float v1 = this.region.offsetX;
            float v2 = this.region.offsetY;
            float v5 = this.getWidthRatio();
            float v0 = this.getHeightRatio();
            this.region.offsetX = this.originalOffsetX;
            this.region.offsetY = this.originalOffsetY;
            this.region.flip(x, y);
            this.originalOffsetX = this.region.offsetX;
            this.originalOffsetY = this.region.offsetY;
            this.region.offsetX *= v5;
            this.region.offsetY *= v0;
            this.translate(this.region.offsetX - v1, this.region.offsetY - v2);
            this.setOrigin(v3, v4);
        }

        public AtlasRegion getAtlasRegion() {
            return this.region;
        }

        public float getHeight() {
            return super.getHeight() / this.region.getRotatedPackedHeight() * (((float)this.region.originalHeight));
        }

        public float getHeightRatio() {
            return super.getHeight() / this.region.getRotatedPackedHeight();
        }

        public float getOriginX() {
            return super.getOriginX() + this.region.offsetX;
        }

        public float getOriginY() {
            return super.getOriginY() + this.region.offsetY;
        }

        public float getWidth() {
            return super.getWidth() / this.region.getRotatedPackedWidth() * (((float)this.region.originalWidth));
        }

        public float getWidthRatio() {
            return super.getWidth() / this.region.getRotatedPackedWidth();
        }

        public float getX() {
            return super.getX() - this.region.offsetX;
        }

        public float getY() {
            return super.getY() - this.region.offsetY;
        }

        public void rotate90(boolean clockwise) {
            super.rotate90(clockwise);
            float v3 = this.getOriginX();
            float v4 = this.getOriginY();
            float v1 = this.region.offsetX;
            float v2 = this.region.offsetY;
            float v5 = this.getWidthRatio();
            float v0 = this.getHeightRatio();
            if(clockwise) {
                this.region.offsetX = v2;
                this.region.offsetY = (((float)this.region.originalHeight)) * v0 - v1 - (((float)this.region.packedWidth)) * v5;
            }
            else {
                this.region.offsetX = (((float)this.region.originalWidth)) * v5 - v2 - (((float)this.region.packedHeight)) * v0;
                this.region.offsetY = v1;
            }

            this.translate(this.region.offsetX - v1, this.region.offsetY - v2);
            this.setOrigin(v3, v4);
        }

        public void setBounds(float x, float y, float width, float height) {
            int v1;
            int v2;
            float v3 = width / (((float)this.region.originalWidth));
            float v0 = height / (((float)this.region.originalHeight));
            this.region.offsetX = this.originalOffsetX * v3;
            this.region.offsetY = this.originalOffsetY * v0;
            if(this.region.rotate) {
                v2 = this.region.packedHeight;
            }
            else {
                v2 = this.region.packedWidth;
            }

            if(this.region.rotate) {
                v1 = this.region.packedWidth;
            }
            else {
                v1 = this.region.packedHeight;
            }

            super.setBounds(this.region.offsetX + x, this.region.offsetY + y, (((float)v2)) * v3, (((float)v1)) * v0);
        }

        public void setOrigin(float originX, float originY) {
            super.setOrigin(originX - this.region.offsetX, originY - this.region.offsetY);
        }

        public void setPosition(float x, float y) {
            super.setPosition(this.region.offsetX + x, this.region.offsetY + y);
        }

        public void setSize(float width, float height) {
            this.setBounds(this.getX(), this.getY(), width, height);
        }

        public void setX(float x) {
            super.setX(this.region.offsetX + x);
        }

        public void setY(float y) {
            super.setY(this.region.offsetY + y);
        }
    }

    public class TextureAtlasData {
        public class Page {
            public Page(FileHandle handle, boolean useMipMaps, Format format, TextureFilter minFilter, TextureFilter magFilter, TextureWrap uWrap, TextureWrap vWrap) {
                super();
                this.textureFile = handle;
                this.useMipMaps = useMipMaps;
                this.format = format;
                this.minFilter = minFilter;
                this.magFilter = magFilter;
                this.uWrap = uWrap;
                this.vWrap = vWrap;
            }
        }

        public class Region {
            public Region() {
                super();
            }
        }

        final Array pages;
        final Array regions;

        public TextureAtlasData(FileHandle packFile, FileHandle imagesDir, boolean flip) {  // has try-catch handlers
            TextureWrap v11;
            TextureWrap v10;
            TextureFilter v9;
            TextureFilter v8;
            Format v7;
            FileHandle v5;
            Page v4;
            String v16;
            super();
            this.pages = new Array();
            this.regions = new Array();
            BufferedReader v18 = new BufferedReader(new InputStreamReader(packFile.read()), 64);
            Page v17 = null;
            try {
                while(true) {
                label_17:
                    v16 = v18.readLine();
                    if(v16 != null) {
                        goto label_38;
                    }

                    break;
                }
            }
            catch(Throwable v6) {
                goto label_84;
            }
            catch(Exception v13) {
                goto label_246;
            }

            StreamUtils.closeQuietly(((Closeable)v18));
            new Sort().sort(this.regions.items, TextureAtlas.indexComparator, 0, this.regions.size);
            return;
            try {
            label_38:
                if(v16.trim().length() == 0) {
                    v4 = null;
                }
                else if(v17 == null) {
                    v5 = imagesDir.child(v16);
                    v7 = Format.valueOf(TextureAtlas.readValue(v18));
                    TextureAtlas.readTuple(v18);
                    v8 = TextureFilter.valueOf(TextureAtlas.tuple[0]);
                    v9 = TextureFilter.valueOf(TextureAtlas.tuple[1]);
                    String v12 = TextureAtlas.readValue(v18);
                    v10 = TextureWrap.ClampToEdge;
                    v11 = TextureWrap.ClampToEdge;
                    if(v12.equals("x")) {
                        v10 = TextureWrap.Repeat;
                    }
                    else {
                        goto label_86;
                    }

                    goto label_66;
                }
                else {
                    goto label_97;
                }

                goto label_42;
            }
            catch(Exception v13) {
                goto label_246;
            }

            try {
            label_86:
                if(v12.equals("y")) {
                    v11 = TextureWrap.Repeat;
                }
                else if(v12.equals("xy")) {
                    v10 = TextureWrap.Repeat;
                    v11 = TextureWrap.Repeat;
                }

                goto label_66;
            }
            catch(Throwable v6) {
                goto label_84;
            }
            catch(Exception v13) {
                goto label_246;
            }

            try {
            label_66:
                v4 = new Page(v5, v8.isMipMap(), v7, v8, v9, v10, v11);
                goto label_68;
            }
            catch(Exception v13) {
                goto label_246;
            }

            try {
            label_68:
                this.pages.add(v4);
                goto label_42;
            }
            catch(Exception v13) {
                goto label_73;
            }

            try {
            label_97:
                boolean v20 = Boolean.valueOf(TextureAtlas.readValue(v18)).booleanValue();
                TextureAtlas.readTuple(v18);
                int v15 = Integer.parseInt(TextureAtlas.tuple[0]);
                int v21 = Integer.parseInt(TextureAtlas.tuple[1]);
                TextureAtlas.readTuple(v18);
                int v22 = Integer.parseInt(TextureAtlas.tuple[0]);
                int v14 = Integer.parseInt(TextureAtlas.tuple[1]);
                Region v19 = new Region();
                v19.page = v17;
                v19.left = v15;
                v19.top = v21;
                v19.width = v22;
                v19.height = v14;
                v19.name = v16;
                v19.rotate = v20;
                if(TextureAtlas.readTuple(v18) == 4) {
                    int[] v6_1 = new int[4];
                    v6_1[0] = Integer.parseInt(TextureAtlas.tuple[0]);
                    v6_1[1] = Integer.parseInt(TextureAtlas.tuple[1]);
                    v6_1[2] = Integer.parseInt(TextureAtlas.tuple[2]);
                    v6_1[3] = Integer.parseInt(TextureAtlas.tuple[3]);
                    v19.splits = v6_1;
                    if(TextureAtlas.readTuple(v18) == 4) {
                        v6_1 = new int[4];
                        v6_1[0] = Integer.parseInt(TextureAtlas.tuple[0]);
                        v6_1[1] = Integer.parseInt(TextureAtlas.tuple[1]);
                        v6_1[2] = Integer.parseInt(TextureAtlas.tuple[2]);
                        v6_1[3] = Integer.parseInt(TextureAtlas.tuple[3]);
                        v19.pads = v6_1;
                        TextureAtlas.readTuple(v18);
                    }
                }

                v19.originalWidth = Integer.parseInt(TextureAtlas.tuple[0]);
                v19.originalHeight = Integer.parseInt(TextureAtlas.tuple[1]);
                TextureAtlas.readTuple(v18);
                v19.offsetX = ((float)Integer.parseInt(TextureAtlas.tuple[0]));
                v19.offsetY = ((float)Integer.parseInt(TextureAtlas.tuple[1]));
                v19.index = Integer.parseInt(TextureAtlas.readValue(v18));
                if(flip) {
                    v19.flip = true;
                }

                this.regions.add(v19);
                v4 = v17;
            }
            catch(Throwable v6) {
                goto label_84;
            }
            catch(Exception v13) {
                goto label_246;
            }

        label_42:
            v17 = v4;
            goto label_17;
        label_246:
            try {
            label_73:
                throw new GdxRuntimeException("Error reading pack file: " + packFile, ((Throwable)v13));
            }
            catch(Throwable v6) {
            label_84:
                StreamUtils.closeQuietly(((Closeable)v18));
                throw v6;
            }
        }

        public Array getPages() {
            return this.pages;
        }

        public Array getRegions() {
            return this.regions;
        }
    }

    static final Comparator indexComparator;
    private final Array regions;
    private final ObjectSet textures;
    static final String[] tuple;

    static  {
        TextureAtlas.tuple = new String[4];
        TextureAtlas.indexComparator = new Comparator() {
            public int compare(Region region1, Region region2) {
                int v2 = -1;
                int v0 = region1.index;
                if(v0 == v2) {
                    v0 = 2147483647;
                }

                int v1 = region2.index;
                if(v1 == v2) {
                    v1 = 2147483647;
                }

                return v0 - v1;
            }

            public int compare(Object x0, Object x1) {
                return this.compare(((Region)x0), ((Region)x1));
            }
        };
    }

    public TextureAtlas(FileHandle packFile) {
        this(packFile, packFile.parent());
    }

    public TextureAtlas() {
        super();
        this.textures = new ObjectSet(4);
        this.regions = new Array();
    }

    public TextureAtlas(FileHandle packFile, FileHandle imagesDir) {
        this(packFile, imagesDir, false);
    }

    public TextureAtlas(FileHandle packFile, FileHandle imagesDir, boolean flip) {
        this(new TextureAtlasData(packFile, imagesDir, flip));
    }

    public TextureAtlas(TextureAtlasData data) {
        super();
        this.textures = new ObjectSet(4);
        this.regions = new Array();
        if(data != null) {
            this.load(data);
        }
    }

    public TextureAtlas(FileHandle packFile, boolean flip) {
        this(packFile, packFile.parent(), flip);
    }

    public TextureAtlas(String internalPackFile) {
        this(Gdx.files.internal(internalPackFile));
    }

    public AtlasRegion addRegion(String name, TextureRegion textureRegion) {
        return this.addRegion(name, textureRegion.texture, textureRegion.getRegionX(), textureRegion.getRegionY(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    public AtlasRegion addRegion(String name, Texture texture, int x, int y, int width, int height) {
        this.textures.add(texture);
        AtlasRegion v0 = new AtlasRegion(texture, x, y, width, height);
        v0.name = name;
        v0.originalWidth = width;
        v0.originalHeight = height;
        v0.index = -1;
        this.regions.add(v0);
        return v0;
    }

    public NinePatch createPatch(String name) {
        NinePatch v0;
        int v12 = 3;
        int v11 = 2;
        int v6 = 0;
        int v7 = this.regions.size;
        while(true) {
            if(v6 < v7) {
                Object v1 = this.regions.get(v6);
                if(((AtlasRegion)v1).name.equals(name)) {
                    int[] v8 = ((AtlasRegion)v1).splits;
                    if(v8 == null) {
                        throw new IllegalArgumentException("Region does not have ninepatch splits: " + name);
                    }
                    else {
                        v0 = new NinePatch(((TextureRegion)v1), v8[0], v8[1], v8[v11], v8[v12]);
                        if(((AtlasRegion)v1).pads != null) {
                            v0.setPadding(((AtlasRegion)v1).pads[0], ((AtlasRegion)v1).pads[1], ((AtlasRegion)v1).pads[v11], ((AtlasRegion)v1).pads[v12]);
                        }
                    }
                }
                else {
                    ++v6;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_38;
        }

        v0 = null;
    label_38:
        return v0;
    }

    public Sprite createSprite(String name) {
        Sprite v2;
        int v0 = 0;
        int v1 = this.regions.size;
        while(true) {
            if(v0 >= v1) {
                break;
            }
            else if(this.regions.get(v0).name.equals(name)) {
                v2 = this.newSprite(this.regions.get(v0));
            }
            else {
                ++v0;
                continue;
            }

            goto label_12;
        }

        v2 = null;
    label_12:
        return v2;
    }

    public Sprite createSprite(String name, int index) {
        Sprite v3;
        int v0 = 0;
        int v1 = this.regions.size;
        while(v0 < v1) {
            Object v2 = this.regions.get(v0);
            if((((AtlasRegion)v2).name.equals(name)) && ((AtlasRegion)v2).index == index) {
                v3 = this.newSprite(this.regions.get(v0));
                goto label_16;
            }

            ++v0;
        }

        v3 = null;
    label_16:
        return v3;
    }

    public Array createSprites() {
        Array v2 = new Array(this.regions.size);
        int v0 = 0;
        int v1 = this.regions.size;
        while(v0 < v1) {
            v2.add(this.newSprite(this.regions.get(v0)));
            ++v0;
        }

        return v2;
    }

    public Array createSprites(String name) {
        Array v1 = new Array();
        int v0 = 0;
        int v2 = this.regions.size;
        while(v0 < v2) {
            Object v3 = this.regions.get(v0);
            if(((AtlasRegion)v3).name.equals(name)) {
                v1.add(this.newSprite(((AtlasRegion)v3)));
            }

            ++v0;
        }

        return v1;
    }

    public void dispose() {
        Iterator v0 = this.textures.iterator();
        while(v0.hasNext()) {
            v0.next().dispose();
        }

        this.textures.clear();
    }

    public AtlasRegion findRegion(String name) {
        Object v2;
        int v0 = 0;
        int v1 = this.regions.size;
        while(true) {
            if(v0 >= v1) {
                break;
            }
            else if(this.regions.get(v0).name.equals(name)) {
                v2 = this.regions.get(v0);
            }
            else {
                ++v0;
                continue;
            }

            goto label_11;
        }

        AtlasRegion v2_1 = null;
    label_11:
        return ((AtlasRegion)v2);
    }

    public AtlasRegion findRegion(String name, int index) {
        int v0 = 0;
        int v1 = this.regions.size;
        while(v0 < v1) {
            Object v2 = this.regions.get(v0);
            if((((AtlasRegion)v2).name.equals(name)) && ((AtlasRegion)v2).index == index) {
                goto label_13;
            }

            ++v0;
        }

        AtlasRegion v2_1 = null;
    label_13:
        return v2_1;
    }

    public Array findRegions(String name) {
        Array v1 = new Array();
        int v0 = 0;
        int v2 = this.regions.size;
        while(v0 < v2) {
            Object v3 = this.regions.get(v0);
            if(((AtlasRegion)v3).name.equals(name)) {
                v1.add(new AtlasRegion(((AtlasRegion)v3)));
            }

            ++v0;
        }

        return v1;
    }

    public Array getRegions() {
        return this.regions;
    }

    public ObjectSet getTextures() {
        return this.textures;
    }

    private void load(TextureAtlasData data) {
        int v5;
        int v4;
        Texture v11;
        ObjectMap v9 = new ObjectMap();
        Iterator v7 = data.pages.iterator();
        while(v7.hasNext()) {
            Object v8 = v7.next();
            if(((Page)v8).texture == null) {
                v11 = new Texture(((Page)v8).textureFile, ((Page)v8).format, ((Page)v8).useMipMaps);
                v11.setFilter(((Page)v8).minFilter, ((Page)v8).magFilter);
                v11.setWrap(((Page)v8).uWrap, ((Page)v8).vWrap);
            }
            else {
                v11 = ((Page)v8).texture;
                v11.setFilter(((Page)v8).minFilter, ((Page)v8).magFilter);
                v11.setWrap(((Page)v8).uWrap, ((Page)v8).vWrap);
            }

            this.textures.add(v11);
            v9.put(v8, v11);
        }

        v7 = data.regions.iterator();
        while(v7.hasNext()) {
            Object v10 = v7.next();
            int v12 = ((Region)v10).width;
            int v6 = ((Region)v10).height;
            Object v1 = v9.get(((Region)v10).page);
            int v2 = ((Region)v10).left;
            int v3 = ((Region)v10).top;
            if(((Region)v10).rotate) {
                v4 = v6;
            }
            else {
                v4 = v12;
            }

            if(((Region)v10).rotate) {
                v5 = v12;
            }
            else {
                v5 = v6;
            }

            AtlasRegion v0 = new AtlasRegion(((Texture)v1), v2, v3, v4, v5);
            v0.index = ((Region)v10).index;
            v0.name = ((Region)v10).name;
            v0.offsetX = ((Region)v10).offsetX;
            v0.offsetY = ((Region)v10).offsetY;
            v0.originalHeight = ((Region)v10).originalHeight;
            v0.originalWidth = ((Region)v10).originalWidth;
            v0.rotate = ((Region)v10).rotate;
            v0.splits = ((Region)v10).splits;
            v0.pads = ((Region)v10).pads;
            if(((Region)v10).flip) {
                v0.flip(false, true);
            }

            this.regions.add(v0);
        }
    }

    private Sprite newSprite(AtlasRegion region) {
        Sprite v0;
        AtlasSprite v0_1;
        if(region.packedWidth != region.originalWidth || region.packedHeight != region.originalHeight) {
            v0_1 = new AtlasSprite(region);
        }
        else if(region.rotate) {
            v0 = new Sprite(((TextureRegion)region));
            v0.setBounds(0f, 0f, ((float)region.getRegionHeight()), ((float)region.getRegionWidth()));
            v0.rotate90(true);
        }
        else {
            v0 = new Sprite(((TextureRegion)region));
        }

        return ((Sprite)v0_1);
    }

    static int readTuple(BufferedReader reader) throws IOException {
        int v7 = -1;
        String v4 = reader.readLine();
        int v0 = v4.indexOf(58);
        if(v0 == v7) {
            throw new GdxRuntimeException("Invalid line: " + v4);
        }

        int v3 = v0 + 1;
        int v2 = 0;
        while(v2 < 3) {
            int v1 = v4.indexOf(44, v3);
            if(v1 != v7) {
                TextureAtlas.tuple[v2] = v4.substring(v3, v1).trim();
                v3 = v1 + 1;
                ++v2;
                continue;
            }
            else if(v2 == 0) {
                throw new GdxRuntimeException("Invalid line: " + v4);
            }

            break;
        }

        TextureAtlas.tuple[v2] = v4.substring(v3).trim();
        return v2 + 1;
    }

    static String readValue(BufferedReader reader) throws IOException {
        String v1 = reader.readLine();
        int v0 = v1.indexOf(58);
        if(v0 == -1) {
            throw new GdxRuntimeException("Invalid line: " + v1);
        }

        return v1.substring(v0 + 1).trim();
    }
}

