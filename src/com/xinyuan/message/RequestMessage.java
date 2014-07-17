package com.xinyuan.message;

import j2se.modules.Introspector.IntrospectHelper;

import java.util.List;
import java.util.Map;


public class RequestMessage {

	// basic
	private List<String> MODELS;
	private List<Map<String, Object>> OBJECTS;
	
	// read
	private List<List<String>> FIELDS;
	private List<Map<String, String>> JOINS;
	private List<List<String>> SORTS;
	private List<List<String>> LIMITS;
	private List<Map<String, Map<String,String>>> CRITERIAS;	// ["and": Map<String,String>, "or":Map<String,String>]
	
	// modify , delete 
	private List<Map<String, String>> IDENTITYS;
	
	// preconditions
	private List<Map<String, String>> PRECONDITIONS;
	
	
	// apns
	private List<String> APNS_FORWARDS;
	private List<Map<String, Object>> APNS_CONTENTS;
	
	
	// additional
	private Map<String, String> PARAMETERS;
	
	
	
	@Override
	public String toString() {
		return IntrospectHelper.objectToString(this);
	}
	public List<String> getMODELS() {
		return MODELS;
	}
	public void setMODELS(List<String> mODELS) {
		MODELS = mODELS;
	}
	
	
	public List<Map<String, Object>> getOBJECTS() {
		return OBJECTS;
	}
	public void setOBJECTS(List<Map<String, Object>> oBJECTS) {
		OBJECTS = oBJECTS;
	}


	public List<List<String>> getFIELDS() {
		return FIELDS;
	}
	public void setFIELDS(List<List<String>> fIELDS) {
		FIELDS = fIELDS;
	}


	public List<Map<String, String>> getJOINS() {
		return JOINS;
	}
	public void setJOINS(List<Map<String, String>> jOINS) {
		JOINS = jOINS;
	}


	public List<List<String>> getSORTS() {
		return SORTS;
	}
	public void setSORTS(List<List<String>> sORTS) {
		SORTS = sORTS;
	}


	public List<List<String>> getLIMITS() {
		return LIMITS;
	}
	public void setLIMITS(List<List<String>> lIMITS) {
		LIMITS = lIMITS;
	}


	public List<Map<String, Map<String, String>>> getCRITERIAS() {
		return CRITERIAS;
	}
	public void setCRITERIAS(List<Map<String, Map<String, String>>> cRITERIAS) {
		CRITERIAS = cRITERIAS;
	}


	public List<Map<String, String>> getIDENTITYS() {
		return IDENTITYS;
	}
	public void setIDENTITYS(List<Map<String, String>> iDENTITYS) {
		IDENTITYS = iDENTITYS;
	}
	

	public List<Map<String, String>> getPRECONDITIONS() {
		return PRECONDITIONS;
	}
	public void setPRECONDITIONS(List<Map<String, String>> pRECONDITIONS) {
		PRECONDITIONS = pRECONDITIONS;
	}
	
	public Map<String, String> getPARAMETERS() {
		return PARAMETERS;
	}
	public void setPARAMETERS(Map<String, String> pARAMETERS) {
		PARAMETERS = pARAMETERS;
	}


	public List<String> getAPNS_FORWARDS() {
		return APNS_FORWARDS;
	}
	public void setAPNS_FORWARDS(List<String> aPNS_FORWARDS) {
		APNS_FORWARDS = aPNS_FORWARDS;
	}
	
	public List<Map<String, Object>> getAPNS_CONTENTS() {
		return APNS_CONTENTS;
	}
	public void setAPNS_CONTENTS(List<Map<String, Object>> aPNS_CONTENTS) {
		APNS_CONTENTS = aPNS_CONTENTS;
	}
	
}
