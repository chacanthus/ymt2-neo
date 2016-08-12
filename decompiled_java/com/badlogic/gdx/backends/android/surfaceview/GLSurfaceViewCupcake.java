// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.backends.android.surfaceview;

import android.content.Context;
import android.opengl.GLSurfaceView$EGLConfigChooser;
import android.opengl.GLSurfaceView$Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder$Callback;
import android.view.SurfaceView;
import java.io.Writer;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class GLSurfaceViewCupcake extends SurfaceView implements SurfaceHolder$Callback {
    abstract class BaseConfigChooser implements GLSurfaceView$EGLConfigChooser {
        protected int[] mConfigSpec;

        public BaseConfigChooser(int[] configSpec) {
            super();
            this.mConfigSpec = configSpec;
        }

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
            int[] v5 = new int[1];
            egl.eglChooseConfig(display, this.mConfigSpec, null, 0, v5);
            int v4 = v5[0];
            if(v4 <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }

            EGLConfig[] v3 = new EGLConfig[v4];
            egl.eglChooseConfig(display, this.mConfigSpec, v3, v4, v5);
            EGLConfig v6 = this.chooseConfig(egl, display, v3);
            if(v6 == null) {
                throw new IllegalArgumentException("No config chosen");
            }

            return v6;
        }

        abstract EGLConfig chooseConfig(EGL10 arg0, EGLDisplay arg1, EGLConfig[] arg2);
    }

    class ComponentSizeChooser extends BaseConfigChooser {
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue;

        public ComponentSizeChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {
            int[] v0 = new int[13];
            v0[0] = 12324;
            v0[1] = redSize;
            v0[2] = 12323;
            v0[3] = greenSize;
            v0[4] = 12322;
            v0[5] = blueSize;
            v0[6] = 12321;
            v0[7] = alphaSize;
            v0[8] = 12325;
            v0[9] = depthSize;
            v0[10] = 12326;
            v0[11] = stencilSize;
            v0[12] = 12344;
            super(v0);
            this.mValue = new int[1];
            this.mRedSize = redSize;
            this.mGreenSize = greenSize;
            this.mBlueSize = blueSize;
            this.mAlphaSize = alphaSize;
            this.mDepthSize = depthSize;
            this.mStencilSize = stencilSize;
        }

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display, EGLConfig[] configs) {
            EGLConfig v10 = null;
            int v11 = 1000;
            EGLConfig[] v8 = configs;
            int v16 = v8.length;
            int v15;
            for(v15 = 0; v15 < v16; ++v15) {
                EGLConfig v4 = v8[v15];
                int v13 = Math.abs(this.findConfigAttrib(egl, display, v4, 12324, 0) - this.mRedSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12323, 0) - this.mGreenSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12322, 0) - this.mBlueSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12321, 0) - this.mAlphaSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12325, 0) - this.mDepthSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12326, 0) - this.mStencilSize);
                if(v13 < v11) {
                    v11 = v13;
                    v10 = v4;
                }
            }

            return v10;
        }

        private int findConfigAttrib(EGL10 egl, EGLDisplay display, EGLConfig config, int attribute, int defaultValue) {
            if(egl.eglGetConfigAttrib(display, config, attribute, this.mValue)) {
                defaultValue = this.mValue[0];
            }

            return defaultValue;
        }
    }

    class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;

        public EglHelper(GLSurfaceViewCupcake arg1) {
            GLSurfaceViewCupcake.this = arg1;
            super();
        }

        public GL createSurface(SurfaceHolder holder) {
            if(this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
            }

            this.mEglSurface = this.mEgl.eglCreateWindowSurface(this.mEglDisplay, this.mEglConfig, holder, null);
            this.mEgl.eglMakeCurrent(this.mEglDisplay, this.mEglSurface, this.mEglSurface, this.mEglContext);
            GL v0 = this.mEglContext.getGL();
            if(GLSurfaceViewCupcake.this.mGLWrapper != null) {
                v0 = GLSurfaceViewCupcake.this.mGLWrapper.wrap(v0);
            }

            return v0;
        }

        public void finish() {
            EGLSurface v5 = null;
            if(this.mEglSurface != null) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                this.mEgl.eglDestroySurface(this.mEglDisplay, this.mEglSurface);
                this.mEglSurface = v5;
            }

            if(this.mEglContext != null) {
                this.mEgl.eglDestroyContext(this.mEglDisplay, this.mEglContext);
                this.mEglContext = ((EGLContext)v5);
            }

            if(this.mEglDisplay != null) {
                this.mEgl.eglTerminate(this.mEglDisplay);
                this.mEglDisplay = ((EGLDisplay)v5);
            }
        }

        public void start() {
            this.mEgl = EGLContext.getEGL();
            this.mEglDisplay = this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEgl.eglInitialize(this.mEglDisplay, new int[2]);
            this.mEglConfig = GLSurfaceViewCupcake.this.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
            this.mEglContext = this.mEgl.eglCreateContext(this.mEglDisplay, this.mEglConfig, EGL10.EGL_NO_CONTEXT, null);
            this.mEglSurface = null;
        }

        public boolean swap() {
            boolean v0;
            this.mEgl.eglSwapBuffers(this.mEglDisplay, this.mEglSurface);
            if(this.mEgl.eglGetError() != 12302) {
                v0 = true;
            }
            else {
                v0 = false;
            }

            return v0;
        }
    }

    class GLThread extends Thread {
        private boolean mDone;
        private EglHelper mEglHelper;
        private ArrayList mEventQueue;
        private boolean mHasSurface;
        private int mHeight;
        private boolean mPaused;
        private int mRenderMode;
        private GLSurfaceView$Renderer mRenderer;
        private boolean mRequestRender;
        private boolean mSizeChanged;
        private int mWidth;

        GLThread(GLSurfaceViewCupcake arg4, GLSurfaceView$Renderer renderer) {
            GLSurfaceViewCupcake.this = arg4;
            super();
            this.mEventQueue = new ArrayList();
            this.mDone = false;
            this.mWidth = 0;
            this.mHeight = 0;
            this.mRequestRender = true;
            this.mRenderMode = 1;
            this.mRenderer = renderer;
            this.mSizeChanged = true;
            this.setName("GLThread");
        }

        private Runnable getEvent() {  // has try-catch handlers
            Object v0_1;
            try {
                if(this.mEventQueue.size() > 0) {
                    v0_1 = this.mEventQueue.remove(0);
                }
                else {
                    Runnable v0_2 = null;
                }

                return ((Runnable)v0_1);
            label_10:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_10;
            }
        }

        public int getRenderMode() {  // has try-catch handlers
            try {
                return this.mRenderMode;
            label_3:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_3;
            }
        }

        private void guardedRun() throws InterruptedException {  // has try-catch handlers
            EglHelper v8_1;
            GL v1_1;
            int v2;
            int v7;
            boolean v0;
            this.mEglHelper = new EglHelper(GLSurfaceViewCupcake.this);
            this.mEglHelper.start();
            GL10 v1 = null;
            int v6 = 1;
            int v5 = 1;
            while(true) {
                if(!this.mDone) {
                    int v3 = 0;
                    try {
                        while(true) {
                            Runnable v4 = this.getEvent();
                            if(v4 == null) {
                                break;
                            }

                            v4.run();
                        }

                        if(this.mPaused) {
                            this.mEglHelper.finish();
                            v3 = 1;
                        }

                        while(this.needToWait()) {
                            this.wait();
                        }

                        if(!this.mDone) {
                            goto label_31;
                        }

                        goto label_28;
                    label_31:
                        v0 = this.mSizeChanged;
                        v7 = this.mWidth;
                        v2 = this.mHeight;
                        this.mSizeChanged = false;
                        this.mRequestRender = false;
                        if(v3 != 0) {
                            goto label_39;
                        }

                        goto label_43;
                    }
                    catch(Throwable v8) {
                        goto label_16;
                    }

                label_39:
                    this.mEglHelper.start();
                    v6 = 1;
                    v0 = true;
                label_43:
                    if(v0) {
                        v1_1 = this.mEglHelper.createSurface(GLSurfaceViewCupcake.this.getHolder());
                        v5 = 1;
                    }

                    if(v6 != 0) {
                        this.mRenderer.onSurfaceCreated(((GL10)v1_1), this.mEglHelper.mEglConfig);
                        v6 = 0;
                    }

                    if(v5 != 0) {
                        this.mRenderer.onSurfaceChanged(((GL10)v1_1), v7, v2);
                        v5 = 0;
                    }

                    if(v7 <= 0) {
                        continue;
                    }

                    if(v2 <= 0) {
                        continue;
                    }

                    this.mRenderer.onDrawFrame(((GL10)v1_1));
                    this.mEglHelper.swap();
                    continue;
                }

                goto label_28;
            }

            try {
            label_28:
                v8_1 = this.mEglHelper;
                goto label_29;
            label_16:
                throw v8;
            }
            catch(Throwable v8) {
                goto label_16;
            }

        label_29:
            v8_1.finish();
        }

        private boolean needToWait() {
            boolean v0 = false;
            if(!this.mDone) {
                if(!this.mPaused && (this.mHasSurface)) {
                    if(this.mWidth > 0 && this.mHeight > 0) {
                        if(!this.mRequestRender) {
                            if(this.mRenderMode != 1) {
                                goto label_19;
                            }

                            goto label_4;
                        }
                        else {
                            goto label_4;
                        }
                    }

                label_19:
                    v0 = true;
                    goto label_4;
                }

                v0 = true;
            }

        label_4:
            return v0;
        }

        public void onPause() {  // has try-catch handlers
            try {
                this.mPaused = true;
                return;
            label_4:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_4;
            }
        }

        public void onResume() {  // has try-catch handlers
            try {
                this.mPaused = false;
                this.notify();
                return;
            label_5:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_5;
            }
        }

        public void onWindowResize(int w, int h) {  // has try-catch handlers
            try {
                this.mWidth = w;
                this.mHeight = h;
                this.mSizeChanged = true;
                this.notify();
                return;
            label_7:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_7;
            }
        }

        public void queueEvent(Runnable r) {  // has try-catch handlers
            try {
                this.mEventQueue.add(r);
                return;
            label_4:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_4;
            }
        }

        public void requestExitAndWait() {  // has try-catch handlers
            try {
                this.mDone = true;
                this.notify();
                try {
                    this.join();
                    return;
                }
                catch(InterruptedException v0) {
                }
            }
            catch(Throwable v1) {
                try {
                label_6:
                    throw v1;
                }
                catch(Throwable v1) {
                    goto label_6;
                }
            }

            Thread.currentThread().interrupt();
        }

        public void requestRender() {  // has try-catch handlers
            try {
                this.mRequestRender = true;
                this.notify();
                return;
            label_5:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_5;
            }
        }

        public void run() {  // has try-catch handlers
            try {
                this.guardedRun();
            }
            catch(InterruptedException v0) {
                goto label_6;
            }
            catch(Throwable v0_1) {
                goto label_8;
            }
            catch(Throwable v0_1) {
                goto label_4;
            }

            return;
            try {
            label_4:
                throw v0_1;
            }
            catch(Throwable v0_1) {
                goto label_4;
            }
            catch(InterruptedException v0) {
            }
            catch(Throwable v0_1) {
                goto label_8;
            }

        label_6:
            return;
        label_8:
            throw v0_1;
        }

        public void setRenderMode(int renderMode) {  // has try-catch handlers
            if(renderMode >= 0 && renderMode <= 1) {
                try {
                    this.mRenderMode = renderMode;
                    if(renderMode == 1) {
                        this.notify();
                    }

                    return;
                label_11:
                    throw v0;
                }
                catch(Throwable v0) {
                    goto label_11;
                }
            }

            throw new IllegalArgumentException("renderMode");
        }

        public void surfaceCreated() {  // has try-catch handlers
            try {
                this.mHasSurface = true;
                this.notify();
                return;
            label_5:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_5;
            }
        }

        public void surfaceDestroyed() {  // has try-catch handlers
            try {
                this.mHasSurface = false;
                this.notify();
                return;
            label_5:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_5;
            }
        }
    }

    public abstract interface GLWrapper {
        public abstract GL wrap(GL arg0);
    }

    class LogWriter extends Writer {
        private StringBuilder mBuilder;

        LogWriter() {
            super();
            this.mBuilder = new StringBuilder();
        }

        public void close() {
            this.flushBuilder();
        }

        public void flush() {
            this.flushBuilder();
        }

        private void flushBuilder() {
            if(this.mBuilder.length() > 0) {
                Log.v("GLSurfaceView", this.mBuilder.toString());
                this.mBuilder.delete(0, this.mBuilder.length());
            }
        }

        public void write(char[] buf, int offset, int count) {
            int v1;
            for(v1 = 0; v1 < count; ++v1) {
                char v0 = buf[offset + v1];
                if(v0 == 10) {
                    this.flushBuilder();
                }
                else {
                    this.mBuilder.append(v0);
                }
            }
        }
    }

    class SimpleEGLConfigChooser extends ComponentSizeChooser {
        public SimpleEGLConfigChooser(boolean withDepthBuffer) {
            int v5;
            int v7 = 5;
            int v1 = 4;
            if(withDepthBuffer) {
                v5 = 16;
            }
            else {
                v5 = 0;
            }

            super(v1, v1, v1, 0, v5, 0);
            this.mRedSize = v7;
            this.mGreenSize = 6;
            this.mBlueSize = v7;
        }
    }

    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY;
    private int mDebugFlags;
    GLSurfaceView$EGLConfigChooser mEGLConfigChooser;
    private GLThread mGLThread;
    GLWrapper mGLWrapper;
    private boolean mHasSurface;
    private int mRenderMode;
    private GLSurfaceView$Renderer mRenderer;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    final ResolutionStrategy resolutionStrategy;
    static final Object sEglLock;

    static  {
        GLSurfaceViewCupcake.sEglLock = new Object();
    }

    public GLSurfaceViewCupcake(Context context, ResolutionStrategy resolutionStrategy) {
        super(context);
        this.resolutionStrategy = resolutionStrategy;
        this.init();
    }

    public GLSurfaceViewCupcake(Context context, AttributeSet attrs, ResolutionStrategy resolutionStrategy) {
        super(context, attrs);
        this.resolutionStrategy = resolutionStrategy;
        this.init();
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public int getRenderMode() {
        return this.mRenderMode;
    }

    private void init() {
        SurfaceHolder v0 = this.getHolder();
        v0.addCallback(((SurfaceHolder$Callback)this));
        v0.setType(2);
        this.mRenderMode = 1;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasuredDimension v0 = this.resolutionStrategy.calcMeasures(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(v0.width, v0.height);
    }

    public void onPause() {
        this.mGLThread.onPause();
        this.mGLThread.requestExitAndWait();
        this.mGLThread = null;
    }

    public void onResume() {
        if(this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new SimpleEGLConfigChooser(true);
        }

        this.mGLThread = new GLThread(this, this.mRenderer);
        this.mGLThread.start();
        this.mGLThread.setRenderMode(this.mRenderMode);
        if(this.mHasSurface) {
            this.mGLThread.surfaceCreated();
        }

        if(this.mSurfaceWidth > 0 && this.mSurfaceHeight > 0) {
            this.mGLThread.onWindowResize(this.mSurfaceWidth, this.mSurfaceHeight);
        }

        this.mGLThread.onResume();
    }

    public void queueEvent(Runnable r) {
        if(this.mGLThread != null) {
            this.mGLThread.queueEvent(r);
        }
    }

    public void requestRender() {
        GLThread v0 = this.mGLThread;
        if(v0 != null) {
            v0.requestRender();
        }
    }

    public void setDebugFlags(int debugFlags) {
        this.mDebugFlags = debugFlags;
    }

    public void setEGLConfigChooser(GLSurfaceView$EGLConfigChooser configChooser) {
        if(this.mRenderer != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }

        this.mEGLConfigChooser = configChooser;
    }

    public void setEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {
        this.setEGLConfigChooser(new ComponentSizeChooser(redSize, greenSize, blueSize, alphaSize, depthSize, stencilSize));
    }

    public void setEGLConfigChooser(boolean needDepth) {
        this.setEGLConfigChooser(new SimpleEGLConfigChooser(needDepth));
    }

    public void setGLWrapper(GLWrapper glWrapper) {
        this.mGLWrapper = glWrapper;
    }

    public void setRenderMode(int renderMode) {
        this.mRenderMode = renderMode;
        if(this.mGLThread != null) {
            this.mGLThread.setRenderMode(renderMode);
        }
    }

    public void setRenderer(GLSurfaceView$Renderer renderer) {
        if(this.mRenderer != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }

        this.mRenderer = renderer;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        if(this.mGLThread != null) {
            this.mGLThread.onWindowResize(w, h);
        }

        this.mSurfaceWidth = w;
        this.mSurfaceHeight = h;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if(this.mGLThread != null) {
            this.mGLThread.surfaceCreated();
        }

        this.mHasSurface = true;
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if(this.mGLThread != null) {
            this.mGLThread.surfaceDestroyed();
        }

        this.mHasSurface = false;
    }
}

