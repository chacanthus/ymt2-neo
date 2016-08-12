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
import com.android.dev2.Res;
import com.android.dev2.Ymt2;
import com.android.dev2.lib.Img;
import com.android.dev2.lib.Util;
import com.android.dev2.libgdx.Dialog;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import java.util.ArrayList;

public class LoginScreen implements Screen {
    private boolean bLogincreenReady;
    static boolean bWait;
    private SpriteBatch batch;
    private TextButton btnTest1;
    private TextButton btnTest2;
    private TextButton btnTest3;
    private TextButton btnTest4;
    private OrthographicCamera camera;
    private float deltaTime;
    private static float fWaitRotation;
    static float fWaitTime;
    public static Ymt2 game;
    private float height;
    private String id;
    private Img imgLoginBack1;
    private Img imgLoginBack2;
    private Img imgRegistBack1;
    private Img imgRegistBack2;
    private Img imgWaitBack1;
    private Img imgWaitBack2;
    private Method mCallbackFromAndroid;
    private String pwd;
    static Skin skin;
    private Sprite spWait1;
    private Sprite spWait2;
    static Stage stage;
    private Table tblTest;
    private static Thread thdLoginWrite;
    private float width;

    static  {
        LoginScreen.fWaitRotation = 0f;
        LoginScreen.fWaitTime = 0f;
        LoginScreen.bWait = false;
        LoginScreen.lsLoginWrite = new ArrayList();
        LoginScreen.lsLoginRead = new ArrayList();
    }

    public LoginScreen(Ymt2 _game) {
        super();
        this.width = ((float)Gdx.graphics.getWidth());
        this.height = ((float)Gdx.graphics.getHeight());
        this.deltaTime = 0f;
        this.id = "";
        this.pwd = "";
        this.bLogincreenReady = false;
        LoginScreen.game = _game;
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(1f, this.height / this.width);
        LoginScreen.stage = new Stage(this.width, this.height, false);
        Gdx.input.setInputProcessor(LoginScreen.stage);
        Gdx.input.setCatchBackKey(true);
        Util.ini(this.width, this.height);
        Global.ini();
        Res.ini();
        this.iniSkin();
        this.iniUI();
        this.iniCallback();
        this.iniTest();
        LoginScreen.sendMsgToAndroid("hide_loading");
        this.bLogincreenReady = true;
        this.connectToBalanceSrv();
    }

    static void access$0(LoginScreen arg0) {
        arg0.connectToBalanceSrv();
    }

    static void access$1(LoginScreen arg0, String[] arg1) {
        arg0.fn_update_info(arg1);
    }

    static void access$2(LoginScreen arg0, String[] arg1) {
        arg0.fn_try_login(arg1);
    }

