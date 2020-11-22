package cn.yuan.system.dao;

import java.util.List;

import cn.yuan.common.config.MyMapper;
import cn.yuan.system.domain.Role;
import cn.yuan.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}