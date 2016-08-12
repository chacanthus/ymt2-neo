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

public class Rectangle implements Serializable {
    public float height;
    private static final long serialVersionUID = 5733252015138115702L;
    public static final Rectangle tmp;
    public static final Rectangle tmp2;
    public float width;
    public float x;
    public float y;

    static  {
        Rectangle.tmp = new Rectangle();
        Rectangle.tmp2 = new Rectangle();
    }

    public Rectangle() {
        super();
    }

    public Rectangle(float x, float y, float width, float height) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle rect) {
        super();
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
    }

    public boolean contains(float x, float y) {
        boolean v0;
        if(this.x > x || this.x + this.width < x || this.y > y || this.y + this.height < y) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    public boolean contains(Rectangle rectangle) {
        boolean v4;
        float v1 = rectangle.x;
        float v0 = v1 + rectangle.width;
        float v3 = rectangle.y;
        float v2 = v3 + rectangle.height;
        if(v1 <= this.x || v1 >= this.x + this.width || v0 <= this.x || v0 >= this.x + this.width || v3 <= this.y || v3 >= this.y + this.height || v2 <= this.y || v2 >= this.y + this.height) {
            v4 = false;
        }
        else {
            v4 = true;
        }

        return v4;
    }

    public boolean contains(Vector2 vector) {
        return this.contains(vector.x, vector.y);
    }

    public Rectangle fitInside(Rectangle rect) {
        float v4 = 2f;
        float v0 = this.getAspectRatio();
        if(v0 < rect.getAspectRatio()) {
            this.setSize(rect.height * v0, rect.height);
        }
        else {
            this.setSize(rect.width, rect.width / v0);
        }

        this.setPosition(rect.x + rect.width / v4 - this.width / v4, rect.y + rect.height / v4 - this.height / v4);
        return this;
    }

    public Rectangle fitOutside(Rectangle rect) {
        float v4 = 2f;
        float v0 = this.getAspectRatio();
        if(v0 > rect.getAspectRatio()) {
            this.setSize(rect.height * v0, rect.height);
        }
        else {
            this.setSize(rect.width, rect.width / v0);
        }

        this.setPosition(rect.x + rect.width / v4 - this.width / v4, rect.y + rect.height / v4 - this.height / v4);
        return this;
    }

    public float getAspectRatio() {
        float v0;
        if(this.height == 0f) {
            v0 = NaNf;
        }
        else {
            v0 = this.width / this.height;
        }

        return v0;
    }

    public Vector2 getCenter(Vector2 vector) {
        vector.x = this.x + this.width / 2f;
        vector.y = this.y + this.height / 2f;
        return vector;
    }

    public float getHeight() {
        return this.height;
    }

    public Vector2 getPosition(Vector2 position) {
        return position.set(this.x, this.y);
    }

    public Vector2 getSize(Vector2 size) {
        return size.set(this.width, this.height);
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

    public Rectangle merge(Rectangle rect) {
        float v2 = Math.min(this.x, rect.x);
        float v0 = Math.max(this.x + this.width, rect.x + rect.width);
        this.x = v2;
        this.width = v0 - v2;
        float v3 = Math.min(this.y, rect.y);
        float v1 = Math.max(this.y + this.height, rect.y + rect.height);
        this.y = v3;
        this.height = v1 - v3;
        return this;
    }

    public boolean overlaps(Rectangle r) {
        boolean v0;
        if(this.x >= r.x + r.width || this.x + this.width <= r.x || this.y >= r.y + r.height || this.y + this.height <= r.y) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        return v0;
    }

    public Rectangle set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public Rectangle set(Rectangle rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
        return this;
    }

    public Rectangle setCenter(float x, float y) {
        this.setPosition(x - this.width / 2f, y - this.height / 2f);
        return this;
    }

    public Rectangle setCenter(Vector2 position) {
        this.setPosition(position.x - this.width / 2f, position.y - this.height / 2f);
        return this;
    }

    public Rectangle setHeight(float height) {
        this.height = height;
        return this;
    }

    public Rectangle setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Rectangle setPosition(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
        return this;
    }

    public Rectangle setSize(float width, float height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Rectangle setSize(float sizeXY) {
        this.width = sizeXY;
        this.height = sizeXY;
        return this;
    }

    public Rectangle setWidth(float width) {
        this.width = width;
        return this;
    }

    public Rectangle setX(float x) {
        this.x = x;
        return this;
    }

    public Rectangle setY(float y) {
        this.y = y;
        return this;
    }

    public String toString() {
        return this.x + "," + this.y + "," + this.width + "," + this.height;
    }
}

