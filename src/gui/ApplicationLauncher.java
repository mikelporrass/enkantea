package gui;

import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher {



	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: "+Locale.getDefault());

		//		----------------------------------------------------
		HasieraGUI HasieraPantaila=new HasieraGUI();
		HasieraPantaila.setVisible(true);
		//		----------------------------------------------------

		try {

			BLFacade appFacadeInterface;
			//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			if (c.isBusinessLogicLocal()) {

				appFacadeInterface=new BLFacadeImplementation();


			}

			else { //Si es remoto

				//String serviceName="http://localhost:9999/ws/ruralHouses?wsdl";
				String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";

				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);


				//1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

				Service service = Service.create(url, qname);

				appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
			 */
			HasieraGUI.setBussinessLogic(appFacadeInterface);
			ErregistratuGUI.setBussinessLogic(appFacadeInterface);
			BezeroGUI.setBussinessLogic(appFacadeInterface);
			LangileGUI.setBussinessLogic(appFacadeInterface);
			JabeGUI.setBussinessLogic(appFacadeInterface);
			EskaintzaEginGUI.setBussinessLogic(appFacadeInterface);
			NireEnkanteakIkusiGUI.setBussinessLogic(appFacadeInterface);
			EnkanteaSortuGUI.setBussinessLogic(appFacadeInterface);
			DiruaSartuGUI.setBussinessLogic(appFacadeInterface);
			EnkanteakBistaratuGUI.setBussinessLogic(appFacadeInterface);
			IrabazitakoEnkanteakGUI.setBussinessLogic(appFacadeInterface);
			EnkanteaItxiGUI.setBussinessLogic(appFacadeInterface);
			//EskaintzaGUI.setBussinessLogic(appFacadeInterface);
			
			
			//RegisterGUI.setBussinessLogic(appFacadeInterface);
			//FindQuestionsGUI.setBussinessLogic(appFacadeInterface);
			//AdminGUI.setBussinessLogic(appFacadeInterface);
			//LangileaGUI.setBussinessLogic(appFacadeInterface);
			//ErabiltzaileaGUI.setBussinessLogic(appFacadeInterface);
			//SeeMugimenduakGUI.setBussinessLogic(appFacadeInterface);
			//InsertMoneyGUI.setBussinessLogic(appFacadeInterface);
			//CreateFeesGUI.setBussinessLogic(appFacadeInterface);
			//CreateQuestionGUI.setBussinessLogic(appFacadeInterface);
			//CreateWorkerGUI.setBussinessLogic(appFacadeInterface);
			//DeleteFeesGUI.setBussinessLogic(appFacadeInterface);
			//PutResultGUI.setBussinessLogic(appFacadeInterface);
			//DeleteUserGUI.setBussinessLogic(appFacadeInterface);



		}catch (Exception e) {
			//a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();


	}
}
