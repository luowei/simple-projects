package com.rootls.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.rootls.bean.Config;
import com.rootls.bean.FJSearchBean;
import com.rootls.model.ContractFJ;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

/**
 * 合同 User: luowei Date: 13-3-22 Time: 下午2:30 To change this template use File |
 * Settings | File Templates.
 */
@Component
public class ContractFJRepository extends BaseRepository {

	Logger logger = Logger.getLogger(ContractFJRepository.class);

	public void updateContract(Integer id, Integer type) {
		StringBuilder tempSql = new StringBuilder(
				"update guestbook_ht_file set filelx =" + type + " where id="
						+ id);
		getJdbcTemplate().execute(tempSql.toString());
	}

	public ContractFJ getContractById(Integer id) {
		StringBuilder tempSql = new StringBuilder(
				"SELECT * FROM v_ht_file where id=" + id);
		List<ContractFJ> results = getJdbcTemplate().query(tempSql.toString(),
				new RowMapper<ContractFJ>() {
					@Override
					public ContractFJ mapRow(ResultSet rs, int i)
							throws SQLException {
						ContractFJ temp = new ContractFJ();
						temp.setId(rs.getInt("id"));
						temp.setGid(rs.getInt("gid"));
						temp.setSid(rs.getInt("sid"));
						temp.setHtBianhao(rs.getString("htbianhao"));
						temp.setTitle(rs.getString("title"));
						temp.setType(rs.getInt("filelx"));
						temp.setFileName(rs.getString("filename"));
						temp.setRealPath(rs.getString("filepath"));
						temp.setHtType(rs.getInt("htlx"));
						temp.setUserName(rs.getString("name"));
						temp.setCompanyName(rs.getString("qymc"));
						temp.setTrackId(rs.getInt("adminid"));
						// TODO
						Date date = rs.getDate("htdate");
						Date date1 = rs.getDate("htdate1");
						Date date2 = rs.getDate("htdate2");
						temp.setStartDate(date1);
						temp.setEndDate(date2);
						String adminName = rs.getString("adminName");
						return temp;
					}
				});
		if (results.size() != 0 && null != results) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public List<ContractFJ> getContractFjByCodition(FJSearchBean fjSearchBean,
			String adminId) {
		if ("".equals(adminId) || null == adminId) {
			return null;
		}
		List<String> adminIds = null;
		/**
		 * adminIds == null 只有自己 adminIds.size=0 所有 adminIds.size>0 集合内的
		 */
		String powersql = "select power from admin where id=" + adminId;
		List<String> resultz = getJdbcTemplate().query(powersql,
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet res, int i)
							throws SQLException {
						return res.getString("power");
					}
				});
		if (resultz != null && resultz.size() > 0) {
			// 获取35位权限
			String truth = resultz.get(0);
			truth = truth.substring(31, 32);

			if (truth.equals("0")) {

			} else if (truth.equals("1")) {
				// 判断mag权限
				JdbcTemplate jdbcTemplate = new JdbcTemplate(
						BasicDataSource.class.cast(ContextLoader
								.getCurrentWebApplicationContext().getBean(
										"dataSource")));
				String mysql = "select MagMan from [Lz_Admin] where id="
						+ adminId;
				List<String> mags = jdbcTemplate.query(mysql,
						new RowMapper<String>() {
							@Override
							public String mapRow(ResultSet res, int i)
									throws SQLException {
								return res.getString("MagMan");
							}
						});
				if (null != mags && mags.size() > 0) {
					if (mags.get(0).equals("1")) {
						adminIds = new ArrayList<String>();
					} else if (mags.get(0).equals("0")) {
						String magsql = "select ManID from  [Lz_MagMan] where MagID="
								+ adminId;
						List<String> result2 = getJdbcTemplate().query(magsql,
								new RowMapper<String>() {

									@Override
									public String mapRow(ResultSet res, int i)
											throws SQLException {
										return res.getString("ManID");
									}
								});
						adminIds = result2;
					}
				}

			}
		}

