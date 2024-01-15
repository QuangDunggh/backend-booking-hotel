package com.rim.myproject.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.model.BookedRoom;
import com.rim.myproject.repository.IBookedRoomRepository;
import com.rim.myproject.response.RoomResponse;
import com.rim.myproject.service.IBookedRoomService;
import com.rim.myproject.service.IRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173/")
public class RoomController {
	
	private final IRoomService roomService;
	
	private final IBookedRoomService bookedRoomService;

	@PostMapping
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam MultipartFile photo, @RequestParam String roomType,
			@RequestParam BigDecimal roomPrice) throws SQLException, IOException {
		return ResponseEntity.ok(roomService.addNewRoom(photo, roomType, roomPrice));
	}
	
	@GetMapping("/room-types")
	public List<String> getRoomType() {
		return roomService.getAllRoomTypes();
	}
	
	@GetMapping
	public ResponseEntity<List<RoomResponse>> getAllRoom() {
		return ResponseEntity.ok(roomService.getAllRoom());
	}
	
	@GetMapping("/{roomId}")
	public ResponseEntity<RoomResponse> getRoomById(@PathVariable("roomId") Long roomId) {
		return ResponseEntity.ok(roomService.getRoomById(roomId));
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<String> deleteRoomById(@PathVariable("roomId") Long roomId) {
		return ResponseEntity.ok(roomService.deleteRoomById(roomId));
	}
	

}
