package com.ttpod.common.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.*;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.nodes.*;
import org.htmlparser.util.*;
import org.htmlparser.tags.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttpod.common.dao.TPhonebrandDAO;
import com.ttpod.common.model.Action;
import com.ttpod.common.model.Phonebrand;
import com.ttpod.common.model.TPhone;
import com.ttpod.common.model.TPhonebrand;
import com.ttpod.common.service.BaseService;
import com.ttpod.common.service.IPhoneManageService;
import com.ttpod.common.service.IPhonebrandManageService;
import com.ttpod.common.service.ITPhonebrandManageService;
import com.ttpod.common.tool.page.Page;
import com.ttpod.common.tool.query.PropertyFilter;
import com.ttpod.common.tool.query.QueryUtil;
/**
 * @className:TPhonebrandManageService.java
 * @classDescription:手机品牌Service
 * @author:luowei
 * @createTime:2012-1-12
 */
@Service
public class TPhonebrandManageService extends BaseService<TPhonebrand> implements ITPhonebrandManageService 
{
	@Resource(name="TPhonebrandDAO")
	private  TPhonebrandDAO tPhonebrandDAO;
	
	

	public TPhonebrandDAO gettPhonebrandDAO() {
		return tPhonebrandDAO;
	}

	public void settPhonebrandDAO(TPhonebrandDAO tPhonebrandDAO) {
		this.tPhonebrandDAO = tPhonebrandDAO;
	}

	/**
	 * 品牌类别过滤器
	 * @author luowei
	 * @createTime 2012-1-13
	 * @param
	 * @return
	 */
	class BrandFilter extends StringFilter
	{
		public BrandFilter(String pattern)
		{
			super(pattern);
		}
		public boolean accept(Node node) {
			if(node!=null)
			{
				if(node instanceof TagNode)
				{
					TagNode tn=(TagNode)node;
					String vl=tn.getAttribute("class");
					if(vl!=null&&vl.equals(this.getPattern()))
					{
						return true;
					}
				}
			}
			return false;
		}
	}
	
