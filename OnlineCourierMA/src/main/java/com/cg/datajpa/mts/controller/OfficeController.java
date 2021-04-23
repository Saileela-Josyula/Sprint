package com.cg.datajpa.mts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.datajpa.mts.entities.CourierOfficeOutlet;
import com.cg.datajpa.mts.exception.CourierNotFoundException;
import com.cg.datajpa.mts.exception.OutletClosedException;
import com.cg.datajpa.mts.exception.OutletNotFoundException;
import com.cg.datajpa.mts.service.OfficeOutletServiceImpl;

@RestController
@RequestMapping("/office")
public class OfficeController
{
	@Autowired
	OfficeOutletServiceImpl officeservice;
	
	public void setOfficeservice(OfficeOutletServiceImpl officeservice) {
		this.officeservice = officeservice;
	}
	@Transactional
	@PostMapping(value="/addoffice",consumes="application/json")
	public ResponseEntity<HttpStatus> add(@RequestBody CourierOfficeOutlet co)
	{
		officeservice.addNewOffice(co);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	@Transactional
	@DeleteMapping(value="/delete")
	public ResponseEntity<HttpStatus> deleteOffice(@RequestBody int officeid)
	{	CourierOfficeOutlet co=null;
		try {
			co=officeservice.getOfficeInfo(officeid);
		}
		catch(OutletNotFoundException ex) {
			
		}
		if(co!=null) {
			officeservice.removeNewOffice(co);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/{officeid}",produces="application/json")
	public ResponseEntity<Optional<CourierOfficeOutlet>> getOffice(@PathVariable("officeid")int officeid) 
	{	Optional<CourierOfficeOutlet> e=null;
		try {
			 e =Optional.ofNullable(officeservice.getOfficeInfo(officeid));
		}
		catch(OutletNotFoundException ex) {
			
		}
		
		if(e!=null)
			return new ResponseEntity <Optional<CourierOfficeOutlet>>(e,HttpStatus.OK);
		else
			return new ResponseEntity<Optional<CourierOfficeOutlet>>(HttpStatus.NOT_FOUND);	
	}
	
	@GetMapping(value="/all",produces="application/json")
	public ResponseEntity<List<CourierOfficeOutlet>>  getAllOfficesData()
	{
		List<CourierOfficeOutlet> li=officeservice.getAllOfficesData();
		return new ResponseEntity<List<CourierOfficeOutlet>>(li,HttpStatus.OK);
	}
	
	@GetMapping(value="/isofficeopen",consumes="application/json")
	public  ResponseEntity<String> isOfficeOpen(@RequestBody int courierid) 
	{
	
		boolean x=false;
		try {
			CourierOfficeOutlet co=officeservice.getOfficeInfo(courierid);
			x = officeservice.isOfficeOpen(co);
		}
		catch (Exception e) 
		{
		}
		if(x) {
			return  new ResponseEntity<String>("Office is Open", HttpStatus.OK);
		}
		else
			return  new ResponseEntity<String>("Office is closed", HttpStatus.OK);
	}
	@GetMapping(value="/isofficeclosed",consumes="application/json")
	public ResponseEntity<String> isOfficeClosed(@RequestBody int officeid)
	{
		boolean x=false;
		try {
			CourierOfficeOutlet co=officeservice.getOfficeInfo(officeid);
			x = officeservice.isOfficeClosed(co);
			
		}
		catch (Exception e) 
		{
		}
		if(x) {
			return  new ResponseEntity<String>("Office is Closed", HttpStatus.OK);
			
		}
		else
			
		return  new ResponseEntity<String>("Office is Open", HttpStatus.OK);
	}
	
}