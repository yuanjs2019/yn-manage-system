package cn.yuan.system.dao;

import java.util.List;

import cn.yuan.common.config.MyMapper;
import cn.yuan.system.domain.User;
import cn.yuan.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}