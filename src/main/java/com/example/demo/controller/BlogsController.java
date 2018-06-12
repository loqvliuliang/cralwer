package com.example.demo.controller;

import cn.iamcrawler.www.crawler_annotation.annotation.CrawlerTimed;
import com.example.demo.controller.dto.BlogDTO;
import com.example.demo.domain.Blogs;
import com.example.demo.service.BlogService;
import com.example.demo.utils.BlogResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 刘亮 on 2018/5/2.
 */
@RestController
@RequestMapping("/api/blogs")
public class BlogsController {


    private final BlogService blogService;

    public BlogsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public BlogResponse getByInput(){
        List<Blogs> blogs = new ArrayList<>();
        Blogs blog1 = Blogs.builder()
                .id(1L)
                .title("第一个博客")
                .tags(Arrays.asList("linux","oracle"))
                .summary("为了防止服务器出现意外导致数据全部丢失，我们可以在另外一台服务器上设置每日去自动备份远端的Mongodb数据库到本地，这样就可以防止数据丢失(虽然大多数情况下不太可能发生)，同时也可以用作数据库的还原。")
                .category("back-end")
                .createTime(new Date())
                .viewTimes(199)
                .build();
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        blogs.add(blog1);
        BlogDTO blogDTO =  new BlogDTO();
        blogDTO.setBlogs(blogs);
        blogDTO.setCount(blogs.size());
        BlogResponse response = BlogResponse.builder().data(blogDTO).status("success").build();
        return response;
    }


    /**
     * 条件查询博客，条件待加
     * @return
     */
    @GetMapping("/get/by/input")
    @CrawlerTimed
    public BlogResponse getBlogsByInput(){
        List<Blogs> blogs = blogService.getBlogsByInput();
        BlogDTO blogDTO =  new BlogDTO();
        blogDTO.setBlogs(blogs);
        blogDTO.setCount(blogs.size());
        BlogResponse response = BlogResponse.builder()
                .data(blogDTO).status("success")
                .build();
        return response;
    }

    /**
     * 更新单个博客
     * @param blogs
     * @return
     */
    @PutMapping("/update/by/id")
    public Boolean updateBlog(@RequestBody Blogs blogs){
        Boolean aBoolean = blogService.updateBlog(blogs);
        return aBoolean;
    }


    /**
     * 新增单个博客
     * @param blogs
     * @return
     */
    @PostMapping("/insert/blog")
    public Boolean insertBlog(@RequestBody Blogs blogs){
        Boolean aBoolean = blogService.insertBlog(blogs);
        return aBoolean;
    }

    /**
     * 查询单个博客
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Blogs getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

}
