<?php

namespace PIDEV\VoyageBundle\Repository;

/**
 * VoyageRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class VoyageRepository extends \Doctrine\ORM\EntityRepository
{
    public function recherche($prixMin,$prixMax,$categorie,$destination)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.prix <= :prixMax')
            ->andWhere('r.destination = :destination')
            ->andWhere('r.categorie = :categorie')
            ->setParameters(array('prixMin'=>$prixMin,'prixMax'=>$prixMax,'destination'=>$destination,'categorie'=>$categorie))
            ->getQuery();
        return $query->getResult();
    }

    public function recherche2($prixMax,$destination,$categorie)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix <= :prixMax ')
            ->andWhere('r.destination = :destination')
            ->andWhere('r.categorie = :categorie')
            ->setParameters(array('prixMax'=>$prixMax,'destination'=>$destination,'categorie'=>$categorie))
            ->getQuery();
        return $query->getResult();
    }

    public function recherche3($prixMin,$destination,$categorie)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.destination = :destination')
            ->andWhere('r.categorie = :categorie')
            ->setParameters(array('prixMin'=>$prixMin,'destination'=>$destination,'categorie'=>$categorie))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche4($prixMin,$destination,$prixMax)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.destination = :destination')
            ->andWhere('r.prix <= :prixMax')
            ->setParameters(array('prixMin'=>$prixMin,'destination'=>$destination,'prixMax'=>$prixMax))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche5($prixMin,$categorie,$prixMax)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.categorie = :categorie')
            ->andWhere('r.prix <= :prixMax')
            ->setParameters(array('prixMin'=>$prixMin,'categorie'=>$categorie,'prixMax'=>$prixMax))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche6($categorie,$prixMax)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMax ')
            ->andWhere('r.categorie = :categorie')

            ->setParameters(array('categorie'=>$categorie,'prixMax'=>$prixMax))
            ->getQuery();
        return $query->getResult();
    }

    public function recherche7($categorie,$prixMin)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.categorie = :categorie')

            ->setParameters(array('categorie'=>$categorie,'prixMin'=>$prixMin))
            ->getQuery();
        return $query->getResult();
    }

    public function recherche8($prixMax,$prixMin)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.prix <= :prixMax ')

            ->setParameters(array('prixMax'=>$prixMax,'prixMin'=>$prixMin))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche9($destination,$prixMin)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->andWhere('r.destination = :destination ')

            ->setParameters(array('destination'=>$destination,'prixMin'=>$prixMin))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche10($destination,$prixMax)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMax ')
            ->andWhere('r.destination = :destination ')

            ->setParameters(array('destination'=>$destination,'prixMax'=>$prixMax))
            ->getQuery();
        return $query->getResult();
    }
    public function recherche11($destination,$categorie)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.categorie = :categorie ')
            ->andWhere('r.destination = :destination ')

            ->setParameters(array('destination'=>$destination,'categorie'=>$categorie))
            ->getQuery();
        return $query->getResult();
    }

    public function recherche12($prixMax)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix <= :prixMax ')
            ->setParameter('prixMax',$prixMax)
            ->getQuery();
        return $query->getResult();
    }
    public function recherche13($prixMin)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.prix >= :prixMin ')
            ->setParameter('prixMin',$prixMin)
            ->getQuery();
        return $query->getResult();
    }

    public function recherche14($categorie)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.categorie = :categorie ')
            ->setParameter('categorie',$categorie)
            ->getQuery();
        return $query->getResult();
    }
    public function recherche15($destination)
    {
        $query= VoyageRepository::createQueryBuilder('r')
            ->where('r.destination = :destination ')
            ->setParameter('destination',$destination)
            ->getQuery();
        return $query->getResult();
    }


}
