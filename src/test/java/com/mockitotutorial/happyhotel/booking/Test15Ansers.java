package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class Test15Ansers {

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
    void should_Calculate_CorrectPrice(){
        try(MockedStatic<CurrencyConverter> converterMockedStatic=mockStatic(CurrencyConverter.class)){
            //given
            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01),
                    LocalDate.of(2020, 01, 05), 2, false);
            double expected=400.0*0.8;
           converterMockedStatic.when(()->CurrencyConverter.toEuro(anyDouble())).thenAnswer(invocationOnMock ->(double) invocationOnMock.getArgument(0)*0.8);
            //when
          double actual=bookingService.calculatePriceEuro(bookingRequest);
            //then
            assertEquals(expected,actual);
        }


    }




}
