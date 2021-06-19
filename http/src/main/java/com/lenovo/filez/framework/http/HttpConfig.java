package com.lenovo.filez.framework.http;

/**
 * HTTP相关配置参数
 *
 */
public class HttpConfig {
    /**
     * 是否debug模式
     * 如果为true，则会打印http请求和响应的数据
     */
    private boolean debug = true;
    /**
     * 读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     */
    private int readTimeout;
    /**
     * 发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     */
    private int writeTimeout;

    /**
     * 链接超时时间，单位为秒
     */
    private int timeout = 25;

    /**
     * 查询是否debug模式
     * 如果为true，则会打印http请求和响应的数据
     *
     * @return 是否为debug模式
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * 设置是否为debug模式
     * 如果为true，则会打印http请求和响应的数据
     *
     * @param debug 是否为debug模式
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * 获得读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     *
     * @return 读取数据超时时间
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * 设置读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     *
     * @param readTimeout 数据读取超时时间
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
     * 获得发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     *
     * @return 数据发送超时时间
     */
    public int getWriteTimeout() {
        return writeTimeout;
    }

    /**
     * 设置发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     *
     * @param writeTimeout 发送数据超时时间
     */
    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    /**
     * 获取链接超时时间，单位为秒
     *
     * @return 链接超时时间
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * 设置链接超时时间，单位为秒
     *
     * @param timeout 链接超时时间
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
