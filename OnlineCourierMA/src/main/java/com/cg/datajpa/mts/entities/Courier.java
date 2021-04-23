package com.cg.datajpa.mts.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="courier")
public class Courier {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "courier_Sequence")
	@SequenceGenerator(name = "courier_Sequence", sequenceName = "courier_seq",allocationSize  =1,initialValue = 1)
	@Column(name="courierid")
	private int courierid;
	@Column(name="status")
	private CourierStatus status;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="senderid")
	private Customer sender;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="receiverid")
	private Customer receiver;
	@Column(name="consignmentno")
	private int consignmentno;
	@Column(name="initiateddate")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
	private LocalDate initiatedDate;
	@Column(name="deliverydate")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
	private LocalDate deliveredDate;
	
	public Courier()
	{}

	public Courier(CourierStatus status, Customer sender, Customer receiver,
			LocalDate initiatedDate, LocalDate deliveredDate) {
		super();
		this.status = status;
		this.sender = sender;
		this.receiver = receiver;
		this.initiatedDate = initiatedDate;
		this.deliveredDate = deliveredDate;
	}

	public int getCourierid() {
		return courierid;
	}

	public CourierStatus getStatus() {
		return status;
	}

	public void setStatus(CourierStatus status) {
		this.status = status;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}

	public int getConsignmentno() {
		return consignmentno;
	}

	public LocalDate getInitiatedDate() {
		return initiatedDate;
	}

	public void setInitiatedDate(LocalDate initiatedDate) {
		this.initiatedDate = initiatedDate;
	}

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	@Override
	public String toString() {
		return "Courier [courierid=" + courierid + ", status=" + status + ", sender=" + sender + ", receiver="
				+ receiver + ", consignmentno=" + consignmentno + ", initiatedDate=" + initiatedDate
				+ ", deliveredDate=" + deliveredDate + "]";
	}
	
}