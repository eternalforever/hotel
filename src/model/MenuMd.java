package model;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Menu;
import utils.JdbcUtil;

public class MenuMd implements IMenu{

	@Override
	public void add(Menu m) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into t_menu(menuName) values(?)";		
		String menuName = m.getMenuName();
		try {
			qr.update(sql, menuName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_menu";
		List<Menu> list = null;
		
		try {
			list = qr.query(sql, new BeanListHandler<Menu>(Menu.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void update(Menu m) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update t_menu set menuName=? where id=?";
		try {
			qr.update(sql, m.getMenuName(),m.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	@Override
	public void delete(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from t_menu where id=?";
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Menu findName(String menuName) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_menu where menuName=?";
		Menu menu = null;
		try {
			menu = qr.query(sql, new BeanHandler<Menu>(Menu.class),menuName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public Menu findSomeone(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_menu where id=?";
		Menu menu = null;
		try {
			menu = qr.query(sql,new BeanHandler<Menu>(Menu.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menu;
	}

}
