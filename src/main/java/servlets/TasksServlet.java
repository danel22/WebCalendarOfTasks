package servlets;

import com.company.db.dao.TaskDAO;
import com.company.pojo.Task;
import com.company.xmlroutine.Tasks;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TasksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("windows-1251");
        resp.getWriter().println("TASK<br>");
        Tasks tasks = new Tasks();
        try {
            tasks.setTasks(TaskDAO.getAll());
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        }
        for (Task task : tasks.getTasks()) {
            resp.getWriter().println(task.toString().replaceAll("\n","<br>")+"<br>");
        }
    }
}