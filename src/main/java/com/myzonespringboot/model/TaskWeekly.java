package com.myzonespringboot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="taskWeekly")
public class TaskWeekly implements Serializable{
    

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1252148870446365282L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//不创建hibernate_sequence表 // 自动生成,当你插入时会自动更新idOK??? 恩
	@Column(length = 20)
	private Long id;
	
	@Column(length = 255)
    private String event;
	
	@Column(length = 20,nullable=false)
    private String cron;

    @Column(length = 255)
    private String template;

	@Column(length = 255)
	private String description;

	@Column
    private Date createDate;
	
	@Column
    private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "TaskWeekly{" +
				"id=" + id +
				", event='" + event + '\'' +
				", cron='" + cron + '\'' +
				", template='" + template + '\'' +
				", description='" + description + '\'' +
				", createDate=" + createDate +
				", updateDate=" + updateDate +
				'}';
	}
}