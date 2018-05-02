<?php

namespace PIDEV\ProduitBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LikeProduit
 *
 * @ORM\Table(name="like_produit")
 * @ORM\Entity(repositoryClass="PIDEV\ProduitBundle\Repository\LikeProduitRepository")
 */
class LikeProduit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_like", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_like;


    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\ProduitBundle\Entity\Produit")
     * @ORM\JoinColumn(name="id_produit",referencedColumnName="id_produit",nullable=true, onDelete="SET NULL")
     */
    private $id_produit;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_owner",referencedColumnName="id",nullable=true, onDelete="SET NULL")
     */
    private $id_owner;

    /**
     * @return int
     */
    public function getIdLike()
    {
        return $this->id_like;
    }

    /**
     * @param int $id_like
     */
    public function setIdLike($id_like)
    {
        $this->id_like = $id_like;
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

}

