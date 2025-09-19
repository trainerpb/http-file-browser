import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SarjitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("SarjitServlet.doGet");
        PrintWriter out = resp.getWriter();
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        out.println("<h1>SarjitServlet Welcome you to the Deep Sea of technology</h1>");
        out.print("<h2>Now it's "+ LocalDateTime.now()+"</h2>");
        String name = req.getParameter("name");
        out.println("<h3> Hello welcome "+name+"</h3>");
        String path=req.getParameter("path");
        File file = new File(path);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IllegalArgumentException("Not a Directory!");
            }

            File[] s=file.listFiles();
            resp.getWriter().println("<ul>");
            for(File x:s){
                if(x.isDirectory()){
                    resp.getWriter().println("<li class='fa fa-folder' aria-hidden='true'><a href='suraj?path=" + URLEncoder.encode(x.getAbsolutePath()) + "'>" + x.getName() + "</a></li>");
                }else {
                    resp.getWriter().println("<li><a href='ds?path=" + URLEncoder.encode(x.getAbsolutePath()) + "'>" + x.getName() + "</a></li>");
                }
            }
            resp.getWriter().println("</ul>");
        } else {
            throw new IllegalArgumentException("Directory does not exist");
        }
    }
}
