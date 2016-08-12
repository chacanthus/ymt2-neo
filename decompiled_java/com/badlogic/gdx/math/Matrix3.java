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

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.Serializable;

public class Matrix3 implements Serializable {
    private static final float DEGREE_TO_RAD = 0.017453f;
    public static final int M00 = 0;
    public static final int M01 = 3;
    public static final int M02 = 6;
    public static final int M10 = 1;
    public static final int M11 = 4;
    public static final int M12 = 7;
    public static final int M20 = 2;
    public static final int M21 = 5;
    public static final int M22 = 8;
    private static final long serialVersionUID = 7907569533774959788L;
    private float[] tmp;
    public float[] val;

    public Matrix3() {
        super();
        this.val = new float[9];
        this.tmp = new float[9];
        this.idt();
    }

    public Matrix3(Matrix3 matrix) {
        super();
        this.val = new float[9];
        this.tmp = new float[9];
        this.set(matrix);
    }

    public Matrix3(float[] values) {
        super();
        this.val = new float[9];
        this.tmp = new float[9];
        this.set(values);
    }

    public float det() {
        return this.val[0] * this.val[4] * this.val[8] + this.val[3] * this.val[7] * this.val[2] + this.val[6] * this.val[1] * this.val[5] - this.val[0] * this.val[7] * this.val[5] - this.val[3] * this.val[1] * this.val[8] - this.val[6] * this.val[4] * this.val[2];
    }

    public float getRotation() {
        return 57.295776f * (((float)Math.atan2(((double)this.val[1]), ((double)this.val[0]))));
    }

    public Vector2 getScale(Vector2 scale) {
        scale.x = ((float)Math.sqrt(((double)(this.val[0] * this.val[0] + this.val[3] * this.val[3]))));
        scale.y = ((float)Math.sqrt(((double)(this.val[1] * this.val[1] + this.val[4] * this.val[4]))));
        return scale;
    }

    public Vector2 getTranslation(Vector2 position) {
        position.x = this.val[6];
        position.y = this.val[7];
        return position;
    }

    public float[] getValues() {
        return this.val;
    }

    public Matrix3 idt() {
        this.val[0] = 1f;
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = 1f;
        this.val[5] = 0f;
        this.val[6] = 0f;
        this.val[7] = 0f;
        this.val[8] = 1f;
        return this;
    }

    public Matrix3 inv() {
        int v12 = 4;
        int v11 = 3;
        int v10 = 2;
        float v0 = this.det();
        if(v0 == 0f) {
            throw new GdxRuntimeException("Can\'t invert a singular matrix");
        }

        float v1 = 1f / v0;
        this.tmp[0] = this.val[v12] * this.val[8] - this.val[5] * this.val[7];
        this.tmp[1] = this.val[v10] * this.val[7] - this.val[1] * this.val[8];
        this.tmp[v10] = this.val[1] * this.val[5] - this.val[v10] * this.val[v12];
        this.tmp[v11] = this.val[5] * this.val[6] - this.val[v11] * this.val[8];
        this.tmp[v12] = this.val[0] * this.val[8] - this.val[v10] * this.val[6];
        this.tmp[5] = this.val[v10] * this.val[v11] - this.val[0] * this.val[5];
        this.tmp[6] = this.val[v11] * this.val[7] - this.val[v12] * this.val[6];
        this.tmp[7] = this.val[1] * this.val[6] - this.val[0] * this.val[7];
        this.tmp[8] = this.val[0] * this.val[v12] - this.val[1] * this.val[v11];
        this.val[0] = this.tmp[0] * v1;
        this.val[1] = this.tmp[1] * v1;
        this.val[v10] = this.tmp[v10] * v1;
        this.val[v11] = this.tmp[v11] * v1;
        this.val[v12] = this.tmp[v12] * v1;
        this.val[5] = this.tmp[5] * v1;
        this.val[6] = this.tmp[6] * v1;
        this.val[7] = this.tmp[7] * v1;
        this.val[8] = this.tmp[8] * v1;
        return this;
    }

