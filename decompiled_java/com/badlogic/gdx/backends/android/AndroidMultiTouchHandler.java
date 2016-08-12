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

public class AndroidMultiTouchHandler implements AndroidTouchHandler {
    public AndroidMultiTouchHandler() {
        super();
    }

    private void logAction(int action, int pointer) {
        String v0;
        if(action == 0) {
            v0 = "DOWN";
        }
        else if(action == 5) {
            v0 = "POINTER DOWN";
        }
        else if(action == 1) {
            v0 = "UP";
        }
        else if(action == 6) {
            v0 = "POINTER UP";
        }
        else if(action == 4) {
            v0 = "OUTSIDE";
        }
        else if(action == 3) {
            v0 = "CANCEL";
        }
        else if(action == 2) {
            v0 = "MOVE";
        }
        else {
            v0 = "UNKNOWN (" + action + ")";
        }

        Gdx.app.log("AndroidMultiTouchHandler", "action " + v0 + ", Android pointer id: " + pointer);
    }

    public void onTouch(MotionEvent event, AndroidInput input) {  // has try-catch handlers
        Application v0_1;
        int v5;
        int v4;
        int v3;
        int v8 = event.getAction() & 255;
        int v12 = (event.getAction() & 65280) >> 8;
        int v11 = event.getPointerId(v12);
        long v6 = System.nanoTime();
        switch(v8) {
            case 2: {
                try {
                    int v10 = event.getPointerCount();
                    int v9;
                    for(v9 = 0; true; ++v9) {
                        if(v9 >= v10) {
                            goto label_12;
                        }

                        v11 = event.getPointerId(v9);
                        v3 = ((int)event.getX(v9));
                        v4 = ((int)event.getY(v9));
                        v5 = input.lookUpPointerIndex(v11);
                        if(v5 != -1) {
                            if(v5 < 40) {
                                this.postTouchEvent(input, 2, v3, v4, v5, v6);
                                input.deltaX[v5] = v3 - input.touchX[v5];
                                input.deltaY[v5] = v4 - input.touchY[v5];
                                input.touchX[v5] = v3;
                                input.touchY[v5] = v4;
                            }
                            else {
                                goto label_12;
                            }
                        }
                    }

                label_45:
                    v5 = input.lookUpPointerIndex(v11);
                    if(v5 == -1) {
                        goto label_12;
                    }

                    if(v5 >= 40) {
                        goto label_12;
                    }

                    input.realId[v5] = -1;
                    v3 = ((int)event.getX(v12));
                    v4 = ((int)event.getY(v12));
                    this.postTouchEvent(input, 1, v3, v4, v5, v6);
                    input.touchX[v5] = v3;
                    input.touchY[v5] = v4;
                    input.deltaX[v5] = 0;
                    input.deltaY[v5] = 0;
                    input.touched[v5] = false;
                    goto label_12;
                label_16:
                    v5 = input.getFreePointerIndex();
                    if(v5 >= 40) {
                        goto label_12;
                    }

                    input.realId[v5] = v11;
                    v3 = ((int)event.getX(v12));
                    v4 = ((int)event.getY(v12));
                    this.postTouchEvent(input, 0, v3, v4, v5, v6);
                    input.touchX[v5] = v3;
                    input.touchY[v5] = v4;
                    input.deltaX[v5] = 0;
                    input.deltaY[v5] = 0;
                    input.touched[v5] = true;
                label_12:
                    v0_1 = Gdx.app;
                    goto label_13;
                label_44:
                    throw v0;
                }
                catch(Throwable v0) {
                    goto label_44;
                }
            }
            case 0: 
            case 5: {
                goto label_16;
            }
            case 1: 
            case 3: 
            case 4: 
            case 6: {
                goto label_45;
            }
        }

    label_13:
        v0_1.getGraphics().requestRendering();
    }

    private void postTouchEvent(AndroidInput input, int type, int x, int y, int pointer, long timeStamp) {
        Object v0 = input.usedTouchEvents.obtain();
        ((TouchEvent)v0).timeStamp = timeStamp;
        ((TouchEvent)v0).pointer = pointer;
        ((TouchEvent)v0).x = x;
        ((TouchEvent)v0).y = y;
        ((TouchEvent)v0).type = type;
        input.touchEvents.add(v0);
    }

    public boolean supportsMultitouch(Context activity) {
        return activity.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    }
}

