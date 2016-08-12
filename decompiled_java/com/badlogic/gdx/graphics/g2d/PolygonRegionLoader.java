// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;

public class PolygonRegionLoader {
    private EarClippingTriangulator triangulator;

    public PolygonRegionLoader() {
        super();
        this.triangulator = new EarClippingTriangulator();
    }

    public PolygonRegion load(TextureRegion textureRegion, FileHandle file) {  // has try-catch handlers
        PolygonRegion v7_1;
        int v1;
        float[] v6;
        String[] v4;
        String v2;
        BufferedReader v5 = file.reader(256);
        try {
            do {
                v2 = v5.readLine();
                if(v2 != null) {
                    continue;
                }

                goto label_4;
            }
            while(!v2.startsWith("s"));

            v4 = v2.substring(1).trim().split(",");
            v6 = new float[v4.length];
            v1 = 0;
            int v3 = v6.length;
            goto label_24;
        }
        catch(IOException v0) {
            goto label_37;
        }
        catch(Throwable v7) {
            goto label_45;
        }

    label_4:
        StreamUtils.closeQuietly(((Closeable)v5));
        throw new GdxRuntimeException("Polygon shape not found: " + file);
        try {
            while(true) {
            label_24:
                if(v1 >= v3) {
                    break;
                }

                v6[v1] = Float.parseFloat(v4[v1]);
                ++v1;
            }

            v7_1 = new PolygonRegion(textureRegion, v6, this.triangulator.computeTriangles(v6).toArray());
        }
        catch(IOException v0) {
            goto label_37;
        }
        catch(Throwable v7) {
            goto label_45;
        }

        StreamUtils.closeQuietly(((Closeable)v5));
        return v7_1;
        try {
        label_37:
            throw new GdxRuntimeException("Error reading polygon shape file: " + file, ((Throwable)v0));
        }
        catch(Throwable v7) {
        label_45:
            StreamUtils.closeQuietly(((Closeable)v5));
            throw v7;
        }
    }
}

