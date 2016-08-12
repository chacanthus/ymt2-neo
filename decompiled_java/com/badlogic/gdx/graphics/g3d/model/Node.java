// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

public class Node {
    public final Array children;
    public final Matrix4 globalTransform;
    public String id;
    public boolean isAnimated;
    public final Matrix4 localTransform;
    public Node parent;
    public Array parts;
    public final Quaternion rotation;
    public final Vector3 scale;
    public final Vector3 translation;

    public Node() {
        super();
        this.children = new Array(2);
        this.translation = new Vector3();
        this.rotation = new Quaternion(0f, 0f, 0f, 1f);
        this.scale = new Vector3(1f, 1f, 1f);
        this.localTransform = new Matrix4();
        this.globalTransform = new Matrix4();
        this.parts = new Array(2);
    }

    public void calculateBoneTransforms(boolean recursive) {
        Object v4;
        Iterator v2 = this.parts.iterator();
        do {
        label_2:
            if(!v2.hasNext()) {
                goto label_31;
            }

            v4 = v2.next();
            if(((NodePart)v4).invBoneBindTransforms == null) {
                goto label_2;
            }

            if(((NodePart)v4).bones == null) {
                goto label_2;
            }
        }
        while(((NodePart)v4).invBoneBindTransforms.size != ((NodePart)v4).bones.length);

        int v3 = ((NodePart)v4).invBoneBindTransforms.size;
        int v1 = 0;
        goto label_17;
    label_31:
        if(recursive) {
            v2 = this.children.iterator();
            while(v2.hasNext()) {
                v2.next().calculateBoneTransforms(true);
            }
        }

        return;
        while(true) {
        label_17:
            if(v1 >= v3) {
                goto label_2;
            }

            ((NodePart)v4).bones[v1].set(((NodePart)v4).invBoneBindTransforms.keys[v1].globalTransform).mul(((NodePart)v4).invBoneBindTransforms.values[v1]);
            ++v1;
        }
    }

    public BoundingBox calculateBoundingBox(BoundingBox out) {
        out.inf();
        return this.extendBoundingBox(out);
    }

    public BoundingBox calculateBoundingBox(BoundingBox out, boolean transform) {
        out.inf();
        return this.extendBoundingBox(out, transform);
    }

    public Matrix4 calculateLocalTransform() {
        if(!this.isAnimated) {
            this.localTransform.set(this.translation, this.rotation, this.scale);
        }

        return this.localTransform;
    }

    public void calculateTransforms(boolean recursive) {
        this.calculateLocalTransform();
        this.calculateWorldTransform();
        if(recursive) {
            Iterator v1 = this.children.iterator();
            while(v1.hasNext()) {
                v1.next().calculateTransforms(true);
            }
        }
    }

    public Matrix4 calculateWorldTransform() {
        if(this.parent == null) {
            this.globalTransform.set(this.localTransform);
        }
        else {
            this.globalTransform.set(this.parent.globalTransform).mul(this.localTransform);
        }

        return this.globalTransform;
    }

    public BoundingBox extendBoundingBox(BoundingBox out) {
        return this.extendBoundingBox(out, true);
    }

    public BoundingBox extendBoundingBox(BoundingBox out, boolean transform) {
        int v3 = this.parts.size;
        int v1;
        for(v1 = 0; v1 < v3; ++v1) {
            MeshPart v2 = this.parts.get(v1).meshPart;
            if(transform) {
                v2.mesh.extendBoundingBox(out, v2.indexOffset, v2.numVertices, this.globalTransform);
            }
            else {
                v2.mesh.extendBoundingBox(out, v2.indexOffset, v2.numVertices);
            }
        }

        int v0 = this.children.size;
        for(v1 = 0; v1 < v0; ++v1) {
            this.children.get(v1).extendBoundingBox(out);
        }

        return out;
    }

    public Node getChild(String id, boolean recursive, boolean ignoreCase) {
        return Node.getNode(this.children, id, recursive, ignoreCase);
    }

    public static Node getNode(Array arg5, String id, boolean recursive, boolean ignoreCase) {
        Node v2_1;
        Object v2;
        int v0;
        int v1 = arg5.size;
        if(ignoreCase) {
            v0 = 0;
            while(true) {
                if(v0 < v1) {
                    v2 = arg5.get(v0);
                    if(!((Node)v2).id.equalsIgnoreCase(id)) {
                        ++v0;
                        continue;
                    }
                }
                else {
                    goto label_19;
                }

                break;
            }
        }
        else {
            v0 = 0;
            while(true) {
                if(v0 < v1) {
                    v2 = arg5.get(v0);
                    if(!((Node)v2).id.equals(id)) {
                        ++v0;
                        continue;
                    }
                }
                else {
                    break;
                }

                goto label_8;
            }

        label_19:
            if(recursive) {
                v0 = 0;
                while(v0 < v1) {
                    v2_1 = Node.getNode(arg5.get(v0).children, id, true, ignoreCase);
                    if(v2_1 == null) {
                        ++v0;
                        continue;
                    }
                    else {
                        goto label_8;
                    }
                }
            }

            v2_1 = null;
        }

    label_8:
        return ((Node)v2);
    }
}

