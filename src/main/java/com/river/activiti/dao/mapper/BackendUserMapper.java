package com.river.activiti.dao.mapper;

import com.river.activiti.model.pojo.BackendUser;

public interface BackendUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    int insert(BackendUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    int insertSelective(BackendUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    BackendUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    int updateByPrimaryKeySelective(BackendUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table backend_user
     *
     * @mbggenerated Tue Dec 05 14:14:24 CST 2017
     */
    int updateByPrimaryKey(BackendUser record);
}