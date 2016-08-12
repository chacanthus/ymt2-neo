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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

public class Actor {
    private final Array actions;
    private final DelayedRemovalArray captureListeners;
    final Color color;
    float height;
    private final DelayedRemovalArray listeners;
    private String name;
    float originX;
    float originY;
    private Group parent;
    float rotation;
    float scaleX;
    float scaleY;
    private Stage stage;
    private Touchable touchable;
    private Object userObject;
    private boolean visible;
    float width;
    float x;
    float y;

    public Actor() {
        super();
        this.listeners = new DelayedRemovalArray(0);
        this.captureListeners = new DelayedRemovalArray(0);
        this.actions = new Array(0);
        this.touchable = Touchable.enabled;
        this.visible = true;
        this.scaleX = 1f;
        this.scaleY = 1f;
        this.color = new Color(1f, 1f, 1f, 1f);
    }

    public void act(float delta) {
        int v1;
        for(v1 = 0; v1 < this.actions.size; ++v1) {
            Object v0 = this.actions.get(v1);
            if((((Action)v0).act(delta)) && v1 < this.actions.size) {
                this.actions.removeIndex(v1);
                ((Action)v0).setActor(null);
                --v1;
            }
        }
    }

    public void addAction(Action action) {
        action.setActor(this);
        this.actions.add(action);
    }

    public boolean addCaptureListener(EventListener listener) {
        if(!this.captureListeners.contains(listener, true)) {
            this.captureListeners.add(listener);
        }

        return 1;
    }

