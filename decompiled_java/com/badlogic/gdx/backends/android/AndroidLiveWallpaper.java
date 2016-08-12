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

import android.opengl.GLSurfaceView;
import android.os.Build$VERSION;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

public class AndroidLiveWallpaper implements Application {
    protected AndroidAudio audio;
    AndroidClipboard clipboard;
    protected final Array executedRunnables;
    protected AndroidFiles files;
    protected boolean firstResume;
    protected AndroidGraphicsLiveWallpaper graphics;
    protected AndroidInput input;
    protected final Array lifecycleListeners;
    protected ApplicationListener listener;
    protected int logLevel;
    protected AndroidNet net;
    protected final Array runnables;

    static  {
        GdxNativesLoader.load();
    }

    public AndroidLiveWallpaper(AndroidLiveWallpaperService service) {
        super();
        this.firstResume = true;
        this.runnables = new Array();
        this.executedRunnables = new Array();
        this.lifecycleListeners = new Array();
        this.logLevel = 2;
        this.service = service;
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
    }

    public ApplicationListener getApplicationListener() {
        return this.listener;
    }

    public Audio getAudio() {
        return this.audio;
    }

    public Clipboard getClipboard() {
        if(this.clipboard == null) {
            this.clipboard = new AndroidClipboard(this.service);
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

    public ApplicationListener getListener() {
        return this.listener;
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
        return new AndroidPreferences(this.service.getSharedPreferences(name, 0));
    }

    public AndroidLiveWallpaperService getService() {
        return this.service;
    }

    public ApplicationType getType() {
        return ApplicationType.Android;
    }

    public int getVersion() {
        return Integer.parseInt(Build$VERSION.SDK);
    }

    public WindowManager getWindowManager() {
        return this.service.getWindowManager();
    }

    public void initialize(ApplicationListener listener, AndroidApplicationConfiguration config) {
        ResolutionStrategy v0_1;
        if(config.resolutionStrategy == null) {
            FillResolutionStrategy v0 = new FillResolutionStrategy();
        }
        else {
            v0_1 = config.resolutionStrategy;
        }

        this.graphics = new AndroidGraphicsLiveWallpaper(this, config, v0_1);
        this.input = AndroidInputFactory.newAndroidInput(((Application)this), this.getService(), this.graphics.view, config);
        this.audio = new AndroidAudio(this.getService(), config);
        this.files = new AndroidFiles(this.getService().getAssets(), this.getService().getFilesDir().getAbsolutePath());
        this.listener = listener;
        Gdx.app = ((Application)this);
        Gdx.input = this.input;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
    }

    public void log(String tag, String message) {
        if(this.logLevel >= 2) {
            Log.i(tag, message);
        }
    }

    public void log(String tag, String message, Throwable exception) {
        if(this.logLevel >= 2) {
            Log.i(tag, message, exception);
        }
    }

    public void onDestroy() {  // has try-catch handlers
        int v2;
        if(this.graphics == null) {
            goto label_32;
        }

        if(this.graphics.view == null) {
            goto label_32;
        }

        if(!(this.graphics.view instanceof GLSurfaceView)) {
            goto label_32;
        }

        View v1 = this.graphics.view;
        Method v5 = null;
        try {
            Method[] v0 = v1.getClass().getMethods();
            int v3 = v0.length;
            v2 = 0;
            while(true) {
            label_16:
                if(v2 < v3) {
                    Method v4 = v0[v2];
                    if(v4.getName().equals("onDestroy")) {
                        v5 = v4;
                    }
                    else {
                        break;
                    }
                }

                goto label_23;
            }
        }
        catch(Throwable v6) {
            goto label_45;
        }

        ++v2;
        goto label_16;
        try {
        label_23:
            if(v5 == null) {
                goto label_39;
            }

            v5.invoke(v1, new Object[0]);
            if(!AndroidLiveWallpaperService.DEBUG) {
                goto label_32;
            }

            Log.d("WallpaperService", " > AndroidLiveWallpaper - onDestroy() stopped GLThread managed by GLSurfaceView");
            goto label_32;
        label_39:
            throw new Exception("method not found!");
        }
        catch(Throwable v6) {
        label_45:
            Log.e("WallpaperService", "failed to destroy GLSurfaceView\'s thread! GLSurfaceView.onDetachedFromWindow impl changed since API lvl 16!");
            v6.printStackTrace();
        }

    label_32:
        if(this.audio != null) {
            this.audio.dispose();
        }
    }

    public void onPause() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause()");
        }

        this.audio.pause();
        this.input.unregisterSensorListeners();
        Arrays.fill(this.input.realId, -1);
        Arrays.fill(this.input.touched, false);
        if(this.graphics != null && this.graphics.view != null) {
            if((this.graphics.view instanceof GLSurfaceViewCupcake)) {
                this.graphics.view.onPause();
            }
            else if((this.graphics.view instanceof GLSurfaceViewAPI18)) {
                this.graphics.view.onPause();
            }
            else if((this.graphics.view instanceof GLSurfaceView)) {
                this.graphics.view.onPause();
            }
            else {
                throw new RuntimeException("unimplemented");
            }
        }

        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause() done!");
        }
    }

    public void onResume() {
        Gdx.app = ((Application)this);
        Gdx.input = this.input;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
        this.input.registerSensorListeners();
        if(this.graphics != null && this.graphics.view != null) {
            if((this.graphics.view instanceof GLSurfaceViewCupcake)) {
                this.graphics.view.onResume();
            }
            else if((this.graphics.view instanceof GLSurfaceViewAPI18)) {
                this.graphics.view.onResume();
            }
            else if((this.graphics.view instanceof GLSurfaceView)) {
                this.graphics.view.onResume();
            }
            else {
                throw new RuntimeException("unimplemented");
            }
        }

        if(!this.firstResume) {
            this.audio.resume();
            this.graphics.resume();
        }
        else {
            this.firstResume = false;
        }
    }

    public void postRunnable(Runnable runnable) {  // has try-catch handlers
        try {
            this.runnables.add(runnable);
            return;
        label_5:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_5;
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
}

