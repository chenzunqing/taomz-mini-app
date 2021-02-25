package com.taomz.mini.apps.param;

/**
 *
 * @author liaoxiaoli
 * @date 2018/5/25
 */
public class PageParam {
    private static final int DEFAUT_PAGE_SIZE = 10;
    @SuppressWarnings("unused")
    private static final int DEFAULT_MAX_PAGE_SIZE = 30;
    private int pageNum = 1;
    private int pageSize = DEFAUT_PAGE_SIZE;

    public int getPageNum() {
        if(pageNum <= 0){
            return 1;
        }else{
            return pageNum;
        }
    }

    public void setPageNum(int pageNum) {
        if(pageNum <= 0){
            this.pageNum = 1;
        }else{
            this.pageNum = pageNum;
        }
    }

    public int getPageSize() {
        if(pageSize <= 0){
            return DEFAUT_PAGE_SIZE;
        }else{
            return pageSize;
        }
    }

    public void setPageSize(int pageSize) {
        if(pageSize <= 0){
            this.pageSize = DEFAUT_PAGE_SIZE;
        }else {
            this.pageSize = pageSize;
        }
    }

    public int getOffset(){
        return (getPageNum() - 1 )* pageSize;
    }
}
