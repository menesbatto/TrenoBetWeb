package app.logic._1_matchResultParser.model; 	 // 

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import app.dao.tabelle.entities.Champ;
import app.utils.ChampEnum;

public class MatchResult implements Serializable{
	
	private static final long serialVersionUID = 5857347462773566556L;

	// Key to results data:
	
	private String Div;			// League division
	private ChampEnum champ;		// League division
	private String Date; 	 	// Match Date (dd/mm/yy)
	private Date matchDate;
	private String HomeTeam; 	// Home Team
	private String AwayTeam; 	// Away Team
	private Integer FTHG; 	 	// Full Time Home Team Goals
	private Integer FTAG; 	 	// Full Time Away Team Goals
	private String FTR; 	 	// Full Time Result (H=Home Win, D=Draw, A=Away Win)
	private Integer HTHG; 	 	// Half Time Home Team Goals
	private Integer HTAG; 	 	// Half Time Away Team Goals
	private String HTR; 	 	// Half Time Result (H=Home Win, D=Draw, A=Away Win)
	

	// Match Statistics (where available)
	private Integer HS; 	 		// Home Team Shots
	private Integer AS; 	 		// Away Team Shots
	private Integer HST; 	 		// Home Team Shots on Target
	private Integer AST; 	 		// Away Team Shots on Target
	private Integer HF; 	 		// Home Team Fouls Committed
	private Integer AF; 	 		// Away Team Fouls Committed
	private Integer HC; 	 		// Home Team Corners
	private Integer AC; 			// Away Team Corners
	private Integer HY; 	 		// Home Team Yellow Cards
	private Integer AY; 	 		// Away Team Yellow Cards
	private Integer HR; 	 		// Home Team Red Cards
	private Integer AR; 	 		// Away Team Red Cards
	
