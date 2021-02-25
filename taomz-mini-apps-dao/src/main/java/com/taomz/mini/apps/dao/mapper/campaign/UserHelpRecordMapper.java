package com.taomz.mini.apps.dao.mapper.campaign;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.model.campaign.UserHelpRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 * 用户助力值记录表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-12-12
 */
public interface UserHelpRecordMapper extends BaseMapper<UserHelpRecord> {

    int currentDayLoginCount(@Param("activityId") Integer activityId, @Param("userId") Integer userId, @Param("nowDate") Date nowDate);
}
