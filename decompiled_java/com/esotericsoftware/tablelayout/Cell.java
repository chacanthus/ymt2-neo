// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.esotericsoftware.tablelayout;

public class Cell {
    Integer align;
    int cellAboveIndex;
    Integer colspan;
    int column;
    float computedPadBottom;
    float computedPadLeft;
    float computedPadRight;
    float computedPadTop;
    boolean endRow;
    Integer expandX;
    Integer expandY;
    Float fillX;
    Float fillY;
    Boolean ignore;
    private BaseTableLayout layout;
    Value maxHeight;
    Value maxWidth;
    Value minHeight;
    Value minWidth;
    Value padBottom;
    Value padLeft;
    Value padRight;
    Value padTop;
    Value prefHeight;
    Value prefWidth;
    int row;
    Value spaceBottom;
    Value spaceLeft;
    Value spaceRight;
    Value spaceTop;
    Boolean uniformX;
    Boolean uniformY;
    Object widget;
    float widgetHeight;
    float widgetWidth;
    float widgetX;
    float widgetY;

    public Cell() {
        super();
        this.cellAboveIndex = -1;
    }

    public Cell align(Integer align) {
        this.align = align;
        return this;
    }

    public Cell bottom() {
        if(this.align == null) {
            this.align = Integer.valueOf(4);
        }
        else {
            this.align = Integer.valueOf(this.align.intValue() | 4);
            this.align = Integer.valueOf(this.align.intValue() & -3);
        }

        return this;
    }

    public Cell center() {
        this.align = Integer.valueOf(1);
        return this;
    }

    public void clear() {
        this.minWidth = null;
        this.minHeight = null;
        this.prefWidth = null;
        this.prefHeight = null;
        this.maxWidth = null;
        this.maxHeight = null;
        this.spaceTop = null;
        this.spaceLeft = null;
        this.spaceBottom = null;
        this.spaceRight = null;
        this.padTop = null;
        this.padLeft = null;
        this.padBottom = null;
        this.padRight = null;
        this.fillX = null;
        this.fillY = null;
        this.align = null;
        this.expandX = null;
        this.expandY = null;
        this.ignore = null;
        this.colspan = null;
        this.uniformX = null;
        this.uniformY = null;
    }

    public Cell colspan(Integer colspan) {
        this.colspan = colspan;
        return this;
    }

    void defaults() {
        this.minWidth = Value.minWidth;
        this.minHeight = Value.minHeight;
        this.prefWidth = Value.prefWidth;
        this.prefHeight = Value.prefHeight;
        this.maxWidth = Value.maxWidth;
        this.maxHeight = Value.maxHeight;
        this.spaceTop = Value.zero;
        this.spaceLeft = Value.zero;
        this.spaceBottom = Value.zero;
        this.spaceRight = Value.zero;
        this.padTop = Value.zero;
        this.padLeft = Value.zero;
        this.padBottom = Value.zero;
        this.padRight = Value.zero;
        this.fillX = Float.valueOf(0f);
        this.fillY = Float.valueOf(0f);
        this.align = Integer.valueOf(1);
        this.expandX = Integer.valueOf(0);
        this.expandY = Integer.valueOf(0);
        this.ignore = Boolean.valueOf(false);
        this.colspan = Integer.valueOf(1);
        this.uniformX = null;
        this.uniformY = null;
    }

    public Cell expand() {
        this.expandX = Integer.valueOf(1);
        this.expandY = Integer.valueOf(1);
        return this;
    }

    public Cell expand(Integer x, Integer y) {
        this.expandX = x;
        this.expandY = y;
        return this;
    }

    public Cell expand(boolean x, boolean y) {
        int v0;
        int v1 = 1;
        if(x) {
            v0 = 1;
        }
        else {
            v0 = 0;
        }

        this.expandX = Integer.valueOf(v0);
        if(!y) {
            v1 = 0;
        }

        this.expandY = Integer.valueOf(v1);
        return this;
    }

    public Cell expandX() {
        this.expandX = Integer.valueOf(1);
        return this;
    }

    public Cell expandY() {
        this.expandY = Integer.valueOf(1);
        return this;
    }

