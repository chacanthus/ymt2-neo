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

import java.io.IOException;
import java.io.Writer;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGL11;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

class EGLLogWrapper implements EGL11 {
    private int mArgCount;
    boolean mCheckError;
    private EGL10 mEgl10;
    Writer mLog;
    boolean mLogArgumentNames;

    public EGLLogWrapper(EGL egl, int configFlags, Writer log) {
        boolean v0;
        boolean v1 = true;
        super();
        this.mEgl10 = ((EGL10)egl);
        this.mLog = log;
        if((configFlags & 4) != 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        this.mLogArgumentNames = v0;
        if((configFlags & 1) == 0) {
            v1 = false;
        }

        this.mCheckError = v1;
    }

    private void arg(String name, int value) {
        this.arg(name, Integer.toString(value));
    }

    private void arg(String name, String value) {
        int v0 = this.mArgCount;
        this.mArgCount = v0 + 1;
        if(v0 > 0) {
            this.log(", ");
        }

        if(this.mLogArgumentNames) {
            this.log(name + "=");
        }

        this.log(value);
    }

    private void arg(String name, Object object) {
        this.arg(name, this.toString(object));
    }

    private void arg(String name, EGLContext object) {
        if(object == EGL10.EGL_NO_CONTEXT) {
            this.arg(name, "EGL10.EGL_NO_CONTEXT");
        }
        else {
            this.arg(name, this.toString(object));
        }
    }

    private void arg(String name, EGLDisplay object) {
        if(object == EGL10.EGL_DEFAULT_DISPLAY) {
            this.arg(name, "EGL10.EGL_DEFAULT_DISPLAY");
        }
        else if(object == EGLLogWrapper.EGL_NO_DISPLAY) {
            this.arg(name, "EGL10.EGL_NO_DISPLAY");
        }
        else {
            this.arg(name, this.toString(object));
        }
    }

    private void arg(String name, EGLSurface object) {
        if(object == EGL10.EGL_NO_SURFACE) {
            this.arg(name, "EGL10.EGL_NO_SURFACE");
        }
        else {
            this.arg(name, this.toString(object));
        }
    }

    private void arg(String name, int[] arr) {
        if(arr == null) {
            this.arg(name, "null");
        }
        else {
            this.arg(name, this.toString(arr.length, arr, 0));
        }
    }

    private void arg(String name, Object[] arr) {
        if(arr == null) {
            this.arg(name, "null");
        }
        else {
            this.arg(name, this.toString(arr.length, arr, 0));
        }
    }

    private void begin(String name) {
        this.log(name + '(');
        this.mArgCount = 0;
    }

    private void checkError() {
        int v0 = this.mEgl10.eglGetError();
        if(v0 != 12288) {
            String v1 = "eglError: " + EGLLogWrapper.getErrorString(v0);
            this.logLine(v1);
            if(this.mCheckError) {
                throw new GLException(v0, v1);
            }
        }
    }

    public boolean eglChooseConfig(EGLDisplay display, int[] attrib_list, EGLConfig[] configs, int config_size, int[] num_config) {
        this.begin("eglChooseConfig");
        this.arg("display", display);
        this.arg("attrib_list", attrib_list);
        this.arg("config_size", config_size);
        this.end();
        boolean v6 = this.mEgl10.eglChooseConfig(display, attrib_list, configs, config_size, num_config);
        this.arg("configs", ((Object[])configs));
        this.arg("num_config", num_config);
        this.returns(v6);
        this.checkError();
        return v6;
    }

    public boolean eglCopyBuffers(EGLDisplay display, EGLSurface surface, Object native_pixmap) {
        this.begin("eglCopyBuffers");
        this.arg("display", display);
        this.arg("surface", surface);
        this.arg("native_pixmap", native_pixmap);
        this.end();
        boolean v0 = this.mEgl10.eglCopyBuffers(display, surface, native_pixmap);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLContext eglCreateContext(EGLDisplay display, EGLConfig config, EGLContext share_context, int[] attrib_list) {
        this.begin("eglCreateContext");
        this.arg("display", display);
        this.arg("config", config);
        this.arg("share_context", share_context);
        this.arg("attrib_list", attrib_list);
        this.end();
        EGLContext v0 = this.mEgl10.eglCreateContext(display, config, share_context, attrib_list);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLSurface eglCreatePbufferSurface(EGLDisplay display, EGLConfig config, int[] attrib_list) {
        this.begin("eglCreatePbufferSurface");
        this.arg("display", display);
        this.arg("config", config);
        this.arg("attrib_list", attrib_list);
        this.end();
        EGLSurface v0 = this.mEgl10.eglCreatePbufferSurface(display, config, attrib_list);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLSurface eglCreatePixmapSurface(EGLDisplay display, EGLConfig config, Object native_pixmap, int[] attrib_list) {
        this.begin("eglCreatePixmapSurface");
        this.arg("display", display);
        this.arg("config", config);
        this.arg("native_pixmap", native_pixmap);
        this.arg("attrib_list", attrib_list);
        this.end();
        EGLSurface v0 = this.mEgl10.eglCreatePixmapSurface(display, config, native_pixmap, attrib_list);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLSurface eglCreateWindowSurface(EGLDisplay display, EGLConfig config, Object native_window, int[] attrib_list) {
        this.begin("eglCreateWindowSurface");
        this.arg("display", display);
        this.arg("config", config);
        this.arg("native_window", native_window);
        this.arg("attrib_list", attrib_list);
        this.end();
        EGLSurface v0 = this.mEgl10.eglCreateWindowSurface(display, config, native_window, attrib_list);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglDestroyContext(EGLDisplay display, EGLContext context) {
        this.begin("eglDestroyContext");
        this.arg("display", display);
        this.arg("context", context);
        this.end();
        boolean v0 = this.mEgl10.eglDestroyContext(display, context);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglDestroySurface(EGLDisplay display, EGLSurface surface) {
        this.begin("eglDestroySurface");
        this.arg("display", display);
        this.arg("surface", surface);
        this.end();
        boolean v0 = this.mEgl10.eglDestroySurface(display, surface);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglGetConfigAttrib(EGLDisplay display, EGLConfig config, int attribute, int[] value) {
        this.begin("eglGetConfigAttrib");
        this.arg("display", display);
        this.arg("config", config);
        this.arg("attribute", attribute);
        this.end();
        boolean v0 = this.mEgl10.eglGetConfigAttrib(display, config, attribute, value);
        this.arg("value", value);
        this.returns(v0);
        this.checkError();
        return 0;
    }

    public boolean eglGetConfigs(EGLDisplay display, EGLConfig[] configs, int config_size, int[] num_config) {
        this.begin("eglGetConfigs");
        this.arg("display", display);
        this.arg("config_size", config_size);
        this.end();
        boolean v0 = this.mEgl10.eglGetConfigs(display, configs, config_size, num_config);
        this.arg("configs", ((Object[])configs));
        this.arg("num_config", num_config);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLContext eglGetCurrentContext() {
        this.begin("eglGetCurrentContext");
        this.end();
        EGLContext v0 = this.mEgl10.eglGetCurrentContext();
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLDisplay eglGetCurrentDisplay() {
        this.begin("eglGetCurrentDisplay");
        this.end();
        EGLDisplay v0 = this.mEgl10.eglGetCurrentDisplay();
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLSurface eglGetCurrentSurface(int readdraw) {
        this.begin("eglGetCurrentSurface");
        this.arg("readdraw", readdraw);
        this.end();
        EGLSurface v0 = this.mEgl10.eglGetCurrentSurface(readdraw);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public EGLDisplay eglGetDisplay(Object native_display) {
        this.begin("eglGetDisplay");
        this.arg("native_display", native_display);
        this.end();
        EGLDisplay v0 = this.mEgl10.eglGetDisplay(native_display);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public int eglGetError() {
        this.begin("eglGetError");
        this.end();
        int v0 = this.mEgl10.eglGetError();
        this.returns(EGLLogWrapper.getErrorString(v0));
        return v0;
    }

    public boolean eglInitialize(EGLDisplay display, int[] major_minor) {
        this.begin("eglInitialize");
        this.arg("display", display);
        this.end();
        boolean v0 = this.mEgl10.eglInitialize(display, major_minor);
        this.returns(v0);
        this.arg("major_minor", major_minor);
        this.checkError();
        return v0;
    }

    public boolean eglMakeCurrent(EGLDisplay display, EGLSurface draw, EGLSurface read, EGLContext context) {
        this.begin("eglMakeCurrent");
        this.arg("display", display);
        this.arg("draw", draw);
        this.arg("read", read);
        this.arg("context", context);
        this.end();
        boolean v0 = this.mEgl10.eglMakeCurrent(display, draw, read, context);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglQueryContext(EGLDisplay display, EGLContext context, int attribute, int[] value) {
        this.begin("eglQueryContext");
        this.arg("display", display);
        this.arg("context", context);
        this.arg("attribute", attribute);
        this.end();
        boolean v0 = this.mEgl10.eglQueryContext(display, context, attribute, value);
        this.returns(value[0]);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public String eglQueryString(EGLDisplay display, int name) {
        this.begin("eglQueryString");
        this.arg("display", display);
        this.arg("name", name);
        this.end();
        String v0 = this.mEgl10.eglQueryString(display, name);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglQuerySurface(EGLDisplay display, EGLSurface surface, int attribute, int[] value) {
        this.begin("eglQuerySurface");
        this.arg("display", display);
        this.arg("surface", surface);
        this.arg("attribute", attribute);
        this.end();
        boolean v0 = this.mEgl10.eglQuerySurface(display, surface, attribute, value);
        this.returns(value[0]);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglSwapBuffers(EGLDisplay display, EGLSurface surface) {
        this.begin("eglInitialize");
        this.arg("display", display);
        this.arg("surface", surface);
        this.end();
        boolean v0 = this.mEgl10.eglSwapBuffers(display, surface);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglTerminate(EGLDisplay display) {
        this.begin("eglTerminate");
        this.arg("display", display);
        this.end();
        boolean v0 = this.mEgl10.eglTerminate(display);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglWaitGL() {
        this.begin("eglWaitGL");
        this.end();
        boolean v0 = this.mEgl10.eglWaitGL();
        this.returns(v0);
        this.checkError();
        return v0;
    }

    public boolean eglWaitNative(int engine, Object bindTarget) {
        this.begin("eglWaitNative");
        this.arg("engine", engine);
        this.arg("bindTarget", bindTarget);
        this.end();
        boolean v0 = this.mEgl10.eglWaitNative(engine, bindTarget);
        this.returns(v0);
        this.checkError();
        return v0;
    }

    private void end() {
        this.log(");\n");
        this.flush();
    }

    private void flush() {  // has try-catch handlers
        try {
            this.mLog.flush();
        }
        catch(IOException v0) {
            this.mLog = null;
        }
    }

    public static String getErrorString(int error) {
        String v0;
        switch(error) {
            case 12288: {
                v0 = "EGL_SUCCESS";
                break;
            }
            case 12289: {
                v0 = "EGL_NOT_INITIALIZED";
                break;
            }
            case 12290: {
                v0 = "EGL_BAD_ACCESS";
                break;
            }
            case 12291: {
                v0 = "EGL_BAD_ALLOC";
                break;
            }
            case 12292: {
                v0 = "EGL_BAD_ATTRIBUTE";
                break;
            }
            case 12293: {
                v0 = "EGL_BAD_CONFIG";
                break;
            }
            case 12294: {
                v0 = "EGL_BAD_CONTEXT";
                break;
            }
            case 12295: {
                v0 = "EGL_BAD_CURRENT_SURFACE";
                break;
            }
            case 12296: {
                v0 = "EGL_BAD_DISPLAY";
                break;
            }
            case 12297: {
                v0 = "EGL_BAD_MATCH";
                break;
            }
            case 12298: {
                v0 = "EGL_BAD_NATIVE_PIXMAP";
                break;
            }
            case 12299: {
                v0 = "EGL_BAD_NATIVE_WINDOW";
                break;
            }
            case 12300: {
                v0 = "EGL_BAD_PARAMETER";
                break;
            }
            case 12301: {
                v0 = "EGL_BAD_SURFACE";
                break;
            }
            case 12302: {
                v0 = "EGL_CONTEXT_LOST";
                break;
            }
            default: {
                v0 = EGLLogWrapper.getHex(error);
                break;
            }
        }

        return v0;
    }

    private static String getHex(int value) {
        return "0x" + Integer.toHexString(value);
    }

    private void log(String message) {  // has try-catch handlers
        try {
            this.mLog.write(message);
        }
        catch(IOException v0) {
        }
    }

    private void logLine(String message) {
        this.log(message + '\n');
    }

    private void returns(int result) {
        this.returns(Integer.toString(result));
    }

    private void returns(String result) {
        this.log(" returns " + result + ";\n");
        this.flush();
    }

    private void returns(Object result) {
        this.returns(this.toString(result));
    }

    private void returns(boolean result) {
        this.returns(Boolean.toString(result));
    }

    private String toString(Object obj) {
        String v0;
        if(obj == null) {
            v0 = "null";
        }
        else {
            v0 = obj.toString();
        }

        return v0;
    }

    private String toString(int n, int[] arr, int offset) {
        StringBuilder v1 = new StringBuilder();
        v1.append("{\n");
        int v0 = arr.length;
        int v2;
        for(v2 = 0; v2 < n; ++v2) {
            int v3 = offset + v2;
            v1.append(" [" + v3 + "] = ");
            if(v3 < 0 || v3 >= v0) {
                v1.append("out of bounds");
            }
            else {
                v1.append(arr[v3]);
            }

            v1.append('\n');
        }

        v1.append("}");
        return v1.toString();
    }

    private String toString(int n, Object[] arr, int offset) {
        StringBuilder v1 = new StringBuilder();
        v1.append("{\n");
        int v0 = arr.length;
        int v2;
        for(v2 = 0; v2 < n; ++v2) {
            int v3 = offset + v2;
            v1.append(" [" + v3 + "] = ");
            if(v3 < 0 || v3 >= v0) {
                v1.append("out of bounds");
            }
            else {
                v1.append(arr[v3]);
            }

            v1.append('\n');
        }

        v1.append("}");
        return v1.toString();
    }
}

