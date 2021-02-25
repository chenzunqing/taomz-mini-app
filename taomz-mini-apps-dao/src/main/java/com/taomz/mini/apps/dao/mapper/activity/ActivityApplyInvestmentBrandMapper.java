package com.taomz.mini.apps.dao.mapper.activity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taomz.mini.apps.dto.activity.ActivityBrandDTO;
import com.taomz.mini.apps.model.activity.ActivityApplyInvestmentBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动品牌招商申请流水表 Mapper 接口
 * </p>
 *
 * @author liaoxiaoli
 * @since 2020-11-21
 */
public interface ActivityApplyInvestmentBrandMapper extends BaseMapper<ActivityApplyInvestmentBrand> {
    /**
     * 活动详情页查询参展品牌
     * @param relationActivity
     * @return
     */
    List<ActivityBrandDTO> getApplyBrandList(@Param("relationActivity") String relationActivity);
}
