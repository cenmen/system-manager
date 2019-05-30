package com.liang.system.application;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liang.system.beans.BicycleDistributeSituation;
import com.liang.system.beans.SysManager;
import com.liang.system.beans.Thority;
import com.liang.system.service.IBicycleDistributeSituationService;
import com.liang.system.service.ISysManagerService;
import com.liang.system.service.IThorityService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class test {
	@Autowired
	private IThorityService thorityService;
	@Autowired
	private ISysManagerService sysManagerService;
	@Autowired
	private IBicycleDistributeSituationService bicycleDistributeSituationService;
	
	//测试 ISysManagerService
	@Test
	public void test() {
		 List<BicycleDistributeSituation> list = bicycleDistributeSituationService.getBicycleDistributeSituationByPage(1);
		 System.out.println("list in test" + list);
	}
}
