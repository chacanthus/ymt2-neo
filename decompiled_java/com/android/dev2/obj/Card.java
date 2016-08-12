// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.android.dev2.obj;

import com.android.dev2.Global;
import com.android.dev2.Res;
import com.android.dev2.lib.Util;
import com.android.dev2.screen.GameScreen;

public class Card {
    public static final int LOOP = 1;
    public static final int STOP;
    public float dX;
    public float dXSize;
    public float dY;
    public float dYSize;
    private float deltaTime;
    public float fAlpha;
    private float fMinAlpha;
    private float frameDuration;
    private int frameNumber;
    private float half_height;
    private float half_width;
    private float height;
    private float hover;
    private int iChannel;
    private int iCurrentLoopCount;
    private int iMoveCnt;
    private int iStep;
    private int iTargetLoopCount;
    private int iTotalFrameCount;
    public float mileage;
    public int no;
    public float oX;
    public float oY;
    private float oldAlpha;
    private int playMode;
    private boolean shrinking;
    public boolean small;
    public float startY;
    public float tX;
    public float tY;
    public float targetY;
    private float totalDuration;
    public boolean visible;
    private float width;
    public float x;
    public float y;

    public Card(int _iChannel, float _frameDuration, int _no) {
        super();
        this.width = 0f;
        this.height = 0f;
        this.half_width = this.width / 2f;
        this.half_height = this.height / 2f;
        this.x = 0f;
        this.y = 0f;
        this.targetY = 0f;
        this.startY = 0f;
        this.shrinking = false;
        this.iMoveCnt = 0;
        this.iStep = 100;
        this.iChannel = 0;
        this.small = false;
        this.tX = 0f;
        this.tY = 0f;
        this.oX = 0f;
        this.oY = 0f;
        this.dX = 0f;
        this.dY = 0f;
        this.dXSize = 0f;
        this.dYSize = 0f;
        this.iTotalFrameCount = 1;
        this.iTargetLoopCount = 1;
        this.iCurrentLoopCount = 0;
        this.deltaTime = 0f;
        this.mileage = 0f;
        this.alphaFlag = 0;
        this.fAlpha = 1f;
        this.fMinAlpha = 0.2f;
        this.visible = true;
        this.playMode = 0;
        this.iChannel = _iChannel;
        this.frameDuration = _frameDuration;
        this.no = _no;
        if(_no == 1) {
            this.iTotalFrameCount = Res.rgCard1.length;
        }
        else if(_no == 2) {
            this.iTotalFrameCount = Res.rgCard2.length;
        }
        else if(_no == 3) {
            this.iTotalFrameCount = Res.rgCard3.length;
        }
        else if(_no == 4) {
            this.iTotalFrameCount = Res.rgCard4.length;
        }
        else if(_no == 5) {
            this.iTotalFrameCount = Res.rgCard5.length;
        }
        else if(_no == 6) {
            this.iTotalFrameCount = Res.rgCard6.length;
        }
        else if(_no == 7) {
            this.iTotalFrameCount = Res.rgCard7.length;
        }
        else if(_no == 8) {
            this.iTotalFrameCount = Res.rgCard8.length;
        }
        else {
            Util.sysout("ERROR", "Card->Card", "Undefined Card.");
        }

        this.totalDuration = (((float)this.iTotalFrameCount)) * this.frameDuration;
    }