	// Key to 1X2 (match) betting odds data:
	private Double B365H; 	 	// Bet365 home win odds
	private Double B365D; 	 	// Bet365 draw odds
	private Double B365A; 	 	// Bet365 away win odds
//	
//	private Double B365H1; 	 	// Bet365 home 1 half win odds
//	private Double B365D1; 	 	// Bet365 draw 1 half odds
//	private Double B365A1; 	 	// Bet365 away 1 half win odds
//	
//	private Double B365H2; 	 	// Bet365 home 2 half win odds
//	private Double B365D2; 	 	// Bet365 draw 2 half odds
//	private Double B365A2; 	 	// Bet365 away 2 half win odds
//	
//	private Double BCH; 	 	// BetClic home win odds
//	private Double BCD; 	 	// BetClic draw odds
//	private Double BCA; 	 	// BetClic away win odds
//
//	private Double BCH1; 	 	// BetClic home 1 half win odds
//	private Double BCD1; 	 	// BetClic draw 1 half odds
//	private Double BCA1; 	 	// BetClic away 1 half win odds
//	
//	private Double BCH2; 	 	// BetClic home 2 half win odds
//	private Double BCD2; 	 	// BetClic draw 2 half odds
//	private Double BCA2; 	 	// BetClic away 2 half win odds
//	
	private Double BWH; 		// Bet&Win home win odds
	private Double BWD; 	 	// Bet&Win draw odds
	private Double BWA; 	 	// Bet&Win away win odds
//	
//	private Double BWH1; 		// Bet&Win home 1 half win odds
//	private Double BWD1; 	 	// Bet&Win draw 1 half odds
//	private Double BWA1; 	 	// Bet&Win away 1 half win odds
//	
//	private Double BWH2; 		// Bet&Win home 2 half win odds
//	private Double BWD2; 	 	// Bet&Win draw 2 half odds
//	private Double BWA2; 	 	// Bet&Win away 2 half win odds
//	
//	private Double PPH; 	 	// PaddyPower home win odds
//	private Double PPD; 	 	// PaddyPower draw odds
//	private Double PPA; 	 	// PaddyPower away win odds
//	
//	private Double PPH1; 	 	// PaddyPower 1 half home win odds
//	private Double PPD1; 	 	// PaddyPower 1 half draw odds
//	private Double PPA1; 	 	// PaddyPower 1 half away win odds
//	
//	private Double PPH2; 	 	// PaddyPower 2 half home win odds
//	private Double PPD2; 	 	// PaddyPower 2 half draw odds
//	private Double PPA2; 	 	// PaddyPower 2 half away win odds
//	
//	private Double UBH; 	 	// Unibet home win odds
//	private Double UBD; 	 	// Unibet Hill draw odds
//	private Double UBA; 	 	// Unibet Hill away win odds
//
//	private Double UBH1; 	 	// Unibet home 1 half win odds
//	private Double UBD1; 	 	// Unibet draw 1 half odds
//	private Double UBA1; 	 	// Unibet away 1 half win odds
//	
//	private Double UBH2; 	 	// Unibet home 2 half win odds
//	private Double UBD2; 	 	// Unibet Hill 2 half draw odds
//	private Double UBA2; 	 	// Unibet Hill 2 half away win odds
//	
//	private Double TIH; 	 	// Tipico home win odds
//	private Double TID; 	 	// Tipico draw odds
//	private Double TIA; 	 	// Tipico away win odds
//
//	private Double TIH1; 	 	// Tipico home 1 half win odds
//	private Double TID1; 	 	// Tipico draw 1 half odds
//	private Double TIA1; 	 	// Tipico away 1 half win odds
//	
//	private Double TIH2; 	 	// Tipico home 2 half win odds
//	private Double TID2; 	 	// Tipico draw 2 half odds
//	private Double TIA2; 	 	// Tipico away 2 half win odds
//
//
	private Double WHH; 	 	// William Hill home win odds
	private Double WHD; 	 	// William Hill draw odds
	private Double WHA; 	 	// William Hill away win odds
//
//	private Double WHH1; 	 	// William Hill 1 half home win odds
//	private Double WHD1; 	 	// William Hill 1 half draw odds
//	private Double WHA1; 	 	// William Hill 1 half away win odds
//	
//	private Double WHH2; 	 	// William Hill home win odds
//	private Double WHD2; 	 	// William Hill draw odds
//	private Double WHA2; 	 	// William Hill away win odds
	
	
	private Double IWH; 	 	// Interwetten home win odds
	private Double IWD; 	 	// Interwetten draw odds
	private Double IWA; 	 	// Interwetten away win odds
	private Double LBH; 	 	// Ladbrokes home win odds
	private Double LBD; 	 	// Ladbrokes draw odds
	private Double LBA; 	 	// Ladbrokes away win odds
	private Double PSH; 	 	// Pinnacle home win odds
	private Double PSD; 	 	// Pinnacle draw odds
	private Double PSA; 	 	// Pinnacle away win odds
	private Double VCH; 	 	// VC Bet home win odds
	private Double VCD; 	 	// VC Bet draw odds
	private Double VCA; 	 	// VC Bet away win odds
	
	private Integer Bb1X2; 	 	// Number of BetBrain bookmakers used to calculate match odds averages and maximums
	private Double BbMxH; 	 	// Betbrain maximum home win odds
	private Double BbAvH; 	 	// Betbrain average home win odds
	private Double BbMxD; 	 	// Betbrain maximum draw odds
	private Double BbAvD; 	 	// Betbrain average draw win odds
	private Double BbMxA; 	 	// Betbrain maximum away win odds
	private Double BbAvA; 	 	// Betbrain average away win odds
	
	// Key to total goals betting odds:
	
	private Integer BbOU; 	 	// Number of BetBrain bookmakers used to calculate over/under 2.5 goals (total goals) averages and maximums
	private Double BbMxO2_5; 	// Betbrain maximum over 2.5 goals
	private Double BbAvO2_5; 	// Betbrain average over 2.5 goals
	private Double BbMxU2_5; 	// Betbrain maximum under 2.5 goals
	private Double BbAvU2_5; 	// Betbrain average under 2.5 goals
	 
