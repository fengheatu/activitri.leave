package com.river.activiti.group.config;

import com.river.activiti.dao.mapper.BackendRoleMapper;
import com.river.activiti.dao.mapper.BackendUserMapper;
import com.river.activiti.model.pojo.BackendRole;
import com.river.activiti.model.pojo.BackendUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: he.feng
 * @date: 14:37 2017/12/5
 * @desc:
 **/
@Component
public class CustomUserEntityManager extends UserEntityManager {

    @Resource
    private BackendUserMapper backendUserMapper;

    @Resource
    private BackendRoleMapper backendRoleMapper;

    @Override
    public List<Group> findGroupsByUser(String userId) {
        Long id = Long.valueOf(userId);
        if (StringUtils.isBlank(userId)) {
            return null;
        }
        List<BackendRole> backendRoles = backendRoleMapper.queryBackendRoleByUserId(id);
        List<Group> groups = new ArrayList<Group>();
        GroupEntity groupEntity = null;
        for (BackendRole backendRole : backendRoles) {
            groupEntity = new GroupEntity();
            groupEntity.setRevision(1);
            groupEntity.setType("assignment");
            groupEntity.setName(backendRole.getEnName());
            groups.add(groupEntity);
        }


        return groups;
    }

    @Override
    public User findUserById(String userId) {
        BackendUser backendUser = backendUserMapper.selectByPrimaryKey(Long.valueOf(userId));
        UserEntity userEntity = new UserEntity();
        userEntity.setId(String.valueOf(backendUser.getId()));
        return userEntity;
    }
}
