// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class PixmapIO {
    class CIM {
        private static final int BUFFER_SIZE = 32000;
        private static final byte[] readBuffer;
        private static final byte[] writeBuffer;

        static  {
            CIM.writeBuffer = new byte[32000];
            CIM.readBuffer = new byte[32000];
        }

        private CIM() {
            super();
        }

        public static Pixmap read(FileHandle file) {  // has try-catch handlers
            DataInputStream v3_1;
            ByteBuffer v5;
            Pixmap v6;
            DataInputStream v4;
            Closeable v3 = null;
            try {
                v4 = new DataInputStream(new InflaterInputStream(new BufferedInputStream(file.read())));
                goto label_5;
            }
            catch(Throwable v9) {
            }
            catch(Exception v0) {
                goto label_28;
                try {
                label_5:
                    v6 = new Pixmap(v4.readInt(), v4.readInt(), Format.fromGdx2DPixmapFormat(v4.readInt()));
                    v5 = v6.getPixels();
                    v5.position(0);
                    v5.limit(v5.capacity());
                    goto label_17;
                }
                catch(Exception v0) {
                    goto label_27;
                }
                catch(Throwable v9) {
                    goto label_47;
                }

                try {
                    while(true) {
                    label_17:
                        int v7 = v4.read(CIM.readBuffer);
                        if(v7 <= 0) {
                            break;
                        }

                        v5.put(CIM.readBuffer, 0, v7);
                    }

                    goto label_41;
                }
                catch(Throwable v9) {
                    goto label_25;
                }

                try {
                label_41:
                    v5.position(0);
                    v5.limit(v5.capacity());
                }
                catch(Exception v0) {
                    goto label_27;
                }
                catch(Throwable v9) {
                    goto label_47;
                }

                StreamUtils.closeQuietly(((Closeable)v4));
                return v6;
                try {
                    try {
                    label_25:
                        throw v9;
                    }
                    catch(Throwable v9) {
                    label_47:
                        v3_1 = v4;
                    }
                    catch(Exception v0) {
                    label_27:
                        v3_1 = v4;
                        try {
                        label_28:
                            throw new GdxRuntimeException("Couldn\'t read Pixmap from file \'" + file + "\'", ((Throwable)v0));
                        }
                        catch(Throwable v9) {
                        }
                    }
                }
                catch(Throwable v9) {
                    goto label_25;
                }
            }

            StreamUtils.closeQuietly(v3);
            throw v9;
        }

        public static void write(FileHandle file, Pixmap pixmap) {  // has try-catch handlers
            DataOutputStream v4_1;
            int v2;
            int v7;
            ByteBuffer v6;
            DataOutputStream v5;
            Closeable v4 = null;
            try {
                v5 = new DataOutputStream(new DeflaterOutputStream(file.write(false)));
                goto label_5;
            }
            catch(Throwable v8) {
            }
            catch(Exception v1) {
                goto label_46;
                try {
                label_5:
                    v5.writeInt(pixmap.getWidth());
                    v5.writeInt(pixmap.getHeight());
                    v5.writeInt(Format.toGdx2DPixmapFormat(pixmap.getFormat()));
                    v6 = pixmap.getPixels();
                    v6.position(0);
                    v6.limit(v6.capacity());
                    v7 = v6.capacity() % 32000;
                    int v3 = v6.capacity() / 32000;
                    v2 = 0;
                }
                catch(Throwable v8) {
                    goto label_59;
                }
                catch(Exception v1) {
                    goto label_45;
                }

                while(true) {
                    if(v2 >= v3) {
                        goto label_30;
                    }

                    try {
                        v6.get(CIM.writeBuffer);
                        v5.write(CIM.writeBuffer);
                        ++v2;
                        continue;
                    label_30:
                        v6.get(CIM.writeBuffer, 0, v7);
                        v5.write(CIM.writeBuffer, 0, v7);
                        goto label_37;
                    }
                    catch(Throwable v8) {
                        goto label_43;
                    }
                }

                try {
                label_37:
                    v6.position(0);
                    v6.limit(v6.capacity());
                }
                catch(Throwable v8) {
                    goto label_59;
                }
                catch(Exception v1) {
                    goto label_45;
                }

                StreamUtils.closeQuietly(((Closeable)v5));
                return;
                try {
                label_43:
                    throw v8;
                }
                catch(Throwable v8) {
                    goto label_59;
                }
                catch(Throwable v8) {
                    goto label_43;
                }
                catch(Exception v1) {
                }

            label_45:
                v4_1 = v5;
                try {
                label_46:
                    throw new GdxRuntimeException("Couldn\'t write Pixmap to file \'" + file + "\'", ((Throwable)v1));
                }
                catch(Throwable v8) {
                    goto label_56;
                }
            }

        label_59:
            v4_1 = v5;
        label_56:
            StreamUtils.closeQuietly(v4);
            throw v8;
        }
    }

    class PNG {
        static final int ZLIB_BLOCK_SIZE = 32000;
        static int[] crcTable;

        private PNG() {
            super();
        }

        private static int calcADLER32(byte[] raw) {
            int v0;
            int v5 = 65521;
            int v2 = 1;
            int v3 = 0;
            int v1;
            for(v1 = 0; v1 < raw.length; ++v1) {
                if(raw[v1] >= 0) {
                    v0 = raw[v1];
                }
                else {
                    v0 = raw[v1] + 256;
                }

                v2 = (v2 + v0) % v5;
                v3 = (v3 + v2) % v5;
            }

            return (v3 << 16) + v2;
        }

        private static void createCRCTable() {
            int v5 = 256;
            PNG.crcTable = new int[v5];
            int v1;
            for(v1 = 0; v1 < v5; ++v1) {
                int v0 = v1;
                int v2;
                for(v2 = 0; v2 < 8; ++v2) {
                    if((v0 & 1) > 0) {
                        v0 = -306674912 ^ v0 >>> 1;
                    }
                    else {
                        v0 >>>= 1;
                    }
                }

                PNG.crcTable[v1] = v0;
            }
        }

        private static byte[] createDataChunk(Pixmap pixmap) throws IOException {
            int v10 = pixmap.getWidth();
            int v5 = pixmap.getHeight();
            byte[] v8 = new byte[v10 * 4 * v5 + v5];
            int v12 = 0;
            int v3 = 0;
            while(v12 < v5) {
                v8[v3] = 0;
                int v11 = 0;
                ++v3;
                while(v11 < v10) {
                    int v6 = pixmap.getPixel(v11, v12) & -1;
                    int v2 = v3 + 1;
                    v8[v3] = ((byte)(v6 >> 24 & 255));
                    v3 = v2 + 1;
                    v8[v2] = ((byte)(v6 >> 16 & 255));
                    v2 = v3 + 1;
                    v8[v3] = ((byte)(v6 >> 8 & 255));
                    v3 = v2 + 1;
                    v8[v2] = ((byte)(v6 & 255));
                    ++v11;
                }

                ++v12;
            }

            return PNG.toChunk("IDAT", PNG.toZLIB(v8));
        }

        private static byte[] createHeaderChunk(int width, int height) throws IOException {
            ByteArrayOutputStream v0 = new ByteArrayOutputStream(13);
            DataOutputStream v1 = new DataOutputStream(((OutputStream)v0));
            v1.writeInt(width);
            v1.writeInt(height);
            v1.writeByte(8);
            v1.writeByte(6);
            v1.writeByte(0);
            v1.writeByte(0);
            v1.writeByte(0);
            return PNG.toChunk("IHDR", v0.toByteArray());
        }

        private static byte[] createTrailerChunk() throws IOException {
            return PNG.toChunk("IEND", new byte[0]);
        }

        private static byte[] toChunk(String id, byte[] raw) throws IOException {
            int v6 = 4;
            ByteArrayOutputStream v0 = new ByteArrayOutputStream(raw.length + 12);
            DataOutputStream v2 = new DataOutputStream(((OutputStream)v0));
            v2.writeInt(raw.length);
            byte[] v1 = new byte[v6];
            int v4;
            for(v4 = 0; v4 < v6; ++v4) {
                v1[v4] = ((byte)id.charAt(v4));
            }

            v2.write(v1);
            v2.write(raw);
            v2.writeInt(PNG.updateCRC(PNG.updateCRC(-1, v1), raw) ^ -1);
            return v0.toByteArray();
        }

        private static byte[] toZLIB(byte[] raw) throws IOException {
            char v6 = '??';
            ByteArrayOutputStream v0 = new ByteArrayOutputStream(raw.length + 6 + raw.length / 32000 * 5);
            DataOutputStream v3 = new DataOutputStream(((OutputStream)v0));
            v3.writeByte(8);
            v3.writeByte(29);
            int v1;
            for(v1 = 0; raw.length - v1 > v6; v1 += 32000) {
                PNG.writeUncompressedDeflateBlock(v3, false, raw, v1, v6);
            }

            PNG.writeUncompressedDeflateBlock(v3, true, raw, v1, ((char)(raw.length - v1)));
            v3.writeInt(PNG.calcADLER32(raw));
            return v0.toByteArray();
        }

        private static int updateCRC(int crc, byte[] raw) {
            if(PNG.crcTable == null) {
                PNG.createCRCTable();
            }

            byte[] v0 = raw;
            int v3 = v0.length;
            int v2;
            for(v2 = 0; v2 < v3; ++v2) {
                crc = PNG.crcTable[(crc ^ v0[v2]) & 255] ^ crc >>> 8;
            }

            return crc;
        }

        static byte[] write(Pixmap pixmap) throws IOException {
            byte[] v3 = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10};
            byte[] v1 = PNG.createHeaderChunk(pixmap.getWidth(), pixmap.getHeight());
            byte[] v0 = PNG.createDataChunk(pixmap);
            byte[] v4 = PNG.createTrailerChunk();
            ByteArrayOutputStream v2 = new ByteArrayOutputStream(v3.length + v1.length + v0.length + v4.length);
            v2.write(v3);
            v2.write(v1);
            v2.write(v0);
            v2.write(v4);
            return v2.toByteArray();
        }

        private static void writeUncompressedDeflateBlock(DataOutputStream zlib, boolean last, byte[] raw, int off, char len) throws IOException {
            int v0;
            int v1 = 65280;
            if(last) {
                v0 = 1;
            }
            else {
                v0 = 0;
            }

            zlib.writeByte(((byte)v0));
            zlib.writeByte(((byte)(len & 255)));
            zlib.writeByte(((byte)((len & v1) >> 8)));
            zlib.writeByte(((byte)((len ^ -1) & 255)));
            zlib.writeByte(((byte)(((len ^ -1) & v1) >> 8)));
            zlib.write(raw, off, len);
        }
    }

    public PixmapIO() {
        super();
    }

    public static Pixmap readCIM(FileHandle file) {
        return CIM.read(file);
    }

    public static void writeCIM(FileHandle file, Pixmap pixmap) {
        CIM.write(file, pixmap);
    }

    public static void writePNG(FileHandle file, Pixmap pixmap) {  // has try-catch handlers
        try {
            file.writeBytes(PNG.write(pixmap), false);
            return;
        }
        catch(IOException v0) {
            throw new GdxRuntimeException("Error writing PNG: " + file, ((Throwable)v0));
        }
    }
}

