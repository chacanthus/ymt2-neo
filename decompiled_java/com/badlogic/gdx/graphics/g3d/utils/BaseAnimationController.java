// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap$Entry;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool$Poolable;
import java.util.Iterator;

public class BaseAnimationController {
    public final class Transform implements Poolable {
        public final Quaternion rotation;
        public final Vector3 scale;
        public final Vector3 translation;

        public Transform() {
            super();
            this.translation = new Vector3();
            this.rotation = new Quaternion();
            this.scale = new Vector3(1f, 1f, 1f);
        }

        public Transform idt() {
            this.translation.set(0f, 0f, 0f);
            this.rotation.idt();
            this.scale.set(1f, 1f, 1f);
            return this;
        }

        public Transform lerp(Vector3 targetT, Quaternion targetR, Vector3 targetS, float alpha) {
            this.translation.lerp(targetT, alpha);
            this.rotation.slerp(targetR, alpha);
            this.scale.lerp(targetS, alpha);
            return this;
        }

        public Transform lerp(Transform target, float alpha) {
            return this.lerp(target.translation, target.rotation, target.scale, alpha);
        }

        public void reset() {
            this.idt();
        }

        public Transform set(Vector3 t, Quaternion r, Vector3 s) {
            this.translation.set(t);
            this.rotation.set(r);
            this.scale.set(s);
            return this;
        }

        public Transform set(Transform other) {
            return this.set(other.translation, other.rotation, other.scale);
        }

        public Matrix4 toMatrix4(Matrix4 out) {
            return out.set(this.translation, this.rotation, this.scale);
        }
    }

    private boolean applying;
    public final ModelInstance target;
    private static final Transform tmpT;
    private final Pool transformPool;
    private static final ObjectMap transforms;

    static  {
        BaseAnimationController.transforms = new ObjectMap();
        BaseAnimationController.tmpT = new Transform();
    }

    public BaseAnimationController(ModelInstance target) {
        super();
        this.transformPool = new Pool() {
            protected Transform newObject() {
                return new Transform();
            }

            protected Object newObject() {
                return this.newObject();
            }
        };
        this.applying = false;
        this.target = target;
    }

    protected void apply(Animation animation, float time, float weight) {
        if(!this.applying) {
            throw new GdxRuntimeException("You must call begin() before adding an animation");
        }

        BaseAnimationController.applyAnimation(BaseAnimationController.transforms, this.transformPool, weight, animation, time);
    }

    protected static void applyAnimation(ObjectMap arg15, Pool arg16, float alpha, Animation animation, float time) {
        Iterator v4 = animation.nodeAnimations.iterator();
        while(true) {
            if(!v4.hasNext()) {
                return;
            }

            Object v7 = v4.next();
            Node v6 = ((NodeAnimation)v7).node;
            v6.isAnimated = true;
            int v5 = ((NodeAnimation)v7).keyframes.size - 1;
            int v1 = 0;
            int v8 = -1;
            int v3;
            for(v3 = 0; v3 < v5; ++v3) {
                if(time >= ((NodeAnimation)v7).keyframes.get(v3).keytime && time <= ((NodeAnimation)v7).keyframes.get(v3 + 1).keytime) {
                    v1 = v3;
                    v8 = v3 + 1;
                    break;
                }
            }

            Transform v11 = BaseAnimationController.tmpT;
            Object v2 = ((NodeAnimation)v7).keyframes.get(v1);
            v11.set(((NodeKeyframe)v2).translation, ((NodeKeyframe)v2).rotation, ((NodeKeyframe)v2).scale);
            if(v8 > v1) {
                Object v9 = ((NodeAnimation)v7).keyframes.get(v8);
                v11.lerp(((NodeKeyframe)v9).translation, ((NodeKeyframe)v9).rotation, ((NodeKeyframe)v9).scale, (time - ((NodeKeyframe)v2).keytime) / (((NodeKeyframe)v9).keytime - ((NodeKeyframe)v2).keytime));
            }

            if(arg15 != null) {
                goto label_53;
            }

            v11.toMatrix4(v6.localTransform);
            continue;
        label_53:
            if(!arg15.containsKey(v6)) {
                goto label_64;
            }

            if(alpha != 1f) {
                goto label_60;
            }

            arg15.get(v6).set(v11);
            continue;
        label_60:
            arg15.get(v6).lerp(v11, alpha);
            continue;
        label_64:
            arg15.put(v6, arg16.obtain().set(v11));
        }
    }

    protected void applyAnimation(Animation animation, float time) {
        ObjectMap v1 = null;
        if(this.applying) {
            throw new GdxRuntimeException("Call end() first");
        }

        BaseAnimationController.applyAnimation(v1, ((Pool)v1), 1f, animation, time);
        this.target.calculateTransforms();
    }

    protected void applyAnimations(Animation anim1, float time1, Animation anim2, float time2, float weight) {
        float v1 = 1f;
        if(anim2 == null || weight == 0f) {
            this.applyAnimation(anim1, time1);
        }
        else {
            if(anim1 != null && weight != v1) {
                if(this.applying) {
                    throw new GdxRuntimeException("Call end() first");
                }
                else {
                    this.begin();
                    this.apply(anim1, time1, v1);
                    this.apply(anim2, time2, weight);
                    this.end();
                    return;
                }
            }

            this.applyAnimation(anim2, time2);
        }
    }

    protected void begin() {
        if(this.applying) {
            throw new GdxRuntimeException("You must call end() after each call to being()");
        }

        this.applying = true;
    }

    protected void end() {
        if(!this.applying) {
            throw new GdxRuntimeException("You must call begin() first");
        }

        Iterator v1 = BaseAnimationController.transforms.entries().iterator();
        while(v1.hasNext()) {
            Object v0 = v1.next();
            ((Entry)v0).value.toMatrix4(((Entry)v0).key.localTransform);
            this.transformPool.free(((Entry)v0).value);
        }

        BaseAnimationController.transforms.clear();
        this.target.calculateTransforms();
        this.applying = false;
    }

    protected void removeAnimation(Animation animation) {
        Iterator v0 = animation.nodeAnimations.iterator();
        while(v0.hasNext()) {
            v0.next().node.isAnimated = false;
        }
    }
}

