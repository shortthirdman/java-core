package com.shortthirdman.core.util;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Column;

public class Products implements Serializable {
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="PRODUCTID")
	private String productId;

	@Column(name="NAMEID")
	private String nameId;

	@Column(name="NAME")
	private String name;

	@Column(name="P_DESC")
	private String desc;

	@Column(name="DIMENSION")
	private String dimension;

	@Column(name="CATEGORY")
	private String category;

	@Column(name="SUB_CATEGORY")
	private String subcategory;

	@Column(name="CATEGORYID")
	private String categoryId;

	@Column(name="SUBCATEGORYID")
	private String subcategoryId;

	@Column(name="TAGS")
	private String tags;

	@Column(name="DESIGNER")
	private String designer;

	@Column(name="CURR")
	private String curr;

	@Column(name="POPULARITY")
	private String popularity;

	@Column(name="RELEVANCE")
	private String relevance;

	@Column(name="SHORTLISTED")
	private String shortlisted;

	@Column(name="LIKES")
	private String likes;

	@Column(name="CREATE_String")
	private String createDt;

	@Column(name="PAGEID")
	private String pageId;

	@Column(name="STYLENAME")
	private String styleName;

	@Column(name="STYLEID")
	private String styleId;

	@Column(name="PRICERANGE")
	private String priceRange;

	@Column(name="PRICEID")
	private String priceId;

	@Column(name="DEFAULT_PRICE")
	private String defaultPrice;

	@Column(name="DEFAULT_MATERIAL")
	private String defaultMaterial;

	@Column(name="DEFAULT_FINISH")
	private String defaultFinish;
	
	/* setters and getters */
}