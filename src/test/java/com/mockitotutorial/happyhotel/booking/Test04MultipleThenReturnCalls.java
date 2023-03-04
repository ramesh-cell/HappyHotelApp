package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test04MultipleThenReturnCalls {

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
    void should_CountAvilablePlaces_When_CalledMultipleTimes(){
        //given
        when(this.roomServiceMock.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("Room 1",2))).thenReturn(Collections.emptyList());
        int expected=2;
        int expectedSecondCall=0;
        //when
        int actualFirst = bookingService.getAvailablePlaceCount();
        int actualSecondCall = bookingService.getAvailablePlaceCount();
        //then
        assertAll(()->assertEquals(expected,actualFirst),
                ()->assertEquals(expectedSecondCall,actualSecondCall)
                );


    }




}
