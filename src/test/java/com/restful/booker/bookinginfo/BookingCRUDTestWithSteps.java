package com.restful.booker.bookinginfo;

import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class BookingCRUDTestWithSteps extends TestBase {

    static String firstName = "Guest" + TestUtils.getRandomValue();
    static String lastName = "Aguest" + TestUtils.getRandomValue();
    static int totalPrice = 100;
    static boolean depositPaid = true;
    static String checkIn = "2023-01-10";
    static String checkOut = "2024-01-10";
    static String additionalNeeds = "Icecream";
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;

    @Steps
    AuthSteps authSteps;
    BookingSteps bookingSteps;

    @Title("This will create new booking")
    @Test
    public void test001() {
        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid,
                                                                      checkIn, checkOut, additionalNeeds);
        response.log().all().statusCode(200);
    }
    @Title("Verify if the booking is added to the application")
    @Test
    public void test002(){
        ValidatableResponse response = bookingSteps.getBookingWithBookingId(bookingId);
        response.log().all().statusCode(200);
    }
    @Title("Update the booking information and verify the udated information")
    @Test
    public void test003() {
        firstName = firstName + "_updated";
        token = authSteps.getAuthToken(username,password);
        bookingSteps.updateBooking(bookingId,firstName,lastName, totalPrice, depositPaid,
                                    checkIn, checkOut, additionalNeeds,token);
    }

    @Title("Delete the booking")
    @Test
    public void test004(){
        bookingSteps.deleteBookingWithBookingId(bookingId,token).statusCode(201);
        bookingSteps.getBookingWithBookingId(bookingId).statusCode(404);
    }
}
