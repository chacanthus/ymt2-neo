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

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Build$VERSION;
import android.os.Debug;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application$ApplicationType;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceViewAPI18;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceViewCupcake;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxNativesLoader;
import java.util.Arrays;

public class AndroidApplication extends Activity implements Application {
    protected AndroidAudio audio;
    AndroidClipboard clipboard;
    protected final Array executedRunnables;
    protected AndroidFiles files;
    protected boolean firstResume;
    protected AndroidGraphics graphics;
    public Handler handler;
    protected boolean hideStatusBar;
    protected AndroidInput input;
    protected final Array lifecycleListeners;
    protected ApplicationListener listener;
    protected int logLevel;
    protected AndroidNet net;
    protected final Array runnables;
    protected boolean useImmersiveMode;

    static  {
        GdxNativesLoader.load();
    }

    public AndroidApplication() {
        super();
        this.firstResume = true;
        this.runnables = new Array();
        this.executedRunnables = new Array();
        this.lifecycleListeners = new Array();
        this.logLevel = 2;
        this.useImmersiveMode = false;
        this.hideStatusBar = false;
    }

    public void addLifecycleListener(LifecycleListener listener) {  // has try-catch handlers
        try {
            this.lifecycleListeners.add(listener);
            return;
        label_5:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_5;
        }
    }

    protected FrameLayout$LayoutParams createLayoutParams() {
        FrameLayout$LayoutParams v0 = new FrameLayout$LayoutParams(-1, -1);
        v0.gravity = 17;
        return v0;
    }

    protected void createWakeLock(boolean use) {
        if(use) {
            this.getWindow().addFlags(128);
        }
    }

    public void debug(String tag, String message) {
        if(this.logLevel >= 3) {
            Log.d(tag, message);
        }
    }

    public void debug(String tag, String message, Throwable exception) {
        if(this.logLevel >= 3) {
            Log.d(tag, message, exception);
        }
    }

    public void error(String tag, String message) {
        if(this.logLevel >= 1) {
            Log.e(tag, message);
        }
    }

    public void error(String tag, String message, Throwable exception) {
        if(this.logLevel >= 1) {
            Log.e(tag, message, exception);
        }
    }

    public void exit() {
        this.handler.post(new Runnable() {
            public void run() {
                AndroidApplication.this.finish();
            }
        });
    }

    public ApplicationListener getApplicationListener() {
        return this.listener;
    }

    public Audio getAudio() {
        return this.audio;
    }

    public Clipboard getClipboard() {
        if(this.clipboard == null) {
            this.clipboard = new AndroidClipboard(((Context)this));
        }

        return this.clipboard;
    }

    public Files getFiles() {
        return this.files;
    }

    public Graphics getGraphics() {
        return this.graphics;
    }

