package entity;

import java.util.Date;
import java.util.List;

import model.DishesMd;
import model.TableMd;
import service.TableServ;
import vertical.VDish;

public class Test {
	public static void main(String[] args) {
		DishesMd md = new DishesMd();
		Dishes dish = md.findSomeone(1);
		dish.setComment("hahaha");
		md.update(dish);
	}
}
