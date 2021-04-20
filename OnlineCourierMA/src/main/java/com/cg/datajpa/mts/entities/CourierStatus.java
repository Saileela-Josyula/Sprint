package com.cg.datajpa.mts.entities;

public enum CourierStatus {

	iniated(0), intransit(1), delivered(2), rejected(3);
	int num;
	
	CourierStatus(){}
	CourierStatus(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
