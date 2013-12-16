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
				result.append("����");
			}

			BigDecimal decimalNumber = new BigDecimal(number);

			String[] numbers = new String[10];

			numbers[1] = "�����";
			numbers[2] = "���";
			numbers[3] = "���";
			numbers[4] = "������";
			numbers[5] = "���";
			numbers[6] = "����";
			numbers[7] = "�����";
			numbers[8] = "����";
			numbers[9] = "�����";

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
							result.append("���");
							break;
						default:
							if (hundreds > 2) {
								result.append(numbers[hundreds]);
							}
							break;
					}
					
					switch (hundreds) {
						case 1:
							result.append("�������");
							break;
						case 2:
							result.append("�������");
							break;
						case 3:
							result.append("�������");
							break;
						case 4:
							result.append("�������");
							break;
						default:
							if (hundreds > 4) {
								result.append("�������");
							}
							break;
					} // end of hundreds check

					if (single != 0) {
						sl1 = numbers[single];
					}

					switch (tens) {
						case 4:
							result.append("����");
							break;
						case 6:
							result.append("���");
							break;
						case 5:
							result.append("��");
							break;
						case 9:
							result.append("����");
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
									result.append("�����");
									break;
								case 1:
									result.append("����");
									break;
								case 4:
									result.append("����");
									break;
								default:
									result.append(numbers[single]);
									break;
								
							} // end of switch tens case 1
							
							if (single > 0) {
								result.append("�����");
							}
							break;

					} // end of switch tens
					
					 if(tens > 1) {
						 result.append("�����");
			         }
					
					if ( (i == 3 || i == 9) && tens != 1 ) {
						if (single == 1) {
							sl1 = "�����";
						} else if (single == 2){
							sl1 = "���";
						}
					}
					
					result.append(sl1);
					
					switch (i) {
					case 0:
						result.append("������");
						if (single > 1 || tens == 1) {
							result.append("�");
						}
						break;
					case 3:
						result.append("�������");
						if ( (triple % 100) > 11 && (triple % 100) < 19) {
							result.append("�");
							
						} else if (single == 1) {
							result.append("�");
							
						} else if (single == 4) {
							result.append("�");
							
						} else if (single > 1) {
							result.append("�");
							
						} 
						break;
					case 6:
						result.append("������");
						
						if ( ((triple % 100) > 11 && (triple % 100) < 19) || single != 1 ) {
							result.append("�");
						}
						break;
						case 9:
							result.append("����");
							 if ( ((triple % 100) > 11 && (triple % 100) < 19) || single == 1 ) {
								 result.append("�");
								 
							 } else if (triple == 1){
								 result.append("�");
			                     
			                 } else if (single > 4 || single == 0){
			                	 result.append("�");
			                     
			                 } else if (single > 1){
			                	 result.append("�");
			                 }
							break;

					} // end of switch i
					
				}

			}
			
			inWords += result.toString();
			
			if ( decimals > 0 ) {
				inWords += " " + decimals + "/100";
				
			}
			
			inWords += " ���.";
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "����� ���������� �����. ������� ���� . �� ��������!");
		}
		
		

	} // end of inToString method
} // end of the BrojeviSlovima class
