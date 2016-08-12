// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.physics.box2d;

import com.badlogic.gdx.math.Vector2;

public class EdgeShape extends Shape {
    static final float[] vertex;

    static  {
        EdgeShape.vertex = new float[2];
    }

    EdgeShape(long addr) {
        super();
        this.addr = addr;
    }

    public EdgeShape() {
        super();
        this.addr = this.newEdgeShape();
    }

    public Type getType() {
        return Type.Edge;
    }

    public void getVertex1(Vector2 vec) {
        this.jniGetVertex1(this.addr, EdgeShape.vertex);
        vec.x = EdgeShape.vertex[0];
        vec.y = EdgeShape.vertex[1];
    }

    public void getVertex2(Vector2 vec) {
        this.jniGetVertex2(this.addr, EdgeShape.vertex);
        vec.x = EdgeShape.vertex[0];
        vec.y = EdgeShape.vertex[1];
    }

    private native void jniGetVertex1(float[] arg1) {
    }

    private native void jniGetVertex2(float[] arg1) {
    }

    private native void jniSet(float arg1, float arg2, float arg3, float arg4) {
    }

    private native long newEdgeShape() {
    }

    public void set(float v1X, float v1Y, float v2X, float v2Y) {
        this.jniSet(this.addr, v1X, v1Y, v2X, v2Y);
    }

    public void set(Vector2 v1, Vector2 v2) {
        this.set(v1.x, v1.y, v2.x, v2.y);
    }
}

