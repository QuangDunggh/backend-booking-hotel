package com.rim.myproject.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.response.RoomResponse;

public interface IRoomService {
	
	RoomResponse addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;

}
