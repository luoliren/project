package cn.itcast.service;

import cn.itcast.domain.Book;
import cn.itcast.domain.User;

import java.util.List;

public interface BookService {
    /**
     * 通过类型名称进行查询
     */
    public List<Book> findByType(String bookType);
    /**
     * 通过书名名称进行查询
     */
    public Book findByName(String bookName);

    /**
     * 通过书id进行查询
     */
    public Book findById(String id);
    /**
     * 全查询
     */
    public List<Book> findByAll();


    /**
     * 保存书籍
     * @param book
     */
    public void saveBook(Book book);

    /**
     * 删除书籍
     * @param id
     */
    public void deleteBook(String id);

    /**
     * 更新书籍
     * @param book
     */
    public void updateBook(Book book);
}
