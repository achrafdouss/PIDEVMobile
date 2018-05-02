<?php

namespace PIDEV\ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert ;


/**
 * Produit
 *
 * @ORM\Table(name="produit")
 * @ORM\Entity(repositoryClass="PIDEV\ProduitBundle\Repository\ProduitRepository")
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_produit", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_produit;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_owner",referencedColumnName="id",nullable=true, onDelete="SET NULL")
     */
    private $id_owner;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie_produit", type="string", length=255)
     */
    private $categorie_produit;


    /**
     * @var string
     *
     * @ORM\Column(name="nom_produit", type="string", length=255)
     */
    private $nom_produit;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255,nullable=true)
     *  @Assert\NotBlank(message="Please, upload the product brochure")
     * @Assert\File(mimeTypes={ "image/jpg","image/png","image/jpeg" })
     */
    private $photo;

    /**
     * @var float
     *
     * @ORM\Column(name="prix_produit", type="float")
     */
    private $prix_produit;



    /**
     * @ORM\Column(name="stock_produit", type="integer" )
     */
    private $stock_produit;


    /**
     * @return int
     */
    public function getIdProduit()
    {
        return $this->id_produit;
    }

    /**
     * @param int $id_produit
     */
    public function setIdProduit($id_produit)
    {
        $this->id_produit = $id_produit;
    }

    /**
     * @return mixed
     */
    public function getIdOwner()
    {
        return $this->id_owner;
    }

    /**
     * @param mixed $id_owner
     */
    public function setIdOwner($id_owner)
    {
        $this->id_owner = $id_owner;
    }


    /**
     * @return string
     */
    public function getNomProduit()
    {
        return $this->nom_produit;
    }

    /**
     * @param string $nom_produit
     */
    public function setNomProduit($nom_produit)
    {
        $this->nom_produit = $nom_produit;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param string $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }


    /**
     * @return int
     */
    public function getPrixProduit()
    {
        return $this->prix_produit;
    }

    /**
     * @param int $prix_produit
     */
    public function setPrixProduit($prix_produit)
    {
        $this->prix_produit = $prix_produit;
    }

    /**
     * @return string
     */




    /**
     * @return mixed
     */
    public function getStockProduit()
    {
        return $this->stock_produit;
    }

    /**
     * @param mixed $stock_produit
     */
    public function setStockProduit($stock_produit)
    {
        $this->stock_produit = $stock_produit;
    }

    /**
     * @return string
     */
    public function getCategorieProduit()
    {
        return $this->categorie_produit;
    }

    /**
     * @param string $categorie_produit
     */
    public function setCategorieProduit($categorie_produit)
    {
        $this->categorie_produit = $categorie_produit;
    }





}

