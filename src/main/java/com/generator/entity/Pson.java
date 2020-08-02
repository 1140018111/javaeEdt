package com.generator.entity;

/**
 * com.generator.entity
 * dxl
 *
 * @author DL
 * @create 2020-06-18
 */
public class Pson {
	public String name;

	public String password;

	public String UserName;
	public String plan;

	public String sataTus;

	public Dog dog;

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public String getSataTus() {
		return sataTus;
	}

	public void setSataTus(String sataTus) {
		this.sataTus = sataTus;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@Override
	public String toString() {
		return "Pson{" +
					   "name='" + name + '\'' +
					   ", password='" + password + '\'' +
					   ", UserName='" + UserName + '\'' +
					   ", plan='" + plan + '\'' +
					   ", sataTus='" + sataTus + '\'' +
					   ", dog=" + dog +
					   '}';
	}
}
