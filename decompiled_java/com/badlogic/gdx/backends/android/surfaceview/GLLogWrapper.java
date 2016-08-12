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
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import javax.microedition.khronos.opengles.GL;

class GLLogWrapper extends GLWrapperBase {
    class PointerInfo {
        public Buffer mPointer;
        public int mSize;
        public int mStride;
        public ByteBuffer mTempByteBuffer;
        public int mType;

        public PointerInfo(GLLogWrapper arg1, int size, int type, int stride, Buffer pointer) {
            GLLogWrapper.this = arg1;
            super();
            this.mSize = size;
            this.mType = type;
            this.mStride = stride;
            this.mPointer = pointer;
        }

        public void bindByteBuffer() {
            this.mTempByteBuffer = GLLogWrapper.this.toByteBuffer(-1, this.mPointer);
        }

        public int getStride() {
            int v0;
            if(this.mStride > 0) {
                v0 = this.mStride;
            }
            else {
                v0 = this.sizeof(this.mType) * this.mSize;
            }

            return v0;
        }

        public int sizeof(int type) {
            int v1 = 4;
            int v0 = 1;
            switch(type) {
                case 5120: 
                case 5121: {
                    break;
                }
                case 5122: {
                    v0 = 2;
                    break;
                }
                case 5126: {
                    v0 = v1;
                    break;
                }
                case 5132: {
                    v0 = v1;
                    break;
                }
                default: {
                    v0 = 0;
                    break;
                }
            }

            return v0;
        }

        public void unbindByteBuffer() {
            this.mTempByteBuffer = null;
        }
    }

    private static final int FORMAT_FIXED = 2;
    private static final int FORMAT_FLOAT = 1;
    private static final int FORMAT_INT;
    private int mArgCount;
    boolean mColorArrayEnabled;
    private PointerInfo mColorPointer;
    private Writer mLog;
    private boolean mLogArgumentNames;
    boolean mNormalArrayEnabled;
    private PointerInfo mNormalPointer;
    StringBuilder mStringBuilder;
    private PointerInfo mTexCoordPointer;
    boolean mTextureCoordArrayEnabled;
    boolean mVertexArrayEnabled;
    private PointerInfo mVertexPointer;

    public GLLogWrapper(GL gl, Writer log, boolean logArgumentNames) {
        super(gl);
        this.mLog = log;
        this.mLogArgumentNames = logArgumentNames;
    }

