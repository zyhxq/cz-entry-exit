package com.lx.springboot.Enums;

/**
 * @Author zhangjun
 * @Date 2019/11/25
 */
public enum TypeEnum {
    /**
     * advisory_notice 表
     * type 类型
     */
    NOTICE("notice", "咨询公告"),
    GUID("guid", "办事指引"),
    ;

    private String modelType;
    private String desc;

    TypeEnum(String modelType, String desc) {
        this.modelType = modelType;
        this.desc = desc;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescByType(String type){
        for (TypeEnum value : TypeEnum.values()) {
            if(value.getModelType().equals(type)){
                return value.getDesc();
            }
        }
        return "";
    }
}
