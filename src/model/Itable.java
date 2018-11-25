package model;

import java.util.List;

import entity.Table;

public interface Itable {
	public List<Table> findAll();
	public Table findSomeone(int id);
	public void update(Table t);
	public void add(Table t);
	public void delete(int id);
	public Table findName(String tableName);
}
