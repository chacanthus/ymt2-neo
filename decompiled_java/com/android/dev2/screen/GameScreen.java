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
import com.android.dev2.lib.Ani;
import com.android.dev2.lib.Img;
import com.android.dev2.lib.Util;
import com.android.dev2.libgdx.Dialog;
import com.android.dev2.obj.Card;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input$TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane$ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

public class GameScreen implements Screen {
    private final int ALL;
    private final int ASK;
    private final int BOTTOM;
    private final int CENTER;
    private final int CHARGE;
    private final int CROSS;
    private final int EXCHANGE;
    private final int HELP;
    private final int HIDE;
    private final int IDLE;
    private final int LEFT;
    private final int MACHINE;
    private final int NONE;
    private final int POST_ALL;
    private final int POST_LEFT;
    private final int POST_RIGHT;
    private final int PUSH_BUTTON;
    private final int REACH_FAILURE;
    private final int REACH_SUCCESS;
    private final int RIGHT;
    private final int TOP;
    private float[][] accelerationAlpha;
    private float[][] accelerationSpeed;
    private boolean bCanInsert10000;
    private boolean[] bCanRequestScenario;
    private boolean bCanReserveMachine;
    private boolean bCanSelectMachine;
    private boolean bCanSwitchMachine;
    private boolean bCanSwitchMoney;
    private boolean bCanUpDownChannel;
    public static boolean bCannonCelemony;
    private boolean bDrawLine;
    private boolean[] bEnding;
    private boolean bGameScreenReady;
    public static boolean bHideCannon;
    public static boolean bHideVideo;
    public static boolean[] bHovering;
    private boolean bLineLoop;
    private boolean[] bPlayVideo1;
    private boolean[] bPlayVideo2;
    private boolean[][] bPostReelCenter;
    private boolean[][] bReelAccelerating;
    private boolean[][] bReelCenter;
    private boolean[][] bReelDecelerating;
    private boolean[][] bReelDeceleratingStarted;
    private boolean[][] bReelMinSpeedStarted;
    private boolean[][] bReelPoppingMinSpeedStarted;
    private boolean[][] bReelPoppingStarted;
    private boolean[][] bReelRunning;
    static boolean bReserving;
    private boolean[] bSecondAcceleration;
    public static boolean bShowCannon;
    public static boolean bShowMenu;
    private boolean bShowToast;
    public static boolean bShowVideo;
    private boolean[] bShrinkCard;
    private boolean bStopScenarioForTest;
    private boolean bSwitching;
    private boolean[] bVideo1Playing;
    private boolean[] bVideo2Playing;
    private boolean[] bVideo3Playing;
    private boolean[] bWaitingScenario;
    public static SpriteBatch batch;
    private TextButton btnTest1;
    private TextButton btnTest10;
    private TextButton btnTest11;
    private TextButton btnTest12;
    private TextButton btnTest13;
    private TextButton btnTest14;
    private TextButton btnTest15;
    private TextButton btnTest16;
    private TextButton btnTest17;
    private TextButton btnTest18;
    private TextButton btnTest19;
    private TextButton btnTest2;
    private TextButton btnTest20;
    private TextButton btnTest21;
    private TextButton btnTest22;
    private TextButton btnTest23;
    private TextButton btnTest24;
    private TextButton btnTest25;
    private TextButton btnTest26;
    private TextButton btnTest27;
    private TextButton btnTest28;
    private TextButton btnTest29;
    private TextButton btnTest3;
    private TextButton btnTest30;
    private TextButton btnTest31;
    private TextButton btnTest32;
    private TextButton btnTest4;
    private TextButton btnTest5;
    private TextButton btnTest6;
    private TextButton btnTest7;
    private TextButton btnTest8;
    private TextButton btnTest9;
    private OrthographicCamera camera;
    private Card[][][] card;
    private Color[] clAsk;
    private Color[] clHelp;
    private Color[] clMsg;
    public static Color color;
    private float[][] decelerationAlpha;
    private float[][] decelerationSpeed;
    private float deltaTime;
    private float elapseTime;
    private float[] fCannonDelayTime;
    private float[] fCannonRotation;
    public static float fCelemony;
    private float fChatStep;
    private float[] fEndScenarioDelay;
    public static float fFackVideoTick;
    private float[] fFireDelayTime;
    private float[] fGlitterDelay;
    private float fHintCannonElapseTime;
    private float fIntervalMiddle;
    private float fLineElapseTime;
    private float[] fNextScenarioDelay;
    private float[] fPoppingDelay;
    private float[] fPushCannonDelay;
    private float[][] fReelAlpha;
    private float[][] fReelDeceleratingStartTime;
    private float[][] fReelDeltaMileage;
    private float[][] fReelElapseTime;
    private float[][] fReelPoppingStartTime;
    private float[] fSecondAccelerationTime;
    private static float fSndStopDelay;
    private float[][] fStartReelDelayTime;
    private float fToastTime;
    public static float[] fTotalReelElapseTime;
    private float[] fVideo1StartTime;
    private float[] fVideo2StartTime;
    private boolean fbDrawRobot;
    private String[] fsPostCardPos;
    private static Ymt2 game;
    private float height;
    private int[] iCannonDirection;
    private int iCurAsk;
    private int iCurHelp;
    private int iCurMsg;
    private int iExMenuMode;
    private int[] iFireType;
    private int iHintCannonStep;
    private int iHintCannonTick;
    private int iHintCannonType;
    private int[] iMachineNo;
    private int[] iMachineState;
    private int[] iMachineToday;
    private int[] iMachineYesterday;
    private int iMaxAsk;
    private int iMaxHelp;
    private int iMaxMsg;
    private int[][] iMaxPopCount;
    private int iMenuMode;
    private int[][] iNextCardNo;
    private int[][] iPopCount;
    private int[][] iPostTargetCardNo;
    private int[][] iReelTensioningStep;
    public static int[] iReserveConfirmTick;
    private int[] iTCBX;
    private int[][] iTargetCardNo;
    private Image imgChannelA;
    private Image imgChannelB;
    private Image imgToast;
    private Image imgToastBack;
    private Label lbChance100;
    private Label lbChargeAccount;
    private Label lbChargeBank;
    private Label lbChargeMan;
    private Label lbChargeOwner;
    private Label lbExchangeBank;
    private Label lbExchangeOwner;
    private Label lbFps;
    private Label lbGift;
    private Label lbMachine;
    private Label[] lbMachineNo;
    private Label lbMoney;
    public static Label lbReserve;
    private Label lbSpin;
    private Label lbToast;
    public static ArrayList lsGameRead;
    public static ArrayList lsGameWrite;
    private ArrayList[] lsScenario;
    private Method mVideoPlayCompleted;
    private float[] minAlpha;
    private float[][] reelMaxSpeed;
    private float[][] reelMinPoppingSpeed;
    private float[][] reelMinSpeed;
    private int[] reelMode;
    public static String sFackVideoParam;
    private String sHintCannon;
    private String sRobot;
    private String sSpace;
    private String[] sVideo1Raw;
    private String[] sVideo2Raw;
    private String[] saAsk;
    private String[] saHelp;
    private String[] saMsg;
    private ScrollPane scpAsk;
    private ScrollPane scpHelp;
    private ScrollPane scpMsg;
    private Skin skin;
    private static Skin skin_window;
    private Card[][] smallCard;
    private ScrollPaneStyle spsAsk;
    private ScrollPaneStyle spsHelp;
    private ScrollPaneStyle spsMsg;
    private static Stage stage;
    private Table tblAsk;
    private Table tblHelp;
    private Table tblMsg;
    private Table tblTest;
    private TextField tfChargeMoney;
    private TextField tfExchangeAccount;
    private TextField tfExchangeMoney;
    private TextField tfExchangeTel;
    private static Thread thdGameWrite;
    private Timer timer;
    private float width;

    static  {
        GameScreen.lsGameWrite = new ArrayList();
        GameScreen.lsGameRead = new ArrayList();
        GameScreen.iReserveConfirmTick = new int[2];
        GameScreen.fTotalReelElapseTime = new float[2];
        GameScreen.bHovering = new boolean[2];
        GameScreen.bHideVideo = true;
        GameScreen.bShowVideo = true;
        GameScreen.bHideCannon = true;
        GameScreen.bShowCannon = true;
        GameScreen.bCannonCelemony = true;
        GameScreen.fCelemony = 1000000000f;
        GameScreen.fSndStopDelay = 0f;
        GameScreen.sFackVideoParam = "";
        GameScreen.fFackVideoTick = 0f;
        GameScreen.bShowMenu = false;
        GameScreen.bReserving = false;
    }

    public GameScreen(Ymt2 _game) {
        super();
        this.width = ((float)Gdx.graphics.getWidth());
        this.height = ((float)Gdx.graphics.getHeight());
        this.deltaTime = 0.055556f;
        this.elapseTime = 0f;
        this.bGameScreenReady = false;
        this.fPoppingDelay = new float[2];
        this.lsScenario = new ArrayList[2];
        this.bStopScenarioForTest = false;
        this.iMaxMsg = 20;
        this.iCurMsg = 0;
        this.saMsg = new String[this.iMaxMsg];
        this.clMsg = new Color[this.iMaxMsg];
        this.iMaxHelp = 60;
        this.iCurHelp = 0;
        this.saHelp = new String[this.iMaxHelp];
        this.clHelp = new Color[this.iMaxHelp];
        this.iMaxAsk = 40;
        this.iCurAsk = 0;
        this.saAsk = new String[this.iMaxAsk];
        this.clAsk = new Color[this.iMaxAsk];
        this.fToastTime = 0f;
        this.bShowToast = false;
        this.lbMachineNo = new Label[16];
        this.iMachineState = new int[16];
        this.iMachineToday = new int[16];
        this.iMachineYesterday = new int[16];
        this.iMachineNo = new int[16];
        this.bReelRunning = new boolean[2][3];
        this.bReelAccelerating = new boolean[2][3];
        this.bReelDecelerating = new boolean[2][3];
        this.bReelPoppingStarted = new boolean[2][3];
        this.bReelDeceleratingStarted = new boolean[2][3];
        this.iReelTensioningStep = new int[2][3];
        this.fReelElapseTime = new float[2][3];
        this.fStartReelDelayTime = new float[2][3];
        this.fReelPoppingStartTime = new float[2][3];
        this.fReelDeceleratingStartTime = new float[2][3];
        this.fReelDeltaMileage = new float[2][3];
        this.iPopCount = new int[2][3];
        this.iMaxPopCount = new int[2][3];
        this.fReelAlpha = new float[2][3];
        this.iNextCardNo = new int[2][3];
        this.bReelCenter = new boolean[2][3];
        this.bPostReelCenter = new boolean[2][3];
        this.iTargetCardNo = new int[2][3];
        this.iPostTargetCardNo = new int[2][3];
        this.reelMaxSpeed = new float[2][3];
        this.reelMinSpeed = new float[2][3];
        this.bReelMinSpeedStarted = new boolean[2][3];
        this.bReelPoppingMinSpeedStarted = new boolean[2][3];
        this.reelMinPoppingSpeed = new float[2][3];
        this.accelerationSpeed = new float[2][3];
        this.decelerationSpeed = new float[2][3];
        this.accelerationAlpha = new float[2][3];
        this.decelerationAlpha = new float[2][3];
        this.fSecondAccelerationTime = new float[2];
        this.bSecondAcceleration = new boolean[2];
        this.bShrinkCard = new boolean[2];
        this.fNextScenarioDelay = new float[2];
        this.fEndScenarioDelay = new float[2];
        this.minAlpha = new float[2];
        this.fbDrawRobot = false;
        this.sVideo1Raw = new String[2];
        this.sVideo2Raw = new String[2];
        this.bVideo1Playing = new boolean[2];
        this.bVideo2Playing = new boolean[2];
        this.bVideo3Playing = new boolean[2];
        this.bPlayVideo1 = new boolean[2];
        this.bPlayVideo2 = new boolean[2];
        this.fVideo1StartTime = new float[2];
        this.fVideo2StartTime = new float[2];
        this.NONE = 0;
        this.LEFT = 5;
        this.RIGHT = 6;
        this.ALL = 7;
        this.POST_LEFT = -1;
        this.POST_RIGHT = -2;
        this.POST_ALL = -3;
        this.iFireType = new int[2];
        this.fFireDelayTime = new float[2];
        this.fCannonDelayTime = new float[2];
        this.fCannonRotation = new float[3];
        this.iCannonDirection = new int[3];
        this.fChatStep = 2f;
        this.fHintCannonElapseTime = 0f;
        this.iHintCannonStep = 0;
        this.iHintCannonType = 0;
        this.iHintCannonTick = 0;
        this.fPushCannonDelay = new float[2];
        this.sHintCannon = "";
        this.sRobot = "";
        this.card = new Card[2][3][4];
        this.smallCard = new Card[2][4];
        this.TOP = 1;
        this.CENTER = 2;
        this.BOTTOM = 3;
        this.CROSS = 4;
        this.bDrawLine = false;
        this.fLineElapseTime = 0f;
        this.iTCBX = new int[2];
        this.bLineLoop = false;
        this.IDLE = 0;
        this.REACH_FAILURE = 1;
        this.REACH_SUCCESS = 2;
        this.PUSH_BUTTON = 3;
        this.reelMode = new int[2];
        this.HIDE = 0;
        this.CHARGE = 1;
        this.EXCHANGE = 2;
        this.HELP = 3;
        this.ASK = 4;
        this.MACHINE = 5;
        this.iMenuMode = 0;
        this.iExMenuMode = 0;
        this.bCanInsert10000 = false;
        this.bCanSwitchMoney = false;
        this.bCanSwitchMachine = false;
        this.bCanUpDownChannel = false;
        this.bCanSelectMachine = false;
        this.bCanReserveMachine = false;
        this.bWaitingScenario = new boolean[2];
        this.bCanRequestScenario = new boolean[2];
        this.bSwitching = true;
        this.bEnding = new boolean[2];
        this.fIntervalMiddle = 0f;
        this.fsPostCardPos = new String[2];
        this.fGlitterDelay = new float[2];
        this.sSpace = "????";
        GameScreen.game = _game;
        GameScreen.batch = new SpriteBatch();
        this.camera = new OrthographicCamera(1f, this.height / this.width);
        GameScreen.stage = new Stage(this.width, this.height, false);
        Gdx.input.setInputProcessor(GameScreen.stage);
        Gdx.input.setCatchBackKey(true);
        this.ini();
        this.iniSkin();
        this.iniUI();
        this.iniCallback();
        this.iniHelpAskMsg();
        this.iniCards(0);
        this.iniCards(1);
        this.iniTimer();
        this.iniTest();
        this.iniChatBack(1);
        GameScreen.bCannonCelemony = true;
        GameScreen.fCelemony = 1000000000f;
        this.iHintCannonType = 3;
        this.fHintCannonElapseTime = 0f;
        this.iHintCannonStep = 1;
        Res.aniLampCircle.play(true);
        Res.sndCelemony.play();
        GameScreen.socketWrite("G010\t" + Global.id);
    }

    static boolean access$0(GameScreen arg1, int arg2, String arg3) {
        return arg1.reelIdle(arg2, arg3);
    }

    static boolean access$1(GameScreen arg1, int arg2, int arg3, int arg4, String arg5, String arg6) {
        return arg1.reelReachFailure(arg2, arg3, arg4, arg5, arg6);
    }

    static void access$10(GameScreen arg0, int arg1, int arg2) {
        arg0.showHintCannon(arg1, arg2);
    }

    static Label access$11(GameScreen arg1) {
        return arg1.lbChance100;
    }

    static String access$12(GameScreen arg1, int arg2, int arg3) {
        return arg1.findCardPos(arg2, arg3);
    }

    static void access$13(GameScreen arg0, String arg1, Color arg2) {
        arg0.addMsg(arg1, arg2);
    }

    static TextField access$14(GameScreen arg1) {
        return arg1.tfChargeMoney;
    }

    static TextField access$15(GameScreen arg1) {
        return arg1.tfExchangeAccount;
    }

    static TextField access$16(GameScreen arg1) {
        return arg1.tfExchangeMoney;
    }

    static TextField access$17(GameScreen arg1) {
        return arg1.tfExchangeTel;
    }

    static void access$18(GameScreen arg0, boolean arg1) {
        arg0.showMenu(arg1);
    }

    static void access$19(GameScreen arg0, boolean arg1) {
        arg0.bCanSelectMachine = arg1;
    }

