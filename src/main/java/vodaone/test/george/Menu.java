package vodaone.test.george;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vodaone.test.george.WebActions.AddMobileNumbers;
import vodaone.test.george.WebActions.ChangeServiceType;
import vodaone.test.george.WebActions.DeleteMobile;
import vodaone.test.george.WebActions.GetAllNumbers;
import vodaone.test.george.WebActions.GetMobileNumber;

/*
 * CLI Menu - To perform actions to the database using the Web Controller
 */

public class Menu {
	private static final String TITLE =
			"\nVodafone Test - "+
			"  ScerriMontaldo_George\n\n"+
			"\t********************\n"+ "\t1. Get All Mobiles \n"+ "\t2. Search Mobile \n"+"\t3. Add Mobile \n"+ "\t4. Change Mobile Plan \n" + "\t5. Delete Mobile \n" + "\t0. Exit \n"+"\t********************\n"+"Please input a single digit (0-5):\n";

				public Menu() {
					int selected = -1;
					while (selected != 0) {
						System.out.println(TITLE);
						BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
						// selected = Integer.parseInt(in.readLine());
						try {
							selected = Integer.parseInt(in.readLine());
							switch (selected) {
							case 1:
								q1();
								break;
							case 2:
								q2();
								break;
							case 3:
								q3();
								break;
							case 4:
								q4();
								break;
							case 5:
								q5();
								break;
							}
						} catch (Exception ex) {
						}
					} // end while
					System.out.println("Bye!");
				}

				// Modify the types of the methods to suit your purposes...
				private void q1() throws IOException, InterruptedException {					
					new GetAllNumbers();
					GetAllNumbers.getAllNumbers();
					
				}

				private void q2() {
					new GetMobileNumber();
					GetMobileNumber.beginSearchMobileNumberProccess();
				}

				private void q3() throws IOException {
					new AddMobileNumbers();
					AddMobileNumbers.beginAddingMobileNumberProcess();
					}

				private void q4() {
					new ChangeServiceType();
					ChangeServiceType.beginSearchMobileNumberProccess();
				}
				
				private void q5() {
					new DeleteMobile();
					DeleteMobile.beginDeleteMobileNumberProcess();
				}

				public static void main(String[] args) {
					new Menu();
				}
				
				public static void resetMenu() {
					VodafoneTestApplication.firstRun = false;

				}
			}


