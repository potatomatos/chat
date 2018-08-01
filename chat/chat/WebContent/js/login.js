$(function() {
	$(".btn-login").click(login);
	function login(){
		if($(".username").val()==""){
			alert("请输入昵称");
			 return;
		}
		$.ajax({
			url:"user/login",
			dataType:'json',
			data:{
				nickname:$(".username").val()
			},
			success:function(data){
				console.log(data);
				localStorage.setItem("user", JSON.stringify(data.args));
				console.log("data",JSON.stringify(data.args));
				console.log("user",localStorage.getItem("user"));
				window.location.href="http://47.52.109.197:8080/chat/";
				alert("登录成功");
		}});
	}
});