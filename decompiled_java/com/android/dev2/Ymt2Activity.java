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

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer$OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout$LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import com.android.dev2.libgdx.Dialog;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer$Task;
import java.util.ArrayList;
import java.util.List;

public class Ymt2Activity extends AndroidApplication implements MediaPlayer$OnCompletionListener, VideoPlayer {
    class CheckGameMsg implements Runnable {
        CheckGameMsg(Ymt2Activity arg1) {
            Ymt2Activity.this = arg1;
            super();
        }

        public void run() {  // has try-catch handlers
            Message v3;
            String[] v0;
            Object v4;
            float v8 = 0.01f;
            while(true) {
                if(!Thread.currentThread().isInterrupted()) {
                    goto label_35;
                }

                return;
                try {
                    while(true) {
                    label_35:
                        if(Ymt2Activity.lsGameMsg.size() > 0) {
                            goto label_5;
                        }

                        goto label_38;
                    label_5:
                        v4 = Ymt2Activity.lsGameMsg.get(0);
                        Ymt2Activity.lsGameMsg.remove(0);
                        v0 = ((String)v4).split("\t");
                        String v1 = v0[0];
                        if(!"start_video".equals(v1)) {
                            goto label_60;
                        }

                        if("".equals(Ymt2Activity.videoFileName)) {
                            continue;
                        }

                        if(Ymt2Activity.videoFileName == null) {
                            continue;
                        }

                        if(Ymt2Activity.bVideoStart.booleanValue()) {
                            continue;
                        }

                        Ymt2Activity.access$1(Boolean.valueOf(true));
                        v3 = new Message();
                        v3.what = 100;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_60:
                        if(!"cannon_on".equals(v1)) {
                            goto label_72;
                        }

                        v3 = new Message();
                        v3.what = 210;
                        v3.obj = v4;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_72:
                        if(!"cannon_off".equals(v1)) {
                            goto label_82;
                        }

                        v3 = new Message();
                        v3.what = 220;
                        v3.obj = v4;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_82:
                        if(!"show_video".equals(v1)) {
                            goto label_91;
                        }

                        v3 = new Message();
                        v3.what = 120;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_91:
                        if(!"hide_video".equals(v1)) {
                            goto label_100;
                        }

                        v3 = new Message();
                        v3.what = 140;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_100:
                        if(!"show_cannon".equals(v1)) {
                            goto label_109;
                        }

                        v3 = new Message();
                        v3.what = 230;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_109:
                        if(!"hide_cannon".equals(v1)) {
                            goto label_118;
                        }

                        v3 = new Message();
                        v3.what = 240;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_118:
                        if(!"stop_video".equals(v1)) {
                            goto label_127;
                        }

                        v3 = new Message();
                        v3.what = 110;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_127:
                        if(!"hide_loading".equals(v1)) {
                            goto label_136;
                        }

                        v3 = new Message();
                        v3.what = 150;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_136:
                        if(!"hide_ui".equals(v1)) {
                            goto label_145;
                        }

                        v3 = new Message();
                        v3.what = 170;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_145:
                        if(!"show_ui".equals(v1)) {
                            goto label_154;
                        }

                        v3 = new Message();
                        v3.what = 160;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_154:
                        if(!"saved_id".equals(v1)) {
                            goto label_164;
                        }

                        v3 = new Message();
                        v3.obj = v4;
                        v3.what = 180;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_164:
                        if(!"agent".equals(v1)) {
                            goto label_174;
                        }

                        v3 = new Message();
                        v3.obj = v4;
                        v3.what = 190;
                        Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        continue;
                    label_174:
                        if(!"update_url".equals(v1)) {
                            goto label_188;
                        }

                        goto label_177;
                    }
                }
                catch(Exception v6) {
                    goto label_71;
                }

                try {
                label_177:
                    Intent v2 = new Intent("android.intent.action.VIEW");
                    v2.setData(Uri.parse(v0[1]));
                    Ymt2Activity.this.startActivity(v2);
                    goto label_35;
                }
                catch(Exception v6) {
                    goto label_35;
                }

                try {
                label_188:
                    if(!"regist_success".equals(v1)) {
                        goto label_35;
                    }

                    v3 = new Message();
                    v3.obj = v4;
                    v3.what = 200;
                    Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                    goto label_35;
                label_38:
                    if(Ymt2Activity.fVideoReallyStartDelay >= 0.001f) {
                        Ymt2Activity.access$9(Ymt2Activity.fVideoReallyStartDelay + v8);
                        if(Ymt2Activity.fVideoReallyStartDelay >= v8) {
                            Ymt2Activity.access$9(0f);
                            Ymt2Activity.access$10(true);
                            v3 = new Message();
                            v3.what = 130;
                            Ymt2Activity.ProcessGameMsg.sendMessage(v3);
                        }
                    }
                }
                catch(Exception v6) {
                    goto label_71;
                }

                long v6_1 = 20;
                try {
                    Thread.sleep(v6_1);
                    continue;
                }
                catch(Exception v6) {
                label_71:
                    continue;
                }
                catch(InterruptedException v6_2) {
                    continue;
                }
            }
        }
    }

    static Handler ProcessGameMsg;
    private static SpinnerAdapter adapter;
    private static Animation ani1;
    private static Animation ani2;
    private static Animation ani3;
    private static Animation ani4;
    private static Animation ani5;
    private static Animation ani6;
    private static boolean bShowVideo;
    private static boolean bVideoReallyStart;
    private static Boolean bVideoStart;
    public static Context context;
    private static EditText etAgent;
    private static EditText etId;
    private static EditText etPwd;
    private static EditText etRegistFormId;
    private static EditText etRegistFormPwd;
    private static EditText etRegistFormTel;
    private static float fFontSize;
    private static float fVideoReallyStartDelay;
    private static int iVideoHeight;
    private static int iVideoPosition;
    private static int iVideoWidth;
    private static ImageButton ibLogin;
    private static ImageButton ibLoginForm;
    private static ImageButton ibRegist;
    private static ImageButton ibRegistForm;
    private static ImageView ivCannon1;
    private static ImageView ivCannon2;
    private static ImageView ivCannon3;
    private static ImageView ivLoading;
    private static ImageView ivLoadingBack;
    private static Method loginScreenMethod;
    private static Object loginScreenObj;
    private static List lsGameMsg;
    private static String playingVideoFileName;
    private static Resources res;
    private static int screen_pixel_height;
    private static int screen_pixel_width;
    private static Spinner spRegistFormAgent;
    private static int standard_screen_pixel_height;
    private static int standard_screen_pixel_width;
    private static Uri uri;
    private static Method videoCompletedMethod;
    private static String videoFileName;
    private static String videoParam;
    private static Object videoRequestObj;
    private static MyVideoView videoView;

