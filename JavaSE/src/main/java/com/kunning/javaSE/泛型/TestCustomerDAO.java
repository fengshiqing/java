package com.kunning.JavaSE.泛型;

public class TestCustomerDAO {
	public static void main(String[] args) {
		CustomerDAO c = new CustomerDAO();
		c.add(new Customer());
		c.get(0);
	}
}
