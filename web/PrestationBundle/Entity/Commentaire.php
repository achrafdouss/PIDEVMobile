<?php

namespace PIDEV\PrestationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentairePrestation")
 * @ORM\Entity(repositoryClass="PIDEV\PrestationBundle\Repository\CommentaireRepository")
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_commentaire", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_commentaire;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\PrestationBundle\Entity\Prestation")
     * @ORM\JoinColumn(name="id_prestation",referencedColumnName="id_prestation")
     */
    private $id_prestation;
    /**
     * @var
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id")
     */
    private $id_user;
    /**
     * @var string
     * @ORM\Column(name="contenu",type="string")
     * @Assert\Length(min=5, max=150)
     */
    private $contenu;
    /**
     * @var integer
     * @ORM\Column(name="nbr_signalisation", type="integer",options={"default" : 0})
     */
    private $nbr_signalisation;
    /**
     * @var \DateTime
     * @ORM\Column(name="date_ajout",type="datetime")
     */
    private $date_ajout;

    /**
     * @return mixed
     */
    public function getDateAjout()
    {
        return $this->date_ajout;
    }

    /**
     * @param mixed $date_ajout
     */
    public function setDateAjout($date_ajout)
    {
        $this->date_ajout = $date_ajout;
    }


    /**
     * @return int
     */
    public function getIdCommentaire()
    {
        return $this->id_commentaire;
    }

    /**
     * @param int $id_commentaire
     */
    public function setIdCommentaire($id_commentaire)
    {
        $this->id_commentaire = $id_commentaire;
    }

    /**
     * @return mixed
     */
    public function getIdPrestation()
    {
        return $this->id_prestation;
    }
    public function incrementsignalisation()
    {
        $this->nbr_signalisation++;
    }

    /**
     * @param mixed $id_prestation
     */
    public function setIdPrestation($id_prestation)
    {
        $this->id_prestation = $id_prestation;
    }

    /**
     * @return mixed
     */
    public function getIdUser()
    {
        return $this->id_user;
    }

    /**
     * @param mixed $id_user
     */
    public function setIdUser($id_user)
    {
        $this->id_user = $id_user;
    }

    /**
     * @return string
     */
    public function getContenu()
    {
        return $this->contenu;
    }

    /**
     * @param string $contenu
     */
    public function setContenu($contenu)
    {
        $this->contenu = $contenu;
    }

    /**
     * @return mixed
     */
    public function getNbrSignalisation()
    {
        return $this->nbr_signalisation;
    }

    /**
     * @param mixed $nbr_signalisation
     */
    public function setNbrSignalisation($nbr_signalisation)
    {
        $this->nbr_signalisation = $nbr_signalisation;
    }

}

