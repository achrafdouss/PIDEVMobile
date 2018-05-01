<?php

namespace PIDEV\PrestationBundle\Controller;

use PIDEV\PrestationBundle\Entity\Commentaire;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class CommentaireController extends Controller
{
    public function addSignalisationAction($id,$idc)
    {
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository("PIDEVPrestationBundle:Commentaire")->find($idc);
        $commentaire->incrementsignalisation();
        $em->persist($commentaire);
        $em->flush();
        return $this->redirectToRoute('listPrestation',array('id' => $id));
    }
    public function supprimerCommentaireAction($idc)
    {
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository("PIDEVPrestationBundle:Commentaire")->find($idc);
        $em->remove($commentaire);
        $em->flush();
        return $this->redirectToRoute('reviewPrestation');
    }
    public function deleteCommentaireAction($id,$idc)
    {
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository("PIDEVPrestationBundle:Commentaire")->find($idc);
        $em->remove($commentaire);
        $em->flush();
        return $this->redirectToRoute('listPrestation',array('id'=>$id));
    }
    public function validerCommentaireAction($idc)
    {
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository("PIDEVPrestationBundle:Commentaire")->find($idc);
        $commentaire->setNbrSignalisation(0);
        $em->persist($commentaire);
        $em->flush();
        return $this->redirectToRoute('reviewPrestation');
    }
}
