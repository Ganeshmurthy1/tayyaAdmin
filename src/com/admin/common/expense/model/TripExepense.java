package com.admin.common.expense.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lintas.admin.common.model.Timestampable;
@Entity
@Table(name="trip_expense")
public class TripExepense extends Timestampable{

	private static final long serialVersionUID = 1L;
	
	@Transient
	private Long trip_id;
	
	@Transient
	private BigDecimal totalAmount;
	
	/*@OneToMany(targetEntity = HotelTravelRequestQuotation.class,   mappedBy = "hotelTravelRequest" , cascade = CascadeType.ALL)
	private List<HotelTravelRequestQuotation> hotelTravelRequestQuotation; // list of rooms
*/
	
	 
	@OneToMany(targetEntity = FlightExpense.class,   mappedBy = "tripExepense" ,  cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<FlightExpense> flightExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = HotelExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<HotelExpense> hotelExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = CarExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private  List<CarExpense> carExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = TrainExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<TrainExpense> trainExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = BusExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<BusExpense> busExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = MealExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<MealExpense> mealExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = LaborExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<LaborExpense> laborExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = MiscellaneousExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private  List<MiscellaneousExpense> miscellaneousExpenseList;

	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = VisaExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private  List<VisaExpense> visaExpenseList;
	
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = InsuranceExpense.class,   mappedBy = "tripExepense" , cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private  List<InsuranceExpense> insuranceExpenseList;
	
	public Long getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(Long trip_id) {
		this.trip_id = trip_id;
	}

	public List<HotelExpense> getHotelExpenseList() {
		return hotelExpenseList;
	}

	public void setHotelExpenseList(List<HotelExpense> hotelExpenseList) {
		this.hotelExpenseList = hotelExpenseList;
	}

	public List<CarExpense> getCarExpenseList() {
		return carExpenseList;
	}

	public void setCarExpenseList(List<CarExpense> carExpenseList) {
		this.carExpenseList = carExpenseList;
	}

	public List<TrainExpense> getTrainExpenseList() {
		return trainExpenseList;
	}

	public void setTrainExpenseList(List<TrainExpense> trainExpenseList) {
		this.trainExpenseList = trainExpenseList;
	}

	public List<BusExpense> getBusExpenseList() {
		return busExpenseList;
	}

	public void setBusExpenseList(List<BusExpense> busExpenseList) {
		this.busExpenseList = busExpenseList;
	}

	public List<MealExpense> getMealExpenseList() {
		return mealExpenseList;
	}

	public void setMealExpenseList(List<MealExpense> mealExpenseList) {
		this.mealExpenseList = mealExpenseList;
	}

	public List<LaborExpense> getLaborExpenseList() {
		return laborExpenseList;
	}

	public void setLaborExpenseList(List<LaborExpense> laborExpenseList) {
		this.laborExpenseList = laborExpenseList;
	}

	public List<MiscellaneousExpense> getMiscellaneousExpenseList() {
		return miscellaneousExpenseList;
	}

	public void setMiscellaneousExpenseList(List<MiscellaneousExpense> miscellaneousExpenseList) {
		this.miscellaneousExpenseList = miscellaneousExpenseList;
	}

	public List<FlightExpense> getFlightExpenseList() {
		return flightExpenseList;
	}

	public void setFlightExpenseList(List<FlightExpense> flightExpenseList) {
		this.flightExpenseList = flightExpenseList;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<VisaExpense> getVisaExpenseList() {
		return visaExpenseList;
	}

	public void setVisaExpenseList(List<VisaExpense> visaExpenseList) {
		this.visaExpenseList = visaExpenseList;
	}

	public List<InsuranceExpense> getInsuranceExpenseList() {
		return insuranceExpenseList;
	}

	public void setInsuranceExpenseList(List<InsuranceExpense> insuranceExpenseList) {
		this.insuranceExpenseList = insuranceExpenseList;
	}

	
	

}
