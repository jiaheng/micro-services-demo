package com.example.companyservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("companies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {

    @Id
    @JsonIgnore
    private String id;

    private String name;

    @JsonProperty("crunchbase_url")
    @Field("crunchbase_url")
    private String crunchbaseUrl;

    @JsonProperty("homepage_url")
    @Field("homepage_url")
    private String homepageUrl;

    @JsonProperty("twitter_username")
    @Field("twitter_username")
    private String twitterUsername;

    @JsonProperty("category_code")
    @Field("category_code")
    private String categoryCode;

    @JsonProperty("number_of_employees")
    @Field("number_of_employees")
    private Long numberOfEmployees;

    @JsonProperty("founded_year")
    @Field("founded_year")
    private Long foundedYear;

    @JsonProperty("email_address")
    @Field("email_address")
    private String email;

    @Field("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCrunchbaseUrl() {
        return crunchbaseUrl;
    }

    public void setCrunchbaseUrl(final String crunchbaseUrl) {
        this.crunchbaseUrl = crunchbaseUrl;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public void setHomepageUrl(final String homepageUrl) {
        this.homepageUrl = homepageUrl;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(final String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(final String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(final Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Long getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(final Long foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
