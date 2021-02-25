package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.campaign.UserHelpRecord;

/**
 * <p>
 * 用户助力值记录表 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface UserHelpRecordService extends IService<UserHelpRecord> {

    /**
     * 保存记录
     * @param actProId
     * @param userId
     * @param shareUserId
     * @param helpNum
     * @param helpInstruction
     * @param type
     * @return
     */
    long save(Integer actProId, Integer userId, Integer shareUserId, Long helpNum, String helpInstruction, int type);
}
