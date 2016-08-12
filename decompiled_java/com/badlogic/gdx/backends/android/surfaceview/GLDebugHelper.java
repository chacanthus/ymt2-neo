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

import java.io.Writer;
import javax.microedition.khronos.egl.EGL;
import javax.microedition.khronos.opengles.GL;

public class GLDebugHelper {
    public static final int CONFIG_CHECK_GL_ERROR = 1;
    public static final int CONFIG_CHECK_THREAD = 2;
    public static final int CONFIG_LOG_ARGUMENT_NAMES = 4;
    public static final int ERROR_WRONG_THREAD = 28672;

    public GLDebugHelper() {
        super();
    }

    public static GL wrap(GL gl, int configFlags, Writer log) {
        GLLogWrapper v3_1;
        boolean v1;
        GL v0_1;
        if(configFlags != 0) {
            GLErrorWrapper v0 = new GLErrorWrapper(gl, configFlags);
        }
        else {
            v0_1 = gl;
        }

        if(log != null) {
            if((configFlags & 4) != 0) {
                v1 = true;
            }
            else {
                v1 = false;
            }

            v3_1 = new GLLogWrapper(v0_1, log, v1);
        }
        else {
            GLErrorWrapper v3_2 = ((GLErrorWrapper)v0_1);
        }

        return ((GL)v3_1);
    }

    public static EGL wrap(EGL egl, int configFlags, Writer log) {
        EGLLogWrapper v1_1;
        if(log != null) {
            v1_1 = new EGLLogWrapper(egl, configFlags, log);
        }

        return ((EGL)v1_1);
    }
}

