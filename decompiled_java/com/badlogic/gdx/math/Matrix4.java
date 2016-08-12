// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.math;

import java.io.Serializable;

public class Matrix4 implements Serializable {
    public static final int M00 = 0;
    public static final int M01 = 4;
    public static final int M02 = 8;
    public static final int M03 = 12;
    public static final int M10 = 1;
    public static final int M11 = 5;
    public static final int M12 = 9;
    public static final int M13 = 13;
    public static final int M20 = 2;
    public static final int M21 = 6;
    public static final int M22 = 10;
    public static final int M23 = 14;
    public static final int M30 = 3;
    public static final int M31 = 7;
    public static final int M32 = 11;
    public static final int M33 = 15;
    static final Vector3 l_vex = null;
    static final Vector3 l_vey = null;
    static final Vector3 l_vez = null;
    static Quaternion quat = null;
    static final Vector3 right = null;
    private static final long serialVersionUID = -2717655254359579617L;
    public final float[] tmp;
    static final Vector3 tmpForward;
    static final Matrix4 tmpMat;
    static final Vector3 tmpUp;
    static final Vector3 tmpVec;
    public final float[] val;

    static  {
        Matrix4.quat = new Quaternion();
        Matrix4.l_vez = new Vector3();
        Matrix4.l_vex = new Vector3();
        Matrix4.l_vey = new Vector3();
        Matrix4.tmpVec = new Vector3();
        Matrix4.tmpMat = new Matrix4();
        Matrix4.right = new Vector3();
        Matrix4.tmpForward = new Vector3();
        Matrix4.tmpUp = new Vector3();
    }

    public Matrix4() {
        super();
        this.tmp = new float[16];
        this.val = new float[16];
        this.val[0] = 1f;
        this.val[5] = 1f;
        this.val[10] = 1f;
        this.val[15] = 1f;
    }

    public Matrix4(Matrix4 matrix) {
        super();
        this.tmp = new float[16];
        this.val = new float[16];
        this.set(matrix);
    }

    public Matrix4(Quaternion quaternion) {
        super();
        this.tmp = new float[16];
        this.val = new float[16];
        this.set(quaternion);
    }

    public Matrix4(Vector3 position, Quaternion rotation, Vector3 scale) {
        super();
        this.tmp = new float[16];
        this.val = new float[16];
        this.set(position, rotation, scale);
    }

    public Matrix4(float[] values) {
        super();
        this.tmp = new float[16];
        this.val = new float[16];
        this.set(values);
    }

    public Matrix4 cpy() {
        return new Matrix4(this);
    }

    public static native float det(float[] arg0) {
    }

    public float det() {
        return this.val[3] * this.val[6] * this.val[9] * this.val[12] - this.val[2] * this.val[7] * this.val[9] * this.val[12] - this.val[3] * this.val[5] * this.val[10] * this.val[12] + this.val[1] * this.val[7] * this.val[10] * this.val[12] + this.val[2] * this.val[5] * this.val[11] * this.val[12] - this.val[1] * this.val[6] * this.val[11] * this.val[12] - this.val[3] * this.val[6] * this.val[8] * this.val[13] + this.val[2] * this.val[7] * this.val[8] * this.val[13] + this.val[3] * this.val[4] * this.val[10] * this.val[13] - this.val[0] * this.val[7] * this.val[10] * this.val[13] - this.val[2] * this.val[4] * this.val[11] * this.val[13] + this.val[0] * this.val[6] * this.val[11] * this.val[13] + this.val[3] * this.val[5] * this.val[8] * this.val[14] - this.val[1] * this.val[7] * this.val[8] * this.val[14] - this.val[3] * this.val[4] * this.val[9] * this.val[14] + this.val[0] * this.val[7] * this.val[9] * this.val[14] + this.val[1] * this.val[4] * this.val[11] * this.val[14] - this.val[0] * this.val[5] * this.val[11] * this.val[14] - this.val[2] * this.val[5] * this.val[8] * this.val[15] + this.val[1] * this.val[6] * this.val[8] * this.val[15] + this.val[2] * this.val[4] * this.val[9] * this.val[15] - this.val[0] * this.val[6] * this.val[9] * this.val[15] - this.val[1] * this.val[4] * this.val[10] * this.val[15] + this.val[0] * this.val[5] * this.val[10] * this.val[15];
    }

