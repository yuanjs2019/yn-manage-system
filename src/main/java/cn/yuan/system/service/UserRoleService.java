package cn.yuan.system.service;

import cn.yuan.common.service.IService;
import cn.yuan.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
