package com.river.activiti.dao.mapper;

import com.river.activiti.model.pojo.AccraditationRecord;

public interface AccraditationRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    int insert(AccraditationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    int insertSelective(AccraditationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    AccraditationRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    int updateByPrimaryKeySelective(AccraditationRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table accraditation_record
     *
     * @mbggenerated Tue Nov 07 17:36:06 CST 2017
     */
    int updateByPrimaryKey(AccraditationRecord record);
}