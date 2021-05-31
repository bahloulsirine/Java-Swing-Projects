import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class Projet_Serveur extends Thread {

	int i=0;
	static public boolean f=false;
	static public int nbre_secret;
	
	static public String ip_gagnat;
	static public int num_gagnt;
	
	
	@Override
	public void run() {
		try {
			ServerSocket s=new ServerSocket(7000);
			nbre_secret=(int)(Math.random()*200);
			while(true){
				Socket so=s.accept();
				i++;
				new Chat_Serv(so,i).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
              new interface_Serveur();
              interface_Serveur.txt.append("serveur en ecoute \n");
              new Projet_Serveur().start();

	}

}
