package com.rim.myproject.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {

	private Long bookingId;

	private LocalDate checkInDate;

	private LocalDate checkOutDate;

	private String guestFullName;

	private String guestEmail;

	private int numOfAdults;

	private int numOfChildren;

	private int totalNumOfGuest;

	private String bookingComfirmationCode;

	private RoomResponse room;

	public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate,
			String bookingComfirmationCode) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingComfirmationCode = bookingComfirmationCode;
	}
}