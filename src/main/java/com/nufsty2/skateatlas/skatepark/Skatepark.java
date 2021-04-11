package com.nufsty2.skateatlas.skatepark;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skatepark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private Boolean lights;

	private Boolean free;

	private Boolean inside;

	private String surface;

	private String address;

	private Integer postalCode;

	private String city;

	private String country;

	private String state;

	private String website;

	public Skatepark() {
	}

	public Skatepark(String name, Boolean lights, Boolean free, Boolean inside, String surface, String address,
			Integer postalCode, String city, String country, String state, String website) {
		this.name = name;
		this.lights = lights;
		this.free = free;
		this.inside = inside;
		this.surface = surface;
		this.address = address;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
		this.state = state;
		this.website = website;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isLights() {
		return this.lights;
	}

	public Boolean getLights() {
		return this.lights;
	}

	public void setLights(Boolean lights) {
		this.lights = lights;
	}

	public Boolean isFree() {
		return this.free;
	}

	public Boolean getFree() {
		return this.free;
	}

	public void setFree(Boolean free) {
		this.free = free;
	}

	public Boolean isInside() {
		return this.inside;
	}

	public Boolean getInside() {
		return this.inside;
	}

	public void setInside(Boolean inside) {
		this.inside = inside;
	}

	public String getSurface() {
		return this.surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", lights='" + isLights() + "'"
				+ ", free='" + isFree() + "'" + ", inside='" + isInside() + "'" + ", surface='" + getSurface() + "'"
				+ ", address='" + getAddress() + "'" + ", postal_code='" + getPostalCode() + "'" + ", city='"
				+ getCity() + "'" + ", country='" + getCountry() + "'" + ", state='" + getState() + "'" + ", website='"
				+ getWebsite() + "'" + "}";
	}

}
