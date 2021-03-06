﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

public abstract class Camera {
    public final Matrix4 combined;
    public final Vector3 direction;
    public float far;
    public final Frustum frustum;
    public final Matrix4 invProjectionView;
    public float near;
    public final Vector3 position;
    public final Matrix4 projection;
    final Ray ray;
    private final Vector3 tmpVec;
    public final Vector3 up;
    public final Matrix4 view;
    public float viewportHeight;
    public float viewportWidth;

    public Camera() {
        super();
        this.position = new Vector3();
        this.direction = new Vector3(0f, 0f, -1f);
        this.up = new Vector3(0f, 1f, 0f);
        this.projection = new Matrix4();
        this.view = new Matrix4();
        this.combined = new Matrix4();
        this.invProjectionView = new Matrix4();
        this.near = 1f;
        this.far = 100f;
        this.viewportWidth = 0f;
        this.viewportHeight = 0f;
        this.frustum = new Frustum();
        this.tmpVec = new Vector3();
        this.ray = new Ray(new Vector3(), new Vector3());
    }

    public void apply(GL10 gl) {
        gl.glMatrixMode(5889);
        gl.glLoadMatrixf(this.projection.val, 0);
        gl.glMatrixMode(5888);
        gl.glLoadMatrixf(this.view.val, 0);
    }

    public Ray getPickRay(float x, float y) {
        return this.getPickRay(x, y, 0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public Ray getPickRay(float x, float y, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        this.unproject(this.ray.origin.set(x, y, 0f), viewportX, viewportY, viewportWidth, viewportHeight);
        this.unproject(this.ray.direction.set(x, y, 1f), viewportX, viewportY, viewportWidth, viewportHeight);
        this.ray.direction.sub(this.ray.origin).nor();
        return this.ray;
    }

    public void lookAt(float x, float y, float z) {
        this.direction.set(x, y, z).sub(this.position).nor();
        this.normalizeUp();
    }

    public void lookAt(Vector3 target) {
        this.direction.set(target).sub(this.position).nor();
        this.normalizeUp();
    }

    public void normalizeUp() {
        this.tmpVec.set(this.direction).crs(this.up).nor();
        this.up.set(this.tmpVec).crs(this.direction).nor();
    }

    public void project(Vector3 vec) {
        this.project(vec, 0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public void project(Vector3 vec, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        vec.prj(this.combined);
        vec.x = (vec.x + 1f) * viewportWidth / 2f + viewportX;
        vec.y = (vec.y + 1f) * viewportHeight / 2f + viewportY;
        vec.z = (vec.z + 1f) / 2f;
    }

    public void rotate(float angle, float axisX, float axisY, float axisZ) {
        this.direction.rotate(angle, axisX, axisY, axisZ);
        this.up.rotate(angle, axisX, axisY, axisZ);
    }

    public void rotate(Matrix4 transform) {
        this.direction.rot(transform);
        this.up.rot(transform);
    }

    public void rotate(Quaternion quat) {
        quat.transform(this.direction);
        quat.transform(this.up);
    }

    public void rotate(Vector3 axis, float angle) {
        this.direction.rotate(axis, angle);
        this.up.rotate(axis, angle);
    }

    public void rotateAround(Vector3 point, Vector3 axis, float angle) {
        this.tmpVec.set(point);
        this.tmpVec.sub(this.position);
        this.translate(this.tmpVec);
        this.rotate(axis, angle);
        this.tmpVec.rotate(axis, angle);
        this.translate(-this.tmpVec.x, -this.tmpVec.y, -this.tmpVec.z);
    }

    public void transform(Matrix4 transform) {
        this.position.mul(transform);
        this.rotate(transform);
    }

    public void translate(Vector3 vec) {
        this.position.add(vec);
    }

    public void translate(float x, float y, float z) {
        this.position.add(x, y, z);
    }

    public void unproject(Vector3 vec, float viewportX, float viewportY, float viewportWidth, float viewportHeight) {
        float v0 = vec.x;
        float v1 = (((float)Gdx.graphics.getHeight())) - vec.y - 1f - viewportY;
        vec.x = 2f * (v0 - viewportX) / viewportWidth - 1f;
        vec.y = 2f * v1 / viewportHeight - 1f;
        vec.z = vec.z * 2f - 1f;
        vec.prj(this.invProjectionView);
    }

    public void unproject(Vector3 vec) {
        this.unproject(vec, 0f, 0f, ((float)Gdx.graphics.getWidth()), ((float)Gdx.graphics.getHeight()));
    }

    public abstract void update();

    public abstract void update(boolean arg0);
}

