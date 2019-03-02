package com.mall.forum.service.impl;

import com.mall.forum.dao.GroupDao;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.Role;
import com.mall.forum.modle.UserSession;
import com.mall.forum.security.RoleManager;
import com.mall.forum.service.GroupService;
import com.mall.forum.util.SecurityConstants;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.forum.core.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by cuilidong on 2019/2/2.
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserSession userSession;

    //private SessionManager sessionManager;

    public List<Group> selectAll(){
       return groupDao.selectAll();
    }

    public void savePermissions(int groupId, Map<String, Map<String, List<?>>> map) {

        Group group = this.groupDao.selectById(groupId);
        RoleManager currentRoles = new RoleManager();

        currentRoles.setGroups(Arrays.asList(group));

        group.getRoles().clear();

        boolean isAdministrator = currentRoles.isAdministrator();
        boolean canManageForums = currentRoles.roleExists(SecurityConstants.CAN_MANAGE_FORUMS);
        boolean isCoAdministrator = currentRoles.isCoAdministrator();

        List<Integer> groups = new ArrayList<Integer>();
        for (int gid : currentRoles.getRoleValues(SecurityConstants.GROUPS)) {
            groups.add(gid);
        }

        boolean canInteractwithOtherGroups = currentRoles.roleExists(SecurityConstants.INTERACT_OTHER_GROUPS);
        boolean isSuperAdministrator = this.userSession.getRoleManager().isAdministrator();

        for (Map.Entry<String, List<?>> entry : map.get("boolean").entrySet()) {
            String key = entry.getKey();
            Boolean value = (Boolean)entry.getValue().get(0);

            if (SecurityConstants.ADMINISTRATOR.equals(key)) {
                registerRole(group, key, isSuperAdministrator ? value : isAdministrator);
            }
            else if (SecurityConstants.CAN_MANAGE_FORUMS.equals(key)) {
                registerRole(group, key, isSuperAdministrator ? value : canManageForums);
            }
            else if (SecurityConstants.CO_ADMINISTRATOR.equals(key)) {
                registerRole(group, key, isSuperAdministrator ? value : isCoAdministrator);
            }
            else if (SecurityConstants.INTERACT_OTHER_GROUPS.equals(key)) {
                registerRole(group, key, isSuperAdministrator ? value : canInteractwithOtherGroups);
            }
            else {
                registerRole(group, key, (Boolean)entry.getValue().get(0));
            }
        }

        for (Map.Entry<String, List<?>> entry : map.get("multiple").entrySet()) {
            String key = entry.getKey();
            List<Integer> value = (List<Integer>) entry.getValue();

            if (SecurityConstants.GROUPS.equals(key)) {
                registerRole(group, key, isSuperAdministrator ? value : groups);
            }
            else {
                registerRole(group, key, value);
            }
        }

    }


    public void appendRole(Group group, String roleName, int roleValue) {
        for (Role role : group.getRoles()) {
            if (role.getName().equals(roleName)) {
                role.getRoleValues().add(roleValue);
                break;
            }
        }

        this.groupDao.update(group);
    }

    private void applyCommonConstraints(Group group) {
        if (group == null) {
            throw new NullPointerException("Cannot save a null group");
        }

        if (StringUtils.isEmpty(group.getName())) {
            throw new ValidationException("A group should have a name");
        }
    }


    private void registerRole(Group group, String name, List<Integer> values) {
        if (values.size() > 0) {
            group.addRole(this.createRole(name, values));
        }
    }

    private void registerRole(Group group, String name, boolean isAllowed) {
        if (isAllowed) {
            group.addRole(this.createRole(name, null));
        }
    }

    private Role createRole(String name, List<Integer> values) {
        Role role = new Role();
        role.setName(name);

        if (values != null) {
            for (int value : values) {
                role.addRoleValue(value);
            }
        }

        return role;
    }


}
