package com.taomz.mini.apps.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdvertisingQueryDTO {
    /**
     * 广告位编码
     */
    @NotNull(message = "广告位编码不可为空")
    private String adsCode;
}
