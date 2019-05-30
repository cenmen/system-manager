package com.liang.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.BicycleQuality;
import com.liang.system.service.IBicycleQualityService;

@RestController
@RequestMapping("/BicycleQualityCon")
public class BicycleQualityController {
	@Autowired
	private IBicycleQualityService bicycleQualityService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@RequestMapping(path="/getAllBicycleQuality.do", produces="application/json;charset=utf-8")
	public List<BicycleQuality> getAllBicycleQuality() {
		List<BicycleQuality> bicycleQualityList = bicycleQualityService.getAllBicycleQuality();
		System.out.println(bicycleQualityList);
		return bicycleQualityList;
	}
	
	@RequestMapping(path="/getBicycleQualityById.do")
	public BicycleQuality getBicycleQualityById(int bq_id) {
		return bicycleQualityService.getBicycleQualityById(bq_id);
	}
	
	@RequestMapping(path="/getBicycleQualityByCondition.do", produces="application/json;charset=utf-8")
	public List<BicycleQuality> getBicycleQualityByCondition(BicycleQuality bicycleQuality) {
		System.out.println("in getByCondition_bq"+bicycleQuality);
		List<BicycleQuality> bicycleDistributeSituationList = bicycleQualityService.getBicycleQualityByCondition(bicycleQuality);
		System.out.println("in getByCondition_bds"+bicycleDistributeSituationList);
		return bicycleDistributeSituationList;
	}
	
	@RequestMapping(path="/getBicycleQualityByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getBicycleQualityByPageCache")
	public List<BicycleQuality> getBicycleQualityByPage(int pageNum) {
		List<BicycleQuality> bicycleQualityList = bicycleQualityService.getBicycleQualityByPage(pageNum);
		return bicycleQualityList;
	}
	
	@RequestMapping(path="/addBicycleQuality.do")
	public boolean addBicycleQuality(BicycleQuality bicycleQuality) {
		return bicycleQualityService.addBicycleQuality(bicycleQuality);
	}
	
	@RequestMapping(path="/updateBicycleQuality.do")
	public void updateBicycleQuality(BicycleQuality bicycleQuality) {
		bicycleQualityService.updateBicycleQuality(bicycleQuality);
	}
	
	@RequestMapping(path="/deleteBicycleQuality.do")
	public void deleteBicycleQuality(int bq_id) {
		bicycleQualityService.deleteBicycleQuality(bq_id);
	}
}
