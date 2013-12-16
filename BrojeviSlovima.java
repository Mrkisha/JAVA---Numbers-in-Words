package brojeviSlovima;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class BrojeviSlovima {
	String str = "";
	StringBuilder result = new StringBuilder();
	String inWords = "";
	String sl1 = "";
	
	public void saveToClipboard() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);

	}

	void inToString(String number) {
		try {
			BigDecimal bd1 = new BigDecimal(number);
			
			if (new BigDecimal(number).compareTo(BigDecimal.ZERO) == 0) {
				result.append("нула");
			}

			BigDecimal decimalNumber = new BigDecimal(number);

			String[] numbers = new String[10];

			numbers[1] = "један";
			numbers[2] = "два";
			numbers[3] = "три";
			numbers[4] = "четири";
			numbers[5] = "пет";
			numbers[6] = "шест";
			numbers[7] = "седам";
			numbers[8] = "осам";
			numbers[9] = "девет";

			long whole = decimalNumber.intValue();
			long decimals = (long) ((decimalNumber.doubleValue() - whole) * 100) % 100;

			String wholeNumberString = String.valueOf(whole);

			int length = 15 - wholeNumberString.length();

			StringBuilder sb = new StringBuilder(length);
			for (int i = 0; i < length; i++) {
				sb.append("0");
			}

			String wNumber = sb.toString() + wholeNumberString;
			

			for (int i = 0; i < 15; i = i + 3) {
				String tri = wNumber.substring(i, i + 3);
				
				int triple = Integer.parseInt(tri);
				
				if ( !tri.equals("000") ) {
					int hundreds = Integer.parseInt(tri.substring(0, 1));
					int tens = Integer.parseInt(tri.substring(1, 2));
					int single = Integer.parseInt(tri.substring(2, 3));
					
					// check for hundreds
					switch (hundreds) {
						case 2:
							result.append("две");
							break;
						default:
							if (hundreds > 2) {
								result.append(numbers[hundreds]);
							}
							break;
					}
					
					switch (hundreds) {
						case 1:
							result.append("стотину");
							break;
						case 2:
							result.append("стотине");
							break;
						case 3:
							result.append("стотине");
							break;
						case 4:
							result.append("стотине");
							break;
						default:
							if (hundreds > 4) {
								result.append("стотина");
							}
							break;
					} // end of hundreds check

					if (single != 0) {
						sl1 = numbers[single];
					}

					switch (tens) {
						case 4:
							result.append("четр");
							break;
						case 6:
							result.append("шез");
							break;
						case 5:
							result.append("пе");
							break;
						case 9:
							result.append("деве");
							break;
						case 2:
							result.append(numbers[tens]);
							break;
						case 3:
							result.append(numbers[tens]);
							break;
						case 7:
							result.append(numbers[tens]);
							break;
						case 8:
							result.append(numbers[tens]);
							break;
						case 1:
							sl1 = "";
							
							switch (single) {
								case 0:
									result.append("десет");
									break;
								case 1:
									result.append("једа");
									break;
								case 4:
									result.append("четр");
									break;
								default:
									result.append(numbers[single]);
									break;
								
							} // end of switch tens case 1
							
							if (single > 0) {
								result.append("наест");
							}
							break;

					} // end of switch tens
					
					 if(tens > 1) {
						 result.append("десет");
			         }
					
					if ( (i == 3 || i == 9) && tens != 1 ) {
						if (single == 1) {
							sl1 = "једна";
						} else if (single == 2){
							sl1 = "две";
						}
					}
					
					result.append(sl1);
					
					switch (i) {
					case 0:
						result.append("билион");
						if (single > 1 || tens == 1) {
							result.append("а");
						}
						break;
					case 3:
						result.append("милијард");
						if ( (triple % 100) > 11 && (triple % 100) < 19) {
							result.append("и");
							
						} else if (single == 1) {
							result.append("а");
							
						} else if (single == 4) {
							result.append("и");
							
						} else if (single > 1) {
							result.append("е");
							
						} 
						break;
					case 6:
						result.append("милион");
						
						if ( ((triple % 100) > 11 && (triple % 100) < 19) || single != 1 ) {
							result.append("а");
						}
						break;
						case 9:
							result.append("хиљад");
							 if ( ((triple % 100) > 11 && (triple % 100) < 19) || single == 1 ) {
								 result.append("а");
								 
							 } else if (triple == 1){
								 result.append("у");
			                     
			                 } else if (single > 4 || single == 0){
			                	 result.append("а");
			                     
			                 } else if (single > 1){
			                	 result.append("е");
			                 }
							break;

					} // end of switch i
					
				}

			}
			
			inWords += result.toString();
			
			if ( decimals > 0 ) {
				inWords += " " + decimals + "/100";
				
			}
			
			inWords += " дин.";
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Унеси одговарајућу цифру. Користи знак . за децимале!");
		}
		
		

	} // end of inToString method
} // end of the BrojeviSlovima class
