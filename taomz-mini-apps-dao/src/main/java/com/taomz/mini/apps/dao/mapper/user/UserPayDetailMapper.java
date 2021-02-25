package com.taomz.mini.apps.dao.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taomz.mini.apps.dto.user.UserPayDetailDTO;
import com.taomz.mini.apps.dto.user.UserPayDetailQueryDTO;
import com.taomz.mini.apps.model.user.UserPayDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 付费用户支付明细表 Mapper 接口
 * </p>
 *
 * @author Guangwei
 * @since 2020-11-23
 */
public interface UserPayDetailMapper extends BaseMapper<UserPayDetail> {

    /**
     * 分页查询
     * @param param
     * @return {@link List}
     */
    Page<UserPayDetailDTO> findPage(Page<UserPayDetailDTO> pagination, @Param(value = "param") UserPayDetailQueryDTO param);
}
