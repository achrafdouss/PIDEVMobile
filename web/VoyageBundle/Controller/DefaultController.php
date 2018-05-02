<?php

namespace PIDEV\VoyageBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PIDEVVoyageBundle:Default:index.html.twig');
    }
}