    private void arg(String name, float value) {
        this.arg(name, Float.toString(value));
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

    private void arg(String name, int value) {
        this.arg(name, Integer.toString(value));
    }

    private void arg(String name, int n, FloatBuffer buf) {
        this.arg(name, this.toString(n, buf));
    }

    private void arg(String name, int n, IntBuffer buf) {
        this.arg(name, this.toString(n, 0, buf));
    }

    private void arg(String name, int n, ShortBuffer buf) {
        this.arg(name, this.toString(n, buf));
    }

    private void arg(String name, int n, float[] arr, int offset) {
        this.arg(name, this.toString(n, arr, offset));
    }

    private void arg(String name, int n, int[] arr, int offset) {
        this.arg(name, this.toString(n, 0, arr, offset));
    }

    private void arg(String name, int n, short[] arr, int offset) {
        this.arg(name, this.toString(n, arr, offset));
    }

    private void arg(String name, boolean value) {
        this.arg(name, Boolean.toString(value));
    }

    private void argPointer(int size, int type, int stride, Buffer pointer) {
        this.arg("size", size);
        this.arg("type", this.getPointerTypeName(type));
        this.arg("stride", stride);
        this.arg("pointer", pointer.toString());
    }

    private void begin(String name) {
        this.log(name + '(');
        this.mArgCount = 0;
    }

    private void bindArrays() {
        if(this.mColorArrayEnabled) {
            this.mColorPointer.bindByteBuffer();
        }

        if(this.mNormalArrayEnabled) {
            this.mNormalPointer.bindByteBuffer();
        }

        if(this.mTextureCoordArrayEnabled) {
            this.mTexCoordPointer.bindByteBuffer();
        }

        if(this.mVertexArrayEnabled) {
            this.mVertexPointer.bindByteBuffer();
        }
    }

    private void checkError() {
        int v1 = this.mgl.glGetError();
        if(v1 != 0) {
            this.logLine("glError: " + Integer.toString(v1));
        }
    }

    private void doArrayElement(StringBuilder builder, boolean enabled, String name, PointerInfo pointer, int index) {
        if(enabled) {
            builder.append(" ");
            builder.append(name + ":{");
            if(pointer == null) {
                builder.append("undefined");
            }
            else if(pointer.mStride < 0) {
                builder.append("invalid stride");
            }
            else {
                int v9 = pointer.getStride();
                ByteBuffer v1 = pointer.mTempByteBuffer;
                int v7 = pointer.mSize;
                int v10 = pointer.mType;
                int v8 = pointer.sizeof(v10);
                int v2 = v9 * index;
                int v4;
                for(v4 = 0; v4 < v7; ++v4) {
                    if(v4 > 0) {
                        builder.append(", ");
                    }

                    switch(v10) {
                        case 5120: {
                            builder.append(Integer.toString(v1.get(v2)));
                            break;
                        }
                        case 5121: {
                            builder.append(Integer.toString(v1.get(v2) & 255));
                            break;
                        }
                        case 5122: {
                            builder.append(Integer.toString(v1.asShortBuffer().get(v2 / 2)));
                            break;
                        }
                        case 5126: {
                            builder.append(Float.toString(v1.asFloatBuffer().get(v2 / 4)));
                            break;
                        }
                        case 5132: {
                            builder.append(Integer.toString(v1.asIntBuffer().get(v2 / 4)));
                            break;
                        }
                        default: {
                            builder.append("?");
                            break;
                        }
                    }

                    v2 += v8;
                }

                builder.append("}");
            }
        }
    }

    private void doElement(StringBuilder builder, int ordinal, int vertexIndex) {
        builder.append(" [" + ordinal + " : " + vertexIndex + "] =");
        this.doArrayElement(builder, this.mVertexArrayEnabled, "v", this.mVertexPointer, vertexIndex);
        this.doArrayElement(builder, this.mNormalArrayEnabled, "n", this.mNormalPointer, vertexIndex);
        this.doArrayElement(builder, this.mColorArrayEnabled, "c", this.mColorPointer, vertexIndex);
        this.doArrayElement(builder, this.mTextureCoordArrayEnabled, "t", this.mTexCoordPointer, vertexIndex);
        builder.append("\n");
    }

    private void end() {
        this.log(");\n");
        this.flush();
    }

    private void endLogIndices() {
        this.log(this.mStringBuilder.toString());
        this.unbindArrays();
    }

    private void flush() {  // has try-catch handlers
        try {
            this.mLog.flush();
        }
        catch(IOException v0) {
            this.mLog = null;
        }
    }

    private void formattedAppend(StringBuilder buf, int value, int format) {
        switch(format) {
            case 0: {
                buf.append(value);
                break;
            }
            case 1: {
                buf.append(Float.intBitsToFloat(value));
                break;
            }
            case 2: {
                buf.append((((float)value)) / 65536f);
                break;
            }
        }
    }

    private String getBeginMode(int mode) {
        String v0;
        switch(mode) {
            case 0: {
                v0 = "GL_POINTS";
                break;
            }
            case 1: {
                v0 = "GL_LINES";
                break;
            }
            case 2: {
                v0 = "GL_LINE_LOOP";
                break;
            }
            case 3: {
                v0 = "GL_LINE_STRIP";
                break;
            }
            case 4: {
                v0 = "GL_TRIANGLES";
                break;
            }
            case 5: {
                v0 = "GL_TRIANGLE_STRIP";
                break;
            }
            case 6: {
                v0 = "GL_TRIANGLE_FAN";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(mode);
                break;
            }
        }

        return v0;
    }

    private String getCap(int cap) {
        String v0;
        switch(cap) {
            case 2832: {
                v0 = "GL_POINT_SMOOTH";
                break;
            }
            case 2848: {
                v0 = "GL_LINE_SMOOTH";
                break;
            }
            case 2884: {
                v0 = "GL_CULL_FACE";
                break;
            }
            case 2896: {
                v0 = "GL_LIGHTING";
                break;
            }
            case 2903: {
                v0 = "GL_COLOR_MATERIAL";
                break;
            }
            case 2912: {
                v0 = "GL_FOG";
                break;
            }
            case 2929: {
                v0 = "GL_DEPTH_TEST";
                break;
            }
            case 2960: {
                v0 = "GL_STENCIL_TEST";
                break;
            }
            case 2977: {
                v0 = "GL_NORMALIZE";
                break;
            }
            case 3008: {
                v0 = "GL_ALPHA_TEST";
                break;
            }
            case 3024: {
                v0 = "GL_DITHER";
                break;
            }
            case 3042: {
                v0 = "GL_BLEND";
                break;
            }
            case 3058: {
                v0 = "GL_COLOR_LOGIC_OP";
                break;
            }
            case 3089: {
                v0 = "GL_SCISSOR_TEST";
                break;
            }
            case 3553: {
                v0 = "GL_TEXTURE_2D";
                break;
            }
            case 16384: {
                v0 = "GL_LIGHT0";
                break;
            }
            case 16385: {
                v0 = "GL_LIGHT1";
                break;
            }
            case 16386: {
                v0 = "GL_LIGHT2";
                break;
            }
            case 16387: {
                v0 = "GL_LIGHT3";
                break;
            }
            case 16388: {
                v0 = "GL_LIGHT4";
                break;
            }
            case 16389: {
                v0 = "GL_LIGHT5";
                break;
            }
            case 16390: {
                v0 = "GL_LIGHT6";
                break;
            }
            case 16391: {
                v0 = "GL_LIGHT7";
                break;
            }
            case 32826: {
                v0 = "GL_RESCALE_NORMAL";
                break;
            }
            case 32884: {
                v0 = "GL_VERTEX_ARRAY";
                break;
            }
            case 32885: {
                v0 = "GL_NORMAL_ARRAY";
                break;
            }
            case 32886: {
                v0 = "GL_COLOR_ARRAY";
                break;
            }
            case 32888: {
                v0 = "GL_TEXTURE_COORD_ARRAY";
                break;
            }
            case 32925: {
                v0 = "GL_MULTISAMPLE";
                break;
            }
            case 32926: {
                v0 = "GL_SAMPLE_ALPHA_TO_COVERAGE";
                break;
            }
            case 32927: {
                v0 = "GL_SAMPLE_ALPHA_TO_ONE";
                break;
            }
            case 32928: {
                v0 = "GL_SAMPLE_COVERAGE";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(cap);
                break;
            }
        }

        return v0;
    }

    private String getClearBufferMask(int mask) {
        StringBuilder v0 = new StringBuilder();
        if((mask & 256) != 0) {
            v0.append("GL_DEPTH_BUFFER_BIT");
            mask &= -257;
        }

        if((mask & 1024) != 0) {
            if(v0.length() > 0) {
                v0.append(" | ");
            }

            v0.append("GL_STENCIL_BUFFER_BIT");
            mask &= -1025;
        }

        if((mask & 16384) != 0) {
            if(v0.length() > 0) {
                v0.append(" | ");
            }

            v0.append("GL_COLOR_BUFFER_BIT");
            mask &= -16385;
        }

        if(mask != 0) {
            if(v0.length() > 0) {
                v0.append(" | ");
            }

            v0.append(GLLogWrapper.getHex(mask));
        }

        return v0.toString();
    }

    private String getClientState(int clientState) {
        String v0;
        switch(clientState) {
            case 32884: {
                v0 = "GL_VERTEX_ARRAY";
                break;
            }
            case 32885: {
                v0 = "GL_NORMAL_ARRAY";
                break;
            }
            case 32886: {
                v0 = "GL_COLOR_ARRAY";
                break;
            }
            case 32888: {
                v0 = "GL_TEXTURE_COORD_ARRAY";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(clientState);
                break;
            }
        }

        return v0;
    }

    public static String getErrorString(int error) {
        String v0;
        switch(error) {
            case 0: {
                v0 = "GL_NO_ERROR";
                break;
            }
            case 1280: {
                v0 = "GL_INVALID_ENUM";
                break;
            }
            case 1281: {
                v0 = "GL_INVALID_VALUE";
                break;
            }
            case 1282: {
                v0 = "GL_INVALID_OPERATION";
                break;
            }
            case 1283: {
                v0 = "GL_STACK_OVERFLOW";
                break;
            }
            case 1284: {
                v0 = "GL_STACK_UNDERFLOW";
                break;
            }
            case 1285: {
                v0 = "GL_OUT_OF_MEMORY";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(error);
                break;
            }
        }

        return v0;
    }

    private String getFaceName(int face) {
        String v0;
        switch(face) {
            case 1032: {
                v0 = "GL_FRONT_AND_BACK";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(face);
                break;
            }
        }

        return v0;
    }

    private String getFactor(int factor) {
        String v0;
        switch(factor) {
            case 0: {
                v0 = "GL_ZERO";
                break;
            }
            case 1: {
                v0 = "GL_ONE";
                break;
            }
            case 768: {
                v0 = "GL_SRC_COLOR";
                break;
            }
            case 769: {
                v0 = "GL_ONE_MINUS_SRC_COLOR";
                break;
            }
            case 770: {
                v0 = "GL_SRC_ALPHA";
                break;
            }
            case 771: {
                v0 = "GL_ONE_MINUS_SRC_ALPHA";
                break;
            }
            case 772: {
                v0 = "GL_DST_ALPHA";
                break;
            }
            case 773: {
                v0 = "GL_ONE_MINUS_DST_ALPHA";
                break;
            }
            case 774: {
                v0 = "GL_DST_COLOR";
                break;
            }
            case 775: {
                v0 = "GL_ONE_MINUS_DST_COLOR";
                break;
            }
            case 776: {
                v0 = "GL_SRC_ALPHA_SATURATE";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(factor);
                break;
            }
        }

        return v0;
    }

    private String getFogPName(int pname) {
        String v0;
        switch(pname) {
            case 2914: {
                v0 = "GL_FOG_DENSITY";
                break;
            }
            case 2915: {
                v0 = "GL_FOG_START";
                break;
            }
            case 2916: {
                v0 = "GL_FOG_END";
                break;
            }
            case 2917: {
                v0 = "GL_FOG_MODE";
                break;
            }
            case 2918: {
                v0 = "GL_FOG_COLOR";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getFogParamCount(int pname) {
        int v0 = 1;
        switch(pname) {
            case 2914: 
            case 2915: 
            case 2916: 
            case 2917: {
                break;
            }
            case 2918: {
                v0 = 4;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private static String getHex(int value) {
        return "0x" + Integer.toHexString(value);
    }

    private String getHintMode(int mode) {
        String v0;
        switch(mode) {
            case 4352: {
                v0 = "GL_DONT_CARE";
                break;
            }
            case 4353: {
                v0 = "GL_FASTEST";
                break;
            }
            case 4354: {
                v0 = "GL_NICEST";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(mode);
                break;
            }
        }

        return v0;
    }

    private String getHintTarget(int target) {
        String v0;
        switch(target) {
            case 3152: {
                v0 = "GL_PERSPECTIVE_CORRECTION_HINT";
                break;
            }
            case 3153: {
                v0 = "GL_POINT_SMOOTH_HINT";
                break;
            }
            case 3154: {
                v0 = "GL_LINE_SMOOTH_HINT";
                break;
            }
            case 3155: {
                v0 = "GL_POLYGON_SMOOTH_HINT";
                break;
            }
            case 3156: {
                v0 = "GL_FOG_HINT";
                break;
            }
            case 33170: {
                v0 = "GL_GENERATE_MIPMAP_HINT";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(target);
                break;
            }
        }

        return v0;
    }

    private String getIndexType(int type) {
        String v0;
        switch(type) {
            case 5121: {
                v0 = "GL_UNSIGNED_BYTE";
                break;
            }
            case 5123: {
                v0 = "GL_UNSIGNED_SHORT";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(type);
                break;
            }
        }

        return v0;
    }

    private int getIntegerStateFormat(int pname) {
        int v0;
        switch(pname) {
            case 35213: 
            case 35214: 
            case 35215: {
                v0 = 1;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private String getIntegerStateName(int pname) {
        String v0;
        switch(pname) {
            case 2834: {
                v0 = "GL_SMOOTH_POINT_SIZE_RANGE";
                break;
            }
            case 2850: {
                v0 = "GL_SMOOTH_LINE_WIDTH_RANGE";
                break;
            }
            case 3377: {
                v0 = "GL_MAX_LIGHTS";
                break;
            }
            case 3379: {
                v0 = "GL_MAX_TEXTURE_SIZE";
                break;
            }
            case 3382: {
                v0 = "GL_MAX_MODELVIEW_STACK_DEPTH";
                break;
            }
            case 3384: {
                v0 = "GL_MAX_PROJECTION_STACK_DEPTH";
                break;
            }
            case 3385: {
                v0 = "GL_MAX_TEXTURE_STACK_DEPTH";
                break;
            }
            case 3386: {
                v0 = "GL_MAX_VIEWPORT_DIMS";
                break;
            }
            case 3408: {
                v0 = "GL_SUBPIXEL_BITS";
                break;
            }
            case 3410: {
                v0 = "GL_RED_BITS";
                break;
            }
            case 3411: {
                v0 = "GL_GREEN_BITS";
                break;
            }
            case 3412: {
                v0 = "GL_BLUE_BITS";
                break;
            }
            case 3413: {
                v0 = "GL_ALPHA_BITS";
                break;
            }
            case 3414: {
                v0 = "GL_DEPTH_BITS";
                break;
            }
            case 3415: {
                v0 = "GL_STENCIL_BITS";
                break;
            }
            case 33000: {
                v0 = "GL_MAX_ELEMENTS_VERTICES";
                break;
            }
            case 33001: {
                v0 = "GL_MAX_ELEMENTS_INDICES";
                break;
            }
            case 33901: {
                v0 = "GL_ALIASED_POINT_SIZE_RANGE";
                break;
            }
            case 33902: {
                v0 = "GL_ALIASED_LINE_WIDTH_RANGE";
                break;
            }
            case 34018: {
                v0 = "GL_MAX_TEXTURE_UNITS";
                break;
            }
            case 34466: {
                v0 = "GL_NUM_COMPRESSED_TEXTURE_FORMATS";
                break;
            }
            case 34467: {
                v0 = "GL_COMPRESSED_TEXTURE_FORMATS";
                break;
            }
            case 35213: {
                v0 = "GL_MODELVIEW_MATRIX_FLOAT_AS_INT_BITS_OES";
                break;
            }
            case 35214: {
                v0 = "GL_PROJECTION_MATRIX_FLOAT_AS_INT_BITS_OES";
                break;
            }
            case 35215: {
                v0 = "GL_TEXTURE_MATRIX_FLOAT_AS_INT_BITS_OES";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getIntegerStateSize(int pname) {
        int v2 = 2;
        int v1 = 1;
        switch(pname) {
            case 2834: {
                v1 = v2;
                break;
            }
            case 2850: {
                v1 = v2;
                break;
            }
            case 3386: {
                v1 = v2;
                break;
            }
            case 33901: {
                v1 = v2;
                break;
            }
            case 33902: {
                v1 = v2;
                break;
            }
            case 3377: 
            case 3379: 
            case 3382: 
            case 3384: 
            case 3385: 
            case 3408: 
            case 3410: 
            case 3411: 
            case 3412: 
            case 3413: 
            case 3414: 
            case 3415: 
            case 33000: 
            case 33001: 
            case 34018: 
            case 34466: {
                break;
            }
            case 34467: {
                int[] v0 = new int[1];
                this.mgl.glGetIntegerv(34466, v0, 0);
                v1 = v0[0];
                break;
            }
            case 35213: 
            case 35214: 
            case 35215: {
                v1 = 16;
                break;
            }
            default: {
                v1 = 0;
                break;
            }
        }

        return v1;
    }

    private String getLightModelPName(int pname) {
        String v0;
        switch(pname) {
            case 2898: {
                v0 = "GL_LIGHT_MODEL_TWO_SIDE";
                break;
            }
            case 2899: {
                v0 = "GL_LIGHT_MODEL_AMBIENT";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getLightModelParamCount(int pname) {
        int v0;
        switch(pname) {
            case 2898: {
                v0 = 1;
                break;
            }
            case 2899: {
                v0 = 4;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private String getLightName(int light) {
        String v0;
        if(light < 16384 || light > 16391) {
            v0 = GLLogWrapper.getHex(light);
        }
        else {
            v0 = "GL_LIGHT" + Integer.toString(light);
        }

        return v0;
    }

    private String getLightPName(int pname) {
        String v0;
        switch(pname) {
            case 4608: {
                v0 = "GL_AMBIENT";
                break;
            }
            case 4609: {
                v0 = "GL_DIFFUSE";
                break;
            }
            case 4610: {
                v0 = "GL_SPECULAR";
                break;
            }
            case 4611: {
                v0 = "GL_POSITION";
                break;
            }
            case 4612: {
                v0 = "GL_SPOT_DIRECTION";
                break;
            }
            case 4613: {
                v0 = "GL_SPOT_EXPONENT";
                break;
            }
            case 4614: {
                v0 = "GL_SPOT_CUTOFF";
                break;
            }
            case 4615: {
                v0 = "GL_CONSTANT_ATTENUATION";
                break;
            }
            case 4616: {
                v0 = "GL_LINEAR_ATTENUATION";
                break;
            }
            case 4617: {
                v0 = "GL_QUADRATIC_ATTENUATION";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getLightParamCount(int pname) {
        int v0 = 4;
        switch(pname) {
            case 4608: 
            case 4609: 
            case 4610: 
            case 4611: {
                break;
            }
            case 4612: {
                v0 = 3;
                break;
            }
            case 4613: {
                v0 = 1;
                break;
            }
            case 4614: {
                v0 = 1;
                break;
            }
            case 4615: {
                v0 = 1;
                break;
            }
            case 4616: {
                v0 = 1;
                break;
            }
            case 4617: {
                v0 = 1;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private String getMaterialPName(int pname) {
        String v0;
        switch(pname) {
            case 4608: {
                v0 = "GL_AMBIENT";
                break;
            }
            case 4609: {
                v0 = "GL_DIFFUSE";
                break;
            }
            case 4610: {
                v0 = "GL_SPECULAR";
                break;
            }
            case 5632: {
                v0 = "GL_EMISSION";
                break;
            }
            case 5633: {
                v0 = "GL_SHININESS";
                break;
            }
            case 5634: {
                v0 = "GL_AMBIENT_AND_DIFFUSE";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getMaterialParamCount(int pname) {
        int v0 = 4;
        switch(pname) {
            case 5633: {
                v0 = 1;
                break;
            }
            case 4608: 
            case 4609: 
            case 4610: 
            case 5632: 
            case 5634: {
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private String getMatrixMode(int matrixMode) {
        String v0;
        switch(matrixMode) {
            case 5888: {
                v0 = "GL_MODELVIEW";
                break;
            }
            case 5889: {
                v0 = "GL_PROJECTION";
                break;
            }
            case 5890: {
                v0 = "GL_TEXTURE";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(matrixMode);
                break;
            }
        }

        return v0;
    }

    private String getPointerTypeName(int type) {
        String v0;
        switch(type) {
            case 5120: {
                v0 = "GL_BYTE";
                break;
            }
            case 5121: {
                v0 = "GL_UNSIGNED_BYTE";
                break;
            }
            case 5122: {
                v0 = "GL_SHORT";
                break;
            }
            case 5126: {
                v0 = "GL_FLOAT";
                break;
            }
            case 5132: {
                v0 = "GL_FIXED";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(type);
                break;
            }
        }

        return v0;
    }

    private String getShadeModel(int model) {
        String v0;
        switch(model) {
            case 7424: {
                v0 = "GL_FLAT";
                break;
            }
            case 7425: {
                v0 = "GL_SMOOTH";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(model);
                break;
            }
        }

        return v0;
    }

    private String getTextureEnvPName(int pname) {
        String v0;
        switch(pname) {
            case 8704: {
                v0 = "GL_TEXTURE_ENV_MODE";
                break;
            }
            case 8705: {
                v0 = "GL_TEXTURE_ENV_COLOR";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private int getTextureEnvParamCount(int pname) {
        int v0;
        switch(pname) {
            case 8704: {
                v0 = 1;
                break;
            }
            case 8705: {
                v0 = 4;
                break;
            }
            default: {
                v0 = 0;
                break;
            }
        }

        return v0;
    }

    private String getTextureEnvParamName(float param) {
        String v1;
        int v0 = ((int)param);
        if(param == (((float)v0))) {
            switch(v0) {
                case 260: {
                    goto label_14;
                }
                case 3042: {
                    goto label_12;
                }
                case 7681: {
                    goto label_6;
                }
                case 8448: {
                    goto label_8;
                }
                case 8449: {
                    goto label_10;
                }
                case 34160: {
                    goto label_16;
                }
            }

            v1 = GLLogWrapper.getHex(v0);
            goto label_5;
        label_6:
            v1 = "GL_REPLACE";
            goto label_5;
        label_8:
            v1 = "GL_MODULATE";
            goto label_5;
        label_10:
            v1 = "GL_DECAL";
            goto label_5;
        label_12:
            v1 = "GL_BLEND";
            goto label_5;
        label_14:
            v1 = "GL_ADD";
            goto label_5;
        label_16:
            v1 = "GL_COMBINE";
        }
        else {
            v1 = Float.toString(param);
        }

    label_5:
        return v1;
    }

    private String getTextureEnvTarget(int target) {
        String v0;
        switch(target) {
            case 8960: {
                v0 = "GL_TEXTURE_ENV";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(target);
                break;
            }
        }

        return v0;
    }

    private String getTexturePName(int pname) {
        String v0;
        switch(pname) {
            case 10240: {
                v0 = "GL_TEXTURE_MAG_FILTER";
                break;
            }
            case 10241: {
                v0 = "GL_TEXTURE_MIN_FILTER";
                break;
            }
            case 10242: {
                v0 = "GL_TEXTURE_WRAP_S";
                break;
            }
            case 10243: {
                v0 = "GL_TEXTURE_WRAP_T";
                break;
            }
            case 33169: {
                v0 = "GL_GENERATE_MIPMAP";
                break;
            }
            case 35741: {
                v0 = "GL_TEXTURE_CROP_RECT_OES";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(pname);
                break;
            }
        }

        return v0;
    }

    private String getTextureParamName(float param) {
        String v1;
        int v0 = ((int)param);
        if(param == (((float)v0))) {
            switch(v0) {
                case 9728: {
                    goto label_10;
                }
                case 9729: {
                    goto label_12;
                }
                case 9984: {
                    goto label_14;
                }
                case 9985: {
                    goto label_16;
                }
                case 9986: {
                    goto label_18;
                }
                case 9987: {
                    goto label_20;
                }
                case 10497: {
                    goto label_8;
                }
                case 33071: {
                    goto label_6;
                }
            }

            v1 = GLLogWrapper.getHex(v0);
            goto label_5;
        label_18:
            v1 = "GL_NEAREST_MIPMAP_LINEAR";
            goto label_5;
        label_20:
            v1 = "GL_LINEAR_MIPMAP_LINEAR";
            goto label_5;
        label_6:
            v1 = "GL_CLAMP_TO_EDGE";
            goto label_5;
        label_8:
            v1 = "GL_REPEAT";
            goto label_5;
        label_10:
            v1 = "GL_NEAREST";
            goto label_5;
        label_12:
            v1 = "GL_LINEAR";
            goto label_5;
        label_14:
            v1 = "GL_NEAREST_MIPMAP_NEAREST";
            goto label_5;
        label_16:
            v1 = "GL_LINEAR_MIPMAP_NEAREST";
        }
        else {
            v1 = Float.toString(param);
        }

    label_5:
        return v1;
    }

    private String getTextureTarget(int target) {
        String v0;
        switch(target) {
            case 3553: {
                v0 = "GL_TEXTURE_2D";
                break;
            }
            default: {
                v0 = GLLogWrapper.getHex(target);
                break;
            }
        }

        return v0;
    }

    public void glActiveTexture(int texture) {
        this.begin("glActiveTexture");
        this.arg("texture", texture);
        this.end();
        this.mgl.glActiveTexture(texture);
        this.checkError();
    }

    public void glAlphaFunc(int func, float ref) {
        this.begin("glAlphaFunc");
        this.arg("func", func);
        this.arg("ref", ref);
        this.end();
        this.mgl.glAlphaFunc(func, ref);
        this.checkError();
    }

    public void glAlphaFuncx(int func, int ref) {
        this.begin("glAlphaFuncx");
        this.arg("func", func);
        this.arg("ref", ref);
        this.end();
        this.mgl.glAlphaFuncx(func, ref);
        this.checkError();
    }

    public void glBindBuffer(int target, int buffer) {
        throw new UnsupportedOperationException();
    }

    public void glBindTexture(int target, int texture) {
        this.begin("glBindTexture");
        this.arg("target", this.getTextureTarget(target));
        this.arg("texture", texture);
        this.end();
        this.mgl.glBindTexture(target, texture);
        this.checkError();
    }

    public void glBlendFunc(int sfactor, int dfactor) {
        this.begin("glBlendFunc");
        this.arg("sfactor", this.getFactor(sfactor));
        this.arg("dfactor", this.getFactor(dfactor));
        this.end();
        this.mgl.glBlendFunc(sfactor, dfactor);
        this.checkError();
    }

    public void glBufferData(int target, int size, Buffer data, int usage) {
        throw new UnsupportedOperationException();
    }

    public void glBufferSubData(int target, int offset, int size, Buffer data) {
        throw new UnsupportedOperationException();
    }

    public void glClear(int mask) {
        this.begin("glClear");
        this.arg("mask", this.getClearBufferMask(mask));
        this.end();
        this.mgl.glClear(mask);
        this.checkError();
    }

    public void glClearColor(float red, float green, float blue, float alpha) {
        this.begin("glClearColor");
        this.arg("red", red);
        this.arg("green", green);
        this.arg("blue", blue);
        this.arg("alpha", alpha);
        this.end();
        this.mgl.glClearColor(red, green, blue, alpha);
        this.checkError();
    }

    public void glClearColorx(int red, int green, int blue, int alpha) {
        this.begin("glClearColor");
        this.arg("red", red);
        this.arg("green", green);
        this.arg("blue", blue);
        this.arg("alpha", alpha);
        this.end();
        this.mgl.glClearColorx(red, green, blue, alpha);
        this.checkError();
    }

    public void glClearDepthf(float depth) {
        this.begin("glClearDepthf");
        this.arg("depth", depth);
        this.end();
        this.mgl.glClearDepthf(depth);
        this.checkError();
    }

    public void glClearDepthx(int depth) {
        this.begin("glClearDepthx");
        this.arg("depth", depth);
        this.end();
        this.mgl.glClearDepthx(depth);
        this.checkError();
    }

    public void glClearStencil(int s) {
        this.begin("glClearStencil");
        this.arg("s", s);
        this.end();
        this.mgl.glClearStencil(s);
        this.checkError();
    }

    public void glClientActiveTexture(int texture) {
        this.begin("glClientActiveTexture");
        this.arg("texture", texture);
        this.end();
        this.mgl.glClientActiveTexture(texture);
        this.checkError();
    }

    public void glClipPlanef(int plane, FloatBuffer equation) {
        this.begin("glClipPlanef");
        this.arg("plane", plane);
        this.arg("equation", 4, equation);
        this.end();
        this.mgl11.glClipPlanef(plane, equation);
        this.checkError();
    }

    public void glClipPlanef(int plane, float[] equation, int offset) {
        this.begin("glClipPlanef");
        this.arg("plane", plane);
        this.arg("equation", 4, equation, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11.glClipPlanef(plane, equation, offset);
        this.checkError();
    }

    public void glClipPlanex(int plane, IntBuffer equation) {
        this.begin("glClipPlanef");
        this.arg("plane", plane);
        this.arg("equation", 4, equation);
        this.end();
        this.mgl11.glClipPlanex(plane, equation);
        this.checkError();
    }

    public void glClipPlanex(int plane, int[] equation, int offset) {
        this.begin("glClipPlanex");
        this.arg("plane", plane);
        this.arg("equation", 4, equation, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11.glClipPlanex(plane, equation, offset);
        this.checkError();
    }

    public void glColor4f(float red, float green, float blue, float alpha) {
        this.begin("glColor4f");
        this.arg("red", red);
        this.arg("green", green);
        this.arg("blue", blue);
        this.arg("alpha", alpha);
        this.end();
        this.mgl.glColor4f(red, green, blue, alpha);
        this.checkError();
    }

    public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
        throw new UnsupportedOperationException();
    }

    public void glColor4x(int red, int green, int blue, int alpha) {
        this.begin("glColor4x");
        this.arg("red", red);
        this.arg("green", green);
        this.arg("blue", blue);
        this.arg("alpha", alpha);
        this.end();
        this.mgl.glColor4x(red, green, blue, alpha);
        this.checkError();
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        this.begin("glColorMask");
        this.arg("red", red);
        this.arg("green", green);
        this.arg("blue", blue);
        this.arg("alpha", alpha);
        this.end();
        this.mgl.glColorMask(red, green, blue, alpha);
        this.checkError();
    }

    public void glColorPointer(int size, int type, int stride, Buffer pointer) {
        this.begin("glColorPointer");
        this.argPointer(size, type, stride, pointer);
        this.end();
        this.mColorPointer = new PointerInfo(this, size, type, stride, pointer);
        this.mgl.glColorPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data) {
        this.begin("glCompressedTexImage2D");
        this.arg("target", this.getTextureTarget(target));
        this.arg("level", level);
        this.arg("internalformat", internalformat);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("border", border);
        this.arg("imageSize", imageSize);
        this.arg("data", data.toString());
        this.end();
        this.mgl.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
        this.checkError();
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data) {
        this.begin("glCompressedTexSubImage2D");
        this.arg("target", this.getTextureTarget(target));
        this.arg("level", level);
        this.arg("xoffset", xoffset);
        this.arg("yoffset", yoffset);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("format", format);
        this.arg("imageSize", imageSize);
        this.arg("data", data.toString());
        this.end();
        this.mgl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
        this.checkError();
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
        this.begin("glCopyTexImage2D");
        this.arg("target", this.getTextureTarget(target));
        this.arg("level", level);
        this.arg("internalformat", internalformat);
        this.arg("x", x);
        this.arg("y", y);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("border", border);
        this.end();
        this.mgl.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
        this.checkError();
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        this.begin("glCopyTexSubImage2D");
        this.arg("target", this.getTextureTarget(target));
        this.arg("level", level);
        this.arg("xoffset", xoffset);
        this.arg("yoffset", yoffset);
        this.arg("x", x);
        this.arg("y", y);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
        this.checkError();
    }

    public void glCullFace(int mode) {
        this.begin("glCullFace");
        this.arg("mode", mode);
        this.end();
        this.mgl.glCullFace(mode);
        this.checkError();
    }

    public void glDeleteBuffers(int n, IntBuffer buffers) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteBuffers(int n, int[] buffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glDeleteTextures(int n, IntBuffer textures) {
        this.begin("glDeleteTextures");
        this.arg("n", n);
        this.arg("textures", n, textures);
        this.end();
        this.mgl.glDeleteTextures(n, textures);
        this.checkError();
    }

    public void glDeleteTextures(int n, int[] textures, int offset) {
        this.begin("glDeleteTextures");
        this.arg("n", n);
        this.arg("textures", n, textures, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glDeleteTextures(n, textures, offset);
        this.checkError();
    }

    public void glDepthFunc(int func) {
        this.begin("glDepthFunc");
        this.arg("func", func);
        this.end();
        this.mgl.glDepthFunc(func);
        this.checkError();
    }

    public void glDepthMask(boolean flag) {
        this.begin("glDepthMask");
        this.arg("flag", flag);
        this.end();
        this.mgl.glDepthMask(flag);
        this.checkError();
    }

    public void glDepthRangef(float near, float far) {
        this.begin("glDepthRangef");
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glDepthRangef(near, far);
        this.checkError();
    }

    public void glDepthRangex(int near, int far) {
        this.begin("glDepthRangex");
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glDepthRangex(near, far);
        this.checkError();
    }

    public void glDisable(int cap) {
        this.begin("glDisable");
        this.arg("cap", this.getCap(cap));
        this.end();
        this.mgl.glDisable(cap);
        this.checkError();
    }

    public void glDisableClientState(int array) {
        this.begin("glDisableClientState");
        this.arg("array", this.getClientState(array));
        this.end();
        switch(array) {
            case 32884: {
                this.mVertexArrayEnabled = false;
                break;
            }
            case 32885: {
                this.mNormalArrayEnabled = false;
                break;
            }
            case 32886: {
                this.mColorArrayEnabled = false;
                break;
            }
            case 32888: {
                this.mTextureCoordArrayEnabled = false;
                break;
            }
        }

        this.mgl.glDisableClientState(array);
        this.checkError();
    }

    public void glDrawArrays(int mode, int first, int count) {
        this.begin("glDrawArrays");
        this.arg("mode", mode);
        this.arg("first", first);
        this.arg("count", count);
        this.startLogIndices();
        int v0;
        for(v0 = 0; v0 < count; ++v0) {
            this.doElement(this.mStringBuilder, v0, first + v0);
        }

        this.endLogIndices();
        this.end();
        this.mgl.glDrawArrays(mode, first, count);
        this.checkError();
    }

    public void glDrawElements(int mode, int count, int type, Buffer indices) {
        this.begin("glDrawElements");
        this.arg("mode", this.getBeginMode(mode));
        this.arg("count", count);
        this.arg("type", this.getIndexType(type));
        char[] v1 = this.toCharIndices(count, type, indices);
        int v2 = v1.length;
        this.startLogIndices();
        int v0;
        for(v0 = 0; v0 < v2; ++v0) {
            this.doElement(this.mStringBuilder, v0, v1[v0]);
        }

        this.endLogIndices();
        this.end();
        this.mgl.glDrawElements(mode, count, type, indices);
        this.checkError();
    }

    public void glDrawTexfOES(float x, float y, float z, float width, float height) {
        this.begin("glDrawTexfOES");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl11Ext.glDrawTexfOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexfvOES(FloatBuffer coords) {
        this.begin("glDrawTexfvOES");
        this.arg("coords", 5, coords);
        this.end();
        this.mgl11Ext.glDrawTexfvOES(coords);
        this.checkError();
    }

    public void glDrawTexfvOES(float[] coords, int offset) {
        this.begin("glDrawTexfvOES");
        this.arg("coords", 5, coords, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11Ext.glDrawTexfvOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexiOES(int x, int y, int z, int width, int height) {
        this.begin("glDrawTexiOES");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl11Ext.glDrawTexiOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexivOES(IntBuffer coords) {
        this.begin("glDrawTexivOES");
        this.arg("coords", 5, coords);
        this.end();
        this.mgl11Ext.glDrawTexivOES(coords);
        this.checkError();
    }

    public void glDrawTexivOES(int[] coords, int offset) {
        this.begin("glDrawTexivOES");
        this.arg("coords", 5, coords, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11Ext.glDrawTexivOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexsOES(short x, short y, short z, short width, short height) {
        this.begin("glDrawTexsOES");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl11Ext.glDrawTexsOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexsvOES(ShortBuffer coords) {
        this.begin("glDrawTexsvOES");
        this.arg("coords", 5, coords);
        this.end();
        this.mgl11Ext.glDrawTexsvOES(coords);
        this.checkError();
    }

    public void glDrawTexsvOES(short[] coords, int offset) {
        this.begin("glDrawTexsvOES");
        this.arg("coords", 5, coords, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11Ext.glDrawTexsvOES(coords, offset);
        this.checkError();
    }

    public void glDrawTexxOES(int x, int y, int z, int width, int height) {
        this.begin("glDrawTexxOES");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl11Ext.glDrawTexxOES(x, y, z, width, height);
        this.checkError();
    }

    public void glDrawTexxvOES(IntBuffer coords) {
        this.begin("glDrawTexxvOES");
        this.arg("coords", 5, coords);
        this.end();
        this.mgl11Ext.glDrawTexxvOES(coords);
        this.checkError();
    }

    public void glDrawTexxvOES(int[] coords, int offset) {
        this.begin("glDrawTexxvOES");
        this.arg("coords", 5, coords, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl11Ext.glDrawTexxvOES(coords, offset);
        this.checkError();
    }

    public void glEnable(int cap) {
        this.begin("glEnable");
        this.arg("cap", this.getCap(cap));
        this.end();
        this.mgl.glEnable(cap);
        this.checkError();
    }

    public void glEnableClientState(int array) {
        this.begin("glEnableClientState");
        this.arg("array", this.getClientState(array));
        this.end();
        switch(array) {
            case 32884: {
                this.mVertexArrayEnabled = true;
                break;
            }
            case 32885: {
                this.mNormalArrayEnabled = true;
                break;
            }
            case 32886: {
                this.mColorArrayEnabled = true;
                break;
            }
            case 32888: {
                this.mTextureCoordArrayEnabled = true;
                break;
            }
        }

        this.mgl.glEnableClientState(array);
        this.checkError();
    }

    public void glFinish() {
        this.begin("glFinish");
        this.end();
        this.mgl.glFinish();
        this.checkError();
    }

    public void glFlush() {
        this.begin("glFlush");
        this.end();
        this.mgl.glFlush();
        this.checkError();
    }

    public void glFogf(int pname, float param) {
        this.begin("glFogf");
        this.arg("pname", pname);
        this.arg("param", param);
        this.end();
        this.mgl.glFogf(pname, param);
        this.checkError();
    }

    public void glFogfv(int pname, FloatBuffer params) {
        this.begin("glFogfv");
        this.arg("pname", this.getFogPName(pname));
        this.arg("params", this.getFogParamCount(pname), params);
        this.end();
        this.mgl.glFogfv(pname, params);
        this.checkError();
    }

    public void glFogfv(int pname, float[] params, int offset) {
        this.begin("glFogfv");
        this.arg("pname", this.getFogPName(pname));
        this.arg("params", this.getFogParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glFogfv(pname, params, offset);
        this.checkError();
    }

    public void glFogx(int pname, int param) {
        this.begin("glFogx");
        this.arg("pname", this.getFogPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glFogx(pname, param);
        this.checkError();
    }

    public void glFogxv(int pname, IntBuffer params) {
        this.begin("glFogxv");
        this.arg("pname", this.getFogPName(pname));
        this.arg("params", this.getFogParamCount(pname), params);
        this.end();
        this.mgl.glFogxv(pname, params);
        this.checkError();
    }

    public void glFogxv(int pname, int[] params, int offset) {
        this.begin("glFogxv");
        this.arg("pname", this.getFogPName(pname));
        this.arg("params", this.getFogParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glFogxv(pname, params, offset);
        this.checkError();
    }

    public void glFrontFace(int mode) {
        this.begin("glFrontFace");
        this.arg("mode", mode);
        this.end();
        this.mgl.glFrontFace(mode);
        this.checkError();
    }

    public void glFrustumf(float left, float right, float bottom, float top, float near, float far) {
        this.begin("glFrustumf");
        this.arg("left", left);
        this.arg("right", right);
        this.arg("bottom", bottom);
        this.arg("top", top);
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glFrustumf(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glFrustumx(int left, int right, int bottom, int top, int near, int far) {
        this.begin("glFrustumx");
        this.arg("left", left);
        this.arg("right", right);
        this.arg("bottom", bottom);
        this.arg("top", top);
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glFrustumx(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glGenBuffers(int n, IntBuffer buffers) {
        throw new UnsupportedOperationException();
    }

    public void glGenBuffers(int n, int[] buffers, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glGenTextures(int n, IntBuffer textures) {
        this.begin("glGenTextures");
        this.arg("n", n);
        this.arg("textures", textures.toString());
        this.mgl.glGenTextures(n, textures);
        this.returns(this.toString(n, 0, textures));
        this.checkError();
    }

    public void glGenTextures(int n, int[] textures, int offset) {
        this.begin("glGenTextures");
        this.arg("n", n);
        this.arg("textures", Arrays.toString(textures));
        this.arg("offset", offset);
        this.mgl.glGenTextures(n, textures, offset);
        this.returns(this.toString(n, 0, textures, offset));
        this.checkError();
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

    public int glGetError() {
        this.begin("glGetError");
        int v0 = this.mgl.glGetError();
        this.returns(v0);
        return v0;
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

    public void glGetIntegerv(int pname, IntBuffer params) {
        this.begin("glGetIntegerv");
        this.arg("pname", this.getIntegerStateName(pname));
        this.arg("params", params.toString());
        this.mgl.glGetIntegerv(pname, params);
        this.returns(this.toString(this.getIntegerStateSize(pname), this.getIntegerStateFormat(pname), params));
        this.checkError();
    }

    public void glGetIntegerv(int pname, int[] params, int offset) {
        this.begin("glGetIntegerv");
        this.arg("pname", this.getIntegerStateName(pname));
        this.arg("params", Arrays.toString(params));
        this.arg("offset", offset);
        this.mgl.glGetIntegerv(pname, params, offset);
        this.returns(this.toString(this.getIntegerStateSize(pname), this.getIntegerStateFormat(pname), params, offset));
        this.checkError();
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

    public String glGetString(int name) {
        this.begin("glGetString");
        this.arg("name", name);
        String v0 = this.mgl.glGetString(name);
        this.returns(v0);
        this.checkError();
        return v0;
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

    public void glHint(int target, int mode) {
        this.begin("glHint");
        this.arg("target", this.getHintTarget(target));
        this.arg("mode", this.getHintMode(mode));
        this.end();
        this.mgl.glHint(target, mode);
        this.checkError();
    }

    public boolean glIsBuffer(int buffer) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsEnabled(int cap) {
        throw new UnsupportedOperationException();
    }

    public boolean glIsTexture(int texture) {
        throw new UnsupportedOperationException();
    }

    public void glLightModelf(int pname, float param) {
        this.begin("glLightModelf");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glLightModelf(pname, param);
        this.checkError();
    }

    public void glLightModelfv(int pname, FloatBuffer params) {
        this.begin("glLightModelfv");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("params", this.getLightModelParamCount(pname), params);
        this.end();
        this.mgl.glLightModelfv(pname, params);
        this.checkError();
    }

    public void glLightModelfv(int pname, float[] params, int offset) {
        this.begin("glLightModelfv");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("params", this.getLightModelParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLightModelfv(pname, params, offset);
        this.checkError();
    }

    public void glLightModelx(int pname, int param) {
        this.begin("glLightModelx");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glLightModelx(pname, param);
        this.checkError();
    }

    public void glLightModelxv(int pname, IntBuffer params) {
        this.begin("glLightModelfv");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("params", this.getLightModelParamCount(pname), params);
        this.end();
        this.mgl.glLightModelxv(pname, params);
        this.checkError();
    }

    public void glLightModelxv(int pname, int[] params, int offset) {
        this.begin("glLightModelxv");
        this.arg("pname", this.getLightModelPName(pname));
        this.arg("params", this.getLightModelParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLightModelxv(pname, params, offset);
        this.checkError();
    }

    public void glLightf(int light, int pname, float param) {
        this.begin("glLightf");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glLightf(light, pname, param);
        this.checkError();
    }

    public void glLightfv(int light, int pname, FloatBuffer params) {
        this.begin("glLightfv");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("params", this.getLightParamCount(pname), params);
        this.end();
        this.mgl.glLightfv(light, pname, params);
        this.checkError();
    }

    public void glLightfv(int light, int pname, float[] params, int offset) {
        this.begin("glLightfv");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("params", this.getLightParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLightfv(light, pname, params, offset);
        this.checkError();
    }

    public void glLightx(int light, int pname, int param) {
        this.begin("glLightx");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glLightx(light, pname, param);
        this.checkError();
    }

    public void glLightxv(int light, int pname, IntBuffer params) {
        this.begin("glLightxv");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("params", this.getLightParamCount(pname), params);
        this.end();
        this.mgl.glLightxv(light, pname, params);
        this.checkError();
    }

    public void glLightxv(int light, int pname, int[] params, int offset) {
        this.begin("glLightxv");
        this.arg("light", this.getLightName(light));
        this.arg("pname", this.getLightPName(pname));
        this.arg("params", this.getLightParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLightxv(light, pname, params, offset);
        this.checkError();
    }

    public void glLineWidth(float width) {
        this.begin("glLineWidth");
        this.arg("width", width);
        this.end();
        this.mgl.glLineWidth(width);
        this.checkError();
    }

    public void glLineWidthx(int width) {
        this.begin("glLineWidthx");
        this.arg("width", width);
        this.end();
        this.mgl.glLineWidthx(width);
        this.checkError();
    }

    public void glLoadIdentity() {
        this.begin("glLoadIdentity");
        this.end();
        this.mgl.glLoadIdentity();
        this.checkError();
    }

    public void glLoadMatrixf(FloatBuffer m) {
        this.begin("glLoadMatrixf");
        this.arg("m", 16, m);
        this.end();
        this.mgl.glLoadMatrixf(m);
        this.checkError();
    }

    public void glLoadMatrixf(float[] m, int offset) {
        this.begin("glLoadMatrixf");
        this.arg("m", 16, m, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLoadMatrixf(m, offset);
        this.checkError();
    }

    public void glLoadMatrixx(IntBuffer m) {
        this.begin("glLoadMatrixx");
        this.arg("m", 16, m);
        this.end();
        this.mgl.glLoadMatrixx(m);
        this.checkError();
    }

    public void glLoadMatrixx(int[] m, int offset) {
        this.begin("glLoadMatrixx");
        this.arg("m", 16, m, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glLoadMatrixx(m, offset);
        this.checkError();
    }

    public void glLogicOp(int opcode) {
        this.begin("glLogicOp");
        this.arg("opcode", opcode);
        this.end();
        this.mgl.glLogicOp(opcode);
        this.checkError();
    }

    public void glMaterialf(int face, int pname, float param) {
        this.begin("glMaterialf");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glMaterialf(face, pname, param);
        this.checkError();
    }

    public void glMaterialfv(int face, int pname, FloatBuffer params) {
        this.begin("glMaterialfv");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("params", this.getMaterialParamCount(pname), params);
        this.end();
        this.mgl.glMaterialfv(face, pname, params);
        this.checkError();
    }

    public void glMaterialfv(int face, int pname, float[] params, int offset) {
        this.begin("glMaterialfv");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("params", this.getMaterialParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glMaterialfv(face, pname, params, offset);
        this.checkError();
    }

    public void glMaterialx(int face, int pname, int param) {
        this.begin("glMaterialx");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glMaterialx(face, pname, param);
        this.checkError();
    }

    public void glMaterialxv(int face, int pname, IntBuffer params) {
        this.begin("glMaterialxv");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("params", this.getMaterialParamCount(pname), params);
        this.end();
        this.mgl.glMaterialxv(face, pname, params);
        this.checkError();
    }

    public void glMaterialxv(int face, int pname, int[] params, int offset) {
        this.begin("glMaterialxv");
        this.arg("face", this.getFaceName(face));
        this.arg("pname", this.getMaterialPName(pname));
        this.arg("params", this.getMaterialParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glMaterialxv(face, pname, params, offset);
        this.checkError();
    }

    public void glMatrixMode(int mode) {
        this.begin("glMatrixMode");
        this.arg("mode", this.getMatrixMode(mode));
        this.end();
        this.mgl.glMatrixMode(mode);
        this.checkError();
    }

    public void glMultMatrixf(FloatBuffer m) {
        this.begin("glMultMatrixf");
        this.arg("m", 16, m);
        this.end();
        this.mgl.glMultMatrixf(m);
        this.checkError();
    }

    public void glMultMatrixf(float[] m, int offset) {
        this.begin("glMultMatrixf");
        this.arg("m", 16, m, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glMultMatrixf(m, offset);
        this.checkError();
    }

    public void glMultMatrixx(IntBuffer m) {
        this.begin("glMultMatrixx");
        this.arg("m", 16, m);
        this.end();
        this.mgl.glMultMatrixx(m);
        this.checkError();
    }

    public void glMultMatrixx(int[] m, int offset) {
        this.begin("glMultMatrixx");
        this.arg("m", 16, m, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glMultMatrixx(m, offset);
        this.checkError();
    }

    public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
        this.begin("glMultiTexCoord4f");
        this.arg("target", target);
        this.arg("s", s);
        this.arg("t", t);
        this.arg("r", r);
        this.arg("q", q);
        this.end();
        this.mgl.glMultiTexCoord4f(target, s, t, r, q);
        this.checkError();
    }

    public void glMultiTexCoord4x(int target, int s, int t, int r, int q) {
        this.begin("glMultiTexCoord4x");
        this.arg("target", target);
        this.arg("s", s);
        this.arg("t", t);
        this.arg("r", r);
        this.arg("q", q);
        this.end();
        this.mgl.glMultiTexCoord4x(target, s, t, r, q);
        this.checkError();
    }

    public void glNormal3f(float nx, float ny, float nz) {
        this.begin("glNormal3f");
        this.arg("nx", nx);
        this.arg("ny", ny);
        this.arg("nz", nz);
        this.end();
        this.mgl.glNormal3f(nx, ny, nz);
        this.checkError();
    }

    public void glNormal3x(int nx, int ny, int nz) {
        this.begin("glNormal3x");
        this.arg("nx", nx);
        this.arg("ny", ny);
        this.arg("nz", nz);
        this.end();
        this.mgl.glNormal3x(nx, ny, nz);
        this.checkError();
    }

    public void glNormalPointer(int type, int stride, Buffer pointer) {
        this.begin("glNormalPointer");
        this.arg("type", type);
        this.arg("stride", stride);
        this.arg("pointer", pointer.toString());
        this.end();
        this.mNormalPointer = new PointerInfo(this, 3, type, stride, pointer);
        this.mgl.glNormalPointer(type, stride, pointer);
        this.checkError();
    }

    public void glOrthof(float left, float right, float bottom, float top, float near, float far) {
        this.begin("glOrthof");
        this.arg("left", left);
        this.arg("right", right);
        this.arg("bottom", bottom);
        this.arg("top", top);
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glOrthof(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glOrthox(int left, int right, int bottom, int top, int near, int far) {
        this.begin("glOrthox");
        this.arg("left", left);
        this.arg("right", right);
        this.arg("bottom", bottom);
        this.arg("top", top);
        this.arg("near", near);
        this.arg("far", far);
        this.end();
        this.mgl.glOrthox(left, right, bottom, top, near, far);
        this.checkError();
    }

    public void glPixelStorei(int pname, int param) {
        this.begin("glPixelStorei");
        this.arg("pname", pname);
        this.arg("param", param);
        this.end();
        this.mgl.glPixelStorei(pname, param);
        this.checkError();
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

    public void glPointSize(float size) {
        this.begin("glPointSize");
        this.arg("size", size);
        this.end();
        this.mgl.glPointSize(size);
        this.checkError();
    }

    public void glPointSizePointerOES(int type, int stride, Buffer pointer) {
        throw new UnsupportedOperationException();
    }

    public void glPointSizex(int size) {
        this.begin("glPointSizex");
        this.arg("size", size);
        this.end();
        this.mgl.glPointSizex(size);
        this.checkError();
    }

    public void glPolygonOffset(float factor, float units) {
        this.begin("glPolygonOffset");
        this.arg("factor", factor);
        this.arg("units", units);
        this.end();
        this.mgl.glPolygonOffset(factor, units);
        this.checkError();
    }

    public void glPolygonOffsetx(int factor, int units) {
        this.begin("glPolygonOffsetx");
        this.arg("factor", factor);
        this.arg("units", units);
        this.end();
        this.mgl.glPolygonOffsetx(factor, units);
        this.checkError();
    }

    public void glPopMatrix() {
        this.begin("glPopMatrix");
        this.end();
        this.mgl.glPopMatrix();
        this.checkError();
    }

    public void glPushMatrix() {
        this.begin("glPushMatrix");
        this.end();
        this.mgl.glPushMatrix();
        this.checkError();
    }

    public int glQueryMatrixxOES(IntBuffer mantissa, IntBuffer exponent) {
        this.begin("glQueryMatrixxOES");
        this.arg("mantissa", mantissa.toString());
        this.arg("exponent", exponent.toString());
        this.end();
        int v0 = this.mgl10Ext.glQueryMatrixxOES(mantissa, exponent);
        this.returns(this.toString(16, 2, mantissa));
        this.returns(this.toString(16, 0, exponent));
        this.checkError();
        return v0;
    }

    public int glQueryMatrixxOES(int[] mantissa, int mantissaOffset, int[] exponent, int exponentOffset) {
        this.begin("glQueryMatrixxOES");
        this.arg("mantissa", Arrays.toString(mantissa));
        this.arg("exponent", Arrays.toString(exponent));
        this.end();
        int v0 = this.mgl10Ext.glQueryMatrixxOES(mantissa, mantissaOffset, exponent, exponentOffset);
        this.returns(this.toString(16, 2, mantissa, mantissaOffset));
        this.returns(this.toString(16, 0, exponent, exponentOffset));
        this.checkError();
        return v0;
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        this.begin("glReadPixels");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("format", format);
        this.arg("type", type);
        this.arg("pixels", pixels.toString());
        this.end();
        this.mgl.glReadPixels(x, y, width, height, format, type, pixels);
        this.checkError();
    }

    public void glRotatef(float angle, float x, float y, float z) {
        this.begin("glRotatef");
        this.arg("angle", angle);
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glRotatef(angle, x, y, z);
        this.checkError();
    }

    public void glRotatex(int angle, int x, int y, int z) {
        this.begin("glRotatex");
        this.arg("angle", angle);
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glRotatex(angle, x, y, z);
        this.checkError();
    }

    public void glSampleCoverage(float value, boolean invert) {
        this.begin("glSampleCoveragex");
        this.arg("value", value);
        this.arg("invert", invert);
        this.end();
        this.mgl.glSampleCoverage(value, invert);
        this.checkError();
    }

    public void glSampleCoveragex(int value, boolean invert) {
        this.begin("glSampleCoveragex");
        this.arg("value", value);
        this.arg("invert", invert);
        this.end();
        this.mgl.glSampleCoveragex(value, invert);
        this.checkError();
    }

    public void glScalef(float x, float y, float z) {
        this.begin("glScalef");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glScalef(x, y, z);
        this.checkError();
    }

    public void glScalex(int x, int y, int z) {
        this.begin("glScalex");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glScalex(x, y, z);
        this.checkError();
    }

    public void glScissor(int x, int y, int width, int height) {
        this.begin("glScissor");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl.glScissor(x, y, width, height);
        this.checkError();
    }

    public void glShadeModel(int mode) {
        this.begin("glShadeModel");
        this.arg("mode", this.getShadeModel(mode));
        this.end();
        this.mgl.glShadeModel(mode);
        this.checkError();
    }

    public void glStencilFunc(int func, int ref, int mask) {
        this.begin("glStencilFunc");
        this.arg("func", func);
        this.arg("ref", ref);
        this.arg("mask", mask);
        this.end();
        this.mgl.glStencilFunc(func, ref, mask);
        this.checkError();
    }

    public void glStencilMask(int mask) {
        this.begin("glStencilMask");
        this.arg("mask", mask);
        this.end();
        this.mgl.glStencilMask(mask);
        this.checkError();
    }

    public void glStencilOp(int fail, int zfail, int zpass) {
        this.begin("glStencilOp");
        this.arg("fail", fail);
        this.arg("zfail", zfail);
        this.arg("zpass", zpass);
        this.end();
        this.mgl.glStencilOp(fail, zfail, zpass);
        this.checkError();
    }

    public void glTexCoordPointer(int size, int type, int stride, Buffer pointer) {
        this.begin("glTexCoordPointer");
        this.argPointer(size, type, stride, pointer);
        this.end();
        this.mTexCoordPointer = new PointerInfo(this, size, type, stride, pointer);
        this.mgl.glTexCoordPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glTexEnvf(int target, int pname, float param) {
        this.begin("glTexEnvf");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("param", this.getTextureEnvParamName(param));
        this.end();
        this.mgl.glTexEnvf(target, pname, param);
        this.checkError();
    }

    public void glTexEnvfv(int target, int pname, FloatBuffer params) {
        this.begin("glTexEnvfv");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("params", this.getTextureEnvParamCount(pname), params);
        this.end();
        this.mgl.glTexEnvfv(target, pname, params);
        this.checkError();
    }

    public void glTexEnvfv(int target, int pname, float[] params, int offset) {
        this.begin("glTexEnvfv");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("params", this.getTextureEnvParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glTexEnvfv(target, pname, params, offset);
        this.checkError();
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

    public void glTexEnvx(int target, int pname, int param) {
        this.begin("glTexEnvx");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glTexEnvx(target, pname, param);
        this.checkError();
    }

    public void glTexEnvxv(int target, int pname, IntBuffer params) {
        this.begin("glTexEnvxv");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("params", this.getTextureEnvParamCount(pname), params);
        this.end();
        this.mgl.glTexEnvxv(target, pname, params);
        this.checkError();
    }

    public void glTexEnvxv(int target, int pname, int[] params, int offset) {
        this.begin("glTexEnvxv");
        this.arg("target", this.getTextureEnvTarget(target));
        this.arg("pname", this.getTextureEnvPName(pname));
        this.arg("params", this.getTextureEnvParamCount(pname), params, offset);
        this.arg("offset", offset);
        this.end();
        this.mgl.glTexEnvxv(target, pname, params, offset);
        this.checkError();
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels) {
        this.begin("glTexImage2D");
        this.arg("target", target);
        this.arg("level", level);
        this.arg("internalformat", internalformat);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("border", border);
        this.arg("format", format);
        this.arg("type", type);
        this.arg("pixels", pixels.toString());
        this.end();
        this.mgl.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
        this.checkError();
    }

    public void glTexParameterf(int target, int pname, float param) {
        this.begin("glTexParameterf");
        this.arg("target", this.getTextureTarget(target));
        this.arg("pname", this.getTexturePName(pname));
        this.arg("param", this.getTextureParamName(param));
        this.end();
        this.mgl.glTexParameterf(target, pname, param);
        this.checkError();
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

    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        this.begin("glTexParameteriv");
        this.arg("target", this.getTextureTarget(target));
        this.arg("pname", this.getTexturePName(pname));
        this.arg("params", 4, params);
        this.end();
        this.mgl11.glTexParameteriv(target, pname, params);
        this.checkError();
    }

    public void glTexParameteriv(int target, int pname, int[] params, int offset) {
        this.begin("glTexParameteriv");
        this.arg("target", this.getTextureTarget(target));
        this.arg("pname", this.getTexturePName(pname));
        this.arg("params", 4, params, offset);
        this.end();
        this.mgl11.glTexParameteriv(target, pname, params, offset);
        this.checkError();
    }

    public void glTexParameterx(int target, int pname, int param) {
        this.begin("glTexParameterx");
        this.arg("target", this.getTextureTarget(target));
        this.arg("pname", this.getTexturePName(pname));
        this.arg("param", param);
        this.end();
        this.mgl.glTexParameterx(target, pname, param);
        this.checkError();
    }

    public void glTexParameterxv(int target, int pname, IntBuffer params) {
        throw new UnsupportedOperationException();
    }

    public void glTexParameterxv(int target, int pname, int[] params, int offset) {
        throw new UnsupportedOperationException();
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels) {
        this.begin("glTexSubImage2D");
        this.arg("target", this.getTextureTarget(target));
        this.arg("level", level);
        this.arg("xoffset", xoffset);
        this.arg("yoffset", yoffset);
        this.arg("width", width);
        this.arg("height", height);
        this.arg("format", format);
        this.arg("type", type);
        this.arg("pixels", pixels.toString());
        this.end();
        this.mgl.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
        this.checkError();
    }

    public void glTranslatef(float x, float y, float z) {
        this.begin("glTranslatef");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glTranslatef(x, y, z);
        this.checkError();
    }

    public void glTranslatex(int x, int y, int z) {
        this.begin("glTranslatex");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("z", z);
        this.end();
        this.mgl.glTranslatex(x, y, z);
        this.checkError();
    }

    public void glVertexPointer(int size, int type, int stride, Buffer pointer) {
        this.begin("glVertexPointer");
        this.argPointer(size, type, stride, pointer);
        this.end();
        this.mVertexPointer = new PointerInfo(this, size, type, stride, pointer);
        this.mgl.glVertexPointer(size, type, stride, pointer);
        this.checkError();
    }

    public void glViewport(int x, int y, int width, int height) {
        this.begin("glViewport");
        this.arg("x", x);
        this.arg("y", y);
        this.arg("width", width);
        this.arg("height", height);
        this.end();
        this.mgl.glViewport(x, y, width, height);
        this.checkError();
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
        this.log(") returns " + result + ";\n");
        this.flush();
    }

    private void startLogIndices() {
        this.mStringBuilder = new StringBuilder();
        this.mStringBuilder.append("\n");
        this.bindArrays();
    }

    ByteBuffer toByteBuffer(int byteCount, Buffer input) {
        int v3;
        ByteBuffer v4;
        Buffer v2;
        int v0;
        if(byteCount < 0) {
            v0 = 1;
        }
        else {
            v0 = 0;
        }

        if((input instanceof ByteBuffer)) {
            v2 = input;
            if(v0 != 0) {
                byteCount = ((ByteBuffer)v2).limit();
            }

            v4 = ByteBuffer.allocate(byteCount).order(((ByteBuffer)v2).order());
            v3 = ((ByteBuffer)v2).position();
            int v1;
            for(v1 = 0; v1 < byteCount; ++v1) {
                v4.put(((ByteBuffer)v2).get());
            }

            ((ByteBuffer)v2).position(v3);
        }
        else {
            if(!(input instanceof CharBuffer)) {
                goto label_45;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((CharBuffer)v2).limit() * 2;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((CharBuffer)v2).order());
            CharBuffer v5 = v4.asCharBuffer();
            v3 = ((CharBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 2; ++v1) {
                v5.put(((CharBuffer)v2).get());
            }

            ((CharBuffer)v2).position(v3);
            goto label_21;
        label_45:
            if(!(input instanceof ShortBuffer)) {
                goto label_65;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((ShortBuffer)v2).limit() * 2;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((ShortBuffer)v2).order());
            ShortBuffer v5_1 = v4.asShortBuffer();
            v3 = ((ShortBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 2; ++v1) {
                v5_1.put(((ShortBuffer)v2).get());
            }

            ((ShortBuffer)v2).position(v3);
            goto label_21;
        label_65:
            if(!(input instanceof IntBuffer)) {
                goto label_85;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((IntBuffer)v2).limit() * 4;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((IntBuffer)v2).order());
            IntBuffer v5_2 = v4.asIntBuffer();
            v3 = ((IntBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 4; ++v1) {
                v5_2.put(((IntBuffer)v2).get());
            }

            ((IntBuffer)v2).position(v3);
            goto label_21;
        label_85:
            if(!(input instanceof FloatBuffer)) {
                goto label_105;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((FloatBuffer)v2).limit() * 4;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((FloatBuffer)v2).order());
            FloatBuffer v5_3 = v4.asFloatBuffer();
            v3 = ((FloatBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 4; ++v1) {
                v5_3.put(((FloatBuffer)v2).get());
            }

            ((FloatBuffer)v2).position(v3);
            goto label_21;
        label_105:
            if(!(input instanceof DoubleBuffer)) {
                goto label_125;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((DoubleBuffer)v2).limit() * 8;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((DoubleBuffer)v2).order());
            DoubleBuffer v5_4 = v4.asDoubleBuffer();
            v3 = ((DoubleBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 8; ++v1) {
                v5_4.put(((DoubleBuffer)v2).get());
            }

            ((DoubleBuffer)v2).position(v3);
            goto label_21;
        label_125:
            if(!(input instanceof LongBuffer)) {
                goto label_145;
            }

            v2 = input;
            if(v0 != 0) {
                byteCount = ((LongBuffer)v2).limit() * 8;
            }

            v4 = ByteBuffer.allocate(byteCount).order(((LongBuffer)v2).order());
            LongBuffer v5_5 = v4.asLongBuffer();
            v3 = ((LongBuffer)v2).position();
            for(v1 = 0; v1 < byteCount / 8; ++v1) {
                v5_5.put(((LongBuffer)v2).get());
            }

            ((LongBuffer)v2).position(v3);
        }

    label_21:
        v4.rewind();
        v4.order(ByteOrder.nativeOrder());
        return v4;
    label_145:
        throw new RuntimeException("Unimplemented Buffer subclass.");
    }

    private char[] toCharIndices(int count, int type, Buffer indices) {
        Buffer v2;
        char[] v6 = new char[count];
        switch(type) {
            case 5121: {
                ByteBuffer v1 = this.toByteBuffer(count, indices);
                byte[] v0 = v1.array();
                int v4 = v1.arrayOffset();
                int v3;
                for(v3 = 0; v3 < count; ++v3) {
                    v6[v3] = ((char)(v0[v4 + v3] & 255));
                }
            }
            case 5123: {
                if((indices instanceof CharBuffer)) {
                    v2 = indices;
                }
                else {
                    CharBuffer v2_1 = this.toByteBuffer(count * 2, indices).asCharBuffer();
                }

                int v5 = ((CharBuffer)v2).position();
                ((CharBuffer)v2).position(0);
                ((CharBuffer)v2).get(v6);
                ((CharBuffer)v2).position(v5);
                break;
            }
        }

        return v6;
    }

    private String toString(int n, FloatBuffer buf) {
        StringBuilder v0 = new StringBuilder();
        v0.append("{\n");
        int v1;
        for(v1 = 0; v1 < n; ++v1) {
            v0.append(" [" + v1 + "] = " + buf.get(v1) + '\n');
        }

        v0.append("}");
        return v0.toString();
    }

    private String toString(int n, int format, IntBuffer buf) {
        StringBuilder v0 = new StringBuilder();
        v0.append("{\n");
        int v1;
        for(v1 = 0; v1 < n; ++v1) {
            v0.append(" [" + v1 + "] = ");
            this.formattedAppend(v0, buf.get(v1), format);
            v0.append('\n');
        }

        v0.append("}");
        return v0.toString();
    }

    private String toString(int n, ShortBuffer buf) {
        StringBuilder v0 = new StringBuilder();
        v0.append("{\n");
        int v1;
        for(v1 = 0; v1 < n; ++v1) {
            v0.append(" [" + v1 + "] = " + buf.get(v1) + '\n');
        }

        v0.append("}");
        return v0.toString();
    }

    private String toString(int n, float[] arr, int offset) {
        StringBuilder v1 = new StringBuilder();
        v1.append("{\n");
        int v0 = arr.length;
        int v2;
        for(v2 = 0; v2 < n; ++v2) {
            int v3 = offset + v2;
            v1.append("[" + v3 + "] = ");
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

    private String toString(int n, int format, int[] arr, int offset) {
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
                this.formattedAppend(v1, arr[v3], format);
            }

            v1.append('\n');
        }

        v1.append("}");
        return v1.toString();
    }

    private String toString(int n, short[] arr, int offset) {
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

    private void unbindArrays() {
        if(this.mColorArrayEnabled) {
            this.mColorPointer.unbindByteBuffer();
        }

        if(this.mNormalArrayEnabled) {
            this.mNormalPointer.unbindByteBuffer();
        }

        if(this.mTextureCoordArrayEnabled) {
            this.mTexCoordPointer.unbindByteBuffer();
        }

        if(this.mVertexArrayEnabled) {
            this.mVertexPointer.unbindByteBuffer();
        }
    }
}

