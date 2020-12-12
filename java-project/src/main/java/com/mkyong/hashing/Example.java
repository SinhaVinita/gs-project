package com.mkyong.hashing;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.*;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;


public class Example {
    public static void main(String args[]) throws java.io.IOException {
        Content c = Request.Post("https://idfs.gs.com/as/token.oauth2")
                .bodyForm(Form.form()
                        .add("grant_type",    "client_credentials")
                        .add("client_id",     "db21815ca4694a7ea4fb01088c67c76a")
                        .add("client_secret", "bc03aad2f387f24885e4f153f613bca01cdee70f313266723948e95f1417cb22")
                        .add("scope",         "read_content read_financial_data read_product_data read_user_profile")
                        .build()
                )
                .execute().returnContent();
        String accessToken = new ObjectMapper().readTree(c.asString()).get("access_token").asText();
        String PATH= "https://api.marquee.gs.com/v1/users/self";

        String serviceResponse = Request.Get(PATH)
                .addHeader("Authorization", "Bearer " + accessToken)
                .execute().returnContent().asString();
        System.out.println(serviceResponse);

        String res1= Request.Get(PATH+ "/v1/assets/data")
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", ":application/json")
                .execute().returnContent().asString();
        JSONObject json = new JSONObject(res1); // Convert text to object
        System.out.println(json.toString(4)); // Print it with specified indentation

    }
}