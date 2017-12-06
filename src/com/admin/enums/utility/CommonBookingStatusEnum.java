package com.admin.enums.utility;

public enum CommonBookingStatusEnum {
	DEFAULT (0, "Default"),
	WALLET_ERROR (1, "Insufficient Wallet Balance."),
	FLIHT_BOOKING_SUCCESS(2, "FlightBooking Initiated"),
	HOTEL_BOOKING_SUCCESS(3, "HotelBooking Initiated"),
	CAR_BOOKING_SUCCESS(4, "CarBooking Initiated"),
	BUS_BOOKING_SUCCESS(5, "BusBooking Initiated"),
	TRAIN_BOOKING_SUCCESS(6, "TrainBooking Initiated"),
	VISA_BOOKING_SUCCESS(7, "VisaBooking Initiated"),
	INSURANCE_BOOKING_SUCCESS(8, "InsuranceBooking Initiated"),
	FLIHT_REMARKS(9, "Flight Booking payment"),
	HOTEL_REMARKS(10, "Hotel Booking payment"),
	CAR_REMARKS(11, "Car Booking payment"),
	BUS_REMARKS(12, "Bus Booking payment"),
	TRAIN_REMARKS(13, "Train Booking payment"),
	VISA_REMARKS(14, "Visa Booking payment"),
	INSURANCE_REMARKS(15, "Insurance Booking payment"),
	TRANSACTION_TYPE_DEBIT(16, "Debit"),
	TRANSACTION_TYPE_CREDIT(17, "Credit"),
	BOOKING_MODE_OFFLINE(18, "Offline"),
	BOOKING_MODE_ONLINE(19, "Online"),
	FLIGHT_FAILEDREMARKS(20, "Flight Booking Failed"),
	HOTEL_FAILEDREMARKS(21, "Hotel Booking Failed"),
	CAR_FAILEDREMARKS(22, "Car Booking Failed"),
	TRAIN_FAILEDREMARKS(23, "Train Booking Failed"),
	INSURANCE_FAILEDREMARKS(24, "Insurance Booking Failed"),
	VISA_FAILEDREMARKS(25, "Visa Booking Failed"),
	BUS_FAILEDREMARKS(26, "Bus Booking Failed"),
	MISCELLANEOUS_REMARKS(27, "Miscellaneous Booking payment"),
	MISCELLANEOUS_FAILEDREMARKS(28, "Miscellaneous Booking failed"),
	FLIGHT(29, "Flight"),
	HOTEL(30, "HOTEL"),
	BUS(31, "BUS"),
	CAR(32, "CAR"),
	TRAIN(33, "TRAIN"),
	INSURANCE(34, "INSURANCE"),
	VISA(35, "VISA"),
	MISCELLANEOUS(36, "MISCELLANEOUS");
	

	
	
	
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private final Integer code;
	private final String message;  
	CommonBookingStatusEnum(Integer code, String message) {
		this.code = code;
		this.message = message;		
	}

	public static CommonBookingStatusEnum getStatusEnum(Integer statusCode) {

		CommonBookingStatusEnum statusEnum = DEFAULT;
		if(statusCode == null)
			return DEFAULT;
		for (CommonBookingStatusEnum s : CommonBookingStatusEnum.values())
		{
			if(s.getCode() == statusCode)
			{
				statusEnum = s;
			}		
		}
		return statusEnum; 

	}
 
}
