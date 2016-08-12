// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.XmlReader$Element;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

class TmxMapHelper {
    static final int FLAG_FLIP_DIAGONALLY = 536870912;
    static final int FLAG_FLIP_HORIZONTALLY = -2147483648;
    static final int FLAG_FLIP_VERTICALLY = 1073741824;
    static final int MASK_CLEAR = -536870912;

    private TmxMapHelper() {
        super();
    }

    static int[] getTileIds(Element element, int width, int height) {  // has try-catch handlers
        int v16;
        ByteArrayInputStream v11_1;
        String v4;
        Element v6 = element.getChildByName("data");
        String v8 = v6.getAttribute("encoding", null);
        if(v8 == null) {
            throw new GdxRuntimeException("Unsupported encoding (XML) for TMX Layer Data");
        }

        int[] v10 = new int[width * height];
        if(v8.equals("csv")) {
            String[] v2 = v6.getText().split(",");
            int v9;
            for(v9 = 0; v9 < v2.length; ++v9) {
                v10[v9] = ((int)Long.parseLong(v2[v9].trim()));
            }
        }
        else if(v8.equals("base64")) {
            Closeable v11 = null;
            try {
                v4 = v6.getAttribute("compression", null);
                byte[] v3 = Base64Coder.decode(v6.getText());
                if(v4 == null) {
                    v11_1 = new ByteArrayInputStream(v3);
                }
                else {
                    boolean v17_1 = v4.equals("gzip");
                    if(v17_1) {
                        GZIPInputStream v11_2 = new GZIPInputStream(new ByteArrayInputStream(v3), v3.length);
                    }
                    else {
                        v17_1 = v4.equals("zlib");
                        if(v17_1) {
                            InflaterInputStream v11_3 = new InflaterInputStream(new ByteArrayInputStream(v3));
                        }
                        else {
                            goto label_119;
                        }
                    }
                }

                byte[] v14 = new byte[4];
                v16 = 0;
                while(true) {
                label_56:
                    if(v16 >= height) {
                        goto label_156;
                    }

                    int v15;
                    for(v15 = 0; v15 < width; ++v15) {
                        int v13 = ((InputStream)v11_1).read(v14);
                        while(v13 < v14.length) {
                            int v5 = ((InputStream)v11_1).read(v14, v13, v14.length - v13);
                            if(v5 == -1) {
                                break;
                            }

                            v13 += v5;
                        }

                        if(v13 != v14.length) {
                            throw new GdxRuntimeException("Error Reading TMX Layer Data: Premature end of tile data");
                        }

                        v10[v16 * width + v15] = TmxMapHelper.unsignedByteToInt(v14[0]) | TmxMapHelper.unsignedByteToInt(v14[1]) << 8 | TmxMapHelper.unsignedByteToInt(v14[2]) << 16 | TmxMapHelper.unsignedByteToInt(v14[3]) << 24;
                    }
                }
            }
            catch(IOException v7) {
                goto label_83;
            }
            catch(Throwable v17) {
                goto label_92;
            }

            ++v16;
            goto label_56;
        label_156:
            StreamUtils.closeQuietly(((Closeable)v11_1));
        }
        else {
            goto label_158;
        }

        return v10;
        try {
        label_119:
            throw new GdxRuntimeException("Unrecognised compression (" + v4 + ") for TMX Layer Data");
        }
        catch(Throwable v17) {
        }
        catch(IOException v7) {
            try {
            label_83:
                throw new GdxRuntimeException("Error Reading TMX Layer Data - IOException: " + v7.getMessage());
            }
            catch(Throwable v17) {
            label_92:
                StreamUtils.closeQuietly(((Closeable)v11_1));
                throw v17;
            }
        }

    label_158:
        throw new GdxRuntimeException("Unrecognised encoding (" + v8 + ") for TMX Layer Data");
    }

    static int unsignedByteToInt(byte b) {
        return b & 255;
    }
}

