package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.SysManager;
import com.liang.system.util.PageModel;


public interface SysManagerMapper {
	
	@Select("select * from sys_manager")
	List<SysManager> getSysManagers();
	
	@Select("select * from sys_manager where sm_id = #{sm_id}")
	SysManager getSysManagerById(SysManager sysManager);
	
	@Select("select * from sys_manager where sm_account = #{sm_account} and sm_password = #{sm_password}")
	SysManager getSysManagerByAccount(SysManager sysManager);
	
	@Select("select * from sys_manager order by sm_id DESC limit 1")
	SysManager getSysManagerLastData();
	
	@Select("<script>"
            + "select * from `sys_manager`"
			+"<where>"
            + "<if test=\"sm_id != null and sm_id != '' \">sm_id = #{sm_id}</if>"
            + "<if test=\"sm_account != null and sm_account != '' \">AND sm_account = #{sm_account}</if>"
            + "<if test=\"sm_username != null and sm_username != '' \">AND sm_username = #{sm_username}</if>"
            + "<if test=\"sm_contact != null and sm_contact != '' \">AND sm_contact = #{sm_contact}</if>"
            + "<if test=\"sm_status != null \">AND sm_status = #{sm_status}</if>"
            +"</where>"
            + "</script>")
	List<SysManager> getSysManagerByCondition(SysManager sysManager);
	
	@Select("select * from sys_manager limit #{start}, #{pageSize}")
	List<SysManager> getSysManagerByPage(PageModel pageModel);
	
	@Insert("insert into sys_manager values(#{sm_id}, #{sm_role_id}, #{sm_account}, #{sm_password}, #{sm_username}, #{sm_contact}, #{sm_create_time}, #{sm_last_time}, #{sm_status}, #{sm_mark})")
	void addSysManager(SysManager sysManager);
	
	//修改系统管理员的密码，用户名，联系方式，账户状态，备注
	@Update("update sys_manager set sm_username = #{sm_username}, sm_contact = #{sm_contact},  sm_last_time = #{sm_last_time}, sm_status = #{sm_status} , sm_mark = #{sm_mark} where sm_id = #{sm_id}")
	void updateSysManager(SysManager sysManager);
	
	@Delete("delete from sys_manager where sm_id = #{sm_id}")
	void deleteSysManager(SysManager sysManager);
	
	@Select("select sm_status from sys_manager where sm_account = #{sm_account}")
	boolean getSysManagerIsStatus(String sm_account);
	
	@Update("update sys_manager set  sm_password = #{sm_password} where sm_account = #{sm_account}")
	void updateSysManagerPwd(SysManager sysManager);
	
	@Select("select * from sys_manager where sm_account = #{sm_account}")
	SysManager getSysManagerByAccountForUpdate(String sm_account);
}
