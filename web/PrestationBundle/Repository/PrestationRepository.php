<?php

namespace PIDEV\PrestationBundle\Repository;

/**
 * PrestationRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class PrestationRepository extends \Doctrine\ORM\EntityRepository
{
    public function findallvalide()
    {
        $query=$this->getEntityManager()
        ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where p.statut = 1 AND p.valide = 1");
        return $query->getResult();

    }
    public function findbycritere($recherche)
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where p.titre like :r OR p.lieu like :r OR p.categorie like :r AND p.statut = 1 AND p.valide = 1")
            ->setParameter('r','%'.$recherche.'%');
        return $query->getResult();
    }
    public function findall()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p");
        return $query->getResult();

    }
    public function findbyx($recherche)
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where p.titre like :r AND p.statut = 1 AND p.valide = 1")
            ->setParameter('r','%'.$recherche.'%');
        return $query->getResult();
    }
    public function findbyid($id)
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where p.id_prestation like :id")
            ->setParameter('id','%'.$id.'%');
        return $query->getResult();
    }
    public function findbycategorie($categorie)
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where p.categorie like :r AND p.statut = 1 AND p.valide = 1")
            ->setParameter('r','%'.$categorie.'%');
        return $query->getResult();
    }
    public function findcategories()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select DISTINCT(p.categorie) AS categorie, count(:etoile) AS nombre from PIDEVPrestationBundle:Prestation p
 WHERE p.statut = 1 AND p.valide = 1
 GROUP BY p.categorie ORDER BY nombre desc")
            ->setParameter(':etoile','*');
        return $query->getResult();
    }
    public function findbyuserid($userid)
    {
        $query=$this->getEntityManager()
            ->createQuery("Select p from PIDEVPrestationBundle:Prestation p where IDENTITY(p.id_inscrit) like :id")
            ->setParameter('id','%'.$userid.'%');
        return $query->getResult();
    }
    public function findallcategories()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select DISTINCT(p.categorie) AS categorie from PIDEVPrestationBundle:Prestation p
 WHERE  p.valide = 1
 GROUP BY p.categorie");
        return $query->getResult();
    }
    public function findalllieux()
    {
        $query=$this->getEntityManager()
            ->createQuery("Select DISTINCT(p.lieu) AS lieu from PIDEVPrestationBundle:Prestation p
 WHERE  p.valide = 1
 GROUP BY p.lieu");
        return $query->getResult();
    }
}
