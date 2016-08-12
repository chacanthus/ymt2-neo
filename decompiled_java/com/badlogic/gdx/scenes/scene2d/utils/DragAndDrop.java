// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class DragAndDrop {
    public class Payload {
        Actor dragActor;
        Actor invalidDragActor;
        Object object;
        Actor validDragActor;

        public Payload() {
            super();
        }

        public Actor getDragActor() {
            return this.dragActor;
        }

        public Actor getInvalidDragActor() {
            return this.invalidDragActor;
        }

        public Object getObject() {
            return this.object;
        }

        public Actor getValidDragActor() {
            return this.validDragActor;
        }

        public void setDragActor(Actor dragActor) {
            this.dragActor = dragActor;
        }

        public void setInvalidDragActor(Actor invalidDragActor) {
            this.invalidDragActor = invalidDragActor;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public void setValidDragActor(Actor validDragActor) {
            this.validDragActor = validDragActor;
        }
    }

    public abstract class Source {
        public Source(Actor actor) {
            super();
            if(actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }

            this.actor = actor;
        }

        public abstract Payload dragStart(InputEvent arg0, float arg1, float arg2, int arg3);

        public void dragStop(InputEvent event, float x, float y, int pointer, Target target) {
        }

        public Actor getActor() {
            return this.actor;
        }
    }

    public abstract class Target {
        final Actor actor;

        public Target(Actor actor) {
            super();
            if(actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }

            this.actor = actor;
            Stage v0 = actor.getStage();
            if(v0 != null && actor == v0.getRoot()) {
                throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
        }

        public abstract boolean drag(Source arg0, Payload arg1, float arg2, float arg3, int arg4);

        public abstract void drop(Source arg0, Payload arg1, float arg2, float arg3, int arg4);

        public Actor getActor() {
            return this.actor;
        }

        public void reset(Source source, Payload payload) {
        }
    }

    int activePointer;
    private int button;
    Actor dragActor;
    float dragActorX;
    float dragActorY;
    long dragStartTime;
    int dragTime;
    boolean isValidTarget;
    Payload payload;
    Source source;
    ObjectMap sourceListeners;
    private float tapSquareSize;
    Target target;
    Array targets;
    static final Vector2 tmpVector;

    static  {
        DragAndDrop.tmpVector = new Vector2();
    }

    public DragAndDrop() {
        super();
        this.targets = new Array();
        this.sourceListeners = new ObjectMap();
        this.tapSquareSize = 8f;
        this.dragActorX = 14f;
        this.dragActorY = -20f;
        this.dragTime = 250;
        this.activePointer = -1;
    }

    public void addSource(Source source) {
        com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop$1 v0 = new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                Object v1;
                if(DragAndDrop.this.payload != null && pointer == DragAndDrop.this.activePointer) {
                    Stage v15 = event.getStage();
                    Touchable v10 = null;
                    if(DragAndDrop.this.dragActor != null) {
                        v10 = DragAndDrop.this.dragActor.getTouchable();
                        DragAndDrop.this.dragActor.setTouchable(Touchable.disabled);
                    }

                    Object v14 = null;
                    DragAndDrop.this.isValidTarget = false;
                    Actor v11 = event.getStage().hit(event.getStageX(), event.getStageY(), true);
                    if(v11 == null) {
                        v11 = event.getStage().hit(event.getStageX(), event.getStageY(), false);
                    }

                    if(v11 != null) {
                        int v12 = 0;
                        int v13 = DragAndDrop.this.targets.size;
                        while(true) {
                            if(v12 < v13) {
                                v1 = DragAndDrop.this.targets.get(v12);
                                if(!((Target)v1).actor.isAscendantOf(v11)) {
                                    ++v12;
                                    continue;
                                }
                                else {
                                    break;
                                }
                            }

                            goto label_80;
                        }

                        v14 = v1;
                        ((Target)v1).actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(event.getStageX(), event.getStageY()));
                        DragAndDrop.this.isValidTarget = ((Target)v1).drag(this.val$source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, pointer);
                    }

                label_80:
                    if(v14 != DragAndDrop.this.target) {
                        if(DragAndDrop.this.target != null) {
                            DragAndDrop.this.target.reset(this.val$source, DragAndDrop.this.payload);
                        }

                        DragAndDrop.this.target = ((Target)v14);
                    }

                    if(DragAndDrop.this.dragActor != null) {
                        DragAndDrop.this.dragActor.setTouchable(v10);
                    }

                    Actor v7 = null;
                    if(DragAndDrop.this.target != null) {
                        if(DragAndDrop.this.isValidTarget) {
                            v7 = DragAndDrop.this.payload.validDragActor;
                        }
                        else {
                            v7 = DragAndDrop.this.payload.invalidDragActor;
                        }
                    }

                    if(v7 == null) {
                        v7 = DragAndDrop.this.payload.dragActor;
                    }

                    if(v7 == null) {
                        return;
                    }

                    if(DragAndDrop.this.dragActor != v7) {
                        if(DragAndDrop.this.dragActor != null) {
                            DragAndDrop.this.dragActor.remove();
                        }

                        DragAndDrop.this.dragActor = v7;
                        v15.addActor(v7);
                    }

                    float v8 = event.getStageX() + DragAndDrop.this.dragActorX;
                    float v9 = event.getStageY() + DragAndDrop.this.dragActorY - v7.getHeight();
                    if(v8 < 0f) {
                        v8 = 0f;
                    }

                    if(v9 < 0f) {
                        v9 = 0f;
                    }

                    if(v7.getWidth() + v8 > v15.getWidth()) {
                        v8 = v15.getWidth() - v7.getWidth();
                    }

                    if(v7.getHeight() + v9 > v15.getHeight()) {
                        v9 = v15.getHeight() - v7.getHeight();
                    }

                    v7.setPosition(v8, v9);
                }
            }

            public void dragStart(InputEvent event, float x, float y, int pointer) {
                if(DragAndDrop.this.activePointer != -1) {
                    event.stop();
                }
                else {
                    DragAndDrop.this.activePointer = pointer;
                    DragAndDrop.this.dragStartTime = System.currentTimeMillis();
                    DragAndDrop.this.payload = this.val$source.dragStart(event, this.getTouchDownX(), this.getTouchDownY(), pointer);
                    event.stop();
                }
            }

            public void dragStop(InputEvent event, float x, float y, int pointer) {
                Target v5;
                Source v6 = null;
                if(pointer == DragAndDrop.this.activePointer) {
                    DragAndDrop.this.activePointer = -1;
                    if(DragAndDrop.this.payload != null) {
                        if(System.currentTimeMillis() - DragAndDrop.this.dragStartTime < (((long)DragAndDrop.this.dragTime))) {
                            DragAndDrop.this.isValidTarget = false;
                        }

                        if(DragAndDrop.this.dragActor != null) {
                            DragAndDrop.this.dragActor.remove();
                        }

                        if(DragAndDrop.this.isValidTarget) {
                            DragAndDrop.this.target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(event.getStageX(), event.getStageY()));
                            DragAndDrop.this.target.drop(this.val$source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, pointer);
                        }

                        Source v0 = this.val$source;
                        if(DragAndDrop.this.isValidTarget) {
                            v5 = DragAndDrop.this.target;
                        }
                        else {
                            v5 = ((Target)v6);
                        }

                        v0.dragStop(event, x, y, pointer, v5);
                        if(DragAndDrop.this.target != null) {
                            DragAndDrop.this.target.reset(this.val$source, DragAndDrop.this.payload);
                        }

                        DragAndDrop.this.source = v6;
                        DragAndDrop.this.payload = ((Payload)v6);
                        DragAndDrop.this.target = ((Target)v6);
                        DragAndDrop.this.isValidTarget = false;
                        DragAndDrop.this.dragActor = ((Actor)v6);
                    }
                }
            }
        };
        ((DragListener)v0).setTapSquareSize(this.tapSquareSize);
        ((DragListener)v0).setButton(this.button);
        source.actor.addCaptureListener(((EventListener)v0));
        this.sourceListeners.put(source, v0);
    }

    public void addTarget(Target target) {
        this.targets.add(target);
    }

    public Actor getDragActor() {
        return this.dragActor;
    }

    public boolean isDragging() {
        boolean v0;
        if(this.payload != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void removeSource(Source source) {
        source.actor.removeCaptureListener(this.sourceListeners.remove(source));
    }

    public void removeTarget(Target target) {
        this.targets.removeValue(target, true);
    }

    public void setButton(int button) {
        this.button = button;
    }

    public void setDragActorPosition(float dragActorX, float dragActorY) {
        this.dragActorX = dragActorX;
        this.dragActorY = dragActorY;
    }

    public void setDragTime(int dragMillis) {
        this.dragTime = dragMillis;
    }

    public void setTapSquareSize(float halfTapSquareSize) {
        this.tapSquareSize = halfTapSquareSize;
    }
}

