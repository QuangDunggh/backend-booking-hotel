package com.rim.myproject.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.model.Room;
import com.rim.myproject.repository.IRoomRepository;
import com.rim.myproject.response.RoomResponse;
import com.rim.myproject.service.IRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

	private final IRoomRepository roomRepository;

	@Override
	public RoomResponse addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice)
			throws SQLException, IOException {
		Room saveRoom = new Room();
		if (!photo.isEmpty()) {
			byte[] photoBytes = photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			saveRoom.setPhoto(photoBlob);
		}
		saveRoom.setRoomType(roomType);
		saveRoom.setRoomPrice(roomPrice);
		Room savedRoom = roomRepository.save(saveRoom);
		return new RoomResponse(savedRoom.getId(), roomType, roomPrice);
	}

}
