package com.crall.insist.utils.intrface;

import java.util.List;
import java.util.Map;

/**
 * 处理页面统一接口
 */
public interface HandleHtml {
    List<Map<String, Object>> handleSampleHtml(String url);

    Map<String, Object> handleMapHtml(String url);
}
