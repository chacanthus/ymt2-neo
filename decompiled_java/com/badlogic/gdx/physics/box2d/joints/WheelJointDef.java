﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.JointDef$JointType;

public class WheelJointDef extends JointDef {
    public WheelJointDef() {
        super();
        this.localAnchorA = new Vector2();
        this.localAnchorB = new Vector2();
        this.localAxisA = new Vector2(1f, 0f);
        this.enableMotor = false;
        this.maxMotorTorque = 0f;
        this.motorSpeed = 0f;
        this.frequencyHz = 2f;
        this.dampingRatio = 0.7f;
        this.type = JointType.WheelJoint;
    }

    public void initialize(Body bodyA, Body bodyB, Vector2 anchor, Vector2 axis) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.localAnchorA.set(bodyA.getLocalPoint(anchor));
        this.localAnchorB.set(bodyB.getLocalPoint(anchor));
        this.localAxisA.set(bodyA.getLocalVector(axis));
    }
}

