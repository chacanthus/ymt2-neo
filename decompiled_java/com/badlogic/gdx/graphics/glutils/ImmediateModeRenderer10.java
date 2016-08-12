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
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.FloatBuffer;

public class ImmediateModeRenderer10 implements ImmediateModeRenderer {
    private float[] colors;
    private FloatBuffer colorsBuffer;
    private boolean hasCols;
    private boolean hasNors;
    private boolean hasTexCoords;
    private int idxCols;
    private int idxNors;
    private int idxPos;
    private int idxTexCoords;
    private final int maxVertices;
    private float[] normals;
    private FloatBuffer normalsBuffer;
    private int numVertices;
    private float[] positions;
    private FloatBuffer positionsBuffer;
    private int primitiveType;
    private float[] texCoords;
    private FloatBuffer texCoordsBuffer;

    public ImmediateModeRenderer10(int maxVertices) {
        super();
        this.idxPos = 0;
        this.idxCols = 0;
        this.idxNors = 0;
        this.idxTexCoords = 0;
        this.maxVertices = maxVertices;
        if(Gdx.graphics.isGL20Available()) {
            throw new GdxRuntimeException("ImmediateModeRenderer can only be used with OpenGL ES 1.0/1.1");
        }

        this.positions = new float[maxVertices * 3];
        this.positionsBuffer = BufferUtils.newFloatBuffer(maxVertices * 3);
        this.colors = new float[maxVertices * 4];
        this.colorsBuffer = BufferUtils.newFloatBuffer(maxVertices * 4);
        this.normals = new float[maxVertices * 3];
        this.normalsBuffer = BufferUtils.newFloatBuffer(maxVertices * 3);
        this.texCoords = new float[maxVertices * 2];
        this.texCoordsBuffer = BufferUtils.newFloatBuffer(maxVertices * 2);
    }

    public ImmediateModeRenderer10() {
        this(2000);
    }

    public void begin(int primitiveType) {
        this.primitiveType = primitiveType;
        this.numVertices = 0;
        this.idxPos = 0;
        this.idxCols = 0;
        this.idxNors = 0;
        this.idxTexCoords = 0;
        this.hasCols = false;
        this.hasNors = false;
        this.hasTexCoords = false;
    }

    public void begin(Matrix4 projModelView, int primitiveType) {
        GL10 v0 = Gdx.gl10;
        v0.glMatrixMode(5889);
        v0.glLoadMatrixf(projModelView.val, 0);
        v0.glMatrixMode(5888);
        v0.glLoadIdentity();
        this.begin(primitiveType);
    }

    public void color(float r, float g, float b, float a) {
        this.colors[this.idxCols] = r;
        this.colors[this.idxCols + 1] = g;
        this.colors[this.idxCols + 2] = b;
        this.colors[this.idxCols + 3] = a;
        this.hasCols = true;
    }

    public void dispose() {
    }

    public void end() {
        int v8 = 32888;
        int v7 = 32886;
        int v6 = 32885;
        int v5 = 5126;
        if(this.idxPos != 0) {
            GL10 v0 = Gdx.gl10;
            v0.glEnableClientState(32884);
            this.positionsBuffer.clear();
            BufferUtils.copy(this.positions, this.positionsBuffer, this.idxPos, 0);
            v0.glVertexPointer(3, v5, 0, this.positionsBuffer);
            if(this.hasCols) {
                v0.glEnableClientState(v7);
                this.colorsBuffer.clear();
                BufferUtils.copy(this.colors, this.colorsBuffer, this.idxCols, 0);
                v0.glColorPointer(4, v5, 0, this.colorsBuffer);
            }

            if(this.hasNors) {
                v0.glEnableClientState(v6);
                this.normalsBuffer.clear();
                BufferUtils.copy(this.normals, this.normalsBuffer, this.idxNors, 0);
                v0.glNormalPointer(v5, 0, this.normalsBuffer);
            }

            if(this.hasTexCoords) {
                v0.glClientActiveTexture(33984);
                v0.glEnableClientState(v8);
                this.texCoordsBuffer.clear();
                BufferUtils.copy(this.texCoords, this.texCoordsBuffer, this.idxTexCoords, 0);
                v0.glTexCoordPointer(2, v5, 0, this.texCoordsBuffer);
            }

            v0.glDrawArrays(this.primitiveType, 0, this.idxPos / 3);
            if(this.hasCols) {
                v0.glDisableClientState(v7);
            }

            if(this.hasNors) {
                v0.glDisableClientState(v6);
            }

            if(!this.hasTexCoords) {
                return;
            }

            v0.glDisableClientState(v8);
        }
    }

    public int getMaxVertices() {
        return this.maxVertices;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public void normal(float x, float y, float z) {
        this.normals[this.idxNors] = x;
        this.normals[this.idxNors + 1] = y;
        this.normals[this.idxNors + 2] = z;
        this.hasNors = true;
    }

    public void texCoord(float u, float v) {
        this.texCoords[this.idxTexCoords] = u;
        this.texCoords[this.idxTexCoords + 1] = v;
        this.hasTexCoords = true;
    }

    public void vertex(float x, float y, float z) {
        float[] v0 = this.positions;
        int v1 = this.idxPos;
        this.idxPos = v1 + 1;
        v0[v1] = x;
        v0 = this.positions;
        v1 = this.idxPos;
        this.idxPos = v1 + 1;
        v0[v1] = y;
        v0 = this.positions;
        v1 = this.idxPos;
        this.idxPos = v1 + 1;
        v0[v1] = z;
        if(this.hasCols) {
            this.idxCols += 4;
        }

        if(this.hasNors) {
            this.idxNors += 3;
        }

        if(this.hasTexCoords) {
            this.idxTexCoords += 2;
        }

        ++this.numVertices;
    }

    public void vertex(Vector3 point) {
        this.vertex(point.x, point.y, point.z);
    }
}

