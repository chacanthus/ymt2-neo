// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class Group extends Actor implements Cullable {
    private final Matrix4 batchTransform;
    private final SnapshotArray children;
    private Rectangle cullingArea;
    private final Matrix3 localTransform;
    private final Matrix4 oldBatchTransform;
    private final Vector2 point;
    private boolean transform;
    private final Matrix3 worldTransform;

    public Group() {
        super();
        this.children = new SnapshotArray(true, 4, Actor.class);
        this.localTransform = new Matrix3();
        this.worldTransform = new Matrix3();
        this.batchTransform = new Matrix4();
        this.oldBatchTransform = new Matrix4();
        this.transform = true;
        this.point = new Vector2();
    }

    public void act(float delta) {
        super.act(delta);
        Object[] v0 = this.children.begin();
        int v1 = 0;
        int v2 = this.children.size;
        while(v1 < v2) {
            v0[v1].act(delta);
            ++v1;
        }

        this.children.end();
    }

    public void addActor(Actor actor) {
        actor.remove();
        this.children.add(actor);
        actor.setParent(this);
        actor.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorAfter(Actor actorAfter, Actor actor) {
        actor.remove();
        int v0 = this.children.indexOf(actorAfter, true);
        if(v0 == this.children.size) {
            this.children.add(actor);
        }
        else {
            this.children.insert(v0 + 1, actor);
        }

        actor.setParent(this);
        actor.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorAt(int index, Actor actor) {
        actor.remove();
        if(index >= this.children.size) {
            this.children.add(actor);
        }
        else {
            this.children.insert(index, actor);
        }

        actor.setParent(this);
        actor.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorBefore(Actor actorBefore, Actor actor) {
        actor.remove();
        this.children.insert(this.children.indexOf(actorBefore, true), actor);
        actor.setParent(this);
        actor.setStage(this.getStage());
        this.childrenChanged();
    }

    protected void applyTransform(Batch batch, Matrix4 transform) {
        this.oldBatchTransform.set(batch.getTransformMatrix());
        batch.setTransformMatrix(transform);
    }

    protected void childrenChanged() {
    }

    public void clear() {
        super.clear();
        this.clearChildren();
    }

    public void clearChildren() {
        Stage v5 = null;
        Object[] v0 = this.children.begin();
        int v2 = 0;
        int v3 = this.children.size;
        while(v2 < v3) {
            Object v1 = v0[v2];
            ((Actor)v1).setStage(v5);
            ((Actor)v1).setParent(((Group)v5));
            ++v2;
        }

        this.children.end();
        this.children.clear();
        this.childrenChanged();
    }

    protected Matrix4 computeTransform() {
        float v9 = 1f;
        float v0 = this.originX;
        float v1 = this.originY;
        float v3 = this.rotation;
        float v4 = this.scaleX;
        float v5 = this.scaleY;
        if(v0 != 0f || v1 != 0f) {
            this.localTransform.setToTranslation(v0, v1);
        }
        else {
            this.localTransform.idt();
        }

        if(v3 != 0f) {
            this.localTransform.rotate(v3);
        }

        if(v4 != v9 || v5 != v9) {
            this.localTransform.scale(v4, v5);
        }

        if(v0 != 0f || v1 != 0f) {
            this.localTransform.translate(-v0, -v1);
        }

        this.localTransform.trn(this.x, this.y);
        Group v2 = this.getParent();
        while(v2 != null) {
            if(v2.transform) {
                break;
            }

            v2 = v2.getParent();
        }

        if(v2 != null) {
            this.worldTransform.set(v2.worldTransform);
            this.worldTransform.mul(this.localTransform);
        }
        else {
            this.worldTransform.set(this.localTransform);
        }

        this.batchTransform.set(this.worldTransform);
        return this.batchTransform;
    }

    public void draw(Batch batch, float parentAlpha) {
        if(this.transform) {
            this.applyTransform(batch, this.computeTransform());
        }

        this.drawChildren(batch, parentAlpha);
        if(this.transform) {
            this.resetTransform(batch);
        }
    }

    protected void drawChildren(Batch batch, float parentAlpha) {
        float v15;
        float v14;
        float v11;
        float v10;
        Object v3;
        int v13;
        int v12;
        parentAlpha *= this.color.a;
        SnapshotArray v4 = this.children;
        Object[] v2 = v4.begin();
        Rectangle v9 = this.cullingArea;
        if(v9 != null) {
            float v6 = v9.x;
            float v7 = v6 + v9.width;
            float v5 = v9.y;
            float v8 = v5 + v9.height;
            if(this.transform) {
                v12 = 0;
                v13 = v4.size;
                while(v12 < v13) {
                    v3 = v2[v12];
                    if(((Actor)v3).isVisible()) {
                        v10 = ((Actor)v3).x;
                        v11 = ((Actor)v3).y;
                        if(v10 <= v7 && v11 <= v8 && ((Actor)v3).width + v10 >= v6 && ((Actor)v3).height + v11 >= v5) {
                            ((Actor)v3).draw(batch, parentAlpha);
                        }
                    }

                    ++v12;
                }

                batch.flush();
            }
            else {
                v14 = this.x;
                v15 = this.y;
                this.x = 0f;
                this.y = 0f;
                v12 = 0;
                v13 = v4.size;
                while(v12 < v13) {
                    v3 = v2[v12];
                    if(((Actor)v3).isVisible()) {
                        v10 = ((Actor)v3).x;
                        v11 = ((Actor)v3).y;
                        if(v10 <= v7 && v11 <= v8 && ((Actor)v3).width + v10 >= v6 && ((Actor)v3).height + v11 >= v5) {
                            ((Actor)v3).x = v10 + v14;
                            ((Actor)v3).y = v11 + v15;
                            ((Actor)v3).draw(batch, parentAlpha);
                            ((Actor)v3).x = v10;
                            ((Actor)v3).y = v11;
                        }
                    }

                    ++v12;
                }

                this.x = v14;
                this.y = v15;
            }
        }
        else {
            if(!this.transform) {
                goto label_119;
            }

            v12 = 0;
            v13 = v4.size;
            while(v12 < v13) {
                v3 = v2[v12];
                if(((Actor)v3).isVisible()) {
                    ((Actor)v3).draw(batch, parentAlpha);
                }

                ++v12;
            }

            batch.flush();
            goto label_50;
        label_119:
            v14 = this.x;
            v15 = this.y;
            this.x = 0f;
            this.y = 0f;
            v12 = 0;
            v13 = v4.size;
            while(v12 < v13) {
                v3 = v2[v12];
                if(((Actor)v3).isVisible()) {
                    v10 = ((Actor)v3).x;
                    v11 = ((Actor)v3).y;
                    ((Actor)v3).x = v10 + v14;
                    ((Actor)v3).y = v11 + v15;
                    ((Actor)v3).draw(batch, parentAlpha);
                    ((Actor)v3).x = v10;
                    ((Actor)v3).y = v11;
                }

                ++v12;
            }

            this.x = v14;
            this.y = v15;
        }

    label_50:
        v4.end();
    }

    public Actor findActor(String name) {
        Actor v5_1;
        SnapshotArray v2 = this.children;
        int v3 = 0;
        int v4 = ((Array)v2).size;
        while(true) {
            if(v3 >= v4) {
                break;
            }
            else if(name.equals(((Array)v2).get(v3).getName())) {
                Object v5 = ((Array)v2).get(v3);
            }
            else {
                ++v3;
                continue;
            }

            goto label_9;
        }

        v3 = 0;
        v4 = ((Array)v2).size;
        while(v3 < v4) {
            Object v1 = ((Array)v2).get(v3);
            if((v1 instanceof Group)) {
                Actor v0 = ((Group)v1).findActor(name);
                if(v0 != null) {
                    v5_1 = v0;
                    goto label_9;
                }
            }

            ++v3;
        }

        v5_1 = null;
    label_9:
        return v5_1;
    }

    public SnapshotArray getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        boolean v0;
        if(this.children.size > 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public Actor hit(float x, float y, boolean touchable) {
        Actor v2;
        if(!touchable || this.getTouchable() != Touchable.disabled) {
            SnapshotArray v1 = this.children;
            int v3;
            for(v3 = ((Array)v1).size - 1; v3 >= 0; --v3) {
                Object v0 = ((Array)v1).get(v3);
                if(((Actor)v0).isVisible()) {
                    ((Actor)v0).parentToLocalCoordinates(this.point.set(x, y));
                    v2 = ((Actor)v0).hit(this.point.x, this.point.y, touchable);
                    if(v2 != null) {
                        goto label_5;
                    }
                }
            }

            v2 = super.hit(x, y, touchable);
        }
        else {
            v2 = null;
        }

    label_5:
        return v2;
    }

    public boolean isTransform() {
        return this.transform;
    }

    public Vector2 localToDescendantCoordinates(Actor descendant, Vector2 localCoords) {
        Group v0 = descendant.getParent();
        if(v0 == null) {
            throw new IllegalArgumentException("Child is not a descendant: " + descendant);
        }

        if(v0 != this) {
            this.localToDescendantCoordinates(((Actor)v0), localCoords);
        }

        descendant.parentToLocalCoordinates(localCoords);
        return localCoords;
    }

    private void print(String indent) {
        Object[] v0 = this.children.begin();
        int v1 = 0;
        int v2 = this.children.size;
        while(v1 < v2) {
            System.out.println(indent + v0[v1]);
            if((v0[v1] instanceof Group)) {
                v0[v1].print(indent + "|  ");
            }

            ++v1;
        }

        this.children.end();
    }

    public void print() {
        this.print("");
    }

    public boolean removeActor(Actor actor) {
        Group v3 = null;
        boolean v1 = true;
        if(!this.children.removeValue(actor, true)) {
            v1 = false;
        }
        else {
            Stage v0 = this.getStage();
            if(v0 != null) {
                v0.unfocus(actor);
            }

            actor.setParent(v3);
            actor.setStage(((Stage)v3));
            this.childrenChanged();
        }

        return v1;
    }

    protected void resetTransform(Batch batch) {
        batch.setTransformMatrix(this.oldBatchTransform);
    }

    public void setCullingArea(Rectangle cullingArea) {
        this.cullingArea = cullingArea;
    }

    protected void setStage(Stage stage) {
        super.setStage(stage);
        SnapshotArray v0 = this.children;
        int v1 = 0;
        int v2 = ((Array)v0).size;
        while(v1 < v2) {
            ((Array)v0).get(v1).setStage(stage);
            ++v1;
        }
    }

    public void setTransform(boolean transform) {
        this.transform = transform;
    }

    public boolean swapActor(int first, int second) {
        boolean v1 = false;
        int v0 = this.children.size;
        if(first >= 0 && first < v0 && second >= 0 && second < v0) {
            this.children.swap(first, second);
            v1 = true;
        }

        return v1;
    }

    public boolean swapActor(Actor first, Actor second) {
        int v4 = -1;
        boolean v2 = true;
        int v0 = this.children.indexOf(first, true);
        int v1 = this.children.indexOf(second, true);
        if(v0 == v4 || v1 == v4) {
            v2 = false;
        }
        else {
            this.children.swap(v0, v1);
        }

        return v2;
    }
}

