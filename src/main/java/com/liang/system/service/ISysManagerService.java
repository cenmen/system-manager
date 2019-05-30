package com.liang.system.service;

import java.util.List;
import java.util.Map;

import com.liang.system.beans.SysManager;


public interface ISysManagerService {
	
	List<SysManager> getAllSysManager();
	
	SysManager getSysManagerById(String sm_id);
	
	SysManager getSysManagerByAccount(String sm_account , String sm_password);
	
	SysManager getSysManagerLastData();
	
	List<SysManager> getSysManagerByCondition(SysManager sysManager);
	
	List<SysManager> getSysManagerByPage(int pageNum);
	
	void addSysManager(SysManager sysManager);
	
	void updateSysManager(SysManager sysManager);
	
	void deleteSysManager(String sm_id);
	
	boolean getSysManagerIsStatus(String sm_account);
	
	Map updateRentManagerPwd(String original_pwd , String new_pwd_1 , String new_pwd_2);
}
