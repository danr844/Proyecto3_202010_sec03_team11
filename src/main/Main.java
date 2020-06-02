package main;
import java.io.FileNotFoundException;
import java.text.ParseException;

import controller.Controller;

public class Main {
	
	public static void main(String[] args) throws ParseException, FileNotFoundException 
	{
		Controller controler = new Controller();
		controler.run();
	}
}
