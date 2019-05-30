package com.liang.system.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.BicyclePurchase;
import com.liang.system.beans.BicyclePurchaseBatch;
import com.liang.system.service.IBicyclePurchaseService;

@RestController
@RequestMapping("/BicyclePurchaseCon")
public class BicyclePurchaseController {
	@Autowired
	private IBicyclePurchaseService bicyclePurchaseService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(path="/getAllBicyclePurchase.do", produces="application/json;charset=utf-8")
	public List<BicyclePurchase> getAllBicyclePurchase(HttpServletRequest request) {
		List<BicyclePurchase> bicyclePurchaseList = bicyclePurchaseService.getAllBicyclePurchase();
		int count = bicyclePurchaseService.getBicyclePurchaseCount();
		request.setAttribute("count", count);
		return bicyclePurchaseList;
	}
	
	@RequestMapping(path="/getBicyclePurchaseCount.do", produces="application/json;charset=utf-8")
	public int getBicyclePurchaseCount() {
		int count = bicyclePurchaseService.getBicyclePurchaseCount();
		return count;
	}
	
	@RequestMapping(path="/addBicyclePurchaseBatch.do")
	public void addBicyclePurchaseBatch(BicyclePurchaseBatch bicyclePurchaseBatch) {
		BicyclePurchase lastBicyclePurchase = bicyclePurchaseService.getBicyclePurchaseLastData();
		BicyclePurchase addBicyclePurchase = new BicyclePurchase();
		String bpIdData = lastBicyclePurchase.getBp_id().substring(2);
		int i = Integer.parseInt(bpIdData);
		String s = "";
		for(int a = 1; a<=bicyclePurchaseBatch.getBp_id_batch(); a++) {
			System.out.println("in the for a = "+a);
			s=String.valueOf("BP"+(i+a));
			System.out.println("in the for s = "+s);
			addBicyclePurchase.setBp_id(s);
			addBicyclePurchase.setBp_brand(bicyclePurchaseBatch.getBp_brand_batch());
			addBicyclePurchase.setBp_model(bicyclePurchaseBatch.getBp_model_batch());
			addBicyclePurchase.setBp_dateOfProduction(bicyclePurchaseBatch.getBp_dateOfProduction_batch());
			addBicyclePurchase.setBp_dateOfPurchase(bicyclePurchaseBatch.getBp_dateOfPurchase_batch());
			addBicyclePurchase.setBp_isallocated(bicyclePurchaseBatch.isBp_isallocated_batch());
			addBicyclePurchase.setBp_mark(bicyclePurchaseBatch.getBp_mark_batch());
			bicyclePurchaseService.addBicyclePurchase(addBicyclePurchase);
		}
	}
	
	@RequestMapping(path="/getBicyclePurchaseById.do")
	public BicyclePurchase getBicyclePurchaseById(String bp_id) {
		return bicyclePurchaseService.getBicyclePurchaseById(bp_id);
	}
	
	@RequestMapping(path="/getBicyclePurchaseByCondition.do", produces="application/json;charset=utf-8")
	public List<BicyclePurchase> getBicyclePurchaseByCondition(BicyclePurchase bicyclePurchase) {
		List<BicyclePurchase> bicycleDistributeSituationList = bicyclePurchaseService.getBicyclePurchaseByCondition(bicyclePurchase);
		return bicycleDistributeSituationList;
	}
	
	@RequestMapping(path="/getBicyclePurchaseByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getBicyclePurchaseByPageCache")
	public List<BicyclePurchase> getBicyclePurchaseByPage(int pageNum) {
		List<BicyclePurchase> bicyclePurchaseList = bicyclePurchaseService.getBicyclePurchaseByPage(pageNum);
		return bicyclePurchaseList;
	}
	
	@RequestMapping(path="/addBicyclePurchase.do")
	public void addBicyclePurchase(BicyclePurchase bicyclePurchase) {
		bicyclePurchaseService.addBicyclePurchase(bicyclePurchase);
	}
	
	@RequestMapping(path="/updateBicyclePurchase.do")
	public void updateBicyclePurchase(BicyclePurchase bicyclePurchase) {
		bicyclePurchaseService.updateBicyclePurchase(bicyclePurchase);
	}
	
	@RequestMapping(path="/updateDistributeBicyclePurchase.do")
	public boolean updateDistributeBicyclePurchase(String[] bp_id, String rbs_rp_id) {
		return bicyclePurchaseService.updateDistributeBicyclePurchase(bp_id, rbs_rp_id);
	}
	
	@RequestMapping(path="/deleteBicyclePurchase.do")
	public void deleteBicyclePurchase(String bp_id) {
		bicyclePurchaseService.deleteBicyclePurchase(bp_id);
	}
}
