package service;

import java.util.List;

import entity.Vdishes;

public interface IVDishServ {
	public List<Vdishes> findAllDish();
	public Vdishes findDishById(int id);
	public void updateDish(Vdishes dish);
	public void addDish(Vdishes dish);
	public void deleteDish(int id);
	public Vdishes findDishByName(String dishName);
}
