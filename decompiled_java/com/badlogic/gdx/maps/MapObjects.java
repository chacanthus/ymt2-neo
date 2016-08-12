// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapObjects implements Iterable {
    private Array objects;

    public MapObjects() {
        super();
        this.objects = new Array();
    }

    public void add(MapObject object) {
        this.objects.add(object);
    }

    public MapObject get(int index) {
        return this.objects.get(index);
    }

    public MapObject get(String name) {
        Object v1;
        Iterator v0 = this.objects.iterator();
        do {
            if(v0.hasNext()) {
                v1 = v0.next();
                if(!name.equals(((MapObject)v1).getName())) {
                    continue;
                }
            }
            else {
                break;
            }

            goto label_8;
        }
        while(true);

        MapObject v1_1 = null;
    label_8:
        return ((MapObject)v1);
    }

    public Array getByType(Class arg2) {
        return this.getByType(arg2, new Array());
    }

    public Array getByType(Class arg4, Array arg5) {
        arg5.clear();
        Iterator v0 = this.objects.iterator();
        while(true) {
            if(!v0.hasNext()) {
                break;
            }

            Object v1 = v0.next();
            if(!ClassReflection.isInstance(arg4, v1)) {
                continue;
            }

            arg5.add(v1);
        }

        return arg5;
    }

    public int getCount() {
        return this.objects.size;
    }

    public Iterator iterator() {
        return this.objects.iterator();
    }

    public void remove(int index) {
        this.objects.removeIndex(index);
    }

    public void remove(MapObject object) {
        this.objects.removeValue(object, true);
    }
}