	private HashMap<TimeTypeEnum, _1x2Full> _1x2;
	
	private HashMap<TimeTypeEnum, UoTimeType> uo;

	private HashMap<TimeTypeEnum, EhTimeType> eh;
	
	
	
	// Key to Asian handicap betting odds:
	private Integer BbAH; 	 	// Number of BetBrain bookmakers used to Asian handicap averages and maximums http://www.betshoot.com/asian-handicap-betting/
	private Double BbAHh; 	 	// Betbrain size of handicap (home team)
	private Double BbMxAHH; 	// Betbrain maximum Asian handicap home team odds
	private Double BbAvAHH; 	// Betbrain average Asian handicap home team odds
	private Double BbMxAHA; 	// Betbrain maximum Asian handicap away team odds
	private Double BbAvAHA; 	// Betbrain average Asian handicap away team odds
	
	// Closing odds (last odds before match starts)
	private Double PSCH; 	 	// Pinnacle closing home win odds
	private Double PSCD; 	 	// Pinnacle closing draw odds
	private Double PSCA; 	 	// Pinnacle closing away win odds


	
	
	public MatchResult() {
		_1x2 = new HashMap<TimeTypeEnum, _1x2Full>();
		_1x2.put(TimeTypeEnum._final, new _1x2Full());
		_1x2.put(TimeTypeEnum._1, new _1x2Full());
		_1x2.put(TimeTypeEnum._2, new _1x2Full());

		uo = new HashMap<TimeTypeEnum, UoTimeType>();
		uo.put(TimeTypeEnum._final, new UoTimeType());
		uo.put(TimeTypeEnum._1, new UoTimeType());
		uo.put(TimeTypeEnum._2, new UoTimeType());

		eh = new HashMap<TimeTypeEnum, EhTimeType>();
		eh.put(TimeTypeEnum._final, new EhTimeType());
		eh.put(TimeTypeEnum._1, new EhTimeType());
		eh.put(TimeTypeEnum._2, new EhTimeType());
	}



