package org.apache.camel.examples;

import java.io.Serializable;

public class AccountHolder implements Serializable {

  //added following for Machine Learning options
  private String treatmentOutcome;

  //added the following values for the fuse process
  private String errorMessage;

  //These getters and members added by me to cover the rules -- goes down to next comment
  public String getCallList() {
    return callList;
  }

  public void setCallList(String callList) {
    this.callList = callList;
  }

  public String getMismatch() {
    return mismatch;
  }

  public void setMismatch(String mismatch) {
    this.mismatch = mismatch;
  }

  public String getCurrentStage() {
    return currentStage;
  }

  public void setCurrentStage(String currentStage) {
    this.currentStage = currentStage;
  }

  public String getProb() {
    return prob;
  }

  public void setProb(String prob) {
    this.prob = prob;
  }

  public Double getBAR() {
    return BAR;
  }

  public void setBAR(Double bAR) {
    BAR = bAR;
  }

  public Double getRisk_score() {
    return risk_score;
  }

  public void setRisk_score(Double risk_score) {
    this.risk_score = risk_score;
  }

  //added for rules processing
  private String callList;
  private String mismatch;
  private String currentStage;
  private String prob;
  private Double BAR;
  private Double risk_score;
  private Double skip_ID;
  // End of custom process getters and setters and class data members not part of original csv file

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public String getAct_DT_1() {
    return Act_DT_1;
  }

  public void setAct_DT_1(String act_DT_1) {
    Act_DT_1 = act_DT_1;
  }

  public String getAct_DT_2() {
    return Act_DT_2;
  }

  public void setAct_DT_2(String act_DT_2) {
    Act_DT_2 = act_DT_2;
  }

  public String getAct_DT_3() {
    return Act_DT_3;
  }

  public void setAct_DT_3(String act_DT_3) {
    Act_DT_3 = act_DT_3;
  }

  public String getIs_Active() {
    return Is_Active;
  }

  public void setIs_Active(String is_Active) {
    Is_Active = is_Active;
  }

  public String getGross_Due_Amount() {
    return Gross_Due_Amount;
  }

  public void setGross_Due_Amount(String gross_Due_Amount) {
    Gross_Due_Amount = gross_Due_Amount;
  }

  public Double getBALANCE() {
    return BALANCE;
  }

  public void setBALANCE(Double bALANCE) {
    BALANCE = bALANCE;
  }

  public String getRegion() {
    return Region;
  }

  public void setRegion(String region) {
    Region = region;
  }

  public String getBflag() {
    return Bflag;
  }

  public void setBflag(String bflag) {
    Bflag = bflag;
  }

  public String getBflag_Processed() {
    return Bflag_Processed;
  }

  public void setBflag_Processed(String bflag_Processed) {
    Bflag_Processed = bflag_Processed;
  }

  public String getBat_DT() {
    return Bat_DT;
  }

  public void setBat_DT(String bat_DT) {
    Bat_DT = bat_DT;
  }

  public String getPosting_Status() {
    return Posting_Status;
  }

  public void setPosting_Status(String posting_Status) {
    Posting_Status = posting_Status;
  }

  public String getProcessingDate() {
    return ProcessingDate;
  }

  public void setProcessingDate(String ProcessingDate) {
    this.ProcessingDate = ProcessingDate;
  }

  public String getClosed_Date() {
    return Closed_Date;
  }

  public void setClosed_Date(String closed_Date) {
    Closed_Date = closed_Date;
  }

  public String getGrade() {
    return Grade;
  }

  public void setGrade(String grade) {
    Grade = grade;
  }

  public String getRegion_Code() {
    return Region_Code;
  }

  public void setRegion_Code(String region_Code) {
    Region_Code = region_Code;
  }

  public String getRate() {
    return Rate;
  }

  public void setRate(String rate) {
    Rate = rate;
  }

  public String getDcode() {
    return Dcode;
  }

  public void setDcode(String dcode) {
    Dcode = dcode;
  }

  public String getDisc_Rate() {
    return Disc_Rate;
  }

  public void setDisc_Rate(String disc_Rate) {
    Disc_Rate = disc_Rate;
  }

  public String getEVR60_MOB() {
    return EVR60_MOB;
  }

  public void setEVR60_MOB(String EVR60_MOB) {
    this.EVR60_MOB = EVR60_MOB;
  }

