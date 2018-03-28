package org.smu.model;

public class CreditCard {

	private int Id;
	private int CustomerID;
	private String Name;
	private String CardType;
	private String CardNumber;
	private String PIN;
	private String Expiry;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int CustomerID) {
		this.CustomerID = CustomerID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String CardType) {
		this.CardType = CardType;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String CardNumber) {
		this.CardNumber = CardNumber;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String PIN) {
		this.PIN = PIN;
	}
	public String getExpiry() {
		return Expiry;
	}
	public void setExpiry(String Expiry) {
		this.Expiry = Expiry;
	}
	
}