    public Quaternion getRotation(Quaternion rotation) {
        return rotation.setFromMatrix(this);
    }

    public Vector3 getScale(Vector3 scale) {
        scale.x = ((float)Math.sqrt(((double)(this.val[0] * this.val[0] + this.val[4] * this.val[4] + this.val[8] * this.val[8]))));
        scale.y = ((float)Math.sqrt(((double)(this.val[1] * this.val[1] + this.val[5] * this.val[5] + this.val[9] * this.val[9]))));
        scale.z = ((float)Math.sqrt(((double)(this.val[2] * this.val[2] + this.val[6] * this.val[6] + this.val[10] * this.val[10]))));
        return scale;
    }

    public Vector3 getTranslation(Vector3 position) {
        position.x = this.val[12];
        position.y = this.val[13];
        position.z = this.val[14];
        return position;
    }

    public float[] getValues() {
        return this.val;
    }

    public Matrix4 idt() {
        this.val[0] = 1f;
        this.val[4] = 0f;
        this.val[8] = 0f;
        this.val[12] = 0f;
        this.val[1] = 0f;
        this.val[5] = 1f;
        this.val[9] = 0f;
        this.val[13] = 0f;
        this.val[2] = 0f;
        this.val[6] = 0f;
        this.val[10] = 1f;
        this.val[14] = 0f;
        this.val[3] = 0f;
        this.val[7] = 0f;
        this.val[11] = 0f;
        this.val[15] = 1f;
        return this;
    }

    public static native boolean inv(float[] arg0) {
    }