	/**
	 * 从中关村抓取品牌信息
	 * @author luowei
	 * @createTime 2012-1-13
	 * @param
	 * @return
	 */
	public List<TPhonebrand> getPhonebrandFromZOL() 
	{
		try
    	{
			//初始两个页面解析器
    		Parser parser1=new Parser("http://detail.zol.com.cn/category/57.html");
    		Parser parser2=new Parser("http://detail.zol.com.cn/category/57.html");
    		List<TPhonebrand>  tPhonebrandList = new ArrayList<TPhonebrand>(); 
    		String[] manu={"manu normal","manu txt"};
    		//获取普通品牌
			tPhonebrandList.addAll(findNormalBrand(parser1.parse(new BrandFilter((manu[0])))));
			//获取其它品牌
			tPhonebrandList.addAll(findOtherBrand(parser2.parse(new BrandFilter(manu[1]))));
    		return tPhonebrandList;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return null;
	}
	
	/**
	 * 获取普通品牌(class=manu nomal)的品牌
	 * manu nomal 下的li子标签如下结构：
	 * <li>
	 * 	<a href="/cell_phone_index/subcate57_98_list_1.html">
	 * 	<img alt="Samsung（三星） 手机" src="http://2c.zol-img.com.cn/manu_photo/98_.jpg">
	 * 	</a>
	 * 	<br>
	 * 	<a title="Samsung（三星） 手机" href="/cell_phone_index/subcate57_98_list_1.html">Samsung（三星）</a>
	 * </li>
	 * @author luowei
	 * @createTime 2012-1-13
	 * @param
	 * @return
	 */
	
	public  List<TPhonebrand> findNormalBrand(NodeList nl1)	
	{
		List<TPhonebrand> list=new ArrayList<TPhonebrand>();
		for(int i=0;i<nl1.size();i++)
		{
			Node node1=nl1.elementAt(i);   //获取第i个class=manu normal的结点
			NodeList nodeList2=node1.getChildren();
			for(int j=0;j<nodeList2.size();j++)
			{
				Node n2=nodeList2.elementAt(j);   //获取li的结点
				LinkTag tag_a=(LinkTag)n2.getFirstChild();	//获取嵌套了图片标签的a标签结点
				ImageTag tag_img=(ImageTag)n2.getFirstChild().getFirstChild();	//获取图片标签结点
				LinkTag tag_an=(LinkTag)n2.getLastChild();	//获取品牌链接

				String name=tag_an.getLinkText();		//得到品牌名称
				String imgpath=tag_img.getImageURL();	//得到图片地址
				String url=tag_a.getLink();			//得到图链接地址
				
				TPhonebrand tphonebrand=new TPhonebrand();
				tphonebrand.setPhonebrandName(name);	//保存品牌名称到tphonebrand对象
				tphonebrand.setPhonebrandImage(imgpath);
				tphonebrand.setRelateURL(url);
				
				list.add(tphonebrand);	
			}
		}
		return list;
	}

	/**
	 * 获取其它品牌(class=manu txt)的品牌
	 * manu txt 下的li子标签如下结构：
	 * <li>
	 * 	<a title="BlackBerry（黑莓） 手机" href="/cell_phone_index/subcate57_12772_list_1.html">BlackBerry（黑莓）手机</a>
	 * </li>
	 * @author luowei
	 * @createTime 2012-1-12
	 * @param
	 * @return
	 */
	public  List<TPhonebrand> findOtherBrand(NodeList nl1)	//
	{
		List<TPhonebrand> list=new ArrayList<TPhonebrand>();
		for(int i=0;i<nl1.size();i++)
		{
			Node n1=nl1.elementAt(i);   //定位到第i个class=manu txt的结点
			NodeList nl2=n1.getChildren();
			for(int j=0;j<nl2.size();j++)	//遍历manu txt结点下的li结点
			{
				Node n2=nl2.elementAt(j);   //定位到第j个li的结点
				
				TPhonebrand tphonebrand=new TPhonebrand();
				LinkTag _an=(LinkTag)n2.getFirstChild();//获取品牌链接

				String name=_an.getLinkText().replace("手机", "");
				String url=_an.getLink();
				tphonebrand.setPhonebrandName(name);
				tphonebrand.setRelateURL(url);
				
				list.add(tphonebrand);
			}
		}
		return list;
	}
	
	/*查询所有的手机品牌*/
	public Page findAllPhonebrands(Page page, List<PropertyFilter> pfList) 
	{
		//初始化hql
		StringBuffer hql=new StringBuffer("from TPhonebrand");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.tPhonebrandDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		//是否存在排序
		if(page.isOrderBySetted())
		{
			hql.append(page.getOrder());
		}
		List<TPhonebrand> list=this.tPhonebrandDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
		return page;
	}

	/**
	 * 判断是否为新品牌
	 * 
	 * @author luowei
	 * @createTime 2012-01-19
	 * @param tphone
	 * @return 是否为真
	 */
	public boolean isNewTPhonebrand(String tphonebrandName) {
		boolean isNew=false;
		if(!StringUtils.isBlank(tphonebrandName)){
			if (!checkPhonebrandName(tphonebrandName)) {
				isNew = true;
			}
		}
		return isNew;
	}
	
	
	/**
	 * 检测品牌名是否存在
	 * @param userName
	 * luowei
	 * @return
	 */
	public boolean checkPhonebrandName(String phonebrandName) {
		boolean notNull=false;
		if(!StringUtils.isBlank(phonebrandName)){
			Map map=new HashMap();
			map.put("phonebrandName", phonebrandName);			
			TPhonebrand ph=tPhonebrandDAO.findOfMap("from TPhonebrand where phonebrandName=:phonebrandName",map);
			if(ph!=null)
			{
				notNull=true;		
			}
		}
		return notNull;
	}
	
	/**
	 * 查找所有品牌
	 * @author luowei
	 * @return 返回临时品牌list集合
	 */
	public List<TPhonebrand> findList() {
		String hql="from TPhonebrand";
		return tPhonebrandDAO.findList(hql);
	}

	/**
	 * 根据品牌名称查找品牌
	 * @param brandName 品牌名称
	 * @author luowei
	 * @return 返回临时品牌
	 */
	public TPhonebrand findByName(String brandName) {
		String hql="from TPhonebrand where phonebrandName=:brandName";
		Map map=new HashMap();
		map.put("brandName", brandName);
		TPhonebrand tPhonebrand= this.tPhonebrandDAO.findOfMap(hql, map);
		return tPhonebrand;
	}
	
	public static void main(String[] args) {
		TPhonebrandManageService tPhonebrandManageService=new TPhonebrandManageService();
		List<TPhonebrand> tPhjonebrandList=tPhonebrandManageService.getPhonebrandFromZOL();
		for(TPhonebrand tPhonebrand:tPhjonebrandList)
		{
			System.out.println(tPhonebrand.getPhonebrandName()
					+"\n"+tPhonebrand.getRelateURL()+"\n----------");
		}
	}

}
