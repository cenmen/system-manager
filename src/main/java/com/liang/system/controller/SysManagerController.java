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

import com.liang.system.beans.SysManager;
import com.liang.system.service.ISysManagerService;


@RestController
@RequestMapping("/SysManagerCon")
public class SysManagerController {
	@Autowired
	private ISysManagerService sysManagerService;
	@Autowired	
	RedisTemplate<String, Object> template;
	
	//只需要加上下面这段即可，注意不能忘记注解
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@RequestMapping(path="/SysManagerLogin.do", produces="application/json;charset=utf-8")
	public Map login(String sm_username, String sm_password , HttpSession session) {
		Map map =new HashMap();
		//第一步：创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken(sm_username, sm_password); //james , 234
		//第二步：获取Subject对象，该对象封装了一系列的操作
		Subject subject = SecurityUtils.getSubject();
		//第三步：执行认证
		try {
			subject.login(token);
			session.setAttribute("sm_username" ,sm_username);
			System.out.println("登录成功");
			map.put("status", "200");
			map.put("message", "登录成功");
			return map;
		} catch (AuthenticationException e) {
			map.put("status", "500");
			map.put("message", "登录失败");
		}
		return map;
	}
	
	@RequestMapping(path="/SysManagerLogout.do", produces="application/json;charset=utf-8")
	public Map logout(HttpSession session) {
		//session.removeAttribute("user"); //删除Session的user属性
		//session.invalidate(); //把整个session销毁
		Map map =new HashMap();
		SecurityUtils.getSubject().logout();
		map.put("status", "200");
		map.put("message", "注销成功");
		return map;
	}
	
	@RequestMapping(path="/getAllSysManager.do", produces="application/json;charset=utf-8")
	public List<SysManager> getAllSysManager() {
		List<SysManager> sysManagerList = sysManagerService.getAllSysManager();
		return sysManagerList;
	}
	
	@RequestMapping(path="/getSysManagerById.do")
	public SysManager getSysManagerById(String sm_id) {
		return sysManagerService.getSysManagerById(sm_id);
	}
	
	@RequestMapping(path="/getSysManagerByCondition.do", produces="application/json;charset=utf-8")
	public List<SysManager> getSysManagerByCondition(SysManager sysManager) {
		System.out.println("params : "+sysManager.getSm_id()+sysManager.getSm_account()+"1");
		List<SysManager> sysManagerList = sysManagerService.getSysManagerByCondition(sysManager);
		return sysManagerList;
	}
	
	@RequestMapping(path="/getSysManagerByPage.do", produces="application/json;charset=utf-8")
	@CachePut("getSysManagerByPageCache")
	public List<SysManager> getSysManagerByPage(int pageNum) {
		List<SysManager> sysManagerList = sysManagerService.getSysManagerByPage(pageNum);
		return sysManagerList;
	}
	
	@RequestMapping(path="/addSysManager.do")
	public void addSysManager(SysManager sysManager) {
		sysManagerService.addSysManager(sysManager);
	}
	
	@RequestMapping(path="/updateSysManager.do")
	public void updateSysManager(SysManager sysManager) {
		sysManagerService.updateSysManager(sysManager);
	}
	
	@RequestMapping(path="/deleteSysManager.do")
	public void deleteSysManager(String sm_id) {
		sysManagerService.deleteSysManager(sm_id);
	}
	
	@RequestMapping(path="/updateSysManagerPwd.do")
	public Map updateRentManagerPwd(String original_pwd , String new_pwd_1 , String new_pwd_2) {
		return sysManagerService.updateRentManagerPwd(original_pwd,new_pwd_1,new_pwd_2);
	}
}
