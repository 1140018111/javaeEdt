package com.generator.entity;

import com.generator.dao.Dog;

/**
 * com.generator.entity
 * dxl
 *
 * @author DL
 * @create 2020-06-19
 */
public class Anmals {
	public String userid;

	public String password;

	public String username;

	public  Cat cat;
	public Dog  dog;

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Anmals{" +
					   "userid='" + userid + '\'' +
					   ", password='" + password + '\'' +
					   ", username='" + username + '\'' +
					   ", cat=" + cat +
					   ", dog=" + dog +
					   '}';
	}
}
