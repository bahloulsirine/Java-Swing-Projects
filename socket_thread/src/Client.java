import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame{

	JTextField t_msg;
	 static public JTextArea txte;
	 JButton envoyer;
	 JPanel pan;
	 static Socket s;
	public Client() {
		
	
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Client");
		this.setSize(800, 300);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		
		envoyer=new JButton("envoyer");
		t_msg=new JTextField(40);
		pan=new JPanel();
		pan.add(t_msg);
		pan.add(envoyer);
		this.add(pan,BorderLayout.SOUTH);
		
		txte=new JTextArea();
		this.add(txte,BorderLayout.CENTER);
		txte.setEnabled(false);
		envoyer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
						try {
					String ch=t_msg.getText();
					boolean test=true;
					int i=0;
					while((i<ch.length())&&test){
						char x=ch.charAt(i);
						int ord=(int)x;
						test=((ord<58)&&(ord>47));
						i++;
					}
					
					if(test){
						InputStreamReader input=new InputStreamReader(s.getInputStream());
						BufferedReader br=new BufferedReader(input);
						
						PrintWriter pw = new PrintWriter(s.getOutputStream());
						pw.println(ch);
						pw.flush();
						txte.append(ch+"\n");
						String ch1=br.readLine();
						txte.append(ch1+"\n");
						t_msg.setText("");}
					else if(test==false){
						JOptionPane.showMessageDialog(Client.this, "Erreur:Vous DEVEZ ECRIRE SEULEMENT DES ENTIERS");

						t_msg.setText("");
				
						
					}}
				catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
		});
		t_msg.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					
					
				
					try {
						
						
						String ch=t_msg.getText();
						boolean test=true;
						int i=0;
						while((i<ch.length())&&test){
							char x=ch.charAt(i);
							int ord=(int)x;
							test=((ord<58)&&(ord>47));
							i++;
						}
						
						if(test){
							InputStreamReader input=new InputStreamReader(s.getInputStream());
							BufferedReader br=new BufferedReader(input);
							
							PrintWriter pw = new PrintWriter(s.getOutputStream());
							pw.println(ch);
							pw.flush();
							txte.append(ch+"\n");
							String ch1=br.readLine();
							txte.append(ch1+"\n");
							t_msg.setText("");}
						else if(test==false){
							JOptionPane.showMessageDialog(Client.this, "Erreur:Vous DEVEZ ECRIRE SEULEMENT DES ENTIERS");

							t_msg.setText("");
					
							
						}}
					catch (UnknownHostException x) {
						// TODO Auto-generated catch block
						x.printStackTrace();
					} catch (IOException x) {
						// TODO Auto-generated catch block
						x.printStackTrace();
					}
				}
			}
		});
		
}
	
	
	public static void main(String[] args) {
		System.out.println("sirine");
		System.out.println("démarrage client...");
		
		try {
			s=new Socket("127.0.0.1",7000);
			JFrame c=new Client();
			
			InputStreamReader input=new InputStreamReader(s.getInputStream());
			BufferedReader br=new BufferedReader(input);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			Scanner sc=new Scanner(System.in);
			String ch=br.readLine();
			txte.append(ch+"\n");
			ch=br.readLine();
			txte.append(ch+"\n");
			
			while(Projet_Serveur.f==false){
			
				int n=sc.nextInt();
				pw.println(n);
				pw.flush();
				txte.append(n+"\n");
				ch=br.readLine();
				txte.append(ch+"\n");
				
			}
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR IP");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR PORT");
		}
	}

}
