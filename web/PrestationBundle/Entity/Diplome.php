<?php

namespace PIDEV\PrestationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Diplome
 *
 * @ORM\Table(name="diplome")
 * @ORM\Entity(repositoryClass="PIDEV\PrestationBundle\Repository\DiplomeRepository")
 */
class Diplome
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_diplome", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_diplome;

    /**
     * @var string
     *
     * @ORM\Column(name="categorie", type="string", length=255)
     */
    private $categoriediplome;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="etablissement", type="string", length=255)
     */
    private $etablissement;

    /**
     * @var integer
     *
     * @ORM\Column(name="date_obtention", type="integer")
     */
    private $dateObtention;


    /**
     * @return int
     */
    public function getIdDiplome()
    {
        return $this->id_diplome;
    }

    /**
     * @return string
     */
    public function getCategoriediplome()
    {
        return $this->categoriediplome;
    }

    /**
     * @param string $categoriediplome
     */
    public function setCategoriediplome($categoriediplome)
    {
        $this->categoriediplome = $categoriediplome;
    }




    /**
     * Set type
     *
     * @param string $type
     *
     * @return Diplome
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set etablissement
     *
     * @param string $etablissement
     *
     * @return Diplome
     */
    public function setEtablissement($etablissement)
    {
        $this->etablissement = $etablissement;

        return $this;
    }

    /**
     * Get etablissement
     *
     * @return string
     */
    public function getEtablissement()
    {
        return $this->etablissement;
    }

    /**
     * Set dateObtention
     *
     * @param \DateTime $dateObtention
     *
     * @return Diplome
     */
    public function setDateObtention($dateObtention)
    {
        $this->dateObtention = $dateObtention;

        return $this;
    }

    /**
     * Get dateObtention
     *
     * @return \DateTime
     */
    public function getDateObtention()
    {
        return $this->dateObtention;
    }
}

