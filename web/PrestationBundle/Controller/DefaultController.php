<?php

namespace PIDEV\PrestationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('PIDEVPrestationBundle:Default:index.html.twig');
    }
}