    public Cell fill() {
        this.fillX = Float.valueOf(1f);
        this.fillY = Float.valueOf(1f);
        return this;
    }

    public Cell fill(Float x, Float y) {
        this.fillX = x;
        this.fillY = y;
        return this;
    }

    public Cell fill(boolean fill) {
        float v0;
        float v1 = 1f;
        if(fill) {
            v0 = v1;
        }
        else {
            v0 = 0f;
        }

        this.fillX = Float.valueOf(v0);
        if(!fill) {
            v1 = 0f;
        }

        this.fillY = Float.valueOf(v1);
        return this;
    }

    public Cell fill(boolean x, boolean y) {
        float v0;
        float v1 = 1f;
        if(x) {
            v0 = v1;
        }
        else {
            v0 = 0f;
        }

        this.fillX = Float.valueOf(v0);
        if(!y) {
            v1 = 0f;
        }

        this.fillY = Float.valueOf(v1);
        return this;
    }

    public Cell fillX() {
        this.fillX = Float.valueOf(1f);
        return this;
    }

    public Cell fillY() {
        this.fillY = Float.valueOf(1f);
        return this;
    }

    public void free() {
        this.widget = null;
        this.layout = null;
        this.endRow = false;
        this.cellAboveIndex = -1;
    }

    public Integer getAlign() {
        return this.align;
    }

    public Integer getColspan() {
        return this.colspan;
    }

    public int getColumn() {
        return this.column;
    }

    public float getComputedPadBottom() {
        return this.computedPadBottom;
    }

    public float getComputedPadLeft() {
        return this.computedPadLeft;
    }

    public float getComputedPadRight() {
        return this.computedPadRight;
    }

    public float getComputedPadTop() {
        return this.computedPadTop;
    }

    public Integer getExpandX() {
        return this.expandX;
    }

    public Integer getExpandY() {
        return this.expandY;
    }

    public Float getFillX() {
        return this.fillX;
    }

    public Float getFillY() {
        return this.fillY;
    }

    public boolean getIgnore() {
        boolean v0 = true;
        if(this.ignore == null || !this.ignore.booleanValue()) {
            v0 = false;
        }

        return v0;
    }

    public BaseTableLayout getLayout() {
        return this.layout;
    }

    public float getMaxHeight() {
        float v0;
        if(this.maxHeight == null) {
            v0 = 0f;
        }
        else {
            v0 = this.maxHeight.height(this);
        }

        return v0;
    }

    public Value getMaxHeightValue() {
        return this.maxHeight;
    }

    public float getMaxWidth() {
        float v0;
        if(this.maxWidth == null) {
            v0 = 0f;
        }
        else {
            v0 = this.maxWidth.width(this);
        }

        return v0;
    }

    public Value getMaxWidthValue() {
        return this.maxWidth;
    }

    public float getMinHeight() {
        float v0;
        if(this.minHeight == null) {
            v0 = 0f;
        }
        else {
            v0 = this.minHeight.height(this);
        }

        return v0;
    }

    public Value getMinHeightValue() {
        return this.minHeight;
    }

    public float getMinWidth() {
        float v0;
        if(this.minWidth == null) {
            v0 = 0f;
        }
        else {
            v0 = this.minWidth.width(this);
        }

        return v0;
    }

    public Value getMinWidthValue() {
        return this.minWidth;
    }

