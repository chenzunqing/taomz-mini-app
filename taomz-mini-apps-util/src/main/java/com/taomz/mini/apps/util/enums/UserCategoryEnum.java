package com.taomz.mini.apps.util.enums;

/**
 * package ：com.ishop.eum
 * describe ：品牌审核状态枚举
 * Date ： 2018/6/22 10:42
 *
 * @author liaobing
 */
public enum UserCategoryEnum {

    申请中(1),
    审核中(2),
    审核通过待付费(3),
    审核通过已付费(4),
    已拒绝(5),
    已过期(6),
    线下付款待审核(7),
    线下付款已拒绝(8),
    线下续费待确认(9),
    线下续费已拒绝(10),
    未知参数(-1);

    private int flag;

    public int getFlag() {
        return flag;
    }

    UserCategoryEnum(int flag) {
        this.flag = flag;
    }

    public static UserCategoryEnum getEnum(int flag) {
        for (UserCategoryEnum em : values()) {
            if (em.getFlag() == flag) {
                return em;
            }
        }
        return 未知参数;
    }

    /**
     * result 状态来自 BasicInfoHistoryEnum 状态
     *
     * @param result
     * @return
     */
    public static int get(int result) {
        int source;
        switch (result) {
            case 0:
                source = 申请中.getFlag();
                break;
            case 1:
                source = 审核中.getFlag();
                break;
            case 2:
            case 4:
                source = 审核通过待付费.getFlag();
                break;
            case 3:
            case 5:
            case 6:
                source = 审核通过已付费.getFlag();
                break;
            case 7:
                source = 已拒绝.getFlag();
                break;
            default:
                source = 未知参数.getFlag();
                break;
        }
        return source;
    }

    /**
     * 判断线上支付还是线下支付状态
     *
     * @param status
     * @return
     */
    public static boolean isOnLine(int status) {
        switch (status) {
            case 7:
            case 8:
            case 9:
            case 10:
                return false;
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return true;

        }
    }

    /**
     * 判断会员支付状态
     *
     * @param status
     * @return
     */
    public static int isMemberStatus(int status) {
        int memberStatus;
        switch (status) {
            case 1:
            case 2:
                memberStatus = 1;
                break;
            case 3:
                memberStatus = 2;
                break;
            case 4:
                memberStatus = 7;
                break;
            case 7:
                memberStatus = 3;
                break;
            case 5:
                memberStatus = 9;
                break;
            case 8:
                memberStatus = 5;
                break;
            case 9:
                memberStatus = 4;
                break;
            case 10:
                memberStatus = 6;
                break;
            case 6:
                memberStatus = 8;
                break;
            default:
                memberStatus = 0;
                break;
        }
        return memberStatus;
    }

    public static int shopGet(Short status) {
        switch (status) {
            case 1:
                return 审核中.flag;
            case 2:
                return 审核通过待付费.flag;
            case 3:
                return 审核通过已付费.flag;
            case 4:
                return 已拒绝.flag;
            case 5:
                return 已过期.flag;
            default:
                return 未知参数.flag;
        }
    }
}
