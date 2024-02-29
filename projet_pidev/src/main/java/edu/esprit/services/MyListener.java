package edu.esprit.services;

import edu.esprit.entities.Commentaire;


public interface MyListener {
    public void onClickListener(Commentaire commentaire);

    void onDeleteListener(Commentaire commentaire);
}
