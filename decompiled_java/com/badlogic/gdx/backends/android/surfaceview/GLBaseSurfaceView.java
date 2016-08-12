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
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView$EGLConfigChooser;
import android.opengl.GLSurfaceView$Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder$Callback;
import java.io.Writer;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class GLBaseSurfaceView extends GLSurfaceView implements SurfaceHolder$Callback {
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
                int v12 = this.findConfigAttrib(egl, display, v4, 12325, 0);
                int v18 = this.findConfigAttrib(egl, display, v4, 12326, 0);
                if(v12 >= this.mDepthSize && v18 >= this.mStencilSize) {
                    int v13 = Math.abs(this.findConfigAttrib(egl, display, v4, 12324, 0) - this.mRedSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12323, 0) - this.mGreenSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12322, 0) - this.mBlueSize) + Math.abs(this.findConfigAttrib(egl, display, v4, 12321, 0) - this.mAlphaSize);
                    if(v13 < v11) {
                        v11 = v13;
                        v10 = v4;
                    }
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

    class DefaultContextFactory implements EGLContextFactory {
        DefaultContextFactory() {
            super();
        }

        public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig config) {
            return egl.eglCreateContext(display, config, EGL10.EGL_NO_CONTEXT, null);
        }

        public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
            egl.eglDestroyContext(display, context);
        }
    }

    class DefaultWindowSurfaceFactory implements EGLWindowSurfaceFactory {
        DefaultWindowSurfaceFactory() {
            super();
        }

        public EGLSurface createWindowSurface(EGL10 egl, EGLDisplay display, EGLConfig config, Object nativeWindow) {
            return egl.eglCreateWindowSurface(display, config, nativeWindow, null);
        }

        public void destroySurface(EGL10 egl, EGLDisplay display, EGLSurface surface) {
            egl.eglDestroySurface(display, surface);
        }
    }

    public abstract interface EGLContextFactory {
        public abstract EGLContext createContext(EGL10 arg0, EGLDisplay arg1, EGLConfig arg2);

        public abstract void destroyContext(EGL10 arg0, EGLDisplay arg1, EGLContext arg2);
    }

    public abstract interface EGLWindowSurfaceFactory {
        public abstract EGLSurface createWindowSurface(EGL10 arg0, EGLDisplay arg1, EGLConfig arg2, Object arg3);

        public abstract void destroySurface(EGL10 arg0, EGLDisplay arg1, EGLSurface arg2);
    }

    class EglHelper {
        EGL10 mEgl;
        EGLConfig mEglConfig;
        EGLContext mEglContext;
        EGLDisplay mEglDisplay;
        EGLSurface mEglSurface;

        public EglHelper(GLBaseSurfaceView arg1) {
            GLBaseSurfaceView.this = arg1;
            super();
        }

        public GL createSurface(SurfaceHolder holder) {
            LogWriter v2_1;
            if(this.mEglSurface != null && this.mEglSurface != EGL10.EGL_NO_SURFACE) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLBaseSurfaceView.this.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
            }

            this.mEglSurface = GLBaseSurfaceView.this.mEGLWindowSurfaceFactory.createWindowSurface(this.mEgl, this.mEglDisplay, this.mEglConfig, holder);
            if(this.mEglSurface == null || this.mEglSurface == EGL10.EGL_NO_SURFACE) {
                this.throwEglException("createWindowSurface");
            }

            if(!this.mEgl.eglMakeCurrent(this.mEglDisplay, this.mEglSurface, this.mEglSurface, this.mEglContext)) {
                this.throwEglException("eglMakeCurrent");
            }

            GL v1 = this.mEglContext.getGL();
            if(GLBaseSurfaceView.this.mGLWrapper != null) {
                v1 = GLBaseSurfaceView.this.mGLWrapper.wrap(v1);
            }

            if((GLBaseSurfaceView.this.mDebugFlags & 3) != 0) {
                int v0 = 0;
                Writer v2 = null;
                if((GLBaseSurfaceView.this.mDebugFlags & 1) != 0) {
                    v0 = 1;
                }

                if((GLBaseSurfaceView.this.mDebugFlags & 2) != 0) {
                    v2_1 = new LogWriter();
                }

                v1 = GLDebugHelper.wrap(v1, v0, ((Writer)v2_1));
            }

            return v1;
        }

        public void destroySurface() {
            if(this.mEglSurface != null && this.mEglSurface != EGL10.EGL_NO_SURFACE) {
                this.mEgl.eglMakeCurrent(this.mEglDisplay, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLBaseSurfaceView.this.mEGLWindowSurfaceFactory.destroySurface(this.mEgl, this.mEglDisplay, this.mEglSurface);
                this.mEglSurface = null;
            }
        }

        public void finish() {
            EGLContext v4 = null;
            if(this.mEglContext != null) {
                GLBaseSurfaceView.this.mEGLContextFactory.destroyContext(this.mEgl, this.mEglDisplay, this.mEglContext);
                this.mEglContext = v4;
            }

            if(this.mEglDisplay != null) {
                this.mEgl.eglTerminate(this.mEglDisplay);
                this.mEglDisplay = ((EGLDisplay)v4);
            }
        }

        public void start() {
            this.mEgl = EGLContext.getEGL();
            this.mEglDisplay = this.mEgl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.mEgl.eglInitialize(this.mEglDisplay, new int[2]);
            this.mEglConfig = GLBaseSurfaceView.this.mEGLConfigChooser.chooseConfig(this.mEgl, this.mEglDisplay);
            this.mEglContext = GLBaseSurfaceView.this.mEGLContextFactory.createContext(this.mEgl, this.mEglDisplay, this.mEglConfig);
            if(this.mEglContext != null && this.mEglContext != EGL10.EGL_NO_CONTEXT) {
                this.mEglSurface = null;
                return;
            }

            throw new RuntimeException("createContext failed");
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

        private void throwEglException(String function) {
            throw new RuntimeException(function + " failed: " + this.mEgl.eglGetError());
        }
    }

    class GLThread extends Thread {
        private EglHelper mEglHelper;
        private ArrayList mEventQueue;
        boolean mExited;
        private boolean mHasSurface;
        private boolean mHaveEgl;
        private int mHeight;
        private boolean mPaused;
        private boolean mRenderComplete;
        private int mRenderMode;
        private GLSurfaceView$Renderer mRenderer;
        private boolean mRequestRender;
        private boolean mShouldExit;
        private boolean mWaitingForSurface;
        private int mWidth;

        GLThread(GLBaseSurfaceView arg4, GLSurfaceView$Renderer renderer) {
            GLBaseSurfaceView.this = arg4;
            super();
            this.mEventQueue = new ArrayList();
            this.mWidth = 0;
            this.mHeight = 0;
            this.mRequestRender = true;
            this.mRenderMode = 1;
            this.mRenderer = renderer;
        }

        public int getRenderMode() {  // has try-catch handlers
            try {
                return this.mRenderMode;
            label_4:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_4;
            }
        }

        private void guardedRun() throws InterruptedException {  // has try-catch handlers
            this.mEglHelper = new EglHelper(GLBaseSurfaceView.this);
            GL10 v4 = null;
            int v1 = 0;
            int v6 = 0;
            int v8 = 0;
            int v2 = 0;
            int v7 = 0;
            int v5 = 0;
            Object v3 = null;
            while(true) {
                try {
                    while(true) {
                    label_13:
                        boolean v9_1 = this.mShouldExit;
                        break;
                    }
                }
                catch(Throwable v9) {
                    goto label_107;
                }
                catch(Throwable v9) {
                    goto label_104;
                }

                if(!v9_1) {
                    goto label_20;
                }

                try {
                    this.stopEglLocked();
                    return;
                }
                catch(Throwable v9) {
                    goto label_19;
                }

                try {
                label_20:
                    if(!this.mEventQueue.isEmpty()) {
                        v3 = this.mEventQueue.remove(0);
                    }
                    else {
                        if((this.mHaveEgl) && (this.mPaused)) {
                            this.stopEglLocked();
                        }

                        if(!this.mHasSurface && !this.mWaitingForSurface) {
                            if(this.mHaveEgl) {
                                this.stopEglLocked();
                            }

                            this.mWaitingForSurface = true;
                            GLBaseSurfaceView.sGLThreadManager.notifyAll();
                        }

                        if((this.mHasSurface) && (this.mWaitingForSurface)) {
                            this.mWaitingForSurface = false;
                            GLBaseSurfaceView.sGLThreadManager.notifyAll();
                        }

                        if(v2 != 0) {
                            v8 = 0;
                            v2 = 0;
                            this.mRenderComplete = true;
                            GLBaseSurfaceView.sGLThreadManager.notifyAll();
                        }

                        if(!this.mPaused && (this.mHasSurface) && this.mWidth > 0 && this.mHeight > 0) {
                            if(!this.mRequestRender && this.mRenderMode != 1) {
                                goto label_112;
                            }

                            if(!this.mHaveEgl && (GLBaseSurfaceView.sGLThreadManager.tryAcquireEglSurfaceLocked(this))) {
                                this.mHaveEgl = true;
                                this.mEglHelper.start();
                                v1 = 1;
                                v6 = 1;
                                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                            }

                            if(!this.mHaveEgl) {
                                goto label_112;
                            }

                            if(GLBaseSurfaceView.this.mSizeChanged) {
                                v6 = 1;
                                v7 = this.mWidth;
                                v5 = this.mHeight;
                                v8 = 1;
                                GLBaseSurfaceView.this.mSizeChanged = false;
                            }
                            else {
                                this.mRequestRender = false;
                            }

                            GLBaseSurfaceView.sGLThreadManager.notifyAll();
                            goto label_28;
                        }

                        goto label_112;
                    }

                label_28:
                    if(v3 == null) {
                        goto label_115;
                    }

                    goto label_29;
                }
                catch(Throwable v9) {
                    goto label_104;
                }

                try {
                label_29:
                    ((Runnable)v3).run();
                    v3 = null;
                    continue;
                }
                catch(Throwable v9) {
                    goto label_107;
                }

            label_115:
                if(v1 != 0) {
                    try {
                        GL v4_1 = this.mEglHelper.createSurface(GLBaseSurfaceView.this.getHolder());
                        this.mRenderer.onSurfaceCreated(((GL10)v4_1), this.mEglHelper.mEglConfig);
                        v1 = 0;
                    label_127:
                        if(v6 != 0) {
                            this.mRenderer.onSurfaceChanged(((GL10)v4_1), v7, v5);
                            v6 = 0;
                        }

                        this.mRenderer.onDrawFrame(((GL10)v4_1));
                        this.mEglHelper.swap();
                        if(v8 == 0) {
                            continue;
                        }

                        goto label_136;
                    }
                    catch(Throwable v9) {
                        goto label_107;
                    }
                }

                goto label_127;
            label_136:
                v2 = 1;
            }

            try {
            label_112:
                GLBaseSurfaceView.sGLThreadManager.wait();
                goto label_13;
            }
            catch(Throwable v9) {
                goto label_104;
            }

            try {
            label_19:
                throw v9;
            }
            catch(Throwable v9) {
                goto label_19;
            }

            try {
            label_104:
                throw v9;
            }
            catch(Throwable v9) {
                goto label_104;
            }
            catch(Throwable v9) {
                goto label_107;
            }

            try {
            label_107:
                this.stopEglLocked();
                throw v9;
            }
            catch(Throwable v9) {
                try {
                label_139:
                    throw v9;
                }
                catch(Throwable v9) {
                    goto label_139;
                }
            }
        }

        public void onPause() {  // has try-catch handlers
            try {
                this.mPaused = true;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                return;
            label_7:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_7;
            }
        }

        public void onResume() {  // has try-catch handlers
            try {
                this.mPaused = false;
                this.mRequestRender = true;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                return;
            label_9:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_9;
            }
        }

        public void onWindowResize(int w, int h) {  // has try-catch handlers
            try {
                this.mWidth = w;
                this.mHeight = h;
                GLBaseSurfaceView.this.mSizeChanged = true;
                this.mRequestRender = true;
                this.mRenderComplete = false;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                while(true) {
                    if(!this.mExited && !this.mPaused && !this.mRenderComplete) {
                        try {
                            GLBaseSurfaceView.sGLThreadManager.wait();
                            continue;
                        }
                        catch(InterruptedException v0) {
                            try {
                                Thread.currentThread().interrupt();
                                continue;
                                return;
                            label_26:
                                throw v1;
                            }
                            catch(Throwable v1) {
                                goto label_26;
                            }
                        }
                    }

                    return;
                }
            }
            catch(Throwable v1) {
                goto label_26;
            }
        }

        public void queueEvent(Runnable r) {  // has try-catch handlers
            if(r != null) {
                goto label_5;
            }

            throw new IllegalArgumentException("r must not be null");
            try {
            label_5:
                this.mEventQueue.add(r);
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                return;
            label_11:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_11;
            }
        }

        public void requestExitAndWait() {  // has try-catch handlers
            try {
                this.mShouldExit = true;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                while(true) {
                    if(this.mExited) {
                        return;
                    }

                    try {
                        GLBaseSurfaceView.sGLThreadManager.wait();
                        continue;
                    }
                    catch(InterruptedException v0) {
                        try {
                            Thread.currentThread().interrupt();
                            continue;
                            return;
                        label_15:
                            throw v1;
                        }
                        catch(Throwable v1) {
                            goto label_15;
                        }
                    }
                }
            }
            catch(Throwable v1) {
                goto label_15;
            }
        }

        public void requestRender() {  // has try-catch handlers
            try {
                this.mRequestRender = true;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                return;
            label_7:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_7;
            }
        }

        public void run() {  // has try-catch handlers
            this.setName("GLThread " + this.getId());
            try {
                this.guardedRun();
            }
            catch(Throwable v0) {
                GLBaseSurfaceView.sGLThreadManager.threadExiting(this);
                throw v0;
            }
            catch(InterruptedException v0_1) {
                GLBaseSurfaceView.sGLThreadManager.threadExiting(this);
                return;
            }

            GLBaseSurfaceView.sGLThreadManager.threadExiting(this);
        }

        public void setRenderMode(int renderMode) {  // has try-catch handlers
            if(renderMode >= 0 && renderMode <= 1) {
                try {
                    this.mRenderMode = renderMode;
                    GLBaseSurfaceView.sGLThreadManager.notifyAll();
                    return;
                label_12:
                    throw v0;
                }
                catch(Throwable v0) {
                    goto label_12;
                }
            }

            throw new IllegalArgumentException("renderMode");
        }

        private void stopEglLocked() {
            if(this.mHaveEgl) {
                this.mHaveEgl = false;
                this.mEglHelper.destroySurface();
                this.mEglHelper.finish();
                GLBaseSurfaceView.sGLThreadManager.releaseEglSurfaceLocked(this);
            }
        }

        public void surfaceCreated() {  // has try-catch handlers
            try {
                this.mHasSurface = true;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                return;
            label_7:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_7;
            }
        }

        public void surfaceDestroyed() {  // has try-catch handlers
            try {
                this.mHasSurface = false;
                GLBaseSurfaceView.sGLThreadManager.notifyAll();
                while(true) {
                    if(!this.mWaitingForSurface && !this.mExited) {
                        try {
                            GLBaseSurfaceView.sGLThreadManager.wait();
                            continue;
                        }
                        catch(InterruptedException v0) {
                            try {
                                Thread.currentThread().interrupt();
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

                    return;
                }
            }
            catch(Throwable v1) {
                goto label_17;
            }
        }
    }

    class GLThreadManager {
        private GLThread mEglOwner;

        GLThreadManager() {
            super();
        }

        public void releaseEglSurfaceLocked(GLThread thread) {
            if(this.mEglOwner == thread) {
                this.mEglOwner = null;
            }

            this.notifyAll();
        }

        public void threadExiting(GLThread thread) {  // has try-catch handlers
            try {
                thread.mExited = true;
                if(this.mEglOwner == thread) {
                    this.mEglOwner = null;
                }

                this.notifyAll();
                return;
            }
            catch(Throwable v0) {
                throw v0;
            }
        }

        public boolean tryAcquireEglSurfaceLocked(GLThread thread) {
            boolean v0;
            if(this.mEglOwner == thread || this.mEglOwner == null) {
                this.mEglOwner = thread;
                this.notifyAll();
                v0 = true;
            }
            else {
                v0 = false;
            }

            return v0;
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
    private static final boolean DRAW_TWICE_AFTER_SIZE_CHANGED = true;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY;
    int mDebugFlags;
    GLSurfaceView$EGLConfigChooser mEGLConfigChooser;
    EGLContextFactory mEGLContextFactory;
    EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private GLThread mGLThread;
    GLWrapper mGLWrapper;
    boolean mSizeChanged;
    static final GLThreadManager sGLThreadManager;

    static  {
        GLBaseSurfaceView.sGLThreadManager = new GLThreadManager();
    }

    public GLBaseSurfaceView(Context context) {
        super(context);
        this.mSizeChanged = true;
        this.init();
    }

    public GLBaseSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSizeChanged = true;
        this.init();
    }

    private void checkRenderThreadState() {
        if(this.mGLThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public int getRenderMode() {
        return this.mGLThread.getRenderMode();
    }

    private void init() {
        this.getHolder().addCallback(((SurfaceHolder$Callback)this));
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mGLThread.requestExitAndWait();
    }

    public void onPause() {
        this.mGLThread.onPause();
    }

    public void onResume() {
        this.mGLThread.onResume();
    }

    public void queueEvent(Runnable r) {
        this.mGLThread.queueEvent(r);
    }

    public void requestRender() {
        this.mGLThread.requestRender();
    }

    public void setDebugFlags(int debugFlags) {
        this.mDebugFlags = debugFlags;
    }

    public void setEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {
        this.setEGLConfigChooser(new ComponentSizeChooser(redSize, greenSize, blueSize, alphaSize, depthSize, stencilSize));
    }

    public void setEGLConfigChooser(GLSurfaceView$EGLConfigChooser configChooser) {
        this.checkRenderThreadState();
        this.mEGLConfigChooser = configChooser;
    }

    public void setEGLConfigChooser(boolean needDepth) {
        this.setEGLConfigChooser(new SimpleEGLConfigChooser(needDepth));
    }

    public void setEGLContextFactory(EGLContextFactory factory) {
        this.checkRenderThreadState();
        this.mEGLContextFactory = factory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory factory) {
        this.checkRenderThreadState();
        this.mEGLWindowSurfaceFactory = factory;
    }

    public void setGLWrapper(GLWrapper glWrapper) {
        this.mGLWrapper = glWrapper;
    }

    public void setRenderMode(int renderMode) {
        this.mGLThread.setRenderMode(renderMode);
    }

    public void setRenderer(GLSurfaceView$Renderer renderer) {
        this.checkRenderThreadState();
        if(this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new SimpleEGLConfigChooser(true);
        }

        if(this.mEGLContextFactory == null) {
            this.mEGLContextFactory = new DefaultContextFactory();
        }

        if(this.mEGLWindowSurfaceFactory == null) {
            this.mEGLWindowSurfaceFactory = new DefaultWindowSurfaceFactory();
        }

        this.mGLThread = new GLThread(this, renderer);
        this.mGLThread.start();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        this.mGLThread.onWindowResize(w, h);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.mGLThread.surfaceCreated();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.mGLThread.surfaceDestroyed();
    }
}

