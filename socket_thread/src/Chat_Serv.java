import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Chat_Serv extends Thread{
	
	private Socket s; 
	private int i;
	 public Chat_Serv(Socket s,int i){
		this.s=s;
		this.i=i;
	}
	public void run() {
		try {
			InputStreamReader input=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(input);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			String ch=s.getRemoteSocketAddress().toString();
			interface_Serveur.txt.append("client num "+i+" connecté son adresse IP= "+ch+"\n");
			
			pw.println("Bienvenue,vous etes client numéro "+i+" avec adresse "+ch);
			pw.flush();
			pw.println("essayer de déterminer mon nombre secret entre 0 et 200");
			pw.flush();
			while (true){
				String req;
			while((req=br.readLine())!=null){
				interface_Serveur.txt.append("client num "+i+" ,adresse IP="+ch+"a envoyé"+req+"\n");
				
				int nb=Integer.parseInt(req);
				
				if(Projet_Serveur.f==false){
					if(nb<Projet_Serveur.nbre_secret){
						pw.println("votre nombre est plus petit!!");
						pw.flush();
					}
					else if(nb>Projet_Serveur.nbre_secret){
						pw.println("votre nombre est plus grand!!");
                        pw.flush();
					}
					else if(nb==Projet_Serveur.nbre_secret){
						Projet_Serveur.ip_gagnat=ch;
						Projet_Serveur.num_gagnt=i;
						Projet_Serveur.f=true;
						pw.println("Bravo vous avez gagner!!!");
						pw.flush();
						interface_Serveur.txt.append("client num "+Projet_Serveur.num_gagnt+"avec adresse IP= "+ch+" gagne le jeu\n");
	                  
					}
				}
				else{
					pw.println("Le jeu est terminé le gagnat est "+Projet_Serveur.ip_gagnat);
					pw.flush();
				}
				
			}}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