	@Override
	public String toString() {
		return 	"ESITO\n"
					+ "\tDiv=" + Div + ",	 Date=" + Date + ",	 HomeTeam=" + HomeTeam + ",	 AwayTeam=" + AwayTeam
					+ ",	 FTHG=" + FTHG + ",	 FTAG=" + FTAG + ",	 FTR=" + FTR + ",	 HTHG=" + HTHG + ",	 HTAG=" + HTAG + ",HTR="
					+ HTR + ",\n"
				
				+ "STATISTICHE\n"
					+ "\tHS=" + HS + ",	 AS=" + AS + ",	 HST=" + HST + ",	 AST=" + AST + ",	 HF=" + HF + ",	 AF=" + AF
					+ ",	 HC=" + HC + ",	 AC=" + AC + ",	 HY=" + HY + ",	 AY=" + AY + ",	 HR=" + HR + ",	 AR=" + AR + ",\n"

//				+ "QUOTE\n"
//					+ "\tB365H=" + B365H + ",	 B365D=" + B365D + ",	 B365A=" + B365A + ",	 \n\tBWH=" + BWH + ",	 BWD=" + BWD + ",	 BWA=" + BWA
//					+ ",	 \n\tIWH=" + IWH + ",	 IWD=" + IWD + ",	 IWA=" + IWA + ",	 \n\tLBH=" + LBH + ",	 LBD=" + LBD + ",	 LBA=" + LBA
//					+ ",	 \n\tPSH=" + PSH + ",	 PSD=" + PSD + ",	 PSA=" + PSA + ",	 \n\tWHH=" + WHH + ",	 WHD=" + WHD + ",	 WHA=" + WHA
//					+ ",	 \n\tVCH=" + VCH + ",	 VCD=" + VCD + ",	 VCA=" + VCA + ",\n"

				+ "QUOTE\n"
					+ "\tB365H=" + "\t" + B365H + "\t" + B365D + "\t" + B365A + "\n\tBWH=" + "\t" + BWH + "\t" + BWD + "\t" + BWA
					+ "\n\tIWH=\t" + IWH + "\t" + IWD + "\t" + IWA + "\n\tLBH=\t" + LBH + "\t" + LBD + "\t" + LBA
					+ "\n\tPSH=\t" + PSH + "\t" + PSD + "\t" + PSA + "\n\tWHH=\t" + WHH + "\t" + WHD + "\t" + WHA
					+ "\n\tVCH=\t" + VCH + "\t" + VCD + "\t" + VCA + "\n"
				
					
				+ "ESITO\n"
					+ "\tBb1X2=" + Bb1X2 + ",	 BbMxH=" + BbMxH + ",	 BbAvH=" + BbAvH + ",	 BbMxD=" + BbMxD + ","	+ " BbAvD=" + BbAvD + ",	 BbMxA=" + BbMxA + ",	 BbAvA="
					+ BbAvA + ",	 BbOU=" + BbOU + ",	\n"
				
				+ "U/O\n"
					+ "\tBbMxO2_5=" + BbMxO2_5 + ",	 BbAvO2_5=" + BbAvO2_5 + ",	 BbMxU2_5="	+ BbMxU2_5 + ",	 BbAvU2_5=" + BbAvU2_5 + ",\n"
				
				+ "HANDICAP\n"
					+ "\tBbAH=" + BbAH + ",	 BbAHh=" + BbAHh + ",	 BbMxAHH=" + BbMxAHH + ",	 BbAvAHH=" + BbAvAHH + ",	 BbMxAHA=" + BbMxAHA + ",	 BbAvAHA=" + BbAvAHA + ",\n"
				
				+ "LAST ODDS\n"
					+ "\tPSCH=" + PSCH + ",	 PSCD=" + PSCD + ",	 PSCA=" + PSCA + "\n\n";
	}



	public String getDiv() {
		return Div;
	}



	public void setDiv(String div) {
		Div = div;
	}



	public String getDate() {
		return Date;
	}



	public void setDate(String date) {
		Date = date;
	}



	public String getHomeTeam() {
		return HomeTeam;
	}



	public void setHomeTeam(String homeTeam) {
		HomeTeam = homeTeam;
	}



	public String getAwayTeam() {
		return AwayTeam;
	}



	public void setAwayTeam(String awayTeam) {
		AwayTeam = awayTeam;
	}



	public Integer getFTHG() {
		return FTHG;
	}



	public void setFTHG(Integer fTHG) {
		FTHG = fTHG;
	}



	public Integer getFTAG() {
		return FTAG;
	}



	public void setFTAG(Integer fTAG) {
		FTAG = fTAG;
	}



	public String getFTR() {
		return FTR;
	}



	public void setFTR(String fTR) {
		FTR = fTR;
	}



	public Integer getHTHG() {
		return HTHG;
	}



	public void setHTHG(Integer hTHG) {
		HTHG = hTHG;
	}



	public Integer getHTAG() {
		return HTAG;
	}



	public void setHTAG(Integer hTAG) {
		HTAG = hTAG;
	}



	public String getHTR() {
		return HTR;
	}



	public void setHTR(String hTR) {
		HTR = hTR;
	}



	public Integer getHS() {
		return HS;
	}



	public void setHS(Integer hS) {
		HS = hS;
	}



	public Integer getAS() {
		return AS;
	}



	public void setAS(Integer aS) {
		AS = aS;
	}



	public Integer getHST() {
		return HST;
	}



	public void setHST(Integer hST) {
		HST = hST;
	}



	public Integer getAST() {
		return AST;
	}



	public void setAST(Integer aST) {
		AST = aST;
	}



	public Integer getHF() {
		return HF;
	}



	public void setHF(Integer hF) {
		HF = hF;
	}



	public Integer getAF() {
		return AF;
	}



	public void setAF(Integer aF) {
		AF = aF;
	}



