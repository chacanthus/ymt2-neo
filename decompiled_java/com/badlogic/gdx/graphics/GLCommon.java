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

import java.nio.Buffer;
import java.nio.IntBuffer;

public abstract interface GLCommon {
    public static final int GL_GENERATE_MIPMAP = 33169;
    public static final int GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT = 34047;
    public static final int GL_TEXTURE_MAX_ANISOTROPY_EXT = 34046;

    public abstract void glActiveTexture(int arg0);

    public abstract void glBindTexture(int arg0, int arg1);

    public abstract void glBlendFunc(int arg0, int arg1);

    public abstract void glClear(int arg0);

    public abstract void glClearColor(float arg0, float arg1, float arg2, float arg3);

    public abstract void glClearDepthf(float arg0);

    public abstract void glClearStencil(int arg0);

    public abstract void glColorMask(boolean arg0, boolean arg1, boolean arg2, boolean arg3);

    public abstract void glCompressedTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, Buffer arg7);

    public abstract void glCompressedTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8);

    public abstract void glCopyTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7);

    public abstract void glCopyTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7);

    public abstract void glCullFace(int arg0);

    public abstract void glDeleteTextures(int arg0, IntBuffer arg1);

    public abstract void glDepthFunc(int arg0);

    public abstract void glDepthMask(boolean arg0);

    public abstract void glDepthRangef(float arg0, float arg1);

    public abstract void glDisable(int arg0);

    public abstract void glDrawArrays(int arg0, int arg1, int arg2);

    public abstract void glDrawElements(int arg0, int arg1, int arg2, Buffer arg3);

    public abstract void glEnable(int arg0);

    public abstract void glFinish();

    public abstract void glFlush();

    public abstract void glFrontFace(int arg0);

    public abstract void glGenTextures(int arg0, IntBuffer arg1);

    public abstract int glGetError();

    public abstract void glGetIntegerv(int arg0, IntBuffer arg1);

    public abstract String glGetString(int arg0);

    public abstract void glHint(int arg0, int arg1);

    public abstract void glLineWidth(float arg0);

    public abstract void glPixelStorei(int arg0, int arg1);

    public abstract void glPolygonOffset(float arg0, float arg1);

    public abstract void glReadPixels(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, Buffer arg6);

    public abstract void glScissor(int arg0, int arg1, int arg2, int arg3);

    public abstract void glStencilFunc(int arg0, int arg1, int arg2);

    public abstract void glStencilMask(int arg0);

    public abstract void glStencilOp(int arg0, int arg1, int arg2);

    public abstract void glTexImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8);

    public abstract void glTexParameterf(int arg0, int arg1, float arg2);

    public abstract void glTexSubImage2D(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, Buffer arg8);

    public abstract void glViewport(int arg0, int arg1, int arg2, int arg3);
}

