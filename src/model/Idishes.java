package model;

import java.util.List;

import entity.Dishes;


public interface Idishes {
	public List<Dishes> findAll();
	public Dishes findSomeone(int id);
	public void update(Dishes dish);
	public void add(Dishes dish);
	public void delete(int id);
	public Dishes findName(String dishName);
}
