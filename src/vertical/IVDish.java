package vertical;

import java.util.List;


import entity.Vdishes;

public interface IVDish {
	public List<Vdishes> findAll();
//	public Vdishes findSomeone(int id);
//	public void update(Vdishes dish);
//	public void add(Vdishes dish);
//	public void delete(int id);
	public Vdishes findName(String dishName);
}
