package com.taomz.mini.apps.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntryQueryDTO
 * @Package : com.taomz.mini.apps.service.dto
 * @Description: 快捷入口查询
 * @date 2020/11/18 9:05
 **/
@Data
public class QuickEntryQueryDTO {

    @NotNull(message = "快捷入口类型不能为空！")
    private Integer type;
}
