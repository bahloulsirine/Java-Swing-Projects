import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.FeatureDescriptor;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Recherche extends JFrame implements ActionListener{
	
	JTextField t_nom,t_prenom,t_pseudo;
	JLabel l_nom,l_prenom,l_pseudo,l_help;
	JButton BTArechercher, BTAnnuler,BTAsupp,BTAmod;

	String url="jdbc:mysql://127.0.0.1/tp4_projet";
    String user="root";
    String mdp="";
	
	Recherche(){
		this.setTitle("Recherche personne");
		this.setSize(350, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(3,3,5,5));
		
		
		l_pseudo=new JLabel("Pseudo");
		t_pseudo= new JTextField(10);
		t_pseudo.setText("Tapez pseudo");
		t_pseudo.setHorizontalAlignment(JLabel.CENTER);
		BTArechercher = new JButton("Rechercher");
		JPanel aj=new JPanel();
		aj.add(BTArechercher);
		BTArechercher.addActionListener(this);
		
		
		pan.add(l_pseudo);
		pan.add(t_pseudo);
		pan.add(aj);
		
		l_nom=new JLabel("Nom");
		t_nom= new JTextField(10);
		t_nom.setEnabled(false);
		t_nom.setHorizontalAlignment(JLabel.CENTER);
		BTAmod = new JButton("Modifier");
		JPanel m=new JPanel();
		m.add(BTAmod);
		BTAmod.addActionListener(this);
		BTAmod.setEnabled(false);
		

		pan.add(l_nom);
		pan.add(t_nom);
		pan.add(m);
		
		l_prenom=new JLabel("Prenom");
		t_prenom= new JTextField(10);
		t_prenom.setEnabled(false);
		t_prenom.setHorizontalAlignment(JLabel.CENTER);
		BTAsupp = new JButton("Supprimer");
		JPanel s=new JPanel();
		s.add(BTAsupp);
		BTAsupp.addActionListener(this);
		BTAsupp.setEnabled(false);
		
		
		pan.add(l_prenom);
		pan.add(t_prenom);
		pan.add(s);
		
		
		BTAnnuler = new JButton("Annuler");
		JPanel an=new JPanel();
		an.add(BTAnnuler);
		BTAnnuler.addActionListener(this);
		
		JPanel a=new JPanel();
		l_help=new JLabel("Help:");
		a.add(l_help);
		a.add(an);
		this.add(a, BorderLayout.SOUTH);
		this.add(pan);
	
		t_nom.setEditable(false);
		t_prenom.setEditable(false);
		t_nom.addMouseListener(new ecouteur_text());
		t_prenom.addMouseListener(new ecouteur_text());
		t_pseudo.addMouseListener(new ecouteur_text());
	
		t_nom.addFocusListener(new ecouteur_focus());
		t_prenom.addFocusListener(new ecouteur_focus());
		t_pseudo.addFocusListener(new ecouteur_focus());
}
	class ecouteur_focus extends FocusAdapter {
	 	   
		
		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource()==t_nom){
				t_nom.setText("");
			}
			if(e.getSource()==t_prenom){
				t_prenom.setText("");
			}
			if(e.getSource()==t_pseudo){
				t_pseudo.setText("");
			}}
		public void focusLost(FocusEvent e) {
			if(e.getSource()==t_nom){
				if(t_nom.getText().equals(""))
				  {t_nom.setText("Tapez votre nom");}
			}
			if(e.getSource()==t_prenom){
				if(t_prenom.getText().equals(""))
				  {t_prenom.setText("Tapez votre prénom");}
			}
			if(e.getSource()==t_pseudo){
				if(t_pseudo.getText().equals(""))
				  {t_pseudo.setText("Tapez votre pseudo");}
			}
				
			}
		}
	class ecouteur_text extends MouseAdapter{
  	   public void mouseEntered(MouseEvent e) {
  		   if(e.getSource()==t_nom)
        			l_help.setText("help: ici veuillez saisir votre nom svp !!" );
  		   if(e.getSource()==t_prenom)
        			l_help.setText("help: ici veuillez saisir votre prenom svp !!" );
  		   if(e.getSource()==t_pseudo)
        			l_help.setText("help: ici veuillez saisir votre pseudo svp !!" );
 			
 		}
 		@Override
 		public void mouseExited(MouseEvent e) {
 			// TODO Auto-generated method stub
 			
 			l_help.setText("help:" );
 			
 		
 			
 		}
     }
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	Object source = e.getSource();
	
	if (source == BTAnnuler) { 
		this.dispose();
	}
	else if (source == BTArechercher) {
		int x=0; 
		Connexion_base cn=new Connexion_base(url, user, mdp);
		String ch=t_pseudo.getText();
		
		
		for(int j=0;j<Fenetre_Tp4.data.size();j++){
			if(Fenetre_Tp4.data.get(j).pseudo.equals(ch)){
				x=1;
				break;
			}
		}
		if(x==0){
			
			JOptionPane.showMessageDialog(Recherche.this, "Aucun personne avec le pseudo "+ch);
			t_pseudo.setText("");
		}
		else{
			for(int i=0;i<Fenetre_Tp4.data.size();i++){
				if(Fenetre_Tp4.data.get(i).pseudo.equals(ch)){
					t_nom.setEnabled(true);
					t_nom.setText(Fenetre_Tp4.data.get(i).nom);
					t_prenom.setEnabled(true);
					t_prenom.setText(Fenetre_Tp4.data.get(i).prenom);
					BTAmod.setEnabled(true);
					BTAsupp.setEnabled(true);
					break;
				}
			}
			
		}
	}
	else if(source==BTAsupp){
		int retour = JOptionPane.showConfirmDialog(Recherche.this,
	             "voulez vous vraiment supprimer cette personne???", 
	             "",
	             JOptionPane.YES_NO_OPTION);
		if (retour==0){
		     int x=0; int i=0;
			String chh=t_pseudo.getText();	
			int j=Fenetre_Tp4.jt_in.indexOf(chh);
			System.out.println(j);
				if(j>=0){
					Fenetre_Tp4.jt.remove(j);
					Fenetre_Tp4.jt_in.remove(j);
				}
				
				while(x==0){
					if(Fenetre_Tp4.data.get(i).pseudo.equals(chh)){
						x=1;
					}
					else{i++;
					}}
				
					
				Connexion_base cn=new Connexion_base(url, user, mdp);
				cn.SupprimerSelonpseudo(chh);
				Fenetre_Tp4.data.remove(i);
				Fenetre_Tp4.m.remove(i);
				
				ResultSet rs=cn.getallPersonne();
				Modele m = new Modele(rs);
				Affiche.jt.setModel(m);
				
				t_nom.setText("");
				t_prenom.setText("");
				t_pseudo.setText("");
				t_nom.setEnabled(false);
				t_prenom.setEnabled(false);
				BTAmod.setEnabled(false);
				BTAsupp.setEnabled(false);
				JOptionPane.showMessageDialog(Recherche.this, "Personne supprimée");}
				else{
					JOptionPane.showMessageDialog(Recherche.this, "supression annulée!!!");
				}

	}
	else if (source==BTAmod){
		int x=0;int i=0;
		Connexion_base cn=new Connexion_base(url, user, mdp);
		
		
		JOptionPane.showMessageDialog(Recherche.this, "vous ne pouvez pas modifier que le pseudo!!!");
		String chh=t_pseudo.getText();
		String ch_mod=JOptionPane.showInputDialog(Recherche.this, "entrer votre nouveau pseudo");
		
		
		while(x==0){
			if(Fenetre_Tp4.data.get(i).pseudo.equals(chh)){
				x=1;
			}
			else{i++;
			}}
		
		
		cn.ModifierSelonNum(chh,ch_mod);
			Fenetre_Tp4.data.get(i).setPseudo(ch_mod);
			int j=Fenetre_Tp4.jt_in.indexOf(chh);
				if(j>=0){
				    Fenetre_Tp4.jt.setTitleAt(j, ch_mod);
				    Fenetre_Tp4.jt_in.set(j, ch_mod); }
		
				Fenetre_Tp4.m.setElementAt(ch_mod, i);
			
					ResultSet rs=cn.getallPersonne();
					Modele m1 = new Modele(rs);
					Affiche.jt.setModel(m1);
			t_pseudo.setText(ch_mod);
			
		}

	}
	}

	


