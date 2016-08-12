// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx.net;

import com.badlogic.gdx.Net$HttpRequest;
import com.badlogic.gdx.Net$HttpResponse;
import com.badlogic.gdx.Net$HttpResponseListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map$Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetJavaImpl {
    class HttpClientResponse implements HttpResponse {
        private HttpURLConnection connection;
        private HttpStatus status;

        public HttpClientResponse(HttpURLConnection connection) throws IOException {  // has try-catch handlers
            super();
            this.connection = connection;
            try {
                this.status = new HttpStatus(connection.getResponseCode());
            }
            catch(IOException v0) {
                this.status = new HttpStatus(-1);
            }
        }

        public String getHeader(String name) {
            return this.connection.getHeaderField(name);
        }

        public Map getHeaders() {
            return this.connection.getHeaderFields();
        }

        private InputStream getInputStream() {  // has try-catch handlers
            InputStream v1;
            try {
                v1 = this.connection.getInputStream();
            }
            catch(IOException v0) {
                v1 = this.connection.getErrorStream();
            }

            return v1;
        }

        public byte[] getResult() {  // has try-catch handlers
            byte[] v2_1;
            InputStream v1 = this.getInputStream();
            try {
                v2_1 = StreamUtils.copyStreamToByteArray(v1, this.connection.getContentLength());
            }
            catch(Throwable v2) {
            label_11:
                StreamUtils.closeQuietly(((Closeable)v1));
                throw v2;
            }
            catch(IOException v0) {
                try {
                    v2_1 = StreamUtils.EMPTY_BYTES;
                }
                catch(Throwable v2) {
                    goto label_11;
                }

                StreamUtils.closeQuietly(((Closeable)v1));
                goto label_5;
            }

            StreamUtils.closeQuietly(((Closeable)v1));
        label_5:
            return v2_1;
        }

        public InputStream getResultAsStream() {
            return this.getInputStream();
        }

        public String getResultAsString() {  // has try-catch handlers
            String v2_1;
            InputStream v1 = this.getInputStream();
            try {
                v2_1 = StreamUtils.copyStreamToString(v1, this.connection.getContentLength());
            }
            catch(Throwable v2) {
            label_11:
                StreamUtils.closeQuietly(((Closeable)v1));
                throw v2;
            }
            catch(IOException v0) {
                try {
                    v2_1 = "";
                }
                catch(Throwable v2) {
                    goto label_11;
                }

                StreamUtils.closeQuietly(((Closeable)v1));
                goto label_5;
            }

            StreamUtils.closeQuietly(((Closeable)v1));
        label_5:
            return v2_1;
        }

        public HttpStatus getStatus() {
            return this.status;
        }
    }

    private final ExecutorService executorService;

    public NetJavaImpl() {
        super();
        this.executorService = Executors.newCachedThreadPool();
    }

    public void sendHttpRequest(HttpRequest httpRequest, HttpResponseListener httpResponseListener) {  // has try-catch handlers
        boolean v3;
        URL v12;
        if(httpRequest.getUrl() != null) {
            goto label_7;
        }

        httpResponseListener.failed(new GdxRuntimeException("can\'t process a HTTP request without URL set"));
        return;
        try {
        label_7:
            String v10 = httpRequest.getMethod();
            if(v10.equalsIgnoreCase("GET")) {
                String v11 = "";
                String v13 = httpRequest.getContent();
                if(v13 != null && !"".equals(v13)) {
                    v11 = "?" + v13;
                }

                v12 = new URL(httpRequest.getUrl() + v11);
            }
            else {
                v12 = new URL(httpRequest.getUrl());
            }

            URLConnection v5 = v12.openConnection();
            if((v10.equalsIgnoreCase("POST")) || (v10.equalsIgnoreCase("PUT"))) {
                v3 = true;
            }
            else {
                v3 = false;
            }

            ((HttpURLConnection)v5).setDoOutput(v3);
            ((HttpURLConnection)v5).setDoInput(true);
            ((HttpURLConnection)v5).setRequestMethod(v10);
            Iterator v9 = httpRequest.getHeaders().entrySet().iterator();
            while(v9.hasNext()) {
                Object v8 = v9.next();
                ((HttpURLConnection)v5).addRequestProperty(((Map$Entry)v8).getKey(), ((Map$Entry)v8).getValue());
            }

            ((HttpURLConnection)v5).setConnectTimeout(httpRequest.getTimeOut());
            ((HttpURLConnection)v5).setReadTimeout(httpRequest.getTimeOut());
            this.executorService.submit(new Runnable() {
                public void run() {  // has try-catch handlers
                    HttpClientResponse v0;
                    OutputStream v4;
                    InputStream v1;
                    OutputStreamWriter v5;
                    String v2;
                    try {
                        if(this.val$doingOutPut) {
                            v2 = this.val$httpRequest.getContent();
                            if(v2 != null) {
                                v5 = new OutputStreamWriter(this.val$connection.getOutputStream());
                                goto label_8;
                            }
                            else {
                                goto label_28;
                            }
                        }

                        goto label_10;
                    }
                    catch(Exception v3) {
                        goto label_24;
                    }

                    try {
                    label_8:
                        v5.write(v2);
                        goto label_9;
                    }
                    catch(Throwable v6) {
                        goto label_20;
                    }

                    try {
                    label_9:
                        StreamUtils.closeQuietly(((Closeable)v5));
                        goto label_10;
                    label_20:
                        StreamUtils.closeQuietly(((Closeable)v5));
                        throw v6;
                    label_28:
                        v1 = this.val$httpRequest.getContentStream();
                        if(v1 != null) {
                            v4 = this.val$connection.getOutputStream();
                            goto label_33;
                        }

                        goto label_10;
                    }
                    catch(Exception v3) {
                        goto label_24;
                    }

                    try {
                    label_33:
                        StreamUtils.copyStream(v1, v4);
                        goto label_34;
                    }
                    catch(Throwable v6) {
                        goto label_37;
                    }

                    try {
                    label_34:
                        StreamUtils.closeQuietly(((Closeable)v4));
                        goto label_10;
                    label_37:
                        StreamUtils.closeQuietly(((Closeable)v4));
                        throw v6;
                    label_10:
                        this.val$connection.connect();
                        v0 = new HttpClientResponse(this.val$connection);
                        goto label_14;
                    }
                    catch(Exception v3) {
                        goto label_24;
                    }

                    try {
                    label_14:
                        this.val$httpResponseListener.handleHttpResponse(((HttpResponse)v0));
                        goto label_16;
                    }
                    catch(Throwable v6) {
                        goto label_41;
                    }

                    try {
                    label_16:
                        this.val$connection.disconnect();
                        return;
                    label_41:
                        this.val$connection.disconnect();
                        throw v6;
                    }
                    catch(Exception v3) {
                    label_24:
                        this.val$connection.disconnect();
                        this.val$httpResponseListener.failed(((Throwable)v3));
                    }
                }
            });
        }
        catch(Exception v7) {
            httpResponseListener.failed(((Throwable)v7));
        }
    }
}

