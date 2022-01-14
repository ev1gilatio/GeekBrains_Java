package servlet;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("product", getProduct());
        getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    private List<Product> getProduct() {
        Product[] arr = new Product[10];
        List<Product> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Product(i + 1, "Title_" + (i + 1), (i + 1) * 1000);
            list.add(arr[i]);
        }

        return list;
    }
}