package al.assu.trust.GestionImageSinistre.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: Summary
 * 
 */
@Entity
public class Summary implements Serializable {

	private Integer Id;
	private int idProj;
	private Integer Proposed_Share;
	private Integer Max_Liability;
	private Integer Rating_factors;
	private Integer Nat_Cat;
	private int expenses;
	private Integer Cost_of_Capital;
	private int Reinsurance_Costs;
	private Integer Total_Cost;
	private Integer Total_Risk_Price;
	private Integer profit_Margin;
	private Integer Brokerage;
	private Integer Commercial_Price;
	private static final long serialVersionUID = 1L;

	public Summary() {
		super();
	}

	@Id
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}

	public Integer getProposed_Share() {
		return this.Proposed_Share;
	}

	public void setProposed_Share(Integer Proposed_Share) {
		this.Proposed_Share = Proposed_Share;
	}

	public Integer getMax_Liability() {
		return this.Max_Liability;
	}

	public void setMax_Liability(Integer Max_Liability) {
		this.Max_Liability = Max_Liability;
	}

	public Integer getRating_factors() {
		return this.Rating_factors;
	}

	public void setRating_factors(Integer Rating_factors) {
		this.Rating_factors = Rating_factors;
	}

	public Integer getNat_Cat() {
		return this.Nat_Cat;
	}

	public void setNat_Cat(Integer Nat_Cat) {
		this.Nat_Cat = Nat_Cat;
	}

	public int getExpenses() {
		return this.expenses;
	}

	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}

	public Integer getCost_of_Capital() {
		return this.Cost_of_Capital;
	}

	public void setCost_of_Capital(Integer Cost_of_Capital) {
		this.Cost_of_Capital = Cost_of_Capital;
	}

	public int getReinsurance_Costs() {
		return this.Reinsurance_Costs;
	}

	public void setReinsurance_Costs(int Reinsurance_Costs) {
		this.Reinsurance_Costs = Reinsurance_Costs;
	}

	public Integer getTotal_Cost() {
		return this.Total_Cost;
	}

	public void setTotal_Cost(Integer Total_Cost) {
		this.Total_Cost = Total_Cost;
	}

	public Integer getTotal_Risk_Price() {
		return this.Total_Risk_Price;
	}

	public void setTotal_Risk_Price(Integer Total_Risk_Price) {
		this.Total_Risk_Price = Total_Risk_Price;
	}

	public Integer getProfit_Margin() {
		return this.profit_Margin;
	}

	public void setProfit_Margin(Integer profit_Margin) {
		this.profit_Margin = profit_Margin;
	}

	public Integer getBrokerage() {
		return this.Brokerage;
	}

	public void setBrokerage(Integer Brokerage) {
		this.Brokerage = Brokerage;
	}

	public Integer getCommercial_Price() {
		return this.Commercial_Price;
	}

	public void setCommercial_Price(Integer Commercial_Price) {
		this.Commercial_Price = Commercial_Price;
	}

	public int getIdProj() {
		return idProj;
	}

	public void setIdProj(int idProj) {
		this.idProj = idProj;
	}

	

}
