package Classification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vectorizator {
	String[] dauChar = {"á","à","ả","ã","ạ","ă","ắ","ằ","ẳ","ẵ","ặ","â","ấ","ầ","ẩ","ẫ","ậ","é","è","ẻ","ẽ","ẹ","ê","ế","ề","ể","ễ","ệ","í","ì","ỉ","ĩ","ị","ó","ò","ỏ","õ","ọ","ô","ố","ồ","ổ","ỗ","ộ","ơ","ớ","ờ","ở","ỡ","ợ","ú","ù","ủ","ũ","ụ","ư","ứ","ừ","ử","ữ","ự","ý","ỳ","ỷ","ỹ","ỵ"};
	String[] nguyenAmChar = {"a","e","i","o","u","y","á","à","ả","ã","ạ","ă","ắ","ằ","ẳ","ẵ","ặ","â","ấ","ầ","ẩ","ẫ","ậ","é","è","ẻ","ẽ","ẹ","ê","ế","ề","ể","ễ","ệ","í","ì","ỉ","ĩ","ị","ó","ò","ỏ","õ","ọ","ô","ố","ồ","ổ","ỗ","ộ","ơ","ớ","ờ","ở","ỡ","ợ","ú","ù","ủ","ũ","ụ","ư","ứ","ừ","ử","ữ","ự","ý","ỳ","ỷ","ỹ","ỵ"};
	String[] phuAmChar = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z","đ"};
	String[] nuocNgoaiChar = {"f","j","z","w"};
	String[] upperChar = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","Ắ","Ằ","Ẳ","Ẵ","Ặ","Ă","Ấ","Ầ","Ẩ","Ẫ","Ậ","Â","Á","À","Ã","Ả","Ạ","Đ","Ế","Ề","Ể","Ễ","Ệ","Ê","É","È","Ẻ","Ẽ","Ẹ","Í","Ì","Ỉ","Ĩ","Ị","Ố","Ồ","Ổ","Ỗ","Ộ","Ô","Ớ","Ờ","Ở","Ỡ","Ợ","Ơ","Ó","Ò","Õ","Ỏ","Ọ","Ứ","Ừ","Ử","Ữ","Ự","Ư","Ú","Ù","Ủ","Ũ","Ụ","Ý","Ỳ","Ỷ","Ỹ","Ỵ"};
	String[] lowerChar = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","á","à","ả","ã","ạ","ă","ắ","ằ","ẳ","ẵ","ặ","â","ấ","ầ","ẩ","ẫ","ậ","é","è","ẻ","ẽ","ẹ","ê","ế","ề","ể","ễ","ệ","í","ì","ỉ","ĩ","ị","ó","ò","ỏ","õ","ọ","ô","ố","ồ","ổ","ỗ","ộ","ơ","ớ","ờ","ở","ỡ","ợ","ú","ù","ủ","ũ","ụ","ư","ứ","ừ","ử","ữ","ự","ý","ỳ","ỷ","ỹ","ỵ","đ"};
	String[] numChar = {"0","1","2","3","4","5","6","7","8","9"};
	String[] puncDateChar = {"-","/"};
	String[] puncHourChar = {":"};
	String[] abbrevChar = {"@","."};
	String[] otherChar = {"`","~","!","#","^","&","*","(",")","_","-","<",">"};
	String[] mathChar = {"+","-","*","/",":","="};
	String[] measureUnitChar = {"m/s","km/s","cm/s","l/m","l/km","km/h","kg/l","g/l"};
	String[] currencyChar = {"$","yên","nhân dân tệ","đồng","euro","tệ","đô la","au","đô-la","rs","kn","aud","irs","usd","hk$","can$","bt"};
	String[] measureChar = {"p","ha","ms","mcg","kcal","km","dm","cm","mm","nm","m2","km2","m3","km3","kg","mg","°","oc","of","khz","mhz","ghz","hz","khz","mhz","ghz","hz","pa","kj","pa","kwh","kw","mah","mW","mw","dl","cl","ml","bq","ft","m","l","W"};
	String[] romanChar = {"i","ii","iii","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX","XXX","XXXI","XXXII","XXXIII","XXXIV","XXXV","XXXVI","XXXVII","XXXVIII","XXXIX","XL","XLI","XLII","XLIII","XLIV","XLV","XLVI","XLVII","XLVIII","XLIX","L","LI","LII","LIII","LIV","LV","LVI","LVII","LVIII","LIX","LX","LXI","LXII","LXIII","LXIV","LXV","LXVI","LXVII","LXVIII","LXIX","LXX","LXXI","LXXII","LXXIII","LXXIV","LXXV","LXXVI","LXXVII","LXXVIII","LXXIX","LXXX","LXXXI","LXXXII","LXXXIII","LXXXIV","LXXXV","LXXXVI","LXXXVII","LXXXVIII","LXXXIX","XC","XCI","XCII","XCIII","XCIV","XCV","XCVI","XCVII","XCVIII","XCIX","C","CC","CCC","CD","D","DC","DCC","DCCC","CM","M"};
	String[] hourText = {"am","pm","h"};
	String[] minuteText = {"p"};
	String[] secText = {"s"};
	String[] dateText = {"ngày","sáng","trưa","chiều","tối"};
	String[] monthText = {"tháng"};
	String[] quyText = {"quý"};
	String[] yearText = {"năm"};
	String[] timeText = {"lúc","vào","hồi","khoảng"};
	String[] fractionText = {"chiếm","khoảng","trên","dưới","gần"};
	String[] scoreText = {"tỷ số","thắng","thua","hòa"};
	String[] romanText = {"phần","giai đoạn","cấp","đại hội","kỳ","khóa"};
	String[] abbr = {"gl","hqđt","tba","tth","tns","đh","gdđh","nha","gdtx","tnnt","dagtđt","xsmn","bgh","đcs","sp","xl","gpxd","hvannd","shnn","csđt","tvhk","yb","tdv","sđtla","ptn","ptthttđt","cskh","tphn","vlxd","tb","kntc","ptnn","lthđt","pctnxh","sg","lsq","đbtn","atnđ","hg","thvl","ace","mk","bgd","qcgnhh","pclbtw","nk","đn","dnvvn","cptd","kđt","sgkv","bhtn","csđttpmt","tdttqg","nltt","đcstq","pctubnd","cntn","nsnn","la","ntm","đhcn","nkkn","tthlttqg","gvc","tbmmn","qt","ubnd","pl","gđ","bđhn","gplx","kk","ptlc","ubcm","cmnd","gptm","nt","đhktcn","đhhhvn","svđv","xd","catp","klđt","klqg","đcsvn","qsd","tnhhmtv","hctc","cqld","tacn","dtxd","kcb","ha","ytdp","đttx","kkbhxh","kđcl","dtht","xhh","gtgt","tsdđ","tbktvn","yt","brvt","sxkd","đbvn","blđ","tbxh","cnch","pnkb","tbtt","tntn","kg","vp","nhctcn","đkhk","hđcdgscs","đna","tcdl","sk","th","đvcnt","pctt","tbn","lcđ","gv","slna","shtt","cntt","khcn","kdnpn","chdc","ubmttq","cspk","thkts","cskt","ttvh","spkt","bđqg","hlg","ps","xskt","đb","ktxh","ttgdck","csđn","xđgn","ctsn","bhx","gmd","ghn","nbcl","đttp","sgtvt","ctn","lđlđvn","bbcvt","pctpcnc","khxhnv","pclbmn","đbdtts","đhgtvt","đhđd","đtnn","mtv","thcb","gcnsk","hmnđ","kn","cn","q","kđclgd","lc","qđxpvphc","dthcp","tkbvtc","mtxt","ttxh","đtvn","ms","qc","tvn","đs","chnd","tbktsg","nvhtn","tandtc","gdnn","xdtm","pctttkcn","đtla","đhbk","dnnn","hđt","cm","tcn","hk","cbcnv","ptth","bchtư","tq","ubck","xttm","uvbch","hn","dvvl","tctt","nmđ","tthtsv","nd","stt","hđtđ","tcnn","ttksgt","qh","hsv","tmđt","đttm","pk","sgd","tm","đhnnhn","cc","tbcn","bch","ncs","nhnt","tntp","ghtk","tx","bp","xnk","sđnđ","hkhkt","vltk","cmtt","cty","bql","bctc","bxh","gd","cnxh","nhcthp","thcsnđ","atvstp","thptdtnt","cpxdsxtm","đkkd","bl","btv","ttvn","đvht","ctcc","kdl","hđđb","khđt","tvqh","gcnqsdđ","tttt","qshnqsdđ","tc","lđtb","khkths","đhcđ","bcnckt","tdp","đttxqm","ctđl","ttdv","vđv","tnđt","đđ","cv","nxbqđnd","đhnt","đsq","hs","nsx","sx","blhs","hlv","đlđt","anđt","bt","nncđdc","sl","lhtn","tsn","ntk","qtkd","ut","sd","syll","bcđ","hsđkdt","antđ","khktnn","csvc","kdhđc","ghpgvn","bcđts","svhs","skss","sggp","GĐKT","hv","chxhcnvn","vl","cnkt","đhkhxh","ttyt","hl","đta","cnh","ptsx","xn","tpct","bcđđmđh","vck","ql","nst","mtđt","kl","UBQLV","qctđhn","đct","cđv","ykvn","lmlm","gcnqshnơ","ag","đhbkhn","ctch","chlb","mssv","bvđk","hqhp","đtv","vh","đhdl","đtlt","lđ","tcqt","tư","đbdt","gtvt","đbtsxb","ctth","hđkt","pcgdbth","vpls","skhđt","xh","gtmt","btbnn","khhgđ","cntttt","pp","vpđd","hb","pghh","kkt","gstskh","bs","tđbkvn","bđn","sxtm","htqt","ubndtp","ub","vđqg","hđtt","ltqđtd","ts","nv","chk","tnkq","hđxx","ntđ","ctđ","gsts","htx","pcsddte","khkthn","pv","blv","rlcd","HKVN","hlhtnvn","vksndtc","bbt","ktx","nhctvn","cnqsdđ","ttvgt","xklđ","vhttdl","kdc","tchq","pcmttp","đhbc","sdđ","hht","pcmt","hvch","vksnd","csvn","dv","hhc","pcccchcn","cttt","gt","tmcp","bcvt","pct","tccn","pt","kt","khktnnmn","đmn","tncn","qhtd","đsvn","xnql","tthc","gđđh","hđnt","thcs","ubtvqh","nh","thads","csqlhc","tctđô","tpcđ","đg","mn","nlđ","qshnơ","tmn","ptt","cb","dvtt","hp","uv","tp","qlđkxlx","hvcnbcvt","bhđc","cx","svđ","ccv","lh","cnv","ncl","qchq","st","ttct","hđcdgsn","bks","bh","ct","hd","skhcnmt","nhđt","ttth","svnckh","hhtg","đbsh","lđbđ","bc","pgđ","vn","mt","bcđpctntư","mmt","ndo","tcmn","hvbcvt","tnsvvn","tw","bhxh","hđcd","lnst","ttk","đhqghn","dd","ttbdvh","nhnnvn","antt","pclb","cap","đvttn","ctck","hkddvn","tv","bhyt","cscđ","hvnclc","nckh","qcdccs","svtn","tnk","tk","tcvsg","tcttmsg","đrl","pctn","đc","ubpl","ccspctpmt","cnkqt","qn","nvh","tnmt","gcnqsh","nxbgd","tndn","tkcn","đhn","đd","đbp","ktqdhn","na","qđnd","ch","nxbtn","ttgt","thx","cskv","gcnqshn","thktsmđ","nmct","đspl","vpcp","svvn","đtvhd","h","qckdtxd","tđ","gpmb","hcb","lđbđvn","vcgt","lhs","nhtmnn","cmt","ubcknn","ctxh","ptnt","csđttp","ktt","kđtm","cđsp","lb","pslđ","ttqlkt","hngđ","lntt","qg","ttttn","vhxh","lhq","tpdn","nxb","atgt","khxh","mxh","vs","plo","cpttt","bd","đbqh","hđh","qlđt","htxdvnn","tcmr","nhtw","ttdvvl","ubgstc","hvn","hsgqg","qlda","đhkh","tg","cchc","pgs","hnv","tctd","TTCP","cbqlgd","gvtn","hctl","khtn","qsdđ","dnvn","gvmn","thtt","tdtt","hđcdgs","bhl","đđv","snn","tđc","tcbvntd","đtnđ","hphn","gk","khls","sđk","mbh","xdhk","đhclc","bxl","lmht","nqd","đk","tnxk","pcgdth","bgđ","tpcn","hlbđ","tths","tand","ttn","ttatgt","nđvn","nsưt","đhkhtnhn","ko","nsđp","nxbvhtt","gdgt","vks","caq","cssx","clb","cahn","tgdđ","plvn","ktm","tmdv","ktđt","csdc","dl","bx","csdl","ptdtbt","khkd","vthkcc","gcn","nkbv","tct","hagl","attt","bg","gđtla","shtd","vhnt","gdđt","cncn","đhkt","cbpg","dnt","pm","tnln","tn","ktv","nhnn","kv","rlc","kcx","ntbd","sgtt","tbt","ktst","kst","đhbktphcm","pgd","nsnd","ctcp","phhs","pgsts","đdv","CQĐT","đtxdcb","ss","đt","pcgdthcs","hhvn","dcch","hlqg","bđbp","pcbl","nxbkhxh","pcbth","ttdd","uvbct","csvl","đv","ls","nhtm","hcvl","cp","npp","đl","lhpn","tncs","đtb","annd","xhcn","tnvn","bqlda","đbscl","csg","mttqvn","nhđá","tttm","dlbc","kdđv","hh","qk","gddt","hđđg","nđ","tcvn","dn","cttnhh","ttnn","hvkhqs","cđ","cpxd","xsmb","scn","tnsv","vhgdttnnđ","bđ","pcgd","sdđnn","qcvn","TQ","nhnnptntvn","gvg","nn","thdl","nvơnn","noxh","ccb","qb","mst","vđvqg","vnđ","đhqg\00đại học sư phạm","btc","ttck","nhm","cshs","ubtdtt","gcnqsdđơ","chdcnd","gvcn","sgk","ưcv","hnht","bvmt","hđndtp","ttbđs","vđ","vđtg","bgdđt","tpo"," CLC","ttbvqtg","ubkt","sn","nhcsxh","sh","nđt","thpt","dntn","cbcc","bts","thcn","py","đhktqd","vv","nhnnptnt","qđ","ubkttw","đkkh","đhbkhcm","cand","ttks","ttxvn","hcm","đbdtin","dvtmcm","atttgt","bvtv","hsbc","hđct","tvtw","hdv","thgt","hkhktvn","da","sđt","tmxd","qltt","nkt","vhtt","nq","hđlđ","pctp","nnptnt","ksnd","hđba","gplhđb","cbnvlđ","ca","ccnv","tnv","gcnqsd","ptv","gp","gpnk","lđlđ","đvtn","htxnn","skhcn","nltd","hvncl","ll","cmcn","hđnd","đtdđ","cvpm","nlmt","qbltd","kđs","nơxh","cbcs","kbnn","cbnv","cnvc","vhgdtntn","tvv","btvh","hc","xk","gcq","csgt","hspt","nctnt","ptcs","lct","đkvn","pttm","xx","qsmt","tnt","pcthcs","hy","hq","tckh","ttđm","kp","hđts","bchqs","đhđb","pcc","nhtmcp","hnd","ck","ksv","stda","hm","htcđ","smđh","hsg","tbd","nls","nc","ctv","hđqt","attp","kts","llvt","ths","tttđb","bđs","lđtbxh","ttdl","csht","vnclc","đkvđ","đkđt","hđ","sxh","đtđ","đvưt","cttc","đkdt","bđkh","đtqg","pccc","chxhcn","ntd","vt","ptdt","sđh","đhđcqt","kh","bcđt","ubqgtkcn","xdcb","ttton","ksndtc","vc","csxh","ks","kđtntl","nctt","bcđqg","ttcn","csđtlx","tgpl","gđt","vb","qsdđơ","kttv","ns","nknv","BTTN","hktt","bv","hcđ","pn","tnhh","pc","tckt","nhtmqd","qld","đhhb","gs","qckt","hsgqt","lhp","bchtw","dhs","ttcp","nl","ht","bn","td","kdcn","cnhhđh","ndt","ttgdtx","chxh","ttđt","hđtv","vd","đhđcđ","dt","ntls","tgđ","cnsh","hđcdgsnn","cs","hcv","sbd","đhđn","hnbvn","hssv","kcn","kdn","thvn","tngt","tnđh","qsqp","khkt","gstt","pcbltkcn","ctcn","tccs","bgk","mttq","sv","ttytdp","dvtm","CTCK","mhx","dtkv","tphcm","pttt","ctclqg","kcbcnn","gcnkqt","lllđ","sxtmdv","thqg","tchl","gđtt","đhct","tskh","hvnh","đhkhtn","bđvn","cqđt","tt","cq","ttlt","ttbyt","đkxt","tnhc","hsd","tvtu","ds","gđkt","ddvn","dtsd","cđdc","ttbdvhng","tthtcđ","tnxh","p","gcnđkkd","bk","gvth","ktnn","nhđtpt","ncc","bst","nb","tnnd","gcmnd","đhbkđn","kddv","bchcd","vqg","ubmttqvn","ktqd","tnxp","gtcc","hkdd","chcn","pcth","cph"};
	String[] prefixAndSuffix = {"de","dis","ex","il","im","in","mis","non","pre","pro","re","un","able","al","er","est","ful","ible","ily","ing","less","ly","ness","y"};
	public ArrayList<String> dauCharList,nguyenAmCharList,phuAmCharList,nuocNgoaiCharList,upperCharList,lowerCharList,numCharList,puncDateCharList,puncHourCharList,abbrevCharList,otherCharList,mathCharList,measureUnitCharList,currencyCharList,measureCharList,romanCharList,dateTextList,monthTextList,quyTextList,yearTextList,hourTextList,minuteTextList,secTextList,timeTextList,fractionTextList,scoreTextList,romanTextList,abbrList,prefixAndSuffixList;
	public Vectorizator(){
		dauCharList = new ArrayList<String>(Arrays.asList(dauChar));
		nguyenAmCharList = new ArrayList<String>(Arrays.asList(nguyenAmChar));
		phuAmCharList = new ArrayList<String>(Arrays.asList(phuAmChar));
		nuocNgoaiCharList = new ArrayList<String>(Arrays.asList(nuocNgoaiChar));
		upperCharList = new ArrayList<String>(Arrays.asList(upperChar));
		lowerCharList = new ArrayList<String>(Arrays.asList(lowerChar));
		numCharList = new ArrayList<String>(Arrays.asList(numChar));
		puncDateCharList = new ArrayList<String>(Arrays.asList(puncDateChar));
		puncHourCharList = new ArrayList<String>(Arrays.asList(puncHourChar));
		abbrevCharList = new ArrayList<String>(Arrays.asList(abbrevChar));
		otherCharList = new ArrayList<String>(Arrays.asList(otherChar));
		mathCharList = new ArrayList<String>(Arrays.asList(mathChar));
		measureUnitCharList = new ArrayList<String>(Arrays.asList(measureUnitChar));
		currencyCharList = new ArrayList<String>(Arrays.asList(currencyChar));
		measureCharList = new ArrayList<String>(Arrays.asList(measureChar));
		romanCharList = new ArrayList<String>(Arrays.asList(romanChar));
		dateTextList = new ArrayList<String>(Arrays.asList(dateText));
		monthTextList = new ArrayList<String>(Arrays.asList(monthText));
		quyTextList = new ArrayList<String>(Arrays.asList(quyText));
		yearTextList = new ArrayList<String>(Arrays.asList(yearText));
		hourTextList = new ArrayList<String>(Arrays.asList(hourText));
		minuteTextList = new ArrayList<String>(Arrays.asList(minuteText));
		secTextList = new ArrayList<String>(Arrays.asList(secText));
		timeTextList = new ArrayList<String>(Arrays.asList(timeText));
		fractionTextList = new ArrayList<String>(Arrays.asList(fractionText));
		scoreTextList = new ArrayList<String>(Arrays.asList(scoreText));
		romanTextList = new ArrayList<String>(Arrays.asList(romanText));
		abbrList = new ArrayList<String>(Arrays.asList(abbr));
		prefixAndSuffixList = new ArrayList<String>(Arrays.asList(prefixAndSuffix));
		}
	public List<Integer> asciiTransform(String inputString,int maxLen){
		List<Integer> vector = new ArrayList<Integer>();
		for (int i = 0; i < Math.max(inputString.length(), maxLen); i++) {
			if (i < inputString.length()){
				vector.add((int) inputString.charAt(i));
			}
			else {
				vector.add(0);
			}
		}
		return vector;
	}
	public int CountChar(String nsw,ArrayList<String> test) {
		int count = 0;
		for (int j = 0; j < nsw.length(); j++) {
			String charString = Character.toString(nsw.charAt(j));
			if (test.contains(charString)) {
				count ++;
			}
		}
		return count;
	}
	public int containsKeyword(String myString, ArrayList<String> keywords){
		   for(String keyword : keywords){
		      if(myString.contains(keyword)){
		         return 1;
		      }
		   }
		   return 0;
		}
	
	public List<Integer> makeSingleCase(String nsw,String contextBefore, String contextAfter){
		List<Integer> vector = new ArrayList<Integer>();
//		feature count 13
		vector.add(nsw.length());
		vector.add(CountChar(nsw, dauCharList));
		vector.add(CountChar(nsw, nguyenAmCharList));
		vector.add(CountChar(nsw, phuAmCharList));
		vector.add(CountChar(nsw, nuocNgoaiCharList));
		vector.add(CountChar(nsw, upperCharList));
		vector.add(CountChar(nsw, lowerCharList));
		vector.add(CountChar(nsw, numCharList));
		vector.add(CountChar(nsw, puncDateCharList));
		vector.add(CountChar(nsw, puncHourCharList));
		vector.add(CountChar(nsw, abbrevCharList));
		vector.add(CountChar(nsw, otherCharList));
		vector.add(CountChar(nsw, mathCharList));
//		feature occur nsw 9
		vector.add(containsKeyword(nsw.toLowerCase(),measureUnitCharList));
		vector.add(containsKeyword(nsw.toLowerCase(),currencyCharList));
		vector.add(containsKeyword(nsw.toLowerCase(),measureCharList));
		vector.add(containsKeyword(nsw,romanCharList));
		vector.add(containsKeyword(nsw.toLowerCase(),hourTextList));
		vector.add(containsKeyword(nsw.toLowerCase(),minuteTextList));
		vector.add(containsKeyword(nsw.toLowerCase(),secTextList));
		vector.add(containsKeyword(nsw.toLowerCase(),abbrList));
		vector.add(containsKeyword(nsw.toLowerCase(),prefixAndSuffixList));
//		feature occur context before 8
		vector.add(containsKeyword(contextBefore.toLowerCase(),dateTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),monthTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),quyTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),yearTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),timeTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),fractionTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),scoreTextList));
		vector.add(containsKeyword(contextBefore.toLowerCase(),romanTextList));