    static boolean access$2(GameScreen arg1, int arg2, int arg3, int arg4, int arg5, String arg6, String arg7, String arg8) {
        return arg1.reelReachSuccess(arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    static void access$20(GameScreen arg0, boolean arg1) {
        arg0.bCanSwitchMoney = arg1;
    }

    static void access$21(GameScreen arg0, boolean arg1) {
        arg0.bCanReserveMachine = arg1;
    }

    static void access$22(GameScreen arg0, boolean arg1) {
        arg0.bCanInsert10000 = arg1;
    }

    static Label access$23(GameScreen arg1) {
        return arg1.lbChargeMan;
    }

    static Label access$24(GameScreen arg1) {
        return arg1.lbExchangeBank;
    }

    static Label access$25(GameScreen arg1) {
        return arg1.lbExchangeOwner;
    }

    static void access$26(GameScreen arg0, String arg1) {
        arg0.sendAsk(arg1);
    }

    static void access$27(GameScreen arg0) {
        arg0.showReserve();
    }

    static boolean access$3(GameScreen arg1, int arg2, int arg3, int arg4, String arg5) {
        return arg1.reelPushButton(arg2, arg3, arg4, arg5);
    }

    static void access$4(GameScreen arg0, String arg1, float arg2) {
        arg0.showToast(arg1, arg2);
    }

    static void access$5(GameScreen arg0, int arg1, String arg2) {
        arg0.setGage(arg1, arg2);
    }

    static boolean access$6(GameScreen arg1) {
        return arg1.bStopScenarioForTest;
    }

    static void access$7(GameScreen arg0, boolean arg1) {
        arg0.bStopScenarioForTest = arg1;
    }

    static void access$8(GameScreen arg0, int arg1, String arg2, String arg3, String arg4) {
        arg0.playVideo3(arg1, arg2, arg3, arg4);
    }

    static void access$9(GameScreen arg0, boolean arg1) {
        arg0.showRobot(arg1);
    }

    private void addAsk(String _sAsk, Color _color) {  // has try-catch handlers
        try {
            if(this.iCurAsk >= this.iMaxAsk) {
                --this.iCurAsk;
                int v1;
                for(v1 = 0; v1 < this.iMaxAsk - 1; ++v1) {
                    this.saAsk[v1] = this.saAsk[v1 + 1];
                    this.clAsk[v1] = this.clAsk[v1 + 1];
                }
            }

            this.saAsk[this.iCurAsk] = _sAsk;
            this.clAsk[this.iCurAsk] = _color;
            ++this.iCurAsk;
            this.tblAsk.clear();
            int v2 = this.iCurAsk + 1;
            if(v2 > this.iMaxAsk) {
                v2 = this.iMaxAsk;
            }

            for(v1 = 0; v1 < v2; ++v1) {
                if(this.width >= 1280f) {
                    this.tblAsk.row().height(38f);
                }
                else {
                    this.tblAsk.row().height(30f);
                }

                this.tblAsk.add(new Label(this.saAsk[v1], this.skin, "hangul-font", this.clAsk[v1])).expandX().fillX();
            }

            this.scpAsk.scrollTo(0f, this.scpAsk.getMaxHeight(), 0f, this.scpAsk.getMaxHeight());
            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->addAsk", Util.getExceptionMessage(v0));
            return;
        }
    }

    private void addHelp(String _sHelp, Color _color) {  // has try-catch handlers
        try {
            if(this.iCurHelp >= this.iMaxHelp) {
                --this.iCurHelp;
                int v1;
                for(v1 = 0; v1 < this.iMaxHelp - 1; ++v1) {
                    this.saHelp[v1] = this.saHelp[v1 + 1];
                    this.clHelp[v1] = this.clHelp[v1 + 1];
                }
            }

            this.saHelp[this.iCurHelp] = _sHelp;
            this.clHelp[this.iCurHelp] = _color;
            ++this.iCurHelp;
            this.tblHelp.clear();
            int v2 = this.iCurHelp + 1;
            if(v2 > this.iMaxHelp) {
                v2 = this.iMaxHelp;
            }

            for(v1 = 0; v1 < v2; ++v1) {
                if(this.width >= 1280f) {
                    this.tblHelp.row().height(38f);
                }
                else {
                    this.tblHelp.row().height(30f);
                }

                this.tblHelp.add(new Label(this.saHelp[v1], this.skin, "hangul-font", this.clHelp[v1])).expandX().fillX();
            }

            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->addHelp", Util.getExceptionMessage(v0));
            return;
        }
    }

    private void addMsg(String _sMsg, Color _color) {  // has try-catch handlers
        try {
            if(this.iCurMsg >= this.iMaxMsg) {
                --this.iCurMsg;
                int v1;
                for(v1 = 0; v1 < this.iMaxMsg - 1; ++v1) {
                    this.saMsg[v1] = this.saMsg[v1 + 1];
                    this.clMsg[v1] = this.clMsg[v1 + 1];
                }
            }

            this.saMsg[this.iCurMsg] = _sMsg;
            this.clMsg[this.iCurMsg] = _color;
            ++this.iCurMsg;
            this.tblMsg.clear();
            int v2 = this.iCurMsg + 1;
            if(v2 > this.iMaxMsg) {
                v2 = this.iMaxMsg;
            }

            for(v1 = 0; v1 < v2; ++v1) {
                if(this.width >= 1280f) {
                    this.tblMsg.row().height(38f);
                }
                else {
                    this.tblMsg.row().height(30f);
                }

                this.tblMsg.add(new Label(this.saMsg[v1], this.skin, "hangul-font", this.clMsg[v1])).expandX().fillX();
            }

            this.scpMsg.scrollTo(0f, this.scpMsg.getMaxHeight(), 0f, this.scpMsg.getMaxHeight());
            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->addMsg", Util.getExceptionMessage(v0));
            return;
        }
    }

    private void arrangeReel(int _iChannel) {
        int v5 = 4;
        if(Global.channel == _iChannel) {
            Res.aniSuccess.visible = false;
        }

        GameScreen.bHovering[_iChannel] = false;
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            int v1;
            for(v1 = 0; v1 < v5; ++v1) {
                if(this.card[_iChannel][v0][v1].alphaFlag < 0) {
                    this.card[_iChannel][v0][v1].setAlphaFlag(1);
                }

                this.card[_iChannel][v0][v1].visible = true;
            }
        }

        for(v0 = 0; v0 < v5; ++v0) {
            this.smallCard[_iChannel][v0].visible = false;
            this.smallCard[_iChannel][v0].small = false;
        }
    }

    private void askExitGame() {
        if(!Global.bAskingGameExit) {
            Global.bAskingGameExit = true;
            new Dialog() {
                protected void result(Object object) {
                    if(((Boolean)object).booleanValue()) {
                        Global.mode = 5;
                        Global.fTerminating = 0f;
                        Global.bTerminating = true;
                        GameScreen.this.showReserve();
                        GameScreen.socketWrite("G020\t" + Global.id);
                    }
                    else {
                        Global.bAskingGameExit = false;
                    }
                }
            }.text("?????? ?????????????????").button("   ??   ", Boolean.valueOf(true)).button("??????", Boolean.valueOf(false)).key(66, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(GameScreen.stage);
        }
    }

    private void changeMenuItem(int _iMenuMode) {
        int v5 = 16;
        this.iMenuMode = _iMenuMode;
        int v0;
        for(v0 = 0; v0 < v5; ++v0) {
            this.lbMachineNo[v0].setVisible(false);
        }

        this.scpAsk.setVisible(false);
        this.scpHelp.setVisible(false);
        this.lbChargeBank.setVisible(false);
        this.lbChargeAccount.setVisible(false);
        this.lbChargeOwner.setVisible(false);
        this.tfChargeMoney.setVisible(false);
        this.lbChargeMan.setVisible(false);
        this.lbExchangeBank.setVisible(false);
        this.lbExchangeOwner.setVisible(false);
        this.tfExchangeAccount.setVisible(false);
        this.tfExchangeMoney.setVisible(false);
        this.tfExchangeTel.setVisible(false);
        if(this.iMenuMode == 5) {
            v0 = 0;
            goto label_34;
        }
        else if(this.iMenuMode == 1) {
            this.lbChargeBank.setVisible(true);
            this.lbChargeAccount.setVisible(true);
            this.lbChargeOwner.setVisible(true);
            this.tfChargeMoney.setVisible(true);
            this.lbChargeMan.setVisible(true);
        }
        else if(this.iMenuMode == 2) {
            this.lbExchangeBank.setVisible(true);
            this.lbExchangeOwner.setVisible(true);
            this.tfExchangeAccount.setVisible(true);
            this.tfExchangeMoney.setVisible(true);
            this.tfExchangeTel.setVisible(true);
        }
        else if(this.iMenuMode == 3) {
            this.scpHelp.setVisible(true);
        }
        else if(this.iMenuMode == 4) {
            this.scpAsk.setVisible(true);
            return;
        label_34:
            while(v0 < v5) {
                this.lbMachineNo[v0].setVisible(true);
                ++v0;
            }

            this.machineListRequest(0);
        }
    }

    private void charge() {
        if(!"member".equals(Global.cls)) {
            this.showMessage("?????? ?????? ?????? ???? ?? ?? ????????.");
        }
        else {
            this.tfChargeMoney.setText(String.valueOf(this.tfChargeMoney.getText()) + " ");
            this.tfChargeMoney.setText(this.tfChargeMoney.getText().trim());
            int v0 = Util.strToint(this.tfChargeMoney.getText(), 0);
            if(v0 <= 0) {
                this.showMessage("???? ?????? ??????????.");
            }
            else if(v0 < 10000) {
                this.showMessage("???? ???? ?????? 10,000????????. ???? ?????? ??????????.");
            }
            else if(v0 >= 10000000) {
                this.showMessage("???? ?????? ??????????. ???? ???? ?????? ??????????????.");
            }
            else if("".equals(this.lbChargeMan.getText().toString().trim())) {
                this.showMessage("?????????? ??????????.");
            }
            else if(this.lbChargeMan.getText().toString().length() > 20) {
                this.showMessage("?????????? ??????????.\n?????? ?????? ??????????????.");
            }
            else {
                new Dialog() {
                    protected void result(Object object) {
                        int v0 = ((Integer)object).intValue();
                        if(v0 > 0) {
                            GameScreen.socketWrite("G120\t" + Global.id + "\t" + GameScreen.this.lbChargeMan.getText().toString().trim() + "\t" + String.valueOf(v0) + "\t" + Global.bank + "\t" + Global.account);
                            GameScreen.this.showToast("??????????????????. ???? ???? ?? ??????????.", 1.5f);
                            if(GameScreen.bShowMenu) {
                                GameScreen.this.showMenu(false);
                            }

                            GameScreen.this.lbChargeMan.setText("");
                            GameScreen.this.tfChargeMoney.setText("");
                        }
                    }
                }.text("[ " + Util.withComma(((long)v0)) + " ]???? ???? ?????????????????").button("??", Integer.valueOf(v0)).button("??????", Integer.valueOf(0)).key(66, Integer.valueOf(0)).key(131, Integer.valueOf(0)).show(GameScreen.stage);
            }
        }
    }

    public boolean cheatCardPos(int _iChannel) {
        int v5 = 3;
        int v4 = 2;
        int v0;
        for(v0 = 0; v0 < 4; ++v0) {
            this.smallCard[_iChannel][v0].visible = false;
            this.smallCard[_iChannel][v0].small = false;
        }

        this.card[_iChannel][0][0].visible = true;
        this.card[_iChannel][0][1].visible = true;
        this.card[_iChannel][0][v4].visible = true;
        this.card[_iChannel][0][v5].visible = true;
        this.card[_iChannel][1][0].visible = true;
        this.card[_iChannel][1][1].visible = true;
        this.card[_iChannel][1][v4].visible = true;
        this.card[_iChannel][1][v5].visible = true;
        this.card[_iChannel][v4][0].visible = true;
        this.card[_iChannel][v4][1].visible = true;
        this.card[_iChannel][v4][v4].visible = true;
        this.card[_iChannel][v4][v5].visible = true;
        if(!"".equals(this.fsPostCardPos[_iChannel]) && this.fsPostCardPos[_iChannel] != null) {
            this.getCardPos(_iChannel, this.fsPostCardPos[_iChannel]);
            this.fsPostCardPos[_iChannel] = "";
        }

        return 1;
    }

    private void clearGameScreen() {  // has try-catch handlers
        int v3;
        try {
            if(GameScreen.thdGameWrite == null) {
                goto label_6;
            }

            GameScreen.thdGameWrite.interrupt();
            GameScreen.thdGameWrite = null;
            goto label_6;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            goto label_6;
        }

        try {
        label_6:
            GameScreen.lsGameWrite.clear();
            goto label_8;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            goto label_8;
        }

        try {
        label_8:
            GameScreen.lsGameRead.clear();
            goto label_10;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            goto label_10;
        }

        try {
        label_10:
            this.lsScenario[0].clear();
            goto label_14;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            goto label_14;
        }

        try {
        label_14:
            this.lsScenario[1].clear();
        }
        catch(Exception v0) {
            v0.printStackTrace();
            goto label_19;
        }

        try {
        label_19:
            this.bCanInsert10000 = true;
            this.bCanUpDownChannel = true;
            this.bCanSwitchMoney = true;
            this.bCanSwitchMachine = true;
            this.bCanReserveMachine = true;
            this.bWaitingScenario[0] = true;
            this.bWaitingScenario[1] = true;
            this.bCanRequestScenario[0] = false;
            this.bCanRequestScenario[1] = false;
            this.bSwitching = true;
            this.bEnding[0] = false;
            this.bEnding[1] = false;
            GameScreen.bReserving = false;
            this.minAlpha[0] = 0.35f;
            this.minAlpha[1] = 0.35f;
            GameScreen.bHovering[0] = false;
            GameScreen.bHovering[1] = false;
            this.fVideo1StartTime[0] = 1000000000f;
            this.fVideo1StartTime[1] = 1000000000f;
            this.fVideo2StartTime[0] = 1000000000f;
            this.fVideo2StartTime[1] = 1000000000f;
            this.fSecondAccelerationTime[0] = 1000000000f;
            this.fSecondAccelerationTime[1] = 1000000000f;
            this.bSecondAcceleration[0] = false;
            this.bSecondAcceleration[1] = false;
            this.bShrinkCard[0] = false;
            this.bShrinkCard[1] = false;
            this.bVideo1Playing[0] = false;
            this.bVideo1Playing[1] = false;
            this.bVideo2Playing[0] = false;
            this.bVideo2Playing[1] = false;
            this.bVideo3Playing[0] = false;
            this.bVideo3Playing[1] = false;
            this.bPlayVideo1[0] = false;
            this.bPlayVideo1[1] = false;
            this.bPlayVideo2[0] = false;
            this.bPlayVideo2[1] = false;
            this.bDrawLine = false;
            this.fLineElapseTime = 0f;
            this.bLineLoop = false;
            this.iTCBX[0] = 1;
            this.iTCBX[1] = 1;
            this.reelMode[0] = 0;
            this.reelMode[1] = 0;
            this.fIntervalMiddle = 0f;
            this.fsPostCardPos[0] = "";
            this.fsPostCardPos[1] = "";
            this.fbDrawRobot = false;
            GameScreen.fTotalReelElapseTime[0] = 0f;
            GameScreen.fTotalReelElapseTime[1] = 0f;
            this.bGameScreenReady = false;
            this.bCanSelectMachine = true;
            v3 = 0;
        }
        catch(Exception v0) {
            goto label_418;
        }

        while(v3 < 2) {
            int v1;
            for(v1 = 0; v1 < 3; ++v1) {
                int v2 = 0;
                while(true) {
                    if(v2 < 4) {
                        goto label_233;
                    }

                    break;
                    try {
                    label_233:
                        this.card[v3][v1][v2].startY = this.card[v3][v1][v2].y;
                        this.card[v3][v1][v2].visible = true;
                        this.card[v3][v1][v2].small = false;
                        this.card[v3][v1][v2].mileage = 0f;
                        this.card[v3][v1][v2].setAlphaFlag(0);
                        ++v2;
                        continue;
                    }
                    catch(Exception v0) {
                        goto label_418;
                    }
                }
            }

            ++v3;
        }

        v1 = 0;
        while(true) {
            if(v1 < 4) {
                goto label_269;
            }

            break;
            try {
            label_269:
                this.smallCard[0][v1].visible = false;
                this.smallCard[0][v1].small = false;
                this.smallCard[1][v1].visible = false;
                this.smallCard[1][v1].small = false;
                ++v1;
                continue;
            }
            catch(Exception v0) {
                goto label_418;
            }
        }

        v1 = 0;
        while(true) {
            if(v1 < 3) {
                goto label_295;
            }

            return;
            try {
            label_295:
                this.bReelRunning[0][v1] = false;
                this.bReelRunning[1][v1] = false;
                this.bReelPoppingStarted[0][v1] = false;
                this.bReelPoppingStarted[1][v1] = false;
                this.bReelDeceleratingStarted[0][v1] = false;
                this.bReelDeceleratingStarted[1][v1] = false;
                this.bReelAccelerating[0][v1] = false;
                this.bReelAccelerating[1][v1] = false;
                this.bReelDecelerating[0][v1] = false;
                this.bReelDecelerating[1][v1] = false;
                this.bReelMinSpeedStarted[0][v1] = false;
                this.bReelMinSpeedStarted[1][v1] = false;
                this.bReelPoppingMinSpeedStarted[0][v1] = false;
                this.bReelPoppingMinSpeedStarted[1][v1] = false;
                this.iReelTensioningStep[0][v1] = 0;
                this.iReelTensioningStep[1][v1] = 0;
                this.fReelDeltaMileage[0][v1] = 0f;
                this.fReelDeltaMileage[1][v1] = 0f;
                this.fReelElapseTime[0][v1] = 0f;
                this.fReelElapseTime[1][v1] = 0f;
                this.iPopCount[0][v1] = 0;
                this.iPopCount[1][v1] = 0;
                this.fReelAlpha[0][v1] = 1f;
                this.fReelAlpha[1][v1] = 1f;
                ++v1;
                continue;
            }
            catch(Exception v0) {
            label_418:
                v0.printStackTrace();
                return;
            }
        }
    }

    private static void clearGameSocket() {  // has try-catch handlers
        try {
            if(GameScreen.thdGameWrite == null) {
                goto label_6;
            }

            GameScreen.thdGameWrite.interrupt();
            GameScreen.thdGameWrite = null;
            goto label_6;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->clearGameSocket(1)", Util.getExceptionMessage(v0));
            goto label_6;
        }

        try {
        label_6:
            GameScreen.lsGameWrite.clear();
            goto label_8;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->clearGameSocket(2)", Util.getExceptionMessage(v0));
            goto label_8;
        }

        try {
        label_8:
            GameScreen.lsGameRead.clear();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->clearGameSocket(3)", Util.getExceptionMessage(v0));
        }
    }

    private void clickEffect(float _x, float _y, float _width, float _height, int _size) {  // has try-catch handlers
        float v5 = 2f;
        try {
            Res.aniClick.setSize(Util.getWidth(((float)_size)), Util.getHeight(((float)_size)));
            Res.aniClick.setPosition(_width / v5 + _x - Util.getWidth(((float)_size)) / v5, _height / v5 + _y - Util.getHeight(((float)_size)) / v5);
            Res.aniClick.visible = true;
            Res.aniClick.play(true, 1);
            Res.sndButton.play();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->clickEffect", Util.getExceptionMessage(v0));
        }
    }

    private void clickEffect(int _x, int _y, int _size) {  // has try-catch handlers
        try {
            Res.aniClick.setSize(Util.getWidth(((float)_size)), Util.getHeight(((float)_size)));
            Res.aniClick.setPosition(Util.XCenter(((float)_x), Res.aniClick.width), Util.YCenter(((float)_y), Res.aniClick.height));
            Res.aniClick.visible = true;
            Res.aniClick.play(true, 1);
            Res.sndButton.play();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->clickEffect", Util.getExceptionMessage(v0));
        }
    }

    private void controlReelSpeed(int _iChannel, int iReelIndex) {
        float v6 = 1f;
        float v1 = 0.25f;
        float v0 = -0.03f;
        if(this.bReelAccelerating[_iChannel][iReelIndex]) {
            if(this.fReelElapseTime[_iChannel][iReelIndex] <= v1) {
                this.fReelDeltaMileage[_iChannel][iReelIndex] = this.deltaTime * v0;
            }
            else {
                this.fReelDeltaMileage[_iChannel][iReelIndex] += this.accelerationSpeed[_iChannel][iReelIndex] * this.deltaTime;
                if(this.fReelDeltaMileage[_iChannel][iReelIndex] > this.reelMaxSpeed[_iChannel][iReelIndex] * this.deltaTime) {
                    this.fReelDeltaMileage[_iChannel][iReelIndex] = this.reelMaxSpeed[_iChannel][iReelIndex] * this.deltaTime;
                }

                this.fReelAlpha[_iChannel][iReelIndex] -= this.accelerationAlpha[_iChannel][iReelIndex];
                if(this.fReelAlpha[_iChannel][iReelIndex] >= this.minAlpha[_iChannel]) {
                    goto label_17;
                }

                this.fReelAlpha[_iChannel][iReelIndex] = this.minAlpha[_iChannel];
            }
        }

    label_17:
        if(this.bReelDecelerating[_iChannel][iReelIndex]) {
            if(this.bReelPoppingStarted[_iChannel][iReelIndex]) {
                if(this.bReelPoppingMinSpeedStarted[_iChannel][iReelIndex]) {
                    this.fReelDeltaMileage[_iChannel][iReelIndex] = this.reelMinPoppingSpeed[_iChannel][iReelIndex] * this.deltaTime;
                }
                else {
                    this.fReelDeltaMileage[_iChannel][iReelIndex] -= this.decelerationSpeed[_iChannel][iReelIndex] * this.deltaTime;
                    if(this.fReelDeltaMileage[_iChannel][iReelIndex] < this.reelMinPoppingSpeed[_iChannel][iReelIndex] * this.deltaTime) {
                        this.fReelDeltaMileage[_iChannel][iReelIndex] = this.reelMinPoppingSpeed[_iChannel][iReelIndex] * this.deltaTime;
                        this.bReelPoppingMinSpeedStarted[_iChannel][iReelIndex] = true;
                    }
                }
            }
            else if(this.bReelMinSpeedStarted[_iChannel][iReelIndex]) {
                this.fReelDeltaMileage[_iChannel][iReelIndex] = this.reelMinSpeed[_iChannel][iReelIndex] * this.deltaTime;
            }
            else {
                this.fReelDeltaMileage[_iChannel][iReelIndex] -= this.decelerationSpeed[_iChannel][iReelIndex] * this.deltaTime;
                if(this.fReelDeltaMileage[_iChannel][iReelIndex] < this.reelMinSpeed[_iChannel][iReelIndex] * this.deltaTime) {
                    this.fReelDeltaMileage[_iChannel][iReelIndex] = this.reelMinSpeed[_iChannel][iReelIndex] * this.deltaTime;
                    this.bReelMinSpeedStarted[_iChannel][iReelIndex] = true;
                }
            }

            this.fReelAlpha[_iChannel][iReelIndex] += this.decelerationSpeed[_iChannel][iReelIndex];
            if(this.fReelAlpha[_iChannel][iReelIndex] <= v6) {
                return;
            }

            this.fReelAlpha[_iChannel][iReelIndex] = v6;
        }
    }

    public void dispose() {
        this.disposeGameScreen();
    }

    private void disposeGameScreen() {  // has try-catch handlers
        try {
            this.timer.cancel();
            goto label_2;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(1)", Util.getExceptionMessage(v0));
            goto label_2;
        }

        try {
        label_2:
            MySocket.clear();
            goto label_3;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(2)", Util.getExceptionMessage(v0));
            goto label_3;
        }

        try {
        label_3:
            GameScreen.clearGameSocket();
            goto label_4;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(3)", Util.getExceptionMessage(v0));
            goto label_4;
        }

        try {
        label_4:
            this.clearGameScreen();
            goto label_5;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(4)", Util.getExceptionMessage(v0));
            goto label_5;
        }

        try {
        label_5:
            GameScreen.batch.dispose();
            goto label_7;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(5)", Util.getExceptionMessage(v0));
            goto label_7;
        }

        try {
        label_7:
            this.skin.dispose();
            goto label_9;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(6)", Util.getExceptionMessage(v0));
            goto label_9;
        }

        try {
        label_9:
            GameScreen.skin_window.dispose();
            goto label_11;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(7)", Util.getExceptionMessage(v0));
            goto label_11;
        }

        try {
        label_11:
            GameScreen.stage.dispose();
            goto label_13;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(8)", Util.getExceptionMessage(v0));
            goto label_13;
        }

        try {
        label_13:
            Res.dispose();
            goto label_14;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(9)", Util.getExceptionMessage(v0));
            goto label_14;
        }

        try {
        label_14:
            Global.ini();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->disposeGameScreen(10)", Util.getExceptionMessage(v0));
            goto label_16;
        }

        try {
        label_16:
            Dialog.count = 0;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "LoginScreen->disposeGameScreen(11)", Util.getExceptionMessage(v0));
        }
    }

    private void drawCards(int _iChannel) {
        this.card[_iChannel][0][0].draw(this.deltaTime, this.fReelAlpha[_iChannel][0]);
        this.card[_iChannel][0][1].draw(this.deltaTime, this.fReelAlpha[_iChannel][0]);
        this.card[_iChannel][0][2].draw(this.deltaTime, this.fReelAlpha[_iChannel][0]);
        this.card[_iChannel][0][3].draw(this.deltaTime, this.fReelAlpha[_iChannel][0]);
        this.card[_iChannel][1][0].draw(this.deltaTime, this.fReelAlpha[_iChannel][1]);
        this.card[_iChannel][1][1].draw(this.deltaTime, this.fReelAlpha[_iChannel][1]);
        this.card[_iChannel][1][2].draw(this.deltaTime, this.fReelAlpha[_iChannel][1]);
        this.card[_iChannel][1][3].draw(this.deltaTime, this.fReelAlpha[_iChannel][1]);
        this.card[_iChannel][2][0].draw(this.deltaTime, this.fReelAlpha[_iChannel][2]);
        this.card[_iChannel][2][1].draw(this.deltaTime, this.fReelAlpha[_iChannel][2]);
        this.card[_iChannel][2][2].draw(this.deltaTime, this.fReelAlpha[_iChannel][2]);
        this.card[_iChannel][2][3].draw(this.deltaTime, this.fReelAlpha[_iChannel][2]);
        this.smallCard[_iChannel][0].draw(this.deltaTime, 1f);
        this.smallCard[_iChannel][1].draw(this.deltaTime, 1f);
        this.smallCard[_iChannel][2].draw(this.deltaTime, 1f);
        this.smallCard[_iChannel][3].draw(this.deltaTime, 1f);
    }

    private void drawLine(int _iChannel) {
        int v2;
        if(this.bDrawLine) {
            float v0 = 0.02f;
            float v1 = 0.035f;
            int v3 = 17;
            int v4 = 20;
            this.fLineElapseTime += this.deltaTime;
            if(!this.bLineLoop) {
                v2 = (((int)(this.fLineElapseTime / v0))) % v3;
                if(this.fLineElapseTime >= (((float)v3)) * v0) {
                    this.bLineLoop = true;
                    this.fLineElapseTime = 0f;
                }
            }
            else {
                v2 = (((int)(this.fLineElapseTime / v1))) % v4 + v3;
            }

            if(this.iTCBX[_iChannel] != 4) {
                goto label_45;
            }

            Res.spCrossLine1[v2].draw(GameScreen.batch);
            Res.spCrossLine2[v2].draw(GameScreen.batch);
            return;
        label_45:
            Res.spLine[v2].draw(GameScreen.batch);
        }
    }

    private void effectChannel(int iCnt) {
        Res.aniBackChannel.play(true, iCnt);
        Res.aniSmallChannelBack.play(true, iCnt);
    }

    private void effectGift(int iCnt) {
        Res.aniBackGift.play(true, iCnt);
    }

    private void effectMoney(int iCnt) {
        Res.aniBackMoney.play(true, iCnt);
    }

    private void effectNo(int iCnt) {
        Res.aniBackNo.play(true, iCnt);
    }

    private void effectSpin(int iCnt) {
        Res.aniBackSpin.play(true, iCnt);
    }

    private void endScenario(int iChannel) {  // has try-catch handlers
        try {
            if(!Global.bTerminating) {
                goto label_3;
            }

            return;
        label_3:
            if(this.lsScenario[iChannel].size() > 0) {
                goto label_24;
            }

            Util.sysout("ERROR", "GameScreen->endScenario(1)", "Invalid Scenario Count.", String.valueOf(iChannel));
            GameScreen.exitApp("?????????? ???? ?? ?? ????????(E1).\n?????????? ??????????.");
            return;
        label_24:
            Object v3 = this.lsScenario[iChannel].get(0);
            String v5 = this.getValue("A", ((String)v3));
            String v4 = this.getValue("B", ((String)v3));
            int v1 = Util.strToint(v4, 0);
            if(Global.machineNo[iChannel] == v1 && v1 > 0) {
                goto label_52;
            }

            Util.sysout("ERROR", "GameScreen->endScenario(2)", "Invalid MachineNo.", String.valueOf(String.valueOf(iChannel)) + "/sMachineNo=" + v4);
            GameScreen.exitApp("?????????? ?????? ?? ????????(E2).\n?????????? ??????????.");
            return;
        label_52:
            this.lsScenario[iChannel].remove(0);
            this.bReelRunning[iChannel][0] = false;
            this.bReelRunning[iChannel][1] = false;
            this.bReelRunning[iChannel][2] = false;
            this.bVideo1Playing[iChannel] = false;
            this.bVideo2Playing[iChannel] = false;
            this.bVideo3Playing[iChannel] = false;
            this.bEnding[iChannel] = true;
            GameScreen.socketWrite("G100\t" + Global.id + "\t" + String.valueOf(Global.machineNo[iChannel]) + "\t" + v5 + "\t" + (String.valueOf(this.findCardPos(iChannel, 0)) + this.findCardPos(iChannel, 1) + this.findCardPos(iChannel, 2)));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->endScenario(0)", Util.getExceptionMessage(v0), String.valueOf(iChannel));
            GameScreen.exitApp("?????????? ???? ?? ?? ????????(E).\n?????????? ??????????.");
        }
    }

    private void exchange() {
        int v4 = 20;
        this.tfExchangeAccount.setText(String.valueOf(this.tfExchangeAccount.getText()) + " ");
        this.tfExchangeAccount.setText(this.tfExchangeAccount.getText().trim());
        if("".equals(this.tfExchangeAccount.getText())) {
            this.showMessage("?????????? ??????????.");
        }
        else {
            this.tfExchangeMoney.setText(String.valueOf(this.tfExchangeMoney.getText()) + " ");
            this.tfExchangeMoney.setText(this.tfExchangeMoney.getText().trim());
            int v0 = Util.strToint(this.tfExchangeMoney.getText(), 0);
            if(v0 <= 0) {
                this.showMessage("???? ?????? ??????????.");
            }
            else if(v0 < 1000) {
                this.showMessage("???? ???? ?????? 1,000????????.");
            }
            else if(v0 > Global.money) {
                this.showMessage("???? ?????? ???????? ?????? ?? ????????.");
            }
            else {
                this.tfExchangeTel.setText(String.valueOf(this.tfExchangeTel.getText()) + " ");
                this.tfExchangeTel.setText(this.tfExchangeTel.getText().trim());
                if("".equals(this.tfExchangeTel.getText())) {
                    this.showMessage("?????????? ??????????.");
                }
                else {
                    this.lbExchangeBank.setText(this.lbExchangeBank.getText().toString().trim());
                    if("".equals(this.lbExchangeBank.getText().toString())) {
                        this.showMessage("???????? ??????????.");
                    }
                    else {
                        this.lbExchangeOwner.setText(this.lbExchangeOwner.getText().toString().trim());
                        if("".equals(this.lbExchangeOwner.getText().toString())) {
                            this.showMessage("???????? ??????????.");
                        }
                        else if(this.lbExchangeBank.getText().toString().length() > v4) {
                            this.showMessage("???????? ??????????.\n?????? ?????? ??????????????.");
                        }
                        else if(this.lbExchangeOwner.getText().toString().length() > v4) {
                            this.showMessage("???????? ??????????.\n?????? ?????? ??????????????.");
                        }
                        else if(this.tfExchangeAccount.getText().length() > v4) {
                            this.showMessage("?????????? ??????????.\n?????? ?????? ??????????????.");
                        }
                        else if(this.tfExchangeTel.getText().length() > v4) {
                            this.showMessage("?????????? ??????????.\n?????? ?????? ??????????????.");
                        }
                        else {
                            new Dialog() {
                                protected void result(Object object) {
                                    if(((Integer)object).intValue() > 0) {
                                        GameScreen.socketWrite("G130\t" + Global.id + "\t" + GameScreen.this.lbExchangeBank.getText().toString().trim() + "\t" + GameScreen.this.lbExchangeOwner.getText().toString().trim() + "\t" + GameScreen.this.tfExchangeAccount.getText().trim() + "\t" + GameScreen.this.tfExchangeMoney.getText().trim() + "\t" + GameScreen.this.tfExchangeTel.getText().trim());
                                        GameScreen.this.showToast("??????????????????. ???? ?? ????????????????.", 1.5f);
                                        if(GameScreen.bShowMenu) {
                                            GameScreen.this.showMenu(false);
                                        }

                                        GameScreen.this.tfExchangeAccount.setText("");
                                        GameScreen.this.tfExchangeMoney.setText("");
                                        GameScreen.this.tfExchangeTel.setText("");
                                        GameScreen.this.lbExchangeBank.setText("");
                                        GameScreen.this.lbExchangeOwner.setText("");
                                    }
                                }
                            }.text("???????? ???????? ???? ?????? ?????????????").button("??", Integer.valueOf(1)).button("??????", Integer.valueOf(0)).key(66, Integer.valueOf(0)).key(131, Integer.valueOf(0)).show(GameScreen.stage);
                        }
                    }
                }
            }
        }
    }

    static void exitApp(String sMsg) {  // has try-catch handlers
        try {
            Global.bAskingGameExit = true;
            Global.mode = 5;
            GameScreen.sendMsgToAndroid("hide_ui");
            MySocket.clear();
            GameScreen.clearGameSocket();
            GameScreen.lbReserve.setVisible(false);
            Res.aniReserveBack.visible = false;
            if("".equals(sMsg)) {
                goto label_37;
            }

            new Dialog() {
                protected void result(Object object) {
                    Global.fTerminating = 0f;
                    Global.bTerminating = true;
                }
            }.text(sMsg).button("   ????   ", Boolean.valueOf(true)).key(66, Boolean.valueOf(true)).key(131, Boolean.valueOf(true)).show(GameScreen.stage);
            return;
        label_37:
            Global.fTerminating = 0f;
            Global.bTerminating = true;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->exitApp", Util.getExceptionMessage(v0));
        }
    }

    private String findCardPos(int _iChannel, int iReelIndex) {  // has try-catch handlers
        String v10 = "";
        int v6 = 0;
        int v7 = 0;
        float v2 = 0f;
        float v3 = 0f;
        try {
            float v5 = Res.fBaseLineY3;
            float v4 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / 2f;
            float[] v8 = new float[4];
            v8[0] = Math.abs(this.card[_iChannel][iReelIndex][0].y - v5);
            v8[1] = Math.abs(this.card[_iChannel][iReelIndex][1].y - v5);
            v8[2] = Math.abs(this.card[_iChannel][iReelIndex][2].y - v5);
            v8[3] = Math.abs(this.card[_iChannel][iReelIndex][3].y - v5);
            float[] v11 = new float[4];
            v11[0] = v8[0];
            v11[1] = v8[1];
            v11[2] = v8[2];
            v11[3] = v8[3];
            Arrays.sort(v8);
            if(v11[0] == v8[0]) {
                v2 = v11[0];
                v6 = 0;
            }
            else if(v11[1] == v8[0]) {
                v2 = v11[1];
                v6 = 1;
            }
            else if(v11[2] == v8[0]) {
                v2 = v11[2];
                v6 = 2;
            }
            else if(v11[3] == v8[0]) {
                v2 = v11[3];
                v6 = 3;
            }
            else {
                Util.sysout("ERROR", "GameScreen->findCardPos(1)", "Can\'t find the card");
            }

            float[] v9 = new float[4];
            v9[0] = Math.abs(this.card[_iChannel][iReelIndex][0].y - v4);
            v9[1] = Math.abs(this.card[_iChannel][iReelIndex][1].y - v4);
            v9[2] = Math.abs(this.card[_iChannel][iReelIndex][2].y - v4);
            v9[3] = Math.abs(this.card[_iChannel][iReelIndex][3].y - v4);
            float[] v12 = new float[4];
            v12[0] = v9[0];
            v12[1] = v9[1];
            v12[2] = v9[2];
            v12[3] = v9[3];
            Arrays.sort(v9);
            if(v12[0] == v9[0]) {
                v3 = v12[0];
                v7 = 0;
            }
            else if(v12[1] == v9[0]) {
                v3 = v12[1];
                v7 = 1;
            }
            else if(v12[2] == v9[0]) {
                v3 = v12[2];
                v7 = 2;
            }
            else if(v12[3] == v9[0]) {
                v3 = v12[3];
                v7 = 3;
            }
            else {
                Util.sysout("ERROR", "GameScreen->findCardPos(2)", "Can\'t find the card");
            }

            if(v2 >= v3) {
                goto label_245;
            }

            v10 = "T" + String.valueOf(this.card[_iChannel][iReelIndex][v6].no);
            goto label_173;
        label_245:
            v10 = "C" + String.valueOf(this.card[_iChannel][iReelIndex][v7].no);
        }
        catch(Exception v1) {
            Util.sysout("ERROR", "GameScreen->findCardPos(0)", Util.getExceptionMessage(v1), "");
        }

    label_173:
        return v10;
    }

    private void fire(int iChannel, int iReel) {  // has try-catch handlers
        try {
            if(Global.channel == iChannel) {
                Res.fire[iReel].visible = true;
                Res.fire[iReel].play(true, 1);
            }

            int v1;
            for(v1 = 0; v1 < 4; ++v1) {
                this.card[iChannel][iReel][v1].startY = this.card[iChannel][iReel][v1].y;
                this.card[iChannel][iReel][v1].mileage = 0f;
                this.card[iChannel][iReel][v1].visible = true;
            }

            this.bReelRunning[iChannel][iReel] = true;
            this.bReelPoppingStarted[iChannel][iReel] = false;
            this.bReelDeceleratingStarted[iChannel][iReel] = false;
            this.bReelAccelerating[iChannel][iReel] = true;
            this.bReelDecelerating[iChannel][iReel] = false;
            this.bReelMinSpeedStarted[iChannel][iReel] = false;
            this.bReelPoppingMinSpeedStarted[iChannel][iReel] = false;
            this.iReelTensioningStep[iChannel][iReel] = 0;
            this.fReelDeltaMileage[iChannel][iReel] = 0f;
            this.fReelElapseTime[iChannel][iReel] = 0f;
            this.iPopCount[iChannel][iReel] = 0;
            this.fStartReelDelayTime[iChannel][iReel] = 0f;
            this.reelMaxSpeed[iChannel][iReel] = 2f;
            this.reelMinSpeed[iChannel][iReel] = 1.5f;
            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fire", Util.getExceptionMessage(v0));
            return;
        }
    }

    private void fn_announce(String[] aMsg) {  // has try-catch handlers
        String v1;
        String v3;
        try {
            String v2 = aMsg[1];
            v3 = aMsg[2];
            if(!"M".equals(v2)) {
                goto label_9;
            }

            this.showMessage(v3);
            return;
        label_9:
            if(!"T".equals(v2)) {
                goto label_22;
            }

            this.showToast(v3, 2f);
            return;
        label_22:
            if(!"C".equals(v2)) {
                goto label_64;
            }

            v1 = "";
            goto label_26;
        }
        catch(Exception v0) {
            goto label_20;
        }

        try {
        label_26:
            if(aMsg.length < 4) {
                goto label_31;
            }

            v1 = aMsg[3];
            goto label_31;
        }
        catch(Exception v4) {
            goto label_71;
        }

        try {
        label_64:
            Util.sysout("ERROR", "GameScreen->fn_announce(1)", "Invalid Announce Flag", Util.toString(aMsg));
            return;
        }
        catch(Exception v0) {
            goto label_20;
        }

    label_71:
        try {
        label_31:
            if(!"orange".equals(v1)) {
                goto label_37;
            }

            this.addMsg(v3, Color.ORANGE);
            return;
        label_37:
            if(!"white".equals(v1)) {
                goto label_43;
            }

            this.addMsg(v3, Color.WHITE);
            return;
        label_43:
            if(!"green".equals(v1)) {
                goto label_49;
            }

            this.addMsg(v3, Color.GREEN);
            return;
        label_49:
            if(!"blue".equals(v1)) {
                goto label_55;
            }

            this.addMsg(v3, Color.BLUE);
            return;
        label_55:
            if(!"red".equals(v1)) {
                goto label_61;
            }

            this.addMsg(v3, Color.RED);
            return;
        label_61:
            this.addMsg(v3, Color.YELLOW);
        }
        catch(Exception v0) {
        label_20:
            Util.sysout("ERROR", "GameScreen->fn_announce(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_ask(String[] aMsg) {  // has try-catch handlers
        int v7 = 3;
        try {
            if(!GameScreen.bShowMenu) {
                this.addMsg("?????? ???? ?????? ????????????.", Color.GREEN);
            }

            int v1;
            for(v1 = 0; v1 < this.iMaxAsk; ++v1) {
                this.saAsk[v1] = "";
            }

            this.iCurAsk = 0;
            this.addAsk("**** 1:1 ???? ????????????. ****", Color.ORANGE);
            int v2 = aMsg.length - 2;
            v1 = 1;
        }
        catch(Exception v0) {
            goto label_58;
        }

        while(true) {
            if(v1 < v2) {
                goto label_34;
            }

            goto label_18;
            try {
            label_34:
                if(aMsg[v1].length() <= v7 || !"??????".equals(aMsg[v1].substring(0, 3))) {
                    this.addAsk(aMsg[v1], Color.YELLOW);
                }
                else {
                    this.addAsk(aMsg[v1], Color.ORANGE);
                }

                ++v1;
                continue;
            }
            catch(Exception v0) {
                break;
            }
        }

        try {
        label_18:
            this.scpAsk.layout();
            this.scpAsk.scrollTo(0f, this.scpAsk.getMaxHeight(), 0f, this.scpAsk.getMaxHeight());
            return;
        }
        catch(Exception v3) {
            return;
        }

    label_58:
        Util.sysout("ERROR", "GameScreen->fn_ask(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
    }

    private void fn_bank(String[] aMsg) {  // has try-catch handlers
        try {
            if("".equals(aMsg[1])) {
                return;
            }

            if("".equals(aMsg[2])) {
                return;
            }

            if("".equals(aMsg[3])) {
                return;
            }

            this.lbChargeBank.setText(aMsg[1]);
            this.lbChargeOwner.setText(aMsg[2]);
            this.lbChargeAccount.setText(aMsg[3]);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_bank(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_charge_completed(String[] aMsg) {  // has try-catch handlers
        try {
            String v4 = aMsg[1];
            int v1 = Util.strToint(aMsg[2], -1);
            int v2 = Util.strToint(aMsg[3], -1);
            if((Global.id.equals(v4)) && v1 >= 0 && v2 >= 0) {
                goto label_22;
            }

            Util.sysout("ERROR", "GameScreen->fn_charge_completed(1)", "????????????????", Util.toString(aMsg));
            return;
        label_22:
            int v3 = Util.strToint(aMsg[4], -1);
            Global.money = v2;
            this.updateLabel();
            if(v3 == 1) {
                this.showToast("?????? ?????? " + String.valueOf(v1) + "?? ??????????????.", 1.5f);
            }
            else {
                this.showToast(String.valueOf(String.valueOf(v1)) + "???? ??????????????????.", 1.5f);
            }

            this.effectMoney(15);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_charge_completed", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_coin_info(String[] aMsg) {  // has try-catch handlers
        int v11;
        try {
            int v6 = Integer.parseInt(aMsg[1]);
            int v1 = Integer.parseInt(aMsg[2]);
            int v4 = Integer.parseInt(aMsg[3]);
            int v2 = Integer.parseInt(aMsg[4]);
            int v9 = Integer.parseInt(aMsg[5]);
            int v5 = Integer.parseInt(aMsg[6]);
            int v3 = Integer.parseInt(aMsg[7]);
            int v10 = Integer.parseInt(aMsg[8]);
            if(v1 != Global.channel || v4 != Global.machineNo[0] || v5 != Global.machineNo[1]) {
                Util.sysout("ERROR", "GameScreen->fn_coin_info(1)", "??????????????", String.valueOf(Util.toString(aMsg)) + "/Global.channel=" + String.valueOf(Global.channel) + "/Global.machine1=" + String.valueOf(Global.machineNo[0]) + "/Global.machine2=" + String.valueOf(Global.machineNo[1]));
                GameScreen.exitApp("?????????? ???????? ????????(CC1).\n?????????? ??????????.");
            }

            Global.money = v6;
            Global.spin[0] = v9;
            Global.gift[0] = v2;
            Global.spin[1] = v10;
            Global.gift[1] = v3;
            v11 = 9;
            goto label_77;
        }
        catch(Exception v0) {
            goto label_96;
        }

        try {
        label_77:
            int v7 = Integer.parseInt(aMsg[v11]);
            int v8 = Integer.parseInt(aMsg[10]);
            Global.reserve[0] = v7;
            Global.reserve[1] = v8;
            this.showReserve();
            goto label_89;
        }
        catch(Exception v11_1) {
            goto label_89;
        }

        try {
        label_89:
            this.updateLabel();
        }
        catch(Exception v0) {
        label_96:
            Util.sysout("ERROR", "GameScreen->fn_coin_info(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???????? ????????(CC).\n?????????? ??????????.");
        }
    }

    private void fn_confirm_reserve(String[] aMsg) {  // has try-catch handlers
        int v5;
        int v2;
        int v4;
        int v3;
        int v6;
        int v1;
        try {
            if(Global.id.equals(aMsg[1])) {
                goto label_11;
            }

            Util.sysout("ERROR", "GameScreen->fn_confirm_reserve(1)", "Id Dismatched.", Util.toString(aMsg));
            return;
        label_11:
            v1 = Util.strToint(aMsg[3], -1);
            v6 = Integer.parseInt(aMsg[4]);
            v3 = Integer.parseInt(aMsg[5]);
            v4 = Integer.parseInt(aMsg[6]);
            v2 = Integer.parseInt(aMsg[7]);
            v5 = Integer.parseInt(aMsg[8]);
            if(v1 != Global.machineNo[0]) {
                goto label_83;
            }
            else if(v1 > 0) {
                if(v3 == 0) {
                    this.showToast("[" + String.valueOf(v1) + "]?? ?????? ??????????????.", 1.5f);
                    if(Global.machineNo[Global.channel] == v1) {
                        this.effectGift(15);
                        this.effectSpin(15);
                        this.effectNo(15);
                        this.effectMoney(15);
                    }
                    else {
                        goto label_80;
                    }
                }

                goto label_57;
            }
            else {
                goto label_83;
            }
        }
        catch(Exception v0) {
            goto label_78;
        }

    label_80:
        int v8 = 15;
        try {
            this.effectMoney(v8);
            goto label_57;
        label_83:
            if(v1 != Global.machineNo[1]) {
                goto label_70;
            }

            if(v1 <= 0) {
                goto label_70;
            }

            if(v3 == 0) {
                this.showToast("[" + String.valueOf(v1) + "]?? ?????? ??????????????.", 1.5f);
                if(Global.machineNo[Global.channel] == v1) {
                    this.effectGift(15);
                    this.effectSpin(15);
                    this.effectNo(15);
                    this.effectMoney(15);
                }
                else {
                    this.effectMoney(15);
                    goto label_110;
                label_57:
                    Global.reserve[0] = v6;
                    Global.machineNo[0] = v3;
                    Global.gift[0] = v2;
                    Global.spin[0] = v5;
                    Global.money = v4;
                    goto label_70;
                }
            }

        label_110:
            Global.reserve[1] = v6;
            Global.machineNo[1] = v3;
            Global.gift[1] = v2;
            Global.spin[1] = v5;
            Global.money = v4;
        label_70:
            this.updateLabel();
            this.showReserve();
        }
        catch(Exception v0) {
        label_78:
            Util.sysout("ERROR", "GameScreen->fn_confirm_reserve(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_end_scenario_failure(String[] aMsg) {  // has try-catch handlers
        try {
            if(!Global.bTerminating) {
                goto label_3;
            }

            return;
        label_3:
            GameScreen.exitApp(String.valueOf(aMsg[1]) + "\n?????????? ??????????.");
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_end_scenario_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?????? ?????? ????????????(CS).\n?????????? ??????????.");
        }
    }

    private void fn_end_scenario_success(String[] aMsg) {  // has try-catch handlers
        int v1;
        try {
            if(!Global.bTerminating) {
                goto label_3;
            }

            return;
        label_3:
            if(Global.id.equals(aMsg[1])) {
                goto label_25;
            }

            Util.sysout("ERROR", "GameScreen->fn_end_scenario_success(1)", "Invalid Id.", Util.toString(aMsg));
            GameScreen.exitApp("?????????? ?????? ?? ????????(CS1).\n?????????? ??????????.");
            return;
        }
        catch(Exception v0) {
            goto label_21;
        }

    label_25:
        int v6 = 2;
        try {
            int v4 = Util.strToint(aMsg[v6], 0);
            int v5 = Util.strToint(aMsg[3], -1);
            int v2 = Util.strToint(aMsg[4], -1);
            int v3 = Util.strToint(aMsg[5], -1);
            if(v4 > 0 && v5 >= 0 && v2 >= 0 && v3 >= 0) {
                goto label_53;
            }

            Util.sysout("ERROR", "GameScreen->fn_end_scenario_success(2)", "Invalid Msg.", Util.toString(aMsg));
            GameScreen.exitApp("?????????? ?????? ?? ????????(CS2).\n?????????? ??????????.");
            return;
        label_53:
            if(Global.machineNo[0] != v4 || v4 <= 0) {
                if(Global.machineNo[1] == v4 && v4 > 0) {
                    v1 = 1;
                    Global.spin[1] = v5;
                    Global.gift[1] = v2;
                    goto label_66;
                }

                Util.sysout("ERROR", "GameScreen->fn_end_scenario_success(3)", "Invalid MachineNo", Util.toString(aMsg));
                GameScreen.exitApp("?????????? ???????? ?? ?????? ????????????(CS3).\n?????????? ??????????.");
                return;
            }
            else {
                v1 = 0;
                Global.spin[0] = v5;
                Global.gift[0] = v2;
            }

        label_66:
            this.setGage(v1, aMsg[6]);
            if(v3 > 0 && Global.channel == v1) {
                this.effectGift(15);
            }

            this.bEnding[v1] = false;
            if(Global.spin[v1] <= 0 && Global.reserve[v1] <= 0 && Global.machineNo[v1] > 0) {
                GameScreen.socketWrite("G080\t" + Global.id + "\t" + String.valueOf(v1) + "\t" + String.valueOf(Global.machineNo[v1]) + "\t" + "1" + "\t" + "180");
                if(v1 == Global.channel) {
                    this.effectSpin(15);
                }
            }

            this.updateLabel();
            this.showReserve();
            this.fNextScenarioDelay[v1] = 0.3f;
        }
        catch(Exception v0) {
        label_21:
            Util.sysout("ERROR", "GameScreen->fn_end_scenario_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???????? ?? ?????? ????????????(CS).\n?????????? ??????????.");
        }
    }

    private void fn_exchange_completed(String[] aMsg) {  // has try-catch handlers
        try {
            String v4 = aMsg[1];
            int v1 = Util.strToint(aMsg[2], -1);
            int v2 = Util.strToint(aMsg[3], -1);
            if((Global.id.equals(v4)) && v1 >= 0 && v2 >= 0) {
                goto label_22;
            }

            Util.sysout("ERROR", "GameScreen->fn_exchange_completed(1)", "????????????????", Util.toString(aMsg));
            return;
        label_22:
            int v3 = Util.strToint(aMsg[4], -1);
            Global.money = v2;
            this.updateLabel();
            if(v3 == 1) {
                this.showToast("?????????? ??????????????.", 1.5f);
            }
            else {
                this.showToast("?????????? ??????????????.", 1.5f);
            }

            this.effectMoney(15);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_exchange_completed", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_game_info(String[] aMsg) {  // has try-catch handlers
        int v1 = 3;
        try {
            Global.mode = v1;
            Global.money = Integer.parseInt(aMsg[2]);
            Global.channel = Integer.parseInt(aMsg[3]);
            Global.machineNo[0] = Integer.parseInt(aMsg[4]);
            Global.gift[0] = Integer.parseInt(aMsg[5]);
            Global.spin[0] = Integer.parseInt(aMsg[6]);
            Global.machineNo[1] = Integer.parseInt(aMsg[7]);
            Global.gift[1] = Integer.parseInt(aMsg[8]);
            Global.spin[1] = Integer.parseInt(aMsg[9]);
            Global.cls = aMsg[10];
            Global.reserve[0] = Integer.parseInt(aMsg[11]);
            Global.reserve[1] = Integer.parseInt(aMsg[12]);
            this.setGage(0, aMsg[13]);
            this.setGage(1, aMsg[14]);
            this.getCardPos(0, aMsg[15]);
            this.getCardPos(1, aMsg[16]);
            Global.errorReport = aMsg[17];
            Global.bank = aMsg[18];
            Global.owner = aMsg[19];
            Global.account = aMsg[20];
            this.lbChargeBank.setText(Global.bank);
            this.lbChargeOwner.setText(Global.owner);
            this.lbChargeAccount.setText(Global.account);
            Global.viewScenario = aMsg[21];
            if(Global.channel == 0) {
                if(Global.machineNo[1] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }
            else if(Global.channel == 1) {
                if(Global.machineNo[0] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }

            this.bGameScreenReady = true;
            this.bCanInsert10000 = true;
            this.bCanSwitchMoney = true;
            this.bCanSwitchMachine = true;
            this.bCanUpDownChannel = true;
            this.bCanSelectMachine = true;
            this.bCanReserveMachine = true;
            this.bSwitching = false;
            this.bEnding[0] = false;
            this.bEnding[1] = false;
            this.bWaitingScenario[0] = false;
            this.bWaitingScenario[1] = false;
            this.bCanRequestScenario[0] = true;
            this.bCanRequestScenario[1] = true;
            this.fNextScenarioDelay[0] = 4f;
            this.fNextScenarioDelay[1] = 4f;
            this.fEndScenarioDelay[0] = 1000000000f;
            this.fEndScenarioDelay[1] = 1000000000f;
            this.updateLabel();
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_game_info(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???????? ?? ?????? ????????????(CI).\n?????????? ??????????.");
        }
    }

    private void fn_game_info_failure(String[] aMsg) {  // has try-catch handlers
        try {
            GameScreen.exitApp(String.valueOf(aMsg[1]) + "\n?????????? ??????????.");
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_game_info_failure", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???????? ?? ?????? ????????????(2).\n?????????? ??????????.");
        }
    }

    private void fn_insert_10000_failure(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanInsert10000 = true;
            this.showToast(aMsg[1], 2f);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_insert_10000_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CM).\n?????????? ??????????.");
        }
    }

    private void fn_insert_10000_success(String[] aMsg) {  // has try-catch handlers
        int v1;
        try {
            this.bCanInsert10000 = true;
            int v2 = Util.strToint(aMsg[1], 0);
            if(v2 <= 0 || v2 != Global.machineNo[0]) {
                if(v2 > 0 && v2 == Global.machineNo[1]) {
                    Global.gift[1] = Integer.parseInt(aMsg[8]);
                    Global.spin[1] = Integer.parseInt(aMsg[9]);
                    v1 = 1;
                    goto label_25;
                }

                Util.sysout("ERROR", "GameScreen->fn_insert_10000_success(1)", "????????????", Util.toString(aMsg));
                GameScreen.exitApp("???????? ?? ?????? ????????????(CM1).\n?????????? ??????????.");
                return;
            }
            else {
                Global.gift[0] = Integer.parseInt(aMsg[5]);
                Global.spin[0] = Integer.parseInt(aMsg[6]);
                v1 = 0;
            }

        label_25:
            Global.money = Integer.parseInt(aMsg[2]);
            this.showToast("[" + String.valueOf(v2) + "] ?? ?????? ?????? ??????????????.", 1.5f);
            this.updateLabel();
            this.effectSpin(15);
            if(Global.reserve[v1] <= 0) {
                return;
            }

            if(Global.channel != v1) {
                return;
            }

            if(this.bSwitching) {
                return;
            }

            if(!this.bCanReserveMachine) {
                return;
            }

            if(GameScreen.bReserving) {
                return;
            }

            this.bCanReserveMachine = false;
            GameScreen.bReserving = true;
            GameScreen.socketWrite("G080\t" + Global.id + "\t" + String.valueOf(v1) + "\t" + String.valueOf(v2) + "\t" + "0" + "\t" + "0");
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_insert_10000_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CM).\n?????????? ??????????.");
        }
    }

    private void fn_machine_list(String[] aMsg) {  // has try-catch handlers
        String[] v0;
        int v5 = 3;
        int v8 = 16;
        try {
            this.bCanUpDownChannel = true;
            if(aMsg.length >= v5) {
                goto label_15;
            }

            Util.sysout("ERROR", "GameScreen->fn_machine_list", "???? ?????? ?????? ???? ?? ????????.", Util.toString(aMsg));
            this.showMessage("???? ?????? ???????? ??????????(CM1).");
            return;
        label_15:
            int v3;
            for(v3 = 0; v3 < v8; ++v3) {
                this.iMachineNo[v3] = 0;
                this.iMachineState[v3] = 0;
                this.iMachineYesterday[v3] = 0;
                this.iMachineToday[v3] = 0;
            }

            Global.division = Integer.valueOf(aMsg[1]).intValue();
            v0 = aMsg[2].split(":");
            v3 = 0;
            while(true) {
            label_27:
                if(v3 < v8) {
                    goto label_80;
                }

                goto label_28;
            }
        }
        catch(Exception v2) {
            goto label_119;
        }

        try {
        label_80:
            String[] v1 = v0[v3].split("/");
            this.iMachineNo[v3] = Integer.parseInt(v1[0]);
            this.iMachineState[v3] = Integer.parseInt(v1[1]);
            this.iMachineYesterday[v3] = Integer.parseInt(v1[2]);
            this.iMachineToday[v3] = Integer.parseInt(v1[3]);
            ++v3;
            goto label_27;
        }
        catch(Exception v2) {
            goto label_28;
        }

        try {
        label_28:
            for(v3 = 0; v3 < v8; ++v3) {
                if(this.iMachineNo[v3] == 0) {
                    this.lbMachineNo[v3].setText("");
                }
                else {
                    this.lbMachineNo[v3].setText(String.valueOf(this.iMachineNo[v3]));
                }

                if(this.iMachineNo[v3] > 0) {
                    if(this.iMachineNo[v3] != Global.machineNo[0] && this.iMachineNo[v3] != Global.machineNo[1]) {
                        goto label_123;
                    }

                    Res.machineBack[v3].setImage("3");
                }
                else {
                label_123:
                    Res.machineBack[v3].setImage(String.valueOf(this.iMachineState[v3]));
                }

                if(this.iMachineState[v3] == 1) {
                    Res.machineScreen[v3].play(true, "1");
                }
                else {
                    Res.machineScreen[v3].play(true, "0");
                }
            }

            return;
        }
        catch(Exception v2) {
        label_119:
            Util.sysout("ERROR", "GameScreen->fn_machine_list", Util.getExceptionMessage(v2), Util.toString(aMsg));
            this.showMessage("???? ?????? ???????? ??????????(CM).");
            return;
        }
    }

    private void fn_machine_select(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanSelectMachine = true;
            int v1 = Integer.parseInt(aMsg[1]);
            Global.machineNo[v1] = Integer.parseInt(aMsg[2]);
            Global.gift[v1] = Integer.parseInt(aMsg[3]);
            Global.spin[v1] = Integer.parseInt(aMsg[4]);
            Global.reserve[v1] = Integer.parseInt(aMsg[5]);
            this.setGage(v1, aMsg[6]);
            this.getCardPos(v1, aMsg[7]);
            this.lsScenario[v1].clear();
            if(Global.channel == 0) {
                if(Global.machineNo[1] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }
            else if(Global.channel == 1) {
                if(Global.machineNo[0] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }

            if(v1 == Global.channel) {
                this.effectNo(15);
            }

            this.updateLabel();
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_machine_select", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CM).\n?????????? ??????????.");
        }
    }

    private void fn_machine_select_failure(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanSelectMachine = true;
            this.showMessage(aMsg[1]);
            this.updateLabel();
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_machine_select", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CM).\n?????????? ??????????.");
        }
    }

    private void fn_private_announce(String[] aMsg) {  // has try-catch handlers
        try {
            String v2 = aMsg[1];
            String v1 = aMsg[2];
            String v3 = aMsg[3];
            if(Global.id.equals(v2)) {
                goto label_15;
            }

            Util.sysout("ERROR", "GameScreen->fn_private_announce(1)", "Invalid Id", Util.toString(aMsg));
            return;
        label_15:
            if(!"M".equals(v1)) {
                goto label_27;
            }

            this.showMessage(v3);
            return;
        label_27:
            if(!"T".equals(v1)) {
                goto label_33;
            }

            this.showToast(v3, 3f);
            return;
        label_33:
            if(!"C".equals(v1)) {
                goto label_39;
            }

            this.addMsg(v3, Color.GREEN);
            return;
        label_39:
            Util.sysout("ERROR", "GameScreen->fn_private_announce(1)", "Invalid Announce Flag", Util.toString(aMsg));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_private_announce(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_report(String[] aMsg) {  // has try-catch handlers
        try {
            String v1 = "";
            if(this.lsScenario[0].size() > 0) {
                v1 = String.valueOf(v1) + "lsScenario[0]=" + this.lsScenario[0].get(0);
            }

            if(this.lsScenario[1].size() > 0) {
                v1 = String.valueOf(v1) + "lsScenario[1]=" + this.lsScenario[1].get(0);
            }

            GameScreen.socketWrite("G001\t" + (String.valueOf(v1) + "/Global.machineNo[]=" + String.valueOf(Global.machineNo[0]) + "/" + String.valueOf(Global.machineNo[1]) + ":" + "/Global.spin[]=" + String.valueOf(Global.spin[0]) + "/" + String.valueOf(Global.spin[1]) + ":" + "/fNextScenarioDelay[]=" + String.valueOf(this.fNextScenarioDelay[0]) + "/" + String.valueOf(this.fNextScenarioDelay[1]) + ":" + "/isReelRunning()=" + String.valueOf(this.isReelRunning(0)) + "/" + String.valueOf(this.isReelRunning(1)) + ":" + "/Global.reserve[]=" + String.valueOf(Global.reserve[0]) + "/" + String.valueOf(Global.reserve[1]) + ":" + "/bVideo1Playing[]=" + String.valueOf(this.bVideo1Playing[0]) + "/" + String.valueOf(this.bVideo1Playing[1]) + ":" + "/bVideo2Playing[]=" + String.valueOf(this.bVideo2Playing[0]) + "/" + String.valueOf(this.bVideo2Playing[1]) + ":" + "/bVideo3Playing[]=" + String.valueOf(this.bVideo3Playing[0]) + "/" + String.valueOf(this.bVideo3Playing[1]) + ":" + "/Global.bAskingGameExit=" + String.valueOf(Global.bAskingGameExit) + ":" + "/Global.bTerminating=" + String.valueOf(Global.bTerminating) + ":" + "/Global.bTerminating=" + String.valueOf(Global.bTerminating) + ":" + "/bCannonCelemony=" + String.valueOf(GameScreen.bCannonCelemony) + ":" + "/bEnding[iChannel]=" + String.valueOf(this.bEnding[0]) + "/" + String.valueOf(this.bEnding[1]) + ":" + "/bWaitingScenario[iChannel]=" + String.valueOf(this.bWaitingScenario[0]) + "/" + String.valueOf(this.bWaitingScenario[1]) + ":" + "/bStopScenarioForTest=" + String.valueOf(this.bStopScenarioForTest) + ":" + "/iFireType[]=" + String.valueOf(this.iFireType[0]) + "/" + String.valueOf(this.iFireType[1]) + ":" + "/bReserving=" + String.valueOf(GameScreen.bReserving) + ":" + "/bSwitching=" + String.valueOf(this.bSwitching) + ":" + "/bPlayVideo2[]=" + String.valueOf(this.bPlayVideo2[0]) + "/" + String.valueOf(this.bPlayVideo2[1]) + ":" + "/sVideo2Raw[_iChannel]=" + String.valueOf(this.sVideo2Raw[0]) + "/" + String.valueOf(this.sVideo2Raw[1]) + ":" + "/ reelMode[_iChannel]=" + String.valueOf(this.reelMode[0]) + "/" + String.valueOf(this.reelMode[1]) + ":" + "/fEndScenarioDelay[]=" + String.valueOf(this.fEndScenarioDelay[0]) + "/" + String.valueOf(this.fEndScenarioDelay[1]) + ":" + "/fPoppingDelay[]=" + String.valueOf(this.fPoppingDelay[0]) + "/" + String.valueOf(this.fPoppingDelay[1]) + ":" + "/iMaxPopCount[][0]=" + String.valueOf(this.iMaxPopCount[0][0]) + "/" + String.valueOf(this.iMaxPopCount[1][0]) + ":" + "/iMaxPopCount[][1]=" + String.valueOf(this.iMaxPopCount[0][1]) + "/" + String.valueOf(this.iMaxPopCount[1][1]) + ":" + "/iMaxPopCount[][2]=" + String.valueOf(this.iMaxPopCount[0][2]) + "/" + String.valueOf(this.iMaxPopCount[1][2]) + ":" + "/iPopCount[][0]=" + String.valueOf(this.iPopCount[0][0]) + "/" + String.valueOf(this.iPopCount[1][0]) + ":" + "/iPopCount[][1]=" + String.valueOf(this.iPopCount[0][1]) + "/" + String.valueOf(this.iPopCount[1][1]) + ":" + "/iPopCount[][2]=" + String.valueOf(this.iPopCount[0][2]) + "/" + String.valueOf(this.iPopCount[1][2]) + ":" + "/fVideo1StartTime[]=" + String.valueOf(this.fVideo1StartTime[0]) + "/" + String.valueOf(this.fVideo1StartTime[1]) + ":" + "/sVideo1Raw[]=" + String.valueOf(this.sVideo1Raw[0]) + "/" + String.valueOf(this.sVideo1Raw[1]) + ":" + "/fVideo2StartTime[]=" + String.valueOf(this.fVideo2StartTime[0]) + "/" + String.valueOf(this.fVideo2StartTime[1]) + ":" + "/fReelDeceleratingStartTime[]=" + String.valueOf(this.fReelDeceleratingStartTime[0][0]) + "/" + String.valueOf(this.fReelDeceleratingStartTime[1][0]) + ":" + "/fReelDeceleratingStartTime[]=" + String.valueOf(this.fReelDeceleratingStartTime[0][1]) + "/" + String.valueOf(this.fReelDeceleratingStartTime[1][1]) + ":" + "/fReelDeceleratingStartTime[]=" + String.valueOf(this.fReelDeceleratingStartTime[0][2]) + "/" + String.valueOf(this.fReelDeceleratingStartTime[1][2]) + ":" + "/fReelPoppingStartTime[]=" + String.valueOf(this.fReelPoppingStartTime[0][0]) + "/" + String.valueOf(this.fReelPoppingStartTime[1][0]) + ":" + "/fReelPoppingStartTime[]=" + String.valueOf(this.fReelPoppingStartTime[0][1]) + "/" + String.valueOf(this.fReelPoppingStartTime[1][1]) + ":" + "/fReelPoppingStartTime[]=" + String.valueOf(this.fReelPoppingStartTime[0][2]) + "/" + String.valueOf(this.fReelPoppingStartTime[1][2]) + ":" + "/fTotalReelElapseTime[]=" + String.valueOf(GameScreen.fTotalReelElapseTime[0]) + "/" + String.valueOf(GameScreen.fTotalReelElapseTime[1]) + ":" + "/fStartReelDelayTime[][0]=" + String.valueOf(this.fStartReelDelayTime[0][0]) + "/" + String.valueOf(this.fStartReelDelayTime[1][0]) + ":" + "/fStartReelDelayTime[][1]=" + String.valueOf(this.fStartReelDelayTime[0][1]) + "/" + String.valueOf(this.fStartReelDelayTime[1][1]) + ":" + "/fStartReelDelayTime[][2]=" + String.valueOf(this.fStartReelDelayTime[0][2]) + "/" + String.valueOf(this.fStartReelDelayTime[1][2]) + ":" + "/fSecondAccelerationTime[]=" + String.valueOf(this.fSecondAccelerationTime[0]) + "/" + String.valueOf(this.fSecondAccelerationTime[1]) + ":" + "/fTotalReelElapseTime[]=" + String.valueOf(GameScreen.fTotalReelElapseTime[0]) + "/" + String.valueOf(GameScreen.fTotalReelElapseTime[1]) + ":" + "/fTotalReelElapseTime[]=" + String.valueOf(GameScreen.fTotalReelElapseTime[0]) + "/" + String.valueOf(GameScreen.fTotalReelElapseTime[1]) + ":" + "/fTotalReelElapseTime[]=" + String.valueOf(GameScreen.fTotalReelElapseTime[0]) + "/" + String.valueOf(GameScreen.fTotalReelElapseTime[1]) + ":" + "/fTotalReelElapseTime[]=" + String.valueOf(GameScreen.fTotalReelElapseTime[0]) + "/" + String.valueOf(GameScreen.fTotalReelElapseTime[1])));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_report(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_reserve_machine_failure(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanReserveMachine = true;
            GameScreen.bReserving = false;
            this.showToast(aMsg[1], 2f);
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_reserve_machine_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_reserve_machine_success(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanReserveMachine = true;
            GameScreen.bReserving = false;
            if(!Global.id.equals(aMsg[1])) {
                this.showToast("???? ???????? ???????? ????????.", 2f);
            }

            if(Global.machineNo[0] != Integer.parseInt(aMsg[3]) || Global.machineNo[1] != Integer.parseInt(aMsg[4])) {
                this.showToast("???? ?????????? ???????? ????????.", 2f);
            }

            Global.reserve[0] = Integer.parseInt(aMsg[5]);
            Global.reserve[1] = Integer.parseInt(aMsg[6]);
            if(Util.strToint(aMsg[8], 0) <= 0) {
                if("1".equals(aMsg[7])) {
                    this.showToast("?????? ??????????????.", 1f);
                }
                else if("0".equals(aMsg[7])) {
                    this.showToast("???? ?????? ??????????????.", 1f);
                }
            }

            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_reserve_machine_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_scenario1(String[] aMsg) {  // has try-catch handlers
        try {
            this.lsScenario[0].clear();
            if(Integer.parseInt(aMsg[1]) > 0) {
                goto label_9;
            }

            return;
        label_9:
            if(aMsg.length < 3) {
                return;
            }

            String[] v3 = aMsg[2].split("!");
            int v1;
            for(v1 = 0; v1 < v3.length; ++v1) {
                this.lsScenario[0].add(v3[v1]);
            }

            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_scenario1(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            return;
        }
    }

    private void fn_scenario2(String[] aMsg) {  // has try-catch handlers
        try {
            this.lsScenario[1].clear();
            if(Integer.parseInt(aMsg[1]) > 0) {
                goto label_9;
            }

            return;
        label_9:
            if(aMsg.length < 3) {
                return;
            }

            String[] v3 = aMsg[2].split("!");
            int v1;
            for(v1 = 0; v1 < v3.length; ++v1) {
                this.lsScenario[1].add(v3[v1]);
            }

            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_scenario2(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            return;
        }
    }

    private void fn_scenario_failure(String[] aMsg) {  // has try-catch handlers
        try {
            GameScreen.exitApp(String.valueOf(aMsg[1]) + "\n?????????? ??????????.");
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_scenario_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???????? ?? ?????? ????????????(CS).\n?????????? ??????????.");
        }
    }

    private void fn_scenario_success(String[] aMsg) {  // has try-catch handlers
        int v2;
        try {
            v2 = Integer.parseInt(aMsg[1]);
            int v3 = Integer.parseInt(aMsg[2]);
            if(v2 != 0 && v2 != 1) {
                Util.sysout("ERROR", "GameScreen->fn_scenario_success(1)", "Invalid Channel.", Util.toString(aMsg));
                GameScreen.exitApp("?????????? ???? ?? ?? ????????(CS1).\n?????????? ??????????.");
                return;
            }

            if(Global.machineNo[v2] == v3) {
                goto label_37;
            }

            Util.sysout("ERROR", "GameScreen->fn_scenario_success(2)", "Invalid MachineNo.", Util.toString(aMsg));
            GameScreen.exitApp("?????????? ???? ?? ?? ????????(CS2).\n?????????? ??????????.");
            return;
        }
        catch(Exception v0) {
            goto label_33;
        }

    label_37:
        int v6 = 3;
        try {
            String v5 = aMsg[v6];
            if(!"".equals(v5) && v5 != null) {
                goto label_51;
            }

            Util.sysout("ERROR", "GameScreen->fn_scenario_success(3)", "Invalid MachineNo.", Util.toString(aMsg));
            GameScreen.exitApp("???????? ?????? ?????? ????????????(CS3).\n?????????? ??????????.");
            return;
        label_51:
            String[] v4 = v5.split("!");
            int v1;
            for(v1 = 0; v1 < v4.length; ++v1) {
                this.lsScenario[v2].add(v4[v1]);
            }

            this.bCanRequestScenario[v2] = true;
            this.bWaitingScenario[v2] = false;
            return;
        }
        catch(Exception v0) {
        label_33:
            Util.sysout("ERROR", "GameScreen->fn_scenario_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?????? ?????? ????????????(CS).\n?????????? ??????????.");
            return;
        }
    }

    private void fn_switch_machine_failure(String[] aMsg) {  // has try-catch handlers
        try {
            GameScreen.exitApp(String.valueOf(aMsg[1]) + "\n?????????? ??????????.");
            this.showReserve();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_switch_machine_failure(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CV).\n?????????? ??????????.");
        }
    }

    private void fn_switch_machine_success(String[] aMsg) {  // has try-catch handlers
        try {
            Global.money = Integer.parseInt(aMsg[1]);
            Global.channel = Integer.parseInt(aMsg[2]);
            Global.machineNo[0] = Integer.parseInt(aMsg[3]);
            Global.gift[0] = Integer.parseInt(aMsg[4]);
            Global.spin[0] = Integer.parseInt(aMsg[5]);
            Global.machineNo[1] = Integer.parseInt(aMsg[6]);
            Global.gift[1] = Integer.parseInt(aMsg[7]);
            Global.spin[1] = Integer.parseInt(aMsg[8]);
            Global.reserve[0] = Integer.parseInt(aMsg[9]);
            Global.reserve[1] = Integer.parseInt(aMsg[10]);
            this.setGage(0, aMsg[11]);
            this.setGage(1, aMsg[12]);
            this.getCardPos(0, aMsg[13]);
            this.getCardPos(1, aMsg[14]);
            if(Global.channel == 0) {
                if(Global.machineNo[1] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }
            else if(Global.channel == 1) {
                if(Global.machineNo[0] > 0) {
                    Res.aniMonitor.play(true, "STOP");
                }
                else {
                    Res.aniMonitor.play(true, "IDLE");
                }
            }

            this.bCanSwitchMachine = true;
            this.bSwitching = false;
            this.fNextScenarioDelay[0] = 1.2f;
            this.fNextScenarioDelay[1] = 1.2f;
            this.fEndScenarioDelay[0] = 1000000000f;
            this.fEndScenarioDelay[1] = 1000000000f;
            this.bEnding[0] = false;
            this.bEnding[1] = false;
            this.bWaitingScenario[0] = false;
            this.bWaitingScenario[1] = false;
            GameScreen.bCannonCelemony = false;
            GameScreen.fCelemony = 0f;
            Res.sndCelemony.stop();
            if(Global.machineNo[0] > 0 && Global.spin[0] <= 0 && Global.reserve[0] <= 0) {
                GameScreen.socketWrite("G080\t" + Global.id + "\t" + "0" + "\t" + String.valueOf(Global.machineNo[0]) + "\t" + "1" + "\t" + "180");
                if(Global.channel == 0) {
                    this.effectSpin(15);
                }
            }

            if(Global.machineNo[1] > 0 && Global.spin[1] <= 0 && Global.reserve[1] <= 0) {
                GameScreen.socketWrite("G080\t" + Global.id + "\t" + "1" + "\t" + String.valueOf(Global.machineNo[1]) + "\t" + "1" + "\t" + "180");
                if(Global.channel == 1) {
                    this.effectSpin(15);
                }
            }

            this.effectNo(15);
            this.effectChannel(15);
            this.updateLabel();
            this.showReserve();
            GameScreen.bHideCannon = true;
            GameScreen.bShowCannon = true;
            Res.sndAlram.play();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_switch_machine_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CV).\n?????????? ??????????.");
        }
    }

    private void fn_switch_money_failure(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanSwitchMoney = true;
            this.showToast(aMsg[1], 2f);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_switch_money_failure", Util.getExceptionMessage(v0), Util.toString(aMsg));
        }
    }

    private void fn_switch_money_success(String[] aMsg) {  // has try-catch handlers
        try {
            this.bCanSwitchMoney = true;
            int v1 = Util.strToint(aMsg[2], -1);
            Global.money = Integer.parseInt(aMsg[3]);
            if(v1 != 0 || Global.channel != 0) {
                if(v1 == 1 && Global.channel == 1) {
                    Global.gift[1] = Integer.parseInt(aMsg[9]);
                    goto label_20;
                }

                Util.sysout("ERROR", "GameScreen->fn_switch_money_success(1)", "????????????", Util.toString(aMsg));
                GameScreen.exitApp("???????? ?? ?????? ????????????(CW1).\n?????????? ??????????.");
            }
            else {
                Global.gift[0] = Integer.parseInt(aMsg[6]);
            }

        label_20:
            int v2 = Util.strToint(aMsg[1], 0);
            if(v2 > 0) {
                this.showToast("[" + v2 + "] ?? ?????? ???????? ??????????????.", 2f);
            }
            else {
                this.showToast("???????? ?????????? ??????????????.", 2f);
            }

            this.effectGift(15);
            this.effectMoney(15);
            this.updateLabel();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->fn_switch_money_success(0)", Util.getExceptionMessage(v0), Util.toString(aMsg));
            GameScreen.exitApp("???????? ?? ?????? ????????????(CW).\n?????????? ??????????.");
        }
    }

    private int getCardIdxOnReachLine(int _iChannel, int iReelIndex, int iTCBX) {
        float v0;
        float v11 = 2f;
        int v10 = 3;
        int v9 = 2;
        int v2 = 0;
        if(this.bReelCenter[_iChannel][iReelIndex]) {
            if(iTCBX == 1) {
                v0 = Res.fBaseLineY3 + (Res.fBaseLineY3 - Res.fBaseLineY2) / v11;
            }
            else if(iTCBX == v9) {
                v0 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v11;
            }
            else {
                v0 = Res.fBaseLineY2 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v11;
            }
        }
        else if(iTCBX == 1) {
            v0 = Res.fBaseLineY3;
        }
        else if(iTCBX == v9) {
            v0 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v11;
        }
        else {
            v0 = Res.fBaseLineY2;
        }

        float[] v1 = new float[4];
        v1[0] = Math.abs(this.card[_iChannel][iReelIndex][0].y - v0);
        v1[1] = Math.abs(this.card[_iChannel][iReelIndex][1].y - v0);
        v1[v9] = Math.abs(this.card[_iChannel][iReelIndex][v9].y - v0);
        v1[v10] = Math.abs(this.card[_iChannel][iReelIndex][v10].y - v0);
        float[] v3 = new float[4];
        v3[0] = v1[0];
        v3[1] = v1[1];
        v3[v9] = v1[v9];
        v3[v10] = v1[v10];
        Arrays.sort(v1);
        if(v3[0] == v1[0]) {
            v2 = 0;
        }
        else if(v3[1] == v1[0]) {
            v2 = 1;
        }
        else if(v3[v9] == v1[0]) {
            v2 = 2;
        }
        else if(v3[v10] == v1[0]) {
            v2 = 3;
        }
        else {
            Util.sysout("ERROR", "GameScreen->getCardIdxOnReachLine", "Can\'t find the card");
        }

        return v2;
    }

    private int getCardNo(int _iNo) {
        while(_iNo < 0) {
            _iNo += 8;
        }

        _iNo %= 8;
        if(_iNo == 0) {
            _iNo = 8;
        }

        return _iNo;
    }

    private void getCardPos(int iChannel, String sPos) {  // has try-catch handlers
        int v3;
        int v10 = 3;
        if(sPos == null) {
            sPos = "";
        }

        sPos = sPos.trim();
        Global.cardPos[iChannel] = sPos;
        if("".equals(sPos)) {
            return;
        }

        if(sPos.length() == 6) {
            goto label_14;
        }

        return;
    label_14:
        String[] v5 = new String[v10];
        String[] v4 = new String[v10];
        int[] v2 = new int[v10];
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 < v10) {
                goto label_84;
            }

            goto label_19;
        label_84:
            int v6 = v1 * 2;
            int v7 = v1 * 2 + 1;
            try {
                v5[v1] = sPos.substring(v6, v7);
                v4[v1] = sPos.substring(v1 * 2 + 1, v1 * 2 + 2);
                v2[v1] = Integer.parseInt(v4[v1]);
                if(v2[v1] >= 1 && v2[v1] <= 8) {
                    goto label_108;
                }

                goto label_103;
            }
            catch(Exception v0) {
                goto label_82;
            }

        label_108:
        }

        try {
        label_103:
            Util.sysout("ERROR", "GameScreen->getCardPos(1)", "Invalid target pos.", sPos);
            return;
        label_19:
            if((("T".equals(sPos.subSequence(0, 1))) || ("C".equals(sPos.subSequence(0, 1))) || ("B".equals(sPos.subSequence(0, 1)))) && (("T".equals(sPos.subSequence(2, 3))) || ("C".equals(sPos.subSequence(2, 3))) || ("B".equals(sPos.subSequence(2, 3)))) && (("T".equals(sPos.subSequence(4, 5))) || ("C".equals(sPos.subSequence(4, 5))) || ("B".equals(sPos.subSequence(4, 5))))) {
                goto label_110;
            }

            Util.sysout("ERROR", "GameScreen->getCardPos(2)", "Invalid target pos.", sPos);
            return;
        }
        catch(Exception v0) {
        label_82:
            Util.sysout("ERROR", "GameScreen->getCardPos(3)", "Invalid target pos.", sPos);
            return;
        }

        try {
        label_110:
            this.card[iChannel][0][2].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY4);
            this.card[iChannel][0][1].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY3);
            this.card[iChannel][0][0].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY2);
            this.card[iChannel][0][3].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY1);
            this.card[iChannel][1][2].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY4);
            this.card[iChannel][1][1].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY3);
            this.card[iChannel][1][0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY2);
            this.card[iChannel][1][3].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY1);
            this.card[iChannel][2][2].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY4);
            this.card[iChannel][2][1].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY3);
            this.card[iChannel][2][0].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY2);
            this.card[iChannel][2][3].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY1);
            v3 = 0;
            while(true) {
            label_243:
                if(v3 >= v10) {
                    return;
                }

                if(!"B".equals(v5[v3])) {
                    break;
                }
                else if(v3 == 0) {
                    this.iNextCardNo[iChannel][v3] = v2[v3] - 3;
                    this.card[iChannel][v3][2].setNo(v2[v3] - 2);
                    this.card[iChannel][v3][1].setNo(v2[v3] - 1);
                    this.card[iChannel][v3][0].setNo(v2[v3]);
                    this.card[iChannel][v3][3].setNo(v2[v3] + 1);
                }
                else {
                    this.iNextCardNo[iChannel][v3] = v2[v3] + 3;
                    this.card[iChannel][v3][2].setNo(v2[v3] + 2);
                    this.card[iChannel][v3][1].setNo(v2[v3] + 1);
                    this.card[iChannel][v3][0].setNo(v2[v3]);
                    this.card[iChannel][v3][3].setNo(v2[v3] - 1);
                }

                goto label_286;
            }
        }
        catch(Exception v0) {
            goto label_410;
        }

        if(v3 == 0) {
            try {
                this.iNextCardNo[iChannel][v3] = v2[v3] - 2;
                this.card[iChannel][v3][2].setNo(v2[v3] - 1);
                this.card[iChannel][v3][1].setNo(v2[v3]);
                this.card[iChannel][v3][0].setNo(v2[v3] + 1);
                this.card[iChannel][v3][3].setNo(v2[v3] + 2);
                goto label_286;
            label_451:
                this.iNextCardNo[iChannel][v3] = v2[v3] + 2;
                this.card[iChannel][v3][2].setNo(v2[v3] + 1);
                this.card[iChannel][v3][1].setNo(v2[v3]);
                this.card[iChannel][v3][0].setNo(v2[v3] - 1);
                this.card[iChannel][v3][3].setNo(v2[v3] - 2);
            label_286:
                if("C".equals(v5[v3])) {
                    this.card[iChannel][v3][2].y -= Util.getHeight((Res.iBaseLinePixelY3 - Res.iBaseLinePixelY4) / 2f);
                    this.card[iChannel][v3][1].y -= Util.getHeight((Res.iBaseLinePixelY3 - Res.iBaseLinePixelY4) / 2f);
                    this.card[iChannel][v3][0].y -= Util.getHeight((Res.iBaseLinePixelY3 - Res.iBaseLinePixelY4) / 2f);
                    this.card[iChannel][v3][3].y -= Util.getHeight((Res.iBaseLinePixelY3 - Res.iBaseLinePixelY4) / 2f);
                }

                ++v3;
                goto label_243;
            }
            catch(Exception v0) {
            label_410:
                Util.sysout("ERROR", "GameScreen->getCardPos(0)", Util.getExceptionMessage(v0), sPos);
                return;
            }
        }
        else {
            goto label_451;
        }

        goto label_286;
    }

    private int getExclusiveNo(int no) {
        int v0;
        do {
            v0 = Util.randomInt(1, 8);
        }
        while(no == v0);

        return v0;
    }

    private int getExclusiveNo(int no1, int no2) {
        int v0;
        do {
        label_1:
            v0 = Util.randomInt(1, 8);
            if(no1 == v0) {
                goto label_1;
            }
        }
        while(no2 == v0);

        return v0;
    }

    private int getExclusiveNo(int no1, int no2, int no3) {
        int v0;
        do {
        label_1:
            v0 = Util.randomInt(1, 8);
            if(no1 == v0) {
                goto label_1;
            }

            if(no2 == v0) {
                goto label_1;
            }
        }
        while(no3 == v0);

        return v0;
    }

    private int getNo(String sNo) {  // has try-catch handlers
        int v1 = 0;
        try {
            if(!"R".equals(sNo.substring(1, 2))) {
                goto label_11;
            }

            v1 = Util.randomInt(1, 8);
            goto label_10;
        label_11:
            v1 = Integer.parseInt(sNo.substring(1, 2));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->getNo", Util.getExceptionMessage(v0), "sNo=" + sNo);
        }

    label_10:
        return v1;
    }

    private int getResultNoFromDestroyVideo(String sDestroyVideo) {  // has try-catch handlers
        int v1;
        try {
            v1 = Integer.parseInt(sDestroyVideo.split("_")[2]);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->getResultNoFromDestroyVideo", Util.getExceptionMessage(v0), "sDestroyVideo=" + sDestroyVideo);
        }

        return v1;
    }

    private float getStopTargetYPos(int _iChannel, int iReelIndex, float ypos) {
        int v9 = 4;
        int v8 = 3;
        int v7 = 2;
        float v1 = 0f;
        float[] v0 = new float[5];
        v0[v9] = Math.abs(ypos - Res.fBaseLineY4);
        v0[v8] = Math.abs(ypos - Res.fBaseLineY3);
        v0[v7] = Math.abs(ypos - Res.fBaseLineY2);
        v0[1] = Math.abs(ypos - Res.fBaseLineY1);
        v0[0] = Math.abs(ypos - Res.fBaseLineY0);
        float[] v2 = new float[5];
        v2[v9] = v0[v9];
        v2[v8] = v0[v8];
        v2[v7] = v0[v7];
        v2[1] = v0[1];
        v2[0] = v0[0];
        Arrays.sort(v0);
        if(v2[0] == v0[0]) {
            v1 = Res.fBaseLineY0;
        }
        else if(v2[1] == v0[0]) {
            v1 = Res.fBaseLineY1;
        }
        else if(v2[v7] == v0[0]) {
            v1 = Res.fBaseLineY2;
        }
        else if(v2[v8] == v0[0]) {
            v1 = Res.fBaseLineY3;
        }
        else if(v2[v9] == v0[0]) {
            v1 = Res.fBaseLineY4;
        }
        else {
            Util.sysout("ERROR", "GameScreen->getStopTargetYPos", "Can\'t find the closest base line.");
        }

        if(this.bReelCenter[_iChannel][iReelIndex]) {
            v1 -= (Res.fBaseLineY1 - Res.fBaseLineY0) / 2f;
        }

        return v1;
    }

    private int getTCBX(String sNo) {  // has try-catch handlers
        int v1;
        int v9 = 4;
        try {
            String v2 = sNo.substring(0, 1).toUpperCase();
            if(!"T".equals(v2)) {
                goto label_11;
            }

            v1 = 1;
            goto label_10;
        label_11:
            if(!"C".equals(v2)) {
                goto label_16;
            }

            v1 = 2;
            goto label_10;
        label_16:
            if(!"B".equals(v2)) {
                goto label_21;
            }

            v1 = 3;
            goto label_10;
        label_21:
            if(!"X".equals(v2)) {
                goto label_26;
            }

            v1 = 4;
            goto label_10;
        label_26:
            if(!"R".equals(v2)) {
                goto label_33;
            }

            v1 = Util.randomInt(1, 4);
            goto label_10;
        label_33:
            Util.sysout("ERROR", "GameScreen->getTCBX(1)", "Invalid TCBX.", "sNo=" + sNo);
            v1 = Util.randomInt(1, 4);
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->getTCBX(2)", Util.getExceptionMessage(v0), "sNo=" + sNo);
            v1 = Util.randomInt(1, v9);
        }

    label_10:
        return v1;
    }

    private String getValue(String _key, String _data) {  // has try-catch handlers
        String v3 = "";
        try {
            int v2 = _data.indexOf("@" + _key + ":");
            if(v2 < 0) {
                goto label_16;
            }

            v3 = _data.substring(v2 + 2 + _key.length(), _data.indexOf("@", v2 + 1));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->getValue", Util.getExceptionMessage(v0), "key=" + _key + "/data=" + _data);
        }

    label_16:
        return v3;
    }

    private String getVideoResId(String name) {  // has try-catch handlers
        String v2;
        try {
            String[] v0 = name.split("_");
            v2 = v0[v0.length - 1].trim();
        }
        catch(Exception v1) {
            Util.sysout("ERROR", "GameScreen->getVideoResId", String.valueOf(name) + "/" + Util.getExceptionMessage(v1));
            v2 = "m000";
        }

        return v2;
    }

    private String getVideoResId2(String name) {
        return "m198";
    }

    public void hide() {
        Res.stopSound();
    }

    private void hideVideo(int iChannel) {
        int v6 = 4;
        int v5 = 3;
        int v4 = 2;
        if((GameScreen.bHideVideo) && ((this.bVideo1Playing[iChannel]) || (this.bVideo2Playing[iChannel]) || (this.bVideo3Playing[iChannel])) && (Dialog.count > 0 || (GameScreen.bShowMenu) || this.fToastTime > 0f)) {
            GameScreen.bHideVideo = false;
            GameScreen.game.sendMsgToAndroid("hide_video");
        }

        if((GameScreen.bShowVideo) && ((this.bVideo1Playing[iChannel]) || (this.bVideo2Playing[iChannel]) || (this.bVideo3Playing[iChannel])) && Dialog.count <= 0 && !GameScreen.bShowMenu && this.fToastTime <= 0f) {
            GameScreen.bShowVideo = false;
            GameScreen.game.sendMsgToAndroid("show_video");
        }

        if((GameScreen.bShowCannon) && Dialog.count <= 0 && !GameScreen.bShowMenu && this.fToastTime <= 0f && (this.iHintCannonStep == v4 || this.iHintCannonStep == v5 || this.iHintCannonStep == v6 || this.iHintCannonStep == 5 || this.iHintCannonStep == 6)) {
            GameScreen.bShowCannon = false;
            GameScreen.game.sendMsgToAndroid("show_cannon");
        }

        if((GameScreen.bHideCannon) && (Dialog.count > 0 || (GameScreen.bShowMenu) || this.fToastTime > 0f) && (this.iHintCannonStep == v4 || this.iHintCannonStep == v5 || this.iHintCannonStep == v6 || this.iHintCannonStep == 5 || this.iHintCannonStep == 6)) {
            GameScreen.bHideCannon = false;
            GameScreen.game.sendMsgToAndroid("hide_cannon");
        }
    }

    private void ini() {
        Dialog.count = 0;
        this.fToastTime = 0f;
        this.bShowToast = false;
        Global.bTerminating = false;
        Global.bAskingGameExit = false;
        this.lsScenario[0] = new ArrayList();
        this.lsScenario[1] = new ArrayList();
        this.lsScenario[0].clear();
        this.lsScenario[1].clear();
        GameScreen.fTotalReelElapseTime[0] = 0f;
        GameScreen.fTotalReelElapseTime[1] = 0f;
        this.fSecondAccelerationTime[0] = 1000000000f;
        this.fSecondAccelerationTime[1] = 1000000000f;
        this.bSecondAcceleration[0] = false;
        this.bSecondAcceleration[1] = false;
        this.bShrinkCard[0] = false;
        this.bShrinkCard[1] = false;
        GameScreen.iReserveConfirmTick[0] = 0;
        GameScreen.iReserveConfirmTick[1] = 0;
        this.fNextScenarioDelay[0] = 1000000000f;
        this.fNextScenarioDelay[1] = 1000000000f;
        this.fEndScenarioDelay[0] = 1000000000f;
        this.fEndScenarioDelay[1] = 1000000000f;
        this.reelMode[0] = 0;
        this.reelMode[1] = 0;
        this.fsPostCardPos[0] = "";
        this.fsPostCardPos[1] = "";
        GameScreen.bHideVideo = true;
        GameScreen.bShowVideo = true;
        GameScreen.fFackVideoTick = 0f;
        GameScreen.sFackVideoParam = "";
        GameScreen.bHideCannon = true;
        GameScreen.bShowCannon = true;
        GameScreen.bShowMenu = false;
        this.fCannonRotation[0] = 0f;
        this.fCannonRotation[1] = 0f;
        this.fCannonRotation[2] = 0f;
        this.iCannonDirection[0] = 0;
        this.iCannonDirection[1] = 0;
        this.iCannonDirection[2] = 0;
        this.iFireType[0] = 0;
        this.iFireType[1] = 0;
        this.fChatStep = 2f;
        this.iHintCannonTick = 0;
        this.iHintCannonType = 0;
        this.fHintCannonElapseTime = 0f;
        this.iHintCannonStep = 0;
        this.elapseTime = 0f;
        this.bGameScreenReady = false;
        this.fbDrawRobot = false;
        this.fPushCannonDelay[0] = 1000000000f;
        this.fPushCannonDelay[1] = 1000000000f;
        this.fGlitterDelay[0] = 1000000000f;
        this.fGlitterDelay[1] = 1000000000f;
        GameScreen.fSndStopDelay = 0f;
        this.sHintCannon = "";
        this.sRobot = "";
        this.bDrawLine = false;
        this.fLineElapseTime = 0f;
        this.bLineLoop = false;
        this.bCanInsert10000 = false;
        this.bCanSwitchMoney = false;
        this.bCanSwitchMachine = false;
        this.bCanUpDownChannel = false;
        this.bCanSelectMachine = false;
        this.bCanReserveMachine = false;
        GameScreen.bReserving = false;
        this.bWaitingScenario[0] = true;
        this.bWaitingScenario[1] = true;
        this.bCanRequestScenario[0] = false;
        this.bCanRequestScenario[1] = false;
        this.bSwitching = true;
        this.fIntervalMiddle = 0f;
        this.sSpace = "????";
        this.bVideo1Playing[0] = false;
        this.bVideo1Playing[1] = false;
        this.bVideo2Playing[0] = false;
        this.bVideo2Playing[1] = false;
        this.bVideo3Playing[0] = false;
        this.bVideo3Playing[1] = false;
        this.bPlayVideo1[0] = false;
        this.bPlayVideo1[1] = false;
        this.bPlayVideo2[0] = false;
        this.bPlayVideo2[1] = false;
        this.bReelRunning[0][0] = false;
        this.bReelRunning[0][1] = false;
        this.bReelRunning[0][2] = false;
        this.bReelRunning[1][0] = false;
        this.bReelRunning[1][1] = false;
        this.bReelRunning[1][2] = false;
        this.fReelDeceleratingStartTime[0][0] = 1000000000f;
        this.fReelDeceleratingStartTime[0][1] = 1000000000f;
        this.fReelDeceleratingStartTime[0][2] = 1000000000f;
        this.fReelDeceleratingStartTime[1][0] = 1000000000f;
        this.fReelDeceleratingStartTime[1][1] = 1000000000f;
        this.fReelDeceleratingStartTime[1][2] = 1000000000f;
        this.fReelPoppingStartTime[0][0] = 1000000000f;
        this.fReelPoppingStartTime[0][1] = 1000000000f;
        this.fReelPoppingStartTime[0][2] = 1000000000f;
        this.fReelPoppingStartTime[1][0] = 1000000000f;
        this.fReelPoppingStartTime[1][1] = 1000000000f;
        this.fReelPoppingStartTime[1][2] = 1000000000f;
        this.fVideo1StartTime[0] = 1000000000f;
        this.fVideo1StartTime[1] = 1000000000f;
        this.fVideo2StartTime[0] = 1000000000f;
        this.fVideo2StartTime[1] = 1000000000f;
    }

    private void iniCallback() {  // has try-catch handlers
        Class[] v1;
        try {
            v1 = new Class[1];
            v1[0] = String.class;
            goto label_5;
        }
        catch(Exception v0) {
            goto label_21;
        }

        try {
        label_5:
            this.mVideoPlayCompleted = GameScreen.class.getMethod("onVideoPlayCompleted", v1);
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->iniCallback(1)", Util.getExceptionMessage(v0), "mVideoPlayCompleted");
            }
            catch(Exception v0) {
            label_21:
                Util.sysout("ERROR", "GameScreen->iniCallback(3)", Util.getExceptionMessage(v0));
            }
        }
    }

    private void iniCards(int _iChannel) {
        int v9 = 3;
        int v8 = 2;
        this.fReelAlpha[_iChannel][0] = 1f;
        this.fReelAlpha[_iChannel][1] = 1f;
        this.fReelAlpha[_iChannel][v8] = 1f;
        if(this.card[_iChannel][0][v8] == null) {
            this.card[_iChannel][0][v8] = new Card(_iChannel, 0.18f, 8);
        }
        else {
            this.card[_iChannel][0][v8].setNo(8);
        }

        if(this.card[_iChannel][0][1] == null) {
            this.card[_iChannel][0][1] = new Card(_iChannel, 0.18f, 1);
        }
        else {
            this.card[_iChannel][0][1].setNo(1);
        }

        if(this.card[_iChannel][0][0] == null) {
            this.card[_iChannel][0][0] = new Card(_iChannel, 0.18f, v8);
        }
        else {
            this.card[_iChannel][0][0].setNo(v8);
        }

        if(this.card[_iChannel][0][v9] == null) {
            this.card[_iChannel][0][v9] = new Card(_iChannel, 0.18f, v9);
        }
        else {
            this.card[_iChannel][0][v9].setNo(v9);
        }

        if(this.card[_iChannel][1][v9] == null) {
            this.card[_iChannel][1][v8] = new Card(_iChannel, 0.18f, v8);
        }
        else {
            this.card[_iChannel][1][v8].setNo(v8);
        }

        if(this.card[_iChannel][1][v9] == null) {
            this.card[_iChannel][1][1] = new Card(_iChannel, 0.18f, 1);
        }
        else {
            this.card[_iChannel][1][1].setNo(1);
        }

        if(this.card[_iChannel][1][v9] == null) {
            this.card[_iChannel][1][0] = new Card(_iChannel, 0.18f, 8);
        }
        else {
            this.card[_iChannel][1][0].setNo(8);
        }

        if(this.card[_iChannel][1][v9] == null) {
            this.card[_iChannel][1][v9] = new Card(_iChannel, 0.18f, 7);
        }
        else {
            this.card[_iChannel][1][v9].setNo(7);
        }

        if(this.card[_iChannel][v8][v9] == null) {
            this.card[_iChannel][v8][v8] = new Card(_iChannel, 0.18f, v8);
        }
        else {
            this.card[_iChannel][v8][v8].setNo(v8);
        }

        if(this.card[_iChannel][v8][v9] == null) {
            this.card[_iChannel][v8][1] = new Card(_iChannel, 0.18f, 1);
        }
        else {
            this.card[_iChannel][v8][1].setNo(1);
        }

        if(this.card[_iChannel][v8][v9] == null) {
            this.card[_iChannel][v8][0] = new Card(_iChannel, 0.18f, 8);
        }
        else {
            this.card[_iChannel][v8][0].setNo(8);
        }

        if(this.card[_iChannel][v8][v9] == null) {
            this.card[_iChannel][v8][v9] = new Card(_iChannel, 0.18f, 7);
        }
        else {
            this.card[_iChannel][v8][v9].setNo(7);
        }

        this.card[_iChannel][0][v8].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][0][1].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][0][0].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][0][v9].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][1][v8].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][1][1].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][1][0].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][1][v9].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][v8][v8].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][v8][1].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][v8][0].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][v8][v9].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        this.card[_iChannel][0][v8].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY4);
        this.card[_iChannel][0][1].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY3);
        this.card[_iChannel][0][0].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY2);
        this.card[_iChannel][0][v9].setPosition(Util.XCenter(Res.iBaseLinePixelX1, 0f), Res.fBaseLineY1);
        this.card[_iChannel][1][v8].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY4);
        this.card[_iChannel][1][1].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY3);
        this.card[_iChannel][1][0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY2);
        this.card[_iChannel][1][v9].setPosition(Util.XCenter(Res.iBaseLinePixelX2, 0f), Res.fBaseLineY1);
        this.card[_iChannel][v8][v8].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY4);
        this.card[_iChannel][v8][1].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY3);
        this.card[_iChannel][v8][0].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY2);
        this.card[_iChannel][v8][v9].setPosition(Util.XCenter(Res.iBaseLinePixelX3, 0f), Res.fBaseLineY1);
        int v0;
        for(v0 = 0; v0 < v9; ++v0) {
            int v1;
            for(v1 = 0; v1 < 4; ++v1) {
                this.card[_iChannel][v0][v1].startY = this.card[_iChannel][v0][v1].y;
                this.card[_iChannel][v0][v1].mileage = 0f;
                this.card[_iChannel][v0][v1].setAlphaFlag(1);
                this.card[_iChannel][v0][v1].visible = true;
            }
        }

        this.iNextCardNo[_iChannel][0] = 5;
        this.iNextCardNo[_iChannel][1] = 4;
        this.iNextCardNo[_iChannel][v8] = 4;
        if(this.smallCard[_iChannel][0] == null) {
            this.smallCard[_iChannel][0] = new Card(_iChannel, 0.2f, this.card[_iChannel][0][1].no);
        }
        else {
            this.smallCard[_iChannel][0].setNo(this.card[_iChannel][0][1].no);
        }

        if(this.smallCard[_iChannel][1] == null) {
            this.smallCard[_iChannel][1] = new Card(_iChannel, 0.2f, this.card[_iChannel][v8][1].no);
        }
        else {
            this.smallCard[_iChannel][0].setNo(this.card[_iChannel][v8][1].no);
        }

        if(this.smallCard[_iChannel][v8] == null) {
            this.smallCard[_iChannel][v8] = new Card(_iChannel, 0.2f, this.card[_iChannel][0][0].no);
        }
        else {
            this.smallCard[_iChannel][0].setNo(this.card[_iChannel][0][0].no);
        }

        if(this.smallCard[_iChannel][v9] == null) {
            this.smallCard[_iChannel][v9] = new Card(_iChannel, 0.2f, this.card[_iChannel][v8][0].no);
        }
        else {
            this.smallCard[_iChannel][0].setNo(this.card[_iChannel][v8][0].no);
        }

        for(v0 = 0; v0 < 4; ++v0) {
            this.smallCard[_iChannel][v0].visible = false;
            this.smallCard[_iChannel][v0].small = false;
        }
    }

    private void iniChatBack(int iFlag) {
        float v4 = 55f;
        if(iFlag == 1) {
            Res.imgChatBack.setPosition(Util.getLeft(0f), Util.getTop(-100f, Res.imgChatBack.height));
            this.scpMsg.setPosition(Util.getActorLeft(v4), Util.getActorTop(-87f, this.scpMsg.getHeight()));
        }
        else {
            Res.imgChatBack.setPosition(Util.getLeft(0f), Util.getTop(0f, Res.imgChatBack.height));
            this.scpMsg.setPosition(Util.getActorLeft(v4), Util.getActorTop(13f, this.scpMsg.getHeight()));
        }
    }

    private void iniHelpAskMsg() {
        this.addHelp(" **** ?????? ????????????. ****", Color.ORANGE);
        this.addHelp("", Color.ORANGE);
        this.addHelp(" **** ???????? ****", Color.WHITE);
        this.addHelp(" ?????? ???? 2?????? ?????? ?? ", Color.WHITE);
        this.addHelp(" ????????.", Color.WHITE);
        this.addHelp(" ????(A ???? B)?? ???????? ??????", Color.WHITE);
        this.addHelp(" ???? ?? ?? ????????.", Color.WHITE);
        this.addHelp("", Color.WHITE);
        this.addHelp("  - ?????? = ?? ???? ", Color.RED);
        this.addHelp("  - ?????? = ???? ???? ???? ", Color.GREEN);
        this.addHelp("  - ?????? = ???? ???? ???? ", Color.YELLOW);
        this.addHelp("", Color.GREEN);
        this.addHelp(" **** ???????? ****", Color.ORANGE);
        this.addHelp(" ???????? ???????? ?????? ????????", Color.ORANGE);
        this.addHelp(" ????????.", Color.ORANGE);
        this.addHelp(" ???? ???? ???? ?????? ???? ??  ", Color.ORANGE);
        this.addHelp(" ???? ???????? ???? ??????????.", Color.ORANGE);
        this.addHelp("", Color.GREEN);
        this.addHelp(" **** ???? ???? ****", Color.WHITE);
        this.addHelp(" ?????? ???????? ???????? ?????? ", Color.WHITE);
        this.addHelp(" ?????????? ??????????????.", Color.WHITE);
        this.addHelp("", Color.GREEN);
        this.addHelp(" **** ???????? ****", Color.YELLOW);
        this.addHelp(" ?????????? ??????????", Color.YELLOW);
        this.addHelp(" ?????? ???????? ?????????? ????????.", Color.YELLOW);
        this.addHelp(" ???? ?????????? ?????????? ??????????", Color.YELLOW);
        this.addHelp(" ?????? ?? ????????.", Color.YELLOW);
        this.addHelp("", Color.GREEN);
        this.addHelp(" **** ???????? ****", Color.GREEN);
        this.addHelp(" ???? ?????? ????????, ?????? ???? ", Color.GREEN);
        this.addHelp(" ?????? ???????? ?????????? ????????.", Color.GREEN);
        this.addHelp(" ???? ?? ?????? ????????????????.", Color.GREEN);
        this.addHelp("", Color.GREEN);
    }

    private void iniReel(int _iChannel) {
        float v8 = 1000000000f;
        int v6 = 2;
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            int v1;
            for(v1 = 0; v1 < 4; ++v1) {
                this.card[_iChannel][v0][v1].startY = this.card[_iChannel][v0][v1].y;
                this.card[_iChannel][v0][v1].visible = true;
                this.card[_iChannel][v0][v1].small = false;
                this.card[_iChannel][v0][v1].mileage = 0f;
                this.card[_iChannel][v0][v1].setAlphaFlag(0);
            }
        }

        for(v0 = 0; v0 < 4; ++v0) {
            this.smallCard[_iChannel][v0].visible = false;
            this.smallCard[_iChannel][v0].small = false;
        }

        for(v0 = 0; v0 < 3; ++v0) {
            this.bReelRunning[_iChannel][v0] = true;
            this.bReelPoppingStarted[_iChannel][v0] = false;
            this.bReelDeceleratingStarted[_iChannel][v0] = false;
            this.bReelAccelerating[_iChannel][v0] = true;
            this.bReelDecelerating[_iChannel][v0] = false;
            this.bReelMinSpeedStarted[_iChannel][v0] = false;
            this.bReelPoppingMinSpeedStarted[_iChannel][v0] = false;
            this.iReelTensioningStep[_iChannel][v0] = 0;
            this.fReelDeltaMileage[_iChannel][v0] = 0f;
            this.fReelElapseTime[_iChannel][v0] = 0f;
            this.iPopCount[_iChannel][v0] = 0;
            this.fReelAlpha[_iChannel][v0] = 1f;
            this.fReelPoppingStartTime[_iChannel][v0] = 60f;
            this.fReelDeceleratingStartTime[_iChannel][v0] = 60f;
        }

        this.fNextScenarioDelay[_iChannel] = v8;
        this.fEndScenarioDelay[_iChannel] = v8;
        GameScreen.fTotalReelElapseTime[_iChannel] = 0f;
        GameScreen.bHovering[_iChannel] = false;
        this.fVideo1StartTime[_iChannel] = v8;
        this.fVideo2StartTime[_iChannel] = v8;
        this.fSecondAccelerationTime[_iChannel] = v8;
        this.bSecondAcceleration[_iChannel] = false;
        this.bVideo1Playing[_iChannel] = false;
        this.bVideo2Playing[_iChannel] = false;
        this.bVideo3Playing[_iChannel] = false;
        this.bPlayVideo1[_iChannel] = false;
        this.bPlayVideo2[_iChannel] = false;
        this.bShrinkCard[_iChannel] = false;
        this.sVideo1Raw[_iChannel] = "";
        this.sVideo2Raw[_iChannel] = "";
        this.fGlitterDelay[_iChannel] = v8;
        this.fsPostCardPos[_iChannel] = "";
        this.fPushCannonDelay[_iChannel] = v8;
        this.fPoppingDelay[_iChannel] = 0f;
        this.iFireType[_iChannel] = 0;
        this.fFireDelayTime[_iChannel] = 0f;
        this.fCannonDelayTime[_iChannel] = 0f;
        this.iMaxPopCount[_iChannel][0] = 6;
        this.iMaxPopCount[_iChannel][1] = 8;
        this.iMaxPopCount[_iChannel][v6] = 8;
        this.fStartReelDelayTime[_iChannel][0] = 0f;
        this.fStartReelDelayTime[_iChannel][1] = 0.2f;
        this.fStartReelDelayTime[_iChannel][v6] = 0.4f;
        this.reelMaxSpeed[_iChannel][0] = 2f;
        this.reelMaxSpeed[_iChannel][1] = 2f;
        this.reelMaxSpeed[_iChannel][v6] = 2f;
        this.reelMinSpeed[_iChannel][0] = 0.4f;
        this.reelMinSpeed[_iChannel][1] = 0.4f;
        this.reelMinSpeed[_iChannel][v6] = 0.4f;
        this.reelMinPoppingSpeed[_iChannel][0] = 0.6f;
        this.reelMinPoppingSpeed[_iChannel][1] = 0.4f;
        this.reelMinPoppingSpeed[_iChannel][v6] = 0.6f;
        this.accelerationSpeed[_iChannel][0] = 0.05f;
        this.accelerationSpeed[_iChannel][1] = 0.05f;
        this.accelerationSpeed[_iChannel][v6] = 0.05f;
        this.decelerationSpeed[_iChannel][0] = 0.02f;
        this.decelerationSpeed[_iChannel][1] = 0.02f;
        this.decelerationSpeed[_iChannel][v6] = 0.02f;
        this.accelerationAlpha[_iChannel][0] = 0.01f;
        this.accelerationAlpha[_iChannel][1] = 0.01f;
        this.accelerationAlpha[_iChannel][v6] = 0.01f;
        this.decelerationAlpha[_iChannel][0] = 0.006f;
        this.decelerationAlpha[_iChannel][1] = 0.006f;
        this.decelerationAlpha[_iChannel][v6] = 0.006f;
        this.minAlpha[_iChannel] = 0.35f;
        this.iPostTargetCardNo[_iChannel][0] = 0;
        this.iPostTargetCardNo[_iChannel][1] = 0;
        this.iPostTargetCardNo[_iChannel][v6] = 0;
        --Global.spin[_iChannel];
        if(Global.channel == _iChannel) {
            this.bDrawLine = false;
            GameScreen.fSndStopDelay = 0f;
            Res.sndReach.stop();
            Res.sndReachSuccess.stop();
            Res.sndFireAlram.stop();
            Res.sndReel.play();
            Res.sndReelEnd.stop();
            Res.imgVideoBack.visible = false;
            this.updateLabel();
            GameScreen.bHideVideo = true;
            GameScreen.bShowVideo = true;
            GameScreen.bHideCannon = true;
            GameScreen.bShowCannon = true;
            this.iniChatBack(3);
        }
        else {
            Res.aniMonitor.play(true, "PLAY");
        }
    }

    private void iniSkin() {  // has try-catch handlers
        try {
            if(this.width < 1280f) {
                goto label_14;
            }

            this.skin = new Skin(Gdx.files.internal("data/uiskin1280.json"));
            GameScreen.skin_window = new Skin(Gdx.files.internal("data/skin1280.json"));
            return;
        label_14:
            this.skin = new Skin(Gdx.files.internal("data/uiskin800.json"));
            GameScreen.skin_window = new Skin(Gdx.files.internal("data/skin800.json"));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->iniSkin", Util.getExceptionMessage(v0));
        }
    }

    private void iniSwitching() {  // has try-catch handlers
        int v5 = 3;
        int v1 = 0;
        while(true) {
            if(v1 < 2) {
                goto label_107;
            }

            break;
            try {
            label_107:
                this.fNextScenarioDelay[v1] = 1000000000f;
                this.fEndScenarioDelay[v1] = 1000000000f;
                this.bVideo1Playing[v1] = false;
                this.bVideo2Playing[v1] = false;
                this.bVideo3Playing[v1] = false;
                this.fVideo1StartTime[v1] = 1000000000f;
                this.fVideo2StartTime[v1] = 1000000000f;
                this.bPlayVideo1[v1] = false;
                this.bPlayVideo2[v1] = false;
                GameScreen.bHovering[v1] = false;
                this.reelMode[v1] = 0;
                this.bSecondAcceleration[v1] = false;
                this.fSecondAccelerationTime[v1] = 1000000000f;
                this.bSecondAcceleration[v1] = false;
                this.minAlpha[v1] = 0.35f;
                this.bShrinkCard[v1] = false;
                this.fGlitterDelay[v1] = 1000000000f;
                this.iFireType[v1] = 0;
                this.sVideo1Raw[v1] = "";
                this.sVideo2Raw[v1] = "";
                GameScreen.fTotalReelElapseTime[v1] = 0f;
                this.fFireDelayTime[v1] = 0f;
                this.fCannonDelayTime[v1] = 0f;
                this.fsPostCardPos[v1] = "";
                GameScreen.iReserveConfirmTick[v1] = 0;
                this.bEnding[v1] = false;
                this.showReachLine(v1, false);
                int v2;
                for(v2 = 0; v2 < v5; ++v2) {
                    this.fReelDeceleratingStartTime[v1][v2] = 1000000000f;
                    this.fReelPoppingStartTime[v1][v2] = 1000000000f;
                    this.iMaxPopCount[v1][v2] = 12;
                    this.iPopCount[v1][v2] = 0;
                    this.bReelRunning[v1][v2] = false;
                    this.bReelPoppingStarted[v1][v2] = false;
                    this.bReelDeceleratingStarted[v1][v2] = false;
                    this.bReelAccelerating[v1][v2] = false;
                    this.bReelDecelerating[v1][v2] = false;
                    this.bReelMinSpeedStarted[v1][v2] = false;
                    this.bReelPoppingMinSpeedStarted[v1][v2] = false;
                    this.iReelTensioningStep[v1][v2] = 0;
                    this.fReelDeltaMileage[v1][v2] = 0f;
                    this.fReelElapseTime[v1][v2] = 0f;
                    this.fReelAlpha[v1][v2] = 1f;
                    this.accelerationAlpha[v1][v2] = 0.01f;
                }

                ++v1;
                continue;
            }
            catch(Exception v0) {
                goto label_273;
            }
        }

        v1 = 0;
        while(true) {
            if(v1 < v5) {
                goto label_257;
            }

            break;
            try {
            label_257:
                this.iCannonDirection[v1] = 0;
                this.fCannonRotation[v1] = 0f;
                Res.spCannon[v1].setRotation(0f);
                ++v1;
                continue;
            }
            catch(Exception v0) {
                goto label_273;
            }
        }

        int v3 = 3;
        try {
            this.iniChatBack(v3);
            this.showRobot(false);
            this.bDrawLine = false;
            this.iHintCannonTick = 0;
            this.iHintCannonType = 0;
            this.fHintCannonElapseTime = 0f;
            this.iHintCannonStep = 0;
            GameScreen.bHideVideo = true;
            GameScreen.bShowVideo = true;
            GameScreen.bHideCannon = true;
            GameScreen.bShowCannon = true;
            this.fPushCannonDelay[0] = 1000000000f;
            this.fPushCannonDelay[1] = 1000000000f;
            this.sHintCannon = "";
            this.sRobot = "";
            GameScreen.fFackVideoTick = 0f;
            GameScreen.sFackVideoParam = "";
            Res.imgVideoBack.visible = false;
            Res.aniSuccess.visible = false;
            Res.aniMonitor.play(true, "IDLE");
            GameScreen.fSndStopDelay = 0f;
            Res.sndReel.stop();
            Res.sndReelEnd.stop();
            Res.sndFire.stop();
            Res.sndFireAlram.stop();
            Res.sndFire.stop();
            Res.sndCannon.stop();
            Res.sndReach.stop();
            Res.sndReachSuccess.stop();
            Res.aniLampCircle.play(false);
            Res.imgBatterBack.visible = false;
            Res.imgChance100Back.visible = false;
            this.lbChance100.setVisible(false);
            Global.chance100[0] = "";
            Global.chance100[1] = "";
            this.sSpace = "????";
            this.iniCards(0);
            this.iniCards(1);
            Res.aniLampTop.play(true, "????");
            Res.aniLampRight.play(true, "????");
        }
        catch(Exception v0) {
        label_273:
            Util.sysout("ERROR", "GameScreen->iniSwitching", Util.getExceptionMessage(v0));
        }
    }

    private void iniTest() {
        float v7 = 20f;
        float v6 = 10f;
        this.lbFps = new Label("", this.skin, "default-font", Color.GREEN);
        this.btnTest1 = new TextButton("   ????   ", this.skin);
        this.btnTest2 = new TextButton("   ????   ", this.skin);
        this.btnTest3 = new TextButton("  ??????1 ", this.skin);
        this.btnTest4 = new TextButton("  ??????3 ", this.skin);
        this.btnTest5 = new TextButton(" ??????123", this.skin);
        this.btnTest6 = new TextButton("  ????X  ", this.skin);
        this.btnTest7 = new TextButton(" ????X??1 ", this.skin);
        this.btnTest8 = new TextButton(" ????X??3 ", this.skin);
        this.btnTest9 = new TextButton("????X??123", this.skin);
        this.btnTest10 = new TextButton("[  ????  ]", this.skin);
        this.btnTest11 = new TextButton("[  ????X ]", this.skin);
        this.btnTest12 = new TextButton("[ ??????1]", this.skin);
        this.btnTest13 = new TextButton("[ ??????3]", this.skin);
        this.btnTest14 = new TextButton("[??????123]", this.skin);
        this.btnTest15 = new TextButton("[ ?????? ]", this.skin);
        this.btnTest16 = new TextButton("[??????????]", this.skin);
        this.btnTest17 = new TextButton("[??????]", this.skin);
        this.btnTest18 = new TextButton("[??????]", this.skin);
        this.btnTest19 = new TextButton("[????????]", this.skin);
        this.btnTest20 = new TextButton("[??????]", this.skin);
        this.btnTest21 = new TextButton("[ ???? ]", this.skin);
        this.btnTest22 = new TextButton("[??????1]", this.skin);
        this.btnTest23 = new TextButton("[??????2]", this.skin);
        this.btnTest24 = new TextButton("[??????3]", this.skin);
        this.btnTest25 = new TextButton("[ SP ]", this.skin);
        this.btnTest26 = new TextButton("[??????]", this.skin);
        this.btnTest27 = new TextButton("[????????]", this.skin);
        this.btnTest28 = new TextButton("[????????]", this.skin);
        this.btnTest29 = new TextButton("[     ]", this.skin);
        this.btnTest30 = new TextButton("[     ]", this.skin);
        this.btnTest31 = new TextButton("[     ]", this.skin);
        this.btnTest32 = new TextButton("[     ]", this.skin);
        this.tblTest = new Table();
        this.tblTest.setFillParent(true);
        if(this.width <= 800f) {
            this.tblTest.left().pad(150f).top().padTop(0f);
        }
        else {
            this.tblTest.left().pad(300f).top().padTop(0f);
        }

        this.tblTest.add(this.lbFps);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest1).padLeft(v6);
        this.tblTest.add(this.btnTest2).padLeft(v6);
        this.tblTest.add(this.btnTest3).padLeft(v6);
        this.tblTest.add(this.btnTest4).padLeft(v6);
        this.tblTest.add(this.btnTest5).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest6).padLeft(v6);
        this.tblTest.add(this.btnTest7).padLeft(v6);
        this.tblTest.add(this.btnTest8).padLeft(v6);
        this.tblTest.add(this.btnTest9).padLeft(v6);
        this.tblTest.add(this.btnTest10).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest11).padLeft(v6);
        this.tblTest.add(this.btnTest12).padLeft(v6);
        this.tblTest.add(this.btnTest13).padLeft(v6);
        this.tblTest.add(this.btnTest14).padLeft(v6);
        this.tblTest.add(this.btnTest15).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest16).padLeft(v6);
        this.tblTest.add(this.btnTest17).padLeft(v6);
        this.tblTest.add(this.btnTest18).padLeft(v6);
        this.tblTest.add(this.btnTest19).padLeft(v6);
        this.tblTest.add(this.btnTest20).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest21).padLeft(v6);
        this.tblTest.add(this.btnTest22).padLeft(v6);
        this.tblTest.add(this.btnTest23).padLeft(v6);
        this.tblTest.add(this.btnTest24).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest25).padLeft(v6);
        this.tblTest.add(this.btnTest26).padLeft(v6);
        this.tblTest.add(this.btnTest27).padLeft(v6);
        this.tblTest.add(this.btnTest28).padLeft(v6);
        this.tblTest.row().padTop(v7);
        this.tblTest.add(this.btnTest29).padLeft(v6);
        this.tblTest.add(this.btnTest30).padLeft(v6);
        this.tblTest.add(this.btnTest31).padLeft(v6);
        this.tblTest.add(this.btnTest32).padLeft(v6);
        GameScreen.stage.addActor(this.tblTest);
        this.lbFps.setVisible(false);
        this.btnTest1.setVisible(false);
        this.btnTest2.setVisible(false);
        this.btnTest3.setVisible(false);
        this.btnTest4.setVisible(false);
        this.btnTest5.setVisible(false);
        this.btnTest6.setVisible(false);
        this.btnTest7.setVisible(false);
        this.btnTest8.setVisible(false);
        this.btnTest9.setVisible(false);
        this.btnTest10.setVisible(false);
        this.btnTest11.setVisible(false);
        this.btnTest12.setVisible(false);
        this.btnTest13.setVisible(false);
        this.btnTest14.setVisible(false);
        this.btnTest15.setVisible(false);
        this.btnTest16.setVisible(false);
        this.btnTest17.setVisible(false);
        this.btnTest18.setVisible(false);
        this.btnTest19.setVisible(false);
        this.btnTest20.setVisible(false);
        this.btnTest21.setVisible(false);
        this.btnTest22.setVisible(false);
        this.btnTest23.setVisible(false);
        this.btnTest24.setVisible(false);
        this.btnTest25.setVisible(false);
        this.btnTest26.setVisible(false);
        this.btnTest27.setVisible(false);
        this.btnTest28.setVisible(false);
        this.btnTest29.setVisible(false);
        this.btnTest30.setVisible(false);
        this.btnTest31.setVisible(false);
        this.btnTest32.setVisible(false);
        this.btnTest1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelIdle(0, "");
                return 0;
            }
        });
        this.btnTest2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), Util.randomInt(1, 3), "", "");
                return 0;
            }
        });
        this.btnTest3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), Util.randomInt(1, 3), "", "1");
                return 0;
            }
        });
        this.btnTest4.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), Util.randomInt(1, 3), "", "3");
                return 0;
            }
        });
        this.btnTest5.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), Util.randomInt(1, 3), "", "123");
                return 0;
            }
        });
        this.btnTest6.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), 4, "", "");
                return 0;
            }
        });
        this.btnTest7.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), 4, "", "1");
                return 0;
            }
        });
        this.btnTest8.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), 4, "", "3");
                return 0;
            }
        });
        this.btnTest9.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachFailure(0, Util.randomInt(1, 8), 4, "", "123");
                return 0;
            }
        });
        this.btnTest10.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachSuccess(0, Util.randomInt(1, 8), Util.randomInt(1, 8), Util.randomInt(1, 3), "", "??????_T7_7_T7T7T7", "");
                return 0;
            }
        });
        this.btnTest11.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachSuccess(0, Util.randomInt(1, 8), Util.randomInt(1, 8), 4, "", "??????+??????_X1_2_T1C2T2", "");
                return 0;
            }
        });
        this.btnTest12.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachSuccess(0, Util.randomInt(1, 8), Util.randomInt(1, 8), Util.randomInt(1, 3), "", "??????_T1_2_B1B2B1", "1");
                return 0;
            }
        });
        this.btnTest13.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachSuccess(0, Util.randomInt(1, 8), Util.randomInt(1, 8), Util.randomInt(1, 3), "", "??????_X5_6_T5C6T6", "3");
                return 0;
            }
        });
        this.btnTest14.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelReachSuccess(0, Util.randomInt(1, 8), Util.randomInt(1, 8), Util.randomInt(1, 3), "", "??????_T8_1_B8B1B8", "123");
                return 0;
            }
        });
        this.btnTest15.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelPushButton(0, Util.randomInt(1, 8), Util.randomInt(1, 4), "??????_??????");
                return 0;
            }
        });
        this.btnTest16.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.reelPushButton(0, Util.randomInt(1, 8), Util.randomInt(1, 4), "??????_??????????");
                return 0;
            }
        });
        this.btnTest17.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.showToast("Toast~~!! \n?????? ????????????.", 3f);
                return 0;
            }
        });
        this.btnTest18.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String v1 = "";
                int v0;
                for(v0 = 0; v0 < 10; ++v0) {
                    v1 = String.valueOf(v1) + String.valueOf(Util.randomInt(0, 9));
                }

                GameScreen.this.setGage(Global.channel, v1);
                return 0;
            }
        });
        this.btnTest19.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0;
                GameScreen v2 = GameScreen.this;
                if(GameScreen.this.bStopScenarioForTest) {
                    v0 = false;
                }
                else {
                    v0 = true;
                }

                GameScreen.access$7(v2, v0);
                return 0;
            }
        });
        this.btnTest20.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.playVideo3(0, "??????_458_8", "T8T8T8", "T8");
                return 0;
            }
        });
        this.btnTest21.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.showRobot(true);
                return 0;
            }
        });
        this.btnTest22.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.showHintCannon(Global.channel, 1);
                return 0;
            }
        });
        this.btnTest23.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.showHintCannon(Global.channel, 2);
                return 0;
            }
        });
        this.btnTest24.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.showHintCannon(Global.channel, 3);
                return 0;
            }
        });
        this.btnTest25.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0;
                boolean v2 = true;
                Ani v3 = Res.aniSp;
                if(Res.aniSp.visible) {
                    v0 = false;
                }
                else {
                    v0 = true;
                }

                v3.visible = v0;
                Res.aniSp.play(Res.aniSp.visible);
                Ani v0_1 = Res.aniSpLamp;
                if(Res.aniSpLamp.visible) {
                    v2 = false;
                }

                v0_1.visible = v2;
                Res.aniSpLamp.play(Res.aniSpLamp.visible);
                return 0;
            }
        });
        this.btnTest26.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0 = true;
                Res.imgChance100Back.visible = false;
                GameScreen.this.lbChance100.setVisible(false);
                Global.chance100[0] = "";
                Global.chance100[1] = "";
                Img v2 = Res.imgBatterBack;
                if(Res.imgBatterBack.visible) {
                    v0 = false;
                }

                v2.visible = v0;
                if(Res.imgBatterBack.visible) {
                    Res.imgSpace.setImage("B");
                }
                else {
                    Res.imgSpace.setImage("N1");
                }

                return 0;
            }
        });
        this.btnTest27.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean v0;
                Res.imgBatterBack.visible = false;
                Img v3 = Res.imgChance100Back;
                if(Res.imgChance100Back.visible) {
                    v0 = false;
                }
                else {
                    v0 = true;
                }

                v3.visible = v0;
                GameScreen.this.lbChance100.setVisible(Res.imgChance100Back.visible);
                Global.chance100[0] = "";
                Global.chance100[1] = "";
                Global.chance100[Global.channel] = String.valueOf(Util.randomInt(1, 100));
                GameScreen.this.lbChance100.setText(Global.chance100[Global.channel]);
                if(Res.imgChance100Back.visible) {
                    Res.imgSpace.setImage("H");
                }
                else {
                    Res.imgSpace.setImage("N1");
                }

                return 0;
            }
        });
        this.btnTest28.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.this.addMsg(String.valueOf(GameScreen.this.findCardPos(Global.channel, 0)) + GameScreen.this.findCardPos(Global.channel, 1) + GameScreen.this.findCardPos(Global.channel, 2), Color.YELLOW);
                return 0;
            }
        });
        this.btnTest29.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return 0;
            }
        });
        this.btnTest30.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return 0;
            }
        });
        this.btnTest31.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return 0;
            }
        });
        this.btnTest32.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return 0;
            }
        });
    }

    private void iniTimer() {
        this.timer = new Timer();
        this.timer.schedule(new MyTask(), 1000, 1000);
    }

    private void iniUI() {
        float v11 = 306f;
        float v9 = 272f;
        float v8 = 24f;
        int v0;
        for(v0 = 0; v0 < this.iMaxMsg; ++v0) {
            this.saMsg[v0] = "";
            this.clMsg[v0] = Color.WHITE;
        }

        this.tblMsg = new Table();
        this.spsMsg = new ScrollPaneStyle();
        this.scpMsg = new ScrollPane(this.tblMsg, this.skin);
        this.scpMsg.setFadeScrollBars(false);
        this.scpMsg.setScrollbarsOnTop(false);
        this.scpMsg.setFlickScroll(true);
        this.scpMsg.setSize(Util.getActorWidth(430f), Util.getActorHeight(66f));
        this.scpMsg.setPosition(Util.getActorLeft(55f), Util.getActorTop(13f, this.scpMsg.getHeight()));
        this.scpMsg.setStyle(this.spsMsg);
        GameScreen.stage.addActor(this.scpMsg);
        for(v0 = 0; v0 < this.iMaxHelp; ++v0) {
            this.saHelp[v0] = "";
            this.clHelp[v0] = Color.WHITE;
        }

        this.tblHelp = new Table();
        this.spsHelp = new ScrollPaneStyle();
        this.scpHelp = new ScrollPane(this.tblHelp, this.skin);
        this.scpHelp.setFadeScrollBars(false);
        this.scpHelp.setScrollbarsOnTop(false);
        this.scpHelp.setFlickScroll(true);
        this.scpHelp.setSize(Util.getActorWidth(497f), Util.getActorHeight(265f));
        this.scpHelp.setPosition(Util.getActorLeft(210f), Util.getActorTop(135f, this.scpHelp.getHeight()));
        this.scpHelp.setStyle(this.spsHelp);
        this.scpHelp.setVisible(false);
        GameScreen.stage.addActor(this.scpHelp);
        for(v0 = 0; v0 < this.iMaxAsk; ++v0) {
            this.saAsk[v0] = "";
            this.clAsk[v0] = Color.WHITE;
        }

        this.tblAsk = new Table();
        this.spsAsk = new ScrollPaneStyle();
        this.scpAsk = new ScrollPane(this.tblAsk, this.skin);
        this.scpAsk.setFadeScrollBars(false);
        this.scpAsk.setScrollbarsOnTop(false);
        this.scpAsk.setFlickScroll(true);
        this.scpAsk.setSize(Util.getActorWidth(530f), Util.getActorHeight(220f));
        this.scpAsk.setPosition(Util.getActorLeft(210f), Util.getActorTop(135f, this.scpAsk.getHeight()));
        this.scpAsk.setStyle(this.spsAsk);
        this.scpAsk.setVisible(false);
        GameScreen.stage.addActor(this.scpAsk);
        this.imgChannelA = new Image(Res.leaner.findRegion("channel_big_a"));
        this.imgChannelA.setSize(Util.getActorWidth(30f), Util.getActorHeight(27f));
        this.imgChannelA.setPosition(Util.getActorLeft(70f), Util.getActorTop(431f, this.imgChannelA.getHeight()));
        this.imgChannelA.setVisible(false);
        GameScreen.stage.addActor(this.imgChannelA);
        this.imgChannelB = new Image(Res.leaner.findRegion("channel_big_b"));
        this.imgChannelB.setSize(Util.getActorWidth(30f), Util.getActorHeight(27f));
        this.imgChannelB.setPosition(Util.getActorLeft(70f), Util.getActorTop(431f, this.imgChannelB.getHeight()));
        this.imgChannelB.setVisible(false);
        GameScreen.stage.addActor(this.imgChannelB);
        this.lbMachine = new Label("", this.skin, "number-large", Color.YELLOW);
        this.lbMachine.setAlignment(16);
        this.lbMachine.setSize(Util.getActorWidth(40f), Util.getActorHeight(v8));
        this.lbMachine.setPosition(Util.getActorLeft(170f), Util.getActorTop(430f, this.lbMachine.getHeight()));
        GameScreen.stage.addActor(this.lbMachine);
        this.lbGift = new Label("", this.skin, "number-large", Color.YELLOW);
        this.lbGift.setAlignment(16);
        this.lbGift.setSize(Util.getActorWidth(103f), Util.getActorHeight(v8));
        this.lbGift.setPosition(Util.getActorLeft(296f), Util.getActorTop(430f, this.lbGift.getHeight()));
        GameScreen.stage.addActor(this.lbGift);
        this.lbSpin = new Label("", this.skin, "number-large", Color.YELLOW);
        this.lbSpin.setAlignment(16);
        this.lbSpin.setSize(Util.getActorWidth(70f), Util.getActorHeight(v8));
        this.lbSpin.setPosition(Util.getActorLeft(484f), Util.getActorTop(430f, this.lbSpin.getHeight()));
        GameScreen.stage.addActor(this.lbSpin);
        this.lbMoney = new Label("", this.skin, "number-large", Color.GREEN);
        this.lbMoney.setSize(Util.getActorWidth(112f), Util.getActorHeight(v8));
        this.lbMoney.setAlignment(16);
        this.lbMoney.setPosition(Util.getActorLeft(652f), Util.getActorTop(430f, this.lbMoney.getHeight()));
        GameScreen.stage.addActor(this.lbMoney);
        GameScreen.lbReserve = new Label("", this.skin, "hangul-font", Color.GREEN);
        GameScreen.lbReserve.setSize(Util.getActorWidth(180f), Util.getActorHeight(40f));
        GameScreen.lbReserve.setAlignment(1);
        GameScreen.lbReserve.setPosition(Util.getActorLeft(379f), Util.getActorTop(258f, GameScreen.lbReserve.getHeight()));
        GameScreen.lbReserve.setVisible(false);
        GameScreen.stage.addActor(GameScreen.lbReserve);
        this.lbChance100 = new Label("", this.skin, "number-small", Color.GREEN);
        this.lbChance100.setAlignment(1);
        this.lbChance100.setSize(Util.getActorWidth(40f), Util.getActorHeight(15f));
        this.lbChance100.setPosition(Util.getActorLeft(561f), Util.getActorTop(104f, this.lbChance100.getHeight()));
        this.lbChance100.setVisible(false);
        GameScreen.stage.addActor(this.lbChance100);
        for(v0 = 0; v0 < 16; ++v0) {
            this.lbMachineNo[v0] = new Label("", this.skin, "number-small-shadow", Color.BLACK);
            this.lbMachineNo[v0].setSize(Util.getWidth(30f), Util.getHeight(8f));
            this.lbMachineNo[v0].setVisible(false);
            this.lbMachineNo[v0].setAlignment(16);
            GameScreen.stage.addActor(this.lbMachineNo[v0]);
        }

        this.lbMachineNo[0].setPosition(Util.getActorLeft(171f), Util.getActorTop(141f, this.lbMachineNo[0].getHeight()));
        this.lbMachineNo[1].setPosition(Util.getActorLeft(305f), Util.getActorTop(141f, this.lbMachineNo[1].getHeight()));
        this.lbMachineNo[2].setPosition(Util.getActorLeft(439f), Util.getActorTop(141f, this.lbMachineNo[2].getHeight()));
        this.lbMachineNo[3].setPosition(Util.getActorLeft(573f), Util.getActorTop(141f, this.lbMachineNo[3].getHeight()));
        this.lbMachineNo[4].setPosition(Util.getActorLeft(171f), Util.getActorTop(214f, this.lbMachineNo[4].getHeight()));
        this.lbMachineNo[5].setPosition(Util.getActorLeft(305f), Util.getActorTop(214f, this.lbMachineNo[5].getHeight()));
        this.lbMachineNo[6].setPosition(Util.getActorLeft(439f), Util.getActorTop(214f, this.lbMachineNo[6].getHeight()));
        this.lbMachineNo[7].setPosition(Util.getActorLeft(573f), Util.getActorTop(214f, this.lbMachineNo[7].getHeight()));
        this.lbMachineNo[8].setPosition(Util.getActorLeft(171f), Util.getActorTop(287f, this.lbMachineNo[8].getHeight()));
        this.lbMachineNo[9].setPosition(Util.getActorLeft(305f), Util.getActorTop(287f, this.lbMachineNo[9].getHeight()));
        this.lbMachineNo[10].setPosition(Util.getActorLeft(439f), Util.getActorTop(287f, this.lbMachineNo[10].getHeight()));
        this.lbMachineNo[11].setPosition(Util.getActorLeft(573f), Util.getActorTop(287f, this.lbMachineNo[11].getHeight()));
        this.lbMachineNo[12].setPosition(Util.getActorLeft(171f), Util.getActorTop(360f, this.lbMachineNo[12].getHeight()));
        this.lbMachineNo[13].setPosition(Util.getActorLeft(305f), Util.getActorTop(360f, this.lbMachineNo[13].getHeight()));
        this.lbMachineNo[14].setPosition(Util.getActorLeft(439f), Util.getActorTop(360f, this.lbMachineNo[14].getHeight()));
        this.lbMachineNo[15].setPosition(Util.getActorLeft(573f), Util.getActorTop(360f, this.lbMachineNo[15].getHeight()));
        this.tfChargeMoney = new TextField("", GameScreen.skin_window, "transparent");
        this.tfChargeMoney.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.tfChargeMoney.setPosition(Util.getActorLeft(301f), Util.getActorTop(154f, this.tfChargeMoney.getHeight()));
        this.tfChargeMoney.setVisible(false);
        GameScreen.stage.addActor(this.tfChargeMoney);
        this.tfChargeMoney.setTextFieldListener(new TextFieldListener() {
            public void keyTyped(TextField tf, char key) {
                if(key == 10) {
                    GameScreen.this.tfChargeMoney.getOnscreenKeyboard().show(false);
                }
            }
        });
        this.lbChargeMan = new Label("", this.skin, "hangul-font", Color.BLACK);
        this.lbChargeMan.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbChargeMan.setPosition(Util.getActorLeft(v11), Util.getActorTop(180f, this.lbChargeMan.getHeight()));
        this.lbChargeMan.setVisible(false);
        GameScreen.stage.addActor(this.lbChargeMan);
        this.lbChargeBank = new Label(Global.bank, this.skin, "hangul-font", Color.BLUE);
        this.lbChargeBank.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbChargeBank.setPosition(Util.getActorLeft(v11), Util.getActorTop(209f, this.lbChargeBank.getHeight()));
        this.lbChargeBank.setVisible(false);
        GameScreen.stage.addActor(this.lbChargeBank);
        this.lbChargeOwner = new Label(Global.owner, this.skin, "hangul-font", Color.BLUE);
        this.lbChargeOwner.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbChargeOwner.setPosition(Util.getActorLeft(v11), Util.getActorTop(238f, this.lbChargeOwner.getHeight()));
        this.lbChargeOwner.setVisible(false);
        GameScreen.stage.addActor(this.lbChargeOwner);
        this.lbChargeAccount = new Label(Global.account, this.skin, "hangul-font", Color.BLUE);
        this.lbChargeAccount.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbChargeAccount.setPosition(Util.getActorLeft(v11), Util.getActorTop(270f, this.lbChargeAccount.getHeight()));
        this.lbChargeAccount.setVisible(false);
        GameScreen.stage.addActor(this.lbChargeAccount);
        this.tfExchangeAccount = new TextField("", GameScreen.skin_window, "transparent");
        this.tfExchangeAccount.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.tfExchangeAccount.setPosition(Util.getActorLeft(301f), Util.getActorTop(154f, this.tfExchangeAccount.getHeight()));
        this.tfExchangeAccount.setVisible(false);
        GameScreen.stage.addActor(this.tfExchangeAccount);
        this.tfExchangeAccount.setTextFieldListener(new TextFieldListener() {
            public void keyTyped(TextField tf, char key) {
                if(key == 10) {
                    GameScreen.this.tfExchangeAccount.setFocusTraversal(true);
                    GameScreen.this.tfExchangeAccount.getOnscreenKeyboard().show(false);
                }
            }
        });
        this.tfExchangeMoney = new TextField("", GameScreen.skin_window, "transparent");
        this.tfExchangeMoney.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.tfExchangeMoney.setPosition(Util.getActorLeft(301f), Util.getActorTop(183f, this.tfExchangeMoney.getHeight()));
        this.tfExchangeMoney.setVisible(false);
        GameScreen.stage.addActor(this.tfExchangeMoney);
        this.tfExchangeMoney.setTextFieldListener(new TextFieldListener() {
            public void keyTyped(TextField tf, char key) {
                if(key == 10) {
                    GameScreen.this.tfExchangeMoney.setFocusTraversal(true);
                    GameScreen.this.tfExchangeMoney.getOnscreenKeyboard().show(false);
                }
            }
        });
        this.tfExchangeTel = new TextField("", GameScreen.skin_window, "transparent");
        this.tfExchangeTel.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.tfExchangeTel.setPosition(Util.getActorLeft(301f), Util.getActorTop(212f, this.tfExchangeTel.getHeight()));
        this.tfExchangeTel.setVisible(false);
        GameScreen.stage.addActor(this.tfExchangeTel);
        this.tfExchangeTel.setTextFieldListener(new TextFieldListener() {
            public void keyTyped(TextField tf, char key) {
                if(key == 10) {
                    GameScreen.this.tfExchangeTel.setFocusTraversal(true);
                    GameScreen.this.tfExchangeTel.getOnscreenKeyboard().show(false);
                }
            }
        });
        this.lbExchangeBank = new Label("", this.skin, "hangul-font", Color.BLACK);
        this.lbExchangeBank.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbExchangeBank.setPosition(Util.getActorLeft(v11), Util.getActorTop(238f, this.lbExchangeBank.getHeight()));
        this.lbExchangeBank.setVisible(false);
        GameScreen.stage.addActor(this.lbExchangeBank);
        this.lbExchangeOwner = new Label("", this.skin, "hangul-font", Color.BLACK);
        this.lbExchangeOwner.setSize(Util.getActorWidth(v9), Util.getActorHeight(v8));
        this.lbExchangeOwner.setPosition(Util.getActorLeft(v11), Util.getActorTop(267f, this.lbExchangeOwner.getHeight()));
        this.lbExchangeOwner.setVisible(false);
        GameScreen.stage.addActor(this.lbExchangeOwner);
        this.imgToastBack = new Image(Res.leaner.findRegion("wait_back2"));
        this.imgToastBack.setSize(Util.getActorWidth(800f), Util.getActorHeight(480f));
        this.imgToastBack.setPosition(Util.getActorLeft(0f), Util.getActorTop(0f, this.imgToastBack.getHeight()));
        this.imgToastBack.setVisible(false);
        GameScreen.stage.addActor(this.imgToastBack);
        this.imgToast = new Image(Res.leaner.findRegion("toast_back"));
        this.imgToast.setSize(Util.getActorWidth(550f), Util.getActorHeight(140f));
        this.imgToast.setPosition(Util.getActorLeft(125f), Util.getActorTop(165f, this.imgToast.getHeight()));
        this.imgToast.setVisible(false);
        GameScreen.stage.addActor(this.imgToast);
        this.lbToast = new Label("", this.skin, "hangul-font", Color.YELLOW);
        this.lbToast.setSize(Util.getActorWidth(450f), Util.getActorHeight(100f));
        this.lbToast.setPosition(Util.getActorLeft(175f), Util.getActorTop(210f, this.lbToast.getHeight()));
        this.lbToast.setAlignment(2);
        this.lbToast.setVisible(false);
        GameScreen.stage.addActor(this.lbToast);
    }

    private boolean isReelRunning(int _iChannel) {
        boolean v0 = false;
        if((this.bReelRunning[_iChannel][0]) || (this.bReelRunning[_iChannel][1]) || (this.bReelRunning[_iChannel][2])) {
            v0 = true;
        }

        return v0;
    }

    private void machineListRequest(int flag) {
        if(this.bCanUpDownChannel) {
            this.bCanUpDownChannel = false;
            int v0 = Global.division;
            if(flag > 0) {
                v0 = Global.division + 1;
            }
            else if(flag < 0) {
                v0 = Global.division - 1;
            }

            GameScreen.socketWrite("G030\t" + String.valueOf(v0));
        }
    }

    private void moveReel(int _iChannel, int iReelIndex) {
        int v6 = 4;
        if(this.iReelTensioningStep[_iChannel][iReelIndex] == 0) {
            int v0;
            for(v0 = 0; v0 < v6; ++v0) {
                this.card[_iChannel][iReelIndex][v0].mileage += this.fReelDeltaMileage[_iChannel][iReelIndex];
                this.card[_iChannel][iReelIndex][v0].y = this.card[_iChannel][iReelIndex][v0].startY - this.card[_iChannel][iReelIndex][v0].mileage;
                if(this.card[_iChannel][iReelIndex][v0].y < Res.fBaseLineY0) {
                    while(this.card[_iChannel][iReelIndex][v0].y < Res.fBaseLineY0) {
                        this.card[_iChannel][iReelIndex][v0].mileage -= Res.fBaseLineY4 - Res.fBaseLineY0;
                        this.card[_iChannel][iReelIndex][v0].y = this.card[_iChannel][iReelIndex][v0].startY - this.card[_iChannel][iReelIndex][v0].mileage;
                    }

                    if(this.bReelPoppingStarted[_iChannel][iReelIndex]) {
                        ++this.iPopCount[_iChannel][iReelIndex];
                        if(iReelIndex == 0) {
                            this.iNextCardNo[_iChannel][iReelIndex] = this.iTargetCardNo[_iChannel][iReelIndex] - (this.iPopCount[_iChannel][iReelIndex] - this.iMaxPopCount[_iChannel][iReelIndex]) % 8;
                        }
                        else {
                            this.iNextCardNo[_iChannel][iReelIndex] = (this.iPopCount[_iChannel][iReelIndex] - this.iMaxPopCount[_iChannel][iReelIndex]) % 8 - (8 - this.iTargetCardNo[_iChannel][iReelIndex]);
                        }

                        this.card[_iChannel][iReelIndex][v0].setNo(this.iNextCardNo[_iChannel][iReelIndex]);
                    }
                    else {
                        this.card[_iChannel][iReelIndex][v0].setNo(this.iNextCardNo[_iChannel][iReelIndex]);
                        if(iReelIndex != 0) {
                            goto label_191;
                        }

                        --this.iNextCardNo[_iChannel][iReelIndex];
                        goto label_124;
                    label_191:
                        ++this.iNextCardNo[_iChannel][iReelIndex];
                    }
                }

            label_124:
            }

            if(this.iReelTensioningStep[_iChannel][iReelIndex] != 0) {
                return;
            }

            if(this.iPopCount[_iChannel][iReelIndex] <= this.iMaxPopCount[_iChannel][iReelIndex]) {
                return;
            }

            this.bReelDecelerating[_iChannel][iReelIndex] = false;
            this.bReelAccelerating[_iChannel][iReelIndex] = false;
            this.iReelTensioningStep[_iChannel][iReelIndex] = 1;
            for(v0 = 0; v0 < v6; ++v0) {
                this.card[_iChannel][iReelIndex][v0].targetY = this.getStopTargetYPos(_iChannel, iReelIndex, this.card[_iChannel][iReelIndex][v0].y);
            }
        }
    }

    private void onMachineClicked(int i, String sNo) {
        if(this.bCanSelectMachine) {
            int v0 = Util.strToint(sNo, 0);
            if(v0 > 0 && v0 != Global.machineNo[0] && v0 != Global.machineNo[1]) {
                if(this.iMachineState[i] != 0) {
                    this.showMessage("???????????? ???????? ??????????.");
                }
                else {
                    if(Global.machineNo[0] > 0 && Global.spin[0] > 0 && Global.machineNo[1] > 0 && Global.spin[1] > 0) {
                        this.showMessage("?????? ???????? ?????? ???? ?????? ?????? ?? ????????.\n???? 2?????? ???? ??????????.");
                        return;
                    }

                    if("member".equals(Global.cls)) {
                        goto label_41;
                    }

                    this.showMessage("?????? ?????? ?????? ???? ?? ?? ????????.");
                    return;
                label_41:
                    new Dialog() {
                        protected void result(Object object) {
                            if(object.intValue() != 0) {
                                GameScreen.this.showMenu(false);
                                GameScreen.access$19(GameScreen.this, false);
                                GameScreen.socketWrite("G040\t" + Global.id + "\t" + Global.channel + "\t" + String.valueOf(object));
                            }
                        }
                    }.text("************* ???????? *************\ntoday : " + String.valueOf(this.iMachineToday[i]) + "     yesterday : " + String.valueOf(this.iMachineYesterday[i]) + "\n" + "[ " + sNo + " ]?? ?????? ?????????????????\n").button("??", Integer.valueOf(v0)).button("??????", Integer.valueOf(0)).key(66, Integer.valueOf(0)).key(131, Integer.valueOf(0)).show(GameScreen.stage);
                }
            }
        }
    }

    public void onVideoPlayCompleted(String param) {  // has try-catch handlers
        int v1;
        String[] v5;
        float v12 = 0.3f;
        int v11 = 3;
        int v10 = 2;
        try {
            if("1".equals(Global.viewScenario)) {
                this.addMsg("??????????=" + param, Color.GREEN);
            }

            if("".equals(param)) {
                return;
            }
        }
        catch(Exception v0) {
            goto label_169;
        }

        if(param != null) {
            goto label_20;
        }

        return;
        try {
        label_20:
            v5 = param.split(";");
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(1)", Util.getExceptionMessage(v0), param);
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        int v6 = 4;
        try {
            v1 = Util.strToint(v5[v6], -1);
            if(v1 != 0) {
                goto label_27;
            }

            goto label_30;
        }
        catch(Exception v0) {
            goto label_169;
        }

    label_27:
        if(v1 == 1) {
        }
        else {
            return;
        }

        try {
        label_30:
            int v2 = Integer.parseInt(v5[0]);
            goto label_32;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(2)", Util.getExceptionMessage(v0), param);
            label_32:
                if(this.reelMode[v1] != v2) {
                    return;
                }
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        try {
            int v3 = Integer.parseInt(v5[1]);
            goto label_39;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(3)", Util.getExceptionMessage(v0), param);
            label_39:
                v6 = 2;
                goto label_41;
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        try {
        label_41:
            String v4 = v5[v6];
            goto label_42;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(4)", Util.getExceptionMessage(v0), param);
            label_42:
                if(v1 == Global.channel) {
                    Res.aniSuccess.visible = false;
                    Res.imgVideoBack.visible = false;
                }

                if(v3 != 1) {
                    goto label_200;
                }

                this.sVideo1Raw[v1] = "";
                this.bVideo1Playing[v1] = false;
                this.bPlayVideo1[v1] = false;
                if(Global.channel == v1) {
                    if(this.reelMode[v1] != v11) {
                        Res.sndReelEnd.play();
                    }

                    if("".equals(this.sRobot)) {
                        goto label_75;
                    }

                    this.showRobot(true);
                    this.sRobot = "";
                }
                else {
                    Res.aniMonitor.play(true, "PLAY");
                }

            label_75:
                if(this.reelMode[v1] == v11) {
                    this.fGlitterDelay[v1] = 2f;
                    this.fEndScenarioDelay[v1] = 4f;
                }

                if(this.reelMode[v1] != 1 && this.reelMode[v1] != v10 && this.reelMode[v1] != 0) {
                    return;
                }

                this.fReelDeceleratingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v12;
                this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 1.1f + this.fPoppingDelay[v1];
                this.fReelDeceleratingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + 0.6f;
                this.fReelPoppingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v12;
                this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 1.1f + this.fPoppingDelay[v1];
                this.fReelPoppingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + 0.6f;
                if(this.reelMode[v1] != v10) {
                    return;
                }

                if(this.iFireType[v1] != 0 && this.iFireType[v1] != 5 && this.iFireType[v1] != 6) {
                    return;
                }

                this.fReelPoppingStartTime[v1][1] = 60f;
                return;
            label_200:
                if(v3 != v10) {
                    goto label_219;
                }

                this.sVideo2Raw[v1] = "";
                this.bVideo2Playing[v1] = false;
                this.bPlayVideo2[v1] = false;
                this.arrangeReel(v1);
                if(Global.channel != v1) {
                    Res.aniMonitor.play(true, "STOP");
                }

                this.endScenario(v1);
                return;
            label_219:
                if(v3 != v11) {
                    return;
                }

                this.bVideo3Playing[v1] = false;
                this.cheatCardPos(v1);
                this.arrangeReel(v1);
                if(v4.indexOf("WATASE") >= 0) {
                    this.fGlitterDelay[v1] = 3f;
                    this.fEndScenarioDelay[v1] = 5f;
                }
                else {
                    this.endScenario(v1);
                }

                if(Global.channel == v1) {
                    return;
                }

                Res.aniMonitor.play(true, "STOP");
            }
            catch(Exception v0) {
            label_169:
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(8)", Util.getExceptionMessage(v0), param);
            }
        }
    }

    public void onVideoPlayCompleted2(String param) {  // has try-catch handlers
        int v1;
        String[] v5;
        float v12 = 0.3f;
        int v11 = 3;
        int v10 = 2;
        try {
            if("1".equals(Global.viewScenario)) {
                this.addMsg("??????????2=" + param, Color.GREEN);
            }

            if("".equals(param)) {
                return;
            }
        }
        catch(Exception v0) {
            goto label_169;
        }

        if(param != null) {
            goto label_20;
        }

        return;
        try {
        label_20:
            v5 = param.split(";");
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(1)", Util.getExceptionMessage(v0), param);
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        int v6 = 4;
        try {
            v1 = Util.strToint(v5[v6], -1);
            if(v1 != 0) {
                goto label_27;
            }

            goto label_30;
        }
        catch(Exception v0) {
            goto label_169;
        }

    label_27:
        if(v1 == 1) {
        }
        else {
            return;
        }

        try {
        label_30:
            int v2 = Integer.parseInt(v5[0]);
            goto label_32;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(2)", Util.getExceptionMessage(v0), param);
            label_32:
                if(this.reelMode[v1] != v2) {
                    return;
                }
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        try {
            int v3 = Integer.parseInt(v5[1]);
            goto label_39;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(3)", Util.getExceptionMessage(v0), param);
            label_39:
                v6 = 2;
                goto label_41;
            }
            catch(Exception v0) {
                goto label_169;
            }
        }

        try {
        label_41:
            String v4 = v5[v6];
            goto label_42;
        }
        catch(Exception v0) {
            try {
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(4)", Util.getExceptionMessage(v0), param);
            label_42:
                if(v1 == Global.channel) {
                    Res.aniSuccess.visible = false;
                    Res.imgVideoBack.visible = false;
                }

                if(v3 != 1) {
                    goto label_200;
                }

                this.sVideo1Raw[v1] = "";
                this.bVideo1Playing[v1] = false;
                this.bPlayVideo1[v1] = false;
                if(Global.channel == v1) {
                    if(this.reelMode[v1] != v11) {
                        Res.sndReelEnd.play();
                    }

                    if("".equals(this.sRobot)) {
                        goto label_75;
                    }

                    this.showRobot(true);
                    this.sRobot = "";
                }
                else {
                    Res.aniMonitor.play(true, "PLAY");
                }

            label_75:
                if(this.reelMode[v1] == v11) {
                    this.fGlitterDelay[v1] = 2f;
                    this.fEndScenarioDelay[v1] = 4f;
                }

                if(this.reelMode[v1] != 1 && this.reelMode[v1] != v10 && this.reelMode[v1] != 0) {
                    return;
                }

                this.fReelDeceleratingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v12;
                this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 1.1f + this.fPoppingDelay[v1];
                this.fReelDeceleratingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + 0.6f;
                this.fReelPoppingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v12;
                this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 1.1f + this.fPoppingDelay[v1];
                this.fReelPoppingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + 0.6f;
                if(this.reelMode[v1] != v10) {
                    return;
                }

                if(this.iFireType[v1] != 0 && this.iFireType[v1] != 5 && this.iFireType[v1] != 6) {
                    return;
                }

                this.fReelPoppingStartTime[v1][1] = 60f;
                return;
            label_200:
                if(v3 != v10) {
                    goto label_219;
                }

                this.sVideo2Raw[v1] = "";
                this.bVideo2Playing[v1] = false;
                this.bPlayVideo2[v1] = false;
                this.arrangeReel(v1);
                if(Global.channel != v1) {
                    Res.aniMonitor.play(true, "STOP");
                }

                this.endScenario(v1);
                return;
            label_219:
                if(v3 != v11) {
                    return;
                }

                this.bVideo3Playing[v1] = false;
                this.cheatCardPos(v1);
                this.arrangeReel(v1);
                if(v4.indexOf("WATASE") >= 0) {
                    this.fGlitterDelay[v1] = 3f;
                    this.fEndScenarioDelay[v1] = 5f;
                }
                else {
                    this.endScenario(v1);
                }

                if(Global.channel == v1) {
                    return;
                }

                Res.aniMonitor.play(true, "STOP");
            }
            catch(Exception v0) {
            label_169:
                Util.sysout("ERROR", "GameScreen->onVideoPlayCompleted(8)", Util.getExceptionMessage(v0), param);
            }
        }
    }

    public void pause() {
        Res.stopSound();
    }

    private void playVideo3(int _iChannel, String _sVideoRaw, String _sCardPos, String _sNo) {
        this.reelMode[_iChannel] = 0;
        this.bVideo3Playing[_iChannel] = true;
        if(!"".equals(_sCardPos)) {
            this.fsPostCardPos[_iChannel] = _sCardPos;
        }

        if(!"".equals(_sNo)) {
            this.iTCBX[_iChannel] = this.getTCBX(_sNo);
        }

        if(Global.channel == _iChannel) {
            Res.imgVideoBack.visible = true;
            GameScreen.game.playVideo(this, this.mVideoPlayCompleted, this.getVideoResId(_sVideoRaw), "0;3;" + _sVideoRaw + ";play;" + String.valueOf(_iChannel));
            GameScreen.bHideVideo = true;
            GameScreen.bShowVideo = true;
            if(_sVideoRaw.indexOf("MOVIE_ROUND") >= 0) {
                Res.aniLampTop.play(true, "??????");
                Res.aniLampRight.play(true, "??????");
            }
            else if(_sVideoRaw.indexOf("CHANGE") >= 0) {
                Res.aniLampTop.play(true, "????");
                Res.aniLampRight.play(true, "????");
            }
        }
        else {
            GameScreen.sFackVideoParam = "0;3;" + _sVideoRaw + ";not_play;" + String.valueOf(_iChannel);
            GameScreen.fFackVideoTick = 0.1f;
            if(_sVideoRaw.indexOf("MOVIE_ROUND") >= 0) {
                Res.aniMonitor.play(true, "FEVER");
            }
            else if(_sVideoRaw.indexOf("CHANGE") >= 0) {
                Res.aniMonitor.play(true, "BATTER");
            }
            else if(_sVideoRaw.indexOf("WATASE") >= 0) {
                Res.aniMonitor.play(true, "BATTER");
            }
        }
    }

    private void processEvent() {
        int v4 = 66;
        int v5 = 150;
        if(!Global.bTerminating && Dialog.count <= 0 && this.fToastTime <= 0f && (this.bGameScreenReady) && GameScreen.fCelemony <= 0f) {
            if((Gdx.input.isKeyPressed(4)) && !Global.bAskingGameExit && !Global.bTerminating) {
                this.askExitGame();
            }

            if(!this.bCanSelectMachine) {
                return;
            }

            if(!this.bCanInsert10000) {
                return;
            }

            if(!this.bCanSwitchMoney) {
                return;
            }

            if(!this.bCanUpDownChannel) {
                return;
            }

            if(!this.bCanSelectMachine) {
                return;
            }

            if(!this.bCanSwitchMachine) {
                return;
            }

            if(this.bSwitching) {
                return;
            }

            if(this.bEnding[0]) {
                return;
            }

            if(this.bEnding[1]) {
                return;
            }

            if(GameScreen.bReserving) {
                return;
            }

            if(!this.bCanRequestScenario[0]) {
                return;
            }

            if(!this.bCanRequestScenario[1]) {
                return;
            }

            if(this.bWaitingScenario[0]) {
                return;
            }

            if(this.bWaitingScenario[1]) {
                return;
            }

            if(!Gdx.input.justTouched()) {
                return;
            }

            Vector3 v8 = Vector3.tmp;
            this.camera.unproject(v8.set(((float)Gdx.input.getX()), ((float)Gdx.input.getY()), 0f));
            if(GameScreen.bShowMenu) {
                goto label_364;
            }

            if(Util.isTouched(Res.btnMenu.getBoundingRectangle(), v8.x, v8.y)) {
                if(Dialog.count <= 0) {
                    this.clickEffect(111, 134, v5);
                    this.showMenu(true);
                }
                else {
                    return;
                }
            }
            else if(!Util.isTouched(Res.btnSwitch.getBoundingRectangle(), v8.x, v8.y)) {
                if(!Util.isTouched(Res.aniBackChannel.getBoundingRectangle(), v8.x, v8.y) && !Util.isTouched(Res.imgGageBack.getBoundingRectangle(), v8.x, v8.y)) {
                    if(Util.isTouched(Res.btnReserve.getBoundingRectangle(), v8.x, v8.y)) {
                        if(Dialog.count > 0) {
                            return;
                        }
                        else if(!this.bCanReserveMachine) {
                            return;
                        }
                        else if(!GameScreen.bReserving) {
                            this.clickEffect(111, 246, v5);
                            if(Global.machineNo[Global.channel] <= 0) {
                                this.showMessage("???? ?????? ?????? ?????? ????????.");
                                return;
                            }
                            else if(Global.spin[Global.channel] <= 0) {
                                this.showMessage("?????? ???? ???????? ?????? ?? ????????.");
                                return;
                            }
                            else if(!"member".equals(Global.cls)) {
                                this.showMessage("?????? ?????? ?????? ???? ?? ?? ????????.");
                                return;
                            }
                            else if(Global.reserve[Global.channel] <= 0) {
                                new Dialog() {
                                    protected void result(Object object) {
                                        if(((Boolean)object).booleanValue()) {
                                            GameScreen.access$21(GameScreen.this, false);
                                            GameScreen.bReserving = true;
                                            GameScreen.socketWrite("G080\t" + Global.id + "\t" + String.valueOf(Global.channel) + "\t" + String.valueOf(Global.machineNo[Global.channel]) + "\t" + "1" + "\t" + "0");
                                        }
                                    }
                                }.text("[" + String.valueOf(Global.machineNo[Global.channel]) + "]?? ?????? ?????????????????").button("   ??   ", Boolean.valueOf(true)).button("??????", Boolean.valueOf(false)).key(v4, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(GameScreen.stage);
                                goto label_86;
                            }
                            else {
                                new Dialog() {
                                    protected void result(Object object) {
                                        if(((Boolean)object).booleanValue()) {
                                            GameScreen.access$21(GameScreen.this, false);
                                            GameScreen.bReserving = true;
                                            GameScreen.socketWrite("G080\t" + Global.id + "\t" + String.valueOf(Global.channel) + "\t" + String.valueOf(Global.machineNo[Global.channel]) + "\t" + "0" + "\t" + "0");
                                        }
                                    }
                                }.text("[" + String.valueOf(Global.machineNo[Global.channel]) + "]?? ?????? ?????? ?????????????????").button("   ??   ", Boolean.valueOf(true)).button("??????", Boolean.valueOf(false)).key(v4, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(GameScreen.stage);
                                goto label_86;
                            }
                        }
                        else {
                            return;
                        }
                    }
                    else if(!Util.isTouched(Res.btnCoin.getBoundingRectangle(), v8.x, v8.y)) {
                        goto label_86;
                    }
                    else if(!this.bCanInsert10000) {
                        return;
                    }
                    else if(Dialog.count <= 0) {
                        this.clickEffect(114, 332, v5);
                        if(Global.money < 10000 && Global.money > 0) {
                            this.showMessage("?????????? ??????????.\n?????? ??????????????.");
                            return;
                        }

                        if(Global.money >= 10000) {
                            goto label_322;
                        }

                        this.showMessage("?????????? ??????????.");
                        return;
                    label_322:
                        if("member".equals(Global.cls)) {
                            goto label_329;
                        }

                        this.showMessage("?????? ?????? ?????? ???? ?? ?? ????????.");
                        return;
                    label_329:
                        if(Global.machineNo[Global.channel] > 0) {
                            goto label_336;
                        }

                        this.showMessage("[????????]?????? ???????? ?????? ???? ??????????.");
                        return;
                    label_336:
                        new Dialog() {
                            protected void result(Object object) {
                                if(((Boolean)object).booleanValue()) {
                                    GameScreen.access$22(GameScreen.this, false);
                                    GameScreen.socketWrite("G050\t" + Global.id + "\t" + Global.machineNo[Global.channel]);
                                }
                            }
                        }.text("[" + Global.machineNo[Global.channel] + "]?? ?????? ?????? ?????????????????").button("   ??   ", Boolean.valueOf(true)).button("??????", Boolean.valueOf(false)).key(v4, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(GameScreen.stage);
                        goto label_86;
                    }
                    else {
                        return;
                    }
                }

                if(Dialog.count > 0) {
                    return;
                }

                this.clickEffect(700, 325, v5);
                Res.sndAlram.play();
                this.switchChannel();
            }
            else if(Dialog.count > 0) {
                return;
            }
            else if(this.bCanSwitchMoney) {
                this.clickEffect(111, 190, v5);
                if(Global.gift[Global.channel] > 0) {
                    String v7 = "";
                    if(Global.machineNo[Global.channel] > 0) {
                        v7 = "[" + Global.machineNo[Global.channel] + "]?? ?????? ";
                    }

                    new Dialog() {
                        protected void result(Object object) {
                            if(((Boolean)object).booleanValue()) {
                                GameScreen.access$20(GameScreen.this, false);
                                GameScreen.socketWrite("G060\t" + Global.id + "\t" + Global.machineNo[Global.channel] + "\t" + Global.channel + "\t" + Global.gift[Global.channel]);
                            }
                        }
                    }.text(String.valueOf(v7) + "???????? ?????????? ?????????????????").button("   ??   ", Boolean.valueOf(true)).button("??????", Boolean.valueOf(false)).key(v4, Boolean.valueOf(false)).key(131, Boolean.valueOf(false)).show(GameScreen.stage);
                }
                else if(Global.machineNo[Global.channel] > 0) {
                    this.showMessage("[" + Global.machineNo[Global.channel] + "]?? ?????? ?????? ???????? ????????.");
                    return;
                }
                else {
                    this.showMessage("?????? ???????? ????????.");
                    return;
                }
            }
            else {
                return;
            }

        label_86:
            if(!Util.isTouched(Res.btnExitGame.getBoundingRectangle(), v8.x, v8.y)) {
                return;
            }

            if(Dialog.count > 0) {
                return;
            }

            if(Global.bAskingGameExit) {
                return;
            }

            if(Global.bTerminating) {
                return;
            }

            this.clickEffect(744, 48, v5);
            this.askExitGame();
            return;
        label_364:
            if(Util.isTouched(Res.imgBtnMachineOn.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnMachineOn.x, Res.imgBtnMachineOn.y, Res.imgBtnMachineOn.width, Res.imgBtnMachineOn.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.changeMenuItem(5);
            }
            else if(Util.isTouched(Res.imgBtnChargeOn.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnChargeOn.x, Res.imgBtnChargeOn.y, Res.imgBtnChargeOn.width, Res.imgBtnChargeOn.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.changeMenuItem(1);
            }
            else if(Util.isTouched(Res.imgBtnExchangeOn.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnExchangeOn.x, Res.imgBtnExchangeOn.y, Res.imgBtnExchangeOn.width, Res.imgBtnExchangeOn.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.changeMenuItem(2);
            }
            else if(Util.isTouched(Res.imgBtnHelpOn.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnHelpOn.x, Res.imgBtnHelpOn.y, Res.imgBtnHelpOn.width, Res.imgBtnHelpOn.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.changeMenuItem(3);
            }
            else if(Util.isTouched(Res.imgBtnAskOn.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnAskOn.x, Res.imgBtnAskOn.y, Res.imgBtnAskOn.width, Res.imgBtnAskOn.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.changeMenuItem(4);
                GameScreen.socketWrite("G140\t" + Global.id + "\t");
            }
            else if(Util.isTouched(Res.imgBtnCloseMenu.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnCloseMenu.x, Res.imgBtnCloseMenu.y, Res.imgBtnCloseMenu.width, Res.imgBtnCloseMenu.height, v5);
                Gdx.input.setOnscreenKeyboardVisible(false);
                this.showMenu(false);
            }

            if(this.iMenuMode != 5) {
                goto label_566;
            }

            if(Util.isTouched(Res.imgChannelLeft.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgChannelLeft.x, Res.imgChannelLeft.y, Res.imgChannelLeft.width, Res.imgChannelLeft.height, v5);
                this.machineListRequest(-1);
            }

            if(Util.isTouched(Res.imgChannelRight.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgChannelRight.x, Res.imgChannelRight.y, Res.imgChannelRight.width, Res.imgChannelRight.height, v5);
                this.machineListRequest(1);
            }

            int v6;
            for(v6 = 0; true; ++v6) {
                if(v6 >= 16) {
                    return;
                }

                if(!Util.isTouched(Res.machineBack[v6].getBoundingRectangle(), v8.x, v8.y)) {
                    goto label_564;
                }

                this.clickEffect(Res.machineBack[v6].x, Res.machineBack[v6].y, Res.machineBack[v6].width, Res.machineBack[v6].height, v5);
                this.onMachineClicked(v6, this.lbMachineNo[v6].getText().toString().trim());
                return;
            label_564:
            }

        label_566:
            if(this.iMenuMode != 1) {
                goto label_602;
            }

            if(Util.isTouched(Res.imgBtnMoneyMan.getBoundingRectangle(), v8.x, v8.y)) {
                Gdx.input.getTextInput(new TextInputListener() {
                    public void canceled() {
                    }

                    public void input(String text) {
                        if(text != null) {
                            text = text.trim();
                        }

                        Gdx.input.setOnscreenKeyboardVisible(false);
                        GameScreen.this.lbChargeMan.setText(((CharSequence)text));
                    }
                }, "?????????? ???????? ?? OK ?????? ??????????.", this.lbChargeMan.getText().toString().trim());
            }

            if(!Util.isTouched(Res.imgBtnCharge.getBoundingRectangle(), v8.x, v8.y)) {
                return;
            }

            this.clickEffect(Res.imgBtnCharge.x, Res.imgBtnCharge.y, Res.imgBtnCharge.width, Res.imgBtnCharge.height, v5);
            Gdx.input.setOnscreenKeyboardVisible(false);
            this.charge();
            return;
        label_602:
            if(this.iMenuMode != 2) {
                goto label_653;
            }

            if(Util.isTouched(Res.imgBtnExchangeBank.getBoundingRectangle(), v8.x, v8.y)) {
                Gdx.input.getTextInput(new TextInputListener() {
                    public void canceled() {
                    }

                    public void input(String text) {
                        if(text != null) {
                            text = text.trim();
                        }

                        GameScreen.this.lbExchangeBank.setText(((CharSequence)text));
                    }
                }, "???????? ???????? ?? OK ?????? ??????????.", this.lbExchangeBank.getText().toString().trim());
            }

            if(Util.isTouched(Res.imgBtnExchangeOwner.getBoundingRectangle(), v8.x, v8.y)) {
                Gdx.input.getTextInput(new TextInputListener() {
                    public void canceled() {
                    }

                    public void input(String text) {
                        if(text != null) {
                            text = text.trim();
                        }

                        GameScreen.this.lbExchangeOwner.setText(((CharSequence)text));
                    }
                }, "???????? ???????? ?? OK ?????? ??????????.", this.lbExchangeOwner.getText().toString().trim());
            }

            if(!Util.isTouched(Res.imgBtnExchange.getBoundingRectangle(), v8.x, v8.y)) {
                return;
            }

            this.clickEffect(Res.imgBtnExchange.x, Res.imgBtnExchange.y, Res.imgBtnExchange.width, Res.imgBtnExchange.height, v5);
            Gdx.input.setOnscreenKeyboardVisible(false);
            this.exchange();
            return;
        label_653:
            if(this.iMenuMode != 3) {
                goto label_705;
            }

            if(Util.isTouched(Res.imgBtnHelpUp.getBoundingRectangle(), v8.x, v8.y)) {
                this.clickEffect(Res.imgBtnHelpUp.x, Res.imgBtnHelpUp.y, Res.imgBtnHelpUp.width, Res.imgBtnHelpUp.height, v5);
                this.scpHelp.setScrollX(0f);
                this.scpHelp.setScrollY(this.scpHelp.getScrollY() - 200f);
            }

            if(!Util.isTouched(Res.imgBtnHelpDown.getBoundingRectangle(), v8.x, v8.y)) {
                return;
            }

            this.clickEffect(Res.imgBtnHelpDown.x, Res.imgBtnHelpDown.y, Res.imgBtnHelpDown.width, Res.imgBtnHelpDown.height, v5);
            this.scpHelp.setScrollX(0f);
            this.scpHelp.setScrollY(this.scpHelp.getScrollY() + 200f);
            return;
        label_705:
            if(this.iMenuMode != 4) {
                return;
            }

            if(!Util.isTouched(Res.imgBtnAskInput.getBoundingRectangle(), v8.x, v8.y)) {
                return;
            }

            this.clickEffect(Res.imgBtnAskInput.x, Res.imgBtnAskInput.y, Res.imgBtnAskInput.width, Res.imgBtnAskInput.height, v5);
            Gdx.input.getTextInput(new TextInputListener() {
                public void canceled() {
                }

                public void input(String text) {
                    int v2 = 100;
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    if(text == null) {
                        text = "";
                    }

                    text = text.trim();
                    if(!"".equals(text)) {
                        if(text.length() > v2) {
                            text = text.substring(0, v2);
                        }

                        GameScreen.this.sendAsk(text);
                    }
                }
            }, "?????????? ???????? ?? OK ?????? ??????????.", "");
        }
    }

    private void processMsg() {  // has try-catch handlers
        if(GameScreen.lsGameRead.size() <= 0) {
            return;
        }

        try {
            Object v3 = GameScreen.lsGameRead.get(0);
            GameScreen.lsGameRead.remove(0);
            String[] v0 = ((String)v3).split("\t");
            String v1 = v0[0];
            if(!"G520".equals(v1)) {
                goto label_18;
            }

            this.fn_game_info(v0);
            return;
        label_18:
            if(!"G526".equals(v1)) {
                goto label_29;
            }

            this.fn_end_scenario_success(v0);
            return;
        label_29:
            if(!"G527".equals(v1)) {
                goto label_34;
            }

            this.fn_end_scenario_failure(v0);
            return;
        label_34:
            if(!"G522".equals(v1)) {
                goto label_39;
            }

            this.fn_scenario1(v0);
            return;
        label_39:
            if(!"G523".equals(v1)) {
                goto label_44;
            }

            this.fn_scenario2(v0);
            return;
        label_44:
            if(!"G525".equals(v1)) {
                goto label_49;
            }

            this.fn_game_info_failure(v0);
            return;
        label_49:
            if(!"G530".equals(v1)) {
                goto label_54;
            }

            this.fn_scenario_success(v0);
            return;
        label_54:
            if(!"G540".equals(v1)) {
                goto label_59;
            }

            this.fn_scenario_failure(v0);
            return;
        label_59:
            if(!"show_ui".equals(v1)) {
                goto label_65;
            }

            GameScreen.sendMsgToAndroid("show_ui");
            return;
        label_65:
            if(!"hide_ui".equals(v1)) {
                goto label_71;
            }

            GameScreen.sendMsgToAndroid("hide_ui");
            return;
        label_71:
            if(!"G550".equals(v1)) {
                goto label_76;
            }

            this.fn_machine_list(v0);
            return;
        label_76:
            if(!"G560".equals(v1)) {
                goto label_81;
            }

            this.fn_machine_select(v0);
            return;
        label_81:
            if(!"G570".equals(v1)) {
                goto label_86;
            }

            this.fn_machine_select_failure(v0);
            return;
        label_86:
            if(!"G580".equals(v1)) {
                goto label_91;
            }

            this.fn_insert_10000_success(v0);
            return;
        label_91:
            if(!"G590".equals(v1)) {
                goto label_96;
            }

            this.fn_insert_10000_failure(v0);
            return;
        label_96:
            if(!"G610".equals(v1)) {
                goto label_101;
            }

            this.fn_switch_money_success(v0);
            return;
        label_101:
            if(!"G620".equals(v1)) {
                goto label_106;
            }

            this.fn_switch_money_failure(v0);
            return;
        label_106:
            if(!"G710".equals(v1)) {
                goto label_111;
            }

            this.fn_charge_completed(v0);
            return;
        label_111:
            if(!"G715".equals(v1)) {
                goto label_116;
            }

            this.fn_exchange_completed(v0);
            return;
        label_116:
            if(!"G600".equals(v1)) {
                goto label_121;
            }

            this.fn_coin_info(v0);
            return;
        label_121:
            if(!"G630".equals(v1)) {
                goto label_126;
            }

            this.fn_switch_machine_success(v0);
            return;
        label_126:
            if(!"G640".equals(v1)) {
                goto label_131;
            }

            this.fn_switch_machine_failure(v0);
            return;
        label_131:
            if(!"G650".equals(v1)) {
                goto label_136;
            }

            this.fn_reserve_machine_success(v0);
            return;
        label_136:
            if(!"G660".equals(v1)) {
                goto label_141;
            }

            this.fn_reserve_machine_failure(v0);
            return;
        label_141:
            if(!"G700".equals(v1)) {
                goto label_146;
            }

            this.fn_confirm_reserve(v0);
            return;
        label_146:
            if(!"G680".equals(v1)) {
                goto label_151;
            }

            this.fn_announce(v0);
            return;
        label_151:
            if(!"G690".equals(v1)) {
                goto label_156;
            }

            this.fn_private_announce(v0);
            return;
        label_156:
            if(!"G740".equals(v1)) {
                goto label_161;
            }

            this.fn_ask(v0);
            return;
        label_161:
            if(!"G790".equals(v1)) {
                goto label_166;
            }

            this.fn_bank(v0);
            return;
        label_166:
            if(!"G800".equals(v1)) {
                goto label_171;
            }

            this.fn_report(v0);
            return;
        label_171:
            Util.sysout("ERROR", "GameScreen->processMsg", "Undefined Cmd.", ((String)v3));
        }
        catch(Exception v2) {
            Util.sysout("ERROR", "GameScreen->processMsg", Util.getExceptionMessage(v2));
        }
    }

    private void processScenario(int iChannel) {  // has try-catch handlers
        try {
            if(Global.machineNo[iChannel] <= 0) {
                return;
            }

            if(Global.spin[iChannel] <= 0) {
                return;
            }

            if(this.fNextScenarioDelay[iChannel] > 0f) {
                return;
            }

            if(this.isReelRunning(iChannel)) {
                return;
            }

            if(Global.reserve[iChannel] > 0) {
                return;
            }

            if(this.bVideo1Playing[iChannel]) {
                return;
            }

            if(this.bVideo2Playing[iChannel]) {
                return;
            }

            if(this.bVideo3Playing[iChannel]) {
                return;
            }

            if(Global.bAskingGameExit) {
                return;
            }

            if(Global.bTerminating) {
                return;
            }

            if(this.bSwitching) {
                return;
            }

            if(GameScreen.bCannonCelemony) {
                return;
            }

            if(this.bEnding[iChannel]) {
                return;
            }

            if(!this.bWaitingScenario[iChannel]) {
                goto label_39;
            }

            return;
        label_39:
            if(this.bStopScenarioForTest) {
                return;
            }

            if(this.lsScenario[iChannel].size() <= 0 && (this.bCanRequestScenario[iChannel])) {
                this.requestScenario(iChannel);
                return;
            }

            if(this.lsScenario[iChannel].size() <= 0) {
                return;
            }

            Object v9 = this.lsScenario[iChannel].get(0);
            if(iChannel == Global.channel && ("1".equals(Global.viewScenario))) {
                this.addMsg(((String)v9), Color.WHITE);
            }

            String v11 = this.getValue("T", ((String)v9));
            if(!"I".equals(v11)) {
                goto label_98;
            }

            if(!this.reelIdle(iChannel, this.getValue("H", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            this.setHintCannonRobot(iChannel, this.getValue("C", ((String)v9)), this.getValue("R", ((String)v9)));
            return;
        label_98:
            if(!"H".equals(v11)) {
                goto label_122;
            }

            if(!this.reelIdle(iChannel, this.getValue("H", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            this.setHintCannonRobot(iChannel, this.getValue("C", ((String)v9)), this.getValue("R", ((String)v9)));
            return;
        label_122:
            if(!"F".equals(v11)) {
                goto label_154;
            }

            String v10 = this.getValue("N", ((String)v9));
            if(!this.reelReachFailure(iChannel, this.getNo(v10), this.getTCBX(v10), this.getValue("H", ((String)v9)), this.getValue("F", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            this.setHintCannonRobot(iChannel, this.getValue("C", ((String)v9)), this.getValue("R", ((String)v9)));
            return;
        label_154:
            if(!"S".equals(v11)) {
                goto label_189;
            }

            v10 = this.getValue("N", ((String)v9));
            String v6 = this.getValue("D", ((String)v9));
            if(!this.reelReachSuccess(iChannel, this.getNo(v10), this.getResultNoFromDestroyVideo(v6), this.getTCBX(v10), this.getValue("H", ((String)v9)), v6, this.getValue("F", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            this.setHintCannonRobot(iChannel, this.getValue("C", ((String)v9)), this.getValue("R", ((String)v9)));
            return;
        label_189:
            if(!"M".equals(v11)) {
                goto label_213;
            }

            this.fNextScenarioDelay[iChannel] = 1000000000f;
            this.playVideo3(iChannel, this.getValue("V", ((String)v9)), this.getValue("P", ((String)v9)), "");
            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            return;
        label_213:
            if(!"W".equals(v11)) {
                goto label_238;
            }

            this.fNextScenarioDelay[iChannel] = 1000000000f;
            this.playVideo3(iChannel, this.getValue("V", ((String)v9)), this.getValue("P", ((String)v9)), this.getValue("N", ((String)v9)));
            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            return;
        label_238:
            if(!"P".equals(v11)) {
                goto label_263;
            }

            if(!this.reelPushButton(iChannel, this.getNo(this.getValue("N", ((String)v9))), this.getTCBX(this.getValue("N", ((String)v9))), this.getValue("V", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
            return;
        label_263:
            if(!"C".equals(v11)) {
                return;
            }

            if(!this.reelPushButton(iChannel, this.getNo(this.getValue("N", ((String)v9))), this.getTCBX(this.getValue("N", ((String)v9))), this.getValue("V", ((String)v9)))) {
                return;
            }

            this.setBackGround(iChannel, this.getValue("S", ((String)v9)), this.getValue("W", ((String)v9)), this.getValue("G", ((String)v9)), this.getValue("Z", ((String)v9)));
        }
        catch(Exception v8) {
            Util.sysout("ERROR", "GameScreen->processScenario(8)", Util.getExceptionMessage(v8));
        }
    }

    private boolean reelIdle(int _iChannel, String _sVideoRaw) {
        int v7 = 6;
        float v6 = 1.7f;
        int v5 = 2;
        boolean v1 = false;
        if(!this.isReelRunning(_iChannel) && Global.reserve[_iChannel] <= 0 && Global.spin[_iChannel] > 0 && !Global.bAskingGameExit && !Global.bTerminating && this.fNextScenarioDelay[_iChannel] <= 0f && this.iFireType[_iChannel] <= 0 && !GameScreen.bReserving && !this.bSwitching && !this.bEnding[_iChannel] && !this.bWaitingScenario[_iChannel]) {
            this.iniReel(_iChannel);
            this.reelMode[_iChannel] = 0;
            this.bReelCenter[_iChannel][0] = Util.randomTrue(20);
            this.bReelCenter[_iChannel][1] = Util.randomTrue(20);
            this.bReelCenter[_iChannel][v5] = Util.randomTrue(20);
            this.setReelIdleTargetNo(_iChannel);
            if(!"".equals(_sVideoRaw)) {
                this.fVideo1StartTime[_iChannel] = v6;
                this.sVideo1Raw[_iChannel] = _sVideoRaw;
                this.bPlayVideo1[_iChannel] = true;
                this.fReelDeceleratingStartTime[_iChannel][0] = 60f;
                this.fReelDeceleratingStartTime[_iChannel][1] = 62f;
                this.fReelDeceleratingStartTime[_iChannel][v5] = 61f;
                this.fReelPoppingStartTime[_iChannel][0] = 60f;
                this.fReelPoppingStartTime[_iChannel][1] = 62f;
                this.fReelPoppingStartTime[_iChannel][v5] = 61f;
            }
            else {
                if(!Res.imgBatterBack.visible && !Util.randomTrue(10)) {
                    float v0 = 0f;
                    if(Util.randomTrue(12)) {
                        v0 = 1.5f;
                    }

                    this.fReelDeceleratingStartTime[_iChannel][0] = v6 + v0;
                    this.fReelDeceleratingStartTime[_iChannel][1] = 2.5f + v0;
                    this.fReelDeceleratingStartTime[_iChannel][v5] = 2.1f + v0;
                    this.fReelPoppingStartTime[_iChannel][0] = v6 + v0;
                    this.fReelPoppingStartTime[_iChannel][1] = 2.5f + v0;
                    this.fReelPoppingStartTime[_iChannel][v5] = 2.1f + v0;
                    goto label_87;
                }

                this.iMaxPopCount[_iChannel][0] = v7;
                this.iMaxPopCount[_iChannel][1] = v7;
                this.iMaxPopCount[_iChannel][v5] = v7;
                this.fReelDeceleratingStartTime[_iChannel][0] = 1.4f;
                this.fReelDeceleratingStartTime[_iChannel][1] = v6;
                this.fReelDeceleratingStartTime[_iChannel][v5] = 1.55f;
                this.fReelPoppingStartTime[_iChannel][0] = 1.4f;
                this.fReelPoppingStartTime[_iChannel][1] = v6;
                this.fReelPoppingStartTime[_iChannel][v5] = 1.55f;
            }

        label_87:
            v1 = true;
        }

        return v1;
    }

    private boolean reelPushButton(int _iChannel, int _iTargetNo, int _iTCBX, String _sVideoRaw) {
        float v6 = 60f;
        int v5 = 3;
        int v4 = 2;
        boolean v0 = false;
        if(!this.isReelRunning(_iChannel) && Global.reserve[_iChannel] <= 0 && Global.spin[_iChannel] > 0 && !Global.bAskingGameExit && !Global.bTerminating && this.fNextScenarioDelay[_iChannel] <= 0f && this.iFireType[_iChannel] <= 0 && !GameScreen.bReserving && !this.bSwitching && !this.bEnding[_iChannel] && !this.bWaitingScenario[_iChannel]) {
            this.iniReel(_iChannel);
            this.reelMode[_iChannel] = v5;
            _iTargetNo = this.getCardNo(_iTargetNo);
            this.iTCBX[_iChannel] = _iTCBX;
            if(_iTCBX == v5) {
                this.iTargetCardNo[_iChannel][0] = this.getCardNo(_iTargetNo - 1);
                this.iTargetCardNo[_iChannel][1] = this.getCardNo(_iTargetNo + 1);
                this.iTargetCardNo[_iChannel][v4] = this.getCardNo(_iTargetNo + 1);
            }
            else {
                if(_iTCBX != 1 && _iTCBX != v4) {
                    if(_iTCBX == 4) {
                        this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                        this.iTargetCardNo[_iChannel][1] = _iTargetNo;
                        this.iTargetCardNo[_iChannel][v4] = this.getCardNo(_iTargetNo + 1);
                        goto label_57;
                    }
                    else {
                        goto label_57;
                    }
                }

                this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                this.iTargetCardNo[_iChannel][1] = _iTargetNo;
                this.iTargetCardNo[_iChannel][v4] = _iTargetNo;
            }

        label_57:
            if(_iTCBX == 1 || _iTCBX == v5) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = false;
                this.bReelCenter[_iChannel][v4] = false;
            }
            else if(_iTCBX == v4) {
                this.bReelCenter[_iChannel][0] = true;
                this.bReelCenter[_iChannel][1] = true;
                this.bReelCenter[_iChannel][v4] = true;
            }
            else if(_iTCBX == 4) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = true;
                this.bReelCenter[_iChannel][v4] = false;
            }

            this.fVideo1StartTime[_iChannel] = 1.7f;
            this.sVideo1Raw[_iChannel] = _sVideoRaw;
            this.bPlayVideo1[_iChannel] = true;
            this.fReelDeceleratingStartTime[_iChannel][0] = v6;
            this.fReelDeceleratingStartTime[_iChannel][1] = 62f;
            this.fReelDeceleratingStartTime[_iChannel][v4] = 61f;
            this.fReelPoppingStartTime[_iChannel][0] = v6;
            this.fReelPoppingStartTime[_iChannel][1] = 62f;
            this.fReelPoppingStartTime[_iChannel][v4] = 61f;
            v0 = true;
        }

        return v0;
    }

    private boolean reelReachFailure(int _iChannel, int _iTargetNo, int _iTCBX, String _sVideoRaw, String _sFire) {
        int v6 = 8;
        int v4 = 3;
        int v5 = 2;
        boolean v0 = false;
        if(!this.isReelRunning(_iChannel) && Global.reserve[_iChannel] <= 0 && Global.spin[_iChannel] > 0 && !Global.bAskingGameExit && !Global.bTerminating && this.fNextScenarioDelay[_iChannel] <= 0f && this.iFireType[_iChannel] <= 0 && !GameScreen.bReserving && !this.bSwitching && !this.bEnding[_iChannel] && !this.bWaitingScenario[_iChannel]) {
            this.iniReel(_iChannel);
            if(_sVideoRaw == null || ("".equals(_sVideoRaw.trim()))) {
                _sVideoRaw = "m124";
            }

            this.reelMode[_iChannel] = 1;
            _iTargetNo = this.getCardNo(_iTargetNo);
            this.iTCBX[_iChannel] = _iTCBX;
            if(_iTCBX == v4) {
                this.iTargetCardNo[_iChannel][0] = this.getCardNo(_iTargetNo - 1);
                this.iTargetCardNo[_iChannel][1] = this.getExclusiveNo(this.getCardNo(_iTargetNo + 1));
                this.iTargetCardNo[_iChannel][v5] = this.getCardNo(_iTargetNo + 1);
            }
            else {
                if(_iTCBX != 1 && _iTCBX != v5) {
                    if(_iTCBX == 4) {
                        this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                        this.iTargetCardNo[_iChannel][1] = this.getExclusiveNo(_iTargetNo, this.getCardNo(_iTargetNo + 1));
                        this.iTargetCardNo[_iChannel][v5] = this.getCardNo(_iTargetNo + 1);
                        goto label_64;
                    }
                    else {
                        goto label_64;
                    }
                }

                this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                this.iTargetCardNo[_iChannel][1] = this.getExclusiveNo(_iTargetNo);
                this.iTargetCardNo[_iChannel][v5] = _iTargetNo;
            }

        label_64:
            if(_iTCBX == 1 || _iTCBX == v4) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = Util.randomTrue(50);
                this.bReelCenter[_iChannel][v5] = false;
            }
            else if(_iTCBX == v5) {
                this.bReelCenter[_iChannel][0] = true;
                this.bReelCenter[_iChannel][1] = Util.randomTrue(50);
                this.bReelCenter[_iChannel][v5] = true;
            }
            else if(_iTCBX == 4) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = true;
                this.bReelCenter[_iChannel][v5] = false;
            }

            this.iPostTargetCardNo[_iChannel][0] = this.iTargetCardNo[_iChannel][0];
            this.iPostTargetCardNo[_iChannel][1] = this.iTargetCardNo[_iChannel][1];
            this.iPostTargetCardNo[_iChannel][v5] = this.iTargetCardNo[_iChannel][v5];
            this.bPostReelCenter[_iChannel][0] = this.bReelCenter[_iChannel][0];
            this.bPostReelCenter[_iChannel][1] = this.bReelCenter[_iChannel][1];
            this.bPostReelCenter[_iChannel][v5] = this.bReelCenter[_iChannel][v5];
            if(Global.channel != _iChannel) {
                _sFire = "";
            }

            this.fPoppingDelay[_iChannel] = 0f;
            if("123".equals(_sFire)) {
                if(Util.randomTrue(70)) {
                    this.iTargetCardNo[_iChannel][0] = Util.randomInt(1, v6);
                    this.iTargetCardNo[_iChannel][1] = this.iTargetCardNo[_iChannel][0];
                    this.iTargetCardNo[_iChannel][v5] = this.iTargetCardNo[_iChannel][0];
                    this.bReelCenter[_iChannel][0] = false;
                    this.bReelCenter[_iChannel][1] = false;
                    this.bReelCenter[_iChannel][v5] = true;
                    this.iFireType[_iChannel] = 7;
                }
                else {
                    this.iTargetCardNo[_iChannel][0] = Util.randomInt(1, v6);
                    this.iTargetCardNo[_iChannel][1] = this.getCardNo(this.iTargetCardNo[_iChannel][0] + 2);
                    this.iTargetCardNo[_iChannel][v5] = this.getCardNo(this.iTargetCardNo[_iChannel][0] + 1);
                    this.bReelCenter[_iChannel][0] = false;
                    this.bReelCenter[_iChannel][1] = false;
                    this.bReelCenter[_iChannel][v5] = true;
                    this.iFireType[_iChannel] = 7;
                }
            }
            else if("1".equals(_sFire)) {
                this.fPoppingDelay[_iChannel] = 1000000000f;
                if(_iTCBX == v4) {
                    this.iTargetCardNo[_iChannel][0] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 1), this.getCardNo(_iTargetNo + 1), _iTargetNo);
                }
                else {
                    this.iTargetCardNo[_iChannel][0] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 2), _iTargetNo, this.getCardNo(_iTargetNo + 1));
                }

                this.bReelCenter[_iChannel][0] = true;
                this.iFireType[_iChannel] = 5;
            }
            else {
                if(!"3".equals(_sFire)) {
                    goto label_153;
                }

                this.fPoppingDelay[_iChannel] = 1000000000f;
                if(_iTCBX == v4) {
                    this.iTargetCardNo[_iChannel][v5] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 1), this.getCardNo(_iTargetNo + 1), _iTargetNo);
                }
                else {
                    this.iTargetCardNo[_iChannel][v5] = this.getExclusiveNo(this.getCardNo(_iTargetNo + 2), _iTargetNo, this.getCardNo(_iTargetNo + 1));
                }

                this.bReelCenter[_iChannel][v5] = true;
                this.iFireType[_iChannel] = 6;
            }

        label_153:
            this.iMaxPopCount[_iChannel][0] = 6;
            if(this.iFireType[_iChannel] == 7) {
                this.iMaxPopCount[_iChannel][1] = v6;
            }
            else {
                this.iMaxPopCount[_iChannel][1] = 16;
            }

            this.iMaxPopCount[_iChannel][v5] = v6;
            if(!"".equals(_sVideoRaw)) {
                this.fVideo1StartTime[_iChannel] = 1.7f;
                this.sVideo1Raw[_iChannel] = _sVideoRaw;
                this.bPlayVideo1[_iChannel] = true;
                this.fReelDeceleratingStartTime[_iChannel][0] = 60f;
                this.fReelDeceleratingStartTime[_iChannel][1] = 62f + this.fPoppingDelay[_iChannel];
                this.fReelDeceleratingStartTime[_iChannel][v5] = 61f;
                this.fReelPoppingStartTime[_iChannel][0] = 60f;
                this.fReelPoppingStartTime[_iChannel][1] = 62f + this.fPoppingDelay[_iChannel];
                this.fReelPoppingStartTime[_iChannel][v5] = 61f;
            }
            else {
                this.fReelDeceleratingStartTime[_iChannel][0] = 1.7f;
                this.fReelDeceleratingStartTime[_iChannel][1] = 2.5f + this.fPoppingDelay[_iChannel];
                this.fReelDeceleratingStartTime[_iChannel][v5] = 2.1f;
                this.fReelPoppingStartTime[_iChannel][0] = 1.7f;
                this.fReelPoppingStartTime[_iChannel][1] = 2.5f + this.fPoppingDelay[_iChannel];
                this.fReelPoppingStartTime[_iChannel][v5] = 2.1f;
            }

            v0 = true;
        }

        return v0;
    }

    private boolean reelReachSuccess(int _iChannel, int _iTargetNo, int _iResultNo, int _iTCBX, String _sVideo1Raw, String _sVideo2Raw, String _sFire) {  // has try-catch handlers
        if(!this.isReelRunning(_iChannel)) {
            goto label_4;
        }

        boolean v2 = false;
        goto label_3;
    label_4:
        if(Global.reserve[_iChannel] <= 0) {
            goto label_9;
        }

        v2 = false;
        goto label_3;
    label_9:
        if(Global.spin[_iChannel] > 0) {
            goto label_14;
        }

        v2 = false;
        goto label_3;
    label_14:
        if(!Global.bAskingGameExit) {
            goto label_18;
        }

        v2 = false;
        goto label_3;
    label_18:
        if(!Global.bTerminating) {
            goto label_22;
        }

        v2 = false;
        goto label_3;
    label_22:
        if(this.fNextScenarioDelay[_iChannel] <= 0f) {
            goto label_28;
        }

        v2 = false;
        goto label_3;
    label_28:
        if(this.iFireType[_iChannel] <= 0) {
            goto label_33;
        }

        v2 = false;
        goto label_3;
    label_33:
        if(!GameScreen.bReserving && !this.bSwitching && !this.bEnding[_iChannel] && !this.bWaitingScenario[_iChannel]) {
            this.iniReel(_iChannel);
            if(_sVideo1Raw == null || ("".equals(_sVideo1Raw.trim()))) {
                _sVideo1Raw = "m124";
            }

            try {
                this.fsPostCardPos[_iChannel] = _sVideo2Raw.split("_")[3];
            }
            catch(Exception v0) {
                Util.sysout("ERROR", "GameScreen->reelReachSuccess(1)", "Unexcpected CardPos", _sVideo2Raw);
            }

            this.reelMode[_iChannel] = 2;
            _iTargetNo = this.getCardNo(_iTargetNo);
            if(_iResultNo == 0) {
                _iResultNo = this.getExclusiveNo(_iTargetNo, this.getCardNo(_iTargetNo + 1), this.getCardNo(_iTargetNo - 1));
            }

            this.iTCBX[_iChannel] = _iTCBX;
            if(_iTCBX == 3) {
                this.iTargetCardNo[_iChannel][0] = this.getCardNo(_iTargetNo - 1);
                this.iTargetCardNo[_iChannel][1] = this.getCardNo(_iResultNo + 1);
                this.iTargetCardNo[_iChannel][2] = this.getCardNo(_iTargetNo + 1);
            }
            else {
                if(_iTCBX != 1 && _iTCBX != 2) {
                    if(_iTCBX == 4) {
                        this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                        this.iTargetCardNo[_iChannel][1] = _iResultNo;
                        this.iTargetCardNo[_iChannel][2] = this.getCardNo(_iTargetNo + 1);
                        goto label_90;
                    }
                    else {
                        goto label_90;
                    }
                }

                this.iTargetCardNo[_iChannel][0] = _iTargetNo;
                this.iTargetCardNo[_iChannel][1] = _iResultNo;
                this.iTargetCardNo[_iChannel][2] = _iTargetNo;
            }

        label_90:
            if(_iTCBX == 1 || _iTCBX == 3) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = false;
                this.bReelCenter[_iChannel][2] = false;
            }
            else if(_iTCBX == 2) {
                this.bReelCenter[_iChannel][0] = true;
                this.bReelCenter[_iChannel][1] = true;
                this.bReelCenter[_iChannel][2] = true;
            }
            else if(_iTCBX == 4) {
                this.bReelCenter[_iChannel][0] = false;
                this.bReelCenter[_iChannel][1] = true;
                this.bReelCenter[_iChannel][2] = false;
            }

            this.iPostTargetCardNo[_iChannel][0] = this.iTargetCardNo[_iChannel][0];
            this.iPostTargetCardNo[_iChannel][1] = this.iTargetCardNo[_iChannel][1];
            this.iPostTargetCardNo[_iChannel][2] = this.iTargetCardNo[_iChannel][2];
            this.bPostReelCenter[_iChannel][0] = this.bReelCenter[_iChannel][0];
            this.bPostReelCenter[_iChannel][1] = this.bReelCenter[_iChannel][1];
            this.bPostReelCenter[_iChannel][2] = this.bReelCenter[_iChannel][2];
            if(Global.channel != _iChannel) {
                _sFire = "";
            }

            this.fPoppingDelay[_iChannel] = 0f;
            if("123".equals(_sFire)) {
                if(Util.randomTrue(70)) {
                    this.iTargetCardNo[_iChannel][0] = Util.randomInt(1, 8);
                    this.iTargetCardNo[_iChannel][1] = this.iTargetCardNo[_iChannel][0];
                    this.iTargetCardNo[_iChannel][2] = this.iTargetCardNo[_iChannel][0];
                    this.bReelCenter[_iChannel][0] = false;
                    this.bReelCenter[_iChannel][1] = false;
                    this.bReelCenter[_iChannel][2] = true;
                    this.iFireType[_iChannel] = 7;
                }
                else {
                    this.iTargetCardNo[_iChannel][0] = Util.randomInt(1, 8);
                    this.iTargetCardNo[_iChannel][1] = this.getCardNo(this.iTargetCardNo[_iChannel][0] + 2);
                    this.iTargetCardNo[_iChannel][2] = this.getCardNo(this.iTargetCardNo[_iChannel][0] + 1);
                    this.bReelCenter[_iChannel][0] = false;
                    this.bReelCenter[_iChannel][1] = false;
                    this.bReelCenter[_iChannel][2] = true;
                    this.iFireType[_iChannel] = 7;
                }
            }
            else if("1".equals(_sFire)) {
                this.fPoppingDelay[_iChannel] = 1000000000f;
                if(_iTCBX == 3) {
                    this.iTargetCardNo[_iChannel][0] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 1), this.getCardNo(_iTargetNo + 1), _iTargetNo);
                }
                else {
                    this.iTargetCardNo[_iChannel][0] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 2), _iTargetNo, this.getCardNo(_iTargetNo + 1));
                }

                this.bReelCenter[_iChannel][0] = true;
                this.iFireType[_iChannel] = 5;
            }
            else {
                if(!"3".equals(_sFire)) {
                    goto label_210;
                }

                this.fPoppingDelay[_iChannel] = 1000000000f;
                if(_iTCBX == 3) {
                    this.iTargetCardNo[_iChannel][2] = this.getExclusiveNo(this.getCardNo(_iTargetNo - 1), this.getCardNo(_iTargetNo + 1), _iTargetNo);
                }
                else {
                    this.iTargetCardNo[_iChannel][2] = this.getExclusiveNo(this.getCardNo(_iTargetNo + 2), _iTargetNo, this.getCardNo(_iTargetNo + 1));
                }

                this.bReelCenter[_iChannel][2] = true;
                this.iFireType[_iChannel] = 6;
            }

        label_210:
            this.iMaxPopCount[_iChannel][0] = 6;
            if(this.iFireType[_iChannel] == 7) {
                this.iMaxPopCount[_iChannel][1] = 8;
            }
            else {
                this.iMaxPopCount[_iChannel][1] = 16;
            }

            this.iMaxPopCount[_iChannel][2] = 8;
            if(!"".equals(_sVideo1Raw)) {
                this.fVideo1StartTime[_iChannel] = 1.7f;
                this.sVideo1Raw[_iChannel] = _sVideo1Raw;
                this.bPlayVideo1[_iChannel] = true;
                this.fReelDeceleratingStartTime[_iChannel][0] = 60f;
                this.fReelDeceleratingStartTime[_iChannel][1] = 62f + this.fPoppingDelay[_iChannel];
                this.fReelDeceleratingStartTime[_iChannel][2] = 61f;
                this.fReelPoppingStartTime[_iChannel][0] = 60f;
                this.fReelPoppingStartTime[_iChannel][1] = 62f + this.fPoppingDelay[_iChannel];
                this.fReelPoppingStartTime[_iChannel][2] = 61f;
            }
            else {
                this.fReelDeceleratingStartTime[_iChannel][0] = 1.7f;
                this.fReelDeceleratingStartTime[_iChannel][1] = 2.5f + this.fPoppingDelay[_iChannel];
                this.fReelDeceleratingStartTime[_iChannel][2] = 2.1f;
                this.fReelPoppingStartTime[_iChannel][0] = 1.7f;
                this.fReelPoppingStartTime[_iChannel][1] = 2.5f + this.fPoppingDelay[_iChannel];
                this.fReelPoppingStartTime[_iChannel][2] = 2.1f;
            }

            this.sVideo2Raw[_iChannel] = _sVideo2Raw;
            this.bPlayVideo2[_iChannel] = true;
            v2 = true;
            goto label_3;
        }

        v2 = false;
    label_3:
        return v2;
    }

    public void render(float _deltaTime) {
        int v10 = 2;
        float v9 = 1000000000f;
        this.deltaTime = _deltaTime;
        if(_deltaTime > 0.055556f) {
            this.deltaTime = 0.055556f;
        }

        this.elapseTime += this.deltaTime;
        GameScreen.fCelemony -= this.deltaTime;
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(16384);
        GameScreen.batch.setProjectionMatrix(this.camera.combined);
        GameScreen.color = GameScreen.batch.getColor();
        GameScreen.batch.begin();
        if((this.bGameScreenReady) && !this.bSwitching) {
            this.updateCards(0);
            this.updateCards(1);
        }

        if(!GameScreen.bShowMenu && Dialog.count <= 0) {
            Res.imgSpace.draw();
            Res.aniWarship.draw(this.deltaTime);
            Res.aniSuccess.draw(this.deltaTime);
            this.drawCards(Global.channel);
            this.drawLine(Global.channel);
            Res.glitter[0].draw(this.deltaTime);
            Res.glitter[1].draw(this.deltaTime);
            Res.glitter[v10].draw(this.deltaTime);
            if(this.fbDrawRobot) {
                int v0;
                for(v0 = 0; v0 < 25; ++v0) {
                    Res.robot[v0].draw(this.deltaTime);
                }
            }

            Res.imgBatterBack.draw();
            Res.imgChance100Back.draw();
            Res.aniBadge.draw(this.deltaTime);
            if(this.bSwitching) {
                Res.imgSwitching.draw();
            }

            Res.aniReserveBack.draw(this.deltaTime);
            if(Global.machineNo[Global.channel] > 0 && GameScreen.fCelemony <= 0f) {
                goto label_94;
            }

            Res.imgLogo.draw();
        }

    label_94:
        Res.imgGameBack1.draw();
        Res.imgGameBack2.draw();
        Res.aniLampLeft.draw(this.deltaTime);
        Res.aniLampBottom.draw(this.deltaTime);
        Res.aniLampMiddleRight.draw(this.deltaTime);
        Res.aniLampRight.draw(this.deltaTime);
        Res.aniLampTop.draw(this.deltaTime);
        if(Global.machineNo[0] <= 0 || Global.spin[0] > 50) {
            if(Global.machineNo[1] > 0 && Global.spin[1] <= 50) {
            label_127:
                Res.btnCoinBack3.draw(GameScreen.batch);
                Res.btnCoinBack3.setRotation((((float)Math.sin((((double)this.elapseTime)) * 0.2))) * 90f);
                goto label_140;
            }

            if((Global.machineNo[0] <= 0 || Global.spin[0] > 80) && (Global.machineNo[1] <= 0 || Global.spin[1] > 80)) {
                goto label_474;
            }

            Res.btnCoinBack2.draw(GameScreen.batch);
            Res.btnCoinBack2.setRotation((((float)Math.sin((((double)this.elapseTime)) * 0.2))) * 90f);
            goto label_140;
        label_474:
            Res.btnCoinBack1.draw(GameScreen.batch);
            Res.btnCoinBack1.setRotation((((float)Math.sin((((double)this.elapseTime)) * 0.2))) * 90f);
        }
        else {
            goto label_127;
        }

    label_140:
        Res.btnCoin.draw();
        Res.btnExitGame.draw();
        Res.btnReserve.draw(this.deltaTime);
        Res.btnMenu.draw(this.deltaTime);
        Res.btnSwitch.draw(this.deltaTime);
        Res.aniLampCircle.draw(this.deltaTime);
        Res.fire[0].draw(this.deltaTime);
        Res.fire[1].draw(this.deltaTime);
        Res.fire[v10].draw(this.deltaTime);
        if(this.iHintCannonStep == 0 || this.iHintCannonStep == 1 || this.iHintCannonStep == 7) {
            Res.spCannon[0].draw(GameScreen.batch);
            Res.spCannon[1].draw(GameScreen.batch);
            Res.spCannon[v10].draw(GameScreen.batch);
        }

        Res.imgGageBack.draw();
        Res.gage[0].draw();
        Res.gage[1].draw();
        Res.gage[v10].draw();
        Res.gage[3].draw();
        Res.gage[4].draw();
        Res.gage[5].draw();
        Res.gage[6].draw();
        Res.gage[7].draw();
        Res.gage[8].draw();
        Res.gage[9].draw();
        Res.aniMonitor.draw(this.deltaTime);
        Res.aniSp.draw(this.deltaTime);
        Res.aniSpLamp.draw(this.deltaTime);
        Res.aniSmallChannelBack.draw(this.deltaTime);
        Res.imgSmallChannel.draw();
        Res.imgChatBack.draw();
        Res.aniBackGift.draw(this.deltaTime);
        Res.aniBackSpin.draw(this.deltaTime);
        Res.aniBackNo.draw(this.deltaTime);
        Res.aniBackMoney.draw(this.deltaTime);
        Res.aniBackChannel.draw(this.deltaTime);
        if(GameScreen.bShowMenu) {
            Res.imgBackMenu1.draw();
            Res.imgBackMenu2.draw();
            if(this.iMenuMode == 5) {
                Res.imgBtnMachineOn.draw();
                Res.imgChannelLeft.draw();
                Res.imgChannelRight.draw();
                for(v0 = 0; v0 < 16; ++v0) {
                    Res.machineBack[v0].draw();
                    Res.machineScreen[v0].draw(this.deltaTime);
                }
            }
            else if(this.iMenuMode == 1) {
                Res.imgBackAsk.draw();
                Res.imgBtnChargeOn.draw();
                Res.imgBackCharge.draw();
                Res.imgBtnCharge.draw();
            }
            else if(this.iMenuMode == v10) {
                Res.imgBackAsk.draw();
                Res.imgBtnExchangeOn.draw();
                Res.imgBackExchange.draw();
                Res.imgBtnExchange.draw();
            }
            else if(this.iMenuMode == 3) {
                Res.imgBtnHelpOn.draw();
                Res.imgBackHelp.draw();
                Res.imgBtnHelpUp.draw();
                Res.imgBtnHelpDown.draw();
            }
            else if(this.iMenuMode == 4) {
                Res.imgBtnAskOn.draw();
                Res.imgBtnAskInput.draw();
                Res.imgBackAsk.draw();
            }
        }

        Res.aniClick.draw(this.deltaTime);
        GameScreen.batch.end();
        this.updateToast();
        this.updateFps();
        GameScreen.stage.act(this.deltaTime);
        GameScreen.stage.draw();
        this.processEvent();
        this.processMsg();
        this.fNextScenarioDelay[0] -= this.deltaTime;
        this.fNextScenarioDelay[1] -= this.deltaTime;
        this.fIntervalMiddle += this.deltaTime;
        if(this.fIntervalMiddle >= 0.2f) {
            this.fIntervalMiddle = 0f;
            this.processScenario(0);
            this.processScenario(1);
            this.hideVideo(Global.channel);
        }

        if(Global.bTerminating) {
            Global.fTerminating += this.deltaTime;
            if(Global.fTerminating > 2f) {
                Global.fTerminating = -100000000f;
                this.disposeGameScreen();
                Gdx.app.exit();
            }
        }

        this.updateFireCannon();
        this.fPushCannonDelay[0] -= this.deltaTime;
        if(this.fPushCannonDelay[0] < 0f) {
            this.fPushCannonDelay[0] = v9;
            this.showHintCannon(0, 3);
        }

        this.fPushCannonDelay[1] -= this.deltaTime;
        if(this.fPushCannonDelay[1] < 0f) {
            this.fPushCannonDelay[1] = v9;
            this.showHintCannon(1, 3);
        }

        this.fGlitterDelay[0] -= this.deltaTime;
        if(this.fGlitterDelay[0] < 0f) {
            this.fGlitterDelay[0] = v9;
            this.showGlitter(0, this.iTCBX[0]);
            this.stopHovering(0);
        }

        this.fGlitterDelay[1] -= this.deltaTime;
        if(this.fGlitterDelay[1] < 0f) {
            this.fGlitterDelay[1] = v9;
            this.showGlitter(1, this.iTCBX[1]);
            this.stopHovering(1);
        }

        this.fEndScenarioDelay[0] -= this.deltaTime;
        this.fEndScenarioDelay[1] -= this.deltaTime;
        if(this.fEndScenarioDelay[0] < 0f) {
            this.fEndScenarioDelay[0] = v9;
            this.endScenario(0);
        }

        if(this.fEndScenarioDelay[1] < 0f) {
            this.fEndScenarioDelay[1] = v9;
            this.endScenario(1);
        }

        if(GameScreen.fFackVideoTick > 0f) {
            GameScreen.fFackVideoTick += this.deltaTime;
            if(GameScreen.fFackVideoTick > 3f) {
                GameScreen.fFackVideoTick = 0f;
                this.onVideoPlayCompleted2(GameScreen.sFackVideoParam);
                GameScreen.sFackVideoParam = "";
            }
        }

        if(GameScreen.fSndStopDelay > 0f) {
            GameScreen.fSndStopDelay += this.deltaTime;
        }

        if((((double)GameScreen.fSndStopDelay)) > 0.7) {
            GameScreen.fSndStopDelay = 0f;
            Res.sndReel.stop();
            Res.sndReelEnd.stop();
            Res.sndReachSuccess.stop();
        }
    }

    private void requestScenario(int iChannel) {  // has try-catch handlers
        try {
            if(!this.bCanRequestScenario[iChannel]) {
                return;
            }

            if(!this.bWaitingScenario[iChannel]) {
                goto label_7;
            }

            return;
        label_7:
            if(Global.machineNo[iChannel] <= 0) {
                return;
            }

            this.bCanRequestScenario[iChannel] = false;
            this.bWaitingScenario[iChannel] = true;
            int v1 = 0;
            if(this.lsScenario[iChannel].size() > 0) {
                Object v2 = this.lsScenario[iChannel].get(this.lsScenario[iChannel].size() - 1);
                v1 = Util.strToint(this.getValue("A", ((String)v2)), 0);
                if(v1 <= 0) {
                    Util.sysout("ERROR", "GameScreen->requestScenario(1)", "sScenario=" + (((String)v2)));
                    GameScreen.exitApp("?????????? ?????? ?? ????????(CS1).\n?????????? ??????????.");
                    return;
                }
            }

            GameScreen.socketWrite("G090\t" + Global.id + "\t" + iChannel + "\t" + Global.machineNo[iChannel] + "\t" + String.valueOf(v1));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->requestScenario(0)", Util.getExceptionMessage(v0), String.valueOf(iChannel));
        }
    }

    public void resize(int width, int height) {
        GameScreen.stage.setViewport(((float)width), ((float)height), false);
    }

    public void resume() {
    }

    private void sendAsk(String msg) {  // has try-catch handlers
        if(msg == null) {
            try {
                msg = "";
            label_2:
                msg = msg.trim();
                if(!"".equals(msg)) {
                    goto label_7;
                }

                return;
            label_7:
                GameScreen.socketWrite("G140\t" + Global.id + "\t" + msg);
                return;
            }
            catch(Exception v0) {
                Util.sysout("ERROR", "GameScreen->sendAsk", Util.getExceptionMessage(v0));
                return;
            }
        }

        goto label_2;
    }

    public static void sendMsgToAndroid(String sMsg) {  // has try-catch handlers
        try {
            GameScreen.game.sendMsgToAndroid(sMsg);
        }
        catch(Exception v0) {
            Util.sysout("DEBUG", "GameScreen->sendMsgToAndroid", Util.getExceptionMessage(v0), sMsg);
        }
    }

    private void setBackGround(int iChannel, String _sSpace, String _sWarship, String _sChance100, String _sSp) {
        if(iChannel == Global.channel) {
            if(("N1".equals(_sSpace)) || ("N2".equals(_sSpace)) || ("N3".equals(_sSpace)) || ("B".equals(_sSpace)) || ("H".equals(_sSpace))) {
                this.sSpace = _sSpace;
                Res.imgSpace.setImage(_sSpace);
                if("H".equals(_sSpace)) {
                    Global.chance100[Global.channel] = _sChance100;
                    this.lbChance100.setText(((CharSequence)_sChance100));
                    Res.imgChance100Back.visible = true;
                    if(GameScreen.bShowMenu) {
                        this.lbChance100.setVisible(false);
                    }
                    else {
                        this.lbChance100.setVisible(true);
                    }
                }
                else {
                    Global.chance100[Global.channel] = "";
                    this.lbChance100.setText("");
                    Res.imgChance100Back.visible = false;
                    this.lbChance100.setVisible(false);
                }

                if("B".equals(_sSpace)) {
                    Res.imgBatterBack.visible = true;
                }
                else {
                    Res.imgBatterBack.visible = false;
                }

                if(this.sSpace.indexOf("H") < 0) {
                    goto label_82;
                }

                Res.aniLampTop.play(true, "????");
                Res.aniLampRight.play(true, "????");
                goto label_52;
            label_82:
                if(this.sSpace.indexOf("B") < 0) {
                    goto label_93;
                }

                Res.aniLampTop.play(true, "????");
                Res.aniLampRight.play(true, "????");
                goto label_52;
            label_93:
                Res.aniLampTop.play(true, "????");
                Res.aniLampRight.play(true, "????");
            }

        label_52:
            if("".equals(_sSp)) {
                goto label_100;
            }

            Res.aniSp.visible = true;
            Res.aniSp.play(true);
            Res.aniSpLamp.visible = true;
            Res.aniSpLamp.play(true);
            return;
        label_100:
            Res.aniSp.visible = false;
            Res.aniSp.play(false);
            Res.aniSpLamp.visible = false;
            Res.aniSpLamp.play(false);
        }
    }

    private void setCardAlpha(int _iChannel, int _alphaFlag) {
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            int v1;
            for(v1 = 0; v1 < 4; ++v1) {
                if(_alphaFlag > 0) {
                    this.card[_iChannel][v0][v1].setAlphaFlag(_alphaFlag);
                }
                else if(_alphaFlag < 0) {
                    this.card[_iChannel][v0][v1].fAlpha = 0.3f;
                }
            }
        }
    }

    private void setGage(int _iChannel, String sData) {  // has try-catch handlers
        int v2;
        try {
            Global.top[_iChannel] = sData;
            if(Global.channel != _iChannel) {
                return;
            }
        }
        catch(Exception v0) {
            goto label_44;
        }

        int v1 = 0;
        goto label_6;
    label_44:
        Util.sysout("ERROR", "GameScreen->setGage", Util.getExceptionMessage(v0), sData);
        return;
    label_6:
        if(v1 < 10) {
            goto label_9;
        }

        return;
        try {
        label_9:
            if(sData.length() > v1) {
                v2 = sData.charAt(v1);
            }
            else {
                v2 = 0;
            }

            if((v2 < 48 || v2 > 57) && (v2 < 65 || v2 > 69)) {
                goto label_29;
            }

            Res.gage[v1].setImage(sData.substring(v1, v1 + 1));
            goto label_25;
        label_29:
            Res.gage[v1].setImage("0");
        }
        catch(Exception v0) {
            try {
                Res.gage[v1].setImage("0");
            }
            catch(Exception v0) {
                goto label_44;
            }
        }

    label_25:
        ++v1;
        goto label_6;
    }

    private void setHintCannonRobot(int iChannel, String _sHintCannon, String _sRobot) {
        if(iChannel == Global.channel) {
            if(("1".equals(_sHintCannon)) || ("2".equals(_sHintCannon)) || ("3".equals(_sHintCannon))) {
                this.sHintCannon = _sHintCannon;
            }
            else {
                this.sHintCannon = "";
            }

            if(!"Y".equals(_sRobot)) {
                goto label_21;
            }

            this.sRobot = _sRobot;
            return;
        label_21:
            this.sRobot = "";
        }
    }

    private void setReelIdleTargetNo(int _iChannel) {
        int v5;
        int v4;
        int v3;
        do {
        label_1:
            v3 = Util.randomInt(1, 8);
            v4 = this.getExclusiveNo(v3);
            v5 = this.getExclusiveNo(v3);
            int v0 = v3 + 1;
            if(v0 == 9) {
                v0 = 1;
            }

            int v1 = v4 - 1;
            if(v1 == 0) {
                v1 = 8;
            }

            int v2 = v5 - 1;
            if(v2 == 0) {
                v2 = 8;
            }

            if(v3 == v4 && v4 == v5) {
                goto label_1;
            }

            if(v0 == v1 && v1 == v2) {
                goto label_1;
            }

            if(v0 == v1 && v1 == v5) {
                goto label_1;
            }

            if(v3 == v2 && v4 == v3) {
                goto label_1;
            }

            if(v0 != v5) {
                break;
            }
        }
        while(v4 == v0);

        this.iTargetCardNo[_iChannel][0] = v3;
        this.iTargetCardNo[_iChannel][1] = v4;
        this.iTargetCardNo[_iChannel][2] = v5;
    }

    public void show() {
    }

    private void showGlitter(int iChannel, int _iTCBX) {
        int v8 = 2;
        float v7 = 2f;
        if(iChannel == Global.channel) {
            Res.sndGlitter.play();
            float v0 = 0f;
            float v1 = 0f;
            float v2 = 0f;
            if(_iTCBX == 1) {
                v0 = Res.fBaseLineY3;
                v1 = Res.fBaseLineY3;
                v2 = Res.fBaseLineY3;
            }
            else if(_iTCBX == v8) {
                v0 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v7;
                v1 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v7;
                v2 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v7;
            }
            else if(_iTCBX == 3) {
                v0 = Res.fBaseLineY2;
                v1 = Res.fBaseLineY2;
                v2 = Res.fBaseLineY2;
            }
            else if(_iTCBX == 4) {
                v0 = Res.fBaseLineY3;
                v1 = Res.fBaseLineY3 - (Res.fBaseLineY3 - Res.fBaseLineY2) / v7;
                v2 = Res.fBaseLineY2;
            }

            Res.glitter[0].setPosition(Util.XCenter(Res.iBaseLinePixelX1, Util.getWidth(((float)Global.cardSizeX))), v0 - Util.getHeight(((float)Global.cardSizeY)) / v7);
            Res.glitter[1].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Util.getWidth(((float)Global.cardSizeX))), v1 - Util.getHeight(((float)Global.cardSizeY)) / v7);
            Res.glitter[v8].setPosition(Util.XCenter(Res.iBaseLinePixelX3, Util.getWidth(((float)Global.cardSizeX))), v2 - Util.getHeight(((float)Global.cardSizeY)) / v7);
            Res.glitter[0].visible = true;
            Res.glitter[1].visible = true;
            Res.glitter[v8].visible = true;
            Res.glitter[0].play(true, 1);
            Res.glitter[1].play(true, 1);
            Res.glitter[v8].play(true, 1);
        }
    }

    private void showHintCannon(int iChannel, int iType) {
        if(iChannel == Global.channel) {
            this.sHintCannon = "";
            this.iHintCannonType = iType;
            this.fHintCannonElapseTime = 0f;
            this.iHintCannonStep = 1;
            this.iHintCannonTick = 0;
            Res.aniLampCircle.play(true, "default");
            this.iniChatBack(3);
        }
    }

    private void showMenu(boolean _bShowMenu) {
        boolean v0;
        int v4 = 5;
        GameScreen.bShowMenu = _bShowMenu;
        Label v3 = this.lbMachine;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3.setVisible(v0);
        v3 = this.lbGift;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3.setVisible(v0);
        v3 = this.lbSpin;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3.setVisible(v0);
        v3 = this.lbMoney;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3.setVisible(v0);
        ScrollPane v3_1 = this.scpMsg;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3_1.setVisible(v0);
        v3 = this.lbChance100;
        if(_bShowMenu) {
            v0 = false;
        }
        else {
            v0 = true;
        }

        v3.setVisible(v0);
        if(!GameScreen.bShowMenu) {
            this.iExMenuMode = this.iMenuMode;
            this.iMenuMode = 0;
        }
        else if(this.iExMenuMode != 0) {
            this.iMenuMode = this.iExMenuMode;
        }
        else {
            this.iMenuMode = v4;
            this.iExMenuMode = v4;
        }

        this.changeMenuItem(this.iMenuMode);
        Res.sndSliding.play();
        GameScreen.bHideVideo = true;
        GameScreen.bShowVideo = true;
        GameScreen.bHideCannon = true;
        GameScreen.bShowCannon = true;
        if(_bShowMenu) {
            GameScreen.sendMsgToAndroid("hide_cannon");
        }

        this.updateLabel();
        this.showReserve();
    }

    private void showMessage(String sMsg) {  // has try-catch handlers
        try {
            GameScreen.sendMsgToAndroid("hide_video");
            sMsg = sMsg.replaceAll("<br/>", System.getProperty("line.separator")).replaceAll("<BR/>", System.getProperty("line.separator"));
            new Dialog() {
            }.text(sMsg).button("   ????   ", Boolean.valueOf(true)).key(66, Boolean.valueOf(true)).key(131, Boolean.valueOf(true)).show(GameScreen.stage);
            String[] v0 = sMsg.split(System.getProperty("line.separator"));
            int v2;
            for(v2 = 0; v2 < v0.length; ++v2) {
                this.addMsg(v0[v2], Color.WHITE);
            }

            return;
        }
        catch(Exception v1) {
            Util.sysout("ERROR", "GameScreen->showMessage", Util.getExceptionMessage(v1));
            return;
        }
    }

    private void showReachLine(int _iChannel, boolean _bDrawLine) {
        int v10 = -1;
        int v9 = 3;
        int v7 = 2;
        this.bDrawLine = _bDrawLine;
        this.fLineElapseTime = 0f;
        this.bLineLoop = false;
        if(_bDrawLine) {
            Res.sndReel.stop();
            Res.sndReelEnd.stop();
            Res.sndReachStart.play();
            Res.sndReach.play();
            Res.aniLampTop.play(true, "????");
            this.turnOnRightLamp(_iChannel);
            if(this.iTCBX[_iChannel] == 1) {
                int v0;
                for(v0 = 0; v0 < Res.spLine.length; ++v0) {
                    Res.spLine[v0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.spLine[v0].getWidth()), Util.YCenter(Res.iBaseLinePixelY3, Res.spLine[v0].getHeight()));
                }
            }
            else if(this.iTCBX[_iChannel] == v7) {
                for(v0 = 0; v0 < Res.spLine.length; ++v0) {
                    Res.spLine[v0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.spLine[v0].getWidth()), Util.YCenter(Res.iBaseLinePixelY3 - (Res.iBaseLinePixelY3 - Res.iBaseLinePixelY2) / 2f, Res.spLine[v0].getHeight()));
                }
            }
            else if(this.iTCBX[_iChannel] == v9) {
                for(v0 = 0; v0 < Res.spLine.length; ++v0) {
                    Res.spLine[v0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.spLine[v0].getWidth()), Util.YCenter(Res.iBaseLinePixelY2, Res.spLine[v0].getHeight()));
                }
            }

            if(this.iTCBX[_iChannel] != 1) {
                goto label_122;
            }

            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, 1)].play(true, 1);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, 1)].play(true, 1);
            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, v9)].setAlphaFlag(v10);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, v9)].setAlphaFlag(v10);
            return;
        label_122:
            if(this.iTCBX[_iChannel] != v7) {
                goto label_162;
            }

            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, v7)].play(true, 1);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, v7)].play(true, 1);
            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, v9)].setAlphaFlag(v10);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, v9)].setAlphaFlag(v10);
            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, 1)].setAlphaFlag(v10);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, 1)].setAlphaFlag(v10);
            return;
        label_162:
            if(this.iTCBX[_iChannel] != v9) {
                goto label_190;
            }

            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, v9)].play(true, 1);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, v9)].play(true, 1);
            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, 1)].setAlphaFlag(v10);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, 1)].setAlphaFlag(v10);
            return;
        label_190:
            if(this.iTCBX[_iChannel] != 4) {
                return;
            }

            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, v9)].play(true, 1);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, v9)].play(true, 1);
            this.card[_iChannel][0][this.getCardIdxOnReachLine(_iChannel, 0, 1)].play(true, 1);
            this.card[_iChannel][v7][this.getCardIdxOnReachLine(_iChannel, v7, 1)].play(true, 1);
        }
    }

    private void showReel(int _iChannel, int iReelIndex, boolean bShow) {
        this.card[_iChannel][iReelIndex][0].visible = bShow;
        this.card[_iChannel][iReelIndex][1].visible = bShow;
        this.card[_iChannel][iReelIndex][2].visible = bShow;
        this.card[_iChannel][iReelIndex][3].visible = bShow;
    }

    private void showReserve() {
        int v1 = 3;
        if(Global.channel == 0) {
            if(!GameScreen.bShowMenu && !GameScreen.bReserving && !this.bSwitching && Global.reserve[0] > 0 && !Global.bTerminating && !this.isReelRunning(0) && Global.mode == v1) {
                GameScreen.lbReserve.setText(Util.toTime(Global.reserve[0]));
                GameScreen.lbReserve.setVisible(true);
                Res.aniReserveBack.visible = true;
                return;
            }

            GameScreen.lbReserve.setVisible(false);
            Res.aniReserveBack.visible = false;
        }
        else {
            if(Global.channel != 1) {
                return;
            }

            if(!GameScreen.bShowMenu && !GameScreen.bReserving && !this.bSwitching && Global.reserve[1] > 0 && !Global.bTerminating && !this.isReelRunning(1) && Global.mode == v1) {
                GameScreen.lbReserve.setText(Util.toTime(Global.reserve[1]));
                GameScreen.lbReserve.setVisible(true);
                Res.aniReserveBack.visible = true;
                return;
            }

            GameScreen.lbReserve.setVisible(false);
            Res.aniReserveBack.visible = false;
        }
    }

    private void showRobot(boolean _bDrawRobot) {
        float v9 = 0.02f;
        int v6 = 4;
        this.fbDrawRobot = _bDrawRobot;
        if(_bDrawRobot) {
            int v0;
            for(v0 = 0; v0 < 15; ++v0) {
                Res.robot[v0].setPosition(Util.getLeft(((float)(Util.randomInt(0, 300) + 800))), Util.getTop(((float)Util.randomInt(100, 480)), 0f));
                Res.robot[v0].speed = Util.getWidth(((float)Util.randomInt(v6, 9)));
                Res.robot[v0].visible = true;
                Res.robot[v0].setPartFrameDuration((((float)Util.randomInt(1, v6))) * v9);
                Res.robot[v0].play(true, String.valueOf(Util.randomInt(1, v6)));
            }

            for(v0 = 15; v0 < 25; ++v0) {
                Res.robot[v0].setPosition(Util.getLeft(((float)(Util.randomInt(0, 300) + 800))), Util.getTop(((float)Util.randomInt(200, 480)), 0f));
                Res.robot[v0].speed = Util.getWidth(((float)Util.randomInt(2, 6)));
                Res.robot[v0].setPartFrameDuration((((float)Util.randomInt(1, v6))) * v9);
                Res.robot[v0].visible = true;
                Res.robot[v0].play(true, String.valueOf(Util.randomInt(1, v6)));
            }
        }
        else {
            for(v0 = 0; v0 < 15; ++v0) {
                Res.robot[v0].visible = false;
                Res.robot[v0].play(false);
            }
        }
    }

    private void showToast(String _msg, float _time) {  // has try-catch handlers
        try {
            this.bShowToast = true;
            this.fToastTime = _time;
            this.lbToast.setText(((CharSequence)_msg));
            this.lbToast.setVisible(true);
            this.imgToast.setVisible(true);
            this.imgToastBack.setVisible(true);
            Res.sndToast.play();
            GameScreen.sendMsgToAndroid("hide_ui");
            GameScreen.sendMsgToAndroid("hide_video");
            String[] v0 = _msg.split("\n");
            int v2;
            for(v2 = 0; v2 < v0.length; ++v2) {
                this.addMsg(v0[v2], Color.GREEN);
            }

            return;
        }
        catch(Exception v1) {
            Util.sysout("ERROR", "GameScreen->showToast", Util.getExceptionMessage(v1));
            return;
        }
    }

    private void shrinkCard(int _iChannel) {
        int v22 = 0;
        int v23 = 0;
        while(v23 < 4) {
            if(this.smallCard[_iChannel][v23].small) {
                v22 = 1;
            }
            else {
                ++v23;
                continue;
            }

            break;
        }

        if(v22 == 0) {
            int v7 = 267;
            int v14 = 585;
            int v8 = 150;
            int v21 = 349;
            if(this.iTCBX[_iChannel] == 1) {
                this.shrinkCard(_iChannel, 0, 1, 0, v7, v8);
                this.shrinkCard(_iChannel, 2, 1, 1, v14, v8);
            }
            else if(this.iTCBX[_iChannel] == 2) {
                this.shrinkCard(_iChannel, 0, 2, 0, v7, v8);
                this.shrinkCard(_iChannel, 2, 2, 1, v14, v8);
            }
            else if(this.iTCBX[_iChannel] == 3) {
                this.shrinkCard(_iChannel, 0, 3, 0, v7, v8);
                this.shrinkCard(_iChannel, 2, 3, 1, v14, v8);
            }
            else if(this.iTCBX[_iChannel] == 4) {
                this.shrinkCard(_iChannel, 0, 1, 0, v7, v8);
                this.shrinkCard(_iChannel, 2, 1, 1, v14, v8);
                this.shrinkCard(_iChannel, 0, 3, 2, v7, v21);
                this.shrinkCard(_iChannel, 2, 3, 3, v14, v21);
            }

            this.showReel(_iChannel, 0, false);
            this.showReel(_iChannel, 2, false);
            this.setCardAlpha(_iChannel, -1);
        }
    }

    private void shrinkCard(int _iChannel, int _iReelIndex, int _iTCBX, int _iSmallCardIdx, int _iTargetX, int _iTargetY) {
        int v0 = this.getCardIdxOnReachLine(_iChannel, _iReelIndex, _iTCBX);
        if(!this.card[_iChannel][_iReelIndex][v0].small) {
            this.card[_iChannel][_iReelIndex][v0].small = true;
            this.card[_iChannel][_iReelIndex][v0].visible = false;
            this.smallCard[_iChannel][_iSmallCardIdx].x = this.card[_iChannel][_iReelIndex][v0].x;
            this.smallCard[_iChannel][_iSmallCardIdx].y = this.card[_iChannel][_iReelIndex][v0].y;
            this.smallCard[_iChannel][_iSmallCardIdx].setNo(this.card[_iChannel][_iReelIndex][v0].no);
            this.smallCard[_iChannel][_iSmallCardIdx].setSize(this.card[_iChannel][_iReelIndex][v0].getWidth(), this.card[_iChannel][_iReelIndex][v0].getHeight());
            this.smallCard[_iChannel][_iSmallCardIdx].small = true;
            this.smallCard[_iChannel][_iSmallCardIdx].shrink(Util.XCenter(((float)_iTargetX), 0f), Util.YCenter(((float)_iTargetY), 0f));
            this.smallCard[_iChannel][_iSmallCardIdx].visible = true;
        }
    }

    public static void socketWrite(String _msg) {  // has try-catch handlers
        try {
            GameScreen.lsGameWrite.add(_msg);
            if(GameScreen.thdGameWrite != null) {
                goto label_10;
            }

            GameScreen.thdGameWrite = new Thread(new ThdGameWrite());
            GameScreen.thdGameWrite.start();
            return;
        label_10:
            GameScreen.thdGameWrite.interrupt();
            GameScreen.thdGameWrite = null;
            GameScreen.thdGameWrite = new Thread(new ThdGameWrite());
            GameScreen.thdGameWrite.start();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->socketWrite", Util.getExceptionMessage(v0));
        }
    }

    private void stopHovering(int _iChannel) {
        int v5 = 4;
        GameScreen.bHovering[_iChannel] = false;
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            int v1;
            for(v1 = 0; v1 < v5; ++v1) {
                if(this.card[_iChannel][v0][v1].alphaFlag < 0) {
                    this.card[_iChannel][v0][v1].setAlphaFlag(1);
                    this.card[_iChannel][v0][v1].visible = true;
                }
            }
        }

        for(v0 = 0; v0 < v5; ++v0) {
            this.smallCard[_iChannel][v0].visible = false;
            this.smallCard[_iChannel][v0].small = false;
        }
    }

    private void switchChannel() {
        if((this.bCanSwitchMachine) && !this.bSwitching && !this.bEnding[0] && !this.bEnding[1] && !this.bWaitingScenario[0] && !this.bWaitingScenario[1] && !GameScreen.bReserving) {
            this.bSwitching = true;
            this.bCanSwitchMachine = false;
            this.showReserve();
            GameScreen.sendMsgToAndroid("stop_video");
            GameScreen.sendMsgToAndroid("cannon_off\t1");
            GameScreen.sendMsgToAndroid("cannon_off\t2");
            GameScreen.sendMsgToAndroid("cannon_off\t3");
            GameScreen.sendMsgToAndroid("hide_cannon");
            this.iniSwitching();
            GameScreen.socketWrite("G070\t" + Global.id + "\t" + Global.channel + "\t" + Global.machineNo[0] + "\t" + Global.machineNo[1]);
        }
    }

    private void tensionReel(int iChannel, int iReelIndex) {
        float v4 = 0.02f;
        float v5 = 0.005f;
        float v1 = 0.1f;
        float v2 = -0.1f;
        float v3 = 0.1f;
        if(this.bReelCenter[iChannel][iReelIndex]) {
            v1 = this.reelMinPoppingSpeed[iChannel][iReelIndex];
        }

        if(this.iReelTensioningStep[iChannel][iReelIndex] == 1) {
            int v0;
            for(v0 = 0; v0 < 4; ++v0) {
                if(this.card[iChannel][iReelIndex][v0].y > this.card[iChannel][iReelIndex][v0].targetY - v4) {
                    this.fReelDeltaMileage[iChannel][iReelIndex] = this.deltaTime * v1;
                    this.card[iChannel][iReelIndex][v0].y -= this.fReelDeltaMileage[iChannel][iReelIndex];
                }
                else {
                    this.iReelTensioningStep[iChannel][iReelIndex] = 2;
                    if(this.reelMode[iChannel] != 0) {
                        GameScreen.bHovering[iChannel] = true;
                    }

                    if(iChannel == Global.channel && iReelIndex == 2 && this.reelMode[iChannel] != 1 && this.reelMode[iChannel] != 2) {
                        Res.sndReelEnd.stop();
                    }

                    if(iChannel == Global.channel && iReelIndex == 1 && (this.reelMode[iChannel] == 1 || this.reelMode[iChannel] == 0)) {
                        Res.sndReelStop.play();
                    }

                    if(iChannel != Global.channel) {
                        goto label_159;
                    }

                    if(iReelIndex != 1) {
                        goto label_159;
                    }

                    Res.sndReel.stop();
                }

            label_159:
            }
        }

        if(this.iReelTensioningStep[iChannel][iReelIndex] == 2) {
            for(v0 = 0; v0 < 4; ++v0) {
                if(this.card[iChannel][iReelIndex][v0].y < this.card[iChannel][iReelIndex][v0].targetY + v5) {
                    this.fReelDeltaMileage[iChannel][iReelIndex] = this.deltaTime * v2;
                    this.card[iChannel][iReelIndex][v0].y -= this.fReelDeltaMileage[iChannel][iReelIndex];
                }
                else {
                    this.iReelTensioningStep[iChannel][iReelIndex] = 3;
                }
            }
        }

        if(this.iReelTensioningStep[iChannel][iReelIndex] == 3) {
            for(v0 = 0; v0 < 4; ++v0) {
                if(this.card[iChannel][iReelIndex][v0].y > this.card[iChannel][iReelIndex][v0].targetY) {
                    this.fReelDeltaMileage[iChannel][iReelIndex] = this.deltaTime * v3;
                    this.card[iChannel][iReelIndex][v0].y -= this.fReelDeltaMileage[iChannel][iReelIndex];
                    if(this.card[iChannel][iReelIndex][v0].y <= this.card[iChannel][iReelIndex][v0].targetY) {
                        this.card[iChannel][iReelIndex][v0].y = this.card[iChannel][iReelIndex][v0].targetY;
                        this.iReelTensioningStep[iChannel][iReelIndex] = 4;
                        this.bReelRunning[iChannel][iReelIndex] = false;
                        this.fReelDeltaMileage[iChannel][iReelIndex] = 0f;
                        this.fReelAlpha[iChannel][iReelIndex] = 1f;
                    }
                }
                else if(this.card[iChannel][iReelIndex][v0].y < this.card[iChannel][iReelIndex][v0].targetY) {
                    this.fReelDeltaMileage[iChannel][iReelIndex] = -1f * v3 * this.deltaTime;
                    this.card[iChannel][iReelIndex][v0].y -= this.fReelDeltaMileage[iChannel][iReelIndex];
                    if(this.card[iChannel][iReelIndex][v0].y >= this.card[iChannel][iReelIndex][v0].targetY) {
                        this.card[iChannel][iReelIndex][v0].y = this.card[iChannel][iReelIndex][v0].targetY;
                        this.iReelTensioningStep[iChannel][iReelIndex] = 4;
                        this.bReelRunning[iChannel][iReelIndex] = false;
                        this.fReelDeltaMileage[iChannel][iReelIndex] = 0f;
                        this.fReelAlpha[iChannel][iReelIndex] = 1f;
                    }
                }
                else {
                    this.iReelTensioningStep[iChannel][iReelIndex] = 4;
                    this.bReelRunning[iChannel][iReelIndex] = false;
                    this.fReelDeltaMileage[iChannel][iReelIndex] = 0f;
                    this.fReelAlpha[iChannel][iReelIndex] = 1f;
                }
            }

            if(this.iReelTensioningStep[iChannel][iReelIndex] != 4) {
                goto label_107;
            }

            if(iReelIndex != 0) {
                goto label_404;
            }

            if(this.iFireType[iChannel] != -1) {
                goto label_107;
            }

            if(Global.channel == iChannel) {
                this.showReachLine(iChannel, true);
                if(this.fCannonRotation[0] <= -90f) {
                    this.iCannonDirection[0] = -1;
                }

                if(this.fCannonRotation[1] <= -90f) {
                    this.iCannonDirection[1] = -1;
                }

                if(this.fCannonRotation[2] <= -90f) {
                    this.iCannonDirection[2] = -1;
                }

                if(this.iCannonDirection[0] != -1 && this.iCannonDirection[1] != -1 && this.iCannonDirection[2] != -1) {
                    goto label_94;
                }

                this.iniChatBack(1);
            }
            else {
                Res.aniMonitor.play(true, "LINE");
            }

        label_94:
            if(this.reelMode[iChannel] != 2) {
                goto label_107;
            }

            this.fSecondAccelerationTime[iChannel] = GameScreen.fTotalReelElapseTime[iChannel] + 5f;
            this.bSecondAcceleration[iChannel] = true;
            goto label_107;
        label_404:
            if(iReelIndex != 2) {
                goto label_519;
            }

            if(this.iFireType[iChannel] != 5) {
                goto label_423;
            }

            if(Global.channel != iChannel) {
                goto label_107;
            }

            this.fFireDelayTime[iChannel] = 4f;
            this.fCannonDelayTime[iChannel] = 2f;
            this.fChatStep = 2f;
            Res.sndFireAlram.loop();
            goto label_107;
        label_423:
            if(this.iFireType[iChannel] != 6) {
                goto label_440;
            }

            if(Global.channel != iChannel) {
                goto label_107;
            }

            this.fFireDelayTime[iChannel] = 4f;
            this.fCannonDelayTime[iChannel] = 2f;
            this.fChatStep = 2f;
            Res.sndFireAlram.loop();
            goto label_107;
        label_440:
            if(this.iFireType[iChannel] == 7) {
                goto label_107;
            }

            if(this.reelMode[iChannel] != 1 && this.reelMode[iChannel] != 2) {
                goto label_107;
            }

            if(Global.channel == iChannel) {
                this.showReachLine(iChannel, true);
                if(this.fCannonRotation[0] <= -90f) {
                    this.iCannonDirection[0] = -1;
                }

                if(this.fCannonRotation[1] <= -90f) {
                    this.iCannonDirection[1] = -1;
                }

                if(this.fCannonRotation[2] <= -90f) {
                    this.iCannonDirection[2] = -1;
                }

                if(this.iCannonDirection[0] != -1 && this.iCannonDirection[1] != -1 && this.iCannonDirection[2] != -1) {
                    goto label_500;
                }

                this.iniChatBack(1);
            }
            else {
                Res.aniMonitor.play(true, "LINE");
            }

        label_500:
            if(this.reelMode[iChannel] != 2) {
                goto label_107;
            }

            this.fSecondAccelerationTime[iChannel] = GameScreen.fTotalReelElapseTime[iChannel] + 5f;
            this.bSecondAcceleration[iChannel] = true;
            goto label_107;
        label_519:
            if(this.iFireType[iChannel] != 7) {
                goto label_540;
            }

            if(Global.channel != iChannel) {
                goto label_107;
            }

            this.fFireDelayTime[iChannel] = 4f;
            this.fCannonDelayTime[iChannel] = 2f;
            this.fChatStep = 0.666667f;
            Res.sndFireAlram.loop();
            Res.sndReel.stop();
            Res.sndReelEnd.stop();
            goto label_107;
        label_540:
            if(Global.channel == iChannel) {
                this.showReachLine(iChannel, false);
                Res.sndReel.stop();
                Res.sndReelEnd.stop();
                if(this.reelMode[iChannel] != 2) {
                    Res.sndReach.stop();
                    Res.sndReachSuccess.stop();
                }

                if(this.reelMode[iChannel] != 1) {
                    goto label_562;
                }

                Res.sndReelStop.play();
            }
            else {
                if(this.bVideo2Playing[iChannel]) {
                    goto label_562;
                }

                Res.aniMonitor.play(true, "STOP");
            }

        label_562:
            if(this.reelMode[iChannel] != 3 && this.reelMode[iChannel] != 2) {
                this.stopHovering(iChannel);
                this.endScenario(iChannel);
            }

            if(this.reelMode[iChannel] != 2) {
                goto label_107;
            }

            if(Global.channel == iChannel) {
                Res.aniSuccess.visible = false;
            }

            this.cheatCardPos(iChannel);
        }

    label_107:
        if(!this.bReelAccelerating[iChannel][iReelIndex] && !this.bReelDecelerating[iChannel][iReelIndex] && this.iReelTensioningStep[iChannel][iReelIndex] == 0) {
            this.fReelDeltaMileage[iChannel][iReelIndex] = 0f;
            this.fReelAlpha[iChannel][iReelIndex] = 1f;
        }
    }

    private void turnOnRightLamp(int iChannel) {  // has try-catch handlers
        try {
            Object v1 = this.lsScenario[iChannel].get(0);
            String v2 = this.getValue("T", ((String)v1));
            if(!"F".equals(v2)) {
                goto label_36;
            }

            if(!Util.randomTrue(66)) {
                goto label_17;
            }

            Res.aniLampRight.play(true, "????1");
            return;
        label_17:
            if(!Util.randomTrue(70)) {
                goto label_31;
            }

            Res.aniLampRight.play(true, "????2");
            return;
        label_31:
            Res.aniLampRight.play(true, "????3");
            return;
        label_36:
            if(!"S".equals(v2)) {
                return;
            }

            if(!"1".equals(this.getValue("", ((String)v1)))) {
                goto label_73;
            }

            if(!Util.randomTrue(60)) {
                goto label_60;
            }

            if(!Util.randomTrue(60)) {
                goto label_55;
            }

            Res.aniLampRight.play(true, "????3");
            return;
        label_55:
            Res.aniLampRight.play(true, "????");
            return;
        label_60:
            if(!Util.randomTrue(50)) {
                goto label_68;
            }

            Res.aniLampRight.play(true, "????2");
            return;
        label_68:
            Res.aniLampRight.play(true, "????1");
            return;
        label_73:
            if(!Util.randomTrue(50)) {
                goto label_81;
            }

            Res.aniLampRight.play(true, "????1");
            return;
        label_81:
            if(!Util.randomTrue(50)) {
                goto label_89;
            }

            Res.aniLampRight.play(true, "????2");
            return;
        label_89:
            Res.aniLampRight.play(true, "????3");
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "turnOnRightLamp", Util.getExceptionMessage(v0));
        }
    }

    private void updateCards(int iChannel) {
        int v10 = 2;
        int v9 = 3;
        float v6 = 0.1f;
        GameScreen.fTotalReelElapseTime[iChannel] += this.deltaTime;
        if((this.bPlayVideo1[iChannel]) && !this.bVideo1Playing[iChannel] && GameScreen.fTotalReelElapseTime[iChannel] > this.fVideo1StartTime[iChannel]) {
            if(Global.channel == iChannel) {
                this.bVideo1Playing[iChannel] = true;
                this.bPlayVideo1[iChannel] = false;
                Res.imgVideoBack.visible = true;
                GameScreen.fSndStopDelay = v6;
                if("1".equals(this.sHintCannon)) {
                    this.showHintCannon(iChannel, 1);
                }
                else if("2".equals(this.sHintCannon)) {
                    this.showHintCannon(iChannel, v10);
                }
                else if("3".equals(this.sHintCannon)) {
                    this.showHintCannon(iChannel, v9);
                }

                GameScreen.game.playVideo(this, this.mVideoPlayCompleted, this.getVideoResId(this.sVideo1Raw[iChannel]), String.valueOf(String.valueOf(this.reelMode[iChannel])) + ";1;" + this.sVideo1Raw[iChannel] + ";play;" + String.valueOf(iChannel));
                GameScreen.bHideVideo = true;
                GameScreen.bShowVideo = true;
            }
            else {
                this.bVideo1Playing[iChannel] = true;
                this.bPlayVideo1[iChannel] = false;
                GameScreen.sFackVideoParam = String.valueOf(String.valueOf(this.reelMode[iChannel])) + ";1;" + this.sVideo1Raw[iChannel] + ";not_play;" + String.valueOf(iChannel);
                GameScreen.fFackVideoTick = v6;
                Res.aniMonitor.play(true, "HINT");
            }

            if(this.reelMode[iChannel] != v9) {
                goto label_113;
            }

            this.fReelDeceleratingStartTime[iChannel][0] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.fReelDeceleratingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.fReelDeceleratingStartTime[iChannel][v10] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.fReelPoppingStartTime[iChannel][0] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.fReelPoppingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.fReelPoppingStartTime[iChannel][v10] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
            this.iMaxPopCount[iChannel][0] = 12;
            this.iMaxPopCount[iChannel][1] = 12;
            this.iMaxPopCount[iChannel][v10] = 12;
            this.fPushCannonDelay[iChannel] = 5f;
        }

    label_113:
        if((this.bPlayVideo2[iChannel]) && !this.bVideo2Playing[iChannel] && GameScreen.fTotalReelElapseTime[iChannel] > this.fVideo2StartTime[iChannel]) {
            if(Global.channel == iChannel) {
                this.bVideo2Playing[iChannel] = true;
                this.bPlayVideo2[iChannel] = false;
                Res.imgVideoBack.visible = true;
                GameScreen.fSndStopDelay = v6;
                this.fReelDeceleratingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
                this.fReelPoppingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
                this.iMaxPopCount[iChannel][1] = this.iPopCount[iChannel][1] + 8;
                this.card[iChannel][1][0].visible = false;
                this.card[iChannel][1][1].visible = false;
                this.card[iChannel][1][v10].visible = false;
                this.card[iChannel][1][v9].visible = false;
                GameScreen.game.playVideo(this, this.mVideoPlayCompleted, this.getVideoResId(this.sVideo2Raw[iChannel]), String.valueOf(String.valueOf(this.reelMode[iChannel])) + ";2;" + this.sVideo2Raw[iChannel] + ";play;" + String.valueOf(iChannel));
                GameScreen.bHideVideo = true;
                GameScreen.bShowVideo = true;
            }
            else {
                this.bVideo2Playing[iChannel] = true;
                this.bPlayVideo2[iChannel] = false;
                GameScreen.sFackVideoParam = String.valueOf(String.valueOf(this.reelMode[iChannel])) + ";2;" + this.sVideo2Raw[iChannel] + ";not_play;" + String.valueOf(iChannel);
                GameScreen.fFackVideoTick = v6;
                Res.aniMonitor.play(true, "DESTROY");
                this.fReelDeceleratingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
                this.fReelPoppingStartTime[iChannel][1] = GameScreen.fTotalReelElapseTime[iChannel] - v6;
                this.iMaxPopCount[iChannel][1] = this.iPopCount[iChannel][1] + 8;
            }
        }

        if((this.bSecondAcceleration[iChannel]) && GameScreen.fTotalReelElapseTime[iChannel] > this.fSecondAccelerationTime[iChannel]) {
            this.fVideo2StartTime[iChannel] = GameScreen.fTotalReelElapseTime[iChannel] + 3f;
            this.bSecondAcceleration[iChannel] = false;
            this.bReelRunning[iChannel][1] = true;
            this.bReelAccelerating[iChannel][1] = true;
            this.bReelDecelerating[iChannel][1] = false;
            this.iReelTensioningStep[iChannel][1] = 0;
            this.accelerationSpeed[iChannel][1] = 0.02f;
            this.minAlpha[iChannel] = v6;
            this.accelerationAlpha[iChannel][1] = 0.005f;
            if(Global.channel == iChannel) {
                this.bShrinkCard[iChannel] = true;
                this.showReachLine(iChannel, false);
                Res.aniSuccess.visible = true;
                Res.aniSuccess.play(true, "on", 1);
                Res.sndReach.stop();
                Res.sndReachSuccess.play();
            }
            else {
                Res.aniMonitor.play(true, "LINE");
            }
        }

        if((this.bShrinkCard[iChannel]) && (((double)GameScreen.fTotalReelElapseTime[iChannel])) > (((double)this.fSecondAccelerationTime[iChannel])) + 1.2) {
            this.bShrinkCard[iChannel] = false;
            if(Global.channel == iChannel) {
                this.shrinkCard(iChannel);
            }
        }

        int v0;
        for(v0 = 0; v0 < v9; ++v0) {
            if(!this.bReelDeceleratingStarted[iChannel][v0] && GameScreen.fTotalReelElapseTime[iChannel] > this.fReelDeceleratingStartTime[iChannel][v0]) {
                this.bReelDeceleratingStarted[iChannel][v0] = true;
                this.bReelAccelerating[iChannel][v0] = false;
                this.bReelDecelerating[iChannel][v0] = true;
            }
        }

        for(v0 = 0; v0 < v9; ++v0) {
            if(!this.bReelPoppingStarted[iChannel][v0] && GameScreen.fTotalReelElapseTime[iChannel] > this.fReelPoppingStartTime[iChannel][v0]) {
                this.bReelPoppingStarted[iChannel][v0] = true;
            }
        }

        for(v0 = 0; v0 < v9; ++v0) {
            if((this.bReelRunning[iChannel][v0]) && GameScreen.fTotalReelElapseTime[iChannel] > this.fStartReelDelayTime[iChannel][v0]) {
                this.fReelElapseTime[iChannel][v0] += this.deltaTime;
                this.controlReelSpeed(iChannel, v0);
                this.moveReel(iChannel, v0);
                this.tensionReel(iChannel, v0);
            }
        }
    }

    private void updateFireCannon() {  // has try-catch handlers
        float v9 = 1.5f;
        int v7 = 2;
        try {
            if(this.iHintCannonType > 0) {
                this.fHintCannonElapseTime += this.deltaTime;
                if(this.iHintCannonStep == 1 && this.fHintCannonElapseTime > 0.1f) {
                    if(GameScreen.bCannonCelemony) {
                        this.iHintCannonTick = 0;
                        this.fHintCannonElapseTime = 400f;
                        this.iHintCannonStep = 4;
                        goto label_23;
                    }
                    else {
                        if(Util.getTop(-100f, Res.imgChatBack.height) > Res.imgChatBack.y) {
                            Res.imgChatBack.y += Util.getHeight(2f) * this.deltaTime * 60f;
                            this.scpMsg.setY(this.scpMsg.getY() + Util.getActorHeight(2f) * this.deltaTime * 60f);
                        }

                        if((((double)this.fHintCannonElapseTime)) <= 1.2) {
                            goto label_23;
                        }

                        this.iHintCannonTick = 0;
                        this.fHintCannonElapseTime = 200f;
                        this.iHintCannonStep = 2;
                        goto label_23;
                    }
                }

                if(this.iHintCannonStep == v7 && this.fHintCannonElapseTime > 200f) {
                    if(this.iHintCannonType == 1) {
                        this.iHintCannonStep = 5;
                        this.fHintCannonElapseTime = 497.899994f;
                    }
                    else {
                        if(this.iHintCannonType != v7 && this.iHintCannonType != 3) {
                            goto label_86;
                        }

                        this.iHintCannonStep = 3;
                        this.fHintCannonElapseTime = 298.899994f;
                    }

                    goto label_86;
                }

                if(this.iHintCannonStep == 3 && this.fHintCannonElapseTime > 300f) {
                    if(this.iHintCannonType == v7) {
                        this.iHintCannonStep = 5;
                        this.fHintCannonElapseTime = 497.899994f;
                    }
                    else if(this.iHintCannonType == 3) {
                        this.iHintCannonStep = 4;
                        this.fHintCannonElapseTime = 398.899994f;
                    }

                    goto label_117;
                }

                if(this.iHintCannonStep == 4 && this.fHintCannonElapseTime > 400f) {
                    this.iHintCannonStep = 5;
                    if(GameScreen.bCannonCelemony) {
                        this.fHintCannonElapseTime = 498.899994f;
                        GameScreen.sendMsgToAndroid("cannon_on\t1");
                        GameScreen.sendMsgToAndroid("cannon_on\t2");
                        GameScreen.sendMsgToAndroid("cannon_on\t3");
                    }
                    else {
                        this.fHintCannonElapseTime = 497.899994f;
                        Res.sndCannon.play();
                        GameScreen.sendMsgToAndroid("cannon_on\t3");
                    }

                    goto label_152;
                }

                if(this.iHintCannonStep == 5 && this.fHintCannonElapseTime > 500f) {
                    if(this.iHintCannonType == 1) {
                        GameScreen.sendMsgToAndroid("cannon_off\t1");
                        GameScreen.bShowCannon = true;
                        GameScreen.bHideCannon = true;
                    }
                    else if(this.iHintCannonType == v7) {
                        GameScreen.sendMsgToAndroid("cannon_off\t1");
                        GameScreen.sendMsgToAndroid("cannon_off\t2");
                        GameScreen.bShowCannon = true;
                        GameScreen.bHideCannon = true;
                    }
                    else if(this.iHintCannonType == 3) {
                        GameScreen.sendMsgToAndroid("cannon_off\t1");
                        GameScreen.sendMsgToAndroid("cannon_off\t2");
                        GameScreen.sendMsgToAndroid("cannon_off\t3");
                        GameScreen.bShowCannon = true;
                        GameScreen.bHideCannon = true;
                    }

                    goto label_178;
                }

                if(this.iHintCannonStep != 6 && this.iHintCannonStep != 7) {
                    goto label_23;
                }

                if(this.fHintCannonElapseTime <= 600f) {
                    goto label_23;
                }

                if(Util.getTop(0f, Res.imgChatBack.height) < Res.imgChatBack.y) {
                    Res.imgChatBack.y -= Util.getHeight(2f) * this.deltaTime * 60f;
                    this.scpMsg.setY(this.scpMsg.getY() - Util.getActorHeight(2f) * this.deltaTime * 60f);
                }

                ++this.iHintCannonTick;
                if((((double)this.fHintCannonElapseTime)) > 602.2) {
                    this.iniChatBack(3);
                    this.iHintCannonType = 0;
                    this.iHintCannonStep = 0;
                    this.fHintCannonElapseTime = 0f;
                    this.iHintCannonTick = 0;
                    GameScreen.sendMsgToAndroid("hide_cannon");
                    Res.aniLampCircle.play(false);
                    GameScreen.fCelemony = 0f;
                    GameScreen.bCannonCelemony = false;
                    this.iniChatBack(3);
                }

                if(this.iHintCannonTick != 3) {
                    goto label_23;
                }

                this.iHintCannonStep = 7;
                GameScreen.sendMsgToAndroid("hide_cannon");
                goto label_23;
            label_86:
                Res.sndCannon.play();
                GameScreen.sendMsgToAndroid("cannon_on\t1");
                GameScreen.bHideCannon = true;
                GameScreen.bShowCannon = true;
                goto label_23;
            label_117:
                Res.sndCannon.play();
                GameScreen.sendMsgToAndroid("cannon_on\t2");
                GameScreen.bHideCannon = true;
                GameScreen.bShowCannon = true;
                goto label_23;
            label_152:
                GameScreen.bHideCannon = true;
                GameScreen.bShowCannon = true;
                goto label_23;
            label_178:
                this.iHintCannonStep = 6;
                this.fHintCannonElapseTime = 598.799988f;
                this.iHintCannonTick = 0;
                this.iniChatBack(1);
            }

        label_23:
            int v1;
            for(v1 = 0; v1 < 3; ++v1) {
                if(this.iCannonDirection[v1] == 1) {
                    if(Util.getTop(-100f, Res.imgChatBack.height) > Res.imgChatBack.y) {
                        Res.imgChatBack.y += Util.getHeight(this.fChatStep) * this.deltaTime * 60f;
                        this.scpMsg.setY(this.scpMsg.getY() + Util.getActorHeight(this.fChatStep) * this.deltaTime * 60f);
                    }

                    this.fCannonRotation[v1] -= this.deltaTime * 50f;
                    if(this.fCannonRotation[v1] <= -90f) {
                        this.fCannonRotation[v1] = -90f;
                        this.iCannonDirection[v1] = 0;
                        this.iniChatBack(1);
                    }

                    Res.spCannon[v1].setRotation(this.fCannonRotation[v1]);
                }
                else {
                    if(this.iCannonDirection[v1] != -1) {
                        goto label_343;
                    }

                    if(Util.getTop(0f, Res.imgChatBack.height) < Res.imgChatBack.y) {
                        Res.imgChatBack.y -= Util.getHeight(this.fChatStep) * this.deltaTime * 60f;
                        this.scpMsg.setY(this.scpMsg.getY() - Util.getActorHeight(this.fChatStep) * this.deltaTime * 60f);
                    }

                    this.fCannonRotation[v1] += this.deltaTime * 50f;
                    if(this.fCannonRotation[v1] >= 0f) {
                        this.fCannonRotation[v1] = 0f;
                        this.iCannonDirection[v1] = 0;
                        this.iniChatBack(3);
                        Res.aniLampCircle.play(false);
                    }

                    Res.spCannon[v1].setRotation(this.fCannonRotation[v1]);
                }

            label_343:
            }

            for(v1 = 0; v1 < v7; ++v1) {
                if(this.iFireType[v1] > 0) {
                    if(this.fCannonDelayTime[v1] > 0f) {
                        this.fCannonDelayTime[v1] -= this.deltaTime;
                    }

                    if(this.fCannonDelayTime[v1] < 0f) {
                        this.fCannonDelayTime[v1] = 0f;
                        if(v1 == Global.channel) {
                            Res.sndCannon.play();
                            this.iniChatBack(3);
                        }

                        if(this.iFireType[v1] != 5) {
                            goto label_582;
                        }

                        this.fCannonRotation[0] = 0f;
                        this.iCannonDirection[0] = 1;
                        goto label_442;
                    label_582:
                        if(this.iFireType[v1] != 6) {
                            goto label_595;
                        }

                        this.fCannonRotation[2] = 0f;
                        this.iCannonDirection[2] = 1;
                        goto label_442;
                    label_595:
                        if(this.iFireType[v1] != 7) {
                            goto label_442;
                        }

                        this.fCannonRotation[0] = 0f;
                        this.fCannonRotation[1] = 0f;
                        this.fCannonRotation[2] = 0f;
                        this.iCannonDirection[0] = 1;
                        this.iCannonDirection[1] = 1;
                        this.iCannonDirection[2] = 1;
                    }

                label_442:
                    if(this.fFireDelayTime[v1] > 0f) {
                        this.fFireDelayTime[v1] -= this.deltaTime;
                    }

                    if(this.fFireDelayTime[v1] >= 0f) {
                        goto label_580;
                    }

                    this.fFireDelayTime[v1] = 0f;
                    this.iTargetCardNo[v1][0] = this.iPostTargetCardNo[v1][0];
                    this.iTargetCardNo[v1][1] = this.iPostTargetCardNo[v1][1];
                    this.iTargetCardNo[v1][2] = this.iPostTargetCardNo[v1][2];
                    this.bReelCenter[v1][0] = this.bPostReelCenter[v1][0];
                    this.bReelCenter[v1][1] = this.bPostReelCenter[v1][1];
                    this.bReelCenter[v1][2] = this.bPostReelCenter[v1][2];
                    if(this.iFireType[v1] != 5) {
                        goto label_649;
                    }

                    this.fire(v1, 0);
                    if(v1 == Global.channel) {
                        Res.sndCannon.stop();
                        Res.sndFireAlram.stop();
                        Res.sndFire.play();
                    }

                    if(this.reelMode[v1] == 1) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 16;
                    }
                    else if(this.reelMode[v1] == v7) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 9999999;
                    }

                    this.fReelDeceleratingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.fReelPoppingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.iMaxPopCount[v1][0] = 10;
                    this.reelMinPoppingSpeed[v1][0] = 0.4f;
                    this.accelerationSpeed[v1][0] = 1.5f;
                    this.decelerationSpeed[v1][0] = 0.02f;
                    this.iFireType[v1] = -1;
                    goto label_580;
                label_649:
                    if(this.iFireType[v1] != 6) {
                        goto label_750;
                    }

                    this.fire(v1, 2);
                    if(v1 == Global.channel) {
                        Res.sndCannon.stop();
                        Res.sndFireAlram.stop();
                        Res.sndFire.play();
                    }

                    if(this.reelMode[v1] == 1) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 16;
                    }
                    else if(this.reelMode[v1] == v7) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 9999999;
                    }

                    this.fReelDeceleratingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.fReelPoppingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.iMaxPopCount[v1][2] = 10;
                    this.reelMinPoppingSpeed[v1][2] = 0.4f;
                    this.accelerationSpeed[v1][2] = 1.5f;
                    this.decelerationSpeed[v1][2] = 0.02f;
                    this.iFireType[v1] = -2;
                    goto label_580;
                label_750:
                    if(this.iFireType[v1] != 7) {
                        goto label_580;
                    }

                    this.fire(v1, 0);
                    this.fire(v1, 1);
                    this.fire(v1, 2);
                    if(v1 == Global.channel) {
                        Res.sndCannon.stop();
                        Res.sndFireAlram.stop();
                        Res.sndFire.play();
                    }

                    if(this.reelMode[v1] == 1) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 16;
                    }
                    else if(this.reelMode[v1] == v7) {
                        this.fReelDeceleratingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.fReelPoppingStartTime[v1][1] = GameScreen.fTotalReelElapseTime[v1] + 4.5f;
                        this.iMaxPopCount[v1][1] = 9999999;
                    }

                    this.fReelDeceleratingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.fReelPoppingStartTime[v1][0] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.iMaxPopCount[v1][0] = 8;
                    this.reelMinPoppingSpeed[v1][0] = 1.5f;
                    this.accelerationSpeed[v1][0] = 1.5f;
                    this.decelerationSpeed[v1][0] = 0.02f;
                    this.fReelDeceleratingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.fReelPoppingStartTime[v1][2] = GameScreen.fTotalReelElapseTime[v1] + v9;
                    this.iMaxPopCount[v1][2] = 8;
                    this.reelMinPoppingSpeed[v1][2] = 1.5f;
                    this.accelerationSpeed[v1][2] = 1.5f;
                    this.decelerationSpeed[v1][2] = 0.02f;
                    this.iFireType[v1] = -3;
                }

            label_580:
            }

            return;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "GameScreen->updateFireCannon", Util.getExceptionMessage(v0));
            return;
        }
    }

    private void updateFps() {
        this.lbFps.setText("FPS:" + Gdx.graphics.getFramesPerSecond() + ":" + Math.round(GameScreen.fTotalReelElapseTime[0] * 10f));
    }

    private void updateLabel() {
        this.lbMachine.setText(String.valueOf(Global.machineNo[Global.channel]));
        this.lbGift.setText(String.valueOf(Global.gift[Global.channel]));
        this.lbSpin.setText(String.valueOf(Global.spin[Global.channel]));
        this.lbMoney.setText(Util.withComma(((long)Global.money)));
        GameScreen.lbReserve.setText(Util.toTime(Global.reserve[Global.channel]));
        this.lbChance100.setText(Global.chance100[Global.channel]);
        if(GameScreen.bShowMenu) {
            this.imgChannelA.setVisible(false);
            this.imgChannelB.setVisible(false);
        }
        else if(Global.channel == 0) {
            this.imgChannelA.setVisible(true);
            this.imgChannelB.setVisible(false);
        }
        else {
            this.imgChannelA.setVisible(false);
            this.imgChannelB.setVisible(true);
            Res.imgSmallChannel.setImage("0");
        }

        if(Global.channel == 0) {
            Res.imgSmallChannel.setImage("1");
        }
        else {
            Res.imgSmallChannel.setImage("0");
        }
    }

    private void updateToast() {
        this.fToastTime -= this.deltaTime;
        if((this.bShowToast) && this.fToastTime < 0f) {
            this.bShowToast = false;
            this.fToastTime = 0f;
            this.lbToast.setVisible(false);
            this.imgToast.setVisible(false);
            this.imgToastBack.setVisible(false);
            GameScreen.bHideCannon = true;
            GameScreen.bShowCannon = true;
            GameScreen.bShowVideo = true;
            GameScreen.bHideVideo = true;
        }
    }
}

