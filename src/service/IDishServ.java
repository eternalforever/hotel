package service;

import java.util.List;

import entity.Dishes;
public interface IDishServ {
	public List<Dishes> findAllDish();
	public Dishes findDishById(int id);
	public void updateDish(Dishes dish);
	public void addDish(Dishes dish);
	public void deleteDish(int id);
	public Dishes findDishByName(String dishName);
}
