package com.am.test;

import com.am.model.AmMusic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;


//import javax.servlet.jsp.jstl.core.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:config/applicationContext.xml")
public class TestApplicationContext {
	
	/*@Autowired
	private Music music;*/
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void handle() throws SQLException {
		//测试加载applicationContext.xml文件
		/*System.out.println("---------------------测试1-----------------------");
		System.out.println("测试加载applicationContext.xml文件成功！");
		System.out.println("测试bean：id = "+music.getId()+",title = "+music.getTitle());*/
		
		//测试2
		/*System.out.println("---------------------测试2-----------------------");
		Music m = context.getBean(Music.class);
		System.out.println("测试bean：id = "+m.getId()+",title = "+m.getTitle());*/
		
		//测试数据库连接
		/*System.out.println("---------------------测试3-----------------------");
		ComboPooledDataSource dataSource = context.getBean(ComboPooledDataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println("测试数据库连接："+connection);*/
		
		/*System.out.println("---------------------测试4-----------------------");
		 jdbcTemplate.query("select * from am_music", new RowCallbackHandler(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("id = "+rs.getInt("id")+",title = "+rs.getString("title"));
			}
		});*/
		
		SessionFactory sessionFactory = context.getBean(SessionFactory.class);
		System.out.println(sessionFactory);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		AmMusic music = new AmMusic();
		music.setTitle("一个人");
		music.setSinger("xxx");
		session.save(music);
		transaction.commit();
		session.close();
	}
	
	/*@Autowired
	private AlbumDao albumDao;
	
	@Test
	public void testAlbum(){
		AmAlbum album = albumDao.getAmAlbum(1);
		System.out.println(album.getDescription());
	}
	
	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void testComment(){
		//保存评论
		*//*AmComment comment = new AmComment();
		comment.setContent("测试一下评论专辑音乐");
		comment.setUser(new AmUser(1));
		comment.setAlbum(new AmAlbum(1));
		AmComment commt = commentDao.save(comment);
		System.out.println(commt);*//*
		
		//查询评论
		List<AmComment> comments = commentDao.findComments(1, 0, 10);
		for(AmComment comment : comments){
			System.out.println(comment.getContent());
		}
	}*/
}
