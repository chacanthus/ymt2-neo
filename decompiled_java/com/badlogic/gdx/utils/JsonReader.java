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

import com.badlogic.gdx.files.FileHandle;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonReader implements BaseJsonReader {
    private static final byte[] _json_actions = null;
    private static final byte[] _json_eof_actions = null;
    private static final short[] _json_index_offsets = null;
    private static final short[] _json_key_offsets = null;
    private static final byte[] _json_range_lengths = null;
    private static final byte[] _json_single_lengths = null;
    private static final byte[] _json_trans_actions = null;
    private static final char[] _json_trans_keys = null;
    private static final byte[] _json_trans_targs = null;
    private JsonValue current;
    private final Array elements;
    static final int json_en_array = 46;
    static final int json_en_main = 1;
    static final int json_en_object = 8;
    static final int json_error = 0;
    static final int json_first_final = 72;
    static final int json_start = 1;
    private final Array lastChild;
    private JsonValue root;

    static  {
        JsonReader._json_actions = JsonReader.init__json_actions_0();
        JsonReader._json_key_offsets = JsonReader.init__json_key_offsets_0();
        JsonReader._json_trans_keys = JsonReader.init__json_trans_keys_0();
        JsonReader._json_single_lengths = JsonReader.init__json_single_lengths_0();
        JsonReader._json_range_lengths = JsonReader.init__json_range_lengths_0();
        JsonReader._json_index_offsets = JsonReader.init__json_index_offsets_0();
        JsonReader._json_trans_targs = JsonReader.init__json_trans_targs_0();
        JsonReader._json_trans_actions = JsonReader.init__json_trans_actions_0();
        JsonReader._json_eof_actions = JsonReader.init__json_eof_actions_0();
    }

    public JsonReader() {
        super();
        this.elements = new Array(8);
        this.lastChild = new Array(8);
    }

    private void addChild(String name, JsonValue child) {
        child.setName(name);
        if(this.current == null) {
            this.current = child;
            this.root = child;
        }
        else {
            if(!this.current.isArray() && !this.current.isObject()) {
                this.root = this.current;
                return;
            }

            if(this.current.size == 0) {
                this.current.child = child;
            }
            else {
                Object v0 = this.lastChild.pop();
                ((JsonValue)v0).next = child;
                child.prev = ((JsonValue)v0);
            }

            this.lastChild.add(child);
            ++this.current.size;
        }
    }

    protected void bool(String name, boolean value) {
        this.addChild(name, new JsonValue(value));
    }

    private static byte[] init__json_actions_0() {
        return new byte[]{0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 9, 1, 10, 1, 11, 1, 12, 2, 0, 2, 2, 0, 3, 2, 3, 10, 2, 3, 12, 2, 4, 10, 2, 4, 12, 2, 5, 10, 2, 5, 12, 2, 6, 3, 2, 7, 3, 2, 8, 3, 3, 6, 3, 10, 3, 6, 3, 12, 3, 7, 3, 10, 3, 7, 3, 12, 3, 8, 3, 10, 3, 8, 3, 12};
    }

    private static byte[] init__json_eof_actions_0() {
        return new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 11, 9, 9, 7, 7, 7, 7, 48, 7, 7, 7, 51, 7, 7, 7, 45, 0, 0};
    }

    private static short[] init__json_index_offsets_0() {
        return new short[]{0, 0, 15, 18, 21, 30, 32, 36, 38, 50, 53, 56, 60, 75, 78, 81, 86, 97, 104, 106, 109, 116, 120, 122, 127, 136, 143, 145, 152, 160, 164, 166, 172, 180, 188, 196, 204, 211, 219, 227, 235, 242, 250, 258, 266, 273, 282, 299, 302, 305, 310, 326, 333, 335, 342, 350, 354, 356, 362, 370, 378, 386, 394, 401, 409, 417, 425, 432, 440, 448, 456, 463, 472, 475, 482, 487, 493, 497, 505, 513, 521, 529, 536, 544, 552, 560, 567, 575, 583, 591, 598, 599};
    }

    private static short[] init__json_key_offsets_0() {
        return new short[]{0, 0, 18, 20, 22, 31, 33, 37, 39, 54, 56, 58, 62, 80, 82, 84, 89, 103, 110, 112, 115, 123, 127, 129, 135, 144, 151, 153, 161, 170, 174, 176, 183, 191, 199, 207, 215, 222, 230, 238, 246, 253, 261, 269, 277, 284, 293, 313, 315, 317, 322, 341, 348, 350, 358, 367, 371, 373, 380, 388, 396, 404, 412, 419, 427, 435, 443, 450, 458, 466, 474, 481, 490, 493, 500, 506, 513, 518, 526, 534, 542, 550, 557, 565, 573, 581, 588, 596, 604, 612, 619, 619};
    }

    private static byte[] init__json_range_lengths_0() {
        return new byte[]{0, 4, 0, 0, 1, 1, 1, 1, 4, 0, 0, 1, 4, 0, 0, 1, 4, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 0, 0, 1, 4, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0};
    }

    private static byte[] init__json_single_lengths_0() {
        return new byte[]{0, 10, 2, 2, 7, 0, 2, 0, 7, 2, 2, 2, 10, 2, 2, 3, 6, 5, 0, 1, 4, 2, 0, 2, 7, 5, 0, 4, 5, 2, 0, 3, 6, 6, 6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 7, 12, 2, 2, 3, 11, 5, 0, 4, 5, 2, 0, 3, 6, 6, 6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 7, 1, 5, 2, 3, 1, 6, 6, 6, 6, 5, 6, 6, 6, 5, 6, 6, 6, 5, 0, 0};
    }

    private static byte[] init__json_trans_actions_0() {
        return new byte[]{0, 0, 1, 1, 17, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 24, 1, 1, 7, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 15, 0, 1, 1, 1, 0, 21, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 17, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 24, 1, 1, 7, 0, 0, 0, 0, 15, 0, 0, 0, 0, 1, 1, 1, 15, 0, 1, 1, 1, 0, 5, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 7, 7, 0, 0, 27, 7, 0, 0, 0, 11, 11, 0, 39, 11, 0, 0, 9, 9, 0, 0, 33, 9, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 33, 9, 0, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 48, 48, 0, 0, 62, 48, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 51, 51, 0, 0, 70, 51, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 7, 7, 0, 0, 0, 27, 7, 0, 45, 45, 0, 0, 54, 45, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 1, 0, 1, 17, 19, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 24, 1, 1, 7, 0, 0, 0, 0, 19, 0, 0, 0, 0, 1, 1, 17, 19, 1, 1, 1, 1, 13, 0, 1, 1, 1, 0, 7, 7, 0, 30, 0, 7, 0, 0, 0, 11, 11, 0, 42, 11, 0, 0, 9, 9, 0, 36, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 36, 9, 0, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 48, 48, 0, 66, 0, 48, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 51, 51, 0, 74, 0, 51, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 7, 7, 0, 30, 0, 0, 7, 0, 45, 45, 0, 58, 0, 45, 0, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 7, 0, 0, 0, 0, 7, 0, 11, 0, 11, 0, 0, 9, 0, 0, 9, 0, 0, 9, 9, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 48, 0, 0, 0, 0, 48, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 51, 0, 0, 0, 0, 51, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 7, 0, 45, 0, 0, 0, 0, 45, 0, 0, 0, 0};
    }

    private static char[] init__json_trans_keys_0() {
        return new char[]{' ', '\"', '$', '-', '[', '_', 'f', 'n', 't', '{', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', '\"', '\\', '\"', '\\', '\"', '/', '\\', 'b', 'f', 'n', 'r', 't', 'u', '0', '9', '+', '-', '0', '9', '0', '9', ' ', '\"', '$', ',', '-', '_', '}', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', '\"', '\\', '\"', '\\', ' ', ':', '\t', '\r', ' ', '\"', '$', '-', '[', '_', 'f', 'n', 't', '{', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', '\"', '\\', '\"', '\\', ' ', ',', '}', '\t', '\r', ' ', '\"', '$', '-', '_', '}', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', ' ', ',', ':', ']', '}', '\t', '\r', '0', '9', '.', '0', '9', ' ', ':', 'E', 'e', '\t', '\r', '0', '9', '+', '-', '0', '9', '0', '9', ' ', ':', '\t', '\r', '0', '9', '\"', '/', '\\', 'b', 'f', 'n', 'r', 't', 'u', ' ', ',', ':', ']', '}', '\t', '\r', '0', '9', ' ', ',', '.', '}', '\t', '\r', '0', '9', ' ', ',', 'E', 'e', '}', '\t', '\r', '0', '9', '+', '-', '0', '9', '0', '9', ' ', ',', '}', '\t', '\r', '0', '9', ' ', ',', ':', ']', 'a', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 's', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'r', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', '\"', '/', '\\', 'b', 'f', 'n', 'r', 't', 'u', ' ', '\"', '$', ',', '-', '[', ']', '_', 'f', 'n', 't', '{', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', '\"', '\\', '\"', '\\', ' ', ',', ']', '\t', '\r', ' ', '\"', '$', '-', '[', ']', '_', 'f', 'n', 't', '{', '\t', '\r', '0', '9', 'A', 'Z', 'a', 'z', ' ', ',', ':', ']', '}', '\t', '\r', '0', '9', ' ', ',', '.', ']', '\t', '\r', '0', '9', ' ', ',', 'E', ']', 'e', '\t', '\r', '0', '9', '+', '-', '0', '9', '0', '9', ' ', ',', ']', '\t', '\r', '0', '9', ' ', ',', ':', ']', 'a', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 's', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'r', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', '\"', '/', '\\', 'b', 'f', 'n', 'r', 't', 'u', ' ', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', '.', '\t', '\r', '0', '9', ' ', 'E', 'e', '\t', '\r', '0', '9', ' ', '\t', '\r', '0', '9', ' ', ',', ':', ']', 'a', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 's', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', 'l', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', ' ', ',', ':', ']', 'r', '}', '\t', '\r', ' ', ',', ':', ']', 'u', '}', '\t', '\r', ' ', ',', ':', ']', 'e', '}', '\t', '\r', ' ', ',', ':', ']', '}', '\t', '\r', '\u0000'};
    }

    private static byte[] init__json_trans_targs_0() {
        return new byte[]{1, 2, 73, 5, 72, 73, 77, 82, 86, 72, 1, 74, 73, 73, 0, 72, 4, 3, 72, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 74, 0, 7, 7, 76, 0, 76, 0, 8, 9, 17, 16, 18, 17, 90, 8, 17, 17, 17, 0, 11, 45, 10, 11, 45, 10, 11, 12, 11, 0, 12, 13, 25, 26, 15, 25, 32, 37, 41, 15, 12, 27, 25, 25, 0, 15, 24, 14, 15, 24, 14, 15, 16, 90, 15, 0, 16, 9, 17, 18, 17, 90, 16, 17, 17, 17, 0, 11, 0, 12, 0, 0, 11, 17, 19, 0, 20, 19, 0, 11, 12, 21, 21, 11, 20, 0, 22, 22, 23, 0, 23, 0, 11, 12, 11, 23, 0, 14, 14, 14, 14, 14, 14, 14, 14, 0, 15, 16, 0, 0, 90, 15, 25, 27, 0, 15, 16, 28, 90, 15, 27, 0, 15, 16, 29, 29, 90, 15, 28, 0, 30, 30, 31, 0, 31, 0, 15, 16, 90, 15, 31, 0, 15, 16, 0, 0, 33, 90, 15, 25, 15, 16, 0, 0, 34, 90, 15, 25, 15, 16, 0, 0, 35, 90, 15, 25, 15, 16, 0, 0, 36, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 15, 16, 0, 0, 38, 90, 15, 25, 15, 16, 0, 0, 39, 90, 15, 25, 15, 16, 0, 0, 40, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 15, 16, 0, 0, 42, 90, 15, 25, 15, 16, 0, 0, 43, 90, 15, 25, 15, 16, 0, 0, 44, 90, 15, 25, 15, 16, 0, 0, 90, 15, 25, 10, 10, 10, 10, 10, 10, 10, 10, 0, 46, 47, 51, 50, 52, 49, 91, 51, 58, 63, 67, 49, 46, 53, 51, 51, 0, 49, 71, 48, 49, 71, 48, 49, 50, 91, 49, 0, 50, 47, 51, 52, 49, 91, 51, 58, 63, 67, 49, 50, 53, 51, 51, 0, 49, 50, 0, 91, 0, 49, 51, 53, 0, 49, 50, 54, 91, 49, 53, 0, 49, 50, 55, 91, 55, 49, 54, 0, 56, 56, 57, 0, 57, 0, 49, 50, 91, 49, 57, 0, 49, 50, 0, 91, 59, 0, 49, 51, 49, 50, 0, 91, 60, 0, 49, 51, 49, 50, 0, 91, 61, 0, 49, 51, 49, 50, 0, 91, 62, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 49, 50, 0, 91, 64, 0, 49, 51, 49, 50, 0, 91, 65, 0, 49, 51, 49, 50, 0, 91, 66, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 49, 50, 0, 91, 68, 0, 49, 51, 49, 50, 0, 91, 69, 0, 49, 51, 49, 50, 0, 91, 70, 0, 49, 51, 49, 50, 0, 91, 0, 49, 51, 48, 48, 48, 48, 48, 48, 48, 48, 0, 72, 72, 0, 72, 0, 0, 0, 0, 72, 73, 72, 75, 72, 74, 0, 72, 6, 6, 72, 75, 0, 72, 72, 76, 0, 72, 0, 0, 0, 78, 0, 72, 73, 72, 0, 0, 0, 79, 0, 72, 73, 72, 0, 0, 0, 80, 0, 72, 73, 72, 0, 0, 0, 81, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 72, 0, 0, 0, 83, 0, 72, 73, 72, 0, 0, 0, 84, 0, 72, 73, 72, 0, 0, 0, 85, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 72, 0, 0, 0, 87, 0, 72, 73, 72, 0, 0, 0, 88, 0, 72, 73, 72, 0, 0, 0, 89, 0, 72, 73, 72, 0, 0, 0, 0, 72, 73, 0, 0, 0};
    }

    protected void number(String name, double value) {
        this.addChild(name, new JsonValue(value));
    }

    protected void number(String name, long value) {
        this.addChild(name, new JsonValue(value));
    }

    public JsonValue parse(FileHandle file) {  // has try-catch handlers
        try {
            return this.parse(file.reader("UTF-8"));
        }
        catch(Exception v0) {
            throw new SerializationException("Error parsing file: " + file, ((Throwable)v0));
        }
    }

    public JsonValue parse(InputStream input) {  // has try-catch handlers
        JsonValue v1_1;
        try {
            v1_1 = this.parse(new InputStreamReader(input, "UTF-8"));
        }
        catch(Throwable v1) {
        }
        catch(IOException v0) {
            try {
                throw new SerializationException(((Throwable)v0));
            }
            catch(Throwable v1) {
                StreamUtils.closeQuietly(((Closeable)input));
                throw v1;
            }
        }

        StreamUtils.closeQuietly(((Closeable)input));
        return v1_1;
    }

    public JsonValue parse(Reader reader) {  // has try-catch handlers
        JsonValue v5_2;
        int v2;
        int v4;
        char[] v0;
        int v5 = 1024;
        try {
            v0 = new char[v5];
            v4 = 0;
            while(true) {
            label_3:
                v2 = reader.read(v0, v4, v0.length - v4);
                if(v2 != -1) {
                    break;
                }

                goto label_8;
            }
        }
        catch(IOException v1) {
            goto label_25;
        }
        catch(Throwable v5_1) {
            goto label_28;
        }

        if(v2 != 0) {
            goto label_22;
        }

        try {
            char[] v3 = new char[v0.length * 2];
            System.arraycopy(v0, 0, v3, 0, v0.length);
            v0 = v3;
            goto label_3;
        }
        catch(IOException v1) {
            goto label_25;
        }
        catch(Throwable v5_1) {
            goto label_28;
        }

    label_22:
        v4 += v2;
        goto label_3;
        try {
        label_8:
            v5_2 = this.parse(v0, 0, v4);
        }
        catch(IOException v1) {
            goto label_25;
        }
        catch(Throwable v5_1) {
            goto label_28;
        }

        StreamUtils.closeQuietly(((Closeable)reader));
        return v5_2;
        try {
        label_25:
            throw new SerializationException(((Throwable)v1));
        }
        catch(Throwable v5_1) {
        label_28:
            StreamUtils.closeQuietly(((Closeable)reader));
            throw v5_1;
        }
    }

    public JsonValue parse(String json) {
        char[] v0 = json.toCharArray();
        return this.parse(v0, 0, v0.length);
    }

    public JsonValue parse(char[] data, int offset, int length) {  // has try-catch handlers
        int[] v31;
        String v28_1;
        Object v28;
        String v40;
        int v9;
        int v16;
        int v17;
        int v10;
        int v15;
        int v19;
        int v14;
        int v32 = offset;
        int v34 = length;
        int v24 = v34;
        int[] v37 = new int[4];
        int v36 = 0;
        Array v29 = new Array(8);
        int v30 = 0;
        int v22 = 0;
        RuntimeException v33 = null;
        if(0 != 0) {
            System.out.println();
        }

        int v20 = 1;
        int v11 = 0;
        int v39 = 0;
        while(true) {
        label_26:
            switch(v11) {
                case 0: {
                    goto label_59;
                }
                case 1: {
                    goto label_67;
                }
                case 2: {
                    goto label_510;
                }
                case 4: {
                    goto label_519;
                }
            }

            goto label_28;
        label_59:
            if(v32 != v34) {
                goto label_64;
            }

            v11 = 4;
            continue;
        label_64:
            if(v20 != 0) {
                goto label_67;
            }

            v11 = 5;
        }

        try {
        label_67:
            int v12 = JsonReader._json_key_offsets[v20];
            int v18 = JsonReader._json_index_offsets[v20];
            int v13 = JsonReader._json_single_lengths[v20];
            if(v13 > 0) {
                v14 = v12;
                v19 = v12 + v13 - 1;
                while(true) {
                    if(v19 >= v14) {
                        v15 = v14 + (v19 - v14 >> 1);
                        if(data[v32] < JsonReader._json_trans_keys[v15]) {
                            v19 = v15 - 1;
                            continue;
                        }
                        else if(data[v32] > JsonReader._json_trans_keys[v15]) {
                            v14 = v15 + 1;
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        goto label_79;
                    }

                    goto label_91;
                }

                v18 += v15 - v12;
                goto label_91;
            label_79:
                v12 += v13;
                v18 += v13;
                goto label_81;
            }
            else {
            label_81:
                v13 = JsonReader._json_range_lengths[v20];
                if(v13 > 0) {
                    v14 = v12;
                    v19 = (v13 << 1) + v12 - 2;
                    while(true) {
                        if(v19 >= v14) {
                            v15 = v14 + (v19 - v14 >> 1 & -2);
                            if(data[v32] < JsonReader._json_trans_keys[v15]) {
                                v19 = v15 - 2;
                                continue;
                            }
                            else if(data[v32] > JsonReader._json_trans_keys[v15 + 1]) {
                                v14 = v15 + 2;
                                continue;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            goto label_90;
                        }

                        goto label_91;
                    }

                    v18 += v15 - v12 >> 1;
                    goto label_91;
                label_90:
                    v18 += v13;
                }
            }

        label_91:
            v20 = JsonReader._json_trans_targs[v18];
            if(JsonReader._json_trans_actions[v18] == 0) {
                goto label_510;
            }

            v10 = JsonReader._json_trans_actions[v18] + 1;
            v17 = JsonReader._json_actions[JsonReader._json_trans_actions[v18]];
            while(true) {
            label_102:
                v16 = v17 - 1;
                if(v17 <= 0) {
                    goto label_510;
                }

                v9 = v10 + 1;
                switch(JsonReader._json_actions[v10]) {
                    case 0: {
                        goto label_158;
                    }
                    case 1: {
                        goto label_162;
                    }
                    case 2: {
                        goto label_164;
                    }
                    case 3: {
                        goto label_194;
                    }
                    case 4: {
                        goto label_235;
                    }
                    case 5: {
                        goto label_271;
                    }
                    case 6: {
                        goto label_307;
                    }
                    case 7: {
                        goto label_334;
                    }
                    case 8: {
                        goto label_361;
                    }
                    case 9: {
                        goto label_386;
                    }
                    case 10: {
                        goto label_438;
                    }
                    case 11: {
                        goto label_448;
                    }
                    case 12: {
                        goto label_500;
                    }
                }

                goto label_108;
            label_162:
                v30 = 1;
                goto label_108;
            label_194:
                if(v22 != 0) {
                    goto label_108;
                }

                v40 = new String(data, v36, v32 - v36);
                v36 = v32;
                if(v30 != 0) {
                    v40 = this.unescape(v40);
                }

                if(v29.size > 0) {
                    v28 = v29.pop();
                }
                else {
                    v28_1 = null;
                }

                if(0 != 0) {
                    System.out.println("string: " + v28_1 + "=" + v40);
                }

                this.string(v28_1, v40);
                goto label_108;
            label_386:
                if(v29.size > 0) {
                    v28 = v29.pop();
                }
                else {
                    break;
                }

                goto label_392;
            }
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

        v28_1 = null;
        try {
        label_392:
            if(0 != 0) {
                System.out.println("startObject: " + v28_1);
            }

            this.startObject(v28_1);
            if(v39 == v37.length) {
                v31 = new int[v37.length * 2];
                System.arraycopy(v37, 0, v31, 0, v37.length);
                v37 = v31;
            }
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

        int v38 = v39 + 1;
        try {
            v37[v39] = v20;
            v20 = 8;
            v11 = 2;
            v39 = v38;
            goto label_26;
        }
        catch(RuntimeException v25) {
            goto label_794;
        }

        try {
        label_164:
            v28_1 = new String(data, v36, v32 - v36);
            v36 = v32;
            if(v30 != 0) {
                v28_1 = this.unescape(v28_1);
            }

            if(0 != 0) {
                System.out.println("name: " + v28_1);
            }

            v29.add(v28_1);
            goto label_108;
        label_361:
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                v28_1 = null;
            }

            if(0 != 0) {
                System.out.println("null: " + v28_1);
            }

            this.string(v28_1, null);
            v22 = 1;
            goto label_108;
        label_235:
            v40 = new String(data, v36, v32 - v36);
            v36 = v32;
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                v28_1 = null;
            }

            if(0 != 0) {
                System.out.println("double: " + v28_1 + "=" + Double.parseDouble(v40));
            }

            this.number(v28_1, Double.parseDouble(v40));
            goto label_108;
        label_334:
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                v28_1 = null;
            }

            if(0 != 0) {
                System.out.println("boolean: " + v28_1 + "=false");
            }

            this.bool(v28_1, false);
            v22 = 1;
            goto label_108;
        label_271:
            v40 = new String(data, v36, v32 - v36);
            v36 = v32;
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                v28_1 = null;
            }

            if(0 != 0) {
                System.out.println("long: " + v28_1 + "=" + Long.parseLong(v40));
            }

            this.number(v28_1, Long.parseLong(v40));
            goto label_108;
        label_307:
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                v28_1 = null;
            }

            if(0 != 0) {
                System.out.println("boolean: " + v28_1 + "=true");
            }

            this.bool(v28_1, true);
            v22 = 1;
            goto label_108;
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

    label_500:
        if(0 != 0) {
            try {
                System.out.println("endArray");
            label_504:
                this.pop();
                v38 = v39 - 1;
                goto label_506;
            }
            catch(RuntimeException v25) {
                goto label_192;
            }
        }

        goto label_504;
        try {
        label_506:
            v20 = v37[v38];
            v11 = 2;
            v39 = v38;
            goto label_26;
        }
        catch(RuntimeException v25) {
            goto label_794;
        }

    label_438:
        if(0 != 0) {
            try {
                System.out.println("endObject");
            label_442:
                this.pop();
                v38 = v39 - 1;
                goto label_444;
            }
            catch(RuntimeException v25) {
                goto label_192;
            }
        }

        goto label_442;
        try {
        label_444:
            v20 = v37[v38];
            v11 = 2;
            v39 = v38;
            goto label_26;
        }
        catch(RuntimeException v25) {
            goto label_794;
        }

        try {
        label_158:
            v36 = v32;
            v30 = 0;
            v22 = 0;
        label_108:
            v17 = v16;
            v10 = v9;
            goto label_102;
        label_448:
            if(v29.size > 0) {
                v28 = v29.pop();
            }
            else {
                goto label_498;
            }

            goto label_454;
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

    label_498:
        v28_1 = null;
        try {
        label_454:
            if(0 != 0) {
                System.out.println("startArray: " + v28_1);
            }

            this.startArray(v28_1);
            if(v39 == v37.length) {
                v31 = new int[v37.length * 2];
                System.arraycopy(v37, 0, v31, 0, v37.length);
                v37 = v31;
            }
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

        v38 = v39 + 1;
        try {
            v37[v39] = v20;
            v20 = 46;
            v11 = 2;
            v39 = v38;
            goto label_26;
        }
        catch(RuntimeException v25) {
        label_794:
            goto label_192;
        }

    label_510:
        if(v20 != 0) {
            goto label_513;
        }

        v11 = 5;
        goto label_26;
    label_513:
        ++v32;
        if(v32 == v34) {
            goto label_519;
        }

        v11 = 1;
        goto label_26;
    label_519:
        if(v32 != v24) {
            goto label_28;
        }

        try {
            int v6 = JsonReader._json_eof_actions[v20] + 1;
            int v8 = JsonReader._json_actions[JsonReader._json_eof_actions[v20]];
            while(true) {
            label_528:
                int v7 = v8 - 1;
                if(v8 <= 0) {
                    break;
                }

                int v5 = v6 + 1;
                switch(JsonReader._json_actions[v6]) {
                    case 3: {
                        if(v22 != 0) {
                            goto label_534;
                        }

                        v40 = new String(data, v36, v32 - v36);
                        v36 = v32;
                        if(v30 != 0) {
                            v40 = this.unescape(v40);
                        }

                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        if(0 != 0) {
                            System.out.println("string: " + v28 + "=" + v40);
                        }

                        this.string(v28, v40);
                        goto label_534;
                    }
                    case 4: {
                        v40 = new String(data, v36, v32 - v36);
                        v36 = v32;
                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        if(0 != 0) {
                            System.out.println("double: " + v28 + "=" + Double.parseDouble(v40));
                        }

                        this.number(v28, Double.parseDouble(v40));
                        goto label_534;
                    }
                    case 5: {
                        v40 = new String(data, v36, v32 - v36);
                        v36 = v32;
                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        if(0 != 0) {
                            System.out.println("long: " + v28 + "=" + Long.parseLong(v40));
                        }

                        this.number(v28, Long.parseLong(v40));
                        goto label_534;
                    }
                    case 6: {
                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        if(0 != 0) {
                            System.out.println("boolean: " + v28 + "=true");
                        }

                        this.bool(v28, true);
                        v22 = 1;
                        goto label_534;
                    }
                    case 7: {
                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        if(0 != 0) {
                            System.out.println("boolean: " + v28 + "=false");
                        }

                        this.bool(v28, false);
                        v22 = 1;
                        goto label_534;
                    }
                    case 8: {
                        if(v29.size > 0) {
                            v28 = v29.pop();
                        }
                        else {
                            v28 = null;
                        }

                        try {
                            if(0 != 0) {
                                System.out.println("null: " + v28);
                            }

                            this.string(v28, null);
                            v22 = 1;
                        label_534:
                            v8 = v7;
                            v6 = v5;
                            goto label_528;
                        label_192:
                            v33 = v25;
                            goto label_28;
                        }
                        catch(RuntimeException v25) {
                            goto label_192;
                        }
                    }
                }
            }
        }
        catch(RuntimeException v25) {
            goto label_192;
        }

    label_28:
        JsonValue v35 = this.root;
        this.root = null;
        this.current = null;
        this.lastChild.clear();
        if(v32 >= v34) {
            goto label_752;
        }

        int v27 = 1;
        int v26 = 0;
        goto label_48;
    label_752:
        if(this.elements.size != 0) {
            Object v23 = this.elements.peek();
            this.elements.clear();
            if(v23 != null && (((JsonValue)v23).isObject())) {
                throw new SerializationException("Error parsing JSON, unmatched brace.");
            }

            throw new SerializationException("Error parsing JSON, unmatched bracket.");
        }

        if(v33 != null) {
            throw new SerializationException("Error parsing JSON: " + new String(data), v33);
        }

        return v35;
        while(true) {
        label_48:
            if(v26 >= v32) {
                break;
            }

            if(data[v26] == 10) {
                ++v27;
            }

            ++v26;
        }

        throw new SerializationException("Error parsing JSON on line " + v27 + " near: " + new String(data, v32, v34 - v32), v33);
    }

    protected void pop() {
        JsonValue v0_1;
        this.root = this.elements.pop();
        if(this.current.size > 0) {
            this.lastChild.pop();
        }

        if(this.elements.size > 0) {
            Object v0 = this.elements.peek();
        }
        else {
            v0_1 = null;
        }

        this.current = v0_1;
    }

    protected void startArray(String name) {
        JsonValue v0 = new JsonValue(ValueType.array);
        if(this.current != null) {
            this.addChild(name, v0);
        }

        this.elements.add(v0);
        this.current = v0;
    }

    protected void startObject(String name) {
        JsonValue v0 = new JsonValue(ValueType.object);
        if(this.current != null) {
            this.addChild(name, v0);
        }

        this.elements.add(v0);
        this.current = v0;
    }

    protected void string(String name, String value) {
        this.addChild(name, new JsonValue(value));
    }

    private String unescape(String value) {
        int v4 = value.length();
        StringBuilder v0 = new StringBuilder(v4 + 16);
        int v3 = 0;
        while(v3 < v4) {
            int v2 = v3 + 1;
            char v1 = value.charAt(v3);
            if(v1 != 92) {
                v0.append(v1);
                v3 = v2;
                continue;
            }
            else if(v2 != v4) {
                v3 = v2 + 1;
                v1 = value.charAt(v2);
                if(v1 == 117) {
                    v0.append(Character.toChars(Integer.parseInt(value.substring(v3, v3 + 4), 16)));
                    v3 += 4;
                    continue;
                }
                else {
                    switch(v1) {
                        case 34: 
                        case 47: 
                        case 92: {
                            goto label_38;
                        }
                        case 98: {
                            goto label_37;
                        }
                        case 102: {
                            goto label_40;
                        }
                        case 110: {
                            goto label_42;
                        }
                        case 114: {
                            goto label_44;
                        }
                        case 116: {
                            goto label_46;
                        }
                    }

                    throw new SerializationException("Illegal escaped character: \\" + v1);
                label_37:
                    v1 = '\b';
                    goto label_38;
                label_40:
                    v1 = '\f';
                    goto label_38;
                label_42:
                    v1 = '\n';
                    goto label_38;
                label_44:
                    v1 = '\r';
                    goto label_38;
                label_46:
                    v1 = '\t';
                label_38:
                    v0.append(v1);
                    continue;
                }
            }

            break;
        }

        return v0.toString();
    }
}

