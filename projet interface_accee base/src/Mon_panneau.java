import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;

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
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class Mon_panneau extends JPanel {
	JPanel p_diff,p_opt,p_cat,p_choix,p_center,p_dif_opt,ps,ps2; 
	JLabel l_bonjour,l_caté; 
	JButton valid2;
	JComboBox niv_dif;
	JCheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11;
	String cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,ch1,ch2,ch3;
	
	
	
	
	Mon_panneau(Contact c){
	this.setLayout(new BorderLayout());

	ch1=c.getNom();
	ch2=c.getPrenom();
	ch3=c.getPseudo();
	
	l_bonjour=new JLabel("Bienvene "+c.nom+" "+c.prenom);
	l_bonjour.setHorizontalAlignment(JLabel.CENTER);
	l_bonjour.setBackground(Color.green);
    l_bonjour.setForeground(Color.BLACK);
    l_bonjour.setOpaque(true);
    l_bonjour.setFont(new Font("Arial", Font.ITALIC, 36));
    this.add(l_bonjour,BorderLayout.NORTH);
    
    valid2= new JButton("Valider");
	valid2.setHorizontalAlignment(JLabel.CENTER);
	ps=new JPanel();
	ps.add(valid2);
	this.add(ps,BorderLayout.SOUTH);
	valid2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (c8.isSelected()==true) {//option
			cat8 = "Son : True";
			}
			else {
			cat8 = "Son : False";
			}
			if (c9.isSelected()==true) {
			cat9 = "Affichage score : true";
			}
			else {
			cat9 ="Affichage score : False";
			}
			if (c10.isSelected()==true) {
			cat10 = "Plein ecron : true";
			}
			else {
			cat10 ="Plein ecron : False";
			}
			if (c11.isSelected()==true) {
			cat11 = "Ombre : true";
			}
			else {
			cat11 ="Ombre : False";
			}
			if(niv_dif.getSelectedIndex()==0){
				cat3="";
				cat4=" ";
				cat5=" ";
				cat6=" ";
				cat7 =" ";
				if(c1.isSelected()==true) {
					
					cat1="Categorie 1";
					
					} 
				if(c1.isSelected()==false) {
					
					cat1="";
					
					} 
				if(c2.isSelected()==true) {
					
					cat2="Categorie 2";
					
					} 
				if(c2.isSelected()==false) {
					
				cat2="";
				
				} }
				if(niv_dif.getSelectedIndex()==1) {
					cat1="Categorie 1";
					cat2="Categorie 2";
					cat5=" ";
					cat6=" ";
					cat7 =" ";
				if(c3.isSelected()==true) {
				cat3="Categorie 3";
				}
				if(c3.isSelected()==false) {
					cat3="";
					}
				if(c4.isSelected()==true) {
					cat4="Categorie 4";
					}
					if(c4.isSelected()==false) {
						cat4="";
						}
				}	
				if(niv_dif.getSelectedIndex()==2) {	
					cat1="Categorie 1";
					cat2="Categorie 2";
					cat3="Categorie 3";
					cat4="Categorie 4";
					if(c5.isSelected()==true) {
						cat5="Categorie 5";
						}
						if(c5.isSelected()==false) {
							cat5="";
							}
						
					if(c6.isSelected()==true) {
						cat6="Categorie 6";
						}
					if(c6.isSelected()==false) {
						cat6="";
						}
					
					if(c7.isSelected()==true) {
						cat7="Categorie 7";
						}
					if(c7.isSelected()==false) {
						cat7="";
						}
				}
				
				

			   //creer et ecrire
			   
			   BufferedWriter bw;
			try {
			bw = new BufferedWriter(new FileWriter("C:\\Users\\Utilisateur\\Desktop\\Test"+ch3+".html"));
			bw.write("<html>"
			+ "<head>"
			+ "<title>"+ch1.toString()+ch2.toString()+"</title>"
			+ "</head>"
			+ "<body>"
			+ "<form>"  
			   + "<fieldset>"  
			    + "<legend>Information personnelle:</legend>"  
			    +"<p> Nom :"+ch1.toString()+"</p>"
			    +"<p> Prenom :"+ch2.toString()+"</p>"
			    +"<p> Pseudo :"+ch3.toString()+"</p>"
			+"</fieldset>"  
			+"</form>"  

			+"<form>"  
			   +"<fieldset>"  
			    +"<legend>Configuration:</legend>"  
			 
			+"<form>"  
			   +"<fieldset>"  
			    +"<legend>difficulté:"+niv_dif.getSelectedItem().toString()+"</legend>"  
			+"<p>"+cat1+"<br>"+cat2+"<br>"+cat3+"<br>"+cat4+"<br>"+cat5+"<br>"+cat6+"<br>"+cat7+"</p>"
			+"</fieldset>"  
			+"</form>"
			+"<form>"  
			+"<fieldset>"  
			+"<legend>Options:</legend>"  
			+"<p>"+cat8+"<br>"+cat9+"<br>"+cat10+"<br>"+cat11
			+"</fieldset>"  
			+"</form>"    
			+"</fieldset>"  
			+"</form>"

			+ "</body>"
			+ "</html>");
			   bw.close();
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}

			try {
			File htmlFile = new File("C:\\Users\\Utilisateur\\Desktop\\Test"+ch3+".html");
			Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
			}




			});
	niv_dif=new JComboBox();
	niv_dif.addItem("Facile");
	niv_dif.addItem("Intermédiaire");
	niv_dif.addItem("Difficile");
	
	
	
	l_caté=new JLabel("Choisir la/les Catégories(s) : ");
	
	c1=new JCheckBox("1");
	c2=new JCheckBox("2");
	c3=new JCheckBox("3");
	c4=new JCheckBox("4");
	c5=new JCheckBox("5");
	c6=new JCheckBox("6");
	c7=new JCheckBox("7");
	
	
	
	
	
	p_cat= new JPanel();
	p_cat.add(l_caté);
	p_cat.add(c1);
	p_cat.add(c2);
	p_cat.add(c3);
	p_cat.add(c4);
	p_cat.add(c5);
	p_cat.add(c6);
	p_cat.add(c7);
	
	p_diff=new JPanel();
	p_diff.setLayout(new BoxLayout(p_diff, BoxLayout.PAGE_AXIS));
	ps2=new JPanel();
	ps2.add(niv_dif);
	p_diff.add(ps2);
	p_diff.setBorder(BorderFactory.createTitledBorder("Difficulé"));
	
	
	
	
	p_opt=new JPanel();
	
	c8=new JCheckBox("Afficher score");
	c9=new JCheckBox("Plein ecran");
	c10=new JCheckBox("ombre");
	c11=new JCheckBox("Emettre son");
	p_choix=new JPanel();
	p_choix.add(c8);
	p_choix.add(c9);
	p_choix.add(c10);
	p_choix.add(c11);
	p_opt.add(p_choix);
	p_opt.setBorder(BorderFactory.createTitledBorder("Option:"));
	
	
	

	
	p_center=new JPanel();
	p_center.setLayout(new BoxLayout(p_center, BoxLayout.PAGE_AXIS));
	p_center.add(p_diff);
	p_center.add(p_opt);
	
	this.add(p_center,BorderLayout.CENTER);

	// les evènement
	
