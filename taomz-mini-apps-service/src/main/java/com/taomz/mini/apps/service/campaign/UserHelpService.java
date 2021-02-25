package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.campaign.UserHelp;

/**
 * <p>
 * 用户助力值表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface UserHelpService extends IService<UserHelp> {

    /**
     * 查询用户剩余助力值
     * @param activityId
     * @param userId
     * @param shareUserId
     * @param isNewUser
     * @return {@link Long}
     */
    Long userSurplusHelp(Integer activityId, Integer userId, Integer shareUserId, Integer isNewUser);
}