    public Input getInput() {
        return this.input;
    }

    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public long getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize();
    }

    public Net getNet() {
        return this.net;
    }

    public Preferences getPreferences(String name) {
        return new AndroidPreferences(this.getSharedPreferences(name, 0));
    }

    public ApplicationType getType() {
        return ApplicationType.Android;
    }

    public int getVersion() {
        return Integer.parseInt(Build$VERSION.SDK);
    }

    protected void hideStatusBar(boolean hide) {  // has try-catch handlers
        if(!hide) {
            return;
        }

        if(this.getVersion() >= 11) {
            goto label_5;
        }

        return;
    label_5:
        View v2 = this.getWindow().getDecorView();
        try {
            Class v3 = View.class;
            Method v1 = v3.getMethod("setSystemUiVisibility", Integer.TYPE);
            v1.invoke(v2, Integer.valueOf(0));
            v3_1 = new Object[1];
            v3_1[0] = Integer.valueOf(1);
            v1.invoke(v2, v3_1);
        }
        catch(Exception v0) {
            this.log("AndroidApplication", "Can\'t hide status bar", ((Throwable)v0));
        }
    }

    public void initialize(ApplicationListener listener, AndroidApplicationConfiguration config) {  // has try-catch handlers
        ResolutionStrategy v5_1;
        int v8 = 1024;
        if(config.resolutionStrategy == null) {
            FillResolutionStrategy v5 = new FillResolutionStrategy();
        }
        else {
            v5_1 = config.resolutionStrategy;
        }

        this.graphics = new AndroidGraphics(this, config, v5_1);
        this.input = AndroidInputFactory.newAndroidInput(((Application)this), ((Context)this), this.graphics.view, config);
        this.audio = new AndroidAudio(((Context)this), config);
        this.files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        this.net = new AndroidNet(this);
        this.listener = listener;
        this.handler = new Handler();
        this.useImmersiveMode = config.useImmersiveMode;
        this.hideStatusBar = config.hideStatusBar;
        Gdx.app = ((Application)this);
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        try {
            this.requestWindowFeature(1);
        }
        catch(Exception v1) {
            this.log("AndroidApplication", "Content already displayed, cannot request FEATURE_NO_TITLE", ((Throwable)v1));
        }

        this.getWindow().setFlags(v8, v8);
        this.getWindow().clearFlags(2048);
        this.setContentView(this.graphics.getView(), this.createLayoutParams());
        this.createWakeLock(config.useWakelock);
        this.hideStatusBar(this.hideStatusBar);
        this.useImmersiveMode(this.useImmersiveMode);
        if(!this.useImmersiveMode) {
            return;
        }

        if(this.getVersion() < 19) {
            return;
        }

        try {
            Class v4 = Class.forName("com.badlogic.gdx.backends.android.AndroidVisibilityListener");
            Object v3 = v4.newInstance();
            Method v2 = v4.getDeclaredMethod("createListener", AndroidApplication.class);
            v2.invoke(v3, this);
        }
        catch(Exception v0) {
            this.log("AndroidApplication", "Failed to create AndroidVisibilityListener");
        }
    }

    public void initialize(ApplicationListener listener, boolean useGL2IfAvailable) {
        AndroidApplicationConfiguration v0 = new AndroidApplicationConfiguration();
        v0.useGL20 = useGL2IfAvailable;
        this.initialize(listener, v0);
    }

    public View initializeForView(ApplicationListener listener, AndroidApplicationConfiguration config) {  // has try-catch handlers
        FillResolutionStrategy v4;
        if(config.resolutionStrategy == null) {
            v4 = new FillResolutionStrategy();
        }
        else {
            ResolutionStrategy v4_1 = config.resolutionStrategy;
        }

        this.graphics = new AndroidGraphics(this, config, ((ResolutionStrategy)v4));
        this.input = AndroidInputFactory.newAndroidInput(((Application)this), ((Context)this), this.graphics.view, config);
        this.audio = new AndroidAudio(((Context)this), config);
        this.files = new AndroidFiles(this.getAssets(), this.getFilesDir().getAbsolutePath());
        this.net = new AndroidNet(this);
        this.listener = listener;
        this.handler = new Handler();
        this.useImmersiveMode = config.useImmersiveMode;
        this.hideStatusBar = config.hideStatusBar;
        Gdx.app = ((Application)this);
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        this.createWakeLock(config.useWakelock);
        this.hideStatusBar(this.hideStatusBar);
        this.useImmersiveMode(this.useImmersiveMode);
        if(!this.useImmersiveMode) {
            goto label_62;
        }

        if(this.getVersion() < 19) {
            goto label_62;
        }

        try {
            Class v3 = Class.forName("com.badlogic.gdx.backends.android.AndroidVisibilityListener");
            Object v2 = v3.newInstance();
            Method v1 = v3.getDeclaredMethod("createListener", AndroidApplication.class);
            v1.invoke(v2, this);
        }
        catch(Exception v0) {
            this.log("AndroidApplication", "Failed to create AndroidVisibilityListener");
        }

    label_62:
        return this.graphics.getView();
    }

    public View initializeForView(ApplicationListener listener, boolean useGL2IfAvailable) {
        AndroidApplicationConfiguration v0 = new AndroidApplicationConfiguration();
        v0.useGL20 = useGL2IfAvailable;
        return this.initializeForView(listener, v0);
    }

    public void log(String tag, String message, Throwable exception) {
        if(this.logLevel >= 2) {
            Log.i(tag, message, exception);
        }
    }

    public void log(String tag, String message) {
        if(this.logLevel >= 2) {
            Log.i(tag, message);
        }
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        boolean v0 = false;
        if(config.hardKeyboardHidden == 1) {
            v0 = true;
        }

        this.input.keyboardAvailable = v0;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onPause() {
        boolean v0 = this.graphics.isContinuousRendering();
        this.graphics.setContinuousRendering(true);
        this.graphics.pause();
        this.input.unregisterSensorListeners();
        Arrays.fill(this.input.realId, -1);
        Arrays.fill(this.input.touched, false);
        if(this.isFinishing()) {
            this.graphics.clearManagedCaches();
            this.graphics.destroy();
        }

        this.graphics.setContinuousRendering(v0);
        if(this.graphics != null && this.graphics.view != null) {
            if((this.graphics.view instanceof GLSurfaceViewCupcake)) {
                this.graphics.view.onPause();
            }

            if((this.graphics.view instanceof GLSurfaceViewAPI18)) {
                this.graphics.view.onPause();
            }

            if(!(this.graphics.view instanceof GLSurfaceView)) {
                goto label_51;
            }

            this.graphics.view.onPause();
        }

    label_51:
        super.onPause();
    }

    protected void onResume() {
        Gdx.app = ((Application)this);
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        this.getInput().registerSensorListeners();
        if(this.graphics != null && this.graphics.view != null) {
            if((this.graphics.view instanceof GLSurfaceViewCupcake)) {
                this.graphics.view.onResume();
            }

            if((this.graphics.view instanceof GLSurfaceViewAPI18)) {
                this.graphics.view.onResume();
            }

            if(!(this.graphics.view instanceof GLSurfaceView)) {
                goto label_39;
            }

            this.graphics.view.onResume();
        }

    label_39:
        if(!this.firstResume) {
            this.graphics.resume();
        }
        else {
            this.firstResume = false;
        }

        super.onResume();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.useImmersiveMode(this.useImmersiveMode);
        this.hideStatusBar(this.hideStatusBar);
    }

    public void postRunnable(Runnable runnable) {  // has try-catch handlers
        try {
            this.runnables.add(runnable);
            Gdx.graphics.requestRendering();
            return;
        label_7:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_7;
        }
    }

    public void removeLifecycleListener(LifecycleListener listener) {  // has try-catch handlers
        try {
            this.lifecycleListeners.removeValue(listener, true);
            return;
        label_6:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_6;
        }
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    protected void useImmersiveMode(boolean use) {  // has try-catch handlers
        if(!use) {
            return;
        }

        if(this.getVersion() >= 19) {
            goto label_5;
        }

        return;
    label_5:
        View v3 = this.getWindow().getDecorView();
        try {
            Class v4 = View.class;
            Method v2 = v4.getMethod("setSystemUiVisibility", Integer.TYPE);
            v2.invoke(v3, Integer.valueOf(5894));
        }
        catch(Exception v1) {
            this.log("AndroidApplication", "Can\'t set immersive mode", ((Throwable)v1));
        }
    }
}