    public Matrix4 inv() {
        int v12 = 4;
        int v11 = 3;
        int v10 = 2;
        float v1 = this.val[v11] * this.val[6] * this.val[9] * this.val[12] - this.val[v10] * this.val[7] * this.val[9] * this.val[12] - this.val[v11] * this.val[5] * this.val[10] * this.val[12] + this.val[1] * this.val[7] * this.val[10] * this.val[12] + this.val[v10] * this.val[5] * this.val[11] * this.val[12] - this.val[1] * this.val[6] * this.val[11] * this.val[12] - this.val[v11] * this.val[6] * this.val[8] * this.val[13] + this.val[v10] * this.val[7] * this.val[8] * this.val[13] + this.val[v11] * this.val[v12] * this.val[10] * this.val[13] - this.val[0] * this.val[7] * this.val[10] * this.val[13] - this.val[v10] * this.val[v12] * this.val[11] * this.val[13] + this.val[0] * this.val[6] * this.val[11] * this.val[13] + this.val[v11] * this.val[5] * this.val[8] * this.val[14] - this.val[1] * this.val[7] * this.val[8] * this.val[14] - this.val[v11] * this.val[v12] * this.val[9] * this.val[14] + this.val[0] * this.val[7] * this.val[9] * this.val[14] + this.val[1] * this.val[v12] * this.val[11] * this.val[14] - this.val[0] * this.val[5] * this.val[11] * this.val[14] - this.val[v10] * this.val[5] * this.val[8] * this.val[15] + this.val[1] * this.val[6] * this.val[8] * this.val[15] + this.val[v10] * this.val[v12] * this.val[9] * this.val[15] - this.val[0] * this.val[6] * this.val[9] * this.val[15] - this.val[1] * this.val[v12] * this.val[10] * this.val[15] + this.val[0] * this.val[5] * this.val[10] * this.val[15];
        if(v1 == 0f) {
            throw new RuntimeException("non-invertible matrix");
        }

        float v0 = 1f / v1;
        this.tmp[0] = this.val[9] * this.val[14] * this.val[7] - this.val[13] * this.val[10] * this.val[7] + this.val[13] * this.val[6] * this.val[11] - this.val[5] * this.val[14] * this.val[11] - this.val[9] * this.val[6] * this.val[15] + this.val[5] * this.val[10] * this.val[15];
        this.tmp[v12] = this.val[12] * this.val[10] * this.val[7] - this.val[8] * this.val[14] * this.val[7] - this.val[12] * this.val[6] * this.val[11] + this.val[v12] * this.val[14] * this.val[11] + this.val[8] * this.val[6] * this.val[15] - this.val[v12] * this.val[10] * this.val[15];
        this.tmp[8] = this.val[8] * this.val[13] * this.val[7] - this.val[12] * this.val[9] * this.val[7] + this.val[12] * this.val[5] * this.val[11] - this.val[v12] * this.val[13] * this.val[11] - this.val[8] * this.val[5] * this.val[15] + this.val[v12] * this.val[9] * this.val[15];
        this.tmp[12] = this.val[12] * this.val[9] * this.val[6] - this.val[8] * this.val[13] * this.val[6] - this.val[12] * this.val[5] * this.val[10] + this.val[v12] * this.val[13] * this.val[10] + this.val[8] * this.val[5] * this.val[14] - this.val[v12] * this.val[9] * this.val[14];
        this.tmp[1] = this.val[13] * this.val[10] * this.val[v11] - this.val[9] * this.val[14] * this.val[v11] - this.val[13] * this.val[v10] * this.val[11] + this.val[1] * this.val[14] * this.val[11] + this.val[9] * this.val[v10] * this.val[15] - this.val[1] * this.val[10] * this.val[15];
        this.tmp[5] = this.val[8] * this.val[14] * this.val[v11] - this.val[12] * this.val[10] * this.val[v11] + this.val[12] * this.val[v10] * this.val[11] - this.val[0] * this.val[14] * this.val[11] - this.val[8] * this.val[v10] * this.val[15] + this.val[0] * this.val[10] * this.val[15];
        this.tmp[9] = this.val[12] * this.val[9] * this.val[v11] - this.val[8] * this.val[13] * this.val[v11] - this.val[12] * this.val[1] * this.val[11] + this.val[0] * this.val[13] * this.val[11] + this.val[8] * this.val[1] * this.val[15] - this.val[0] * this.val[9] * this.val[15];
        this.tmp[13] = this.val[8] * this.val[13] * this.val[v10] - this.val[12] * this.val[9] * this.val[v10] + this.val[12] * this.val[1] * this.val[10] - this.val[0] * this.val[13] * this.val[10] - this.val[8] * this.val[1] * this.val[14] + this.val[0] * this.val[9] * this.val[14];
        this.tmp[v10] = this.val[5] * this.val[14] * this.val[v11] - this.val[13] * this.val[6] * this.val[v11] + this.val[13] * this.val[v10] * this.val[7] - this.val[1] * this.val[14] * this.val[7] - this.val[5] * this.val[v10] * this.val[15] + this.val[1] * this.val[6] * this.val[15];
        this.tmp[6] = this.val[12] * this.val[6] * this.val[v11] - this.val[v12] * this.val[14] * this.val[v11] - this.val[12] * this.val[v10] * this.val[7] + this.val[0] * this.val[14] * this.val[7] + this.val[v12] * this.val[v10] * this.val[15] - this.val[0] * this.val[6] * this.val[15];
        this.tmp[10] = this.val[v12] * this.val[13] * this.val[v11] - this.val[12] * this.val[5] * this.val[v11] + this.val[12] * this.val[1] * this.val[7] - this.val[0] * this.val[13] * this.val[7] - this.val[v12] * this.val[1] * this.val[15] + this.val[0] * this.val[5] * this.val[15];
        this.tmp[14] = this.val[12] * this.val[5] * this.val[v10] - this.val[v12] * this.val[13] * this.val[v10] - this.val[12] * this.val[1] * this.val[6] + this.val[0] * this.val[13] * this.val[6] + this.val[v12] * this.val[1] * this.val[14] - this.val[0] * this.val[5] * this.val[14];
        this.tmp[v11] = this.val[9] * this.val[6] * this.val[v11] - this.val[5] * this.val[10] * this.val[v11] - this.val[9] * this.val[v10] * this.val[7] + this.val[1] * this.val[10] * this.val[7] + this.val[5] * this.val[v10] * this.val[11] - this.val[1] * this.val[6] * this.val[11];
        this.tmp[7] = this.val[v12] * this.val[10] * this.val[v11] - this.val[8] * this.val[6] * this.val[v11] + this.val[8] * this.val[v10] * this.val[7] - this.val[0] * this.val[10] * this.val[7] - this.val[v12] * this.val[v10] * this.val[11] + this.val[0] * this.val[6] * this.val[11];
        this.tmp[11] = this.val[8] * this.val[5] * this.val[v11] - this.val[v12] * this.val[9] * this.val[v11] - this.val[8] * this.val[1] * this.val[7] + this.val[0] * this.val[9] * this.val[7] + this.val[v12] * this.val[1] * this.val[11] - this.val[0] * this.val[5] * this.val[11];
        this.tmp[15] = this.val[v12] * this.val[9] * this.val[v10] - this.val[8] * this.val[5] * this.val[v10] + this.val[8] * this.val[1] * this.val[6] - this.val[0] * this.val[9] * this.val[6] - this.val[v12] * this.val[1] * this.val[10] + this.val[0] * this.val[5] * this.val[10];
        this.val[0] = this.tmp[0] * v0;
        this.val[v12] = this.tmp[v12] * v0;
        this.val[8] = this.tmp[8] * v0;
        this.val[12] = this.tmp[12] * v0;
        this.val[1] = this.tmp[1] * v0;
        this.val[5] = this.tmp[5] * v0;
        this.val[9] = this.tmp[9] * v0;
        this.val[13] = this.tmp[13] * v0;
        this.val[v10] = this.tmp[v10] * v0;
        this.val[6] = this.tmp[6] * v0;
        this.val[10] = this.tmp[10] * v0;
        this.val[14] = this.tmp[14] * v0;
        this.val[v11] = this.tmp[v11] * v0;
        this.val[7] = this.tmp[7] * v0;
        this.val[11] = this.tmp[11] * v0;
        this.val[15] = this.tmp[15] * v0;
        return this;
    }

