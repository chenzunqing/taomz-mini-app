package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.TShareFissionMapper;
import com.taomz.mini.apps.model.campaign.TShareFission;
import com.taomz.mini.apps.service.campaign.TShareFissionService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 分享裂变 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class TShareFissionServiceImpl extends ServiceImpl<TShareFissionMapper, TShareFission> implements TShareFissionService {

    @Override
    public void save(Integer cmId, Integer userId, Integer isNewUser) {
        TShareFission shareFission = new TShareFission()
                .setCmId(cmId)
                .setUserId(userId)
                .setIsNewUser(isNewUser == 0 ? 0 : 1)
                .setCreateTime(DateUtil.date());
        getBaseMapper().insert(shareFission);
    }

    @Override
    public TShareFission queryShareSucc(Integer cmId, Integer userId, Integer status) {
        LambdaQueryWrapper<TShareFission> lambdaQueryWrapper = Wrappers.lambdaQuery(TShareFission.class)
                .eq(TShareFission::getCmId,cmId)
                .eq(TShareFission::getUserId, userId);
        if (Objects.nonNull(status)) {
            lambdaQueryWrapper = lambdaQueryWrapper.eq(TShareFission::getStatus, status);
        }
        return getBaseMapper().selectOne(lambdaQueryWrapper);
    }

    @Override
    public void updateShareUsed(Integer cmId, Integer userId, Integer status) {
        if (Objects.isNull(cmId) || Objects.isNull(userId) || Objects.isNull(status)) {
            return;
        }
        getBaseMapper().update(
                new TShareFission().setStatus(status),
                Wrappers.lambdaQuery(TShareFission.class)
                        .eq(TShareFission::getCmId, cmId)
                        .eq(TShareFission::getUserId, userId));
    }
}
