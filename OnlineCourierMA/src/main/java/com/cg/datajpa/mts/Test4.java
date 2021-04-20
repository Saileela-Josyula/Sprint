package com.cg.datajpa.mts;

import java.time.LocalDate;
import java.util.List;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.repository.CourierDAOImp;

public class Test4 {
	
	public static void main(String[] args) {
		CourierDAOImp courierdao=new CourierDAOImp();
		Courier courier=new Courier(2,CourierStatus.delivered,null,null,26548,LocalDate.parse("2021-03-27"),LocalDate.now());
		Courier courier2=new Courier(3,CourierStatus.delivered,null,null,26578,LocalDate.parse("2021-03-24"),LocalDate.parse("2021-04-15"));
		//courierdao.addCourierInfo(courier2);
		//System.out.println(courierdao.getCourierInfo(1));
		//courierdao.removeCourierInfo(2);
		List<Courier> couriers=courierdao.getAllDeliveredCouriers();
		for(Courier c:couriers) {
			System.out.println(c);
		}
		
		List<Courier> couriers2=courierdao.getAllDeliveredCouriersByDate(LocalDate.now());
		for(Courier c:couriers2) {
			System.out.println(c);
		}
	}
	
}
