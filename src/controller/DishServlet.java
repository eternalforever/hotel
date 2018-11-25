package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import entity.Dishes;
import entity.Menu;
import entity.Vdishes;
import model.MenuMd;
import service.DishServ;
import service.VDishServ;

/**
 * Servlet implementation class DishServlet
 */
public class DishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VDishServ dhServ = new VDishServ(); 
    private DishServ dh = new DishServ();
    MenuMd md = new MenuMd();
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<Menu> list = md.findAll();
		this.getServletContext().setAttribute("List",list);			
		
		String key = request.getParameter("key");
		switch (key) {
		case "findAll":
			this.findAll(request, response);
			break;
		case "addDish":
			Dishes dish = new Dishes();
			this.addDish(request, response,dish);
			try {
				
				dh.addDish(dish);
				request.setAttribute("dish", dish);
				response.sendRedirect(request.getContextPath()+ "/DishServlet?key=findAll");		
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			this.delete(request, response);
			break;
		case "update":
			
			Dishes dish1 = new Dishes();
			
			this.update(request, response,dish1);
			break;
		case "findOne":
			this.findOne(request, response);
			break;
		case "findByName":
			this.findByName(request, response);
			break;

		default:
			break;
		}
	}
	private void findOne(HttpServletRequest request, HttpServletResponse response) {
		Dishes dish = null;
		
		try {
			dish = dh.findDishById(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("dish", dish);
			request.getRequestDispatcher("/detail/updateFood.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		List<Vdishes> list = null;
		list = dhServ.findAllDish();
		
		request.setAttribute("list", list);			
		try {
			request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response,Dishes dish) {
		
		try {
			this.addDish(request, response,dish);
			dh.updateDish(dish);
			response.sendRedirect(request.getContextPath()+ "/DishServlet?key=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response ) {
		dh.deleteDish(Integer.parseInt(request.getParameter("id")));
		try {
			response.sendRedirect(request.getContextPath()+ "/DishServlet?key=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void findByName(HttpServletRequest request, HttpServletResponse response) {
		Vdishes dish = null;
		List<Vdishes> dishes = new ArrayList<Vdishes>();
		dish = dhServ.findDishByName(request.getParameter("dishName"));
		dishes.add(dish);
		request.setAttribute("list", dishes);
		try {
			request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	private void addDish(HttpServletRequest request, HttpServletResponse response,Dishes dish) {
		// 创建 DiskFileItemFactory 对象
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
	
		// 根据工厂对象创建ServletFileUpload 对象
		ServletFileUpload fileUpload = new ServletFileUpload(fileFactory);
		
		// 设置上传文件的最大大小，单位字节
		fileUpload.setFileSizeMax(1000 * 1024);
		// 文件名中如果有中文，可以设编码方式,tomcat8不用设置
		fileUpload.setHeaderEncoding("utf-8");
		
		// 判断表单的enctype 是否是multipart/form-data
		if(ServletFileUpload.isMultipartContent(request)){
			
			try {
				// 将请求中的数据转为列表形式，列表中存储FileItem对象
				List<FileItem> items = fileUpload.parseRequest(request);
				
				for (FileItem item : items) {
					// 判断是否是普通的表单数据
					if(item.isFormField()){
						// 获取提交的数据的name
						String name = item.getFieldName();
						
						// 获取对应的value
						//String value = item.getString();
						// 普通表单数据的中文乱码解决
						String value = item.getString("utf-8");
						System.out.println(name + ":" + value);

						if(name.equals("typeId")) {
							dish.setTypeID(Integer.valueOf(value));						
						}else if(name.equals("dishName")) {
							dish.setDishName(value);
						}else if(name.equals("price")) {
							dish.setPrice(Double.valueOf(value));
						}else if(name.equals("vip")) {
							dish.setVip(Double.valueOf(value));
						}else if(name.equals("comment")) {
							dish.setComment(value);
						}else if(name.equals("id")) {
							dish.setId(Integer.parseInt(value));
						}
						
					}else{
						// 获取上传的文件的文件名
						String fileName = item.getName();
						
						// 获取uuid
						String uuid = UUID.randomUUID().toString().replace("-", "");
						// 生成新的文件名
						//aa_9ea1beb9ef2245ca8d6c3640967194cd.txt
						String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + 
								"_" + uuid + fileName.substring(fileName.lastIndexOf("."));
						
						// 将上传的文件保存到指定的路径
						// 可以通过在tomcat下配置虚拟路径，借助url进行具体资源文件的访问（理解）
						String path = "D:/upload";
						File pathFile = new File(path);
						// 如果文件夹不存在，创建
						if(!pathFile.exists()){
							pathFile.mkdirs();
						}
						// 获取web应用下相关资源的真实路径
						//String path = this.getServletContext().getRealPath("/upload");
						File file = new File(path, newFileName);
						// 保存文件
						dish.setImgPath(file+"");
						item.write(file);
						// 删除临时文件
						item.delete();
					}
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
