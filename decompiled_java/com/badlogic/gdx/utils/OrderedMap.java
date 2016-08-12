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

public class OrderedMap extends ObjectMap {
    final Array keys;

    public OrderedMap() {
        super();
        this.keys = new Array();
    }

    public OrderedMap(int initialCapacity) {
        super(initialCapacity);
        this.keys = new Array(this.capacity);
    }

    public OrderedMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.keys = new Array(this.capacity);
    }

    public OrderedMap(ObjectMap arg3) {
        super(arg3);
        this.keys = new Array(this.capacity);
    }

    public void clear() {
        this.keys.clear();
        super.clear();
    }

    public void clear(int maximumCapacity) {
        this.keys.clear();
        super.clear(maximumCapacity);
    }

    public Entries entries() {
        return new Entries() {
            void advance() {
                boolean v0;
                ++this.nextIndex;
                if(this.nextIndex < this.map.size) {
                    v0 = true;
                }
                else {
                    v0 = false;
                }

                this.hasNext = v0;
            }

            public Entry next() {
                this.entry.key = OrderedMap.this.keys.get(this.nextIndex);
                this.entry.value = this.map.get(this.entry.key);
                this.advance();
                return this.entry;
            }

            public Object next() {
                return this.next();
            }

            public void remove() {
                this.map.remove(this.entry.key);
            }
        };
    }

    public Keys keys() {
        return new Keys() {
            void advance() {
                boolean v0;
                ++this.nextIndex;
                if(this.nextIndex < this.map.size) {
                    v0 = true;
                }
                else {
                    v0 = false;
                }

                this.hasNext = v0;
            }

            public Object next() {
                Object v0 = OrderedMap.this.keys.get(this.nextIndex);
                this.advance();
                return v0;
            }

            public void remove() {
                this.map.remove(OrderedMap.this.keys.get(this.nextIndex - 1));
            }
        };
    }

    public Array orderedKeys() {
        return this.keys;
    }

    public Object put(Object arg2, Object arg3) {
        if(!this.containsKey(arg2)) {
            this.keys.add(arg2);
        }

        return super.put(arg2, arg3);
    }

    public Object remove(Object arg3) {
        this.keys.removeValue(arg3, false);
        return super.remove(arg3);
    }

    public String toString() {
        String v5;
        if(this.size == 0) {
            v5 = "{}";
        }
        else {
            StringBuilder v0 = new StringBuilder(32);
            v0.append('{');
            Array v3 = this.keys;
            int v1 = 0;
            int v4 = v3.size;
            while(v1 < v4) {
                Object v2 = v3.get(v1);
                if(v1 > 0) {
                    v0.append(", ");
                }

                v0.append(v2);
                v0.append('=');
                v0.append(this.get(v2));
                ++v1;
            }

            v0.append('}');
            v5 = v0.toString();
        }

        return v5;
    }

    public Values values() {
        return new Values() {
            void advance() {
                boolean v0;
                ++this.nextIndex;
                if(this.nextIndex < this.map.size) {
                    v0 = true;
                }
                else {
                    v0 = false;
                }

                this.hasNext = v0;
            }

            public Object next() {
                Object v0 = this.map.get(OrderedMap.this.keys.get(this.nextIndex));
                this.advance();
                return v0;
            }

            public void remove() {
                this.map.remove(OrderedMap.this.keys.get(this.nextIndex - 1));
            }
        };
    }
}

