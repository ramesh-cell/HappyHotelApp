package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class Test11Annotations {

    @InjectMocks
    private BookingService bookingService;
    @Mock
    private PaymentService paymentServiceMock;
    @Mock
    private RoomService roomServiceMock;
    @Spy
    private BookingDAO bookingDAOMock;
    @Mock
    private MailSender mailSenderMock;

    @Captor
    private ArgumentCaptor<Double> argumentCaptor;



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
