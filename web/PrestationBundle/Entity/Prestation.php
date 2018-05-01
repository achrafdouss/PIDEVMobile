<?php

namespace PIDEV\PrestationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Prestation
 *
 * @ORM\Table(name="prestation")
 * @ORM\Entity(repositoryClass="PIDEV\PrestationBundle\Repository\PrestationRepository")
 */
class Prestation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_prestation", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_prestation;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=1000)
     * @Assert\Length(min=5)
     */
    private $titre;

    /**
     * @var string
     * @Assert\Length(min=20)
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var float
     *
     * @ORM\Column(name="salaire", type="float",nullable=true)
     */
    private $salaire;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=255,nullable=true)
     */
    private $lieu;
    /**
     * @var \DateTime
     * @ORM\Column(name="date_ajout",type="datetime")
     */
    private $date_ajout;

    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_inscrit",referencedColumnName="id")
     */
    private $id_inscrit;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\PrestationBundle\Entity\Diplome",cascade={"persist"})
     * @ORM\JoinColumn(name="id_diplome",referencedColumnName="id_diplome")
     */
    private $id_diplome;
    /**
     * @var string
     * @ORM\Column(name="categorie", type="string", nullable=true)
     */
    private $categorie;
    /**
     * @var boolean
     * @ORM\Column(name="statut", type="boolean", options={"default" : 1})
     */
    private $statut;
    /**
     * @var boolean
     * @ORM\Column(name="valide", type="boolean", options={"default" : 0})
     */
    private $valide;

    /**
     * @return mixed
     */
    public function getValide()
    {
        return $this->valide;
    }

    /**
     * @param mixed $valide
     */
    public function setValide($valide)
    {
        $this->valide = $valide;
    }


    /**
     * @return mixed
     */

    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param mixed $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }


    /**
     * @return DateType
     */
    public function getDateAjout()
    {
        return $this->date_ajout;
    }

    /**
     * @return mixed
     */
    public function getStatut()
    {
        return $this->statut;
    }

    /**
     * @param mixed $statut
     */
    public function setStatut($statut)
    {
        $this->statut = $statut;
    }


    /**
     * @param DateType $date_ajout
     */
    public function setDateAjout($date_ajout)
    {
        $this->date_ajout = $date_ajout;
    }

    /**
     * @return int
     */
    public function getIdPrestation()
    {
        return $this->id_prestation;
    }

    /**
     * @param int $id_prestation
     */
    public function setIdPrestation($id_prestation)
    {
        $this->id_prestation = $id_prestation;
    }



    /**
     * @return mixed
     */
    public function getIdDiplome()
    {
        return $this->id_diplome;
    }

    /**
     * @param mixed $id_diplome
     */
    public function setIdDiplome($id_diplome)
    {
        $this->id_diplome = $id_diplome;
    }

    /**
     * @return mixed
     */
    public function getIdInscrit()
    {
        return $this->id_inscrit;
    }

    /**
     * @param mixed $id_inscrit
     */
    public function setIdInscrit($id_inscrit)
    {
        $this->id_inscrit = $id_inscrit;
    }




    /**
     * Set titre
     *
     * @param string $titre
     *
     * @return Prestation
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;

        return $this;
    }

    /**
     * Get titre
     *
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Prestation
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set salaire
     *
     * @param float $salaire
     *
     * @return Prestation
     */
    public function setSalaire($salaire)
    {
        $this->salaire = $salaire;

        return $this;
    }

    /**
     * Get salaire
     *
     * @return float
     */
    public function getSalaire()
    {
        return $this->salaire;
    }

    /**
     * Set lieu
     *
     * @param string $lieu
     *
     * @return Prestation
     */
    public function setLieu($lieu)
    {
        $this->lieu = $lieu;

        return $this;
    }

    /**
     * Get lieu
     *
     * @return string
     */
    public function getLieu()
    {
        return $this->lieu;
    }
}

