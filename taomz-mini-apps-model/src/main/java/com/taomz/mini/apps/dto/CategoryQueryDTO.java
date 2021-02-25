package com.taomz.mini.apps.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author nanmu@taomz.com
 * @version V1.0
 * @title : CategoryQueryDTO
 * @Package : com.taomz.mini.apps.dto
 * @Description:
 * @date 2020/11/24 16:56
 **/
@Setter
@Getter
public class CategoryQueryDTO implements Serializable {

    private Integer type;

    private Integer lev = 1;

    private Long pid = 0L;
}
