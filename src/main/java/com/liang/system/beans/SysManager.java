package com.liang.system.beans;

import java.sql.Date;
import lombok.Data;

@Data
public class SysManager {
	private String sm_id;
	private int sm_role_id;
	private String sm_account;
	private String sm_password;
	private String sm_username;
	private String sm_contact;
	private Date sm_create_time;
	private Date sm_last_time;
	private boolean sm_status;
	private String sm_mark;
}
