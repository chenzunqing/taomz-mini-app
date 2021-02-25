package com.taomz.mini.apps.service.dto.topic;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author zhaofei@taomz.com
 * @version V1.0
 * @title : TopicPlate
 * @Package : com.taomz.mini.apps.dto.topic
 * @Description:
 * @date 2020/11/23 18:06
 **/
@Data
public class TopicPlateDTO {
	@NotEmpty(message = "专题编码不能为空")
	private String plateCode;
}
