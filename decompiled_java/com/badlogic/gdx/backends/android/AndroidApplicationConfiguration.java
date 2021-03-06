﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;

public class AndroidApplicationConfiguration {
    public int a;
    public int b;
    public int depth;
    public boolean disableAudio;
    public int g;
    public boolean getTouchEventsForLiveWallpaper;
    public int maxSimultaneousSounds;
    public int numSamples;
    public int r;
    public int stencil;
    public int touchSleepTime;
    public boolean useAccelerometer;
    public boolean useCompass;
    public boolean useGL20;
    public boolean useGLSurfaceViewAPI18;

    public AndroidApplicationConfiguration() {
        super();
        this.useGL20 = false;
        this.r = 5;
        this.g = 6;
        this.b = 5;
        this.a = 0;
        this.depth = 16;
        this.stencil = 0;
        this.numSamples = 0;
        this.useAccelerometer = true;
        this.useCompass = true;
        this.touchSleepTime = 0;
        this.useWakelock = false;
        this.hideStatusBar = false;
        this.disableAudio = false;
        this.maxSimultaneousSounds = 16;
        this.resolutionStrategy = new FillResolutionStrategy();
        this.getTouchEventsForLiveWallpaper = false;
        this.useGLSurfaceViewAPI18 = false;
        this.useImmersiveMode = false;
    }
}

