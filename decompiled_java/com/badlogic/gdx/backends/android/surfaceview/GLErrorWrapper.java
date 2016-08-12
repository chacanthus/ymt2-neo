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

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL;

class GLErrorWrapper extends GLWrapperBase {
    boolean mCheckError;
    boolean mCheckThread;
    Thread mOurThread;

    public GLErrorWrapper(GL gl, int configFlags) {
        boolean v0;
        boolean v1 = true;
        super(gl);
        if((configFlags & 1) != 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        this.mCheckError = v0;
        if((configFlags & 2) == 0) {
            v1 = false;
        }

        this.mCheckThread = v1;
    }

    private void checkError() {
        if(this.mCheckError) {
            int v0 = this.mgl.glGetError();
            if(v0 != 0) {
                throw new GLException(v0);
            }
        }
    }

    private void checkThread() {
        if(this.mCheckThread) {
            Thread v0 = Thread.currentThread();
            if(this.mOurThread == null) {
                this.mOurThread = v0;
            }
            else if(!this.mOurThread.equals(v0)) {
                throw new GLException(28672, "OpenGL method called from wrong thread.");
            }
        }
    }

    public void glActiveTexture(int texture) {
        this.checkThread();
        this.mgl.glActiveTexture(texture);
        this.checkError();
    }

    public void glAlphaFunc(int func, float ref) {
        this.checkThread();
        this.mgl.glAlphaFunc(func, ref);
        this.checkError();
    }

    public void glAlphaFuncx(int func, int ref) {
        this.checkThread();
        this.mgl.glAlphaFuncx(func, ref);
        this.checkError();
    }

    public void glBindTexture(int target, int texture) {
        this.checkThread();
        this.mgl.glBindTexture(target, texture);
        this.checkError();
    }

    public void glBlendFunc(int sfactor, int dfactor) {
        this.checkThread();
        this.mgl.glBlendFunc(sfactor, dfactor);
        this.checkError();
    }

    public void glClear(int mask) {
        this.checkThread();
        this.mgl.glClear(mask);
        this.checkError();
    }

    public void glClearColor(float red, float green, float blue, float alpha) {
        this.checkThread();
        this.mgl.glClearColor(red, green, blue, alpha);
        this.checkError();
    }

    public void glClearColorx(int red, int green, int blue, int alpha) {
        this.checkThread();
        this.mgl.glClearColorx(red, green, blue, alpha);
        this.checkError();
    }

    public void glClearDepthf(float depth) {
        this.checkThread();
        this.mgl.glClearDepthf(depth);
        this.checkError();
    }

    public void glClearDepthx(int depth) {
        this.checkThread();
        this.mgl.glClearDepthx(depth);
        this.checkError();
    }

    public void glClearStencil(int s) {
        this.checkThread();
        this.mgl.glClearStencil(s);
        this.checkError();
    }

    public void glClientActiveTexture(int texture) {
        this.checkThread();
        this.mgl.glClientActiveTexture(texture);
        this.checkError();
    }

    public void glClipPlanef(int plane, FloatBuffer equation) {
        this.checkThread();
        this.mgl11.glClipPlanef(plane, equation);
        this.checkError();
    }

    public void glClipPlanef(int plane, float[] equation, int offset) {
        this.checkThread();
        this.mgl11.glClipPlanef(plane, equation, offset);
        this.checkError();
    }

    public void glClipPlanex(int plane, IntBuffer equation) {
        this.checkThread();
        this.mgl11.glClipPlanex(plane, equation);
        this.checkError();
    }

    public void glClipPlanex(int plane, int[] equation, int offset) {
        this.checkThread();
        this.mgl11.glClipPlanex(plane, equation, offset);
        this.checkError();
    }

    public void glColor4f(float red, float green, float blue, float alpha) {
        this.checkThread();
        this.mgl.glColor4f(red, green, blue, alpha);
        this.checkError();
    }

    public void glColor4x(int red, int green, int blue, int alpha) {
        this.checkThread();
        this.mgl.glColor4x(red, green, blue, alpha);
        this.checkError();
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        this.checkThread();
        this.mgl.glColorMask(red, green, blue, alpha);
        this.checkError();
    }

    public void glColorPointer(int size, int type, int stride, Buffer pointer) {
        this.checkThread();
        this.mgl.glColorPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data) {
        this.checkThread();
        this.mgl.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
        this.checkError();
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data) {
        this.checkThread();
        this.mgl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
        this.checkError();
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
        this.checkThread();
        this.mgl.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
        this.checkError();
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        this.checkThread();
        this.mgl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
        this.checkError();
    }

    public void glCullFace(int mode) {
        this.checkThread();
        this.mgl.glCullFace(mode);
        this.checkError();
    }

    public void glDeleteTextures(int n, IntBuffer textures) {
        this.checkThread();
        this.mgl.glDeleteTextures(n, textures);
        this.checkError();
    }

    public void glDeleteTextures(int n, int[] textures, int offset) {
        this.checkThread();
        this.mgl.glDeleteTextures(n, textures, offset);
        this.checkError();
    }

    public void glDepthFunc(int func) {
        this.checkThread();
        this.mgl.glDepthFunc(func);
        this.checkError();
    }

    public void glDepthMask(boolean flag) {
        this.checkThread();
        this.mgl.glDepthMask(flag);
        this.checkError();
    }

    public void glDepthRangef(float near, float far) {
        this.checkThread();
        this.mgl.glDepthRangef(near, far);
        this.checkError();
    }

    public void glDepthRangex(int near, int far) {
        this.checkThread();
        this.mgl.glDepthRangex(near, far);
        this.checkError();
    }

    public void glDisable(int cap) {
        this.checkThread();
        this.mgl.glDisable(cap);
        this.checkError();
    }

    public void glDisableClientState(int array) {
        this.checkThread();
        this.mgl.glDisableClientState(array);
        this.checkError();
    }

    public void glDrawArrays(int mode, int first, int count) {
        this.checkThread();
        this.mgl.glDrawArrays(mode, first, count);
        this.checkError();
    }

    public void glDrawElements(int mode, int count, int type, Buffer indices) {
        this.checkThread();
        this.mgl.glDrawElements(mode, count, type, indices);
        this.checkError();
    }

    public void glDrawTexfOES(float x, float y, float z, float width, float height) {
        this.checkThread();
        this.mgl11Ext.glDrawTexfOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexfvOES(FloatBuffer coords) {
        this.checkThread();
        this.mgl11Ext.glDrawTexfvOES(coords);
        this.checkError();
    }

    public void glDrawTexfvOES(float[] coords, int offset) {
        this.checkThread();
        this.mgl11Ext.glDrawTexfvOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexiOES(int x, int y, int z, int width, int height) {
        this.checkThread();
        this.mgl11Ext.glDrawTexiOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexivOES(IntBuffer coords) {
        this.checkThread();
        this.mgl11Ext.glDrawTexivOES(coords);
        this.checkError();
    }

    public void glDrawTexivOES(int[] coords, int offset) {
        this.checkThread();
        this.mgl11Ext.glDrawTexivOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexsOES(short x, short y, short z, short width, short height) {
        this.checkThread();
        this.mgl11Ext.glDrawTexsOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexsvOES(ShortBuffer coords) {
        this.checkThread();
        this.mgl11Ext.glDrawTexsvOES(coords);
        this.checkError();
    }

    public void glDrawTexsvOES(short[] coords, int offset) {
        this.checkThread();
        this.mgl11Ext.glDrawTexsvOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexxOES(int x, int y, int z, int width, int height) {
        this.checkThread();
        this.mgl11Ext.glDrawTexxOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexxvOES(IntBuffer coords) {
        this.checkThread();
        this.mgl11Ext.glDrawTexxvOES(coords);
        this.checkError();
    }

    public void glDrawTexxvOES(int[] coords, int offset) {
        this.checkThread();
        this.mgl11Ext.glDrawTexxvOES(coords, offset);
        this.checkError();
    }

    public void glEnable(int cap) {
        this.checkThread();
        this.mgl.glEnable(cap);
        this.checkError();
    }

    public void glEnableClientState(int array) {
        this.checkThread();
        this.mgl.glEnableClientState(array);
        this.checkError();
    }

    public void glFinish() {
        this.checkThread();
        this.mgl.glFinish();
        this.checkError();
    }

    public void glFlush() {
        this.checkThread();
        this.mgl.glFlush();
        this.checkError();
    }

    public void glFogf(int pname, float param) {
        this.checkThread();
        this.mgl.glFogf(pname, param);
        this.checkError();
    }

    public void glFogfv(int pname, FloatBuffer params) {
        this.checkThread();
        this.mgl.glFogfv(pname, params);
        this.checkError();
    }

    public void glFogfv(int pname, float[] params, int offset) {
        this.checkThread();
        this.mgl.glFogfv(pname, params, offset);
        this.checkError();
    }

    public void glFogx(int pname, int param) {
        this.checkThread();
        this.mgl.glFogx(pname, param);
        this.checkError();
    }

    public void glFogxv(int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glFogxv(pname, params);
        this.checkError();
    }

    public void glFogxv(int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glFogxv(pname, params, offset);
        this.checkError();
    }

    public void glFrontFace(int mode) {
        this.checkThread();
        this.mgl.glFrontFace(mode);
        this.checkError();
    }

    public void glFrustumf(float left, float right, float bottom, float top, float near, float far) {
        this.checkThread();
        this.mgl.glFrustumf(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glFrustumx(int left, int right, int bottom, int top, int near, int far) {
        this.checkThread();
        this.mgl.glFrustumx(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glGenTextures(int n, IntBuffer textures) {
        this.checkThread();
        this.mgl.glGenTextures(n, textures);
        this.checkError();
    }

    public void glGenTextures(int n, int[] textures, int offset) {
        this.checkThread();
        this.mgl.glGenTextures(n, textures, offset);
        this.checkError();
    }

    public int glGetError() {
        this.checkThread();
        return this.mgl.glGetError();
    }

    public void glGetIntegerv(int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glGetIntegerv(pname, params);
        this.checkError();
    }

    public void glGetIntegerv(int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glGetIntegerv(pname, params, offset);
        this.checkError();
    }

    public String glGetString(int name) {
        this.checkThread();
        String v0 = this.mgl.glGetString(name);
        this.checkError();
        return v0;
    }

    public void glHint(int target, int mode) {
        this.checkThread();
        this.mgl.glHint(target, mode);
        this.checkError();
    }

    public void glLightModelf(int pname, float param) {
        this.checkThread();
        this.mgl.glLightModelf(pname, param);
        this.checkError();
    }

    public void glLightModelfv(int pname, FloatBuffer params) {
        this.checkThread();
        this.mgl.glLightModelfv(pname, params);
        this.checkError();
    }

    public void glLightModelfv(int pname, float[] params, int offset) {
        this.checkThread();
        this.mgl.glLightModelfv(pname, params, offset);
        this.checkError();
    }

    public void glLightModelx(int pname, int param) {
        this.checkThread();
        this.mgl.glLightModelx(pname, param);
        this.checkError();
    }

    public void glLightModelxv(int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glLightModelxv(pname, params);
        this.checkError();
    }

    public void glLightModelxv(int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glLightModelxv(pname, params, offset);
        this.checkError();
    }

    public void glLightf(int light, int pname, float param) {
        this.checkThread();
        this.mgl.glLightf(light, pname, param);
        this.checkError();
    }

    public void glLightfv(int light, int pname, FloatBuffer params) {
        this.checkThread();
        this.mgl.glLightfv(light, pname, params);
        this.checkError();
    }

    public void glLightfv(int light, int pname, float[] params, int offset) {
        this.checkThread();
        this.mgl.glLightfv(light, pname, params, offset);
        this.checkError();
    }

    public void glLightx(int light, int pname, int param) {
        this.checkThread();
        this.mgl.glLightx(light, pname, param);
        this.checkError();
    }

    public void glLightxv(int light, int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glLightxv(light, pname, params);
        this.checkError();
    }

    public void glLightxv(int light, int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glLightxv(light, pname, params, offset);
        this.checkError();
    }

    public void glLineWidth(float width) {
        this.checkThread();
        this.mgl.glLineWidth(width);
        this.checkError();
    }

    public void glLineWidthx(int width) {
        this.checkThread();
        this.mgl.glLineWidthx(width);
        this.checkError();
    }

    public void glLoadIdentity() {
        this.checkThread();
        this.mgl.glLoadIdentity();
        this.checkError();
    }

    public void glLoadMatrixf(FloatBuffer m) {
        this.checkThread();
        this.mgl.glLoadMatrixf(m);
        this.checkError();
    }

    public void glLoadMatrixf(float[] m, int offset) {
        this.checkThread();
        this.mgl.glLoadMatrixf(m, offset);
        this.checkError();
    }

    public void glLoadMatrixx(IntBuffer m) {
        this.checkThread();
        this.mgl.glLoadMatrixx(m);
        this.checkError();
    }

    public void glLoadMatrixx(int[] m, int offset) {
        this.checkThread();
        this.mgl.glLoadMatrixx(m, offset);
        this.checkError();
    }

    public void glLogicOp(int opcode) {
        this.checkThread();
        this.mgl.glLogicOp(opcode);
        this.checkError();
    }

    public void glMaterialf(int face, int pname, float param) {
        this.checkThread();
        this.mgl.glMaterialf(face, pname, param);
        this.checkError();
    }

    public void glMaterialfv(int face, int pname, FloatBuffer params) {
        this.checkThread();
        this.mgl.glMaterialfv(face, pname, params);
        this.checkError();
    }

    public void glMaterialfv(int face, int pname, float[] params, int offset) {
        this.checkThread();
        this.mgl.glMaterialfv(face, pname, params, offset);
        this.checkError();
    }

    public void glMaterialx(int face, int pname, int param) {
        this.checkThread();
        this.mgl.glMaterialx(face, pname, param);
        this.checkError();
    }

    public void glMaterialxv(int face, int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glMaterialxv(face, pname, params);
        this.checkError();
    }

    public void glMaterialxv(int face, int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glMaterialxv(face, pname, params, offset);
        this.checkError();
    }

    public void glMatrixMode(int mode) {
        this.checkThread();
        this.mgl.glMatrixMode(mode);
        this.checkError();
    }

    public void glMultMatrixf(FloatBuffer m) {
        this.checkThread();
        this.mgl.glMultMatrixf(m);
        this.checkError();
    }

    public void glMultMatrixf(float[] m, int offset) {
        this.checkThread();
        this.mgl.glMultMatrixf(m, offset);
        this.checkError();
    }

    public void glMultMatrixx(IntBuffer m) {
        this.checkThread();
        this.mgl.glMultMatrixx(m);
        this.checkError();
    }

    public void glMultMatrixx(int[] m, int offset) {
        this.checkThread();
        this.mgl.glMultMatrixx(m, offset);
        this.checkError();
    }

    public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
        this.checkThread();
        this.mgl.glMultiTexCoord4f(target, s, t, r, q);
        this.checkError();
    }

    public void glMultiTexCoord4x(int target, int s, int t, int r, int q) {
        this.checkThread();
        this.mgl.glMultiTexCoord4x(target, s, t, r, q);
        this.checkError();
    }

    public void glNormal3f(float nx, float ny, float nz) {
        this.checkThread();
        this.mgl.glNormal3f(nx, ny, nz);
        this.checkError();
    }

    public void glNormal3x(int nx, int ny, int nz) {
        this.checkThread();
        this.mgl.glNormal3x(nx, ny, nz);
        this.checkError();
    }

    public void glNormalPointer(int type, int stride, Buffer pointer) {
        this.checkThread();
        this.mgl.glNormalPointer(type, stride, pointer);
        this.checkError();
    }

    public void glOrthof(float left, float right, float bottom, float top, float near, float far) {
        this.checkThread();
        this.mgl.glOrthof(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glOrthox(int left, int right, int bottom, int top, int near, int far) {
        this.checkThread();
        this.mgl.glOrthox(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glPixelStorei(int pname, int param) {
        this.checkThread();
        this.mgl.glPixelStorei(pname, param);
        this.checkError();
    }

    public void glPointSize(float size) {
        this.checkThread();
        this.mgl.glPointSize(size);
        this.checkError();
    }

    public void glPointSizex(int size) {
        this.checkThread();
        this.mgl.glPointSizex(size);
        this.checkError();
    }

    public void glPolygonOffset(float factor, float units) {
        this.checkThread();
        this.mgl.glPolygonOffset(factor, units);
        this.checkError();
    }

    public void glPolygonOffsetx(int factor, int units) {
        this.checkThread();
        this.mgl.glPolygonOffsetx(factor, units);
        this.checkError();
    }

    public void glPopMatrix() {
        this.checkThread();
        this.mgl.glPopMatrix();
        this.checkError();
    }

    public void glPushMatrix() {
        this.checkThread();
        this.mgl.glPushMatrix();
        this.checkError();
    }

    public int glQueryMatrixxOES(IntBuffer mantissa, IntBuffer exponent) {
        this.checkThread();
        int v0 = this.mgl10Ext.glQueryMatrixxOES(mantissa, exponent);
        this.checkError();
        return v0;
    }

    public int glQueryMatrixxOES(int[] mantissa, int mantissaOffset, int[] exponent, int exponentOffset) {
        this.checkThread();
        int v0 = this.mgl10Ext.glQueryMatrixxOES(mantissa, mantissaOffset, exponent, exponentOffset);
        this.checkError();
        return v0;
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        this.checkThread();
        this.mgl.glReadPixels(x, y, width, height, format, type, pixels);
        this.checkError();
    }

    public void glRotatef(float angle, float x, float y, float z) {
        this.checkThread();
        this.mgl.glRotatef(angle, x, y, z);
        this.checkError();
    }

    public void glRotatex(int angle, int x, int y, int z) {
        this.checkThread();
        this.mgl.glRotatex(angle, x, y, z);
        this.checkError();
    }

    public void glSampleCoverage(float value, boolean invert) {
        this.checkThread();
        this.mgl.glSampleCoverage(value, invert);
        this.checkError();
    }

    public void glSampleCoveragex(int value, boolean invert) {
        this.checkThread();
        this.mgl.glSampleCoveragex(value, invert);
        this.checkError();
    }

    public void glScalef(float x, float y, float z) {
        this.checkThread();
        this.mgl.glScalef(x, y, z);
        this.checkError();
    }

    public void glScalex(int x, int y, int z) {
        this.checkThread();
        this.mgl.glScalex(x, y, z);
        this.checkError();
    }

    public void glScissor(int x, int y, int width, int height) {
        this.checkThread();
        this.mgl.glScissor(x, y, width, height);
        this.checkError();
    }

    public void glShadeModel(int mode) {
        this.checkThread();
        this.mgl.glShadeModel(mode);
        this.checkError();
    }

    public void glStencilFunc(int func, int ref, int mask) {
        this.checkThread();
        this.mgl.glStencilFunc(func, ref, mask);
        this.checkError();
    }

    public void glStencilMask(int mask) {
        this.checkThread();
        this.mgl.glStencilMask(mask);
        this.checkError();
    }

    public void glStencilOp(int fail, int zfail, int zpass) {
        this.checkThread();
        this.mgl.glStencilOp(fail, zfail, zpass);
        this.checkError();
    }

    public void glTexCoordPointer(int size, int type, int stride, Buffer pointer) {
        this.checkThread();
        this.mgl.glTexCoordPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glTexEnvf(int target, int pname, float param) {
        this.checkThread();
        this.mgl.glTexEnvf(target, pname, param);
        this.checkError();
    }

    public void glTexEnvfv(int target, int pname, FloatBuffer params) {
        this.checkThread();
        this.mgl.glTexEnvfv(target, pname, params);
        this.checkError();
    }

    public void glTexEnvfv(int target, int pname, float[] params, int offset) {
        this.checkThread();
        this.mgl.glTexEnvfv(target, pname, params, offset);
        this.checkError();
    }

    public void glTexEnvx(int target, int pname, int param) {
        this.checkThread();
        this.mgl.glTexEnvx(target, pname, param);
        this.checkError();
    }

    public void glTexEnvxv(int target, int pname, IntBuffer params) {
        this.checkThread();
        this.mgl.glTexEnvxv(target, pname, params);
        this.checkError();
    }

    public void glTexEnvxv(int target, int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl.glTexEnvxv(target, pname, params, offset);
        this.checkError();
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels) {
        this.checkThread();
        this.mgl.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
        this.checkError();
    }

    public void glTexParameterf(int target, int pname, float param) {
        this.checkThread();
        this.mgl.glTexParameterf(target, pname, param);
        this.checkError();
    }

    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        this.checkThread();
        this.mgl11.glTexParameteriv(target, pname, params);
        this.checkError();
    }

    public void glTexParameteriv(int target, int pname, int[] params, int offset) {
        this.checkThread();
        this.mgl11.glTexParameteriv(target, pname, params, offset);
        this.checkError();
    }

    public void glTexParameterx(int target, int pname, int param) {
        this.checkThread();
        this.mgl.glTexParameterx(target, pname, param);
        this.checkError();
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels) {
        this.checkThread();
        this.mgl.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
        this.checkError();
    }

    public void glTranslatef(float x, float y, float z) {
        this.checkThread();
        this.mgl.glTranslatef(x, y, z);
        this.checkError();
    }

    public void glTranslatex(int x, int y, int z) {
        this.checkThread();
        this.mgl.glTranslatex(x, y, z);
        this.checkError();
    }

    public void glVertexPointer(int size, int type, int stride, Buffer pointer) {
        this.checkThread();
        this.mgl.glVertexPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glViewport(int x, int y, int width, int height) {
        this.checkThread();
        this.mgl.glViewport(x, y, width, height);
        this.checkError();
    }
}