    public Matrix4 lerp(Matrix4 matrix, float alpha) {
        int v0;
        for(v0 = 0; v0 < 16; ++v0) {
            this.val[v0] = this.val[v0] * (1f - alpha) + matrix.val[v0] * alpha;
        }

        return this;
    }

    public Matrix4 mul(Matrix4 matrix) {
        Matrix4.mul(this.val, matrix.val);
        return this;
    }

    public static native void mul(float[] arg0, float[] arg1) {
    }

    public static native void mulVec(float[] arg0, float[] arg1) {
    }

    public static native void mulVec(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public static native void prj(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public static native void prj(float[] arg0, float[] arg1) {
    }

    public static native void rot(float[] arg0, float[] arg1) {
    }

    public static native void rot(float[] arg0, float[] arg1, int arg2, int arg3, int arg4) {
    }

    public Matrix4 rotate(float axisX, float axisY, float axisZ, float degrees) {
        if(degrees != 0f) {
            Matrix4.quat.setFromAxis(axisX, axisY, axisZ, degrees);
            this = this.rotate(Matrix4.quat);
        }

        return this;
    }

    public Matrix4 rotate(Quaternion rotation) {
        rotation.toMatrix(this.tmp);
        Matrix4.mul(this.val, this.tmp);
        return this;
    }

    public Matrix4 rotate(Vector3 axis, float degrees) {
        if(degrees != 0f) {
            Matrix4.quat.set(axis, degrees);
            this = this.rotate(Matrix4.quat);
        }

        return this;
    }

    public Matrix4 rotate(Vector3 v1, Vector3 v2) {
        return this.rotate(Matrix4.quat.setFromCross(v1, v2));
    }

    public Matrix4 scale(float scaleX, float scaleY, float scaleZ) {
        this.tmp[0] = scaleX;
        this.tmp[4] = 0f;
        this.tmp[8] = 0f;
        this.tmp[12] = 0f;
        this.tmp[1] = 0f;
        this.tmp[5] = scaleY;
        this.tmp[9] = 0f;
        this.tmp[13] = 0f;
        this.tmp[2] = 0f;
        this.tmp[6] = 0f;
        this.tmp[10] = scaleZ;
        this.tmp[14] = 0f;
        this.tmp[3] = 0f;
        this.tmp[7] = 0f;
        this.tmp[11] = 0f;
        this.tmp[15] = 1f;
        Matrix4.mul(this.val, this.tmp);
        return this;
    }

    public Matrix4 scl(float scale) {
        this.val[0] *= scale;
        this.val[5] *= scale;
        this.val[10] *= scale;
        return this;
    }

    public Matrix4 scl(float x, float y, float z) {
        this.val[0] *= x;
        this.val[5] *= y;
        this.val[10] *= z;
        return this;
    }

    public Matrix4 scl(Vector3 scale) {
        this.val[0] *= scale.x;
        this.val[5] *= scale.y;
        this.val[10] *= scale.z;
        return this;
    }

    public Matrix4 set(Matrix4 matrix) {
        return this.set(matrix.val);
    }

    public Matrix4 set(Vector3 position, Quaternion orientation, Vector3 scale) {
        float v4 = orientation.x * 2f;
        float v8 = orientation.y * 2f;
        float v11 = orientation.z * 2f;
        float v1 = orientation.w * v4;
        float v2 = orientation.w * v8;
        float v3 = orientation.w * v11;
        float v5 = orientation.x * v4;
        float v6 = orientation.x * v8;
        float v7 = orientation.x * v11;
        float v9 = orientation.y * v8;
        float v10 = orientation.y * v11;
        float v12 = orientation.z * v11;
        this.val[0] = scale.x * (1f - (v9 + v12));
        this.val[4] = scale.x * (v6 - v3);
        this.val[8] = scale.x * (v7 + v2);
        this.val[12] = position.x;
        this.val[1] = scale.y * (v6 + v3);
        this.val[5] = scale.y * (1f - (v5 + v12));
        this.val[9] = scale.y * (v10 - v1);
        this.val[13] = position.y;
        this.val[2] = scale.z * (v7 - v2);
        this.val[6] = scale.z * (v10 + v1);
        this.val[10] = scale.z * (1f - (v5 + v9));
        this.val[14] = position.z;
        this.val[3] = 0f;
        this.val[7] = 0f;
        this.val[11] = 0f;
        this.val[15] = 1f;
        return this;
    }

    public Matrix4 set(Quaternion quaternion) {
        return this.set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Matrix4 set(float[] values) {
        System.arraycopy(values, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix4 set(float x, float y, float z, float w) {
        float v1 = x * x;
        float v2 = x * y;
        float v3 = x * z;
        float v0 = x * w;
        float v5 = y * y;
        float v6 = y * z;
        float v4 = y * w;
        float v8 = z * z;
        float v7 = z * w;
        this.val[0] = 1f - 2f * (v5 + v8);
        this.val[4] = 2f * (v2 - v7);
        this.val[8] = 2f * (v3 + v4);
        this.val[12] = 0f;
        this.val[1] = 2f * (v2 + v7);
        this.val[5] = 1f - 2f * (v1 + v8);
        this.val[9] = 2f * (v6 - v0);
        this.val[13] = 0f;
        this.val[2] = 2f * (v3 - v4);
        this.val[6] = 2f * (v6 + v0);
        this.val[10] = 1f - 2f * (v1 + v5);
        this.val[14] = 0f;
        this.val[3] = 0f;
        this.val[7] = 0f;
        this.val[11] = 0f;
        this.val[15] = 1f;
        return this;
    }

    public Matrix4 set(Matrix3 mat) {
        this.val[0] = mat.val[0];
        this.val[1] = mat.val[1];
        this.val[2] = mat.val[2];
        this.val[3] = 0f;
        this.val[4] = mat.val[3];
        this.val[5] = mat.val[4];
        this.val[6] = mat.val[5];
        this.val[7] = 0f;
        this.val[8] = 0f;
        this.val[9] = 0f;
        this.val[10] = 1f;
        this.val[11] = 0f;
        this.val[12] = mat.val[6];
        this.val[13] = mat.val[7];
        this.val[14] = 0f;
        this.val[15] = mat.val[8];
        return this;
    }

    public Matrix4 set(Vector3 xAxis, Vector3 yAxis, Vector3 zAxis, Vector3 pos) {
        this.val[0] = xAxis.x;
        this.val[4] = xAxis.y;
        this.val[8] = xAxis.z;
        this.val[1] = yAxis.x;
        this.val[5] = yAxis.y;
        this.val[9] = yAxis.z;
        this.val[2] = -zAxis.x;
        this.val[6] = -zAxis.y;
        this.val[10] = -zAxis.z;
        this.val[12] = pos.x;
        this.val[13] = pos.y;
        this.val[14] = pos.z;
        this.val[3] = 0f;
        this.val[7] = 0f;
        this.val[11] = 0f;
        this.val[15] = 1f;
        return this;
    }

    public Matrix4 setFromEulerAngles(float yaw, float pitch, float roll) {
        Matrix4.quat.setEulerAngles(yaw, pitch, roll);
        return this.set(Matrix4.quat);
    }

    public Matrix4 setToLookAt(Vector3 direction, Vector3 up) {
        Matrix4.l_vez.set(direction).nor();
        Matrix4.l_vex.set(direction).nor();
        Matrix4.l_vex.crs(up).nor();
        Matrix4.l_vey.set(Matrix4.l_vex).crs(Matrix4.l_vez).nor();
        this.idt();
        this.val[0] = Matrix4.l_vex.x;
        this.val[4] = Matrix4.l_vex.y;
        this.val[8] = Matrix4.l_vex.z;
        this.val[1] = Matrix4.l_vey.x;
        this.val[5] = Matrix4.l_vey.y;
        this.val[9] = Matrix4.l_vey.z;
        this.val[2] = -Matrix4.l_vez.x;
        this.val[6] = -Matrix4.l_vez.y;
        this.val[10] = -Matrix4.l_vez.z;
        return this;
    }

    public Matrix4 setToLookAt(Vector3 position, Vector3 target, Vector3 up) {
        Matrix4.tmpVec.set(target).sub(position);
        this.setToLookAt(Matrix4.tmpVec, up);
        this.mul(Matrix4.tmpMat.setToTranslation(position.tmp().scl(-1f)));
        return this;
    }

    public Matrix4 setToOrtho(float left, float right, float bottom, float top, float near, float far) {
        this.idt();
        this.val[0] = 2f / (right - left);
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = 0f;
        this.val[5] = 2f / (top - bottom);
        this.val[6] = 0f;
        this.val[7] = 0f;
        this.val[8] = 0f;
        this.val[9] = 0f;
        this.val[10] = -2f / (far - near);
        this.val[11] = 0f;
        this.val[12] = -(right + left) / (right - left);
        this.val[13] = -(top + bottom) / (top - bottom);
        this.val[14] = -(far + near) / (far - near);
        this.val[15] = 1f;
        return this;
    }

    public Matrix4 setToOrtho2D(float x, float y, float width, float height) {
        this.setToOrtho(x, x + width, y, y + height, 0f, 1f);
        return this;
    }

    public Matrix4 setToOrtho2D(float x, float y, float width, float height, float near, float far) {
        this.setToOrtho(x, x + width, y, y + height, near, far);
        return this;
    }

    public Matrix4 setToProjection(float near, float far, float fov, float aspectRatio) {
        this.idt();
        float v2 = ((float)(1 / Math.tan((((double)fov)) * 0.017453 / 2)));
        this.val[0] = v2 / aspectRatio;
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = 0f;
        this.val[5] = v2;
        this.val[6] = 0f;
        this.val[7] = 0f;
        this.val[8] = 0f;
        this.val[9] = 0f;
        this.val[10] = (far + near) / (near - far);
        this.val[11] = -1f;
        this.val[12] = 0f;
        this.val[13] = 0f;
        this.val[14] = 2f * far * near / (near - far);
        this.val[15] = 0f;
        return this;
    }

    public Matrix4 setToRotation(float axisX, float axisY, float axisZ, float degrees) {
        if(degrees == 0f) {
            this.idt();
        }
        else {
            this = this.set(Matrix4.quat.setFromAxis(axisX, axisY, axisZ, degrees));
        }

        return this;
    }

    public Matrix4 setToRotation(float x1, float y1, float z1, float x2, float y2, float z2) {
        return this.set(Matrix4.quat.setFromCross(x1, y1, z1, x2, y2, z2));
    }

    public Matrix4 setToRotation(Vector3 axis, float degrees) {
        if(degrees == 0f) {
            this.idt();
        }
        else {
            this = this.set(Matrix4.quat.set(axis, degrees));
        }

        return this;
    }

    public Matrix4 setToRotation(Vector3 v1, Vector3 v2) {
        return this.set(Matrix4.quat.setFromCross(v1, v2));
    }

    public Matrix4 setToScaling(float x, float y, float z) {
        this.idt();
        this.val[0] = x;
        this.val[5] = y;
        this.val[10] = z;
        return this;
    }

    public Matrix4 setToScaling(Vector3 vector) {
        this.idt();
        this.val[0] = vector.x;
        this.val[5] = vector.y;
        this.val[10] = vector.z;
        return this;
    }

    public Matrix4 setToTranslation(Vector3 vector) {
        this.idt();
        this.val[12] = vector.x;
        this.val[13] = vector.y;
        this.val[14] = vector.z;
        return this;
    }

    public Matrix4 setToTranslation(float x, float y, float z) {
        this.idt();
        this.val[12] = x;
        this.val[13] = y;
        this.val[14] = z;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(float translationX, float translationY, float translationZ, float scalingX, float scalingY, float scalingZ) {
        this.idt();
        this.val[12] = translationX;
        this.val[13] = translationY;
        this.val[14] = translationZ;
        this.val[0] = scalingX;
        this.val[5] = scalingY;
        this.val[10] = scalingZ;
        return this;
    }

    public Matrix4 setToTranslationAndScaling(Vector3 translation, Vector3 scaling) {
        this.idt();
        this.val[12] = translation.x;
        this.val[13] = translation.y;
        this.val[14] = translation.z;
        this.val[0] = scaling.x;
        this.val[5] = scaling.y;
        this.val[10] = scaling.z;
        return this;
    }

    public Matrix4 setToWorld(Vector3 position, Vector3 forward, Vector3 up) {
        Matrix4.tmpForward.set(forward).nor();
        Matrix4.right.set(Matrix4.tmpForward).crs(up).nor();
        Matrix4.tmpUp.set(Matrix4.right).crs(Matrix4.tmpForward).nor();
        this.set(Matrix4.right, Matrix4.tmpUp, Matrix4.tmpForward, position);
        return this;
    }

    public Matrix4 setTranslation(float x, float y, float z) {
        this.val[12] = x;
        this.val[13] = y;
        this.val[14] = z;
        return this;
    }

    public Matrix4 setTranslation(Vector3 vector) {
        this.val[12] = vector.x;
        this.val[13] = vector.y;
        this.val[14] = vector.z;
        return this;
    }

    public Matrix4 toNormalMatrix() {
        this.val[12] = 0f;
        this.val[13] = 0f;
        this.val[14] = 0f;
        return this.inv().tra();
    }

    public String toString() {
        return "[" + this.val[0] + "|" + this.val[4] + "|" + this.val[8] + "|" + this.val[12] + "]\n" + "[" + this.val[1] + "|" + this.val[5] + "|" + this.val[9] + "|" + this.val[13] + "]\n" + "[" + this.val[2] + "|" + this.val[6] + "|" + this.val[10] + "|" + this.val[14] + "]\n" + "[" + this.val[3] + "|" + this.val[7] + "|" + this.val[11] + "|" + this.val[15] + "]\n";
    }

    public Matrix4 tra() {
        this.tmp[0] = this.val[0];
        this.tmp[4] = this.val[1];
        this.tmp[8] = this.val[2];
        this.tmp[12] = this.val[3];
        this.tmp[1] = this.val[4];
        this.tmp[5] = this.val[5];
        this.tmp[9] = this.val[6];
        this.tmp[13] = this.val[7];
        this.tmp[2] = this.val[8];
        this.tmp[6] = this.val[9];
        this.tmp[10] = this.val[10];
        this.tmp[14] = this.val[11];
        this.tmp[3] = this.val[12];
        this.tmp[7] = this.val[13];
        this.tmp[11] = this.val[14];
        this.tmp[15] = this.val[15];
        return this.set(this.tmp);
    }

    public Matrix4 translate(float x, float y, float z) {
        this.tmp[0] = 1f;
        this.tmp[4] = 0f;
        this.tmp[8] = 0f;
        this.tmp[12] = x;
        this.tmp[1] = 0f;
        this.tmp[5] = 1f;
        this.tmp[9] = 0f;
        this.tmp[13] = y;
        this.tmp[2] = 0f;
        this.tmp[6] = 0f;
        this.tmp[10] = 1f;
        this.tmp[14] = z;
        this.tmp[3] = 0f;
        this.tmp[7] = 0f;
        this.tmp[11] = 0f;
        this.tmp[15] = 1f;
        Matrix4.mul(this.val, this.tmp);
        return this;
    }

    public Matrix4 translate(Vector3 translation) {
        return this.translate(translation.x, translation.y, translation.z);
    }

    public Matrix4 trn(float x, float y, float z) {
        this.val[12] += x;
        this.val[13] += y;
        this.val[14] += z;
        return this;
    }

    public Matrix4 trn(Vector3 vector) {
        this.val[12] += vector.x;
        this.val[13] += vector.y;
        this.val[14] += vector.z;
        return this;
    }
}

