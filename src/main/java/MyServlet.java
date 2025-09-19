import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MyServlet.doGet");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String p = req.getParameter("p");


        resp.getWriter().println("<h1>Date is " + LocalDateTime.now() + "</h1>");


        List<String> fileNames = new ArrayList<>();
        File file = new File(p);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("Not a Directory!");
            }

            String[] s=file.list();
            resp.getWriter().println("<ul>");
            for(String x:s){
                resp.getWriter().println("<li>"+x+"</li>");
            }
            resp.getWriter().println("</ul>");
        } else {
            throw new IllegalArgumentException("Directory does not exist");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
