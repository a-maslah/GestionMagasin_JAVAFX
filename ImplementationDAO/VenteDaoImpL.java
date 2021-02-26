package ImplementationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import Entites.LigneCommande;
import Entites.Vente;
import IDAO.VenteDAO;

public class VenteDaoImpL extends AbstractDAO implements VenteDAO{
	 Statement stm=null;
	    ResultSet rs;
	     ImplClientDao daoclient = new ImplClientDao();
	    @Override
	    public int getLastId() {
	        String sql="select MAX(id) from ventes";
	        try {
	        	stm=connection.createStatement();

	            rs=stm.executeQuery(sql);
	            if(rs.next()) {
	                return rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return 0;
	    }
	    
	    
	@Override
	public Vente create(Vente v) {
		
		double total=0;
		  for(LigneCommande l:v.getListCommande()) {
			  total+=l.getStotal();
		  }
	        LigneCommandeDaoImpL cmd=new LigneCommandeDaoImpL();
	        String sql="insert into ventes(id_client,total,date) values('"+v.getClient().getId()+"','"+total+"','"+v.getDate()+"')";
	        try {
	        	stm=connection.createStatement();

	             if(!stm.execute(sql)){
	                 v.setId(this.getLastId());
	                 for (LigneCommande l: v.getListCommande()) {
	                     cmd.createLigne(l,v);
	                 }
	                 return v;
	             }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return v;
	}


	@Override
	public List<Vente> getAll() {
		  String sql="select * from ventes";
	        ArrayList<Vente> ventes= new ArrayList<>();
	        try {
	        	stm=connection.createStatement();

	            rs=stm.executeQuery(sql);

	            while(rs.next()) {
	            	ventes.add(new Vente(rs.getInt("id"),rs.getDouble("total"),daoclient.find(rs.getInt("id_client")),rs.getDate("date").toLocalDate()));
	            }
	            return ventes;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}
	
	 @Override
	    public Vente find(int id) {

	        String sql="select * from ventes where id="+id;
	        try {
	        	stm=connection.createStatement();

	            rs=stm.executeQuery(sql);
	            Vente c;
	            if(rs.next()) {
	                c= new Vente(rs.getInt("id"), rs.getDouble("total"), (daoclient.find(rs.getInt("id_client"))),rs.getDate("date").toLocalDate());
	                return c;
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return null;
	    }
	
	

}
