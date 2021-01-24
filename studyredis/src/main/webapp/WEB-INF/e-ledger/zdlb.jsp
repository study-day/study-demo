<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<%--	<style type="text/css">
		body {
			margin: 0;
			font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
			font-size: 1rem;
			font-weight: 400;
			line-height: 1.5;
			color: #212529;
			text-align: left;
			background-color: #fff;
		}
	</style>--%>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/style/layui/css/layui.css">
	<%--bootstrap 放在后面--%>
<link rel="stylesheet" href="/style/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="/style/dist/css/toastr.min.css">

<script type="text/javascript" src="/style/dist/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/style/dist/js/popper.min.js"></script>
<script type="text/javascript" src="/style/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/style/layui/layui.js"></script>
<script type="text/javascript" src="/style/dist/js/toastr.min.js"></script>
<script type="text/javascript" src="/style/dist/js/eledgerdata.js"></script>

<title>电子账单</title>
</head>
<body>


	<!-- Content here -->
	<!-- topnav -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="xzzd">电子账单</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<li class="nav-item active"><a class="nav-link" href="zdlb">账单 <span class="sr-only">(current)</span></a></li>
				<li class="nav-item "><a class="nav-link" href="xzzd">记账

				</a></li>

				<li class="nav-item"><a class="nav-link" href="cljsq">材料计算器</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					   aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0"  type="submit">搜索</button>
			</form>
		</div>
	</nav>

	<!-- 查询字段 -->

	<fieldset style="margin: 15px 15px 0px 15px;">
		<div class="form-row">
			<div class="form-group  col-md-2">
				<label for="input_tuzhimingcheng">名称</label> <input type="text"
					class="form-control form-control-lg" id="input_tuzhimingcheng"
					name="tuzhimingcheng" >

			</div>
			<div class="form-group  col-md-2">
				<label for="input_tuzhibianhao">图号</label> <input type="text"
					class="form-control form-control-lg" id="input_tuzhibianhao"
					name="tuzhibianhao">

			</div>

			<div class="form-group  col-md-2">
				<label for="input_xingzhuang">形状选择</label> <select
					id="input_xingzhuang"
					name="xingzhuang" class="form-control  form-control-lg">
				    <option value=""></option>
				    <option value="yuanbangliao">圆棒料</option>
					<option value="yuanguanliao">圆管料</option>
					<option value="banliao">板料</option>
					<option value="fangguanliao">方管</option>
					<option value="liujiaobang">六角棒</option>
				</select>

			</div>


			<div class="col-md-2">
				<label for="input_cailiao">材料选择</label> <select
					id="input_cailiao"
					name="cailiao" class="form-control  form-control-lg">
				    <option value=""></option>
					<option value="1">碳钢：7.85g/cm3</option>
					<option value="2">不锈钢：7.9g/cm3</option>
					<option value="3">硬铝：2.8g/cm3</option>
					<option value="4">黄铜：8.5g/cm3</option>
					<option value="5">紫铜：8.9g/cm3</option>
				</select>

			</div>
			<div class="form-group col-md-2">
				<label for="input_shuliang">数量</label> <input type="number"
					class="form-control  form-control-lg" id="input_shuliang"
					name="shuliang">

			</div>
			<div class="form-group col-md-2">
				<label for="input_danjia">单价</label> <input type="text"
					class="form-control  form-control-lg" id="input_danjia"
					name="danjia">

			</div>
		</div>

		<!--形状大小 -->

		<div class="form-row">

			<div class="form-group col-md-2">
				<label for="input_createTime_start">开始时间</label> <input type="text"
					class="form-control form-control-lg" id="input_createTime_start"
					name="createTime_start">

			</div>

			<div class="form-group col-md-2">
				<label for="input_createTime_end">结束时间</label> <input type="text"
					class="form-control form-control-lg" id="input_createTime_end"
					name="createTime_end">
			</div>

			<div class="form-group col-md-2">
			<label for="input_createTime_end">&nbsp</label>
			<!--style="margin-top:26px"  -->
				<button type="button" data-type="reload" id="chaxun_btn" class="btn btn-primary btn-lg btn-block">查询</button>
			</div>
		</div>
		
	</fieldset>
	<!-- 表格 -->

	<div class="col-md-12">
		<table id="zdlb_table" lay-filter="test" ></table>
	</div>


</body>

<script type="text/html" id="toolbarDemo">
<%--  <div class="layui-btn-container">
    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    <button class="layui-btn layui-btn-sm" lay-event="reload">重载</button>
  </div>--%>
</script>



<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary  layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del" >删除</a>
</script>

<script type="text/html" id="xingzhuangTpl">
	{{xingzhuangCodeMap.get(d.xingzhuang)}}
</script>

<script type="text/html" id="cailiaoTpl">
	{{ cailiaoCodeMap.get(d.cailiao) }}
</script>

<script type="text/html" id="guigeTpl">
	var
	{{ cailiaoCodeMap.get(d.cailiao) }}
</script>

