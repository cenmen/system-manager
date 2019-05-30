function SysManagerLogout() {
		$.post("/SysManagerCon/SysManagerLogout.do", function(data){
			if(data.status=='200'){
				$(location).attr('href', 'sys-manager-login.html');
				alert("注销成功");
				}
		    });
}