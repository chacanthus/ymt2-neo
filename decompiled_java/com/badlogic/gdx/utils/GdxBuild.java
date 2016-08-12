// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.utils;

import com.badlogic.gdx.jnigen.AntScriptGenerator;
import com.badlogic.gdx.jnigen.BuildConfig;
import com.badlogic.gdx.jnigen.BuildTarget;
import com.badlogic.gdx.jnigen.BuildTarget$TargetOs;
import com.badlogic.gdx.jnigen.NativeCodeGenerator;

public class GdxBuild {
    public GdxBuild() {
        super();
    }

    public static void main(String[] args) throws Exception {
        NativeCodeGenerator v1 = new NativeCodeGenerator();
        String[] v5 = new String[1];
        v5[0] = "**/*";
        v1.generate("src", "bin", "jni", v5, null);
        NativeCodeGenerator v5_1 = new NativeCodeGenerator();
        String v8 = "jni" + "/iosgl";
        String[] v9 = new String[1];
        v9[0] = "**/IOSGLES10.java";
        v5_1.generate("../backends/gdx-backend-robovm/src", "../backends/gdx-backend-robovm/bin", v8, v9, null);
        String[] v13 = new String[2];
        v13[0] = "android/**";
        v13[1] = "iosgl/**";
        BuildTarget v19 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Windows, false);
        v19.compilerPrefix = "";
        v19.buildFileName = "build-windows32home.xml";
        v19.excludeFromMasterBuildFile = true;
        v19.cppExcludes = v13;
        BuildTarget v18 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Windows, false);
        v18.cppExcludes = v13;
        BuildTarget v20 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Windows, true);
        v20.cppExcludes = v13;
        BuildTarget v15 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Linux, false);
        v15.cppExcludes = v13;
        BuildTarget v16 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Linux, true);
        v16.cppExcludes = v13;
        BuildTarget v12 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.Android, false);
        v12.linkerFlags = v12.linkerFlags + " -lGLESv2 -llog";
        String[] v1_1 = new String[1];
        v1_1[0] = "iosgl/**";
        v12.cppExcludes = v1_1;
        BuildTarget v17 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.MacOsX, false);
        v17.cppExcludes = v13;
        BuildTarget v14 = BuildTarget.newDefaultTarget(BuildTarget$TargetOs.IOS, false);
        v1_1 = new String[1];
        v1_1[0] = "android/**";
        v14.cppExcludes = v1_1;
        v1_1 = new String[1];
        v1_1[0] = "iosgl";
        v14.headerDirs = v1_1;
        AntScriptGenerator v1_2 = new AntScriptGenerator();
        BuildConfig v2 = new BuildConfig("gdx", "../target/native", "libs", "jni");
        BuildTarget[] v3 = new BuildTarget[8];
        v3[0] = v17;
        v3[1] = v19;
        v3[2] = v18;
        v3[3] = v20;
        v3[4] = v15;
        v3[5] = v16;
        v3[6] = v12;
        v3[7] = v14;
        v1_2.generate(v2, v3);
    }
}

