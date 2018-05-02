<?php

namespace PIDEV\ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Menu
 *
 * @ORM\Table(name="commandeProd")
 * @ORM\Entity(repositoryClass="PIDEV\ProduitBundle\Repository\CommandeRepository")
 */
class CommandeProd
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_commande", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_commande;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\ProduitBundle\Entity\Produit")
     * @ORM\JoinColumn(name="id_produit",referencedColumnName="id_produit",nullable=true, onDelete="SET NULL")
     */
    private $id_produit;

    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_acheteur",referencedColumnName="id",nullable=true, onDelete="SET NULL")
     */
    private $id_acheteur;
    /**
     * @var int
     *
     * @ORM\Column(name="quantite", type="integer")
     */
    private $quantite;




    /**
     * @return int
     */
    public function getIdCommande()
    {
        return $this->id_commande;
    }

    /**
     * @param int $id_commande
     */
    public function setIdCommande($id_commande)
    {
        $this->id_commande = $id_commande;
    }

    /**
     * @return mixed
     */
    public function getIdAcheteur()
    {
        return $this->id_acheteur;
    }

    /**
     * @param mixed $id_acheteur
     */
    public function setIdAcheteur($id_acheteur)
    {
        $this->id_acheteur = $id_acheteur;
    }

    /**
     * @return int
     */
    public function getQuantite()
    {
        return $this->quantite;
    }

    /**
     * @param int $quantite
     */
    public function setQuantite($quantite)
    {
        $this->quantite = $quantite;
    }



    /**
     * @return mixed
     */
    public function getIdProduit()
    {
        return $this->id_produit;
    }

    /**
     * @param mixed $id_produit
     */
    public function setIdProduit($id_produit)
    {
        $this->id_produit = $id_produit;
    }




}

