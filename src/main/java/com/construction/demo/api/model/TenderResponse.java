/**
 * 
 */
package com.construction.demo.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class TenderResponse {

	private List<TenderModel> tenderList;

	public TenderResponse(List<TenderModel> tenderList) {
		this.tenderList = tenderList;
	}

	public List<TenderModel> getTenderList() {
		return tenderList;
	}

}