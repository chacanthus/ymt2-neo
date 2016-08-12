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
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class VertexBufferObjectSubData implements VertexData {
    final VertexAttributes attributes;
    final FloatBuffer buffer;
    int bufferHandle;
    final ByteBuffer byteBuffer;
    boolean isBound;
    final boolean isDirect;
    boolean isDirty;
    final boolean isStatic;
    static final IntBuffer tmpHandle;
    final int usage;

    static  {
        VertexBufferObjectSubData.tmpHandle = BufferUtils.newIntBuffer(1);
    }

    public VertexBufferObjectSubData(boolean isStatic, int numVertices, VertexAttribute[] attributes) {
        int v0;
        super();
        this.isDirty = false;
        this.isBound = false;
        this.isStatic = isStatic;
        this.attributes = new VertexAttributes(attributes);
        this.byteBuffer = BufferUtils.newByteBuffer(this.attributes.vertexSize * numVertices);
        this.isDirect = true;
        if(isStatic) {
            v0 = 35044;
        }
        else {
            v0 = 35048;
        }

        this.usage = v0;
        this.buffer = this.byteBuffer.asFloatBuffer();
        this.bufferHandle = this.createBufferObject();
        this.buffer.flip();
        this.byteBuffer.flip();
    }

    public void bind() {
        int v10 = 34962;
        int v9 = 5126;
        GL11 v2 = Gdx.gl11;
        v2.glBindBuffer(v10, this.bufferHandle);
        if(this.isDirty) {
            this.byteBuffer.limit(this.buffer.limit() * 4);
            v2.glBufferSubData(v10, 0, this.byteBuffer.limit(), this.byteBuffer);
            this.isDirty = false;
        }

        int v5 = 0;
        int v4 = this.attributes.size();
        int v3;
        for(v3 = 0; v3 < v4; ++v3) {
            VertexAttribute v0 = this.attributes.get(v3);
            switch(v0.usage) {
                case 1: {
                    goto label_35;
                }
                case 2: 
                case 4: {
                    goto label_44;
                }
                case 8: {
                    goto label_57;
                }
                case 16: {
                    goto label_64;
                }
            }

            throw new GdxRuntimeException("unkown vertex attribute type: " + v0.usage);
        label_35:
            v2.glEnableClientState(32884);
            v2.glVertexPointer(v0.numComponents, v9, this.attributes.vertexSize, v0.offset);
            goto label_42;
        label_57:
            v2.glEnableClientState(32885);
            v2.glNormalPointer(v9, this.attributes.vertexSize, v0.offset);
            goto label_42;
        label_44:
            int v1 = 5126;
            if(v0.usage == 4) {
                v1 = 5121;
            }

            v2.glEnableClientState(32886);
            v2.glColorPointer(v0.numComponents, v1, this.attributes.vertexSize, v0.offset);
            goto label_42;
        label_64:
            v2.glClientActiveTexture(33984 + v5);
            v2.glEnableClientState(32888);
            v2.glTexCoordPointer(v0.numComponents, v9, this.attributes.vertexSize, v0.offset);
            ++v5;
        label_42:
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
                Gdx.gl20.glBufferSubData(v4, 0, this.byteBuffer.limit(), this.byteBuffer);
            }
            else {
                Gdx.gl11.glBufferSubData(v4, 0, this.byteBuffer.limit(), this.byteBuffer);
            }

            this.isDirty = false;
        }
    }

    private int createBufferObject() {
        Buffer v5 = null;
        int v3 = 34962;
        if(Gdx.gl20 != null) {
            Gdx.gl20.glGenBuffers(1, VertexBufferObjectSubData.tmpHandle);
            Gdx.gl20.glBindBuffer(v3, VertexBufferObjectSubData.tmpHandle.get(0));
            Gdx.gl20.glBufferData(v3, this.byteBuffer.capacity(), v5, this.usage);
            Gdx.gl20.glBindBuffer(v3, 0);
        }
        else {
            Gdx.gl11.glGenBuffers(1, VertexBufferObjectSubData.tmpHandle);
            Gdx.gl11.glBindBuffer(v3, VertexBufferObjectSubData.tmpHandle.get(0));
            Gdx.gl11.glBufferData(v3, this.byteBuffer.capacity(), v5, this.usage);
            Gdx.gl11.glBindBuffer(v3, 0);
        }

        return VertexBufferObjectSubData.tmpHandle.get(0);
    }

    public void dispose() {
        int v5 = 34962;
        if(Gdx.gl20 != null) {
            VertexBufferObjectSubData.tmpHandle.clear();
            VertexBufferObjectSubData.tmpHandle.put(this.bufferHandle);
            VertexBufferObjectSubData.tmpHandle.flip();
            GL20 v0 = Gdx.gl20;
            v0.glBindBuffer(v5, 0);
            v0.glDeleteBuffers(1, VertexBufferObjectSubData.tmpHandle);
            this.bufferHandle = 0;
        }
        else {
            VertexBufferObjectSubData.tmpHandle.clear();
            VertexBufferObjectSubData.tmpHandle.put(this.bufferHandle);
            VertexBufferObjectSubData.tmpHandle.flip();
            GL11 v0_1 = Gdx.gl11;
            v0_1.glBindBuffer(v5, 0);
            v0_1.glDeleteBuffers(1, VertexBufferObjectSubData.tmpHandle);
            this.bufferHandle = 0;
        }
    }

    public VertexAttributes getAttributes() {
        return this.attributes;
    }

    public FloatBuffer getBuffer() {
        this.isDirty = true;
        return this.buffer;
    }

    public int getBufferHandle() {
        return this.bufferHandle;
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
        if(this.isDirect) {
            BufferUtils.copy(vertices, this.byteBuffer, count, offset);
            this.buffer.position(0);
            this.buffer.limit(count);
        }
        else {
            this.buffer.clear();
            this.buffer.put(vertices, offset, count);
            this.buffer.flip();
            this.byteBuffer.position(0);
            this.byteBuffer.limit(this.buffer.limit() << 2);
        }

        this.bufferChanged();
    }

    public void unbind() {
        GL11 v1 = Gdx.gl11;
        int v4 = 0;
        int v3 = this.attributes.size();
        int v2;
        for(v2 = 0; v2 < v3; ++v2) {
            VertexAttribute v0 = this.attributes.get(v2);
            switch(v0.usage) {
                case 1: {
                    goto label_21;
                }
                case 2: 
                case 4: {
                    goto label_19;
                }
                case 8: {
                    goto label_23;
                }
                case 16: {
                    goto label_26;
                }
            }

            throw new GdxRuntimeException("unkown vertex attribute type: " + v0.usage);
        label_19:
            v1.glDisableClientState(32886);
            goto label_21;
        label_23:
            v1.glDisableClientState(32885);
            goto label_21;
        label_26:
            v1.glClientActiveTexture(33984 + v4);
            v1.glDisableClientState(32888);
            ++v4;
        label_21:
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
        if(this.isDirect) {
            int v0 = this.byteBuffer.position();
            this.byteBuffer.position(targetOffset * 4);
            BufferUtils.copy(vertices, sourceOffset, count, this.byteBuffer);
            this.byteBuffer.position(v0);
            this.bufferChanged();
            return;
        }

        throw new GdxRuntimeException("Buffer must be allocated direct.");
    }
}

