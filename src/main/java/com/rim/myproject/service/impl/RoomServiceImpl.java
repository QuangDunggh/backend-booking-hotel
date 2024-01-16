package com.rim.myproject.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import com.rim.myproject.request.RoomRequest;
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
            roomResponse.setPhoto(getPhotoById(room.getId()));
            roomResponses.add(roomResponse);
        });

        return roomResponses;
    }

    @Override
    public String getPhotoById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundResourceException("Can not find by id " + roomId));
        Blob photo = room.getPhoto();
        try {
            byte[] photoByte = photo.getBytes(1, (int) photo.length());
            return Base64.encodeBase64String(photoByte);
        } catch (SQLException e) {
            throw new NotFoundResourceException("Can not convert the photo");
        }

    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundResourceException("Can not find room by id: " + roomId));
        return RoomResponse.from(room);
    }

    @Override
    public String deleteRoomById(Long id) {
        roomRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("can not find room by id " + id));
        roomRepository.deleteById(id);
        return "Delete room successful";
    }

    @Override
    public RoomResponse updateRoom(Long roomId, RoomRequest roomRequest) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            Room updateRoom = RoomRequest.convertToRoom(roomRequest);
            return RoomResponse.from(roomRepository.save(updateRoom));
        }
        throw new NotFoundResourceException("Can not find room by id: " + roomId);
    }

}
