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
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class VertexBufferObject implements VertexData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    boolean isBound;
    boolean isDirty;
    final boolean isStatic;
    static final IntBuffer tmpHandle;
    final int usage;

    static  {
        VertexBufferObject.tmpHandle = BufferUtils.newIntBuffer(1);
    }

    public VertexBufferObject(boolean isStatic, int numVertices, VertexAttribute[] attributes) {
        this(isStatic, numVertices, new VertexAttributes(attributes));
    }

    public VertexBufferObject(boolean isStatic, int numVertices, VertexAttributes attributes) {
        int v0;
        super();
        this.isDirty = false;
        this.isBound = false;
        this.isStatic = isStatic;
        this.attributes = attributes;
        this.byteBuffer = BufferUtils.newUnsafeByteBuffer(this.attributes.vertexSize * numVertices);
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.buffer.flip();
        this.byteBuffer.flip();
        this.bufferHandle = this.createBufferObject();
        if(isStatic) {
            v0 = 35044;
        }
        else {
            v0 = 35048;
        }

        this.usage = v0;
    }

    public void bind() {
        int v10 = 34962;
        int v9 = 5126;
        GL11 v2 = Gdx.gl11;
        v2.glBindBuffer(v10, this.bufferHandle);
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 4);
            v2.glBufferData(v10, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }

        int v5 = 0;
        int v4 = this.attributes.size();
        int v3;
        for(v3 = 0; v3 < v4; ++v3) {
            VertexAttribute v0 = this.attributes.get(v3);
            switch(v0.usage) {
                case 1: {
                    v2.glEnableClientState(32884);
                    v2.glVertexPointer(v0.numComponents, v9, this.attributes.vertexSize, v0.offset);
                    break;
                }
                case 2: 
                case 4: {
                    int v1 = 5126;
                    if(v0.usage == 4) {
                        v1 = 5121;
                    }

                    v2.glEnableClientState(32886);
                    v2.glColorPointer(v0.numComponents, v1, this.attributes.vertexSize, v0.offset);
                    break;
                }
                case 8: {
                    v2.glEnableClientState(32885);
                    v2.glNormalPointer(v9, this.attributes.vertexSize, v0.offset);
                    break;
                }
                case 16: {
                    v2.glClientActiveTexture(33984 + v5);
                    v2.glEnableClientState(32888);
                    v2.glTexCoordPointer(v0.numComponents, v9, this.attributes.vertexSize, v0.offset);
                    ++v5;
                    break;
                }
            }
        }

        this.isBound = true;
    }

    public void bind(ShaderProgram shader) {
        this.bind(shader, null);
    }

    public void bind(ShaderProgram shader, int[] locations) {
        int v1;
        VertexAttribute v7;
        GL20 v8 = Gdx.gl20;
        v8.glBindBuffer(34962, this.bufferHandle);
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 4);
            v8.glBufferData(34962, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            this.isDirty = false;
        }

        int v10 = this.attributes.size();
        if(locations == null) {
            int v9;
            for(v9 = 0; v9 < v10; ++v9) {
                v7 = this.attributes.get(v9);
                v1 = shader.getAttributeLocation(v7.alias);
                if(v1 >= 0) {
                    shader.enableVertexAttribute(v1);
                    if(v7.usage == 4) {
                        shader.setVertexAttribute(v1, v7.numComponents, 5121, true, this.attributes.vertexSize, v7.offset);
                    }
                    else {
                        shader.setVertexAttribute(v1, v7.numComponents, 5126, false, this.attributes.vertexSize, v7.offset);
                    }
                }
            }
        }
        else {
            for(v9 = 0; v9 < v10; ++v9) {
                v7 = this.attributes.get(v9);
                v1 = locations[v9];
                if(v1 >= 0) {
                    shader.enableVertexAttribute(v1);
                    if(v7.usage == 4) {
                        shader.setVertexAttribute(v1, v7.numComponents, 5121, true, this.attributes.vertexSize, v7.offset);
                    }
                    else {
                        shader.setVertexAttribute(v1, v7.numComponents, 5126, false, this.attributes.vertexSize, v7.offset);
                    }
                }
            }
        }

        this.isBound = true;
    }

    private void bufferChanged() {
        int v4 = 34962;
        if(this.isBound) {
            if(Gdx.gl20 != null) {
                Gdx.gl20.glBufferData(v4, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            }
            else {
                Gdx.gl11.glBufferData(v4, this.byteBuffer.limit(), this.byteBuffer, this.usage);
            }

            this.isDirty = false;
        }
    }

    private int createBufferObject() {
        if(Gdx.gl20 != null) {
            Gdx.gl20.glGenBuffers(1, VertexBufferObject.tmpHandle);
        }
        else {
            Gdx.gl11.glGenBuffers(1, VertexBufferObject.tmpHandle);
        }

        return VertexBufferObject.tmpHandle.get(0);
    }

    public void dispose() {
        int v5 = 34962;
        if(Gdx.gl20 != null) {
            VertexBufferObject.tmpHandle.clear();
            VertexBufferObject.tmpHandle.put(this.bufferHandle);
            VertexBufferObject.tmpHandle.flip();
            GL20 v0 = Gdx.gl20;
            v0.glBindBuffer(v5, 0);
            v0.glDeleteBuffers(1, VertexBufferObject.tmpHandle);
            this.bufferHandle = 0;
        }
        else {
            VertexBufferObject.tmpHandle.clear();
            VertexBufferObject.tmpHandle.put(this.bufferHandle);
            VertexBufferObject.tmpHandle.flip();
            GL11 v0_1 = Gdx.gl11;
            v0_1.glBindBuffer(v5, 0);
            v0_1.glDeleteBuffers(1, VertexBufferObject.tmpHandle);
            this.bufferHandle = 0;
        }

        BufferUtils.disposeUnsafeByteBuffer(this.byteBuffer);
    }

    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    public FloatBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    public int getNumMaxVertices() {
        return this.byteBuffer.capacity() / this.attributes.vertexSize;
    }

    public int getNumVertices() {
        return this.buffer.limit() * 4 / this.attributes.vertexSize;
    }

    public void invalidate() {
        this.bufferHandle = this.createBufferObject();
        this.isDirty = true;
    }

    public void setVertices(float[] vertices, int offset, int count) {
        this.isDirty = true;
        BufferUtils.copy(vertices, this.byteBuffer, count, offset);
        this.buffer.position(0);
        this.buffer.limit(count);
        this.bufferChanged();
    }

    public void unbind() {
        GL11 v1 = Gdx.gl11;
        int v4 = 0;
        int v3 = this.attributes.size();
        int v2;
        for(v2 = 0; v2 < v3; ++v2) {
            switch(this.attributes.get(v2).usage) {
                case 2: 
                case 4: {
                    v1.glDisableClientState(32886);
                    break;
                }
                case 8: {
                    v1.glDisableClientState(32885);
                    break;
                }
                case 16: {
                    v1.glClientActiveTexture(33984 + v4);
                    v1.glDisableClientState(32888);
                    ++v4;
                    break;
                }
            }
        }

        v1.glBindBuffer(34962, 0);
        this.isBound = false;
    }

    public void unbind(ShaderProgram shader) {
        this.unbind(shader, null);
    }

    public void unbind(ShaderProgram shader, int[] locations) {
        GL20 v0 = Gdx.gl20;
        int v3 = this.attributes.size();
        if(locations == null) {
            int v1;
            for(v1 = 0; v1 < v3; ++v1) {
                shader.disableVertexAttribute(this.attributes.get(v1).alias);
            }
        }
        else {
            for(v1 = 0; v1 < v3; ++v1) {
                int v2 = locations[v1];
                if(v2 >= 0) {
                    shader.disableVertexAttribute(v2);
                }
            }
        }

        v0.glBindBuffer(34962, 0);
        this.isBound = false;
    }

    public void updateVertices(int targetOffset, float[] vertices, int sourceOffset, int count) {
        this.isDirty = true;
        int v0 = this.byteBuffer.position();
        this.byteBuffer.position(targetOffset * 4);
        BufferUtils.copy(vertices, sourceOffset, count, this.byteBuffer);
        this.byteBuffer.position(v0);
        this.buffer.position(0);
        this.bufferChanged();
    }
}

