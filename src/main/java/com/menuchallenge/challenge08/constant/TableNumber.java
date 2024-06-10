package com.menuchallenge.challenge08.constant;


public enum TableNumber {

	TABLE1("table 1"),
	TABLE2("table 2"),
	TABLE3("table 3"),
	TABLE4("table 4"),
	;

	private final String tableOption;

	TableNumber(String tableOption) {
		this.tableOption = tableOption;
	}

	public String getTableOption() {
		return tableOption;
	}
}
