package com.liang.system.service;

import com.liang.system.beans.Thority;

public interface IThorityService {
	//根据角色获取角色对应的权限
	Thority getThor(int id);
}
