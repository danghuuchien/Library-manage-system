package DAO;

import java.util.ArrayList;

public interface DAO_Interface<T> {
	 public int Them(T t);
	 public int Sua(T t);
	 public int Xoa(T t);
	    
	 public ArrayList<T> ShowAll();
}
