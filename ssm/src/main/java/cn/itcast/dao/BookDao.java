package cn.itcast.dao;

import cn.itcast.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    @Select("select * from book where bookType = #{bookType}")
    public List<Book> findByType(String bookType);

    @Select("select * from book where bookName = #{bookName}")
    public Book findByName(String bookName);

    @Select("select * from book ")
    public List<Book> findByAll();

    @Insert("insert into book(bookName,bookPrice,bookType,bookPublish,bookAuthor) values (#{bookName},#{bookPrice},#{bookType},#{bookPublish},#{bookAuthor})")
    public void saveBook(Book book);

    @Delete("delete from book where id = #{id}")
    public void deleteBook(String id);

    @Update("update book set bookName = #{bookName},bookPrice = #{bookPrice},bookAuthor = #{bookAuthor}" +
            ",bookType = #{bookType},bookPublish = #{bookPublish} where id = #{id}")
    public void updateBook(Book book);

    @Select("select * from book where id = #{id}")
    public Book findById(String id);
}
