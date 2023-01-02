package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.BookingDatesPojo;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BookingSteps {

    @Step("Creating new booking with firstName: {0}, lastName: {1}, totalPrice: {2}, depositPaid: {3}, checkIn: {4}, " +
            "checkOut: {5}, additionalNeeds: {6}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid,
                                             String checkIn, String checkOut, String additionalNeeds) {
        BookingDatesPojo bookingDatesPojo = BookingDatesPojo.getBookingDatesPojo(checkIn, checkOut);
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, bookingDatesPojo,
                additionalNeeds);
        return SerenityRest.rest().given().log().all()
                .header("Content-type", "application/json")
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREATE_BOOKING)
                .then();
    }

    @Step("Get booking with BookingId: {0}")
    public ValidatableResponse getBookingWithBookingId(int bookingId) {
        return SerenityRest.given().log().all()
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_BOOKING)
                .then();
    }

    @Step("Update booking with bookingId: {0}, firstName: {1}, lastName: {2}, totalPrice: {3}, depositPaid: {4}, " +
            "checkIn: {5}, checkOut: {6} and additionalNeeds: {7}")
    public ValidatableResponse updateBooking(int bookingId, String firstName, String lastName, int totalPrice,
                                             boolean depositPaid, String checkIn, String checkOut,
                                             String additionalNeeds, String token) {
        BookingDatesPojo bookingDatesPojo = BookingDatesPojo.getBookingDatesPojo(checkIn, checkOut);
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, bookingDatesPojo,
                additionalNeeds);
        return SerenityRest.rest().given().log().all()
                .header("Content-type", "application/json")
                .pathParam("bookingId", bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING)
                .then();
    }
    @Step("Delete bookings with BookingId: {0}")
    public ValidatableResponse deleteBookingWithBookingId(int bookingId, String token) {
        return SerenityRest.given().log().all()
                .header("Content-type", "application/json")
                .pathParam("bookingId", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING)
                .then();
    }
}