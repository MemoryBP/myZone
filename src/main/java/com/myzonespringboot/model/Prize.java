package com.myzonespringboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.node.DecimalNode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cgq on 2017/10/25.
 */
@Entity
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "prize_sequence")
public class Prize implements Serializable {

    @Transient
    private static final long serialVersionUID = -3116771718205435774L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//不创建hibernate_sequence表 // 自动生成
    @Column(length = 20)
    private Long id;
    /**
     * 奖品名称
     */
    private String name;

    /**
     * 描述
     */
    private String memo;

    /**
     * 类型
     */
    private Integer code;

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 库存
     */
    private Integer size;

    /**
     * 剩余
     */
    private Integer remain;

    /**
     * 中奖率
     */
    @Column(precision = 23, scale = 6)
    private BigDecimal percent;

    /**
     * 可使用开始时间
     */
    @Column(name="use_date")
    private Date useDate;

    /**
     * 结束时间
     */
    @Column(name="end_date")
    private Date endDate;

    /**
     * 创建时间
     */
    @Column(name="create_date")
    private Date createDate;

    /**
     * 更新时间
     */
    @Column(name="update_date")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }


    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                ", code=" + code +
                ", enable=" + enable +
                ", size=" + size +
                ", remain=" + remain +
                ", percent=" + percent +
                ", useDate=" + useDate +
                ", endDate=" + endDate +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
