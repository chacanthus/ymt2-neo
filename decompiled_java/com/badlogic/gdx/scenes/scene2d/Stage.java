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

import com.badlogic.gdx.Application$ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener$FocusEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool$Poolable;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

public class Stage extends InputAdapter implements Disposable {
    public final class TouchFocus implements Poolable {
        public TouchFocus() {
            super();
        }

        public void reset() {
            this.listenerActor = null;
            this.listener = null;
        }
    }

    private static final Vector2 actorCoords;
    private final Batch batch;
    private Camera camera;
    private static final Vector3 cameraCoords;
    private float gutterHeight;
    private float gutterWidth;
    private float height;
    private Actor keyboardFocus;
    private Actor mouseOverActor;
    private int mouseScreenX;
    private int mouseScreenY;
    private final boolean ownsBatch;
    private final Actor[] pointerOverActors;
    private final int[] pointerScreenX;
    private final int[] pointerScreenY;
    private final boolean[] pointerTouched;
    private final Group root;
    private Actor scrollFocus;
    private final Vector2 stageCoords;
    private final SnapshotArray touchFocuses;
    private float viewportHeight;
    private float viewportWidth;
    private float viewportX;
    private float viewportY;
    private float width;

    static  {
        Stage.actorCoords = new Vector2();
        Stage.cameraCoords = new Vector3();
    }

    public Stage(float width, float height, boolean keepAspectRatio) {
        this(width, height, keepAspectRatio, null);
    }

