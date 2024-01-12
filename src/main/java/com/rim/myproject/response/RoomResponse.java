package com.rim.myproject.response;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.rim.myproject.exception.CanNotConvertImageException;
import com.rim.myproject.model.Room;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomResponse {

	private Long id;

	private String roomType;

	private BigDecimal roomPrice;

	private boolean isBooked = false;

	private String photo;

	private List<BookingResponse> bookings;

	public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
		this.id = id;
		this.roomPrice = roomPrice;
		this.roomType = roomType;
	}

	public RoomResponse(Long id, String roomType, BigDecimal roomPrice, boolean isBooked, byte[] photoBytes,
			List<BookingResponse> bookings) {
		this.id = id;
		this.roomPrice = roomPrice;
		this.roomType = roomType;
		this.isBooked = isBooked;
		this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;
		this.bookings = bookings;
	}

	public static RoomResponse from(Room room) {
		RoomResponse roomResponse = new RoomResponse();
		roomResponse.setBooked(room.isBooked());
		roomResponse.setId(room.getId());
		roomResponse.setRoomPrice(room.getRoomPrice());
		roomResponse.setRoomType(room.getRoomType());
		try {
			byte[] photoByte = room.getPhoto().getBytes(1,(int) room.getPhoto().length());
			roomResponse.setPhoto(Base64.encodeBase64String(photoByte));
		} catch (SQLException e) {
			throw new CanNotConvertImageException("can not convert exception");
		}
		
		
		return roomResponse;
	}


}
