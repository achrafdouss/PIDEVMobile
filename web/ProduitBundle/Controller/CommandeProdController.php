<?php

namespace PIDEV\ProduitBundle\Controller;

use PIDEV\ProduitBundle\Entity\CommandeProd;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class CommandeProdController extends Controller
{
    public function commanderAction ($idproduit){
        $produits = new CommandeProd();
        $em=$this->getDoctrine()->getManager();
        $commanderp=$em->getRepository('PIDEVProduitBundle:CommandeProd')->find($idproduit);
        $em->persist($produits);
        $em->flush();
        return $this->render('PIDEVProduitBundle:Produit:commanderproduit.html.twig',
            array(
                'commande'=>$commanderp
            ));

    }
    public function commanderrAction ($idproduit){
        $em = $this->getDoctrine()->getManager();
        $produits = new CommandeProd();

    $produit=$em->getRepository('PIDEVProduitBundle:Produit')
        ->find($idproduit);

    $user = $this->container->get('security.token_storage')->getToken()->getUser();
    $em = $this->getDoctrine()->getManager();
    $produits->setIdAcheteur($user);
    $produits->setIdProduit($produit);

    $em->persist($produits);
    $em->flush();


    return $this->render(
        'PIDEVProduitBundle:Produit:commanderproduit.html.twig',array('commande'=>$produits));}
}
