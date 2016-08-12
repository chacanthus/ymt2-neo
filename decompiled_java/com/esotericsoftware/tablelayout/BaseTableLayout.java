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

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTableLayout {
    public enum Debug {
        static  {
            Debug.none = new Debug("none", 0);
            Debug.all = new Debug("all", 1);
            Debug.table = new Debug("table", 2);
            Debug.cell = new Debug("cell", 3);
            Debug.widget = new Debug("widget", 4);
            Debug[] v0 = new Debug[5];
            v0[0] = Debug.none;
            v0[1] = Debug.all;
            v0[2] = Debug.table;
            v0[3] = Debug.cell;
            v0[4] = Debug.widget;
            Debug.$VALUES = v0;
        }

        private Debug(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Debug valueOf(String name) {
            return Enum.valueOf(Debug.class, name);
        }

        public static Debug[] values() {
            return Debug.$VALUES.clone();
        }
    }

    public static final int BOTTOM = 4;
    public static final int CENTER = 1;
    public static final int LEFT = 8;
    public static final int RIGHT = 16;
    public static final int TOP = 2;
    int align;
    private final Cell cellDefaults;
    private final ArrayList cells;
    private final ArrayList columnDefaults;
    private float[] columnMinWidth;
    private float[] columnPrefWidth;
    private float[] columnWeightedWidth;
    private float[] columnWidth;
    private int columns;
    Debug debug;
    private float[] expandHeight;
    private float[] expandWidth;
    Value padBottom;
    Value padLeft;
    Value padRight;
    Value padTop;
    private Cell rowDefaults;
    private float[] rowHeight;
    private float[] rowMinHeight;
    private float[] rowPrefHeight;
    private float[] rowWeightedHeight;
    private int rows;
    private boolean sizeInvalid;
    Object table;
    private float tableMinHeight;
    private float tableMinWidth;
    private float tablePrefHeight;
    private float tablePrefWidth;
    Toolkit toolkit;

    public BaseTableLayout(Toolkit arg4) {
        super();
        this.cells = new ArrayList(4);
        this.columnDefaults = new ArrayList(2);
        this.sizeInvalid = true;
        this.align = 1;
        this.debug = Debug.none;
        this.toolkit = arg4;
        this.cellDefaults = arg4.obtainCell(this);
        this.cellDefaults.defaults();
    }

    public Cell add(Object arg11) {
        Cell v0 = this.toolkit.obtainCell(this);
        v0.widget = arg11;
        if(this.cells.size() > 0) {
            Object v4 = this.cells.get(this.cells.size() - 1);
            if(!((Cell)v4).endRow) {
                v0.column = ((Cell)v4).column + ((Cell)v4).colspan.intValue();
                v0.row = ((Cell)v4).row;
            }
            else {
                v0.column = 0;
                v0.row = ((Cell)v4).row + 1;
            }

            if(v0.row <= 0) {
                goto label_37;
            }

            int v3;
            for(v3 = this.cells.size() - 1; v3 >= 0; --v3) {
                Object v6 = this.cells.get(v3);
                int v1 = ((Cell)v6).column;
                int v5 = v1 + ((Cell)v6).colspan.intValue();
                while(v1 < v5) {
                    if(v1 != v0.column) {
                        goto label_62;
                    }

                    v0.cellAboveIndex = v3;
                    goto label_37;
                label_62:
                    ++v1;
                }
            }
        }
        else {
            v0.column = 0;
            v0.row = 0;
        }

    label_37:
        this.cells.add(v0);
        v0.set(this.cellDefaults);
        if(v0.column < this.columnDefaults.size()) {
            Object v2 = this.columnDefaults.get(v0.column);
            if(v2 != null) {
                v0.merge(((Cell)v2));
            }
        }

        v0.merge(this.rowDefaults);
        if(arg11 != null) {
            this.toolkit.addChild(this.table, arg11);
        }

        return v0;
    }

    public BaseTableLayout align(int align) {
        this.align = align;
        return this;
    }

    public BaseTableLayout bottom() {
        this.align |= 4;
        this.align &= -3;
        return this;
    }

    public BaseTableLayout center() {
        this.align = 1;
        return this;
    }

    public void clear() {
        int v1;
        for(v1 = this.cells.size() - 1; v1 >= 0; --v1) {
            Object v0 = this.cells.get(v1);
            Object v2 = ((Cell)v0).widget;
            if(v2 != null) {
                this.toolkit.removeChild(this.table, v2);
            }

            this.toolkit.freeCell(((Cell)v0));
        }

        this.cells.clear();
        this.rows = 0;
        this.columns = 0;
        if(this.rowDefaults != null) {
            this.toolkit.freeCell(this.rowDefaults);
        }

        this.rowDefaults = null;
        this.invalidate();
    }

    public Cell columnDefaults(int column) {
        Cell v0_1;
        Object v0;
        Object v3 = null;
        if(this.columnDefaults.size() > column) {
            v0 = this.columnDefaults.get(column);
        }
        else {
            v0 = v3;
        }

        if(v0 == null) {
            v0_1 = this.toolkit.obtainCell(this);
            v0_1.clear();
            if(column >= this.columnDefaults.size()) {
                int v1;
                for(v1 = this.columnDefaults.size(); v1 < column; ++v1) {
                    this.columnDefaults.add(v3);
                }

                this.columnDefaults.add(v0_1);
            }
            else {
                this.columnDefaults.set(column, v0_1);
            }
        }

        return v0_1;
    }

    private void computeSize() {
        float v19;
        int v16;
        int v6;
        float v29;
        float v9;
        float v12;
        float v14;
        float v18;
        float v30;
        Object v4;
        this.sizeInvalid = false;
        ArrayList v5 = this.cells;
        if(v5.size() > 0 && !v5.get(v5.size() - 1).endRow) {
            this.endRow();
        }

        this.columnMinWidth = this.ensureSize(this.columnMinWidth, this.columns);
        this.rowMinHeight = this.ensureSize(this.rowMinHeight, this.rows);
        this.columnPrefWidth = this.ensureSize(this.columnPrefWidth, this.columns);
        this.rowPrefHeight = this.ensureSize(this.rowPrefHeight, this.rows);
        this.columnWidth = this.ensureSize(this.columnWidth, this.columns);
        this.rowHeight = this.ensureSize(this.rowHeight, this.rows);
        this.expandWidth = this.ensureSize(this.expandWidth, this.columns);
        this.expandHeight = this.ensureSize(this.expandHeight, this.rows);
        float v21 = 0f;
        int v10 = 0;
        int v15 = v5.size();
        while(v10 < v15) {
            v4 = v5.get(v10);
            if(!((Cell)v4).ignore.booleanValue()) {
                if(((Cell)v4).expandY.intValue() != 0 && this.expandHeight[((Cell)v4).row] == 0f) {
                    this.expandHeight[((Cell)v4).row] = ((float)((Cell)v4).expandY.intValue());
                }

                if(((Cell)v4).colspan.intValue() == 1 && ((Cell)v4).expandX.intValue() != 0 && this.expandWidth[((Cell)v4).column] == 0f) {
                    this.expandWidth[((Cell)v4).column] = ((float)((Cell)v4).expandX.intValue());
                }

                float v31 = this.w(((Cell)v4).padLeft, ((Cell)v4));
                if(((Cell)v4).column == 0) {
                    v30 = 0f;
                }
                else {
                    v30 = Math.max(0f, this.w(((Cell)v4).spaceLeft, ((Cell)v4)) - v21);
                }

                ((Cell)v4).computedPadLeft = v30 + v31;
                ((Cell)v4).computedPadTop = this.h(((Cell)v4).padTop, ((Cell)v4));
                if(((Cell)v4).cellAboveIndex != -1) {
                    Object v3 = v5.get(((Cell)v4).cellAboveIndex);
                    ((Cell)v4).computedPadTop += Math.max(0f, this.h(((Cell)v4).spaceTop, ((Cell)v4)) - this.h(((Cell)v3).spaceBottom, ((Cell)v3)));
                }

                float v20 = this.w(((Cell)v4).spaceRight, ((Cell)v4));
                v31 = this.w(((Cell)v4).padRight, ((Cell)v4));
                if(((Cell)v4).column + ((Cell)v4).colspan.intValue() == this.columns) {
                    v30 = 0f;
                }
                else {
                    v30 = v20;
                }

                ((Cell)v4).computedPadRight = v30 + v31;
                v31 = this.h(((Cell)v4).padBottom, ((Cell)v4));
                if(((Cell)v4).row == this.rows - 1) {
                    v30 = 0f;
                }
                else {
                    v30 = this.h(((Cell)v4).spaceBottom, ((Cell)v4));
                }

                ((Cell)v4).computedPadBottom = v30 + v31;
                v21 = v20;
                v18 = ((Cell)v4).prefWidth.get(((Cell)v4));
                float v17 = ((Cell)v4).prefHeight.get(((Cell)v4));
                v14 = ((Cell)v4).minWidth.get(((Cell)v4));
                float v13 = ((Cell)v4).minHeight.get(((Cell)v4));
                v12 = ((Cell)v4).maxWidth.get(((Cell)v4));
                float v11 = ((Cell)v4).maxHeight.get(((Cell)v4));
                if(v18 < v14) {
                    v18 = v14;
                }

                if(v17 < v13) {
                    v17 = v13;
                }

                if(v12 > 0f && v18 > v12) {
                    v18 = v12;
                }

                if(v11 > 0f && v17 > v11) {
                    v17 = v11;
                }

                if(((Cell)v4).colspan.intValue() == 1) {
                    v9 = ((Cell)v4).computedPadLeft + ((Cell)v4).computedPadRight;
                    this.columnPrefWidth[((Cell)v4).column] = Math.max(this.columnPrefWidth[((Cell)v4).column], v18 + v9);
                    this.columnMinWidth[((Cell)v4).column] = Math.max(this.columnMinWidth[((Cell)v4).column], v14 + v9);
                }

                v29 = ((Cell)v4).computedPadTop + ((Cell)v4).computedPadBottom;
                this.rowPrefHeight[((Cell)v4).row] = Math.max(this.rowPrefHeight[((Cell)v4).row], v17 + v29);
                this.rowMinHeight[((Cell)v4).row] = Math.max(this.rowMinHeight[((Cell)v4).row], v13 + v29);
            }

            ++v10;
        }

        v10 = 0;
        v15 = v5.size();
        while(v10 < v15) {
            v4 = v5.get(v10);
            if(!((Cell)v4).ignore.booleanValue() && ((Cell)v4).expandX.intValue() != 0) {
                v6 = ((Cell)v4).column;
                v16 = v6 + ((Cell)v4).colspan.intValue();
                while(true) {
                    if(v6 >= v16) {
                        break;
                    }
                    else if(this.expandWidth[v6] == 0f) {
                        ++v6;
                        continue;
                    }

                    goto label_420;
                }

                v6 = ((Cell)v4).column;
                v16 = v6 + ((Cell)v4).colspan.intValue();
                while(v6 < v16) {
                    this.expandWidth[v6] = ((float)((Cell)v4).expandX.intValue());
                    ++v6;
                }
            }

        label_420:
            ++v10;
        }

        v10 = 0;
        v15 = v5.size();
        while(v10 < v15) {
            v4 = v5.get(v10);
            if(!((Cell)v4).ignore.booleanValue() && ((Cell)v4).colspan.intValue() != 1) {
                v14 = ((Cell)v4).minWidth.get(((Cell)v4));
                v18 = ((Cell)v4).prefWidth.get(((Cell)v4));
                v12 = ((Cell)v4).maxWidth.get(((Cell)v4));
                if(v18 < v14) {
                    v18 = v14;
                }

                if(v12 > 0f && v18 > v12) {
                    v18 = v12;
                }

                float v22 = -(((Cell)v4).computedPadLeft + ((Cell)v4).computedPadRight);
                float v23 = v22;
                v6 = ((Cell)v4).column;
                v16 = v6 + ((Cell)v4).colspan.intValue();
                while(v6 < v16) {
                    v22 += this.columnMinWidth[v6];
                    v23 += this.columnPrefWidth[v6];
                    ++v6;
                }

                float v24 = 0f;
                v6 = ((Cell)v4).column;
                v16 = v6 + ((Cell)v4).colspan.intValue();
                while(v6 < v16) {
                    v24 += this.expandWidth[v6];
                    ++v6;
                }

                float v7 = Math.max(0f, v14 - v22);
                float v8 = Math.max(0f, v18 - v23);
                v6 = ((Cell)v4).column;
                v16 = v6 + ((Cell)v4).colspan.intValue();
                while(v6 < v16) {
                    if(v24 == 0f) {
                        v19 = 1f / (((float)((Cell)v4).colspan.intValue()));
                    }
                    else {
                        v19 = this.expandWidth[v6] / v24;
                    }

                    this.columnMinWidth[v6] += v7 * v19;
                    this.columnPrefWidth[v6] += v8 * v19;
                    ++v6;
                }
            }

            ++v10;
        }

        float v26 = 0f;
        float v25 = 0f;
        float v28 = 0f;
        float v27 = 0f;
        v10 = 0;
        v15 = v5.size();
        while(v10 < v15) {
            v4 = v5.get(v10);
            if(!((Cell)v4).ignore.booleanValue()) {
                if(((Cell)v4).uniformX == Boolean.TRUE && ((Cell)v4).colspan.intValue() == 1) {
                    v9 = ((Cell)v4).computedPadLeft + ((Cell)v4).computedPadRight;
                    v26 = Math.max(v26, this.columnMinWidth[((Cell)v4).column] - v9);
                    v28 = Math.max(v28, this.columnPrefWidth[((Cell)v4).column] - v9);
                }

                if(((Cell)v4).uniformY != Boolean.TRUE) {
                    goto label_591;
                }

                v29 = ((Cell)v4).computedPadTop + ((Cell)v4).computedPadBottom;
                v25 = Math.max(v25, this.rowMinHeight[((Cell)v4).row] - v29);
                v27 = Math.max(v27, this.rowPrefHeight[((Cell)v4).row] - v29);
            }

        label_591:
            ++v10;
        }

        if(v28 > 0f || v27 > 0f) {
            v10 = 0;
            v15 = v5.size();
            while(v10 < v15) {
                v4 = v5.get(v10);
                if(!((Cell)v4).ignore.booleanValue()) {
                    if(v28 > 0f && ((Cell)v4).uniformX == Boolean.TRUE && ((Cell)v4).colspan.intValue() == 1) {
                        v9 = ((Cell)v4).computedPadLeft + ((Cell)v4).computedPadRight;
                        this.columnMinWidth[((Cell)v4).column] = v26 + v9;
                        this.columnPrefWidth[((Cell)v4).column] = v28 + v9;
                    }

                    if(v27 <= 0f) {
                        goto label_675;
                    }

                    if(((Cell)v4).uniformY != Boolean.TRUE) {
                        goto label_675;
                    }

                    v29 = ((Cell)v4).computedPadTop + ((Cell)v4).computedPadBottom;
                    this.rowMinHeight[((Cell)v4).row] = v25 + v29;
                    this.rowPrefHeight[((Cell)v4).row] = v27 + v29;
                }

            label_675:
                ++v10;
            }
        }

        this.tableMinWidth = 0f;
        this.tableMinHeight = 0f;
        this.tablePrefWidth = 0f;
        this.tablePrefHeight = 0f;
        for(v10 = 0; v10 < this.columns; ++v10) {
            this.tableMinWidth += this.columnMinWidth[v10];
            this.tablePrefWidth += this.columnPrefWidth[v10];
        }

        for(v10 = 0; v10 < this.rows; ++v10) {
            this.tableMinHeight += this.rowMinHeight[v10];
            this.tablePrefHeight += Math.max(this.rowMinHeight[v10], this.rowPrefHeight[v10]);
        }

        v9 = this.w(this.padLeft) + this.w(this.padRight);
        v29 = this.h(this.padTop) + this.h(this.padBottom);
        this.tableMinWidth += v9;
        this.tableMinHeight += v29;
        this.tablePrefWidth = Math.max(this.tablePrefWidth + v9, this.tableMinWidth);
        this.tablePrefHeight = Math.max(this.tablePrefHeight + v29, this.tableMinHeight);
    }

    public BaseTableLayout debug() {
        this.debug = Debug.all;
        this.invalidate();
        return this;
    }

    public BaseTableLayout debug(Debug debug) {
        this.debug = debug;
        if(debug == Debug.none) {
            this.toolkit.clearDebugRectangles(this);
        }
        else {
            this.invalidate();
        }

        return this;
    }

    public BaseTableLayout debugCell() {
        this.debug = Debug.cell;
        this.invalidate();
        return this;
    }

    public BaseTableLayout debugTable() {
        this.debug = Debug.table;
        this.invalidate();
        return this;
    }

    public BaseTableLayout debugWidget() {
        this.debug = Debug.widget;
        this.invalidate();
        return this;
    }

    public Cell defaults() {
        return this.cellDefaults;
    }

    private void endRow() {
        int v2 = 0;
        int v1 = this.cells.size() - 1;
        while(v1 >= 0) {
            Object v0 = this.cells.get(v1);
            if(((Cell)v0).endRow) {
                break;
            }

            v2 += ((Cell)v0).colspan.intValue();
            --v1;
        }

        this.columns = Math.max(this.columns, v2);
        ++this.rows;
        this.cells.get(this.cells.size() - 1).endRow = true;
    }

    private float[] ensureSize(float[] array, int size) {
        if(array == null || array.length < size) {
            array = new float[size];
        }
        else {
            int v0 = 0;
            int v1 = array.length;
            while(v0 < v1) {
                array[v0] = 0f;
                ++v0;
            }
        }

        return array;
    }

    public int getAlign() {
        return this.align;
    }

    public Cell getCell(Object arg5) {
        Object v0;
        int v1 = 0;
        int v2 = this.cells.size();
        while(true) {
            if(v1 < v2) {
                v0 = this.cells.get(v1);
                if(((Cell)v0).widget != arg5) {
                    ++v1;
                    continue;
                }
            }
            else {
                break;
            }

            goto label_8;
        }

        Cell v0_1 = null;
    label_8:
        return ((Cell)v0);
    }

    public List getCells() {
        return this.cells;
    }

    public Debug getDebug() {
        return this.debug;
    }

    public float getMinHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.tableMinHeight;
    }

    public float getMinWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.tableMinWidth;
    }

    public float getPadBottom() {
        float v0;
        if(this.padBottom == null) {
            v0 = 0f;
        }
        else {
            v0 = this.padBottom.height(this.table);
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
            v0 = this.padLeft.width(this.table);
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
            v0 = this.padRight.width(this.table);
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
            v0 = this.padTop.height(this.table);
        }

        return v0;
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.tablePrefHeight;
    }

    public float getPrefWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }

        return this.tablePrefWidth;
    }

    public int getRow(float y) {
        Object v0;
        int v1;
        int v2;
        int v5 = 0;
        int v4 = 0;
        y += this.h(this.padTop);
        int v3 = this.cells.size();
        if(v3 == 0) {
            v5 = -1;
        }
        else if(v3 != 1) {
            if(this.cells.get(0).widgetY < this.cells.get(1).widgetY) {
                v2 = 0;
                while(v2 < v3) {
                    v1 = v2 + 1;
                    v0 = this.cells.get(v2);
                    if(((Cell)v0).getIgnore()) {
                        v2 = v1;
                        continue;
                    }
                    else if(((Cell)v0).widgetY + ((Cell)v0).computedPadTop <= y) {
                        if(((Cell)v0).endRow) {
                            ++v4;
                        }

                        v2 = v1;
                        continue;
                    }

                    break;
                }

                v5 = v4 - 1;
            }
            else {
                v2 = 0;
                while(v2 < v3) {
                    v1 = v2 + 1;
                    v0 = this.cells.get(v2);
                    if(((Cell)v0).getIgnore()) {
                        v2 = v1;
                        continue;
                    }
                    else if(((Cell)v0).widgetY + ((Cell)v0).computedPadTop >= y) {
                        if(((Cell)v0).endRow) {
                            ++v4;
                        }

                        v2 = v1;
                        continue;
                    }

                    break;
                }

                v5 = v4;
            }
        }

        return v5;
    }

    public Object getTable() {
        return this.table;
    }

    private float h(Value value, Cell cell) {
        float v0;
        if(value == null) {
            v0 = 0f;
        }
        else {
            v0 = value.height(cell);
        }

        return v0;
    }

    private float h(Value value) {
        float v0;
        if(value == null) {
            v0 = 0f;
        }
        else {
            v0 = value.height(this.table);
        }

        return v0;
    }

    public void invalidate() {
        this.sizeInvalid = true;
    }

    public abstract void invalidateHierarchy();

    public void layout(float layoutX, float layoutY, float layoutWidth, float layoutHeight) {
        float v7;
        float v9;
        int v24;
        float v43;
        float v16;
        float v25;
        float v26;
        int v30;
        int v12;
        Object v10;
        float[] v35;
        float v18;
        float[] v13;
        Toolkit v2 = this.toolkit;
        ArrayList v11 = this.cells;
        if(this.sizeInvalid) {
            this.computeSize();
        }

        float v31 = this.w(this.padLeft);
        float v22 = v31 + this.w(this.padRight);
        float v32 = this.h(this.padTop);
        float v44 = v32 + this.h(this.padBottom);
        float v40 = 0f;
        float v39 = 0f;
        int v23;
        for(v23 = 0; v23 < this.columns; ++v23) {
            v40 += this.expandWidth[v23];
        }

        for(v23 = 0; v23 < this.rows; ++v23) {
            v39 += this.expandHeight[v23];
        }

        float v42 = this.tablePrefWidth - this.tableMinWidth;
        if(v42 == 0f) {
            v13 = this.columnMinWidth;
        }
        else {
            v18 = Math.min(v42, Math.max(0f, layoutWidth - this.tableMinWidth));
            v13 = this.ensureSize(this.columnWeightedWidth, this.columns);
            this.columnWeightedWidth = v13;
            for(v23 = 0; v23 < this.columns; ++v23) {
                v13[v23] = this.columnMinWidth[v23] + v18 * ((this.columnPrefWidth[v23] - this.columnMinWidth[v23]) / v42);
            }
        }

        float v41 = this.tablePrefHeight - this.tableMinHeight;
        if(v41 == 0f) {
            v35 = this.rowMinHeight;
        }
        else {
            v35 = this.ensureSize(this.rowWeightedHeight, this.rows);
            this.rowWeightedHeight = v35;
            float v17 = Math.min(v41, Math.max(0f, layoutHeight - this.tableMinHeight));
            for(v23 = 0; v23 < this.rows; ++v23) {
                v35[v23] = this.rowMinHeight[v23] + v17 * ((this.rowPrefHeight[v23] - this.rowMinHeight[v23]) / v41);
            }
        }

        v23 = 0;
        int v29 = v11.size();
        while(v23 < v29) {
            v10 = v11.get(v23);
            if(!((Cell)v10).ignore.booleanValue()) {
                float v36 = 0f;
                v12 = ((Cell)v10).column;
                v30 = v12 + ((Cell)v10).colspan.intValue();
                while(v12 < v30) {
                    v36 += v13[v12];
                    ++v12;
                }

                float v45 = v35[((Cell)v10).row];
                float v34 = ((Cell)v10).prefWidth.get(((Cell)v10));
                float v33 = ((Cell)v10).prefHeight.get(((Cell)v10));
                float v28 = ((Cell)v10).minWidth.get(((Cell)v10));
                float v27 = ((Cell)v10).minHeight.get(((Cell)v10));
                v26 = ((Cell)v10).maxWidth.get(((Cell)v10));
                v25 = ((Cell)v10).maxHeight.get(((Cell)v10));
                if(v34 < v28) {
                    v34 = v28;
                }

                if(v33 < v27) {
                    v33 = v27;
                }

                if(v26 > 0f && v34 > v26) {
                    v34 = v26;
                }

                if(v25 > 0f && v33 > v25) {
                    v33 = v25;
                }

                ((Cell)v10).widgetWidth = Math.min(v36 - ((Cell)v10).computedPadLeft - ((Cell)v10).computedPadRight, v34);
                ((Cell)v10).widgetHeight = Math.min(v45 - ((Cell)v10).computedPadTop - ((Cell)v10).computedPadBottom, v33);
                if(((Cell)v10).colspan.intValue() == 1) {
                    this.columnWidth[((Cell)v10).column] = Math.max(this.columnWidth[((Cell)v10).column], v36);
                }

                this.rowHeight[((Cell)v10).row] = Math.max(this.rowHeight[((Cell)v10).row], v45);
            }

            ++v23;
        }

        if(v40 > 0f) {
            v16 = layoutWidth - v22;
            for(v23 = 0; v23 < this.columns; ++v23) {
                v16 -= this.columnWidth[v23];
            }

            v43 = 0f;
            v24 = 0;
            for(v23 = 0; v23 < this.columns; ++v23) {
                if(this.expandWidth[v23] != 0f) {
                    v9 = this.expandWidth[v23] * v16 / v40;
                    this.columnWidth[v23] += v9;
                    v43 += v9;
                    v24 = v23;
                }
            }

            this.columnWidth[v24] += v16 - v43;
        }

        if(v39 > 0f) {
            v16 = layoutHeight - v44;
            for(v23 = 0; v23 < this.rows; ++v23) {
                v16 -= this.rowHeight[v23];
            }

            v43 = 0f;
            v24 = 0;
            for(v23 = 0; v23 < this.rows; ++v23) {
                if(this.expandHeight[v23] != 0f) {
                    v9 = this.expandHeight[v23] * v16 / v39;
                    this.rowHeight[v23] += v9;
                    v43 += v9;
                    v24 = v23;
                }
            }

            this.rowHeight[v24] += v16 - v43;
        }

        v23 = 0;
        v29 = v11.size();
        while(v23 < v29) {
            v10 = v11.get(v23);
            if(!((Cell)v10).ignore.booleanValue() && ((Cell)v10).colspan.intValue() != 1) {
                v18 = 0f;
                v12 = ((Cell)v10).column;
                v30 = v12 + ((Cell)v10).colspan.intValue();
                while(v12 < v30) {
                    v18 += v13[v12] - this.columnWidth[v12];
                    ++v12;
                }

                v18 = (v18 - Math.max(0f, ((Cell)v10).computedPadLeft + ((Cell)v10).computedPadRight)) / (((float)((Cell)v10).colspan.intValue()));
                if(v18 > 0f) {
                    v12 = ((Cell)v10).column;
                    v30 = v12 + ((Cell)v10).colspan.intValue();
                    while(v12 < v30) {
                        this.columnWidth[v12] += v18;
                        ++v12;
                    }
                }
            }

            ++v23;
        }

        float v38 = v22;
        float v37 = v44;
        for(v23 = 0; v23 < this.columns; ++v23) {
            v38 += this.columnWidth[v23];
        }

        for(v23 = 0; v23 < this.rows; ++v23) {
            v37 += this.rowHeight[v23];
        }

        float v46 = layoutX + v31;
        if((this.align & 16) != 0) {
            v46 += layoutWidth - v38;
        }
        else if((this.align & 8) == 0) {
            v46 += (layoutWidth - v38) / 2f;
        }

        float v47 = layoutY + v32;
        if((this.align & 4) != 0) {
            v47 += layoutHeight - v37;
        }
        else if((this.align & 2) == 0) {
            v47 += (layoutHeight - v37) / 2f;
        }

        float v5 = v46;
        float v15 = v47;
        v23 = 0;
        v29 = v11.size();
        while(v23 < v29) {
            v10 = v11.get(v23);
            if(!((Cell)v10).ignore.booleanValue()) {
                v7 = 0f;
                v12 = ((Cell)v10).column;
                v30 = v12 + ((Cell)v10).colspan.intValue();
                while(v12 < v30) {
                    v7 += this.columnWidth[v12];
                    ++v12;
                }

                v7 -= ((Cell)v10).computedPadLeft + ((Cell)v10).computedPadRight;
                v5 += ((Cell)v10).computedPadLeft;
                if(((Cell)v10).fillX.floatValue() > 0f) {
                    ((Cell)v10).widgetWidth = ((Cell)v10).fillX.floatValue() * v7;
                    v26 = ((Cell)v10).maxWidth.get(((Cell)v10));
                    if(v26 > 0f) {
                        ((Cell)v10).widgetWidth = Math.min(((Cell)v10).widgetWidth, v26);
                    }
                }

                if(((Cell)v10).fillY.floatValue() > 0f) {
                    ((Cell)v10).widgetHeight = this.rowHeight[((Cell)v10).row] * ((Cell)v10).fillY.floatValue() - ((Cell)v10).computedPadTop - ((Cell)v10).computedPadBottom;
                    v25 = ((Cell)v10).maxHeight.get(((Cell)v10));
                    if(v25 > 0f) {
                        ((Cell)v10).widgetHeight = Math.min(((Cell)v10).widgetHeight, v25);
                    }
                }

                if((((Cell)v10).align.intValue() & 8) != 0) {
                    ((Cell)v10).widgetX = v5;
                }
                else if((((Cell)v10).align.intValue() & 16) != 0) {
                    ((Cell)v10).widgetX = v5 + v7 - ((Cell)v10).widgetWidth;
                }
                else {
                    ((Cell)v10).widgetX = (v7 - ((Cell)v10).widgetWidth) / 2f + v5;
                }

                if((((Cell)v10).align.intValue() & 2) != 0) {
                    ((Cell)v10).widgetY = ((Cell)v10).computedPadTop + v15;
                }
                else if((((Cell)v10).align.intValue() & 4) != 0) {
                    ((Cell)v10).widgetY = this.rowHeight[((Cell)v10).row] + v15 - ((Cell)v10).widgetHeight - ((Cell)v10).computedPadBottom;
                }
                else {
                    ((Cell)v10).widgetY = (this.rowHeight[((Cell)v10).row] - ((Cell)v10).widgetHeight + ((Cell)v10).computedPadTop - ((Cell)v10).computedPadBottom) / 2f + v15;
                }

                if(!((Cell)v10).endRow) {
                    goto label_576;
                }

                v5 = v46;
                v15 += this.rowHeight[((Cell)v10).row];
                goto label_430;
            label_576:
                v5 += ((Cell)v10).computedPadRight + v7;
            }

        label_430:
            ++v23;
        }

        if(this.debug != Debug.none) {
            v2.clearDebugRectangles(this);
            float v14 = v46;
            v15 = v47;
            if(this.debug == Debug.table || this.debug == Debug.all) {
                v2.addDebugRectangle(this, Debug.table, layoutX, layoutY, layoutWidth, layoutHeight);
                v2.addDebugRectangle(this, Debug.table, v46, v47, v38 - v22, v37 - v44);
            }

            v23 = 0;
            v29 = v11.size();
            while(v23 < v29) {
                v10 = v11.get(v23);
                if(((Cell)v10).ignore.booleanValue()) {
                    v5 = v14;
                }
                else {
                    if(this.debug == Debug.widget || this.debug == Debug.all) {
                        v2.addDebugRectangle(this, Debug.widget, ((Cell)v10).widgetX, ((Cell)v10).widgetY, ((Cell)v10).widgetWidth, ((Cell)v10).widgetHeight);
                    }

                    v7 = 0f;
                    v12 = ((Cell)v10).column;
                    v30 = v12 + ((Cell)v10).colspan.intValue();
                    while(v12 < v30) {
                        v7 += this.columnWidth[v12];
                        ++v12;
                    }

                    v7 -= ((Cell)v10).computedPadLeft + ((Cell)v10).computedPadRight;
                    v5 = v14 + ((Cell)v10).computedPadLeft;
                    if(this.debug == Debug.cell || this.debug == Debug.all) {
                        v2.addDebugRectangle(this, Debug.cell, v5, v15 + ((Cell)v10).computedPadTop, v7, this.rowHeight[((Cell)v10).row] - ((Cell)v10).computedPadTop - ((Cell)v10).computedPadBottom);
                    }

                    if(!((Cell)v10).endRow) {
                        goto label_689;
                    }

                    v5 = v46;
                    v15 += this.rowHeight[((Cell)v10).row];
                    goto label_622;
                label_689:
                    v5 += ((Cell)v10).computedPadRight + v7;
                }

            label_622:
                ++v23;
                v14 = v5;
            }
        }
    }

    public BaseTableLayout left() {
        this.align |= 8;
        this.align &= -17;
        return this;
    }

    public BaseTableLayout pad(float pad) {
        this.padTop = new FixedValue(pad);
        this.padLeft = new FixedValue(pad);
        this.padBottom = new FixedValue(pad);
        this.padRight = new FixedValue(pad);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout pad(float top, float left, float bottom, float right) {
        this.padTop = new FixedValue(top);
        this.padLeft = new FixedValue(left);
        this.padBottom = new FixedValue(bottom);
        this.padRight = new FixedValue(right);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout pad(Value pad) {
        this.padTop = pad;
        this.padLeft = pad;
        this.padBottom = pad;
        this.padRight = pad;
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout pad(Value top, Value left, Value bottom, Value right) {
        this.padTop = top;
        this.padLeft = left;
        this.padBottom = bottom;
        this.padRight = right;
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padBottom(float padBottom) {
        this.padBottom = new FixedValue(padBottom);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padBottom(Value padBottom) {
        this.padBottom = padBottom;
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padLeft(float padLeft) {
        this.padLeft = new FixedValue(padLeft);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padLeft(Value padLeft) {
        this.padLeft = padLeft;
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padRight(float padRight) {
        this.padRight = new FixedValue(padRight);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padRight(Value padRight) {
        this.padRight = padRight;
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padTop(float padTop) {
        this.padTop = new FixedValue(padTop);
        this.sizeInvalid = true;
        return this;
    }

    public BaseTableLayout padTop(Value padTop) {
        this.padTop = padTop;
        this.sizeInvalid = true;
        return this;
    }

    public void reset() {
        this.clear();
        this.padTop = null;
        this.padLeft = null;
        this.padBottom = null;
        this.padRight = null;
        this.align = 1;
        if(this.debug != Debug.none) {
            this.toolkit.clearDebugRectangles(this);
        }

        this.debug = Debug.none;
        this.cellDefaults.defaults();
        int v1 = 0;
        int v2 = this.columnDefaults.size();
        while(v1 < v2) {
            Object v0 = this.columnDefaults.get(v1);
            if(v0 != null) {
                this.toolkit.freeCell(((Cell)v0));
            }

            ++v1;
        }

        this.columnDefaults.clear();
    }

    public BaseTableLayout right() {
        this.align |= 16;
        this.align &= -9;
        return this;
    }

    public Cell row() {
        if(this.cells.size() > 0) {
            this.endRow();
            this.invalidate();
        }

        if(this.rowDefaults != null) {
            this.toolkit.freeCell(this.rowDefaults);
        }

        this.rowDefaults = this.toolkit.obtainCell(this);
        this.rowDefaults.clear();
        return this.rowDefaults;
    }

    public void setTable(Object arg1) {
        this.table = arg1;
    }

    public void setToolkit(Toolkit arg1) {
        this.toolkit = arg1;
    }

    public BaseTableLayout top() {
        this.align |= 2;
        this.align &= -5;
        return this;
    }

    private float w(Value value, Cell cell) {
        float v0;
        if(value == null) {
            v0 = 0f;
        }
        else {
            v0 = value.width(cell);
        }

        return v0;
    }

    private float w(Value value) {
        float v0;
        if(value == null) {
            v0 = 0f;
        }
        else {
            v0 = value.width(this.table);
        }

        return v0;
    }
}

