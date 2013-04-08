package com.rootls.common.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.rootls.common.dao.TPhoneDAO;
import com.rootls.common.model.TPhone;
import com.rootls.common.service.BaseService;
import com.rootls.common.service.IPhoneManageService;
import com.rootls.common.service.ITPhoneManageService;
import com.rootls.common.tool.page.Page;
import com.rootls.common.tool.query.PropertyFilter;
import com.rootls.common.tool.query.QueryUtil;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.*;
import org.htmlparser.nodes.*;
import org.htmlparser.util.*;
import org.htmlparser.tags.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className:UserManageService.java
 * @classDescription:用户管理类
 * @author xiping.tang
 * @version v0.0.1
 * @createTime:2010-7-8
 */
@Service
public class TPhoneManageService extends BaseService<TPhone> implements
		ITPhoneManageService {

	@Autowired
	protected IPhoneManageService phoneManageService;
	@Autowired
	protected TPhoneDAO tphoneDAO;
	String param[] = { "网络模式", "外观设计", "主屏尺寸", "触摸屏", "摄像头像", "操作系统", "机身内存",
			"电池容量" }; // 类型

	/**
	 * 根据url查找手机对象全部集合
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return List<TPhone> 返回查询手机集合
	 */
	public List<TPhone> getPhonesFromZOL(String url) {
		List<TPhone> list = new Vector<TPhone>();
		int num = getPageCount(url); // 获取页数
		for (int i = 1; i <= num; i++) {
			List<TPhone> temp; // 临时手机集合
			try {
				String urlTmp = url.replaceFirst("list_1", "list_" + i);
				Parser p1 = new Parser(urlTmp);
				NodeList nl2 = p1.parse(new ZOLPhoneFilter());
				temp = findInfor(nl2);
				list.addAll(temp); // 将i页的手机集合保存在temp中
			} catch (ParserException e) {

			} catch (Exception e) {

			}

		}
		return list;
	}
	/**
	 * 根据url查找手机对象本页集合
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return List<TPhone> 返回查询手机集合
	 */
	public List<TPhone> getPhonesByPageFromZOL(String url) {
		List<TPhone> list = new Vector<TPhone>();
		Parser p1;
		try {
			p1 = new Parser(url);
			NodeList nl = p1.parse(new ZOLPhoneFilter());
			list=findInfor(nl);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return list;
		
	}

	/**
	 * 从url获取页数
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param url
	 * @return int
	 */
	public int getPageCount(String url) {
		// 获取页数
		int num = 1;
		final int p1 = 0; // 开始位置
		final int p2 = 7; // 子常用节点
		final int p3 = 5; // 子备用节点
		try {

			Parser p = new Parser(url);
			NodeList nl1 = p.parse(new ZOLPhonePageFilter());
			try {
				String count = nl1.elementAt(p1).getChildren().elementAt(p2)
						.getText();
				count = count.substring(1);
				num = Integer.parseInt(count);
			} catch (NumberFormatException e) {
				String count = nl1.elementAt(p1).getChildren().elementAt(p3).getText();
				count = count.substring(1);
				num = Integer.parseInt(count);

			}

		} catch (ParserException e) {
		} catch (Exception e) {
		}
		return num;

	}

	/**
	 * 从NodeList获取TPhone并保存在List<TPhone>对象中
	 * 
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param nl1
	 * @return List<TPhone>
	 */
	public List<TPhone> findInfor(NodeList nl1) {
		List list = new Vector();
		for (int i = 0; i < nl1.size(); i++) {

			Node n1 = nl1.elementAt(i); // 获取class=conlist的结点
			NodeList nl2 = n1.getChildren();
			for (int j = 0; j < nl2.size(); j++) {
				Node n2 = nl2.elementAt(j); // 获取class=conlist的子结点
				if (n2 instanceof Div) {
					Div tn = (Div) n2;
					String atr = tn.getAttribute("class");
					try {
						TPhone tphone = new TPhone();
						if ("item".equals(atr)) // 获取class=item的结点
						{
							Div item = (Div) tn; // item为div对象
							getPhoneName(item, tphone);
							getPicture(item, tphone);
							getPrice(item, tphone);
							getParam(item, tphone);// 从item中获取手机参数并保存至tphone中
							// position为参数所在的位置
							list.add(tphone);

						}
					} catch (Exception e) {
						
					}
				}
			}
		}
		return list;
	}

	/**
	 * 从item获取手机名称并保存在tphone对象中
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param item
	 * @param tphone
	 * @return void
	 */
	public void getPhoneName(Node phoneitem, TPhone tphone) {
		// 获取手机名称
		Node item;
		int key[] = { 5, 1 };
		try {
			item = (Node) phoneitem.clone();
			LinkTag _aphoneName = (LinkTag) item.getChildren()
					.elementAt(key[0]).getChildren().elementAt(key[1]);
			String phoneName = _aphoneName.getLinkText();
			tphone.setPhoneName(phoneName);
		} catch (CloneNotSupportedException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 从item获取手机图片并保存在tphone对象中
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param item
	 * @param tphone
	 * @return void
	 */
	public void getPicture(Node phoneitem, TPhone tphone) {
		// 获取手机图片
		Node item;
		try {
			item = (Node) phoneitem.clone();
			LinkTag _aphonePic = (LinkTag) item.getChildren().elementAt(3)
					.getChildren().elementAt(1);
			ImageTag _imphonePic = (ImageTag) _aphonePic.getChild(0);
			String phonePic = _imphonePic.getImageURL();
			tphone.setPhonePic(phonePic);
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 从item获取手机价格并保存在tphone对象中
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param phoneitem       HTML中phoneitem对象
	 * @param tphone          临时机型对象
	 * @return void
	 */
	public void getPrice(Node phoneitem, TPhone tphone) {
		// 获取手机价格
		Node item;
		try {
			item = (Node) phoneitem.clone();
			String phonePrice = item.getChildren().elementAt(5).getChildren()
					.elementAt(3).getFirstChild().getChildren().elementAt(2)
					.getText();
			if("/b".equals(phonePrice))
			{
				return ;
			}
			int p = Integer.parseInt(phonePrice);
			tphone.setPhonePrice(p + "");
		} catch (CloneNotSupportedException e1) {
		} catch (Exception e) {	
		}

	}

	/**
	 * 从item根据pisition位置获取手机参数并保存在tphone对象中
	 * 
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param item
	 * @param pisition
	 * @param tphone
	 * @return void 返回查询总数
	 */
	public void getParam(Node item, TPhone tphone) {
		int[][] position = { { 5, 9, 1 }, { 5, 9, 2 }, { 5, 9, 3 },
				{ 5, 9, 4 }, { 5, 9, 5 }, { 5, 9, 6 }, { 5, 9, 7 },
				{ 5, 9, 8 }, { 5, 9, 10, 1, 2 }}; // 关键位置
		for (int[] row : position) {
			String map = ""; // 获取map字符串
			try {
				Node temp = (Node) item.clone();
				for (int i = 0; i < row.length; i++) {
					temp = temp.getChildren().elementAt(row[i]);
				}
				map = temp.getFirstChild().getText(); // 获取的map信息 如
				// 网络模式:cdma2000

			}catch (Exception e) {
			}
			String mapTemp[] = map.split("：");
			if (param[0].equals(mapTemp[0])) {
				tphone.setPhoneModel(mapTemp[1]);
			} else if (param[1].equals(mapTemp[0])) {
				tphone.setPhoneView(mapTemp[1]);
			} else if (param[2].equals(mapTemp[0])) {
				tphone.setPhoneSize(mapTemp[1]);
			} else if (param[3].equals(mapTemp[0])) {
				tphone.setPhoneTouchType(mapTemp[1]);
			} else if (param[4].equals(mapTemp[0])) {
				String s = "";
				for (int i = 1; i < mapTemp.length; i++) {
					s = s + mapTemp[i];
				}
				tphone.setPhoneCamera(s);
			} else if (param[5].equals(mapTemp[0])) {
				tphone.setPhoneOs(mapTemp[1]);
			} else if (param[6].equals(mapTemp[0])) {
				tphone.setPhoneMemery(mapTemp[0]);
			} else if (param[7].equals(mapTemp[0])) {
				tphone.setPhoneBatter(mapTemp[0]);
			}
		}

	}

	/**
	 * 从Node n根据pisition位置获取文本
	 * 
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param n
	 * @param position
	 * @return String
	 */
	public String getNodeText(Node n, int[] position) {
		Node temp = null;
		try {
			temp = (Node) n.clone();
		} catch (CloneNotSupportedException e) {

		}

		for (int p : position) {
			n = n.getChildren().elementAt(p);
		}
		String txt = n.getText();
		return txt;
	}

	/**
	 * @className:ZOLPhoneFilter
	 * @classDescription:中关村页面手机数据过滤内部类
	 * @author xiping.tang
	 * @version v0.0.1
	 * @createTime:2010-7-8
	 */
	class ZOLPhoneFilter implements NodeFilter {
		public boolean accept(Node node) {
			if (node != null) {
				if (node instanceof TagNode) {
					TagNode tn = (TagNode) node;
					String vl = tn.getAttribute("class");
					if (vl != null && vl.equals("conlist")) {
						return true;

					}

				}
			}
			return false;
		}
	}

	/**
	 * @className:ZOLPhonePageFilter
	 * @classDescription:中关村页面手机页面内部类
	 * @author xiping.tang
	 * @version v0.0.1
	 * @createTime:2010-7-8
	 */
	class ZOLPhonePageFilter implements NodeFilter {
		public boolean accept(Node node) {
			if (node != null) {
				if (node instanceof TagNode) {
					TagNode tn = (TagNode) node;
					String vl = tn.getAttribute("class");
					if (vl != null && vl.equals("page")) {
						return true;

					}

				}
			}
			return false;
		}
	}

	/**
	 * 根据tphone检查真实表存在新的
	 * 
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param tphone
	 * @return 是否为真
	 */
	public boolean isNewPhone(TPhone tphone) {
		Map map=new HashMap();
		map.put("phoneName",tphone.getPhoneName());			
		TPhone ph=this.findOfMap("from TPhone where phoneName=:phoneName",map);
		if(ph==null)
		{
			return true;		
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 根据tphone检查真实表存在新的
	 * 
	 * @author xiping.tang
	 * @createTime 2012-01-17
	 * @param tphone
	 * @return 是否为真
	 */
	public boolean isNewPhone2(TPhone tphone) {
		String inBrandName = tphone.getBrandName();
		String inPhoneName = tphone.getPhoneName();
		String simplePhoneName = "";
		if (inBrandName.contains("（") && inBrandName.contains("）")) {
			String newBrandName = inBrandName.substring(inBrandName
					.indexOf("（") + 1, inBrandName.indexOf("）"));
			simplePhoneName = inPhoneName.replace(newBrandName, "");
		}
		if (!phoneManageService.checkPhoneName(simplePhoneName)) {
			return true;
		} else {
			return false;
		}
	}
	
	public IPhoneManageService getPhoneManageService() {
		return phoneManageService;
	}

	public void setPhoneManageService(IPhoneManageService phoneManageService) {
		this.phoneManageService = phoneManageService;
	}

	/**
	 * 根据条件查询手机机型
	 * @author luowei
	 * @version v0.0.1
	 * @createTime:2011-1-19
	 */
	public Page findAllPhones(Page page, List<PropertyFilter> pfList) {
		//初始化hql
		StringBuffer hql=new StringBuffer("from TPhone");
		//设置查询条件
		String condition= QueryUtil.toSqlString(pfList, true);
		hql.append(condition);
		//查找所有用户的总条数
		int totalCount=this.tphoneDAO.findCountBySql(hql.toString());
		page.setTotalCount(totalCount);
		
		//是否存在排序
		if(page.isOrderBySetted()){
			hql.append(page.getOrder());
		}
		List<TPhone> list=this.tphoneDAO.findList(hql.toString(), page.getPageNo(), page.getPageSize());
		//查出结果集
		page.setResult(list);
	
		return page;
	}

	public TPhoneDAO getTphoneDAO() {
		return tphoneDAO;
	}

	public void setTphoneDAO(TPhoneDAO tphoneDAO) {
		this.tphoneDAO = tphoneDAO;
	}

	/**
	 * 查找临时机型列表
	 * @author luowei
	 * @version v0.0.1
	 * @createTime:2011-1-19
	 */
	public List<TPhone> findList() {
		String hql="from TPhone";
		return tphoneDAO.findList(hql);
	}

	/**
	 * 清空临时机型
	 * @author luowei
	 * @createTime 2012-01-20
	 * @return
	 */
	public boolean clearAll() {
		
		tphoneDAO.executeSql("delete from tphone");
		return true;
	}
	
	/**
	 * 机型的完整名转换成正常机型名
	 * @author wei.luo
	 * @createTime 2012-2-9
	 * @param tphone
	 * @return
	 */
	public String fullName2Name(TPhone tPhone) {
		String phoneName=null;
		if(tPhone!=null){
			phoneName=tPhone.getPhoneName();
			String brandName = tPhone.getBrandName();
			if(StringUtils.isNotBlank(phoneName) && StringUtils.isNotBlank(brandName)){
				phoneName=phoneName.replace(brandName, "").trim();
				if (brandName.contains("（") && brandName.contains("）")) {
					String newBrandName = brandName.substring(brandName.indexOf("（") + 1, brandName.indexOf("）"));
					phoneName = phoneName.replace(newBrandName, "").trim();
				}
			}
		}
		return phoneName;
	}
}
