package com.rim.myproject.request;

import com.rim.myproject.exception.CanNotConvertImageException;
import com.rim.myproject.model.Room;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

@Builder
@Data
public class RoomRequest {
    private Long roomId;
    private String roomType;
    private BigDecimal roomPrice;
    private MultipartFile photo;

    public static Room convertToRoom(RoomRequest roomRequest) {
        Room room = new Room();
        room.setId(roomRequest.roomId);
        room.setRoomType(roomRequest.roomType);
        room.setRoomPrice(roomRequest.roomPrice);
        try {
            byte[] photoByte = roomRequest.getPhoto().getBytes();
            Blob blobPhoto = new SerialBlob(photoByte);
            room.setPhoto(blobPhoto);
        } catch (Exception e ) {
            throw new CanNotConvertImageException("Can not convert this image");
        }
        return room;
    }
}
