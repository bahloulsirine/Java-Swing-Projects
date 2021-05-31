import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;


public class Fenetre_Tp4 extends JFrame{
JPanel p1,p_nom,p_prenom,p_pseudo,ps; 
JTextField t_nom,t_prenom,t_pseudo;
JLabel l_nom,l_prenom,l_pseudo,l_help; 
JButton valid1,valid2,affiche;
JSplitPane js; public static JList  l; public static DefaultListModel m; public static JTabbedPane jt;
JMenuItem item_renomer,item_supprimer,item_supprimertout;
String url="jdbc:mysql://127.0.0.1/tp4_projet";
String user="root";
String mdp="";
boolean af_vis=false;
public static ArrayList<Contact> data=new ArrayList<Contact>();
public static ArrayList<String> jt_in=new ArrayList<String>();

Connexion_base cn;


Fenetre_Tp4(){
		this.setTitle("Test Evenement");
		this.setSize(700,500);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		cn=new Connexion_base(url, user, mdp);
		
		
		
		l_nom=new JLabel("Nom");
		t_nom= new JTextField(10);
		t_nom.setText("Tapez votre nom");
		t_nom.setHorizontalAlignment(JLabel.CENTER);
		p_nom=new JPanel();
		p_nom.add(l_nom);
		p_nom.add(t_nom);
		
		l_prenom=new JLabel("Prenom");
		t_prenom= new JTextField(10);
		t_prenom.setText("Tapez votre prénom");
		t_prenom.setHorizontalAlignment(JLabel.CENTER);
		p_prenom=new JPanel();
		p_prenom.add(l_prenom);
		p_prenom.add(t_prenom);
		
		l_pseudo=new JLabel("Pseudo");
		t_pseudo= new JTextField(10);
		t_pseudo.setText("Tapez votre pseudo");
		t_pseudo.setHorizontalAlignment(JLabel.CENTER);
		p_pseudo=new JPanel();
		p_pseudo.add(l_pseudo);
		p_pseudo.add(t_pseudo);
		

		valid1= new JButton("Valider");
		valid1.setHorizontalAlignment(JLabel.CENTER);
        valid1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String txt1=t_nom.getText();
				String txt2=t_prenom.getText();
				String txt3=t_pseudo.getText();
				
				if((txt1.equals("Tapez votre nom"))||(txt2.equals("Tapez votre prénom"))||(txt3.equals("Tapez votre pseudo")))
				{
					JOptionPane.showMessageDialog(Fenetre_Tp4.this, "Erreur:Vous N'avez pas remplir tous les champs");
				}
				else{
					
					boolean test=true;
					for(int i=0;i<data.size();i++){
						if(data.get(i).pseudo.equals(txt3)){
							JOptionPane.showMessageDialog(Fenetre_Tp4.this, "Erreur: Pseudo déja existé");
							test=false;
							break;
						}
					}
					if(test==true){
						Contact c= new Contact(txt1, txt2, txt3);
						data.add(c);
						m.addElement(txt3);
						cn.inserer(txt3, txt1,txt2);
						if(af_vis){
						ResultSet rs=cn.getallPersonne();
						Modele m = new Modele(rs);
						Affiche.jt.setModel(m);
						

						}
						
						t_nom.setText("");
						t_prenom.setText("");
						t_pseudo.setText("");
						
					}
					
					
					
				}}});
				
				
			
		
		
		p1=new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(p_nom);
		p1.add(p_prenom);
		p1.add(p_pseudo);
		p1.add(valid1);
		
		this.add(p1, BorderLayout.NORTH);
		
		l_help=new JLabel("Help:");
		
		affiche= new JButton("Afficher sous forme tableau");
		affiche.setHorizontalAlignment(JLabel.CENTER);
		affiche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Affiche().setVisible(true);
				af_vis=true;
				
			}
		});
		ps=new JPanel();
		ps.add(affiche);
		
		JPanel a=new JPanel();
		a.add(l_help);
		a.add(affiche);
		this.add(a, BorderLayout.SOUTH);
		
		
				
		l=new MaListe();
		m=new DefaultListModel();
		l.setModel(m);
		l.setPreferredSize(new Dimension(250,250));
		
		jt=new JTabbedPane();
		
		
		js=new JSplitPane();
		js.setDividerLocation(150);
		js.setLeftComponent(l);
		js.setRightComponent(jt);
		this.add(js, BorderLayout.CENTER);
		
		// les evenements
		t_nom.addFocusListener(new ecouteur_focus());
		t_prenom.addFocusListener(new ecouteur_focus());
		t_pseudo.addFocusListener(new ecouteur_focus());
		
		t_nom.addMouseListener(new ecouteur_text());
		t_prenom.addMouseListener(new ecouteur_text());
		t_pseudo.addMouseListener(new ecouteur_text());

		l_nom.addMouseListener(new Ecouteurmouse());
		l_prenom.addMouseListener(new Ecouteurmouse());
		l_pseudo.addMouseListener(new Ecouteurmouse());
		l_help.addMouseListener(new Ecouteurmouse());
		l.addMouseListener(new ecouteurliste());
		



		
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
           class Ecouteurmouse extends MouseAdapter {
        		
       		@Override
       		public void mouseEntered(MouseEvent e) {
       			
       			((JLabel)e.getSource()).setBackground(Color.RED);
       			
       			((JLabel)e.getSource()).setOpaque(true);
       		}
       		@Override
       		public void mouseExited(MouseEvent e) {
       			// TODO Auto-generated method stub
       			
       			((JLabel)e.getSource()).setBackground(null);
       		}}
         
      
           class ecouteurliste extends MouseAdapter{
        	   
        	   public void mouseClicked (MouseEvent e){
        		   final int ind=l.locationToIndex(new Point(e.getX(),e.getY()));
        		   if(e.getClickCount()==2)
					   if(ind>=0){
						   /**    // methode contains
						     if(jt_in.contains(l.getSelectedValue())){
							   
							   jt.setSelectedIndex(jt_in.indexOf(l.getSelectedValue()));
						   }
						   else{
							   Contact c=data.get(ind);
							   Mon_panneau mp=   new Mon_panneau(c.nom+" "+c.prenom) ;   
						       jt.addTab(""+m.getElementAt(ind), mp);
						       String ch=(String)m.getElementAt(ind);
						       jt_in.add(ch); 
						       jt.setSelectedIndex(jt_in.size()-1);
						   }*/
						        //methode indexof
							    int j=jt_in.indexOf(l.getSelectedValue());
							    if(j<0){
							   Contact c=data.get(ind);
							   Mon_panneau mp=   new Mon_panneau(c) ;   
						       jt.addTab(""+m.getElementAt(ind), mp);
						       String ch=(String)m.getElementAt(ind);
						       jt_in.add(ch); 
						       //jt.setSelectedIndex(jt_in.indexOf(data.get(l.getSelectedIndex()).pseudo));
						       jt.setSelectedIndex(jt_in.size()-1);
						   }
						   else{
							   System.out.println("deja ouvert");
							   jt.setSelectedIndex(j);
						   }}
        		   if(e.getButton()==e.BUTTON3)
        		   {
        		   //int ind=l.locationToIndex(new Point(e.getX(),e.getY()));
        		   if(ind>=0)
        		   { JPopupMenu pop=new JPopupMenu();

        		   item_renomer=new JMenuItem("renommer");
        		   pop.add(item_renomer);
        		   
        		   item_supprimer=new JMenuItem("supprimer");
        		   pop.add(item_supprimer);
        		   
        		   item_supprimertout=new JMenuItem("supprimer tout");
        		   pop.add(item_supprimertout);
                   
        		   item_supprimertout.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						int retour = JOptionPane.showConfirmDialog(Fenetre_Tp4.this,
					             "voulez vous vraiment supprimer cette personne???", 
					             "",
					             JOptionPane.YES_NO_OPTION);
						if(retour==0){
						jt_in.clear();
						jt.removeAll();
						m.removeAllElements();
						data.clear();
						cn.Supprimertout();
						if(af_vis){

   							ResultSet rs=cn.getallPersonne();
   							Modele m = new Modele(rs);
   							Affiche.jt.setModel(m);
   						}}
						else{
							JOptionPane.showMessageDialog(Fenetre_Tp4.this, "supression annulée!!!");

						}
						
					}
				});
        		   item_supprimer.addActionListener(new ActionListener() {
   					
   					@Override
   					public void actionPerformed(ActionEvent arg0) {
   						// TODO Auto-generated method stub
   						// autre méthode :int j=jt_in.indexOf(m.getElementAt(l.getSelectedIndex()));
   						int retour = JOptionPane.showConfirmDialog(Fenetre_Tp4.this,"voulez vous vraiment supprimer cette personne???", 
					             "",
					             JOptionPane.YES_NO_OPTION);
   						if(retour==0){
   						int j=jt_in.indexOf(data.get(l.getSelectedIndex()).pseudo);
   						if(j>=0){
   							jt.remove(j);
   							jt_in.remove(j);
   						}
   						
   						cn.SupprimerSelonpseudo(data.get(l.getSelectedIndex()).pseudo);
   						
   						data.remove(l.getSelectedIndex());
						m.remove(ind);
						
						if(af_vis){
   							ResultSet rs=cn.getallPersonne();
   							Modele m1 = new Modele(rs);
   							Affiche.jt.setModel(m1);}
   						
   						}
   						else{
   							JOptionPane.showMessageDialog(Fenetre_Tp4.this, "supression annulée!!!");

   						}
   					}});
        		   
        		   item_renomer.addActionListener(new ActionListener() {
      					
      					@Override
      					public void actionPerformed(ActionEvent arg0) {
      						// TODO Auto-generated method stub
      						String h=data.get(l.getSelectedIndex()).pseudo;
      						String ch=JOptionPane.showInputDialog(Fenetre_Tp4.this, "entrer votre nouveau pseudo");
      						if(ch!=null){
      							boolean test=true;
      							for(int i=0;i<data.size();i++){
      								if(data.get(i).pseudo.equals(ch)){
      									JOptionPane.showMessageDialog(Fenetre_Tp4.this, "Erreur: Pseudo déja existé vous ne pouvez pas renomer ");
      									test=false;
      									break;}}
      							if(test==true){
      								cn.ModifierSelonNum(h,ch);
      								data.get(ind).setPseudo(ch);
      								int i=jt_in.indexOf(m.getElementAt(l.getSelectedIndex()));
          	   						if(i>=0){
          	   						    jt.setTitleAt(i, ch);
          	   						    jt_in.set(i, ch); }
      							
          							m.setElementAt(ch, ind);
          							if(af_vis){
          	   							//Affiche.jt.remove(i);
          	   							ResultSet rs=cn.getallPersonne();
          	   							Modele m1 = new Modele(rs);
          	   							Affiche.jt.setModel(m1);}}	
      						}}});
        		   pop.show(l, e.getX(),e.getY());
        		   }}}} 
           class ecouteur_text extends MouseAdapter{
        	   public void mouseEntered(MouseEvent e) {
        		   if(e.getSource()==t_nom)
              			l_help.setText("help: ici veuillez saisir votre nom svp !!" );
        		   if(e.getSource()==t_prenom)
              			l_help.setText("help: ici veuillez saisir votre prenom svp !!" );
        		   if(e.getSource()==t_pseudo)
              			l_help.setText("help: ici veuillez saisir votre pseudo svp !!" );}
       		@Override
       		public void mouseExited(MouseEvent e) {
       			// TODO Auto-generated method stub
       			l_help.setText("help:" );	
       		}
           }}