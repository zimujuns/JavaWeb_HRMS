package com.github.javaweb.web;

import com.github.javaweb.data.Data;
import com.github.javaweb.data.Ddept;
import com.github.javaweb.data.Demployee;
import com.github.javaweb.data.Djob;
import com.github.javaweb.util.DataUtil;
import com.github.javaweb.util.HrmsEditSql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HrmsEditSql hes = new HrmsEditSql();
        try {
            switch (req.getParameter("editType")){
                case "add":
                    Demployee demployee = new Demployee(0,req.getParameter("name"),req.getParameter("jobId"),req.getParameter("adminId"),req.getParameter("money"),req.getParameter("msg"));
                    hes.edit(demployee.getAddSql());
                    break;
                case "del":
                    hes.remove_id(req.getParameter("uid"));
                    break;
                case "edit":
                    int oldId = Integer.parseInt(req.getParameter("oldId"));
                    String sql = Demployee.getMap().get(oldId).getEditSql(req.getParameter("name"),req.getParameter("jobId"),req.getParameter("adminId"),req.getParameter("money"),req.getParameter("msg"));
                    hes.edit(sql);
                    break;
                default:
                    req.getSession().setAttribute("msg","设置失败");
                    resp.setHeader("refresh","3;URL=http://localhost:8080/index.jsp");
                    resp.sendRedirect("http://localhost:8080/msg.jsp");
                    return;
            }
            req.getSession().setAttribute("msg","设置成功,稍后将自动返回主页");
            resp.setHeader("refresh","3;URL=http://localhost:8080/index.jsp");
            DataUtil.init();
        } catch (SQLException e) {
            req.getSession().setAttribute("msg","设置失败!");
            resp.setHeader("refresh","3;URL=http://localhost:8080/index.jsp");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化失败!");
        }catch (NullPointerException | NumberFormatException e){
            req.getSession().setAttribute("msg","设置失败");
            e.printStackTrace();
            resp.setHeader("refresh","3;URL=http://localhost:8080/index.jsp");
        } finally {
            resp.sendRedirect("http://localhost:8080/msg.jsp");
        }
    }


}
