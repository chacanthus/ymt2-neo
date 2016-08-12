// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShapeRenderer {
    public enum ShapeType {
        public static final enum ShapeType Point;
        private final int glType;

        static  {
            ShapeType.Point = new ShapeType("Point", 0, 0);
            ShapeType.Line = new ShapeType("Line", 1, 1);
            ShapeType.Filled = new ShapeType("Filled", 2, 4);
            ShapeType[] v0 = new ShapeType[3];
            v0[0] = ShapeType.Point;
            v0[1] = ShapeType.Line;
            v0[2] = ShapeType.Filled;
            ShapeType.$VALUES = v0;
        }

        private ShapeType(String arg1, int arg2, int glType) {
            super(arg1, arg2);
            this.glType = glType;
        }

        public int getGlType() {
            return this.glType;
        }

        public static ShapeType valueOf(String name) {
            return Enum.valueOf(ShapeType.class, name);
        }

        public static ShapeType[] values() {
            return ShapeType.$VALUES.clone();
        }
    }

    Color color;
    Matrix4 combined;
    ShapeType currType;
    boolean matrixDirty;
    Matrix4 projView;
    ImmediateModeRenderer renderer;
    Vector2 tmp;
    Matrix4 transform;

    public ShapeRenderer() {
        this(5000);
    }

    public ShapeRenderer(int maxVertices) {
        super();
        this.matrixDirty = false;
        this.projView = new Matrix4();
        this.transform = new Matrix4();
        this.combined = new Matrix4();
        this.tmp = new Vector2();
        this.color = new Color(1f, 1f, 1f, 1f);
        this.currType = null;
        if(Gdx.graphics.isGL20Available()) {
            this.renderer = new ImmediateModeRenderer20(maxVertices, false, true, 0);
        }
        else {
            this.renderer = new ImmediateModeRenderer10(maxVertices);
        }

        this.projView.setToOrtho2D(0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
        this.matrixDirty = true;
    }

    public void arc(float x, float y, float radius, float start, float angle) {
        this.arc(x, y, radius, start, angle, Math.max(1, ((int)(6f * (((float)Math.cbrt(((double)radius)))) * (angle / 360f)))));
    }

    public void arc(float x, float y, float radius, float start, float angle, int segments) {
        float v6;
        if(segments <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }

        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        float v7 = 6.283185f * (angle / 360f) / (((float)segments));
        float v1 = MathUtils.cos(v7);
        float v5 = MathUtils.sin(v7);
        float v2 = radius * MathUtils.cos(0.017453f * start);
        float v3 = radius * MathUtils.sin(0.017453f * start);
        if(this.currType == ShapeType.Line) {
            this.checkFlush(segments * 2 + 2);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v2, y + v3, 0f);
            int v4;
            for(v4 = 0; v4 < segments; ++v4) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v2, y + v3, 0f);
                v6 = v2;
                v2 = v1 * v2 - v5 * v3;
                v3 = v5 * v6 + v1 * v3;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v2, y + v3, 0f);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v2, y + v3, 0f);
        }
        else {
            this.checkFlush(segments * 3 + 3);
            for(v4 = 0; v4 < segments; ++v4) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x, y, 0f);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v2, y + v3, 0f);
                v6 = v2;
                v2 = v1 * v2 - v5 * v3;
                v3 = v5 * v6 + v1 * v3;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v2, y + v3, 0f);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v2, y + v3, 0f);
        }

        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(x + 0f, y + 0f, 0f);
    }

    public void begin(ShapeType type) {
        if(this.currType != null) {
            throw new GdxRuntimeException("Call end() before beginning a new shape batch");
        }

        this.currType = type;
        if(this.matrixDirty) {
            this.combined.set(this.projView);
            Matrix4.mul(this.combined.val, this.transform.val);
            this.matrixDirty = false;
        }

        this.renderer.begin(this.combined, this.currType.getGlType());
    }

    public void box(float x, float y, float z, float width, float height, float depth) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        depth = -depth;
        if(this.currType == ShapeType.Line) {
            this.checkFlush(24);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
        }
        else {
            this.checkFlush(36);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + depth);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
        }
    }

    private void checkDirty() {
        if(this.matrixDirty) {
            ShapeType v0 = this.currType;
            this.end();
            this.begin(v0);
        }
    }

    private void checkFlush(int newVertices) {
        if(this.renderer.getMaxVertices() - this.renderer.getNumVertices() < newVertices) {
            ShapeType v0 = this.currType;
            this.end();
            this.begin(v0);
        }
    }

    public void circle(float x, float y, float radius) {
        this.circle(x, y, radius, Math.max(1, ((int)(6f * (((float)Math.cbrt(((double)radius))))))));
    }

    public void circle(float x, float y, float radius, int segments) {
        float v7;
        if(segments <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }

        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        float v1 = 6.283185f / (((float)segments));
        float v2 = MathUtils.cos(v1);
        float v6 = MathUtils.sin(v1);
        float v3 = radius;
        float v4 = 0f;
        if(this.currType == ShapeType.Line) {
            this.checkFlush(segments * 2 + 2);
            int v5;
            for(v5 = 0; v5 < segments; ++v5) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v3, y + v4, 0f);
                v7 = v3;
                v3 = v2 * v3 - v6 * v4;
                v4 = v6 * v7 + v2 * v4;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v3, y + v4, 0f);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v3, y + v4, 0f);
        }
        else {
            this.checkFlush(segments * 3 + 3);
            --segments;
            for(v5 = 0; v5 < segments; ++v5) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x, y, 0f);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v3, y + v4, 0f);
                v7 = v3;
                v3 = v2 * v3 - v6 * v4;
                v4 = v6 * v7 + v2 * v4;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v3, y + v4, 0f);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v3, y + v4, 0f);
        }

        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(x + radius, y + 0f, 0f);
    }

    public void cone(float x, float y, float z, float radius, float height) {
        this.cone(x, y, z, radius, height, Math.max(1, ((int)(4f * (((float)Math.sqrt(((double)radius))))))));
    }

    public void cone(float x, float y, float z, float radius, float height, int segments) {
        float v10;
        float v9;
        if(segments <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }

        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(segments * 4 + 2);
        float v3 = 6.283185f / (((float)segments));
        float v4 = MathUtils.cos(v3);
        float v8 = MathUtils.sin(v3);
        float v5 = radius;
        float v6 = 0f;
        if(this.currType == ShapeType.Line) {
            int v7;
            for(v7 = 0; v7 < segments; ++v7) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x, y, z + height);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
                v9 = v5;
                v5 = v4 * v5 - v8 * v6;
                v6 = v8 * v9 + v4 * v6;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v5, y + v6, z);
        }
        else {
            --segments;
            for(v7 = 0; v7 < segments; ++v7) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x, y, z);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
                v9 = v5;
                v10 = v6;
                v5 = v4 * v5 - v8 * v6;
                v6 = v8 * v9 + v4 * v6;
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v9, y + v10, z);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x + v5, y + v6, z);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(x, y, z + height);
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v5, y + v6, z);
        }

        v9 = v5;
        v10 = v6;
        v5 = radius;
        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(x + v5, y + 0f, z);
        if(this.currType != ShapeType.Line) {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v9, y + v10, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + v5, y + 0f, z);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, z + height);
        }
    }

    public void curve(float x1, float y1, float cx1, float cy1, float cx2, float cy2, float x2, float y2, int segments) {
        if(this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(segments * 2 + 2);
        float v17 = 1f / (((float)segments));
        float v18 = v17 * v17;
        float v19 = v17 * v17 * v17;
        float v12 = 3f * v17;
        float v13 = 3f * v18;
        float v14 = 6f * v18;
        float v15 = 6f * v19;
        float v20 = x1 - 2f * cx1 + cx2;
        float v21 = y1 - 2f * cy1 + cy2;
        float v22 = (cx1 - cx2) * 3f - x1 + x2;
        float v23 = (cy1 - cy2) * 3f - y1 + y2;
        float v10 = x1;
        float v11 = y1;
        float v8 = (cx1 - x1) * v12 + v20 * v13 + v22 * v19;
        float v9 = (cy1 - y1) * v12 + v21 * v13 + v23 * v19;
        float v6 = v20 * v14 + v22 * v15;
        float v7 = v21 * v14 + v23 * v15;
        float v4 = v22 * v15;
        float v5 = v23 * v15;
        int v16;
        for(v16 = segments; true; v16 = segments) {
            segments = v16 - 1;
            if(v16 <= 0) {
                break;
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v10, v11, 0f);
            v10 += v8;
            v11 += v9;
            v8 += v6;
            v9 += v7;
            v6 += v4;
            v7 += v5;
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v10, v11, 0f);
        }

        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(v10, v11, 0f);
        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(x2, y2, 0f);
    }

    public void dispose() {
        this.renderer.dispose();
    }

    public void ellipse(float x, float y, float width, float height) {
        this.ellipse(x, y, width, height, Math.max(1, ((int)(12f * (((float)Math.cbrt(((double)Math.max(width * 0.5f, 0.5f * height)))))))));
    }

    public void ellipse(float x, float y, float width, float height, int segments) {
        if(segments <= 0) {
            throw new IllegalArgumentException("segments must be > 0.");
        }

        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(segments * 3);
        float v0 = 6.283185f / (((float)segments));
        float v1 = x + width / 2f;
        float v2 = y + height / 2f;
        if(this.currType == ShapeType.Line) {
            int v3;
            for(v3 = 0; v3 < segments; ++v3) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(0.5f * width * MathUtils.cos((((float)v3)) * v0) + v1, 0.5f * height * MathUtils.sin((((float)v3)) * v0) + v2, 0f);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(0.5f * width * MathUtils.cos((((float)(v3 + 1))) * v0) + v1, 0.5f * height * MathUtils.sin((((float)(v3 + 1))) * v0) + v2, 0f);
            }
        }
        else {
            for(v3 = 0; v3 < segments; ++v3) {
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(0.5f * width * MathUtils.cos((((float)v3)) * v0) + v1, 0.5f * height * MathUtils.sin((((float)v3)) * v0) + v2, 0f);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(v1, v2, 0f);
                this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
                this.renderer.vertex(0.5f * width * MathUtils.cos((((float)(v3 + 1))) * v0) + v1, 0.5f * height * MathUtils.sin((((float)(v3 + 1))) * v0) + v2, 0f);
            }
        }
    }

    public void end() {
        this.renderer.end();
        this.currType = null;
    }

    public void flush() {
        ShapeType v0 = this.currType;
        this.end();
        this.begin(v0);
    }

    public Color getColor() {
        return this.color;
    }

    public ShapeType getCurrentType() {
        return this.currType;
    }

    public Matrix4 getProjectionMatrix() {
        return this.projView;
    }

    public ImmediateModeRenderer getRenderer() {
        return this.renderer;
    }

    public Matrix4 getTransformMatrix() {
        return this.transform;
    }

    public void identity() {
        this.transform.idt();
        this.matrixDirty = true;
    }

    public final void line(float x, float y, float x2, float y2) {
        this.line(x, y, 0f, x2, y2, 0f, this.color, this.color);
    }

    public void line(float x, float y, float z, float x2, float y2, float z2, Color c1, Color c2) {
        if(this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(2);
        this.renderer.color(c1.r, c1.g, c1.b, c1.a);
        this.renderer.vertex(x, y, z);
        this.renderer.color(c2.r, c2.g, c2.b, c2.a);
        this.renderer.vertex(x2, y2, z2);
    }

    public final void line(float x, float y, float z, float x2, float y2, float z2) {
        this.line(x, y, z, x2, y2, z2, this.color, this.color);
    }

    public final void line(float x, float y, float x2, float y2, Color c1, Color c2) {
        this.line(x, y, 0f, x2, y2, 0f, c1, c2);
    }

    public final void line(Vector2 v0, Vector2 v1) {
        this.line(v0.x, v0.y, 0f, v1.x, v1.y, 0f, this.color, this.color);
    }

    public final void line(Vector3 v0, Vector3 v1) {
        this.line(v0.x, v0.y, v0.z, v1.x, v1.y, v1.z, this.color, this.color);
    }

    public void point(float x, float y, float z) {
        if(this.currType != ShapeType.Point) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Point)");
        }

        this.checkDirty();
        this.checkFlush(1);
        this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
        this.renderer.vertex(x, y, z);
    }

    public void polygon(float[] vertices) {
        this.polygon(vertices, 0, vertices.length);
    }

    public void polygon(float[] vertices, int offset, int count) {
        float v8;
        float v6;
        if(this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Line)");
        }

        if(count < 6) {
            throw new IllegalArgumentException("Polygons must contain at least 3 points.");
        }

        if(count % 2 != 0) {
            throw new IllegalArgumentException("Polygons must have an even number of vertices.");
        }

        this.checkDirty();
        this.checkFlush(count);
        float v1 = vertices[0];
        float v2 = vertices[1];
        int v3 = offset;
        int v4 = offset + count;
        while(v3 < v4) {
            float v5 = vertices[v3];
            float v7 = vertices[v3 + 1];
            if(v3 + 2 >= count) {
                v6 = v1;
                v8 = v2;
            }
            else {
                v6 = vertices[v3 + 2];
                v8 = vertices[v3 + 3];
            }

            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v5, v7, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v6, v8, 0f);
            v3 += 2;
        }
    }

    public void polyline(float[] vertices) {
        this.polyline(vertices, 0, vertices.length);
    }

    public void polyline(float[] vertices, int offset, int count) {
        if(this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Line)");
        }

        if(count < 4) {
            throw new IllegalArgumentException("Polylines must contain at least 2 points.");
        }

        if(count % 2 != 0) {
            throw new IllegalArgumentException("Polylines must have an even number of vertices.");
        }

        this.checkDirty();
        this.checkFlush(count);
        int v0 = offset;
        int v1 = offset + count - 2;
        while(v0 < v1) {
            float v2 = vertices[v0];
            float v4 = vertices[v0 + 1];
            float v3 = vertices[v0 + 2];
            float v5 = vertices[v0 + 3];
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v2, v4, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v3, v5, 0f);
            v0 += 2;
        }
    }

    public void rect(float x, float y, float width, float height) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(8);
        if(this.currType == ShapeType.Line) {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
        }
        else {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x, y, 0f);
        }
    }

    public void rect(float x, float y, float width, float height, float originX, float originY, float rotation) {
        this.rect(x, y, width, height, originX, originY, rotation, this.color, this.color, this.color, this.color);
    }

    public void rect(float x, float y, float width, float height, float originX, float originY, float rotation, Color col1, Color col2, Color col3, Color col4) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(8);
        float v2 = ((float)Math.toRadians(((double)rotation)));
        float v1 = ((float)Math.cos(((double)v2)));
        float v3 = ((float)Math.sin(((double)v2)));
        float v4 = (0f - originX) * v1 + x + -v3 * (0f - originY) + originX;
        float v8 = (0f - originX) * v3 + y + (0f - originY) * v1 + originY;
        float v5 = (width - originX) * v1 + x + -v3 * (0f - originY) + originX;
        float v9 = (width - originX) * v3 + y + (0f - originY) * v1 + originY;
        float v6 = (width - originX) * v1 + x + -v3 * (height - originY) + originX;
        float v10 = (width - originX) * v3 + y + (height - originY) * v1 + originY;
        float v7 = (0f - originX) * v1 + x + -v3 * (height - originY) + originX;
        float v11 = (0f - originX) * v3 + y + (height - originY) * v1 + originY;
        if(this.currType == ShapeType.Line) {
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(v4, v8, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(v5, v9, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(v5, v9, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(v6, v10, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(v6, v10, 0f);
            this.renderer.color(col4.r, col4.g, col4.b, col4.a);
            this.renderer.vertex(v7, v11, 0f);
            this.renderer.color(col4.r, col4.g, col4.b, col4.a);
            this.renderer.vertex(v7, v11, 0f);
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(v4, v8, 0f);
        }
        else {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v4, v8, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v5, v9, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v6, v10, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v6, v10, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v7, v11, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(v4, v8, 0f);
        }
    }

    public void rect(float x, float y, float width, float height, Color col1, Color col2, Color col3, Color col4) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(8);
        if(this.currType == ShapeType.Line) {
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(col4.r, col4.g, col4.b, col4.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(col4.r, col4.g, col4.b, col4.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x, y, 0f);
        }
        else {
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x, y, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x + width, y, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x + width, y + height, 0f);
            this.renderer.color(col4.r, col4.g, col4.b, col4.a);
            this.renderer.vertex(x, y + height, 0f);
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x, y, 0f);
        }
    }

    public void rectLine(float x1, float y1, float x2, float y2, float width) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(8);
        Vector2 v0 = this.tmp.set(y2 - y1, x1 - x2).nor();
        width *= 0.5f;
        float v1 = v0.x * width;
        float v2 = v0.y * width;
        if(this.currType == ShapeType.Line) {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 + v1, y1 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 - v1, y1 - v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 + v1, y2 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 - v1, y2 - v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 + v1, y2 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 + v1, y1 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 - v1, y2 - v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 - v1, y1 - v2, 0f);
        }
        else {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 + v1, y1 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 - v1, y1 - v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 + v1, y2 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 - v1, y2 - v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2 + v1, y2 + v2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1 - v1, y1 - v2, 0f);
        }
    }

    public void rectLine(Vector2 p1, Vector2 p2, float width) {
        this.rectLine(p1.x, p1.y, p2.x, p2.y, width);
    }

    public void rotate(float axisX, float axisY, float axisZ, float angle) {
        this.transform.rotate(axisX, axisY, axisZ, angle);
        this.matrixDirty = true;
    }

    public void scale(float scaleX, float scaleY, float scaleZ) {
        this.transform.scale(scaleX, scaleY, scaleZ);
        this.matrixDirty = true;
    }

    public void setColor(float r, float g, float b, float a) {
        this.color.set(r, g, b, a);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setProjectionMatrix(Matrix4 matrix) {
        this.projView.set(matrix);
        this.matrixDirty = true;
    }

    public void setTransformMatrix(Matrix4 matrix) {
        this.transform.set(matrix);
        this.matrixDirty = true;
    }

    public void translate(float x, float y, float z) {
        this.transform.translate(x, y, z);
        this.matrixDirty = true;
    }

    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(6);
        if(this.currType == ShapeType.Line) {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1, y1, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x3, y3, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x3, y3, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1, y1, 0f);
        }
        else {
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x1, y1, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(this.color.r, this.color.g, this.color.b, this.color.a);
            this.renderer.vertex(x3, y3, 0f);
        }
    }

    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3, Color col1, Color col2, Color col3) {
        if(this.currType != ShapeType.Filled && this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Filled) or begin(ShapeType.Line)");
        }

        this.checkDirty();
        this.checkFlush(6);
        if(this.currType == ShapeType.Line) {
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x1, y1, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x3, y3, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x3, y3, 0f);
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x1, y1, 0f);
        }
        else {
            this.renderer.color(col1.r, col1.g, col1.b, col1.a);
            this.renderer.vertex(x1, y1, 0f);
            this.renderer.color(col2.r, col2.g, col2.b, col2.a);
            this.renderer.vertex(x2, y2, 0f);
            this.renderer.color(col3.r, col3.g, col3.b, col3.a);
            this.renderer.vertex(x3, y3, 0f);
        }
    }

    public void x(float x, float y, float radius) {
        if(this.currType != ShapeType.Line) {
            throw new GdxRuntimeException("Must call begin(ShapeType.Line)");
        }

        this.line(x - radius, y - radius, x + radius, y + radius);
        this.line(x - radius, y + radius, x + radius, y - radius);
    }

    public void x(Vector2 p, float radius) {
        this.x(p.x, p.y, radius);
    }
}

