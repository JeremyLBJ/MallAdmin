package com.lhd.sys.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhd.sys.dao.SysDeptMapper;
import com.lhd.sys.entity.SysDept;
import com.lhd.sys.entity.SysDeptExample;
import com.lhd.sys.service.SysDeptService;
import com.lhd.sys.untils.LaYuiPage;
import com.lhd.sys.vo.DeptVO;

@Service("SysDeptService")
public class SysDeptServiceImpl implements SysDeptService{
	
	@Autowired
	private SysDeptMapper sysDeptMapper ;

	/**
	 * 加载部门树
	 * 
	 */
	@Override
	public List<SysDept> deptTree() {
		SysDeptExample example = new SysDeptExample() ;
		example.createCriteria().andAvailableEqualTo("1") ;
		List<SysDept> list = sysDeptMapper.selectByExample(example) ;
		return list;
	}

	@Override
	public LaYuiPage findAllDeptInfo(DeptVO deptVO) {
		Page<Object> page = PageHelper.startPage(deptVO.getPage(), deptVO.getLimit()) ;
		List<SysDept> list = sysDeptMapper.allFindSysDeptinfo(deptVO.getId(), deptVO.getId(),deptVO.getRemark() 
												, deptVO.getAddress() , deptVO.getTitle(), deptVO.getAvailable()) ;
		long total = page.getTotal() ;
		return new LaYuiPage (list , total) ;
	}

	@Override
	@Transactional
	public void addSysDept(DeptVO deptVO) {
		deptVO.setCreatetime(new Date());
		sysDeptMapper.insert(deptVO) ;
		
	}

	@SuppressWarnings("null")
	@Override
	public Integer maxorderNum() {
		Integer num = this.sysDeptMapper.maxorderNum() ;
		if ( num == null ) {
			num = num + 1 ;
		}
		return num;
	}

	@Override
	public List<SysDept> allDept() {
		SysDeptExample example = new SysDeptExample() ;
		example.setOrderByClause("ordernum desc");
		List<SysDept> list = this.sysDeptMapper.selectByExample(example) ;
		return list;
	}

	
	/**
	 * 物流删除
	 * 
	 */
	@Override
	@Transactional
	public void deleteDeptInfo(DeptVO deptVO) {
		deptVO.setAvailable("0");
		this.sysDeptMapper.updateById(deptVO.getAvailable(), new Date(), deptVO.getId()) ;
		
	}

	
	/**
	 * 
	 * 修改
	 * 
	 */
	@Override
	@Transactional
	public void updateById(DeptVO deptVO) {
		this.sysDeptMapper.updateByPrimaryKey(deptVO) ;
	}

	
	/**
	 * 
	 * 查询子节点
	 * 
	 */
	@Override
	public List<SysDept> findChildNode(DeptVO deptVO) {
		SysDeptExample example = new SysDeptExample () ;
		example.createCriteria().andPidEqualTo(deptVO.getId()) ;
		List<SysDept> list = this.sysDeptMapper.selectByExample(example) ;
		return list ;
	}
	
	

}