niv_dif.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			p_diff.add(p_cat);
			if(niv_dif.getSelectedIndex()==0){
				
				c1.setEnabled(true);
				c2.setEnabled(true);
				c1.setSelected(false);
				c2.setSelected(false);
				
				c3.setEnabled(false);
				c4.setEnabled(false);
				c5.setEnabled(false);
				c6.setEnabled(false);
				c7.setEnabled(false);
				
				
				
				c3.setSelected(false);
				c4.setSelected(false);
				c5.setSelected(false);
				c6.setSelected(false);
				c7.setSelected(false);
			}else{
			if(niv_dif.getSelectedIndex()==1){
				
				c3.setEnabled(true);
				c4.setEnabled(true);
				c3.setSelected(false);
				c4.setSelected(false);
				
				c1.setSelected(true);
				c2.setSelected(true);
				c5.setSelected(false);
				c6.setSelected(false);
				c7.setSelected(false);
				c1.setEnabled(false);
				c2.setEnabled(false);
				c5.setEnabled(false);
				c6.setEnabled(false);
				c7.setEnabled(false);
				
				
			}else{
			
				
				c5.setEnabled(true);
				c6.setEnabled(true);
				c7.setEnabled(true);
				
				c1.setSelected(true);
				c2.setSelected(true);
				c1.setEnabled(false);
				c2.setEnabled(false);
				c3.setSelected(true);
				c4.setSelected(true);
				c3.setEnabled(false);
				c4.setEnabled(false);
				
			
				
			}
			}
		}
	});

	c8.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p_opt.setBorder(BorderFactory.createTitledBorder("Option:1"));
	}
});
	c9.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p_opt.setBorder(BorderFactory.createTitledBorder("Option:2"));
	}
});
	c10.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p_opt.setBorder(BorderFactory.createTitledBorder("Option:3"));
		
	}
});
	c11.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		p_opt.setBorder(BorderFactory.createTitledBorder("Option:4"));
	}
});
}
	

	
}