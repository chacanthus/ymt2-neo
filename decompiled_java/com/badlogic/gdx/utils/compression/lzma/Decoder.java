// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.utils.compression.lzma;

import com.badlogic.gdx.utils.compression.lz.OutWindow;
import com.badlogic.gdx.utils.compression.rangecoder.BitTreeDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Decoder {
    class LenDecoder {
        short[] m_Choice;
        BitTreeDecoder m_HighCoder;
        BitTreeDecoder[] m_LowCoder;
        BitTreeDecoder[] m_MidCoder;
        int m_NumPosStates;

        LenDecoder(Decoder arg3) {
            Decoder.this = arg3;
            super();
            this.m_Choice = new short[2];
            this.m_LowCoder = new BitTreeDecoder[16];
            this.m_MidCoder = new BitTreeDecoder[16];
            this.m_HighCoder = new BitTreeDecoder(8);
            this.m_NumPosStates = 0;
        }

        public void Create(int numPosStates) {
            int v3 = 3;
            while(this.m_NumPosStates < numPosStates) {
                this.m_LowCoder[this.m_NumPosStates] = new BitTreeDecoder(v3);
                this.m_MidCoder[this.m_NumPosStates] = new BitTreeDecoder(v3);
                ++this.m_NumPosStates;
            }
        }

        public int Decode(com.badlogic.gdx.utils.compression.rangecoder.Decoder rangeDecoder, int posState) throws IOException {
            int v0;
            if(rangeDecoder.DecodeBit(this.m_Choice, 0) == 0) {
                v0 = this.m_LowCoder[posState].Decode(rangeDecoder);
            }
            else {
                v0 = 8;
                if(rangeDecoder.DecodeBit(this.m_Choice, 1) == 0) {
                    v0 += this.m_MidCoder[posState].Decode(rangeDecoder);
                }
                else {
                    v0 += this.m_HighCoder.Decode(rangeDecoder) + 8;
                }
            }

            return v0;
        }

        public void Init() {
            com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Choice);
            int v0;
            for(v0 = 0; v0 < this.m_NumPosStates; ++v0) {
                this.m_LowCoder[v0].Init();
                this.m_MidCoder[v0].Init();
            }

            this.m_HighCoder.Init();
        }
    }

    class LiteralDecoder {
        class Decoder2 {
            short[] m_Decoders;

            Decoder2(LiteralDecoder arg2) {
                this.this$1 = arg2;
                super();
                this.m_Decoders = new short[768];
            }

            public byte DecodeNormal(com.badlogic.gdx.utils.compression.rangecoder.Decoder rangeDecoder) throws IOException {
                int v0 = 1;
                do {
                    v0 = v0 << 1 | rangeDecoder.DecodeBit(this.m_Decoders, v0);
                }
                while(v0 < 256);

                return ((byte)v0);
            }

            public byte DecodeWithMatchByte(com.badlogic.gdx.utils.compression.rangecoder.Decoder rangeDecoder, byte matchByte) throws IOException {
                int v5 = 256;
                int v2 = 1;
                do {
                    int v1 = matchByte >> 7 & 1;
                    matchByte = ((byte)(matchByte << 1));
                    int v0 = rangeDecoder.DecodeBit(this.m_Decoders, (v1 + 1 << 8) + v2);
                    v2 = v2 << 1 | v0;
                    if(v1 != v0) {
                        break;
                    }
                    else if(v2 < v5) {
                        continue;
                    }

                    goto label_21;
                }
                while(true);

                while(v2 < v5) {
                    v2 = v2 << 1 | rangeDecoder.DecodeBit(this.m_Decoders, v2);
                }

            label_21:
                return ((byte)v2);
            }

            public void Init() {
                com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Decoders);
            }
        }

        Decoder2[] m_Coders;
        int m_NumPosBits;
        int m_NumPrevBits;
        int m_PosMask;

        LiteralDecoder(Decoder arg1) {
            Decoder.this = arg1;
            super();
        }

        public void Create(int numPosBits, int numPrevBits) {
            if(this.m_Coders == null || this.m_NumPrevBits != numPrevBits || this.m_NumPosBits != numPosBits) {
                this.m_NumPosBits = numPosBits;
                this.m_PosMask = (1 << numPosBits) - 1;
                this.m_NumPrevBits = numPrevBits;
                int v1 = 1 << this.m_NumPrevBits + this.m_NumPosBits;
                this.m_Coders = new Decoder2[v1];
                int v0;
                for(v0 = 0; v0 < v1; ++v0) {
                    this.m_Coders[v0] = new Decoder2(this);
                }
            }
        }

        Decoder2 GetDecoder(int pos, byte prevByte) {
            return this.m_Coders[((this.m_PosMask & pos) << this.m_NumPrevBits) + ((prevByte & 255) >>> 8 - this.m_NumPrevBits)];
        }

        public void Init() {
            int v1 = 1 << this.m_NumPrevBits + this.m_NumPosBits;
            int v0;
            for(v0 = 0; v0 < v1; ++v0) {
                this.m_Coders[v0].Init();
            }
        }
    }

    int m_DictionarySize;
    int m_DictionarySizeCheck;
    short[] m_IsMatchDecoders;
    short[] m_IsRep0LongDecoders;
    short[] m_IsRepDecoders;
    short[] m_IsRepG0Decoders;
    short[] m_IsRepG1Decoders;
    short[] m_IsRepG2Decoders;
    LenDecoder m_LenDecoder;
    LiteralDecoder m_LiteralDecoder;
    OutWindow m_OutWindow;
    BitTreeDecoder m_PosAlignDecoder;
    short[] m_PosDecoders;
    BitTreeDecoder[] m_PosSlotDecoder;
    int m_PosStateMask;
    com.badlogic.gdx.utils.compression.rangecoder.Decoder m_RangeDecoder;
    LenDecoder m_RepLenDecoder;

    public Decoder() {
        int v4 = 4;
        super();
        this.m_OutWindow = new OutWindow();
        this.m_RangeDecoder = new com.badlogic.gdx.utils.compression.rangecoder.Decoder();
        this.m_IsMatchDecoders = new short[192];
        this.m_IsRepDecoders = new short[12];
        this.m_IsRepG0Decoders = new short[12];
        this.m_IsRepG1Decoders = new short[12];
        this.m_IsRepG2Decoders = new short[12];
        this.m_IsRep0LongDecoders = new short[192];
        this.m_PosSlotDecoder = new BitTreeDecoder[v4];
        this.m_PosDecoders = new short[114];
        this.m_PosAlignDecoder = new BitTreeDecoder(v4);
        this.m_LenDecoder = new LenDecoder(this);
        this.m_RepLenDecoder = new LenDecoder(this);
        this.m_LiteralDecoder = new LiteralDecoder(this);
        this.m_DictionarySize = -1;
        this.m_DictionarySizeCheck = -1;
        int v0;
        for(v0 = 0; v0 < v4; ++v0) {
            this.m_PosSlotDecoder[v0] = new BitTreeDecoder(6);
        }
    }

    public boolean Code(InputStream inStream, OutputStream outStream, long outSize) throws IOException {
        boolean v17;
        int v4;
        int v5;
        this.m_RangeDecoder.SetStream(inStream);
        this.m_OutWindow.SetStream(outStream);
        this.Init();
        int v16 = Base.StateInit();
        int v12 = 0;
        int v13 = 0;
        int v14 = 0;
        int v15 = 0;
        long v6 = 0;
        byte v11 = 0;
        while(true) {
            if(outSize < 0 || v6 < outSize) {
                int v10 = (((int)v6)) & this.m_PosStateMask;
                if(this.m_RangeDecoder.DecodeBit(this.m_IsMatchDecoders, (v16 << 4) + v10) == 0) {
                    Decoder2 v3 = this.m_LiteralDecoder.GetDecoder(((int)v6), v11);
                    if(!Base.StateIsCharState(v16)) {
                        v11 = v3.DecodeWithMatchByte(this.m_RangeDecoder, this.m_OutWindow.GetByte(v12));
                    }
                    else {
                        v11 = v3.DecodeNormal(this.m_RangeDecoder);
                    }

                    this.m_OutWindow.PutByte(v11);
                    v16 = Base.StateUpdateChar(v16);
                    ++v6;
                    continue;
                }
                else {
                    if(this.m_RangeDecoder.DecodeBit(this.m_IsRepDecoders, v16) == 1) {
                        v5 = 0;
                        if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG0Decoders, v16) != 0) {
                            if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG1Decoders, v16) == 0) {
                                v4 = v13;
                            }
                            else {
                                if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG2Decoders, v16) == 0) {
                                    v4 = v14;
                                }
                                else {
                                    v4 = v15;
                                    v15 = v14;
                                }

                                v14 = v13;
                            }

                            v13 = v12;
                            v12 = v4;
                        }
                        else if(this.m_RangeDecoder.DecodeBit(this.m_IsRep0LongDecoders, (v16 << 4) + v10) == 0) {
                            v16 = Base.StateUpdateShortRep(v16);
                            v5 = 1;
                        }

                        if(v5 != 0) {
                            goto label_125;
                        }

                        v5 = this.m_RepLenDecoder.Decode(this.m_RangeDecoder, v10) + 2;
                        v16 = Base.StateUpdateRep(v16);
                    }
                    else {
                        v15 = v14;
                        v14 = v13;
                        v13 = v12;
                        v5 = this.m_LenDecoder.Decode(this.m_RangeDecoder, v10) + 2;
                        v16 = Base.StateUpdateMatch(v16);
                        int v9 = this.m_PosSlotDecoder[Base.GetLenToPosState(v5)].Decode(this.m_RangeDecoder);
                        if(v9 < 4) {
                            goto label_250;
                        }

                        int v8 = (v9 >> 1) - 1;
                        v12 = (v9 & 1 | 2) << v8;
                        if(v9 >= 14) {
                            goto label_215;
                        }

                        v12 += BitTreeDecoder.ReverseDecode(this.m_PosDecoders, v12 - v9 - 1, this.m_RangeDecoder, v8);
                        goto label_125;
                    label_215:
                        v12 = v12 + (this.m_RangeDecoder.DecodeDirectBits(v8 - 4) << 4) + this.m_PosAlignDecoder.ReverseDecode(this.m_RangeDecoder);
                        if(v12 >= 0) {
                            goto label_125;
                        }

                        if(v12 != -1) {
                            goto label_248;
                        }

                    label_234:
                        this.m_OutWindow.Flush();
                        this.m_OutWindow.ReleaseStream();
                        this.m_RangeDecoder.ReleaseStream();
                        v17 = true;
                        goto label_134;
                    label_248:
                        v17 = false;
                        goto label_134;
                    label_250:
                        v12 = v9;
                    }

                label_125:
                    if((((long)v12)) < v6 && v12 < this.m_DictionarySizeCheck) {
                        this.m_OutWindow.CopyBlock(v12, v5);
                        v6 += ((long)v5);
                        v11 = this.m_OutWindow.GetByte(0);
                        continue;
                    }

                    break;
                }
            }
            else {
                goto label_234;
            }

            goto label_134;
        }

        v17 = false;
    label_134:
        return v17;
    }

    void Init() throws IOException {
        this.m_OutWindow.Init(false);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsMatchDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRep0LongDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG0Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG1Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG2Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_PosDecoders);
        this.m_LiteralDecoder.Init();
        int v0;
        for(v0 = 0; v0 < 4; ++v0) {
            this.m_PosSlotDecoder[v0].Init();
        }

        this.m_LenDecoder.Init();
        this.m_RepLenDecoder.Init();
        this.m_PosAlignDecoder.Init();
        this.m_RangeDecoder.Init();
    }

    public boolean SetDecoderProperties(byte[] properties) {
        boolean v7 = false;
        if(properties.length >= 5) {
            int v6 = properties[0] & 255;
            int v2 = v6 % 9;
            int v5 = v6 / 9;
            int v3 = v5 % 5;
            int v4 = v5 / 5;
            int v0 = 0;
            int v1;
            for(v1 = 0; v1 < 4; ++v1) {
                v0 += (properties[v1 + 1] & 255) << v1 * 8;
            }

            if(this.SetLcLpPb(v2, v3, v4)) {
                v7 = this.SetDictionarySize(v0);
            }
        }

        return v7;
    }

    boolean SetDictionarySize(int dictionarySize) {
        boolean v0 = true;
        if(dictionarySize < 0) {
            v0 = false;
        }
        else if(this.m_DictionarySize != dictionarySize) {
            this.m_DictionarySize = dictionarySize;
            this.m_DictionarySizeCheck = Math.max(this.m_DictionarySize, 1);
            this.m_OutWindow.Create(Math.max(this.m_DictionarySizeCheck, 4096));
        }

        return v0;
    }

    boolean SetLcLpPb(int lc, int lp, int pb) {
        int v3 = 4;
        boolean v1 = true;
        if(lc > 8 || lp > v3 || pb > v3) {
            v1 = false;
        }
        else {
            this.m_LiteralDecoder.Create(lp, lc);
            int v0 = 1 << pb;
            this.m_LenDecoder.Create(v0);
            this.m_RepLenDecoder.Create(v0);
            this.m_PosStateMask = v0 - 1;
        }

        return v1;
    }
}

