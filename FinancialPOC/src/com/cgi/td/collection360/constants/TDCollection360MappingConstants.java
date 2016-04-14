package com.cgi.td.collection360.constants;

public class TDCollection360MappingConstants {
	
	
	public static final String BCDMACCT_FILE_PATH = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//TD Sample Input Files//POC_BCDMACCT_R2016-03-14.txt";
	public static final String BCDMCUST_FILE_PATH  = "D:\\suresh Devaki\\D Drive\\Suresh\\Devaki\\POC for TD Collection 360\\TD Sample Input Files\\POC_BCDMCUST_R2016-03-14.txt";
	public static final String p327ext_FILE_PATH  = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//p327ext.txt";
	public static final String atoctran_FILE_PATH  = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//atoctran.txt";
	public static final String contact_account_FILE_PATH  = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact-account.txt";
	public static final String contact_FILE_PATH = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact.txt";
	

	public static final String SPACES_1 = patchEmptySpaces(1);
	public static final String SPACES_2 = patchEmptySpaces(2);
	public static final String SPACES_3 = patchEmptySpaces(3);
	public static final String SPACES_4 = patchEmptySpaces(4);
	public static final String SPACES_5 = patchEmptySpaces(5);
	public static final String SPACES_7 = patchEmptySpaces(7);
	public static final String SPACES_8 = patchEmptySpaces(8);
	public static final String SPACES_12 = patchEmptySpaces(12);
	public static final String SPACES_18 = patchEmptySpaces(18);
	public static final String SPACES_20 = patchEmptySpaces(20);
	public static final String SPACES_25 = patchEmptySpaces(25);
	public static final String SPACES_30 = patchEmptySpaces(30);
	public static final String SPACES_31 = patchEmptySpaces(31);
	public static final String SPACES_34 = patchEmptySpaces(34);
	public static final String SPACES_35 = patchEmptySpaces(35);
	public static final String SPACES_40 = patchEmptySpaces(40);
	public static final String SPACES_45 = patchEmptySpaces(45);
	public static final String SPACES_50 = patchEmptySpaces(50);
	
	public static final String ACTG_SYS_ID  = "BCDM";
	
	
	public static final String PLUS_ZEROS_18 = "+000000000000000000";
	public static final String ZEROS_3 = "000";
	public static final String ZEROS_8 = "00000000";
	public static final String PLUS_ZEROS_5 = "+00000";
	public static final String PLUS_ZEROS_3 = "+000";
	
	
	public static String patchEmptySpaces(int count){
		String emptyStr="";
		for(int i=0; i<count; i++){
			emptyStr = emptyStr + " ";
		 }
		return emptyStr;
	}
	
	
}
