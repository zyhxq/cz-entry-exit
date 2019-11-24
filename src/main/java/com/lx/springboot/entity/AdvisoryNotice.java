package com.lx.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 申请人信息
 * </p>
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer   id ;

    private String alipayId;

    private String   namef ;// '中文姓',

    private String   namel ;// '中文名',

    private String   namepinf ;// '拼音姓',

    private String   namepinl  ;//'拼音名',

    private Integer   gender ;// '1男，0女',

    private String   nation  ;// '民族',

    private String 	phone;// '手机号',

    private Date birthday;// '生日',

    private Date createTime;

    private Date updateTime;

    private Integer   isValid ;// '是否有效1：有效 0：无效',

    private String 	idType;// '证件类型',

    private String 	idNo;// '证件号码',

    private String 	contactName;// '紧急联系人',

    private String 	contactPhone;// '紧急联系人手机',

    private String 	pickType;//'取件方式'

    private String 	consigneeName;//'收件人姓名'

    private String 	consigneePhone;//'收件人手机'

    private String 	consigneeAdress;//'收件人地址'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamef() {
        return namef;
    }

    public void setNamef(String namef) {
        this.namef = namef;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public String getNamepinf() {
        return namepinf;
    }

    public void setNamepinf(String namepinf) {
        this.namepinf = namepinf;
    }

    public String getNamepinl() {
        return namepinl;
    }

    public void setNamepinl(String namepinl) {
        this.namepinl = namepinl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getPickType() {
        return pickType;
    }

    public void setPickType(String pickType) {
        this.pickType = pickType;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAdress() {
        return consigneeAdress;
    }

    public void setConsigneeAdress(String consigneeAdress) {
        this.consigneeAdress = consigneeAdress;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", alipayId='" + alipayId + '\'' +
                ", namef='" + namef + '\'' +
                ", namel='" + namel + '\'' +
                ", namepinf='" + namepinf + '\'' +
                ", namepinl='" + namepinl + '\'' +
                ", gender=" + gender +
                ", nation='" + nation + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", pickType='" + pickType + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneePhone='" + consigneePhone + '\'' +
                ", consigneeAdress='" + consigneeAdress + '\'' +
                '}';
    }
}
