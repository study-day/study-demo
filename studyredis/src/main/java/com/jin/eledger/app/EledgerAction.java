package com.jin.eledger.app;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import com.jin.eledger.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.jin.eledger.service.EledgerService;

/**
 * 
 * 电子账单类
 *
 */
@Controller
@RequestMapping("e-ledger")
public class EledgerAction {

	@Autowired
	private EledgerService eledgerService;
	/**
	 * 新增账单
	 * @return
	 */
	@RequestMapping(value="xzzd",method = RequestMethod.GET)
	public String xzzd(String eledgerId,HttpServletRequest request){
		if(eledgerId!=null){
			EledgerVo eledgerVo = eledgerService.queryById(eledgerId);
			System.out.println(eledgerVo.getGuige());
			Guige guige = JSONObject.parseObject(eledgerVo.getGuige(),Guige.class);
			eledgerVo.setGuige("");//置空

			request.setAttribute("guige01", JSONObject.toJSONString(guige));
			request.setAttribute("eledger01", JSONObject.toJSONString(eledgerVo));
		}

		return "xzzd";
	}

	
	@RequestMapping("zdlb")
	public String zdlb(){
		return "zdlb";
	}


	@RequestMapping("cljsq")
	public String cljsq(){
		return "cljsq";
	}

	@ResponseBody
	@RequestMapping("cxzd")
	public LayuiPage<EledgerVo> cxzd(EledgerVo eledger,int page,int limit){
		eledger.setStatus((byte)1);
		return eledgerService.queryPage(eledger, page, limit);
	}
	
	
	/**
	 * 新增账单
	 * @return
	 */
	@RequestMapping(value="xzzd",method = RequestMethod.POST)
	public String save(Guige guigeData,EledgerVo eledgerData,HttpServletRequest request){
		String guigeJson = JSONObject.toJSONString(guigeData,SerializerFeature.WriteMapNullValue);
		eledgerData.setGuige(guigeJson);
		ReponseResult result = new ReponseResult();
		if(eledgerData.getLedgerId()!=null && !"".equals(eledgerData.getLedgerId())){
			//更新
			eledgerData.setStatus((byte)1);//正常
			eledgerData.setCreateUesr("10001");//默认员工号
			eledgerData.setUpdateTime(new Date());
			eledgerData.setIp(request.getRemoteAddr());
			eledgerService.update(eledgerData);
			result.setCode((byte)1);
			result.setMsg("修改成功！");
		}else{
			//新增
			eledgerData.setStatus((byte)1);//正常
			eledgerData.setCreateTime(new Date());
			eledgerData.setCreateUesr("10001");//默认员工号
			eledgerData.setUpdateTime(new Date());
			eledgerData.setIp(request.getRemoteAddr());
			eledgerService.add(eledgerData);
			result.setCode((byte)1);
			result.setMsg("保存成功！");
		}
		request.setAttribute("result", JSONObject.toJSONString(result));
		return "xzzd";
	}
	//修改账单
	@RequestMapping("xgzd")
	public String xgzd(String eledgerId){
		//重定向
		return "redirect:xzzd?eledgerId="+eledgerId;

	}

	//删除账单
	@ResponseBody
	@RequestMapping("sczd")
	public ResultMsg sczd(String eledgerId, HttpServletRequest request){
		EledgerVo eledgerVo = eledgerService.queryById(eledgerId);
		eledgerVo.setStatus((byte)0);//删除
		eledgerService.update(eledgerVo);
		ResultMsg msg = new ResultMsg();
		msg.setMsg("删除成功！");
		return  msg;
	}

}
