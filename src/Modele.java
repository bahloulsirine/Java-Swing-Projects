 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class  Modele extends AbstractTableModel {
                 //gerer l contenu de jtable
	ResultSetMetaData rsmd;
	
	ArrayList<Object[]> data=new ArrayList<Object[]>();
	String url="jdbc:mysql://127.0.0.1/tp4_projet";
    String user="root";
    String mdp="";
	
	
    Modele(ResultSet rs){
		try {
			rsmd=rs.getMetaData();
			while(rs.next()){
				
				Object [] ligne=new Object[rsmd.getColumnCount()];
				
				for(int i=0;i<ligne.length;i++){
					ligne[i]=rs.getObject(i+1);
				}
				data.add(ligne);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		try {
			return rsmd.getColumnCount();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public int getRowCount() { //nbre de ligne
		// TODO Auto-generated method stub
		
		return data.size();
	}

	@Override
	public Object getValueAt(int l, int c) {//parcourir ligne et colonne
		return data.get(l)[c];
	}

	public String getColumnName(int c){
		try {
			return rsmd.getColumnName(c+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	


	public boolean isCellEditable(int l, int c) {
	// TODO Auto-generated method stub
	    if(getColumnName(c).equals("pseudo")){
	    	return true;
	    }
	    else{
	    	return false;
	    }	
	}
	
	
	
	
	
	public void supprimer(int l){
		Connexion_base cn=new Connexion_base(url, user, mdp);
		cn.SupprimerSelonpseudo((String)data.get(l)[0]);
		data.remove(l);
		this.fireTableDataChanged(); //rafraichissement
	}
	
	public void Supprimertout(){
		 Connexion_base cn=new Connexion_base(url, user, mdp);
		cn.Supprimertout();
		data.removeAll(data);
		this.fireTableDataChanged();
	 }
	
}

