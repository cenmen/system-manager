package com.liang.system.beans;

import lombok.Data;

@Data
public class BicycleQuality {
	private int bq_id;
	private String bq_bp_id;
	private String bq_rp_id;
	private String bq_trouble;
	private boolean bq_ishandle;
	private boolean bq_isremand;
}
