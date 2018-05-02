<?php

namespace PIDEV\ProduitBundle\Form;

use Doctrine\DBAL\Types\FloatType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\MoneyType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\HttpFoundation\File\UploadedFile;

class ProduitType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('categorieProduit',ChoiceType::class, array('choices'=>array('Immobilier'=>'Immobilier',
            'Pour La Maison Et Jardin'=>'Pour La Maison Et Jardin','Informatique Et Multimedia'=>'Informatique Et Multimedia',
            'Loisirs Et Divertissement'=>'Loisirs Et Divertissement',
            'Vehicules'=>'Vehicules', 'Habillement Et Bien Etre'=>'Habillement Et Bien Etre'),
            'multiple'=>false, 'label'=>'Categorie'))
            ->add('nom_produit')
            ->add('description',TextareaType::class)
            ->add('prixProduit',MoneyType::class)
            ->add('stockProduit',IntegerType::class)
            ->add('photo', FileType::class, array('label' => 'photo (pnj file)','data_class'=>null))
        ->add('Ajouter',SubmitType::class);
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PIDEV\ProduitBundle\Entity\Produit'
        ));
    }


    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidev_produitbundle_produit';
    }


}
