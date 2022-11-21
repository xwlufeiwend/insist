package com.crall.insist.entity;

import lombok.Data;

/**
 * 请求头实体
 */
@Data
public class HeaderEntity {
    private String useAgent;
    private String cookie;
    private String referer;
    private String accept;
}
