package com.projeto.escola.enums;

public enum Serie {
	PA("1º ano A"), 
	PB("1º ano B"),
	PC("1º ano C"),
	SA("2º ano A"),
	SB("2º ano B"),
	SC("2º ano C"),
	TA("3º ano A"),
	TB("3º ano B"),
	TC("3º ano C");
	
    private final String displayValue;
    
    private Serie(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
