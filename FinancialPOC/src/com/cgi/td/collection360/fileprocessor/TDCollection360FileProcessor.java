package com.cgi.td.collection360.fileprocessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.cgi.td.collection360.domain.Account;
import com.cgi.td.collection360.domain.Customer;
import com.cgi.td.collection360.util.TDCollectionUtil;

import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_1;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_2;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_3;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_4;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_5;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_7;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_8;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_12;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_18;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_20;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_25;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_30;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_31;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_34;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_35;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_40;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_45;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.SPACES_50;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.ZEROS_8;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.ZEROS_3;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.PLUS_ZEROS_18;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.PLUS_ZEROS_5;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.PLUS_ZEROS_3;
import static com.cgi.td.collection360.constants.TDCollection360MappingConstants.ACTG_SYS_ID;

public class TDCollection360FileProcessor {
    
	private String ACCT_INPUT_FILE="";
	private String CUST_INPUT_FILE="";
	public List<Account> readTDAccountData() {
		
		//String accountFilePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//TD Sample Input Files//POC_BCDMACCT_R2016-03-14.txt";
		
		List<Account> accountList = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(ACCT_INPUT_FILE)))
		{

			String aCurrentLine;
			String validationDate = null;
			int index = 0;
			accountList = new ArrayList<Account>();

			//while ((aCurrentLine = br.readLine()) != null && !br.readLine().contains("\r\n")) {
				while ((aCurrentLine = br.readLine()) != null ) {
				//System.out.println(aCurrentLine);
	
					if(index != 0) {
						if (aCurrentLine.length() > 0) {
						Account account = populateAccountData(aCurrentLine, validationDate);
						accountList.add(account);
					  }	
					}
					else{
						//System.out.println(aCurrentLine);
						validationDate = aCurrentLine.substring(35, 45);
						//System.out.println("validationDate: " + validationDate);
					}
					index++;
			}
				
				accountList.remove(accountList.size()-1);
				
				//System.out.println("accountList: "  + accountList);
				
				/*for (Account acct : accountList) {
				    System.out.println("Acct Num before sorting: " + acct.getAccountNum());
				}*/
				
				sortAccountsOnAcctNum(accountList);
				
				
				
				/*for (Account acct : accountList) {
				    System.out.println("Acct Num after sorting: " + acct.getAccountNum());
				}*/


		} catch (IOException e) {
			e.printStackTrace();
		} 
		//System.out.println("readTDAccountData");
		return accountList;
	}
	
	@SuppressWarnings("unchecked")
	public void sortAccountsOnAcctNum(List<Account> accountList) {
		
		Collections.sort(accountList, new Comparator() {
	        @Override
	        public int compare(Object acct1, Object acct2) {
	            return ((Account)acct1).getAccountNum()
                        .compareTo(((Account)acct2).getAccountNum());

	        }
	    }); 
	}
	
	
	@SuppressWarnings("unchecked")
	public void sortCustomersOnNrtRefNumAndRelationType(List<Customer> customerList) {
		
		Collections.sort(customerList, new Comparator() {
	        @Override
	        public int compare(Object cust1, Object cust2) {
	        	
	        	 int value1 = ((Customer)cust1).getNrtRefNum().compareTo(((Customer)cust2).getNrtRefNum());
	             if (value1 == 0) {
	            	 return ((Customer)cust1).getRelationType().compareTo(((Customer)cust2).getRelationType());
	             }
	             else {
	            	 return value1;
	             }
	        }
	    }); 
	}
	
	
	

	
	public List<Customer> readTDCustomerData(){
		
		//String customerFilePath = "D:\\suresh Devaki\\D Drive\\Suresh\\Devaki\\POC for TD Collection 360\\TD Sample Input Files\\POC_BCDMCUST_R2016-03-14.txt";
		List<Customer> customerList = null;
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(CUST_INPUT_FILE)))
		{
			int index = 0;
			String sCurrentLine;
			customerList = new ArrayList<Customer>();
			while ((sCurrentLine = br.readLine()) != null && !br.readLine().contains("\r\n") ) {
				//System.out.println(sCurrentLine);
					
				if(index != 0) {
					
					Customer customer = populateCustomerData(sCurrentLine);
					customerList.add(customer);
				 
				}
				index++;
			}
			
			customerList.remove(customerList.size()-1);
			//System.out.println("customerList: "  + customerList);
			
			/*for (Customer customer : customerList) {
			    System.out.println("Nrt Ref Num  and Relation type before sorting: " + customer.getNrtRefNum() + "\t" + customer.getRelationType());
			}*/
			
			sortCustomersOnNrtRefNumAndRelationType(customerList);
			
			/*for (Customer customer : customerList) {
			    System.out.println("Nrt Ref Num  and Relation type after sorting: " + customer.getNrtRefNum() + "\t" + customer.getRelationType());
			}*/
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//System.out.println("readTDCustomerData");
		
		return customerList;
	}
	
	public Customer populateCustomerData(String customerRecord){
		Customer customer = new Customer();
		//System.out.println("customerRecord: " + customerRecord);
		customer.setNrtRefNum(customerRecord.substring(0, 9));
		customer.setCreationTimeStamp(customerRecord.substring(9, 36));
		customer.setName(customerRecord.substring(35, 75));
		customer.setRelationType(customerRecord.substring(75, 78));
		customer.setHomePhoneNum(customerRecord.substring(78, 88));
		customer.setBusPhoneNum(customerRecord.substring(88, 98));
		customer.setBusPhoneExt(customerRecord.substring(98, 102));
		customer.setAltPhoneNum(customerRecord.substring(102, 112));
		customer.setBestTimeToCall(customerRecord.substring(112, 122));
		customer.setStreet(customerRecord.substring(122, 162));
		customer.setCity(customerRecord.substring(162, 202));
		customer.setProvince(customerRecord.substring(202, 204));
		customer.setCountry(customerRecord.substring(204, 207));
		customer.setPostCd(customerRecord.substring(207, 217));
		customer.setCreationUserId(customerRecord.substring(217, 225));
		customer.setLastModTimeStamp(customerRecord.substring(225, 251));
		customer.setLastModUserId(customerRecord.substring(251, 259));
		customer.setCdmCustNum(customerRecord.substring(259, 269));
		customer.setCustBasicInfoInd(customerRecord.substring(269, 270));
		
		
		/*System.out.println("customer.getNrtRefNum(): " + customer.getNrtRefNum());
		System.out.println("customer.getCreationTimeStamp(): " + customer.getCreationTimeStamp());
		System.out.println("customer.getName(): " + customer.getName());
		System.out.println("customer.getRelationType(): " + customer.getRelationType());
		System.out.println("customer.getHomePhoneNum(): " + customer.getHomePhoneNum());
		System.out.println("customer.getBusPhoneNum(): " + customer.getBusPhoneNum());
		System.out.println("customer.getBusPhoneExt(): " + customer.getBusPhoneExt());
		System.out.println("customer.getAltPhoneNum(): " + customer.getAltPhoneNum());
		System.out.println("customer.getStreet(): " + customer.getStreet());
		System.out.println("customer.getCity(): " + customer.getCity());
		System.out.println("customer.getProvince(): " + customer.getProvince());
		System.out.println("customer.getPostCd(): " + customer.getPostCd());
		System.out.println("customer.getCreationUserId(): " + customer.getCreationUserId());
		System.out.println("customer.getLastModTimeStamp(): " + customer.getLastModTimeStamp());
		System.out.println("customer.setLastModUserId(): " + customer.getLastModUserId());
		System.out.println("customer.getCdmCustNum(): " + customer.getCdmCustNum());
		System.out.println("customer.getCustBasicInfoInd(): " + customer.getCustBasicInfoInd());*/
		
		return customer;
			
	}
	
	public Account populateAccountData(String accountRecord, String validationDate){
		Account account = new Account();
		
		//System.out.println("accountRecord: " + accountRecord);
		account.setValidationDate(validationDate);
		account.setAccountNum(accountRecord.substring(0, 20));
		account.setCifLinkBranch(accountRecord.substring(20, 25));
		account.setProdType(accountRecord.substring(25, 28));
		account.setProdSubType(accountRecord.substring(28, 38));
		account.setNrtRefNum(accountRecord.substring(38, 47));
		account.setBranchNum(accountRecord.substring(47, 51));
		account.setStatusCd(accountRecord.substring(51, 52));
		account.setPreviousStatusCd(accountRecord.substring(52, 53));
		account.setInitAmtOverdue(accountRecord.substring(53, 66));
		account.setCurrencyCd(accountRecord.substring(66, 69));
		account.setInitDaysDelinq(accountRecord.substring(69, 73));
		account.setNrtAssignDate(accountRecord.substring(73, 83));
		account.setCurrAmtOverDue(accountRecord.substring(83, 96));
		account.setCurrDaysDelinq(accountRecord.substring(96, 100));
		account.setCurrValueDate(accountRecord.substring(100, 110));
		account.setLegalActionStage(accountRecord.substring(110, 111));
		account.setCollatSaleStage(accountRecord.substring(111, 112));
		account.setGoodStandFlag(accountRecord.substring(112, 113));
		account.setCreationTimeStamp(accountRecord.substring(113, 139));
		account.setCreationUserId(accountRecord.substring(139, 147));
		account.setLastModTimeStamp(accountRecord.substring(147, 173));
		account.setLastModUserId(accountRecord.substring(173, 181));
		account.setCdicEligibleCd(accountRecord.substring(181, 182));
		account.setPdaDesignationCd(accountRecord.substring(182, 186));
		account.setCifLinkAccount(accountRecord.substring(186, 196));
		account.setCdicClaimStage(accountRecord.substring(196, 197));
		account.setLineOfCredit(accountRecord.substring(197, 208));
		account.setTotalOsAmt(accountRecord.substring(208, 223));
		account.setInitLineOfCrdt(accountRecord.substring(223, 234));
		account.setInitTotalOsAmt(accountRecord.substring(234, 249));
		account.setInitWrtoffAmt(accountRecord.substring(249, 264));
		account.setWrtoffReasonCd(accountRecord.substring(264, 270));
		account.setOccupCd(accountRecord.substring(270, 272));
		account.setWrtoffDate(accountRecord.substring(272, 282));
		account.setAgencyPgmCd(accountRecord.substring(282, 284));
		account.setNsfAmtDue(accountRecord.substring(284, 299));
		account.setCurrDelqDate(accountRecord.substring(299, 309));
		account.setLastDepositDt(accountRecord.substring(309, 319));
		account.setOrg(accountRecord.substring(319, 322));
		account.setLogo(accountRecord.substring(322, 325));
		account.setCreditCardsBlockCd1(accountRecord.substring(325, 326));
		account.setCreditCardsBlockCd2(accountRecord.substring(326, 327));
		account.setCdmBlockCd(accountRecord.substring(327, 328));
		account.setOpenDate(accountRecord.substring(328, 338));
		account.setMarketingType(accountRecord.substring(338, 344));
		account.setVipInd(accountRecord.substring(344, 345));
		account.setSecuredInd(accountRecord.substring(345, 346));
		account.setCifProfileInd(accountRecord.substring(346, 347));
		account.setFirstPymtDefInd(accountRecord.substring(347, 348));
		account.setBankruptInd(accountRecord.substring(348, 349));
		account.setDeceasedInd(accountRecord.substring(349, 350));
		account.setInitInhibitInd(accountRecord.substring(350, 351));
		account.setCashBalAmt(accountRecord.substring(351, 366));
		account.setLastPymtAmt(accountRecord.substring(366, 381));
		account.setNumChequesRet(accountRecord.substring(381, 384));
		account.setBehaviourScore(accountRecord.substring(384, 387));
		account.setDelinqHistory(accountRecord.substring(387, 411));
		
		
		/*System.out.println("account.getAccountNum(): " + account.getAccountNum());
		System.out.println("account.getCifLinkBranch(): " + account.getCifLinkBranch());
		System.out.println("account.getProdType(): " + account.getProdType());
		System.out.println("account.getProdSubType(): " + account.getProdSubType());
		System.out.println("account.getNrtRefNum(): " + account.getNrtRefNum());
		System.out.println("account.getBranchNum(): " + account.getBranchNum());
		System.out.println("account.getStatusCd(): " + account.getStatusCd());
		System.out.println("account.getPreviousStatusCd(): " + account.getPreviousStatusCd());
		System.out.println("account.getInitAmtOverdue(): " + account.getInitAmtOverdue());
		System.out.println("account.getCurrencyCd(): " + account.getCurrencyCd());
		System.out.println("account.getInitDaysDelinq(): " + account.getInitDaysDelinq());
		System.out.println("account.getNrtAssignDate(): " + account.getNrtAssignDate());
		System.out.println("account.getCurrAmtOverDue(): " + account.getCurrAmtOverDue());
		System.out.println("account.getCurrDaysDelinq(): " + account.getCurrDaysDelinq());
		System.out.println("account.getCurrValueDate(): " + account.getCurrValueDate());
		System.out.println("account.getLegalActionStage(): " + account.getLegalActionStage());
		System.out.println("account.getCollatSaleStage(): " + account.getCollatSaleStage());
		System.out.println("account.getGoodStandFlag(): " + account.getGoodStandFlag());
		System.out.println("account.getCreationTimeStamp(): " + account.getCreationTimeStamp());
		System.out.println("account.getCreationUserId(): " + account.getCreationUserId());
		System.out.println("account.getLastModTimeStamp(): " + account.getLastModTimeStamp());
		System.out.println("account.getLastModUserId(): " + account.getLastModUserId());
		System.out.println("account.getCdicEligibleCd(): " + account.getCdicEligibleCd());
		System.out.println("account.getPdaDesignationCd(): " + account.getPdaDesignationCd());
		System.out.println("account.getCdicClaimStage(): " + account.getCdicClaimStage());
		System.out.println("account.getLineOfCredit(): " + account.getLineOfCredit());
		System.out.println("account.getTotalOsAmt(): " + account.getTotalOsAmt());
		System.out.println("account.getInitLineOfCrdt(): " + account.getInitLineOfCrdt());
		System.out.println("account.getInitTotalOsAmt(): " + account.getInitTotalOsAmt());
		System.out.println("account.getInitWrtoffAmt(): " + account.getInitWrtoffAmt());
		System.out.println("account.getWrtoffReasonCd(): " + account.getWrtoffReasonCd());
		System.out.println("account.getOccupCd(): " + account.getOccupCd());
		System.out.println("account.getWrtoffDate(): " + account.getWrtoffDate());
		System.out.println("account.getAgencyPgmCd(): " + account.getAgencyPgmCd());
		System.out.println("account.getNsfAmtDue(): " + account.getNsfAmtDue());
		System.out.println("account.getCurrDelqDate(): " + account.getCurrDelqDate());
		System.out.println("account.getLastDepositDt(): " + account.getLastDepositDt());
		System.out.println("account.getOrg(): " + account.getOrg());
		System.out.println("account.getLogo(): " + account.getLogo());
		System.out.println("account.getCreditCardsBlockCd1(): " + account.getCreditCardsBlockCd1());
		System.out.println("account.getCreditCardsBlockCd2(): " + account.getCreditCardsBlockCd2());
		System.out.println("account.getCdmBlockCd(): " + account.getCdmBlockCd());
		System.out.println("account.getOpenDate(): " + account.getOpenDate());
		System.out.println("account.getMarketingType(): " + account.getMarketingType());
		System.out.println("account.getVipInd(): " + account.getVipInd());
		System.out.println("account.getSecuredInd(): " + account.getSecuredInd());
		System.out.println("account.getCifProfileInd(): " + account.getCifProfileInd());
		System.out.println("account.getFirstPymtDefInd(): " + account.getFirstPymtDefInd());
		System.out.println("account.getBankruptInd(): " + account.getBankruptInd());
		System.out.println("account.getDeceasedInd(): " + account.getDeceasedInd());
		System.out.println("account.getInitInhibitInd(): " + account.getInitInhibitInd());
		System.out.println("account.getCashBalAmt(): " + account.getCashBalAmt());
		System.out.println("account.getLastPymtAmt(): " + account.getLastPymtAmt());
		System.out.println("account.getNumChequesRet(): " + account.getNumChequesRet());
		System.out.println("account.getBehaviourScore(): " + account.getBehaviourScore());
		System.out.println("account.getDelinqHistory(): " + account.getDelinqHistory());*/
		
		
		return account;
	}
	
	
	public void generateFinancialExtract(List<Account> accountList){
		String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//p327ext.txt";
		
		BufferedWriter bw = null;
	      try {
		 String mycontent = "This String would be written" +
		    " to the specified File";
	         //Specify the file name and path here
		 File file = new File(filePath);

		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file);
		  bw = new BufferedWriter(fw);
		  
		  for(Account account: accountList){
			  StringBuffer sb = new StringBuffer();
			  
			  /*System.out.println("account.getProdType(): " + account.getProdType());
			  System.out.println("account.getAccountNum(): " + account.getAccountNum());
			  System.out.println("account.getAccountNum().length(): " + account.getAccountNum().length());
			  System.out.println("account.getLineOfCredit(): " + account.getLineOfCredit());
			  System.out.println("account.getLineOfCredit().trim().length(): " + account.getLineOfCredit().trim().length());
			  System.out.println("account.getTotalOsAmt(): " + account.getTotalOsAmt());
			  System.out.println("account.getTotalOsAmt().length(): " + account.getTotalOsAmt().length());
			  System.out.println("account.getCurrAmtOverDue(): " + account.getCurrAmtOverDue());
			  System.out.println("account.getCurrDaysDelinq(): " + account.getCurrDaysDelinq());
			  System.out.println("account.getLastPymtAmt(): " + account.getLastPymtAmt());
			  System.out.println("account.getWrtoffDate(): " + account.getWrtoffDate());
			  System.out.println("account.getOpenDate(): " + account.getOpenDate());
			  System.out.println("account.getCashBalAmt(): " + account.getCashBalAmt());
			  System.out.println("account.getInitAmtOverdue(): " + account.getInitAmtOverdue());
			  System.out.println("account.getNrtAssignDate(): " + account.getNrtAssignDate());
			  System.out.println("account.getNrtAssignDate(): " + account.getNrtAssignDate());
			  System.out.println("account.getNumChequesRet(): " + account.getNumChequesRet());
			  System.out.println("account.getNumChequesRet().length(): " + account.getNumChequesRet().length());
			  System.out.println("account.getCurrValueDate(): " + account.getCurrValueDate());
			  System.out.println("account.getBankruptInd(): " + account.getBankruptInd());
			  System.out.println("account.getBehaviourScore(): " + account.getBehaviourScore());
			  System.out.println("account.getBehaviourScore().length(): " + account.getBehaviourScore().length());
			  System.out.println("account.getLastDepositDt(): " + account.getLastDepositDt());
			  System.out.println("account.getInitInhibitInd(): " + account.getInitInhibitInd());
			  System.out.println("account.getInitInhibitInd().length(): " + account.getInitInhibitInd().length());
			  System.out.println("account.getInitWrtoffAmt(): " + account.getInitWrtoffAmt());
			  System.out.println("account.getProdSubType(): " + account.getProdSubType());
			  System.out.println("account.getProdSubType().length(): " + account.getProdSubType().length());
			  System.out.println("account.getDelinqHistory(): " + account.getDelinqHistory());
			  System.out.println("account.getDelinqHistory().length(): " + account.getDelinqHistory().length());
			  System.out.println("account.getMarketingType(): " + account.getMarketingType());
			  System.out.println("account.getMarketingType().length(): " + account.getMarketingType().length());
			  System.out.println("account.getInitLineOfCrdt(): " + account.getInitLineOfCrdt());
			  System.out.println("account.getInitLineOfCrdt().length(): " + account.getInitLineOfCrdt().length());
			  System.out.println("account.getInitTotalOsAmt(): " + account.getInitTotalOsAmt());
			  System.out.println("account.getLastModUserId(): " + account.getLastModUserId());
			  System.out.println("account.getCifLinkBranch(): " + account.getCifLinkBranch());
			  System.out.println("account.getInitDaysDelinq(): " + account.getInitDaysDelinq());
			  System.out.println("account.getBranchNum(): " + account.getBranchNum());
			  System.out.println("account.getWrtoffReasonCd(): " + account.getWrtoffReasonCd());
			  System.out.println("account.getWrtoffReasonCd():.length() " + account.getWrtoffReasonCd().length());
			  System.out.println("account.getNsfAmtDue(): " + account.getNsfAmtDue());
			  System.out.println("account.getNsfAmtDue():.length() " + account.getNsfAmtDue().length());
			  System.out.println("account.getDeceasedInd(): " + account.getDeceasedInd());
			  System.out.println("account.getCurrencyCd(): " + account.getCurrencyCd());
			  System.out.println("account.getStatusCd(): " + account.getStatusCd());
			  System.out.println("account.getPreviousStatusCd(): " + account.getPreviousStatusCd());
			  System.out.println("account.getLegalActionStage(): " + account.getLegalActionStage());
			  System.out.println("account.getCollatSaleStage(): " + account.getCollatSaleStage());
			  System.out.println("account.getGoodStandFlag(): " + account.getGoodStandFlag());
			  System.out.println("account.getCdicEligibleCd(): " + account.getCdicEligibleCd());
			  System.out.println("account.getCdicClaimStage(): " + account.getCdicClaimStage());
			  System.out.println("account.getCreditCardsBlockCd1(): " + account.getCreditCardsBlockCd1());
			  System.out.println("account.getCreditCardsBlockCd2(): " + account.getCreditCardsBlockCd2());
			  System.out.println("account.getVipInd(): " + account.getVipInd());
			  System.out.println("account.getAgencyPgmCd(): " + account.getAgencyPgmCd());*/
			  
			  
			  
			  sb.append(TDCollectionUtil.getProdType(account.getProdType()));
			  sb.append(TDCollectionUtil.getAccountNum(account.getAccountNum()));
			  sb.append(TDCollectionUtil.getLineOfCredit(account.getLineOfCredit()));
			  
			  //sb.append("<--CREDIT-LIMIT-AMT");
			  
			  sb.append(TDCollectionUtil.getExpirationDate());
			  sb.append(TDCollectionUtil.getTotalOsAmt(account.getTotalOsAmt()));
			  
			  //sb.append("<--BALANCE AMT");
			  
			  sb.append(TDCollectionUtil.getOverlimitAmt());
			  sb.append(TDCollectionUtil.getDisputedAmt());
			  sb.append(TDCollectionUtil.getTotalDueAmt(account.getTotalOsAmt()));
			  
			  //sb.append("<--TOTAL DUE AMT");
			  
			  sb.append(TDCollectionUtil.getCurrentDueAmt(account.getCurrAmtOverDue()));
			  
			  //sb.append("<--CURRENT DUE AMT");
			  
			  sb.append(TDCollectionUtil.getCollAsgnUd1());
			  sb.append(TDCollectionUtil.getCollAsgnUdType1());
			  sb.append(TDCollectionUtil.getCollAsgnUdFmt1());
			  sb.append(TDCollectionUtil.getCollAsgnUd2());
			  sb.append(TDCollectionUtil.getCollAsgnUdType2());
			  sb.append(TDCollectionUtil.getCollAsgnUdFmt2());
			  sb.append(TDCollectionUtil.getTotalOsAmt(account.getCurrAmtOverDue()));
			  
			  //sb.append("<--TOTAL-DELINQ-AMT");
			  
			  sb.append(TDCollectionUtil.getCyclesDelinqCnt());
			  sb.append(TDCollectionUtil.getDaysDelinqCnt(account.getCurrDaysDelinq()));
			  
			  //sb.append("<--DAYS-DELINQ-CNT");
			  
			  sb.append(TDCollectionUtil.getLastPaymentAmt(account.getLastPymtAmt()));
			  
			  //sb.append("<--LAST-PAYMENT-AMT");
			  
			  /*CHARGE-OFF-DATE*/
			  //sb.append(TDCollectionUtil.getChargeOffDate(account.getWrtoffDate()));
			  sb.append("00000000");
			  
			  sb.append(TDCollectionUtil.getDlqDaysChrgOff());
			  sb.append(TDCollectionUtil.getLstPartCofAmt());
			  
			  /*LST-PART-COF-DATE*/
			  //sb.append(TDCollectionUtil.getLstPartCofDate());
			  sb.append("00000000");
			  
			  
			  sb.append(TDCollectionUtil.getTotPartCofAmt());
			  sb.append(TDCollectionUtil.getBalPartCofAmt());
			  
			  /*PART-COF-ACCT-NUM*/
			  sb.append(TDCollectionUtil.getPartCofAcctNum());
			  
			  sb.append(TDCollectionUtil.getUserDefined7(account.getTotalOsAmt()));
			  
			  //sb.append("<--USER-DEFINED-7");
			  
			  sb.append(TDCollectionUtil.getUd7Type());
			  sb.append(TDCollectionUtil.getUd7Fmt());
			  
			  /*ACCT-OPEN-DATE*/
			  //sb.append(TDCollectionUtil.getAcctOpenDate(account.getOpenDate()));
			  sb.append("00000000");
			  
			  sb.append(TDCollectionUtil.getNextDueDate());
			  sb.append(TDCollectionUtil.getInterestRate());
			  sb.append(TDCollectionUtil.getOthDelinqAmt(account.getCashBalAmt()));
			  
			  //sb.append("<--OTH-DELINQ-AMT");
			  

			  
			  sb.append(TDCollectionUtil.getRepoCharges());
			  sb.append(TDCollectionUtil.getRepossessionDate());
			  sb.append(TDCollectionUtil.getMaturityDate());
			  sb.append(TDCollectionUtil.getPaymentAmt());
			  sb.append(TDCollectionUtil.getPrincipalDelqAmt(account.getInitAmtOverdue()));
			  
			  //sb.append("<--PRINCIPAL-DELQ-AMT");
			  
			  sb.append(TDCollectionUtil.getInterestDelqAmt());
			  sb.append(TDCollectionUtil.getUserDefSearch1());
			  sb.append(TDCollectionUtil.getUdSearch1Type());
			  sb.append(TDCollectionUtil.getUdSearch1Fmt());
			  sb.append(TDCollectionUtil.getUserDefSearch2());
			  sb.append(TDCollectionUtil.getUdSearch2Type());
			  sb.append(TDCollectionUtil.getUdSearch2Fmt());
			  sb.append(TDCollectionUtil.getAcctNumSort());
			  sb.append(TDCollectionUtil.getCycleId());
			  sb.append(TDCollectionUtil.getUdDateField1(account.getNrtAssignDate()));
			  sb.append(TDCollectionUtil.getLastBillingDate());
			  sb.append(TDCollectionUtil.getAgedHistory());
			  sb.append(TDCollectionUtil.getCreditLimitDate());
			  sb.append(TDCollectionUtil.getIntLateChgAmt());
			  sb.append(TDCollectionUtil.getDelinqAmtAge1());
			  sb.append(TDCollectionUtil.getDelinqAmtAge2());
			  sb.append(TDCollectionUtil.getDelinqAmtAge3());
			  sb.append(TDCollectionUtil.getDelinqAmtAge4());
			  sb.append(TDCollectionUtil.getNumberOfCards(account.getNumChequesRet()));
			  sb.append(TDCollectionUtil.getWarnBulletinDate(account.getCurrValueDate()));
			  sb.append(TDCollectionUtil.getWarnBulletinZone(account.getBankruptInd()));
			  sb.append(TDCollectionUtil.getOtherAcctNum());
			  sb.append(TDCollectionUtil.getCreditScore());
			  sb.append(TDCollectionUtil.getBehaviorIndex(account.getBehaviourScore()));
			  sb.append(TDCollectionUtil.getLastPaymentDate(account.getLastDepositDt()));
			  sb.append(TDCollectionUtil.getLastMonetaryAmt());
			  sb.append(TDCollectionUtil.getLastMonetaryDate());
			  sb.append(TDCollectionUtil.getLastMonetaryType(account.getInitInhibitInd()));
			  sb.append(TDCollectionUtil.getChargeOffAmt(account.getInitWrtoffAmt()));
			  
			  //sb.append("<--CHARGE-OFF-AMT");
			  
			  sb.append(TDCollectionUtil.getSortValue1());
			  sb.append(TDCollectionUtil.getSortValue1Type());
			  sb.append(TDCollectionUtil.getSortValue1Fmt());
			  sb.append(TDCollectionUtil.getSortValue2());
			  sb.append(TDCollectionUtil.getSortValue2Type());
			  sb.append(TDCollectionUtil.getSortValue2Fmt());
			  sb.append(TDCollectionUtil.getUserDefined1(account.getProdSubType()));
			  sb.append(TDCollectionUtil.getUd1Type());
			  sb.append(TDCollectionUtil.getUd1Fmt());
			  sb.append(TDCollectionUtil.getUserDefined2(account.getDelinqHistory()));
			  sb.append(TDCollectionUtil.getUd2Type());
			  sb.append(TDCollectionUtil.getUd2Fmt());
			  sb.append(TDCollectionUtil.getUserDefined3(account.getMarketingType()));
			  sb.append(TDCollectionUtil.getUd3Type());
			  sb.append(TDCollectionUtil.getUd3Fmt());
			  sb.append(TDCollectionUtil.getUserDefined4());
			  sb.append(TDCollectionUtil.getUd4Type());
			  sb.append(TDCollectionUtil.getUd4Fmt());
			  sb.append(TDCollectionUtil.getUserDefined5());
			  sb.append(TDCollectionUtil.getUd5Type());
			  sb.append(TDCollectionUtil.getUd5Fmt());
			  sb.append(TDCollectionUtil.getUserDefined6(account.getTotalOsAmt()));
			  sb.append(TDCollectionUtil.getUd6Type());
			  sb.append(TDCollectionUtil.getUd6Fmt());
			  sb.append(TDCollectionUtil.getUserDefined81());
			  sb.append(TDCollectionUtil.getUserDefined82());
			  sb.append(TDCollectionUtil.getUserDefined83());
			  sb.append(TDCollectionUtil.getUserDefined84());
			  sb.append(TDCollectionUtil.getUserDefined85());
			  sb.append(TDCollectionUtil.getUd81Type());
			  sb.append(TDCollectionUtil.getUd82Type());
			  sb.append(TDCollectionUtil.getUd83Type());
			  sb.append(TDCollectionUtil.getUd84Type());
			  sb.append(TDCollectionUtil.getUd85Type());
			  sb.append(TDCollectionUtil.getUd81Fmt());
			  sb.append(TDCollectionUtil.getUd82Fmt());
			  sb.append(TDCollectionUtil.getUd83Fmt());
			  sb.append(TDCollectionUtil.getUd84Fmt());
			  sb.append(TDCollectionUtil.getUd85Fmt());
			  sb.append(TDCollectionUtil.getPrincipalAmt());
			  sb.append(TDCollectionUtil.getOtherAmt(account.getInitLineOfCrdt()));
			  
			  //sb.append("<--OTHERAMT");
			  
			  sb.append(TDCollectionUtil.getOrigBalanceAmt(account.getInitTotalOsAmt()));
			  
			  //sb.append("<--ORIGIBALAMT");
			  
			  sb.append(TDCollectionUtil.getLastModUserId(account.getLastModUserId()));
			  sb.append(TDCollectionUtil.getBranchNum(account.getCifLinkBranch()));
			  sb.append(TDCollectionUtil.getOriginalTerm(account.getInitDaysDelinq()));
			  sb.append(TDCollectionUtil.getOrigInterestRate());
			  sb.append(TDCollectionUtil.getDealerNum(account.getBranchNum()));
			  
			  /*PAYMENT-FREQUENCY*/
			  sb.append(SPACES_2);
			  
			  /*REFINANCE-DATE*/
			  sb.append(ZEROS_8);
			  
			  /*DEPOSIT-IND --> SECURED-IND  */
			  sb.append(account.getSecuredInd());
			  
			  /*ON-BUDGET-IND --> CIF-PROFILE-IND    */
			  sb.append(account.getCifProfileInd());
			  
			  /* DPA-IND --> FIRST-PYMT-DEF-IND */
			  sb.append(account.getFirstPymtDefInd());
			  
			  /* CUT-OFF-DATE */
			  sb.append(ZEROS_8);
			  
			  sb.append(TDCollectionUtil.getCutOffCode(account.getWrtoffReasonCd()));
			  
			  /*ACCOUNT-TYPE*/
			  sb.append(SPACES_3);
			  
			  /*SERVICE-TYPE*/
			  sb.append(SPACES_3);
			  
			  /*BUDGET-PAYMENT-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  sb.append(TDCollectionUtil.getTerminateAmt(account.getNsfAmtDue()));
			  
			  //sb.append("<--TERMINATE-AMT");
			  
			  /*TERMINATE-EXP-DATE*/
			  sb.append(ZEROS_8);
			  
			  /*SAT-USER-DEFINED-1*/
			  sb.append(SPACES_12);
			  
			  /*SAT-UD-1-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*SAT-UD-1-FMT*/
			  sb.append(SPACES_2);
			  
			  /*SAT-USER-DEFINED-2*/
			  sb.append(SPACES_12);
			  
			  /*SAT-UD-2-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*SAT-UD-2-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-1*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-1-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-1-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-2*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-2-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-2-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-3*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-3-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-3-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-4*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-4-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-4-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-5*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-5-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-5-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-6*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-6-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-6-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-7*/
			  sb.append(SPACES_30);
			  
			  /*LETTER-UD-7-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-7-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-8*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*LETTER-UD-8-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-8-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-9*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*LETTER-UD-9-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-9-FMT*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-USER-DEF-10*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*LETTER-UD-10-TYPE*/
			  sb.append(SPACES_2);
			  
			  /*LETTER-UD-10-FMT*/
			  sb.append(SPACES_2);
			  
			  /*BANK-NUM*/
			  sb.append(PLUS_ZEROS_5);
			  
			  /*APP-CODE*/
			  sb.append(PLUS_ZEROS_3);
			  
			  /*ESTIMATED-MNTH-PAY*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*ACI-INDICATOR --> DECEASED-IND */
			  sb.append(account.getDeceasedInd());
			  
			  /*SERVICING-SYSTEM*/
			  sb.append(SPACES_8);
			  
			  /*NATL-CURRENCY*/
			  sb.append(account.getCurrencyCd());
			  
			  /*BASE-CURRENCY*/
			  sb.append(account.getCurrencyCd());
			  
			  /*ACCT-TYPE-CD-1*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-1*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-1*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-1*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-1*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-1*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-1*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-2*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-2*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-2*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-2*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-2*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-2*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-2*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-3*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-3*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-3*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-3*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-3*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-3*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-3*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-4*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-4*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-4*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-4*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-4*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-4*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-4*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-5*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-5*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-5*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-5*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-5*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-5*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-5*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-6*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-6*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-6*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-6*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-6*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-6*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-6*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-7*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-7*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-7*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-7*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-7*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-7*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-7*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-8*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-8*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-8*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-8*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-8*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-8*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-8*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-9*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-9*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-9*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-9*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-9*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-9*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-9*/
			  sb.append(SPACES_1);
			  
			  /*ACCT-TYPE-CD-10*/
			  sb.append(SPACES_2);
			  
			  /*LABEL-1-CD-10*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-1-10*/
			  sb.append(SPACES_18);
			  
			  /*VAL-1-TYPE-CD-10*/
			  sb.append(SPACES_1);
			  
			  /*LABEL-2-CD-10*/
			  sb.append(SPACES_2);
			  
			  /*STRING-VALUE-2-10*/
			  sb.append(SPACES_18);
			  
			  /*VAL-2-TYPE-CD-10*/
			  sb.append(SPACES_1);
			  
			  /*VALUE-CODE*/
			  sb.append(SPACES_2);
			  
			  /*RISK-CODE*/
			  sb.append(SPACES_2);
			  
			  /*RECOMMENDED-ACT-CD*/
			  sb.append(SPACES_2);
			  
			  /*TIME-ZONE*/
			  sb.append(SPACES_5);

			  /*ACTG-SYS-ID*/
			  sb.append(ACTG_SYS_ID);
			  
			  /*EXTERNAL-ACCT-NUM*/
			  sb.append(SPACES_30);
			  
			  /*ACCT-STAT-CODE-1	-->	STATUS-CD*/
			  sb.append(account.getStatusCd());
			  
			  /*ACCT-STAT-CODE-2	-->	PREVIOUS-STATUS-CD*/
			  sb.append(account.getPreviousStatusCd());
			  
			  /*ACCT-STAT-CODE-3	-->	LEGAL-ACTION-STAGE*/
			  sb.append(account.getLegalActionStage());
			  
			  /*ACCT-STAT-CODE-4	--> COLLAT-SALE-STAGE */
			  sb.append(account.getCollatSaleStage());
			  
			  /*ACCT-STAT-CODE-5	-->	GOOD-STAND-FLAG   */
			  sb.append(account.getGoodStandFlag());
			  			  
			  /*ACCT-STAT-CODE-6	-->	CDIC-ELIGIBLE-CD */
			  sb.append(account.getCdicEligibleCd());
			  
			  /*ACCT-STAT-CODE-7	--> CDIC-CLAIM-STAGE */ 
			  sb.append(account.getCdicClaimStage());
			  
			  /*ACCT-STAT-CODE-8	--> Credit Cards-BLOCK-CD-1*/
			  sb.append(account.getCreditCardsBlockCd1());
			  
			  /*ACCT-STAT-CODE-9	--> Credit Cards-BLOCK-CD-2*/
			  sb.append(account.getCreditCardsBlockCd2());
			  
			  /*ACCT-STAT-CODE-10	-->	VIP-IND*/
			  sb.append(account.getVipInd());

			  /*LGL-STATUS	-->	AGENCY-PGM-CD*/
			  sb.append(TDCollectionUtil.getLglStatus(account.getAgencyPgmCd()));
			  
			  /*REC-INT-MTD-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-PRIN-MTD-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-TOT-COST-MTD*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*JGMT-EFFECT-DATE*/
			  sb.append(ZEROS_8);
			  
			  /*JGMT-INT-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*JGMT-PRIN-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*JGMT-TOT-COST-AMT*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*ADDL-JGMTS-IND*/
			  sb.append(SPACES_1);
			  
			  
			  /*REC-CODST-IN-BAL*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-NOT-BAL*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-ASSES-MTD*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-WAIVE-MTD*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAPT-LTD*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-1*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-1*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-2*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-2*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-3*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-3*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-4*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-4*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-5*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-5*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-6*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-6*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-7*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-7*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-8*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-8*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-9*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-9*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-10*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-10*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-11*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-11*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-12*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-12*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-13*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-13*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-14*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-14*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-15*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-15*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-16*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-16*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-17*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-17*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-18*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-18*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-19*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-19*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*REC-COST-CAT-20*/
			  sb.append(SPACES_3);
			  
			  /*REC-COST-AMT-20*/
			  sb.append(PLUS_ZEROS_18);
			  
			  /*LOAN-POOL-NUM*/
			  sb.append(SPACES_8);
			  
			  /*LOAN-POOL-SLD-DATE*/
			  sb.append(ZEROS_8);
			  
			  /*ORIG-PORTF-TYPE*/
			  sb.append(SPACES_1);
			  
			  /*DAYS-SNC-CHRGOFF*/
			  sb.append(ZEROS_8);
			  
			  /*LAST-EXT-UPD-DATE*/
			  sb.append(ZEROS_8);
			  
			  /*LEGAL-STATE*/
			  sb.append(SPACES_3);
			  
			  /*CHRG-OFF-DOWN-IND*/
			  sb.append(SPACES_1);
			  
			  /*INVESTOR-NUM*/
			  sb.append(ZEROS_8);
			  
			  /*EXT-RAND-NUM*/
			  sb.append(ZEROS_3);
			  
			  /*CMPL-CODE*/
			  sb.append(SPACES_4);
			  
			  /*CMPL-TIME-ZONE*/
			  sb.append(SPACES_5);
			  
			  /*PRODUCT-TYPE*/
			  sb.append(SPACES_8);
			  
			  /*NEXT-BILL-DATE*/
			  sb.append(ZEROS_8);

			  
			  bw.write( sb.toString());
			  bw.newLine();
		  }
		  
		 
	          //System.out.println("File written Successfully");

	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
		finally
		{ 
		   try{
		      if(bw!=null)
			 bw.close();
		   }catch(Exception ex){
		      System.out.println("Error in closing the BufferedWriter"+ex);
		    }
		}

	}
	
	public void generateContactDetailTransactions(List<Account> accountList){
		String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact.txt";
		
		BufferedWriter bw = null;
	      try {
		 
	         //Specify the file name and path here
		 File file = new File(filePath);

		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file);
		  bw = new BufferedWriter(fw);
		  
		  
			  for(Account account: accountList){
				  for(Customer customer: account.getCustomerList()){
					  StringBuffer sb = new StringBuffer();
					  
					  /*CONTACT-ID*/
					 // sb.append(TDCollectionUtil.getContactId(customer.getCdmCustNum()));
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCdmCustNum(), 24));
					  
					  /*CONTACT-RECORD-TYPE */
					  sb.append("1");
					  
					  /*EXTERNAL-SYSTEM-ID*/
					  sb.append("EDPP");
					  
					  /*FILLER*/
					  sb.append(SPACES_31);
					  
					  /*ACTION-CODE*/
					  sb.append("C");
					  
					  /*NAME*/
					  //sb.append(TDCollectionUtil.getName(customer.getName()));
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getName(), 45));
					  
					  /*PREFIX*/
					  sb.append(SPACES_4);
					  
					  /*FIRST-NAME*/
					  sb.append(SPACES_25);
					  
					  /*MIDDLE-NAME*/
					  sb.append(SPACES_30);
					  
					  /*LAST-NAME*/
					  sb.append(SPACES_35);
					  
					  /*SUFFIX*/
					  sb.append(SPACES_4);
					  
					  /*MATERNAL-NAME*/
					  sb.append(SPACES_35);
					  
					  /*NATIONAL-IDENTIFICATION-NUMBER*/
					  sb.append(SPACES_30);
					  
					  /*NATIONAL-IDENTIFICATION-NUMBER-TYPE*/
					  sb.append(SPACES_1);
					  
					  /*DATE-OF-BIRTH*/
					  sb.append("00000000");
					  
					  /*RIGHT-TO-PRIVACY*/
					  sb.append("Y");
					  
					  /*ISO-LANGUAGE-CODE*/
					  sb.append("en");
					  
					  /*ADDRESS-DISPLAY-FORMAT*/
					  sb.append("03");
					  
					  /*NOTES*/
					  sb.append(SPACES_34);
					  
					  /*EMPLOYER*/
					  sb.append(SPACES_45);
					  
					  /*CUSTOMER-SINCE-DATE*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-STATUS-1*/
					  //sb.append(TDCollectionUtil.getProdType(account.getProdType()));
					  //sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(TDCollectionUtil.getProdType(account.getProdType()), 8));
					  sb.append(SPACES_8);
					  
					  /*USER-DEFINED-STATUS-2*/
					  sb.append(SPACES_8);
					  
					  /*USER-DEFINED-STATUS-3*/
					  sb.append(SPACES_8);
					  
					  /*USER-DEFINED-STATUS-4*/
					  sb.append(SPACES_8);
					  
					  /*USER-DEFINED-STATUS-5*/
					  sb.append(SPACES_8);
					  
					  /*USER-DEFINED-DATE-1*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-DATE-2*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-DATE-3*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-DATE-4*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-DATE-5*/
					  sb.append("00000000");
					  
					  /*USER-DEFINED-TEXT-1*/
					  //sb.append(SPACES_40);
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCustBasicInfoInd(), 40));
					  
					  /*USER-DEFINED-TEXT-2*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCreationUserId(), 40));
					  
					  /*USER-DEFINED-TEXT-3*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getLastModUserId(), 40));
					  
					  /*USER-DEFINED-TEXT-4*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getLastModTimeStamp(), 40));
					  
					  /*USER-DEFINED-TEXT-5*/
					  sb.append(SPACES_40);
					  
					  /*DEMOG-STATUS-IND*/
					  sb.append("Y");
					  
					  /*SEARCH-NAME*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getName(), 50));
					  
					  /*USER-DEFINED-SEARCH-1*/
					  sb.append(SPACES_20);
					  
					  /*USER-DEFINED-SEARCH-2*/
					  sb.append(SPACES_20);
					  
					  /*DATE-LAST-VERIFIED*/
					  sb.append("00000000");
					  
					  /*CHANGE-DATE*/
					  sb.append("00000000");
					  
					  /*CHANGE-INDICATOR*/
					  sb.append(SPACES_1);
					  
					  bw.write( sb.toString());
					  bw.newLine();
				  }
			  }
			  
			  
			  //System.out.println("File written Successfully");
		  
	      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
	}
	
	
	public void generateContactAddressTransactions(List<Account> accountList){
		String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact.txt";
		
		BufferedWriter bw = null;
	      try {
		 
	         //Specify the file name and path here
		 File file = new File(filePath);

		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file, true);
		  bw = new BufferedWriter(fw);
		  
			  for(Account account: accountList){
				  for(Customer customer: account.getCustomerList()){
					  StringBuffer sb = new StringBuffer();
					  
					  /*CONTACT-ID*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCdmCustNum(), 24));
					  
					  /*CONTACT-RECORD-TYPE*/
					  sb.append("2");
					  
					  /*EXTERNAL-KEY*/
					  //String externalKey = customer.getCdmCustNum() + "ADR" + customer.getRelationType();
					  String externalKey = customer.getCdmCustNum() + "ADR1";
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(externalKey, 35));
					  
					  /*ACTION-CODE*/
					  sb.append("C");
					  
					  /*NICKNAME*/
					  String nickName = "Home Address";
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(nickName, 45));
					  
					  /*ADDRESS-RELATIONSHIP*/
					  sb.append("1");
					  
					  /*ADDRESS-STATUS*/
					  sb.append("C");
					  
					  /*SEASONAL-ADDRESS-FROM*/
					  sb.append("00000000");
					  
					  /*SEASONAL-ADDRESS-TO*/
					  sb.append("00000000");
					  
					  /*ADDRESS-BLOCK-IND*/
					  sb.append("N");
					  
					  /*PREFERRED-ADDRESS-INDICATOR*/
					  sb.append("1");
					  
					  /*CREDIT-BUREAU-ADDRESS*/
					  sb.append("Y");
					  
					  /*CREDIT-BUREAU-ADDRESS-INDICATOR-DATE-UPDATED*/
					  sb.append("00000000");
					  
					  /*ADDRESS-LINE-1*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getStreet(), 50));
					  
					  /*ADDRESS-LINE-2*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCity(), 50));
					  
					  /*ADDRESS-LINE-3*/
					  sb.append(SPACES_50);
					  
					  /*CITY*/
					  sb.append(SPACES_30);
					  
					  /*STATE-PROVINCE*/
					  sb.append(customer.getProvince());
					  
					  /*POSTAL-CODE*/
					  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getPostCd(), 30));
					  
					  /*COUNTY*/
					  sb.append(SPACES_35);
					  
					  /*COUNTRY*/
					  sb.append("CA");
					  
					  /*CHANGE-DATE*/
					  sb.append("00000000");
					  
					  /*CHANGE-INDICATOR*/
					  sb.append(SPACES_1);
					  
					  bw.write( sb.toString());
					  bw.newLine();
				  }
			  }
			  //System.out.println("File written Successfully");
		  
	      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
	}
	
	
	public void generateContactPhoneTransactions(List<Account> accountList){
		String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact.txt";
		
		BufferedWriter bw = null;
	      try {
		 
	         //Specify the file name and path here
		 File file = new File(filePath);

		 /* This logic will make sure that the file 
		  * gets created if it is not present at the
		  * specified location*/
		  if (!file.exists()) {
		     file.createNewFile();
		  }

		  FileWriter fw = new FileWriter(file, true);
		  bw = new BufferedWriter(fw);
		  
		  StringBuffer sb = null;
		  
			  for(Account account: accountList){
				  for(Customer customer: account.getCustomerList()){
					  
					  
					  /*System.out.println("customer.getHomePhoneNum(): " + customer.getHomePhoneNum());
					  System.out.println("customer.getBusPhoneNum(): " + customer.getBusPhoneNum());
					  System.out.println("customer.getAltPhoneNum(): " + customer.getAltPhoneNum());*/
					 
					  boolean isHomePhoneNumZero = false;
					  boolean isBusPhoneNumZero = false;
					  boolean isAltPhoneNumZero = false;
					  
					  if (customer.getHomePhoneNum().trim().equalsIgnoreCase("0"))
					  {
						  isHomePhoneNumZero = true;
					  }
					  
					  if (customer.getBusPhoneNum().trim().equalsIgnoreCase("0"))
					  {
						  isBusPhoneNumZero = true;
					  }
					  
					  if (customer.getAltPhoneNum().trim().equalsIgnoreCase("0"))
					  {
						  isAltPhoneNumZero = true;
					  }
					  
					  if(!isHomePhoneNumZero){
						  sb = new StringBuffer();
					  
						  /*CONTACT-ID*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCdmCustNum(), 24));
						  
						  /*CONTACT-RECORD-TYPE*/
						  sb.append("3");
						  
						  /*EXTERNAL-KEY*/
						  String externalKey = customer.getCdmCustNum() + "PHN" + "P" + "L";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(externalKey, 35));
						  
						  /*ACTION-CODE*/
						  sb.append("C");
						  
						  /*NICKNAME*/
						  String nickName = "Home Phone";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(nickName, 45));
						  
						  /*Home Phone*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getHomePhoneNum(), 20));
						  
						  /*EXTENSION*/
						  //sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getBusPhoneExt(), 7));
						  sb.append(SPACES_7);
						  
						  /*PHONE-TYPE*/
						  sb.append("P");
						  
						  /*PHONE-FORMAT*/
						  sb.append("01");
						  
						  /*PHONE-AVAILABILITY*/
						  sb.append("V");
						  
						  /*PREFERRED-PHONE-NUMBER IND*/
						  sb.append("1");
						  
						  /*SERVICE-TYPE*/
						  sb.append("L");
	
						  /*CHANGE-DATE*/
						  sb.append("00000000");
						  
						  /*CHANGE-INDICATOR*/
						  sb.append(SPACES_1);
						  
						  /*DO-NOT-CONTACT*/
						  sb.append("N");
	
						  
						  bw.write( sb.toString());
						  bw.newLine();
					  }
					  
					  if(!isBusPhoneNumZero){
						  sb = new StringBuffer();
						  
						  /*CONTACT-ID*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCdmCustNum(), 24));
						  
						  /*CONTACT-RECORD-TYPE*/
						  sb.append("3");
						  
						  /*EXTERNAL-KEY*/
						  String externalKey = customer.getCdmCustNum() + "PHN" + "B" + "C";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(externalKey, 35));
						  
						  /*ACTION-CODE*/
						  sb.append("C");
						  
						  /*NICKNAME*/
						  String nickName = "Business Phone";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(nickName, 45));
						  
						  /*Home Phone*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getBusPhoneNum(), 20));
						  
						  /*EXTENSION*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getBusPhoneExt(), 7));
						  //sb.append(SPACES_7);
						  
						  /*PHONE-TYPE*/
						  sb.append("B");
						  
						  /*PHONE-FORMAT*/
						  sb.append("01");
						  
						  /*PHONE-AVAILABILITY*/
						  sb.append("V");
						  
						  /*PREFERRED-PHONE-NUMBER IND*/
						  sb.append("0");
						  
						  /*SERVICE-TYPE*/
						  sb.append("C");
	
						  /*CHANGE-DATE*/
						  sb.append("00000000");
						  
						  /*CHANGE-INDICATOR*/
						  sb.append(SPACES_1);
						  
						  /*DO-NOT-CONTACT*/
						  sb.append("N");
	
						  
						  bw.write( sb.toString());
						  bw.newLine();
						  
					  }
					  
					  
					  if(!isAltPhoneNumZero){
						  sb = new StringBuffer();
						  
						  /*CONTACT-ID*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getCdmCustNum(), 24));
						  
						  /*CONTACT-RECORD-TYPE*/
						  sb.append("3");
						  
						  /*EXTERNAL-KEY*/
						  String externalKey = customer.getCdmCustNum() + "PHN" + "A" + "C";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(externalKey, 35));
						  
						  /*ACTION-CODE*/
						  sb.append("C");
						  
						  /*NICKNAME*/
						  String nickName = "Alternate Phone";
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(nickName, 45));
						  
						  /*Home Phone*/
						  sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getAltPhoneNum(), 20));
						  
						  /*EXTENSION*/
						  //sb.append(TDCollectionUtil.getFieldAfterPatchingSpacesOnRight(customer.getBusPhoneExt(), 7));
						  sb.append(SPACES_7);
						  
						  /*PHONE-TYPE*/
						  sb.append("A");
						  
						  /*PHONE-FORMAT*/
						  sb.append("01");
						  
						  /*PHONE-AVAILABILITY*/
						  sb.append("V");
						  
						  /*PREFERRED-PHONE-NUMBER IND*/
						  sb.append("0");
						  
						  /*SERVICE-TYPE*/
						  sb.append("C");
	
						  /*CHANGE-DATE*/
						  sb.append("00000000");
						  
						  /*CHANGE-INDICATOR*/
						  sb.append(SPACES_1);
						  
						  /*DO-NOT-CONTACT*/
						  sb.append("N");
	
						  
						  bw.write( sb.toString());
						  bw.newLine();
					  }
					  
					  
					  
				  }
			  }
			  //System.out.println("File written Successfully");
		  
	      } catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally
			{ 
			   try{
			      if(bw!=null)
				 bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
	}
	
	
	
	
	
	
	public static void main (String args[]) {
		
		
		TDCollection360FileProcessor tDCollection360FileProcessor = new TDCollection360FileProcessor();
		System.out.println(args[0]);
		System.out.println(args[1]);
		tDCollection360FileProcessor.ACCT_INPUT_FILE=args[0];
		tDCollection360FileProcessor.CUST_INPUT_FILE=args[1];
		List<Account> accountList = tDCollection360FileProcessor.readTDAccountData();
		List<Customer> customerList = tDCollection360FileProcessor.readTDCustomerData();
		
		/*System.out.println("accountList size: "+ accountList.size());
		System.out.println("customerList size: "+ customerList.size());*/
		
		for (Account acct : accountList) {
			List<Customer> commonAcctCustList = new ArrayList<Customer>();
			for (Customer customer : customerList) {
		    	if(acct.getNrtRefNum().equalsIgnoreCase(customer.getNrtRefNum())){
		    		commonAcctCustList.add(customer);
		    	}
			}
			acct.setCustomerList(commonAcctCustList);
			//System.out.println("For this account: "+ acct.getAccountNum() + " Associated customers size: " + acct.getCustomerList().size());
		}
	
		
		tDCollection360FileProcessor.generateFinancialExtract(accountList);
		
		
		tDCollection360FileProcessor.generateContactAccount(accountList);
		tDCollection360FileProcessor.generateAtocTranAccount(accountList);
		
		tDCollection360FileProcessor.generateContactDetailTransactions(accountList);
		tDCollection360FileProcessor.generateContactAddressTransactions(accountList);
		tDCollection360FileProcessor.generateContactPhoneTransactions(accountList);
		
		//System.out.println("TDCollection360FileProcessor");
	}
	
	public void generateContactAccount(List<Account> accountList) {
	     String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//contact-account.txt";
			
			BufferedWriter bw = null;
		      try {
			
		         //Specify the file name and path here
			 File file = new File(filePath);

			 /* This logic will make sure that the file 
			  * gets created if it is not present at the
			  * specified location*/
			  if (!file.exists()) {
			     file.createNewFile();
			  }

			  FileWriter fw = new FileWriter(file);
			  bw = new BufferedWriter(fw);
			 for(Account account: accountList) {
				List<Customer> customerList = account.getCustomerList();
				 for(Customer customer: customerList) {
					 StringBuffer sb = new StringBuffer();
					  sb.append(TDCollectionUtil.getBanknum());
					  sb.append(TDCollectionUtil.getApplicationid());
					  sb.append(TDCollectionUtil.getLoannumber());
					  sb.append(TDCollectionUtil.getRefnumert());
					  sb.append(TDCollectionUtil.getFiller(130));
					  sb.append(TDCollectionUtil.getEffdatert());
					  sb.append(TDCollectionUtil.getTransactioncode());
					  sb.append(TDCollectionUtil.getBatitmnumert());
					  sb.append(TDCollectionUtil.getInpsrccodert());
					  sb.append(TDCollectionUtil.getTrncntert());
					  sb.append(TDCollectionUtil.getTrnmordtaflgert());
					  sb.append(TDCollectionUtil.getCifactnumcus());
					  sb.append(TDCollectionUtil.getCifactcodcus());
					  sb.append(TDCollectionUtil.getCifcbrrptindcus());
					  sb.append(TDCollectionUtil.getCifcsminfindcus());
					  sb.append(TDCollectionUtil.getDatbkyreccus());
					  sb.append(TDCollectionUtil.getCifactcoddescus());
					  sb.append(TDCollectionUtil.getCifrefnumber());
					  sb.append(TDCollectionUtil.getLascsminfindcus());
					  sb.append(TDCollectionUtil.getEcoacodecus());
					  sb.append(TDCollectionUtil.getLastecoacodecus());
					  sb.append(TDCollectionUtil.getLastbureaurecordeddatecus());
					  sb.append(TDCollectionUtil.getFinalreportindicatorcus());
					  sb.append(TDCollectionUtil.getLastreportedsegtypecus());
					  sb.append(TDCollectionUtil.getActioncode());
					  sb.append(customer.getCdmCustNum());
					  sb.append(TDCollectionUtil.getFiller(14));
					  sb.append(TDCollectionUtil.getProdType(account.getProdType()));
					  sb.append(TDCollectionUtil.getAccountNum(account.getAccountNum()));
					  sb.append(TDCollectionUtil.getNamerelationship(customer.getRelationType()));
					  sb.append(TDCollectionUtil.getLeadcontactind(customer.getRelationType()));
					  sb.append(TDCollectionUtil.getResponsibleparty(customer.getRelationType()));
					  sb.append(TDCollectionUtil.getCasaddressind());
					  sb.append(TDCollectionUtil.getExternalsystemid());
					  sb.append(account.getCurrencyCd());
					 bw.write(sb.toString());
					 bw.newLine();
				 }
			 }
				  
				  
		}catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally { 
			   try{
			      if(bw!=null)
				      bw.close();
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		}
	
	public void generateAtocTranAccount(List<Account> accountList) {
	     String filePath = "D://suresh Devaki//D Drive//Suresh//Devaki//POC for TD Collection 360//Collection 360 Files//atoctran.txt";
			
			BufferedWriter bw = null;
			boolean flag=true;
			String Header_Date="";
		      try {
			 
		         //Specify the file name and path here
			 File file = new File(filePath);

			  
			  if (!file.exists()) {
			     file.createNewFile();
			  }

			  FileWriter fw = new FileWriter(file);
			  bw = new BufferedWriter(fw);
			 for(Account account: accountList) {
				List<Customer> customerList = account.getCustomerList();
				Iterator<Customer> custitr=customerList.iterator();
				if(flag) {
					Header_Date=account.getValidationDate();
					flag=false;
				}
				if(custitr.hasNext()) {
					
					  Customer customer=custitr.next();
				  	  StringBuffer sb = new StringBuffer();
					  sb.append(TDCollectionUtil.getProdType(account.getProdType()));
					  sb.append(TDCollectionUtil.getAccountNum(account.getAccountNum()));
					  sb.append(TDCollectionUtil.getAtocTransactionCode());
					  sb.append(TDCollectionUtil.getFormattedDate(account.getValidationDate()));
					  sb.append(TDCollectionUtil.getPreviousCCI());
					  sb.append(TDCollectionUtil.getPortfolioLocCode());
					  sb.append(customer.getCdmCustNum()); // CUSTOMER-PORTFOLIO ID
					  sb.append(TDCollectionUtil.patchEmptySpaces(8));
					  sb.append(customer.getCdmCustNum()); // CUSTOMER-PORTFOLIO-CONTACT-ID
					  sb.append(TDCollectionUtil.patchEmptySpaces(14));
					  
					 bw.write(sb.toString());
					 bw.newLine();
				   }
				  				     
				 }
			 
			    populateAtoTran100(bw,Header_Date);
					  
		}catch (IOException ioe) {
			   ioe.printStackTrace();
			}
			finally { 
			   try {
			      if(bw!=null)
				      bw.close();
			   } catch(Exception ex) {
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		} 
	
	public void populateAtoTran100(BufferedWriter bw,String date) throws IOException {
		String [] loc_codes={"101112", "101122", "101132", "101142", "101152", "101162", "101172", "201112", "201122", "201132", "201142", "201152", "201162", "201172", "301112", "301122", "301132", "401132", "401142", "401152", "401162", "401172", "401182"};
		String [] portfolio_codes={"301102", "401102", "401112", "401122"};
		 for (int i=0;i<loc_codes.length;i++) {
			 StringBuffer sb=new StringBuffer();
		      sb.append(loc_codes[i]);
              sb.append(TDCollectionUtil.getFiller(18)); // Appending Spaces
              sb.append(TDCollectionUtil.getAtocTransactionCode100());
              sb.append(TDCollectionUtil.getFormattedDate(date));
              sb.append(TDCollectionUtil.getFormattedDay(date));
              sb.append(TDCollectionUtil.getFiller(18)); // Filling Up Spaces
              bw.write(sb.toString());
              bw.newLine();
		 }
		 
		 for (int j=0;j<portfolio_codes.length;j++) {
			 StringBuffer sb=new StringBuffer();
		      sb.append(portfolio_codes[j]);
             sb.append(TDCollectionUtil.getFiller(18)); // Appending Spaces
             sb.append(TDCollectionUtil.getAtocTransactionCode100());
             sb.append(TDCollectionUtil.getFormattedDate(date));
             sb.append(TDCollectionUtil.getPortfolioLocCode100());
             sb.append(TDCollectionUtil.getFiller(18));
             bw.write(sb.toString());
             bw.newLine();
		 }
	}
	

}

