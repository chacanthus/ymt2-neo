// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class VertexAttributes implements Iterable {
    class ReadonlyIterable implements Iterable {
        private final Object[] array;
        private ReadonlyIterator iterator1;
        private ReadonlyIterator iterator2;

        public ReadonlyIterable(Object[] arg1) {
            super();
            this.array = arg1;
        }

        public Iterator iterator() {
            ReadonlyIterator v0;
            if(this.iterator1 == null) {
                this.iterator1 = new ReadonlyIterator(this.array);
                this.iterator2 = new ReadonlyIterator(this.array);
            }

            if(!this.iterator1.valid) {
                this.iterator1.index = 0;
                this.iterator1.valid = true;
                this.iterator2.valid = false;
                v0 = this.iterator1;
            }
            else {
                this.iterator2.index = 0;
                this.iterator2.valid = true;
                this.iterator1.valid = false;
                v0 = this.iterator2;
            }

            return ((Iterator)v0);
        }
    }

    class ReadonlyIterator implements Iterable, Iterator {
        private final Object[] array;
        int index;
        boolean valid;

        public ReadonlyIterator(Object[] arg2) {
            super();
            this.valid = true;
            this.array = arg2;
        }

        public boolean hasNext() {
            boolean v0;
            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }

            if(this.index < this.array.length) {
                v0 = true;
            }
            else {
                v0 = false;
            }

            return v0;
        }

        public Iterator iterator() {
            return this;
        }

        public Object next() {
            if(this.index >= this.array.length) {
                throw new NoSuchElementException(String.valueOf(this.index));
            }

            if(!this.valid) {
                throw new GdxRuntimeException("#iterator() cannot be used nested.");
            }

            Object[] v0 = this.array;
            int v1 = this.index;
            this.index = v1 + 1;
            return v0[v1];
        }

        public void remove() {
            throw new GdxRuntimeException("Remove not allowed.");
        }

        public void reset() {
            this.index = 0;
        }
    }

    public final class Usage {
        public static final int BiNormal = 256;
        public static final int BoneWeight = 64;
        public static final int Color = 2;
        public static final int ColorPacked = 4;
        public static final int Generic = 32;
        public static final int Normal = 8;
        public static final int Position = 1;
        public static final int Tangent = 128;
        public static final int TextureCoordinates = 16;

        public Usage() {
            super();
        }
    }

    private final VertexAttribute[] attributes;
    private ReadonlyIterable iterable;
    private long mask;
    public final int vertexSize;

    public VertexAttributes(VertexAttribute[] attributes) {
        super();
        this.mask = -1;
        if(attributes.length == 0) {
            throw new IllegalArgumentException("attributes must be >= 1");
        }

        VertexAttribute[] v1 = new VertexAttribute[attributes.length];
        int v0;
        for(v0 = 0; v0 < attributes.length; ++v0) {
            v1[v0] = attributes[v0];
        }

        this.attributes = v1;
        if(!Gdx.graphics.isGL20Available()) {
            this.checkGLES1Validity();
        }

        this.vertexSize = this.calculateOffsets();
    }

    private int calculateOffsets() {
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < this.attributes.length; ++v2) {
            VertexAttribute v0 = this.attributes[v2];
            v0.offset = v1;
            if(v0.usage == 4) {
                v1 += 4;
            }
            else {
                v1 += v0.numComponents * 4;
            }
        }

        return v1;
    }

    private void checkGLES1Validity() {
        int v7 = 4;
        int v4 = 0;
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < this.attributes.length; ++v2) {
            VertexAttribute v0 = this.attributes[v2];
            if(v0.usage == 1) {
                if(v4 != 0) {
                    throw new IllegalArgumentException("two position attributes were specified");
                }
                else {
                    v4 = 1;
                }
            }

            if(v0.usage == 8 && 0 != 0) {
                throw new IllegalArgumentException("two normal attributes were specified");
            }

            if(v0.usage == 2 || v0.usage == v7) {
                if(v0.numComponents != v7) {
                    throw new IllegalArgumentException("color attribute must have 4 components");
                }
                else if(v1 != 0) {
                    throw new IllegalArgumentException("two color attributes were specified");
                }
                else {
                    v1 = 1;
                }
            }
        }

        if(v4 == 0) {
            throw new IllegalArgumentException("no position attribute was specified");
        }
    }

    public boolean equals(Object obj) {
        boolean v2 = false;
        if((obj instanceof VertexAttributes)) {
            Object v1 = obj;
            if(this.attributes.length == ((VertexAttributes)v1).size()) {
                int v0 = 0;
                while(true) {
                    if(v0 >= this.attributes.length) {
                        break;
                    }
                    else if(this.attributes[v0].equals(((VertexAttributes)v1).attributes[v0])) {
                        ++v0;
                        continue;
                    }

                    goto label_3;
                }

                v2 = true;
            }
        }

    label_3:
        return v2;
    }

    public VertexAttribute findByUsage(int usage) {
        VertexAttribute v2;
        int v1 = this.size();
        int v0 = 0;
        while(true) {
            if(v0 >= v1) {
                break;
            }
            else if(this.get(v0).usage == usage) {
                v2 = this.get(v0);
            }
            else {
                ++v0;
                continue;
            }

            goto label_7;
        }

        v2 = null;
    label_7:
        return v2;
    }

    public VertexAttribute get(int index) {
        return this.attributes[index];
    }

    public long getMask() {
        if(this.mask == -1) {
            long v1 = 0;
            int v0;
            for(v0 = 0; v0 < this.attributes.length; ++v0) {
                v1 |= ((long)this.attributes[v0].usage);
            }

            this.mask = v1;
        }

        return this.mask;
    }

    public int getOffset(int usage) {
        int v1;
        VertexAttribute v0 = this.findByUsage(usage);
        if(v0 == null) {
            v1 = 0;
        }
        else {
            v1 = v0.offset / 4;
        }

        return v1;
    }

    public Iterator iterator() {
        if(this.iterable == null) {
            this.iterable = new ReadonlyIterable(this.attributes);
        }

        return this.iterable.iterator();
    }

    public int size() {
        return this.attributes.length;
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder();
        v0.append("[");
        int v1;
        for(v1 = 0; v1 < this.attributes.length; ++v1) {
            v0.append("(");
            v0.append(this.attributes[v1].alias);
            v0.append(", ");
            v0.append(this.attributes[v1].usage);
            v0.append(", ");
            v0.append(this.attributes[v1].numComponents);
            v0.append(", ");
            v0.append(this.attributes[v1].offset);
            v0.append(")");
            v0.append("\n");
        }

        v0.append("]");
        return v0.toString();
    }
}

