package model;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.Table;
import utils.JdbcUtil;

public class TableMd implements Itable {

	@Override
	public List<Table> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_table ";
		List<Table> list = null;
		try {
			list = qr.query(sql,new BeanListHandler<Table>(Table.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Table findSomeone(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_table where id=?";
		Table t = null;
		try {
			t = qr.query(sql, new BeanHandler<Table>(Table.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public void update(Table t) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "update t_table set tablename=?,status=?,schedule=? where id=?";
		
		Object[] params = {
				t.getTableName(),
				t.getStatus(),
				t.getSchedule(),
				t.getId(),
		};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void add(Table t) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "insert into t_table(id,tablename,status,schedule) values(?,?,?,?)";
		
		Object[] params = {
			t.getId(),
			t.getTableName(),
			t.getStatus(),
			t.getSchedule(),			
		};		
		try {
			qr.update(sql, params);
			
		} catch (SQLException e) {			
			e.printStackTrace();
			
		}
	}

	@Override
	public void delete(int id) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "delete from t_table where id=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Table findName(String tableName) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select * from t_table where tableName=?";
		Table t = null;
		try {
			t = qr.query(sql, new BeanHandler<>(Table.class) ,tableName);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return t;
	}

}
