package com.taomz.mini.apps.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DictQueryDTO {
    /**
     * 字典类型
     */
    @NotNull(message = "字典类型不可为空")
    private String dicType;
}
