package com.taomz.mini.apps.ext;

import com.taomz.mini.apps.model.quickentry.QuickEntry;
import org.springframework.data.annotation.Transient;

/**
 * @author : wangling
 * @version V1.0
 * @title : QuickEntryExt
 * @Package : com.taomz.mini.apps.ext
 * @Description:
 * @date 2020/11/18 10:57
 **/

public class QuickEntryExt extends QuickEntry {
    /**
     * 跳转栏目类型
     */

    @Transient
    private Integer jumpColumn;
}
