// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application$ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap$Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.Texture$TextureWrap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FrameBuffer implements Disposable {
    private static final Map buffers;
    protected Texture colorTexture;
    private static int defaultFramebufferHandle;
    private static boolean defaultFramebufferHandleInitialized;
    private int depthbufferHandle;
    protected final Format format;
    private int framebufferHandle;
    protected final boolean hasDepth;
    protected final int height;
    protected final int width;

    static  {
        FrameBuffer.buffers = new HashMap();
        FrameBuffer.defaultFramebufferHandleInitialized = false;
    }

    public FrameBuffer(Format format, int width, int height, boolean hasDepth) {
        super();
        this.width = width;
        this.height = height;
        this.format = format;
        this.hasDepth = hasDepth;
        this.build();
        FrameBuffer.addManagedFrameBuffer(Gdx.app, this);
    }

    private static void addManagedFrameBuffer(Application app, FrameBuffer frameBuffer) {
        Object v0 = FrameBuffer.buffers.get(app);
        if(v0 == null) {
            Array v0_1 = new Array();
        }

        ((Array)v0).add(frameBuffer);
        FrameBuffer.buffers.put(app, v0);
    }

    public void begin() {
        this.bind();
        this.setFrameBufferViewport();
    }

    public void bind() {
        Gdx.graphics.getGL20().glBindFramebuffer(36160, this.framebufferHandle);
    }

    private void build() {
        int v3 = 3553;
        int v11 = 36161;
        int v1 = 36160;
        if(!Gdx.graphics.isGL20Available()) {
            throw new GdxRuntimeException("GL2 is required.");
        }

        GL20 v0 = Gdx.graphics.getGL20();
        if(!FrameBuffer.defaultFramebufferHandleInitialized) {
            FrameBuffer.defaultFramebufferHandleInitialized = true;
            if(Gdx.app.getType() == ApplicationType.iOS) {
                IntBuffer v7 = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
                v0.glGetIntegerv(36006, v7);
                FrameBuffer.defaultFramebufferHandle = v7.get(0);
            }
            else {
                FrameBuffer.defaultFramebufferHandle = 0;
            }
        }

        this.setupTexture();
        IntBuffer v6 = BufferUtils.newIntBuffer(1);
        v0.glGenFramebuffers(1, v6);
        this.framebufferHandle = v6.get(0);
        if(this.hasDepth) {
            v6.clear();
            v0.glGenRenderbuffers(1, v6);
            this.depthbufferHandle = v6.get(0);
        }

        v0.glBindTexture(v3, this.colorTexture.getTextureObjectHandle());
        if(this.hasDepth) {
            v0.glBindRenderbuffer(v11, this.depthbufferHandle);
            v0.glRenderbufferStorage(v11, 33189, this.colorTexture.getWidth(), this.colorTexture.getHeight());
        }

        v0.glBindFramebuffer(v1, this.framebufferHandle);
        v0.glFramebufferTexture2D(v1, 36064, v3, this.colorTexture.getTextureObjectHandle(), 0);
        if(this.hasDepth) {
            v0.glFramebufferRenderbuffer(v1, 36096, v11, this.depthbufferHandle);
        }

        int v8 = v0.glCheckFramebufferStatus(v1);
        v0.glBindRenderbuffer(v11, 0);
        v0.glBindTexture(v3, 0);
        v0.glBindFramebuffer(v1, FrameBuffer.defaultFramebufferHandle);
        if(v8 != 36053) {
            this.colorTexture.dispose();
            if(this.hasDepth) {
                v6.clear();
                v6.put(this.depthbufferHandle);
                v6.flip();
                v0.glDeleteRenderbuffers(1, v6);
            }

            this.colorTexture.dispose();
            v6.clear();
            v6.put(this.framebufferHandle);
            v6.flip();
            v0.glDeleteFramebuffers(1, v6);
            if(v8 == 36054) {
                throw new IllegalStateException("frame buffer couldn\'t be constructed: incomplete attachment");
            }

            if(v8 == 36057) {
                throw new IllegalStateException("frame buffer couldn\'t be constructed: incomplete dimensions");
            }

            if(v8 == 36055) {
                throw new IllegalStateException("frame buffer couldn\'t be constructed: missing attachment");
            }

            if(v8 == 36061) {
                throw new IllegalStateException("frame buffer couldn\'t be constructed: unsupported combination of formats");
            }

            throw new IllegalStateException("frame buffer couldn\'t be constructed: unknown error " + v8);
        }
    }

    public static void clearAllFrameBuffers(Application app) {
        FrameBuffer.buffers.remove(app);
    }

    public void dispose() {
        GL20 v0 = Gdx.graphics.getGL20();
        IntBuffer v1 = BufferUtils.newIntBuffer(1);
        this.colorTexture.dispose();
        if(this.hasDepth) {
            v1.put(this.depthbufferHandle);
            v1.flip();
            v0.glDeleteRenderbuffers(1, v1);
        }

        v1.clear();
        v1.put(this.framebufferHandle);
        v1.flip();
        v0.glDeleteFramebuffers(1, v1);
        if(FrameBuffer.buffers.get(Gdx.app) != null) {
            FrameBuffer.buffers.get(Gdx.app).removeValue(this, true);
        }
    }

    public void end() {
        FrameBuffer.unbind();
        this.setDefaultFrameBufferViewport();
    }

    public void end(int x, int y, int width, int height) {
        FrameBuffer.unbind();
        Gdx.graphics.getGL20().glViewport(x, y, width, height);
    }

    public Texture getColorBufferTexture() {
        return this.colorTexture;
    }

    public int getHeight() {
        return this.colorTexture.getHeight();
    }

    public static String getManagedStatus() {
        return FrameBuffer.getManagedStatus(new StringBuilder()).toString();
    }

    public static StringBuilder getManagedStatus(StringBuilder builder) {
        builder.append("Managed buffers/app: { ");
        Iterator v1 = FrameBuffer.buffers.keySet().iterator();
        while(v1.hasNext()) {
            builder.append(FrameBuffer.buffers.get(v1.next()).size);
            builder.append(" ");
        }

        builder.append("}");
        return builder;
    }

    public int getWidth() {
        return this.colorTexture.getWidth();
    }

    public static void invalidateAllFrameBuffers(Application app) {
        if(Gdx.graphics.getGL20() != null) {
            Object v0 = FrameBuffer.buffers.get(app);
            if(v0 != null) {
                int v1;
                for(v1 = 0; v1 < ((Array)v0).size; ++v1) {
                    ((Array)v0).get(v1).build();
                }
            }
        }
    }

    protected void setDefaultFrameBufferViewport() {
        Gdx.graphics.getGL20().glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    protected void setFrameBufferViewport() {
        Gdx.graphics.getGL20().glViewport(0, 0, this.colorTexture.getWidth(), this.colorTexture.getHeight());
    }

    protected void setupTexture() {
        this.colorTexture = new Texture(this.width, this.height, this.format);
        this.colorTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        this.colorTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
    }

    public static void unbind() {
        Gdx.graphics.getGL20().glBindFramebuffer(36160, FrameBuffer.defaultFramebufferHandle);
    }
}

