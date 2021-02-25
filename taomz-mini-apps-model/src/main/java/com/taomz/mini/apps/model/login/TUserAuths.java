package com.taomz.mini.apps.model.login;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author : wangling
 * @version V1.0
 * @title : TUserAuths
 * @Package : com.taomz.mini.apps.model.login
 * @Description: 第三方登录验证
 * @date 2020/11/18 12:24
 **/
@Data
public class TUserAuths {

    public static final String FIELD_WX_LITEAPP_OPENID = "wx_liteapp_openid";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *用户ID
     */
    private Integer userId;

    /**
     *登陆类型
     */
    private String identifyType;

    /**
     *微信openid
     */
    private String wxOpenid;

    /**
     *
     */
    private String qqOpenid;

    /**
     *qq  openid
     */
    private String wxLiteappOpenid;

    /**
     *苹果授权唯一标识
     */
    private String appleSub;

    /**
     *授权凭证
     */
    private String credential;

    /**
     *是否已经验证
     */
    private Integer verified;

    /**
     *是否有效  1有效 0无效
     */
    private Integer deleteFlag;

}
