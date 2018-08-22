package com.xh.comm.util.base;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xh.comm.entry.AutoIdBean;
import com.xh.comm.entry.AutoIdBeanExample;
import com.xh.comm.entry.AutoIdBeanExample.Criteria;
import com.xh.comm.util.DateUtil;
import com.xh.comm.util.Dict;
import com.xh.dao.AutoIdBeanMapper;
/**
 * 生成序列号
 * @author yuq
 * @date 2017年8月9日
 * @done 高并发速度问题(极限取决于数据库连接的最大次数) 可以一下生成多个序列号（极限为JVM的分配给堆的大小和int的最大值）
 * @todo TODO 可以回收序列号  
 */
@Component
@Scope("singleton")
public class Sequence {
	
	private final static Logger log = LoggerFactory.getLogger(Sequence.class);
	
	/** 生成多个序列号器锁 */
	private final static ReentrantLock locks = new ReentrantLock();
	
	/**
	 * 操作数据库的锁
	 */
	private final static ReentrantLock DBlock = new ReentrantLock();
	
	/**
	 * 线程池的最大值
	 */
	private final static int THREAD_POOL_MAX_NUM = 10;
	
	/**
	 * 线程池
	 */
	public final static ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_MAX_NUM);
	
	
	@Autowired
	private AutoIdBeanMapper dao;
	
	
	/**
	 * 根据序列规则的前缀为key序列为V的map，用做入库
	 */
	private static Map<String,AutoIdBean> keyMap = new ConcurrentHashMap <String,AutoIdBean>();
	
	
	
	
	/**
	 *	查询AutoID表请严格检查主键是否在表中有，否则会有异常
	 * @param code
	 * @param projectName
	 * @return
	 */
	private  AutoIdBean getBeans(String code,String projectName){
		if(!keyMap.containsKey(code)){//缓存没有当前的seq
			AutoIdBean bean = new AutoIdBean();
			AutoIdBeanExample example = new AutoIdBeanExample();
			Criteria criteria = example.createCriteria();
			criteria.andCodeEqualTo(code);
			criteria.andProjectNameEqualTo(projectName);
			bean = dao.selectByPrimaryKey(code);
			if(bean == null){
				log.error("请检查下code,project_name");
				throw new RuntimeException("请检查下code,project_name");
			}
			keyMap.put(code, bean);
			return bean;
		}
		
		return keyMap.get(code);
			
	}
	
	/**
	 * 预生成的序列和最大值比较
	 * @param bean
	 * @param preNum
	 * @return
	 */
	private  boolean jugeMaxNum(AutoIdBean bean,int preNum){
		return bean.getMaxNum()>preNum;
	}
	
	/**
	 * 新增序列规则
	 * @param bean
	 */
	public void addNewSeq(AutoIdBean bean){
		dao.insert(bean);
	}
	
	
	/**
	 * 删除序列规则 
	 * @param key
	 */
	public void delSeq(String key){
		dao.deleteByPrimaryKey(key);
	}
	
	/**
	 * 更新序列的CurNum
	 * @param key
	 */
	private void updateSeqCurnum(AutoIdBean bean){
				DBlock.lock();
				try {
					dao.updateByPrimaryKey(keyMap.get(bean.getCode()));
				} finally {
					DBlock.unlock();
				}
			
	}
	
	
	/**
	 * 根据序列的key去查询
	 * @param code
	 * @param projectName
	 * @return
	 */
	public  String getSequenceStr(String code,String projectName){
		return getSequenceStr(code, projectName, 1)[0];
	}
	
	/**
	 * 根据序列的key去查询
	 * @param code
	 * @param projectName
	 * @return
	 */
	public  String getSequenceStr(String code){
		return getSequenceStr(code, Dict.PROJECT_NAME, 1)[0];
	}
	
	/**
	 * 根据序列的key去查询
	 * @param code
	 * @param projectName
	 * @return
	 */
	public  String[] getSequenceStr(String code,int num){
		return getSequenceStr(code, Dict.PROJECT_NAME, num);
	}
	
	/**
	 * 
	 * @param code
	 * @param projectName
	 * @param num 序列号的个数
	 * @return
	 */
	public String[] getSequenceStr(String code,String projectName,int num){
		locks.lock();
		String[] seqs = new String[num];
		Integer[] seqNum = new Integer[num];
		AutoIdBean bean = getBeans(code, projectName);
		try {
			int curNum = reBeginCurNum(bean);
			if(jugeMaxNum(bean, curNum)){
				//序号可以使用
				for (int i = 0; i < num; i++) {
					seqs[i] = getWholeSeq(curNum,bean);
					curNum++;
					seqNum[i] = curNum;
				}
				bean.setCurNum(curNum);
				bean.setUpdater("yu");
				bean.setUpdateTime(new Date());
				keyMap.put(code,bean);
				updateSeqCurnum(bean);
			}
		} finally {
			locks.unlock();
		}
		return seqs;
	}
	
	
	/**
	 * 得到完成的序列号
	 * @param curNum
	 * @param code
	 * @return
	 */
	private  String getWholeSeq(int curNum,AutoIdBean bean){
			return bean.getCode()+DateUtil.getCurrDate2()+String.valueOf(bean.getMaxNum()+curNum);
	}
	
	/**
	 * 如果是新的一天那么序号要重新开始
	 * @param bean
	 * @return
	 */
	private  int reBeginCurNum(AutoIdBean bean){
		int num = 0;
		if(!DateUtil.jugeCurDay(new Date(), bean.getUpdateTime())){
			AutoIdBean idBean = getBeans(bean.getCode(), bean.getProjectName());//最新的bean
			AutoIdBean autoIdBean = new AutoIdBean();//将要更新的Bean
			//插入数据
			autoIdBean.setCode(bean.getCode());
			autoIdBean.setProjectName(bean.getProjectName());
			autoIdBean.setCurNum(idBean.getMinNum());
			autoIdBean.setUpdater("yu");
			autoIdBean.setUpdateTime(new Date());
			if( !DateUtil.jugeCurDay(keyMap.get(bean.getCode()).getUpdateTime(), new Date())){
				updateSeqCurnum(idBean);
			}
			keyMap.put(bean.getCode(), bean);
			num = idBean.getMinNum();
			return num;
		}
		num = bean.getCurNum();
		return num;
	}
	
	
}
