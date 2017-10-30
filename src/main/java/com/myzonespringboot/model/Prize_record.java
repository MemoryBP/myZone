package com.myzonespringboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by cgq on 2017/10/25.
 */
@Entity
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "prize_record_sequence")
public class Prize_record implements Serializable {

    @Transient
    private static final long serialVersionUID = -857616769420775115L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//不创建hibernate_sequence表 // 自动生成
    @Column(length = 20)
    private Long id;

    /**
     * 奖品编号
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "prize",nullable = false, updatable = false)
    private Prize prize;

    /**
     * 描述
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer",nullable = false, updatable = false)
    private User customer;

    /**
     * 中奖时间
     */
    @Column(name="create_date")
    private Date createDate;

    /**
     * 使用时间
     */
    @Column(name="use_date")
    private Date useDate;

    /**
     * 过期时间
     */
    @Column(name="end_date")
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @JoinColumn(nullable = false, updatable = false)
    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    @Override
    public String toString() {
        return "Prize_record{" +
                "id=" + id +
                ", prize=" + prize +
                ", customer=" + customer +
                ", createDate=" + createDate +
                ", useDate=" + useDate +
                ", endDate=" + endDate +
                '}';
    }
}
