<?php
/**
 * Created by PhpStorm.
 * User: bouyo
 * Date: 19/02/2018
 * Time: 00:08
 */

namespace PIDEV\ProduitBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert ;

/**
 * ProduitFavories
 *
 * @ORM\Table(name="ProduitFavories")
 * @ORM\Entity
 */
class ProduitFavories
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_favori", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_favori;
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
    public function getIdFavori()
    {
        return $this->id_favori;
    }

    /**
     * @param int $id_favori
     */
    public function setIdFavori($id_favori)
    {
        $this->id_favori = $id_favori;
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