  public String getEXTENSION_MOB() {
    return EXTENSION_MOB;
  }

  public void setEXTENSION_MOB(String EXTENSION_MOB) {
    this.EXTENSION_MOB = EXTENSION_MOB;
  }

  public String getTerm_Count() {
    return Term_Count;
  }

  public void setTerm_Count(String term_Count) {
    Term_Count = term_Count;
  }

  public String getScore() {
    return Score;
  }

  public void setScore(String score) {
    Score = score;
  }

  public String getEphone() {
    return Ephone;
  }

  public void setEphone(String ephone) {
    Ephone = ephone;
  }

  public String getInsert_DT() {
    return Insert_DT;
  }

  public void setInsert_DT(String insert_DT) {
    Insert_DT = insert_DT;
  }

  public String getCount30() {
    return Count30;
  }

  public void setCount30(String count30) {
    Count30 = count30;
  }

  public String getCount60() {
    return Count60;
  }

  public void setCount60(String count60) {
    Count60 = count60;
  }

  public String getCount90() {
    return Count90;
  }

  public void setCount90(String count90) {
    Count90 = count90;
  }

  public String getHOME_IS_MOBILE() {
    return HOME_IS_MOBILE;
  }

  public void setHOME_IS_MOBILE(String hOME_IS_MOBILE) {
    HOME_IS_MOBILE = hOME_IS_MOBILE;
  }

  public String getHNFlag() {
    return HNFlag;
  }

  public void setHNFlag(String hNFlag) {
    HNFlag = hNFlag;
  }

  public String getHphone() {
    return Hphone;
  }

  public void setHphone(String hphone) {
    Hphone = hphone;
  }

  public String getHOME_PHONE_OWNERSHIP() {
    return HOME_PHONE_OWNERSHIP;
  }

  public void setHOME_PHONE_OWNERSHIP(String hOME_PHONE_OWNERSHIP) {
    HOME_PHONE_OWNERSHIP = hOME_PHONE_OWNERSHIP;
  }

  public String getLANGUAGE_PREFERENCE() {
    return LANGUAGE_PREFERENCE;
  }

  public void setLANGUAGE_PREFERENCE(String lANGUAGE_PREFERENCE) {
    LANGUAGE_PREFERENCE = lANGUAGE_PREFERENCE;
  }

  public String getEntry_DT() {
    return Entry_DT;
  }

  public void setEntry_DT(String entry_DT) {
    Entry_DT = entry_DT;
  }

  public String getExt_DT() {
    return Ext_DT;
  }

  public void setExt_DT(String ext_DT) {
    Ext_DT = ext_DT;
  }

  public String getLC_DT() {
    return LC_DT;
  }

  public void setLC_DT(String lC_DT) {
    LC_DT = lC_DT;
  }

  public String getLC1() {
    return LC1;
  }

  public void setLC1(String lC1) {
    LC1 = lC1;
  }

  public String getLIST_1() {
    return LIST_1;
  }

  public void setLIST_1(String lIST_1) {
    LIST_1 = lIST_1;
  }

  public String getList_Region_1() {
    return List_Region_1;
  }

  public void setList_Region_1(String list_Region_1) {
    List_Region_1 = list_Region_1;
  }

  public String getLIST_STATUS_1() {
    return LIST_STATUS_1;
  }

  public void setLIST_STATUS_1(String lIST_STATUS_1) {
    LIST_STATUS_1 = lIST_STATUS_1;
  }

  public String getMATURITY_DATE() {
    return MATURITY_DATE;
  }

  public void setMATURITY_DATE(String mATURITY_DATE) {
    MATURITY_DATE = mATURITY_DATE;
  }

  public String getMflag() {
    return Mflag;
  }

  public void setMflag(String mflag) {
    Mflag = mflag;
  }

  public String getMNFlag() {
    return MNFlag;
  }

  public void setMNFlag(String mNFlag) {
    MNFlag = mNFlag;
  }

  public String getMOB() {
    return MOB;
  }

  public void setMOB(String MOB) {
    this.MOB = MOB;
  }

  public String getMOBILE_PHONE() {
    return MOBILE_PHONE;
  }

  public void setMOBILE_PHONE(String mOBILE_PHONE) {
    MOBILE_PHONE = mOBILE_PHONE;
  }