    public boolean addListener(EventListener listener) {
        boolean v0 = true;
        if(!this.listeners.contains(listener, true)) {
            this.listeners.add(listener);
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void clear() {
        this.clearActions();
        this.clearListeners();
    }

    public void clearActions() {
        int v0;
        for(v0 = this.actions.size - 1; v0 >= 0; --v0) {
            this.actions.get(v0).setActor(null);
        }

        this.actions.clear();
    }

    public void clearListeners() {
        this.listeners.clear();
        this.captureListeners.clear();
    }

    public boolean clipBegin() {
        return this.clipBegin(this.x, this.y, this.width, this.height);
    }

    public boolean clipBegin(float x, float y, float width, float height) {
        boolean v3;
        Rectangle v2 = Rectangle.tmp;
        v2.x = x;
        v2.y = y;
        v2.width = width;
        v2.height = height;
        Stage v1 = this.stage;
        Object v0 = Pools.obtain(Rectangle.class);
        v1.calculateScissors(v2, ((Rectangle)v0));
        if(ScissorStack.pushScissors(((Rectangle)v0))) {
            v3 = true;
        }
        else {
            Pools.free(v0);
            v3 = false;
        }

        return v3;
    }

    public void clipEnd() {
        Pools.free(ScissorStack.popScissors());
    }

    public void draw(Batch batch, float parentAlpha) {
    }

    public boolean fire(Event event) {  // has try-catch handlers
        boolean v5_1;
        int v2;
        if(event.getStage() == null) {
            event.setStage(this.getStage());
        }

        event.setTarget(this);
        Object v0 = Pools.obtain(Array.class);
        Group v4;
        for(v4 = this.getParent(); true; v4 = v4.getParent()) {
            if(v4 == null) {
                goto label_12;
            }

            ((Array)v0).add(v4);
        }

        try {
        label_12:
            v2 = ((Array)v0).size - 1;
            while(true) {
            label_14:
                if(v2 >= 0) {
                    ((Array)v0).get(v2).notify(event, true);
                    if(event.isStopped()) {
                        v5_1 = event.isCancelled();
                        break;
                    }
                    else {
                        goto label_24;
                    }
                }
                else {
                    goto label_27;
                }

                goto label_23;
            }
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
        goto label_23;
    label_24:
        --v2;
        goto label_14;
        try {
        label_27:
            this.notify(event, true);
            if(event.isStopped()) {
                v5_1 = event.isCancelled();
            }
            else {
                goto label_35;
            }
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
        goto label_23;
        try {
        label_35:
            this.notify(event, false);
            if(!event.getBubbles()) {
                v5_1 = event.isCancelled();
            }
            else {
                goto label_42;
            }
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
        goto label_23;
        try {
        label_42:
            if(event.isStopped()) {
                v5_1 = event.isCancelled();
            }
            else {
                goto label_48;
            }
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
        goto label_23;
    label_48:
        v2 = 0;
        try {
            int v3 = ((Array)v0).size;
            while(true) {
            label_50:
                if(v2 < v3) {
                    ((Array)v0).get(v2).notify(event, false);
                    if(event.isStopped()) {
                        v5_1 = event.isCancelled();
                        break;
                    }
                    else {
                        goto label_60;
                    }
                }
                else {
                    goto label_62;
                }

                goto label_23;
            }
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
        goto label_23;
    label_60:
        ++v2;
        goto label_50;
        try {
        label_62:
            v5_1 = event.isCancelled();
        }
        catch(Throwable v5) {
            goto label_67;
        }

        ((Array)v0).clear();
        Pools.free(v0);
    label_23:
        return v5_1;
    label_67:
        ((Array)v0).clear();
        Pools.free(v0);
        throw v5;
    }

    public Array getActions() {
        return this.actions;
    }

    public Array getCaptureListeners() {
        return this.captureListeners;
    }

    public Color getColor() {
        return this.color;
    }

    public float getHeight() {
        return this.height;
    }

    public Array getListeners() {
        return this.listeners;
    }

    public String getName() {
        return this.name;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public Group getParent() {
        return this.parent;
    }

    public float getRight() {
        return this.x + this.width;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public Stage getStage() {
        return this.stage;
    }

    public float getTop() {
        return this.y + this.height;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int getZIndex() {
        int v1;
        Group v0 = this.parent;
        if(v0 == null) {
            v1 = -1;
        }
        else {
            v1 = v0.getChildren().indexOf(this, true);
        }

        return v1;
    }

    public boolean hasParent() {
        boolean v0;
        if(this.parent != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public Actor hit(float x, float y, boolean touchable) {
        Actor v0 = null;
        if(!touchable || this.touchable == Touchable.enabled) {
            if(x < 0f || x >= this.width || y < 0f || y >= this.height) {
                this = v0;
            }

            v0 = this;
        }

        return v0;
    }

    public boolean isAscendantOf(Actor actor) {
        boolean v0;
        if(actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }

        while(true) {
            if(actor == null) {
                break;
            }
            else if(actor == this) {
                v0 = true;
            }
            else {
                Group v3_1 = ((Actor)v3_1).parent;
                continue;
            }

            goto label_7;
        }

        v0 = false;
    label_7:
        return v0;
    }

    public boolean isDescendantOf(Actor actor) {
        boolean v1;
        if(actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }

        Actor v0 = this;
        while(true) {
            if(v0 == null) {
                break;
            }
            else if(v0 == actor) {
                v1 = true;
            }
            else {
                Group v0_1 = v0.parent;
                continue;
            }

            goto label_7;
        }

        v1 = false;
    label_7:
        return v1;
    }

    public boolean isTouchable() {
        boolean v0;
        if(this.touchable == Touchable.enabled) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Vector2 localToAscendantCoordinates(Actor ascendant, Vector2 localCoords) {
        Actor v0 = this;
        do {
            if(v0.parent == null) {
                break;
            }

            v0.localToParentCoordinates(localCoords);
            Group v0_1 = v0.parent;
        }
        while((((Actor)v0_1)) != ascendant);

        return localCoords;
    }

    public Vector2 localToParentCoordinates(Vector2 localCoords) {
        float v2;
        float v1;
        float v12 = 1f;
        float v13 = 0.017453f;
        float v3 = -this.rotation;
        float v4 = this.scaleX;
        float v5 = this.scaleY;
        float v9 = this.x;
        float v10 = this.y;
        if(v3 == 0f) {
            if(v4 == v12 && v5 == v12) {
                localCoords.x += v9;
                localCoords.y += v10;
                goto label_18;
            }

            v1 = this.originX;
            v2 = this.originY;
            localCoords.x = (localCoords.x - v1) * v4 + v1 + v9;
            localCoords.y = (localCoords.y - v2) * v5 + v2 + v10;
        }
        else {
            float v0 = ((float)Math.cos(((double)(v3 * v13))));
            float v6 = ((float)Math.sin(((double)(v3 * v13))));
            v1 = this.originX;
            v2 = this.originY;
            float v7 = localCoords.x - v1;
            float v8 = localCoords.y - v2;
            localCoords.x = (v7 * v0 + v8 * v6) * v4 + v1 + v9;
            localCoords.y = (-v6 * v7 + v8 * v0) * v5 + v2 + v10;
        }

    label_18:
        return localCoords;
    }

    public Vector2 localToStageCoordinates(Vector2 localCoords) {
        return this.localToAscendantCoordinates(null, localCoords);
    }

    public boolean notify(Event event, boolean capture) {
        boolean v0;
        DelayedRemovalArray v8;
        if(event.getTarget() == null) {
            throw new IllegalArgumentException("The event target cannot be null.");
        }

        if(capture) {
            v8 = this.captureListeners;
        }
        else {
            v8 = this.listeners;
        }

        if(v8.size == 0) {
            v0 = event.isCancelled();
        }
        else {
            event.setListenerActor(this);
            event.setCapture(capture);
            if(event.getStage() == null) {
                event.setStage(this.stage);
            }

            v8.begin();
            int v6 = 0;
            int v9 = v8.size;
            while(v6 < v9) {
                Object v1 = v8.get(v6);
                if(((EventListener)v1).handle(event)) {
                    event.handle();
                    if((event instanceof InputEvent)) {
                        Event v7 = event;
                        if(((InputEvent)v7).getType() == Type.touchDown) {
                            event.getStage().addTouchFocus(((EventListener)v1), this, ((InputEvent)v7).getTarget(), ((InputEvent)v7).getPointer(), ((InputEvent)v7).getButton());
                        }
                    }
                }

                ++v6;
            }

            v8.end();
            v0 = event.isCancelled();
        }

        return v0;
    }

    public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
        float v4;
        float v3;
        float v12 = 1f;
        float v13 = 0.017453f;
        float v5 = this.rotation;
        float v6 = this.scaleX;
        float v7 = this.scaleY;
        float v0 = this.x;
        float v1 = this.y;
        if(v5 == 0f) {
            if(v6 == v12 && v7 == v12) {
                parentCoords.x -= v0;
                parentCoords.y -= v1;
                goto label_17;
            }

            v3 = this.originX;
            v4 = this.originY;
            parentCoords.x = (parentCoords.x - v0 - v3) / v6 + v3;
            parentCoords.y = (parentCoords.y - v1 - v4) / v7 + v4;
        }
        else {
            float v2 = ((float)Math.cos(((double)(v5 * v13))));
            float v8 = ((float)Math.sin(((double)(v5 * v13))));
            v3 = this.originX;
            v4 = this.originY;
            float v9 = parentCoords.x - v0 - v3;
            float v10 = parentCoords.y - v1 - v4;
            parentCoords.x = (v9 * v2 + v10 * v8) / v6 + v3;
            parentCoords.y = (-v8 * v9 + v10 * v2) / v7 + v4;
        }

    label_17:
        return parentCoords;
    }

    public boolean remove() {
        boolean v0;
        if(this.parent != null) {
            v0 = this.parent.removeActor(this);
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void removeAction(Action action) {
        if(this.actions.removeValue(action, true)) {
            action.setActor(null);
        }
    }

    public boolean removeCaptureListener(EventListener listener) {
        return this.captureListeners.removeValue(listener, true);
    }

    public boolean removeListener(EventListener listener) {
        return this.listeners.removeValue(listener, true);
    }

    public void rotate(float amountInDegrees) {
        this.rotation += amountInDegrees;
    }

    public void scale(float scale) {
        this.scaleX += scale;
        this.scaleY += scale;
    }

    public void scale(float scaleX, float scaleY) {
        this.scaleX += scaleX;
        this.scaleY += scaleY;
    }

    public Vector2 screenToLocalCoordinates(Vector2 screenCoords) {
        Stage v0 = this.stage;
        if(v0 != null) {
            screenCoords = this.stageToLocalCoordinates(v0.screenToStageCoordinates(screenCoords));
        }

        return screenCoords;
    }

    public void setBounds(float x, float y, float width, float height) {
        float v1 = this.width;
        float v0 = this.height;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if(width != v1 || height != v0) {
            this.sizeChanged();
        }
    }

    public void setColor(float r, float g, float b, float a) {
        this.color.set(r, g, b, a);
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setHeight(float height) {
        float v0 = this.height;
        this.height = height;
        if(height != v0) {
            this.sizeChanged();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    protected void setParent(Group parent) {
        this.parent = parent;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setRotation(float degrees) {
        this.rotation = degrees;
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
    }

    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public void setSize(float width, float height) {
        float v1 = this.width;
        float v0 = this.height;
        this.width = width;
        this.height = height;
        if(width != v1 || height != v0) {
            this.sizeChanged();
        }
    }

    protected void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
    }

    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setWidth(float width) {
        float v0 = this.width;
        this.width = width;
        if(width != v0) {
            this.sizeChanged();
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZIndex(int index) {
        if(index < 0) {
            throw new IllegalArgumentException("ZIndex cannot be < 0.");
        }

        Group v1 = this.parent;
        if(v1 != null) {
            SnapshotArray v0 = v1.getChildren();
            if(((Array)v0).size != 1 && (((Array)v0).removeValue(this, true))) {
                if(index >= ((Array)v0).size) {
                    ((Array)v0).add(this);
                }
                else {
                    ((Array)v0).insert(index, this);
                }
            }
        }
    }

    public void size(float size) {
        this.width += size;
        this.height += size;
        this.sizeChanged();
    }

    public void size(float width, float height) {
        this.width += width;
        this.height += height;
        this.sizeChanged();
    }

    protected void sizeChanged() {
    }

    public Vector2 stageToLocalCoordinates(Vector2 stageCoords) {
        if(this.parent != null) {
            this.parent.stageToLocalCoordinates(stageCoords);
            this.parentToLocalCoordinates(stageCoords);
        }

        return stageCoords;
    }

    public void toBack() {
        this.setZIndex(0);
    }

    public void toFront() {
        this.setZIndex(2147483647);
    }

    public String toString() {
        String v1 = this.name;
        if(v1 == null) {
            v1 = this.getClass().getName();
            int v0 = v1.lastIndexOf(46);
            if(v0 != -1) {
                v1 = v1.substring(v0 + 1);
            }
        }

        return v1;
    }

    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }
}

