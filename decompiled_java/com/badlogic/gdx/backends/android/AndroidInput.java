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
import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnKeyListener;
import android.view.View$OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics$DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input$Orientation;
import com.badlogic.gdx.Input$Peripheral;
import com.badlogic.gdx.Input$TextInputListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import java.util.ArrayList;

public class AndroidInput implements View$OnKeyListener, View$OnTouchListener, Input {
    class KeyEvent {
        static final int KEY_DOWN = 0;
        static final int KEY_TYPED = 2;
        static final int KEY_UP = 1;

        KeyEvent() {
            super();
        }
    }

    class SensorListener implements SensorEventListener {
        final float[] accelerometerValues;
        final float[] magneticFieldValues;
        final Orientation nativeOrientation;

        SensorListener(AndroidInput arg1, Orientation nativeOrientation, float[] accelerometerValues, float[] magneticFieldValues) {
            AndroidInput.this = arg1;
            super();
            this.accelerometerValues = accelerometerValues;
            this.magneticFieldValues = magneticFieldValues;
            this.nativeOrientation = nativeOrientation;
        }

        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        public void onSensorChanged(SensorEvent event) {
            int v4 = 2;
            if(event.sensor.getType() == 1) {
                if(this.nativeOrientation == Orientation.Portrait) {
                    System.arraycopy(event.values, 0, this.accelerometerValues, 0, this.accelerometerValues.length);
                }
                else {
                    this.accelerometerValues[0] = event.values[1];
                    this.accelerometerValues[1] = -event.values[0];
                    this.accelerometerValues[v4] = event.values[v4];
                }
            }

            if(event.sensor.getType() == v4) {
                System.arraycopy(event.values, 0, this.magneticFieldValues, 0, this.magneticFieldValues.length);
            }
        }
    }

    class TouchEvent {
        static final int TOUCH_DOWN = 0;
        static final int TOUCH_DRAGGED = 2;
        static final int TOUCH_UP = 1;

        TouchEvent() {
            super();
        }
    }

    public static final int NUM_TOUCHES = 40;
    final float[] R;
    public boolean accelerometerAvailable;
    private SensorEventListener accelerometerListener;
    private final float[] accelerometerValues;
    final Application app;
    private float azimuth;
    private boolean catchBack;
    private boolean catchMenu;
    private boolean compassAvailable;
    private SensorEventListener compassListener;
    private final AndroidApplicationConfiguration config;
    final Context context;
    private long currentEventTimeStamp;
    int[] deltaX;
    int[] deltaY;
    private Handler handle;
    final boolean hasMultitouch;
    private float inclination;
    private boolean justTouched;
    ArrayList keyEvents;
    ArrayList keyListeners;
    private IntMap keys;
    private final float[] magneticFieldValues;
    private SensorManager manager;
    private final Orientation nativeOrientation;
    private final AndroidOnscreenKeyboard onscreenKeyboard;
    final float[] orientation;
    private float pitch;
    private InputProcessor processor;
    int[] realId;
    boolean requestFocus;
    private float roll;
    private int sleepTime;
    private String text;
    private TextInputListener textListener;
    ArrayList touchEvents;
    private final AndroidTouchHandler touchHandler;
    int[] touchX;
    int[] touchY;
    boolean[] touched;
    Pool usedKeyEvents;
    Pool usedTouchEvents;
    protected final Vibrator vibrator;

