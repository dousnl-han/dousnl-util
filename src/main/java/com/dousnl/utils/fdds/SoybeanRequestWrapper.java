package com.dousnl.utils.fdds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/16 19:36
 */
public class SoybeanRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger LOG = LoggerFactory.getLogger(SoybeanRequestWrapper.class);
    private byte[] bodyCopier = null;
    private String method;
    private String contentType;

    @Override
    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public SoybeanRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.method = RequestMethod.GET.name();
        this.contentType = null;
        this.contentType = request.getContentType();
        this.method = request.getMethod();
        InputStream in = request.getInputStream();
        int length = request.getIntHeader("Content-Length");
        if (length > 0) {
            this.bodyCopier = new byte[length];
            int bytesRead = 0;
            int offset = 0;
            if (length > in.available()) {
                LOG.debug(request.getRequestURI() + ",length:" + length + ",available:" + in.available() + ",type:" + request.getContentType());
            }

            while(offset < length) {
                bytesRead = in.read(this.bodyCopier, offset, this.bodyCopier.length - offset);
                if (bytesRead == -1) {
                    break;
                }

                offset += bytesRead;
            }

            in.close();
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return this.bodyCopier != null ? new ServletInputStreamCopier(this.bodyCopier) : null;
    }

    public byte[] getCopy() {
        return this.bodyCopier;
    }

    public String getBody() throws UnsupportedEncodingException {
        return this.bodyCopier != null ? new String(this.bodyCopier, "utf-8") : null;
    }

    public void setBody(String param) {
        this.bodyCopier = param.getBytes();
    }

    public int getContentLength() {
        return this.bodyCopier == null ? 0 : this.bodyCopier.length;
    }

    public long getContentLengthLong() {
        return (long)this.getContentLength();
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