    private static void mul(float[] mata, float[] matb) {
        float v0 = mata[0] * matb[0] + mata[3] * matb[1] + mata[6] * matb[2];
        float v1 = mata[0] * matb[3] + mata[3] * matb[4] + mata[6] * matb[5];
        float v2 = mata[0] * matb[6] + mata[3] * matb[7] + mata[6] * matb[8];
        float v3 = mata[1] * matb[0] + mata[4] * matb[1] + mata[7] * matb[2];
        float v4 = mata[1] * matb[3] + mata[4] * matb[4] + mata[7] * matb[5];
        float v5 = mata[1] * matb[6] + mata[4] * matb[7] + mata[7] * matb[8];
        float v6 = mata[2] * matb[0] + mata[5] * matb[1] + mata[8] * matb[2];
        float v7 = mata[2] * matb[3] + mata[5] * matb[4] + mata[8] * matb[5];
        float v8 = mata[2] * matb[6] + mata[5] * matb[7] + mata[8] * matb[8];
        mata[0] = v0;
        mata[1] = v3;
        mata[2] = v6;
        mata[3] = v1;
        mata[4] = v4;
        mata[5] = v7;
        mata[6] = v2;
        mata[7] = v5;
        mata[8] = v8;
    }

    public Matrix3 mul(Matrix3 m) {
        float v0 = this.val[0] * m.val[0] + this.val[3] * m.val[1] + this.val[6] * m.val[2];
        float v1 = this.val[0] * m.val[3] + this.val[3] * m.val[4] + this.val[6] * m.val[5];
        float v2 = this.val[0] * m.val[6] + this.val[3] * m.val[7] + this.val[6] * m.val[8];
        float v3 = this.val[1] * m.val[0] + this.val[4] * m.val[1] + this.val[7] * m.val[2];
        float v4 = this.val[1] * m.val[3] + this.val[4] * m.val[4] + this.val[7] * m.val[5];
        float v5 = this.val[1] * m.val[6] + this.val[4] * m.val[7] + this.val[7] * m.val[8];
        float v6 = this.val[2] * m.val[0] + this.val[5] * m.val[1] + this.val[8] * m.val[2];
        float v7 = this.val[2] * m.val[3] + this.val[5] * m.val[4] + this.val[8] * m.val[5];
        float v8 = this.val[2] * m.val[6] + this.val[5] * m.val[7] + this.val[8] * m.val[8];
        this.val[0] = v0;
        this.val[1] = v3;
        this.val[2] = v6;
        this.val[3] = v1;
        this.val[4] = v4;
        this.val[5] = v7;
        this.val[6] = v2;
        this.val[7] = v5;
        this.val[8] = v8;
        return this;
    }

    public Matrix3 rotate(float degrees) {
        if(degrees != 0f) {
            degrees *= 0.017453f;
            float v0 = ((float)Math.cos(((double)degrees)));
            float v1 = ((float)Math.sin(((double)degrees)));
            this.tmp[0] = v0;
            this.tmp[1] = v1;
            this.tmp[2] = 0f;
            this.tmp[3] = -v1;
            this.tmp[4] = v0;
            this.tmp[5] = 0f;
            this.tmp[6] = 0f;
            this.tmp[7] = 0f;
            this.tmp[8] = 1f;
            Matrix3.mul(this.val, this.tmp);
        }

        return this;
    }

    public Matrix3 scale(float scaleX, float scaleY) {
        this.tmp[0] = scaleX;
        this.tmp[1] = 0f;
        this.tmp[2] = 0f;
        this.tmp[3] = 0f;
        this.tmp[4] = scaleY;
        this.tmp[5] = 0f;
        this.tmp[6] = 0f;
        this.tmp[7] = 0f;
        this.tmp[8] = 1f;
        Matrix3.mul(this.val, this.tmp);
        return this;
    }

    public Matrix3 scale(Vector2 scale) {
        this.tmp[0] = scale.x;
        this.tmp[1] = 0f;
        this.tmp[2] = 0f;
        this.tmp[3] = 0f;
        this.tmp[4] = scale.y;
        this.tmp[5] = 0f;
        this.tmp[6] = 0f;
        this.tmp[7] = 0f;
        this.tmp[8] = 1f;
        Matrix3.mul(this.val, this.tmp);
        return this;
    }

    public Matrix3 scl(float scale) {
        this.val[0] *= scale;
        this.val[4] *= scale;
        return this;
    }

    public Matrix3 scl(Vector2 scale) {
        this.val[0] *= scale.x;
        this.val[4] *= scale.y;
        return this;
    }