    public AndroidInput(Application activity, Context context, Object view, AndroidApplicationConfiguration config) {
        super();
        this.usedKeyEvents = new Pool() {
            protected KeyEvent newObject() {
                return new KeyEvent();
            }

            protected Object newObject() {
                return this.newObject();
            }
        };
        this.usedTouchEvents = new Pool() {
            protected TouchEvent newObject() {
                return new TouchEvent();
            }

            protected Object newObject() {
                return this.newObject();
            }
        };
        this.keyListeners = new ArrayList();
        this.keyEvents = new ArrayList();
        this.touchEvents = new ArrayList();
        this.touchX = new int[40];
        this.touchY = new int[40];
        this.deltaX = new int[40];
        this.deltaY = new int[40];
        this.touched = new boolean[40];
        this.realId = new int[40];
        this.keys = new IntMap();
        this.accelerometerAvailable = false;
        this.accelerometerValues = new float[3];
        this.text = null;
        this.textListener = null;
        this.sleepTime = 0;
        this.catchBack = false;
        this.catchMenu = false;
        this.compassAvailable = false;
        this.magneticFieldValues = new float[3];
        this.azimuth = 0f;
        this.pitch = 0f;
        this.roll = 0f;
        this.inclination = 0f;
        this.justTouched = false;
        this.currentEventTimeStamp = System.nanoTime();
        this.requestFocus = true;
        this.R = new float[9];
        this.orientation = new float[3];
        if((view instanceof View)) {
            view.setOnKeyListener(((View$OnKeyListener)this));
            view.setOnTouchListener(((View$OnTouchListener)this));
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.requestFocusFromTouch();
        }

        this.config = config;
        this.onscreenKeyboard = new AndroidOnscreenKeyboard(context, new Handler(), this);
        int v0;
        for(v0 = 0; v0 < this.realId.length; ++v0) {
            this.realId[v0] = -1;
        }

        this.handle = new Handler();
        this.app = activity;
        this.context = context;
        this.sleepTime = config.touchSleepTime;
        if(Integer.parseInt(Build$VERSION.SDK) >= 5) {
            this.touchHandler = new AndroidMultiTouchHandler();
        }
        else {
            this.touchHandler = new AndroidSingleTouchHandler();
        }

        this.hasMultitouch = this.touchHandler.supportsMultitouch(context);
        this.vibrator = context.getSystemService("vibrator");
        int v2 = this.getRotation();
        DisplayMode v1 = this.app.getGraphics().getDesktopDisplayMode();
        if(v2 != 0 && v2 != 180) {
            goto label_127;
        }
        else if(v1.width < v1.height) {
        label_127:
            if((v2 == 90 || v2 == 270) && v1.width <= v1.height) {
                goto label_134;
            }

            this.nativeOrientation = Orientation.Portrait;
        }
        else {
        label_134:
            this.nativeOrientation = Orientation.Landscape;
        }
    }

    public void addKeyListener(View$OnKeyListener listener) {
        this.keyListeners.add(listener);
    }

    public void cancelVibrate() {
        this.vibrator.cancel();
    }

    public float getAccelerometerX() {
        return this.accelerometerValues[0];
    }

    public float getAccelerometerY() {
        return this.accelerometerValues[1];
    }

    public float getAccelerometerZ() {
        return this.accelerometerValues[2];
    }

    public float getAzimuth() {
        float v0;
        if(!this.compassAvailable) {
            v0 = 0f;
        }
        else {
            this.updateOrientation();
            v0 = this.azimuth;
        }

        return v0;
    }

    public long getCurrentEventTime() {
        return this.currentEventTimeStamp;
    }

    public int getDeltaX() {
        return this.deltaX[0];
    }

    public int getDeltaX(int pointer) {
        return this.deltaX[pointer];
    }

    public int getDeltaY() {
        return this.deltaY[0];
    }

    public int getDeltaY(int pointer) {
        return this.deltaY[pointer];
    }

    public int getFreePointerIndex() {
        int v1 = this.realId.length;
        int v0 = 0;
        while(true) {
            if(v0 >= v1) {
                break;
            }
            else if(this.realId[v0] != -1) {
                ++v0;
                continue;
            }

            goto label_9;
        }

        int[] v2 = new int[this.realId.length + 1];
        System.arraycopy(this.realId, 0, v2, 0, this.realId.length);
        this.realId = v2;
        v0 = v2.length - 1;
    label_9:
        return v0;
    }

    public InputProcessor getInputProcessor() {
        return this.processor;
    }

    public Orientation getNativeOrientation() {
        return this.nativeOrientation;
    }

    public float getPitch() {
        float v0;
        if(!this.compassAvailable) {
            v0 = 0f;
        }
        else {
            this.updateOrientation();
            v0 = this.pitch;
        }

        return v0;
    }

