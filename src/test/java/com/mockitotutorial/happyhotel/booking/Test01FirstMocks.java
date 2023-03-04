package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test01FirstMocks {

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
    void should_calculatecorrectPrice_when_correctInput(){
        //given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
        double expected=4*2*50.0;
        //when
        double actual = bookingService.calculatePrice(bookingRequest);
        //then
        assertEquals(expected,actual);
    }


}
