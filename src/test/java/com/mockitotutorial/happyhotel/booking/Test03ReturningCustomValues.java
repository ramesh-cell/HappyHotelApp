package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class Test03ReturningCustomValues {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;


    @BeforeEach
    void setUp(){
        this.paymentServiceMock =mock(PaymentService.class);
        this.roomServiceMock=mock(RoomService.class);
        this.bookingDAOMock=mock(BookingDAO.class);
        this.mailSenderMock=mock(MailSender.class);
        this.bookingService=new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);

    }


    @Test
    void should_CountAvilablePlaces_When_OneRoomAvilable(){
        //given

        when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1",2)));
        int expected=2;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }

    @Test
    void should_CountAvilablePlaces_When_MultipleRoomsAvilable(){
        //given
        List<Room> rooms = Arrays.asList(new Room("Room-1", 2), new Room("Room-2", 5));
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(rooms);
        int expected=7;
        //when
        int actual = bookingService.getAvailablePlaceCount();
        //then
        assertEquals(expected,actual);
    }


}