    public void getPlaceholderTextInput(TextInputListener listener, String title, String placeholder) {
        this.handle.post(new Runnable() {
            public void run() {
                AlertDialog$Builder v0 = new AlertDialog$Builder(AndroidInput.this.context);
                v0.setTitle(this.val$title);
                EditText v1 = new EditText(AndroidInput.this.context);
                v1.setHint(this.val$placeholder);
                v1.setSingleLine();
                v0.setView(((View)v1));
                v0.setPositiveButton("Ok", new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.val$listener.input(this.this$2.val$input.getText().toString());
                            }
                        });
                    }
                });
                v0.setOnCancelListener(new DialogInterface$OnCancelListener() {
                    public void onCancel(DialogInterface arg0) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.val$listener.canceled();
                            }
                        });
                    }
                });
                v0.show();
            }
        });
    }

    public float getRoll() {
        float v0;
        if(!this.compassAvailable) {
            v0 = 0f;
        }
        else {
            this.updateOrientation();
            v0 = this.roll;
        }

        return v0;
    }

    public int getRotation() {
        int v1;
        int v0;
        if((this.context instanceof Activity)) {
            v0 = this.context.getWindowManager().getDefaultDisplay().getOrientation();
        }
        else {
            v0 = this.context.getSystemService("window").getDefaultDisplay().getOrientation();
        }

        switch(v0) {
            case 0: {
                v1 = 0;
                break;
            }
            case 1: {
                v1 = 90;
                break;
            }
            case 2: {
                v1 = 180;
                break;
            }
            case 3: {
                v1 = 270;
                break;
            }
            default: {
                v1 = 0;
                break;
            }
        }

        return v1;
    }

    public void getRotationMatrix(float[] matrix) {
        SensorManager.getRotationMatrix(matrix, null, this.accelerometerValues, this.magneticFieldValues);
    }

    public void getTextInput(TextInputListener listener, String title, String text) {
        this.handle.post(new Runnable() {
            public void run() {
                AlertDialog$Builder v0 = new AlertDialog$Builder(AndroidInput.this.context);
                v0.setTitle(this.val$title);
                EditText v1 = new EditText(AndroidInput.this.context);
                v1.setText(this.val$text);
                v1.setSingleLine();
                v0.setView(((View)v1));
                v0.setPositiveButton("Ok", new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.val$listener.input(this.this$2.val$input.getText().toString());
                            }
                        });
                    }
                });
                v0.setNegativeButton("Cancel", new DialogInterface$OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.val$listener.canceled();
                            }
                        });
                    }
                });
                v0.setOnCancelListener(new DialogInterface$OnCancelListener() {
                    public void onCancel(DialogInterface arg0) {
                        Gdx.app.postRunnable(new Runnable() {
                            public void run() {
                                this.this$2.this$1.val$listener.canceled();
                            }
                        });
                    }
                });
                v0.show();
            }
        });
    }

    public int getX() {  // has try-catch handlers
        try {
            return this.touchX[0];
        label_5:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_5;
        }
    }

    public int getX(int pointer) {  // has try-catch handlers
        try {
            return this.touchX[pointer];
        label_4:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_4;
        }
    }

    public int getY() {  // has try-catch handlers
        try {
            return this.touchY[0];
        label_5:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_5;
        }
    }

    public int getY(int pointer) {  // has try-catch handlers
        try {
            return this.touchY[pointer];
        label_4:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_4;
        }
    }

    public boolean isButtonPressed(int button) {
        boolean v0;
        if(button == 0) {
            v0 = this.isTouched();
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean isCursorCatched() {
        return 0;
    }

    public boolean isKeyPressed(int key) {  // has try-catch handlers
        boolean v0_1;
        if(key == -1) {
            try {
                if(this.keys.size > 0) {
                    v0_1 = true;
                }
                else {
                    v0_1 = false;
                    goto label_6;
                label_9:
                    v0_1 = this.keys.containsKey(key);
                }

            label_6:
                return v0_1;
            label_13:
                throw v0;
            }
            catch(Throwable v0) {
                goto label_13;
            }
        }
        else {
            goto label_9;
        }

        goto label_6;
    }

    public boolean isPeripheralAvailable(Peripheral peripheral) {
        boolean v0 = true;
        if(peripheral == Peripheral.Accelerometer) {
            v0 = this.accelerometerAvailable;
        }
        else if(peripheral == Peripheral.Compass) {
            v0 = this.compassAvailable;
        }
        else if(peripheral == Peripheral.HardwareKeyboard) {
            v0 = this.keyboardAvailable;
        }
        else if(peripheral != Peripheral.OnscreenKeyboard) {
            if(peripheral == Peripheral.Vibrator) {
                if(this.vibrator == null) {
                    v0 = false;
                }
            }
            else if(peripheral == Peripheral.MultitouchScreen) {
                v0 = this.hasMultitouch;
            }
            else {
                v0 = false;
            }
        }

        return v0;
    }

    public boolean isTouched() {  // has try-catch handlers
        try {
            return this.touched[0];
        label_5:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_5;
        }
    }

    public boolean isTouched(int pointer) {  // has try-catch handlers
        try {
            return this.touched[pointer];
        label_4:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_4;
        }
    }

    public boolean justTouched() {
        return this.justTouched;
    }

    public int lookUpPointerIndex(int pointerId) {
        int v2 = this.realId.length;
        int v1 = 0;
        while(true) {
            if(v1 >= v2) {
                break;
            }
            else if(this.realId[v1] != pointerId) {
                ++v1;
                continue;
            }

            goto label_7;
        }

        StringBuffer v0 = new StringBuffer();
        for(v1 = 0; v1 < v2; ++v1) {
            v0.append(v1 + ":" + this.realId[v1] + " ");
        }

        Gdx.app.log("AndroidInput", "Pointer ID lookup failed: " + pointerId + ", " + v0.toString());
        v1 = -1;
    label_7:
        return v1;
    }

    public void onDrop(int x, int y) {
        this.postTap(x, y);
    }

    public boolean onKey(View v, int keyCode, android.view.KeyEvent e) {  // has try-catch handlers
        Object v3;
        String v2;
        boolean v6;
        int v12 = 255;
        int v11 = 4;
        int v9 = 2;
        int v4 = 0;
        int v5 = this.keyListeners.size();
        while(true) {
            if(v4 >= v5) {
                goto label_19;
            }
            else if(this.keyListeners.get(v4).onKey(v, keyCode, e)) {
                v6 = true;
            }
            else {
                ++v4;
                continue;
            }

            goto label_15;
        }

        try {
        label_19:
            if(e.getKeyCode() == 0 && e.getAction() == v9) {
                v2 = e.getCharacters();
                v4 = 0;
                goto label_25;
            }

            char v1 = ((char)e.getUnicodeChar());
            if(keyCode == 67) {
                v1 = '\b';
            }

            switch(e.getAction()) {
                case 0: {
                    v3 = this.usedKeyEvents.obtain();
                    ((KeyEvent)v3).keyChar = '\u0000';
                    ((KeyEvent)v3).keyCode = e.getKeyCode();
                    ((KeyEvent)v3).type = 0;
                    if(keyCode == v11 && (e.isAltPressed())) {
                        keyCode = 255;
                        ((KeyEvent)v3).keyCode = keyCode;
                    }

                    this.keyEvents.add(v3);
                    this.keys.put(((KeyEvent)v3).keyCode, null);
                    break;
                }
                case 1: {
                    v3 = this.usedKeyEvents.obtain();
                    ((KeyEvent)v3).keyChar = '\u0000';
                    ((KeyEvent)v3).keyCode = e.getKeyCode();
                    ((KeyEvent)v3).type = 1;
                    if(keyCode == v11 && (e.isAltPressed())) {
                        keyCode = 255;
                        ((KeyEvent)v3).keyCode = keyCode;
                    }

                    this.keyEvents.add(v3);
                    v3 = this.usedKeyEvents.obtain();
                    ((KeyEvent)v3).keyChar = v1;
                    ((KeyEvent)v3).keyCode = 0;
                    ((KeyEvent)v3).type = 2;
                    this.keyEvents.add(v3);
                    if(keyCode != v12) {
                        goto label_113;
                    }

                    this.keys.remove(255);
                    goto label_50;
                label_113:
                    this.keys.remove(e.getKeyCode());
                    break;
                }
            }

        label_50:
            this.app.getGraphics().requestRendering();
            if(keyCode != v12) {
                goto label_117;
            }

            v6 = true;
            goto label_15;
        }
        catch(Throwable v6_1) {
            goto label_79;
        }

    label_117:
        if((this.catchBack) && keyCode == v11) {
            v6 = true;
            goto label_15;
        }

        if((this.catchMenu) && keyCode == 82) {
            v6 = true;
            goto label_15;
        }

        v6 = false;
        goto label_15;
        try {
            while(true) {
            label_25:
                if(v4 >= v2.length()) {
                    break;
                }

                v3 = this.usedKeyEvents.obtain();
                ((KeyEvent)v3).keyCode = 0;
                ((KeyEvent)v3).keyChar = v2.charAt(v4);
                ((KeyEvent)v3).type = 2;
                this.keyEvents.add(v3);
                ++v4;
            }

            v6 = false;
            goto label_15;
        label_79:
            throw v6_1;
        }
        catch(Throwable v6_1) {
            goto label_79;
        }

    label_15:
        return v6;
    }

    public void onTap(int x, int y) {
        this.postTap(x, y);
    }

    public boolean onTouch(View view, MotionEvent event) {  // has try-catch handlers
        if((this.requestFocus) && view != null) {
            view.requestFocus();
            view.requestFocusFromTouch();
            this.requestFocus = false;
        }

        this.touchHandler.onTouch(event, this);
        if(this.sleepTime == 0) {
            return 1;
        }

        try {
            Thread.sleep(((long)this.sleepTime));
        }
        catch(InterruptedException v0) {
        }

        return 1;
    }

    protected void postTap(int x, int y) {  // has try-catch handlers
        Application v1_1;
        try {
            Object v0 = this.usedTouchEvents.obtain();
            ((TouchEvent)v0).timeStamp = System.nanoTime();
            ((TouchEvent)v0).pointer = 0;
            ((TouchEvent)v0).x = x;
            ((TouchEvent)v0).y = y;
            ((TouchEvent)v0).type = 0;
            this.touchEvents.add(v0);
            v0 = this.usedTouchEvents.obtain();
            ((TouchEvent)v0).timeStamp = System.nanoTime();
            ((TouchEvent)v0).pointer = 0;
            ((TouchEvent)v0).x = x;
            ((TouchEvent)v0).y = y;
            ((TouchEvent)v0).type = 1;
            this.touchEvents.add(v0);
            v1_1 = Gdx.app;
        }
        catch(Throwable v1) {
            try {
            label_29:
                throw v1;
            }
            catch(Throwable v1) {
                goto label_29;
            }
        }

        v1_1.getGraphics().requestRendering();
    }

    void processEvents() {  // has try-catch handlers
        Object v0;
        int v2;
        try {
            this.justTouched = false;
            if(this.processor != null) {
                InputProcessor v3 = this.processor;
                v2 = this.keyEvents.size();
                int v1;
                for(v1 = 0; v1 < v2; ++v1) {
                    v0 = this.keyEvents.get(v1);
                    this.currentEventTimeStamp = ((KeyEvent)v0).timeStamp;
                    switch(((KeyEvent)v0).type) {
                        case 0: {
                            v3.keyDown(((KeyEvent)v0).keyCode);
                            break;
                        }
                        case 1: {
                            v3.keyUp(((KeyEvent)v0).keyCode);
                            break;
                        }
                        case 2: {
                            v3.keyTyped(((KeyEvent)v0).keyChar);
                            break;
                        }
                    }

                    this.usedKeyEvents.free(v0);
                }

                v2 = this.touchEvents.size();
                for(v1 = 0; v1 < v2; ++v1) {
                    v0 = this.touchEvents.get(v1);
                    this.currentEventTimeStamp = ((TouchEvent)v0).timeStamp;
                    switch(((TouchEvent)v0).type) {
                        case 0: {
                            v3.touchDown(((TouchEvent)v0).x, ((TouchEvent)v0).y, ((TouchEvent)v0).pointer, 0);
                            this.justTouched = true;
                            break;
                        }
                        case 1: {
                            v3.touchUp(((TouchEvent)v0).x, ((TouchEvent)v0).y, ((TouchEvent)v0).pointer, 0);
                            break;
                        }
                        case 2: {
                            v3.touchDragged(((TouchEvent)v0).x, ((TouchEvent)v0).y, ((TouchEvent)v0).pointer);
                            break;
                        }
                    }

                    this.usedTouchEvents.free(v0);
                }
            }
            else {
                v2 = this.touchEvents.size();
                for(v1 = 0; v1 < v2; ++v1) {
                    v0 = this.touchEvents.get(v1);
                    if(((TouchEvent)v0).type == 0) {
                        this.justTouched = true;
                    }

                    this.usedTouchEvents.free(v0);
                }

                v2 = this.keyEvents.size();
                for(v1 = 0; v1 < v2; ++v1) {
                    this.usedKeyEvents.free(this.keyEvents.get(v1));
                }
            }

            if(this.touchEvents.size() == 0) {
                for(v1 = 0; v1 < this.deltaX.length; ++v1) {
                    this.deltaX[0] = 0;
                    this.deltaY[0] = 0;
                }
            }

            this.keyEvents.clear();
            this.touchEvents.clear();
            return;
        label_23:
            throw v4;
        }
        catch(Throwable v4) {
            goto label_23;
        }
    }

    void registerSensorListeners() {
        if(this.config.useAccelerometer) {
            this.manager = this.context.getSystemService("sensor");
            if(this.manager.getSensorList(1).size() == 0) {
                this.accelerometerAvailable = false;
            }
            else {
                Object v0 = this.manager.getSensorList(1).get(0);
                this.accelerometerListener = new SensorListener(this, this.nativeOrientation, this.accelerometerValues, this.magneticFieldValues);
                this.accelerometerAvailable = this.manager.registerListener(this.accelerometerListener, ((Sensor)v0), 1);
            }
        }
        else {
            this.accelerometerAvailable = false;
        }

        if(this.config.useCompass) {
            if(this.manager == null) {
                this.manager = this.context.getSystemService("sensor");
            }

            Sensor v1 = this.manager.getDefaultSensor(2);
            if(v1 == null) {
                goto label_60;
            }

            this.compassAvailable = this.accelerometerAvailable;
            if(!this.compassAvailable) {
                goto label_40;
            }

            this.compassListener = new SensorListener(this, this.nativeOrientation, this.accelerometerValues, this.magneticFieldValues);
            this.compassAvailable = this.manager.registerListener(this.compassListener, v1, 1);
            goto label_40;
        label_60:
            this.compassAvailable = false;
        }
        else {
            this.compassAvailable = false;
        }

    label_40:
        Gdx.app.log("AndroidInput", "sensor listener setup");
    }

    public void setCatchBackKey(boolean catchBack) {
        this.catchBack = catchBack;
    }

    public void setCatchMenuKey(boolean catchMenu) {
        this.catchMenu = catchMenu;
    }

    public void setCursorCatched(boolean catched) {
    }

    public void setCursorImage(Pixmap pixmap, int xHotspot, int yHotspot) {
    }

    public void setCursorPosition(int x, int y) {
    }

    public void setInputProcessor(InputProcessor processor) {  // has try-catch handlers
        try {
            this.processor = processor;
            return;
        label_3:
            throw v0;
        }
        catch(Throwable v0) {
            goto label_3;
        }
    }

    public void setOnscreenKeyboardVisible(boolean visible) {
        this.handle.post(new Runnable() {
            public void run() {
                Object v0 = AndroidInput.this.context.getSystemService("input_method");
                if(this.val$visible) {
                    View v1 = AndroidInput.this.app.getGraphics().getView();
                    v1.setFocusable(true);
                    v1.setFocusableInTouchMode(true);
                    ((InputMethodManager)v0).showSoftInput(AndroidInput.this.app.getGraphics().getView(), 0);
                }
                else {
                    ((InputMethodManager)v0).hideSoftInputFromWindow(AndroidInput.this.app.getGraphics().getView().getWindowToken(), 0);
                }
            }
        });
    }

    void unregisterSensorListeners() {
        SensorEventListener v2 = null;
        if(this.manager != null) {
            if(this.accelerometerListener != null) {
                this.manager.unregisterListener(this.accelerometerListener);
                this.accelerometerListener = v2;
            }

            if(this.compassListener != null) {
                this.manager.unregisterListener(this.compassListener);
                this.compassListener = v2;
            }

            this.manager = ((SensorManager)v2);
        }

        Gdx.app.log("AndroidInput", "sensor listener tear down");
    }

    private void updateOrientation() {
        if(SensorManager.getRotationMatrix(this.R, null, this.accelerometerValues, this.magneticFieldValues)) {
            SensorManager.getOrientation(this.R, this.orientation);
            this.azimuth = ((float)Math.toDegrees(((double)this.orientation[0])));
            this.pitch = ((float)Math.toDegrees(((double)this.orientation[1])));
            this.roll = ((float)Math.toDegrees(((double)this.orientation[2])));
        }
    }

    public void vibrate(int milliseconds) {
        this.vibrator.vibrate(((long)milliseconds));
    }

    public void vibrate(long[] pattern, int repeat) {
        this.vibrator.vibrate(pattern, repeat);
    }
}