    public void draw(float _deltaTime, float _alpha) {
        float v4 = 2f;
        float v3 = 1f;
        float v2 = 0.02f;
        if(this.visible) {
            if(this.shrinking) {
                ++this.iMoveCnt;
                this.width += this.dXSize;
                this.height += this.dYSize;
                this.half_width = this.width / v4;
                this.half_height = this.height / v4;
                this.x += this.dX;
                this.y += this.dY;
                if(this.iMoveCnt >= this.iStep) {
                    this.x = this.tX;
                    this.y = this.tY;
                    this.shrinking = false;
                }
            }

            this.oldAlpha = GameScreen.color.a;
            if(this.alphaFlag == 0) {
                GameScreen.color.a = _alpha;
            }
            else if(this.alphaFlag > 0) {
                this.fAlpha += v2;
                if(this.fAlpha > v3) {
                    this.fAlpha = v3;
                }

                GameScreen.color.a = this.fAlpha;
            }
            else {
                if(this.alphaFlag >= 0) {
                    goto label_49;
                }

                this.fAlpha -= v2;
                if(this.fAlpha < this.fMinAlpha) {
                    this.fAlpha = this.fMinAlpha;
                }

                GameScreen.color.a = this.fAlpha;
            }

        label_49:
            GameScreen.batch.setColor(GameScreen.color);
            if(GameScreen.bHovering[this.iChannel]) {
                this.hover = (((float)Math.sin(((double)(GameScreen.fTotalReelElapseTime[this.iChannel] * 7f))))) * 0.0014f;
            }
            else {
                this.hover = 0f;
            }

            this.deltaTime += _deltaTime;
            if(this.no == 1) {
                GameScreen.batch.draw(Res.rgCard1[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 2) {
                GameScreen.batch.draw(Res.rgCard2[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 3) {
                GameScreen.batch.draw(Res.rgCard3[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 4) {
                GameScreen.batch.draw(Res.rgCard4[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 5) {
                GameScreen.batch.draw(Res.rgCard5[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 6) {
                GameScreen.batch.draw(Res.rgCard6[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 7) {
                GameScreen.batch.draw(Res.rgCard7[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else if(this.no == 8) {
                GameScreen.batch.draw(Res.rgCard8[this.getFrameNumber()], this.x - this.half_width, this.y - this.half_height + this.hover, this.width, this.height);
            }
            else {
                Util.sysout("ERROR", "Card->draw", "Undefined Card.", "no=" + this.no);
            }

            GameScreen.color.a = this.oldAlpha;
            GameScreen.batch.setColor(GameScreen.color);
        }
    }

    public int getFrameNumber() {
        this.frameNumber = ((int)(this.deltaTime / this.frameDuration));
        if(this.playMode == 0) {
            this.frameNumber = 0;
        }
        else {
            this.frameNumber %= this.iTotalFrameCount;
        }

        this.iCurrentLoopCount = ((int)(this.deltaTime / this.totalDuration));
        if(this.iCurrentLoopCount >= this.iTargetLoopCount) {
            this.playMode = 0;
            this.frameNumber = 0;
        }

        return this.frameNumber;
    }

    public float getHeight() {
        return this.height;
    }

    public float getWidth() {
        return this.width;
    }

    public void play(boolean bPlay, int _iTargetLoopCount) {
        this.deltaTime = this.frameDuration;
        this.iCurrentLoopCount = 0;
        if(bPlay) {
            this.playMode = 1;
            this.iTargetLoopCount = _iTargetLoopCount;
        }
        else {
            this.playMode = 0;
            this.iTargetLoopCount = 0;
        }
    }

    public void play(boolean bPlay) {
        this.deltaTime = this.frameDuration;
        this.iCurrentLoopCount = 0;
        if(bPlay) {
            this.playMode = 1;
            this.iTargetLoopCount = 2147483647;
        }
        else {
            this.playMode = 0;
            this.iTargetLoopCount = 0;
        }
    }

    public void setAlphaFlag(int _alphaFlag) {
        if(_alphaFlag > 0) {
            this.fAlpha = this.fMinAlpha;
        }
        else if(_alphaFlag < 0) {
            this.fAlpha = 1f;
        }

        this.alphaFlag = _alphaFlag;
    }

    public void setNo(int _no) {
        _no %= 8;
        if(_no < 0) {
            _no = 8 - _no * -1;
        }

        if(_no == 0) {
            _no = 8;
        }

        this.no = _no;
        if(_no == 1) {
            this.iTotalFrameCount = Res.rgCard1.length;
        }
        else if(_no == 2) {
            this.iTotalFrameCount = Res.rgCard2.length;
        }
        else if(_no == 3) {
            this.iTotalFrameCount = Res.rgCard3.length;
        }
        else if(_no == 4) {
            this.iTotalFrameCount = Res.rgCard4.length;
        }
        else if(_no == 5) {
            this.iTotalFrameCount = Res.rgCard5.length;
        }
        else if(_no == 6) {
            this.iTotalFrameCount = Res.rgCard6.length;
        }
        else if(_no == 7) {
            this.iTotalFrameCount = Res.rgCard7.length;
        }
        else if(_no == 8) {
            this.iTotalFrameCount = Res.rgCard8.length;
        }
        else {
            Util.sysout("ERROR", "Card->Card", "Undefined Card.");
        }

        this.totalDuration = (((float)this.iTotalFrameCount)) * this.frameDuration;
    }

    public void setPosition(float _x, float _y) {
        this.x = _x;
        this.y = _y;
    }

    public void setSize(float _width, float _height) {
        this.width = _width;
        this.height = _height;
        this.half_width = _width / 2f;
        this.half_height = _height / 2f;
    }

    public void shrink(float _tX, float _tY) {
        this.shrinking = true;
        this.iStep = 100;
        this.iMoveCnt = 0;
        this.tX = _tX;
        this.tY = _tY;
        this.oX = this.x;
        this.oY = this.y;
        this.dX = (this.tX - this.oX) / (((float)this.iStep));
        this.dY = (this.tY - this.oY) / (((float)this.iStep));
        this.dXSize = (Util.getWidth(((float)Global.smallCardSizeX)) - Util.getWidth(((float)Global.cardSizeX))) / (((float)this.iStep));
        this.dYSize = (Util.getHeight(((float)Global.smallCardSizeY)) - Util.getHeight(((float)Global.cardSizeY))) / (((float)this.iStep));
    }
}

