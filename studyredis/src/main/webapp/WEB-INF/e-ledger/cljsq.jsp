<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="/style/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="/style/dist/css/toastr.min.css">

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

            <li class="nav-item"><a class="nav-link" href="zdlb">账单</a></li>
			<li class="nav-item"><a class="nav-link" href="xzzd">记账
			</a></li>
			<li class="nav-item active"><a class="nav-link" href="cljsq">材料计算器
                <span class="sr-only">(current)</span>
            </a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
		</form>
	</div>
</nav>

<div class="container">
	<!--新增表单  -->
	<form style="margin-bottom: 20px; margin-top: 20px;"
		  class="needs-validation" novalidate action="xzzd" method="post" id="xzzdForm">
		<!-- 提醒 -->
		<%--<div class="alert alert-success alert-dismissible fade show"--%>
		<%--style="display: none">--%>
		<%--<button type="button" class="close" data-dismiss="alert">&times;</button>--%>
		<%--<strong>成功!</strong> 指定操作成功提示信息。--%>
		<%--</div>--%>

		<div class="form-row">
			<div class="form-group col-md-6">
				<input type="text"
					   class="form-control form-control-lg" id="input_ledgerId"
					   name="ledgerId" hidden>

				<label for="input_tuzhimingcheng">名称</label> <input type="text"
																	class="form-control form-control-lg" id="input_tuzhimingcheng"
																	name="tuzhimingcheng"  required>
				<div class="invalid-feedback">必填</div>
			</div>
			<div class="form-group col-md-6">
				<label for="input_tuzhibianhao">图号</label> <input type="text"
																  class="form-control form-control-lg" id="input_tuzhibianhao"
																  name="tuzhibianhao" required>
				<div class="invalid-feedback">必填</div>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="input_xingzhuang">形状选择</label> <select
					onchange="change_shape(this.value)" id="input_xingzhuang"
					name="xingzhuang" class="form-control  form-control-lg" required>
				<option selected value="yuanbangliao">圆棒料</option>
				<option value="yuanguanliao">圆管料</option>
				<option value="banliao">板料</option>
				<option value="fangguanliao">方管</option>
				<option value="liujiaobang">六角棒</option>
			</select>
				<div class="invalid-feedback">必填</div>
			</div>

			<div class="form-group row col-md-6">
				<div class="col-md-6">
					<label for="input_cailiao">材料选择</label> <select
						onchange="change_material(this.value)" id="input_cailiao"
						name="cailiao" class="form-control  form-control-lg" required>
					<option selected value="1">碳钢：7.85g/cm3</option>
					<option value="2">不锈钢：7.9g/cm3</option>
					<option value="3">硬铝：2.8g/cm3</option>
					<option value="4">黄铜：8.5g/cm3</option>
					<option value="5">紫铜：8.9g/cm3</option>
				</select>
					<div class="invalid-feedback">必填</div>
				</div>
				<div class="col-md-6">
					<label for="input_midu">密度（g/cm3）</label> <input type="text"
																	 class="form-control form-control-lg" id="input_midu" name="midu"
																	 pattern="^[0-9.]+[0-9]*$" value="7.85" required>
					<div class="invalid-feedback">必填</div>
				</div>
			</div>
		</div>
		<!--形状大小 -->
		<div class="form-row" id="idshapeSize">
			<div class="form-group col-md-6">
				<label for="input_changdu">长度（毫米）</label> <input type="number"
																 class="form-control form-control-lg" id="input_changdu"
																 name="changdu" pattern="^[1-9]+[0-9]*$" required>
				<div class="invalid-feedback">必填，数字类型，单位是毫米</div>
			</div>
			<div class="form-group col-md-6">
				<label for="input_waijing">外径（毫米）</label> <input type="number"
																 class="form-control  form-control-lg" id="input_waijing"
																 name="waijing" pattern="^[1-9]+[0-9]*$" required>
				<div class="invalid-feedback">必填，数字类型，单位是毫米</div>
			</div>

		</div>


		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="input_shuliang">数量</label> <input type="number"
															  class="form-control  form-control-lg" id="input_shuliang"
															  name="shuliang" pattern="^[1-9]+[0-9]*$" required>
				<div class="invalid-feedback">必填</div>
			</div>
			<div class="form-group col-md-6">
				<label for="input_danjia">单价</label> <input type="text"
															class="form-control  form-control-lg" id="input_danjia"
															name="danjia" pattern="^[0-9.]+[0-9]*$" required>
				<div class="invalid-feedback">必填，只能填数字或者带小数点的数字</div>
			</div>

		</div>

		<div class="form-row">

			<div class="form-group col-md-6">
				<label for="input_zhongliang">重量（公斤）</label> <input type="text"
																	class="form-control form-control-lg" id="input_zhongliang"
																	name="zhongliang" pattern="^[0-9.]+[0-9]*$"  >
                <div class="invalid-feedback">必填，只能填数字或者带小数点的数字</div>
				<div class="invalid-feedback">必填</div>
			</div>

			<div class="form-group col-md-6">
				<label for="input_tiji">体积（cm3）</label> <input type="text"
															   class="form-control form-control-lg" id="input_tiji" name="tiji"
															   pattern="^[0-9.]+[0-9]*$"  >
                <div class="invalid-feedback">必填，只能填数字或者带小数点的数字</div>
				<div class="invalid-feedback">必填</div>
			</div>
		</div>


		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="input_zongjia">总价</label> <input type="text"
															 class="form-control  form-control-lg" name="zongjia"
															 id="input_zongjia">
			</div>
			<!--tip 总价和单价自动转换为汉字大写  -->
		</div>

		<div class="form-row">
			<div class="form-group col-md-12 " style="text-align:center">
				<button type="button" class="btn btn-outline-dark mr-3" onclick="jisuan_hs()" >计算</button>

			</div>
		</div>

		<span></span>


	</form>


