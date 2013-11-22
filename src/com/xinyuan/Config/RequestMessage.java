package com.xinyuan.Config;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.modules.Introspector.IntrospectHelper;

public class RequestMessage {

	private List MODELS;
	private List OBJECTS;
	
	private List<List<String>> FIELDS;
	private List<Map<String, String>> JOINS;
	private List<List<String>> SORTS;
	private List<List<String>> LIMITS;
	
	private List<Map<String, Map>> CRITERIAS;
	private List<Map<String, String>> IDENTITYS;
	private List PARAMETERS;
	
	private List<String> PASSWORDS;
	private List APNS_CONTENTS;
	private List APNS_FORWARDS;
	
	
	public List getMODELS() {
		return MODELS;
	}
	public void setMODELS(List mODELS) {
		MODELS = mODELS;
	}
	public List getOBJECTS() {
		return OBJECTS;
	}
	public void setOBJECTS(List oBJECTS) {
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
	public List<Map<String, Map>> getCRITERIAS() {
		return CRITERIAS;
	}
	public void setCRITERIAS(List<Map<String, Map>> cRITERIAS) {
		CRITERIAS = cRITERIAS;
	}
	public List<Map<String, String>> getIDENTITYS() {
		return IDENTITYS;
	}
	public void setIDENTITYS(List<Map<String, String>> iDENTITYS) {
		IDENTITYS = iDENTITYS;
	}
	public List getPARAMETERS() {
		return PARAMETERS;
	}
	public void setPARAMETERS(List pARAMETERS) {
		PARAMETERS = pARAMETERS;
	}
	public List<String> getPASSWORDS() {
		return PASSWORDS;
	}
	public void setPASSWORDS(List<String> pASSWORDS) {
		PASSWORDS = pASSWORDS;
	}
	public List getAPNS_CONTENTS() {
		return APNS_CONTENTS;
	}
	public void setAPNS_CONTENTS(List aPNS_CONTENTS) {
		APNS_CONTENTS = aPNS_CONTENTS;
	}
	public List getAPNS_FORWARDS() {
		return APNS_FORWARDS;
	}
	public void setAPNS_FORWARDS(List aPNS_FORWARDS) {
		APNS_FORWARDS = aPNS_FORWARDS;
	}
	
	
	
	@Override
	public String toString() {
		return IntrospectHelper.objectToString(this);
	}
	
}
