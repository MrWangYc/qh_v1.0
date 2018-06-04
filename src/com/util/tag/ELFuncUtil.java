package com.util.tag;

import com.qh.service.BaseService;
import com.util.CtxUtil;

/**
 * 自定义el表达式
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ELFuncUtil {


    /**
     * 判断功能权限
     *
     * @param fid
     * @return
     */
    public static boolean auth(Integer fid) {
        return CtxUtil.getService(BaseService.class).auth(fid);
    }



}
