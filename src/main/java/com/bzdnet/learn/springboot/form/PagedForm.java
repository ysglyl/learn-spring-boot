package com.bzdnet.learn.springboot.form;

import io.swagger.annotations.ApiModelProperty;

public class PagedForm {

    @ApiModelProperty(value = "当前页码，从1开始，默认为1", example = "1")
    private long current = 1L;
    @ApiModelProperty(value = "分页大小，默认为10", example = "10")
    private long size = 10L;

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
