package com.mall.common.db;

/**
 * 数据源枚举
 * @author King chen
 * @emai 396885563@qq.com
 * @date 2018年4月8日
 */

public enum DataSourceType {
    read("slaveDataSource", "salve"), write("masterDataSource", "master");
    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
