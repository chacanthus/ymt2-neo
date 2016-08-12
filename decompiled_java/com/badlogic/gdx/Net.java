// 도박중독 예방 캠페인
// 당신 곁에 우리가 있어요!
// 감당하기 힘든 어려움을 혼자 견디고 계신가요?
// 무엇을 어떻게 해야 할지 막막한가요?
// 당신의 이야기를 듣고 도움을 줄 수 있는 정보를 찾아 드립니다.
// - 한국도박문제관리센터 (국번없이 1336, 24시간)
// - KL중독관리센터 (전화상담  080-7575-535/545)
// - 사행산업통합감독위원회 불법사행산업감시신고센터 (전화상담 1588-0112)
// - 불법도박 등 범죄수익 신고 (지역번호 + 1301)

package com.badlogic.gdx;

import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public abstract interface Net {
    public abstract interface HttpMethods {
        public static final String DELETE = "DELETE";
        public static final String GET = "GET";
        public static final String POST = "POST";
        public static final String PUT = "PUT";

    }

    public class HttpRequest {
        private String content;
        private long contentLength;
        private InputStream contentStream;
        private Map headers;
        private final String httpMethod;
        private int timeOut;
        private String url;

        public HttpRequest(String httpMethod) {
            super();
            this.timeOut = 0;
            this.httpMethod = httpMethod;
            this.headers = new HashMap();
        }

        public String getContent() {
            return this.content;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public InputStream getContentStream() {
            return this.contentStream;
        }

        public Map getHeaders() {
            return this.headers;
        }

        public String getMethod() {
            return this.httpMethod;
        }

        public int getTimeOut() {
            return this.timeOut;
        }

        public String getUrl() {
            return this.url;
        }

        public void setContent(InputStream contentStream, long contentLength) {
            this.contentStream = contentStream;
            this.contentLength = contentLength;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setHeader(String name, String value) {
            this.headers.put(name, value);
        }

        public void setTimeOut(int timeOut) {
            this.timeOut = timeOut;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public abstract interface HttpResponse {
        public abstract String getHeader(String arg0);

        public abstract Map getHeaders();

        public abstract byte[] getResult();

        public abstract InputStream getResultAsStream();

        public abstract String getResultAsString();

        public abstract HttpStatus getStatus();
    }

    public abstract interface HttpResponseListener {
        public abstract void failed(Throwable arg0);

        public abstract void handleHttpResponse(HttpResponse arg0);
    }

    public enum Protocol {
        public static final enum Protocol TCP;

        static  {
            Protocol.TCP = new Protocol("TCP", 0);
            Protocol[] v0 = new Protocol[1];
            v0[0] = Protocol.TCP;
            Protocol.$VALUES = v0;
        }

        private Protocol(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static Protocol valueOf(String name) {
            return Enum.valueOf(Protocol.class, name);
        }

        public static Protocol[] values() {
            return Protocol.$VALUES.clone();
        }
    }

    public abstract Socket newClientSocket(Protocol arg0, String arg1, int arg2, SocketHints arg3);

    public abstract ServerSocket newServerSocket(Protocol arg0, int arg1, ServerSocketHints arg2);

    public abstract void openURI(String arg0);

    public abstract void sendHttpRequest(HttpRequest arg0, HttpResponseListener arg1);
}

