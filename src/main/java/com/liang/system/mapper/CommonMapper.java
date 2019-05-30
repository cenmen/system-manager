package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.BicycleQuality;
import com.liang.system.beans.Information;
import com.liang.system.beans.RentManager;
import com.liang.system.util.PageModel;

public interface CommonMapper {
	
	//查询所有租借点ID
	@Select("select rp_id from rent_place")
	List<String> getAllRp_idFromRP();
	
	//查询所有车辆ID
	@Select("select bp_id from bicycle_purchase")
	List<String> getAllBp_idFromBP();
	
	//获取最后一条租借点ID
	@Select("select rp_id from rent_place order by rp_id DESC limit 1")
	String getLastRp_idFromRP();
	
	//根据车辆ID查询车辆所在租借点ID
	@Select("select rbs_rp_id from rent_a_bicycle_status where rbs_bp_id = #{rbs_bp_id}")
	String getRp_idByBp_id(String rbs_bp_id);
	
	//查询消息
	@Select("select * from information")
	List<Information> getAllInformation();
	
	//查询系统账号和未读的消息
	@Select("select * from information where info_to = #{info_to} and info_status = false")
	List<Information> getAllInformationByInfoTo(String info_to);
	
	//增加消息
	@Insert("insert into information values(#{info_id}, #{info_from}, #{info_to}, #{info_type}, #{info_content}, #{info_status})")
	void addInformation(Information information);
	
	//修改消息状态
	@Update("update information set info_status = #{info_status} where info_id = #{info_id}")
	boolean updateInformationStatus(Information information);
	
}
