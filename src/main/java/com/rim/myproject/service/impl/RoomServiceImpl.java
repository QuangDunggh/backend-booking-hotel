package com.rim.myproject.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.exception.NotFoundResourceException;
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

	@Override
	public List<String> getAllRoomTypes() {
		// TODO Auto-generated method stub
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public List<RoomResponse> getAllRoom() {
		List<Room> rooms = roomRepository.findAll();
		List<RoomResponse> roomResponses = new ArrayList<>();

		rooms.forEach(room -> {
			RoomResponse roomResponse = new RoomResponse();
			roomResponse.setId(room.getId());
			roomResponse.setBooked(room.isBooked());
			roomResponse.setRoomPrice(room.getRoomPrice());
			roomResponse.setRoomType(room.getRoomType());
			
//			byte[] photo = room.getPhoto().getBytes(1, (int)room.getPhoto().length());
			
//			roomResponse.setPhoto(Base64.encodeBase64String(photo));
			roomResponses.add(roomResponse);
		});
		
		return roomResponses;
	}

	@Override
	public byte[] getPhotoById(Long roomId) {
		Room room = roomRepository.findById(roomId).orElseThrow(() ->  new NotFoundResourceException("Can not find by id "+ roomId));
		return null;
	}

	
	
	
	
	
	
	
	
	
	
}
