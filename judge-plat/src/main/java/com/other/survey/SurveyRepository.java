package com.other.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-10-31
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
@Component
public class SurveyRepository extends JdbcDaoSupport {

    @Autowired
    @Qualifier("pcdbDataSource")
    private DataSource dataSource;

    @PostConstruct
    void init() {
        setDataSource(dataSource);
    }


    public void insert(Survey survey) {

        String sql = " insert into t_survey(userId,userName,userMobie,userAddress,ip," +
                "question01,question02,question03,question04,question05,question06,question07,question08,question09,question10) values(%s)";

        String values = survey.getUserId() + ",'" + survey.getUserName() + "','" + survey.getUserMobie() + "','" + survey.getUserAddress() + "','" + survey.getIp() + "','" +
                survey.getQuestion01() + "','" + survey.getQuestion02() + "','" + survey.getQuestion03() + "','" + survey.getQuestion04() + "','" + survey.getQuestion05() + "','" +
                survey.getQuestion06() + "','" + survey.getQuestion07() + "','" + survey.getQuestion08() + "','" + survey.getQuestion09() + "','" + survey.getQuestion10()+"' ";

        getJdbcTemplate().update(String.format(sql, values));

    }

    public int getIpVoted(String ipAddr) {

        String sql = "select count(*) from t_survey where ip='" + ipAddr + "'";
        return getJdbcTemplate().queryForInt(sql);
    }

    public User findUserById(Integer id) {
        String sql = " select top 1 * from guestbook where id="+id;

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(BoneCPDataSource.class.cast(getCurrentWebApplicationContext().getBean("dataSource")));
        return getJdbcTemplate().query(sql, new ResultSetExtractor<User>(){

            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("mobile"),
                        rs.getString("name"),
                        rs.getString("area")
                );
            }
        });
    }

    public User findUserByUserName(String username) {
        String sql = " select top 1 * from guestbook where name="+username;

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(BoneCPDataSource.class.cast(getCurrentWebApplicationContext().getBean("dataSource")));
        return getJdbcTemplate().query(sql, new ResultSetExtractor<User>(){

            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("mobile"),
                        rs.getString("name"),
                        rs.getString("area")
                );
            }
        });

    }

    public class User {

        Integer Id;
        String userName;
        String realName;
        String userAddress;

        public User() {
        }

        public User(Integer id, String userName, String realName, String userAddress) {
            Id = id;
            this.userName = userName;
            this.realName = realName;
            this.userAddress = userAddress;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }
    }
}
