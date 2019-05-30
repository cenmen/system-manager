package com.liang.system.beans;

import java.sql.Date;

import lombok.Data;

@Data
public class RentPlace {
	private String rp_id;
	private String rp_name;
	private String rp_place;
	private Date rp_establish_time;
	private String rp_mark;
}
