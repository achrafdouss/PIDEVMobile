<?php
/**
 * Created by PhpStorm.
 * User: bouyo
 * Date: 20/02/2018
 * Time: 21:06
 */

namespace PIDEV\ProduitBundle\Form;


use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilder;
use Symfony\Component\Form\FormBuilderInterface;

class RechercheForm extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom_produit')
            ->add('Rechercher',SubmitType::class);
    }

}