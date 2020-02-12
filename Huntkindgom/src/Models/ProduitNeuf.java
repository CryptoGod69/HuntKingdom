/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


public class ProduitNeuf extends Produits {
        private int idProdNeuf;
        
        public ProduitNeuf(int reference, String nom, String categorie, String description, float prix, int nbProduits, int idProdNeuf) {
        super(reference,nom,categorie,description,prix,nbProduits);
        this.idProdNeuf=idProdNeuf;
    }
        
        public ProduitNeuf() {
        super();
        idProdNeuf=0;
    }
        
        public int getIdProdNeuf() {
        return idProdNeuf;
    }

    public void setIdProdNeuf (int idProdNeuf) {
        this.idProdNeuf= idProdNeuf;
    }    
    
    @Override
    public String toString() {
        return "Produit{" + super.toString() + ", Id produit Neuf=" + idProdNeuf + '}';
    }
    
}
