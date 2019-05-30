package com.liang.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.RentA_BicycleManager;
import com.liang.system.service.IRentA_BicycleManagerService;


@RestController
@RequestMapping("/RentA_BicycleManagerCon")
public class RentA_BicycleManagerController {
	@Autowired
	private IRentA_BicycleManagerService rentA_BicycleManagerService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllRentA_BicycleManager.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleManager> getAllRentA_BicycleManager() {
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getAllRentA_BicycleManager();
		System.out.println(rentA_BicycleManagerList);
		return rentA_BicycleManagerList;
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerById.do")
	public RentA_BicycleManager getRentA_BicycleManagerById(int rbm_id) {
		return rentA_BicycleManagerService.getRentA_BicycleManagerById(rbm_id);
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerByCondition.do", produces="application/json;charset=utf-8")
	public List<RentA_BicycleManager> getRentA_BicycleManagerByCondition(RentA_BicycleManager rentA_BicycleManager) {
		System.out.println("in getByCondition_rbm"+rentA_BicycleManager);
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getRentA_BicycleManagerByCondition(rentA_BicycleManager);
		System.out.println("in getByCondition_bds"+rentA_BicycleManagerList);
		return rentA_BicycleManagerList;
	}
	
	@RequestMapping(path="/getRentA_BicycleManagerByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentA_BicycleManagerByPageCache")
	public List<RentA_BicycleManager> getRentA_BicycleManagerByPage(int pageNum) {
		System.out.println("in controller");
		List<RentA_BicycleManager> rentA_BicycleManagerList = rentA_BicycleManagerService.getRentA_BicycleManagerByPage(pageNum);
		System.out.println(pageNum+","+rentA_BicycleManagerList);
		return rentA_BicycleManagerList;
	}
}