		List<ContractFJ> result = new ArrayList<ContractFJ>();
		int pageNumber = Integer.valueOf(fjSearchBean.getPage());
		int startRow = (pageNumber - 1) * Config.PAGE_SIZE;
		int endRow = pageNumber * Config.PAGE_SIZE;
		StringBuilder whereCondition = new StringBuilder();
		String orderCondition = "";
		if (null != fjSearchBean.getOrder()
				&& !"".equals(fjSearchBean.getOrder())) {
			if (fjSearchBean.getOrder().equals("1")) {
				orderCondition = " id desc";
			} else if (fjSearchBean.getOrder().equals("2")) {
				orderCondition = " id ";
			} else if (fjSearchBean.getOrder().equals("3")) {
				orderCondition = " htdate desc";
			} else if (fjSearchBean.getOrder().equals("4")) {
				orderCondition = " htdate ";
			} else if (fjSearchBean.getOrder().equals("5")) {
				orderCondition = " htdate1 desc";
			} else if (fjSearchBean.getOrder().equals("6")) {
				orderCondition = " htdate1 ";
			} else if (fjSearchBean.getOrder().equals("7")) {
				orderCondition = " htdate2 desc";
			} else if (fjSearchBean.getOrder().equals("8")) {
				orderCondition = " htdate2 ";
			}
		} else {
			orderCondition = " id ";
		}
		if (fjSearchBean.getStartDate() != null
				&& !"".equals(fjSearchBean.getStartDate())) {
			whereCondition.append(" and ");
			if (fjSearchBean.getMtype().equals("htdate")) {
				whereCondition.append("htdate >= '"
						+ fjSearchBean.getStartDate());
			} else if (fjSearchBean.getMtype().equals("htdate1")) {
				whereCondition.append("htdate1 >= '"
						+ fjSearchBean.getStartDate());
			} else if (fjSearchBean.getMtype().equals("htdate2")) {
				whereCondition.append("htdate2 >= '"
						+ fjSearchBean.getStartDate());
			}
			whereCondition.append("'");
		}
		if (fjSearchBean.getEndDate() != null
				&& !"".equals(fjSearchBean.getEndDate())) {
			whereCondition.append(" and ");
			if (fjSearchBean.getMtype().equals("htdate")) {
				whereCondition
						.append("htdate <= '" + fjSearchBean.getEndDate());
			} else if (fjSearchBean.getMtype().equals("htdate1")) {
				whereCondition.append("htdate1 <= '"
						+ fjSearchBean.getEndDate());
			} else if (fjSearchBean.getMtype().equals("htdate2")) {
				whereCondition.append("htdate2 <= '"
						+ fjSearchBean.getEndDate());
			}
			whereCondition.append("'");
		}
		if (null != fjSearchBean.getHtType()
				&& !"".equals(fjSearchBean.getHtType())) {
			whereCondition.append(" and htlx=" + fjSearchBean.getHtType());
		}
		if (null != fjSearchBean.getTrackName()
				&& !"".equals(fjSearchBean.getTrackName())) {
			String leftPart = fjSearchBean.getTrackName();
			leftPart = leftPart.substring(0, leftPart.indexOf("."));
			whereCondition.append(" and adminid ='" + leftPart + "'");
		}
		if (null != fjSearchBean.getType()
				&& !"".equals(fjSearchBean.getType())) {
			String[] types = fjSearchBean.getType();
			String temp = "";
			for (String str : types) {
				temp += str + ",";
			}
			if ("" != temp) {
				temp = temp.substring(0, temp.lastIndexOf(","));
			}
			whereCondition.append(" and filelx in (" + temp + ")");
		}
		if (null != fjSearchBean.getConditionName()
				&& !"".equals(fjSearchBean.getConditionName())) {
			if (null != fjSearchBean.getConditionFlag()
					&& !"".equals(fjSearchBean.getConditionFlag())) {
				if (null != fjSearchBean.getConditionValue()
						&& !"".equals(fjSearchBean.getConditionValue())) {
					String value = fjSearchBean.getConditionValue();
					if (fjSearchBean.getConditionName().equals("qymc")) {
						whereCondition.append(" and qymc");
					} else if (fjSearchBean.getConditionName().equals("lxr")) {
						whereCondition.append(" and name");
					} else if (fjSearchBean.getConditionName().equals("mobile")) {
						whereCondition.append(" and htbianhao");
					}
					if (fjSearchBean.getConditionFlag().equals("=")) {
						whereCondition.append(" = '" + value + "'");
					} else if (fjSearchBean.getConditionFlag().equals("like")) {
						whereCondition.append(" like '%" + value + "%'");
					} else if (fjSearchBean.getConditionFlag().equals("not")) {
						whereCondition.append(" not like '%" + value + "%'");
					}

				}
			}

		}
		StringBuilder tempSql = new StringBuilder("");
		if (null == adminIds) {
			tempSql = new StringBuilder(
					"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
							+ orderCondition
							+ ") AS RowNo FROM v_ht_file where 1=1 and adminid = "
							+ adminId + whereCondition
							+ " ) AS A WHERE RowNo > " + startRow
							+ " and RowNo <= " + endRow + ";");
		} else {
			if (adminIds.size() == 0) {
				tempSql = new StringBuilder(
						"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
								+ orderCondition
								+ ") AS RowNo FROM v_ht_file where 1=1 "
								+ whereCondition + " ) AS A WHERE RowNo > "
								+ startRow + " and RowNo <= " + endRow + ";");
			} else {
				String inStr = "";
				for (String str : adminIds) {
					inStr += str + ",";
				}
				inStr = inStr.substring(0, inStr.lastIndexOf(","));
				tempSql = new StringBuilder(
						"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
								+ orderCondition
								+ ") AS RowNo FROM v_ht_file where 1=1 and adminid in("
								+ inStr + ") " + whereCondition
								+ " ) AS A WHERE RowNo > " + startRow
								+ " and RowNo <= " + endRow + ";");
			}
		}
		result = getJdbcTemplate().query(tempSql.toString(),
				new RowMapper<ContractFJ>() {
					@Override
					public ContractFJ mapRow(ResultSet rs, int i)
							throws SQLException {
						ContractFJ temp = new ContractFJ();
						temp.setId(rs.getInt("id"));
						temp.setGid(rs.getInt("gid"));
						temp.setSid(rs.getInt("sid"));
						temp.setHtBianhao(rs.getString("htbianhao"));
						temp.setTitle(rs.getString("title"));
						temp.setType(rs.getInt("filelx"));
						temp.setFileName(rs.getString("filename"));
						temp.setRealPath(rs.getString("filepath"));
						temp.setHtType(rs.getInt("htlx"));
						temp.setUserName(rs.getString("name"));
						temp.setCompanyName(rs.getString("qymc"));
						temp.setTrackId(rs.getInt("adminid"));
						// TODO
						Date date = rs.getDate("htdate");
						Date date1 = rs.getDate("htdate1");
						Date date2 = rs.getDate("htdate2");
						temp.setStartDate(date1);
						temp.setEndDate(date2);
						String adminName = rs.getString("adminName");
						return temp;
					}
				});
		return result;
	}

	public int getPageCountByCondition(FJSearchBean fjSearchBean, String adminId) {
		List<String> adminIds = null;
		/**
		 * adminIds == null 只有自己 adminIds.size=0 所有 adminIds.size>0 集合内的
		 */
		if (adminId == null || adminId.equals("")) {
			return 0;
		}
		String powersql = "select power from admin where id=" + adminId;
		List<String> resultz = getJdbcTemplate().query(powersql,
				new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet res, int i)
							throws SQLException {
						return res.getString("power");
					}
				});
		if (resultz != null && resultz.size() > 0) {
			// 获取35位权限
			String truth = resultz.get(0);
			truth = truth.substring(31, 32);

			if (truth.equals("0")) {

			} else if (truth.equals("1")) {
				// 判断mag权限
				JdbcTemplate jdbcTemplate = new JdbcTemplate(
						BasicDataSource.class.cast(ContextLoader
								.getCurrentWebApplicationContext().getBean(
										"dataSource")));
				String mysql = "select MagMan from [Lz_Admin] where id="
						+ adminId;
				List<String> mags = jdbcTemplate.query(mysql,
						new RowMapper<String>() {
							@Override
							public String mapRow(ResultSet res, int i)
									throws SQLException {
								return res.getString("MagMan");
							}
						});
				if (null != mags && mags.size() > 0) {
					if (mags.get(0).equals("1")) {
						adminIds = new ArrayList<String>();
					} else if (mags.get(0).equals("0")) {
						String magsql = "select ManID from  [Lz_MagMan] where MagID="
								+ adminId;
						List<String> result2 = getJdbcTemplate().query(magsql,
								new RowMapper<String>() {

									@Override
									public String mapRow(ResultSet res, int i)
											throws SQLException {
										return res.getString("ManID");
									}
								});
						adminIds = result2;
					}
				}

			}
		}
		List<ContractFJ> result = new ArrayList<ContractFJ>();
		int pageNumber = Integer.valueOf(fjSearchBean.getPage());
		int startRow = (pageNumber - 1) * Config.PAGE_SIZE;
		int endRow = pageNumber * Config.PAGE_SIZE;
		String orderCondition = "";
		if (null != fjSearchBean.getOrder()
				&& !"".equals(fjSearchBean.getOrder())) {
			if (fjSearchBean.getOrder().equals("1")) {
				orderCondition = " id desc";
			} else if (fjSearchBean.getOrder().equals("2")) {
				orderCondition = " id ";
			} else if (fjSearchBean.getOrder().equals("3")) {
				orderCondition = " htdate desc";
			} else if (fjSearchBean.getOrder().equals("4")) {
				orderCondition = " htdate ";
			} else if (fjSearchBean.getOrder().equals("5")) {
				orderCondition = " htdate1 desc";
			} else if (fjSearchBean.getOrder().equals("6")) {
				orderCondition = " htdate1 ";
			} else if (fjSearchBean.getOrder().equals("7")) {
				orderCondition = " htdate2 desc";
			} else if (fjSearchBean.getOrder().equals("8")) {
				orderCondition = " htdate2 ";
			}
		} else {
			orderCondition = " id ";
		}
		StringBuilder whereCondition = new StringBuilder();
		if (fjSearchBean.getStartDate() != null
				&& !"".equals(fjSearchBean.getStartDate())) {
			whereCondition.append(" and ");
			if (fjSearchBean.getMtype().equals("htdate")) {
				whereCondition.append("htdate >= '"
						+ fjSearchBean.getStartDate());
			} else if (fjSearchBean.getMtype().equals("htdate1")) {
				whereCondition.append("htdate1 >= '"
						+ fjSearchBean.getStartDate());
			} else if (fjSearchBean.getMtype().equals("htdate2")) {
				whereCondition.append("htdate2 >= '"
						+ fjSearchBean.getStartDate());
			}
			whereCondition.append("'");
		}
		if (fjSearchBean.getEndDate() != null
				&& !"".equals(fjSearchBean.getEndDate())) {
			whereCondition.append(" and ");
			if (fjSearchBean.getMtype().equals("htdate")) {
				whereCondition
						.append("htdate <= '" + fjSearchBean.getEndDate());
			} else if (fjSearchBean.getMtype().equals("htdate1")) {
				whereCondition.append("htdate1 <= '"
						+ fjSearchBean.getEndDate());
			} else if (fjSearchBean.getMtype().equals("htdate2")) {
				whereCondition.append("htdate2 <= '"
						+ fjSearchBean.getEndDate());
			}
			whereCondition.append("'");
		}
		if (null != fjSearchBean.getHtType()
				&& !"".equals(fjSearchBean.getHtType())) {
			whereCondition.append(" and htlx=" + fjSearchBean.getHtType());
		}
		if (null != fjSearchBean.getTrackName()
				&& !"".equals(fjSearchBean.getTrackName())) {
			String leftPart = fjSearchBean.getTrackName();
			leftPart = leftPart.substring(0, leftPart.indexOf("."));
			whereCondition.append(" and adminid ='" + leftPart + "'");
		}
		if (null != fjSearchBean.getType()
				&& !"".equals(fjSearchBean.getType())) {
			String[] types = fjSearchBean.getType();
			String temp = "";
			for (String str : types) {
				temp += str + ",";
			}
			if ("" != temp) {
				temp = temp.substring(0, temp.lastIndexOf(","));
			}
			whereCondition.append(" and filelx in (" + temp + ")");
		}
		if (null != fjSearchBean.getConditionName()
				&& !"".equals(fjSearchBean.getConditionName())) {
			if (null != fjSearchBean.getConditionFlag()
					&& !"".equals(fjSearchBean.getConditionFlag())) {
				if (null != fjSearchBean.getConditionValue()
						&& !"".equals(fjSearchBean.getConditionValue())) {
					String value = fjSearchBean.getConditionValue();
					if (fjSearchBean.getConditionName().equals("qymc")) {
						whereCondition.append(" and qymc");
					} else if (fjSearchBean.getConditionName().equals("lxr")) {
						whereCondition.append(" and name");
					} else if (fjSearchBean.getConditionName().equals("mobile")) {
						whereCondition.append(" and htbianhao");
					}
					if (fjSearchBean.getConditionFlag().equals("=")) {
						whereCondition.append(" = '" + value + "'");
					} else if (fjSearchBean.getConditionFlag().equals("like")) {
						whereCondition.append(" like '%" + value + "%'");
					} else if (fjSearchBean.getConditionFlag().equals("not")) {
						whereCondition.append(" not like '%" + value + "%'");
					}

				}
			}

		}

		StringBuilder tempSql = new StringBuilder("");
		if (null == adminIds) {
			tempSql = new StringBuilder(
					"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
							+ orderCondition
							+ ") AS RowNo FROM v_ht_file where 1=1 and adminid = "
							+ adminId + whereCondition
							+ " ) AS A WHERE 1=1;");
		} else {
			if (adminIds.size() == 0) {
				tempSql = new StringBuilder(
						"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
								+ orderCondition
								+ ") AS RowNo FROM v_ht_file where 1=1 "
								+ whereCondition + " ) AS A WHERE 1=1");
			} else {
				String inStr = "";
				for (String str : adminIds) {
					inStr += str + ",";
				}
				inStr = inStr.substring(0, inStr.lastIndexOf(","));
				tempSql = new StringBuilder(
						"SELECT * FROM(SELECT *,ROW_NUMBER() OVER (ORDER BY "
								+ orderCondition
								+ ") AS RowNo FROM v_ht_file where 1=1 and adminid in("
								+ inStr + ") " + whereCondition
								+ " ) AS A WHERE 1=1;");
			}
		}
		result = getJdbcTemplate().query(tempSql.toString(),
				new RowMapper<ContractFJ>() {
					@Override
					public ContractFJ mapRow(ResultSet rs, int i)
							throws SQLException {
						ContractFJ temp = new ContractFJ();
						temp.setId(rs.getInt("id"));
						temp.setGid(rs.getInt("gid"));
						temp.setSid(rs.getInt("sid"));
						temp.setHtBianhao(rs.getString("htbianhao"));
						temp.setTitle(rs.getString("title"));
						temp.setType(rs.getInt("filelx"));
						temp.setFileName(rs.getString("filename"));
						temp.setRealPath(rs.getString("filepath"));
						temp.setHtType(rs.getInt("htlx"));
						temp.setUserName(rs.getString("name"));
						temp.setCompanyName(rs.getString("qymc"));
						temp.setTrackId(rs.getInt("adminid"));
						// TODO
						Date date = rs.getDate("htdate");
						Date date1 = rs.getDate("htdate1");
						Date date2 = rs.getDate("htdate2");
						temp.setStartDate(date1);
						temp.setEndDate(date2);
						String adminName = rs.getString("adminName");
						return temp;
					}
				});
		return result.size();
	}

	public Boolean deleteOptionBeforeCheck(String adminID, String fjId) {
		List<ContractFJ> result = new ArrayList<ContractFJ>();
		String sql = "select * from v_ht_file where id =  "
				+ fjId
				+ " and ywyreadonly=0 and isValid=0 ";
		result = getJdbcTemplate().query(sql, new RowMapper<ContractFJ>() {
			@Override
			public ContractFJ mapRow(ResultSet rs, int i) throws SQLException {
				ContractFJ temp = new ContractFJ();
				temp.setId(rs.getInt("id"));
				temp.setGid(rs.getInt("gid"));
				temp.setSid(rs.getInt("sid"));
				temp.setHtBianhao(rs.getString("htbianhao"));
				temp.setTitle(rs.getString("title"));
				temp.setType(rs.getInt("filelx"));
				temp.setFileName(rs.getString("filename"));
				temp.setRealPath(rs.getString("filepath"));
				temp.setHtType(rs.getInt("htlx"));
				temp.setUserName(rs.getString("name"));
				temp.setCompanyName(rs.getString("qymc"));
				temp.setTrackId(rs.getInt("adminid"));
				// TODO
				Date date = rs.getDate("htdate");
				Date date1 = rs.getDate("htdate1");
				Date date2 = rs.getDate("htdate2");
				temp.setStartDate(date1);
				temp.setEndDate(date2);
				String adminName = rs.getString("adminName");
				return temp;
			}
		});
		if (result.size() <= 0) {
			return false;
		} else {
			ContractFJ fj = result.get(0);
			String adminId = fj.getTrackId() + "";
			String sql2 = "select count(*) as id from admin where id = "
					+ adminId + " and ht_delright = 1";
			List<String> resultz = getJdbcTemplate().query(sql,
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int i)
								throws SQLException {
							return rs.getInt("id") + "";
						}
					});
			if (resultz.size() <= 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	public Boolean updateOptionBeforeCheck(String adminID, String fjId) {
		List<ContractFJ> result = new ArrayList<ContractFJ>();
		String sql = "select * from v_ht_file where id =  "
				+ fjId
				+ " and ywyreadonly=0 and isValid=0  ";
		result = getJdbcTemplate().query(sql, new RowMapper<ContractFJ>() {
			@Override
			public ContractFJ mapRow(ResultSet rs, int i) throws SQLException {
				ContractFJ temp = new ContractFJ();
				temp.setId(rs.getInt("id"));
				temp.setGid(rs.getInt("gid"));
				temp.setSid(rs.getInt("sid"));
				temp.setHtBianhao(rs.getString("htbianhao"));
				temp.setTitle(rs.getString("title"));
				temp.setType(rs.getInt("filelx"));
				temp.setFileName(rs.getString("filename"));
				temp.setRealPath(rs.getString("filepath"));
				temp.setHtType(rs.getInt("htlx"));
				temp.setUserName(rs.getString("name"));
				temp.setCompanyName(rs.getString("qymc"));
				temp.setTrackId(rs.getInt("adminid"));
				// TODO
				Date date = rs.getDate("htdate");
				Date date1 = rs.getDate("htdate1");
				Date date2 = rs.getDate("htdate2");
				temp.setStartDate(date1);
				temp.setEndDate(date2);
				String adminName = rs.getString("adminName");
				return temp;
			}
		});
		if (result.size() <= 0) {
			return false;
		} else {
			ContractFJ fj = result.get(0);
			String adminId = fj.getTrackId() + "";
			String sql2 = "select count(*) as id from admin where id = "
					+ adminId + " and ht_audits = 1";
			List<String> resultz = getJdbcTemplate().query(sql,
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int i)
								throws SQLException {
							return rs.getInt("id") + "";
						}
					});
			if (resultz.size() <= 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	public void updateContractFile(ContractFJ contractFj) {
		String id = contractFj.getId() + "";
		if (contractFj.getId() == null || contractFj.getRealPath() == null) {
			return;
		}
		Date date = new Date();
		SimpleDateFormat dateformat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss E");
		int flag = contractFj.getFileName().lastIndexOf(".");
		String filetype = contractFj.getFileName().substring(flag);
		StringBuilder tempSql = new StringBuilder(
				"update guestbook_ht_file set filepath ='"
						+ contractFj.getRealPath()
						+ "',uploadtime =getdate(),filename='"
						+ contractFj.getFileName() + "',filetype='" + filetype
						+ "'  where id=" + id);
		getJdbcTemplate().execute(tempSql.toString());
	}

	public void deleteContract(String id) {
		if (null == id || "".equals(id)) {
			return;
		} else {
			String sql = "delete from guestbook_ht_file where id=" + id;
			getJdbcTemplate().execute(sql);
		}
	}

}
