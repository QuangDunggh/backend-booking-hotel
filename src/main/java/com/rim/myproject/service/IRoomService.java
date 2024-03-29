package com.rim.myproject.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.rim.myproject.request.RoomRequest;
import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.response.RoomResponse;

public interface IRoomService {
	
	RoomResponse addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;
	
	List<String> getAllRoomTypes();
	
	List<RoomResponse> getAllRoom();
	
	String getPhotoById(Long roomId);
	
	RoomResponse getRoomById(Long roomId);

	String deleteRoomById(Long id);

	RoomResponse updateRoom(Long roomId, RoomRequest roomRequest);

}
