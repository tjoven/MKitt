package com.example.download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * ResponseBody for download
 * @author lianbin.xu
 */
class DownloadProgressResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private FileDownloadUiCallback progressListener;
    private BufferedSource bufferedSource;
    private static final long KILO_BYTES = 1024L;

    public DownloadProgressResponseBody(ResponseBody responseBody,
                                        FileDownloadUiCallback progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            long lastTotalBytesRead = 0;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                final long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if(bytesRead==-1 || (totalBytesRead - lastTotalBytesRead) >= KILO_BYTES){
                    lastTotalBytesRead = totalBytesRead;
                    if (null != progressListener) {
                        progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    }
                }
                return bytesRead;
            }
        };
    }
}
