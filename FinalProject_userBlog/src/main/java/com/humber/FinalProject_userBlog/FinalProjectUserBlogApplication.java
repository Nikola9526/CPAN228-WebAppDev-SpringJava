package com.humber.FinalProject_userBlog;

import com.humber.FinalProject_userBlog.models.Comment;
import com.humber.FinalProject_userBlog.models.Post;
import com.humber.FinalProject_userBlog.models.myUser;
import com.humber.FinalProject_userBlog.services.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectUserBlogApplication implements CommandLineRunner {

	private final PostService postService;
	public FinalProjectUserBlogApplication(PostService postService) {
		this.postService = postService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectUserBlogApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//postService.addPost(new Post(1, "Parking for Sale", "Selling a Spot for $100 Access Now", new myUser(10,"NIK", "STOJ","NIKOLA26", "STOJ@GMAIL.COM",new Post(1)), "we", 1));

	}
}
