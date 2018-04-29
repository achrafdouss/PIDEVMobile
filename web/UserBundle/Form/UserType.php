<?php

namespace PIDEV\UserBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')->add('prenom')
            ->add('telephone',IntegerType::class)
            ->add('addresse')
            ->add('roles', ChoiceType::class, array(
                    'label' => 'Type',
                    'choices' => array(
                        'USER' => 'ROLE_CLIENT'
                    ),
                    'required' => true,
                    'multiple' => true,)
            )
       ;
    }

    public function getParent()
    {
        return 'FOS\UserBundle\Form\Type\RegistrationFormType';
    }

    public function configureOptions(OptionsResolver $resolver)
    {

    }

    public function getBlockPrefix()
    {
        return 'forum_bundle_registration_type';
    }
}