  public String getMOBILE_PHONE_OWNERSHIP() {
    return MOBILE_PHONE_OWNERSHIP;
  }

  public void setMOBILE_PHONE_OWNERSHIP(String mOBILE_PHONE_OWNERSHIP) {
    MOBILE_PHONE_OWNERSHIP = mOBILE_PHONE_OWNERSHIP;
  }

  public String getMOB_TERM_RATIO() {
    return MOB_TERM_RATIO;
  }

  public void setMOB_TERM_RATIO(String MOB_TERM_RATIO) {
    this.MOB_TERM_RATIO = MOB_TERM_RATIO;
  }

  public String getMONTHS_REMAINING() {
    return MONTHS_REMAINING;
  }

  public void setMONTHS_REMAINING(String MONTHS_REMAINING) {
    this.MONTHS_REMAINING = MONTHS_REMAINING;
  }

  public String getMTR2() {
    return MTR2;
  }

  public void setMTR2(String MTR2) {
    this.MTR2 = MTR2;
  }

  public String getMTR_GT_08() {
    return MTR_GT_08;
  }

  public void setMTR_GT_08(String MTR_GT_08) {
    this.MTR_GT_08 = MTR_GT_08;
  }

  public String getPayment_Count() {
    return Payment_Count;
  }

  public void setPayment_Count(String payment_Count) {
    Payment_Count = payment_Count;
  }

  public String getPayment_Due_Count() {
    return Payment_Due_Count;
  }

  public void setPayment_Due_Count(String payment_Due_Count) {
    Payment_Due_Count = payment_Due_Count;
  }

  public String getNext_DT() {
    return Next_DT;
  }

  public void setNext_DT(String next_DT) {
    Next_DT = next_DT;
  }

  public String getPayment_Paid_Count() {
    return Payment_Paid_Count;
  }

  public void setPayment_Paid_Count(String payment_Paid_Count) {
    Payment_Paid_Count = payment_Paid_Count;
  }

  public String getPayment_Left_Count() {
    return Payment_Left_Count;
  }

  public void setPayment_Left_Count(String payment_Left_Count) {
    Payment_Left_Count = payment_Left_Count;
  }

  public String getUA4() {
    return UA4;
  }

  public void setUA4(String uA4) {
    UA4 = uA4;
  }

  public String getUA7() {
    return UA7;
  }

  public void setUA7(String uA7) {
    UA7 = uA7;
  }

  public String getOind() {
    return Oind;
  }

  public void setOind(String oind) {
    Oind = oind;
  }

  public String getU_Amount_Due() {
    return U_Amount_Due;
  }

  public void setU_Amount_Due(String u_Amount_Due) {
    U_Amount_Due = u_Amount_Due;
  }

  public String getOFlag() {
    return OFlag;
  }

  public void setOFlag(String oFlag) {
    OFlag = oFlag;
  }

  public String getPAYOFF_AMOUNT() {
    return PAYOFF_AMOUNT;
  }

  public void setPAYOFF_AMOUNT(String pAYOFF_AMOUNT) {
    PAYOFF_AMOUNT = pAYOFF_AMOUNT;
  }

  public String getP_D_Date() {
    return P_D_Date;
  }

  public void setP_D_Date(String p_D_Date) {
    P_D_Date = p_D_Date;
  }

  public String getProcessing_Date() {
    return Processing_Date;
  }

  public void setProcessing_Date(String processing_Date) {
    Processing_Date = processing_Date;
  }

  public String getP_D_Date_1() {
    return P_D_Date_1;
  }

  public void setP_D_Date_1(String p_D_Date_1) {
    P_D_Date_1 = p_D_Date_1;
  }

  public String getPD30_Count() {
    return PD30_Count;
  }

  public void setPD30_Count(String pD30_Count) {
    PD30_Count = pD30_Count;
  }

  public String getPD60_Count() {
    return PD60_Count;
  }

  public void setPD60_Count(String pD60_Count) {
    PD60_Count = pD60_Count;
  }

  public String getPD90_Count() {
    return PD90_Count;
  }

  public void setPD90_Count(String pD90_Count) {
    PD90_Count = pD90_Count;
  }

  public String getRCflag() {
    return RCflag;
  }

  public void setRCflag(String rCflag) {
    RCflag = rCflag;
  }

  public String getREGION_WEST() {
    return REGION_WEST;
  }

