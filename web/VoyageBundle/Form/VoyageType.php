<?php

namespace PIDEV\VoyageBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType ;

use Symfony\Component\Validator\Constraints\DateTime;

class VoyageType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('categorie',ChoiceType::class,array(
                'choices'=>array('Noce'=>'Noce','Exploration'=>'Exploration',"Nocturne"=>"Nocturne"),
                'multiple'=>false,
            ))
            ->add('type',TextType::class)
            ->add('nbrPlace')
            ->add('dateDepart',Datetype::class,[

                 'widget'=>'single_text'

                ]


            )
            ->add('dateArrivee',DateType::class, [


                'widget'=>'single_text'

            ])
            ->add('prix', IntegerType::class)
            ->add('description',TextareaType::class)
            ->add('destination',TextType::class)
            ->add('photo', FileType::class, array('label' => 'Image(JPEG)','data_class'=>null))
            ->add('ajouter',SubmitType::class);


    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PIDEV\VoyageBundle\Entity\Voyage'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidev_voyagebundle_voyage';
    }
    public function getName()
    {
        return 'pidev_voyagebundle_voyage_form';
    }

}
