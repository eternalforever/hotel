package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Table;
import service.TableServ;

/**
 * Servlet implementation class TableServlet
 */
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TableServ ts = new TableServ();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		this.modify(request, response);
	}
	private void list(HttpServletRequest request, HttpServletResponse response) {
		List<Table> list = null;
		list = ts.findAllTable();
		request.setAttribute("list", list);
		
		try {
			request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
	private void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String tableName = request.getParameter("tableName");
		String status = request.getParameter("status");
		
		Table t = new Table();
		t.setTableName(tableName);
		t.setStatus(Integer.parseInt(status));
		t.setSchedule(new Date());
		
		try {
			ts.add(t);			
			response.sendRedirect(request.getContextPath()+"/TableServlet?key=findAll");
			} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		ts.delete(Integer.parseInt(request.getParameter("id")));
		try {
			response.sendRedirect(request.getContextPath()+"/TableServlet?key=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) {
		Table t = ts.findTableById(Integer.parseInt(request.getParameter("id")));
		int status = t.getStatus();
		t.setStatus(status == 0 ? 1:0);
		
		if (t.getStatus() == 0) {
			t.setSchedule(null);
		}else {
			t.setSchedule(new Date());
		}
		ts.updateTable(t);
		try {
			response.sendRedirect(request.getContextPath()+"/TableServlet?key=findAll");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void findOneById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Table tt = ts.findTableById(Integer.parseInt(id));
		request.setAttribute("Table", tt);
		
	}
	private void findTableByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tableName = request.getParameter("tableName");
		
		Table tt = ts.findTableByName(tableName);
		List<Table> list = new ArrayList<Table>();
		list.add(tt);
		
		request.setAttribute("list", list);
	
		try {
			request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};;
		
	}
	
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		
		switch (key) {
		case "add":
			this.add(request, response);
			break;
			
		case "delete":
			this.delete(request, response);			
			break;
			
		case "update":
			this.update(request, response);
			break;
			
		case "findSomeone":
			this.findOneById(request, response);
			break;
			
		case "findAll":
			this.list(request, response);			
			break;
		case "findTableByName":
			this.findTableByName(request, response);			
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
