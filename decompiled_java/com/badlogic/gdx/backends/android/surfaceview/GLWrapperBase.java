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
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL10Ext;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

abstract class GLWrapperBase implements GL10, GL10Ext, GL11, GL11Ext, GL {
    protected GL10 mgl;
    protected GL10Ext mgl10Ext;
    protected GL11 mgl11;
    protected GL11Ext mgl11Ext;
    protected GL11ExtensionPack mgl11ExtensionPack;

    public GLWrapperBase(GL gl) {
        super();
        this.mgl = gl;
        if((gl instanceof GL10Ext)) {
            this.mgl10Ext = gl;
        }

        if((gl instanceof GL11)) {
            this.mgl11 = gl;
        }

        if((gl instanceof GL11Ext)) {
            this.mgl11Ext = gl;
        }

        if((gl instanceof GL11ExtensionPack)) {
            this.mgl11ExtensionPack = ((GL11ExtensionPack)gl);
        }
    }

    public void glBindBuffer(int target, int buffer) {
        throw new UnsupportedOperationException();
    }

    public void glBindFramebufferOES(int target, int framebuffer) {
        throw new UnsupportedOperationException();
    }

    public void glBindRenderbufferOES(int target, int renderbuffer) {
        throw new UnsupportedOperationException();
    }

    public void glBlendEquation(int mode) {
        throw new UnsupportedOperationException();
    }

    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        throw new UnsupportedOperationException();
    }

    public void glBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        throw new UnsupportedOperationException();
    }

    public void glBufferData(int target, int size, Buffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    public void glBufferSubData(int target, int offset, int size, Buffer data) {
        throw new UnsupportedOperationException();
    }

    int glCheckFramebufferStatusOES(int target) {
        throw new UnsupportedOperationException();
    }

    public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
        throw new UnsupportedOperationException();
    }

    public void glColorPointer(int size, int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize) {
        throw new UnsupportedOperationException();
    }

    public void glCurrentPaletteMatrixOES(int matrixpaletteindex) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteBuffers(int n, IntBuffer buffers) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteBuffers(int n, int[] buffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteFramebuffersOES(int n, IntBuffer framebuffers) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteFramebuffersOES(int n, int[] framebuffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteRenderbuffersOES(int n, IntBuffer renderbuffers) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteRenderbuffersOES(int n, int[] renderbuffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glDrawElements(int mode, int count, int type, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glFramebufferRenderbufferOES(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        throw new UnsupportedOperationException();
    }

    public void glFramebufferTexture2DOES(int target, int attachment, int textarget, int texture, int level) {
        throw new UnsupportedOperationException();
    }

    public void glGenBuffers(int n, IntBuffer buffers) {
        throw new UnsupportedOperationException();
    }

    public void glGenBuffers(int n, int[] buffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGenFramebuffersOES(int n, IntBuffer framebuffers) {
        throw new UnsupportedOperationException();
    }

    public void glGenFramebuffersOES(int n, int[] framebuffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGenRenderbuffersOES(int n, IntBuffer renderbuffers) {
        throw new UnsupportedOperationException();
    }

    public void glGenRenderbuffersOES(int n, int[] renderbuffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGenerateMipmapOES(int target) {
        throw new UnsupportedOperationException();
    }

    public void glGetBooleanv(int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetBooleanv(int pname, boolean[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetBufferParameteriv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetClipPlanef(int pname, FloatBuffer eqn) {
        throw new UnsupportedOperationException();
    }

    public void glGetClipPlanef(int pname, float[] eqn, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetClipPlanex(int pname, IntBuffer eqn) {
        throw new UnsupportedOperationException();
    }

    public void glGetClipPlanex(int pname, int[] eqn, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetFixedv(int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetFixedv(int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetFloatv(int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetFloatv(int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetFramebufferAttachmentParameterivOES(int target, int attachment, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetFramebufferAttachmentParameterivOES(int target, int attachment, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetLightfv(int light, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetLightfv(int light, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetLightxv(int light, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetLightxv(int light, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetMaterialfv(int face, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetMaterialfv(int face, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetMaterialxv(int face, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetMaterialxv(int face, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetPointerv(int pname, Buffer[] params) {
        throw new UnsupportedOperationException();
    }

    public void glGetRenderbufferParameterivOES(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetRenderbufferParameterivOES(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexEnviv(int env, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexEnviv(int env, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexEnvxv(int env, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexEnvxv(int env, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGenfv(int coord, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGenfv(int coord, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGeniv(int coord, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGeniv(int coord, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGenxv(int coord, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexGenxv(int coord, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameterfv(int target, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameteriv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameterxv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glGetTexParameterxv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsBuffer(int buffer) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsEnabled(int cap) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsFramebufferOES(int framebuffer) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsRenderbufferOES(int renderbuffer) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsTexture(int texture) {
        throw new UnsupportedOperationException();
    }

    public void glLoadPaletteFromModelViewMatrixOES() {
        throw new UnsupportedOperationException();
    }

    public void glMatrixIndexPointerOES(int size, int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glMatrixIndexPointerOES(int size, int type, int stride, Buffer pointer) {
        throw new UnsupportedOperationException();
    }

    public void glNormalPointer(int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterf(int pname, float param) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterfv(int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterfv(int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterx(int pname, int param) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterxv(int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glPointParameterxv(int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glPointSizePointerOES(int type, int stride, Buffer pointer) {
        throw new UnsupportedOperationException();
    }

    public void glRenderbufferStorageOES(int target, int internalformat, int width, int height) {
        throw new UnsupportedOperationException();
    }

    public void glTexCoordPointer(int size, int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexEnvi(int target, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    public void glTexEnviv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexEnviv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenf(int coord, int pname, float param) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenfv(int coord, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenfv(int coord, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexGeni(int coord, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    public void glTexGeniv(int coord, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexGeniv(int coord, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenx(int coord, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenxv(int coord, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexGenxv(int coord, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameterfv(int target, int pname, FloatBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameterfv(int target, int pname, float[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameteri(int target, int pname, int param) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameterxv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameterxv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glVertexPointer(int size, int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glWeightPointerOES(int size, int type, int stride, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glWeightPointerOES(int size, int type, int stride, Buffer pointer) {
        throw new UnsupportedOperationException();
    }
}

