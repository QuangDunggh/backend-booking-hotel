package com.rim.myproject.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookedRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(name = "check_in")
	private LocalDate checkInDate;
	
	@Column(name= "check_out")
	private LocalDate checkOutDate;
	
	@Column(name= "guest_full_name")
	private String guestFullName;
	
	@Column(name="guest_email")
	private String guestEmail;
	
	@Column(name="adults")
	private int numOfAdults;
	
	@Column(name="children")
	private int numOfChildren;
	
	@Column(name = "total_guest")
	private int totalNumOfGuest;
	
	@Column(name= "confirmation_code")
	private String bookingComfirmationCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

}
