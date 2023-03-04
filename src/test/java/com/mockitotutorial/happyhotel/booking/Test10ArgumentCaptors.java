package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test10ArgumentCaptors {

    private BookingService bookingService;

    private PaymentService paymentServiceMock;
    private RoomService roomServiceMock;
    private BookingDAO bookingDAOMock;
    private MailSender mailSenderMock;

    private ArgumentCaptor<Double> argumentCaptor;


    @BeforeEach
    void setUp(){
        this.paymentServiceMock =mock(PaymentService.class);
        this.roomServiceMock=mock(RoomService.class);
        this.bookingDAOMock=mock(BookingDAO.class);
        this.mailSenderMock=mock(MailSender.class);
        this.bookingService=new BookingService(paymentServiceMock,roomServiceMock,bookingDAOMock,mailSenderMock);

        this.argumentCaptor=ArgumentCaptor.forClass(Double.class);
    }


    @Test
    void should_PayCorrectPrices_When_MultipleCallsOK(){
        //given
        BookingRequest bookingRequest1 = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 05), 2, true);
        BookingRequest bookingRequest2 = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                LocalDate.of(2020, 01, 02), 2, true);
        //when
        bookingService.makeBooking(bookingRequest1);
        bookingService.makeBooking(bookingRequest2);
        //then
      verify(paymentServiceMock,times(2)).pay(any(),argumentCaptor.capture());
        System.out.println("argumentCaptor = " + argumentCaptor.getAllValues());
        assertEquals(Arrays.asList(400.0,100.0),argumentCaptor.getAllValues());
    }




}