<script type="text/javascript">
	layui.use('table', function() {
		var table = layui.table;

		//return;

		//渲染
		var tableins = table.render({
			id:'zdlb_grid'
			,elem : '#zdlb_table',
			height : 640
			//,width: 600
			,
			title : '用户数据表',
			url : 'cxzd'
			//,size: 'lg'
			,
			page : {

			}

			,
			autoSort : false
			//,loading: false
			,
			totalRow : false,//合计
			limit : 20,
			toolbar : '#toolbarDemo',
			defaultToolbar : [ 'filter', 'exports', 'print', {
				title : '帮助',
				layEvent : 'LAYTABLE_TIPS',
				icon : 'layui-icon-tips'
			} ],
			cols : [ [ {
				type : 'checkbox',
				fixed : 'left'
			}, {
				field : 'ledgerid',
				title : 'ID',
				width : 80,
				hide : true
			}, {
					field : 'tuzhimingcheng',
					title : '图纸名称',
					width : 160
			},{
				field : 'tuzhibianhao',
				title : '图纸编号',
				width : 160
			}, {
				field : 'xingzhuang',
				title : '形状',
				width : 110,
				templet:'#xingzhuangTpl'
			}, {
				field : 'guige_desc',
				title : '规格',
				width : 320
			},{
				field : 'guige',
				title : '规格',
				width : 300,
				hide : true
			}, {
				field : 'cailiao',
				title : '材料',
				width : 150,
				templet:'#cailiaoTpl'
			}, {
				field : 'midu',
				title : '密度',
				width : 90
			}, {
				field : 'shuliang',
				title : '数量',
				width : 90
			}, {
				field : 'danjia',
				title : '单价(元)',
				width : 100
			}, {
				field : 'tiji',
				title : '体积(cm3)',
				width : 100
			}, {
				field : 'zhongliang',
				title : '重量(kg)',
				width : 100,
			}, {
				field : 'zongjia',
				title : '总价(元)',
				width : 120
			}, {
				field : 'createTime',
				title : '创建时间',
				width : 160
			}, {
				fixed : 'right',
				title : '操作',
				toolbar : '#barDemo',
				width : 130
			} ] ]

		});

		var $ = layui.$, active = {
			reload: function(){
				//执行重载
				table.reload('zdlb_grid', {
					where: { //设定异步数据接口的额外参数，任意设
						tuzhimingcheng: $("#input_tuzhimingcheng").val()
						,tuzhibianhao: $("#input_tuzhibianhao").val()
						,xingzhuang: $("#input_xingzhuang").val()
						,cailiao: $("#input_cailiao").val()
						,shuliang: $("#input_shuliang").val()
						,danjia: $("#input_danjia").val()
						,createTime_start: $("#input_createTime_start").val()
						,createTime_end: $("#input_createTime_end").val()
						,status:$("#input_status").val()
					}
					,method:'post'
					,page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			}
		};

		$('#chaxun_btn').on('click', function(){
			var  start = $("#input_createTime_start").val();
			var end  = $("#input_createTime_end").val();
			if(!!start && !!end && start>end){
				toastr.warning("开始时间不能大于结束时间，请重新选择！");
				return;
			}
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

/*		/!*搜索函数，方法级别*!/
		var sousuo_hs = tableins.reload('zdlb_grid',{
			where: { //设定异步数据接口的额外参数，任意设
				tuzhimingcheng: $("#input_tuzhimingcheng").val()
				,tuzhibianhao: $("#input_tuzhibianhao").val()
				,xingzhuang: $("#input_xingzhuang").val()
				,cailiao: $("#input_cailiao").val()
				,shuliang: $("#input_shuliang").val()
				,danjia: $("#input_danjia").val()
				,createTime_start: $("#input_createTime_start").val()
				,createTime_end: $("#input_createTime_end").val()
			}
			,page: {
				curr: 1 //重新从第 1 页开始
			}
		});*/



		//工具栏事件
		table.on('toolbar(test)', function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
			switch (obj.event) {
			case 'add':
				layer.msg('添加');
				break;
			case 'update':
				layer.msg('编辑');
				break;
			case 'delete':
				layer.msg('删除');
				break;
			case 'getCheckData':
				var data = checkStatus.data;
				layer.alert(JSON.stringify(data));
				break;
			case 'getCheckLength':
				var data = checkStatus.data;
				layer.msg('选中了：' + data.length + ' 个');
				break;
			case 'isAll':
				layer.msg(checkStatus.isAll ? '全选' : '未全选')
				break;
			case 'LAYTABLE_TIPS':
				layer.alert('欢迎使用电子账单！');
				break;
			case 'reload':
				table.reload('test', {
					page : {
						curr : 5
					}
				//,height: 300
				//,url: 'x'
				}, 'data');
				break;
			}
			;
		});

		//行工具事件
		table.on('tool(test)', function(obj) {
			//console.log(obj);
			//layer.closeAll('tips');
			var data=obj.data;
			if(obj.event=='edit'){
				window.location.href="xgzd?eledgerId="+obj.data.ledgerId
				return;
			}
			if(obj.event === 'del'){
				layer.confirm('确定删除？', function(index){
					$.post("sczd?eledgerId="+obj.data.ledgerId,function(data){
						if(data.code=="0"){
							toastr.success(data.msg);
							active['reload'].call(this)
						}
					});
					obj.del();
					layer.close(index);
				});
			}
		});




		////监听行单击事件
		table.on('row(test)', function(obj) {
			//console.log(obj);
			//layer.closeAll('tips');
		});

	});


	/* 开始时间，结束时间 */
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		//执行一个laydate实例
		laydate.render({
			elem : '#input_createTime_start' //指定元素
		});
		laydate.render({
			elem : '#input_createTime_end' //指定元素
		});
	});
</script>
</html>

