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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

public class GLES10Shader implements Shader {
    private Camera camera;
    private RenderContext context;
    private Material currentMaterial;
    private Mesh currentMesh;
    private Texture currentTexture0;
    private Matrix4 currentTransform;
    public static int defaultCullFace;
    private final float[] lightVal;
    private final float[] oneVal4;
    private Color tmpC;
    private final float[] zeroVal4;

    static  {
        GLES10Shader.defaultCullFace = 1029;
    }

    public GLES10Shader() {
        super();
        this.lightVal = new float[]{0f, 0f, 0f, 0f};
        this.zeroVal4 = new float[]{0f, 0f, 0f, 0f};
        this.oneVal4 = new float[]{1f, 1f, 1f, 1f};
        this.tmpC = new Color();
        if(Gdx.gl10 == null) {
            throw new GdxRuntimeException("This shader requires OpenGL ES 1.x");
        }
    }

    public void begin(Camera camera, RenderContext context) {
        this.context = context;
        this.camera = camera;
        context.setDepthTest(515, 0f, 1f);
        context.setDepthMask(true);
        Gdx.gl10.glMatrixMode(5889);
        Gdx.gl10.glLoadMatrixf(camera.combined.val, 0);
        Gdx.gl10.glMatrixMode(5888);
    }

    private void bindLights(Environment lights) {
        Object v3;
        if(lights == null) {
            Gdx.gl10.glDisable(2896);
        }
        else {
            Gdx.gl10.glEnable(2896);
            if(lights.has(ColorAttribute.AmbientLight)) {
                Gdx.gl10.glLightModelfv(2899, GLES10Shader.getValues(this.lightVal, lights.get(ColorAttribute.AmbientLight).color), 0);
            }
            else {
                Gdx.gl10.glLightModelfv(2899, this.zeroVal4, 0);
            }

            Gdx.gl10.glLightfv(16384, 4609, this.zeroVal4, 0);
            int v1 = 0;
            Gdx.gl10.glPushMatrix();
            Gdx.gl10.glLoadIdentity();
            int v0 = 0;
            while(v0 < lights.directionalLights.size) {
                if(v1 >= 8) {
                    break;
                }

                v3 = lights.directionalLights.get(v0);
                Gdx.gl10.glEnable(v1 + 16384);
                Gdx.gl10.glLightfv(v1 + 16384, 4609, GLES10Shader.getValues(this.lightVal, ((DirectionalLight)v3).color), 0);
                Gdx.gl10.glLightfv(v1 + 16384, 4611, GLES10Shader.getValues(this.lightVal, -((DirectionalLight)v3).direction.x, -((DirectionalLight)v3).direction.y, -((DirectionalLight)v3).direction.z, 0f), 0);
                Gdx.gl10.glLightf(v1 + 16384, 4614, 180f);
                Gdx.gl10.glLightf(v1 + 16384, 4615, 1f);
                Gdx.gl10.glLightf(v1 + 16384, 4616, 0f);
                Gdx.gl10.glLightf(v1 + 16384, 4617, 0f);
                ++v1;
                ++v0;
            }

            v0 = 0;
            while(v0 < lights.pointLights.size) {
                if(v1 >= 8) {
                    break;
                }

                Gdx.gl10.glEnable(v1 + 16384);
                v3 = lights.pointLights.get(v0);
                Gdx.gl10.glLightfv(v1 + 16384, 4609, GLES10Shader.getValues(this.lightVal, ((PointLight)v3).color), 0);
                Gdx.gl10.glLightfv(v1 + 16384, 4611, GLES10Shader.getValues(this.lightVal, ((PointLight)v3).position.x, ((PointLight)v3).position.y, ((PointLight)v3).position.z, 1f), 0);
                Gdx.gl10.glLightf(v1 + 16384, 4614, 180f);
                Gdx.gl10.glLightf(v1 + 16384, 4615, 0f);
                Gdx.gl10.glLightf(v1 + 16384, 4616, 0f);
                Gdx.gl10.glLightf(v1 + 16384, 4617, 1f / ((PointLight)v3).intensity);
                ++v1;
                ++v0;
            }

            int v2;
            for(v2 = v1; v2 < 8; ++v2) {
                Gdx.gl10.glDisable(v2 + 16384);
            }

            Gdx.gl10.glPopMatrix();
        }
    }

    public boolean canRender(Renderable renderable) {
        return 1;
    }

    public int compareTo(Shader other) {
        return 0;
    }

    public void dispose() {
    }