    public float getPadBottom() {
        float v0;
        if(this.padBottom == null) {
            v0 = 0f;
        }
        else {
            v0 = this.padBottom.height(this);
        }

        return v0;
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        float v0;
        if(this.padLeft == null) {
            v0 = 0f;
        }
        else {
            v0 = this.padLeft.width(this);
        }

        return v0;
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        float v0;
        if(this.padRight == null) {
            v0 = 0f;
        }
        else {
            v0 = this.padRight.width(this);
        }

        return v0;
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        float v0;
        if(this.padTop == null) {
            v0 = 0f;
        }
        else {
            v0 = this.padTop.height(this);
        }

        return v0;
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPrefHeight() {
        float v0;
        if(this.prefHeight == null) {
            v0 = 0f;
        }
        else {
            v0 = this.prefHeight.height(this);
        }

        return v0;
    }

    public Value getPrefHeightValue() {
        return this.prefHeight;
    }

    public float getPrefWidth() {
        float v0;
        if(this.prefWidth == null) {
            v0 = 0f;
        }
        else {
            v0 = this.prefWidth.width(this);
        }

        return v0;
    }

    public Value getPrefWidthValue() {
        return this.prefWidth;
    }

    public int getRow() {
        return this.row;
    }

    public float getSpaceBottom() {
        float v0;
        if(this.spaceBottom == null) {
            v0 = 0f;
        }
        else {
            v0 = this.spaceBottom.height(this);
        }

        return v0;
    }

    public Value getSpaceBottomValue() {
        return this.spaceBottom;
    }

    public float getSpaceLeft() {
        float v0;
        if(this.spaceLeft == null) {
            v0 = 0f;
        }
        else {
            v0 = this.spaceLeft.width(this);
        }

        return v0;
    }

    public Value getSpaceLeftValue() {
        return this.spaceLeft;
    }

    public float getSpaceRight() {
        float v0;
        if(this.spaceRight == null) {
            v0 = 0f;
        }
        else {
            v0 = this.spaceRight.width(this);
        }

        return v0;
    }

    public Value getSpaceRightValue() {
        return this.spaceRight;
    }

    public float getSpaceTop() {
        float v0;
        if(this.spaceTop == null) {
            v0 = 0f;
        }
        else {
            v0 = this.spaceTop.height(this);
        }

        return v0;
    }

    public Value getSpaceTopValue() {
        return this.spaceTop;
    }

    public Boolean getUniformX() {
        return this.uniformX;
    }

    public Boolean getUniformY() {
        return this.uniformY;
    }

    public Object getWidget() {
        return this.widget;
    }

    public float getWidgetHeight() {
        return this.widgetHeight;
    }

    public float getWidgetWidth() {
        return this.widgetWidth;
    }

    public float getWidgetX() {
        return this.widgetX;
    }

    public float getWidgetY() {
        return this.widgetY;
    }

    public boolean hasWidget() {
        boolean v0;
        if(this.widget != null) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public Cell height(float height) {
        this.height(new FixedValue(height));
        return this;
    }

    public Cell height(Value height) {
        this.minHeight = height;
        this.prefHeight = height;
        this.maxHeight = height;
        return this;
    }

    public Cell ignore() {
        this.ignore = Boolean.valueOf(true);
        return this;
    }

    public Cell ignore(Boolean ignore) {
        this.ignore = ignore;
        return this;
    }

    public boolean isEndRow() {
        return this.endRow;
    }

    public Cell left() {
        if(this.align == null) {
            this.align = Integer.valueOf(8);
        }
        else {
            this.align = Integer.valueOf(this.align.intValue() | 8);
            this.align = Integer.valueOf(this.align.intValue() & -17);
        }

        return this;
    }

    public Cell maxHeight(float maxHeight) {
        this.maxHeight = new FixedValue(maxHeight);
        return this;
    }

    public Cell maxHeight(Value maxHeight) {
        this.maxHeight = maxHeight;
        return this;
    }

    public Cell maxSize(float size) {
        this.maxWidth = new FixedValue(size);
        this.maxHeight = new FixedValue(size);
        return this;
    }

    public Cell maxSize(float width, float height) {
        this.maxWidth = new FixedValue(width);
        this.maxHeight = new FixedValue(height);
        return this;
    }

    public Cell maxSize(Value size) {
        this.maxWidth = size;
        this.maxHeight = size;
        return this;
    }

    public Cell maxSize(Value width, Value height) {
        this.maxWidth = width;
        this.maxHeight = height;
        return this;
    }

    public Cell maxWidth(float maxWidth) {
        this.maxWidth = new FixedValue(maxWidth);
        return this;
    }

    public Cell maxWidth(Value maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    void merge(Cell cell) {
        if(cell != null) {
            if(cell.minWidth != null) {
                this.minWidth = cell.minWidth;
            }

            if(cell.minHeight != null) {
                this.minHeight = cell.minHeight;
            }

            if(cell.prefWidth != null) {
                this.prefWidth = cell.prefWidth;
            }

            if(cell.prefHeight != null) {
                this.prefHeight = cell.prefHeight;
            }

            if(cell.maxWidth != null) {
                this.maxWidth = cell.maxWidth;
            }

            if(cell.maxHeight != null) {
                this.maxHeight = cell.maxHeight;
            }

            if(cell.spaceTop != null) {
                this.spaceTop = cell.spaceTop;
            }

            if(cell.spaceLeft != null) {
                this.spaceLeft = cell.spaceLeft;
            }

            if(cell.spaceBottom != null) {
                this.spaceBottom = cell.spaceBottom;
            }

            if(cell.spaceRight != null) {
                this.spaceRight = cell.spaceRight;
            }

            if(cell.padTop != null) {
                this.padTop = cell.padTop;
            }

            if(cell.padLeft != null) {
                this.padLeft = cell.padLeft;
            }

            if(cell.padBottom != null) {
                this.padBottom = cell.padBottom;
            }

            if(cell.padRight != null) {
                this.padRight = cell.padRight;
            }

            if(cell.fillX != null) {
                this.fillX = cell.fillX;
            }

            if(cell.fillY != null) {
                this.fillY = cell.fillY;
            }

            if(cell.align != null) {
                this.align = cell.align;
            }

            if(cell.expandX != null) {
                this.expandX = cell.expandX;
            }

            if(cell.expandY != null) {
                this.expandY = cell.expandY;
            }

            if(cell.ignore != null) {
                this.ignore = cell.ignore;
            }

            if(cell.colspan != null) {
                this.colspan = cell.colspan;
            }

            if(cell.uniformX != null) {
                this.uniformX = cell.uniformX;
            }

            if(cell.uniformY == null) {
                return;
            }

            this.uniformY = cell.uniformY;
        }
    }

    public Cell minHeight(float minHeight) {
        this.minHeight = new FixedValue(minHeight);
        return this;
    }

    public Cell minHeight(Value minHeight) {
        this.minHeight = minHeight;
        return this;
    }

    public Cell minSize(float size) {
        this.minWidth = new FixedValue(size);
        this.minHeight = new FixedValue(size);
        return this;
    }

    public Cell minSize(float width, float height) {
        this.minWidth = new FixedValue(width);
        this.minHeight = new FixedValue(height);
        return this;
    }

    public Cell minSize(Value size) {
        this.minWidth = size;
        this.minHeight = size;
        return this;
    }

    public Cell minSize(Value width, Value height) {
        this.minWidth = width;
        this.minHeight = height;
        return this;
    }

    public Cell minWidth(float minWidth) {
        this.minWidth = new FixedValue(minWidth);
        return this;
    }

    public Cell minWidth(Value minWidth) {
        this.minWidth = minWidth;
        return this;
    }

    public Cell pad(float pad) {
        FixedValue v0 = new FixedValue(pad);
        this.padTop = ((Value)v0);
        this.padLeft = ((Value)v0);
        this.padBottom = ((Value)v0);
        this.padRight = ((Value)v0);
        return this;
    }

    public Cell pad(float top, float left, float bottom, float right) {
        this.padTop = new FixedValue(top);
        this.padLeft = new FixedValue(left);
        this.padBottom = new FixedValue(bottom);
        this.padRight = new FixedValue(right);
        return this;
    }

    public Cell pad(Value pad) {
        this.padTop = pad;
        this.padLeft = pad;
        this.padBottom = pad;
        this.padRight = pad;
        return this;
    }

    public Cell pad(Value top, Value left, Value bottom, Value right) {
        this.padTop = top;
        this.padLeft = left;
        this.padBottom = bottom;
        this.padRight = right;
        return this;
    }

    public Cell padBottom(float padBottom) {
        this.padBottom = new FixedValue(padBottom);
        return this;
    }

    public Cell padBottom(Value padBottom) {
        this.padBottom = padBottom;
        return this;
    }

    public Cell padLeft(float padLeft) {
        this.padLeft = new FixedValue(padLeft);
        return this;
    }

    public Cell padLeft(Value padLeft) {
        this.padLeft = padLeft;
        return this;
    }

    public Cell padRight(float padRight) {
        this.padRight = new FixedValue(padRight);
        return this;
    }

    public Cell padRight(Value padRight) {
        this.padRight = padRight;
        return this;
    }

    public Cell padTop(float padTop) {
        this.padTop = new FixedValue(padTop);
        return this;
    }

    public Cell padTop(Value padTop) {
        this.padTop = padTop;
        return this;
    }

    public Cell prefHeight(float prefHeight) {
        this.prefHeight = new FixedValue(prefHeight);
        return this;
    }

    public Cell prefHeight(Value prefHeight) {
        this.prefHeight = prefHeight;
        return this;
    }

    public Cell prefSize(float size) {
        this.prefWidth = new FixedValue(size);
        this.prefHeight = new FixedValue(size);
        return this;
    }

    public Cell prefSize(float width, float height) {
        this.prefWidth = new FixedValue(width);
        this.prefHeight = new FixedValue(height);
        return this;
    }

    public Cell prefSize(Value size) {
        this.prefWidth = size;
        this.prefHeight = size;
        return this;
    }

    public Cell prefSize(Value width, Value height) {
        this.prefWidth = width;
        this.prefHeight = height;
        return this;
    }

    public Cell prefWidth(float prefWidth) {
        this.prefWidth = new FixedValue(prefWidth);
        return this;
    }

    public Cell prefWidth(Value prefWidth) {
        this.prefWidth = prefWidth;
        return this;
    }

    public Cell right() {
        if(this.align == null) {
            this.align = Integer.valueOf(16);
        }
        else {
            this.align = Integer.valueOf(this.align.intValue() | 16);
            this.align = Integer.valueOf(this.align.intValue() & -9);
        }

        return this;
    }

    public Cell row() {
        return this.layout.row();
    }

    void set(Cell defaults) {
        this.minWidth = defaults.minWidth;
        this.minHeight = defaults.minHeight;
        this.prefWidth = defaults.prefWidth;
        this.prefHeight = defaults.prefHeight;
        this.maxWidth = defaults.maxWidth;
        this.maxHeight = defaults.maxHeight;
        this.spaceTop = defaults.spaceTop;
        this.spaceLeft = defaults.spaceLeft;
        this.spaceBottom = defaults.spaceBottom;
        this.spaceRight = defaults.spaceRight;
        this.padTop = defaults.padTop;
        this.padLeft = defaults.padLeft;
        this.padBottom = defaults.padBottom;
        this.padRight = defaults.padRight;
        this.fillX = defaults.fillX;
        this.fillY = defaults.fillY;
        this.align = defaults.align;
        this.expandX = defaults.expandX;
        this.expandY = defaults.expandY;
        this.ignore = defaults.ignore;
        this.colspan = defaults.colspan;
        this.uniformX = defaults.uniformX;
        this.uniformY = defaults.uniformY;
    }

    public void setLayout(BaseTableLayout layout) {
        this.layout = layout;
    }

    public Cell setWidget(Object arg3) {
        this.layout.toolkit.setWidget(this.layout, this, arg3);
        return this;
    }

    public void setWidgetBounds(float x, float y, float width, float height) {
        this.widgetX = x;
        this.widgetY = y;
        this.widgetWidth = width;
        this.widgetHeight = height;
    }

    public void setWidgetHeight(float widgetHeight) {
        this.widgetHeight = widgetHeight;
    }

    public void setWidgetWidth(float widgetWidth) {
        this.widgetWidth = widgetWidth;
    }

    public void setWidgetX(float widgetX) {
        this.widgetX = widgetX;
    }

    public void setWidgetY(float widgetY) {
        this.widgetY = widgetY;
    }

    public Cell size(float size) {
        this.size(new FixedValue(size));
        return this;
    }

    public Cell size(Value size) {
        this.minWidth = size;
        this.minHeight = size;
        this.prefWidth = size;
        this.prefHeight = size;
        this.maxWidth = size;
        this.maxHeight = size;
        return this;
    }

    public Cell size(float width, float height) {
        this.size(new FixedValue(width), new FixedValue(height));
        return this;
    }

    public Cell size(Value width, Value height) {
        this.minWidth = width;
        this.minHeight = height;
        this.prefWidth = width;
        this.prefHeight = height;
        this.maxWidth = width;
        this.maxHeight = height;
        return this;
    }

    public Cell space(float space) {
        if(space < 0f) {
            throw new IllegalArgumentException("space cannot be < 0.");
        }

        FixedValue v0 = new FixedValue(space);
        this.spaceTop = ((Value)v0);
        this.spaceLeft = ((Value)v0);
        this.spaceBottom = ((Value)v0);
        this.spaceRight = ((Value)v0);
        return this;
    }

    public Cell space(float top, float left, float bottom, float right) {
        if(top < 0f) {
            throw new IllegalArgumentException("top cannot be < 0.");
        }

        if(left < 0f) {
            throw new IllegalArgumentException("left cannot be < 0.");
        }

        if(bottom < 0f) {
            throw new IllegalArgumentException("bottom cannot be < 0.");
        }

        if(right < 0f) {
            throw new IllegalArgumentException("right cannot be < 0.");
        }

        this.spaceTop = new FixedValue(top);
        this.spaceLeft = new FixedValue(left);
        this.spaceBottom = new FixedValue(bottom);
        this.spaceRight = new FixedValue(right);
        return this;
    }

    public Cell space(Value space) {
        this.spaceTop = space;
        this.spaceLeft = space;
        this.spaceBottom = space;
        this.spaceRight = space;
        return this;
    }

    public Cell space(Value top, Value left, Value bottom, Value right) {
        this.spaceTop = top;
        this.spaceLeft = left;
        this.spaceBottom = bottom;
        this.spaceRight = right;
        return this;
    }

    public Cell spaceBottom(float spaceBottom) {
        if(spaceBottom < 0f) {
            throw new IllegalArgumentException("spaceBottom cannot be < 0.");
        }

        this.spaceBottom = new FixedValue(spaceBottom);
        return this;
    }

    public Cell spaceBottom(Value spaceBottom) {
        this.spaceBottom = spaceBottom;
        return this;
    }

    public Cell spaceLeft(float spaceLeft) {
        if(spaceLeft < 0f) {
            throw new IllegalArgumentException("spaceLeft cannot be < 0.");
        }

        this.spaceLeft = new FixedValue(spaceLeft);
        return this;
    }

    public Cell spaceLeft(Value spaceLeft) {
        this.spaceLeft = spaceLeft;
        return this;
    }

    public Cell spaceRight(float spaceRight) {
        if(spaceRight < 0f) {
            throw new IllegalArgumentException("spaceRight cannot be < 0.");
        }

        this.spaceRight = new FixedValue(spaceRight);
        return this;
    }

    public Cell spaceRight(Value spaceRight) {
        this.spaceRight = spaceRight;
        return this;
    }

    public Cell spaceTop(float spaceTop) {
        if(spaceTop < 0f) {
            throw new IllegalArgumentException("spaceTop cannot be < 0.");
        }

        this.spaceTop = new FixedValue(spaceTop);
        return this;
    }

    public Cell spaceTop(Value spaceTop) {
        this.spaceTop = spaceTop;
        return this;
    }

    public Cell top() {
        if(this.align == null) {
            this.align = Integer.valueOf(2);
        }
        else {
            this.align = Integer.valueOf(this.align.intValue() | 2);
            this.align = Integer.valueOf(this.align.intValue() & -5);
        }

        return this;
    }

    public Cell uniform() {
        this.uniformX = Boolean.valueOf(true);
        this.uniformY = Boolean.valueOf(true);
        return this;
    }

    public Cell uniform(Boolean x, Boolean y) {
        this.uniformX = x;
        this.uniformY = y;
        return this;
    }

    public Cell uniformX() {
        this.uniformX = Boolean.valueOf(true);
        return this;
    }

    public Cell uniformY() {
        this.uniformY = Boolean.valueOf(true);
        return this;
    }

    public Cell width(float width) {
        this.width(new FixedValue(width));
        return this;
    }

    public Cell width(Value width) {
        this.minWidth = width;
        this.prefWidth = width;
        this.maxWidth = width;
        return this;
    }
}

