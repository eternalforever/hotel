package service;

import java.util.List;

import entity.Table;
import model.TableMd;

public class TableServ implements TableService {
	private TableMd md = new TableMd();
	
	@Override
	public List<Table> findAllTable() {
		List<Table> list = null;
		try {
			list = md.findAll();
		} catch (Exception e) {
			throw new RuntimeException("kongshuju");
		}
		
		return list;
	}

	@Override
	public Table findTableById(int id) {
		Table t = null;
		try {
			 t = md.findSomeone(id);
		} catch (Exception e) {
			throw new RuntimeException("数据不存在");
		}
		
		return t;
	}

	@Override
	public void updateTable(Table t) {
		if (t != null) {
			md.update(t);
			
		}else {
			throw new RuntimeException("不存在");
		}
		
		
	}

	@Override
	public void add(Table t) {
		if (t != null) {
			md.add(t);
			
		}else {
			throw new RuntimeException("数据为空");
		}
		
	}

	@Override
	public void delete(int id) {
		try {
			md.delete(id);
		} catch (Exception e) {
			throw new RuntimeException("数据不存在");
		}
		
	}

	@Override
	public Table findTableByName(String tableName) {
		Table t = null;
		try {
			t = md.findName(tableName);
		} catch (Exception e) {
			throw  new RuntimeException("对象不存在");
		}
		return t;
	}

}