    public Matrix3 scl(Vector3 scale) {
        this.val[0] *= scale.x;
        this.val[4] *= scale.y;
        return this;
    }

    public Matrix3 set(Matrix4 mat) {
        this.val[0] = mat.val[0];
        this.val[1] = mat.val[1];
        this.val[2] = mat.val[2];
        this.val[3] = mat.val[4];
        this.val[4] = mat.val[5];
        this.val[5] = mat.val[6];
        this.val[6] = mat.val[8];
        this.val[7] = mat.val[9];
        this.val[8] = mat.val[10];
        return this;
    }

    public Matrix3 set(Matrix3 mat) {
        System.arraycopy(mat.val, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 set(float[] values) {
        System.arraycopy(values, 0, this.val, 0, this.val.length);
        return this;
    }

    public Matrix3 setToRotation(float degrees) {
        float v0 = 0.017453f * degrees;
        float v1 = ((float)Math.cos(((double)v0)));
        float v2 = ((float)Math.sin(((double)v0)));
        this.val[0] = v1;
        this.val[1] = v2;
        this.val[2] = 0f;
        this.val[3] = -v2;
        this.val[4] = v1;
        this.val[5] = 0f;
        this.val[6] = 0f;
        this.val[7] = 0f;
        this.val[8] = 1f;
        return this;
    }

    public Matrix3 setToScaling(float scaleX, float scaleY) {
        this.val[0] = scaleX;
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = scaleY;
        this.val[5] = 0f;
        this.val[6] = 0f;
        this.val[7] = 0f;
        this.val[8] = 1f;
        return this;
    }

    public Matrix3 setToTranslation(float x, float y) {
        this.val[0] = 1f;
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = 1f;
        this.val[5] = 0f;
        this.val[6] = x;
        this.val[7] = y;
        this.val[8] = 1f;
        return this;
    }

    public Matrix3 setToTranslation(Vector2 translation) {
        this.val[0] = 1f;
        this.val[1] = 0f;
        this.val[2] = 0f;
        this.val[3] = 0f;
        this.val[4] = 1f;
        this.val[5] = 0f;
        this.val[6] = translation.x;
        this.val[7] = translation.y;
        this.val[8] = 1f;
        return this;
    }

    public String toString() {
        return "[" + this.val[0] + "|" + this.val[3] + "|" + this.val[6] + "]\n" + "[" + this.val[1] + "|" + this.val[4] + "|" + this.val[7] + "]\n" + "[" + this.val[2] + "|" + this.val[5] + "|" + this.val[8] + "]";
    }

    public Matrix3 translate(float x, float y) {
        this.tmp[0] = 1f;
        this.tmp[1] = 0f;
        this.tmp[2] = 0f;
        this.tmp[3] = 0f;
        this.tmp[4] = 1f;
        this.tmp[5] = 0f;
        this.tmp[6] = x;
        this.tmp[7] = y;
        this.tmp[8] = 1f;
        Matrix3.mul(this.val, this.tmp);
        return this;
    }

    public Matrix3 translate(Vector2 translation) {
        this.tmp[0] = 1f;
        this.tmp[1] = 0f;
        this.tmp[2] = 0f;
        this.tmp[3] = 0f;
        this.tmp[4] = 1f;
        this.tmp[5] = 0f;
        this.tmp[6] = translation.x;
        this.tmp[7] = translation.y;
        this.tmp[8] = 1f;
        Matrix3.mul(this.val, this.tmp);
        return this;
    }

    public Matrix3 transpose() {
        float v0 = this.val[1];
        float v1 = this.val[2];
        float v2 = this.val[3];
        float v3 = this.val[5];
        float v4 = this.val[6];
        float v5 = this.val[7];
        this.val[3] = v0;
        this.val[6] = v1;
        this.val[1] = v2;
        this.val[7] = v3;
        this.val[2] = v4;
        this.val[5] = v5;
        return this;
    }

    public Matrix3 trn(float x, float y) {
        this.val[6] += x;
        this.val[7] += y;
        return this;
    }

    public Matrix3 trn(Vector2 vector) {
        this.val[6] += vector.x;
        this.val[7] += vector.y;
        return this;
    }

    public Matrix3 trn(Vector3 vector) {
        this.val[6] += vector.x;
        this.val[7] += vector.y;
        return this;
    }
}

