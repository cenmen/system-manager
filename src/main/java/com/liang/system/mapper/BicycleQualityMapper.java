package com.liang.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liang.system.beans.BicycleQuality;
import com.liang.system.beans.RentA_BicycleStatus;
import com.liang.system.util.PageModel;

public interface BicycleQualityMapper {
	
	@Select("select * from bicycle_quality")
	List<BicycleQuality> getBicycleQualitys();
	
	@Select("select * from bicycle_quality where bq_id = #{bq_id}")
	BicycleQuality getBicycleQualityById(BicycleQuality bicycleQuality);
	
	@Select("<script>"
            + "select * from `bicycle_quality`"
            + "<where>"
            + "<if test=\"bq_bp_id != null and bq_bp_id != '' \">AND bq_bp_id = #{bq_bp_id}</if>"
            + "<if test=\"bq_rp_id != null and bq_rp_id != '' \">AND bq_rp_id = #{bq_rp_id}</if>"
            + "<if test=\"bq_ishandle != null\"> AND bq_ishandle = #{bq_ishandle}</if>"
            + "<if test=\"bq_isremand != null\"> AND bq_isremand = #{bq_isremand}</if>"
            + "</where>"
            + "</script>")
	List<BicycleQuality> getBicycleQualityByCondition(BicycleQuality bicycleQuality);
	
	@Select("select * from bicycle_quality limit #{start}, #{pageSize}")
	List<BicycleQuality> getBicycleQualityByPage(PageModel pageModel);
	
	@Insert("insert into bicycle_quality values(#{bq_id}, #{bq_bp_id}, #{bq_rp_id}, #{bq_trouble}, #{bq_ishandle}, #{bq_isremand})")
	void addBicycleQuality(BicycleQuality bicycleQuality);
	
	@Update("update bicycle_quality set bq_bp_id = #{bq_bp_id}, bq_rp_id = #{bq_rp_id}, bq_trouble = #{bq_trouble}, bq_ishandle = #{bq_ishandle}, bq_isremand = #{bq_isremand} where bq_id = #{bq_id}")
	void updateBicycleQuality(BicycleQuality bicycleQuality);
	
	@Delete("delete from bicycle_quality where bq_id = #{bq_id}")
	void deleteBicycleQuality(BicycleQuality bicycleQuality);
	
	@Update("update rent_a_bicycle_status set rbs_bicycle_quality_status = #{rbs_bicycle_quality_status} where rbs_bp_id = #{rbs_bp_id}")
	void updateRentA_BicycleStatusToNormal(RentA_BicycleStatus rentA_BicycleStatus);
}