  public void setREGION_WEST(String REGION_WEST) {
    this.REGION_WEST = REGION_WEST;
  }

  public String getRcode() {
    return Rcode;
  }

  public void setRcode(String rcode) {
    Rcode = rcode;
  }

  public String getRflag() {
    return Rflag;
  }

  public void setRflag(String rflag) {
    Rflag = rflag;
  }

  public String getSPECIAL_HANDLING_FLAG() {
    return SPECIAL_HANDLING_FLAG;
  }

  public void setSPECIAL_HANDLING_FLAG(String sPECIAL_HANDLING_FLAG) {
    SPECIAL_HANDLING_FLAG = sPECIAL_HANDLING_FLAG;
  }

  public String getSC1() {
    return SC1;
  }

  public void setSC1(String sC1) {
    SC1 = sC1;
  }

  public String getSS1() {
    return SS1;
  }

  public void setSS1(String sS1) {
    SS1 = sS1;
  }

  public String getSAS2() {
    return SAS2;
  }

  public void setSAS2(String sAS2) {
    SAS2 = sAS2;
  }

  public String getSTATE() {
    return STATE;
  }

  public void setSTATE(String sTATE) {
    STATE = sTATE;
  }

  public String getRT5() {
    return RT5;
  }

  public void setRT5(String rT5) {
    RT5 = rT5;
  }

  public String getTIME_ZONE_IND() {
    return TIME_ZONE_IND;
  }

  public void setTIME_ZONE_IND(String tIME_ZONE_IND) {
    TIME_ZONE_IND = tIME_ZONE_IND;
  }

  public String getTOT_MNTHS_EXT_12MNTH() {
    return TOT_MNTHS_EXT_12MNTH;
  }

  public void setTOT_MNTHS_EXT_12MNTH(String tOT_MNTHS_EXT_12MNTH) {
    TOT_MNTHS_EXT_12MNTH = tOT_MNTHS_EXT_12MNTH;
  }

  public String getTOT_MNTHS_EXT_6MNTH() {
    return TOT_MNTHS_EXT_6MNTH;
  }

  public void setTOT_MNTHS_EXT_6MNTH(String tOT_MNTHS_EXT_6MNTH) {
    TOT_MNTHS_EXT_6MNTH = tOT_MNTHS_EXT_6MNTH;
  }

  public String getTOT_TIMES_EXT_12MNTH() {
    return TOT_TIMES_EXT_12MNTH;
  }

  public void setTOT_TIMES_EXT_12MNTH(String tOT_TIMES_EXT_12MNTH) {
    TOT_TIMES_EXT_12MNTH = tOT_TIMES_EXT_12MNTH;
  }

  public String getTOT_TIMES_EXT_6MNTH() {
    return TOT_TIMES_EXT_6MNTH;
  }

  public void setTOT_TIMES_EXT_6MNTH(String tOT_TIMES_EXT_6MNTH) {
    TOT_TIMES_EXT_6MNTH = tOT_TIMES_EXT_6MNTH;
  }

  public String getTYPE() {
    return TYPE;
  }

  public void setTYPE(String tYPE) {
    TYPE = tYPE;
  }

  public String getUA10() {
    return UA10;
  }

  public void setUA10(String uA10) {
    UA10 = uA10;
  }

  public String getUM2() {
    return UM2;
  }

  public void setUM2(String uM2) {
    UM2 = uM2;
  }

  public String getWORK_IS_MOBILE() {
    return WORK_IS_MOBILE;
  }

  public void setWORK_IS_MOBILE(String wORK_IS_MOBILE) {
    WORK_IS_MOBILE = wORK_IS_MOBILE;
  }

  public String getWNFlag() {
    return WNFlag;
  }

  public void setWNFlag(String wNFlag) {
    WNFlag = wNFlag;
  }

  public String getWORK_PHONE_OWNERSHIP() {
    return WORK_PHONE_OWNERSHIP;
  }

  public void setWORK_PHONE_OWNERSHIP(String wORK_PHONE_OWNERSHIP) {
    WORK_PHONE_OWNERSHIP = wORK_PHONE_OWNERSHIP;
  }

  public String getZIP() {
    return ZIP;
  }

  public void setZIP(String zIP) {
    ZIP = zIP;
  }

  public String getCMA3000() {
    return CMA3000;
  }

