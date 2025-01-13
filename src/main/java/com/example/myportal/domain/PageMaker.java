package com.example.myportal.domain;

import lombok.Data;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData() {
		setEndPage((int)(Math.ceil(cri.getPage()/(double)displayPageNum) * displayPageNum));
		
		setStartPage((getEndPage() - displayPageNum) + 1);
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if(getEndPage() > tempEndPage) {
			setEndPage(tempEndPage);
		}
		
		setPrev(getStartPage() == 1 ? false : true);
		
		setNext(getEndPage() * cri.getPerPageNum() >= totalCount ? false : true);
	}

	@Override
	public String toString() {
		return "Criteria [page=" + cri.getPage() + ", " + "perPageNum=" + cri.getPerPageNum() + "]";
	}

	public String makeQuery(int page) {
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page",  page)
				.queryParam("perPageNum",  cri.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page) {
		
		UriComponents uriComponents =
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum",  cri.getPerPageNum())
				.queryParam("searchType",  ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword",  ((SearchCriteria)cri).getKeyword())
				.build();
		
		return uriComponents.toUriString();
		
	}

}
