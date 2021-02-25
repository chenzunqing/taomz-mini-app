package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.model.campaign.TShareFission;

/**
 * <p>
 * 分享裂变 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface TShareFissionService extends IService<TShareFission> {

    /**
     * 分享成功保存
     * @param cmId
     * @param userId
     * @param isNewUser
     */
    void save(Integer cmId, Integer userId, Integer isNewUser);

    /**
     * 查询分享成功记录
     * @param cmId
     * @param userId
     * @param status
     * @return
     */
    TShareFission queryShareSucc(Integer cmId, Integer userId, Integer status);

    /**
     * 使用成功更新记录
     * @param cmId
     * @param userId
     * @param status
     * @return
     */
    void updateShareUsed(Integer cmId, Integer userId, Integer status);
}
