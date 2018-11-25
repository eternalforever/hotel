package service;

import java.util.List;

import entity.Vdishes;
import vertical.VDish;

public class VDishServ implements IVDishServ{
	private VDish dh = new VDish();
	@Override
	public List<Vdishes> findAllDish() {
		List<Vdishes> list = null;
		list = dh.findAll();
		return list;
		
	}

	@Override
	public Vdishes findDishById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDish(Vdishes dish) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addDish(Vdishes dish) {
		
		
	}

	@Override
	public void deleteDish(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vdishes findDishByName(String dishName) {
		Vdishes dish = null;
		try {
			dish = dh.findName(dishName);
		} catch (Exception e) {
			throw new RuntimeException("wushhuu");
		};
		return dish;
	}

}
