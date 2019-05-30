package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.RentA_BicycleManager;

public interface IRentA_BicycleManagerService {
	
	List<RentA_BicycleManager> getAllRentA_BicycleManager();
	
	RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id);
	
	List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager);
	
	List<RentA_BicycleManager> getRentA_BicycleManagerByPage(int pageNum);

}
