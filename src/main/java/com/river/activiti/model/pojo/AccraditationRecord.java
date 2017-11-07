package com.river.activiti.model.pojo;

import java.util.Date;

public class AccraditationRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.leave_id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Long leaveId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.name
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.accraditation_opinion
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private String accraditationOpinion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.is_agree
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Byte isAgree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.create_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Date createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.update_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Date updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.gmt_create
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.gmt_modified
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accraditation_record.is_delete
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    private Byte isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.id
     *
     * @return the value of accraditation_record.id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.id
     *
     * @param id the value for accraditation_record.id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.leave_id
     *
     * @return the value of accraditation_record.leave_id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Long getLeaveId() {
        return leaveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.leave_id
     *
     * @param leaveId the value for accraditation_record.leave_id
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.name
     *
     * @return the value of accraditation_record.name
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.name
     *
     * @param name the value for accraditation_record.name
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.accraditation_opinion
     *
     * @return the value of accraditation_record.accraditation_opinion
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public String getAccraditationOpinion() {
        return accraditationOpinion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.accraditation_opinion
     *
     * @param accraditationOpinion the value for accraditation_record.accraditation_opinion
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setAccraditationOpinion(String accraditationOpinion) {
        this.accraditationOpinion = accraditationOpinion == null ? null : accraditationOpinion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.is_agree
     *
     * @return the value of accraditation_record.is_agree
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Byte getIsAgree() {
        return isAgree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.is_agree
     *
     * @param isAgree the value for accraditation_record.is_agree
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setIsAgree(Byte isAgree) {
        this.isAgree = isAgree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.create_by
     *
     * @return the value of accraditation_record.create_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Date getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.create_by
     *
     * @param createBy the value for accraditation_record.create_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setCreateBy(Date createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.update_by
     *
     * @return the value of accraditation_record.update_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Date getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.update_by
     *
     * @param updateBy the value for accraditation_record.update_by
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setUpdateBy(Date updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.gmt_create
     *
     * @return the value of accraditation_record.gmt_create
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.gmt_create
     *
     * @param gmtCreate the value for accraditation_record.gmt_create
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.gmt_modified
     *
     * @return the value of accraditation_record.gmt_modified
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.gmt_modified
     *
     * @param gmtModified the value for accraditation_record.gmt_modified
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accraditation_record.is_delete
     *
     * @return the value of accraditation_record.is_delete
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accraditation_record.is_delete
     *
     * @param isDelete the value for accraditation_record.is_delete
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}