  public void setCMA3000(String cMA3000) {
    CMA3000 = cMA3000;
  }

  public String getCMA3224() {
    return CMA3224;
  }

  public void setCMA3224(String cMA3224) {
    CMA3224 = cMA3224;
  }

  public String getCMA3579() {
    return CMA3579;
  }

  public void setCMA3579(String cMA3579) {
    CMA3579 = cMA3579;
  }

  public String getCMA3863() {
    return CMA3863;
  }

  public void setCMA3863(String cMA3863) {
    CMA3863 = cMA3863;
  }

  public String getCMA3329() {
    return CMA3329;
  }

  public void setCMA3329(String cMA3329) {
    CMA3329 = cMA3329;
  }

  public String getCMA3237() {
    return CMA3237;
  }

  public void setCMA3237(String cMA3237) {
    CMA3237 = cMA3237;
  }

  public String getCMA3580() {
    return CMA3580;
  }

  public void setCMA3580(String cMA3580) {
    CMA3580 = cMA3580;
  }

  public String getCMA3865() {
    return CMA3865;
  }

  public void setCMA3865(String cMA3865) {
    CMA3865 = cMA3865;
  }

  public String getCMA3116() {
    return CMA3116;
  }

  public void setCMA3116(String cMA3116) {
    CMA3116 = cMA3116;
  }

  public String getCMA3986() {
    return CMA3986;
  }

  public void setCMA3986(String cMA3986) {
    CMA3986 = cMA3986;
  }

  public String getAM201TOT() {
    return AM201TOT;
  }

  public void setAM201TOT(String aM201TOT) {
    AM201TOT = aM201TOT;
  }

  public String getCMA_3001() {
    return CMA_3001;
  }

  public void setCMA_3001(String cMA_3001) {
    CMA_3001 = cMA_3001;
  }

  public String getCMA_3100() {
    return CMA_3100;
  }

  public void setCMA_3100(String cMA_3100) {
    CMA_3100 = cMA_3100;
  }

  public String getCMA_3109() {
    return CMA_3109;
  }

  public void setCMA_3109(String cMA_3109) {
    CMA_3109 = cMA_3109;
  }

  public String getCMA_3123() {
    return CMA_3123;
  }

  public void setCMA_3123(String cMA_3123) {
    CMA_3123 = cMA_3123;
  }

  public String getCMA_3148() {
    return CMA_3148;
  }

  public void setCMA_3148(String cMA_3148) {
    CMA_3148 = cMA_3148;
  }

  public String getCMA_3207() {
    return CMA_3207;
  }

  public void setCMA_3207(String cMA_3207) {
    CMA_3207 = cMA_3207;
  }

  public String getCMA_3273() {
    return CMA_3273;
  }

  public void setCMA_3273(String cMA_3273) {
    CMA_3273 = cMA_3273;
  }

  public String getCMA_3536() {
    return CMA_3536;
  }

  public void setCMA_3536(String cMA_3536) {
    CMA_3536 = cMA_3536;
  }

  public String getCMA_3913() {
    return CMA_3913;
  }

  public void setCMA_3913(String cMA_3913) {
    CMA_3913 = cMA_3913;
  }

  public String getCMA_3857() {
    return CMA_3857;
  }

  public void setCMA_3857(String cMA_3857) {
    CMA_3857 = cMA_3857;
  }

  public String getCMA_3938() {
    return CMA_3938;
  }

  public void setCMA_3938(String cMA_3938) {
    CMA_3938 = cMA_3938;
  }

  public String getCMA3002() {
    return CMA3002;
  }

  public void setCMA3002(String cMA3002) {
    CMA3002 = cMA3002;
  }

  public String getCMA3203() {
    return CMA3203;
  }

  public void setCMA3203(String cMA3203) {
    CMA3203 = cMA3203;
  }

  public String getCMA3215() {
    return CMA3215;
  }

  public void setCMA3215(String cMA3215) {
    CMA3215 = cMA3215;
  }

  public String getCMA3217() {
    return CMA3217;
  }

  public void setCMA3217(String cMA3217) {
    CMA3217 = cMA3217;
  }

  public String getCMA3235() {
    return CMA3235;
  }

  public void setCMA3235(String cMA3235) {
    CMA3235 = cMA3235;
  }

  public String getCMA3268() {
    return CMA3268;
  }

