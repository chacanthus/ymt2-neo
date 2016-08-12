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

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

public class Vector2 implements Vector, Serializable {
    public static final Vector2 X = null;
    public static final Vector2 Y = null;
    public static final Vector2 Zero = null;
    private static final long serialVersionUID = 913902788239530931L;
    public float x;
    public float y;

    static  {
        Vector2.X = new Vector2(1f, 0f);
        Vector2.Y = new Vector2(0f, 1f);
        Vector2.Zero = new Vector2(0f, 0f);
    }

    public Vector2() {
        super();
    }

    public Vector2(float x, float y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        super();
        this.set(v);
    }

    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector add(Vector x0) {
        return this.add(((Vector2)x0));
    }

    public float angle() {
        float v0 = (((float)Math.atan2(((double)this.y), ((double)this.x)))) * 57.295776f;
        if(v0 < 0f) {
            v0 += 360f;
        }

        return v0;
    }

    public Vector2 clamp(float min, float max) {
        float v0 = this.len2();
        if(v0 != 0f) {
            if(v0 > max * max) {
                this = this.nor().scl(max);
            }
            else if(v0 < min * min) {
                this = this.nor().scl(min);
            }
        }

        return this;
    }

    public Vector clamp(float x0, float x1) {
        return this.clamp(x0, x1);
    }

    public Vector2 cpy() {
        return new Vector2(this);
    }

    public Vector cpy() {
        return this.cpy();
    }

    public float crs(float x, float y) {
        return this.x * y - this.y * x;
    }

    public float crs(Vector2 v) {
        return this.x * v.y - this.y * v.x;
    }

    public Vector2 div(float value) {
        return this.scl(1f / value);
    }

    public Vector2 div(float vx, float vy) {
        return this.scl(1f / vx, 1f / vy);
    }

    public Vector2 div(Vector2 other) {
        return this.scl(1f / other.x, 1f / other.y);
    }

    public float dot(Vector2 v) {
        return this.x * v.x + this.y * v.y;
    }

    public float dot(Vector x0) {
        return this.dot(((Vector2)x0));
    }

    public float dst(Vector2 v) {
        float v0 = v.x - this.x;
        float v1 = v.y - this.y;
        return ((float)Math.sqrt(((double)(v0 * v0 + v1 * v1))));
    }

    public float dst(float x, float y) {
        float v0 = x - this.x;
        float v1 = y - this.y;
        return ((float)Math.sqrt(((double)(v0 * v0 + v1 * v1))));
    }

    public float dst(Vector x0) {
        return this.dst(((Vector2)x0));
    }

    public float dst2(Vector2 v) {
        float v0 = v.x - this.x;
        float v1 = v.y - this.y;
        return v0 * v0 + v1 * v1;
    }

    public float dst2(float x, float y) {
        float v0 = x - this.x;
        float v1 = y - this.y;
        return v0 * v0 + v1 * v1;
    }

    public float dst2(Vector x0) {
        return this.dst2(((Vector2)x0));
    }

    public boolean epsilonEquals(float x, float y, float epsilon) {
        boolean v0 = false;
        if(Math.abs(x - this.x) <= epsilon && Math.abs(y - this.y) <= epsilon) {
            v0 = true;
        }

        return v0;
    }

    public boolean epsilonEquals(Vector2 obj, float epsilon) {
        boolean v0 = false;
        if(obj != null && Math.abs(obj.x - this.x) <= epsilon && Math.abs(obj.y - this.y) <= epsilon) {
            v0 = true;
        }

        return v0;
    }

    public boolean equals(Object obj) {
        boolean v1 = true;
        if(this != (((Vector2)obj))) {
            if(obj == null) {
                v1 = false;
            }
            else if(this.getClass() != obj.getClass()) {
                v1 = false;
            }
            else {
                Object v0 = obj;
                if(NumberUtils.floatToIntBits(this.x) != NumberUtils.floatToIntBits(((Vector2)v0).x)) {
                    v1 = false;
                }
                else if(NumberUtils.floatToIntBits(this.y) != NumberUtils.floatToIntBits(((Vector2)v0).y)) {
                    v1 = false;
                }
            }
        }

        return v1;
    }

    public int hashCode() {
        return (NumberUtils.floatToIntBits(this.x) + 31) * 31 + NumberUtils.floatToIntBits(this.y);
    }

    public boolean isUnit() {
        return this.isUnit(0f);
    }

    public boolean isUnit(float margin) {
        boolean v0;
        if(Math.abs(this.len2() - 1f) < margin) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean isZero() {
        boolean v0;
        if(this.x != 0f || this.y != 0f) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    public boolean isZero(float margin) {
        boolean v0;
        if(this.len2() < margin) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public float len() {
        return ((float)Math.sqrt(((double)(this.x * this.x + this.y * this.y))));
    }

    public float len2() {
        return this.x * this.x + this.y * this.y;
    }

    public Vector2 lerp(Vector2 target, float alpha) {
        float v0 = 1f - alpha;
        this.x = this.x * v0 + target.x * alpha;
        this.y = this.y * v0 + target.y * alpha;
        return this;
    }

    public Vector lerp(Vector x0, float x1) {
        return this.lerp(((Vector2)x0), x1);
    }

    public Vector2 limit(float limit) {
        if(this.len2() > limit * limit) {
            this.nor();
            this.scl(limit);
        }

        return this;
    }

    public Vector limit(float x0) {
        return this.limit(x0);
    }

    public Vector2 mul(Matrix3 mat) {
        float v0 = this.x * mat.val[0] + this.y * mat.val[3] + mat.val[6];
        float v1 = this.x * mat.val[1] + this.y * mat.val[4] + mat.val[7];
        this.x = v0;
        this.y = v1;
        return this;
    }

    public Vector2 mul(float scalar) {
        return this.scl(scalar);
    }

    public Vector2 mul(float x, float y) {
        return this.scl(x, y);
    }

    public Vector2 mul(Vector2 v) {
        return this.scl(v);
    }

    public Vector2 nor() {
        float v0 = this.len();
        if(v0 != 0f) {
            this.x /= v0;
            this.y /= v0;
        }

        return this;
    }

    public Vector nor() {
        return this.nor();
    }

    public Vector2 rotate(float degrees) {
        float v3 = degrees * 0.017453f;
        float v0 = ((float)Math.cos(((double)v3)));
        float v4 = ((float)Math.sin(((double)v3)));
        float v1 = this.x * v0 - this.y * v4;
        float v2 = this.x * v4 + this.y * v0;
        this.x = v1;
        this.y = v2;
        return this;
    }

    public Vector2 rotate90(int dir) {
        float v0 = this.x;
        if(dir >= 0) {
            this.x = -this.y;
            this.y = v0;
        }
        else {
            this.x = this.y;
            this.y = -v0;
        }

        return this;
    }

    public Vector2 scl(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 scl(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2 scl(Vector2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    public Vector scl(float x0) {
        return this.scl(x0);
    }

    public Vector scl(Vector x0) {
        return this.scl(((Vector2)x0));
    }

    public Vector2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    public Vector set(Vector x0) {
        return this.set(((Vector2)x0));
    }

    public Vector2 setAngle(float degrees) {
        this.set(this.len(), 0f);
        this.rotate(degrees);
        return this;
    }

    public Vector2 sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector sub(Vector x0) {
        return this.sub(((Vector2)x0));
    }

    public String toString() {
        return "[" + this.x + ":" + this.y + "]";
    }
}

