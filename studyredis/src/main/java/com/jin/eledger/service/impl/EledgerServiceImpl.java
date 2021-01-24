package com.jin.eledger.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.pagehelper.Page;
import com.jin.eledger.utils.EledgerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jin.eledger.dao.LedgerMapper;
import com.jin.eledger.pojo.EledgerPo;
import com.jin.eledger.pojo.EledgerVo;
import com.jin.eledger.pojo.LayuiPage;
import com.jin.eledger.service.EledgerService;

@Service
public class EledgerServiceImpl implements EledgerService {

	@Autowired
	private LedgerMapper ledgerMapper;

	@Override
	public String add(EledgerVo eledger) {
		// TODO Auto-generated method stub
		EledgerPo eledgerPo = new EledgerPo();
		String ledgerid = UUID.randomUUID().toString();
		BeanUtils.copyProperties(eledger, eledgerPo);
		eledgerPo.setLedgerId(ledgerid);
		System.out.println(eledgerPo);
		ledgerMapper.insert(eledgerPo);
		return ledgerid;
	}

	@Override
	public void update(EledgerVo eledger) {
		// TODO Auto-generated method stub
		EledgerPo eledgerPo = new EledgerPo();
		BeanUtils.copyProperties(eledger,eledgerPo);
	    ledgerMapper.updateByPrimaryKeySelective(eledgerPo);
	}

	@Override
	public void delete(EledgerVo eledger) {
		// TODO Auto-generated method stub

	}

	@Override
	public EledgerVo queryById(String eledger) {
		// TODO Auto-generated method stub
		EledgerPo eledgerPo = ledgerMapper.selectByPrimaryKey(eledger);
		EledgerVo eledgerVo = new EledgerVo();
		BeanUtils.copyProperties(eledgerPo,eledgerVo);
		return eledgerVo;
	}

	@Override
	public List<EledgerVo> queryList(EledgerVo eledger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LayuiPage<EledgerVo> queryPage(EledgerVo eledger, int pageNum, int pageSize) {
		// 使用PageHelper设置分页，为了安全分页，后边最好紧跟mybatis mapper方法
		// 注意这里看起来似乎是属于内存分页，但其实PageHelper插件对mybatis执行流程进行了增强，属于物理分页
		PageHelper.startPage(pageNum, pageSize);
		List<EledgerPo> eledgerPos = ledgerMapper.selectByCase(eledger);
		PageInfo<EledgerPo> page = new PageInfo<>(eledgerPos);
		List<EledgerVo> eledgerVos = new ArrayList<EledgerVo>();
		EledgerVo v = null;
		for (EledgerPo p : eledgerPos) {
			v = new EledgerVo();
			BeanUtils.copyProperties(p, v);
			v.setGuige_desc(EledgerUtils.guige_convert(p.getGuige()));
			eledgerVos.add(v);
		}
		// 返回的是一个PageInfo,包含了分页的所有信息
		PageInfo<EledgerVo> pageInfo = new PageInfo<>(eledgerVos);
		LayuiPage<EledgerVo> layuiPage = new LayuiPage<EledgerVo>();
		layuiPage.setData(pageInfo.getList());
		layuiPage.setCount(page.getTotal());
		if(page.getTotal()==0){
			layuiPage.setMsg("无数据");
		}
		return layuiPage;
	}
}
