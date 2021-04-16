package com.cg.datajpa.mts.entities;

public class BankAccount {

	private int accountno;
	private String accountHolderName;
	private String accountType;
	public BankAccount() {}
	public BankAccount(int accountno, String accountHolderName, String accountType) {
		
		this.accountno = accountno;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "BankAccount [accountno=" + accountno + ", accountHolderName=" + accountHolderName + ", accountType="
				+ accountType + "]";
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}	

}
