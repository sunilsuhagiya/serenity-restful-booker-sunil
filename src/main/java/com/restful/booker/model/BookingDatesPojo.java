package com.restful.booker.model;

public class BookingDatesPojo {
    public String checkin;
    public String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public static BookingDatesPojo getBookingDatesPojo(String checkin,String checkout){
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo();
        bookingDatesPojo.setCheckin(checkin);
        bookingDatesPojo.setCheckout(checkout);
        return bookingDatesPojo;
    }
}
