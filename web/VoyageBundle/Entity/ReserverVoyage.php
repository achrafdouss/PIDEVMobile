<?php

namespace PIDEV\VoyageBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ReserverVoyage
 *
 * @ORM\Table(name="reserver_voyage")
 * @ORM\Entity(repositoryClass="PIDEV\VoyageBundle\Repository\ReserverVoyageRepository")
 */
class ReserverVoyage
{

    /**
     * @var int
     *
     * @ORM\Column(name="id_reservation", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */


    private $id_reservation;
    /**
     *
     * @var int
     * @ORM\ManyToOne(targetEntity="PIDEV\VoyageBundle\Entity\Voyage")
     * @ORM\JoinColumn(name="id_voyage",referencedColumnName="id_voyage" ,nullable=true, onDelete="SET NULL")
     */
    private $id_voyage;
    /**
     *
     * @var int
     * @ORM\ManyToOne(targetEntity="PIDEV\UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_inscrit",referencedColumnName="id",nullable=true, onDelete="SET NULL")
     */
    private $id_inscrit;


    /**
     * @var integer
     *
     * @ORM\Column(name="Nb_place_reserve", type="integer", nullable=false)
     */
    private $nbPlaceReserve;







    /**
     * @return int
     */
    public function getIdReservation()
    {
        return $this->id_reservation;
    }

    /**
     * @param int $id_reservation
     */
    public function setIdReservation($id_reservation)
    {
        $this->id_reservation = $id_reservation;
    }

    /**
     * @return mixed
     */
    public function getIdVoyage()
    {
        return $this->id_voyage;
    }

    /**
     * @param mixed $id_voyage
     */
    public function setIdVoyage($id_voyage)
    {
        $this->id_voyage = $id_voyage;
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
     * @return int
     */
    public function getNbPlaceReserve()
    {
        return $this->nbPlaceReserve;
    }

    /**
     * @param int $nbPlaceReserve
     */
    public function setNbPlaceReserve($nbPlaceReserve)
    {
        $this->nbPlaceReserve = $nbPlaceReserve;
    }




}

