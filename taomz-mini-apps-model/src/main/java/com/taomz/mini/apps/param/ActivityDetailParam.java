package com.taomz.mini.apps.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ActivityDetailParam {
    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不可为空")
    private Integer activityId;

    /**
     * 用户ID
     */
    private Integer userId;
}
