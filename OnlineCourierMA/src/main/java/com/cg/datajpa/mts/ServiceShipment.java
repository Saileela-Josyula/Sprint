package com.cg.datajpa.mts;

import java.time.LocalDate;

import com.cg.datajpa.mts.entities.Courier;
import com.cg.datajpa.mts.entities.CourierStatus;
import com.cg.datajpa.mts.repository.CourierDAOImp;
import com.cg.datajpa.mts.service.ShipmentServiceImp;

public class ServiceShipment {
	public static void main(String[] args) {
		ShipmentServiceImp shipmentservice=new ShipmentServiceImp();
		shipmentservice.setCourdao(new CourierDAOImp());
		
		Courier courier=new Courier(4,CourierStatus.iniated,null,null,234,LocalDate.now(),LocalDate.parse("2021-04-25"));
		//System.out.println(shipmentservice.checkShipmentStatus(courier));
		
		//shipmentservice.initiateShipmentTransaction(courier);
		//System.out.println(shipmentservice.checkShipmentStatus(courier));
		
		shipmentservice.closeShipmentTransaction(courier);
		
		//shipmentservice.rejectShipmentTransaction(courier);
		System.out.println(shipmentservice.checkShipmentStatus(courier));
	}
	
	
}
	