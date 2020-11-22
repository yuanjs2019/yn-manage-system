package cn.yuan.system.service;

import java.util.List;

import cn.yuan.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
