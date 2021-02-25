package com.taomz.mini.apps.service.campaign.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taomz.mini.apps.dao.mapper.campaign.UserHelpMapper;
import com.taomz.mini.apps.dao.mapper.campaign.UserHelpRecordMapper;
import com.taomz.mini.apps.model.campaign.UserHelp;
import com.taomz.mini.apps.model.campaign.UserHelpRecord;
import com.taomz.mini.apps.service.campaign.UserHelpRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 用户助力值记录表 服务实现类
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
@Service
public class UserHelpRecordServiceImpl extends ServiceImpl<UserHelpRecordMapper, UserHelpRecord> implements UserHelpRecordService {

    @Autowired
    private UserHelpMapper userHelpMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long save(Integer actProId, Integer userId, Integer shareUserId, Long helpNum, String helpInstruction, int type) {
        synchronized (UserHelpRecordServiceImpl.class) {
            UserHelp userHelp = userHelpMapper.selectOne(
                    Wrappers.lambdaQuery(UserHelp.class)
                            .eq(UserHelp::getActProdId, actProId)
                            .eq(UserHelp::getUserId, userId));
            if (Objects.isNull(userHelp) || Objects.isNull(helpNum)) {
                return 0L;
            }
            userHelp.setHelpNum(userHelp.getHelpNum() + Math.abs(helpNum));
            if ("登录".equals(helpInstruction)) {
                userHelp.setLoginTime(DateUtil.date());
            }
            // 更新用户助力值
            userHelpMapper.updateById(userHelp);
            // 更新用户助力记录值
            UserHelpRecord userHelpRecord = new UserHelpRecord()
                    .setHelpId(userHelp.getId())
                    .setHelpNum(helpNum)
                    .setInstruction(helpInstruction)
                    .setType(type)
                    .setOperateUserId(Objects.nonNull(shareUserId) ? shareUserId : userId)
                    .setCreateTime(DateUtil.date());
            getBaseMapper().insert(userHelpRecord);
            return userHelp.getHelpNum() - userHelp.getUseredHelpNum();
        }
    }
}
