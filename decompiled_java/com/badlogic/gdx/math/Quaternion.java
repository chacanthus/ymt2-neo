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

public class Quaternion implements Serializable {
    private static final float NORMALIZATION_TOLERANCE = 0.00001f;
    private static final long serialVersionUID = -7661875440774897168L;
    private static Quaternion tmp1;
    private static Quaternion tmp2;
    public float w;
    public float x;
    public float y;
    public float z;

    static  {
        Quaternion.tmp1 = new Quaternion(0f, 0f, 0f, 0f);
        Quaternion.tmp2 = new Quaternion(0f, 0f, 0f, 0f);
    }

    public Quaternion(float x, float y, float z, float w) {
        super();
        this.set(x, y, z, w);
    }

    public Quaternion() {
        super();
        this.idt();
    }

    public Quaternion(Quaternion quaternion) {
        super();
        this.set(quaternion);
    }

    public Quaternion(Vector3 axis, float angle) {
        super();
        this.set(axis, angle);
    }

    public Quaternion conjugate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        return this;
    }

    public Quaternion cpy() {
        return new Quaternion(this);
    }

    public float dot(Quaternion other) {
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    public boolean equals(Object o) {
        boolean v1 = true;
        if(this != (((Quaternion)o))) {
            if(!(o instanceof Quaternion)) {
                v1 = false;
            }
            else {
                Object v0 = o;
                if(this.x == ((Quaternion)v0).x && this.y == ((Quaternion)v0).y && this.z == ((Quaternion)v0).z && this.w == ((Quaternion)v0).w) {
                    goto label_3;
                }

                v1 = false;
            }
        }

    label_3:
        return v1;
    }

    public float getAxisAngle(Vector3 axis) {
        float v7 = 1f;
        if(this.w > v7) {
            this.nor();
        }

        float v0 = ((float)(2 * Math.acos(((double)this.w))));
        double v1 = Math.sqrt(((double)(v7 - this.w * this.w)));
        if(v1 < 0.00001) {
            axis.x = this.x;
            axis.y = this.y;
            axis.z = this.z;
        }
        else {
            axis.x = ((float)((((double)this.x)) / v1));
            axis.y = ((float)((((double)this.y)) / v1));
            axis.z = ((float)((((double)this.z)) / v1));
        }

        return 57.295776f * v0;
    }

    public Quaternion idt() {
        return this.set(0f, 0f, 0f, 1f);
    }

    public float len() {
        return ((float)Math.sqrt(((double)(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w))));
    }

    public float len2() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public Quaternion mul(Quaternion q) {
        float v1 = this.w * q.x + this.x * q.w + this.y * q.z - this.z * q.y;
        float v2 = this.w * q.y + this.y * q.w + this.z * q.x - this.x * q.z;
        float v3 = this.w * q.z + this.z * q.w + this.x * q.y - this.y * q.x;
        float v0 = this.w * q.w - this.x * q.x - this.y * q.y - this.z * q.z;
        this.x = v1;
        this.y = v2;
        this.z = v3;
        this.w = v0;
        return this;
    }

    public Quaternion mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
        return this;
    }

    public Quaternion mulLeft(Quaternion q) {
        float v1 = q.w * this.x + q.x * this.w + q.y * this.z - q.z * this.y;
        float v2 = q.w * this.y + q.y * this.w + q.z * this.x - q.x * this.z;
        float v3 = q.w * this.z + q.z * this.w + q.x * this.y - q.y * this.x;
        float v0 = q.w * this.w - q.x * this.x - q.y * this.y - q.z * this.z;
        this.x = v1;
        this.y = v2;
        this.z = v3;
        this.w = v0;
        return this;
    }

    public Quaternion nor() {
        float v0 = this.len2();
        if(v0 != 0f && Math.abs(v0 - 1f) > 0.00001f) {
            v0 = ((float)Math.sqrt(((double)v0)));
            this.w /= v0;
            this.x /= v0;
            this.y /= v0;
            this.z /= v0;
        }

        return this;
    }

    public Quaternion set(Vector3 axis, float angle) {
        return this.setFromAxis(axis.x, axis.y, axis.z, angle);
    }

    public Quaternion set(Quaternion quaternion) {
        return this.set(quaternion.x, quaternion.y, quaternion.z, quaternion.w);
    }

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion setEulerAngles(float yaw, float pitch, float roll) {
        yaw = ((float)Math.toRadians(((double)yaw)));
        pitch = ((float)Math.toRadians(((double)pitch)));
        float v13 = (((float)Math.toRadians(((double)roll)))) * 0.5f;
        float v10 = ((float)Math.sin(((double)v13)));
        float v9 = ((float)Math.cos(((double)v13)));
        float v12 = pitch * 0.5f;
        float v8 = ((float)Math.sin(((double)v12)));
        float v7 = ((float)Math.cos(((double)v12)));
        float v11 = yaw * 0.5f;
        float v6 = ((float)Math.sin(((double)v11)));
        float v5 = ((float)Math.cos(((double)v11)));
        float v1 = v5 * v8;
        float v2 = v6 * v7;
        float v3 = v5 * v7;
        float v4 = v6 * v8;
        this.x = v1 * v9 + v2 * v10;
        this.y = v2 * v9 - v1 * v10;
        this.z = v3 * v10 - v4 * v9;
        this.w = v3 * v9 + v4 * v10;
        return this;
    }

    public Quaternion setFromAxes(float xx, float xy, float xz, float yx, float yy, float yz, float zx, float zy, float zz) {
        double v23;
        double v21;
        double v19;
        double v17;
        double v14;
        float v5 = xx;
        float v6 = xy;
        float v7 = xz;
        float v8 = yx;
        float v9 = yy;
        float v10 = yz;
        float v11 = zx;
        float v12 = zy;
        float v13 = zz;
        float v16 = v5 + v9 + v13;
        if(v16 >= 0f) {
            v14 = Math.sqrt(((double)(1f + v16)));
            v17 = 0.5 * v14;
            v14 /= 0.5;
            v19 = (((double)(v12 - v10))) * v14;
            v21 = (((double)(v7 - v11))) * v14;
            v23 = (((double)(v8 - v6))) * v14;
        }
        else {
            if(v5 > v9 && v5 > v13) {
                v14 = Math.sqrt(1 + (((double)v5)) - (((double)v9)) - (((double)v13)));
                v19 = v14 * 0.5;
                v14 /= 0.5;
                v21 = (((double)(v8 + v6))) * v14;
                v23 = (((double)(v7 + v11))) * v14;
                v17 = (((double)(v12 - v10))) * v14;
                goto label_38;
            }

            if(v9 <= v13) {
                goto label_122;
            }

            v14 = Math.sqrt(1 + (((double)v9)) - (((double)v5)) - (((double)v13)));
            v21 = v14 * 0.5;
            v14 /= 0.5;
            v19 = (((double)(v8 + v6))) * v14;
            v23 = (((double)(v12 + v10))) * v14;
            v17 = (((double)(v7 - v11))) * v14;
            goto label_38;
        label_122:
            v14 = Math.sqrt(1 + (((double)v13)) - (((double)v5)) - (((double)v9)));
            v23 = v14 * 0.5;
            v14 /= 0.5;
            v19 = (((double)(v7 + v11))) * v14;
            v21 = (((double)(v12 + v10))) * v14;
            v17 = (((double)(v8 - v6))) * v14;
        }

    label_38:
        return this.set(((float)v19), ((float)v21), ((float)v23), ((float)v17));
    }

    public Quaternion setFromAxis(float x, float y, float z, float angle) {
        Quaternion v4;
        float v6 = 2f;
        float v0 = Vector3.len(x, y, z);
        if(v0 == 0f) {
            v4 = this.idt();
        }
        else {
            v0 /= 1f;
            float v1 = angle * 0.017453f;
            float v3 = ((float)Math.sin(((double)(v1 / v6))));
            v4 = this.set(v0 * x * v3, v0 * y * v3, v0 * z * v3, ((float)Math.cos(((double)(v1 / v6))))).nor();
        }

        return v4;
    }

    public Quaternion setFromAxis(Vector3 axis, float angle) {
        return this.setFromAxis(axis.x, axis.y, axis.z, angle);
    }

    public Quaternion setFromCross(Vector3 v1, Vector3 v2) {
        return this.setFromAxis(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x, (((float)Math.acos(((double)MathUtils.clamp(v1.dot(v2), -1f, 1f))))) * 57.295776f);
    }

    public Quaternion setFromCross(float x1, float y1, float z1, float x2, float y2, float z2) {
        return this.setFromAxis(y1 * z2 - z1 * y2, z1 * x2 - x1 * z2, x1 * y2 - y1 * x2, (((float)Math.acos(((double)MathUtils.clamp(Vector3.dot(x1, y1, z1, x2, y2, z2), -1f, 1f))))) * 57.295776f);
    }

    public Quaternion setFromMatrix(Matrix4 matrix) {
        return this.setFromAxes(matrix.val[0], matrix.val[4], matrix.val[8], matrix.val[1], matrix.val[5], matrix.val[9], matrix.val[2], matrix.val[6], matrix.val[10]);
    }

    public Quaternion slerp(Quaternion end, float alpha) {
        float v0;
        float v3 = this.dot(end);
        if(v3 < 0f) {
            v0 = -v3;
        }
        else {
            v0 = v3;
        }

        float v6 = 1f - alpha;
        float v7 = alpha;
        if((((double)(1f - v0))) > 0.1) {
            double v1 = Math.acos(((double)v0));
            double v4 = 1 / Math.sin(v1);
            v6 = ((float)(Math.sin((((double)(1f - alpha))) * v1) * v4));
            v7 = ((float)(Math.sin((((double)alpha)) * v1) * v4));
        }

        if(v3 < 0f) {
            v7 = -v7;
        }

        this.x = this.x * v6 + end.x * v7;
        this.y = this.y * v6 + end.y * v7;
        this.z = this.z * v6 + end.z * v7;
        this.w = this.w * v6 + end.w * v7;
        return this;
    }

    public void toMatrix(float[] matrix) {
        float v1 = this.x * this.x;
        float v2 = this.x * this.y;
        float v3 = this.x * this.z;
        float v0 = this.x * this.w;
        float v5 = this.y * this.y;
        float v6 = this.y * this.z;
        float v4 = this.y * this.w;
        float v8 = this.z * this.z;
        float v7 = this.z * this.w;
        matrix[0] = 1f - (v5 + v8) * 2f;
        matrix[4] = (v2 - v7) * 2f;
        matrix[8] = (v3 + v4) * 2f;
        matrix[12] = 0f;
        matrix[1] = (v2 + v7) * 2f;
        matrix[5] = 1f - (v1 + v8) * 2f;
        matrix[9] = (v6 - v0) * 2f;
        matrix[13] = 0f;
        matrix[2] = (v3 - v4) * 2f;
        matrix[6] = (v6 + v0) * 2f;
        matrix[10] = 1f - (v1 + v5) * 2f;
        matrix[14] = 0f;
        matrix[3] = 0f;
        matrix[7] = 0f;
        matrix[11] = 0f;
        matrix[15] = 1f;
    }

    public String toString() {
        return "[" + this.x + "|" + this.y + "|" + this.z + "|" + this.w + "]";
    }

    public Vector3 transform(Vector3 v) {
        Quaternion.tmp2.set(this);
        Quaternion.tmp2.conjugate();
        Quaternion.tmp2.mulLeft(Quaternion.tmp1.set(v.x, v.y, v.z, 0f)).mulLeft(this);
        v.x = Quaternion.tmp2.x;
        v.y = Quaternion.tmp2.y;
        v.z = Quaternion.tmp2.z;
        return v;
    }
}

