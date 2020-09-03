package com.github.tanyueran.modal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "food_tab")
public class CloudFood implements Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 菜品名称
     */
    @Column(name = "food_name")
    private String foodName;

    /**
     * 菜品类型
     */
    @Column(name = "food_type")
    private Integer foodType;

    /**
     * 菜品介绍
     */
    @Column(name = "food_detail")
    private String foodDetail;

    /**
     * 价格
     */
    private Double price;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 菜品头像id
     */
    @Column(name = "img_id")
    private String imgId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取菜品名称
     *
     * @return food_name - 菜品名称
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * 设置菜品名称
     *
     * @param foodName 菜品名称
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    /**
     * 获取菜品类型
     *
     * @return food_type - 菜品类型
     */
    public Integer getFoodType() {
        return foodType;
    }

    /**
     * 设置菜品类型
     *
     * @param foodType 菜品类型
     */
    public void setFoodType(Integer foodType) {
        this.foodType = foodType;
    }

    /**
     * 获取菜品介绍
     *
     * @return food_detail - 菜品介绍
     */
    public String getFoodDetail() {
        return foodDetail;
    }

    /**
     * 设置菜品介绍
     *
     * @param foodDetail 菜品介绍
     */
    public void setFoodDetail(String foodDetail) {
        this.foodDetail = foodDetail == null ? null : foodDetail.trim();
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取菜品头像id
     *
     * @return img_id - 菜品头像id
     */
    public String getImgId() {
        return imgId;
    }

    /**
     * 设置菜品头像id
     *
     * @param imgId 菜品头像id
     */
    public void setImgId(String imgId) {
        this.imgId = imgId == null ? null : imgId.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}