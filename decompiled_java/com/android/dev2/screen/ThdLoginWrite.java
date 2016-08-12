// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.android.dev2.screen;

import com.android.dev2.Global;
import com.android.dev2.MySocket;
import com.android.dev2.lib.Util;

class ThdLoginWrite implements Runnable {
    ThdLoginWrite() {
        super();
    }

    public void run() {  // has try-catch handlers
        try {
            do {
            label_2:
                if(LoginScreen.lsLoginWrite.size() > 0) {
                    goto label_6;
                }

                return;
            label_6:
                Object v2 = LoginScreen.lsLoginWrite.get(0);
                LoginScreen.lsLoginWrite.remove(0);
                if(MySocket.write(((String)v2))) {
                    goto label_2;
                }
            }
            while(Global.mode == 5);

            Util.sysout("ERROR", "ThdLoginWrite->run(1)", "Unable to write.");
            LoginScreen.exitApp("서버에 연결할 수 없습니다(A).\n프로그램을 종료합니다.");
            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "ThdLoginWrite->run(2)", Util.getExceptionMessage(v0));
            LoginScreen.exitApp("서버에 연결할 수 없습니다(A2).\n프로그램을 종료합니다.");
            return;
        }
    }
}

