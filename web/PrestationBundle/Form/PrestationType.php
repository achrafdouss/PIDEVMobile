<?php

namespace PIDEV\PrestationBundle\Form;

use Doctrine\DBAL\Types\FloatType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ButtonType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\FormType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\MoneyType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\Range;

class PrestationType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('titre',TextType::class, array('label'=> 'Titre: ','attr'=> array('id'=>'titre')))
           // ->add('categorie', ChoiceType::class, array('label'=> 'Categorie: ','attr'=> array('id'=>'categorie')))
            ->add('description', TextareaType::class, array('label'=> 'Description: ',
                'attr' => array('class' => 'prestationdescription')))
            ->add('lieu', TextType::class, array('label'=> 'Lieu: ','attr'=>array('list'=>'lieuxprestdispo')))
            ->add('categorie', TextType::class, array('label'=>'Categorie: ','required'=>false,'attr'=>array(
                'list' => 'catprestdispo'
            )))
            ->add('statut', HiddenType::class, array('required'=>false, 'mapped'=>false))
            ->add('Salaire', CheckboxType::class, array(
                'label' => 'Souhaitez vous spécifier votre salaire ? ',
                'mapped' => false,
                'required' => false,
                'label_attr' => array('id' => 'SalaireQuestionLabel'),))
            ->add('salaire', NumberType::class, array('label' => 'Salaire (TND): ','label_attr' => array('id' => 'salairelabel'),
                'required' => false,'constraints' => array(new Range(array('min' => 0.1,'max' => 1000)))))
            ->add('Diplome', CheckboxType::class, array(
                'label' => 'Souhaitez vous ajouter un diplome ? ',
                'mapped' => false,
                'required' => false,
                'label_attr' => array('id' => 'DiplomeQuestionLabel')
            ))
            ->add('categoriediplome', ChoiceType::class, array(
                'choices'  => array(
                    'Doctorat' => 'Doctorat',
                    'Ingénieur' => 'Ingénieur',
                    'Master' => 'Master',
                    'Expert' => 'Expert',
                    'Technicien' => 'Technicien',
                    'License' => 'License',
                    'Formation' => 'Formation'
                ),'label'=>'Type',
                'required'=>false,'mapped'=>false,'attr' => array('id' => 'categoried'),
                    'label_attr' => array('id' => 'categoriedlabel')))
            ->add('type', TextType::class,
                array('label'=>'Categorie d\'etude: ','required'=>false,'mapped'=>false,'attr' => array('id' => 'type','list'=>'catdipdispo'),
                    'label_attr' => array('id' => 'typelabel')))
            ->add('etablissement', TextType::class,
                array('label'=>'Etablissement: ','required'=>false,'mapped'=>false,'attr' => array('id' => 'etablissement','list'=>'etabdispo')
                ,'label_attr' => array('id' => 'etablissementlabel')))
            ->add('dateObtention', NumberType::class,
                array('label'=>'Annee d\'obtention: ','required'=>false,'mapped'=>false,'attr' => array('id' => 'anneeObtention')
                ,'label_attr' => array('id' => 'anneeObtentionlabel'),
                    'constraints' => array(new Range(array('min' => 1980,'max' => 2018)))))
            ->add('Ajout',SubmitType::class, array('label'=>'Ajouter'));

    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PIDEV\PrestationBundle\Entity\Prestation'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidev_prestationbundle_prestation';
    }


}
