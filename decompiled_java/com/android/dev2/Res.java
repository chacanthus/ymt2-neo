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

import com.android.dev2.lib.Ani;
import com.android.dev2.lib.Img;
import com.android.dev2.lib.Util;
import com.android.dev2.obj.Fire;
import com.android.dev2.obj.Gage;
import com.android.dev2.obj.Glitter;
import com.android.dev2.obj.MachineBack;
import com.android.dev2.obj.MachineScreen;
import com.android.dev2.obj.Robot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Res {
    public static final String MO_BATTER = "BATTER";
    public static final String MO_DESTROY = "DESTROY";
    public static final String MO_FEVER = "FEVER";
    public static final String MO_HINT = "HINT";
    public static final String MO_IDLE = "IDLE";
    public static final String MO_LINE = "LINE";
    public static final String MO_PLAY = "PLAY";
    public static final String MO_STOP = "STOP";
    public static Ani aniBackChannel;
    public static Ani aniBackGift;
    public static Ani aniBackMoney;
    public static Ani aniBackNo;
    public static Ani aniBackSpin;
    public static Ani aniBadge;
    public static Ani aniLampBottom;
    public static Ani aniLampLeft;
    public static Ani aniLampMiddleRight;
    public static Ani aniLampRight;
    public static Ani aniLampTop;
    public static Ani aniMonitor;
    public static Ani aniReserveBack;
    public static Ani aniSmallChannelBack;
    public static Ani aniSp;
    public static Ani aniSpLamp;
    public static Ani aniWarship;
    public static Img btnCoin;
    public static Sprite btnCoinBack1;
    public static Sprite btnCoinBack2;
    public static Sprite btnCoinBack3;
    public static Img btnExitGame;
    public static Ani btnMenu;
    public static Ani btnReserve;
    public static Ani btnSwitch;
    public static float fBaseLineY0;
    public static float fBaseLineY1;
    public static float fBaseLineY2;
    public static float fBaseLineY3;
    public static float fBaseLineY4;
    public static Fire[] fire;
    public static Gage[] gage;
    public static Glitter[] glitter;
    public static float iBaseLinePixelX1;
    public static float iBaseLinePixelX2;
    public static float iBaseLinePixelX3;
    public static float iBaseLinePixelY0;
    public static float iBaseLinePixelY1;
    public static float iBaseLinePixelY2;
    public static float iBaseLinePixelY3;
    public static float iBaseLinePixelY4;
    public static Img imgBackAsk;
    public static Img imgBackCharge;
    public static Img imgBackExchange;
    public static Img imgBackHelp;
    public static Img imgBackMenu1;
    public static Img imgBackMenu2;
    public static Img imgBatterBack;
    public static Img imgBtnAskInput;
    public static Img imgBtnAskOn;
    public static Img imgBtnCharge;
    public static Img imgBtnChargeOn;
    public static Img imgBtnCloseMenu;
    public static Img imgBtnExchange;
    public static Img imgBtnExchangeBank;
    public static Img imgBtnExchangeOn;
    public static Img imgBtnExchangeOwner;
    public static Img imgBtnHelpDown;
    public static Img imgBtnHelpOn;
    public static Img imgBtnHelpUp;
    public static Img imgBtnMachineOn;
    public static Img imgBtnMoneyMan;
    public static Img imgChance100Back;
    public static Img imgChannelLeft;
    public static Img imgChannelRight;
    public static Img imgChatBack;
    public static Img imgGageBack;
    public static Img imgGameBack1;
    public static Img imgGameBack2;
    public static Img imgLogo;
    public static Img imgSmallChannel;
    public static Img imgSpace;
    public static Img imgSwitching;
    public static Img imgVideoBack;
    public static TextureAtlas leaner;
    public static MachineBack[] machineBack;
    public static MachineScreen[] machineScreen;
    public static TextureAtlas nearest;
    public static TextureRegion[] rgCard1;
    public static TextureRegion[] rgCard2;
    public static TextureRegion[] rgCard3;
    public static TextureRegion[] rgCard4;
    public static TextureRegion[] rgCard5;
    public static TextureRegion[] rgCard6;
    public static TextureRegion[] rgCard7;
    public static TextureRegion[] rgCard8;
    public static TextureRegion[] rgFire;
    public static TextureRegion[] rgGage;
    public static TextureRegion[] rgGlitter;
    public static TextureRegion[] rgMachineBack;
    public static TextureRegion[] rgMachineScreen;
    public static TextureRegion[] rgRobot;
    public static Robot[] robot;
    public static Sound sndAlram;
    public static Sound sndCannon;
    public static Sound sndDocking;
    public static Sound sndFire;
    public static Sound sndFireAlram;
    public static Sound sndGlitter;
    public static Sound sndReach;
    public static Sound sndReachStart;
    public static Sound sndReachSuccess;
    public static Sound sndReel;
    public static Sound sndReelEnd;
    public static Sound sndReelStop;
    public static Sound sndRoll;
    public static Sound sndSliding;
    public static Sound sndToast;
    public static Sprite[] spCannon;
    public static Sprite[] spCrossLine1;
    public static Sprite[] spCrossLine2;
    public static Sprite[] spLine;

    static  {
        Res.leaner = null;
        Res.nearest = null;
        Res.rgRobot = new TextureRegion[8];
        Res.robot = new Robot[25];
        Res.imgGameBack1 = null;
        Res.imgGameBack2 = null;
        Res.imgChatBack = null;
        Res.imgVideoBack = null;
        Res.imgGageBack = null;
        Res.aniSuccess = null;
        Res.imgSpace = null;
        Res.aniWarship = null;
        Res.aniBackGift = null;
        Res.aniBackSpin = null;
        Res.aniBackChannel = null;
        Res.aniBackNo = null;
        Res.aniBackMoney = null;
        Res.aniReserveBack = null;
        Res.imgBatterBack = null;
        Res.imgChance100Back = null;
        Res.btnExitGame = null;
        Res.btnSwitch = null;
        Res.btnReserve = null;
        Res.btnMenu = null;
        Res.btnCoin = null;
        Res.btnCoinBack1 = null;
        Res.btnCoinBack2 = null;
        Res.btnCoinBack3 = null;
        Res.aniMonitor = null;
        Res.aniSp = null;
        Res.aniSpLamp = null;
        Res.spLine = new Sprite[37];
        Res.spCrossLine1 = new Sprite[37];
        Res.spCrossLine2 = new Sprite[37];
        Res.spCannon = new Sprite[3];
        Res.rgFire = new TextureRegion[41];
        Res.fire = new Fire[3];
        Res.rgGlitter = new TextureRegion[10];
        Res.glitter = new Glitter[3];
        Res.aniLampRight = null;
        Res.aniLampTop = null;
        Res.aniLampCircle = null;
        Res.aniLampLeft = null;
        Res.aniLampBottom = null;
        Res.aniLampMiddleRight = null;
        Res.rgMachineScreen = new TextureRegion[3];
        Res.rgMachineBack = new TextureRegion[4];
        Res.imgChannelLeft = null;
        Res.imgChannelRight = null;
        Res.rgGage = new TextureRegion[15];
        Res.gage = new Gage[10];
        Res.machineBack = new MachineBack[16];
        Res.machineScreen = new MachineScreen[16];
        Res.imgBackMenu1 = null;
        Res.imgBackMenu2 = null;
        Res.imgBtnHelpOn = null;
        Res.imgBtnMachineOn = null;
        Res.imgBtnAskOn = null;
        Res.imgBtnExchangeOn = null;
        Res.imgBtnChargeOn = null;
        Res.imgBtnCloseMenu = null;
        Res.imgBtnAskInput = null;
        Res.imgBtnExchangeBank = null;
        Res.imgBtnExchangeOwner = null;
        Res.imgBtnMoneyMan = null;
        Res.imgBackCharge = null;
        Res.imgBackExchange = null;
        Res.imgBackAsk = null;
        Res.imgBackHelp = null;
        Res.imgBtnCharge = null;
        Res.imgBtnExchange = null;
        Res.imgBtnHelpUp = null;
        Res.imgBtnHelpDown = null;
        Res.aniClick = null;
        Res.imgLogo = null;
        Res.imgSwitching = null;
        Res.aniBadge = null;
        Res.imgSmallChannel = null;
        Res.aniSmallChannelBack = null;
        Res.sndButton = null;
        Res.sndToast = null;
        Res.sndReelStop = null;
        Res.sndReachStart = null;
        Res.sndReel = null;
        Res.sndReelEnd = null;
        Res.sndReach = null;
        Res.sndReachSuccess = null;
        Res.sndRoll = null;
        Res.sndSliding = null;
        Res.sndDocking = null;
        Res.sndAlram = null;
        Res.sndGlitter = null;
        Res.sndFire = null;
        Res.sndFireAlram = null;
        Res.sndCannon = null;
        Res.sndCelemony = null;
    }

    public Res() {
        super();
    }

    public static void dispose() {  // has try-catch handlers
        try {
            Res.leaner.dispose();
        }
        catch(Exception v0) {
        }

        TextureAtlas v0_1 = null;
        try {
            Res.leaner = v0_1;
            goto label_4;
        }
        catch(Exception v0) {
            goto label_4;
        }

        try {
        label_4:
            Res.nearest.dispose();
        }
        catch(Exception v0) {
        }

        v0_1 = null;
        try {
            Res.nearest = v0_1;
        }
        catch(Exception v0) {
        }

        Res.stopSound();
        Res.disposeSound();
    }

    private static void disposeSound() {  // has try-catch handlers
        try {
            Res.sndButton.dispose();
            Res.sndToast.dispose();
            Res.sndReel.dispose();
            Res.sndReelEnd.dispose();
            Res.sndReelStop.dispose();
            Res.sndReach.dispose();
            Res.sndReachStart.dispose();
            Res.sndReachSuccess.dispose();
            Res.sndRoll.dispose();
            Res.sndSliding.dispose();
            Res.sndDocking.dispose();
            Res.sndAlram.dispose();
            Res.sndGlitter.dispose();
            Res.sndFire.dispose();
            Res.sndFireAlram.dispose();
            Res.sndCannon.dispose();
            Res.sndCelemony.dispose();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "Res->disposeSound(1)", Util.getExceptionMessage(v0));
        }

        Sound v1 = null;
        try {
            Res.sndButton = v1;
            Res.sndToast = null;
            Res.sndReel = null;
            Res.sndReelEnd = null;
            Res.sndReelStop = null;
            Res.sndReach = null;
            Res.sndReachStart = null;
            Res.sndReachSuccess = null;
            Res.sndRoll = null;
            Res.sndSliding = null;
            Res.sndDocking = null;
            Res.sndAlram = null;
            Res.sndGlitter = null;
            Res.sndCannon = null;
            Res.sndFire = null;
            Res.sndFireAlram = null;
            Res.sndCelemony = null;
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "Res->disposeSound(2)", Util.getExceptionMessage(v0));
        }
    }

    public static void ini() {
        Res.leaner = new TextureAtlas(Gdx.files.internal("data/_linear.atlas"));
        Res.nearest = new TextureAtlas(Gdx.files.internal("data/_nearest.atlas"));
        Res.iniBackground();
        Res.iniEtc();
        Res.iniEtc2();
        Res.iniSound();
        Res.iniCardBaseLine();
        Res.iniCards();
        Res.iniMonitor();
        Res.iniLine();
        Res.iniCannons();
        Res.iniLamps();
        Res.iniGage();
        Res.iniMachine();
        Res.iniMenu();
        Res.iniRobot();
    }

    public static void iniBackground() {
        TextureRegion[] v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_left");
        Res.imgGameBack1 = new Img(v2);
        Res.imgGameBack1.setSize(Util.getWidth(400f), Util.getHeight(480f));
        Res.imgGameBack1.setPosition(Util.getLeft(0f), Util.getTop(0f, Res.imgGameBack1.height));
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_right");
        Res.imgGameBack2 = new Img(v2);
        Res.imgGameBack2.setSize(Util.getWidth(400f), Util.getHeight(480f));
        Res.imgGameBack2.setPosition(Util.getLeft(400f), Util.getTop(0f, Res.imgGameBack2.height));
        TextureRegion[] v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_channel1");
        v3[1] = Res.leaner.findRegion("back_channel2");
        Res.aniBackChannel = new Ani(0, 0.1f, v3);
        Res.aniBackChannel.setSize(Util.getWidth(114f), Util.getHeight(47f));
        Res.aniBackChannel.setPosition(Util.getLeft(13f), Util.getTop(422f, Res.aniBackChannel.height));
        Res.aniBackChannel.play(true, 20);
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_no1");
        v3[1] = Res.leaner.findRegion("back_no2");
        Res.aniBackNo = new Ani(0, 0.1f, v3);
        Res.aniBackNo.setSize(Util.getWidth(113f), Util.getHeight(47f));
        Res.aniBackNo.setPosition(Util.getLeft(127f), Util.getTop(422f, Res.aniBackNo.height));
        Res.aniBackNo.play(true, 20);
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_gift1");
        v3[1] = Res.leaner.findRegion("back_gift2");
        Res.aniBackGift = new Ani(0, 0.1f, v3);
        Res.aniBackGift.setSize(Util.getWidth(182f), Util.getHeight(47f));
        Res.aniBackGift.setPosition(Util.getLeft(240f), Util.getTop(422f, Res.aniBackGift.height));
        Res.aniBackGift.play(true, 20);
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_spin1");
        v3[1] = Res.leaner.findRegion("back_spin2");
        Res.aniBackSpin = new Ani(0, 0.1f, v3);
        Res.aniBackSpin.setSize(Util.getWidth(155f), Util.getHeight(47f));
        Res.aniBackSpin.setPosition(Util.getLeft(422f), Util.getTop(422f, Res.aniBackSpin.height));
        Res.aniBackSpin.play(true, 20);
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_money1");
        v3[1] = Res.leaner.findRegion("back_money2");
        Res.aniBackMoney = new Ani(0, 0.1f, v3);
        Res.aniBackMoney.setSize(Util.getWidth(209f), Util.getHeight(47f));
        Res.aniBackMoney.setPosition(Util.getLeft(577f), Util.getTop(422f, Res.aniBackMoney.height));
        Res.aniBackMoney.play(true, 20);
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_menu1");
        Res.imgBackMenu1 = new Img(v2);
        Res.imgBackMenu1.setSize(Util.getWidth(400f), Util.getHeight(480f));
        Res.imgBackMenu1.setPosition(Util.getLeft(0f), Util.getTop(0f, Res.imgBackMenu1.height));
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_menu2");
        Res.imgBackMenu2 = new Img(v2);
        Res.imgBackMenu2.setSize(Util.getWidth(400f), Util.getHeight(480f));
        Res.imgBackMenu2.setPosition(Util.getLeft(400f), Util.getTop(0f, Res.imgBackMenu2.height));
        v3 = new TextureRegion[17];
        v3[0] = Res.leaner.findRegion("success_back1");
        v3[1] = Res.leaner.findRegion("success_back2");
        v3[2] = Res.leaner.findRegion("success_back3");
        v3[3] = Res.leaner.findRegion("success_back4");
        v3[4] = Res.leaner.findRegion("success_back5");
        v3[5] = Res.leaner.findRegion("success_back6");
        v3[6] = Res.leaner.findRegion("success_back7");
        v3[7] = Res.leaner.findRegion("success_back8");
        v3[8] = Res.leaner.findRegion("success_back7");
        v3[9] = Res.leaner.findRegion("success_back10");
        v3[10] = Res.leaner.findRegion("success_back11");
        v3[11] = Res.leaner.findRegion("success_back12");
        v3[12] = Res.leaner.findRegion("success_back13");
        v3[13] = Res.leaner.findRegion("success_back14");
        v3[14] = Res.leaner.findRegion("success_back15");
        v3[15] = Res.leaner.findRegion("success_back16");
        v3[16] = Res.leaner.findRegion("success_back17");
        Res.aniSuccess = new Ani(1, 0.14f, v3);
        Res.aniSuccess.setSize(Util.getWidth(400f), Util.getHeight(300f));
        Res.aniSuccess.setPosition(Util.getLeft(228f), Util.getTop(98f, Res.aniSuccess.height));
        Res.aniSuccess.setPart(0, "on", 14, 0.2f, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16});
        Res.aniSuccess.bHideWhenStop = true;
        Res.aniSuccess.iStopFrame = 16;
        Res.aniSuccess.visible = false;
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_reserve1");
        v3[1] = Res.leaner.findRegion("back_reserve2");
        Res.aniReserveBack = new Ani(1, 0.7f, v3);
        Res.aniReserveBack.setSize(Util.getWidth(323f), Util.getHeight(223f));
        Res.aniReserveBack.setPosition(Util.XCenter(421f, Res.aniReserveBack.width), Util.YCenter(258f, Res.aniReserveBack.height));
        Res.aniReserveBack.play(true);
        Res.aniReserveBack.visible = false;
        v2 = new TextureRegion[5];
        v2[0] = Res.leaner.findRegion("space1");
        v2[1] = Res.leaner.findRegion("space2");
        v2[2] = Res.leaner.findRegion("space3");
        v2[3] = Res.leaner.findRegion("space4");
        v2[4] = Res.leaner.findRegion("space5");
        Res.imgSpace = new Img(v2);
        Img v0 = Res.imgSpace;
        String[] v2_1 = new String[5];
        v2_1[0] = "N1";
        v2_1[1] = "N2";
        v2_1[2] = "N3";
        v2_1[3] = "B";
        v2_1[4] = "H";
        v0.setNames(v2_1);
        Res.imgSpace.setSize(Util.getWidth(440f), Util.getHeight(330f));
        Res.imgSpace.setPosition(Util.XCenter(421f, Res.imgSpace.width), Util.YCenter(258f, Res.imgSpace.height));
        Res.imgSpace.setImage("N1");
        v3 = new TextureRegion[3];
        v3[0] = Res.leaner.findRegion("warship1");
        v3[1] = Res.leaner.findRegion("warship2");
        v3[2] = Res.leaner.findRegion("warship3");
        Res.aniWarship = new Ani(0, 0.5f, v3);
        Res.aniWarship.setSize(Util.getWidth(338f), Util.getHeight(281f));
        Res.aniWarship.setPosition(Util.getLeft(220f), Util.getTop(117f, Res.aniWarship.height));
        Res.aniWarship.play(true);
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_chat");
        Res.imgChatBack = new Img(v2);
        Res.imgChatBack.setSize(Util.getWidth(504f), Util.getHeight(96f));
        Res.imgChatBack.setPosition(Util.getLeft(0f), Util.getTop(0f, Res.imgChatBack.height));
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_video");
        Res.imgVideoBack = new Img(v2);
        Res.imgVideoBack.setSize(Util.getWidth(400f), Util.getHeight(300f));
        Res.imgVideoBack.setPosition(Util.XCenter(428f, Res.imgVideoBack.width), Util.YCenter(148f, Res.imgVideoBack.height));
        Res.imgVideoBack.visible = false;
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_gage");
        Res.imgGageBack = new Img(v2);
        Res.imgGageBack.setSize(Util.getWidth(145f), Util.getHeight(226f));
        Res.imgGageBack.setPosition(Util.getLeft(655f), Util.getTop(204f, Res.imgGageBack.height));
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_batter");
        Res.imgBatterBack = new Img(v2);
        Res.imgBatterBack.setSize(Util.getWidth(93f), Util.getHeight(24f));
        Res.imgBatterBack.setPosition(Util.getLeft(530f), Util.getTop(103f, Res.imgBatterBack.height));
        Res.imgBatterBack.visible = false;
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("back_chance100");
        Res.imgChance100Back = new Img(v2);
        Res.imgChance100Back.setSize(Util.getWidth(178f), Util.getHeight(28f));
        Res.imgChance100Back.setPosition(Util.getLeft(449f), Util.getTop(102f, Res.imgChance100Back.height));
        Res.imgChance100Back.visible = false;
    }

    public static void iniCannons() {
        float v9 = 0.04f;
        int v8 = 41;
        int v7 = 2;
        int v0;
        for(v0 = 0; v0 < 3; ++v0) {
            Res.spCannon[v0] = Res.leaner.createSprite("cannon");
            Res.spCannon[v0].setOrigin(Util.getWidth(94f), Util.getHeight(94f));
            Res.spCannon[v0].setSize(Util.getWidth(188f), Util.getHeight(188f));
            Res.spCannon[v0].setRotation(0f);
        }

        Res.spCannon[0].setPosition(Util.XCenter(283f, Res.spCannon[0].getWidth()), Util.YCenter(31f, Res.spCannon[0].getHeight()));
        Res.spCannon[1].setPosition(Util.XCenter(419f, Res.spCannon[0].getWidth()), Util.YCenter(31f, Res.spCannon[0].getHeight()));
        Res.spCannon[v7].setPosition(Util.XCenter(561f, Res.spCannon[0].getWidth()), Util.YCenter(31f, Res.spCannon[0].getHeight()));
        for(v0 = 0; v0 < v8; ++v0) {
            Res.rgFire[v0] = Res.leaner.findRegion("fire" + String.valueOf(v0));
        }

        Res.fire[0] = new Fire(0, v9, v8);
        Res.fire[1] = new Fire(0, v9, v8);
        Res.fire[v7] = new Fire(0, v9, v8);
        Res.fire[0].setSize(Util.getWidth(180f), Util.getHeight(330f));
        Res.fire[1].setSize(Util.getWidth(180f), Util.getHeight(330f));
        Res.fire[v7].setSize(Util.getWidth(180f), Util.getHeight(330f));
        Res.fire[0].setPosition(Util.XCenter(Res.iBaseLinePixelX1, Res.fire[0].width), Util.getTop(92f, Res.fire[0].height));
        Res.fire[1].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.fire[1].width), Util.getTop(92f, Res.fire[1].height));
        Res.fire[v7].setPosition(Util.XCenter(Res.iBaseLinePixelX3, Res.fire[v7].width), Util.getTop(92f, Res.fire[v7].height));
        Res.fire[0].bHideWhenStop = true;
        Res.fire[1].bHideWhenStop = true;
        Res.fire[v7].bHideWhenStop = true;
    }

    private static void iniCardBaseLine() {
        Res.iBaseLinePixelX1 = 285f;
        Res.iBaseLinePixelX2 = 421f;
        Res.iBaseLinePixelX3 = 564f;
        Res.iBaseLinePixelY4 = 30f;
        Res.iBaseLinePixelY3 = 180f;
        Res.iBaseLinePixelY2 = 330f;
        Res.iBaseLinePixelY1 = 480f;
        Res.iBaseLinePixelY0 = 630f;
        Res.fBaseLineY4 = Util.YCenter(Res.iBaseLinePixelY4, 0f);
        Res.fBaseLineY3 = Util.YCenter(Res.iBaseLinePixelY3, 0f);
        Res.fBaseLineY2 = Util.YCenter(Res.iBaseLinePixelY2, 0f);
        Res.fBaseLineY1 = Util.YCenter(Res.iBaseLinePixelY1, 0f);
        Res.fBaseLineY0 = Util.YCenter(Res.iBaseLinePixelY0, 0f);
    }

    public static void iniCards() {
        int v5 = 6;
        Res.rgCard1 = new TextureRegion[v5];
        Res.rgCard2 = new TextureRegion[v5];
        Res.rgCard3 = new TextureRegion[v5];
        Res.rgCard4 = new TextureRegion[v5];
        Res.rgCard5 = new TextureRegion[v5];
        Res.rgCard6 = new TextureRegion[v5];
        Res.rgCard7 = new TextureRegion[v5];
        Res.rgCard8 = new TextureRegion[v5];
        int v0;
        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard1[v0] = Res.leaner.findRegion("obj/a" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard2[v0] = Res.leaner.findRegion("obj/b" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard3[v0] = Res.leaner.findRegion("obj/c" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard4[v0] = Res.leaner.findRegion("obj/d" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard5[v0] = Res.leaner.findRegion("obj/e" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard6[v0] = Res.leaner.findRegion("obj/f" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard7[v0] = Res.leaner.findRegion("obj/g" + String.valueOf(v0 + 1));
        }

        for(v0 = 0; v0 < v5; ++v0) {
            Res.rgCard8[v0] = Res.leaner.findRegion("obj/h" + String.valueOf(v0 + 1));
        }
    }

    public static void iniEtc() {
        int v11 = 10;
        int v10 = 3;
        TextureRegion[] v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("logo");
        Res.imgLogo = new Img(v2);
        Res.imgLogo.setSize(Util.getWidth(400f), Util.getHeight(300f));
        Res.imgLogo.setPosition(Util.getLeft(228f), Util.getTop(98f, Res.imgLogo.height));
        v2 = new TextureRegion[1];
        v2[0] = Res.leaner.findRegion("switching");
        Res.imgSwitching = new Img(v2);
        Res.imgSwitching.setSize(Util.getWidth(400f), Util.getHeight(300f));
        Res.imgSwitching.setPosition(Util.getLeft(228f), Util.getTop(98f, Res.imgSwitching.height));
        Res.imgSwitching.visible = true;
        TextureRegion[] v3 = new TextureRegion[v11];
        v3[0] = Res.leaner.findRegion("wave01");
        v3[1] = Res.leaner.findRegion("wave02");
        v3[2] = Res.leaner.findRegion("wave03");
        v3[v10] = Res.leaner.findRegion("wave04");
        v3[4] = Res.leaner.findRegion("wave05");
        v3[5] = Res.leaner.findRegion("wave06");
        v3[6] = Res.leaner.findRegion("wave07");
        v3[7] = Res.leaner.findRegion("wave08");
        v3[8] = Res.leaner.findRegion("wave09");
        v3[9] = Res.leaner.findRegion("wave10");
        Res.aniClick = new Ani(0, 0.01f, v3);
        Res.aniClick.bHideWhenStop = true;
        Res.aniClick.visible = false;
        v3 = new TextureRegion[7];
        v3[0] = Res.leaner.findRegion("badge1");
        v3[1] = Res.leaner.findRegion("badge2");
        v3[2] = Res.leaner.findRegion("badge3");
        v3[v10] = Res.leaner.findRegion("badge4");
        v3[4] = Res.leaner.findRegion("badge5");
        v3[5] = Res.leaner.findRegion("badge6");
        v3[6] = Res.leaner.findRegion("badge7");
        Res.aniBadge = new Ani(0, 0.13f, v3);
        Res.aniBadge.setSize(Util.getWidth(25f), Util.getHeight(125f));
        Res.aniBadge.setPosition(Util.getLeft(230f), Util.getTop(272f, Res.aniBadge.height));
        Res.aniBadge.play(true);
        v2 = new TextureRegion[2];
        v2[0] = Res.leaner.findRegion("channel_small_a");
        v2[1] = Res.leaner.findRegion("channel_small_b");
        Res.imgSmallChannel = new Img(v2);
        Img v1 = Res.imgSmallChannel;
        String[] v2_1 = new String[2];
        v2_1[0] = "0";
        v2_1[1] = "1";
        v1.setNames(v2_1);
        Res.imgSmallChannel.setSize(Util.getWidth(17f), Util.getHeight(13f));
        Res.imgSmallChannel.setPosition(Util.getLeft(656f), Util.getTop(275f, Res.imgSmallChannel.height));
        Res.imgSmallChannel.setImage("0");
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("back_small_channel1");
        v3[1] = Res.leaner.findRegion("back_small_channel2");
        Res.aniSmallChannelBack = new Ani(0, 0.1f, v3);
        Res.aniSmallChannelBack.setSize(Util.getWidth(83f), Util.getHeight(27f));
        Res.aniSmallChannelBack.setPosition(Util.getLeft(651f), Util.getTop(270f, Res.aniSmallChannelBack.height));
        Res.aniSmallChannelBack.play(true, 15);
        int v0;
        for(v0 = 0; v0 < v11; ++v0) {
            Res.rgGlitter[v0] = Res.leaner.findRegion("glitter" + String.valueOf(v0));
        }

        for(v0 = 0; v0 < v10; ++v0) {
            Res.glitter[v0] = new Glitter(0, 0.03f, v11);
            Res.glitter[v0].bHideWhenStop = true;
            Res.glitter[v0].setSize(Util.getWidth(((float)Global.cardSizeX)), Util.getHeight(((float)Global.cardSizeY)));
        }
    }

    public static void iniEtc2() {
        TextureRegion[] v1 = new TextureRegion[2];
        v1[0] = Res.leaner.findRegion("btn_menu1");
        v1[1] = Res.leaner.findRegion("btn_menu2");
        Res.btnMenu = new Ani(0, 2f, v1);
        Res.btnMenu.setSize(Util.getWidth(222f), Util.getHeight(70f));
        Res.btnMenu.setPosition(Util.getLeft(0f), Util.getTop(91f, Res.btnMenu.height));
        TextureRegion[] v2 = new TextureRegion[2];
        v2[0] = Res.leaner.findRegion("btn_change1");
        v2[1] = Res.leaner.findRegion("btn_change2");
        Res.btnSwitch = new Ani(0, 3f, v2);
        Res.btnSwitch.setSize(Util.getWidth(222f), Util.getHeight(70f));
        Res.btnSwitch.setPosition(Util.getLeft(0f), Util.getTop(148f, Res.btnSwitch.height));
        v2 = new TextureRegion[2];
        v2[0] = Res.leaner.findRegion("btn_reserve1");
        v2[1] = Res.leaner.findRegion("btn_reserve2");
        Res.btnReserve = new Ani(0, 4f, v2);
        Res.btnReserve.setSize(Util.getWidth(222f), Util.getHeight(70f));
        Res.btnReserve.setPosition(Util.getLeft(0f), Util.getTop(207f, Res.btnReserve.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_close");
        Res.btnExitGame = new Img(v1);
        Res.btnExitGame.setSize(Util.getWidth(93f), Util.getHeight(81f));
        Res.btnExitGame.setPosition(Util.getLeft(707f), Util.getTop(12f, Res.btnExitGame.height));
        Res.btnCoinBack1 = Res.leaner.createSprite("btn_coin_back1");
        Res.btnCoinBack1.setSize(Util.getWidth(195f), Util.getHeight(194f));
        Res.btnCoinBack1.setOrigin(Res.btnCoinBack1.getWidth() / 2f, Res.btnCoinBack1.getHeight() / 2f);
        Res.btnCoinBack1.setPosition(Util.getLeft(18f), Util.getTop(235f, Res.btnCoinBack1.getHeight()));
        Res.btnCoinBack2 = Res.leaner.createSprite("btn_coin_back2");
        Res.btnCoinBack2.setSize(Util.getWidth(195f), Util.getHeight(194f));
        Res.btnCoinBack2.setOrigin(Res.btnCoinBack1.getWidth() / 2f, Res.btnCoinBack1.getHeight() / 2f);
        Res.btnCoinBack2.setPosition(Util.getLeft(18f), Util.getTop(235f, Res.btnCoinBack1.getHeight()));
        Res.btnCoinBack3 = Res.leaner.createSprite("btn_coin_back3");
        Res.btnCoinBack3.setSize(Util.getWidth(195f), Util.getHeight(194f));
        Res.btnCoinBack3.setOrigin(Res.btnCoinBack1.getWidth() / 2f, Res.btnCoinBack1.getHeight() / 2f);
        Res.btnCoinBack3.setPosition(Util.getLeft(18f), Util.getTop(235f, Res.btnCoinBack1.getHeight()));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_coin");
        Res.btnCoin = new Img(v1);
        Res.btnCoin.setSize(Util.getWidth(149f), Util.getHeight(172f));
        Res.btnCoin.setPosition(Util.getLeft(40f), Util.getTop(256f, Res.btnCoin.height));
    }

    public static void iniGage() {
        int v9 = 3;
        int v8 = 2;
        int v5 = 10;
        Res.rgGage[0] = Res.leaner.findRegion("gage1");
        Res.rgGage[1] = Res.leaner.findRegion("gage2");
        Res.rgGage[v8] = Res.leaner.findRegion("gage3");
        Res.rgGage[v9] = Res.leaner.findRegion("gage4");
        Res.rgGage[4] = Res.leaner.findRegion("gage5");
        Res.rgGage[5] = Res.leaner.findRegion("gage6");
        Res.rgGage[6] = Res.leaner.findRegion("gage7");
        Res.rgGage[7] = Res.leaner.findRegion("gage8");
        Res.rgGage[8] = Res.leaner.findRegion("gage9");
        Res.rgGage[9] = Res.leaner.findRegion("gage10");
        Res.rgGage[v5] = Res.leaner.findRegion("gage11");
        Res.rgGage[11] = Res.leaner.findRegion("gage12");
        Res.rgGage[12] = Res.leaner.findRegion("gage13");
        Res.rgGage[13] = Res.leaner.findRegion("gage14");
        Res.rgGage[14] = Res.leaner.findRegion("gage15");
        int v0;
        for(v0 = 0; v0 < v5; ++v0) {
            Res.gage[v0] = new Gage();
            Res.gage[v0].setSize(Util.getWidth(5f), Util.getHeight(56f));
            Res.gage[v0].setPosition(Util.getLeft(((float)(v0 * 6 + 698))), Util.getBottom(415f));
            Gage v1 = Res.gage[v0];
            String[] v2 = new String[15];
            v2[0] = "0";
            v2[1] = "1";
            v2[v8] = "2";
            v2[v9] = "3";
            v2[4] = "4";
            v2[5] = "5";
            v2[6] = "6";
            v2[7] = "7";
            v2[8] = "8";
            v2[9] = "9";
            v2[v5] = "A";
            v2[11] = "B";
            v2[12] = "C";
            v2[13] = "D";
            v2[14] = "E";
            v1.setNames(v2);
            Res.gage[v0].setImage("0");
        }
    }

    public static void iniLamps() {
        TextureRegion[] v4 = new TextureRegion[15];
        v4[0] = Res.leaner.findRegion("right_lampa1");
        v4[1] = Res.leaner.findRegion("right_lampa2");
        v4[2] = Res.leaner.findRegion("right_lampa3");
        v4[3] = Res.leaner.findRegion("right_lampb1");
        v4[4] = Res.leaner.findRegion("right_lampb2");
        v4[5] = Res.leaner.findRegion("right_lampb3");
        v4[6] = Res.leaner.findRegion("right_lampc1");
        v4[7] = Res.leaner.findRegion("right_lampc2");
        v4[8] = Res.leaner.findRegion("right_lampc3");
        v4[9] = Res.leaner.findRegion("right_lampd1");
        v4[10] = Res.leaner.findRegion("right_lampd2");
        v4[11] = Res.leaner.findRegion("right_lampd3");
        v4[12] = Res.leaner.findRegion("right_lampe1");
        v4[13] = Res.leaner.findRegion("right_lampe2");
        v4[14] = Res.leaner.findRegion("right_lampe3");
        Res.aniLampRight = new Ani(9, 0.7f, v4);
        Res.aniLampRight.setSize(Util.getWidth(75f), Util.getHeight(190f));
        Res.aniLampRight.setPosition(Util.getLeft(662f), Util.getTop(83f, Res.aniLampRight.height));
        Res.aniLampRight.setPart(0, "????", 0, 0.2f, new int[]{0, 1, 2});
        Res.aniLampRight.setPart(1, "????", 0, 0.2f, new int[]{3, 4, 5});
        Res.aniLampRight.setPart(2, "????", 0, 0.2f, new int[]{6, 7, 8});
        Res.aniLampRight.setPart(3, "????", 0, 0.1f, new int[]{6, 7, 8, 9, 10, 11});
        Res.aniLampRight.setPart(4, "??????", 0, 0.2f, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        Res.aniLampRight.setPart(5, "????1", 0, 0.1f, new int[]{0, 1, 2, 6, 7, 8, 9, 10, 11});
        Res.aniLampRight.setPart(6, "????2", 0, 0.1f, new int[]{0, 1, 2, 12, 13, 14, 6, 7, 8, 12, 13, 14, 9, 10, 11, 12, 13, 14});
        Res.aniLampRight.setPart(7, "????3", 0, 0.1f, new int[]{0, 1, 2, 3, 12, 13, 14, 12, 13, 14, 4, 5, 6, 12, 13, 14, 12, 13, 14, 7, 8, 9, 10, 11, 12, 13, 14, 12, 13, 14});
        Res.aniLampRight.setPart(8, "????", 0, 0.1f, new int[]{12, 13, 15});
        Res.aniLampRight.play(true, "????");
        v4 = new TextureRegion[8];
        v4[0] = Res.leaner.findRegion("top_lamp1");
        v4[1] = Res.leaner.findRegion("top_lamp11");
        v4[2] = Res.leaner.findRegion("top_lamp2");
        v4[3] = Res.leaner.findRegion("top_lamp22");
        v4[4] = Res.leaner.findRegion("top_lamp3");
        v4[5] = Res.leaner.findRegion("top_lamp33");
        v4[6] = Res.leaner.findRegion("top_lamp4");
        v4[7] = Res.leaner.findRegion("top_lamp44");
        Res.aniLampTop = new Ani(6, 0.7f, v4);
        Res.aniLampTop.setSize(Util.getWidth(415f), Util.getHeight(81f));
        Res.aniLampTop.setPosition(Util.getLeft(221f), Util.getTop(11f, Res.aniLampTop.height));
        Res.aniLampTop.setPart(0, "????", 0, 0.3f, new int[]{0, 1});
        Res.aniLampTop.setPart(1, "????", 0, 0.3f, new int[]{2, 3});
        Res.aniLampTop.setPart(2, "????", 0, 0.3f, new int[]{6, 7});
        Res.aniLampTop.setPart(3, "??????", 0, 0.3f, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        Res.aniLampTop.setPart(4, "????", 0, 0.1f, new int[]{0, 1, 2, 3, 4, 5});
        Res.aniLampTop.setPart(5, "????", 0, 0.1f, new int[]{2, 3, 4, 5});
        Res.aniLampTop.play(true, "????");
        TextureRegion[] v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("top_circle1");
        v3[1] = Res.leaner.findRegion("top_circle2");
        Res.aniLampCircle = new Ani(1, 0.7f, v3);
        Res.aniLampCircle.setSize(Util.getWidth(368f), Util.getHeight(75f));
        Res.aniLampCircle.setPosition(Util.getLeft(240f), Util.getTop(0f, Res.aniLampCircle.height));
        Res.aniLampCircle.setPart(0, "default", 0, 0.3f, new int[]{0, 1});
        Res.aniLampCircle.play(false);
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("left_lamp1");
        v3[1] = Res.leaner.findRegion("empty");
        Res.aniLampLeft = new Ani(1, 0.5f, v3);
        Res.aniLampLeft.setSize(Util.getWidth(94f), Util.getHeight(217f));
        Res.aniLampLeft.setPosition(Util.getLeft(132f), Util.getTop(1f, Res.aniLampLeft.height));
        Res.aniLampLeft.setPart(0, "default", 0, 0.6f, new int[]{0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1});
        Res.aniLampLeft.play(true, "default");
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("bottom_lamp1");
        v3[1] = Res.leaner.findRegion("empty");
        Res.aniLampBottom = new Ani(1, 0.6f, v3);
        Res.aniLampBottom.setSize(Util.getWidth(433f), Util.getHeight(82f));
        Res.aniLampBottom.setPosition(Util.getLeft(202f), Util.getTop(398f, Res.aniLampBottom.height));
        Res.aniLampBottom.setPart(0, "default", 0, 0.8f, new int[]{0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1});
        Res.aniLampBottom.play(true, "default");
        v3 = new TextureRegion[2];
        v3[0] = Res.leaner.findRegion("middle_right_lamp1");
        v3[1] = Res.leaner.findRegion("empty");
        Res.aniLampMiddleRight = new Ani(1, 0.7f, v3);
        Res.aniLampMiddleRight.setSize(Util.getWidth(80f), Util.getHeight(268f));
        Res.aniLampMiddleRight.setPosition(Util.getLeft(641f), Util.getTop(147f, Res.aniLampMiddleRight.height));
        Res.aniLampMiddleRight.setPart(0, "default", 0, 0.7f, new int[]{0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1});
        Res.aniLampMiddleRight.play(true, "default");
    }

    public static void iniLine() {
        float v8 = 580f;
        float v7 = 65f;
        float v6 = 2f;
        int v0;
        for(v0 = 0; v0 < 37; ++v0) {
            Res.spLine[v0] = Res.nearest.createSprite("line" + String.valueOf(v0 + 1));
            Res.spLine[v0].setSize(Util.getWidth(500f), Util.getHeight(v7));
            Res.spLine[v0].setOrigin(Res.spLine[v0].getWidth() / v6, Res.spLine[v0].getHeight() / v6);
            Res.spCrossLine1[v0] = Res.nearest.createSprite("line" + String.valueOf(v0 + 1));
            Res.spCrossLine1[v0].setSize(Util.getWidth(v8), Util.getHeight(v7));
            Res.spCrossLine1[v0].setOrigin(Res.spCrossLine1[v0].getWidth() / v6, Res.spCrossLine1[v0].getHeight() / v6);
            Res.spCrossLine1[v0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.spCrossLine1[v0].getWidth()), Util.YCenter(Res.iBaseLinePixelY3 - (Res.iBaseLinePixelY3 - Res.iBaseLinePixelY2) / v6, Res.spCrossLine1[v0].getHeight()));
            Res.spCrossLine1[v0].setRotation(30f);
            Res.spCrossLine2[v0] = Res.nearest.createSprite("line" + String.valueOf(v0 + 1));
            Res.spCrossLine2[v0].setSize(Util.getWidth(v8), Util.getHeight(60f));
            Res.spCrossLine2[v0].setOrigin(Res.spCrossLine2[v0].getWidth() / v6, Res.spCrossLine2[v0].getHeight() / v6);
            Res.spCrossLine2[v0].setPosition(Util.XCenter(Res.iBaseLinePixelX2, Res.spCrossLine2[v0].getWidth()), Util.YCenter(Res.iBaseLinePixelY3 - (Res.iBaseLinePixelY3 - Res.iBaseLinePixelY2) / v6, Res.spCrossLine2[v0].getHeight()));
            Res.spCrossLine2[v0].setRotation(-30f);
        }
    }

    public static void iniMachine() {
        float v12 = 136f;
        int v11 = 2;
        float v9 = 125f;
        Res.rgMachineBack[0] = Res.leaner.findRegion("machine_back_black");
        Res.rgMachineBack[1] = Res.leaner.findRegion("machine_back_green");
        Res.rgMachineBack[v11] = Res.leaner.findRegion("machine_back_yellow");
        Res.rgMachineBack[3] = Res.leaner.findRegion("machine_back_red");
        Res.rgMachineScreen[0] = Res.leaner.findRegion("machine_screen0");
        Res.rgMachineScreen[1] = Res.leaner.findRegion("machine_screen1");
        Res.rgMachineScreen[v11] = Res.leaner.findRegion("machine_screen2");
        int v8;
        for(v8 = 0; v8 < 16; ++v8) {
            Res.machineBack[v8] = new MachineBack();
            Res.machineBack[v8].setSize(Util.getWidth(v9), Util.getHeight(67f));
            MachineBack v0 = Res.machineBack[v8];
            String[] v2 = new String[4];
            v2[0] = "0";
            v2[1] = "1";
            v2[v11] = "2";
            v2[3] = "3";
            v0.setNames(v2);
            Res.machineBack[v8].setImage("0");
            Res.machineScreen[v8] = new MachineScreen(v11, 0.5f, 3);
            Res.machineScreen[v8].setSize(Util.getWidth(v9), Util.getHeight(67f));
            MachineScreen v0_1 = Res.machineScreen[v8];
            int[] v5 = new int[1];
            v5[0] = 0;
            v0_1.setPart(0, "0", 0, 1f, v5);
            Res.machineScreen[v8].setPart(1, "1", 0, 0.5f, new int[]{1, 2});
            Res.machineScreen[v8].play(false);
        }

        Res.machineBack[0].setPosition(Util.getLeft(v12), Util.getTop(v9, Res.machineBack[0].height));
        Res.machineBack[1].setPosition(Util.getLeft(270f), Util.getTop(v9, Res.machineBack[1].height));
        Res.machineBack[v11].setPosition(Util.getLeft(404f), Util.getTop(v9, Res.machineBack[v11].height));
        Res.machineBack[3].setPosition(Util.getLeft(538f), Util.getTop(v9, Res.machineBack[3].height));
        Res.machineBack[4].setPosition(Util.getLeft(v12), Util.getTop(198f, Res.machineBack[4].height));
        Res.machineBack[5].setPosition(Util.getLeft(270f), Util.getTop(198f, Res.machineBack[5].height));
        Res.machineBack[6].setPosition(Util.getLeft(404f), Util.getTop(198f, Res.machineBack[6].height));
        Res.machineBack[7].setPosition(Util.getLeft(538f), Util.getTop(198f, Res.machineBack[7].height));
        Res.machineBack[8].setPosition(Util.getLeft(v12), Util.getTop(271f, Res.machineBack[8].height));
        Res.machineBack[9].setPosition(Util.getLeft(270f), Util.getTop(271f, Res.machineBack[9].height));
        Res.machineBack[10].setPosition(Util.getLeft(404f), Util.getTop(271f, Res.machineBack[10].height));
        Res.machineBack[11].setPosition(Util.getLeft(538f), Util.getTop(271f, Res.machineBack[11].height));
        Res.machineBack[12].setPosition(Util.getLeft(v12), Util.getTop(344f, Res.machineBack[12].height));
        Res.machineBack[13].setPosition(Util.getLeft(270f), Util.getTop(344f, Res.machineBack[13].height));
        Res.machineBack[14].setPosition(Util.getLeft(404f), Util.getTop(344f, Res.machineBack[14].height));
        Res.machineBack[15].setPosition(Util.getLeft(538f), Util.getTop(344f, Res.machineBack[15].height));
        Res.machineScreen[0].setPosition(Util.getLeft(v12), Util.getTop(v9, Res.machineScreen[0].height));
        Res.machineScreen[1].setPosition(Util.getLeft(270f), Util.getTop(v9, Res.machineScreen[1].height));
        Res.machineScreen[v11].setPosition(Util.getLeft(404f), Util.getTop(v9, Res.machineScreen[v11].height));
        Res.machineScreen[3].setPosition(Util.getLeft(538f), Util.getTop(v9, Res.machineScreen[3].height));
        Res.machineScreen[4].setPosition(Util.getLeft(v12), Util.getTop(198f, Res.machineScreen[4].height));
        Res.machineScreen[5].setPosition(Util.getLeft(270f), Util.getTop(198f, Res.machineScreen[5].height));
        Res.machineScreen[6].setPosition(Util.getLeft(404f), Util.getTop(198f, Res.machineScreen[6].height));
        Res.machineScreen[7].setPosition(Util.getLeft(538f), Util.getTop(198f, Res.machineScreen[7].height));
        Res.machineScreen[8].setPosition(Util.getLeft(v12), Util.getTop(271f, Res.machineScreen[8].height));
        Res.machineScreen[9].setPosition(Util.getLeft(270f), Util.getTop(271f, Res.machineScreen[9].height));
        Res.machineScreen[10].setPosition(Util.getLeft(404f), Util.getTop(271f, Res.machineScreen[10].height));
        Res.machineScreen[11].setPosition(Util.getLeft(538f), Util.getTop(271f, Res.machineScreen[11].height));
        Res.machineScreen[12].setPosition(Util.getLeft(v12), Util.getTop(344f, Res.machineScreen[12].height));
        Res.machineScreen[13].setPosition(Util.getLeft(270f), Util.getTop(344f, Res.machineScreen[13].height));
        Res.machineScreen[14].setPosition(Util.getLeft(404f), Util.getTop(344f, Res.machineScreen[14].height));
        Res.machineScreen[15].setPosition(Util.getLeft(538f), Util.getTop(344f, Res.machineScreen[15].height));
    }

    public static void iniMenu() {
        TextureRegion[] v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_machine_on");
        Res.imgBtnMachineOn = new Img(v1);
        Res.imgBtnMachineOn.setSize(Util.getWidth(110f), Util.getHeight(40f));
        Res.imgBtnMachineOn.setPosition(Util.getLeft(117f), Util.getTop(65f, Res.imgBtnMachineOn.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_charge_on");
        Res.imgBtnChargeOn = new Img(v1);
        Res.imgBtnChargeOn.setSize(Util.getWidth(110f), Util.getHeight(40f));
        Res.imgBtnChargeOn.setPosition(Util.getLeft(233f), Util.getTop(65f, Res.imgBtnChargeOn.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_exchange_on");
        Res.imgBtnExchangeOn = new Img(v1);
        Res.imgBtnExchangeOn.setSize(Util.getWidth(110f), Util.getHeight(40f));
        Res.imgBtnExchangeOn.setPosition(Util.getLeft(349f), Util.getTop(65f, Res.imgBtnExchangeOn.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_help_on");
        Res.imgBtnHelpOn = new Img(v1);
        Res.imgBtnHelpOn.setSize(Util.getWidth(110f), Util.getHeight(40f));
        Res.imgBtnHelpOn.setPosition(Util.getLeft(465f), Util.getTop(65f, Res.imgBtnHelpOn.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_ask_on");
        Res.imgBtnAskOn = new Img(v1);
        Res.imgBtnAskOn.setSize(Util.getWidth(110f), Util.getHeight(40f));
        Res.imgBtnAskOn.setPosition(Util.getLeft(581f), Util.getTop(65f, Res.imgBtnAskOn.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("black90");
        Res.imgBtnCloseMenu = new Img(v1);
        Res.imgBtnCloseMenu.setSize(Util.getWidth(85f), Util.getHeight(85f));
        Res.imgBtnCloseMenu.setPosition(Util.getLeft(715f), Util.getTop(85f, 0f));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("channel_left");
        Res.imgChannelLeft = new Img(v1);
        Res.imgChannelLeft.setSize(Util.getWidth(43f), Util.getHeight(63f));
        Res.imgChannelLeft.setPosition(Util.getLeft(57f), Util.getTop(245f, Res.imgChannelLeft.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("channel_right");
        Res.imgChannelRight = new Img(v1);
        Res.imgChannelRight.setSize(Util.getWidth(43f), Util.getHeight(63f));
        Res.imgChannelRight.setPosition(Util.getLeft(703f), Util.getTop(245f, Res.imgChannelRight.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_ask_input");
        Res.imgBtnAskInput = new Img(v1);
        Res.imgBtnAskInput.setSize(Util.getWidth(127f), Util.getHeight(40f));
        Res.imgBtnAskInput.setPosition(Util.getLeft(341f), Util.getTop(374f, Res.imgBtnAskInput.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("black90");
        Res.imgBtnExchangeBank = new Img(v1);
        Res.imgBtnExchangeBank.setSize(Util.getWidth(272f), Util.getHeight(24f));
        Res.imgBtnExchangeBank.setPosition(Util.getLeft(306f), Util.getTop(241f, Res.imgBtnExchangeBank.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("black90");
        Res.imgBtnExchangeOwner = new Img(v1);
        Res.imgBtnExchangeOwner.setSize(Util.getWidth(272f), Util.getHeight(24f));
        Res.imgBtnExchangeOwner.setPosition(Util.getLeft(306f), Util.getTop(270f, Res.imgBtnExchangeOwner.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("black90");
        Res.imgBtnMoneyMan = new Img(v1);
        Res.imgBtnMoneyMan.setSize(Util.getWidth(272f), Util.getHeight(24f));
        Res.imgBtnMoneyMan.setPosition(Util.getLeft(306f), Util.getTop(180f, Res.imgBtnMoneyMan.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("back_charge");
        Res.imgBackCharge = new Img(v1);
        Res.imgBackCharge.setSize(Util.getWidth(347f), Util.getHeight(186f));
        Res.imgBackCharge.setPosition(Util.getLeft(227f), Util.getTop(153f, Res.imgBackCharge.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_charge");
        Res.imgBtnCharge = new Img(v1);
        Res.imgBtnCharge.setSize(Util.getWidth(127f), Util.getHeight(40f));
        Res.imgBtnCharge.setPosition(Util.getLeft(341f), Util.getTop(374f, Res.imgBtnCharge.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("back_exchange");
        Res.imgBackExchange = new Img(v1);
        Res.imgBackExchange.setSize(Util.getWidth(347f), Util.getHeight(186f));
        Res.imgBackExchange.setPosition(Util.getLeft(227f), Util.getTop(153f, Res.imgBackExchange.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("btn_exchange");
        Res.imgBtnExchange = new Img(v1);
        Res.imgBtnExchange.setSize(Util.getWidth(127f), Util.getHeight(40f));
        Res.imgBtnExchange.setPosition(Util.getLeft(341f), Util.getTop(374f, Res.imgBtnCharge.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("back_help");
        Res.imgBackHelp = new Img(v1);
        Res.imgBackHelp.setSize(Util.getWidth(528f), Util.getHeight(315f));
        Res.imgBackHelp.setPosition(Util.getLeft(137f), Util.getTop(124f, Res.imgBackHelp.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("help_up");
        Res.imgBtnHelpUp = new Img(v1);
        Res.imgBtnHelpUp.setSize(Util.getWidth(50f), Util.getHeight(34f));
        Res.imgBtnHelpUp.setPosition(Util.getLeft(599f), Util.getTop(151f, Res.imgBtnHelpUp.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("help_down");
        Res.imgBtnHelpDown = new Img(v1);
        Res.imgBtnHelpDown.setSize(Util.getWidth(50f), Util.getHeight(34f));
        Res.imgBtnHelpDown.setPosition(Util.getLeft(599f), Util.getTop(348f, Res.imgBtnHelpDown.height));
        v1 = new TextureRegion[1];
        v1[0] = Res.leaner.findRegion("back_help");
        Res.imgBackAsk = new Img(v1);
        Res.imgBackAsk.setSize(Util.getWidth(528f), Util.getHeight(265f));
        Res.imgBackAsk.setPosition(Util.getLeft(137f), Util.getTop(124f, Res.imgBackAsk.height));
    }

    public static void iniMonitor() {
        TextureRegion[] v5 = new TextureRegion[12];
        v5[0] = Res.leaner.findRegion("monitor_idle1");
        v5[1] = Res.leaner.findRegion("monitor_stop1");
        v5[2] = Res.leaner.findRegion("monitor_play1");
        v5[3] = Res.leaner.findRegion("monitor_play2");
        v5[4] = Res.leaner.findRegion("monitor_hint1");
        v5[5] = Res.leaner.findRegion("monitor_hint2");
        v5[6] = Res.leaner.findRegion("monitor_line1");
        v5[7] = Res.leaner.findRegion("monitor_line2");
        v5[8] = Res.leaner.findRegion("monitor_destroy1");
        v5[9] = Res.leaner.findRegion("monitor_destroy2");
        v5[10] = Res.leaner.findRegion("monitor_fever1");
        v5[11] = Res.leaner.findRegion("monitor_fever2");
        Res.aniMonitor = new Ani(8, 0.5f, v5);
        Res.aniMonitor.setSize(Util.getWidth(62f), Util.getHeight(40f));
        Res.aniMonitor.setPosition(Util.getLeft(674f), Util.getTop(303f, Res.aniMonitor.height));
        Ani v0 = Res.aniMonitor;
        int[] v5_1 = new int[1];
        v5_1[0] = 0;
        v0.setPart(0, "IDLE", 0, 0.3f, v5_1);
        Ani v5_2 = Res.aniMonitor;
        int[] v10 = new int[1];
        v10[0] = 1;
        v5_2.setPart(1, "STOP", 0, 0.3f, v10);
        Res.aniMonitor.setPart(2, "PLAY", 0, 0.3f, new int[]{2, 3});
        Res.aniMonitor.setPart(3, "HINT", 0, 0.3f, new int[]{4, 5});
        Res.aniMonitor.setPart(4, "LINE", 0, 0.3f, new int[]{6, 7});
        Res.aniMonitor.setPart(5, "DESTROY", 0, 0.3f, new int[]{8, 9});
        Res.aniMonitor.setPart(6, "FEVER", 0, 0.3f, new int[]{10, 11});
        Res.aniMonitor.setPart(7, "BATTER", 0, 0.3f, new int[]{8, 11});
        Res.aniMonitor.play(true, "IDLE");
        TextureRegion[] v2 = new TextureRegion[2];
        v2[0] = Res.leaner.findRegion("sp1");
        v2[1] = Res.leaner.findRegion("empty");
        Res.aniSp = new Ani(0, 0.3f, v2);
        Res.aniSp.setSize(Util.getWidth(28f), Util.getHeight(25f));
        Res.aniSp.setPosition(Util.getLeft(748f), Util.getTop(268f, Res.aniSp.height));
        Res.aniSp.visible = false;
        Res.aniSp.play(false);
        v2 = new TextureRegion[2];
        v2[0] = Res.leaner.findRegion("splamp1");
        v2[1] = Res.leaner.findRegion("splamp2");
        Res.aniSpLamp = new Ani(0, 0.3f, v2);
        Res.aniSpLamp.setSize(Util.getWidth(26f), Util.getHeight(17f));
        Res.aniSpLamp.setPosition(Util.getLeft(751f), Util.getTop(298f, Res.aniSpLamp.height));
        Res.aniSpLamp.visible = false;
        Res.aniSpLamp.play(false);
    }

    public static void iniRobot() {
        int v15 = 3;
        float v4 = 0.5f;
        int v14 = 2;
        Res.rgRobot[0] = Res.leaner.findRegion("robota1");
        Res.rgRobot[1] = Res.leaner.findRegion("robota2");
        Res.rgRobot[v14] = Res.leaner.findRegion("robotb1");
        Res.rgRobot[v15] = Res.leaner.findRegion("robotb2");
        Res.rgRobot[4] = Res.leaner.findRegion("robotc1");
        Res.rgRobot[5] = Res.leaner.findRegion("robotc2");
        Res.rgRobot[6] = Res.leaner.findRegion("robotd1");
        Res.rgRobot[7] = Res.leaner.findRegion("robotd2");
        int v13;
        for(v13 = 0; v13 < Res.robot.length; ++v13) {
            Res.robot[v13] = new Robot(4, v4, 6);
            Res.robot[v13].setSize(Util.getWidth(60f), Util.getHeight(92f));
            Res.robot[v13].setPart(0, "1", 0, v4, new int[]{0, 1});
            Res.robot[v13].setPart(1, "2", 0, v4, new int[]{2, 3});
            Res.robot[v13].setPart(v14, "3", 0, v4, new int[]{4, 5});
            Res.robot[v13].setPart(v15, "4", 0, v4, new int[]{6, 7});
            Res.robot[v13].visible = false;
            Res.robot[v13].play(false);
        }
    }

    public static void iniSound() {  // has try-catch handlers
        try {
            Res.sndButton = Gdx.audio.newSound(Gdx.files.internal("sound/click.ogg"));
            Res.sndToast = Gdx.audio.newSound(Gdx.files.internal("sound/alram.ogg"));
            Res.sndReel = Gdx.audio.newSound(Gdx.files.internal("sound/reel.ogg"));
            Res.sndReelEnd = Gdx.audio.newSound(Gdx.files.internal("sound/reelend.ogg"));
            Res.sndReelStop = Gdx.audio.newSound(Gdx.files.internal("sound/reelstop.ogg"));
            Res.sndReach = Gdx.audio.newSound(Gdx.files.internal("sound/reach.ogg"));
            Res.sndReachStart = Gdx.audio.newSound(Gdx.files.internal("sound/reachstart.ogg"));
            Res.sndReachSuccess = Gdx.audio.newSound(Gdx.files.internal("sound/reachsuccess.ogg"));
            Res.sndRoll = Gdx.audio.newSound(Gdx.files.internal("sound/roll.ogg"));
            Res.sndSliding = Gdx.audio.newSound(Gdx.files.internal("sound/sliding.ogg"));
            Res.sndDocking = Gdx.audio.newSound(Gdx.files.internal("sound/docking.ogg"));
            Res.sndAlram = Gdx.audio.newSound(Gdx.files.internal("sound/alram.ogg"));
            Res.sndGlitter = Gdx.audio.newSound(Gdx.files.internal("sound/glitter.ogg"));
            Res.sndFire = Gdx.audio.newSound(Gdx.files.internal("sound/fire.ogg"));
            Res.sndFireAlram = Gdx.audio.newSound(Gdx.files.internal("sound/firealram.ogg"));
            Res.sndCannon = Gdx.audio.newSound(Gdx.files.internal("sound/cannon.ogg"));
            Res.sndCelemony = Gdx.audio.newSound(Gdx.files.internal("sound/celemony.ogg"));
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "Res->iniSound", Util.getExceptionMessage(v0));
        }
    }

    public static void stopSound() {  // has try-catch handlers
        try {
            if(Res.sndReel != null) {
                Res.sndReel.stop();
            }

            if(Res.sndReelEnd != null) {
                Res.sndReelEnd.stop();
            }

            if(Res.sndButton != null) {
                Res.sndButton.stop();
            }

            if(Res.sndToast != null) {
                Res.sndToast.stop();
            }

            if(Res.sndReelStop != null) {
                Res.sndReelStop.stop();
            }

            if(Res.sndReach != null) {
                Res.sndReach.stop();
            }

            if(Res.sndReachStart != null) {
                Res.sndReachStart.stop();
            }

            if(Res.sndReachSuccess != null) {
                Res.sndReachSuccess.stop();
            }

            if(Res.sndRoll != null) {
                Res.sndRoll.stop();
            }

            if(Res.sndSliding != null) {
                Res.sndSliding.stop();
            }

            if(Res.sndDocking != null) {
                Res.sndDocking.stop();
            }

            if(Res.sndAlram != null) {
                Res.sndAlram.stop();
            }

            if(Res.sndGlitter != null) {
                Res.sndGlitter.stop();
            }

            if(Res.sndFire != null) {
                Res.sndFire.stop();
            }

            if(Res.sndFireAlram != null) {
                Res.sndFire.stop();
            }

            if(Res.sndCannon != null) {
                Res.sndCannon.stop();
            }

            if(Res.sndCelemony == null) {
                return;
            }

            Res.sndCelemony.stop();
        }
        catch(Exception v0) {
            Util.sysout("ERROR", "Res->stopSound", Util.getExceptionMessage(v0));
        }
    }
}

