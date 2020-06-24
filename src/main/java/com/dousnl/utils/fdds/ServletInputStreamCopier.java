package com.dousnl.utils.fdds;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/16 19:37
 */
public class ServletInputStreamCopier extends ServletInputStream {
    private ByteArrayInputStream bais;

    public ServletInputStreamCopier(byte[] in) {
        if (in != null) {
            this.bais = new ByteArrayInputStream(in);
        }

    }

    public boolean isFinished() {
        return this.bais.available() == 0;
    }

    public boolean isReady() {
        return true;
    }

    public void setReadListener(ReadListener readListener) {
        throw new RuntimeException("Not implemented");
    }

    public int read() throws IOException {
        return this.bais.read();
    }
}