</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<%--<script type="text/javascript" src="/style/dist/js/jquery.slim.min.js"></script>--%>
<script type="text/javascript" src="/style/dist/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/style/dist/js/popper.min.js"></script>
<script type="text/javascript" src="/style/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/style/dist/js/toastr.min.js"></script>
<script type="text/javascript" src="/style/dist/js/eledgerdata.js"></script>

<script>
	/**
	 * 	<option selected value="yuanbangliao">圆棒料</option>
	 <option value="yuanguanliao">圆管料</option>
	 <option value="banliao">板料</option>
	 <option value="fangguanliao">方管</option>
	 <option value="liujiaobang">六角棒</option>
	 */

	
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load',
				function() {
					// Fetch all the forms we want to apply custom Bootstrap validation styles to
					var forms = document
							.getElementsByClassName('needs-validation');
					// Loop over them and prevent submission
					var validation = Array.prototype.filter.call(forms,
							function(form) {
								form.addEventListener('submit', function(
										event) {

									if (form.checkValidity() === false) {
										event.preventDefault();
										event.stopPropagation();
									}

									form.classList.add('was-validated');
								}, false);
							});
				}, false);
	})();


	function change_material(newVal) {
		$("#input_midu").val(cailiaoMap.get(newVal))
	}
	function change_shape(newVal) {
		//idshapeSize
		var sizeEle = shapeMap.get(newVal);
		if (!!sizeEle) {
			var sizeHtml = "";
			for (var i = 0; i < sizeEle.length; i++) {
				sizeHtml += '<div class="form-group col-md-6"> <label for="input_'+sizeEle[i].flag+'">'
						+ sizeEle[i].name
						+ '（毫米）</label> <input type="number" class="form-control form-control-lg" id="input_'+sizeEle[i].flag+'" name="'+sizeEle[i].flag+'" pattern="^[1-9]+[0-9]*$" required> <div class="invalid-feedback">必填，数字类型，单位是毫米</div> </div>'
			}
			$("#idshapeSize").html(sizeHtml)
		}
	}

	/* 提醒 */


	$(document).ready(function() {

		var result = eval(
				<%=request.getAttribute("result")%>
		)
		if (!!result) {
			toastr.success(result.msg);
		}


		/* 修改账单 */
		var guigeData = eval(<%=request.getAttribute("guige01")%>)
		var eledgerVo = eval(<%=request.getAttribute("eledger01")%>)
		//xzzdForm

		if(!!eledgerVo){
			var xzzdFormSet = $("form input");
			for(var i =0; i < xzzdFormSet.length;i++){
				for(let key  in eledgerVo){

					if(xzzdFormSet[i].getAttribute("id")==("input_"+key)){
						$("#input_"+key).val(eledgerVo[key])
					}

				}
				for(let key2  in guigeData){
					if(xzzdFormSet[i].getAttribute("id")==("input_"+key2)){
						$("#input_"+key2).val(guigeData[key2])
					}

				}
			}

		}
	});
</script>
</body>
</html>