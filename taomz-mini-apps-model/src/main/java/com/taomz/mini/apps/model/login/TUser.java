package com.taomz.mini.apps.model.login;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : wangling
 * @version V1.0
 * @title : TUser
 * @Package : com.taomz.mini.apps.model.login
 * @Description: 用户
 * @date 2020/11/18 12:30
 **/

@Data
public class TUser implements Serializable {

    public static final String FIELD_PHONE = "phone";
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员编码
     */
    private String memberCode;
    /**
     * 会员编码身份字母（M:会员B:品牌S：红人MB:会员+品牌MS会员)
     */
    private String codeIdentityLetter;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     *用户密码
     */
    private String passWord;

    /**
     *性别（男-0,1-女）
     */
    private Integer sex;

    /**
     *头像地址
     */
    private String imageUrl;

    /**
     *用户昵称
     */
    private String nickName;

    /**
     *用户名称
     */
    private String userName;

    /**
     *用户生日
     */
    private Date birthday;

    /**
     *QQ
     */
    private String qq;

    /**
     *微信
     */
    private String weiXin;

    /**
     *苹果
     */
    private String apple;

    /**
     *允许设备最大接入数量
     */
    private Integer maxLoginNumber;

    /**
     *用户登录身份令牌
     */
    private String toKen;

    /**
     *是否年缴费( 0 - 否，1 - 是) 第一次缴费后必须按年缴费
     */
    private Integer isYearPayment;

    /**
     *登录设备token
     */
    private String deviceToken;

    /**
     *登录设备类型
     */
    private String deviceType;

    /**
     *创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *用户是否被锁定(0:未锁定，1:锁定)
     */
    private Integer locked;

    /**
     *注册渠道(APP/H5/IOS/Android/PC)
     */
    private String registerChannel;

    /**
     *(0-无等级，1-有等级)
     */
    private Integer isGrade;

    /**
     *最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     *销售名字
     */
    private String saleName;

    /**
     *是否是注销用户 1是 0否
     */
    private Integer logoutFlag;

    /**
     *注销时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;
}
