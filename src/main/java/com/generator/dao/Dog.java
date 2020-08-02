package com.generator.dao;

/**
 * com.generator.entity
 * dxl
 *
 * @author DL
 * @create 2020-06-19
 */
public class Dog {
	public String yibai;
	public String tou;
	public String paole;

	public String getYibai() {
		return yibai;
	}

	public void setYibai(String yibai) {
		this.yibai = yibai;
	}

	public String getTou() {
		return tou;
	}

	public void setTou(String tou) {
		this.tou = tou;
	}

	public String getPaole() {
		return paole;
	}

	public void setPaole(String paole) {
		this.paole = paole;
	}

	@Override
	public String toString() {
		return "Dog{" +
					   "yibai='" + yibai + '\'' +
					   ", tou='" + tou + '\'' +
					   ", paole='" + paole + '\'' +
					   '}';
	}
}
