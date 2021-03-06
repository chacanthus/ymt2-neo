﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.assets.loaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture$TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class BitmapFontLoader extends AsynchronousAssetLoader {
    public class BitmapFontParameter extends AssetLoaderParameters {
        public BitmapFontParameter() {
            super();
            this.flip = false;
            this.minFilter = TextureFilter.Nearest;
            this.magFilter = TextureFilter.Nearest;
            this.bitmapFontData = null;
        }
    }

    BitmapFontData data;

    public BitmapFontLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    public Array getDependencies(String x0, FileHandle x1, AssetLoaderParameters x2) {
        return this.getDependencies(x0, x1, ((BitmapFontParameter)x2));
    }

    public Array getDependencies(String fileName, FileHandle file, BitmapFontParameter parameter) {
        boolean v2;
        Array v0 = new Array();
        if(parameter == null || parameter.bitmapFontData == null) {
            if(parameter != null) {
                v2 = parameter.flip;
            }
            else {
                v2 = false;
            }

            this.data = new BitmapFontData(file, v2);
            int v1;
            for(v1 = 0; v1 < this.data.getImagePaths().length; ++v1) {
                v0.add(new AssetDescriptor(this.data.getImagePath(v1), Texture.class));
            }
        }
        else {
            this.data = parameter.bitmapFontData;
        }

        return v0;
    }

    public void loadAsync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        this.loadAsync(x0, x1, x2, ((BitmapFontParameter)x3));
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle file, BitmapFontParameter parameter) {
    }

    public BitmapFont loadSync(AssetManager manager, String fileName, FileHandle file, BitmapFontParameter parameter) {
        TextureRegion[] v2 = new TextureRegion[this.data.getImagePaths().length];
        int v0;
        for(v0 = 0; v0 < v2.length; ++v0) {
            TextureRegion v1 = new TextureRegion(manager.get(this.data.getImagePath(v0), Texture.class));
            if(parameter != null) {
                v1.getTexture().setFilter(parameter.minFilter, parameter.magFilter);
            }

            v2[v0] = v1;
        }

        return new BitmapFont(this.data, v2, true);
    }

    public Object loadSync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        return this.loadSync(x0, x1, x2, ((BitmapFontParameter)x3));
    }
}