	public Integer getHC() {
		return HC;
	}



	public void setHC(Integer hC) {
		HC = hC;
	}



	public Integer getAC() {
		return AC;
	}



	public void setAC(Integer aC) {
		AC = aC;
	}



	public Integer getHY() {
		return HY;
	}



	public void setHY(Integer hY) {
		HY = hY;
	}



	public Integer getAY() {
		return AY;
	}



	public void setAY(Integer aY) {
		AY = aY;
	}



	public Integer getHR() {
		return HR;
	}



	public void setHR(Integer hR) {
		HR = hR;
	}



	public Integer getAR() {
		return AR;
	}



	public void setAR(Integer aR) {
		AR = aR;
	}



	public Double getB365H() {
		return B365H;
	}



	public void setB365H(Double b365h) {
		B365H = b365h;
	}



	public Double getB365D() {
		return B365D;
	}



	public void setB365D(Double b365d) {
		B365D = b365d;
	}



	public Double getB365A() {
		return B365A;
	}



	public void setB365A(Double b365a) {
		B365A = b365a;
	}



	public Double getBWH() {
		return BWH;
	}



	public void setBWH(Double bWH) {
		BWH = bWH;
	}



	public Double getBWD() {
		return BWD;
	}



	public void setBWD(Double bWD) {
		BWD = bWD;
	}



	public Double getBWA() {
		return BWA;
	}



	public void setBWA(Double bWA) {
		BWA = bWA;
	}



	public Double getIWH() {
		return IWH;
	}



	public void setIWH(Double iWH) {
		IWH = iWH;
	}



	public Double getIWD() {
		return IWD;
	}



	public void setIWD(Double iWD) {
		IWD = iWD;
	}



	public Double getIWA() {
		return IWA;
	}



	public void setIWA(Double iWA) {
		IWA = iWA;
	}



	public Double getLBH() {
		return LBH;
	}



	public void setLBH(Double lBH) {
		LBH = lBH;
	}



	public Double getLBD() {
		return LBD;
	}



	public void setLBD(Double lBD) {
		LBD = lBD;
	}



	public Double getLBA() {
		return LBA;
	}



	public void setLBA(Double lBA) {
		LBA = lBA;
	}



	public Double getPSH() {
		return PSH;
	}



	public void setPSH(Double pSH) {
		PSH = pSH;
	}



	public Double getPSD() {
		return PSD;
	}



	public void setPSD(Double pSD) {
		PSD = pSD;
	}



	public Double getPSA() {
		return PSA;
	}



	public void setPSA(Double pSA) {
		PSA = pSA;
	}



	public Double getWHH() {
		return WHH;
	}



	public void setWHH(Double wHH) {
		WHH = wHH;
	}



	public Double getWHD() {
		return WHD;
	}



	public void setWHD(Double wHD) {
		WHD = wHD;
	}



	public Double getWHA() {
		return WHA;
	}



	public void setWHA(Double wHA) {
		WHA = wHA;
	}



	public Double getVCH() {
		return VCH;
	}



	public void setVCH(Double vCH) {
		VCH = vCH;
	}



	public Double getVCD() {
		return VCD;
	}



	public void setVCD(Double vCD) {
		VCD = vCD;
	}



	public Double getVCA() {
		return VCA;
	}



	public void setVCA(Double vCA) {
		VCA = vCA;
	}



	public Integer getBb1X2() {
		return Bb1X2;
	}



	public void setBb1X2(Integer bb1x2) {
		Bb1X2 = bb1x2;
	}



	public Double getBbMxH() {
		return BbMxH;
	}



	public void setBbMxH(Double bbMxH) {
		BbMxH = bbMxH;
	}



	public Double getBbAvH() {
		return BbAvH;
	}



	public void setBbAvH(Double bbAvH) {
		BbAvH = bbAvH;
	}



	public Double getBbMxD() {
		return BbMxD;
	}



	public void setBbMxD(Double bbMxD) {
		BbMxD = bbMxD;
	}



