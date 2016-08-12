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

import com.android.dev2.lib.Crypt;
import com.android.dev2.lib.Util;

public class Global {
    public static String account = null;
    public static boolean bTerminating = false;
    public static String balance_host = null;
    public static String balance_host_pool = null;
    public static int balance_port = 0;
    public static String bank = null;
    public static String[] cardPos = null;
    public static int cardSizeX = 0;
    public static int cardSizeY = 0;
    public static String[] chance100 = null;
    public static final int connection_time_out_millisecond = 10000;
    public static int division = 0;
    public static String errorReport = null;
    public static float fTerminating = 0f;
    public static String game_host = null;
    public static int game_port = 0;
    public static int[] gift = null;
    public static int iLiveTick = 0;
    public static int[] machineNo = null;
    public static int mode = 0;
    public static int money = 0;
    public static String owner = null;
    public static int[] reserve = null;
    public static final float response_time_out_second = 0f;
    public static int smallCardSizeX = 0;
    public static int smallCardSizeY = 0;
    public static int[] spin = null;
    public static String[] top = null;
    public static final String version = "z1.0";
    public static String viewScenario;

    static  {
        Global.id = "";
        Global.cls = "";
        Global.money = 0;
        Global.errorReport = "0";
        Global.viewScenario = "0";
        Global.bank = "";
        Global.owner = "";
        Global.account = "";
        Global.channel = 0;
        Global.division = 1;
        Global.iLiveTick = 0;
        Global.machineNo = new int[2];
        Global.gift = new int[2];
        Global.spin = new int[2];
        Global.chance100 = new String[2];
        Global.reserve = new int[2];
        Global.top = new String[2];
        Global.cardPos = new String[2];
        Global.mode = 0;
        Global.bAskingGameExit = false;
        Global.bTerminating = false;
        Global.fTerminating = -100000000f;
        Global.game_host = "";
        Global.game_port = 0;
        Global.balance_host = "";
        Global.balance_port = 10000;
        Global.balance_host_pool = "H4sIAAAAAAAAAEtJyc9LT0ssqjQz00vOzwUAOZzTPA8AAAA=";
        Global.smallCardSizeX = 88;
        Global.smallCardSizeY = 94;
        Global.cardSizeX = 158;
        Global.cardSizeY = 158;
    }

    public Global() {
        super();
    }

    public static void ini() {  // has try-catch handlers
        try {
            Global.id = "";
            Global.cls = "";
            Global.money = 0;
            Global.channel = 0;
            Global.machineNo[0] = 0;
            Global.machineNo[1] = 0;
            Global.gift[0] = 0;
            Global.gift[1] = 0;
            Global.spin[0] = 0;
            Global.spin[1] = 0;
            Global.reserve[0] = 0;
            Global.reserve[1] = 0;
            Global.top[0] = "";
            Global.top[1] = "";
            Global.cardPos[0] = "";
            Global.cardPos[1] = "";
            Global.chance100[0] = "";
            Global.chance100[1] = "";
            Global.iLiveTick = 0;
            Global.bTerminating = false;
            Global.fTerminating = -100000000f;
            goto label_70;
        }
        catch(Exception v1) {
            v1.printStackTrace();
            goto label_70;
        }

        try {
        label_70:
            String[] v0 = Crypt.decrypt(Global.balance_host_pool).split("/");
            Global.balance_host = v0[Util.randomInt(0, v0.length - 1)].trim();
        }
        catch(Exception v4) {
        }
    }
}

