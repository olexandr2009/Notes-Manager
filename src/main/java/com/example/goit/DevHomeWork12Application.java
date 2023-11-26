package com.example.goit;

import com.example.goit.note.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevHomeWork12Application {

	public static void main(String[] args) {
		SpringApplication.run(DevHomeWork12Application.class, args);
		System.out.println(
				Note.builder()
						.id(1L)
						.title("TITLE")
						.content("content")
				.build()
		);

	}
}
