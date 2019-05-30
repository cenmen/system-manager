package com.liang.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.BicycleDistributeSituation;
import com.liang.system.service.IBicycleDistributeSituationService;

@RestController
@RequestMapping("/BicycleDistributeSituationCon")
public class BicycleDistributeSituationController {
	@Autowired
	private IBicycleDistributeSituationService bicycleDistributeSituationService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllBicycleDistributeSituation.do", produces="application/json;charset=utf-8")
	public List<BicycleDistributeSituation> getAllBicycleDistributeSituation() {
		List<BicycleDistributeSituation> bicycleDistributeSituationList = bicycleDistributeSituationService.getAllBicycleDistributeSituation();
		return bicycleDistributeSituationList;
	}
	
	@RequestMapping(path="/getBicycleDistributeSituationByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getBicycleDistributeSituationByPageCache")
	public List<BicycleDistributeSituation> getBicycleDistributeSituationByPage(int pageNum) {
		List<BicycleDistributeSituation> bicycleDistributeSituationList = bicycleDistributeSituationService.getBicycleDistributeSituationByPage(pageNum);
		System.out.println(pageNum+","+bicycleDistributeSituationList);
		System.out.println("如果第二次没有走到这里说明缓存被添加了");
		return bicycleDistributeSituationList;
	}
	
	@RequestMapping(path="/getBicycleDistributeSituationById.do", produces="application/json;charset=utf-8")
	public BicycleDistributeSituation getBicycleDistributeSituationById(String bds_id) {
		BicycleDistributeSituation bicycleDistributeSituation = bicycleDistributeSituationService.getBicycleDistributeSituationById(bds_id);
		return bicycleDistributeSituation;
	}
	
	@RequestMapping(path="/getBicycleDistributeSituationByCondition.do", produces="application/json;charset=utf-8")
	public List<BicycleDistributeSituation> getBicycleDistributeSituationByCondition(String bds_id, String bds_rp_id) {
		List<BicycleDistributeSituation> bicycleDistributeSituationList = bicycleDistributeSituationService.getBicycleDistributeSituationByCondition( bds_id , bds_rp_id);
		return bicycleDistributeSituationList;
	}
	
	@RequestMapping(path="/updateBicycleDistributeSituation.do")
	public void updateBicycleDistributeSituation(BicycleDistributeSituation bicycleDistributeSituation) {
		bicycleDistributeSituationService.updateBicycleDistributeSituation(bicycleDistributeSituation);
	}
	
	@RequestMapping(path="/deleteBicycleDistributeSituation.do")
	public void deleteBicycleDistributeSituation(String bds_id) {
		bicycleDistributeSituationService.deleteBicycleDistributeSituation(bds_id);
	}
}
