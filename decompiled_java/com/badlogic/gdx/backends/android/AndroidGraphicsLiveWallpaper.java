// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView$EGLConfigChooser;
import android.opengl.GLSurfaceView$Renderer;
import android.opengl.GLU;
import android.os.Build;
import android.os.Build$VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Graphics$BufferFormat;
import com.badlogic.gdx.Graphics$DisplayMode;
import com.badlogic.gdx.Graphics$GraphicsType;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceViewAPI18;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceViewCupcake;
import com.badlogic.gdx.backends.android.surfaceview.GdxEglConfigChooser;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy$MeasuredDimension;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.WindowedMean;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public final class AndroidGraphicsLiveWallpaper implements GLSurfaceView$Renderer, Graphics {
    class AndroidDisplayMode extends DisplayMode {
        protected AndroidDisplayMode(AndroidGraphicsLiveWallpaper arg1, int width, int height, int refreshRate, int bitsPerPixel) {
            AndroidGraphicsLiveWallpaper.this = arg1;
            super(width, height, refreshRate, bitsPerPixel);
        }
    }

    AndroidLiveWallpaper app;
    private BufferFormat bufferFormat;
    private final AndroidApplicationConfiguration config;
    boolean configLogged;
    volatile boolean created;
    protected float deltaTime;
    protected float density;
    volatile boolean destroy;
    protected EGLContext eglContext;
    protected String extensions;
    protected int fps;
    protected long frameStart;
    protected int frames;
    protected GLCommon gl;
    protected GL10 gl10;
    protected GL11 gl11;
    protected GL20 gl20;
    protected GLU glu;
    int height;
    protected boolean isContinuous;
    protected long lastFrameTime;
    protected WindowedMean mean;
    volatile boolean pause;
    protected float ppcX;
    protected float ppcY;
    protected float ppiX;
    protected float ppiY;
    volatile boolean resume;
    volatile boolean running;
    Object synch;
    int[] value;
    final View view;
    int width;

    public AndroidGraphicsLiveWallpaper(AndroidLiveWallpaper app, AndroidApplicationConfiguration config, ResolutionStrategy resolutionStrategy) {
        super();
        this.lastFrameTime = System.nanoTime();
        this.deltaTime = 0f;
        this.frameStart = System.nanoTime();
        this.frames = 0;
        this.mean = new WindowedMean(5);
        this.created = false;
        this.running = false;
        this.pause = false;
        this.resume = false;
        this.destroy = false;
        this.ppiX = 0f;
        this.ppiY = 0f;
        this.ppcX = 0f;
        this.ppcY = 0f;
        this.density = 1f;
        this.bufferFormat = new BufferFormat(5, 6, 5, 0, 16, 0, 0, false);
        this.isContinuous = true;
        this.configLogged = false;
        this.value = new int[1];
        this.synch = new Object();
        this.config = config;
        this.app = app;
        this.view = this.createGLSurfaceView(app.service, config.useGL20, resolutionStrategy);
        this.setPreserveContext(this.view);
    }

    protected boolean checkGL20() {
        boolean v4;
        EGL v0 = EGLContext.getEGL();
        EGLDisplay v1 = ((EGL10)v0).eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        ((EGL10)v0).eglInitialize(v1, new int[2]);
        int[] v2 = new int[9];
        v2[0] = 12324;
        v2[1] = 4;
        v2[2] = 12323;
        v2[3] = 4;
        v2[4] = 12322;
        v2[5] = 4;
        v2[6] = 12352;
        v2[7] = 4;
        v2[8] = 12344;
        EGLConfig[] v3 = new EGLConfig[10];
        int[] v5 = new int[1];
        ((EGL10)v0).eglChooseConfig(v1, v2, v3, 10, v5);
        ((EGL10)v0).eglTerminate(v1);
        if(v5[0] > 0) {
            v4 = true;
        }
        else {
            v4 = false;
        }

        return v4;
    }

    public void clearManagedCaches() {
        Mesh.clearAllMeshes(this.app);
        Texture.clearAllTextures(this.app);
        ShaderProgram.clearAllShaderPrograms(this.app);
        FrameBuffer.clearAllFrameBuffers(this.app);
        if(AndroidLiveWallpaperService.DEBUG) {
            Gdx.app.debug("AndroidGraphics", Mesh.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", Texture.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", ShaderProgram.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", FrameBuffer.getManagedStatus());
        }
    }

    private View createGLSurfaceView(Context context, boolean useGL2, ResolutionStrategy resolutionStrategy) {
        com.badlogic.gdx.backends.android.AndroidGraphicsLiveWallpaper$4 v0_3;
        GLSurfaceView$EGLConfigChooser v7 = this.getEglConfigChooser();
        if(!useGL2 || !this.checkGL20()) {
            this.config.useGL20 = false;
            v7 = this.getEglConfigChooser();
            if(Integer.parseInt(Build$VERSION.SDK) < 11) {
                goto label_50;
            }

            com.badlogic.gdx.backends.android.AndroidGraphicsLiveWallpaper$2 v0_1 = new GLSurfaceView() {
                public SurfaceHolder getHolder() {
                    return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
                }

                public void onDestroy() {
                    this.onDetachedFromWindow();
                }

                protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                    MeasuredDimension v0 = this.val$resolutionStrategy.calcMeasures(widthMeasureSpec, heightMeasureSpec);
                    this.setMeasuredDimension(v0.width, v0.height);
                }
            };
            if(v7 != null) {
                ((GLSurfaceView)v0_1).setEGLConfigChooser(v7);
            }
            else {
                ((GLSurfaceView)v0_1).setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
            }

            ((GLSurfaceView)v0_1).setRenderer(((GLSurfaceView$Renderer)this));
            goto label_8;
        label_50:
            if(!this.config.useGLSurfaceViewAPI18) {
                goto label_72;
            }

            com.badlogic.gdx.backends.android.AndroidGraphicsLiveWallpaper$3 v0_2 = new GLSurfaceViewAPI18() {
                public SurfaceHolder getHolder() {
                    return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
                }
            };
            if(v7 != null) {
                ((GLSurfaceViewAPI18)v0_2).setEGLConfigChooser(v7);
            }
            else {
                ((GLSurfaceViewAPI18)v0_2).setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
            }

            ((GLSurfaceViewAPI18)v0_2).setRenderer(((GLSurfaceView$Renderer)this));
            goto label_8;
        label_72:
            v0_3 = new GLSurfaceViewCupcake() {
                public SurfaceHolder getHolder() {
                    return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
                }
            };
            if(v7 != null) {
                ((GLSurfaceViewCupcake)v0_3).setEGLConfigChooser(v7);
            }
            else {
                ((GLSurfaceViewCupcake)v0_3).setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
            }

            ((GLSurfaceViewCupcake)v0_3).setRenderer(((GLSurfaceView$Renderer)this));
        }
        else {
            com.badlogic.gdx.backends.android.AndroidGraphicsLiveWallpaper$1 v0 = new GLSurfaceView20() {
                public SurfaceHolder getHolder() {
                    return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
                }

                public void onDestroy() {
                    this.onDetachedFromWindow();
                }
            };
            if(v7 != null) {
                ((GLSurfaceView20)v0).setEGLConfigChooser(v7);
            }
            else {
                ((GLSurfaceView20)v0).setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
            }

            ((GLSurfaceView20)v0).setRenderer(((GLSurfaceView$Renderer)this));
        }

    label_8:
        return ((View)v0_3);
    }

    void destroy() {  // has try-catch handlers
        try {
            this.running = false;
            this.destroy = true;
            while(true) {
                if(!this.destroy) {
                    return;
                }

                try {
                    this.synch.wait();
                    continue;
                }
                catch(InterruptedException v0) {
                    try {
                        Gdx.app.log("AndroidGraphics", "waiting for destroy synchronization failed!");
                        continue;
                        return;
                    label_17:
                        throw v1;
                    }
                    catch(Throwable v1) {
                        goto label_17;
                    }
                }
            }
        }
        catch(Throwable v1) {
            goto label_17;
        }
    }

    private int getAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int attrib, int defValue) {
        if(egl.eglGetConfigAttrib(display, config, attrib, this.value)) {
            defValue = this.value[0];
        }

        return defValue;
    }

    public BufferFormat getBufferFormat() {
        return this.bufferFormat;
    }

    public float getDeltaTime() {
        float v0;
        if(this.mean.getMean() == 0f) {
            v0 = this.deltaTime;
        }
        else {
            v0 = this.mean.getMean();
        }

        return v0;
    }

    public float getDensity() {
        return this.density;
    }

    public DisplayMode getDesktopDisplayMode() {
        DisplayMetrics v6 = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(v6);
        return new AndroidDisplayMode(this, v6.widthPixels, v6.heightPixels, 0, 0);
    }

    public DisplayMode[] getDisplayModes() {
        DisplayMode[] v0 = new DisplayMode[1];
        v0[0] = this.getDesktopDisplayMode();
        return v0;
    }

    private GLSurfaceView$EGLConfigChooser getEglConfigChooser() {
        return new GdxEglConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil, this.config.numSamples, this.config.useGL20);
    }

    public int getFramesPerSecond() {
        return this.fps;
    }

    public GL10 getGL10() {
        return this.gl10;
    }

    public GL11 getGL11() {
        return this.gl11;
    }

    public GL20 getGL20() {
        return this.gl20;
    }

    public GLCommon getGLCommon() {
        return this.gl;
    }

    public int getHeight() {
        return this.height;
    }

    public float getPpcX() {
        return this.ppcX;
    }

    public float getPpcY() {
        return this.ppcY;
    }

    public float getPpiX() {
        return this.ppiX;
    }

    public float getPpiY() {
        return this.ppiY;
    }

    public float getRawDeltaTime() {
        return this.deltaTime;
    }

    SurfaceHolder getSurfaceHolder() {  // has try-catch handlers
        try {
            return this.app.service.getSurfaceHolder();
        label_8:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_8;
        }
    }

    public GraphicsType getType() {
        return GraphicsType.AndroidGL;
    }

    public View getView() {
        return this.view;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isContinuousRendering() {
        return this.isContinuous;
    }

    public boolean isFullscreen() {
        return 1;
    }

    public boolean isGL11Available() {
        boolean v0;
        if(this.gl11 != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean isGL20Available() {
        boolean v0;
        if(this.gl20 != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    private static boolean isPowerOfTwo(int value) {
        boolean v0;
        if(value == 0 || (value - 1 & value) != 0) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    private void logConfig(EGLConfig config) {
        boolean v11;
        EGL v1 = EGLContext.getEGL();
        EGLDisplay v2 = ((EGL10)v1).eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int v13 = this.getAttrib(((EGL10)v1), v2, config, 12324, 0);
        int v12 = this.getAttrib(((EGL10)v1), v2, config, 12323, 0);
        int v6 = this.getAttrib(((EGL10)v1), v2, config, 12322, 0);
        int v7 = this.getAttrib(((EGL10)v1), v2, config, 12321, 0);
        int v8 = this.getAttrib(((EGL10)v1), v2, config, 12325, 0);
        int v9 = this.getAttrib(((EGL10)v1), v2, config, 12326, 0);
        int v10 = Math.max(this.getAttrib(((EGL10)v1), v2, config, 12337, 0), this.getAttrib(((EGL10)v1), v2, config, 12513, 0));
        if(this.getAttrib(((EGL10)v1), v2, config, 12513, 0) != 0) {
            v11 = true;
        }
        else {
            v11 = false;
        }

        if(!this.configLogged) {
            if(this.gl != null) {
                Gdx.app.log("AndroidGraphics", "OGL renderer: " + this.gl.glGetString(7937));
                Gdx.app.log("AndroidGraphics", "OGL vendor: " + this.gl.glGetString(7936));
                Gdx.app.log("AndroidGraphics", "OGL version: " + this.gl.glGetString(7938));
                Gdx.app.log("AndroidGraphics", "OGL extensions: " + this.gl.glGetString(7939));
                this.configLogged = true;
            }

            Gdx.app.log("AndroidGraphics", "framebuffer: (" + v13 + ", " + v12 + ", " + v6 + ", " + v7 + ")");
            Gdx.app.log("AndroidGraphics", "depthbuffer: (" + v8 + ")");
            Gdx.app.log("AndroidGraphics", "stencilbuffer: (" + v9 + ")");
            Gdx.app.log("AndroidGraphics", "samples: (" + v10 + ")");
            Gdx.app.log("AndroidGraphics", "coverage sampling: (" + v11 + ")");
        }

        this.bufferFormat = new BufferFormat(v13, v12, v6, v7, v8, v9, v10, v11);
    }

    public void onDrawFrame(javax.microedition.khronos.opengles.GL10 gl) {  // has try-catch handlers
        AndroidLiveWallpaper v8_2;
        int v0;
        float v8;
        long v6 = System.nanoTime();
        this.deltaTime = (((float)(v6 - this.lastFrameTime))) / 1000000000f;
        this.lastFrameTime = v6;
        WindowedMean v9 = this.mean;
        if(this.resume) {
            v8 = 0f;
        }
        else {
            v8 = this.deltaTime;
        }

        v9.addValue(v8);
        try {
            boolean v4 = this.running;
            boolean v2 = this.pause;
            boolean v1 = this.destroy;
            boolean v3 = this.resume;
            if(this.resume) {
                this.resume = false;
                this.synch.notifyAll();
            }

            if(this.pause) {
                this.pause = false;
                this.synch.notifyAll();
            }

            if(this.destroy) {
                this.destroy = false;
                this.synch.notifyAll();
            }

            if(!v3) {
                goto label_49;
            }
        }
        catch(Throwable v8_1) {
            try {
            label_86:
                throw v8_1;
            }
            catch(Throwable v8_1) {
                goto label_86;
            }
        }

        this.app.listener.resume();
        Gdx.app.log("AndroidGraphics", "resumed");
    label_49:
        if(!v4) {
            goto label_98;
        }

        if(Gdx.graphics.getGL10() == null && Gdx.graphics.getGL11() == null) {
            if(Gdx.graphics.getGL20() != null) {
            }
            else {
                goto label_98;
            }
        }

        try {
            this.app.executedRunnables.clear();
            this.app.executedRunnables.addAll(this.app.runnables);
            this.app.runnables.clear();
            v0 = 0;
            while(true) {
            label_73:
                if(v0 >= this.app.executedRunnables.size) {
                    goto label_92;
                }

                try {
                    this.app.executedRunnables.get(v0).run();
                    break;
                }
                catch(Throwable v5) {
                    try {
                        v5.printStackTrace();
                        break;
                    }
                    catch(Throwable v8_1) {
                        goto label_91;
                    }
                }
            }
        }
        catch(Throwable v8_1) {
            goto label_91;
        }

        ++v0;
        goto label_73;
        try {
        label_92:
            v8_2 = this.app;
        }
        catch(Throwable v8_1) {
            goto label_91;
        }

        v8_2.input.processEvents();
        this.app.listener.render();
        goto label_98;
        try {
        label_91:
            throw v8_1;
        }
        catch(Throwable v8_1) {
            goto label_91;
        }

    label_98:
        if(v2) {
            this.app.listener.pause();
            Gdx.app.log("AndroidGraphics", "paused");
        }

        if(v1) {
            this.app.listener.dispose();
            Gdx.app.log("AndroidGraphics", "destroyed");
        }

        if(v6 - this.frameStart > 1000000000) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameStart = v6;
        }

        ++this.frames;
    }

    public void onSurfaceChanged(javax.microedition.khronos.opengles.GL10 gl, int width, int height) {  // has try-catch handlers
        AndroidLiveWallpaper v0_1;
        this.width = width;
        this.height = height;
        this.updatePpi();
        gl.glViewport(0, 0, this.width, this.height);
        if(!this.created) {
            this.app.listener.create();
            this.created = true;
            try {
                this.running = true;
            label_16:
                v0_1 = this.app;
                goto label_17;
            }
            catch(Throwable v0) {
                try {
                label_21:
                    throw v0;
                }
                catch(Throwable v0) {
                    goto label_21;
                }
            }
        }

        goto label_16;
    label_17:
        v0_1.listener.resize(width, height);
    }

    public void onSurfaceCreated(javax.microedition.khronos.opengles.GL10 gl, EGLConfig config) {
        this.eglContext = EGLContext.getEGL().eglGetCurrentContext();
        this.setupGL(gl);
        this.logConfig(config);
        this.updatePpi();
        Mesh.invalidateAllMeshes(this.app);
        Texture.invalidateAllTextures(this.app);
        ShaderProgram.invalidateAllShaderPrograms(this.app);
        FrameBuffer.invalidateAllFrameBuffers(this.app);
        if(AndroidLiveWallpaperService.DEBUG) {
            Gdx.app.debug("AndroidGraphics", Mesh.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", Texture.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", ShaderProgram.getManagedStatus());
            Gdx.app.debug("AndroidGraphics", FrameBuffer.getManagedStatus());
        }

        Display v0 = this.app.getWindowManager().getDefaultDisplay();
        this.width = v0.getWidth();
        this.height = v0.getHeight();
        this.mean = new WindowedMean(5);
        this.lastFrameTime = System.nanoTime();
        gl.glViewport(0, 0, this.width, this.height);
    }

    void pause() {  // has try-catch handlers
        try {
            if(this.running) {
                goto label_4;
            }

            return;
        label_4:
            this.running = false;
            this.pause = true;
            while(true) {
                if(!this.pause) {
                    return;
                }

                try {
                    this.synch.wait();
                    continue;
                }
                catch(InterruptedException v0) {
                    try {
                        Gdx.app.log("AndroidGraphics", "waiting for pause synchronization failed!");
                        continue;
                        return;
                    label_20:
                        throw v1;
                    }
                    catch(Throwable v1) {
                        goto label_20;
                    }
                }
            }
        }
        catch(Throwable v1) {
            goto label_20;
        }
    }

    public void requestRendering() {
        if(this.view != null) {
            if((this.view instanceof GLSurfaceViewCupcake)) {
                this.view.requestRender();
            }
            else if((this.view instanceof GLSurfaceViewAPI18)) {
                this.view.requestRender();
            }
            else if((this.view instanceof GLSurfaceView)) {
                this.view.requestRender();
            }
            else {
                throw new RuntimeException("unimplemented");
            }
        }
    }

    void resume() {  // has try-catch handlers
        try {
            this.running = true;
            this.resume = true;
            while(true) {
                if(!this.resume) {
                    return;
                }

                try {
                    this.synch.wait();
                    continue;
                }
                catch(InterruptedException v0) {
                    try {
                        Gdx.app.log("AndroidGraphics", "waiting for resume synchronization failed!");
                        continue;
                        return;
                    label_17:
                        throw v1;
                    }
                    catch(Throwable v1) {
                        goto label_17;
                    }
                }
            }
        }
        catch(Throwable v1) {
            goto label_17;
        }
    }

    public void setContinuousRendering(boolean isContinuous) {
        int v0;
        if(this.view != null) {
            this.isContinuous = isContinuous;
            if(isContinuous) {
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            if((this.view instanceof GLSurfaceViewCupcake)) {
                this.view.setRenderMode(v0);
            }
            else if((this.view instanceof GLSurfaceViewAPI18)) {
                this.view.setRenderMode(v0);
            }
            else if((this.view instanceof GLSurfaceView)) {
                this.view.setRenderMode(v0);
            }
            else {
                goto label_27;
            }

            this.mean.clear();
            return;
        label_27:
            throw new RuntimeException("unimplemented");
        }
    }

    public boolean setDisplayMode(int width, int height, boolean fullscreen) {
        return 0;
    }

    public boolean setDisplayMode(DisplayMode displayMode) {
        return 0;
    }

    private void setPreserveContext(Object view) {  // has try-catch handlers
        int v1;
        if(Integer.parseInt(Build$VERSION.SDK) < 11) {
            return;
        }

        if(!(view instanceof GLSurfaceView)) {
            return;
        }

        Method v4 = null;
        try {
            Method[] v0 = view.getClass().getMethods();
            int v2 = v0.length;
            v1 = 0;
            while(true) {
            label_11:
                if(v1 < v2) {
                    Method v3 = v0[v1];
                    if(v3.getName().equals("setPreserveEGLContextOnPause")) {
                        v4 = v3;
                    }
                    else {
                        break;
                    }
                }

                goto label_18;
            }
        }
        catch(Exception v6) {
            goto label_30;
        }

        ++v1;
        goto label_11;
        try {
        label_18:
            if(v4 == null) {
                return;
            }

            v4.invoke(view, Boolean.valueOf(true));
        }
        catch(Exception v6) {
        label_30:
        }
    }

    public void setTitle(String title) {
    }

    public void setVSync(boolean vsync) {
    }

    private void setupGL(javax.microedition.khronos.opengles.GL10 gl) {
        if(this.gl10 == null && this.gl20 == null) {
            if((this.view instanceof GLSurfaceView20)) {
                this.gl20 = new AndroidGL20();
                this.gl = this.gl20;
            }
            else {
                this.gl10 = new AndroidGL10(gl);
                this.gl = this.gl10;
                if((gl instanceof javax.microedition.khronos.opengles.GL11)) {
                    String v0 = gl.glGetString(7937);
                    if(v0 != null && !v0.toLowerCase().contains("pixelflinger") && !Build.MODEL.equals("MB200") && !Build.MODEL.equals("MB220") && !Build.MODEL.contains("Behold")) {
                        this.gl11 = new AndroidGL11(gl);
                        this.gl10 = this.gl11;
                    }
                }
            }

            Gdx.gl = this.gl;
            Gdx.gl10 = this.gl10;
            Gdx.gl11 = this.gl11;
            Gdx.gl20 = this.gl20;
        }
    }

    public boolean supportsDisplayModeChange() {
        return 0;
    }

    public boolean supportsExtension(String extension) {
        if(this.extensions == null) {
            this.extensions = Gdx.gl.glGetString(7939);
        }

        return this.extensions.contains(((CharSequence)extension));
    }

    private void updatePpi() {
        DisplayMetrics v0 = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(v0);
        this.ppiX = v0.xdpi;
        this.ppiY = v0.ydpi;
        this.ppcX = v0.xdpi / 2.54f;
        this.ppcY = v0.ydpi / 2.54f;
        this.density = v0.density;
    }
}

