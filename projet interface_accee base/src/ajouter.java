import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;




public class ajouter extends JFrame implements ActionListener{
	String url="jdbc:mysql://127.0.0.1/tp4_projet";
	String user="root";
	String mdp="";
	
	JTextField t_nom,t_prenom,t_pseudo;
	JLabel l_nom,l_prenom,l_pseudo,l_help;
	JButton BTAjouter, BTAnnuler;
	ajouter(){
		
		this.setTitle("ajouter personne");
		this.setSize(350, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel pan=new JPanel();
		pan.setLayout(new GridLayout(4,2,5,5));
		
		
		l_nom=new JLabel("Nom");
		t_nom= new JTextField(10);
		t_nom.setText("Tapez votre nom");
		t_nom.setHorizontalAlignment(JLabel.CENTER);
		
		pan.add(l_nom);
		pan.add(t_nom);
		
		l_prenom=new JLabel("Prenom");
		t_prenom= new JTextField(10);
		t_prenom.setText("Tapez votre prénom");
		t_prenom.setHorizontalAlignment(JLabel.CENTER);
		
		pan.add(l_prenom);
		pan.add(t_prenom);
		
		l_pseudo=new JLabel("Pseudo");
		t_pseudo= new JTextField(10);
		t_pseudo.setText("Tapez votre pseudo");
		t_pseudo.setHorizontalAlignment(JLabel.CENTER);
		
		pan.add(l_pseudo);
		pan.add(t_pseudo);
		
		BTAjouter = new JButton("Ajouter");
		JPanel aj=new JPanel();
		aj.add(BTAjouter);
		pan.add(aj);
		BTAjouter.addActionListener(this);

		BTAnnuler = new JButton("Annuler");
		JPanel an=new JPanel();
		an.add(BTAnnuler);
		pan.add(an);
		BTAnnuler.addActionListener(this);
		
		l_help=new JLabel("Help:");
		this.add(l_help, BorderLayout.SOUTH);
		this.add(pan);
		
		t_nom.addFocusListener(new ecouteur_focus());
		t_prenom.addFocusListener(new ecouteur_focus());
		t_pseudo.addFocusListener(new ecouteur_focus());
		
		
		
		t_nom.addMouseListener(new ecouteur_text());
		t_prenom.addMouseListener(new ecouteur_text());
		t_pseudo.addMouseListener(new ecouteur_text());
		
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
		
		
		
	
	public void actionPerformed(ActionEvent e) {
Object source = e.getSource();
		
		if (source == BTAnnuler) { 
			this.dispose();
		}
		else if (source == BTAjouter) { 
			String txt1=t_nom.getText();
			String txt2=t_prenom.getText();
			String txt3=t_pseudo.getText();
			
			if((txt1.equals("Tapez votre nom"))||(txt2.equals("Tapez votre prénom"))||(txt3.equals("Tapez votre pseudo")))
			{
				JOptionPane.showMessageDialog(ajouter.this, "Erreur:Vous N'avez pas remplir tous les champs");
			}
			else{
				
				boolean test=true;
				for(int i=0;i<Fenetre_Tp4.data.size();i++){
					if(Fenetre_Tp4.data.get(i).pseudo.equals(txt3)){
						JOptionPane.showMessageDialog(ajouter.this, "Erreur: Pseudo déja existé");
						test=false;
						break;
					}
				}
				if(test==true){
					Connexion_base cn=new Connexion_base(url, user, mdp);
					Contact c= new Contact(txt1, txt2, txt3);
					Fenetre_Tp4.data.add(c);
					Fenetre_Tp4.m.addElement(txt3);
					cn.inserer(txt3, txt1,txt2);
					ResultSet rs=cn.getallPersonne();
					Modele m = new Modele(rs);
					Affiche.jt.setModel(m);
					
					t_nom.setText("");
					t_prenom.setText("");
					t_pseudo.setText("");
							
					
				}
				
				
			}
		}
	}
	
	}
	

