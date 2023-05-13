package com.example.retrofitapp;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Results implements Serializable {

	@SerializedName("uid")
	private String uid;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("image_ids")
	private List<String> imageIds;

	@SerializedName("image_urls")
	private List<String> imageUrls;

	@SerializedName("image_urls_thumbnails")
	private List<String> imageUrlsThumbnails;

	public void setUid(String uid){
		this.uid = uid;
	}

	public String getUid(){
		return uid;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setImageIds(List<String> imageIds){
		this.imageIds = imageIds;
	}

	public List<String> getImageIds(){
		return imageIds;
	}

	public void setImageUrls(List<String> imageUrls){
		this.imageUrls = imageUrls;
	}

	public List<String> getImageUrls(){
		return imageUrls;
	}

	public void setImageUrlsThumbnails(List<String> imageUrlsThumbnails){
		this.imageUrlsThumbnails = imageUrlsThumbnails;
	}

	public List<String> getImageUrlsThumbnails(){
		return imageUrlsThumbnails;
	}
}