  public void setCMA3268(String cMA3268) {
    CMA3268 = cMA3268;
  }

  public String getCMA3333() {
    return CMA3333;
  }

  public void setCMA3333(String cMA3333) {
    CMA3333 = cMA3333;
  }

  public String getCMA3535() {
    return CMA3535;
  }

  public void setCMA3535(String cMA3535) {
    CMA3535 = cMA3535;
  }

  public String getCMA3747() {
    return CMA3747;
  }

  public void setCMA3747(String cMA3747) {
    CMA3747 = cMA3747;
  }

  public String getCMA3835() {
    return CMA3835;
  }

  public void setCMA3835(String cMA3835) {
    CMA3835 = cMA3835;
  }

  public String getCMA3854() {
    return CMA3854;
  }

  public void setCMA3854(String cMA3854) {
    CMA3854 = cMA3854;
  }

  public String getCMA3871() {
    return CMA3871;
  }

  public void setCMA3871(String cMA3871) {
    CMA3871 = cMA3871;
  }

  public String getCMA3872() {
    return CMA3872;
  }

  public void setCMA3872(String cMA3872) {
    CMA3872 = cMA3872;
  }

  public String getCMA3969() {
    return CMA3969;
  }

  public void setCMA3969(String cMA3969) {
    CMA3969 = cMA3969;
  }

  public String getCMA3546() {
    return CMA3546;
  }

  public void setCMA3546(String cMA3546) {
    CMA3546 = cMA3546;
  }

  public String getCMA3843() {
    return CMA3843;
  }

  public void setCMA3843(String cMA3843) {
    CMA3843 = cMA3843;
  }

  public String getCMA3844() {
    return CMA3844;
  }

  public void setCMA3844(String cMA3844) {
    CMA3844 = cMA3844;
  }

  public String getCMA3873() {
    return CMA3873;
  }

  public void setCMA3873(String cMA3873) {
    CMA3873 = cMA3873;
  }

  public String getCMA3895() {
    return CMA3895;
  }

  public void setCMA3895(String cMA3895) {
    CMA3895 = cMA3895;
  }

  public String getCMA3994() {
    return CMA3994;
  }

  public void setCMA3994(String cMA3994) {
    CMA3994 = cMA3994;
  }

  public String getCMA3120() {
    return CMA3120;
  }

  public void setCMA3120(String cMA3120) {
    CMA3120 = cMA3120;
  }

  public String getCMA_3135() {
    return CMA_3135;
  }

  public void setCMA_3135(String cMA_3135) {
    CMA_3135 = cMA_3135;
  }

  public String getCMA_3376() {
    return CMA_3376;
  }

  public void setCMA_3376(String cMA_3376) {
    CMA_3376 = cMA_3376;
  }

  public String getCMA_3862() {
    return CMA_3862;
  }

  public void setCMA_3862(String cMA_3862) {
    CMA_3862 = cMA_3862;
  }

  public String getCMA_3124() {
    return CMA_3124;
  }

  public void setCMA_3124(String cMA_3124) {
    CMA_3124 = cMA_3124;
  }

  public String getCMA_3164() {
    return CMA_3164;
  }

  public void setCMA_3164(String cMA_3164) {
    CMA_3164 = cMA_3164;
  }

  public String getCMA_3159() {
    return CMA_3159;
  }

  public void setCMA_3159(String cMA_3159) {
    CMA_3159 = cMA_3159;
  }

  public String getAT01() {
    return AT01;
  }

  public void setAT01(String aT01) {
    AT01 = aT01;
  }

  public String getAVG_Payment_Count2() {
    return AVG_Payment_Count2;
  }

  public void setAVG_Payment_Count2(String AVG_Payment_Count2) {
    this.AVG_Payment_Count2 = AVG_Payment_Count2;
  }

  public String getPscore() {
    return Pscore;
  }

  public void setPscore(String pscore) {
    Pscore = pscore;
  }

  public String getPRAT() {
    return PRAT;
  }

  public void setPRAT(String pRAT) {
    PRAT = pRAT;
  }

  public String getAratio() {
    return Aratio;
  }

  public void setAratio(String aratio) {
    Aratio = aratio;
  }

  public String getLT_CHRG_BAL_AMT() {
    return LT_CHRG_BAL_AMT;
  }

