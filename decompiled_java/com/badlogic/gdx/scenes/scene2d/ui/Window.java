// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont$TextBounds;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Window extends Table {
    public class WindowStyle {
        public WindowStyle() {
            super();
            this.titleFontColor = new Color(1f, 1f, 1f, 1f);
        }

        public WindowStyle(BitmapFont titleFont, Color titleFontColor, Drawable background) {
            super();
            this.titleFontColor = new Color(1f, 1f, 1f, 1f);
            this.background = background;
            this.titleFont = titleFont;
            this.titleFontColor.set(titleFontColor);
        }

        public WindowStyle(WindowStyle style) {
            super();
            this.titleFontColor = new Color(1f, 1f, 1f, 1f);
            this.background = style.background;
            this.titleFont = style.titleFont;
            this.titleFontColor = new Color(style.titleFontColor);
        }
    }

    private static final int MOVE = 32;
    Table buttonTable;
    boolean dragging;
    boolean isModal;
    boolean isMovable;
    boolean isResizable;
    boolean keepWithinStage;
    int resizeBorder;
    private WindowStyle style;
    private String title;
    private int titleAlignment;
    private BitmapFontCache titleCache;
    private static final Vector2 tmpPosition;
    private static final Vector2 tmpSize;

    static  {
        Window.tmpPosition = new Vector2();
        Window.tmpSize = new Vector2();
    }

    public Window(String title, Skin skin) {
        this(title, skin.get(WindowStyle.class));
        this.setSkin(skin);
    }

    public Window(String title, WindowStyle style) {
        float v2 = 150f;
        super();
        this.isMovable = true;
        this.resizeBorder = 8;
        this.titleAlignment = 1;
        this.keepWithinStage = true;
        if(title == null) {
            throw new IllegalArgumentException("title cannot be null.");
        }

        this.title = title;
        this.setTouchable(Touchable.enabled);
        this.setClip(true);
        this.setStyle(style);
        this.setWidth(v2);
        this.setHeight(v2);
        this.setTitle(title);
        this.buttonTable = new Table();
        this.addActor(this.buttonTable);
        this.addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Window.this.toFront();
                return 0;
            }
        });
        this.addListener(new InputListener() {
            int edge;
            float lastX;
            float lastY;
            float startX;
            float startY;

            public boolean keyDown(InputEvent event, int keycode) {
                return Window.this.isModal;
            }

            public boolean keyTyped(InputEvent event, char character) {
                return Window.this.isModal;
            }

            public boolean keyUp(InputEvent event, int keycode) {
                return Window.this.isModal;
            }

            public boolean mouseMoved(InputEvent event, float x, float y) {
                return Window.this.isModal;
            }

            public boolean scrolled(InputEvent event, float x, float y, int amount) {
                return Window.this.isModal;
            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v3;
                boolean v5 = false;
                if(button == 0) {
                    int v0 = Window.this.resizeBorder;
                    float v2 = Window.this.getWidth();
                    float v1 = Window.this.getHeight();
                    this.edge = 0;
                    if(Window.this.isResizable) {
                        if(x < (((float)v0))) {
                            this.edge |= 8;
                        }

                        if(x > v2 - (((float)v0))) {
                            this.edge |= 16;
                        }

                        if(y < (((float)v0))) {
                            this.edge |= 4;
                        }

                        if(y > v1 - (((float)v0))) {
                            this.edge |= 2;
                        }

                        if(this.edge != 0) {
                            v0 += 25;
                        }

                        if(x < (((float)v0))) {
                            this.edge |= 8;
                        }

                        if(x > v2 - (((float)v0))) {
                            this.edge |= 16;
                        }

                        if(y < (((float)v0))) {
                            this.edge |= 4;
                        }

                        if(y <= v1 - (((float)v0))) {
                            goto label_60;
                        }

                        this.edge |= 2;
                    }

                label_60:
                    if((Window.this.isMovable) && this.edge == 0 && y <= v1 && y >= v1 - Window.this.getPadTop() && x >= 0f && x <= v2) {
                        this.edge = 32;
                    }

                    Window v6 = Window.this;
                    if(this.edge != 0) {
                        v3 = true;
                    }
                    else {
                        v3 = false;
                    }

                    v6.dragging = v3;
                    this.startX = x;
                    this.startY = y;
                    this.lastX = x;
                    this.lastY = y;
                }

                if(this.edge != 0 || (Window.this.isModal)) {
                    v5 = true;
                }

                return v5;
            }

            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float v3;
                float v2;
                int v4;
                if(Window.this.dragging) {
                    float v11 = Window.this.getWidth();
                    float v5 = Window.this.getHeight();
                    float v12 = Window.this.getX();
                    float v13 = Window.this.getY();
                    float v9 = Window.this.getMinWidth();
                    Window.this.getMaxWidth();
                    float v8 = Window.this.getMinHeight();
                    Window.this.getMaxHeight();
                    Stage v10 = Window.this.getStage();
                    if(!Window.this.keepWithinStage || Window.this.getParent() != v10.getRoot()) {
                        v4 = 0;
                    }
                    else {
                        v4 = 1;
                    }

                    if((this.edge & 32) != 0) {
                        v12 += x - this.startX;
                        v13 += y - this.startY;
                    }

                    if((this.edge & 8) != 0) {
                        v2 = x - this.startX;
                        if(v11 - v2 < v9) {
                            v2 = -(v9 - v11);
                        }

                        if(v4 != 0 && v12 + v2 < 0f) {
                            v2 = -v12;
                        }

                        v11 -= v2;
                        v12 += v2;
                    }

                    if((this.edge & 4) != 0) {
                        v3 = y - this.startY;
                        if(v5 - v3 < v8) {
                            v3 = -(v8 - v5);
                        }

                        if(v4 != 0 && v13 + v3 < 0f) {
                            v3 = -v13;
                        }

                        v5 -= v3;
                        v13 += v3;
                    }

                    if((this.edge & 16) != 0) {
                        v2 = x - this.lastX;
                        if(v11 + v2 < v9) {
                            v2 = v9 - v11;
                        }

                        if(v4 != 0 && v12 + v11 + v2 > v10.getWidth()) {
                            v2 = v10.getWidth() - v12 - v11;
                        }

                        v11 += v2;
                    }

                    if((this.edge & 2) != 0) {
                        v3 = y - this.lastY;
                        if(v5 + v3 < v8) {
                            v3 = v8 - v5;
                        }

                        if(v4 != 0 && v13 + v5 + v3 > v10.getHeight()) {
                            v3 = v10.getHeight() - v13 - v5;
                        }

                        v5 += v3;
                    }

                    this.lastX = x;
                    this.lastY = y;
                    Window.this.setBounds(((float)Math.round(v12)), ((float)Math.round(v13)), ((float)Math.round(v11)), ((float)Math.round(v5)));
                }
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Window.this.dragging = false;
            }
        });
    }

    public Window(String title, Skin skin, String styleName) {
        this(title, skin.get(styleName, WindowStyle.class));
        this.setSkin(skin);
    }

    public void draw(Batch batch, float parentAlpha) {
        this.keepWithinStage();
        super.draw(batch, parentAlpha);
    }

    protected void drawBackground(Batch batch, float parentAlpha) {
        float v14 = this.getX();
        float v15 = this.getY();
        float v13 = this.getWidth();
        float v10 = this.getHeight();
        float v11 = this.getPadTop();
        if(this.style.stageBackground != null) {
            Color v9 = this.getColor();
            batch.setColor(v9.r, v9.g, v9.b, v9.a * parentAlpha);
            Stage v12 = this.getStage();
            this.stageToLocalCoordinates(Window.tmpPosition.set(0f, 0f));
            this.stageToLocalCoordinates(Window.tmpSize.set(v12.getWidth(), v12.getHeight()));
            this.style.stageBackground.draw(batch, v14 + Window.tmpPosition.x, v15 + Window.tmpPosition.y, v14 + Window.tmpSize.x, v15 + Window.tmpSize.y);
        }

        super.drawBackground(batch, parentAlpha);
        this.buttonTable.getColor().a = this.getColor().a;
        this.buttonTable.pack();
        this.buttonTable.setPosition(v13 - this.buttonTable.getWidth(), Math.min(v10 - v11, v10 - this.buttonTable.getHeight()));
        this.buttonTable.translate(v14, v15);
        this.buttonTable.draw(batch, parentAlpha);
        this.buttonTable.translate(-v14, -v15);
        v15 += v10;
        TextBounds v8 = this.titleCache.getBounds();
        if((this.titleAlignment & 8) != 0) {
            v14 += this.getPadLeft();
        }
        else if((this.titleAlignment & 16) != 0) {
            v14 += v13 - v8.width - this.getPadRight();
        }
        else {
            v14 += (v13 - v8.width) / 2f;
        }

        if((this.titleAlignment & 2) == 0) {
            if((this.titleAlignment & 4) != 0) {
                v15 -= v11 - v8.height;
            }
            else {
                v15 -= (v11 - v8.height) / 2f;
            }
        }

        this.titleCache.setColors(Color.tmp.set(this.getColor()).mul(this.style.titleFontColor));
        this.titleCache.setPosition(((float)(((int)v14))), ((float)(((int)v15))));
        this.titleCache.draw(batch, parentAlpha);
    }

    public Table getButtonTable() {
        return this.buttonTable;
    }

    public float getPrefWidth() {
        return Math.max(super.getPrefWidth(), this.getTitleWidth() + this.getPadLeft() + this.getPadRight());
    }

    public WindowStyle getStyle() {
        return this.style;
    }

    public String getTitle() {
        return this.title;
    }

    public float getTitleWidth() {
        return this.titleCache.getBounds().width;
    }

    public Actor hit(float x, float y, boolean touchable) {
        Actor v3_1;
        Actor v0 = super.hit(x, y, touchable);
        if(v0 != null || !this.isModal || (touchable) && this.getTouchable() != Touchable.enabled) {
            v3_1 = v0;
        }

        return ((Window)v3_1);
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public boolean isModal() {
        return this.isModal;
    }

    public boolean isMovable() {
        return this.isMovable;
    }

    public boolean isResizable() {
        return this.isResizable;
    }

    void keepWithinStage() {
        if(this.keepWithinStage) {
            Stage v2 = this.getStage();
            if(this.getParent() == v2.getRoot()) {
                float v1 = v2.getWidth();
                float v0 = v2.getHeight();
                if(this.getX() < 0f) {
                    this.setX(0f);
                }

                if(this.getRight() > v1) {
                    this.setX(v1 - this.getWidth());
                }

                if(this.getY() < 0f) {
                    this.setY(0f);
                }

                if(this.getTop() <= v0) {
                    return;
                }

                this.setY(v0 - this.getHeight());
            }
        }
    }

    public void setKeepWithinStage(boolean keepWithinStage) {
        this.keepWithinStage = keepWithinStage;
    }

    public void setModal(boolean isModal) {
        this.isModal = isModal;
    }

    public void setMovable(boolean isMovable) {
        this.isMovable = isMovable;
    }

    public void setResizable(boolean isResizable) {
        this.isResizable = isResizable;
    }

    public void setResizeBorder(int resizeBorder) {
        this.resizeBorder = resizeBorder;
    }

    public void setStyle(WindowStyle style) {
        if(style == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }

        this.style = style;
        this.setBackground(style.background);
        this.titleCache = new BitmapFontCache(style.titleFont);
        this.titleCache.setColor(style.titleFontColor);
        if(this.title != null) {
            this.setTitle(this.title);
        }

        this.invalidateHierarchy();
    }

    public void setTitle(String title) {
        this.title = title;
        this.titleCache.setMultiLineText(((CharSequence)title), 0f, 0f);
    }

    public void setTitleAlignment(int titleAlignment) {
        this.titleAlignment = titleAlignment;
    }
}

