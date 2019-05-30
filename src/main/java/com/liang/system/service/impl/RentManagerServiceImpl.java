package com.liang.system.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.RentManager;
import com.liang.system.mapper.RentManagerMapper;
import com.liang.system.service.IRentManagerService;
import com.liang.system.util.PageModel;

@Service
public class RentManagerServiceImpl implements IRentManagerService {
	@Autowired
	private RentManagerMapper rentManagerMapper;
	
	@Override
	public List<RentManager> getAllRentManager() {
		return rentManagerMapper.getRentManagers();
	}
	
	@Override
	public RentManager getRentManagerById(String rm_id) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_id(rm_id);
		return rentManagerMapper.getRentManagerById(rentManager);
	}
	
	@Override
	public RentManager getRentManagerByAccount(String rm_account , String rm_password) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_account(rm_account);
		return rentManagerMapper.getRentManagerByAccount(rentManager);
	}
	
	@Override
	public RentManager getRentManagerLastData() {
		return rentManagerMapper.getRentManagerLastData();
	}
	
	@Override
	public List<RentManager> getRentManagerByCondition(RentManager rentManager) {
		return rentManagerMapper.getRentManagerByCondition(rentManager);
	}
	
	@Override
	public List<RentManager> getRentManagerByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return rentManagerMapper.getRentManagerByPage(pageModel);
	}
	
	@Override
	public void addRentManager(RentManager rentManager) {
		RentManager rentManagerLastData = rentManagerMapper.getRentManagerLastData();
		String rmIdData = rentManagerLastData.getRm_id().substring(2);
		int i = Integer.parseInt(rmIdData);
		String s = "";
		s=String.valueOf("RM"+(i+1));
		rentManager.setRm_id(s);
		rentManager.setRm_role_id(102);
		Date date = new Date(System.currentTimeMillis());
		rentManager.setRm_create_time(date);
		rentManagerMapper.addRentManager(rentManager);
	}
	
	@Override
	public void updateRentManager(RentManager rentManager) {
		Date date = new Date(System.currentTimeMillis());
		rentManager.setRm_last_time(date);
		rentManagerMapper.updateRentManager(rentManager);
	}
	
	@Override
	public void deleteRentManager(String rm_id) {
		RentManager rentManager = new RentManager();
		rentManager.setRm_id(rm_id);
		rentManagerMapper.deleteRentManager(rentManager);
	}
}
