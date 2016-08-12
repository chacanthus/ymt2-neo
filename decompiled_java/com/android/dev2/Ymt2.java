// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.android.dev2;

import com.android.dev2.screen.LoginScreen;
import com.badlogic.gdx.Game;

public class Ymt2 extends Game {
    private VideoPlayer videoPlayer;

    public Ymt2(VideoPlayer _videoPlayer) {
        super();
        this.videoPlayer = _videoPlayer;
    }

    public void create() {
        this.setScreen(new LoginScreen(this));
    }

    public void dispose() {
        super.dispose();
    }

    public void pause() {
    }

    public boolean playVideo(Object _obj, Method _method, String _fileName, String _param) {
        return this.videoPlayer.playVideo(_obj, _method, _fileName, _param);
    }

    public void render() {
        super.render();
    }

    public void resize(int width, int height) {
    }

    public void resume() {
    }

    public void sendMsgToAndroid(String _msg) {
        this.videoPlayer.sendMsgToAndroid(_msg);
    }

    public void setLoginScreenCallback(Object _obj, Method _method) {
        this.videoPlayer.setLoginScreenCallback(_obj, _method);
    }
}

