package org.smu.model;

public class CreditCard {

	private int Id;
	private int CustomerId;
	private String name;
	private String CardType;
	private String CardNumber;
	private String SecurityPin;
	private String ExpiryDate;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getCustomerId() {
		return CustomerId;
	}
	public void setCustomerID(int CustomerId) {
		this.CustomerId = CustomerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String NameOnCard) {
		this.name = NameOnCard;
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
	public String getSecurityPin() {
		return SecurityPin;
	}
	public void setSecurityPin(String SecurityPin) {
		this.SecurityPin = SecurityPin;
	}
	public String getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiry(String ExpiryDate) {
		this.ExpiryDate = ExpiryDate;
	}
	
}
