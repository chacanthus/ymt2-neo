﻿// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.utils;

public class GdxNativesLoader {
    public static boolean disableNativesLoading;
    private static boolean nativesLoaded;

    static  {
        GdxNativesLoader.disableNativesLoading = false;
    }

    public GdxNativesLoader() {
        super();
    }

    public static void load() {  // has try-catch handlers
        try {
            if(!GdxNativesLoader.nativesLoaded) {
                GdxNativesLoader.nativesLoaded = true;
                if(GdxNativesLoader.disableNativesLoading) {
                    System.out.println("Native loading is disabled.");
                }
                else {
                    new SharedLibraryLoader().load("gdx");
                }
            }
        }
        catch(Throwable v0) {
            throw v0;
        }
    }
}

