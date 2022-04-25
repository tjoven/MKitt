package com.example.image;

/**
 * 磁盘缓存策略
 * @author kitt
 */

public enum DiskCacheTactic {
    /**
     * 缓存{@link #SOURCE} and {@link #RESULT}的数据
     */
    ALL,
    /**
     * 数据不缓存
     */
    NONE,
    /**
     * 只保存原始数据到磁盘
     */
    SOURCE,
    /**
     * 保存转换后的媒体数据到磁盘
     */
    RESULT;
}
