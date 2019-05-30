package com.liang.system.beans;

import java.sql.Date;

import lombok.Data;

@Data
public class BicycleDistributeSituation {
	private String bds_id;
	private String bds_rp_id;
	private int bds_distribute_num;
	private int dbs_intend_num;
	private String dbs_dispatch_plan;
	private boolean dbs_dispatch_finish;
}
