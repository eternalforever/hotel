package vertical;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import entity.Vdishes;
import utils.JdbcUtil;


public class VDish implements IVDish  {

	@Override
	public List<Vdishes> findAll() {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "select d.id,d.dishName,m.menuname,d.price,d.vip from t_dishes d , t_menu m  where  d.typeId = m.id ";
		List<Vdishes> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Vdishes.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public Vdishes findName(String dishName) {
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql  = "select * from t_dishes where dishName=?";
		Vdishes dish = null;
		try {
			dish = qr.query(sql, new BeanHandler<>(Vdishes.class),dishName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dish;
	}


}
