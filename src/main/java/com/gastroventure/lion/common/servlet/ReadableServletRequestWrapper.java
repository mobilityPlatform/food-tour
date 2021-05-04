//package com.gastroventure.lion.common.servlet;
//
//import org.apache.commons.io.IOUtils;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//import java.nio.charset.Charset;
//
//public class ReadableServletRequestWrapper extends HttpServletRequestWrapper {
//    private Charset encoding;
//    private byte[] body;
//
//    public ReadableServletRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//
//        String characterEncoding = request.getCharacterEncoding();
//        if (characterEncoding == null || "".equals(characterEncoding))
//            characterEncoding = Charset.defaultCharset().name();
//        this.encoding = Charset.forName(characterEncoding);
//
//        InputStream inputStream = request.getInputStream();
//        body = IOUtils.toByteArray(inputStream);
//    }
//
//    public String getBody() {
//        return new String(body, this.encoding);
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(getInputStream(), this.encoding));
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//        final ByteArrayInputStream inputStream = new ByteArrayInputStream(this.body);
//        ServletInputStream servletInputStream = new ServletInputStream() {
//            @Override
//            public int read() throws IOException {
//                return inputStream.read();
//            }
//
//            @Override
//            public boolean isFinished() {
//                return inputStream.available() == 0;
//            }
//
//            @Override
//            public boolean isReady() {
//                return true;
//            }
//
//            @Override
//            public void setReadListener(ReadListener listener) {
//            }
//        };
//
//        return servletInputStream;
//    }
//}
