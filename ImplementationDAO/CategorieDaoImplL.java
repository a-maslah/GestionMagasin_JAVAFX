package ImplementationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.AbstractDAO;
import DB.SingleConnection;
import Entites.Categorie;
import Entites.Produit;
import IDAO.CategorieDAO;

public class CategorieDaoImplL extends AbstractDAO implements CategorieDAO{

	

    Statement stm=null;
    ResultSet rs;
   
    @Override
    public List<Categorie> getAll() {
        String sql = "select *from categories";
        ArrayList<Categorie> c = new ArrayList<>();
        try {
        	stm=connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                c.add(new Categorie(rs.getInt("id"), rs.getString("label")));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int getId() {
        String sql = "select MAX(id) from categories";
        try {
        	stm=connection.createStatement();

            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
}

    @Override
    public Categorie find(int id) {
        String sql = "select *from categories where id=" + id;
        try {
        	stm=connection.createStatement();

            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return new Categorie(rs.getInt("id"), rs.getString("label"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return null;
    }
    @Override
    public void create(Categorie c) {
        String sql = "insert into categories(label) values('" + c.getLabel() + "')";
        try {
        	stm=connection.createStatement();

            if (stm.execute(sql))
                System.out.println("categories created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    @Override
    public void delete(Categorie c) throws  Exception {
        String sql = "delete from categories where id=" + c.getId();
        try {
        	stm=connection.createStatement();

            if (stm.execute(sql)) System.out.println("categorie deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Categorie p) {
        String sql = "update categories set label ='" + p.getLabel() + "' where id='" + p.getId() + "' ";
        try {
        	stm=connection.createStatement();

            if (stm.execute(sql))
                System.out.println("produit updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	@Override
	public List<Produit> getProduitOfCategorie(Categorie catg) {
		String sql="select * from produits where categorie_id="+catg.getId();
		List<Produit> produits = new ArrayList<Produit>();
		try {
        	stm=connection.createStatement();
            rs = stm.executeQuery(sql);
			 while (rs.next()) {
	                produits.add(new Produit(rs.getString("designation"), rs.getDouble("prix"), catg));
	            }
	            return produits;
            
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}