package com.lx.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 咨询公告信息
 * </p>
 */
public class AdvisoryNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer   id ;

    private String title;//标题

    private String   contentDetail ;// 详细内容

    private String   type ;// 类型

    private Date createTime;

    private Date updateTime;

    private Integer   isValid ;// '是否有效1：有效 0：无效',

    private Integer   start ;

    private Integer   end ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AdvisoryNotice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contentDetail='" + contentDetail + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
