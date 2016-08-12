// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.math;

import java.io.Serializable;

public class Ellipse implements Serializable {
    public float height;
    private static final long serialVersionUID = 7381533206532032099L;
    public float width;
    public float x;
    public float y;

    public Ellipse() {
        super();
    }

    public Ellipse(float x, float y, float width, float height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Ellipse(Ellipse ellipse) {
        super();
        this.x = ellipse.x;
        this.y = ellipse.y;
        this.width = ellipse.width;
        this.height = ellipse.height;
    }

    public Ellipse(Vector2 position, float width, float height) {
        super();
        this.x = position.x;
        this.y = position.y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(float x, float y) {
        boolean v0;
        x -= this.x;
        y -= this.y;
        if(x * x / (this.width * 0.5f * this.width * 0.5f) + y * y / (this.height * 0.5f * this.height * 0.5f) <= 1f) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean contains(Vector2 point) {
        return this.contains(point.x, point.y);
    }

    public void set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void set(Ellipse ellipse) {
        this.x = ellipse.x;
        this.y = ellipse.y;
        this.width = ellipse.width;
        this.height = ellipse.height;
    }

    public Ellipse setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Ellipse setPosition(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
        return this;
    }

    public Ellipse setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }
}