  public void setLT_CHRG_BAL_AMT(String lT_CHRG_BAL_AMT) {
    LT_CHRG_BAL_AMT = lT_CHRG_BAL_AMT;
  }

  public String getExtns() {
    return Extns;
  }

  public void setExtns(String extns) {
    Extns = extns;
  }

  public String getFDIFF() {
    return FDIFF;
  }

  public void setFDIFF(String FDIFF) {
    this.FDIFF = FDIFF;
  }

  public String getOTerm_Count() {
    return OTerm_Count;
  }

  public void setOTerm_Count(String oTerm_Count) {
    OTerm_Count = oTerm_Count;
  }

  public String getPERIODS_EXTENDED() {
    return PERIODS_EXTENDED;
  }

  public void setPERIODS_EXTENDED(String pERIODS_EXTENDED) {
    PERIODS_EXTENDED = pERIODS_EXTENDED;
  }

  public String getDELQ_MAX() {
    return DELQ_MAX;
  }

  public void setDELQ_MAX(String dELQ_MAX) {
    DELQ_MAX = dELQ_MAX;
  }

  public Integer getDaysDelinquent() {
    return DaysDelinquent;
  }

  public void setDaysDelinquent(Integer daysDelinquent) {
    DaysDelinquent = daysDelinquent;
  }

  public String getBad_Number() {
    return Bad_Number;
  }

  public void setBad_Number(String bad_Number) {
    Bad_Number = bad_Number;
  }

  public String getBL_FLAG() {
    return BL_FLAG;
  }

  public void setBL_FLAG(String bL_FLAG) {
    BL_FLAG = bL_FLAG;
  }

  public String getDAFlag() {
    return DAFlag;
  }

  public void setDAFlag(String dAFlag) {
    DAFlag = dAFlag;
  }

  public String getEflag() {
    return Eflag;
  }

  public void setEflag(String eflag) {
    Eflag = eflag;
  }

  public String getLS_FLAG() {
    return LS_FLAG;
  }

  public void setLS_FLAG(String lS_FLAG) {
    LS_FLAG = lS_FLAG;
  }

  public String getNflag() {
    return Nflag;
  }

  public void setNflag(String nflag) {
    Nflag = nflag;
  }

  public String getNDate() {
    return NDate;
  }

  public void setNDate(String NDate) {
    this.NDate = NDate;
  }

  public String getNO_CONTACT() {
    return NO_CONTACT;
  }

  public void setNO_CONTACT(String nO_CONTACT) {
    NO_CONTACT = nO_CONTACT;
  }

  public String getPST_Home_Area_Flag() {
    return PST_Home_Area_Flag;
  }

  public void setPST_Home_Area_Flag(String pST_Home_Area_Flag) {
    PST_Home_Area_Flag = pST_Home_Area_Flag;
  }

  public String getP_1() {
    return P_1;
  }

  public void setP_1(String P_1) {
    this.P_1 = P_1;
  }

  public String getSB() {
    return SB;
  }

  public void setSB(String sB) {
    SB = sB;
  }

  public String getSflag() {
    return Sflag;
  }

  public void setSflag(String sflag) {
    Sflag = sflag;
  }

  public String getAflag() {
    return Aflag;
  }

  public void setAflag(String aflag) {
    Aflag = aflag;
  }

  public String getCto_score_gen3() {
    return cto_score_gen3;
  }

  public void setCto_score_gen3(String cto_score_gen3) {
    this.cto_score_gen3 = cto_score_gen3;
  }

  public String getCto_seg() {
    return cto_seg;
  }

  public void setCto_seg(String cto_seg) {
    this.cto_seg = cto_seg;
  }

