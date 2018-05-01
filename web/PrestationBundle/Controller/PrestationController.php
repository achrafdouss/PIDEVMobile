<?php
namespace PIDEV\PrestationBundle\Controller;

use FOS\UserBundle\Model\UserInterface;
use PIDEV\PrestationBundle\Entity\Commentaire;
use PIDEV\PrestationBundle\Entity\Diplome;
use PIDEV\PrestationBundle\Entity\Prestation;
use PIDEV\PrestationBundle\Form\CommentaireType;
use PIDEV\PrestationBundle\Form\DiplomeType;
use PIDEV\PrestationBundle\Form\PrestationType;
use PIDEV\UserBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\GetSetMethodNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Tests\Fixtures\ToString;
set_time_limit(180);
class PrestationController extends Controller
{
    public function getallprestationsmobileAction()
    {
        $prestations = $this->getDoctrine()->getManager()
            ->getRepository('PIDEVPrestationBundle:Prestation')
            ->findallvalide();
        foreach ($prestations as $p) {
            $p->setDateAjout($p->getDateAjout()->format('Y-m-d'));
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($prestations);
        return new JsonResponse($formatted);
    }
    public function findbycritereAction($criteria)
    {
        $prestations = $this->getDoctrine()->getManager()
            ->getRepository('PIDEVPrestationBundle:Prestation')
            ->findbycritere($criteria);
        foreach ($prestations as $p) {
            $p->setDateAjout($p->getDateAjout()->format('Y-m-d'));
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($prestations);
        return new JsonResponse($formatted);
    }
    public function findcommentairesAction($idPrest)
    {
        /* $commentaires = $this->getDoctrine()->getManager()
             ->getRepository('PrestationBundle:CommentairePrestation')
             ->findbyidprest($idPrest);
         foreach ($commentaires as $c) {
             $c->setDateAjout($c->getDateAjout()->format('Y-m-d H:i'));
         }
         $serializer = new Serializer([new ObjectNormalizer()]);
         $formatted = $serializer->normalize($commentaires);
         return new JsonResponse($formatted);
         */
        $em = $this->getDoctrine()->getManager(); // ...or getEntityManager() prior to Symfony 2.1
        $connection = $em->getConnection();
        $statement = $connection->prepare("SELECT c.id_commentaire,c.id_prestation,c.id_user,c.contenu,
  u.username, DATE_FORMAT(c.date_ajout, '%d-%m %h:%i') as date_ajout FROM commentaireprestation c inner join fos_user u on c.id_user = u.id WHERE id_prestation = :id");
        $statement->bindValue('id', $idPrest);
        $statement->execute();
        $results = $statement->fetchAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($results);
        return new JsonResponse($formatted);
    }
    public function addCommentaireAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $idUser = $request->get('idUser');
        $idPrest = $request->get('idPrest');
        $contenu = $request->get('contenu');
        $connection = $em->getConnection();
        $statement = $connection->prepare("INSERT into commentaireprestation(id_user,id_prestation,contenu,nbr_signalisation,date_ajout)
VALUES(:iduser, :idprest, :contenu,0,SYSDATE())");
        $statement->bindValue('idprest', $idPrest);
        $statement->bindValue('iduser',$idUser);
        $statement->bindValue('contenu',$contenu);
        $statement->execute();
        $done = "effectue";
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($done);
        return new JsonResponse($formatted);

    }
    public function addPrestationAction(Request $request)
    {
        $userid = $this->getUser();
        $prestation = new Prestation();
        $diplome = new Diplome();
        $time = new \DateTime();
        $form = $this->createForm(PrestationType::class);
        $form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();
        $catprestdispo = $em->getRepository("PIDEVPrestationBundle:Prestation")->findallcategories();
        $lieuxprestdispo =$em->getRepository("PIDEVPrestationBundle:Prestation")->findalllieux();
        $catdipdispo = $em->getRepository("PIDEVPrestationBundle:Diplome")->findallcategoriesdiplome();
        $etabdispo = $em->getRepository("PIDEVPrestationBundle:Diplome")->findalletabdispo();
        if ($form->isValid()) {
            $prestation->setIdInscrit($userid);
            $prestation->setDateAjout($time);
            $prestation->setValide(0);
            $prestation->setStatut(1);
            $prestation->setTitre($form->get('titre')->getData());
            $prestation->setDescription($form->get('description')->getData());
            $prestation->setLieu($form->get('lieu')->getData());
            $prestation->setCategorie($form->get('categorie')->getData());

            if($form->get('Salaire')->getData() == false)
            {
                $prestation->setSalaire(0);
            }
            if($form->get('Diplome')->getData() == false)
            {
                $em->persist($prestation);
                $em->flush();
            }
            else {
                $diplome->setCategoriediplome($form->get('categoriediplome')->getData());
                $diplome->setDateObtention($form->get('dateObtention')->getData());
                $diplome->setEtablissement($form->get('etablissement')->getData());
                $diplome->setType($form->get('type')->getData());
                $em->persist($diplome);
                $em->flush();
                $dips = $em->getRepository("PIDEVPrestationBundle:Diplome")->findbyproperties($diplome->getCategoriediplome(),$diplome->getType(),$diplome->getDateObtention());
                foreach ($dips as $d)
                {
                    $prestation->setIdDiplome($d);
                }
                $em->persist($prestation);
                $em->flush();
           }
            return $this->redirectToRoute('mesPrestations');

        }
        return $this->render('@PIDEVPrestation/Default/addPrestation.html.twig', array(
            'form' => $form->createView(),
            'catprestdispo' => $catprestdispo,
            'lieuxprestdispo' => $lieuxprestdispo,
            'catdipdispo' => $catdipdispo,
            'etabdispo' => $etabdispo));
    }
    public function indexPrestationAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $prestations = $em->getRepository("PIDEVPrestationBundle:Prestation")->findallvalide();
        return $this->render('PIDEVPrestationBundle:Default:index.html.twig', array(
            'prestations' => $prestations
        ));
    }
    public function listPrestationAction($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $commentaire = new Commentaire();
        $commentaires = $em->getRepository("PIDEVPrestationBundle:Commentaire")->findbyprestation($id);
        $formCom = $this->createForm(CommentaireType::class, $commentaire);
        $formCom->handleRequest($request);
        $userid = $this->getUser();
        $prestations = $em->getRepository("PIDEVPrestationBundle:Prestation")->findbyid($id);
        $diplomes = $em->getRepository("PIDEVPrestationBundle:Diplome")->findAll();
        if ($formCom->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $commentaire->setIdPrestation($em->getRepository("PIDEVPrestationBundle:Prestation")->find($id));
            $commentaire->setIdUser($userid);
            $commentaire->setNbrSignalisation(0);
            $commentaire->setDateAjout(new \DateTime());
            $em->persist($commentaire);
            $em->flush();
            return $this->redirectToRoute('listPrestation',array('id'=>$id));

        }
        return $this->render('@PIDEVPrestation/Default/listPrestation.html.twig', array(
            'prestations' => $prestations,
            'diplomes' => $diplomes,
            'formCom' => $formCom->createView(),
            'commentaires' => $commentaires,
            'currentuser' => $userid
        ));

    }
    public function reviewPrestationAction()
    {
        $em = $this->getDoctrine()->getManager();
        $commentaires = $em->getRepository("PIDEVPrestationBundle:Commentaire")->findsignales();
        $prestations = $em->getRepository("PIDEVPrestationBundle:Prestation")->findAll();
        return $this->render('@PIDEVPrestation/Default/reviewPrestation.html.twig', array(
            'prestations' => $prestations,
            'commentaires' => $commentaires
        ));

    }
    public function deletePrestationAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $prestation = $em->getRepository("PIDEVPrestationBundle:Prestation")->find($id);
        $em->remove($prestation);
        $em->flush();
        return $this->redirectToRoute('reviewPrestation');

    }
    public function listPrestationCategorieAction($categorie)
    {
        $em = $this->getDoctrine()->getManager();
        $prestations = $em->getRepository("PIDEVPrestationBundle:Prestation")->findbycategorie($categorie);
        return $this->render('PIDEVPrestationBundle:Default:index.html.twig', array(
            'prestations' => $prestations
        ));

    }
    public function categoriePrestationAction()
    {
        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository("PIDEVPrestationBundle:Prestation")->findcategories();
        return $this->render('PIDEVPrestationBundle:Default:categoriesPrestation.html.twig', array(
            'categories' => $categories
        ));
    }
    public function mesPrestationsAction()
    {
        $user = $this->getUser();
        $id = $user->getId();
        $em = $this->getDoctrine()->getManager();
        $prestations = $em->getRepository("PIDEVPrestationBundle:Prestation")->findbyuserid($id);
        return $this->render('PIDEVPrestationBundle:Default:mesPrestations.html.twig', array(
            'prestations' => $prestations
        ));
    }
    public function activerPrestationAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $prestation = $em->getRepository("PIDEVPrestationBundle:Prestation")->find($id);
        $prestation->setStatut(1);
        $em->flush();
        return $this->redirectToRoute('mesPrestations');
    }
    public function desactiverPrestationAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $prestation = $em->getRepository("PIDEVPrestationBundle:Prestation")->find($id);
        $prestation->setStatut(0);
        $em->flush();
        return $this->redirectToRoute('mesPrestations');
    }
    public function modifierPrestationAction(Request $request, $id)
    {

        $em = $this->getDoctrine()->getManager();
        $prestation = $em->getRepository("PIDEVPrestationBundle:Prestation")->find($id);
        $form = $this->createForm(PrestationType::class, $prestation);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $em->persist($prestation);
            $em->flush();
            return $this->redirectToRoute('listPrestation',array('id'=>$id));

        }
        return $this->render('@PIDEVPrestation/Default/modifierPrestation.html.twig', array(
            'form' => $form->createView()));

    }
    public function validerPrestationAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $prestation = $em->getRepository("PIDEVPrestationBundle:Prestation")->find($id);
        $prestation->setValide(1);
        $em->flush();
        return $this->redirectToRoute('reviewPrestation');
    }

}
