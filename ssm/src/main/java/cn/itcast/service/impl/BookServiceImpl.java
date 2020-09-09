package cn.itcast.service.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.domain.Book;
import cn.itcast.domain.User;
import cn.itcast.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {


    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> findByType(String bookType) {

        return bookDao.findByType(bookType);
    }

    @Override
    public Book findByName(String bookName) {

        return bookDao.findByName(bookName);
    }

    @Override
    public Book findById(String id) {

        return bookDao.findById(id);
    }

    @Override
    public List<Book> findByAll() {

        return bookDao.findByAll();
    }

    @Override
    public void saveBook(Book book) {

        bookDao.saveBook(book);
    }

    @Override
    public void deleteBook(String id) {

        bookDao.deleteBook(id);
    }

    @Override
    public void updateBook(Book book) {

        bookDao.updateBook(book);
    }
}
