package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.AuthPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class AuthSteps {
    @Step("Getting auth token with username: {0} and password: {1}")
    public String getAuthToken(String username, String password) {
        AuthPojo authPojo = AuthPojo.getAuthBody(username, password);
        return SerenityRest.given()
                .header("Content-type", "application/json")
                .body(authPojo)
                .when()
                .post(EndPoints.AUTH)
                .then()
                .statusCode(200).extract().path("Token");

    }
}