    static  {
        Ymt2Activity.standard_screen_pixel_width = 800;
        Ymt2Activity.standard_screen_pixel_height = 480;
        Ymt2Activity.ani1 = new RotateAnimation(0f, 90f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.ani2 = new RotateAnimation(0f, 90f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.ani3 = new RotateAnimation(0f, 90f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.ani4 = new RotateAnimation(90f, 0f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.ani5 = new RotateAnimation(90f, 0f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.ani6 = new RotateAnimation(90f, 0f, 1, 0.5f, 1, 0.5f);
        Ymt2Activity.bShowVideo = false;
        Ymt2Activity.bVideoStart = Boolean.valueOf(false);
        Ymt2Activity.bVideoReallyStart = false;
        Ymt2Activity.fVideoReallyStartDelay = 0f;
        Ymt2Activity.videoFileName = "";
        Ymt2Activity.playingVideoFileName = "";
        Ymt2Activity.videoParam = "";
        Ymt2Activity.iVideoPosition = 0;
        Ymt2Activity.iVideoWidth = 1;
        Ymt2Activity.iVideoHeight = 1;
        Ymt2Activity.videoRequestObj = null;
        Ymt2Activity.videoCompletedMethod = null;
        Ymt2Activity.lsGameMsg = new ArrayList();
        Ymt2Activity.loginScreenObj = null;
        Ymt2Activity.loginScreenMethod = null;
        Ymt2Activity.ProcessGameMsg = new Handler() {
            public void handleMessage(Message msg) {  // has try-catch handlers
                String v4;
                String v2;
                String[] v0;
                switch(msg.what) {
                    case 100: {
                        goto label_5;
                    }
                    case 110: {
                        goto label_57;
                    }
                    case 120: {
                        goto label_94;
                    }
                    case 130: {
                        goto label_322;
                    }
                    case 140: {
                        goto label_116;
                    }
                    case 150: {
                        goto label_340;
                    }
                    case 160: {
                        goto label_355;
                    }
                    case 170: {
                        goto label_351;
                    }
                    case 180: {
                        goto label_359;
                    }
                    case 200: {
                        goto label_375;
                    }
                    case 210: {
                        goto label_190;
                    }
                    case 220: {
                        goto label_256;
                    }
                    case 230: {
                        goto label_157;
                    }
                    case 240: {
                        goto label_130;
                    }
                }

                return;
            label_322:
                if(Ymt2Activity.videoView == null) {
                    return;
                }

                if("".equals(Ymt2Activity.playingVideoFileName)) {
                    return;
                }

                if((Ymt2Activity.bVideoReallyStart) && (Ymt2Activity.bShowVideo)) {
                    Ymt2Activity.videoView.setSize(Ymt2Activity.iVideoWidth, Ymt2Activity.iVideoHeight);
                    return;
                }

                Ymt2Activity.videoView.setSize(1, 1);
                return;
                try {
                label_130:
                    Ymt2Activity.ivCannon1.setAlpha(0);
                    Ymt2Activity.ivCannon1.setVisibility(4);
                    Ymt2Activity.ivCannon1.invalidate();
                    Ymt2Activity.ivCannon2.setAlpha(0);
                    Ymt2Activity.ivCannon2.setVisibility(4);
                    Ymt2Activity.ivCannon2.invalidate();
                    Ymt2Activity.ivCannon3.setAlpha(0);
                    Ymt2Activity.ivCannon3.setVisibility(4);
                    Ymt2Activity.ivCannon3.invalidate();
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_355:
                    Ymt2Activity.showUI();
                    return;
                }
                catch(Exception v6) {
                    return;
                }

            label_5:
                if(!Ymt2Activity.bVideoStart.booleanValue()) {
                    return;
                }

                Ymt2Activity.access$1(Boolean.valueOf(false));
                String v5 = Ymt2Activity.videoFileName;
                Ymt2Activity.access$3("");
                if(Ymt2Activity.res.getIdentifier("com.android.dev2:raw/" + v5, null, null) <= 0) {
                    v5 = "m000";
                }

                Ymt2Activity.access$5(Uri.parse("android.resource://com.android.dev2/raw/" + v5));
                Ymt2Activity.access$6(v5);
                Ymt2Activity.videoView.setVideoURI(Ymt2Activity.uri);
                Ymt2Activity.videoView.setSize(1, 1);
                Ymt2Activity.videoView.start();
                Ymt2Activity.videoView.setVisibility(0);
                Ymt2Activity.videoView.bringToFront();
                Ymt2Activity.videoView.setZOrderOnTop(true);
                Ymt2Activity.videoView.setZOrderMediaOverlay(true);
                Ymt2Activity.access$9(0.002f);
                Ymt2Activity.access$10(false);
                Ymt2Activity.access$11(false);
                Ymt2Activity.ivCannon1.bringToFront();
                Ymt2Activity.ivCannon2.bringToFront();
                Ymt2Activity.ivCannon3.bringToFront();
                return;
                try {
                label_359:
                    v0 = msg.obj.split("\t");
                    goto label_365;
                }
                catch(Exception v6) {
                    goto label_374;
                }

                try {
                label_365:
                    v2 = v0[1];
                }
                catch(Exception v6) {
                }

                int v6_1 = 2;
                try {
                    v4 = v0[v6_1];
                    goto label_368;
                }
                catch(Exception v6) {
                    goto label_368;
                }

                try {
                label_368:
                    Ymt2Activity.etId.setText(((CharSequence)v2));
                    Ymt2Activity.etPwd.setText(((CharSequence)v4));
                    return;
                }
                catch(Exception v6) {
                label_374:
                    return;
                }

                try {
                label_116:
                    Ymt2Activity.access$11(false);
                    if(Ymt2Activity.videoView == null) {
                        return;
                    }

                    if("".equals(Ymt2Activity.playingVideoFileName)) {
                        return;
                    }

                    Ymt2Activity.videoView.setSize(1, 1);
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_340:
                    Ymt2Activity.ivLoading.setVisibility(4);
                    Ymt2Activity.ivLoading.clearAnimation();
                    Ymt2Activity.ivLoadingBack.setVisibility(4);
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_375:
                    v0 = msg.obj.split("\t");
                    goto label_381;
                }
                catch(Exception v6) {
                    goto label_399;
                }

                try {
                label_381:
                    v2 = v0[1];
                }
                catch(Exception v6) {
                }

                v6_1 = 2;
                try {
                    v4 = v0[v6_1];
                    goto label_384;
                }
                catch(Exception v6) {
                    goto label_384;
                }

                try {
                label_384:
                    Ymt2Activity.etId.setText(((CharSequence)v2));
                    Ymt2Activity.etPwd.setText(((CharSequence)v4));
                    Ymt2Activity.etRegistFormId.setText("");
                    Ymt2Activity.etRegistFormPwd.setText("");
                    Ymt2Activity.etRegistFormTel.setText("");
                    return;
                }
                catch(Exception v6) {
                label_399:
                    return;
                }

                try {
                label_57:
                    Ymt2Activity.access$11(false);
                    if(Ymt2Activity.videoView == null) {
                        goto label_69;
                    }

                    if("".equals(Ymt2Activity.playingVideoFileName)) {
                        goto label_69;
                    }

                    Ymt2Activity.videoView.setSize(1, 1);
                }
                catch(Exception v6) {
                    goto label_69;
                }

                try {
                label_69:
                    Ymt2Activity.access$1(Boolean.valueOf(false));
                    Ymt2Activity.access$6("");
                    Ymt2Activity.access$3("");
                    Ymt2Activity.access$16("");
                    Ymt2Activity.access$9(0f);
                    Ymt2Activity.access$10(false);
                    Ymt2Activity.access$11(false);
                    Ymt2Activity.videoView.setVisibility(4);
                    Ymt2Activity.videoView.stopPlayback();
                    Ymt2Activity.access$17(0);
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_157:
                    Ymt2Activity.ivCannon1.setAlpha(255);
                    Ymt2Activity.ivCannon1.setVisibility(0);
                    Ymt2Activity.ivCannon1.bringToFront();
                    Ymt2Activity.ivCannon1.invalidate();
                    Ymt2Activity.ivCannon2.setAlpha(255);
                    Ymt2Activity.ivCannon2.setVisibility(0);
                    Ymt2Activity.ivCannon2.bringToFront();
                    Ymt2Activity.ivCannon2.invalidate();
                    Ymt2Activity.ivCannon3.setAlpha(255);
                    Ymt2Activity.ivCannon3.setVisibility(0);
                    Ymt2Activity.ivCannon3.bringToFront();
                    Ymt2Activity.ivCannon3.invalidate();
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_94:
                    Ymt2Activity.access$11(true);
                    if(Ymt2Activity.videoView == null) {
                        return;
                    }

                    if("".equals(Ymt2Activity.playingVideoFileName)) {
                        return;
                    }

                    if(!Ymt2Activity.bVideoReallyStart) {
                        goto label_110;
                    }

                    Ymt2Activity.videoView.setSize(Ymt2Activity.iVideoWidth, Ymt2Activity.iVideoHeight);
                    return;
                label_110:
                    Ymt2Activity.videoView.setSize(1, 1);
                    return;
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_190:
                    v0 = msg.obj.split("\t");
                    if("1".equals(v0[1])) {
                        Ymt2Activity.ivCannon1.clearAnimation();
                        Ymt2Activity.ivCannon1.setAlpha(255);
                        Ymt2Activity.ivCannon1.setVisibility(0);
                        Ymt2Activity.ivCannon1.bringToFront();
                        Ymt2Activity.ivCannon1.invalidate();
                        Ymt2Activity.ivCannon1.startAnimation(Ymt2Activity.ani1);
                    }

                    if("2".equals(v0[1])) {
                        Ymt2Activity.ivCannon2.clearAnimation();
                        Ymt2Activity.ivCannon2.setAlpha(255);
                        Ymt2Activity.ivCannon2.setVisibility(0);
                        Ymt2Activity.ivCannon2.bringToFront();
                        Ymt2Activity.ivCannon2.invalidate();
                        Ymt2Activity.ivCannon2.startAnimation(Ymt2Activity.ani2);
                    }

                    if(!"3".equals(v0[1])) {
                        return;
                    }

                    Ymt2Activity.ivCannon3.clearAnimation();
                    Ymt2Activity.ivCannon3.setAlpha(255);
                    Ymt2Activity.ivCannon3.setVisibility(0);
                    Ymt2Activity.ivCannon3.bringToFront();
                    Ymt2Activity.ivCannon3.invalidate();
                    Ymt2Activity.ivCannon3.startAnimation(Ymt2Activity.ani3);
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_351:
                    Ymt2Activity.hideUI();
                    return;
                }
                catch(Exception v6) {
                    return;
                }

                try {
                label_256:
                    v0 = msg.obj.split("\t");
                    if("1".equals(v0[1])) {
                        Ymt2Activity.ivCannon1.clearAnimation();
                        Ymt2Activity.ivCannon1.setAlpha(255);
                        Ymt2Activity.ivCannon1.setVisibility(0);
                        Ymt2Activity.ivCannon1.bringToFront();
                        Ymt2Activity.ivCannon1.invalidate();
                        Ymt2Activity.ivCannon1.startAnimation(Ymt2Activity.ani4);
                    }

                    if("2".equals(v0[1])) {
                        Ymt2Activity.ivCannon2.clearAnimation();
                        Ymt2Activity.ivCannon2.setAlpha(255);
                        Ymt2Activity.ivCannon2.setVisibility(0);
                        Ymt2Activity.ivCannon2.bringToFront();
                        Ymt2Activity.ivCannon2.invalidate();
                        Ymt2Activity.ivCannon2.startAnimation(Ymt2Activity.ani5);
                    }

                    if(!"3".equals(v0[1])) {
                        return;
                    }

                    Ymt2Activity.ivCannon3.clearAnimation();
                    Ymt2Activity.ivCannon3.setAlpha(255);
                    Ymt2Activity.ivCannon3.setVisibility(0);
                    Ymt2Activity.ivCannon3.bringToFront();
                    Ymt2Activity.ivCannon3.invalidate();
                    Ymt2Activity.ivCannon3.startAnimation(Ymt2Activity.ani6);
                }
                catch(Exception v6) {
                }
            }
        };
    }

    public Ymt2Activity() {
        super();
    }

    public static float XCenter(float pixel_x, float _width) {
        return (pixel_x - (((float)(Ymt2Activity.standard_screen_pixel_width / 2)))) / (((float)Ymt2Activity.standard_screen_pixel_width)) - _width / 2f;
    }

    public static float YCenter(float pixel_y, float _height) {
        return (pixel_y - (((float)(Ymt2Activity.standard_screen_pixel_height / 2)))) / (((float)Ymt2Activity.standard_screen_pixel_height)) * (((float)(-Ymt2Activity.screen_pixel_height))) / (((float)Ymt2Activity.screen_pixel_width)) - _height / 2f;
    }

    static Boolean access$0() {
        return Ymt2Activity.bVideoStart;
    }

    static void access$1(Boolean arg0) {
        Ymt2Activity.bVideoStart = arg0;
    }

    static void access$10(boolean arg0) {
        Ymt2Activity.bVideoReallyStart = arg0;
    }

    static void access$11(boolean arg0) {
        Ymt2Activity.bShowVideo = arg0;
    }

    static ImageView access$12() {
        return Ymt2Activity.ivCannon1;
    }

    static ImageView access$13() {
        return Ymt2Activity.ivCannon2;
    }

    static ImageView access$14() {
        return Ymt2Activity.ivCannon3;
    }

    static String access$15() {
        return Ymt2Activity.playingVideoFileName;
    }

    static void access$16(String arg0) {
        Ymt2Activity.videoParam = arg0;
    }

    static void access$17(int arg0) {
        Ymt2Activity.iVideoPosition = arg0;
    }

    static boolean access$18() {
        return Ymt2Activity.bVideoReallyStart;
    }

    static int access$19() {
        return Ymt2Activity.iVideoWidth;
    }

    static String access$2() {
        return Ymt2Activity.videoFileName;
    }

    static int access$20() {
        return Ymt2Activity.iVideoHeight;
    }

    static Animation access$21() {
        return Ymt2Activity.ani1;
    }

    static Animation access$22() {
        return Ymt2Activity.ani2;
    }

    static Animation access$23() {
        return Ymt2Activity.ani3;
    }

    static Animation access$24() {
        return Ymt2Activity.ani4;
    }

    static Animation access$25() {
        return Ymt2Activity.ani5;
    }

    static Animation access$26() {
        return Ymt2Activity.ani6;
    }

    static boolean access$27() {
        return Ymt2Activity.bShowVideo;
    }

    static ImageView access$28() {
        return Ymt2Activity.ivLoading;
    }

    static ImageView access$29() {
        return Ymt2Activity.ivLoadingBack;
    }

    static void access$3(String arg0) {
        Ymt2Activity.videoFileName = arg0;
    }

    static void access$30() {
        Ymt2Activity.hideUI();
    }

    static void access$31() {
        Ymt2Activity.showUI();
    }

    static EditText access$32() {
        return Ymt2Activity.etId;
    }

    static EditText access$33() {
        return Ymt2Activity.etPwd;
    }

    static EditText access$34() {
        return Ymt2Activity.etRegistFormId;
    }

    static EditText access$35() {
        return Ymt2Activity.etRegistFormPwd;
    }

    static EditText access$36() {
        return Ymt2Activity.etRegistFormTel;
    }

    static List access$37() {
        return Ymt2Activity.lsGameMsg;
    }

    static float access$38() {
        return Ymt2Activity.fVideoReallyStartDelay;
    }

    static void access$39(Ymt2Activity arg0) {
        arg0.hideSofttKeyboard();
    }

    static Resources access$4() {
        return Ymt2Activity.res;
    }

    static EditText access$40() {
        return Ymt2Activity.etAgent;
    }

    static void access$5(Uri arg0) {
        Ymt2Activity.uri = arg0;
    }

    static void access$6(String arg0) {
        Ymt2Activity.playingVideoFileName = arg0;
    }

    static MyVideoView access$7() {
        return Ymt2Activity.videoView;
    }

    static Uri access$8() {
        return Ymt2Activity.uri;
    }

    static void access$9(float arg0) {
        Ymt2Activity.fVideoReallyStartDelay = arg0;
    }

    public static int getX(int pixel_width) {
        return Ymt2Activity.screen_pixel_width * pixel_width / Ymt2Activity.standard_screen_pixel_width;
    }

    public static int getY(int pixel_height) {
        return Ymt2Activity.screen_pixel_height * pixel_height / Ymt2Activity.standard_screen_pixel_height;
    }

    private void hideSofttKeyboard() {  // has try-catch handlers
        try {
            Gdx.input.setOnscreenKeyboardVisible(false);
        }
        catch(Exception v0) {
        }
    }

    private static void hideUI() {
        Ymt2Activity.etId.setVisibility(4);
        Ymt2Activity.etPwd.setVisibility(4);
        Ymt2Activity.ibLogin.setVisibility(4);
        Ymt2Activity.ibRegistForm.setVisibility(4);
        Ymt2Activity.etRegistFormId.setVisibility(4);
        Ymt2Activity.etRegistFormPwd.setVisibility(4);
        Ymt2Activity.etRegistFormTel.setVisibility(4);
        Ymt2Activity.etAgent.setVisibility(4);
        Ymt2Activity.ibRegist.setVisibility(4);
        Ymt2Activity.ibLoginForm.setVisibility(4);
    }

    private void iniCannon() {
        Ymt2Activity.ivCannon1 = this.findViewById(2131099650);
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(Ymt2Activity.getX(188), Ymt2Activity.getY(188));
        v0.leftMargin = Ymt2Activity.getX(283) - Ymt2Activity.getX(188) / 2;
        v0.topMargin = Ymt2Activity.getY(31) - Ymt2Activity.getX(188) / 2;
        Ymt2Activity.ivCannon1.setLayoutParams(((ViewGroup$LayoutParams)v0));
        Ymt2Activity.ivCannon1.clearAnimation();
        Ymt2Activity.ivCannon1.setAlpha(0);
        Ymt2Activity.ivCannon1.bringToFront();
        Ymt2Activity.ivCannon1.setVisibility(4);
        Ymt2Activity.ivCannon1.invalidate();
        Ymt2Activity.ivCannon2 = this.findViewById(2131099651);
        FrameLayout$LayoutParams v1 = new FrameLayout$LayoutParams(Ymt2Activity.getX(188), Ymt2Activity.getY(188));
        v1.leftMargin = Ymt2Activity.getX(419) - Ymt2Activity.getX(188) / 2;
        v1.topMargin = Ymt2Activity.getY(31) - Ymt2Activity.getY(188) / 2;
        Ymt2Activity.ivCannon2.setLayoutParams(((ViewGroup$LayoutParams)v1));
        Ymt2Activity.ivCannon2.clearAnimation();
        Ymt2Activity.ivCannon2.setAlpha(0);
        Ymt2Activity.ivCannon2.bringToFront();
        Ymt2Activity.ivCannon2.setVisibility(4);
        Ymt2Activity.ivCannon2.invalidate();
        Ymt2Activity.ivCannon3 = this.findViewById(2131099652);
        FrameLayout$LayoutParams v2 = new FrameLayout$LayoutParams(Ymt2Activity.getX(188), Ymt2Activity.getY(188));
        v2.leftMargin = Ymt2Activity.getX(561) - Ymt2Activity.getX(188) / 2;
        v2.topMargin = Ymt2Activity.getY(31) - Ymt2Activity.getY(188) / 2;
        Ymt2Activity.ivCannon3.setLayoutParams(((ViewGroup$LayoutParams)v2));
        Ymt2Activity.ivCannon3.clearAnimation();
        Ymt2Activity.ivCannon3.setAlpha(0);
        Ymt2Activity.ivCannon3.bringToFront();
        Ymt2Activity.ivCannon3.setVisibility(4);
        Ymt2Activity.ivCannon3.invalidate();
        Ymt2Activity.ani1.setDuration(1200);
        Ymt2Activity.ani1.setRepeatCount(0);
        Ymt2Activity.ani1.setFillEnabled(true);
        Ymt2Activity.ani1.setFillAfter(true);
        Ymt2Activity.ani2.setDuration(1200);
        Ymt2Activity.ani2.setRepeatCount(0);
        Ymt2Activity.ani2.setFillEnabled(true);
        Ymt2Activity.ani2.setFillAfter(true);
        Ymt2Activity.ani3.setDuration(1200);
        Ymt2Activity.ani3.setRepeatCount(0);
        Ymt2Activity.ani3.setFillEnabled(true);
        Ymt2Activity.ani3.setFillAfter(true);
        Ymt2Activity.ani4.setDuration(1200);
        Ymt2Activity.ani4.setRepeatCount(0);
        Ymt2Activity.ani4.setFillEnabled(true);
        Ymt2Activity.ani4.setFillAfter(true);
        Ymt2Activity.ani5.setDuration(1200);
        Ymt2Activity.ani5.setRepeatCount(0);
        Ymt2Activity.ani5.setFillEnabled(true);
        Ymt2Activity.ani5.setFillAfter(true);
        Ymt2Activity.ani6.setDuration(1200);
        Ymt2Activity.ani6.setRepeatCount(0);
        Ymt2Activity.ani6.setFillEnabled(true);
        Ymt2Activity.ani6.setFillAfter(true);
    }

    private void iniLoadingUI() {
        Ymt2Activity.ivLoadingBack = this.findViewById(2131099653);
        FrameLayout$LayoutParams v8 = new FrameLayout$LayoutParams(Ymt2Activity.getX(215), Ymt2Activity.getY(215));
        v8.leftMargin = Ymt2Activity.getX(293);
        v8.topMargin = Ymt2Activity.getY(133);
        Ymt2Activity.ivLoadingBack.setLayoutParams(((ViewGroup$LayoutParams)v8));
        Ymt2Activity.ivLoadingBack.setVisibility(0);
        Ymt2Activity.ivLoadingBack.bringToFront();
        Ymt2Activity.ivLoading = this.findViewById(2131099654);
        FrameLayout$LayoutParams v7 = new FrameLayout$LayoutParams(Ymt2Activity.getX(135), Ymt2Activity.getY(135));
        v7.leftMargin = Ymt2Activity.getX(333);
        v7.topMargin = Ymt2Activity.getY(183);
        Ymt2Activity.ivLoading.setLayoutParams(((ViewGroup$LayoutParams)v7));
        RotateAnimation v0 = new RotateAnimation(0f, 359f, 1, 0.5f, 1, 0.5f);
        ((Animation)v0).setDuration(6000);
        ((Animation)v0).setRepeatCount(-1);
        Ymt2Activity.ivLoading.startAnimation(((Animation)v0));
        Ymt2Activity.ivLoading.setVisibility(0);
        Ymt2Activity.ivLoading.bringToFront();
    }

    private void iniLoginFormUI() {
        Ymt2Activity.etId = this.findViewById(2131099656);
        Ymt2Activity.etId.setText("");
        Ymt2Activity.etId.setTextSize(Ymt2Activity.fFontSize);
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(Ymt2Activity.getX(198), Ymt2Activity.getY(44));
        v0.leftMargin = Ymt2Activity.getX(283);
        v0.topMargin = Ymt2Activity.getY(104);
        Ymt2Activity.etId.setLayoutParams(((ViewGroup$LayoutParams)v0));
        Ymt2Activity.etId.setVisibility(4);
        Ymt2Activity.etId.bringToFront();
        Ymt2Activity.etId.clearFocus();
        Ymt2Activity.etPwd = this.findViewById(2131099658);
        Ymt2Activity.etPwd.setText("");
        Ymt2Activity.etPwd.setTextSize(Ymt2Activity.fFontSize);
        FrameLayout$LayoutParams v1 = new FrameLayout$LayoutParams(Ymt2Activity.getX(198), Ymt2Activity.getY(44));
        v1.leftMargin = Ymt2Activity.getX(283);
        v1.topMargin = Ymt2Activity.getY(159);
        Ymt2Activity.etPwd.setLayoutParams(((ViewGroup$LayoutParams)v1));
        Ymt2Activity.etPwd.setVisibility(4);
        Ymt2Activity.etPwd.bringToFront();
        Ymt2Activity.etPwd.clearFocus();
        Ymt2Activity.ibLogin = this.findViewById(2131099659);
        FrameLayout$LayoutParams v2 = new FrameLayout$LayoutParams(Ymt2Activity.getX(155), Ymt2Activity.getY(45));
        v2.leftMargin = Ymt2Activity.getX(502);
        v2.topMargin = Ymt2Activity.getY(100);
        Ymt2Activity.ibLogin.setLayoutParams(((ViewGroup$LayoutParams)v2));
        Ymt2Activity.ibLogin.setVisibility(4);
        Ymt2Activity.ibLogin.bringToFront();
        Ymt2Activity.ibLogin.setOnClickListener(new View$OnClickListener() {
            static Ymt2Activity access$0(com.android.dev2.Ymt2Activity$2 arg1) {
                return arg1.this$0;
            }

            public void onClick(View v) {
                Ymt2Activity.hideUI();
                Ymt2Activity.this.hideSofttKeyboard();
                Timer.schedule(new Task() {
                    public void run() {
                        this.this$1.this$0.sendMsgToLoginScreen("try_login\t" + Ymt2Activity.etId.getText() + "\t" + Ymt2Activity.etPwd.getText() + "\t" + "z1.0");
                    }
                }, 0.1f);
            }
        });
        Ymt2Activity.ibRegistForm = this.findViewById(2131099660);
        FrameLayout$LayoutParams v3 = new FrameLayout$LayoutParams(Ymt2Activity.getX(155), Ymt2Activity.getY(45));
        v3.leftMargin = Ymt2Activity.getX(502);
        v3.topMargin = Ymt2Activity.getY(155);
        Ymt2Activity.ibRegistForm.setLayoutParams(((ViewGroup$LayoutParams)v3));
        Ymt2Activity.ibRegistForm.setVisibility(4);
        Ymt2Activity.ibRegistForm.bringToFront();
        Ymt2Activity.ibRegistForm.setOnClickListener(new View$OnClickListener() {
            public void onClick(View v) {
                Global.mode = 2;
                Ymt2Activity.showUI();
                Ymt2Activity.this.hideSofttKeyboard();
            }
        });
    }

    private void iniRegistFormUI() {
        Ymt2Activity.etRegistFormId = this.findViewById(2131099662);
        Ymt2Activity.etRegistFormId.setText("");
        Ymt2Activity.etRegistFormId.setTextSize(Ymt2Activity.fFontSize);
        FrameLayout$LayoutParams v7 = new FrameLayout$LayoutParams(Ymt2Activity.getX(178), Ymt2Activity.getY(44));
        v7.leftMargin = Ymt2Activity.getX(293);
        v7.topMargin = Ymt2Activity.getY(63);
        Ymt2Activity.etRegistFormId.setLayoutParams(((ViewGroup$LayoutParams)v7));
        Ymt2Activity.etRegistFormId.setVisibility(4);
        Ymt2Activity.etRegistFormId.bringToFront();
        Ymt2Activity.etRegistFormId.clearFocus();
        Ymt2Activity.etRegistFormPwd = this.findViewById(2131099664);
        Ymt2Activity.etRegistFormPwd.setText("");
        Ymt2Activity.etRegistFormPwd.setTextSize(Ymt2Activity.fFontSize);
        FrameLayout$LayoutParams v8 = new FrameLayout$LayoutParams(Ymt2Activity.getX(178), Ymt2Activity.getY(44));
        v8.leftMargin = Ymt2Activity.getX(293);
        v8.topMargin = Ymt2Activity.getY(111);
        Ymt2Activity.etRegistFormPwd.setLayoutParams(((ViewGroup$LayoutParams)v8));
        Ymt2Activity.etRegistFormPwd.setVisibility(4);
        Ymt2Activity.etRegistFormPwd.bringToFront();
        Ymt2Activity.etRegistFormPwd.clearFocus();
        Ymt2Activity.etRegistFormTel = this.findViewById(2131099666);
        Ymt2Activity.etRegistFormTel.setText("");
        Ymt2Activity.etRegistFormTel.setTextSize(Ymt2Activity.fFontSize);
        Ymt2Activity.etRegistFormTel.setInputType(12290);
        FrameLayout$LayoutParams v9 = new FrameLayout$LayoutParams(Ymt2Activity.getX(178), Ymt2Activity.getY(44));
        v9.leftMargin = Ymt2Activity.getX(293);
        v9.topMargin = Ymt2Activity.getY(159);
        Ymt2Activity.etRegistFormTel.setLayoutParams(((ViewGroup$LayoutParams)v9));
        Ymt2Activity.etRegistFormTel.setVisibility(4);
        Ymt2Activity.etRegistFormTel.bringToFront();
        Ymt2Activity.etRegistFormTel.clearFocus();
        Ymt2Activity.etAgent = this.findViewById(2131099668);
        Ymt2Activity.etAgent.setText("");
        Ymt2Activity.etAgent.setTextSize(Ymt2Activity.fFontSize);
        FrameLayout$LayoutParams v6 = new FrameLayout$LayoutParams(Ymt2Activity.getX(178), Ymt2Activity.getY(44));
        v6.leftMargin = Ymt2Activity.getX(293);
        v6.topMargin = Ymt2Activity.getY(206);
        Ymt2Activity.etAgent.setLayoutParams(((ViewGroup$LayoutParams)v6));
        Ymt2Activity.etAgent.setVisibility(4);
        Ymt2Activity.etAgent.bringToFront();
        Ymt2Activity.etAgent.clearFocus();
        Ymt2Activity.spRegistFormAgent = this.findViewById(2131099669);
        String[] v3 = new String[1];
        v3[0] = "bmw";
        Ymt2Activity.adapter = new SpinnerAdapter(this, 17367048, v3, Ymt2Activity.fFontSize, 2f + Ymt2Activity.fFontSize);
        Ymt2Activity.adapter.setDropDownViewResource(17367049);
        Ymt2Activity.spRegistFormAgent.setAdapter(Ymt2Activity.adapter);
        FrameLayout$LayoutParams v12 = new FrameLayout$LayoutParams(Ymt2Activity.getX(1), Ymt2Activity.getY(1));
        v12.leftMargin = Ymt2Activity.getX(-1000);
        v12.topMargin = Ymt2Activity.getY(-1000);
        Ymt2Activity.spRegistFormAgent.setLayoutParams(((ViewGroup$LayoutParams)v12));
        Ymt2Activity.spRegistFormAgent.setVisibility(4);
        Ymt2Activity.spRegistFormAgent.bringToFront();
        Ymt2Activity.ibRegist = this.findViewById(2131099671);
        FrameLayout$LayoutParams v11 = new FrameLayout$LayoutParams(Ymt2Activity.getX(127), Ymt2Activity.getY(40));
        v11.leftMargin = Ymt2Activity.getX(486);
        v11.topMargin = Ymt2Activity.getY(107);
        Ymt2Activity.ibRegist.setLayoutParams(((ViewGroup$LayoutParams)v11));
        Ymt2Activity.ibRegist.setVisibility(4);
        Ymt2Activity.ibRegist.bringToFront();
        Ymt2Activity.ibRegist.setOnClickListener(new View$OnClickListener() {
            static Ymt2Activity access$0(com.android.dev2.Ymt2Activity$4 arg1) {
                return arg1.this$0;
            }

            public void onClick(View v) {
                Ymt2Activity.hideUI();
                Ymt2Activity.this.hideSofttKeyboard();
                Timer.schedule(new Task() {
                    public void run() {
                        this.this$1.this$0.sendMsgToLoginScreen("try_regist\t" + Ymt2Activity.etRegistFormId.getText() + "\t" + Ymt2Activity.etRegistFormPwd.getText() + "\t" + Ymt2Activity.etRegistFormTel.getText() + "\t" + Ymt2Activity.etAgent.getText() + "\t" + "z1.0");
                    }
                }, 0.1f);
            }
        });
        Ymt2Activity.ibLoginForm = this.findViewById(2131099670);
        FrameLayout$LayoutParams v10 = new FrameLayout$LayoutParams(Ymt2Activity.getX(127), Ymt2Activity.getY(40));
        v10.leftMargin = Ymt2Activity.getX(486);
        v10.topMargin = Ymt2Activity.getY(158);
        Ymt2Activity.ibLoginForm.setLayoutParams(((ViewGroup$LayoutParams)v10));
        Ymt2Activity.ibLoginForm.setVisibility(4);
        Ymt2Activity.ibLoginForm.bringToFront();
        Ymt2Activity.ibLoginForm.setOnClickListener(new View$OnClickListener() {
            public void onClick(View v) {
                Global.mode = 1;
                Ymt2Activity.showUI();
                Ymt2Activity.this.hideSofttKeyboard();
            }
        });
    }

    private void iniVideoViewUI() {
        Ymt2Activity.videoView = this.findViewById(2131099649);
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(Ymt2Activity.getX(400), Ymt2Activity.getY(301));
        v0.leftMargin = Ymt2Activity.getX(228);
        v0.topMargin = Ymt2Activity.getY(98);
        Ymt2Activity.videoView.setLayoutParams(((ViewGroup$LayoutParams)v0));
        Ymt2Activity.videoView.setVisibility(4);
        Ymt2Activity.videoView.setOnCompletionListener(((MediaPlayer$OnCompletionListener)this));
        Ymt2Activity.videoView.setMediaController(null);
        Ymt2Activity.iVideoWidth = Ymt2Activity.getX(400);
        Ymt2Activity.iVideoHeight = Ymt2Activity.getY(300);
        Ymt2Activity.videoView.setSize(Ymt2Activity.iVideoWidth, Ymt2Activity.iVideoHeight);
    }

    public void onCompletion(MediaPlayer mp) {  // has try-catch handlers
        Ymt2Activity.bVideoStart = Boolean.valueOf(false);
        Ymt2Activity.bShowVideo = false;
        Ymt2Activity.bVideoReallyStart = false;
        Ymt2Activity.fVideoReallyStartDelay = 0f;
        Ymt2Activity.playingVideoFileName = "";
        Ymt2Activity.videoFileName = "";
        Ymt2Activity.iVideoPosition = 0;
        Ymt2Activity.videoView.setVisibility(4);
        Object[] v0 = new Object[1];
        v0[0] = Ymt2Activity.videoParam;
        try {
            Ymt2Activity.videoCompletedMethod.invoke(Ymt2Activity.videoRequestObj, v0);
        }
        catch(Exception v1) {
        }

        Ymt2Activity.videoParam = "";
    }

    public void onCreate(Bundle savedInstanceState) {
        int v7 = 1280;
        int v6 = 800;
        float v5 = 14f;
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(1024, 1024);
        this.getWindow().addFlags(128);
        this.getWindow().setSoftInputMode(3);
        this.requestWindowFeature(1);
        this.setContentView(2130903040);
        Ymt2Activity.context = ((Context)this);
        DisplayMetrics v0 = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(v0);
        Ymt2Activity.screen_pixel_width = v0.widthPixels;
        Ymt2Activity.screen_pixel_height = v0.heightPixels;
        if(Ymt2Activity.screen_pixel_width <= v6) {
            Ymt2Activity.fFontSize = 9.5f;
        }
        else {
            if(Ymt2Activity.screen_pixel_width > v6 && Ymt2Activity.screen_pixel_width <= v7) {
                Ymt2Activity.fFontSize = v5;
                goto label_30;
            }

            if(Ymt2Activity.screen_pixel_width > v7 && Ymt2Activity.screen_pixel_width <= 1920) {
                Ymt2Activity.fFontSize = v5;
                goto label_30;
            }

            Ymt2Activity.fFontSize = v5;
        }

    label_30:
        Ymt2Activity.res = this.getResources();
        new Thread(new CheckGameMsg(this)).start();
        this.findViewById(2131099648).addView(this.initializeForView(new Ymt2(((VideoPlayer)this)), false));
        this.iniLoadingUI();
        this.iniVideoViewUI();
        this.iniLoginFormUI();
        this.iniRegistFormUI();
        this.iniCannon();
    }

    public void onPause() {  // has try-catch handlers
        super.onPause();
        try {
            if(Ymt2Activity.videoView == null) {
                return;
            }

            Ymt2Activity.videoView.bringToFront();
            Ymt2Activity.videoView.setZOrderOnTop(true);
            Ymt2Activity.videoView.setZOrderMediaOverlay(true);
            if(!"".equals(Ymt2Activity.playingVideoFileName)) {
                Ymt2Activity.iVideoPosition = Ymt2Activity.videoView.getCurrentPosition();
                Ymt2Activity.videoView.suspend();
            }

            Ymt2Activity.ivCannon1.bringToFront();
            Ymt2Activity.ivCannon2.bringToFront();
            Ymt2Activity.ivCannon3.bringToFront();
        }
        catch(Exception v0) {
        }
    }

    public void onResume() {  // has try-catch handlers
        super.onResume();
        try {
            if(Ymt2Activity.videoView == null) {
                return;
            }

            Ymt2Activity.videoView.bringToFront();
            Ymt2Activity.videoView.setZOrderOnTop(true);
            Ymt2Activity.videoView.setZOrderMediaOverlay(true);
            if(!"".equals(Ymt2Activity.playingVideoFileName)) {
                Ymt2Activity.videoView.seekTo(Ymt2Activity.iVideoPosition);
                Ymt2Activity.videoView.resume();
            }

            Ymt2Activity.ivCannon1.bringToFront();
            Ymt2Activity.ivCannon2.bringToFront();
            Ymt2Activity.ivCannon3.bringToFront();
        }
        catch(Exception v0) {
        }
    }

    public boolean playVideo(Object _videoRequestObj, Method _videoCompletedMethod, String _videoFileName, String _videoParam) {
        boolean v0 = false;
        if(_videoRequestObj != null && _videoCompletedMethod != null && _videoFileName != null && !"".equals(_videoFileName.trim()) && _videoParam != null && !"".equals(_videoParam)) {
            Ymt2Activity.videoRequestObj = _videoRequestObj;
            Ymt2Activity.videoCompletedMethod = _videoCompletedMethod;
            Ymt2Activity.videoFileName = _videoFileName;
            Ymt2Activity.videoParam = _videoParam;
            Ymt2Activity.bVideoStart = Boolean.valueOf(false);
            Ymt2Activity.lsGameMsg.add("start_video");
            v0 = true;
        }

        return v0;
    }

    public void sendMsgToAndroid(String _cmd) {  // has try-catch handlers
        try {
            Ymt2Activity.lsGameMsg.add(_cmd);
        }
        catch(Exception v0) {
        }
    }

    public void sendMsgToLoginScreen(String sMsg) {  // has try-catch handlers
        try {
            if(Ymt2Activity.loginScreenMethod == null) {
                return;
            }

            if(Ymt2Activity.loginScreenObj == null) {
                return;
            }

            Ymt2Activity.loginScreenMethod.invoke(Ymt2Activity.loginScreenObj, sMsg);
        }
        catch(Exception v1) {
        }
    }

    public void setLoginScreenCallback(Object _obj, Method _method) {  // has try-catch handlers
        try {
            Ymt2Activity.loginScreenObj = _obj;
            Ymt2Activity.loginScreenMethod = _method;
        }
        catch(Exception v0) {
        }
    }

    private static void showUI() {
        int v3 = 4;
        if(Global.mode != 2 || Dialog.count > 0) {
            if(Global.mode == 1 && Dialog.count <= 0) {
                Ymt2Activity.etId.setVisibility(0);
                Ymt2Activity.etPwd.setVisibility(0);
                Ymt2Activity.ibLogin.setVisibility(0);
                Ymt2Activity.ibRegistForm.setVisibility(0);
                Ymt2Activity.etRegistFormId.setVisibility(v3);
                Ymt2Activity.etRegistFormPwd.setVisibility(v3);
                Ymt2Activity.etRegistFormTel.setVisibility(v3);
                Ymt2Activity.etAgent.setVisibility(v3);
                Ymt2Activity.ibRegist.setVisibility(v3);
                Ymt2Activity.ibLoginForm.setVisibility(v3);
                goto label_27;
            }

            Ymt2Activity.hideUI();
        }
        else {
            Ymt2Activity.etId.setVisibility(v3);
            Ymt2Activity.etPwd.setVisibility(v3);
            Ymt2Activity.ibLogin.setVisibility(v3);
            Ymt2Activity.ibRegistForm.setVisibility(v3);
            Ymt2Activity.etRegistFormId.setVisibility(0);
            Ymt2Activity.etRegistFormPwd.setVisibility(0);
            Ymt2Activity.etRegistFormTel.setVisibility(0);
            Ymt2Activity.etAgent.setVisibility(0);
            Ymt2Activity.ibRegist.setVisibility(0);
            Ymt2Activity.ibLoginForm.setVisibility(0);
        }

    label_27:
        Ymt2Activity.adapter.notifyDataSetChanged();
    }
}

