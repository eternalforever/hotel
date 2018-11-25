package service;

import java.util.List;

import entity.Table;

public interface TableService {
	
	public List<Table> findAllTable();
	public Table findTableById(int id);
	public void updateTable(Table t);
	public void add(Table t);
	public void delete(int id);
	public Table findTableByName(String tableName);
}
