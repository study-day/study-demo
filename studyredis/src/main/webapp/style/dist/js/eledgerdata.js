		var shapeMap = new Map();
		shapeMap.set("fangguanliao", [ {
			"flag" : "changdu",
			"name" : "长度"
		}, {
			"flag" : "kuandu",
			"name" : "宽度"
		}, {
			"flag" : "gaodu",
			"name" : "高度"
		}, {
			"flag" : "neikuan",
			"name" : "内宽"
		}, {
			"flag" : "neigao",
			"name" : "内高"
		} ]);
		shapeMap.set("yuanbangliao", [ {
			"flag" : "changdu",
			"name" : "长度"
		}, {
			"flag" : "waijing",
			"name" : "外径"
		} ]);
		//圆管料
		shapeMap.set("yuanguanliao", [ {
			"flag" : "changdu",
			"name" : "长度"
		}, {
			"flag" : "waijing",
			"name" : "外径"
		}, {
			"flag" : "neijing",
			"name" : "内径"
		}, {
			"flag" : "bihou",
			"name" : "壁厚"
		} ]);
		//板料
		shapeMap.set("banliao", [ {
			"flag" : "changdu",
			"name" : "长度"
		}, {
			"flag" : "kuandu",
			"name" : "宽度"
		}, {
			"flag" : "houdu",
			"name" : "厚度"
		} ]);
		//六角棒
		shapeMap.set("liujiaobang", [ {
			"flag" : "changdu",
			"name" : "长度"
		}, {
			"flag" : "duibian",
			"name" : "对边"
		} ]);


		var xingzhuangCodeMap = new Map();
		xingzhuangCodeMap.set("yuanbangliao","圆棒料");
		xingzhuangCodeMap.set("yuanguanliao","圆管料");
		xingzhuangCodeMap.set("banliao","板料");
		xingzhuangCodeMap.set("fangguanliao","方管");
		xingzhuangCodeMap.set("liujiaobang","六角棒");


		var cailiaoMap = new Map();
		cailiaoMap.set("1", "7.85")
		cailiaoMap.set("2", "7.9")
		cailiaoMap.set("3", "2.8")
		cailiaoMap.set("4", "8.5")
		cailiaoMap.set("5", "8.9")

		var cailiaoCodeMap = new Map();
		cailiaoCodeMap.set(1,"碳钢：7.85g/cm3")
		cailiaoCodeMap.set(2,"不锈钢：7.9g/cm3")
		cailiaoCodeMap.set(3,"硬铝：2.8g/cm3")
		cailiaoCodeMap.set(4,"黄铜：8.5g/cm3")
		cailiaoCodeMap.set(5,"紫铜：8.9g/cm3")



