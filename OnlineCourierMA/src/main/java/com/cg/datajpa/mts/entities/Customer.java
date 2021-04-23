package com.cg.datajpa.mts.entities;

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

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_Sequence")
	@SequenceGenerator(name = "customer_Sequence", sequenceName = "customer_seq",allocationSize  =1,initialValue = 1)
	@Column(name="customerid")
	private int customerid;
	@Column(name="aadharno")
	private long aadharno;
	@Column(name="firstname")
	private String firstname;
	@Column(name="lastname")
	private String lastname;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addid")
	private Address addr;
	@Column(name="mobileno")
	private long mobileno;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="accountno")
	private BankAccount acct;
	public Customer() {
		super();
	}
	public Customer( long aadharno, String firstname, String lastname, Address addr, long mobileno,
			BankAccount acct) {
		super();
		this.aadharno = aadharno;
		this.firstname = firstname;
		this.lastname = lastname;
		this.addr = addr;
		this.mobileno = mobileno;
		this.acct = acct;
	}
	public int getCustomerid() {
		return customerid;
	}

	public long getAadharno() {
		return aadharno;
	}

	public void setAadharno(long aadharno) {
		this.aadharno = aadharno;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public long getMobileno() {
		return mobileno;
	}

	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}

	public BankAccount getAcct() {
		return acct;
	}

	public void setAcct(BankAccount acct) {
		this.acct = acct;
	}
	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", aadharno=" + aadharno + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", addr=" + addr + ", mobileno=" + mobileno + ", acct=" + acct + "]";
	}
	
}