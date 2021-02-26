package ImplementationDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import DB.SingleConnection;
import Entites.Categorie;
import Entites.Produit;
import IDAO.CategorieDAO;
import IDAO.ProduitDAO;

public class ImplementationProduitDao extends AbstractDAO implements ProduitDAO {
	 
	
	
	    Statement stm=null;
	    ResultSet rs;
	    CategorieDAO daocategory=new CategorieDaoImplL(); 

	    
	 
	@Override
	public void create(Produit p) {
		// TODO Auto-generated method stub
		String sql="insert into produits(designation,prix,qte,categorie_id,date) values('"+p.getDesignation()+"','"+p.getPrix()+"','"+p.getQte()+"','"+p.getC().getId()+"','"+p.getDate()+"')";
        try {
        	stm=connection.createStatement();
            if(stm.execute(sql))
                System.out.println("produit bien ajouter");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	@Override
	public int getId() {
		  String sql="select MAX(id) from produits";
	        try {
	        	stm=connection.createStatement();
	            rs=stm.executeQuery(sql);
	            if(rs.next()) {
	                return rs.getInt(1)+1;
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return 0;
	    }
	

	@Override
	public List<Produit> getAll() {
		String sql="select *from produits";
        List<Produit> p=new ArrayList<>();
        try {
        	stm=connection.createStatement();

            rs=stm.executeQuery(sql);
            while(rs.next()) {
                Categorie c=daocategory.find(rs.getInt("categorie_id"));
                p.add(new Produit(rs.getInt("id"),rs.getString("designation"),rs.getDouble("prix"),rs.getInt("qte"),c,rs.getDate("date").toLocalDate()));
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public void update(Produit p) {
		String sql="update produits set designation ='"+p.getDesignation()+"',prix='"+p.getPrix()+"',qte='"+p.getQte()+"',categorie_id='"+p.getC().getId()+"',date='"+p.getDate()+"' where id='"+p.getId()+"' ";
        try {
        	stm=connection.createStatement();

            if (stm.execute(sql))
                System.out.println("produit bien modifier");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void delete(Produit p) throws SQLException {
		 String sql="delete from produits where id="+p.getId();
	        try {
	        	stm=connection.createStatement();
	            if (stm.execute(sql))
	                System.out.println("produit est supprime");
	        } catch (SQLException e) {
	            throw e;
	        }
		
	}
	
	 @Override
	    public Produit find(int id) {
	        String sql="select *from produits where id="+id;
	        try {
	        	stm=connection.createStatement();

	            rs=stm.executeQuery(sql);
	            if(rs.next()) {
	                Categorie c=daocategory.find(rs.getInt("categorie_id"));
	                return new Produit(rs.getInt("id"),rs.getString("designation"),rs.getDouble("prix"),c);
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return null;
	    }

}
