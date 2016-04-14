package com.cgi.td.collection360.util;

public class TDCollectionUtil {
	
	
	/*p327ext - Fields START*/
	
	public static String getProdType(String prodType){
		//System.out.println("account.getProdType() in Util: " + prodType);
		if(prodType != null && prodType.trim().equalsIgnoreCase("LON")){
			prodType = "401132";
		}
		else{
			prodType = "401142";
		}
		return prodType;
	}
	
	
	public static String getAccountNum(String accountNum){
		String acctNum;
		 if(accountNum != null && accountNum.length() > 18) {
			 accountNum = accountNum.substring(0, accountNum.length()-2);
		 }
		 else if(accountNum.length()  != 18){
			 int spacesCount = 18-accountNum.length();
			 accountNum = accountNum + patchEmptySpaces(spacesCount);
		 }
		 //System.out.println("account.getAccountNum().length() in util: " + accountNum.length());
		return accountNum;
	}
	
	
	public static String getLineOfCredit(String lineOfCredit){
		return patchZerosForAmount(lineOfCredit, false, true, 12, 0 , 0);
	}
	
	public static String getExpirationDate(){
		return "00000000";
	}
	
	
	public static String getTotalOsAmt(String totalOsAmt){
		return patchZerosForAmount(totalOsAmt, true, true, 0, 12, 6);
	}
	
	public static String getOverlimitAmt(){
		return "+000000000000000000";
	}
	
	public static String getDisputedAmt(){
		return "+000000000000000000";
	}
	
	public static String getTotalDueAmt(String totalOsAmt){
		return patchZerosForAmount(totalOsAmt, true, true, 0, 12, 6);
	}
	
	public static String getCurrentDueAmt(String currAmtOverDue){
		return patchZerosForAmount(currAmtOverDue, true, true, 0, 12, 6);
	}
	
	public static String getCollAsgnUd1(){
		return patchEmptySpaces(12);
	}
	
	public static String getCollAsgnUdType1(){
		return patchEmptySpaces(2);
	}
	
	public static String getCollAsgnUdFmt1(){
		return patchEmptySpaces(2);
	}
	
	public static String getCollAsgnUd2(){
		return patchEmptySpaces(12);
	}
	
	public static String getCollAsgnUdType2(){
		return patchEmptySpaces(2);
	}
	
	public static String getCollAsgnUdFmt2(){
		return patchEmptySpaces(2);
	}
	
	public static String getTotalDelinqAmt(String currAmtOverDue){
		return patchZerosForAmount(currAmtOverDue, true, true, 0, 12, 6);
	}
	
	public static String getCyclesDelinqCnt(){
		return "+00";
	}
	
	public static String getDaysDelinqCnt(String currDaysDelinq){
		//return currDaysDelinq.substring(1);
		return patchZerosForAmount(currDaysDelinq, false, true, 3, 0 , 0);
	}
	
	public static String getLastPaymentAmt(String lastPymtAmt){
		return patchZerosForAmount(lastPymtAmt, true, true, 0, 12, 6);
	}
	
	public static String getChargeOffDate(String wrtoffDate){
		return getFormattedDate(wrtoffDate);
	}
	
	public static String getDlqDaysChrgOff(){
		return "+000";
	}
	
	public static String getLstPartCofAmt(){
		return "+000000000000000000";
	}
	
	public static String getLstPartCofDate(){
		return "+000000000000000000";
	}
	
	public static String getTotPartCofAmt(){
		return "+000000000000000000";
	}
	
	public static String getBalPartCofAmt(){
		return "+000000000000000000";
	}
	public static String getPartCofAcctNum(){
		//return "+000000000000000000";
		return patchEmptySpaces(30);
	}
	
	public static String getUserDefined7(String totalOsAmt){
		return patchZerosForAmount(totalOsAmt, true, false, 0, 10, 2);
	}
	
	public static String getUd7Type(){
		return "N ";
	}
	
	public static String getUd7Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getAcctOpenDate(String openDate){
		return getFormattedDate(openDate);
	}
	
	public static String getNextDueDate(){
		return "00000000";
	}
	
	public static String getInterestRate(){
		return "+000000";
	}
	
