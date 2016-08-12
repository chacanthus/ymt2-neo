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
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.CubemapAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.AmbientCubemap;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class DefaultShader extends BaseShader {
    public class Config {
        public int defaultCullFace;
        public int defaultDepthFunc;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public int numBones;
        public int numDirectionalLights;
        public int numPointLights;
        public int numSpotLights;
        public String vertexShader;

        public Config() {
            super();
            this.vertexShader = null;
            this.fragmentShader = null;
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
        }

        public Config(String vertexShader, String fragmentShader) {
            super();
            this.vertexShader = null;
            this.fragmentShader = null;
            this.numDirectionalLights = 2;
            this.numPointLights = 5;
            this.numSpotLights = 0;
            this.numBones = 12;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.vertexShader = vertexShader;
            this.fragmentShader = fragmentShader;
        }
    }

    public class Inputs {
        public static final Uniform dirLights;
        public static final Uniform pointLights;

        static  {
            Inputs.projTrans = new Uniform("u_projTrans");
            Inputs.viewTrans = new Uniform("u_viewTrans");
            Inputs.projViewTrans = new Uniform("u_projViewTrans");
            Inputs.cameraPosition = new Uniform("u_cameraPosition");
            Inputs.cameraDirection = new Uniform("u_cameraDirection");
            Inputs.cameraUp = new Uniform("u_cameraUp");
            Inputs.worldTrans = new Uniform("u_worldTrans");
            Inputs.worldViewTrans = new Uniform("u_worldViewTrans");
            Inputs.projViewWorldTrans = new Uniform("u_projViewWorldTrans");
            Inputs.normalMatrix = new Uniform("u_normalMatrix");
            Inputs.bones = new Uniform("u_bones");
            Inputs.shininess = new Uniform("u_shininess", FloatAttribute.Shininess);
            Inputs.opacity = new Uniform("u_opacity", BlendingAttribute.Type);
            Inputs.diffuseColor = new Uniform("u_diffuseColor", ColorAttribute.Diffuse);
            Inputs.diffuseTexture = new Uniform("u_diffuseTexture", TextureAttribute.Diffuse);
            Inputs.specularColor = new Uniform("u_specularColor", ColorAttribute.Specular);
            Inputs.specularTexture = new Uniform("u_specularTexture", TextureAttribute.Specular);
            Inputs.emissiveColor = new Uniform("u_emissiveColor", ColorAttribute.Emissive);
            Inputs.reflectionColor = new Uniform("u_reflectionColor", ColorAttribute.Reflection);
            Inputs.normalTexture = new Uniform("u_normalTexture", TextureAttribute.Normal);
            Inputs.alphaTest = new Uniform("u_alphaTest", FloatAttribute.AlphaTest);
            Inputs.ambientCube = new Uniform("u_ambientCubemap");
            Inputs.dirLights = new Uniform("u_dirLights");
            Inputs.pointLights = new Uniform("u_pointLights");
            Inputs.environmentCubemap = new Uniform("u_environmentCubemap");
        }

        public Inputs() {
            super();
        }
    }

    public class Setters {
        static  {
            Setters.projTrans = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.projection);
                }
            };
            Setters.viewTrans = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.view);
                }
            };
            Setters.projViewTrans = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.combined);
                }
            };
            Setters.cameraPosition = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.position.x, shader.camera.position.y, shader.camera.position.z, 1.1881f / (shader.camera.far * shader.camera.far));
                }
            };
            Setters.cameraDirection = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.direction);
                }
            };
            Setters.cameraUp = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 1;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.camera.up);
                }
            };
            Setters.worldTrans = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, renderable.worldTransform);
                }
            };
            Setters.worldViewTrans = new Setter() {
                final Matrix4 temp;

                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, this.temp.set(renderable.worldTransform).mul(shader.camera.view));
                }
            };
            Setters.projViewWorldTrans = new Setter() {
                final Matrix4 temp;

                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, this.temp.set(shader.camera.combined).mul(renderable.worldTransform));
                }
            };
            Setters.normalMatrix = new Setter() {
                private final Matrix3 tmpM;

                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, this.tmpM.set(renderable.worldTransform).inv().transpose());
                }
            };
            Setters.shininess = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, combinedAttributes.get(FloatAttribute.Shininess).value);
                }
            };
            Setters.diffuseColor = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, combinedAttributes.get(ColorAttribute.Diffuse).color);
                }
            };
            Setters.diffuseTexture = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.context.textureBinder.bind(combinedAttributes.get(TextureAttribute.Diffuse).textureDescription));
                }
            };
            Setters.specularColor = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, combinedAttributes.get(ColorAttribute.Specular).color);
                }
            };
            Setters.specularTexture = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.context.textureBinder.bind(combinedAttributes.get(TextureAttribute.Specular).textureDescription));
                }
            };
            Setters.emissiveColor = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, combinedAttributes.get(ColorAttribute.Emissive).color);
                }
            };
            Setters.reflectionColor = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, combinedAttributes.get(ColorAttribute.Reflection).color);
                }
            };
            Setters.normalTexture = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    shader.set(inputID, shader.context.textureBinder.bind(combinedAttributes.get(TextureAttribute.Normal).textureDescription));
                }
            };
            Setters.environmentCubemap = new Setter() {
                public boolean isGlobal(BaseShader shader, int inputID) {
                    return 0;
                }

                public void set(BaseShader shader, int inputID, Renderable renderable, Attributes combinedAttributes) {
                    if(combinedAttributes.has(CubemapAttribute.EnvironmentMap)) {
                        shader.set(inputID, shader.context.textureBinder.bind(combinedAttributes.get(CubemapAttribute.EnvironmentMap).textureDescription));
                    }
                }
            };
        }

        public Setters() {
            super();
        }
    }

    protected final AmbientCubemap ambientCubemap;
    private Camera camera;
    protected final Config config;
    Material currentMaterial;
    public static int defaultCullFace;
    public static int defaultDepthFunc;
    private static String defaultFragmentShader;
    private static String defaultVertexShader;
    protected int dirLightsColorOffset;
    protected int dirLightsDirectionOffset;
    protected int dirLightsLoc;
    protected int dirLightsSize;
    protected final DirectionalLight[] directionalLights;
    protected final boolean environmentCubemap;
    protected final boolean fog;
    protected static long implementedFlags;
    protected final boolean lighting;
    private boolean lightsSet;
    private long materialMask;
    private Matrix3 normalMatrix;
    private static final long optionalAttributes;
    protected final PointLight[] pointLights;
    protected int pointLightsColorOffset;
    protected int pointLightsLoc;
    protected int pointLightsPositionOffset;
    protected int pointLightsSize;
    private Renderable renderable;
    protected final boolean shadowMap;
    private float time;
    private final Vector3 tmpV1;
    public final int u_alphaTest;
    protected final int u_ambientCubemap;
    public final int u_bones;
    public final int u_cameraDirection;
    public final int u_cameraPosition;
    public final int u_cameraUp;
    public final int u_diffuseColor;
    public final int u_diffuseTexture;
    protected final int u_dirLights0color;
    protected final int u_dirLights0direction;
    protected final int u_dirLights1color;
    public final int u_emissiveColor;
    protected final int u_environmentCubemap;
    protected final int u_fogColor;
    public final int u_normalMatrix;
    public final int u_normalTexture;
    public final int u_opacity;
    protected final int u_pointLights0color;
    protected final int u_pointLights0intensity;
    protected final int u_pointLights0position;
    protected final int u_pointLights1color;
    public final int u_projTrans;
    public final int u_projViewTrans;
    public final int u_projViewWorldTrans;
    public final int u_reflectionColor;
    protected final int u_shadowMapProjViewTrans;
    protected final int u_shadowPCFOffset;
    protected final int u_shadowTexture;
    public final int u_shininess;
    public final int u_specularColor;
    public final int u_specularTexture;
    public final int u_time;
    public final int u_viewTrans;
    public final int u_viewWorldTrans;
    public final int u_worldTrans;
    private long vertexMask;

    static  {
        DefaultShader.defaultVertexShader = null;
        DefaultShader.defaultFragmentShader = null;
        DefaultShader.implementedFlags = BlendingAttribute.Type | TextureAttribute.Diffuse | ColorAttribute.Diffuse | ColorAttribute.Specular | FloatAttribute.Shininess;
        DefaultShader.defaultCullFace = 1029;
        DefaultShader.defaultDepthFunc = 515;
        DefaultShader.optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;
    }

    public DefaultShader(Renderable renderable) {
        this(renderable, new Config());
    }

    public DefaultShader(Renderable renderable, Config config) {
        this(renderable, config, DefaultShader.createPrefix(renderable, config));
    }

    public DefaultShader(Renderable renderable, Config config, String prefix) {
        String v5;
        String v4;
        if(config.vertexShader != null) {
            v4 = config.vertexShader;
        }
        else {
            v4 = DefaultShader.getDefaultVertexShader();
        }

        if(config.fragmentShader != null) {
            v5 = config.fragmentShader;
        }
        else {
            v5 = DefaultShader.getDefaultFragmentShader();
        }

        this(renderable, config, prefix, v4, v5);
    }

    public DefaultShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        int v1_1;
        boolean v1;
        int v4 = -1;
        boolean v2 = true;
        int v3 = 0;
        super();
        this.u_dirLights0color = this.register(new Uniform("u_dirLights[0].color"));
        this.u_dirLights0direction = this.register(new Uniform("u_dirLights[0].direction"));
        this.u_dirLights1color = this.register(new Uniform("u_dirLights[1].color"));
        this.u_pointLights0color = this.register(new Uniform("u_pointLights[0].color"));
        this.u_pointLights0position = this.register(new Uniform("u_pointLights[0].position"));
        this.u_pointLights0intensity = this.register(new Uniform("u_pointLights[0].intensity"));
        this.u_pointLights1color = this.register(new Uniform("u_pointLights[1].color"));
        this.u_fogColor = this.register(new Uniform("u_fogColor"));
        this.u_shadowMapProjViewTrans = this.register(new Uniform("u_shadowMapProjViewTrans"));
        this.u_shadowTexture = this.register(new Uniform("u_shadowTexture"));
        this.u_shadowPCFOffset = this.register(new Uniform("u_shadowPCFOffset"));
        this.ambientCubemap = new AmbientCubemap();
        this.normalMatrix = new Matrix3();
        this.tmpV1 = new Vector3();
        this.config = config;
        this.program = shaderProgram;
        if(renderable.environment != null) {
            v1 = true;
        }
        else {
            v1 = false;
        }

        this.lighting = v1;
        if(!renderable.material.has(CubemapAttribute.EnvironmentMap)) {
            if((this.lighting) && (renderable.environment.has(CubemapAttribute.EnvironmentMap))) {
                goto label_70;
            }

            v1 = false;
        }
        else {
        label_70:
            v1 = true;
        }

        this.environmentCubemap = v1;
        if(!this.lighting || renderable.environment.shadowMap == null) {
            v1 = false;
        }
        else {
            v1 = true;
        }

        this.shadowMap = v1;
        if(!this.lighting || !renderable.environment.has(ColorAttribute.Fog)) {
            v2 = false;
        }

        this.fog = v2;
        this.renderable = renderable;
        this.materialMask = renderable.material.getMask() | DefaultShader.optionalAttributes;
        this.vertexMask = renderable.mesh.getVertexAttributes().getMask();
        if(!this.lighting || config.numDirectionalLights <= 0) {
            v1_1 = 0;
        }
        else {
            v1_1 = config.numDirectionalLights;
        }

        this.directionalLights = new DirectionalLight[v1_1];
        int v0;
        for(v0 = 0; v0 < this.directionalLights.length; ++v0) {
            this.directionalLights[v0] = new DirectionalLight();
        }

        if((this.lighting) && config.numPointLights > 0) {
            v3 = config.numPointLights;
        }

        this.pointLights = new PointLight[v3];
        for(v0 = 0; v0 < this.pointLights.length; ++v0) {
            this.pointLights[v0] = new PointLight();
        }

        if(!config.ignoreUnimplemented && (DefaultShader.implementedFlags & this.materialMask) != this.materialMask) {
            throw new GdxRuntimeException("Some attributes not implemented yet (" + this.materialMask + ")");
        }

        this.u_projTrans = this.register(Inputs.projTrans, Setters.projTrans);
        this.u_viewTrans = this.register(Inputs.viewTrans, Setters.viewTrans);
        this.u_projViewTrans = this.register(Inputs.projViewTrans, Setters.projViewTrans);
        this.u_cameraPosition = this.register(Inputs.cameraPosition, Setters.cameraPosition);
        this.u_cameraDirection = this.register(Inputs.cameraDirection, Setters.cameraDirection);
        this.u_cameraUp = this.register(Inputs.cameraUp, Setters.cameraUp);
        this.u_time = this.register(new Uniform("u_time"));
        this.u_worldTrans = this.register(Inputs.worldTrans, Setters.worldTrans);
        this.u_viewWorldTrans = this.register(Inputs.worldViewTrans, Setters.worldViewTrans);
        this.u_projViewWorldTrans = this.register(Inputs.projViewWorldTrans, Setters.projViewWorldTrans);
        this.u_normalMatrix = this.register(Inputs.normalMatrix, Setters.normalMatrix);
        if(renderable.bones == null || config.numBones <= 0) {
            v1_1 = v4;
        }
        else {
            v1_1 = this.register(Inputs.bones, new Bones(config.numBones));
        }

        this.u_bones = v1_1;
        this.u_shininess = this.register(Inputs.shininess, Setters.shininess);
        this.u_opacity = this.register(Inputs.opacity);
        this.u_diffuseColor = this.register(Inputs.diffuseColor, Setters.diffuseColor);
        this.u_diffuseTexture = this.register(Inputs.diffuseTexture, Setters.diffuseTexture);
        this.u_specularColor = this.register(Inputs.specularColor, Setters.specularColor);
        this.u_specularTexture = this.register(Inputs.specularTexture, Setters.specularTexture);
        this.u_emissiveColor = this.register(Inputs.emissiveColor, Setters.emissiveColor);
        this.u_reflectionColor = this.register(Inputs.reflectionColor, Setters.reflectionColor);
        this.u_normalTexture = this.register(Inputs.normalTexture, Setters.normalTexture);
        this.u_alphaTest = this.register(Inputs.alphaTest);
        if(this.lighting) {
            v1_1 = this.register(Inputs.ambientCube, new ACubemap(config.numDirectionalLights, config.numPointLights));
        }
        else {
            v1_1 = v4;
        }

        this.u_ambientCubemap = v1_1;
        if(this.environmentCubemap) {
            v4 = this.register(Inputs.environmentCubemap, Setters.environmentCubemap);
        }

        this.u_environmentCubemap = v4;
    }

    public DefaultShader(Renderable renderable, Config config, String prefix, String vertexShader, String fragmentShader) {
        this(renderable, config, new ShaderProgram(prefix + vertexShader, prefix + fragmentShader));
    }

    private static final boolean and(long mask, long flag) {
        boolean v0;
        if((mask & flag) == flag) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void begin(Camera camera, RenderContext context) {
        super.begin(camera, context);
        DirectionalLight[] v10 = this.directionalLights;
        int v12 = v10.length;
        int v11;
        for(v11 = 0; v11 < v12; ++v11) {
            v10[v11].set(0f, 0f, 0f, 0f, -1f, 0f);
        }

        PointLight[] v10_1 = this.pointLights;
        v12 = v10_1.length;
        for(v11 = 0; v11 < v12; ++v11) {
            v10_1[v11].set(0f, 0f, 0f, 0f, 0f, 0f, 0f);
        }

        this.lightsSet = false;
        if(this.has(this.u_time)) {
            int v1 = this.u_time;
            float v3 = this.time + Gdx.graphics.getDeltaTime();
            this.time = v3;
            this.set(v1, v3);
        }
    }

    protected void bindLights(Renderable renderable) {
        int v2;
        float v12 = 1f;
        Environment v3 = renderable.environment;
        Array v0 = v3.directionalLights;
        Array v4 = v3.pointLights;
        if(this.dirLightsLoc >= 0) {
            int v1;
            for(v1 = 0; v1 < this.directionalLights.length; ++v1) {
                if(v0 == null || v1 >= v0.size) {
                    if((this.lightsSet) && this.directionalLights[v1].color.r == 0f && this.directionalLights[v1].color.g == 0f && this.directionalLights[v1].color.b == 0f) {
                        goto label_31;
                    }

                    this.directionalLights[v1].color.set(0f, 0f, 0f, v12);
                label_37:
                    v2 = this.dirLightsLoc + this.dirLightsSize * v1;
                    this.program.setUniformf(this.dirLightsColorOffset + v2, this.directionalLights[v1].color.r, this.directionalLights[v1].color.g, this.directionalLights[v1].color.b);
                    this.program.setUniformf(this.dirLightsDirectionOffset + v2, this.directionalLights[v1].direction);
                    if(this.dirLightsSize > 0) {
                        goto label_31;
                    }

                    break;
                }
                else {
                    if((this.lightsSet) && (this.directionalLights[v1].equals(v0.get(v1)))) {
                        goto label_31;
                    }

                    this.directionalLights[v1].set(v0.get(v1));
                    goto label_37;
                }

            label_31:
            }
        }

        if(this.pointLightsLoc >= 0) {
            for(v1 = 0; v1 < this.pointLights.length; ++v1) {
                if(v4 == null || v1 >= v4.size) {
                    if((this.lightsSet) && this.pointLights[v1].intensity == 0f) {
                        goto label_81;
                    }

                    this.pointLights[v1].intensity = 0f;
                label_98:
                    v2 = this.pointLightsLoc + this.pointLightsSize * v1;
                    this.program.setUniformf(this.pointLightsColorOffset + v2, this.pointLights[v1].color.r * this.pointLights[v1].intensity, this.pointLights[v1].color.g * this.pointLights[v1].intensity, this.pointLights[v1].color.b * this.pointLights[v1].intensity);
                    this.program.setUniformf(this.pointLightsPositionOffset + v2, this.pointLights[v1].position);
                    if(this.pointLightsSize > 0) {
                        goto label_81;
                    }

                    break;
                }
                else {
                    if((this.lightsSet) && (this.pointLights[v1].equals(v4.get(v1)))) {
                        goto label_81;
                    }

                    this.pointLights[v1].set(v4.get(v1));
                    goto label_98;
                }

            label_81:
            }
        }

        if(v3.has(ColorAttribute.Fog)) {
            this.set(this.u_fogColor, v3.get(ColorAttribute.Fog).color);
        }

        if(v3.shadowMap != null) {
            this.set(this.u_shadowMapProjViewTrans, v3.shadowMap.getProjViewTrans());
            this.set(this.u_shadowTexture, v3.shadowMap.getDepthMap());
            this.set(this.u_shadowPCFOffset, v12 / (2f * (((float)v3.shadowMap.getDepthMap().texture.getWidth()))));
        }

        this.lightsSet = true;
    }

    protected void bindMaterial(Renderable renderable) {
        Object v0;
        int v2;
        int v1;
        int v12 = -1;
        if(this.currentMaterial != renderable.material) {
            if(this.config.defaultCullFace == v12) {
                v1 = DefaultShader.defaultCullFace;
            }
            else {
                v1 = this.config.defaultCullFace;
            }

            if(this.config.defaultDepthFunc == v12) {
                v2 = DefaultShader.defaultDepthFunc;
            }
            else {
                v2 = this.config.defaultDepthFunc;
            }

            float v5 = 0f;
            float v4 = 1f;
            boolean v3 = true;
            this.currentMaterial = renderable.material;
            Iterator v7 = this.currentMaterial.iterator();
            do {
            label_20:
                if(!v7.hasNext()) {
                    goto label_78;
                }

                v0 = v7.next();
                long v8 = ((Attribute)v0).type;
                if(!BlendingAttribute.is(v8)) {
                    goto label_43;
                }

                this.context.setBlending(true, v0.sourceFunction, v0.destFunction);
                this.set(this.u_opacity, ((BlendingAttribute)v0).opacity);
                goto label_20;
            label_43:
                if((IntAttribute.CullFace & v8) != IntAttribute.CullFace) {
                    goto label_49;
                }

                v1 = ((IntAttribute)v0).value;
                goto label_20;
            label_49:
                if((FloatAttribute.AlphaTest & v8) != FloatAttribute.AlphaTest) {
                    goto label_57;
                }

                this.set(this.u_alphaTest, ((FloatAttribute)v0).value);
                goto label_20;
            label_57:
                if((DepthTestAttribute.Type & v8) != DepthTestAttribute.Type) {
                    continue;
                }

                v2 = v0.depthFunc;
                v5 = v0.depthRangeNear;
                v4 = v0.depthRangeFar;
                v3 = v0.depthMask;
                goto label_20;
            }
            while(this.config.ignoreUnimplemented);

            throw new GdxRuntimeException("Unknown material attribute: " + ((Attribute)v0).toString());
        label_78:
            this.context.setCullFace(v1);
            this.context.setDepthTest(v2, v5, v4);
            this.context.setDepthMask(v3);
        }
    }

    public boolean canRender(Renderable renderable) {
        boolean v0;
        boolean v1 = true;
        if(this.materialMask != (renderable.material.getMask() | DefaultShader.optionalAttributes) || this.vertexMask != renderable.mesh.getVertexAttributes().getMask()) {
        label_32:
            v1 = false;
        }
        else {
            if(renderable.environment != null) {
                v0 = true;
            }
            else {
                v0 = false;
            }

            if(v0 != this.lighting) {
                goto label_32;
            }

            if(renderable.environment == null || !renderable.environment.has(ColorAttribute.Fog)) {
                v0 = false;
            }
            else {
                v0 = true;
            }

            if(v0 != this.fog) {
                goto label_32;
            }
        }

        return v1;
    }

    public int compareTo(Shader other) {
        int v0 = 0;
        if(other == null) {
            v0 = -1;
        }

        return v0;
    }

    public static String createPrefix(Renderable renderable, Config config) {
        String v7 = "";
        long v4 = renderable.material.getMask();
        long v1 = renderable.mesh.getVertexAttributes().getMask();
        if(DefaultShader.and(v1, 1)) {
            v7 = v7 + "#define positionFlag\n";
        }

        if(DefaultShader.or(v1, 6)) {
            v7 = v7 + "#define colorFlag\n";
        }

        if(DefaultShader.and(v1, 256)) {
            v7 = v7 + "#define binormalFlag\n";
        }

        if(DefaultShader.and(v1, 128)) {
            v7 = v7 + "#define tangentFlag\n";
        }

        if(DefaultShader.and(v1, 8)) {
            v7 = v7 + "#define normalFlag\n";
        }

        if(((DefaultShader.and(v1, 8)) || (DefaultShader.and(v1, 384))) && renderable.environment != null) {
            v7 = new StringBuilder().append(new StringBuilder().append(v7).append("#define lightingFlag\n").toString() + "#define ambientCubemapFlag\n").append("#define numDirectionalLights ").append(config.numDirectionalLights).append("\n").toString() + "#define numPointLights " + config.numPointLights + "\n";
            if(renderable.environment.has(ColorAttribute.Fog)) {
                v7 = v7 + "#define fogFlag\n";
            }

            if(renderable.environment.shadowMap != null) {
                v7 = v7 + "#define shadowMapFlag\n";
            }

            if(!renderable.material.has(CubemapAttribute.EnvironmentMap) && !renderable.environment.has(CubemapAttribute.EnvironmentMap)) {
                goto label_112;
            }

            v7 = v7 + "#define environmentCubemapFlag\n";
        }

    label_112:
        int v6 = renderable.mesh.getVertexAttributes().size();
        int v3;
        for(v3 = 0; v3 < v6; ++v3) {
            VertexAttribute v0 = renderable.mesh.getVertexAttributes().get(v3);
            if(v0.usage == 64) {
                v7 = v7 + "#define boneWeight" + v0.unit + "Flag\n";
            }
            else if(v0.usage == 16) {
                v7 = v7 + "#define texCoord" + v0.unit + "Flag\n";
            }
        }

        if((128 & v1) == 128) {
            v7 = v7 + "#define tangentFlag\n";
        }

        if((256 & v1) == 256) {
            v7 = v7 + "#define binormalFlag\n";
        }

        if((BlendingAttribute.Type & v4) == BlendingAttribute.Type) {
            v7 = v7 + "#define blendedFlag\n";
        }

        if((TextureAttribute.Diffuse & v4) == TextureAttribute.Diffuse) {
            v7 = new StringBuilder().append(v7).append("#define diffuseTextureFlag\n").toString() + "#define diffuseTextureCoord texCoord0\n";
        }

        if((TextureAttribute.Normal & v4) == TextureAttribute.Normal) {
            v7 = new StringBuilder().append(v7).append("#define normalTextureFlag\n").toString() + "#define normalTextureCoord texCoord0\n";
        }

        if((ColorAttribute.Diffuse & v4) == ColorAttribute.Diffuse) {
            v7 = v7 + "#define diffuseColorFlag\n";
        }

        if((ColorAttribute.Specular & v4) == ColorAttribute.Specular) {
            v7 = v7 + "#define specularColorFlag\n";
        }

        if((ColorAttribute.Emissive & v4) == ColorAttribute.Emissive) {
            v7 = v7 + "#define emissiveColorFlag\n";
        }

        if((ColorAttribute.Reflection & v4) == ColorAttribute.Reflection) {
            v7 = v7 + "#define reflectionColorFlag\n";
        }

        if((FloatAttribute.Shininess & v4) == FloatAttribute.Shininess) {
            v7 = v7 + "#define shininessFlag\n";
        }

        if((FloatAttribute.AlphaTest & v4) == FloatAttribute.AlphaTest) {
            v7 = v7 + "#define alphaTestFlag\n";
        }

        if(renderable.bones != null && config.numBones > 0) {
            v7 = v7 + "#define numBones " + config.numBones + "\n";
        }

        return v7;
    }

    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    public void end() {
        this.currentMaterial = null;
        super.end();
    }

    public boolean equals(DefaultShader obj) {
        boolean v0;
        if(obj == this) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public boolean equals(Object obj) {
        boolean v0;
        if((obj instanceof DefaultShader)) {
            v0 = this.equals(((DefaultShader)obj));
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public int getDefaultCullFace() {
        int v0;
        if(this.config.defaultCullFace == -1) {
            v0 = DefaultShader.defaultCullFace;
        }
        else {
            v0 = this.config.defaultCullFace;
        }

        return v0;
    }

    public int getDefaultDepthFunc() {
        int v0;
        if(this.config.defaultDepthFunc == -1) {
            v0 = DefaultShader.defaultDepthFunc;
        }
        else {
            v0 = this.config.defaultDepthFunc;
        }

        return v0;
    }

    public static String getDefaultFragmentShader() {
        if(DefaultShader.defaultFragmentShader == null) {
            DefaultShader.defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.fragment.glsl").readString();
        }

        return DefaultShader.defaultFragmentShader;
    }

    public static String getDefaultVertexShader() {
        if(DefaultShader.defaultVertexShader == null) {
            DefaultShader.defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/shaders/default.vertex.glsl").readString();
        }

        return DefaultShader.defaultVertexShader;
    }

    public void init() {
        ShaderProgram v0 = this.program;
        this.program = null;
        this.init(v0, this.renderable);
        this.renderable = null;
        this.dirLightsLoc = this.loc(this.u_dirLights0color);
        this.dirLightsColorOffset = this.loc(this.u_dirLights0color) - this.dirLightsLoc;
        this.dirLightsDirectionOffset = this.loc(this.u_dirLights0direction) - this.dirLightsLoc;
        this.dirLightsSize = this.loc(this.u_dirLights1color) - this.dirLightsLoc;
        if(this.dirLightsSize < 0) {
            this.dirLightsSize = 0;
        }

        this.pointLightsLoc = this.loc(this.u_pointLights0color);
        this.pointLightsColorOffset = this.loc(this.u_pointLights0color) - this.pointLightsLoc;
        this.pointLightsPositionOffset = this.loc(this.u_pointLights0position) - this.pointLightsLoc;
        this.pointLightsSize = this.loc(this.u_pointLights1color) - this.pointLightsLoc;
        if(this.pointLightsSize < 0) {
            this.pointLightsSize = 0;
        }
    }

    private static final boolean or(long mask, long flag) {
        boolean v0;
        if((mask & flag) != 0) {
            v0 = true;
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public void render(Renderable renderable) {
        if(!renderable.material.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, 770, 771);
        }

        this.bindMaterial(renderable);
        if(this.lighting) {
            this.bindLights(renderable);
        }

        super.render(renderable);
    }

    public void setDefaultCullFace(int cullFace) {
        this.config.defaultCullFace = cullFace;
    }

    public void setDefaultDepthFunc(int depthFunc) {
        this.config.defaultDepthFunc = depthFunc;
    }
}

