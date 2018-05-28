package com.tgram.android.task;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

/**
 * 测试UserDao
 */
public class TestDao {

    private Logger logger;

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() {
        logger = Logger.getLogger(TaskApplicationTests.class);

        InputStream is = null;
        try {
            String resource = "SqlMapConfig.xml";
            is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
