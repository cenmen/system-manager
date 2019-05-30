package com.liang.system.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.system.beans.RentA_BicycleManager;
import com.liang.system.mapper.RentA_BicycleManagerMapper;
import com.liang.system.service.IRentA_BicycleManagerService;
import com.liang.system.util.PageModel;

@Service
public class RentA_BicycleManagerServiceImpl implements IRentA_BicycleManagerService{
	
	@Autowired
	private RentA_BicycleManagerMapper rentA_BicycleManagerMapper;
	
	@Override
	public List<RentA_BicycleManager> getAllRentA_BicycleManager() {
		return rentA_BicycleManagerMapper.getRentA_BicycleManagers();
	}
	
	@Override
	public RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id) {
		RentA_BicycleManager rentA_BicycleManager = new RentA_BicycleManager();
		rentA_BicycleManager.setRbm_id(rbm_id);
		return rentA_BicycleManagerMapper.getRentA_BicycleManagerById(rentA_BicycleManager);
	}
	
	@Override
	public List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager) {
		return rentA_BicycleManagerMapper.getRentA_BicycleManagerByCondition(rentA_BicycleManager);
	}
	
	@Override
	public List<RentA_BicycleManager> getRentA_BicycleManagerByPage(int pageNum) {
		//计算开始查询的位置
		int start = (pageNum - 1) * 12;
		PageModel pageModel =new PageModel();
		pageModel.setStart(start);
		pageModel.setPageSize(12);
		return rentA_BicycleManagerMapper.getRentA_BicycleManagerByPage(pageModel);
	}
}
