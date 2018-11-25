package entity;

public class Dishes {
	private Integer id;
	private String dishName;
	private Integer typeID;
	private Double price;
	private Double vip;
	private String comment;
	private String imgPath;
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	public Integer getId() {
		return id;
	}

	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getVip() {
		return vip;
	}
	public void setVip(Double vip) {
		this.vip = vip;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	@Override
	public String toString() {
		return "Dishes [id=" + id + ", dishName=" + dishName + ", typeID=" + typeID + ", price=" + price + ", vip="
				+ vip + ", comment=" + comment + ", imgPath=" + imgPath + "]";
	}
	
	
}