    private void askExitGame() {  // has try-catch handlers
        try {
            if(!Global.bAskingGameExit) {
                goto label_3;
            }

            return;
        label_3:
            Global.bAskingGameExit = true;
            new Dialog() {
                protected void result(Object object) {
                    if(((Boolean)object).booleanValue()) {
                        Global.mode = 5;
                        Global.fTerminating = 0f;
                        Global.bTerminating = true;
                    }
                    else {
                        Global.bAskingGameExit = false;
                    }
                }
            }.text("게임을 종료하시겠습니까?").button("   예   ", Boolean.valueOf(true)).button("아니요", Boolean.valueOf(false)).key(66, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(LoginScreen.stage);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->askExitGame", Util.getExceptionMessage(v0));
        }
    }

    private static void clearLoginSocket() {  // has try-catch handlers
        try {
            if(LoginScreen.thdLoginWrite == null) {
                goto label_6;
            }

            LoginScreen.thdLoginWrite.interrupt();
            LoginScreen.thdLoginWrite = null;
            goto label_6;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->clearLoginSocket(1)", Util.getExceptionMessage(v0));
            goto label_6;
        }

        try {
        label_6:
            LoginScreen.lsLoginWrite.clear();
            goto label_8;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->clearLoginSocket(2)", Util.getExceptionMessage(v0));
            goto label_8;
        }

        try {
        label_8:
            LoginScreen.lsLoginRead.clear();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->clearLoginSocket(3)", Util.getExceptionMessage(v0));
        }
    }

    private void connectToBalanceSrv() {  // has try-catch handlers
        try {
            Global.mode = 0;
            LoginScreen.sendMsgToAndroid("hide_ui");
            LoginScreen.showWait(true);
            MySocket.host = Global.balance_host;
            MySocket.port = Global.balance_port;
            if(MySocket.connect()) {
                goto label_25;
            }

            LoginScreen.exitApp("서버에 연결할 수 없습니다(BC).\n" + Global.balance_host + "\n프로그램을 종료합니다.");
            Util.sysout("ERROR", "LoginScreen->connectToBalanceSrv(1)", "Unable to connect balance server.");
            return;
        label_25:
            LoginScreen.lsLoginWrite.add("L000\tz1.0");
            if(LoginScreen.thdLoginWrite != null) {
                goto label_50;
            }

            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
            return;
        label_50:
            LoginScreen.thdLoginWrite.interrupt();
            LoginScreen.thdLoginWrite = null;
            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
        }
        catch(Exception v0) {
            LoginScreen.exitApp("서버에 연결할 수 없습니다(BC2).\n" + Global.balance_host + "\n프로그램을 종료합니다.");
            Util.sysout("ERROR", "LoginScreen->connectToBalanceSrv(2)", Util.getExceptionMessage(v0));
        }
    }

    public void dispose() {
        this.disposeLoginScreen();
    }

    private void disposeLoginScreen() {  // has try-catch handlers
        try {
            MySocket.clear();
            goto label_1;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(1)", Util.getExceptionMessage(v0));
            goto label_1;
        }

        try {
        label_1:
            LoginScreen.clearLoginSocket();
            goto label_2;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(2)", Util.getExceptionMessage(v0));
            goto label_2;
        }

        try {
        label_2:
            this.batch.dispose();
            goto label_4;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(3)", Util.getExceptionMessage(v0));
            goto label_4;
        }

        try {
        label_4:
            LoginScreen.skin.dispose();
            goto label_6;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(4)", Util.getExceptionMessage(v0));
            goto label_6;
        }

        try {
        label_6:
            LoginScreen.stage.dispose();
            goto label_8;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(5)", Util.getExceptionMessage(v0));
            goto label_8;
        }

        try {
        label_8:
            Res.dispose();
            goto label_9;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(6)", Util.getExceptionMessage(v0));
            goto label_9;
        }

        try {
        label_9:
            Global.ini();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(7)", Util.getExceptionMessage(v0));
            goto label_11;
        }

        try {
        label_11:
            Dialog.count = 0;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeLoginScreen(8)", Util.getExceptionMessage(v0));
        }
    }

    private void drawWait() {
        if(LoginScreen.bWait) {
            this.imgWaitBack1.draw(this.batch);
            LoginScreen.fWaitRotation -= 0.5f;
            this.spWait1.setRotation(LoginScreen.fWaitRotation / 3f * -1f);
            this.spWait1.draw(this.batch);
            this.imgWaitBack2.draw(this.batch);
            this.spWait2.setRotation(LoginScreen.fWaitRotation);
            this.spWait2.draw(this.batch);
            LoginScreen.fWaitTime += this.deltaTime;
            if(LoginScreen.fWaitTime > 10f) {
                LoginScreen.bWait = false;
                LoginScreen.fWaitTime = 0f;
                if(Global.mode == 0) {
                    LoginScreen.exitApp("서버에 연결할 수 없습니다(W).\n프로그램을 종료합니다.");
                }
                else {
                    if(Global.mode != 1 && Global.mode != 2) {
                        LoginScreen.exitApp("서버에 연결할 수 없습니다(W3).\n프로그램을 종료합니다.");
                        return;
                    }

                    LoginScreen.showMessage("서버에 연결할 수 없습니다(W2).");
                }
            }
        }
    }

    static void exitApp(String sMsg) {  // has try-catch handlers
        try {
            Global.bAskingGameExit = true;
            Global.mode = 5;
            LoginScreen.showWait(false);
            LoginScreen.sendMsgToAndroid("hide_ui");
            MySocket.clear();
            LoginScreen.clearLoginSocket();
            if("".equals(sMsg)) {
                goto label_33;
            }

            new Dialog() {
                protected void result(Object object) {
                    Global.fTerminating = 0f;
                    Global.bTerminating = true;
                }
            }.text(sMsg).button("   확인   ", Boolean.valueOf(true)).key(66, Boolean.valueOf(true)).key(131, Boolean.valueOf(true)).show(LoginScreen.stage);
            return;
        label_33:
            Global.fTerminating = 0f;
            Global.bTerminating = true;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->exitApp", Util.getExceptionMessage(v0));
        }
    }

    private void fn_agent(String[] aMsg) {
        LoginScreen.sendMsgToAndroid("agent\t" + Util.toNoCmdString(aMsg));
    }

    private void fn_login_failure(String[] aMsg) {  // has try-catch handlers
        try {
            LoginScreen.showWait(false);
            this.id = "";
            this.pwd = "";
            this.saveId("", "");
            LoginScreen.showMessage(aMsg[1]);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->fn_login_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            LoginScreen.exitApp("로그인 중 예외가 발생했습니다(CL).\n프로그램을 종료합니다.");
        }
    }

    private void fn_login_success(String[] aMsg) {  // has try-catch handlers
        try {
            LoginScreen.showWait(false);
            LoginScreen.sendMsgToAndroid("hide_ui");
            LoginScreen.clearLoginSocket();
            String v1 = aMsg[1];
            if((this.id.equals(v1)) && this.id != null && !"".equals(this.id)) {
                goto label_24;
            }

            Util.sysout("ERROR", "LoginScreen->fn_login_success(1)", "Invalid ID.", Util.toString(aMsg));
            LoginScreen.exitApp("로그인 중 예외가 발생했습니다(CL1).\n프로그램을 종료합니다.");
            return;
        label_24:
            Global.id = v1;
            this.saveId(this.id, this.pwd);
            this.id = "";
            this.pwd = "";
            LoginScreen.game.setScreen(new GameScreen(LoginScreen.game));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->fn_login_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            LoginScreen.exitApp("로그인 중 예외가 발생했습니다(CL).\n프로그램을 종료합니다.");
        }
    }

    private void fn_notice(String[] aMsg) {  // has try-catch handlers
        int v1 = 1;
        try {
            while(v1 < aMsg.length) {
                LoginScreen.showMessage(aMsg[v1]);
                ++v1;
            }

            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->fn_notice", Util.getExceptionMessage(v0), Util.toString(aMsg));
            return;
        }
    }

    private void fn_regist_failure(String[] aMsg) {  // has try-catch handlers
        String v1;
        try {
            LoginScreen.showWait(false);
            goto label_4;
        }
        catch(Exception v0) {
            goto label_16;
        }

        try {
        label_4:
            v1 = aMsg[1];
            goto label_5;
        }
        catch(Exception v2) {
            goto label_5;
        }

        try {
        label_5:
            if("".equals(v1)) {
                v1 = "등록하지 못하였습니다.";
            }

            LoginScreen.showMessage(v1);
        }
        catch(Exception v0) {
        label_16:
            Util.sysout("ERROR", "LoginScreen->fn_regist_failure", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_regist_success(String[] aMsg) {  // has try-catch handlers
        String v2;
        String v1;
        try {
            LoginScreen.showWait(false);
            Global.mode = 1;
            goto label_7;
        }
        catch(Exception v0) {
            goto label_31;
        }

        try {
        label_7:
            v1 = aMsg[1];
        }
        catch(Exception v3) {
        }

        int v3_1 = 2;
        try {
            v2 = aMsg[v3_1];
            goto label_10;
        }
        catch(Exception v3) {
            goto label_10;
        }

        try {
        label_10:
            if(!"".equals(v1)) {
                LoginScreen.sendMsgToAndroid("regist_success\t" + v1 + "\t" + v2 + "\tZ");
            }

            LoginScreen.showMessage("등록되었습니다.");
        }
        catch(Exception v0) {
        label_31:
            Util.sysout("ERROR", "LoginScreen->fn_regist_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_srv_list(String[] aMsg) {  // has try-catch handlers
        try {
            LoginScreen.showWait(false);
            Global.mode = 1;
            LoginScreen.sendMsgToAndroid("show_ui");
            String[] v1 = aMsg[1].split("/");
            Global.game_host = v1[0];
            Global.game_port = Integer.parseInt(v1[1]);
            if(!"".equals(Global.game_host) && Global.game_port != 0) {
                goto label_31;
            }

            Util.sysout("ERROR", "LoginScreen->fn_srv_list(1)", "No game server.", Util.toString(aMsg));
            LoginScreen.exitApp("사용 가능한 게임서버가 없습니다.\n프로그램을 종료합니다.");
            return;
        label_31:
            this.sendSavedIdPwd();
            MySocket.close();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->fn_srv_list(2)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            LoginScreen.exitApp("사용 가능한 게임서버가 없습니다.\n프로그램을 종료합니다.");
        }
    }

    private void fn_try_login(String[] aMsg) {  // has try-catch handlers
        String v3;
        String v2;
        try {
            this.id = "";
            this.pwd = "";
            goto label_8;
        }
        catch(Exception v0) {
            goto label_25;
        }

        try {
        label_8:
            v2 = aMsg[1];
        }
        catch(Exception v4) {
        }

        int v4_1 = 2;
        try {
            v3 = aMsg[v4_1];
            goto label_11;
        }
        catch(Exception v4) {
            goto label_11;
        }

        try {
        label_11:
            if(!"".equals(v2)) {
                goto label_17;
            }

            LoginScreen.showMessage("아이디를 입력하세요.");
            return;
        label_17:
            if(!Util.isInclude(v2, new char[]{'\t', ' '})) {
                goto label_34;
            }

            LoginScreen.showMessage("아이디에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_34:
            if(!"".equals(v3)) {
                goto label_40;
            }

            LoginScreen.showMessage("비밀번호를 입력하세요.");
            return;
        label_40:
            if(!Util.isInclude(v3, new char[]{'\t', ' '})) {
                goto label_47;
            }

            LoginScreen.showMessage("비밀번호에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_47:
            LoginScreen.showWait(true);
            LoginScreen.sendMsgToAndroid("hide_ui");
            MySocket.host = Global.game_host;
            MySocket.port = Global.game_port;
            MySocket.close();
            if(MySocket.connect()) {
                goto label_76;
            }

            LoginScreen.showWait(false);
            LoginScreen.showMessage("서버에 연결할 수 없습니다(G).");
            Util.sysout("ERROR", "LoginScreen->fn_try_login(1)", "Unable to connect game server.", "host=" + Global.game_host + "/port=" + Global.game_port);
            return;
        label_76:
            this.id = v2;
            this.pwd = v3;
            LoginScreen.lsLoginWrite.add("L010\t" + v2 + "\t" + v3 + "\t" + "z1.0");
            if(LoginScreen.thdLoginWrite != null) {
                goto label_99;
            }

            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
            return;
        label_99:
            LoginScreen.thdLoginWrite.interrupt();
            LoginScreen.thdLoginWrite = null;
            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
        }
        catch(Exception v0) {
        label_25:
            LoginScreen.showWait(false);
            LoginScreen.showMessage("서버에 연결할 수 없습니다(G2).");
            Util.sysout("ERROR", "LoginScreen->fn_try_login(2)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_try_regist(String[] aMsg) {  // has try-catch handlers
        String v2;
        String v5;
        String v4;
        String v3;
        try {
            v3 = aMsg[1];
        }
        catch(Exception v6) {
        }

        int v6_1 = 2;
        try {
            v4 = aMsg[v6_1];
        }
        catch(Exception v6) {
        }

        v6_1 = 3;
        try {
            v5 = aMsg[v6_1];
        }
        catch(Exception v6) {
        }

        v6_1 = 4;
        try {
            v2 = aMsg[v6_1];
            goto label_13;
        }
        catch(Exception v6) {
            goto label_13;
        }

        try {
        label_13:
            if(!"".equals(v3)) {
                goto label_19;
            }

            LoginScreen.showMessage("아이디를 입력하세요.");
            return;
        label_19:
            if(!Util.isInclude(v3, new char[]{'\t', ' '})) {
                goto label_36;
            }

            LoginScreen.showMessage("아이디에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_36:
            if(!"".equals(v4)) {
                goto label_42;
            }

            LoginScreen.showMessage("비밀번호를 입력하세요.");
            return;
        label_42:
            if(!Util.isInclude(v4, new char[]{'\t', ' '})) {
                goto label_49;
            }

            LoginScreen.showMessage("비밀번호에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_49:
            if(!"".equals(v5)) {
                goto label_55;
            }

            LoginScreen.showMessage("전화번호를 입력하세요.");
            return;
        label_55:
            if(!Util.isInclude(v5, new char[]{'\t', ' '})) {
                goto label_62;
            }

            LoginScreen.showMessage("전화번호에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_62:
            if(v5.length() >= 7) {
                goto label_68;
            }

            LoginScreen.showMessage("전화번호를 확인하세요.");
            return;
        label_68:
            if(!"".equals(v2)) {
                goto label_74;
            }

            LoginScreen.showMessage("추천인을 입력하세요.");
            return;
        label_74:
            if(!Util.isInclude(v2, new char[]{'\t', ' '})) {
                goto label_81;
            }

            LoginScreen.showMessage("추천인에 사용할 수 없는 문자가 포함되어 있습니다.");
            return;
        label_81:
            LoginScreen.showWait(true);
            LoginScreen.sendMsgToAndroid("hide_ui");
            MySocket.host = Global.balance_host;
            MySocket.port = Global.balance_port;
            if(MySocket.connect()) {
                goto label_109;
            }

            LoginScreen.showWait(false);
            LoginScreen.showMessage("서버에 연결할 수 없습니다(R).");
            Util.sysout("ERROR", "LoginScreen->fn_try_regist(1)", "Unable to connect blance server.", String.valueOf(Global.balance_host) + ":" + String.valueOf(Global.balance_port));
            return;
        label_109:
            LoginScreen.lsLoginWrite.add("L020\t" + v3 + "\t" + v4 + "\t" + v5 + "\t" + v2 + "\t" + "z1.0" + "\tZ");
            if(LoginScreen.thdLoginWrite != null) {
                goto label_138;
            }

            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
            return;
        label_138:
            LoginScreen.thdLoginWrite.interrupt();
            LoginScreen.thdLoginWrite = null;
            LoginScreen.thdLoginWrite = new Thread(new ThdLoginWrite());
            LoginScreen.thdLoginWrite.start();
        }
        catch(Exception v0) {
            LoginScreen.showWait(false);
            LoginScreen.showMessage("서버에 연결할 수 없습니다(R2).");
            Util.sysout("ERROR", "LoginScreen->fn_try_regist(2)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_update_info(String[] aMsg) {  // has try-catch handlers
        try {
            LoginScreen.showWait(false);
            LoginScreen.sendMsgToAndroid("hide_ui");
            Global.mode = 4;
            LoginScreen.sendMsgToAndroid("update_url\t" + Util.toNoCmdString(aMsg));
            new Dialog() {
                protected void result(Object object) {
                    Global.mode = 5;
                    Global.fTerminating = 0f;
                    Global.bTerminating = true;
                }
            }.text("사용할 수 없는 버전입니다.\n최신 버전으로 업데이트하십시요.").button("   확인   ", Boolean.valueOf(true)).key(66, Boolean.valueOf(true)).key(131, Boolean.valueOf(true)).show(LoginScreen.stage);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->fn_update_info", Util.toString(aMsg));
        }
    }

    public void getMsgFromAndroid(String msg) {  // has try-catch handlers
        try {
            String[] v0 = msg.split("\t");
            String v1 = v0[0];
            if("try_login".equals(v1)) {
                this.fn_try_login(v0);
            }
            else if("try_regist".equals(v1)) {
                this.fn_try_regist(v0);
            }

            Util.sysout("DEBUG", "LoginScreen->getMsgFromAndroid", msg);
        }
        catch(Exception v2) {
            LoginScreen.showMessage("안드로이드 명령어 처리중 오류가 발생했습니다.\n" + msg);
            Util.sysout("ERROR", "LoginScreen->getMsgFromAndroid", Util.getExceptionMessage(v2), "msg=" + msg);
        }
    }

    public void hide() {
    }

    private void iniCallback() {  // has try-catch handlers
        try {
            this.mCallbackFromAndroid = LoginScreen.class.getMethod("getMsgFromAndroid", String.class);
            LoginScreen.game.setLoginScreenCallback(this, this.mCallbackFromAndroid);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->iniCallback", Util.getExceptionMessage(v0));
        }
    }

    private void iniSkin() {  // has try-catch handlers
        try {
            LoginScreen.skin = new Skin(Gdx.files.internal("data/skin800.json"));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->iniSkin", Util.getExceptionMessage(v0));
        }
    }

    private void iniTest() {
        this.btnTest1 = new TextButton("   연결    ", LoginScreen.skin);
        this.btnTest2 = new TextButton(" 업데이트  ", LoginScreen.skin);
        this.btnTest3 = new TextButton("   등록    ", LoginScreen.skin);
        this.btnTest4 = new TextButton("  로그인   ", LoginScreen.skin);
        this.tblTest = new Table();
        this.tblTest.setFillParent(true);
        this.tblTest.left().top().padTop(0f);
        this.tblTest.row().padTop(15f);
        this.tblTest.add(this.btnTest1).padLeft(10f);
        this.tblTest.add(this.btnTest2).padLeft(10f);
        this.tblTest.add(this.btnTest3).padLeft(10f);
        this.tblTest.add(this.btnTest4).padLeft(10f);
        LoginScreen.stage.addActor(this.tblTest);
        this.btnTest1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                LoginScreen.this.connectToBalanceSrv();
                return 0;
            }
        });
        this.btnTest2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                LoginScreen v0 = LoginScreen.this;
                String[] v1 = new String[2];
                v1[0] = "";
                v1[1] = "http://127.0.0.1:8080";
                v0.fn_update_info(v1);
                return 0;
            }
        });
        this.btnTest3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0;
                if(LoginScreen.bWait) {
                    v0 = false;
                }
                else {
                    v0 = true;
                }

                LoginScreen.bWait = v0;
                return 0;
            }
        });
        this.btnTest4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String[] v0 = new String[6];
                v0[0] = "try_login";
                v0[1] = "sp25";
                v0[2] = "1";
                LoginScreen.this.fn_try_login(v0);
                return 0;
            }
        });
        this.btnTest1.setVisible(false);
        this.btnTest2.setVisible(false);
        this.btnTest3.setVisible(false);
        this.btnTest4.setVisible(false);
    }

    private void iniUI() {
        TextureRegion[] v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("login_back_left");
        this.imgLoginBack1 = new Img(v1);
        this.imgLoginBack1.setSize(Util.getWidth(400f), Util.getHeight(480f));
        this.imgLoginBack1.setPosition(Util.getLeft(0f), Util.getTop(0f, this.imgLoginBack1.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("login_back_right");
        this.imgLoginBack2 = new Img(v1);
        this.imgLoginBack2.setSize(Util.getWidth(400f), Util.getHeight(480f));
        this.imgLoginBack2.setPosition(Util.getLeft(399f), Util.getTop(0f, this.imgLoginBack2.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("regist_back_left");
        this.imgRegistBack1 = new Img(v1);
        this.imgRegistBack1.setSize(Util.getWidth(400f), Util.getHeight(480f));
        this.imgRegistBack1.setPosition(Util.getLeft(0f), Util.getTop(0f, this.imgRegistBack1.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("regist_back_right");
        this.imgRegistBack2 = new Img(v1);
        this.imgRegistBack2.setSize(Util.getWidth(400f), Util.getHeight(480f));
        this.imgRegistBack2.setPosition(Util.getLeft(399f), Util.getTop(0f, this.imgRegistBack2.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("wait_back2");
        this.imgWaitBack1 = new Img(v1);
        this.imgWaitBack1.setSize(Util.getWidth(800f), Util.getHeight(480f));
        this.imgWaitBack1.setPosition(Util.getLeft(0f), Util.getTop(0f, this.imgWaitBack1.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("wait_back");
        this.imgWaitBack2 = new Img(v1);
        this.imgWaitBack2.setSize(Util.getWidth(149f), Util.getHeight(153f));
        this.imgWaitBack2.setPosition(Util.getLeft(324f), Util.getTop(152f, this.imgWaitBack2.height));
        this.spWait1 = Res.leaner.createSprite("wait1");
        this.spWait1.setSize(Util.getWidth(216f), Util.getHeight(216f));
        this.spWait1.setPosition(Util.XCenter(400f, this.spWait1.getWidth()), Util.YCenter(228f, this.spWait1.getHeight()));
        this.spWait1.setOrigin(this.spWait1.getWidth() / 2f, this.spWait1.getHeight() / 2f);
        this.spWait2 = Res.leaner.createSprite("wait2");
        this.spWait2.setSize(Util.getWidth(107f), Util.getHeight(107f));
        this.spWait2.setPosition(Util.XCenter(400f, this.spWait2.getWidth()), Util.YCenter(228f, this.spWait2.getHeight()));
        this.spWait2.setOrigin(this.spWait2.getWidth() / 2f, this.spWait2.getHeight() / 2f);
    }

    public void pause() {
    }

    private void processEvent() {
        if((this.bLogincreenReady) && !LoginScreen.bWait && !Global.bTerminating && Dialog.count <= 0 && (Gdx.input.isKeyPressed(4)) && !Global.bAskingGameExit && !Global.bTerminating) {
            this.askExitGame();
        }
    }

    private void processMsg() {  // has try-catch handlers
        if(this.bLogincreenReady) {
            goto label_3;
        }

        return;
    label_3:
        if(LoginScreen.lsLoginRead.size() <= 0) {
            return;
        }

        try {
            Object v3 = LoginScreen.lsLoginRead.get(0);
            LoginScreen.lsLoginRead.remove(0);
            String[] v0 = ((String)v3).split("\t");
            String v1 = v0[0];
            if(!"L030".equals(v1)) {
                goto label_29;
            }

            this.fn_srv_list(v0);
            return;
        label_29:
            if(!"L010".equals(v1)) {
                goto label_34;
            }

            this.fn_notice(v0);
            return;
        label_34:
            if(!"L020".equals(v1)) {
                goto label_39;
            }

            this.fn_update_info(v0);
            return;
        label_39:
            if(!"L040".equals(v1)) {
                goto label_44;
            }

            this.fn_agent(v0);
            return;
        label_44:
            if(!"L050".equals(v1)) {
                goto label_49;
            }

            this.fn_login_success(v0);
            return;
        label_49:
            if(!"L060".equals(v1)) {
                goto label_54;
            }

            this.fn_login_failure(v0);
            return;
        label_54:
            if(!"L070".equals(v1)) {
                goto label_59;
            }

            this.fn_regist_success(v0);
            return;
        label_59:
            if(!"L080".equals(v1)) {
                goto label_64;
            }

            this.fn_regist_failure(v0);
            return;
        label_64:
            if(!"show_ui".equals(v1)) {
                goto label_70;
            }

            LoginScreen.sendMsgToAndroid("show_ui");
            return;
        label_70:
            if(!"hide_ui".equals(v1)) {
                return;
            }

            LoginScreen.sendMsgToAndroid("hide_ui");
        }
        catch(Exception v2) {
            LoginScreen.showMessage("로그인 명령어 처리중 오류가 발생했습니다.");
            Util.sysout("ERROR", "LoginScreen->processMsg", Util.getExceptionMessage(v2));
        }
    }

    public void render(float _deltaTime) {
        float v1 = 0.055556f;
        this.deltaTime = _deltaTime;
        if(_deltaTime > v1) {
            this.deltaTime = v1;
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(16384);
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        if(Global.mode == 2) {
            this.imgRegistBack1.draw(this.batch);
            this.imgRegistBack2.draw(this.batch);
        }
        else {
            this.imgLoginBack1.draw(this.batch);
            this.imgLoginBack2.draw(this.batch);
        }

        this.drawWait();
        this.batch.end();
        LoginScreen.stage.act(this.deltaTime);
        LoginScreen.stage.draw();
        this.processEvent();
        this.processMsg();
        if(Global.bTerminating) {
            Global.fTerminating += this.deltaTime;
            if(Global.fTerminating > 2f) {
                Global.fTerminating = -100000000f;
                this.disposeLoginScreen();
                Gdx.app.exit();
            }
        }
    }

    public void resize(int width, int height) {
        LoginScreen.stage.setViewport(((float)width), ((float)height), false);
    }

    public void resume() {
    }

    private void saveId(String _id, String _pwd) {  // has try-catch handlers
        try {
            Gdx.files.local("id.dat").writeString(String.valueOf(_id) + "\t" + _pwd, false);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->saveId", Util.getExceptionMessage(v0));
        }
    }

    public static void sendMsgToAndroid(String sMsg) {  // has try-catch handlers
        try {
            LoginScreen.game.sendMsgToAndroid(sMsg);
        }
        catch(Exception v0) {
            Util.sysout("DEBUG", "LoginScreen->sendMsgToAndroid", Util.getExceptionMessage(v0), sMsg);
        }
    }

    private void sendSavedIdPwd() {  // has try-catch handlers
        String v5;
        String v3;
        String[] v0;
        try {
            if(!Gdx.files.local("id.dat").exists()) {
                return;
            }

            String v4 = Gdx.files.local("id.dat").readString();
            if(v4 == null) {
                return;
            }

            v0 = v4.split("\t");
            goto label_15;
        }
        catch(Exception v1) {
            goto label_31;
        }

        try {
        label_15:
            v3 = v0[0];
        }
        catch(Exception v6) {
            goto label_17;
        }

        try {
        label_17:
            v5 = v0[1];
            goto label_18;
        }
        catch(Exception v6) {
            goto label_18;
        }

        try {
        label_18:
            LoginScreen.sendMsgToAndroid("saved_id\t" + v3 + "\t" + v5);
        }
        catch(Exception v1) {
        label_31:
            Util.sysout("ERROR", "LoginScreen->sendSavedIdPwd", Util.getExceptionMessage(v1));
        }
    }

    public void show() {
    }

    static void showMessage(String sMsg) {  // has try-catch handlers
        try {
            LoginScreen.sendMsgToAndroid("hide_ui");
            new Dialog() {
            }.text(sMsg.replaceAll("<br/>", System.getProperty("line.separator")).replaceAll("<BR/>", System.getProperty("line.separator"))).button("   확인   ", Boolean.valueOf(true)).key(66, Boolean.valueOf(true)).key(131, Boolean.valueOf(true)).show(LoginScreen.stage);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->showMessage", Util.getExceptionMessage(v0));
        }
    }

    private static void showWait(boolean bShow) {  // has try-catch handlers
        if(!bShow) {
            goto label_8;
        }

        try {
            LoginScreen.fWaitTime = 0f;
            LoginScreen.fWaitRotation = 0f;
            LoginScreen.bWait = true;
            return;
        label_8:
            LoginScreen.bWait = false;
            LoginScreen.fWaitRotation = 0f;
            LoginScreen.fWaitTime = 0f;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->showWait", Util.getExceptionMessage(v0), String.valueOf(bShow));
        }
    }
}