/*消息提醒*/
		toastr.options = {

			"closeButton": true, //是否显示关闭按钮

			"debug": false, //是否使用debug模式

			"positionClass": "toast-top-full-width",//弹出窗的位置

			"showDuration": "300",//显示的动画时间

			"hideDuration": "1000",//消失的动画时间

			"timeOut": "5000", //展现时间

			"extendedTimeOut": "1000",//加长展示时间

			"showEasing": "swing",//显示时的动画缓冲方式

			"hideEasing": "linear",//消失时的动画缓冲方式

			"showMethod": "fadeIn",//显示时的动画方式

			"hideMethod": "fadeOut" //消失时的动画方式

		};

		function keepTwoDecimalFull(num) {
			var result = parseFloat(num);
			if (isNaN(result)) {
				alert('传递参数错误，请检查！');
				return false;
			}
			result = Math.round(num * 1000) / 1000;
			var s_x = result.toString(); //将数字转换为字符串

			var pos_decimal = s_x.indexOf('.'); //小数点的索引值


			// 当整数时，pos_decimal=-1 自动补0
			if (pos_decimal < 0) {
				pos_decimal = s_x.length;
				s_x += '.';
			}

			// 当数字的长度< 小数点索引+2时，补0
			while (s_x.length <= pos_decimal + 3) {
				s_x += '0';
			}
			//数据不合法
			if(s_x.indexOf("NaN")>=0){
				alert('传递参数错误，请检查！');
			}
			return s_x;
		}




		function jisuan_hs() {
			var tiji= "";
			var zhongliang= "";
			var zongjia = "";
			var xingzhuang = $("#input_xingzhuang").val();
			var changdu = $("#input_changdu").val();
			var waijing = $("#input_waijing").val();
			var midu = $("#input_midu").val();
			var danjia = $("#input_danjia").val();
			var shuliang = $("#input_shuliang").val();

			var neijing = $("#input_neijing").val();

			var kuandu = $("#input_kuandu").val();
			var houdu = $("#input_houdu").val();
			var gaodu = $("#input_gaodu").val();
			var neikuan = $("#input_neikuan").val();
			var neigao = $("#input_neigao").val();
			var duibian = $("#input_duibian").val();
			if(xingzhuang=="yuanbangliao"){
				if(changdu.trim()==""||!Number.isInteger(changdu/1)){
					toastr.warning("长度必填，且只能输入整数，请重新输入！");
					return;
				}
				if(!waijing || waijing.trim()==""||!Number.isInteger(waijing/1)){
					toastr.warning("外径必填，且只能输入整数，请重新输入！");
					return;
				}
				tiji = (changdu/10)*3.14*(waijing/10/2)*(waijing/10/2)*shuliang
				if(!!midu && midu.trim()!=""){
					try {
						zhongliang = tiji*midu/1000;
						console.log(zhongliang)
						console.log(tiji*midu)
					}
					catch(err){
						toastr.warning("密度必填！");
						return;
					}

				}
				if(!danjia || danjia.trim()==""){
					toastr.warning("单价必填，请重新输入！");
					return;
				}
				if(!shuliang || shuliang.trim()==""||!Number.isInteger(shuliang/1)){
					toastr.warning("数量必填，且只能输入整数，请重新输入！");
					return;
				}
				//zongjia = keepTwoDecimalFull(danjia*zhongliang);
				zongjia = danjia*zhongliang;

			}

			//圆管料
			if(xingzhuang=="yuanguanliao"){
				if(changdu.trim()==""||!Number.isInteger(changdu/1)){
					toastr.warning("长度必填，且只能输入整数，请重新输入！");
					return;
				}
				if(!waijing || waijing.trim()==""||!Number.isInteger(waijing/1)){
					toastr.warning("外径必填，且只能输入整数，请重新输入！");
					return;
				}

				if(!neijing || neijing.trim()==""||!Number.isInteger(neijing/1)){
					toastr.warning("内径必填，且只能输入整数，请重新输入！");
					return;
				}
				//tiji = (waijing/10)*3.14*(waijing/10/2)*(waijing/10/2)*shuliang
				tiji = (((waijing/10/2)*(waijing/10/2)*3.14*changdu/10)-((neijing/10/2)*(neijing/10/2)*3.14*changdu/10))*shuliang
				if(!!midu && midu.trim()!=""){
					try {
						zhongliang = tiji*midu/1000;
						console.log("midu : "+midu)
						console.log(zhongliang)
						console.log(tiji*midu)
					}
					catch(err){
						toastr.warning("密度必填！");
						return;
					}

				}
				if(!danjia || danjia.trim()==""){
					toastr.warning("单价必填，请重新输入！");
					return;
				}
				if(!shuliang || shuliang.trim()==""||!Number.isInteger(shuliang/1)){
					toastr.warning("数量必填，且只能输入整数，请重新输入！");
					return;
				}
				zongjia = danjia*zhongliang;
			}

			//板料
			if(xingzhuang=="banliao"){
				if(changdu.trim()==""||!Number.isInteger(changdu/1)){
					toastr.warning("长度必填，且只能输入整数，请重新输入！");
					return;
				}
				if(!kuandu || kuandu.trim()==""||!Number.isInteger(kuandu/1)){
					toastr.warning("宽度必填，且只能输入整数，请重新输入！");
					return;
				}

				if(!houdu || houdu.trim()==""||!Number.isInteger(houdu/1)){
					toastr.warning("厚度必填，且只能输入整数，请重新输入！");
					return;
				}
				tiji = ((changdu/10)*(kuandu/10)*(houdu/10))*shuliang
				if(!!midu && midu.trim()!=""){
					try {
						zhongliang = tiji*midu/1000;
						console.log("midu : "+midu)
						console.log(zhongliang)
						console.log(tiji*midu)
					}
					catch(err){
						toastr.warning("密度必填！");
						return;
					}

				}
				if(!danjia || danjia.trim()==""){
					toastr.warning("单价必填，请重新输入！");
					return;
				}
				if(!shuliang || shuliang.trim()==""||!Number.isInteger(shuliang/1)){
					toastr.warning("数量必填，且只能输入整数，请重新输入！");
					return;
				}
				zongjia = danjia*zhongliang;
			}

			//方管
			if(xingzhuang=="fangguanliao"){
				if(changdu.trim()==""||!Number.isInteger(changdu/1)){
					toastr.warning("长度必填，且只能输入整数，请重新输入！");
					return;
				}
				if(!kuandu || kuandu.trim()==""||!Number.isInteger(kuandu/1)){
					toastr.warning("宽度必填，且只能输入整数，请重新输入！");
					return;
				}

				if(!gaodu || gaodu.trim()==""||!Number.isInteger(gaodu/1)){
					toastr.warning("厚度必填，且只能输入整数，请重新输入！");
					return;
				}

				if(!neigao || neigao.trim()==""||!Number.isInteger(neigao/1)){
					toastr.warning("内高必填，且只能输入整数，请重新输入！");
					return;
				}

				if(!neikuan || neikuan.trim()==""||!Number.isInteger(neikuan/1)){
					toastr.warning("内宽必填，且只能输入整数，请重新输入！");
					return;
				}
				tiji = (((changdu/10)*(kuandu/10)*(gaodu/10))-((changdu/10)*(neikuan/10)*(neigao/10)))*shuliang
				if(!!midu && midu.trim()!=""){
					try {
						zhongliang = tiji*midu/1000;
						console.log("midu : "+midu)
						console.log(zhongliang)
						console.log(tiji*midu)
					}
					catch(err){
						toastr.warning("密度必填！");
						return;
					}

				}
				if(!danjia || danjia.trim()==""){
					toastr.warning("单价必填，请重新输入！");
					return;
				}
				if(!shuliang || shuliang.trim()==""||!Number.isInteger(shuliang/1)){
					toastr.warning("数量必填，且只能输入整数，请重新输入！");
					return;
				}
				zongjia = danjia*zhongliang;
			}

			//六角棒
			if(xingzhuang=="liujiaobang"){
				if(changdu.trim()==""||!Number.isInteger(changdu/1)){
					toastr.warning("长度必填，且只能输入整数，请重新输入！");
					return;
				}
				if(!duibian || duibian.trim()==""||!Number.isInteger(duibian/1)){
					toastr.warning("对边必填，且只能输入整数，请重新输入！");
					return;
				}
				//2分之根号3约等于0.866
				tiji = (0.866*duibian/10*changdu/10)*shuliang
				if(!!midu && midu.trim()!=""){
					try {
						zhongliang = tiji*midu/1000;
						console.log("midu : "+midu)
						console.log(zhongliang)
						console.log(tiji*midu)
					}
					catch(err){
						toastr.warning("密度必填！");
						return;
					}

				}
				if(!danjia || danjia.trim()==""){
					toastr.warning("单价必填，请重新输入！");
					return;
				}
				if(!shuliang || shuliang.trim()==""||!Number.isInteger(shuliang/1)){
					toastr.warning("数量必填，且只能输入整数，请重新输入！");
					return;
				}
				zongjia = danjia*zhongliang;
			}

			$("#input_tiji").val(tiji);
			$("#input_zhongliang").val(zhongliang);
			$("#input_zongjia").val(zongjia);
		}