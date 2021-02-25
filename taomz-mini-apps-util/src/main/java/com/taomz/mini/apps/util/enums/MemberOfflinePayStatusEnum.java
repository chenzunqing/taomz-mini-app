package com.taomz.mini.apps.util.enums;

import lombok.Getter;

/**
 * @ProjectName: yf-coc
 * @Package: com.ishop.eum
 * @ClassName: MemberOfflinePayStatusEnum
 * @Author: guang
 * @Description: ${description}
 * @Date: 2020/2/13 14:53
 * @Version: 1.0
 */
@Getter
public enum MemberOfflinePayStatusEnum {

    未知参数((short) -1),
    待审核((short) 0),
    业务审核通过((short) 1),
    财务审核通过((short) 2),
    拒绝((short) 3),
    已退款((short) 4),
    已取消((short) 9);

    public Short flag;

    MemberOfflinePayStatusEnum(Short flag) {
        this.flag = flag;
    }

    public static MemberOfflinePayStatusEnum getEnum(Short flag) {
        for (MemberOfflinePayStatusEnum em : values()) {
            if (em.flag.equals(flag)) {
                return em;
            }
        }
        return 未知参数;
    }
}
