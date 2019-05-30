package com.liang.system.service;

import java.util.List;

import com.liang.system.beans.RentManager;


public interface IRentManagerService {
	
	List<RentManager> getAllRentManager();
	
	RentManager getRentManagerById(String rm_id);
	
	RentManager getRentManagerByAccount(String rm_account , String rm_password);
	
	RentManager getRentManagerLastData();
	
	List<RentManager> getRentManagerByCondition(RentManager rentManager);
	
	List<RentManager> getRentManagerByPage(int pageNum);
	
	void addRentManager(RentManager rentManager);
	
	void updateRentManager(RentManager rentManager);
	
	void deleteRentManager(String rm_id);
}
