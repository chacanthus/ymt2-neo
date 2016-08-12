// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.view.MotionEvent;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

public class AndroidSingleTouchHandler implements AndroidTouchHandler {
    public AndroidSingleTouchHandler() {
        super();
    }

    public void onTouch(MotionEvent event, AndroidInput input) {
        int v3 = ((int)event.getX());
        int v4 = ((int)event.getY());
        int v8 = input.touchX[0];
        int v9 = input.touchY[0];
        input.touchX[0] = v3;
        input.touchY[0] = v4;
        long v6 = event.getEventTime() * 1000000;
        if(event.getAction() == 0) {
            this.postTouchEvent(input, 0, v3, v4, 0, v6);
            input.touched[0] = true;
            input.deltaX[0] = 0;
            input.deltaY[0] = 0;
        }
        else if(event.getAction() == 2) {
            this.postTouchEvent(input, 2, v3, v4, 0, v6);
            input.touched[0] = true;
            input.deltaX[0] = v3 - v8;
            input.deltaY[0] = v4 - v9;
        }
        else if(event.getAction() == 1) {
            this.postTouchEvent(input, 1, v3, v4, 0, v6);
            input.touched[0] = false;
            input.deltaX[0] = 0;
            input.deltaY[0] = 0;
        }
        else if(event.getAction() == 3) {
            this.postTouchEvent(input, 1, v3, v4, 0, v6);
            input.touched[0] = false;
            input.deltaX[0] = 0;
            input.deltaY[0] = 0;
        }
    }

    private void postTouchEvent(AndroidInput input, int type, int x, int y, int pointer, long timeStamp) {  // has try-catch handlers
        Application v1_1;
        try {
            Object v0 = input.usedTouchEvents.obtain();
            ((TouchEvent)v0).timeStamp = timeStamp;
            ((TouchEvent)v0).pointer = 0;
            ((TouchEvent)v0).x = x;
            ((TouchEvent)v0).y = y;
            ((TouchEvent)v0).type = type;
            input.touchEvents.add(v0);
            v1_1 = Gdx.app;
        }
        catch(Throwable v1) {
            try {
            label_15:
                throw v1;
            }
            catch(Throwable v1) {
                goto label_15;
            }
        }

        v1_1.getGraphics().requestRendering();
    }

    public boolean supportsMultitouch(Context activity) {
        return 0;
    }
}