	public static String getOthDelinqAmt(String cashBalAmt){
		return patchZerosForAmount(cashBalAmt, true, true, 0, 12, 6);
	}
	
	public static String getRepoCharges(){
		return "+000000000000000000";
	}
	
	public static String getRepossessionDate(){
		return "00000000";
	}
	
	public static String getMaturityDate(){
		return "00000000";
	}
	
	public static String getPaymentAmt(){
		return "+000000000000000000";
	}
	
	public static String getPrincipalDelqAmt(String initAmtOverdue){
		return patchZerosForAmount(initAmtOverdue, true, true, 0, 12, 6);
	}
	
	public static String getInterestDelqAmt(){
		return "+000000000000000000";
	}
	
	public static String getUserDefSearch1(){
		return patchEmptySpaces(10);
	}
	
	public static String getUdSearch1Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUdSearch1Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefSearch2(){
		return patchEmptySpaces(10);
	}
	
	public static String getUdSearch2Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUdSearch2Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getAcctNumSort(){
		return patchEmptySpaces(7);
	}
	
	public static String getCycleId(){
		return "01";
	}
	
	public static String getUdDateField1(String nrtAssignDate){
		return getFormattedDate(nrtAssignDate);
	}
	
	public static String getLastBillingDate(){
		return "00000000";
	}
	
	public static String getAgedHistory(){
		return patchEmptySpaces(12);
	}
	
	public static String getCreditLimitDate(){
		return "00000000";
	}
	
	public static String getIntLateChgAmt(){
		return "+000000000000000000";
	}
	
	public static String getDelinqAmtAge1(){
		return "+000000000000000000";
	}
	
	public static String getDelinqAmtAge2(){
		return "+000000000000000000";
	}
	
	public static String getDelinqAmtAge3(){
		return "+000000000000000000";
	}
	
	public static String getDelinqAmtAge4(){
		return "+000000000000000000";
	}
	
	public static String getNumberOfCards(String numChequesRet){
		return numChequesRet.substring(1);
	}
	
	public static String getWarnBulletinDate(String currValueDate){
		return getFormattedDate(currValueDate);
	}
	
	public static String getWarnBulletinZone(String bankruptInd){
		return bankruptInd + patchEmptySpaces(3);
	}
	
	public static String getOtherAcctNum(){
		return patchEmptySpaces(18);
	}
	
	public static String getCreditScore(){
		return patchEmptySpaces(5);
	}
	
	public static String getBehaviorIndex(String behaviourScore){
		return behaviourScore + patchEmptySpaces(1);
	}
	
	public static String getLastPaymentDate(String lastDepositDt){
		return getFormattedDate(lastDepositDt);
	}
	
	public static String getLastMonetaryAmt(){
		return "+000000000000000000";
	}
	
	public static String getLastMonetaryDate(){
		return "00000000";
	}
	
	public static String getLastMonetaryType(String initInhibitInd){
		return initInhibitInd + patchEmptySpaces(1);
	}
	
	public static String getChargeOffAmt(String initWrtoffAmt){
		return patchZerosForAmount(initWrtoffAmt, true, true, 0, 12, 6);
	}
	
	public static String getSortValue1(){
		return "000000000000";
	}
	
	public static String getSortValue1Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getSortValue1Fmt(){
		return "00";
	}
	
	public static String getSortValue2(){
		return patchEmptySpaces(12);
	}
	
	public static String getSortValue2Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getSortValue2Fmt(){
		return "00";
	}
	
	public static String getUserDefined1(String prodSubType){
		return prodSubType + patchEmptySpaces(7);
	}
	
	public static String getUd1Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd1Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined2(String delinqHistory){
		return delinqHistory + patchEmptySpaces(6);
	}
	
	public static String getUd2Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd2Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined3(String marketingType){
		return marketingType + patchEmptySpaces(8);
	}
	
	public static String getUd3Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd3Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined4(){
		return patchEmptySpaces(79);
	}
	
	public static String getUd4Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd4Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined5(){
		return patchEmptySpaces(79);
	}
	
	public static String getUd5Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd5Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined6(String totalOsAmt){
		return "Total OS Amt";
	}
	
