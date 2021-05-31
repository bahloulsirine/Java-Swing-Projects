import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Affiche extends JFrame{
	public static JTable jt; JScrollPane js;public static  Modele m;
	String url="jdbc:mysql://127.0.0.1/tp4_projet";
    String user="root";
    String mdp="";
    ResultSet rs;
   
    JButton annuler,chercher,ajouter;
	Affiche(){
		super("affichage données");
		this.setSize(700, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		final  Connexion_base cn=new Connexion_base(url, user, mdp);
		rs=cn.getallPersonne();
		
		m = new Modele(rs);
		jt=new JTable();
		jt.setModel(m);
		js=new JScrollPane(jt);
		
		
	     
		
		annuler=new JButton("annuler");
		chercher=new JButton("chercher");
		ajouter=new JButton("ajouter");
		JPanel pan=new JPanel();
		pan.add(annuler);
		pan.add(chercher);
		pan.add(ajouter);
		this.add(js);
		
		
		this.add(pan,BorderLayout.SOUTH);
		
		jt.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getButton()==e.BUTTON3){
				
				
				 JPopupMenu pop=new JPopupMenu();
				 JMenuItem supp=new JMenuItem("supprimer");
				 JMenuItem supp_tout=new JMenuItem("supprimer tout");
				 JMenuItem ren=new JMenuItem("renomer");
				 pop.add(supp);
				 pop.add(supp_tout);
				 pop.add(ren);
				 pop.show(jt, e.getX(),e.getY()); 
			     supp.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int l=jt.getSelectedRow();
						if(l==-1){
							JOptionPane.showMessageDialog(Affiche.this, "Erreur:Vous N'avez pas selectionner une ligne a supprimer");
						}
						else{
							int retour = JOptionPane.showConfirmDialog(Affiche.this,
						             "voulez vous vraiment supprimer cette personne???", 
						             "",
						             JOptionPane.YES_NO_OPTION);
							if(retour==0){
							String chh=jt.getValueAt(l, 0).toString();
							
							
							int j=Fenetre_Tp4.jt_in.indexOf(chh);
	   						if(j>=0){
	   							Fenetre_Tp4.jt.remove(j);
	   							Fenetre_Tp4.jt_in.remove(j);
	   						}
	   						Connexion_base cn1=new Connexion_base(url, user, mdp);

	   						Fenetre_Tp4.data.remove(l);
	   						Fenetre_Tp4.m.remove(l);
	   						cn1.SupprimerSelonpseudo(chh);
	   						
	   						//jt.remove(l);
	   						rs=cn1.getallPersonne();
							m = new Modele(rs);
							jt.setModel(m);
	   						
							}
							else{
								JOptionPane.showMessageDialog(Affiche.this, "supression annulée!!!");
							}

						}}
				});
			     supp_tout.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						// TODO Auto-generated method stub
						int retour = JOptionPane.showConfirmDialog(Affiche.this,
					             "voulez vous vraiment supprimer cette personne???", 
					             "",
					             JOptionPane.YES_NO_OPTION);
						if (retour==0){
							Connexion_base cn=new Connexion_base(url, user, mdp);

						Fenetre_Tp4.jt_in.clear();
						Fenetre_Tp4.jt.removeAll();
						Fenetre_Tp4.m.removeAllElements();
						Fenetre_Tp4.data.clear();
						cn.Supprimertout();
						rs=cn.getallPersonne();
						m = new Modele(rs);
						jt.setModel(m);}
						else{
							JOptionPane.showMessageDialog(Affiche.this, "supression annulée!!!");

						}
							
						
						
						
					}
				});
			     ren.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						int l=jt.getSelectedRow();
						if(l==-1){
							JOptionPane.showMessageDialog(Affiche.this, "Erreur:Vous N'avez pas selectionner une ligne a renomer");
						}
						else{
							
							JOptionPane.showMessageDialog(Affiche.this, "vous ne pouvez pas renomer que le pseudo!!!");
							String chh=jt.getValueAt(l, 0).toString();
							String ch_mod=JOptionPane.showInputDialog(Affiche.this, "entrer votre nouveau pseudo");
						
							Connexion_base cn=new Connexion_base(url, user, mdp);

							
							cn.ModifierSelonNum(chh,ch_mod);
							Fenetre_Tp4.data.get(l).setPseudo(ch_mod);
							int j=Fenetre_Tp4.jt_in.indexOf(chh);
	   						if(j>=0){
	   							Fenetre_Tp4.jt.setTitleAt(j, ch_mod);
	   							Fenetre_Tp4.jt_in.set(j, ch_mod);
	   						}
	   						Fenetre_Tp4.m.setElementAt(ch_mod, l);	
	   						rs=cn.getallPersonne();
							m = new Modele(rs);
							jt.setModel(m);
	   						
	   						
	   						
					}
				}
			});
			
			}
	        


		}
				});
		
		// les evènements
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		ajouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ajouter().setVisible(true);
			}
		});
	
		chercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Recherche().setVisible(true);
			}
		});
		

	
		}}
