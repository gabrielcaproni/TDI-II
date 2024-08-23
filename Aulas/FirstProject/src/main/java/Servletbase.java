import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/servlet"})
public class Servletbase extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
	
	 resp.setContentType("text/html");
	
	 PrintWriter out = resp.getWriter();
	
	 out.append("<html>");
	 out.append("<header>");
	 out.append("<meta charset=\"UTF-8\">");
	 out.append("<title> Página dinâmica </title>");
	 out.append("</header>");
	 out.append("<body>");
	 out.append("<p>");
	 out.append("Hoje é ");
	
	 DateTimeFormatter formatter = DateTimeFormatter.
	 ofPattern("EEEE, dd MMMM yyyy HH:mm:ss a");
	 String dateTime = LocalDateTime.now().format(formatter);
	
	 out.append("<b>" + dateTime + "</b>");
	 out.append("</p>");
	 out.append("</body>");
	 out.append("</html>");
	
	}
}
	
	

