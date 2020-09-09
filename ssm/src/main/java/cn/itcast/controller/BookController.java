package cn.itcast.controller;

import cn.itcast.domain.Book;
import cn.itcast.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping("/findByType")
    public void findByType(Model model, HttpServletResponse response, String bookType) throws IOException {
       List<Book> books =  bookService.findByType(bookType);
        System.out.println(books);
        ObjectMapper mapper = new ObjectMapper();

        model.addAttribute("books",books);
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),books);

    }
    @RequestMapping("/findByName")
    public void findByName(String bookName,HttpServletResponse response) throws IOException {
        Book book = bookService.findByName(bookName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),book);
    }


    @RequestMapping("/findByAll")
    public void findByAll(HttpServletResponse response) throws IOException {
        List<Book> byAll = bookService.findByAll();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),byAll);
    }

    @RequestMapping("/saveBook")
    public void saveBook (HttpServletResponse response,Book book) throws IOException {
        bookService.saveBook(book);
        List<Book> byAll = bookService.findByAll();
        byAll.add(book);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),byAll);
    }


    @RequestMapping("/deleteBook")
    public void deleteBook (HttpServletResponse response,String id,String string) throws IOException {


            bookService.deleteBook(id);
            List<Book> byAll = bookService.findByAll();
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=utf-8");
            mapper.writeValue(response.getWriter(),byAll);


        }
    @RequestMapping("/transfer")
    public void transfer (HttpServletResponse response, HttpServletRequest request, String id) throws IOException {

        Book book = bookService.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),book);



    }


    @RequestMapping("/updateBook")
    public void updateBook (HttpServletResponse response,String id,
                            String bookName, Double bookPrice,String bookAuthor,String bookPublish,
                            String bookType,HttpServletRequest request) throws IOException {


        Book book = new Book();
        book.setId(Integer.parseInt(id));
        book.setBookAuthor(bookAuthor);
        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setBookType(bookType);
        book.setBookPublish(bookPublish);
        System.out.println(book);

        bookService.updateBook(book);
        Book b = bookService.findById(id);
        System.out.println(b);
        List<Book> byAll = bookService.findByAll();

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=utf-8");
        mapper.writeValue(response.getWriter(),byAll);


      }
}


