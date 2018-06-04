package com.qh.action.mobile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qh.action.BaseAction;
import com.qh.model.base.SysCode;
import com.qh.service.base.SysCodeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 移动端调用接口
 */
@Action("/mInterface")
public class MInterface extends BaseAction {

    @Autowired
    SysCodeService sysCodeService;
    /**
     * 根据ids获得数据字典
     * @return
     */
    @Action("/getDictJsonArray")
    public void getDicJsonArrayByIds(){
        String ids = req.getParameter("ids");

        JSONArray jsonArr = new JSONArray();
        if(StringUtils.isNotEmpty(ids)){
            List<SysCode> list = new ArrayList<>();
            if("all".equals(ids)){
                List<SysCode> tempList = sysCodeService.getAllSysCodeList();
                for (SysCode code:tempList) {
                    if(code.getParent() != null){
                        code.setPid(code.getParent().getId());
                    }

                    code.setParent(null);
                    code.setChildCodes(null);
                    list.add(code);
                }
            }else{
                String[] arr = ids.split(",");
                for(int i=0;i<arr.length;i++){
                    List<SysCode> tempList = sysCodeService.getListByParentId(NumberUtils.toInt(arr[i],0));
                    for (SysCode code:tempList) {
                        if(code.getParent() != null){
                            code.setPid(code.getParent().getId());
                        }
                        code.setParent(null);
                        code.setChildCodes(null);
                        list.add(code);
                    }
                }
            }
            writer(JSON.toJSONString(list));
        }else{
            writer(jsonArr.toString());
        }
    }

}
