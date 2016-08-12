// 도박중독 예방 캠페인
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
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider$AssetTextureProvider;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider$FileTextureProvider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap$Entry;
import java.util.Iterator;

public abstract class ModelLoader extends AsynchronousAssetLoader {
    protected Array items;

    public ModelLoader(FileHandleResolver resolver) {
        super(resolver);
        this.items = new Array();
    }

    public Array getDependencies(String fileName, FileHandle file, AssetLoaderParameters arg13) {  // has try-catch handlers
        Object v5;
        Array v7_1;
        Array v1 = new Array();
        ModelData v0 = this.loadModelData(file, arg13);
        if(v0 != null) {
            Entry v4 = new Entry();
            v4.key = fileName;
            v4.value = v0;
            try {
                this.items.add(v4);
                v7_1 = v0.materials;
            }
            catch(Throwable v7) {
                try {
                label_28:
                    throw v7;
                }
                catch(Throwable v7) {
                    goto label_28;
                }
            }

            Iterator v2 = v7_1.iterator();
            do {
            label_12:
                if(v2.hasNext()) {
                    v5 = v2.next();
                    if(((ModelMaterial)v5).textures == null) {
                        continue;
                    }

                    break;
                }

                goto label_3;
            }
            while(true);

            Iterator v3 = ((ModelMaterial)v5).textures.iterator();
            while(true) {
                if(!v3.hasNext()) {
                    goto label_12;
                }

                v1.add(new AssetDescriptor(v3.next().fileName, Texture.class));
            }
        }

    label_3:
        return v1;
    }

    public void loadAsync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters arg4) {
    }

    public Model loadModel(FileHandle fileHandle) {
        return this.loadModel(fileHandle, new FileTextureProvider(), null);
    }

    public Model loadModel(FileHandle fileHandle, TextureProvider textureProvider, AssetLoaderParameters arg5) {
        Model v1;
        ModelData v0 = this.loadModelData(fileHandle, arg5);
        if(v0 == null) {
            v1 = null;
        }
        else {
            v1 = new Model(v0, textureProvider);
        }

        return v1;
    }

    public Model loadModel(FileHandle fileHandle, AssetLoaderParameters arg3) {
        return this.loadModel(fileHandle, new FileTextureProvider(), arg3);
    }

    public Model loadModel(FileHandle fileHandle, TextureProvider textureProvider) {
        return this.loadModel(fileHandle, textureProvider, null);
    }

    public abstract ModelData loadModelData(FileHandle arg0, AssetLoaderParameters arg1);

    public ModelData loadModelData(FileHandle fileHandle) {
        return this.loadModelData(fileHandle, null);
    }

    public Model loadSync(AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters arg12) {  // has try-catch handlers
        Model v5;
        Object v1 = null;
        int v4 = 0;
        try {
            while(v4 < this.items.size) {
                if(this.items.get(v4).key.equals(fileName)) {
                    v1 = this.items.get(v4).value;
                    this.items.removeIndex(v4);
                }

                ++v4;
            }

            if(v1 == null) {
                v5 = null;
            }
            else {
                goto label_25;
            }

            goto label_22;
        }
        catch(Throwable v6) {
            goto label_24;
        }

    label_25:
        v5 = new Model(((ModelData)v1), new AssetTextureProvider(manager));
        Iterator v3 = v5.getManagedDisposables().iterator();
        while(true) {
            if(v3.hasNext()) {
                if(!(v3.next() instanceof Texture)) {
                    continue;
                }

                v3.remove();
                continue;
            }

            goto label_22;
        }

        try {
        label_22:
            return v5;
        label_24:
            throw v6;
        }
        catch(Throwable v6) {
            goto label_24;
        }
    }

    public Object loadSync(AssetManager x0, String x1, FileHandle x2, AssetLoaderParameters x3) {
        return this.loadSync(x0, x1, x2, x3);
    }
}

