// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.esotericsoftware.tablelayout;

public abstract class Value {
    public abstract class CellValue extends Value {
        public CellValue() {
            this();
        }

        public float get(Object table) {
            throw new UnsupportedOperationException("This value can only be used for a cell property.");
        }
    }

    public class FixedValue extends Value {
        private float value;

        public FixedValue(float value) {
            this();
            this.value = value;
        }

        public float get(Cell cell) {
            return this.value;
        }

        public float get(Object table) {
            return this.value;
        }

        public void set(float value) {
            this.value = value;
        }
    }

    public abstract class TableValue extends Value {
        public TableValue() {
            this();
        }

        public float get(Cell cell) {
            return this.get(cell.getLayout().getTable());
        }
    }

    public static Value maxHeight;
    public static Value maxWidth;
    public static Value minHeight;
    public static Value minWidth;
    public static Value prefHeight;
    public static Value prefWidth;
    public static final Value zero;

    static  {
        Value.zero = new CellValue() {
            public float get(Cell cell) {
                return 0;
            }

            public float get(Object table) {
                return 0;
            }
        };
        Value.minWidth = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("minWidth can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getMinWidth(v0);
                }

                return v1;
            }
        };
        Value.minHeight = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("minHeight can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getMinHeight(v0);
                }

                return v1;
            }
        };
        Value.prefWidth = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("prefWidth can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getPrefWidth(v0);
                }

                return v1;
            }
        };
        Value.prefHeight = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("prefHeight can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getPrefHeight(v0);
                }

                return v1;
            }
        };
        Value.maxWidth = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("maxWidth can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getMaxWidth(v0);
                }

                return v1;
            }
        };
        Value.maxHeight = new CellValue() {
            public float get(Cell cell) {
                float v1;
                if(cell == null) {
                    throw new RuntimeException("maxHeight can only be set on a cell property.");
                }

                Object v0 = cell.widget;
                if(v0 == null) {
                    v1 = 0f;
                }
                else {
                    v1 = Toolkit.instance.getMaxHeight(v0);
                }

                return v1;
            }
        };
    }

    public Value() {
        super();
    }

    public abstract float get(Cell arg0);

    public abstract float get(Object arg0);

    public float height(Object table) {
        return Toolkit.instance.height(this.get(table));
    }

    public float height(Cell cell) {
        return Toolkit.instance.height(this.get(cell));
    }

    public static Value percentHeight(float percent) {
        return new TableValue() {
            public float get(Object table) {
                return Toolkit.instance.getHeight(table) * this.val$percent;
            }
        };
    }

    public static Value percentHeight(float percent, Object widget) {
        return new TableValue() {
            public float get(Object table) {
                return Toolkit.instance.getHeight(this.val$widget) * this.val$percent;
            }
        };
    }

    public static Value percentWidth(float percent) {
        return new TableValue() {
            public float get(Object table) {
                return Toolkit.instance.getWidth(table) * this.val$percent;
            }
        };
    }

    public static Value percentWidth(float percent, Object widget) {
        return new Value() {
            public float get(Cell cell) {
                return Toolkit.instance.getWidth(this.val$widget) * this.val$percent;
            }

            public float get(Object table) {
                return Toolkit.instance.getWidth(this.val$widget) * this.val$percent;
            }
        };
    }

    public float width(Object table) {
        return Toolkit.instance.width(this.get(table));
    }

    public float width(Cell cell) {
        return Toolkit.instance.width(this.get(cell));
    }
}