	public static String getUd6Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd6Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUserDefined81(){
		return patchEmptySpaces(79);
	}
	
	public static String getUserDefined82(){
		return patchEmptySpaces(79);
	}
	
	public static String getUserDefined83(){
		return patchEmptySpaces(79);
	}
	
	public static String getUserDefined84(){
		return patchEmptySpaces(79);
	}
	
	public static String getUserDefined85(){
		return patchEmptySpaces(79);
	}
	
	public static String getUd81Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd82Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd83Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd84Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd85Type(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd81Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd82Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd83Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd84Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getUd85Fmt(){
		return patchEmptySpaces(2);
	}
	
	public static String getPrincipalAmt(){
		return "+000000000000000000";
	}
	
	public static String getOtherAmt(String initLineOfCrdt){
		return patchZerosForAmount(initLineOfCrdt, true, true, 0, 12, 6);
	}
	
	public static String getOrigBalanceAmt(String initTotalOsAmt){
		return patchZerosForAmount(initTotalOsAmt, true, true, 0, 12, 6);
	}
	
	public static String getLastModUserId(String lastModUserId){
		return lastModUserId + patchEmptySpaces(9-lastModUserId.length());
	}
	
	public static String getBranchNum(String cifLinkBranch){
		return cifLinkBranch + patchEmptySpaces(6-cifLinkBranch.length());
	}
	
	public static String getOriginalTerm(String initDaysDelinq){
		return initDaysDelinq;
	}
	
	public static String getOrigInterestRate(){
		return "+000000";
	}
	
	public static String getDealerNum(String branchNum){
		return  patchEmptySpaces(7-branchNum.length()) + branchNum;
	}
	
	
	public static String getCutOffCode(String wrtoffReasonCd){
		return  wrtoffReasonCd.substring(0, wrtoffReasonCd.length()-3);
	}
	
	public static String getTerminateAmt(String nsfAmtDue){
		return patchZerosForAmount(nsfAmtDue, true, true, 0, 12, 6);
	}
	
	public static String getLglStatus(String agencyPgmCd){
		return  patchEmptySpaces(1) + agencyPgmCd;
	}
	
	
	
	/*p327ext - Fields END*/
	
	
	/*contactDetail - Fields START*/
	
	public static String getContactId(String cdmCustNum){
		return cdmCustNum + patchEmptySpaces(24 - cdmCustNum.length());
	}
	
	public static String getName(String name){
		/*System.out.println("name length in util: " + name.length() );
		System.out.println("name patchEmptySpaces(45 - name.length() in util: " + patchEmptySpaces(45 - name.length()).length());
		System.out.println("name length after patching in util: " + ((name + patchEmptySpaces(45 - name.length()))).length());*/
		return name + patchEmptySpaces(45 - name.length());
	}
	
	public static String getUserDefinedText2(String creationUserId){
		//System.out.println("creationUserId length after patching in util: " + (creationUserId + patchEmptySpaces(40 - creationUserId.length())).length() );
		return creationUserId + patchEmptySpaces(40 - creationUserId.length());
	}
	
	public static String getFieldAfterPatchingSpacesOnRight(String fileldStr, int fieldLength){
		//System.out.println("field length after patching in util: " + (fileldStr + patchEmptySpaces(fieldLength - fileldStr.length())).length() );
		return fileldStr + patchEmptySpaces(fieldLength - fileldStr.length());
	} 

	
	
	/*contactDetail - Fields END*/
	
	
	public static String patchEmptySpaces(int count){
		String emptyStr="";
		for(int i=0; i<count; i++){
			emptyStr = emptyStr + " ";
		 }
		return emptyStr;
	}
	
	public static String prepareAmount(String totalOsAmt){
		int position = totalOsAmt.indexOf('.', 0);
		
		//System.out.println("prepareAmount position in util: " + position );
		
		if(position != -1){
			String strBeforeDecimal = totalOsAmt.substring(0, position);
			String strAfterDecimal = totalOsAmt.substring(position+1);
			
			/*System.out.println("getTotalOsAmt - strBeforeDecimal in util: " + strBeforeDecimal );
			System.out.println("getTotalOsAmt - strAfterDecimal in util: " + strAfterDecimal );*/
			
			if(strBeforeDecimal != null && strBeforeDecimal.length() < 12) {
				int spacesCount = 12-strBeforeDecimal.length();
				strBeforeDecimal = "+9" + patchEmptySpaces(spacesCount) + strBeforeDecimal;
			 }
			 else {
				 strBeforeDecimal = "+9"  + strBeforeDecimal;
			 }
			
			if(strAfterDecimal != null && strAfterDecimal.length() < 6) {
				int spacesCount = 6-strAfterDecimal.length();
				strAfterDecimal =   strAfterDecimal + patchEmptySpaces(spacesCount);
			 }
			
			 //System.out.println("getTotalOsAmt in util: " + strBeforeDecimal + "." + strAfterDecimal);
			return strBeforeDecimal + "." + strAfterDecimal;
		}
		else{
			return patchEmptySpaces(19-totalOsAmt.length()) + totalOsAmt;
		}
	}
	
	
	public static String patchZerosForAmount(String amount, boolean withDecimal, boolean withPlusSymbol, int fieldLength, int fieldLengthBFDecimal, int fieldLengthAFDecimal){
		if(!withDecimal){
			amount = amount.trim();
			if(amount.length() < fieldLength){
				amount = patchZeros(fieldLength - amount.length()) + amount;
			}
			else{
				amount = amount.substring(amount.length()-fieldLength);
			}
			
			if(withPlusSymbol){
				amount = "+" + amount;
			}
			/*System.out.println("amount withot decimal in util: withDecimal withPlusSymbol" + amount + "\t" + withDecimal + "\t" + withPlusSymbol);
			System.out.println("amount.length() in util: " + amount.length());*/
			return amount;
		}
		else{
			
			int position = amount.indexOf('.', 0);
			
			if(position != -1){
				String strBeforeDecimal = amount.substring(0, position).trim();
				String strAfterDecimal = amount.substring(position+1).trim();
				
				/*System.out.println("amount - strBeforeDecimal in util: " + strBeforeDecimal );
				System.out.println("amount - strAfterDecimal in util: " + strAfterDecimal );*/
				
				if(strBeforeDecimal != null && strBeforeDecimal.length() < fieldLengthBFDecimal) {
					int zerosCount = fieldLengthBFDecimal-strBeforeDecimal.length();
					strBeforeDecimal = patchZeros(zerosCount) + strBeforeDecimal;
				 }
				 else if (strBeforeDecimal.length() > fieldLengthBFDecimal){
					 strBeforeDecimal = strBeforeDecimal.substring(strBeforeDecimal.length() - fieldLengthBFDecimal);
				 }
				
				if(withPlusSymbol){
					strBeforeDecimal = "+" + strBeforeDecimal;
				}
				
				if(strAfterDecimal != null && strAfterDecimal.length() < fieldLengthAFDecimal) {
					int zerosCount = fieldLengthAFDecimal - strAfterDecimal.length();
					strAfterDecimal =   strAfterDecimal + patchZeros(zerosCount);
				 }
				else if(strAfterDecimal.length() > fieldLengthAFDecimal){
					strAfterDecimal = strAfterDecimal.substring(strAfterDecimal.length() - fieldLengthAFDecimal);
				}
				
				 //System.out.println("amount with decimal in util: " + strBeforeDecimal + strAfterDecimal);
				return strBeforeDecimal + strAfterDecimal;
			}
			else{
				if(withPlusSymbol){
					return "+" + patchZeros(fieldLengthBFDecimal + fieldLengthAFDecimal - amount.trim().length()) + amount.trim();
				}
				else{
					return patchZeros(fieldLengthBFDecimal + fieldLengthAFDecimal - amount.trim().length()) + amount.trim();
				}
				
			}
		}
	}
	
	public static String patchZeros(int count){
		String zerosStr="";
		for(int i=0; i<count; i++){
			zerosStr = zerosStr + "0";
		 }
		return zerosStr;
	}
	
	public static String getFormattedDate(String wrtoffDate){
		String date = null;
		int firstPosition = wrtoffDate.indexOf('-', 0);
		int lastPosition = wrtoffDate.lastIndexOf('-');
		date = wrtoffDate.substring(0, firstPosition) + wrtoffDate.substring(firstPosition+1, lastPosition) + wrtoffDate.substring(lastPosition+1);
		if(date.equalsIgnoreCase("00010101")){
			date = "00000000";
		}
		//System.out.println("date: ************* " + date);
		return date;
	}
	
	
	// Rajanikanth added code logic
	

		public static String getBanknum() {
			return "00000";
		}

		public static String getApplicationid() {
			return "000";
		}

		public static String getLoannumber() {
			return patchEmptySpaces(18);
		}

		public static String getRefnumert() {
			return patchEmptySpaces(3);
		}

		public static String getFiller(int val) {
			return patchEmptySpaces(val);
		}

		public static String getEffdatert() {
			return patchEmptySpaces(10);
		}

		public static String getTransactioncode() {
			return "95005";

		}

		public static String getBatitmnumert() {
			return patchEmptySpaces(9);
		}

		public static String getInpsrccodert() {
			return patchEmptySpaces(3);
		}

		public static String getTrncntert() {
			return patchEmptySpaces(3);
		}

		public static String getTrnmordtaflgert() {
			return patchEmptySpaces(1);
		}

		public static String getCifactnumcus() {
			return patchEmptySpaces(24);
		}

		public static String getCifactcodcus() {
			return patchEmptySpaces(1);
		}

		public static String getCifcbrrptindcus() {
			return patchEmptySpaces(1);
		}

		public static String getCifcsminfindcus() {
			return patchEmptySpaces(2);
		}

		public static String getDatbkyreccus() {
			return patchEmptySpaces(10);
		}

		public static String getCifactcoddescus() {
			return patchEmptySpaces(18);
		}

		public static String getCifrefnumber() {
			return patchEmptySpaces(3);
		}

		public static String getLascsminfindcus() {
			return patchEmptySpaces(2);
		}

		public static String getEcoacodecus() {
			return patchEmptySpaces(1);
		}

		public static String getLastecoacodecus() {
			return patchEmptySpaces(1);
		}

		public static String getLastbureaurecordeddatecus() {
			return patchEmptySpaces(10);
		}

		public static String getFinalreportindicatorcus() {
			return patchEmptySpaces(1);
			//return "Y";
		}

		public static String getLastreportedsegtypecus() {
			return patchEmptySpaces(2);
		}
		
		public static String getActioncode() {
			return "C";
		}

		public static String getNamerelationship(String RelationType) {
			return getRelationType(RelationType);
		}


		public static String getLeadcontactind(String ContactInd) {
			return getRelationTypeNum(ContactInd);
		}


		public static String getResponsibleparty(String Party) {
			return getRelationTypeNum(Party);
		}

		
		public static String getCasaddressind() {
			return "N";
		}

		public static String getExternalsystemid() {
			return "EDPP";
		}
		
		public static String getRelationType(String relationType) {
			  if (relationType!=null && (relationType.equals("019") ||relationType.equals("000"))){
				   relationType="A";
			  } else if(relationType.equals("020")){
				  relationType="B";
			  } else  relationType="D";
			  
			  return relationType;
		}
		
		public static String getRelationTypeNum(String relationType) {
			  if (relationType!=null && (relationType.equals("019") ||relationType.equals("000"))){
				   relationType="1";
			  } else if(relationType.equals("020")){
				  relationType="0";
			  } else  relationType="0";
			  
			  return relationType;
		}
		
		public static String getAtocTransactionCode() {
			return "200";
		}
		
		public static String getAtocTransactionCode100() {
			return "100";
		}
		public static String getPreviousCCI() {
			return "0";
		}
		
		public static String getPortfolioLocCode() {
			return "401112";
		}
		
		public static String getPortfolioLocCode100() {
			return "00";
		}
		
		public static String getFormattedDay(String wrtoffDate){
			String date = null;
			int lastPosition = wrtoffDate.lastIndexOf('-');
			date = wrtoffDate.substring(lastPosition+1);
			return date;
		}
	
	/*Rjani END*/
	
}
