package com.mall.common.constant;

/**
 * 平台通用状态
 * @author cuilidong
 * @date 2019/2/11 13:43
 */
public class State {
    /**
     * YES-是 NO-否
     */
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     * 获取所有属性值
     * @return
     */
    public Integer[] getAllValues() {
        Integer[] fields = new Integer[2];
        fields[0] = NO;
        fields[1] = YES;
        return fields;
    }

    /**
     * 判断是否存在某个值
     * @param param 需要判断的值
     * @return
     */
    public boolean isExistValue(Integer param){
        for(Integer v: this.getAllValues()){
            if(v.equals(param)){
                return true;
            }
        }
        return false;
    }
}
