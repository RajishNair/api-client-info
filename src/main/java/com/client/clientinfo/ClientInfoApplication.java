package com.client.clientinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientInfoApplication {

	public static void main(String[] args) {
		StringBuffer word = null;
		Random rand = new Random();
		Integer randomInteger = rand.nextInt();
		
		switch(randomInteger.intValue()) {
		case 1: word = new StringBuffer('A');
		case 2: word = new StringBuffer('G');
		default: word = null;
		
		}
		word.append('i');
		System.out.println(word);
		SpringApplication.run(ClientInfoApplication.class, args);
	}

}