    public void end() {
        Mesh v1 = null;
        if(this.currentMesh != null) {
            this.currentMesh.unbind();
        }

        this.currentMesh = v1;
        if(this.currentTransform != null) {
            Gdx.gl10.glPopMatrix();
        }

        this.currentTransform = ((Matrix4)v1);
        this.currentTexture0 = ((Texture)v1);
        this.currentMaterial = ((Material)v1);
        Gdx.gl10.glDisable(2896);
    }

    public boolean equals(GLES10Shader obj) {
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
        if((obj instanceof GLES10Shader)) {
            v0 = this.equals(((GLES10Shader)obj));
        }
        else {
            v0 = false;
        }

        return v0;
    }

    private static final float[] getValues(float[] out, Color color) {
        return GLES10Shader.getValues(out, color.r, color.g, color.b, color.a);
    }

    private static final float[] getValues(float[] out, float v0, float v1, float v2, float v3) {
        out[0] = v0;
        out[1] = v1;
        out[2] = v2;
        out[3] = v3;
        return out;
    }

    public void init() {
    }

    public void render(Renderable renderable) {
        int v10 = 3553;
        int v13 = 2903;
        int v12 = 1032;
        float v9 = 1f;
        this.tmpC.set(v9, v9, v9, v9);
        int v3 = 0;
        if(this.currentMaterial != renderable.material) {
            this.currentMaterial = renderable.material;
            if(!this.currentMaterial.has(BlendingAttribute.Type)) {
                this.context.setBlending(false, 770, 771);
            }

            if(!this.currentMaterial.has(ColorAttribute.Diffuse)) {
                Gdx.gl10.glColor4f(v9, v9, v9, v9);
                if(renderable.environment != null) {
                    Gdx.gl10.glDisable(v13);
                }
            }

            if(!this.currentMaterial.has(TextureAttribute.Diffuse)) {
                Gdx.gl10.glDisable(v10);
            }

            int v2 = GLES10Shader.defaultCullFace;
            Iterator v4 = this.currentMaterial.iterator();
            while(true) {
                if(!v4.hasNext()) {
                    break;
                }

                Object v1 = v4.next();
                if(((Attribute)v1).type != BlendingAttribute.Type) {
                    goto label_58;
                }

                this.context.setBlending(true, v1.sourceFunction, v1.destFunction);
                v3 = 1;
                this.tmpC.a = ((BlendingAttribute)v1).opacity;
                continue;
            label_58:
                if(((Attribute)v1).type != ColorAttribute.Diffuse) {
                    goto label_70;
                }

                float v0 = this.tmpC.a;
                this.tmpC.set(((ColorAttribute)v1).color);
                this.tmpC.a = v0;
                v3 = 1;
                continue;
            label_70:
                if(((Attribute)v1).type != TextureAttribute.Diffuse) {
                    goto label_91;
                }

                TextureDescriptor v5 = ((TextureAttribute)v1).textureDescription;
                if(this.currentTexture0 != v5.texture) {
                    GLTexture v6 = v5.texture;
                    this.currentTexture0 = ((Texture)v6);
                    ((Texture)v6).bind(0);
                }

                this.currentTexture0.unsafeSetFilter(v5.minFilter, v5.magFilter);
                this.currentTexture0.unsafeSetWrap(v5.uWrap, v5.vWrap);
                Gdx.gl10.glEnable(v10);
                continue;
            label_91:
                if((((Attribute)v1).type & IntAttribute.CullFace) != IntAttribute.CullFace) {
                    continue;
                }

                v2 = ((IntAttribute)v1).value;
            }

            this.context.setCullFace(v2);
        }

        if(v3 != 0) {
            Gdx.gl10.glColor4f(this.tmpC.r, this.tmpC.g, this.tmpC.b, this.tmpC.a);
            if(renderable.environment != null) {
                Gdx.gl10.glEnable(v13);
                Gdx.gl10.glMaterialfv(v12, 4608, GLES10Shader.getValues(this.lightVal, this.tmpC), 0);
                Gdx.gl10.glMaterialfv(v12, 4609, GLES10Shader.getValues(this.lightVal, this.tmpC), 0);
            }
        }

        if(this.currentTransform != renderable.worldTransform) {
            if(this.currentTransform != null) {
                Gdx.gl10.glPopMatrix();
            }

            this.currentTransform = renderable.worldTransform;
            Gdx.gl10.glPushMatrix();
            Gdx.gl10.glLoadMatrixf(this.currentTransform.val, 0);
        }

        this.bindLights(renderable.environment);
        if(this.currentMesh != renderable.mesh) {
            if(this.currentMesh != null) {
                this.currentMesh.unbind();
            }

            Mesh v6_1 = renderable.mesh;
            this.currentMesh = v6_1;
            v6_1.bind();
        }

        renderable.mesh.render(renderable.primitiveType, renderable.meshPartOffset, renderable.meshPartSize);
    }
}

