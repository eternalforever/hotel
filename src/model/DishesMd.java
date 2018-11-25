package model;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Dishes;
import utils.JdbcUtil;

public class DishesMd implements Idishes {

	@Override
	public List<Dishes> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_dishes";
		List<Dishes> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Dishes.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Dishes findSomeone(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Dishes dish = null;
		String sql = "select * from t_dishes where id=?";
		try {
			dish = qr.query(sql, new BeanHandler<Dishes>(Dishes.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dish;
	}

	@Override
	public void update(Dishes dish) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update t_dishes set dishName=?,typeId=?,price=?,vip=?,comment=?,imgPath=? where id=?";
		Object[] params = {
				dish.getDishName(),
				dish.getTypeID(),
				dish.getPrice(),
				dish.getVip(),
				dish.getComment(),
				dish.getImgPath(),
				dish.getId(),
		};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void add(Dishes dish) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into t_dishes(dishName,typeId,price,vip,comment,imgPath) values(?,?,?,?,?,?)";
		Object[] params = {
				dish.getDishName(),
				dish.getTypeID(),
				dish.getPrice(),
				dish.getVip(),
				dish.getComment(),
				dish.getImgPath()
		};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from t_dishes where id=?";
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Dishes findName(String dishName) {
		// TODO Auto-generated method stub
		return null;
	}

}
