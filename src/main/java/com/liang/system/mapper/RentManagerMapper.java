package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.RentManager;
import com.liang.system.util.PageModel;


public interface RentManagerMapper {
	
	@Select("select * from rent_manager")
	List<RentManager> getRentManagers();
	
	@Select("select * from rent_manager where rm_id = #{rm_id}")
	RentManager getRentManagerById(RentManager rentManager);
	
	@Select("select * from rent_manager where rm_account = #{rm_account} and rm_password = #{rm_password}")
	RentManager getRentManagerByAccount(RentManager rentManager);
	
	@Select("select * from rent_manager order by rm_id DESC limit 1")
	RentManager getRentManagerLastData();
	
	@Select("<script>"
            + "select * from `rent_manager`"
            + "<where>"
            + "<if test=\"rm_id != null and rm_id != '' \">AND rm_id = #{rm_id}</if>"
            + "<if test=\"rm_account != null and rm_account != '' \">AND rm_account = #{rm_account}</if>"
            + "<if test=\"rm_username != null and rm_username != '' \">AND rm_username = #{rm_username}</if>"
            + "<if test=\"rm_contact != null and rm_contact != '' \">AND rm_contact = #{rm_contact}</if>"
            + "<if test=\"rm_status != null\">AND rm_status = #{rm_status}</if>"
            + "<if test=\"rm_rp_id != null and rm_rp_id != '' \">AND rm_rp_id = #{rm_rp_id}</if>"
            + "</where>"
            + "</script>")
	List<RentManager> getRentManagerByCondition(RentManager rentManager);
	
	@Select("select * from rent_manager limit #{start}, #{pageSize}")
	List<RentManager> getRentManagerByPage(PageModel pageModel);
	
	@Insert("insert into rent_manager values(#{rm_id}, #{rm_role_id}, #{rm_account}, #{rm_password}, #{rm_username}, #{rm_contact}, #{rm_create_time}, #{rm_last_time}, #{rm_status}, #{rm_rp_id}, #{rm_mark})")
	void addRentManager(RentManager rentManager);
	
	//修改租借点管理员的密码，用户名，联系方式，账户状态，备注
	@Update("update rent_manager set rm_password = #{rm_password}, rm_username = #{rm_username}, rm_contact = #{rm_contact}, rm_status = #{rm_status} , rm_mark = #{rm_mark} where rm_id = #{rm_id}")
	void updateRentManager(RentManager rentManager);
	
	@Delete("delete from rent_manager where rm_id = #{rm_id}")
	void deleteRentManager(RentManager rentManager);
}