  private String ID;
  private String Act_DT_1;
  private String Act_DT_2;
  private String Act_DT_3;
  private String Is_Active;
  private String Gross_Due_Amount;
  private Double BALANCE;
  private String Region;
  private String Bflag;
  private String Bflag_Processed;
  private String Bat_DT;
  private String Posting_Status;
  private String ProcessingDate;
  private String Closed_Date;
  private String Grade;
  private String Region_Code;
  private String Rate;
  private String Dcode;
  private String Disc_Rate;
  private String EVR60_MOB;
  private String EXTENSION_MOB;
  private String Term_Count;
  private String Score;
  private String Ephone;
  private String Insert_DT;
  private String Count30;
  private String Count60;
  private String Count90;
  private String HOME_IS_MOBILE;
  private String HNFlag;
  private String Hphone;
  private String HOME_PHONE_OWNERSHIP;
  private String LANGUAGE_PREFERENCE;
  private String Entry_DT;
  private String Ext_DT;
  private String LC_DT;
  private String LC1;
  private String LIST_1;
  private String List_Region_1;
  private String LIST_STATUS_1;
  private String MATURITY_DATE;
  private String Mflag;
  private String MNFlag;
  private String MOB;
  private String MOBILE_PHONE;
  private String MOBILE_PHONE_OWNERSHIP;
  private String MOB_TERM_RATIO;
  private String MONTHS_REMAINING;
  private String MTR2;
  private String MTR_GT_08;
  private String Payment_Count;
  private String Payment_Due_Count;
  private String Next_DT;
  private String Payment_Paid_Count;
  private String Payment_Left_Count;
  private String UA4;
  private String UA7;
  private String Oind;
  private String U_Amount_Due;
  private String OFlag;
  private String PAYOFF_AMOUNT;
  private String P_D_Date;
  private String Processing_Date;
  private String P_D_Date_1;
  private String PD30_Count;
  private String PD60_Count;
  private String PD90_Count;
  private String RCflag;
  private String REGION_WEST;
  private String Rcode;
  private String Rflag;
  private String SPECIAL_HANDLING_FLAG;
  private String SC1;
  private String SS1;
  private String SAS2;
  private String STATE;
  private String RT5;
  private String TIME_ZONE_IND;
  private String TOT_MNTHS_EXT_12MNTH;
  private String TOT_MNTHS_EXT_6MNTH;
  private String TOT_TIMES_EXT_12MNTH;
  private String TOT_TIMES_EXT_6MNTH;
  private String TYPE;
  private String UA10;
  private String UM2;
  private String WORK_IS_MOBILE;
  private String WNFlag;
  private String WORK_PHONE_OWNERSHIP;
  private String ZIP;
  private String CMA3000;
  private String CMA3224;
  private String CMA3579;
  private String CMA3863;
  private String CMA3329;
  private String CMA3237;
  private String CMA3580;
  private String CMA3865;
  private String CMA3116;
  private String CMA3986;
  private String AM201TOT;
  private String CMA_3001;
  private String CMA_3100;
  private String CMA_3109;
  private String CMA_3123;
  private String CMA_3148;
  private String CMA_3207;
  private String CMA_3273;
  private String CMA_3536;
  private String CMA_3913;
  private String CMA_3857;
  private String CMA_3938;
  private String CMA3002;
  private String CMA3203;
  private String CMA3215;
  private String CMA3217;
  private String CMA3235;
  private String CMA3268;
  private String CMA3333;
  private String CMA3535;
  private String CMA3747;
  private String CMA3835;
  private String CMA3854;
  private String CMA3871;
  private String CMA3872;
  private String CMA3969;
  private String CMA3546;
  private String CMA3843;
  private String CMA3844;
  private String CMA3873;
  private String CMA3895;
  private String CMA3994;
  private String CMA3120;
  private String CMA_3135;
  private String CMA_3376;
  private String CMA_3862;
  private String CMA_3124;
  private String CMA_3164;
  private String CMA_3159;
  private String AT01;
  private String AVG_Payment_Count2;
  private String Pscore;
  private String PRAT;
  private String Aratio;
  private String LT_CHRG_BAL_AMT;
  private String Extns;
  private String FDIFF;
  private String OTerm_Count;
  private String PERIODS_EXTENDED;
  private String DELQ_MAX;
  private Integer DaysDelinquent;
  private String Bad_Number;
  private String BL_FLAG;
  private String DAFlag;
  private String Eflag;
  private String LS_FLAG;
  private String Nflag;
  private String NDate;
  private String NO_CONTACT;
  private String PST_Home_Area_Flag;
  private String P_1;
  private String SB;
  private String Sflag;
  private String Aflag;
  private String cto_score_gen3;
  private String cto_seg;

  public String getTreatmentOutcome() {
    return treatmentOutcome;
  }

  public void setTreatmentOutcome(String treatmentOutcome) {
    this.treatmentOutcome = treatmentOutcome;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public Double getSkip_ID() {
    return skip_ID;
  }

  public void setSkip_ID(Double skip_ID) {
    this.skip_ID = skip_ID;
  }

}