    public Stage() {
        this(((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()), false, null);
    }

    public Stage(float width, float height, boolean keepAspectRatio, Batch batch) {
        SpriteBatch v8_1;
        boolean v0 = true;
        super();
        this.stageCoords = new Vector2();
        this.pointerOverActors = new Actor[20];
        this.pointerTouched = new boolean[20];
        this.pointerScreenX = new int[20];
        this.pointerScreenY = new int[20];
        this.touchFocuses = new SnapshotArray(true, 4, TouchFocus.class);
        if(batch != null) {
            v0 = false;
        }

        this.ownsBatch = v0;
        if(this.ownsBatch) {
            v8_1 = new SpriteBatch();
        }

        this.batch = ((Batch)v8_1);
        this.width = width;
        this.height = height;
        this.root = new Group();
        this.root.setStage(this);
        this.camera = new OrthographicCamera();
        this.setViewport(width, height, keepAspectRatio);
    }

    public Stage(float width, float height) {
        this(width, height, false, null);
    }

    public void act(float delta) {
        int v3 = 0;
        int v1 = this.pointerOverActors.length;
        while(v3 < v1) {
            Actor v2 = this.pointerOverActors[v3];
            if(this.pointerTouched[v3]) {
                this.pointerOverActors[v3] = this.fireEnterAndExit(v2, this.pointerScreenX[v3], this.pointerScreenY[v3], v3);
            }
            else if(v2 != null) {
                this.pointerOverActors[v3] = null;
                this.screenToStageCoordinates(this.stageCoords.set(((float)this.pointerScreenX[v3]), ((float)this.pointerScreenY[v3])));
                Object v0 = Pools.obtain(InputEvent.class);
                ((InputEvent)v0).setType(Type.exit);
                ((InputEvent)v0).setStage(this);
                ((InputEvent)v0).setStageX(this.stageCoords.x);
                ((InputEvent)v0).setStageY(this.stageCoords.y);
                ((InputEvent)v0).setRelatedActor(v2);
                ((InputEvent)v0).setPointer(v3);
                v2.fire(((Event)v0));
                Pools.free(v0);
            }

            ++v3;
        }

        ApplicationType v4 = Gdx.app.getType();
        if(v4 == ApplicationType.Desktop || v4 == ApplicationType.Applet || v4 == ApplicationType.WebGL) {
            this.mouseOverActor = this.fireEnterAndExit(this.mouseOverActor, this.mouseScreenX, this.mouseScreenY, -1);
        }

        this.root.act(delta);
    }

    public void act() {
        this.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333f));
    }

    public void addAction(Action action) {
        this.root.addAction(action);
    }

    public void addActor(Actor actor) {
        this.root.addActor(actor);
    }

    public boolean addCaptureListener(EventListener listener) {
        return this.root.addCaptureListener(listener);
    }

    public boolean addListener(EventListener listener) {
        return this.root.addListener(listener);
    }

    public void addTouchFocus(EventListener listener, Actor listenerActor, Actor target, int pointer, int button) {
        Object v0 = Pools.obtain(TouchFocus.class);
        ((TouchFocus)v0).listenerActor = listenerActor;
        ((TouchFocus)v0).target = target;
        ((TouchFocus)v0).listener = listener;
        ((TouchFocus)v0).pointer = pointer;
        ((TouchFocus)v0).button = button;
        this.touchFocuses.add(v0);
    }

    public void calculateScissors(Rectangle area, Rectangle scissor) {
        ScissorStack.calculateScissors(this.camera, this.viewportX, this.viewportY, this.viewportWidth, this.viewportHeight, this.batch.getTransformMatrix(), area, scissor);
    }

    public void cancelTouchFocus(EventListener listener, Actor actor) {
        Object v0 = Pools.obtain(InputEvent.class);
        ((InputEvent)v0).setStage(this);
        ((InputEvent)v0).setType(Type.touchUp);
        ((InputEvent)v0).setStageX(-2147483648f);
        ((InputEvent)v0).setStageY(-2147483648f);
        SnapshotArray v5 = this.touchFocuses;
        Object[] v3 = v5.begin();
        int v2 = 0;
        int v4 = v5.size;
        while(v2 < v4) {
            Object v1 = v3[v2];
            if((((TouchFocus)v1).listener != listener || ((TouchFocus)v1).listenerActor != actor) && (v5.removeValue(v1, true))) {
                ((InputEvent)v0).setTarget(((TouchFocus)v1).target);
                ((InputEvent)v0).setListenerActor(((TouchFocus)v1).listenerActor);
                ((InputEvent)v0).setPointer(((TouchFocus)v1).pointer);
                ((InputEvent)v0).setButton(((TouchFocus)v1).button);
                ((TouchFocus)v1).listener.handle(((Event)v0));
            }

            ++v2;
        }

        v5.end();
        Pools.free(v0);
    }

    public void cancelTouchFocus() {
        this.cancelTouchFocus(null, null);
    }

    public void clear() {
        this.unfocusAll();
        this.root.clear();
    }

    public void dispose() {
        this.clear();
        if(this.ownsBatch) {
            this.batch.dispose();
        }
    }

    public void draw() {
        this.camera.update();
        if(this.root.isVisible()) {
            this.batch.setProjectionMatrix(this.camera.combined);
            this.batch.begin();
            this.root.draw(this.batch, 1f);
            this.batch.end();
        }
    }

    private Actor fireEnterAndExit(Actor overLast, int screenX, int screenY, int pointer) {
        this.screenToStageCoordinates(this.stageCoords.set(((float)screenX), ((float)screenY)));
        Actor v1 = this.hit(this.stageCoords.x, this.stageCoords.y, true);
        if(v1 != overLast) {
            Object v0 = Pools.obtain(InputEvent.class);
            ((InputEvent)v0).setStage(this);
            ((InputEvent)v0).setStageX(this.stageCoords.x);
            ((InputEvent)v0).setStageY(this.stageCoords.y);
            ((InputEvent)v0).setPointer(pointer);
            if(overLast != null) {
                ((InputEvent)v0).setType(Type.exit);
                ((InputEvent)v0).setRelatedActor(v1);
                overLast.fire(((Event)v0));
            }

            if(v1 != null) {
                ((InputEvent)v0).setType(Type.enter);
                ((InputEvent)v0).setRelatedActor(overLast);
                v1.fire(((Event)v0));
            }

            Pools.free(v0);
            overLast = v1;
        }

        return overLast;
    }

    public Array getActors() {
        return this.root.getChildren();
    }

    public Camera getCamera() {
        return this.camera;
    }

    public float getGutterHeight() {
        return this.gutterHeight;
    }

    public float getGutterWidth() {
        return this.gutterWidth;
    }

    public float getHeight() {
        return this.height;
    }

    public Actor getKeyboardFocus() {
        return this.keyboardFocus;
    }

    public Group getRoot() {
        return this.root;
    }

    public Actor getScrollFocus() {
        return this.scrollFocus;
    }

    public Batch getSpriteBatch() {
        return this.batch;
    }

    public float getWidth() {
        return this.width;
    }

    public Actor hit(float stageX, float stageY, boolean touchable) {
        this.root.parentToLocalCoordinates(Stage.actorCoords.set(stageX, stageY));
        return this.root.hit(Stage.actorCoords.x, Stage.actorCoords.y, touchable);
    }

    public boolean keyDown(int keyCode) {
        Group v2;
        if(this.keyboardFocus == null) {
            v2 = this.root;
        }
        else {
            Actor v2_1 = this.keyboardFocus;
        }

        Object v0 = Pools.obtain(InputEvent.class);
        ((InputEvent)v0).setStage(this);
        ((InputEvent)v0).setType(Type.keyDown);
        ((InputEvent)v0).setKeyCode(keyCode);
        ((Actor)v2).fire(((Event)v0));
        boolean v1 = ((InputEvent)v0).isHandled();
        Pools.free(v0);
        return v1;
    }

    public boolean keyTyped(char character) {
        Group v2;
        if(this.keyboardFocus == null) {
            v2 = this.root;
        }
        else {
            Actor v2_1 = this.keyboardFocus;
        }

        Object v0 = Pools.obtain(InputEvent.class);
        ((InputEvent)v0).setStage(this);
        ((InputEvent)v0).setType(Type.keyTyped);
        ((InputEvent)v0).setCharacter(character);
        ((Actor)v2).fire(((Event)v0));
        boolean v1 = ((InputEvent)v0).isHandled();
        Pools.free(v0);
        return v1;
    }

    public boolean keyUp(int keyCode) {
        Group v2;
        if(this.keyboardFocus == null) {
            v2 = this.root;
        }
        else {
            Actor v2_1 = this.keyboardFocus;
        }

        Object v0 = Pools.obtain(InputEvent.class);
        ((InputEvent)v0).setStage(this);
        ((InputEvent)v0).setType(Type.keyUp);
        ((InputEvent)v0).setKeyCode(keyCode);
        ((Actor)v2).fire(((Event)v0));
        boolean v1 = ((InputEvent)v0).isHandled();
        Pools.free(v0);
        return v1;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        Group v2_1;
        boolean v1 = false;
        if((((float)screenX)) >= this.viewportX && (((float)screenX)) < this.viewportX + this.viewportWidth && (((float)(Gdx.graphics.getHeight() - screenY))) >= this.viewportY && (((float)(Gdx.graphics.getHeight() - screenY))) < this.viewportY + this.viewportHeight) {
            this.mouseScreenX = screenX;
            this.mouseScreenY = screenY;
            this.screenToStageCoordinates(this.stageCoords.set(((float)screenX), ((float)screenY)));
            Object v0 = Pools.obtain(InputEvent.class);
            ((InputEvent)v0).setStage(this);
            ((InputEvent)v0).setType(Type.mouseMoved);
            ((InputEvent)v0).setStageX(this.stageCoords.x);
            ((InputEvent)v0).setStageY(this.stageCoords.y);
            Actor v2 = this.hit(this.stageCoords.x, this.stageCoords.y, true);
            if(v2 == null) {
                v2_1 = this.root;
            }

            ((Actor)v2_1).fire(((Event)v0));
            v1 = ((InputEvent)v0).isHandled();
            Pools.free(v0);
        }

        return v1;
    }

    public boolean removeCaptureListener(EventListener listener) {
        return this.root.removeCaptureListener(listener);
    }

    public boolean removeListener(EventListener listener) {
        return this.root.removeListener(listener);
    }

    public void removeTouchFocus(EventListener listener, Actor listenerActor, Actor target, int pointer, int button) {
        SnapshotArray v2 = this.touchFocuses;
        int v1;
        for(v1 = v2.size - 1; v1 >= 0; --v1) {
            Object v0 = v2.get(v1);
            if(((TouchFocus)v0).listener == listener && ((TouchFocus)v0).listenerActor == listenerActor && ((TouchFocus)v0).target == target && ((TouchFocus)v0).pointer == pointer && ((TouchFocus)v0).button == button) {
                v2.removeIndex(v1);
                Pools.free(v0);
            }
        }
    }

    public Vector2 screenToStageCoordinates(Vector2 screenCoords) {
        this.camera.unproject(Stage.cameraCoords.set(screenCoords.x, screenCoords.y, 0f), this.viewportX, this.viewportY, this.viewportWidth, this.viewportHeight);
        screenCoords.x = Stage.cameraCoords.x;
        screenCoords.y = Stage.cameraCoords.y;
        return screenCoords;
    }

    public boolean scrolled(int amount) {
        Actor v2_1;
        if(this.scrollFocus == null) {
            Group v2 = this.root;
        }
        else {
            v2_1 = this.scrollFocus;
        }

        this.screenToStageCoordinates(this.stageCoords.set(((float)this.mouseScreenX), ((float)this.mouseScreenY)));
        Object v0 = Pools.obtain(InputEvent.class);
        ((InputEvent)v0).setStage(this);
        ((InputEvent)v0).setType(Type.scrolled);
        ((InputEvent)v0).setScrollAmount(amount);
        ((InputEvent)v0).setStageX(this.stageCoords.x);
        ((InputEvent)v0).setStageY(this.stageCoords.y);
        v2_1.fire(((Event)v0));
        boolean v1 = ((InputEvent)v0).isHandled();
        Pools.free(v0);
        return v1;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setKeyboardFocus(Actor actor) {
        if(this.keyboardFocus != actor) {
            Object v0 = Pools.obtain(FocusEvent.class);
            ((FocusEvent)v0).setStage(this);
            ((FocusEvent)v0).setType(com.badlogic.gdx.scenes.scene2d.utils.FocusListener$FocusEvent$Type.keyboard);
            Actor v1 = this.keyboardFocus;
            if(v1 != null) {
                ((FocusEvent)v0).setFocused(false);
                ((FocusEvent)v0).setRelatedActor(actor);
                v1.fire(((Event)v0));
            }

            if(!((FocusEvent)v0).isCancelled()) {
                this.keyboardFocus = actor;
                if(actor != null) {
                    ((FocusEvent)v0).setFocused(true);
                    ((FocusEvent)v0).setRelatedActor(v1);
                    actor.fire(((Event)v0));
                    if(((FocusEvent)v0).isCancelled()) {
                        this.setKeyboardFocus(v1);
                    }
                }
            }

            Pools.free(v0);
        }
    }

    public void setScrollFocus(Actor actor) {
        if(this.scrollFocus != actor) {
            Object v0 = Pools.obtain(FocusEvent.class);
            ((FocusEvent)v0).setStage(this);
            ((FocusEvent)v0).setType(com.badlogic.gdx.scenes.scene2d.utils.FocusListener$FocusEvent$Type.scroll);
            Actor v1 = this.keyboardFocus;
            if(v1 != null) {
                ((FocusEvent)v0).setFocused(false);
                ((FocusEvent)v0).setRelatedActor(actor);
                v1.fire(((Event)v0));
            }

            if(!((FocusEvent)v0).isCancelled()) {
                this.scrollFocus = actor;
                if(actor != null) {
                    ((FocusEvent)v0).setFocused(true);
                    ((FocusEvent)v0).setRelatedActor(v1);
                    actor.fire(((Event)v0));
                    if(((FocusEvent)v0).isCancelled()) {
                        this.setScrollFocus(v1);
                    }
                }
            }

            Pools.free(v0);
        }
    }

    public void setViewport(float width, float height, boolean keepAspectRatio) {
        this.setViewport(width, height, keepAspectRatio, 0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public void setViewport(float width, float height) {
        this.setViewport(width, height, false, 0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public void setViewport(float stageWidth, float stageHeight, boolean keepAspectRatio, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        float v5;
        this.viewportX = viewportX;
        this.viewportY = viewportY;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        if(!keepAspectRatio) {
            this.width = stageWidth;
            this.height = stageHeight;
            this.gutterWidth = 0f;
            this.gutterHeight = 0f;
        }
        else if(viewportHeight / viewportWidth < stageHeight / stageWidth) {
            v5 = (viewportWidth - stageWidth * (viewportHeight / stageHeight)) * (stageHeight / viewportHeight);
            this.width = stageWidth + v5;
            this.height = stageHeight;
            this.gutterWidth = v5 / 2f;
            this.gutterHeight = 0f;
        }
        else {
            v5 = (viewportHeight - stageHeight * (viewportWidth / stageWidth)) * (stageWidth / viewportWidth);
            this.height = stageHeight + v5;
            this.width = stageWidth;
            this.gutterWidth = 0f;
            this.gutterHeight = v5 / 2f;
        }

        this.camera.position.set(this.width / 2f, this.height / 2f, 0f);
        this.camera.viewportWidth = this.width;
        this.camera.viewportHeight = this.height;
    }

    public Vector2 stageToScreenCoordinates(Vector2 stageCoords) {
        this.camera.project(Stage.cameraCoords.set(stageCoords.x, stageCoords.y, 0f), this.viewportX, this.viewportY, this.viewportWidth, this.viewportHeight);
        stageCoords.x = Stage.cameraCoords.x;
        stageCoords.y = this.viewportHeight - Stage.cameraCoords.y;
        return stageCoords;
    }

    public Vector2 toScreenCoordinates(Vector2 coords, Matrix4 transformMatrix) {
        ScissorStack.toWindowCoordinates(this.camera, transformMatrix, coords);
        return coords;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean v1 = false;
        if((((float)screenX)) >= this.viewportX && (((float)screenX)) < this.viewportX + this.viewportWidth && (((float)(Gdx.graphics.getHeight() - screenY))) >= this.viewportY && (((float)(Gdx.graphics.getHeight() - screenY))) < this.viewportY + this.viewportHeight) {
            this.pointerTouched[pointer] = true;
            this.pointerScreenX[pointer] = screenX;
            this.pointerScreenY[pointer] = screenY;
            this.screenToStageCoordinates(this.stageCoords.set(((float)screenX), ((float)screenY)));
            Object v0 = Pools.obtain(InputEvent.class);
            ((InputEvent)v0).setType(Type.touchDown);
            ((InputEvent)v0).setStage(this);
            ((InputEvent)v0).setStageX(this.stageCoords.x);
            ((InputEvent)v0).setStageY(this.stageCoords.y);
            ((InputEvent)v0).setPointer(pointer);
            ((InputEvent)v0).setButton(button);
            Actor v2 = this.hit(this.stageCoords.x, this.stageCoords.y, true);
            if(v2 == null) {
                Group v2_1 = this.root;
            }

            v2.fire(((Event)v0));
            v1 = ((InputEvent)v0).isHandled();
            Pools.free(v0);
        }

        return v1;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean v3;
        this.pointerScreenX[pointer] = screenX;
        this.pointerScreenY[pointer] = screenY;
        this.mouseScreenX = screenX;
        this.mouseScreenY = screenY;
        if(this.touchFocuses.size == 0) {
            v3 = false;
        }
        else {
            this.screenToStageCoordinates(this.stageCoords.set(((float)screenX), ((float)screenY)));
            Object v0 = Pools.obtain(InputEvent.class);
            ((InputEvent)v0).setType(Type.touchDragged);
            ((InputEvent)v0).setStage(this);
            ((InputEvent)v0).setStageX(this.stageCoords.x);
            ((InputEvent)v0).setStageY(this.stageCoords.y);
            ((InputEvent)v0).setPointer(pointer);
            SnapshotArray v6 = this.touchFocuses;
            Object[] v2 = v6.begin();
            int v4 = 0;
            int v5 = v6.size;
            while(v4 < v5) {
                Object v1 = v2[v4];
                if(((TouchFocus)v1).pointer == pointer) {
                    ((InputEvent)v0).setTarget(((TouchFocus)v1).target);
                    ((InputEvent)v0).setListenerActor(((TouchFocus)v1).listenerActor);
                    if(((TouchFocus)v1).listener.handle(((Event)v0))) {
                        ((InputEvent)v0).handle();
                    }
                }

                ++v4;
            }

            v6.end();
            v3 = ((InputEvent)v0).isHandled();
            Pools.free(v0);
        }

        return v3;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean v3 = false;
        this.pointerTouched[pointer] = false;
        this.pointerScreenX[pointer] = screenX;
        this.pointerScreenY[pointer] = screenY;
        if(this.touchFocuses.size != 0) {
            this.screenToStageCoordinates(this.stageCoords.set(((float)screenX), ((float)screenY)));
            Object v0 = Pools.obtain(InputEvent.class);
            ((InputEvent)v0).setType(Type.touchUp);
            ((InputEvent)v0).setStage(this);
            ((InputEvent)v0).setStageX(this.stageCoords.x);
            ((InputEvent)v0).setStageY(this.stageCoords.y);
            ((InputEvent)v0).setPointer(pointer);
            ((InputEvent)v0).setButton(button);
            SnapshotArray v6 = this.touchFocuses;
            Object[] v2 = v6.begin();
            int v4 = 0;
            int v5 = v6.size;
            while(v4 < v5) {
                Object v1 = v2[v4];
                if(((TouchFocus)v1).pointer == pointer && ((TouchFocus)v1).button == button && (v6.removeValue(v1, true))) {
                    ((InputEvent)v0).setTarget(((TouchFocus)v1).target);
                    ((InputEvent)v0).setListenerActor(((TouchFocus)v1).listenerActor);
                    if(((TouchFocus)v1).listener.handle(((Event)v0))) {
                        ((InputEvent)v0).handle();
                    }

                    Pools.free(v1);
                }

                ++v4;
            }

            v6.end();
            v3 = ((InputEvent)v0).isHandled();
            Pools.free(v0);
        }

        return v3;
    }

    public void unfocus(Actor actor) {
        Actor v1 = null;
        if(this.scrollFocus != null && (this.scrollFocus.isDescendantOf(actor))) {
            this.scrollFocus = v1;
        }

        if(this.keyboardFocus != null && (this.keyboardFocus.isDescendantOf(actor))) {
            this.keyboardFocus = v1;
        }
    }

    public void unfocusAll() {
        this.scrollFocus = null;
        this.keyboardFocus = null;
        this.cancelTouchFocus();
    }
}