//		ascii 20 nsw 30 context
		vector.addAll(30,asciiTransform(nsw,30));
//		vector.add(-1);
		vector.addAll(60,asciiTransform(contextBefore,40));
//		vector.add(-1);
		vector.addAll(100,asciiTransform(contextAfter,40));
		return vector;
	}
	
	public List<List<Integer>> makeFeatureSentence(String inputString){
		List<List<Integer>> listVector = new ArrayList<List<Integer>>();
		Pattern pattern = Pattern.compile("<nsw[^>]*>([^<]*)</nsw>");
		Matcher m = pattern.matcher(inputString);
		int lenString = inputString.length();
		while (m.find()) {
			int start = m.start();
			int end = m.end();
			String nsw = inputString.substring(start + 5 ,end - 6);
			String contextBefore = inputString.substring(Math.max(0, start - 40),start);
			String contextAfter  = inputString.substring(end,Math.min(lenString, end + 40));
//			System.out.println("contextBefore : " + contextBefore);
//			System.out.println("contextAfter  : " + contextAfter);
//			System.out.println("nsw           : " + nsw);
			listVector.add(makeSingleCase(nsw,contextBefore,contextAfter));
		}
		return listVector;
	}
	public List<Integer> vectorize(String sentence){
		List<Integer> vector = new ArrayList<Integer>();
		vector.add(CountChar(sentence, nguyenAmCharList));
		return vector;
	}
	public static void main(String[] args) {   
		String sentence = "<nsw> 12/1/2019 </nsw> vaf ngafy <nsw> 12/1/2019 </nsw>";
//		String nsw = "2-4";    
//		String contextLeft = "năm";    
//		String contextRight = "chiếm";    
        int count = 0;    
        Vectorizator vector = new Vectorizator();
        List<List<Integer>> charCount = vector.makeFeatureSentence(sentence);
//        ArrayList<String> output = vector.getNswContext(string);
//        System.out.println(charCount.size() + " Total number of characters in a string: " + charCount);    

        System.out.println("Size = "+ charCount.size());
//        for (int i=0; i<charCount.size();i++)
//            System.out.println(charCount.get(i).size());
        System.out.println(charCount.get(0));
    }    
}
