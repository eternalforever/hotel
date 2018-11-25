package service;

import java.util.List;

import entity.Dishes;
import model.DishesMd;

public class DishServ implements IDishServ{
	private DishesMd dh = new DishesMd();
	@Override
	public List<Dishes> findAllDish() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dishes findDishById(int id) {
		Dishes dish = null;
		dish = dh.findSomeone(id);
		return dish;
	}

	@Override
	public void updateDish(Dishes dish) {
		dh.update(dish);
		
	}

	@Override
	public void addDish(Dishes dish) {
		dh.add(dish);
	}

	@Override
	public void deleteDish(int id) {
		dh.delete(id);
		
	}

	@Override
	public Dishes findDishByName(String dishName) {
		// TODO Auto-generated method stub
		return null;
	}

}
