package com.jin.eledger.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.jin.eledger.pojo.EledgerVo;
import com.jin.eledger.pojo.LayuiPage;

public interface EledgerService {
	public String add(EledgerVo eledger);
	public void update(EledgerVo eledger);
	public void delete(EledgerVo eledger);
	public EledgerVo queryById(String eledger);
	public List<EledgerVo> queryList(EledgerVo eledger);
	//分页查询
	public LayuiPage<EledgerVo> queryPage(EledgerVo eledger,int pageNum, int pageSize);
}
