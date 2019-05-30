package com.liang.system.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liang.system.beans.RentManager;
import com.liang.system.service.IRentManagerService;


@RestController
@RequestMapping("/RentManagerCon")
public class RentManagerController {
	@Autowired
	private IRentManagerService rentManagerService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(path="/getAllRentManager.do", produces="application/json;charset=utf-8")
	public List<RentManager> getAllRentManager() {
		List<RentManager> rentManagerList = rentManagerService.getAllRentManager();
		System.out.println(rentManagerList);
		return rentManagerList;
	}
	
	@RequestMapping(path="/getRentManagerById.do")
	public RentManager getRentManagerById(String rm_id) {
		return rentManagerService.getRentManagerById(rm_id);
	}
	
	@RequestMapping(path="/getRentManagerByCondition.do", produces="application/json;charset=utf-8")
	public List<RentManager> getRentManagerByCondition(RentManager rentManager) {
		List<RentManager> rentManagerList = rentManagerService.getRentManagerByCondition(rentManager);
		System.out.println("in getByCondition_bds"+rentManagerList);
		return rentManagerList;
	}
	
	@RequestMapping(path="/getRentManagerByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getRentManagerByPageCache")
	public List<RentManager> getRentManagerByPage(int pageNum) {
		List<RentManager> rentManagerList = rentManagerService.getRentManagerByPage(pageNum);
		System.out.println(pageNum+","+rentManagerList);
		return rentManagerList;
	}
	
	@RequestMapping(path="/addRentManager.do")
	public void addRentManager(RentManager rentManager) {
		System.out.println(rentManager);
		rentManagerService.addRentManager(rentManager);
	}
	
	@RequestMapping(path="/updateRentManager.do")
	public void updateRentManager(RentManager rentManager) {
		rentManagerService.updateRentManager(rentManager);
	}
	
	@RequestMapping(path="/deleteRentManager.do")
	public void deleteRentManager(String rm_id) {
		rentManagerService.deleteRentManager(rm_id);
	}
}
