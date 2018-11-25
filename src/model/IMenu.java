package model;

import java.util.List;

import entity.Menu;

public interface IMenu {
	public void add(Menu m);
	public List<Menu> findAll();
	public void update(Menu m);
	public void delete(int id);
	public Menu findName(String menuName);
	public Menu findSomeone(int id);

}
