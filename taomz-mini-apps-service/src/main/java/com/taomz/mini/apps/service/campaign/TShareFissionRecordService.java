package com.taomz.mini.apps.service.campaign;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taomz.mini.apps.dto.campaign.ShareFissionRecordCountDTO;
import com.taomz.mini.apps.model.campaign.TShareFissionRecord;
import com.taomz.mini.apps.util.enums.ShareFissionSuccEnum;

import java.util.List;

/**
 * <p>
 * 分享裂变记录 服务类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface TShareFissionRecordService extends IService<TShareFissionRecord> {

    /**
     * 保存分享记录
     * @param cmId
     * @param pUserId
     * @param succEnum
     * @param userId
     * @param gradeId
     * @param isNewUser
     */
    void save(Integer cmId, Integer pUserId, ShareFissionSuccEnum succEnum, Integer userId, Integer gradeId, Integer isNewUser);

    /**
     * 查询分享记录
     * @param cmId
     * @param pUserId
     * @param succEnum
     * @return
     */
    List<ShareFissionRecordCountDTO> queryShareCount(Integer cmId, Integer pUserId, ShareFissionSuccEnum succEnum);
}
