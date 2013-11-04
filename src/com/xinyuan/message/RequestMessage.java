package com.xinyuan.message;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

import com.modules.introspector.IntrospectHelper;

public class RequestMessage {

	private List MODELS;
	private List OBJECTS;
	
	private List FIELDS;
	private List JOINS;
	private List SORTS;
	private List LIMITS;
	
	private List CRITERIAS;
	private List IDENTITYS;
	private List PARAMETERS;
	
	private List PASSWORDS;
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
	public List getFIELDS() {
		return FIELDS;
	}
	public void setFIELDS(List fIELDS) {
		FIELDS = fIELDS;
	}
	public List getJOINS() {
		return JOINS;
	}
	public void setJOINS(List jOINS) {
		JOINS = jOINS;
	}
	public List getSORTS() {
		return SORTS;
	}
	public void setSORTS(List sORTS) {
		SORTS = sORTS;
	}
	public List getLIMITS() {
		return LIMITS;
	}
	public void setLIMITS(List lIMITS) {
		LIMITS = lIMITS;
	}
	public List getCRITERIAS() {
		return CRITERIAS;
	}
	public void setCRITERIAS(List cRITERIAS) {
		CRITERIAS = cRITERIAS;
	}
	public List getIDENTITYS() {
		return IDENTITYS;
	}
	public void setIDENTITYS(List iDENTITYS) {
		IDENTITYS = iDENTITYS;
	}
	public List getPARAMETERS() {
		return PARAMETERS;
	}
	public void setPARAMETERS(List pARAMETERS) {
		PARAMETERS = pARAMETERS;
	}
	public List getPASSWORDS() {
		return PASSWORDS;
	}
	public void setPASSWORDS(List pASSWORDS) {
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
