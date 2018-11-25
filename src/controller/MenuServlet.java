package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Menu;
import model.MenuMd;

/**
 * Servlet implementation class MenuServlet
 */
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MenuMd md = new MenuMd();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
    }
    private void findAll(HttpServletRequest request, HttpServletResponse response) {
    	List<Menu> list = null;
    	list = md.findAll();
//    	request.setAttribute("List", list);
    	this.getServletContext().setAttribute("List", list);
    	try {
    		
			request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void findOne(HttpServletRequest request, HttpServletResponse response) {
    	Menu menu = null;
    	
    	try {
    		menu = md.findSomeone(Integer.parseInt(request.getParameter("id")));
    		request.setAttribute("menu", menu);
			request.getRequestDispatcher("/detail/updateCuisine.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void update(HttpServletRequest request, HttpServletResponse response) {
    	Menu menu = null;
    	
    	try {
    		menu = md.findSomeone(Integer.parseInt(request.getParameter("id")));
        	menu.setMenuName(request.getParameter("menuName"));
        	md.update(menu);
			response.sendRedirect(request.getContextPath() + "/MenuServlet?key=findAll");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    private void add(HttpServletRequest request, HttpServletResponse response) {
    	Menu m = new Menu();
    	
    	m.setMenuName(request.getParameter("menuName"));
    	md.add(m);
    	try {
			response.sendRedirect(request.getContextPath() + "/MenuServlet?key=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) {
    	
    	md.delete(Integer.parseInt(request.getParameter("id")));
    	try {
    		response.sendRedirect(request.getContextPath() + "/MenuServlet?key=findAll");
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("key");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		switch (key) {
		case "findAll":
			this.findAll(request, response);
			break;
		case "add":
			this.add(request, response);
			break;
		case "delete":
			this.delete(request, response);
			break;
		case "findOne":
			this.findOne(request, response);
			break;
		case "update":
			this.update(request, response);
			break;

		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
