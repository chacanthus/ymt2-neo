// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class BoundingBox implements Serializable {
    final Vector3 cnt;
    final Vector3[] crn;
    boolean crn_dirty;
    final Vector3 dim;
    public final Vector3 max;
    public final Vector3 min;
    private static final long serialVersionUID = -1286036817192127343L;

    public BoundingBox() {
        int v3 = 8;
        super();
        this.crn = new Vector3[v3];
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.crn_dirty = true;
        this.crn_dirty = true;
        int v0;
        for(v0 = 0; v0 < v3; ++v0) {
            this.crn[v0] = new Vector3();
        }

        this.clr();
    }

    public BoundingBox(Vector3 minimum, Vector3 maximum) {
        int v3 = 8;
        super();
        this.crn = new Vector3[v3];
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.crn_dirty = true;
        this.crn_dirty = true;
        int v0;
        for(v0 = 0; v0 < v3; ++v0) {
            this.crn[v0] = new Vector3();
        }

        this.set(minimum, maximum);
    }

    public BoundingBox(BoundingBox bounds) {
        int v3 = 8;
        super();
        this.crn = new Vector3[v3];
        this.min = new Vector3();
        this.max = new Vector3();
        this.cnt = new Vector3();
        this.dim = new Vector3();
        this.crn_dirty = true;
        this.crn_dirty = true;
        int v0;
        for(v0 = 0; v0 < v3; ++v0) {
            this.crn[v0] = new Vector3();
        }

        this.set(bounds);
    }

    public BoundingBox clr() {
        this.crn_dirty = true;
        return this.set(this.min.set(0f, 0f, 0f), this.max.set(0f, 0f, 0f));
    }

    public boolean contains(Vector3 v) {
        boolean v0;
        if(this.min.x > v.x || this.max.x < v.x || this.min.y > v.y || this.max.y < v.y || this.min.z > v.z || this.max.z < v.z) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    public boolean contains(BoundingBox b) {
        boolean v0;
        if(this.isValid()) {
            if(this.min.x <= b.min.x && this.min.y <= b.min.y && this.min.z <= b.min.z && this.max.x >= b.max.x && this.max.y >= b.max.y && this.max.z >= b.max.z) {
                goto label_32;
            }

            v0 = false;
        }
        else {
        label_32:
            v0 = true;
        }

        return v0;
    }

    public BoundingBox ext(float x, float y, float z) {
        this.crn_dirty = true;
        return this.set(this.min.set(BoundingBox.min(this.min.x, x), BoundingBox.min(this.min.y, y), BoundingBox.min(this.min.z, z)), this.max.set(BoundingBox.max(this.max.x, x), BoundingBox.max(this.max.y, y), BoundingBox.max(this.max.z, z)));
    }

    public BoundingBox ext(Vector3 point) {
        this.crn_dirty = true;
        return this.set(this.min.set(BoundingBox.min(this.min.x, point.x), BoundingBox.min(this.min.y, point.y), BoundingBox.min(this.min.z, point.z)), this.max.set(Math.max(this.max.x, point.x), Math.max(this.max.y, point.y), Math.max(this.max.z, point.z)));
    }

    public BoundingBox ext(BoundingBox a_bounds) {
        this.crn_dirty = true;
        return this.set(this.min.set(BoundingBox.min(this.min.x, a_bounds.min.x), BoundingBox.min(this.min.y, a_bounds.min.y), BoundingBox.min(this.min.z, a_bounds.min.z)), this.max.set(BoundingBox.max(this.max.x, a_bounds.max.x), BoundingBox.max(this.max.y, a_bounds.max.y), BoundingBox.max(this.max.z, a_bounds.max.z)));
    }

    public BoundingBox ext(BoundingBox bounds, Matrix4 transform) {
        bounds.updateCorners();
        Vector3[] v0 = this.crn;
        int v2 = v0.length;
        int v1;
        for(v1 = 0; v1 < v2; ++v1) {
            Vector3 v3 = v0[v1];
            v3.mul(transform);
            this.min.set(BoundingBox.min(this.min.x, v3.x), BoundingBox.min(this.min.y, v3.y), BoundingBox.min(this.min.z, v3.z));
            this.max.set(BoundingBox.max(this.max.x, v3.x), BoundingBox.max(this.max.y, v3.y), BoundingBox.max(this.max.z, v3.z));
        }

        this.crn_dirty = true;
        bounds.crn_dirty = true;
        return this.set(this.min, this.max);
    }

    public Vector3 getCenter() {
        return this.cnt;
    }

    public Vector3[] getCorners() {
        this.updateCorners();
        return this.crn;
    }

    public Vector3 getDimensions() {
        return this.dim;
    }

    public Vector3 getMax() {  // has try-catch handlers
        try {
            return this.max;
        }
        catch(Throwable v0) {
            throw v0;
        }
    }

    public Vector3 getMin() {
        return this.min;
    }

    public BoundingBox inf() {
        this.min.set(Infinityf, Infinityf, Infinityf);
        this.max.set(-Infinityf, -Infinityf, -Infinityf);
        this.cnt.set(0f, 0f, 0f);
        this.dim.set(0f, 0f, 0f);
        this.crn_dirty = true;
        return this;
    }

    public boolean intersects(BoundingBox b) {
        boolean v6 = false;
        float v9 = 2f;
        if(this.isValid()) {
            float v0 = Math.abs(this.cnt.x - b.cnt.x);
            float v3 = this.dim.x / v9 + b.dim.x / v9;
            float v1 = Math.abs(this.cnt.y - b.cnt.y);
            float v4 = this.dim.y / v9 + b.dim.y / v9;
            float v2 = Math.abs(this.cnt.z - b.cnt.z);
            float v5 = this.dim.z / v9 + b.dim.z / v9;
            if(v0 <= v3 && v1 <= v4 && v2 <= v5) {
                v6 = true;
            }
        }

        return v6;
    }

    public boolean isValid() {
        boolean v0;
        if(this.min.x >= this.max.x || this.min.y >= this.max.y || this.min.z >= this.max.z) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    static float max(float a, float b) {
        if(a <= b) {
            a = b;
        }

        return a;
    }

    static float min(float a, float b) {
        if(a <= b) {
            b = a;
        }

        return b;
    }

    public BoundingBox mul(Matrix4 matrix) {
        this.updateCorners();
        this.inf();
        Vector3[] v0 = this.crn;
        int v3 = v0.length;
        int v1;
        for(v1 = 0; v1 < v3; ++v1) {
            Vector3 v2 = v0[v1];
            v2.mul(matrix);
            this.min.set(BoundingBox.min(this.min.x, v2.x), BoundingBox.min(this.min.y, v2.y), BoundingBox.min(this.min.z, v2.z));
            this.max.set(BoundingBox.max(this.max.x, v2.x), BoundingBox.max(this.max.y, v2.y), BoundingBox.max(this.max.z, v2.z));
        }

        this.crn_dirty = true;
        return this.set(this.min, this.max);
    }

    public BoundingBox set(Vector3 minimum, Vector3 maximum) {
        float v2;
        float v1;
        float v0;
        Vector3 v3 = this.min;
        if(minimum.x < maximum.x) {
            v0 = minimum.x;
        }
        else {
            v0 = maximum.x;
        }

        if(minimum.y < maximum.y) {
            v1 = minimum.y;
        }
        else {
            v1 = maximum.y;
        }

        if(minimum.z < maximum.z) {
            v2 = minimum.z;
        }
        else {
            v2 = maximum.z;
        }

        v3.set(v0, v1, v2);
        v3 = this.max;
        if(minimum.x > maximum.x) {
            v0 = minimum.x;
        }
        else {
            v0 = maximum.x;
        }

        if(minimum.y > maximum.y) {
            v1 = minimum.y;
        }
        else {
            v1 = maximum.y;
        }

        if(minimum.z > maximum.z) {
            v2 = minimum.z;
        }
        else {
            v2 = maximum.z;
        }

        v3.set(v0, v1, v2);
        this.cnt.set(this.min).add(this.max).scl(0.5f);
        this.dim.set(this.max).sub(this.min);
        this.crn_dirty = true;
        return this;
    }

    public BoundingBox set(BoundingBox bounds) {
        this.crn_dirty = true;
        return this.set(bounds.min, bounds.max);
    }

    public BoundingBox set(List arg4) {
        this.inf();
        Iterator v0 = arg4.iterator();
        while(v0.hasNext()) {
            this.ext(v0.next());
        }

        this.crn_dirty = true;
        return this;
    }

    public BoundingBox set(Vector3[] points) {
        this.inf();
        Vector3[] v0 = points;
        int v3 = v0.length;
        int v1;
        for(v1 = 0; v1 < v3; ++v1) {
            this.ext(v0[v1]);
        }

        this.crn_dirty = true;
        return this;
    }

    public String toString() {
        return "[" + this.min + "|" + this.max + "]";
    }

    protected void updateCorners() {
        if(this.crn_dirty) {
            this.crn[0].set(this.min.x, this.min.y, this.min.z);
            this.crn[1].set(this.max.x, this.min.y, this.min.z);
            this.crn[2].set(this.max.x, this.max.y, this.min.z);
            this.crn[3].set(this.min.x, this.max.y, this.min.z);
            this.crn[4].set(this.min.x, this.min.y, this.max.z);
            this.crn[5].set(this.max.x, this.min.y, this.max.z);
            this.crn[6].set(this.max.x, this.max.y, this.max.z);
            this.crn[7].set(this.min.x, this.max.y, this.max.z);
            this.crn_dirty = false;
        }
    }
}

