package com.taomz.mini.apps.dto.campaign;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : ShareFissionRecordCountDTO
 * @Package : com.ishop.dto.campaign
 * @Description: 分享裂变查询DTO
 * @date 2020/12/9 22:21
 **/
@Setter
@Getter
public class ShareFissionRecordCountDTO {

    /**
     * 等级ID
     */
    private Integer gradeId;

    /**
     * 生效次数
     */
    private Integer validNum;

    /**
     * 生效条件
     */
    private String validCondition;

    /**
     * 剩余次数
     */
    private Integer surplusCount;
}
