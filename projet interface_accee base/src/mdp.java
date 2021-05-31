import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class mdp extends JFrame {
    JLabel l_mdp,l_bnj; JPasswordField t; JButton b;JPanel p;JFrame f;
    String url="jdbc:mysql://127.0.0.1/tp4_projet";
	String user="root";
	String mdp="";
	
    mdp() {
		this.setTitle("Authentification");
		this.setSize(500,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 Connexion_base cn=new Connexion_base(url, user, mdp);
	        cn.Supprimertout();
	        
		l_bnj=new JLabel("BIENVENUE");
		l_bnj.setBackground(new Color(161, 113, 136));
		l_bnj.setHorizontalAlignment(JLabel.CENTER);
		l_bnj.setForeground(Color.BLACK);
	    l_bnj.setOpaque(true);
	    l_bnj.setFont(new Font("Arial", Font.ITALIC, 36));
	    this.add(l_bnj,BorderLayout.NORTH);
	    
	    
		l_mdp= new JLabel("ecrire mot de passe");
		l_mdp.setForeground(new Color(161, 113, 136));
		l_mdp.setOpaque(true);

		t=new JPasswordField(7);
		t.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					String ch=t.getText();
					if(ch.equals("issatso")){
						Fenetre_Tp4 f2=new Fenetre_Tp4();
				        f2.setVisible(true);
				        dispose();
				    }
					else{
						JOptionPane.showMessageDialog(mdp.this, "mot de passe fausse vous devez réessayer");
						t.setText("");


					}
				}
			}
		});
		p=new JPanel();
		p.add(l_mdp);
		p.add(t);
		
		b= new JButton("Se connecter");
		b.setForeground(new Color(161, 113, 136));
		b.setOpaque(true);
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String ch=t.getText();
				if(ch.equals("issatso")){
					Fenetre_Tp4 f2=new Fenetre_Tp4();
			        f2.setVisible(true);
			       
			        dispose();
			        
				}
				else{
					JOptionPane.showMessageDialog(mdp.this, "mot de passe fausse vous devez réessayer");
					t.setText("");


				}
			}
		});
		JPanel pan=new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		
		pan.add(p);
		
		ImageIcon icone= new ImageIcon("image/et.png");
	    JLabel lbl_map=new JLabel(icone);
	    pan.add(lbl_map);
		this.add(pan,BorderLayout.CENTER);
		JPanel pn=new JPanel();
		pn.add(b);
		this.add(pn,BorderLayout.SOUTH);
		
		
	}

}
