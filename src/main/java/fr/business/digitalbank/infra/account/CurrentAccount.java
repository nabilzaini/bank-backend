package fr.business.digitalbank.infra.account;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = "CUR")
/*
Héritage on peut le répresenter en BDD en 3 façons:
- Single Table: toute l'hiérarchie des classes en une seule Table (limité si le nombre de champs est elevé)
- Table Per Class: Une table par Classe Enfant
- Joined Table: Table par classe, moins rapide en terme de performance
*/
public class CurrentAccount extends BankAccount{
    private double overDraft;
}