	public Double getBbAvD() {
		return BbAvD;
	}



	public void setBbAvD(Double bbAvD) {
		BbAvD = bbAvD;
	}



	public Double getBbMxA() {
		return BbMxA;
	}



	public void setBbMxA(Double bbMxA) {
		BbMxA = bbMxA;
	}



	public Double getBbAvA() {
		return BbAvA;
	}



	public void setBbAvA(Double bbAvA) {
		BbAvA = bbAvA;
	}



	public Integer getBbOU() {
		return BbOU;
	}



	public void setBbOU(Integer bbOU) {
		BbOU = bbOU;
	}



	public Double getBbMxO2_5() {
		return BbMxO2_5;
	}



	public void setBbMxO2_5(Double bbMxO2_5) {
		BbMxO2_5 = bbMxO2_5;
	}



	public Double getBbAvO2_5() {
		return BbAvO2_5;
	}



	public void setBbAvO2_5(Double bbAvO2_5) {
		BbAvO2_5 = bbAvO2_5;
	}



	public Double getBbMxU2_5() {
		return BbMxU2_5;
	}



	public void setBbMxU2_5(Double bbMxU2_5) {
		BbMxU2_5 = bbMxU2_5;
	}



	public Double getBbAvU2_5() {
		return BbAvU2_5;
	}



	public void setBbAvU2_5(Double bbAvU2_5) {
		BbAvU2_5 = bbAvU2_5;
	}



	public Integer getBbAH() {
		return BbAH;
	}



	public void setBbAH(Integer bbAH) {
		BbAH = bbAH;
	}



	public Double getBbAHh() {
		return BbAHh;
	}



	public void setBbAHh(Double bbAHh) {
		BbAHh = bbAHh;
	}



	public Double getBbMxAHH() {
		return BbMxAHH;
	}



	public void setBbMxAHH(Double bbMxAHH) {
		BbMxAHH = bbMxAHH;
	}



	public Double getBbAvAHH() {
		return BbAvAHH;
	}



	public void setBbAvAHH(Double bbAvAHH) {
		BbAvAHH = bbAvAHH;
	}



	public Double getBbMxAHA() {
		return BbMxAHA;
	}



	public void setBbMxAHA(Double bbMxAHA) {
		BbMxAHA = bbMxAHA;
	}



	public Double getBbAvAHA() {
		return BbAvAHA;
	}



	public void setBbAvAHA(Double bbAvAHA) {
		BbAvAHA = bbAvAHA;
	}



	public Double getPSCH() {
		return PSCH;
	}



	public void setPSCH(Double pSCH) {
		PSCH = pSCH;
	}



	public Double getPSCD() {
		return PSCD;
	}



	public void setPSCD(Double pSCD) {
		PSCD = pSCD;
	}



	public Double getPSCA() {
		return PSCA;
	}



	public void setPSCA(Double pSCA) {
		PSCA = pSCA;
	}



	public Date getMatchDate() {
		return matchDate;
	}



	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}





	public HashMap<TimeTypeEnum, UoTimeType> getUo() {
		return uo;
	}



	public void setUO(HashMap<TimeTypeEnum, UoTimeType> uO) {
		uo = uO;
	}



	public HashMap<TimeTypeEnum, _1x2Full> get_1x2() {
		return _1x2;
	}



	public void set_1x2(HashMap<TimeTypeEnum, _1x2Full> _1x2) {
		this._1x2 = _1x2;
	}



	public void setUo(HashMap<TimeTypeEnum, UoTimeType> uo) {
		this.uo = uo;
	}



	public HashMap<TimeTypeEnum, EhTimeType> getEh() {
		return eh;
	}



	public void setEh(HashMap<TimeTypeEnum, EhTimeType> eh) {
		this.eh = eh;
	}



	public ChampEnum getChamp() {
		return champ;
	}



	public void setChamp(ChampEnum champ) {
		this.champ = champ;
	}






	
}