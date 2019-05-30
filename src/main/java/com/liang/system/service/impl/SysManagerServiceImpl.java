package com.liang.system.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.SysManager;
import com.liang.system.mapper.SysManagerMapper;
import com.liang.system.service.ISysManagerService;
import com.liang.system.util.PageModel;


@Service
public class SysManagerServiceImpl implements ISysManagerService{
	@Autowired
	private SysManagerMapper sysManagerMapper;
	
	@Override
	public List<SysManager> getAllSysManager() {
		return sysManagerMapper.getSysManagers();
	}
	
	@Override
	public SysManager getSysManagerById(String sm_id) {
		SysManager sysManager = new SysManager();
		sysManager.setSm_id(sm_id);
		return sysManagerMapper.getSysManagerById(sysManager);
	}
	
	@Override
	public SysManager getSysManagerByAccount(String sm_account , String sm_password) {
		SysManager sysManager = new SysManager();
		sysManager.setSm_account(sm_account);
		sysManager.setSm_password(sm_password);
		return sysManagerMapper.getSysManagerByAccount(sysManager);
	}
	
	@Override
	public SysManager getSysManagerLastData() {
		return sysManagerMapper.getSysManagerLastData();
	}
	
	@Override
	public List<SysManager> getSysManagerByCondition(SysManager sysManager) {
		System.out.println("getByCondition in impl ="+sysManager.getSm_id()+","+sysManager.getSm_account()+","+sysManager.getSm_username()+","+sysManager.getSm_contact()+","+sysManager.isSm_status());
		return sysManagerMapper.getSysManagerByCondition(sysManager);
	}
	
	@Override
	public List<SysManager> getSysManagerByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return sysManagerMapper.getSysManagerByPage(pageModel);
	}
	
	@Override
	public void addSysManager(SysManager sysManager) {
		SysManager sysManagerLastData = sysManagerMapper.getSysManagerLastData();
		String rmIdData = sysManagerLastData.getSm_id().substring(2);
		int i = Integer.parseInt(rmIdData);
		String s = "";
		s=String.valueOf("SM"+(i+1));
		sysManager.setSm_id(s);
		sysManager.setSm_role_id(101);
		Date date = new Date(System.currentTimeMillis());
		sysManager.setSm_create_time(date);
		sysManagerMapper.addSysManager(sysManager);
	}
	
	@Override
	public void updateSysManager(SysManager sysManager) {
		Date date = new Date(System.currentTimeMillis());
		sysManager.setSm_last_time(date);
		sysManagerMapper.updateSysManager(sysManager);
	}
	
	@Override
	public void deleteSysManager(String sm_id) {
		SysManager sysManager = new SysManager();
		sysManager.setSm_id(sm_id);
		sysManagerMapper.deleteSysManager(sysManager);
	}
	
	@Override
	public boolean getSysManagerIsStatus(String sm_account) {
		return sysManagerMapper.getSysManagerIsStatus(sm_account);
	}
	
	@Override
	public Map updateRentManagerPwd(String original_pwd , String new_pwd_1 , String new_pwd_2) {
		Map map =new HashMap();
		SysManager sysManager = new SysManager();
		if(!new_pwd_1.equals(new_pwd_2)) {
			map.put("status", "500");
			return map;
		}
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("sm_username");
		System.out.println("in session toString "+String.valueOf(val));
		String sm_account = String.valueOf(val);
		SysManager sysManagerForTestPwd = sysManagerMapper.getSysManagerByAccountForUpdate(sm_account);
		String pwd = sysManagerForTestPwd.getSm_password();
		if(original_pwd.equals(pwd)) {
			sysManager.setSm_account(sm_account);
			sysManager.setSm_password(new_pwd_2);
			sysManagerMapper.updateSysManagerPwd(sysManager);
			System.out.println("sysManager : "+sysManager);
			map.put("status", "200");
			return map;
		}else {
			map.put("status", "500");
			return map;
		}
	}
}
