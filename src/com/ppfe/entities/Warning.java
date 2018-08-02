package com.ppfe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Warning")
public class Warning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "priority")
	private String priority;
	@Column(name = "count_difference")
	private int countDifference;
	@Column(name = "id_purchase")
	private Long idPurchase;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getCountDifference() {
		return countDifference;
	}

	public void setCountDifference(int countDifference) {
		this.countDifference = countDifference;
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	@Override
	public String toString() {
		return "WARNING WITH PRIORITY :"+this.priority;
	}
	
	

}
