package com.rootls.price.bean;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Map;

public class ReadExcel {
	public static Map<String, Map<String, Integer>> readDmPriceExcel(
			Workbook book) {
		Map<String, Map<String, Integer>> list = new HashMap<String, Map<String, Integer>>();
		// 获得第一个表的工作对象，“0”表示第一个表
		Sheet sheet = book.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			String ganglianId = subZeroAndDot(sheet.getRow(i).getCell(0)
					.toString());
			String productId = subZeroAndDot(sheet.getRow(i).getCell(1)
					.toString());
			String toganglian = "";
			if (null != sheet.getRow(i).getCell(8)) {
				toganglian = subZeroAndDot(sheet.getRow(i).getCell(8)
						.toString());
			}
			Map<String, Integer> record = new HashMap<String, Integer>();
			if ((null != productId && !"".equals(productId))
					&& (null != ganglianId && !"".equals(ganglianId))) {
				try {
					Integer.valueOf(ganglianId);
				} catch (Exception ex) {
					continue;
				}
				if (null == toganglian || "".equals(toganglian)) {
					toganglian = "-1";
				} else if ("是".equals(toganglian)) {
					toganglian = "1";
				} else if ("否".equals(toganglian)) {
					toganglian = "0";
				} else if ("1".equals(toganglian)) {
					toganglian = "1";
				} else if ("0".equals(toganglian)) {
					toganglian = "0";
				} else {
					toganglian = "-1";
				}
				int toganglianz = Integer.valueOf(toganglian);
				record.put(ganglianId, toganglianz);
				list.put(productId, record);
			}
		}
		return list;
	}

	public static Map<String, Map<String, Integer>> readDmOilPriceExcel(
			Workbook book) {
		Map<String, Map<String, Integer>> list = new HashMap<String, Map<String, Integer>>();
		// 获得第一个表的工作对象，“0”表示第一个表
		Sheet sheet = book.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			String ganglianId = subZeroAndDot(sheet.getRow(i).getCell(0)
					.toString());
			String productId = subZeroAndDot(sheet.getRow(i).getCell(1)
					.toString());
			String toganglian = "";
			if (null != sheet.getRow(i).getCell(7)) {
				toganglian = subZeroAndDot(sheet.getRow(i).getCell(7)
						.toString());
			}
			Map<String, Integer> record = new HashMap<String, Integer>();
			if ((null != productId && !"".equals(productId))
					&& (null != ganglianId && !"".equals(ganglianId))) {
				try {
					Integer.valueOf(ganglianId);
				} catch (Exception ex) {
					continue;
				}
				if (null == toganglian || "".equals(toganglian)) {
					toganglian = "-1";
				} else if ("是".equals(toganglian)) {
					toganglian = "1";
				} else if ("否".equals(toganglian)) {
					toganglian = "0";
				} else if ("1".equals(toganglian)) {
					toganglian = "1";
				} else if ("0".equals(toganglian)) {
					toganglian = "0";
				} else {
					toganglian = "-1";
				}
				int toganglianz = Integer.valueOf(toganglian);
				record.put(ganglianId, toganglianz);
				list.put(productId, record);
			}
		}
		return list;
	}

	public static Map<String, Map<String, Integer>> readInteralPriceExcel(
			Workbook book) {
		Map<String, Map<String, Integer>> list = new HashMap<String, Map<String, Integer>>();
		// 获得第一个表的工作对象，“0”表示第一个表
		Sheet sheet = book.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			String ganglianId = subZeroAndDot(sheet.getRow(i).getCell(0)
					.toString());
			String productId = subZeroAndDot(sheet.getRow(i).getCell(1)
					.toString());
			String toganglian = "";
			if (null != sheet.getRow(i).getCell(8)) {
				toganglian = subZeroAndDot(sheet.getRow(i).getCell(8)
						.toString());
			}
			Map<String, Integer> record = new HashMap<String, Integer>();
			if ((null != productId && !"".equals(productId))
					&& (null != ganglianId && !"".equals(ganglianId))) {
				try {
					Integer.valueOf(ganglianId);
				} catch (Exception ex) {
					continue;
				}
				if (null == toganglian || "".equals(toganglian)) {
					toganglian = "-1";
				} else if ("是".equals(toganglian)) {
					toganglian = "1";
				} else if ("否".equals(toganglian)) {
					toganglian = "0";
				} else if ("1".equals(toganglian)) {
					toganglian = "1";
				} else if ("0".equals(toganglian)) {
					toganglian = "0";
				} else {
					toganglian = "-1";
				}
				int toganglianz = Integer.valueOf(toganglian);
				record.put(ganglianId, toganglianz);
				list.put(productId, record);
			}
		}
		return list;
	}

	public static Map<String, Map<String, Integer>> readDEPriceExcel(
			Workbook book) {
		Map<String, Map<String, Integer>> list = new HashMap<String, Map<String, Integer>>();
		// 获得第一个表的工作对象，“0”表示第一个表
		Sheet sheet = book.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		for (int i = 1; i <= rows; i++) {
			String ganglianId = subZeroAndDot(sheet.getRow(i).getCell(0)
					.toString());
			String productId = subZeroAndDot(sheet.getRow(i).getCell(1)
					.toString());
			String toganglian = "";
			if (null != sheet.getRow(i).getCell(8)) {
				toganglian = subZeroAndDot(sheet.getRow(i).getCell(8)
						.toString());
			}
			Map<String, Integer> record = new HashMap<String, Integer>();
			if ((null != productId && !"".equals(productId))
					&& (null != ganglianId && !"".equals(ganglianId))) {
				try {
					Integer.valueOf(ganglianId);
				} catch (Exception ex) {
					continue;
				}
				if (null == toganglian || "".equals(toganglian)) {
					toganglian = "-1";
				} else if ("是".equals(toganglian)) {
					toganglian = "1";
				} else if ("否".equals(toganglian)) {
					toganglian = "0";
				} else if ("1".equals(toganglian)) {
					toganglian = "1";
				} else if ("0".equals(toganglian)) {
					toganglian = "0";
				} else {
					toganglian = "-1";
				}
				int toganglianz = Integer.valueOf(toganglian);
				record.put(ganglianId, toganglianz);
				list.put(productId, record);
			}
		}
		return list;
	}

	// 判断是否有数字或者小数点
	public static String isNumOrDount(String str) {
		String temp = "";
		char[] q = str.toCharArray();
		for (int i = 0; i < q.length; i++) {
			if (q[i] == '.' || q[i] == '0' || q[i] == '1' || q[i] == '2'
					|| q[i] == '3' || q[i] == '4' || q[i] == '5' || q[i] == '6'
					|| q[i] == '7' || q[i] == '8' || q[i] == '9') {
				temp = temp + q[i];
			} else {
			}
		}
		return temp;
	}

	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}
