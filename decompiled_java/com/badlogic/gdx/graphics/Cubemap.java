// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Cubemap extends GLTexture {
    public enum CubemapSide {
        static  {
            CubemapSide.PositiveX = new CubemapSide("PositiveX", 0, 0, 34069);
            CubemapSide.NegativeX = new CubemapSide("NegativeX", 1, 1, 34070);
            CubemapSide.PositiveY = new CubemapSide("PositiveY", 2, 2, 34071);
            CubemapSide.NegativeY = new CubemapSide("NegativeY", 3, 3, 34072);
            CubemapSide.PositiveZ = new CubemapSide("PositiveZ", 4, 4, 34073);
            CubemapSide.NegativeZ = new CubemapSide("NegativeZ", 5, 5, 34074);
            CubemapSide[] v0 = new CubemapSide[6];
            v0[0] = CubemapSide.PositiveX;
            v0[1] = CubemapSide.NegativeX;
            v0[2] = CubemapSide.PositiveY;
            v0[3] = CubemapSide.NegativeY;
            v0[4] = CubemapSide.PositiveZ;
            v0[5] = CubemapSide.NegativeZ;
            CubemapSide.$VALUES = v0;
        }

        private CubemapSide(String arg1, int arg2, int index, int glEnum) {
            super(arg1, arg2);
            this.index = index;
            this.glEnum = glEnum;
        }

        public int getGLEnum() {
            return this.glEnum;
        }

        public static CubemapSide valueOf(String name) {
            return Enum.valueOf(CubemapSide.class, name);
        }

        public static CubemapSide[] values() {
            return CubemapSide.$VALUES.clone();
        }
    }

    protected final TextureData[] data;

    public Cubemap() {
        this(null, null, null, null, null, null);
    }

    public Cubemap(TextureData positiveX, TextureData negativeX, TextureData positiveY, TextureData negativeY, TextureData positiveZ, TextureData negativeZ) {
        super(34067);
        this.data = new TextureData[6];
        this.minFilter = TextureFilter.Nearest;
        this.magFilter = TextureFilter.Nearest;
        this.uWrap = TextureWrap.ClampToEdge;
        this.vWrap = TextureWrap.ClampToEdge;
        this.load(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ);
    }

    public Cubemap(int width, int height, int depth, Format format) {
        this(new PixmapTextureData(new Pixmap(depth, height, format), null, false, true), new PixmapTextureData(new Pixmap(depth, height, format), null, false, true), new PixmapTextureData(new Pixmap(width, depth, format), null, false, true), new PixmapTextureData(new Pixmap(width, depth, format), null, false, true), new PixmapTextureData(new Pixmap(width, height, format), null, false, true), new PixmapTextureData(new Pixmap(width, height, format), null, false, true));
    }

    public Cubemap(FileHandle positiveX, FileHandle negativeX, FileHandle positiveY, FileHandle negativeY, FileHandle positiveZ, FileHandle negativeZ) {
        this(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ, false);
    }

    public Cubemap(FileHandle positiveX, FileHandle negativeX, FileHandle positiveY, FileHandle negativeY, FileHandle positiveZ, FileHandle negativeZ, boolean useMipMaps) {
        this(Cubemap.createTextureData(positiveX, useMipMaps), Cubemap.createTextureData(negativeX, useMipMaps), Cubemap.createTextureData(positiveY, useMipMaps), Cubemap.createTextureData(negativeY, useMipMaps), Cubemap.createTextureData(positiveZ, useMipMaps), Cubemap.createTextureData(negativeZ, useMipMaps));
    }

    public Cubemap(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ) {
        this(positiveX, negativeX, positiveY, negativeY, positiveZ, negativeZ, false);
    }

    public Cubemap(Pixmap positiveX, Pixmap negativeX, Pixmap positiveY, Pixmap negativeY, Pixmap positiveZ, Pixmap negativeZ, boolean useMipMaps) {
        PixmapTextureData v5_1;
        PixmapTextureData v4_1;
        TextureData v3;
        PixmapTextureData v2_1;
        PixmapTextureData v1_1;
        TextureData v6 = null;
        if(positiveX == null) {
            TextureData v1 = v6;
        }
        else {
            v1_1 = new PixmapTextureData(positiveX, ((Format)v6), useMipMaps, false);
        }

        if(negativeX == null) {
            TextureData v2 = v6;
        }
        else {
            v2_1 = new PixmapTextureData(negativeX, ((Format)v6), useMipMaps, false);
        }

        if(positiveY == null) {
            v3 = v6;
        }
        else {
            PixmapTextureData v3_1 = new PixmapTextureData(positiveY, ((Format)v6), useMipMaps, false);
        }

        if(negativeY == null) {
            TextureData v4 = v6;
        }
        else {
            v4_1 = new PixmapTextureData(negativeY, ((Format)v6), useMipMaps, false);
        }

        if(positiveZ == null) {
            TextureData v5 = v6;
        }
        else {
            v5_1 = new PixmapTextureData(positiveZ, ((Format)v6), useMipMaps, false);
        }

        if(negativeZ != null) {
            PixmapTextureData v6_1 = new PixmapTextureData(negativeZ, ((Format)v6), useMipMaps, false);
        }

        this(((TextureData)v1_1), ((TextureData)v2_1), v3, ((TextureData)v4_1), ((TextureData)v5_1), v6);
    }

    public int getDepth() {
        int v1;
        int v0 = 0;
        if(this.data[CubemapSide.PositiveX.index] != null) {
            v1 = this.data[CubemapSide.PositiveX.index].getWidth();
            if(v1 > 0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.NegativeX.index] != null) {
            v1 = this.data[CubemapSide.NegativeX.index].getWidth();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.PositiveY.index] != null) {
            v1 = this.data[CubemapSide.PositiveY.index].getHeight();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.NegativeY.index] != null) {
            v1 = this.data[CubemapSide.NegativeY.index].getHeight();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        return v0;
    }

    public int getHeight() {
        int v1;
        int v0 = 0;
        if(this.data[CubemapSide.PositiveZ.index] != null) {
            v1 = this.data[CubemapSide.PositiveZ.index].getHeight();
            if(v1 > 0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.NegativeZ.index] != null) {
            v1 = this.data[CubemapSide.NegativeZ.index].getHeight();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.PositiveX.index] != null) {
            v1 = this.data[CubemapSide.PositiveX.index].getHeight();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        if(this.data[CubemapSide.NegativeX.index] != null) {
            v1 = this.data[CubemapSide.NegativeX.index].getHeight();
            if(v1 > v0) {
                v0 = v1;
            }
        }

        return v0;
    }

    public TextureData getTextureData(CubemapSide side) {
        return this.data[side.index];
    }

    public int getWidth() {
        int v0;
        int v1 = 0;
        if(this.data[CubemapSide.PositiveZ.index] != null) {
            v0 = this.data[CubemapSide.PositiveZ.index].getWidth();
            if(v0 > 0) {
                v1 = v0;
            }
        }

        if(this.data[CubemapSide.NegativeZ.index] != null) {
            v0 = this.data[CubemapSide.NegativeZ.index].getWidth();
            if(v0 > v1) {
                v1 = v0;
            }
        }

        if(this.data[CubemapSide.PositiveY.index] != null) {
            v0 = this.data[CubemapSide.PositiveY.index].getWidth();
            if(v0 > v1) {
                v1 = v0;
            }
        }

        if(this.data[CubemapSide.NegativeY.index] != null) {
            v0 = this.data[CubemapSide.NegativeY.index].getWidth();
            if(v0 > v1) {
                v1 = v0;
            }
        }

        return v1;
    }

    public boolean isComplete() {
        boolean v1;
        int v0 = 0;
        while(true) {
            if(v0 >= this.data.length) {
                break;
            }
            else if(this.data[v0] == null) {
                v1 = false;
            }
            else {
                ++v0;
                continue;
            }

            goto label_8;
        }

        v1 = true;
    label_8:
        return v1;
    }

    public boolean isManaged() {
        boolean v4;
        TextureData[] v0 = this.data;
        int v3 = v0.length;
        int v2 = 0;
        while(true) {
            if(v2 >= v3) {
                break;
            }
            else if(!v0[v2].isManaged()) {
                v4 = false;
            }
            else {
                ++v2;
                continue;
            }

            goto label_8;
        }

        v4 = true;
    label_8:
        return v4;
    }

    public void load(TextureData positiveX, TextureData negativeX, TextureData positiveY, TextureData negativeY, TextureData positiveZ, TextureData negativeZ) {
        this.bind();
        this.unsafeSetFilter(this.minFilter, this.magFilter, true);
        this.unsafeSetWrap(this.uWrap, this.vWrap, true);
        this.unsafeLoad(CubemapSide.PositiveX, positiveX);
        this.unsafeLoad(CubemapSide.NegativeX, negativeX);
        this.unsafeLoad(CubemapSide.PositiveY, positiveY);
        this.unsafeLoad(CubemapSide.NegativeY, negativeY);
        this.unsafeLoad(CubemapSide.PositiveZ, positiveZ);
        this.unsafeLoad(CubemapSide.NegativeZ, negativeZ);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    public void load(CubemapSide side, FileHandle file) {
        this.load(side, file, false);
    }

    public void load(CubemapSide side, FileHandle file, boolean useMipMaps) {
        this.load(side, Cubemap.createTextureData(file, useMipMaps));
    }

    public void load(CubemapSide side, TextureData data) {
        this.bind();
        this.unsafeLoad(side, data);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    public void load(CubemapSide side, Pixmap pixmap) {
        TextureData v0 = null;
        if(pixmap != null) {
            PixmapTextureData v0_1 = new PixmapTextureData(pixmap, ((Format)v0), false, false);
        }

        this.load(side, v0);
    }

    public void load(CubemapSide side, Pixmap pixmap, boolean useMipMaps) {
        PixmapTextureData v0_1;
        TextureData v0 = null;
        if(pixmap != null) {
            v0_1 = new PixmapTextureData(pixmap, ((Format)v0), useMipMaps, false);
        }

        this.load(side, ((TextureData)v0_1));
    }

    protected void reload() {
        if(!this.isManaged()) {
            throw new GdxRuntimeException("Tried to reload an unmanaged Cubemap");
        }

        this.glHandle = Cubemap.createGLHandle();
        this.load(this.data[CubemapSide.PositiveX.index], this.data[CubemapSide.NegativeX.index], this.data[CubemapSide.PositiveY.index], this.data[CubemapSide.NegativeY.index], this.data[CubemapSide.PositiveZ.index], this.data[CubemapSide.NegativeZ.index]);
    }

    protected void unsafeLoad(CubemapSide side, TextureData data) {
        int v0 = side.index;
        if(this.data[v0] != null && data != null && data.isManaged() != this.data[v0].isManaged()) {
            throw new GdxRuntimeException("New data must have the same managed status as the old data");
        }

        Cubemap.uploadImageData(side.glEnum, data);
        this.data[v0] = data;
    }
}

