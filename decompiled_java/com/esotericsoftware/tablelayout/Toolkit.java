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

public abstract class Toolkit {
    public static Toolkit instance;

    public Toolkit() {
        super();
    }

    public abstract void addChild(Object arg0, Object arg1);

    public abstract void addDebugRectangle(BaseTableLayout arg0, Debug arg1, float arg2, float arg3, float arg4, float arg5);

    public abstract void clearDebugRectangles(BaseTableLayout arg0);

    public abstract void freeCell(Cell arg0);

    public abstract float getHeight(Object arg0);

    public abstract float getMaxHeight(Object arg0);

    public abstract float getMaxWidth(Object arg0);

    public abstract float getMinHeight(Object arg0);

    public abstract float getMinWidth(Object arg0);

    public abstract float getPrefHeight(Object arg0);

    public abstract float getPrefWidth(Object arg0);

    public abstract float getWidth(Object arg0);

    public float height(float value) {
        return value;
    }

    public abstract Cell obtainCell(BaseTableLayout arg0);

    public abstract void removeChild(Object arg0, Object arg1);

    public void setWidget(BaseTableLayout arg3, Cell cell, Object arg5) {
        if(cell.widget != arg5) {
            this.removeChild(arg3.table, cell.widget);
            cell.widget = arg5;
            if(arg5 != null) {
                this.addChild(arg3.table, arg5);
            }
        }
    }

    public float width(float value) {
        return value;
    }
}

