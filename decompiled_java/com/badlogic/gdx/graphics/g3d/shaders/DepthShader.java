// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class DepthShader extends DefaultShader {
    public class Config extends com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Config {
        public boolean depthBufferOnly;

        public Config() {
            super();
            this.depthBufferOnly = false;
            this.defaultCullFace = 1028;
        }

        public Config(String vertexShader, String fragmentShader) {
            super(vertexShader, fragmentShader);
            this.depthBufferOnly = false;
        }
    }

    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    public final int numBones;
    public final int weights;

    static  {
        DepthShader.defaultVertexShader = null;
        DepthShader.defaultFragmentShader = null;
    }

    public DepthShader(Renderable renderable) {
        this(renderable, new Config());
    }

    public DepthShader(Renderable renderable, Config config) {
        this(renderable, config, DepthShader.createPrefix(renderable, config));
    }

    public DepthShader(Renderable renderable, Config config, String prefix) {
        String v5;
        String v4;
        if(config.vertexShader != null) {
            v4 = config.vertexShader;
        }
        else {
            v4 = DepthShader.getDefaultVertexShader();
        }

        if(config.fragmentShader != null) {
            v5 = config.fragmentShader;
        }
        else {
            v5 = DepthShader.getDefaultFragmentShader();
        }

        this(renderable, config, prefix, v4, v5);
    }

    public DepthShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        int v4;
        super(renderable, ((com.badlogic.gdx.graphics.g3d.shaders.DefaultShader$Config)config), shaderProgram);
        if(renderable.bones == null) {
            v4 = 0;
        }
        else {
            v4 = config.numBones;
        }

        this.numBones = v4;
        int v3 = 0;
        int v2 = renderable.mesh.getVertexAttributes().size();
        int v1;
        for(v1 = 0; v1 < v2; ++v1) {
            VertexAttribute v0 = renderable.mesh.getVertexAttributes().get(v1);
            if(v0.usage == 64) {
                v3 |= 1 << v0.unit;
            }
        }

        this.weights = v3;
    }

    public DepthShader(Renderable renderable, Config config, String prefix, String vertexShader, String fragmentShader) {
        this(renderable, config, new ShaderProgram(prefix + vertexShader, prefix + fragmentShader));
    }

    public void begin(Camera camera, RenderContext context) {
        super.begin(camera, context);
    }

    public boolean canRender(Renderable renderable) {
        int v7;
        int v3;
        boolean v5 = true;
        if((renderable.mesh.getVertexAttributes().getMask() & 64) == 64) {
            v3 = 1;
        }
        else {
            v3 = 0;
        }

        if(this.numBones > 0) {
            v7 = 1;
        }
        else {
            v7 = 0;
        }

        if(v3 != v7) {
            v5 = false;
        }
        else if(v3 != 0) {
            int v4 = 0;
            int v2 = renderable.mesh.getVertexAttributes().size();
            int v1;
            for(v1 = 0; v1 < v2; ++v1) {
                VertexAttribute v0 = renderable.mesh.getVertexAttributes().get(v1);
                if(v0.usage == 64) {
                    v4 |= 1 << v0.unit;
                }
            }

            if(v4 == this.weights) {
                goto label_14;
            }

            v5 = false;
        }

    label_14:
        return v5;
    }

    public static String createPrefix(Renderable renderable, Config config) {
        String v7 = "";
        renderable.material.getMask();
        if((renderable.mesh.getVertexAttributes().getMask() & 64) == 64) {
            int v6 = renderable.mesh.getVertexAttributes().size();
            int v3;
            for(v3 = 0; v3 < v6; ++v3) {
                VertexAttribute v0 = renderable.mesh.getVertexAttributes().get(v3);
                if(v0.usage == 64) {
                    v7 = v7 + "#define boneWeight" + v0.unit + "Flag\n";
                }
            }
        }

        if(renderable.bones != null && config.numBones > 0) {
            v7 = v7 + "#define numBones " + config.numBones + "\n";
        }

        if(!config.depthBufferOnly) {
            v7 = v7 + "#define PackedDepthFlag\n";
        }

        return v7;
    }

    public void end() {
        super.end();
    }

    public static final String getDefaultFragmentShader() {
        if(DepthShader.defaultFragmentShader == null) {
            DepthShader.defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.fragment.glsl").readString();
        }

        return DepthShader.defaultFragmentShader;
    }

    public static final String getDefaultVertexShader() {
        if(DepthShader.defaultVertexShader == null) {
            DepthShader.defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/depth.vertex.glsl").readString();
        }

        return DepthShader.defaultVertexShader;
    }
}

