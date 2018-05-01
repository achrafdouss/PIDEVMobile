<?php

namespace PIDEV\PrestationBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class DiplomeType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('categorie', TextType::class, array('label'=>'Categorie: ','required'=>false))
            ->add('type', TextType::class, array('label'=>'Type: ','required'=>false))
            ->add('etablissement', TextType::class, array('label'=>'Etablissement: ','required'=>false))
            ->add('dateObtention', TextType::class, array('label'=>'Annee d\'obtention: ','required'=>false));
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PIDEV\PrestationBundle\Entity\Diplome'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidev_prestationbundle_diplome';
    }


}
