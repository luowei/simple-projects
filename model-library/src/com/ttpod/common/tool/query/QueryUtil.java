package com.ttpod.common.tool.query;

import java.util.List;

/**
 * @className:QueryUtil.java
 * @classDescription:
 * @author:xiayingjie
 * @createTime:2010-7-13
 */
public class QueryUtil {

	/**
	 * 获取字段查询语句
	 * @param pf
	 * @return
	 */
	public static  String toSqlStringByField(PropertyFilter pf){
		//如果过滤属性为空，直接返回空
		String filterStr="";
		if("".equals(pf.getPropertyValue())||null==pf.getPropertyValue()){
			return filterStr;
		}
		//获取比较符
		String matchStr="";
		if (PropertyFilter.MatchType.EQ.equals(pf.getMatchType())) {			
			matchStr="=";
		} else if (PropertyFilter.MatchType.LIKE.equals(pf.getMatchType())) {
			//暂时只实现全匹配
			matchStr="like";
			pf.setPropertyValue("%"+pf.getPropertyValue()+"%");
		} else if (PropertyFilter.MatchType.LE.equals(pf.getMatchType())) {
			matchStr="<=";
		} else if (PropertyFilter.MatchType.LT.equals(pf.getMatchType())) {
			matchStr="<";
		} else if (PropertyFilter.MatchType.GE.equals(pf.getMatchType())) {
			matchStr=">=";
		} else if (PropertyFilter.MatchType.GT.equals(pf.getMatchType())) {
			matchStr=">";
		}
		//获取sql查询属性
		if(PropertyFilter.PropertyType.S.getValue()==pf.getPropertyType()
		  ||PropertyFilter.PropertyType.D.getValue()==pf.getPropertyType() 
		){
			pf.setPropertyValue("'"+pf.getPropertyValue()+"'");
		}
		filterStr=" "+pf.getPropertyName()+" "+matchStr+" "+pf.getPropertyValue();
		return filterStr;
	}
	/**
	 * 获取多条件查询语句(暂时条件都是 and)
	 * @param pfList
	 * @param flag  是否需要where
	 * @return
	 */
	public static String toSqlString(List<PropertyFilter>pfList,boolean flag ){	
		StringBuffer fragment = new StringBuffer();
		//属性组合
		for(PropertyFilter pf:pfList){
			if (flag){
				if( null!=pf.getPropertyValue()&&!"".equals(pf.getPropertyValue())){
					fragment.append("  where  ");
					fragment.append(toSqlStringByField(pf));
					flag=false;
				}
			
			}else{
				if( null!=pf.getPropertyValue()&&!"".equals(pf.getPropertyValue())){
					fragment.append("  and  ");
					fragment.append(toSqlStringByField(pf));
				}
				
			}
		}
		return fragment.toString();
	}

}
