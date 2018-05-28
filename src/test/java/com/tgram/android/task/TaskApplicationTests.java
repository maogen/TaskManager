package com.tgram.android.task;

import com.tgram.android.task.bean.User;
import com.tgram.android.task.utils.DateUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {

    private Logger logger = Logger.getLogger(TaskApplicationTests.class);

    @Test
    public void test(){
        logger.info("间隔时间："+DateUtil.getDateSpace("2018-03-22 11:23:0","2018-03-22 11:22:58")+"毫秒");
    }
    @Test
    public void contextLoads() {
    }

    @Test
    public void getUserByName() {
        SqlSession sqlSession = null;
        InputStream is = null;
        try {
            String resource = "SqlMapConfig.xml";
            is = Resources.getResourceAsStream(resource);
            // 创建工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 创建Session
            sqlSession = sqlSessionFactory.openSession();
            // 通过Session操作数据库
            User user = sqlSession.selectOne("user.findUserByName", "admin");
            if (null == user) {
                logger.info("未找到用户");
            } else {
                logger.info("找到用户" + user.getUsername() + "；联系方式是" + user.getPhone());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void getUserLikeIdCard() {
        SqlSession sqlSession = null;
        InputStream is = null;
        try {

            String resource = "SqlMapConfig.xml";
            is = Resources.getResourceAsStream(resource);
            // 创建工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 创建Session
            sqlSession = sqlSessionFactory.openSession();
            // 通过Session操作数据库
            List<User> list = sqlSession.selectList("user.findUserLikeIdCard", "342402");
            if (null == list || list.size() == 0) {
                logger.info("未找到用户");
            } else {
                for (User user : list) {
                    logger.info("找到用户" + user.getUsername() + "；联系方式是" + user.getPhone());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }


    @Test
    public void insertUser() {
        SqlSession sqlSession = null;
        InputStream is = null;
        try {
            String resource = "SqlMapConfig.xml";
            is = Resources.getResourceAsStream(resource);
            // 创建工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            // 创建Session
            sqlSession = sqlSessionFactory.openSession();
            // 通过Session操作数据库
            User user = new User();
//            String uuid = UUID.randomUUID().toString();
//            logger.info("java代码生成了uuid：" + uuid);
//            user.setId(uuid);
            user.setUsername("admin");
            user.setPassword("123456");
            user.setIdcard("2340775257659203");
            user.setGender("1");
            user.setPhone("18233334444");
            user.setAddress("地址");

            String date = DateUtil.getDateByFormat(DateUtil.defaultFormat);
            user.setCreateTime(date);
            user.setUpdateTime(date);
            int count = sqlSession.insert("user.insertUser", user);
            if (count > 0) {
                logger.info("新增用户成功");
            } else {
                logger.info("新增用户失败");
            }
            sqlSession.commit();
            logger.info("新增用户的id:" + user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != sqlSession) {
                sqlSession.close();
            }
        }
    }

}
