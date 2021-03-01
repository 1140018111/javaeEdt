package com.generator.entity;

/**
 * com.generator.entity
 * dxl
 *
 * @author DL
 * @create 2020-06-19
 */
public class Cat {
	public String mao;
	public String tou;
	public String yiBai;
	private Dog dog;


	public String getMao() {
		return mao;
	}

	public void setMao(String mao) {
		this.mao = mao;
	}

	public String getTou() {
		return tou;
	}

	public void setTou(String tou) {
		this.tou = tou;
	}

	public String getYiBai() {
		return yiBai;
	}

	public void setYiBai(String yiBai) {
		this.yiBai = yiBai;
	}

	@Override
	public String toString() {
		return "Cat{" +
					   "mao='" + mao + '\'' +
					   ", tou='" + tou + '\'' +
					   ", yiBai='" + yiBai + '\'' +
					   '}';
	}

	public Dog getDog() {
		return dog;
	}

	public void setDog(Dog dog) {
		this.dog = dog;
	}
}
